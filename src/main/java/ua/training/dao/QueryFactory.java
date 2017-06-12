package ua.training.dao;

import ua.training.domain.Entity;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryFactory {
  public static final String ID = "id";
  public static final String SELECT_ALL = "Select * from ";

    public static <T extends Entity> String getQuery(QueryTypes type, String className) {
        if (type.equals(QueryTypes.FIND_ALL)) {
            return SELECT_ALL + className;
        } else {
            return null;
        }
    }

    public static <T extends Entity> String getQuery(QueryTypes type, String className, String condition) {
        if (type.equals(QueryTypes.FIND_BY_ATTRIBUTE)) {
            return SELECT_ALL + className +  condition;
        } else if (type.equals(QueryTypes.DELETE)){
            return String.format("DELETE from %1$s %2$s",className, condition);
        }
        return null;
    }

  public static <T extends Entity> String getQuery(QueryTypes type, T entity)  {
        String className = entity.getClass().getSimpleName().toLowerCase();
        HashMap<String, Object> objectMap = new HashMap<>();
        try {
            PropertyDescriptor[] prop = Introspector.getBeanInfo(entity.getClass(), Object.class).getPropertyDescriptors();
            for (PropertyDescriptor p : prop) {
                try {
                    objectMap.put(p.getName(), p.getReadMethod().invoke(entity));
                } catch (InvocationTargetException e) {
                    continue;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        HashMap<String, String> map = getCorrectColumnNames(objectMap, className);
            if (type.equals(QueryTypes.CREATE)) {
                return composeCreateQuery(map, className);
            } else if (type.equals(QueryTypes.UPDATE)) {
                return composeUpdateQuery(map, className);
            }
            return null;
        }

    private static String composeUpdateQuery(HashMap<String,String> map, String className) {
        StringBuffer request = new StringBuffer("UPDATE ").append(className).append(" Set ");
        StringBuffer condition = new StringBuffer(" where ");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().contains(ID)) {
                condition.append(entry.getKey())
                        .append(" = ")
                        .append(entry.getValue());
            } else {
                request.append(entry.getKey()).append(" = ")
                        .append(convertIntoDBValue(entry.getValue()));
            }
        }
        request.delete(request.length()-2, request.length());
        return request.append(condition).toString();
    }

    private static String composeCreateQuery(HashMap<String,String> map, String className) {
            StringBuffer request = new StringBuffer("Insert INTO ");
            StringBuffer values = new StringBuffer(" values (");
            request.append(className).append(" (");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                request.append(entry.getKey()).append(", ");
                values.append(convertIntoDBValue(entry.getValue()));
            }
            values.delete((values.length()-2), (values.length())).append(" )");
            request.delete((request.length()-2), (request.length()))
                    .append(" ) ")
                    .append(values);
            return request.toString();
        }

    private static HashMap<String, String> getCorrectColumnNames(HashMap<String,Object> map, String className) {
        HashMap<String, String> stringMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            if (entry.getValue() instanceof Entity) {
                stringMap.put(makeKeys(entry.getKey(), className),
                        String.valueOf(((Entity) entry.getValue()).getId()));
            } else {
                stringMap.put(makeKeys(entry.getKey(), className), entry.getValue().toString());
            }
        }
        return stringMap;
    }

    private static String makeKeys(String value, String className) {
        Pattern pattern = Pattern.compile("[A-Z]\\w+");
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            String word = "_" + matcher.group().toLowerCase();
            return matcher.replaceAll(word);
        }
        if (className.contains("bookingrequest") && value.equals(ID) ) {
            return "request_id";
        }
        if (value.equals(ID)) {
            return String.format("%1$s_%2$s",className, ID);
        }
        return value;
    }
    private static String convertIntoDBValue(String unknown) {
        Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}.*");
        Matcher matcher = datePattern.matcher(unknown);
        if (matcher.find()) {
            return String.format("date('%1$s'), ",unknown);
        }
        Pattern booleanPattern = Pattern.compile("true|false");
        Matcher boolMatcher = booleanPattern.matcher(unknown);
        if (boolMatcher.find()) {
            return unknown.replaceAll("true", "1, ").replaceAll("false", "0, ");
        }
        try {
            Long.parseLong(unknown);
            return String.format("%1$s, ",unknown);
        } catch (NumberFormatException e) {
            return String.format("'%1$s', ",unknown);
        }
    }

}

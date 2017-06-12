package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String PATTERN_NAME = "^[A-Z][a-z]+[-']?$";
    private static final String PATTERN_LOGIN = "^\\w+[-_']?$";
    private static final String PATTERN_PHONE_NUMBER = "\\d{2}-\\d{3}-\\d{3}-\\d{4}";
    private static final String PATTERN_EMAIL = "\\w+[-_+.]*\\w+?@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}";
    private static final String PATTERN_CITY = "[A-Z][a-z]{2,}";
    private static final String PATTERN_STREET = "[A-Z][a-z]{4,}";
    private static final String PATTERN_HOUSE = "[0-9]+";
    private static final String PATTERN_DATE = "\\d{4}-\\d{2}-\\d{2}";
    private static final String PATTERN_PASSWORD = "\\w{6}";

    public static Timestamp parseDate(String value) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = date.getTime();
        return new Timestamp(time);
    }

    public static boolean validateOneField(HttpServletRequest request, String fieldName) {
        String value = request.getParameter(fieldName);
        Validator validator = new Validator();
        if (value == null || value.isEmpty()) {
            return true;
        }
        String regEx = validator.getPatterns().get(fieldName);
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches()) {
            request.setAttribute(fieldName, value);
        } else {
            request.setAttribute("invalid_" + fieldName, "invalid." + fieldName);
        }
        return matcher.matches();
    }

    private HashMap<String, String> getPatterns() {
        HashMap<String, String> patterns = new HashMap<>();
        patterns.put("firstname", PATTERN_NAME);
        patterns.put("lastname", PATTERN_NAME);
        patterns.put("login", PATTERN_LOGIN);
        patterns.put("password", PATTERN_PASSWORD);
        patterns.put("email", PATTERN_EMAIL);
        patterns.put("mobilenumber", PATTERN_PHONE_NUMBER);
        patterns.put("birthdate", PATTERN_DATE);
        patterns.put("city", PATTERN_CITY);
        patterns.put("street", PATTERN_STREET);
        patterns.put("house", PATTERN_HOUSE);
        patterns.put("appartment", PATTERN_HOUSE);
        return patterns;
    }

}

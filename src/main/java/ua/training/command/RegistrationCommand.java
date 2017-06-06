package ua.training.command;

import ua.training.domain.User;
import ua.training.exception.FailedOperationException;
import ua.training.service.RegistrationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationCommand implements ICommand {
    private RegistrationService registrationService = RegistrationService.getInstance();
    private static final String PATTERN_NAME = "^[A-Z][a-z]+[-']?$";
    private static final String PATTERN_LOGIN = "^\\w+[-_']?$";
    private static final String PATTERN_PHONE_NUMBER = "^\\d{2}-\\d{3}-\\d{3}-\\d{4}$";
    private static final String PATTERN_EMAIL = "^\\w+[-_+.]?@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String PATTERN_CITY = "^[A-Z][a-z]{2,}$";
    private static final String PATTERN_STREET = "^[A-Z][a-z]{4,}$";
    private static final String PATTERN_HOUSE = "^[0-9]+$";
    private static final String PATTERN_DATE = "//d{4}-//d{2}-//d{2}";
    private static final String PATTERN_PASSWORD = "\\w{6}";
    private static final String FIRST_NAME_FIELD = "firstname";
    private static final String LAST_NAME_FIELD = "lastname";
    private static final String LOGIN_NAME_FIELD = "login";
    private static final String PASSWORD_FIELD = "password";
    private static final String EMAIL_NAME_FIELD = "email";
    private static final String MOBILE_NAME_FIELD = "mobilenumber";
    private static final String BIRTH_DATE_FIELD = "birthdate";
    private static final String CITY_FIELD = "city";
    private static final String STREET_FIELD = "street";
    private static final String HOUSE_FIELD = "house";
    private static final String APPARTMENT_FIELD = "appartment";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String page = null;

        User user = new User();
        user.setFirstName(request.getAttribute("firstname").toString());
        user.setLogin(request.getAttribute("login").toString());
        try {
            if (registrationService.registerNewUser(user)) {
                request.setAttribute("user", user);
                page = Config.getInstance().getProperty(Config.MAIN);
            }
        } catch (FailedOperationException e) {
            request.setAttribute("user", user );
            request.setAttribute("error", e.getMessage());
            page = Config.getInstance().getProperty(Config.REGISTRATION);
        } catch (Exception e) {
            request.setAttribute("error", Message.getInstance().getProperty(Message.LOGIN_ERROR));
            page = Config.getInstance().getProperty(Config.ERROR);
        }
        return page;
    }

    private boolean validateAll(HttpServletRequest request, User user) {
        boolean isValid;
        isValid = validateOneField(request, FIRST_NAME_FIELD);
        isValid = isValid && validateOneField(request, LAST_NAME_FIELD);
        isValid = isValid && validateOneField(request, LOGIN_NAME_FIELD);
        isValid = isValid && validateOneField(request, PASSWORD_FIELD);
        isValid = isValid && validateOneField(request, EMAIL_NAME_FIELD);
        isValid = isValid && validateOneField(request, MOBILE_NAME_FIELD);
        isValid = isValid && validateOneField(request, BIRTH_DATE_FIELD);
        isValid = isValid && validateOneField(request, CITY_FIELD);
        isValid = isValid && validateOneField(request, HOUSE_FIELD);
        isValid = isValid && validateOneField(request, APPARTMENT_FIELD);
        isValid = isValid && validateOneField(request, STREET_FIELD);
        return isValid;
    }

    private boolean validateOneField(HttpServletRequest request, String fieldName) {
        String value = (String) request.getAttribute(fieldName);
        String regEx = getPatterns().get(fieldName);
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches()) {
            request.setAttribute(FIRST_NAME_FIELD, value);
        } else {
            request.setAttribute(FIRST_NAME_FIELD + "_invalid", FIRST_NAME_FIELD + "_invalid" );
        }
        return matcher.matches();
    }

    private  HashMap<String, String> getPatterns() {
        HashMap<String, String> patterns = new HashMap<>();
        patterns.put("firstname", PATTERN_NAME);
        patterns.put("lastname", PATTERN_NAME);
        patterns.put("login", PATTERN_LOGIN);
        patterns.put("password", PATTERN_PASSWORD);
        patterns.put("email", PATTERN_EMAIL);
        patterns.put("mobilenumber", PATTERN_PHONE_NUMBER);
        patterns.put("checkedpassword", PATTERN_PASSWORD);
        patterns.put("birthdate", PATTERN_DATE);
        patterns.put("city", PATTERN_CITY);
        patterns.put("street", PATTERN_STREET);
        patterns.put("house", PATTERN_HOUSE);
        patterns.put("appartment", PATTERN_HOUSE);
        return patterns;
    }
 }

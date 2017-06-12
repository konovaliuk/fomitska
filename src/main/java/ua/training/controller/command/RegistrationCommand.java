package ua.training.controller.command;

import ua.training.controller.Config;
import ua.training.domain.Password;
import ua.training.domain.User;
import ua.training.service.AuthorizationService;
import ua.training.service.RegistrationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class RegistrationCommand extends BaseCommand {
    private RegistrationService registrationService = RegistrationService.getInstance();
    private AuthorizationService authorizationService = AuthorizationService.getInstance();
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
    public String subCommandExecute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String page;
            if (validateAll(request)) {
                Long idUser = registrationService.registerNewUser(setUserInfo(request));
                String password = request.getParameter(PASSWORD_FIELD);
                setPassword(password, idUser);
                request.setAttribute("registerSuccess", "reg.congratulation");
                page = Config.getInstance().getProperty(Config.LOGIN);
            } else {
                page = Config.getInstance().getProperty(Config.REGISTRATION);
            }
        return page;
    }

    private boolean validateAll(HttpServletRequest request) {
        boolean isValid = true;
        if (!Validator.validateOneField(request, FIRST_NAME_FIELD)) {
            isValid = false;
        }
        if (!Validator.validateOneField(request, LAST_NAME_FIELD)) {
            isValid = false;
        }
        if (!Validator.validateOneField(request, LOGIN_NAME_FIELD)) {
            isValid = false;
        }
        if (!Validator.validateOneField(request, PASSWORD_FIELD)) {
            isValid = false;
        }
        if (!Validator.validateOneField(request, EMAIL_NAME_FIELD)) {
            isValid = false;
        }
        if (!Validator.validateOneField(request, MOBILE_NAME_FIELD)) {
            isValid = false;
        }
        if (!Validator.validateOneField(request, BIRTH_DATE_FIELD)) {
            isValid = false;
        }
        if (!Validator.validateOneField(request, CITY_FIELD)) {
            isValid = false;
        }
        if (!Validator.validateOneField(request, HOUSE_FIELD)) {
            isValid = false;
        }
        if (!Validator.validateOneField(request, APPARTMENT_FIELD)) {
            isValid = false;
        }
        if (!Validator.validateOneField(request, STREET_FIELD)) {
            isValid = false;
        }
        if (!checkUniqueFields(request)) {
            isValid = false;
        }
        return isValid;
    }

    private boolean checkUniqueFields(HttpServletRequest request) {
        String email = request.getParameter(EMAIL_NAME_FIELD);
        String login = request.getParameter(LOGIN_NAME_FIELD);
        boolean isEmailUnique = registrationService.isEmailUnique(email);
        if (!isEmailUnique) {
            request.setAttribute("emailNonUnique", "email.nonunique");
            request.removeAttribute("email");
        }
        boolean isLoginUnique = registrationService.isLoginUnigue(login);
        if (!isLoginUnique) {
            request.setAttribute("loginNonUnique", "login.nonunique");
            request.removeAttribute("login");
        }
        return isEmailUnique && isLoginUnique;
    }


    private User setUserInfo(HttpServletRequest request) {
        User user = User.newBuilder()
                .setFirstName( request.getParameter(FIRST_NAME_FIELD))
                .setLastName( request.getParameter(LAST_NAME_FIELD))
                .setLogin( request.getParameter(LOGIN_NAME_FIELD))
                .setEmail( request.getParameter(EMAIL_NAME_FIELD))
                .setMobileNumber( request.getParameter(MOBILE_NAME_FIELD))
                .setActive(1)
                .setFkUsertype(authorizationService.getUserID())
                .build();
        if (!request.getParameter(BIRTH_DATE_FIELD).isEmpty()) {
            user.setBirthDt(Validator.parseDate(request.getParameter(BIRTH_DATE_FIELD)));

        }
        if (!request.getParameter(CITY_FIELD).isEmpty()) {
            user.setCity( request.getParameter(CITY_FIELD));
        }
        if (!request.getParameter(STREET_FIELD).isEmpty()) {
            user.setCity( request.getParameter(STREET_FIELD));
        }
        if (!request.getParameter(HOUSE_FIELD).isEmpty()) {
            user.setCity( request.getParameter(HOUSE_FIELD));
        }
        if (!request.getParameter(APPARTMENT_FIELD).isEmpty()) {
            user.setCity( request.getParameter(APPARTMENT_FIELD));
        }
        return user;
    }

    private void setPassword(String passValue, Long idUser) throws SQLException {
        Password password = new Password();
        password.setPassword(passValue);
        password.setFkUser(new User(idUser));
        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, +1);
        password.setExpiryDt(new Timestamp(calendar.getTimeInMillis()));
        password.setAttempt(0L);
        authorizationService.createPassword(password);
    }


 }

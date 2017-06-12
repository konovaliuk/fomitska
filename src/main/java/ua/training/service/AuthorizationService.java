package ua.training.service;

import ua.training.dao.implementations.PasswordDAO;
import ua.training.dao.implementations.UserDao;
import ua.training.dao.implementations.UserTypeDAO;
import ua.training.dao.factories.BaseFactory;
import ua.training.dao.factories.DAOFactory;
import ua.training.dao.DBTypes;
import ua.training.domain.Password;
import ua.training.domain.User;
import ua.training.exception.FailedOperationException;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AuthorizationService {
    private static volatile AuthorizationService instance;
    private BaseFactory factory;
    private UserDao userDao;
    private UserTypeDAO userTypeDAO;
    private PasswordDAO passwordDAO;

    public static AuthorizationService getInstance() {
        if (instance == null) {
            synchronized(AuthorizationService.class) {
                if (instance == null) {
                    instance = new AuthorizationService();
                }
            }
        }
        return instance;
    }

    public Long getAdminID() {
        return userTypeDAO.getRoleID("Admin");
    }

    public Long getUserID() {
        return userTypeDAO.getRoleID("User");
    }

    private AuthorizationService() {
        factory = DAOFactory.getFactory(DBTypes.MySQL);
        userDao = factory.createUserDAO();
        userTypeDAO = factory.createUserTypeDAO();
        passwordDAO = factory.createPasswordDAO();
    }

    public User authentificate(String login, String password) throws FailedOperationException {
        User user = userDao.findByLogin(login);
        if (user == null) {
            throw new FailedOperationException("login.not.found");
        }
        Password expectedPassword = passwordDAO.getExpectedPassword(user.getId());
        if (expectedPassword == null) {
            throw new FailedOperationException("password.expired");
        }
        if (checkPassword(expectedPassword, password, user)) {
            return user;
        }
        return null;
        //return (checkPassword(expectedPassword, password, user));
    }

    private boolean checkPassword(Password expected, String actual, User user) throws FailedOperationException {
        if (expected.getPassword().equals(actual)) {
            return true;
        } else {
            ResourceBundle resource = ResourceBundle.getBundle("config");
            Long attempts = Long.parseLong(resource.getString("attempt_counter"));
            Long counter = expected.getAttempt();
            counter++;
            if (counter <= attempts) {
                expected.setAttempt(counter);
                passwordDAO.update(expected);
                throw new FailedOperationException("wrong.login.pass");
            } else {
                user.setActive(0);
                userDao.update(user);
                throw new FailedOperationException("deactivation");
            }
        }
    }
    public Long createPassword(Password password) throws SQLException {
        return passwordDAO.create(password);
    }
    private void setNewPasswordDueExpiration(String newPassword) {

    }

}

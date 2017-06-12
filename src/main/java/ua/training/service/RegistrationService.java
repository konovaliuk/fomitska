package ua.training.service;

import ua.training.dao.DBTypes;
import ua.training.dao.factories.BaseFactory;
import ua.training.dao.factories.DAOFactory;
import ua.training.dao.implementations.PasswordDAO;
import ua.training.dao.implementations.UserDao;
import ua.training.dao.implementations.UserTypeDAO;
import ua.training.domain.Password;
import ua.training.domain.User;

import java.sql.SQLException;

public class RegistrationService {
    private static volatile RegistrationService instance;
    private BaseFactory factory;
    private UserDao userDao;
    private UserTypeDAO userTypeDAO;
    private PasswordDAO passwordDAO;

    public static RegistrationService getInstance() {
        if (instance == null) {
            synchronized(RegistrationService.class) {
                if (instance == null) {
                    instance = new RegistrationService();
                }
            }
        }
        return instance;
    }

    private RegistrationService() {
        factory = DAOFactory.getFactory(DBTypes.MySQL);
        userDao = factory.createUserDAO();
        userTypeDAO = factory.createUserTypeDAO();
        passwordDAO = factory.createPasswordDAO();
    }

    public Long registerNewUser(User user) throws SQLException {
        return userDao.create(user);
    }

    public Long createNewPassword(Password password) throws SQLException {
        return passwordDAO.create(password);
    }

    public boolean isEmailUnique(String email) {
       return userDao.getUniqueAttribute("email", email);
    }

    public boolean isLoginUnigue(String login) {
        return userDao.getUniqueAttribute("login", login);
    }
}

package ua.training.service;

import ua.training.dao.implementations.UserDao;
import ua.training.dao.implementations.UserTypeDAO;
import ua.training.dao.factories.BaseFactory;
import ua.training.dao.factories.DAOFactory;
import ua.training.dao.DBTypes;
import ua.training.domain.User;
import ua.training.exception.FailedOperationException;

public class RegistrationService {
    private static volatile RegistrationService instance;
    private BaseFactory factory;
    private UserDao userDao;
    private UserTypeDAO userTypeDAO;

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
    }

    public boolean registerNewUser(User user) throws FailedOperationException {
       if (checkUniqueFields(user)) {
            userDao.create(user);
            return true;
        }
        return false;
       }

    private boolean checkUniqueFields(User user) throws FailedOperationException {
      return userDao.isLoginUnigue(user.getLogin()) && userDao.isEmailUnique(user.getEmail()) ? true : false;

    }
}

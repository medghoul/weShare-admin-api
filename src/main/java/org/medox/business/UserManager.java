package org.medox.business;

import org.medox.dao.UserDao;
import org.medox.exception.GenericException;
import org.medox.model.User;

public class UserManager {
    public static User getUser(Long id) {
        UserDao dao = new UserDao();
        User user = dao.getUser(id);
        dao.close();
        return user;
    }
    public static User getUserByEmailPassword(String  email, String password){
        UserDao dao=new UserDao();
        User user=dao.getUserByEmailPassword(email,password);
        dao.close();
        return user;
    }
    public static User findUserByEmail(String email){
        UserDao dao=new UserDao();
        User user=dao.findUserByEmail(email);
        dao.close();
        return user;
    }

    public static Long saveUser(User user) {
        UserDao dao = new UserDao();
        dao.saveUser(user);
        dao.close();
        return user.getId();
    }

    public static void resetPassword(Long id, String password) {
        User db=UserManager.getUser(id);
        if(db==null){
            throw new GenericException("User non esiste");
        }
        db.setPassword(password);
        UserDao dao=new UserDao();
        dao.saveUser(db);
        dao.close();
    }
}

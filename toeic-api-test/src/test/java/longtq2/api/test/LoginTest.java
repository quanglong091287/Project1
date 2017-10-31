package longtq2.api.test;

import longtq2.core.dao.UserDao;
import longtq2.core.daoimpl.UserDaoImpl;
import longtq2.core.persistence.enity.UserEntity;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class LoginTest {
    private final Logger log = Logger.getLogger(this.getClass());
    @Test
    public void checkIsUserExits(){
        UserDao userDao = new UserDaoImpl();
        String name ="tranquanglong";
        String password ="123456";
        UserEntity entity = userDao.isUserExits(name, password);
        if(entity!= null){
            log.error("login succes");
        }else{
            log.error("login fail");
        }
    }
    @Test
    public void checkFindRoleByUser(){
        UserDao userDao = new UserDaoImpl();
        String name ="tranquanglong";
        String password ="123456";
        UserEntity entity = userDao.findRoleByUser(name, password);
        log.error(entity.getRoleEntity().getRoleId() +"-" + entity.getRoleEntity().getName());
    }

}

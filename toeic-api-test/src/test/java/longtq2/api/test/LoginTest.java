package longtq2.api.test;

import longtq2.core.dao.UserDao;
import longtq2.core.daoimpl.UserDaoImpl;
import longtq2.core.persistence.enity.UserEntity;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import longtq2.core.dao.RoleDao;
import longtq2.core.daoimpl.RoleDaoImpl;
import longtq2.core.persistence.enity.RoleEntity;
import java.util.ArrayList;
import java.util.List;

public class LoginTest {
    private final Logger log = Logger.getLogger(this.getClass());
    @Test
    public  void checkFindAll(){
        RoleDao roleDao = new RoleDaoImpl();
        List<RoleEntity> list =  roleDao.findAll();
    }

    @Test
    public void checkUpdateRole(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(1);
        entity.setName("ADMIN");
        roleDao.update(entity);
    }

    @Test
    public void checkSaveRole(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(3);
        entity.setName("MANAGER");
        roleDao.save(entity);
    }

    @Test
    public void checkFindById(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity entity =  roleDao.finfById(1);
    }

    @Test
    public void checkFindByProperty(){
        RoleDao roleDao = new RoleDaoImpl();
        String property = null;
        Object value = null;
        String sortExpression = null;
        String sortDirection = null;
        Object[] objects = roleDao.findByProperty(property, value, sortExpression, sortDirection, 0, 2);
    }

    @Test
    public  void checkDelete(){
        List<Integer> listId = new ArrayList<Integer>();
        listId.add(1);
        listId.add(2);
        RoleDao roleDao = new RoleDaoImpl();
        Integer count = roleDao.delete(listId);

    }
    @Test
    public void checkIsUserExits(){
        UserDao userDao = new UserDaoImpl();
        String name ="tranquanglong";
        String password ="123456";
        UserEntity entity = userDao.findUserByUsernameAndPassword(name, password);
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
        UserEntity entity = userDao.findUserByUsernameAndPassword(name, password);
        log.error(entity.getRoleEntity().getRoleId() +"-" + entity.getRoleEntity().getName());
    }

}

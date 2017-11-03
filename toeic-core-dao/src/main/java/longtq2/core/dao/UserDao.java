package longtq2.core.dao;

import longtq2.core.data.dao.GenericDao;
import longtq2.core.persistence.enity.UserEntity;

public interface UserDao extends GenericDao<Integer, UserEntity> {
    UserEntity findUserByUsernameAndPassword(String name , String password);
}

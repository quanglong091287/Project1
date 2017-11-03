package longtq2.core.service.impl;

import longtq2.core.dao.UserDao;
import longtq2.core.daoimpl.UserDaoImpl;
import longtq2.core.dto.UserDTO;
import longtq2.core.persistence.enity.UserEntity;
import longtq2.core.service.UserService;
import longtq2.core.utils.UserBeanUtil;

public class UserServiceImpl implements UserService {

    public UserDTO isUserExits(UserDTO dto) {
        UserDao userDao = new UserDaoImpl();
        UserEntity entity = userDao.findUserByUsernameAndPassword(dto.getName(), dto.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }

    public UserDTO findRoleByUser(UserDTO dto) {
        UserDao userDao = new UserDaoImpl();
        UserEntity entity = userDao.findUserByUsernameAndPassword(dto.getName(), dto.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }
}

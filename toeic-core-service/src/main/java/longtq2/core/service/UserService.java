package longtq2.core.service;

import longtq2.core.dto.UserDTO;
import longtq2.core.persistence.enity.UserEntity;

public interface UserService {
    UserDTO isUserExits(UserDTO dto);
    UserDTO findRoleByUser(UserDTO dto);
}

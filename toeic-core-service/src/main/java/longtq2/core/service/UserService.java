package longtq2.core.service;

import longtq2.core.dto.UserDTO;

public interface UserService {
    UserDTO isUserExits(UserDTO dto);
    UserDTO findRoleByUser(UserDTO dto);
}

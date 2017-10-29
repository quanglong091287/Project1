package longtq2.core.utils;

import longtq2.core.dto.UserDTO;
import longtq2.core.persistence.enity.UserEntity;

// ham chuyen doi tu entity sang dto va nguoc lai
public class UserBeanUtil {
    // ham chuyen doi tu entity sang dto
    public static UserDTO entity2Dto(UserEntity userEntity) {
        UserDTO dto = new UserDTO();
        dto.setUserId(userEntity.getUserId());
        dto.setName(userEntity.getName());
        dto.setPassword(userEntity.getPassword());
        dto.setFullName(userEntity.getFullName());
        dto.setCreatedDate(userEntity.getCreatedDate());
        dto.setRoleDTO(RoleBeanUtil.entity2Dto(userEntity.getRoleEntity()));
        return dto;
    }
    // ham chuyen doi tu dto sang entity
    public static UserEntity dto2Entity(UserDTO userDTO) {
        UserEntity entity = new UserEntity();
        entity.setUserId(userDTO.getUserId());
        entity.setName(userDTO.getName());
        entity.setPassword(userDTO.getPassword());
        entity.setFullName(userDTO.getFullName());
        entity.setCreatedDate(userDTO.getCreatedDate());
        entity.setRoleEntity(RoleBeanUtil.dto2Entity(userDTO.getRoleDTO()));
        return entity;
    }
}


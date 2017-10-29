package longtq2.core.utils;

import longtq2.core.dto.RoleDTO;
import longtq2.core.persistence.enity.RoleEntity;

public class RoleBeanUtil {
    public static RoleDTO entity2Dto(RoleEntity roleEntity) {
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(roleEntity.getRoleId());
        dto.setName(roleEntity.getName());
        return dto;
    }
    public static RoleEntity dto2Entity(RoleDTO roleDTO) {
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(roleDTO.getRoleId());
        entity.setName(roleDTO.getName());
        return entity;
    }
}

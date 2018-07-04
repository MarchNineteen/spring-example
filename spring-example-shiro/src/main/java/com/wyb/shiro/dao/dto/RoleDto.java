package com.wyb.shiro.dao.dto;

import com.wyb.shiro.dao.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author Kunzite
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto extends BaseDto{

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 描述
     */
    private String description;

    public RoleDto(RoleDto roleDto) {
        BeanUtils.copyProperties(roleDto, this);
    }
}
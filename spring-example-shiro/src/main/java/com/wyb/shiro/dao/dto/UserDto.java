package com.wyb.shiro.dao.dto;

import com.wyb.shiro.dao.dto.base.BaseDto;
import com.wyb.shiro.dao.model.UserDo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author Kunzite
 */
@Data
@AllArgsConstructor
public class UserDto extends BaseDto {

    private static final long serialVersionUID = -5530231434340956140L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 角色
     */
    private List<RoleDto> roleDtoList;

    public UserDto(UserDo userDo) {
        BeanUtils.copyProperties(userDo, this);
    }

    public UserDto() {
    }
}

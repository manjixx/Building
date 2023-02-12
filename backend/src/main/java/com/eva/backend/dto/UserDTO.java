package com.eva.backend.dto;

import com.eva.backend.dto.base.OutputConverter;
import com.eva.backend.entity.Role;
import com.eva.backend.entity.User;
import lombok.Data;

import java.util.List;

/**
 * Author：
 * Date：2023/1/1811:26
 * Desc:
 */
@Data
public class UserDTO implements OutputConverter<UserDTO, User> {
    private int id;

    private String username;

    private String name;

    private boolean status;

    private List<Role> roles;
}

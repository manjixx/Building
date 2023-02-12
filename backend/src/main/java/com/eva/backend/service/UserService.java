package com.eva.backend.service;

import com.eva.backend.dao.UserDAO;
import com.eva.backend.dto.UserDTO;
import com.eva.backend.entity.Role;
import com.eva.backend.entity.User;
import com.eva.backend.entity.UserRole;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author：
 * Date：2022/12/623:22
 * Desc:
 */
@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    @Lazy
    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    public boolean isAdmin(int userId) {
        List<UserRole> users = userRoleService.findByUserId(userId);
        int userRoleId = users.get(0).getRoleId();
        if(userRoleId == 1){
            return true;
        }
            return false;
    }

    public List<UserDTO> list() {
        List<User> users = userDAO.findAll();

        // Find all roles in DB to enable JPA persistence context.
        // List<AdminRole> allRoles = adminRoleService.findAll();

        List<UserDTO> userDTOS = users
                .stream().map(user -> (UserDTO) new UserDTO().convertFrom(user)).collect(Collectors.toList());

        userDTOS.forEach(user -> {
            List<Role> roles = roleService.listRolesByUser(user.getUsername());
            user.setRoles(roles);
        });

        return userDTOS;
    }

    public boolean isExist(String username) {
        User user = getByName(username);
        return null != user;
    }

    public User getByName(String username) {
        return userDAO.findByUsername(username);
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public User get(String username, String password){
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public int register(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String name = user.getName();


        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        user.setEnable(true);
        user.setGmtCreate(new Date(System.currentTimeMillis()));
        user.setGmtModified(new Date(System.currentTimeMillis()));

        if (username.equals("") || password.equals("")) {
            return 0;
        }

        boolean exist = isExist(username);

        if (exist) {
            return 2;
        }

        // 默认生成 16 位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();

        user.setSalt(salt);
        user.setPassword(encodedPassword);

        userDAO.save(user);

        return 1;
    }

    public void updateUserStatus(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        userInDB.setEnable(user.isEnable());
        userInDB.setGmtModified(new Date(System.currentTimeMillis()));
        userDAO.save(userInDB);
    }

    public User resetPassword(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        userInDB.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", "123456", salt, times).toString();
        userInDB.setPassword(encodedPassword);
        userInDB.setGmtModified(new Date(System.currentTimeMillis()));
        return userDAO.save(userInDB);
    }

    public void editUser(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        userInDB.setName(user.getName());
        userInDB.setGmtModified(new Date(System.currentTimeMillis()));
        userDAO.save(userInDB);
        userRoleService.saveRoleChanges(userInDB.getId(), user.getRoles());
    }

    public void deleteById(int id) {
        userDAO.deleteById(id);
    }


    public void add(User user) {
        userDAO.save(user);
        System.out.println("保存用户成功");
    }

    public int getUserIdByName(String username) {
        User user = userDAO.getByUsername(username);
        if(user == null){
            return -1;
        }else{
            return user.getId();
        }
    }


    public boolean deleteRole(int roleId) throws Exception {
        try {
            userDAO.deleteById(roleId);
            userRoleService.deleteAllByUserId(roleId);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}

package com.peerfund.peerfundapp.services;


import com.peerfund.peerfundapp.dto.UserDto;
import com.peerfund.peerfundapp.entities.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    String signUp(UserDto userDto);

    String updateProfile(Long userId,UserDto userDto);

    User getUser(Long id);

    List<User> membersOfGroup(Long groupId);

    User findByEmail(String email);

    void userBecomeAdmin(Long id,Long groupId);

}

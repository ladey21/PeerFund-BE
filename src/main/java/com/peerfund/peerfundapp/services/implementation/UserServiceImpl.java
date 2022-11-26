package com.peerfund.peerfundapp.services.implementation;


import com.peerfund.peerfundapp.dto.UserDto;
import com.peerfund.peerfundapp.entities.ContributionGroup;
import com.peerfund.peerfundapp.exceptions.BadRequestException;
import com.peerfund.peerfundapp.entities.Enums.RoleType;
import com.peerfund.peerfundapp.entities.Role;
import com.peerfund.peerfundapp.entities.User;
import com.peerfund.peerfundapp.repositories.ContributionGroupRepository;
import com.peerfund.peerfundapp.repositories.UserRepository;
import com.peerfund.peerfundapp.security.JwtTokenProvider;
import com.peerfund.peerfundapp.services.UserService;


import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private final PasswordEncoder passwordEncoder;
    private final ContributionGroupRepository groupRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;

    public UserServiceImpl(PasswordEncoder passwordEncoder, ContributionGroupRepository groupRepository, ModelMapper modelMapper, UserRepository userRepository, JwtTokenProvider tokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.groupRepository = groupRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }


    @Override
    public String signUp(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmailAddress())) {
            throw new BadRequestException("Error: Email is already taken!");
        } else{
            User user = modelMapper.map(userDto,User.class);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setRoles(Set.of(new Role(RoleType.Member)));
            userRepository.save(user);
        }
        return "created";
    }

    @Override
    public String updateProfile(Long userId,UserDto userDto) {
        User user = getUser(userId);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(user);
        return "updated";
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public List<User> membersOfGroup(Long groupId) {
        return groupRepository.findContributionGroupById(groupId).getMembers();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void userBecomeAdmin(Long id,Long groupId) {
       ContributionGroup group = groupRepository.findContributionGroupById(groupId);
       group.setAdmin(userRepository.findUserById(id));
       groupRepository.save(group);
    }

}

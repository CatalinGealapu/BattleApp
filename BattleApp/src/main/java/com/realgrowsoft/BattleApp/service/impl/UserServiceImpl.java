package com.realgrowsoft.BattleApp.service.impl;


import com.realgrowsoft.BattleApp.bo.User;
import com.realgrowsoft.BattleApp.exception.ResourceNotFoundException;
import com.realgrowsoft.BattleApp.payload.UserDto;
import com.realgrowsoft.BattleApp.payload.UserPasswordDto;
import com.realgrowsoft.BattleApp.repository.UserRepository;
import com.realgrowsoft.BattleApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserDto getUser(Long userId) {
        User user=userRepository.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        return modelMapper.map(user,UserDto.class);
    }
    @Override
    public List<UserDto> getAllUsers() {
        List<User>users=userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }
    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user=userRepository.findById(userId). orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        User updateUser=userRepository.save(user);
        return modelMapper.map(updateUser,UserDto.class);
    }

    @Override
    public UserPasswordDto updateUserPassword(UserPasswordDto userPasswordDto, String newPassword) {
        //
        Long userId=userPasswordDto.getId();
        User user=userRepository.findById(userId).orElse(null);
        if (user!=null){
            String encodedPassword=passwordEncoder.encode(newPassword);
            user.setPassword(encodedPassword);
            userRepository.save(user);
        }
        return modelMapper.map(userPasswordDto,UserPasswordDto.class) ;

    }

    @Override
    public Boolean invalidUser(Long userId) {
        return null;
    }

    @Override
    public Boolean validUser(Long userId) {
        return null;
    }

    @Override
    public List<UserDto> getAllValidUser() {
        return null;
    }
}

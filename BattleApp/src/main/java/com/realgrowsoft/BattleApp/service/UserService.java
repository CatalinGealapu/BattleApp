package com.realgrowsoft.BattleApp.service;

import com.realgrowsoft.BattleApp.payload.UserDto;
import com.realgrowsoft.BattleApp.payload.UserPasswordDto;

import java.util.List;

public interface UserService {

    UserDto getUser(Long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto userDto,Long userId);
   UserPasswordDto updateUserPassword(UserPasswordDto userPasswordDto, String newPassword);
    Boolean invalidUser(Long userId);
    Boolean validUser(Long userId);
    List<UserDto> getAllValidUser();




}

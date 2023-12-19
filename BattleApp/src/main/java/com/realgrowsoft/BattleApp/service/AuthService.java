package com.realgrowsoft.BattleApp.service;


import com.realgrowsoft.BattleApp.payload.LoginDto;
import com.realgrowsoft.BattleApp.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}

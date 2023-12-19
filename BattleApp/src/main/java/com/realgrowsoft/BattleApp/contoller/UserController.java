package com.realgrowsoft.BattleApp.contoller;


import com.realgrowsoft.BattleApp.payload.UserDto;
import com.realgrowsoft.BattleApp.payload.UserPasswordDto;
import com.realgrowsoft.BattleApp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long userId) {
        UserDto userDto = userService.getUser(userId);
        return ResponseEntity.ok(userDto);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,
                                              @PathVariable("id") Long userId) {
        return ResponseEntity.ok(userService.updateUser(userDto, userId));
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updatePassword")
    public ResponseEntity<UserPasswordDto> updateUserPassword(@RequestBody UserPasswordDto userPasswordDto,
                                                              @RequestParam Long userId) {
        UserDto userDto = userService.getUser(userId);
        if (userPasswordDto == null) {
            UserPasswordDto errorResponse = new UserPasswordDto();
            errorResponse.setId(userId);
            errorResponse.setPassword(null);
            errorResponse.setEmail("Adresa de email nu corespunde utilizatorului.");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        if (!userPasswordDto.getEmail().equals(userPasswordDto.getEmail())) {
            UserPasswordDto errorResponse = new UserPasswordDto();
            errorResponse.setId(userId);
            errorResponse.setPassword(null);
            errorResponse.setEmail("Adresa de email nu corespunde utilizatorului.");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        return ResponseEntity.ok(userService.updateUserPassword(userPasswordDto, userPasswordDto.getPassword()));

//        return ResponseEntity.ok(userPasswordDto);
    }


}

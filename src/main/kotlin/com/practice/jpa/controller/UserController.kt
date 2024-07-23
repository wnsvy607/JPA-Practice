package com.practice.jpa.controller

import com.practice.jpa.dto.UserSignUpRequest
import com.practice.jpa.dto.UserUpdateRequest
import com.practice.jpa.entity.User
import com.practice.jpa.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    val userService: UserService
) {

    @PostMapping("/users")
    fun signUp(
        @RequestBody userSignUpRequest: UserSignUpRequest
    ) {
        userService.signUp(userSignUpRequest)
    }

    @PatchMapping("/users")
    fun update(
        @RequestBody userUpdateRequest: UserUpdateRequest
    ): String {
        userService.update(userUpdateRequest)
        return "success"
    }

    @GetMapping("/users")
    fun getAll(): List<User> {
        return userService.getAll()
    }


}

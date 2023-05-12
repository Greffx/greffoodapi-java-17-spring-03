package com.greff.foodapi.api.controller;

import com.greff.foodapi.api.assembler.UserAssembler;
import com.greff.foodapi.api.assembler.UserRequestDisassembler;
import com.greff.foodapi.api.model.request.UserPasswordUpdateRequest;
import com.greff.foodapi.api.model.request.UserRequest;
import com.greff.foodapi.api.model.request.UserUpdateRequest;
import com.greff.foodapi.api.model.response.UserResponse;
import com.greff.foodapi.domain.usecase.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserAssembler userAssembler;
    private final UserRequestDisassembler userRequestDisassembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Valid UserRequest userRequest) {
        var user = userRequestDisassembler.toDomainObject(userRequest);

        userService.create(user);

        return userAssembler.toModel(user);
    }

    @PutMapping("/{id}")
    public UserResponse update(@RequestBody @Valid UserUpdateRequest userUpdateRequest, @PathVariable Long id) {
        var user = userService.findById(id);

        userRequestDisassembler.updateUserDomainObject(userUpdateRequest, user);
        userService.update(user);

        return userAssembler.toModel(user);
    }

    @PutMapping("/{id}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void passwordUpdate(@RequestBody @Valid UserPasswordUpdateRequest userPasswordUpdateRequest, @PathVariable Long id) {
        var user = userService.findById(id);
        var password = userPasswordUpdateRequest.getCurrentPassword();
        var passwordVerification = user.currentPasswordIsSimilarTo(password);

        if (passwordVerification)
            userRequestDisassembler.updateUserPasswordDomainObject(userPasswordUpdateRequest, user);

        userService.updatePassword(user, passwordVerification);
    }

    @GetMapping
    public List<UserResponse> findAll() {
        var users = userService.findAll();

        return userAssembler.toCollectionModel(users);
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        var user = userService.findById(id);

        return userAssembler.toModel(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
package com.project.IIITBuyNSell.controllers;

import com.project.IIITBuyNSell.dto.UserDTO;
import com.project.IIITBuyNSell.dto.response.BaseResponse;
import com.project.IIITBuyNSell.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{emailId}")
    public BaseResponse getUser(@PathVariable("emailId") String emailId) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            UserDTO user = userService.getUser(emailId);
            baseResponse.setData(user);
        } catch (Exception e) {
            log.error("Exception occurred while fetching data for user : {}", emailId, e);
        }
        return baseResponse;
    }

    @PostMapping("/{emailId}")
    public BaseResponse createUser(@PathVariable("emailId") String emailId, @RequestBody UserDTO userDTO) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            userService.saveUser(userDTO);
        } catch (Exception e) {
            log.error("Exception occured while saving data for user : {}", emailId, e);
        }
        return baseResponse;
    }

    @PostMapping("/")
    public BaseResponse createMultipleUser(@RequestBody List<UserDTO> userDTOs) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            userService.saveAllUsers(userDTOs);
        } catch (Exception e) {
            log.error("Exception occurred while saving users : {}", userDTOs, e);
        }
        return baseResponse;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}

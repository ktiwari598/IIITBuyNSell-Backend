package com.project.IIITBuyNSell.service;

import com.project.IIITBuyNSell.dto.UserDTO;
import com.project.IIITBuyNSell.exceptions.ResourceNotFoundException;
import com.project.IIITBuyNSell.model.User;
import com.project.IIITBuyNSell.repositories.UserRepository;
import com.project.IIITBuyNSell.utils.ClassConverterUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClassConverterUtils classConverterUtils;

    public void saveAllUsers(List<UserDTO> usersList) {
        List<User> users = usersList.stream().map(userDTO -> classConverterUtils.dtoToClass(userDTO)).toList();
        userRepository.saveAll(users);
    }

    public void saveUser(UserDTO userDTO) {
        userRepository.save(classConverterUtils.dtoToClass(userDTO));
    }

    public UserDTO getUser(String emailId) {
        User user = userRepository.findById(emailId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "emailId", emailId));
        return classConverterUtils.classToDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(classConverterUtils::classToDTO)
                .toList();
    }
}

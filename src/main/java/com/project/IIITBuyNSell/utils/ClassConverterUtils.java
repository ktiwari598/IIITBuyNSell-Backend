package com.project.IIITBuyNSell.utils;

import com.project.IIITBuyNSell.dto.ItemDTO;
import com.project.IIITBuyNSell.dto.UserDTO;
import com.project.IIITBuyNSell.model.Item;
import com.project.IIITBuyNSell.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassConverterUtils {

    @Autowired
    private ModelMapper modelMapper;

    public ItemDTO classToDTO(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }

    public Item dtoToClass(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, Item.class);
    }

    public UserDTO classToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User dtoToClass(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}

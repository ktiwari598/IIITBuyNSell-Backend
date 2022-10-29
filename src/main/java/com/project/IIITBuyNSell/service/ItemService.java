package com.project.IIITBuyNSell.service;

import com.project.IIITBuyNSell.dto.ItemDTO;
import com.project.IIITBuyNSell.exceptions.GlobalExceptionHandler;
import com.project.IIITBuyNSell.exceptions.ResourceNotFoundException;
import com.project.IIITBuyNSell.model.Item;
import com.project.IIITBuyNSell.repositories.ItemRepository;
import com.project.IIITBuyNSell.repositories.UserRepository;
import com.project.IIITBuyNSell.utils.ClassConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassConverterUtils classConverterUtils;

    public void saveItem(List<ItemDTO> itemList) {
        if (!CollectionUtils.isEmpty(itemList)) {
            List<Item> items = itemList.stream().map(itemDTO -> classConverterUtils.dtoToClass(itemDTO))
                    .collect(Collectors.toList());
            items.forEach(item -> {
                userRepository.save(item.getUser());
                itemRepository.save(item);
            });
        } else {
            throw new GlobalExceptionHandler.UserDTONotFoundException();
        }
    }

    public ItemDTO getItem(BigInteger itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "itemId", itemId.toString()));
        return classConverterUtils.classToDTO(item);
    }

    public ItemDTO updateItem(BigInteger itemId, ItemDTO itemDTO) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "itemId", itemId.toString()));
        item.setItemName(itemDTO.getItemName());
        item.setItemPrice(itemDTO.getItemPrice());
        item.setItemCategory(itemDTO.getItemCategory());
        item.setItemDescription(itemDTO.getItemDescription());
        item.setIsLostFound(itemDTO.getIsLostFound());
        return classConverterUtils.classToDTO(itemRepository.save(item));
    }

    public void deleteItem(BigInteger itemId) {
        Item item = itemRepository.findById(itemId).
                orElseThrow(() -> new ResourceNotFoundException("Item", "itemId", itemId.toString()));
        itemRepository.delete(item);
    }

}

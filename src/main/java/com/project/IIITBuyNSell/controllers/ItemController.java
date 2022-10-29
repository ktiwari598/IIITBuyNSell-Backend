package com.project.IIITBuyNSell.controllers;

import com.project.IIITBuyNSell.dto.ItemDTO;
import com.project.IIITBuyNSell.dto.response.BaseResponse;
import com.project.IIITBuyNSell.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/item")
public class ItemController {

    private static final String SUCCESS = "Success";

    @Autowired
    private ItemService itemService;

    @GetMapping("/{itemId}")
    public ResponseEntity<BaseResponse> getItem(@PathVariable("itemId") BigInteger itemId) {
        ItemDTO item = itemService.getItem(itemId);
        BaseResponse baseResponse = BaseResponse.builder()
                .data(item)
                .message(SUCCESS)
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<BaseResponse> createItem(@RequestBody ItemDTO itemDTO) {
        itemService.saveItem(List.of(itemDTO));
        return new ResponseEntity<>(new BaseResponse(), HttpStatus.OK);
    }

    @PostMapping("/multipleItems")
    public BaseResponse createMultipleItems(@RequestBody List<ItemDTO> itemDTOs) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            itemService.saveItem(itemDTOs);
        } catch (Exception e) {
            log.error("Exception occured while saving users : {}", itemDTOs, e);
        }
        return baseResponse;
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<BaseResponse> updateItem(@PathVariable("itemId") BigInteger itemId, @RequestBody ItemDTO itemDTO) {
        ItemDTO item = itemService.updateItem(itemId, itemDTO);
        BaseResponse baseResponse = BaseResponse.builder()
                .data(item)
                .message(SUCCESS)
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<BaseResponse> deleteItem(@PathVariable("itemId") BigInteger itemId) {
        itemService.deleteItem(itemId);
        BaseResponse baseResponse = BaseResponse.builder()
                .message(SUCCESS)
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}

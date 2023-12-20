package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.GroceryItemService;

@RestController
public class GroceryItemController {

    @Autowired
    private GroceryItemService groceryItemService;

    @GetMapping("/add-grocery-item")
    public String addGroceryItem() {
        groceryItemService.addOrUpdateGroceryItem();
        return "Item agregado o actualizado";
    }

}


package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.ItemRepository;
import com.example.model.GroceryItem;

@Service
public class GroceryItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void addOrUpdateGroceryItem() {
        GroceryItem item = new GroceryItem();
        item.setName("Pan");
        item.setQuantity(15);
        item.setCategory("Panadería");

        // Guardar el item en la base de datos
        itemRepository.save(item);
    }

    public void printAllGroceryItems() {
        List<GroceryItem> items = itemRepository.findAll();
        items.forEach(item -> System.out.println(item));
    }
}


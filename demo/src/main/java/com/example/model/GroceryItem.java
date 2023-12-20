package com.example.model;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("groceryitems")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class GroceryItem {

    @Id
    private String id;
    private String name;
    private int quantity;
    private String category;

    @Override
    public String toString() {
        return "GroceryItem{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", quantity=" + quantity +
               ", category='" + category + '\'' +
               '}';
    }

}
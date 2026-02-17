package com.gustcustodio.to_do_list_api.controllers;

import com.gustcustodio.to_do_list_api.dtos.ItemDTO;
import com.gustcustodio.to_do_list_api.services.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/todos")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ItemDTO> getSingleToDoItem(@PathVariable Long id) {
        ItemDTO itemDTO = itemService.getSingleToDoItem(id);
        return ResponseEntity.ok(itemDTO);
    }

}

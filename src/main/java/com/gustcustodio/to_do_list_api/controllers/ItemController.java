package com.gustcustodio.to_do_list_api.controllers;

import com.gustcustodio.to_do_list_api.dtos.ItemDTO;
import com.gustcustodio.to_do_list_api.services.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

    @GetMapping
    public ResponseEntity<Page<ItemDTO>> getAllToDoItems(Pageable pageable) {
        Page<ItemDTO> result = itemService.getAllToDoItems(pageable);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ItemDTO> createToDoItem(@RequestBody ItemDTO itemDTO) {
        itemDTO = itemService.createToDoItem(itemDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(itemDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(itemDTO);
    }

}

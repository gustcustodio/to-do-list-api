package com.gustcustodio.to_do_list_api.controllers;

import com.gustcustodio.to_do_list_api.dtos.ItemDTO;
import com.gustcustodio.to_do_list_api.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<ItemDTO> createToDoItem(@Valid @RequestBody ItemDTO itemDTO) {
        itemDTO = itemService.createToDoItem(itemDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(itemDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(itemDTO);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ItemDTO> updateToDoItem(@PathVariable Long id, @Valid @RequestBody ItemDTO itemDTO) {
        itemDTO = itemService.updateToDoItem(id, itemDTO);
        return ResponseEntity.ok(itemDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteToDoItem(@PathVariable Long id) {
        itemService.deleteToDoItem(id);
        return ResponseEntity.noContent().build();
    }

}

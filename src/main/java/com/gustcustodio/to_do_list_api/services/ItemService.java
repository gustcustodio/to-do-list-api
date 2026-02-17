package com.gustcustodio.to_do_list_api.services;

import com.gustcustodio.to_do_list_api.dtos.ItemDTO;
import com.gustcustodio.to_do_list_api.entities.Item;
import com.gustcustodio.to_do_list_api.mappers.ItemMapper;
import com.gustcustodio.to_do_list_api.repositories.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    @Transactional(readOnly = true)
    public ItemDTO getSingleToDoItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow();
        return itemMapper.convertEntityToDto(item);
    }

}

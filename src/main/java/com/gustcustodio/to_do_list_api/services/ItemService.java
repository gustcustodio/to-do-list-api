package com.gustcustodio.to_do_list_api.services;

import com.gustcustodio.to_do_list_api.dtos.ItemDTO;
import com.gustcustodio.to_do_list_api.entities.Item;
import com.gustcustodio.to_do_list_api.mappers.ItemMapper;
import com.gustcustodio.to_do_list_api.repositories.ItemRepository;
import com.gustcustodio.to_do_list_api.services.exceptions.DatabaseException;
import com.gustcustodio.to_do_list_api.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return itemMapper.convertEntityToDto(item);
    }

    @Transactional(readOnly = true)
    public Page<ItemDTO> getAllToDoItems(Pageable pageable) {
        Page<Item> items = itemRepository.findAll(pageable);
        return items.map(item -> itemMapper.convertEntityToDto(item));
    }

    @Transactional
    public ItemDTO createToDoItem(ItemDTO itemDTO) {
        Item item = itemMapper.convertDtoToEntity(itemDTO);
        item = itemRepository.save(item);
        return itemMapper.convertEntityToDto(item);
    }

    @Transactional
    public ItemDTO updateToDoItem(Long id, ItemDTO itemDTO) {
        try {
            Item item = itemRepository.getReferenceById(id);
            itemMapper.updateEntityFromDto(itemDTO, item);
            item = itemRepository.save(item);
            return itemMapper.convertEntityToDto(item);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteToDoItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found");
        }
        try {
            itemRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity failure");
        }
    }

}

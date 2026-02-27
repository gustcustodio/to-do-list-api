package com.gustcustodio.to_do_list_api.services;

import com.gustcustodio.to_do_list_api.dtos.ItemDTO;
import com.gustcustodio.to_do_list_api.entities.Item;
import com.gustcustodio.to_do_list_api.entities.User;
import com.gustcustodio.to_do_list_api.mappers.ItemMapper;
import com.gustcustodio.to_do_list_api.repositories.ItemRepository;
import com.gustcustodio.to_do_list_api.services.exceptions.ForbiddenException;
import com.gustcustodio.to_do_list_api.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final UserService userService;
    private final AuthService authService;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper, UserService userService, AuthService authService) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.userService = userService;
        this.authService = authService;
    }

    @Transactional(readOnly = true)
    public ItemDTO getSingleToDoItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        authService.validateUser(item.getUser().getId());
        return itemMapper.convertEntityToDto(item);
    }

    @Transactional(readOnly = true)
    public Page<ItemDTO> getAllToDoItems(Integer page, Integer limit) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        User user = userService.authenticated();
        Page<Item> items = itemRepository.findByUserId(user.getId(), pageRequest);
        return items.map(item -> itemMapper.convertEntityToDto(item));
    }

    @Transactional
    public ItemDTO createToDoItem(ItemDTO itemDTO) {
        Item item = itemMapper.convertDtoToEntity(itemDTO);
        User user = userService.authenticated();
        item.setUser(user);
        item = itemRepository.save(item);
        return itemMapper.convertEntityToDto(item);
    }

    @Transactional
    public ItemDTO updateToDoItem(Long id, ItemDTO itemDTO) {
        try {
            User user = userService.authenticated();
            Item item = itemRepository.findByItemIdAndUserId(id, user.getId()).orElseThrow(() -> new ForbiddenException("Forbidden"));
            itemMapper.updateEntityFromDto(itemDTO, item);
            item = itemRepository.save(item);
            return itemMapper.convertEntityToDto(item);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    @Transactional
    public void deleteToDoItem(Long id) {
        User user = userService.authenticated();
        Item item = itemRepository.findByItemIdWithUser(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        if (!item.getUser().getId().equals(user.getId())) {
            throw new ForbiddenException("Forbidden");
        }
        itemRepository.delete(item);
    }

}

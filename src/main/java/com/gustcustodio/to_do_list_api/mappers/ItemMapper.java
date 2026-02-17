package com.gustcustodio.to_do_list_api.mappers;

import com.gustcustodio.to_do_list_api.dtos.ItemDTO;
import com.gustcustodio.to_do_list_api.entities.Item;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ItemMapper {

    Item convertDtoToEntity(ItemDTO itemDTO);

    ItemDTO convertEntityToDto(Item item);

    List<ItemDTO> convertEntityListToDtoList(List<Item> items);

    void updateEntityFromDto(ItemDTO itemDTO, @MappingTarget Item item);

}

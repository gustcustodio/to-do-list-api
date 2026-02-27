package com.gustcustodio.to_do_list_api.repositories;

import com.gustcustodio.to_do_list_api.entities.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findByUserId(Long userId, PageRequest pageRequest);

    @Query("SELECT item FROM Item item JOIN FETCH item.user WHERE item.id = :id AND item.user.id = :userId")
    Optional<Item> findByItemIdAndUserId(Long id, Long userId);

    @Query("SELECT i FROM Item i JOIN FETCH i.user WHERE i.id = :id")
    Optional<Item> findByItemIdWithUser(Long id);

}

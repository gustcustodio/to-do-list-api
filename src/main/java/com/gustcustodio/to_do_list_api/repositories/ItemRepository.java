package com.gustcustodio.to_do_list_api.repositories;

import com.gustcustodio.to_do_list_api.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}

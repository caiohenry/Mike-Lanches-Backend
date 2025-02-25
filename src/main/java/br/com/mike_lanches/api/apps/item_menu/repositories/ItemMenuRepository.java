package br.com.mike_lanches.api.apps.item_menu.repositories;

// Imports
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// Item Menu Model Reference Implementation
import br.com.mike_lanches.api.apps.item_menu.models.ItemMenuModel;


// Create repository - item menu
@Repository
public interface ItemMenuRepository extends CrudRepository<ItemMenuModel, Long>{

    Page<ItemMenuModel> findAll(Pageable pageable);
    
}

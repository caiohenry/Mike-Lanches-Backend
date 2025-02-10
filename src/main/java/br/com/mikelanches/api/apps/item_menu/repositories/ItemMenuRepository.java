package br.com.mikelanches.api.apps.item_menu.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// Imports
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


// Item Menu Model Reference Implementation
import br.com.mikelanches.api.apps.item_menu.models.ItemMenuModel;


// Create a new repository by item menu
@Repository
public interface ItemMenuRepository extends CrudRepository<ItemMenuModel, Long> {

    Page<ItemMenuModel> findAll(Pageable pageable);
   
}

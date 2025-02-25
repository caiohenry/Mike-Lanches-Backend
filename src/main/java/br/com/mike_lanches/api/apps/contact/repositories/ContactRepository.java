package br.com.mike_lanches.api.apps.contact.repositories;

// Imports
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// Contact Model Reference Implementation
import br.com.mike_lanches.api.apps.contact.models.ContactModel;


// Create repository - contact
@Repository
public interface ContactRepository extends CrudRepository<ContactModel, Long>{

    Page<ContactModel> findAll(Pageable pageable);
    
}

package br.com.mike_lanches.api.apps.user.repositories;


// Imports
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// User Model Reference Implementation
import br.com.mike_lanches.api.apps.user.models.UserModel;


// Create repository - user
@Repository
public interface UserRepository extends CrudRepository<UserModel, Long>{

    Page<UserModel> findAll(Pageable pageable);

    UserModel findByEmail(String email);
    
}

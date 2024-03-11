package com.info902.projet.repository;

import com.info902.projet.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByPseudo(String pseudo);

    Optional<User> findByPseudoAndPassword(String pseudo, String password);
}

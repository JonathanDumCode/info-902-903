package com.info902.projet.repository;

import com.info902.projet.model.Assistant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistantRepository extends CrudRepository<Assistant, Long> {
}

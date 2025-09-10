package com.postgresql.demo.domain.repo;

import com.postgresql.demo.domain.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GameRepo extends JpaRepository<Game,Long> {
}

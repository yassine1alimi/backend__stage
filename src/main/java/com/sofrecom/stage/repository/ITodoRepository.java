package com.sofrecom.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import com.sofrecom.stage.models.Todo;


@Repository
@RestController
public interface ITodoRepository extends JpaRepository<Todo, Long> {

}

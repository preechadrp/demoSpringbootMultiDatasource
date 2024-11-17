package com.example.demoSpringbootMultiDatasource.db2repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoSpringbootMultiDatasource.db2.User2;

@Repository
public interface User2Repos extends JpaRepository<User2, Integer> {

}

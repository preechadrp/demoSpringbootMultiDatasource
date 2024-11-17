package com.example.demoSpringbootMultiDatasource.db1repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoSpringbootMultiDatasource.db1.Member;

@Repository
public interface MemberRepos extends JpaRepository<Member, Long> {

}

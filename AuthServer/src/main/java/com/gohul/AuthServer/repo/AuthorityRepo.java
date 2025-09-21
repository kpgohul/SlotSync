package com.gohul.AuthServer.repo;

import com.gohul.AuthServer.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, Integer> {

    Authority findDistinctByName(String name);
}

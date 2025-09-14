package com.gohul.AuthServer.repo;

import com.gohul.AuthServer.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority, Integer> {

    Authority findDistinctByName(String name);
}

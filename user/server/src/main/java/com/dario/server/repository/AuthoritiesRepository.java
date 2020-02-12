package com.dario.server.repository;

import com.dario.server.dataObject.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities,String> {

}

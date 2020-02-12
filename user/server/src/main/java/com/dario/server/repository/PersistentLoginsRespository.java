package com.dario.server.repository;

import com.dario.server.dataObject.PersistentLogins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersistentLoginsRespository extends JpaRepository<PersistentLogins,String> {
}

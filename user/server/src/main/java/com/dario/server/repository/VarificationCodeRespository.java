package com.dario.server.repository;

import com.dario.server.dataObject.VarificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VarificationCodeRespository extends JpaRepository<VarificationCode,Integer> {

    public List<VarificationCode> findAllByPhone(String phone);

}

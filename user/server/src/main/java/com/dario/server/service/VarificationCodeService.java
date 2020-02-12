package com.dario.server.service;

import com.dario.server.dataObject.VarificationCode;

import java.util.List;

public interface VarificationCodeService {

    public void insertVarification(VarificationCode varificationCode) throws Exception;

    public List<VarificationCode> findAllByPhone(String phone) throws Exception;

}

package com.dario.server.service.impl;

import com.dario.server.dataObject.VarificationCode;
import com.dario.server.repository.VarificationCodeRespository;
import com.dario.server.service.VarificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VarifyCodeServiceImpl implements VarificationCodeService {

    @Autowired
    private VarificationCodeRespository varificationCodeRespository;

    @Override
    public void insertVarification(VarificationCode varificationCode) throws Exception {
        varificationCodeRespository.save(varificationCode);
    }

    @Override
    public List<VarificationCode> findAllByPhone(String phone) throws Exception {
        return varificationCodeRespository.findAllByPhone(phone);
    }
}

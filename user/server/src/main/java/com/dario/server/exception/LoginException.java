package com.dario.server.exception;

import com.dario.server.unums.LoginStatusEnum;

public class LoginException extends RuntimeException {

    private Integer code;

    public LoginException(Integer code,String messages){
        super(messages);
        this.code=code;
    }

    public LoginException(LoginStatusEnum loginStatusEnum){
        super(loginStatusEnum.getMessages());
        this.code=loginStatusEnum.getCode();
    }
}

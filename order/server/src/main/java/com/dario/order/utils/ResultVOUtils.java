package com.dario.order.utils;

import com.dario.order.VO.ResultVO;

public class ResultVOUtils {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        resultVO.setT(object);
        return resultVO;
    }

    public static ResultVO error(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(-1);
        resultVO.setMessage("错误");
        resultVO.setT(object);
        return resultVO;
    }

}

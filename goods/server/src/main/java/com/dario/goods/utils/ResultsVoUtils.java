package com.dario.goods.utils;

import com.dario.goods.VO.ResultVO;

public class ResultsVoUtils {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO error(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setMsg("失败");
        resultVO.setCode(-1);
        return resultVO;
    }
}

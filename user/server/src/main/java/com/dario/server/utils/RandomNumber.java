package com.dario.server.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class RandomNumber {

    /*生成size位随机数*/
    public static String getRandomNumber(int size){
        Random random=new Random();
        StringBuilder stringBuilder=new StringBuilder(size);
        for (int i=0;i<size;i++){
            stringBuilder.append(random.nextInt(10));
        }
        return  stringBuilder.toString();
    }
}

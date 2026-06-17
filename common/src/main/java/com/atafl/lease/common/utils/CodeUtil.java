package com.atafl.lease.common.utils;

import java.util.Random;

public class CodeUtil {
    public static String getRandomCode(Integer length){
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            int num = random.nextInt(10);
            code.append(num);
        }
        return code.toString();
    };
}

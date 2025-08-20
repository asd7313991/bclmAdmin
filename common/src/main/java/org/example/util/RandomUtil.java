package org.example.util;

import java.util.Random;

public class RandomUtil {
    public static String generateCode6(){
        Random random = new Random();
        return Integer.toString(100000 + random.nextInt(900000));
    }

    public static String generateCode4(){
        Random random = new Random();
        return Integer.toString(1000 + random.nextInt(9000));
    }
}

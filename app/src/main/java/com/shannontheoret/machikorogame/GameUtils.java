package com.shannontheoret.machikorogame;

import java.util.Random;

/**
 * Created by shannonbrown on 2017-11-23.
 */

public class GameUtils {

    public static Integer generateRollValue(){
        Random random = new Random();
        return random.nextInt(6) + 1;
    }


}

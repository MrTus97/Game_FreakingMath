package com.example.tuphanthanh.game_freakingmath;

import android.util.Log;

import java.util.Random;

public class GenerateLevel {
    public static final int EASY =10;
    public static final int MEDIUM = 20;
    public static final int HARD = 150;
    public static LevelModel generateLevel(int count){

        LevelModel level = new LevelModel();
        Random rand = new Random();
        //Get current difficult level
        if (count <= EASY){
            level.difficultLevel =1;
        }else if (count <=MEDIUM){
            level.difficultLevel=2;
        }else if (count <=HARD){
            level.difficultLevel=3;
        }else{
            level.difficultLevel=3;
        }
        //random operation
        level.operator = rand.nextInt(level.difficultLevel);

        level.x = rand.nextInt(level.arrMaxOperatorValue[level.difficultLevel])-1;
        level.y = rand.nextInt(level.arrMaxOperatorValue[level.difficultLevel])-1;
        level.correctWrong = rand.nextBoolean();

        if (level.correctWrong == false){
            switch (level.operator){
                case LevelModel.ADD:
                    do{
                        level.result = rand.nextInt(level.arrMaxOperatorValue[level.difficultLevel]);
                    }while (level.result == (level.x +level.y));
                    break;
                case LevelModel.SUB:
                    do{
                        level.result = rand.nextInt(level.arrMaxOperatorValue[level.difficultLevel]);
                    }while (level.result == (level.x -level.y));
                    break;
                case LevelModel.MUL:
                    do{
                        level.result = rand.nextInt(level.arrMaxOperatorValue[level.difficultLevel]);
                    }while (level.result == (level.x *level.y));
                    break;
                case LevelModel.DIV:
                    do{
                        level.result = rand.nextInt(level.arrMaxOperatorValue[level.difficultLevel]);
                    }while (level.result == (level.x /level.y));
                    break;
                default:
                    break;

            }
        }else {
            switch (level.operator){
                case LevelModel.ADD:
                    level.result = level.x +level.y;
                    break;
                case LevelModel.SUB:
                    level.result = level.x -level.y;
                    break;
                case LevelModel.MUL:
                    level.result = level.x *level.y;
                    break;
                case LevelModel.DIV:
                    level.result = level.x /level.y;
                    break;
                default:
                    break;
            }
        }
        level.strOperator = String.valueOf(level.x)+level.arrOperatorText[level.operator]+String.valueOf(level.y);
        level.strResult = LevelModel.EQU_TEXT + String.valueOf(level.result);
        return level;
    }
}

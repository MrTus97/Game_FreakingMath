package com.example.tuphanthanh.game_freakingmath;

public class LevelModel {
    public int difficultLevel = 1;
    //operator
    public static final int ADD =0;
    public static final int SUB =1;
    public static final int MUL =2;
    public static final int DIV =3;

    //operator text
    public static final String ADD_TEXT ="+";
    public static final String SUB_TEXT ="-";
    public static final String MUL_TEXT ="x";
    public static final String DIV_TEXT =":";
    public static final String EQU_TEXT ="=";
    public static final String[] arrOperatorText = {ADD_TEXT,SUB_TEXT,MUL_TEXT,DIV_TEXT};
    //Components of fomular
    public int x;
    public int y;
    public int result;
    public int operator;
    public boolean correctWrong;
    public String strOperator ="";
    public String strResult = "";
    //max value of operator depend on level : easy, medium, hard
    public static final int MAX_OPERATOR_LEVEL_EASY = 5;
    public static final int MAX_OPERATOR_LEVEL_MEDIUM = 10;
    public static final int MAX_OPERATOR_LEVEL_HARD = 50;
    public static final int[] arrMaxOperatorValue = {MAX_OPERATOR_LEVEL_EASY,MAX_OPERATOR_LEVEL_MEDIUM,MAX_OPERATOR_LEVEL_HARD};
}

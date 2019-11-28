package com.ifmo.lesson19;

public class TestRef implements Cloneable{

    private static int cloneCount;
    private String string = "111111";
    private int integer = 1;

    public static int getCloneCount(){
        return cloneCount;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        ++cloneCount;
        return new TestRef();
    }
}

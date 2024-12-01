package org.example;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        String s1 = "John";
        String s2 = new String ("John");
        HashMap<String, Integer> map = new HashMap<>();
        map.put(s1,20);
        map.put(s2,30);
        System.out.println( s1.hashCode()+ "  " + s2.hashCode() + " " +map.size() + "  " );



    }
}
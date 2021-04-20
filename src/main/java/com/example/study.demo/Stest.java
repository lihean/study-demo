package com.example.study.demo;

import java.io.*;

public class Stest {

    private static final long serialVersionUID = -3213123128321L;

    public int num = 1390;

    public static void main(String[] args) {

        try {
            FileOutputStream fos = new FileOutputStream("D:\\serialize.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Serialize serialize = new Serialize();

            oos.writeObject(serialize);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

package AutismFortress;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.Thread;
import javax.swing.JOptionPane;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Test {
    public static void main(String[] arg) throws InterruptedException {
        Scanner scan = new Scanner(System.in);

        String[] stats;
        int a= 0;
        do {
            a = (int)(Math.random()*6+1);
            System.out.println(a);
            Thread.sleep(1*1000);

        }while (a != 2);
        String temp = scan.nextLine();
        System.out.print(temp);
    }

}

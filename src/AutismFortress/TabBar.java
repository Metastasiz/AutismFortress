package AutismFortress;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class TabBar {
    public static void main(String[] arg){
        ChatLog msg = new ChatLog();
        msg.switchSave();
        msg.refresh("asdf");
        msg.refresh("asdf");
        msg.getLog();
    }
}
class MapHelp{
    char[][] biomeHelp = new char[5][109];
    char[][] biomeMode = new char[5][109];
    char[][] blank = new char[5][109];
    public void append(String text, int desY, char[][] out){
        for (int i = 1; i < out[0].length-1; i++) {
            out[desY][i] = text.charAt(i-1);
            if (i==text.length()) break;
        }
    }
    public void setup(char[][] save){
        for (int i = 0; i < save.length; i++) {
            for (int j = 0; j < save[0].length; j++) {
                save[i][j] = ' ';
            }
        }
    }
    public void print(char[][] save){
        for (int i = 0; i < save.length; i++) {
            for (int j = 0; j < save[0].length; j++) {
                System.out.print(save[i][j]);
            }
            System.out.println();
        }
    }
    public void frame(char[][] a){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                char t = a[i][j];
                if (t == '╠' || t == '╣' || t == '╩' || t == '╦');
                else if (i == 0 && j == 0) a[i][j] = '╔';
                else if (i == 0 && j == a[0].length-1) a[i][j] = '╗';
                else if (i == a.length-1 && j == 0) a[i][j] = '╚';
                else if (i == a.length-1 && j == a[0].length-1) a[i][j] = '╝';
                else if (i == a.length-1 || i == 0) a[i][j] = '═';
                else if (j == a[0].length-1 || j == 0) a[i][j] = '║';
            }
        }
    }
    public void biomeSetup(){
        setup(biomeHelp);
        String  text1 = " Tropical ┲ ║   Hills ∩ ║  Plains ▓ ║  Low Mountains ▵ ║   Lake ◯ ║       You X",
                text2 = "  Deserts ● ║   Swamp ☵ ║ Forests ♣ ║  Mid Mountains ▴ ║  River ~ ║ Wasteland ¥ ",
                text3 = "  Savanna ß ║ Volcano ≙ ║  Tundra ` ║ High Mountains ▲ ║ Oceans ≈ ║";
        append(text1, 1, biomeHelp);
        append(text2, 2, biomeHelp);
        append(text3, 3, biomeHelp);
        frame(biomeHelp);
    }
    public void biomeModeSetup(){
        setup(biomeMode);
        String  text1 = " Select your preferred mode ",
                text2 = "     Default 0 ║  Islandic 1 ║ Forests 2 ",
                text3 = " Mountainous 3 ║ Temperate 4 ║  Tundra 5 ";
        append(text1, 1, biomeMode);
        append(text2, 2, biomeMode);
        append(text3, 3, biomeMode);
        frame(biomeMode);
    }
    public void blankQuick(String a, String b, String c){
        setup(blank);
        String  text1 = a,
                text2 = b,
                text3 = c;
        append(text1, 1, blank);
        append(text2, 2, blank);
        append(text3, 3, blank);
        frame(blank);
    }
}
class ChatLog{
    int h = 3, w = 41;
    String test = "hello world!";
    char[][] saveB = new char[h*4+3][w], saveM = new char[h+2][w], save = saveB;
    public void switchSave(){
        if (save == saveB) save = saveM;
        else save = saveB;
    }
    public ChatLog(){
        def();
        refresh();
    }
    public void frame(char[][] a){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                char t = a[i][j];
                if (t == '╠' || t == '╣' || t == '╩' || t == '╦');
                else if (i == 0 && j == 0) a[i][j] = '╔';
                else if (i == 0 && j == a[0].length-1) a[i][j] = '╗';
                else if (i == a.length-1 && j == 0) a[i][j] = '╚';
                else if (i == a.length-1 && j == a[0].length-1) a[i][j] = '╝';
                else if (i == a.length-1 || i == 0) a[i][j] = '═';
                else if (j == a[0].length-1 || j == 0) a[i][j] = '║';
            }
        }
    }
    public void clean(){
        for (int i = 0; i < saveB.length; i++){
            moveUp();
        }
    }
    public void def(){
        for (int i = 0; i < saveB.length; i++) {
            for (int j = 0; j < saveB[0].length; j++) {
                saveB[i][j] = ' ';
            }
        }
        for (int i = 0; i < saveB[0].length; i++) {
            saveB[2][i] = '═';
        }
        saveB[0][0] = '╠';
        saveB[saveB.length-1][saveB[0].length-1] = '╩';
        saveB[2][0] = '╠';
        saveB[2][saveB[0].length-1] = '╣';
        frame(saveB);
        {
            addMenu(" /help ", 1);
            addMenu(" /exit ", 9);
            addMenu(" /stats ", 17);
            addMenu(" /lvlup ", 26);
            addMenu("  L  ", w-1-5);
        }
        for (int i = 0; i < saveM.length-1; i++) {
            saveM[i] = saveB[i];
        }
        frame(saveM);
    }
    public void replace(int y, int x, char[][] a){
        for (int i = y; i < saveB.length+y; i++) {
            for (int j = x; j < saveB[0].length+x; j++) {
                saveB[i-y][j-x] = a[i][j];
            }
        }
    }
    public void saveM(){
        for (int i = 1; i < saveM.length-2; i++) {
            saveM[saveM.length-i] = saveB[saveB.length-i];
        }
    }
    public void saveB(){
        for (int i = 1; i < saveM.length-2; i++) {
            saveB[saveB.length-i] = saveM[saveM.length-i];
        }
    }
    public void getLog(){
        for (int i = 0; i < save.length; i++) {
            for (int j = 0; j < save[0].length; j++) {
                System.out.print(save[i][j]);
            }
            System.out.println();
        }
    }
    public void getLog(char[][] a){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
    }
    public void append(String a){
        String rest = "";
        for (int j = 0; j < a.length(); j++) {
            if (j+1>=w-1||a.charAt(j)=='\n') {
                if (a.charAt(j)=='\n') rest += a.substring(j+1);
                else {rest += a.substring(j);}
                refresh(rest);
                break;
            }
            saveB[saveB.length-2][j+1] = a.charAt(j);
        }
    }
    public void moveUp(){
        for (int i = 3; i < saveB.length-1; i++) {
            for (int j = 1; j < w-1; j++) {
                saveB[i][j] = ' ';
                if (i != saveB.length-2) saveB[i][j] = saveB[i+1][j];

            }
        }
    }
    public void addMenu(String a, int x){
        for (int i = 0; i < a.length(); i++) {
            if (i > 38) break;
            saveB[1][x+i] = a.charAt(i);
        }
        saveB[1][x-1] = '║';
        saveB[1][a.length()+x] = '║';
        if (saveB[0][x-1] == '═') saveB[0][x-1] = '╦';
        if (saveB[2][x-1] == '═') saveB[2][x-1] = '╩';
        if (saveB[0][a.length()+x] == '═') saveB[0][a.length()+x] = '╦';
        if (saveB[2][a.length()+x] == '═') saveB[2][a.length()+x] = '╩';
    }
    public void refresh(String a){
        moveUp();
        append(a);
        if (save == saveB) saveM();
        else saveB();
    }
    public void refresh(){
        moveUp();
        if (save == saveB) saveM();
        else saveB();
    }
}
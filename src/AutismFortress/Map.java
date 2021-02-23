package AutismFortress;

import java.util.Arrays;

public class Map {
    public static void main(String[] arg){
        Town twn = new Town();
        String[] test = {"asdf","dfdf"};
        twn.gen();
        twn.refresh();
        twn.print(twn.charGen);
        twn.print();
        System.out.print("END");
    }
}
class Town extends BasicMap {
    //Check variables description in class Map
    final int hs = 25, ws = 141; //screen size
    final int h = hs*3, w = ws*2; //true size
    private int num = -1;
    int posY = (h-1)/2, posX= (w-1)/2;
    int mode = 0;
    private int[] percentage = new int[20], buildPercentage = new int[9];
    //charSF = output char[][], save = output according to screen size
    char[][] save = new char[hs][ws], charGen = new char[h][w],
            charSF = new char[h][w];
    char[][][] worldPos = new char[25*149][h][w];
    //Basic Methods
    public Town() {
        for (int i = 0; i < save.length; i++) {
            for (int j = 0; j < save[0].length; j++) {
                save[i][j] = ' ';
            }
        }
    }
    public void saveTown(int a){
        if (worldPos[a] == null) worldPos[a] = charGen;
    }
    public void print(){
        for (int i = 0; i < save.length; i++) {
            for (int j = 0; j < save[0].length; j++) {
                System.out.print(save[i][j]);
            }
            System.out.println();
        }
    }
    public void clear(){
        for (int i = 0; i < charSF.length; i++) {
            for (int j = 0; j < charSF[0].length; j++) {
                charSF[i][j] = ' ';
            }
        }
    }
    //Convertion Methods
    public void worldAssign(char[][] a, int pos) {
        worldPos[pos] = a;
    }
    public void charGenToCharSF(){
        for (int i = 0; i < charSF.length; i++) {
            for (int j = 0; j < charSF[0].length; j++) {
                charSF[i][j] = charGen[i][j];
            }
        }
        charSF[posY][posX] = '@';
    }

    public void charSFtoSave(){
        int y = posY-(hs-1)/2<0?0:posY-(hs-1)/2;
        int x = posX-(ws-1)/2<0?0:posX-(ws-1)/2;
        if (x+save[0].length > charSF[0].length) x = charSF[0].length-save[0].length;
        if (y+save.length > charSF.length) y = charSF.length-save.length;
        save = new char[hs][ws];
        for (int i = y; i < y+save.length; i++) {
            for (int j = x; j < x+save[0].length; j++) {
                save[i-y][j-x] = charSF[i][j];
            }
        }
    }
    public void refresh(){
        //refresh save and posY and posX
        charGenToCharSF();
        charSFtoSave();
        frame(save);
    }
    //Map Specific Methods
    public String[] buildingType(char a){
        String[] r = {Character.toString(a)};
        switch (a) {
            case '1' : r = new String[] {
                    "     fff       ",
                    "▛▀▀▀▀fff▀▀▀▀▀▀▜",
                    "▌fffffffffffff▐",
                    "▙▄▄▄▄▄▄▄▄▄▄▄▄▄▟"
            }; return r;
            case '2' : r = new String[]{
                    "   fff         ",
                    "▛▀▀fff▀▀▀▀▀▀▀▀▜",
                    "▌fffffffffffff▐",
                    "▙▄▄▄▄▄▄▄▄fff▄▄▟",
                    "         fff   "
            }; return r;
            case '3' : r = new String[]{
                    "    fff        ",
                    "▛▀▀▀fff▀▀▀▀▀▀▀▜",
                    "▌fffffffffffff▐",
                    "▌fffffffffffff▐",
                    "▌fffffffffffff▐",
                    "▙▄▖ff▗▄▄▄▄▖fff▐▄▄▄▄",
                    "  ▌ff▐    ▌fffffff▐",
                    "  ▙▄▄▟    ▙▄fff▄▄▄▟",
                    "            fff    "
            }; return r;
            case '4' : r = new String[]{
                    "       fff       ",
                    "▛▀▀▀▀▀▀fff▀▀▀▀▀▀▜",
                    "▌fffffffffffffff▐",
                    "▌fffffffffffffff▐",
                    "▌fffffffffffffff▐",
                    "▌fffffffffffffff▐",
                    "▙▄▄▄▄▄▄▄▄▄▄▄fff▄▟",
                    "            fff  "
            }; return r;
            case '5' : r = new String[] {
                    "▛▀▀▀▀▀▀▀▀▀▀▀▀▀▜",
                    "▌fffffffffffff▐",
                    "▙▄▄▄▄fff▄▄▄▄▄▄▟",
                    "     fff       "
            }; return r;
            case '6' : r = new String[]{
                    "▛▀▀▀▀▀▀▀▀▀▀▀▀▀▜",
                    "▌fffffffffffff▐",
                    "▌fffffffffffff▐",
                    "▌fffffffffffff▐",
                    "▙▄▖ff▗▄▄▄▄▖fff▐▄▄▄▄",
                    "  ▌ff▐    ▌fffffff▐",
                    "  ▙▄▄▟    ▙▄fff▄▄▄▟",
                    "            fff    "
            }; return r;
            case '7' : r = new String[]{
                    "     ▛▀▀▀▀▀▀▀▜     ",
                    " ▛▀▀▀▘fffffff▝▀▀▀▜ ",
                    "fffffffffffffffffff",
                    " ▙▄▄▄▄▖ffffff▗▄▄▄▟ ",
                    "      ▙▄▄▄▄▄▄▟"
            }; return r;
            case '8' : r = new String[]{
                    "    ▛▀▀▀▀▀▀▀▜    ",
                    "▛▀▀▀▘fffffff▝▀▀▀▜",
                    "▌fffffffffffffff▐",
                    "▙▄▄▄▄▖ffffff▗▄▄▄▟",
                    "     ▙▄fff▄▄▟    ",
                    "       fff       "
            }; return r;
            case '9' : r = new String[]{
                    "       fff       ",
                    "    ▛▀▀fff▀▀▜    ",
                    "▛▀▀▀▘fffffff▝▀▀▀▜",
                    "▌fffffffffffffff▐",
                    "▙▄▄▄▄▖ffffff▗▄▄▄▟",
                    "     ▙▄fff▄▄▟    ",
                    "       fff       "
            }; return r;
        }
        return r;
    }
    public void makeBuilding(int h, int w, char[][] c) {
        boolean nearby = false;
        int min = 3;
        int max = 7;
        int ranDis = (int)(Math.random()*(max-min)+min);
        for (int i = -ranDis; i <= ranDis; i++){
            if (i+h < 0) continue;
            else if (i+h > charGen.length-1) continue;
            for (int j = -ranDis; j <= ranDis; j++){
                if (j+w < 0) continue;
                else if (j+w > charGen[0].length-1) continue;
                if(checkBuilding(c[h+i][w+j])) {
                    nearby = true;
                }
            }
        }
        if (!nearby) {
            //if ((buildingType(c[h][w]).length!=1)) {print(charGen);}
            append(buildingType(c[h][w]), h, w, charGen);
        }
    }
    public boolean checkBuilding(char a){
        switch(a) {
            case 'f':
            case '▌':
            case '▐':
            case '▙':
            case '▟':
            case '▛':
            case '▜':
            case '▀':
            case '▄':
            case '▗':
            case '▝':
            case '▘':
            case '▖':

            case 'E':
            case 'S':
            case '◘': return true;
        }
        return false;
    }
    public boolean checkNotPassable(char a){
        switch(a) {
            case '▌':
            case '▐':
            case '▙':
            case '▟':
            case '▛':
            case '▜':
            case '▀':
            case '▄':
            case '▗':
            case '▝':
            case '▘':
            case '▖':
                return true;
        }
        return false;
    }
    public char convertTerrian(char a){
        switch(a) {
            //world Map
            case '0': return ' ';
            case '1': return '≈';
            case '2': return '♣';
            case '4':
            case '6':
            case '5':
                return 'E';
        }
        return ' ';
    }
    public char convertObject(char a){
        switch(a) {
            //world Map
            case '0': return 'S';
            //case '1': return 'N';
            //case '2': return 'N';
            //case '3': return 'N';

            case '4':
            case '5':
            case '6':
                return 'E';
            //case '7': return 'E';

            case '8': return '◘';
            //case '9': return '◘';
        }
        return ' ';
    }
    /*
            case '0': return '⌘';
            '☠'
     */
    //Gen
    public void gen(){
        for (int i = 1; i < charGen.length-1; i++) {
            for (int j = 1; j < charGen[0].length-1; j++) {
                genBuilding();
                findMax(buildPercentage);
                charGen[i][j] = intToChar(num);
            }
        }
        //print(charGen);
        for (int i = 1; i < charGen.length-1; i++) {
            for (int j = 1; j < charGen[0].length - 1; j++) {
                makeBuilding(i, j, charGen);
            }
        }
        for (int i = 1; i < charGen.length-1; i++) {
            for (int j = 1; j < charGen[0].length - 1; j++) {
                if (charGen[i][j]=='f'){
                    genRandom();
                    findMax(percentage);
                    charGen[i][j] = convertObject(intToChar(num));
                }
            }
        }
        for (int i = 1; i < charGen.length-1; i++) {
            for (int j = 1; j < charGen[0].length - 1; j++) {
                if (!checkBuilding(charGen[i][j])){
                    genRandom();
                    fixSurround(i,j);
                    findMax(percentage);
                    charGen[i][j] = intToChar(num);
                }
            }
        }
        for (int i = 1; i < charGen.length-1; i++) {
            for (int j = 1; j < charGen[0].length - 1; j++) {
                if (charGen[i][j]=='f'||!checkBuilding(charGen[i][j])) {
                    charGen[i][j] = convertTerrian(charGen[i][j]);
                }
            }
        }
    }
    public void findMax(int[] arr){
        //fix generation; find max percentage
        num = -1;
        int per = 0;
        for (int m = 0; m < arr.length; m++) {
            if (per < arr[m]) {
                per = arr[m];
                num = m;
            }
        }
    }
    public void genBuilding(){
        //generation process
        for (int i = 0; i < buildPercentage.length; i++) buildPercentage[i] = (int)(Math.random()*100+1);
    }
    public void genRandom(){
        //generation process
        for (int i = 0; i < percentage.length; i++) percentage[i] = (int)(Math.random()*100+1);
    }
    public void fixSurround(int i, int j){
        //fix generation; increase tile per by surrounding
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                char a = charGen[i + k][j + l];
                if (a == '1') {
                    //water
                    percentage[1] += 50;
                } else if (a == '2') {
                    //tree
                    percentage[2] += 50;
                } else {
                    if (!(charToInt(a)>3 && charToInt(a)<7)) {
                        if (charToInt(a)!=-1&&charToInt(a)<21)percentage[charToInt(a)] += 40;
                    }
                }
            }
        }
    }
    public void move(int y, int x) {
        if (checkNotPassable(charGen[posY+y][posX+x])) return;
        if (posY+y < 1) posY = 1;
        else if (posY+y > charSF.length-2) posY = charSF.length-2;
        else posY += y;
        if (posX+x < 1) posX = 1;
        else if (posX+x > charSF[0].length-2) posY = charSF.length-2;
        else posX += x;
    }
}
class City{

}
class World extends BasicMap{
    //size include frame
    final int h = 25, w = 149; //true size
    final int hs = 11, ws = 41;
    final int hm = 5, wm = 9;
    int posY = (h-1)/2, posX= (w-1)/2;
    int mode = 0;
    private int[] percentage = new int[16];
    private int biomePer = -1, biomeNum = -1,
            lakeStack = 0, riverStack = 0, oceanStack = 0, tundraStack = 0, tropicalStack = 0;
    //save is output, charGen = char[][] generation, charSF = char[][] save full-screen, small, minimised
    char[][] save = new char[h][w], charGen = new char[h][(w-3)/2+2],
            charSF = new char[h][w], charSS = new char[hs][ws], charSM = new char[hm][wm];
    //used when want to null
    final char[][] clean = new char[h][charGen[0].length];
    //skip mode x
    Boolean first = false, found = false, skip0 = false, skip1 = false, skip2 = false, skip3 = false, skip4 = false;
    //Basic Methods
    public World() {
        for (int i = 0; i < save.length; i++) {
            for (int j = 0; j < save[0].length; j++) {
                save[i][j] = ' ';
            }
        }
    }
    public void print(){
        for (int i = 0; i < save.length; i++) {
            for (int j = 0; j < save[0].length; j++) {
                System.out.print(save[i][j]);
            }
            System.out.println();
        }
    }
    public void clear(){
        for (int i = 0; i < charSF.length; i++) {
            for (int j = 0; j < charSF[0].length; j++) {
                charSF[i][j] = ' ';
            }
        }
    }
    //Map Specific Methods
    public char convertSymbol(char a){
        switch(a) {
            //world Map
            case '0': return '┲';
            case '1': return '≙';
            case '2': return '≈';
            case '3': return '♣';
            case '4': return '▓';
            case '5': return '☵';
            case '6': return '◯';
            case '7': return '∩';
            case '8': return '▵';
            case '9': return '▴';
            case 'a': return '▲'; //10
            case 'b': return 'ß'; //11
            case 'c': return '●'; //12
            case 'd': return '~'; //13
            case 'e': return '`'; //14
            case 'f': return '¥'; //15
        }
        return ' ';
    }
    //Convertion Methods
    public void genToVisual(){
        //gen char to visual char
        for (int i = 1; i < charGen.length-1; i++) {
            for (int j = 1; j < (charGen[0].length-1)*2; j+=2) {
                charSF[i][j] = ' ';
                charSF[i][j+1] = convertSymbol(charGen[i][(j+1)/2]);
            }
        }
    }
    public void charSFCopy(){
        save = new char[charSF.length][charSF[0].length];
        for (int i = 0; i < charSF.length; i++) {
            for (int j = 0; j < charSF[0].length; j++) {
                save[i][j] = charSF[i][j];
            }
        }
        save[posY][posX] = 'X';
    }
    public void charSSCopy(){
        //save = charSF then add posX and posY then charSSCopy
        save = new char[charSF.length][charSF[0].length];
        for (int i = 0; i < charSF.length; i++) {
            for (int j = 0; j < charSF[0].length; j++) {
                save[i][j] = charSF[i][j];
            }
        }
        save[posY][posX] = 'X';
        int y = posY-(hs-1)/2<0?0:posY-(hs-1)/2;
        int x = posX-(ws-1)/2<0?0:posX-(ws-1)/2;
        for (int i = 0; i < charSS.length; i++) {
            for (int j = 0; j < charSS[0].length; j++) {
                charSS[i][j] = save[i+y][j+x];
            }
        }
        frame(charSS);
        save = charSS;
    }
    public void charSMCopy(){
        //save = charSF then add posX and posY then charSSCopy
        save = new char[charSF.length][charSF[0].length];
        for (int i = 0; i < charSF.length; i++) {
            for (int j = 0; j < charSF[0].length; j++) {
                save[i][j] = charSF[i][j];
            }
        }
        int y = posY-(hm-1)/2<0?0:posY-(hm-1)/2;
        int x = posX-(wm-1)/2<2?2:posX-(wm-1)/2;
        for (int i = 0; i < charSM.length; i++) {
            for (int j = 1; j < charSM[0].length-1; j++) {
                charSM[i][j] = save[y+i][x+((j-2)*2)];
            }
        }
        frame(charSM);
        save = charSM;
    }
    public void switchSave(){
        //char Full to char Small to char Minimised to char Full
        //save to default/charSF then assign charType then put posY and posX
        if (save.length==charSF.length) {charSSCopy();}
        else if (save.length==charSS.length) {charSMCopy();}
        else {charSFCopy();}
    }
    public void refresh(){
        //refresh save and posY and posX
        if (save.length==charSS.length) {charSSCopy();}
        else if (save.length==charSM.length) {charSMCopy();}
        else {charSFCopy();}
    }
    //Generation Process
    public void resetFix(int a){
        switch (a) {
            case 2: oceanStack /= 2;
            case 13: riverStack /= 2;
            case 14: {if (mode == 5) {tundraStack = 0;} else {tundraStack /= 2;}}
            case 0: tropicalStack /= 2;
        }
    }
    public void genRandom(){
        //generation process
        for (int i = 0; i < percentage.length; i++) percentage[i] = (int)(Math.random()*100+1);
        if (mode == 1) {
            percentage[0] += 20*2;
            percentage[1] += 20/4;
            percentage[2] += 20/2*3;
            percentage[8] -= 20*2;
            percentage[9] -= 20*2;
            percentage[10] -= 20*2;
        }else if (mode == 2) {
            percentage[3] += 20;
            percentage[4] += 20/2;
            percentage[5] += 20/2;
            percentage[6] += 20/2;
            percentage[7] += 20/2;
        }else if (mode == 3) {
            percentage[7] += 20/3;
            percentage[8] += 20/2;
            percentage[9] += 20/3*2;
            percentage[10] += 20/3*2;
            percentage[0] -= 20*2;
            percentage[1] -= 20*2;
        }else if (mode == 4) {
            percentage[11] += 15;
            percentage[12] += 15;
            percentage[14] -= 20*2;
        }else if (mode == 5) {
            percentage[14] += 20;
            percentage[8] += 20/2;
            percentage[9] += 20;
            percentage[10] += 20;
            percentage[11] -= 20*2;
            percentage[12] -= 20*2;
            percentage[13] -= 20*2;
        }
    }
    public void fixSurround(int i, int j){
        //fix generation; increase tile per by surrounding
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                char a = charGen[i + k][j + l];
                if (a == '7' || a == '8' || a == '9' || a == 'a') {
                    //mountains
                    percentage[1] += 3;
                    percentage[8] += 7;
                    percentage[9] += 10;
                    percentage[10] += 7;
                } else if (a == '2') {
                    //ocean
                    if (i > 4 && i < charGen[0].length-1-4)percentage[0] += 15;
                    percentage[6] += 5;
                    percentage[13] += 7;
                    percentage[2] += 10;
                } else if (a == 'd') {
                    //river
                    percentage[6] += 7;
                    percentage[13] += 15;
                    percentage[2] += 10;
                } else if (a == '0') {
                    //tropical
                    if (mode != 1)
                    percentage[1] += 7;
                } else {
                    if (charToInt(a)==1) percentage[charToInt(a)]+= 10;
                    else if (a == '6') percentage[charToInt(a)]+= 15;
                    else if (a == 'f') percentage[charToInt(a)]+= 10;
                    else if (charToInt(a)!=-1) percentage[charToInt(a)]+= 18;
                }
            }
        }
        if (i < 0+4) {
            //tundra
            percentage[14] += (6-i)*15;
        } else if (i > charGen.length-6) {
            percentage[14] += (i-(charGen.length-1-6))*15;
        }

        if (j < 0+4) {
            //tundra
            percentage[2] += (6-j)*15;
        } else if (j > charGen[0].length-6) {
            percentage[2] += (j-(charGen[0].length-1-6))*15;
        }
        percentage[0] += tropicalStack;
        percentage[2] += oceanStack;
        percentage[13] += riverStack;
        percentage[14] += tundraStack;
    }
    public void findMaxMain(int i, int j){
        first = true; found = false; skip0 = false; skip1 = false; skip2 = false; skip3 = false; skip4 = false;
        do {
            findMax();
        } while (!fixCondition(i,j));
        resetFix(biomeNum);
    }
    public boolean fixCondition(int i, int j){
        if (biomeNum == 2) {
            for (int k = -1; k <= 1; k++)
                for (int l = -1; l <= 1; l++)
                    if (charGen[i + k][j + l] == '2' || j == 1 || j == charGen[0].length-2 || i == 1 || i == charGen.length-2)
                        return true;
            if (first) {
                oceanStack += 20;
                first = false;
            }
            skip0 = true;
            return false;
        }
        else if (biomeNum == 13) {
            for (int k = -1; k <= 1; k++)
                for (int l = -1; l <= 1; l++)
                    if (charGen[i + k][j + l] == '2' || charGen[i + k][j + l] == 'd')
                        return true;
            if (first) {
                riverStack += 20;
                oceanStack = riverStack > oceanStack ? riverStack : oceanStack + 20;
                first = false;
            }
            skip1 = true;
            return false;
        }
        else if (mode != 5 && biomeNum == 14) {
            for (int k = -1; k <= 1; k++)
                for (int l = -1; l <= 1; l++)
                    if ((charGen[i + k][j + l] == 'e' || i == 1 || i == h - 2) && (i < 1 + 6 || i > charGen.length -1 - 6))
                    {
                        return true;
                    }
            if (first) {
                tundraStack += 20;
                first = false;
            }
            skip2 = true;
            return false;
        }
        else if (mode != 1 && biomeNum == 0) {
            for (int k = -1; k <= 1; k++)
                for (int l = -1; l <= 1; l++)
                    if (charGen[i + k][j + l] == '0' || charGen[i + k][j + l] == '2')
                        return true;
            if (first) {
                tropicalStack += 20;
                first = false;
            }
            skip3 = true;
            return false;
        }
        else if (biomeNum == 1 || biomeNum == 11 || biomeNum == 12) {
            for (int k = -1; k <= 1; k++)
                for (int l = -1; l <= 1; l++)
                    if (charGen[i + k][j + l] == 'e') {
                        skip4 = true;
                        return false;
                    }
            return true;
        } else {return true;}
    }
    public void findMax(){
        //fix generation; find max percentage
        biomePer = -1;
        for (int m = 0; m < percentage.length; m++) {
            if (m==2 && skip0) continue;
            else if (m==13 && skip1) continue;
            else if (m==14 && skip2) continue;
            else if (m==0 && skip3) continue;
            else if ((m==1||m==11||m==12) && skip4) continue;
            if (biomePer < percentage[m]) {
                biomePer = percentage[m];
                biomeNum = m;
            }
        }
    }
    public void fixLink(int i, int j){
        //fix generation; check surround tile and convert
        int counterO = 0, counterL = 0, counterR = 0, counterRL = 0;
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                if (charGen[i+k][j+l] == '2') {counterO++;}
                else if (charGen[i+k][j+l] == '6') {counterL++;}
                else if (charGen[i+k][j+l] == 'd') {counterR++;}
            }
        }
        if (charGen[i][j]=='d' && counterO > 2) {charGen[i][j]='2';}
        else if (charGen[i][j]=='6' && counterL > 1 && counterR > 0) {charGen[i][j]='d';}
    }
    public void gen() {
        //generation loop
        for (int i = 1; i < charGen.length-1; i++) {
            for (int j = 1; j < charGen[0].length-1; j++) {
                genRandom();
                fixSurround(i, j);
                findMaxMain(i, j);
                charGen[i][j] = intToChar(biomeNum);
                //System.out.println(i+" "+j+" "+charGen[i][j]+Arrays.toString(percentage));
            }
        }
        for (int i = charGen.length-2; i > 0; i--) {
            for (int j = charGen[0].length-2; j > 0; j--) {
                genRandom();
                fixSurround(i, j);
                findMaxMain(i, j);
                charGen[i][j] = intToChar(biomeNum);
                //System.out.println(i+" "+j+" "+charGen[i][j]+Arrays.toString(percentage));
            }
        }
        for (int n = 0; n < 2; n++) {
            for (int i = 1; i < charGen.length - 1; i++)
                for (int j = 1; j < charGen[0].length - 1; j++)
                    fixLink(i, j);
        }
        genToVisual();
        frame(charSF);
    }
    public void move(int y, int x) {
        if (posY+y < 1) posY = 1;
        else if (posY+y > charSF.length-2) posY = charSF.length-2;
        else posY += y;
        if (posX+x < 2) posX = 2;
        else if (posX+x > charSF[0].length-3) posY = charSF.length-3;
        else posX += x*2;
    }
    public int getPos(){
        return (posY+1)*(posX+1);
    }
}
class BasicMap{
    char[][] save, charSF;

    public void print(char[][] save){
        for (int i = 0; i < save.length; i++) {
            for (int j = 0; j < save[0].length; j++) {
                System.out.print(save[i][j]);
            }
            System.out.println();
        }
    }
    public void append(String[] text, int desY, int desX, char[][] out){
        for (int j = desY; j < out.length; j++){
            for (int i = desX; i < out[0].length; i++) {
                if (text[j-desY].charAt(i-desX) == ' ') {
                    if (i-desX==text[j-desY].length()-1) break;
                    continue;
                }
                out[j][i] = text[j-desY].charAt(i-desX);
                if (i-desX==text[j-desY].length()-1) break;
            }
            if (j-desY==text.length-1) break;
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
    public int charToInt(char a) {
        if (Character.getNumericValue(a) > -1) {int b = Character.getNumericValue(a); return b;}
        return -1;
    }
    public char intToChar(int a) {
        if (a < 10) {char b = Integer.toString(a).charAt(0); return b;}
        else {
            switch (a) {
                //world Map
                case 10: return 'a';
                case 11: return 'b';
                case 12: return 'c';
                case 13: return 'd';
                case 14: return 'e';
                case 15: return 'f';
            }
        }
        return ' ';
    }
    public boolean checkIfBorder(char a){
        switch (a) {
            case '╔': return true;
            case '╗': return true;
            case '╚': return true;
            case '╝': return true;
            case '═': return true;
            case '║': return true;
            case '╬': return true;
            case '╣': return true;
            case '╠': return true;
            case '╩': return true;
            case '╦': return true;
        }
        return false;
    }

}

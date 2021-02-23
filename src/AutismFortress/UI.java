package AutismFortress;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.lang.Object;

class UItest{
    public static void main(String[] arg) throws InterruptedException {
        UI.screen();
    }
}
public class UI{
    public static void screen() throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        //
        char ans = ' ';
        boolean buttonB = true;
        boolean exit = false;
        boolean upgrade = false;
        char[][] tempArr;
        char[] intro;
        boolean lvlup = false;
        //
        Screen scn = new Screen();
        World wrd = new World();
        Town twn = new Town();
        Button btn = new Button();
        ChatLog msg = new ChatLog();
        Clean cleaner = new Clean();
        MapHelp mapHelp = new MapHelp();
        Item item = new Item();
        scn.frame();
        //
        scn = new Screen();
        scn.frame();
        msg.switchSave();
        msg.refresh("");
        msg.refresh("Please enter your name");
        scn.append(scn.save.length-msg.save.length,0, msg.save );
        scn.refresh();
        scn.render();
        System.out.print("Enter >> ");
        String uIn, user = " " + scan.nextLine();
        String[] stats;
        int str = 0, con = 0, dex = 0;
        do {
            msg = new ChatLog();
            msg.refresh("");
            msg.refresh("Please select your starting");
            msg.refresh("STR CON DEX attribute points");
            msg.refresh("The total attributes must be 30 points");
            msg.refresh("Enter in Whole Number only");
            msg.refresh("In this format: STR CON DEX");
            scn.append(scn.save.length-msg.save.length,0, msg.save );
            scn.refresh();
            scn.render();
            System.out.print("Enter >> ");
            stats = scan.nextLine().split(" ");
            if (stats.length==3){
                str = Integer.parseInt(stats[0]);
                con = Integer.parseInt(stats[1]);
                dex = Integer.parseInt(stats[2]);
            }
        } while (!(stats.length==3 && str+con+dex==30));
        Player ply = new Player(str, con, dex);
        //
        //setup
        msg = new ChatLog();
        scn = new Screen();
        mapHelp.biomeSetup();
        mapHelp.biomeModeSetup();
        msg.refresh("Please Select your preferred mode");
        msg.switchSave();
        scn.frame();
        scn.refresh();
        //
        do {
            String uOut = "Please Select your preferred mode";
            msg.refresh(uOut);
            scn.append(scn.save.length-msg.save.length,0, msg.save );
            scn.append(scn.save.length-5, msg.save[0].length-1,mapHelp.biomeMode);
            scn.refresh();
            scn.render();
            System.out.print("Enter >> ");
            int mode = Character.getNumericValue(scan.next().charAt(0));

            uOut = "Confirm this generation? Y | N";
            wrd = new World();
            wrd.mode = mode;
            wrd.gen();
            wrd.refresh();
            msg.refresh(uOut);
            scn.append(0,0,wrd.charSF);
            scn.append(scn.save.length-msg.save.length,0, msg.save );
            scn.append(scn.save.length-5, msg.save[0].length-1,mapHelp.biomeHelp);
            scn.refresh();
            scn.render();
            System.out.print("Enter >> ");
            ans = scan.next().charAt(0);
        } while (ans != 'Y' && ans != 'y');
        //
        twn.gen();
        twn.refresh();
        twn.saveTown(wrd.getPos());
        scn.append(scn.save.length-msg.save.length,0, msg.save );
        scn.append(scn.save.length - btn.biomeButton.length - 2, scn.save[0].length - btn.biomeButton[0].length, btn.biomeButton);
        scn.refresh();
        msg = new ChatLog();
        msg.refresh("Press Q W E | A D | Z X C To Move.");
        msg.refresh("Press S To Interact/Select.");
        msg.refresh("Press L To Minimised The Chat Log.");
        msg.refresh("Press B To Switch World View.");
        msg.refresh("Can't perform until you select a Biome");
        msg.refresh("Enter /exit To Exit.");
        msg.refresh("Enter /help To Show Commands.");
        msg.refresh("");
        msg.refresh("Please select your Starting Biome");
        scn.append(0,0,wrd.save);
        scn.append(scn.save.length-msg.save.length,0, msg.save );
        scn.refresh();
        //
        SECOND:
        do {
            scan = new Scanner(System.in);
            String uOut = user;
            scn.render();
            System.out.print("Enter >> ");
            uIn = scan.nextLine();
            if (uIn.equalsIgnoreCase("/exit")) return;
            if (uIn.equalsIgnoreCase("/help")) {
                msg.refresh("Press Q W E | A D | Z X C To Move.");
                msg.refresh("Press S To Interact/Select.");
                msg.refresh("Press L To Minimised The Chat Log.");
                msg.refresh("Press B To Switch World View.");
                msg.refresh("Can't perform until you select a Biome");
                msg.refresh("Enter /exit To Exit.");
                msg.refresh("Enter /help To Show Commands.");
            }
            if (check(uIn)&&uIn.toUpperCase(Locale.ROOT).charAt(0) =='S') {
                if (!exit) {uIn = "oki"; uOut = "Are you sure you want pick this biome? Y | N";}
                msg.refresh(uOut);
                exit = true;
            }
            if (exit && (uIn.equalsIgnoreCase("yes") || uIn.equalsIgnoreCase("y"))) {
                break SECOND;
            }
            if (check(uIn)) {
                char chk = uIn.toUpperCase(Locale.ROOT).charAt(0);
                switch (chk) {
                    case 'L' : {
                        cleaner.clean(chk, scn.save);
                        msg.switchSave();
                        break;
                    }
                    case 'S': uIn = "/exit";twn.worldAssign(twn.charGen, wrd.getPos());break;
                    //
                    case 'Q': if (buttonB) wrd.move(-1, -1);else { twn.move(-1, -1); }break;
                    case 'W': if (buttonB) wrd.move(-1, 0);else { twn.move(-1, 0); }break;
                    case 'E': if (buttonB) wrd.move(-1, 1);else { twn.move(-1, 1); }break;

                    case 'A': if (buttonB) wrd.move(0, -1);else { twn.move(0, -1); }break;
                    case 'D': if (buttonB) wrd.move(0, 1);else { twn.move(0, 1); }break;

                    case 'Z': if (buttonB) wrd.move(1, -1);else { twn.move(1, -1); }break;
                    case 'X': if (buttonB) wrd.move(1, 0);else { twn.move(1, 0); }break;
                    case 'C': if (buttonB) wrd.move(1, 1);else { twn.move(1, 1); }break;
                    //
                }
                wrd.refresh();
            }
            scn.append(0,0,wrd.save);
            scn.append(scn.save.length-msg.save.length,0, msg.save );
            scn.refresh();
        } while (!uIn.equalsIgnoreCase("/exit"));
        exit = false;
        uIn = "";
        msg = new ChatLog();
        scn.append(scn.save.length-msg.save.length,0, msg.save );
        scn.refresh();
        do {
            scan = new Scanner(System.in);
            String uOut = user + ": ";
            scn.render();
            System.out.print("Enter >> ");
            uIn = scan.nextLine();
            //condition output
            if (upgrade&&uIn.length()==1) {
                upgrade = false;
                switch (uIn.toUpperCase(Locale.ROOT).charAt(0)) {
                    case '1': if (ply.item1 != null) {if (ply.gold >= 2*ply.item1.gold) {uOut = ply.removeGold(2*ply.item1.gold);ply.item1 = ply.item1.upgradeItem(1);} else uOut = "Not enough gold";} else uOut = "Slot is empty"; uIn = "";break;
                    case '2': if (ply.item2 != null) {if (ply.gold >= 2*ply.item2.gold) {uOut = ply.removeGold(2*ply.item2.gold);ply.item2 = ply.item2.upgradeItem(1);} else uOut = "Not enough gold";} else uOut = "Slot is empty"; uIn = "";break;
                    case '3': if (ply.item3 != null) {if (ply.gold >= 2*ply.item3.gold) {uOut = ply.removeGold(2*ply.item3.gold);ply.item3 = ply.item3.upgradeItem(1);} else uOut = "Not enough gold";} else uOut = "Slot is empty"; uIn = "";break;
                    case '4': if (ply.item4 != null) {if (ply.gold >= 2*ply.item4.gold) {uOut = ply.removeGold(2*ply.item4.gold);ply.item4 = ply.item4.upgradeItem(1);} else uOut = "Not enough gold";} else uOut = "Slot is empty"; uIn = "";break;
                    default : uOut = "Upgrade cancel"; //not needed?
                }
            } else if (upgrade) {upgrade = false;}
            if (ply.lvlup&&lvlup&&uIn.length()==1) {
                lvlup = false;
                ply.lvlup = false;
                System.out.println(uIn.toUpperCase(Locale.ROOT).charAt(0));
                System.out.println(uIn.toUpperCase(Locale.ROOT).charAt(0) == 'D');
                ply.addPoint(uIn.toUpperCase(Locale.ROOT).charAt(0),1);
                uOut = "";
                uIn = "";
            } else if (lvlup) {lvlup = false;}
            if (exit && (uIn.equalsIgnoreCase("yes") || uIn.equalsIgnoreCase("y"))) {
                uIn = "/exit";
            }
            if (ply.itemFull){ply.itemFull = false; if (uIn.length()==1){char chk = uIn.toUpperCase(Locale.ROOT).charAt(0); uOut = ply.removeItem(chk); ply.addItem(item.random()); uIn = "";}}
            //command output
            if (uIn.equalsIgnoreCase("greedisgood")) {
                ply.gold += 10000;
                uOut = "Cheat enabled!";
                uIn = "";
            }
            if (uIn.equalsIgnoreCase("whosyourdaddy")) {
                ply.strB = 10000;ply.conB = 10000;ply.dexB = 10000;
                uOut = "Cheat enabled!";
                uIn = "";
            }
            if (uIn.equalsIgnoreCase("/help")) {
                msg.refresh("Press Q W E | A D | Z X C To Move.");
                msg.refresh("Press S To Interact/Select.");
                msg.refresh("Press L To Minimised The Chat Log.");
                msg.refresh("Press B To Switch World View.");
                msg.refresh("Enter /exit To Exit.");
                uOut = "Enter /help To Show Commands.";
                uIn = "";
            }
            if (uIn.equalsIgnoreCase("/exit")) {
                if (!exit) {uIn = "oki"; uOut = "Are you sure you want to exit? Y | N";}
                exit = true;
            }
            if (uIn.equalsIgnoreCase("/stats")) {
                msg.refresh(ply.getStats1());
                msg.refresh(ply.getStats2());
                msg.refresh(ply.getItem1());
                msg.refresh(ply.getItem2());
                msg.refresh(ply.getItem3());
                uOut = ply.getItem4();
                uIn = "";
            }
            if (uIn.equalsIgnoreCase("/lvlup")) {
                lvlup = true;
                if (ply.lvlup) {
                    uOut = "Please select the attribute\nSTR:S CON:C DEX:D";
                } else {
                    uOut = "You do not have any attribute point";
                }
                uIn = "";
            }
            //controls output
            if (check(uIn)) {
                char chk = uIn.toUpperCase(Locale.ROOT).charAt(0);
                switch (chk) {
                    case 'L' : {
                        cleaner.clean(chk, scn.save);
                        msg.switchSave();
                        break;
                    }
                    case 'S' : break;
                    //
                    case 'Q' : if(buttonB)wrd.move(-1,-1); else{twn.move(-1,-1);
                    }break;
                    case 'W' : if(buttonB)wrd.move(-1,0); else{twn.move(-1,0);
                    }break;
                    case 'E' : if(buttonB)wrd.move(-1,1); else{twn.move(-1,1);
                    }break;
                    case 'A' : if(buttonB)wrd.move(0,-1); else{twn.move(0,-1);
                    }break;
                    case 'D' : if(buttonB)wrd.move(0,1); else{twn.move(0,1);
                    }break;
                    case 'Z' : if(buttonB)wrd.move(1,-1); else{twn.move(1,-1);
                    }break;
                    case 'X' : if(buttonB)wrd.move(1,0); else{twn.move(1,0);
                    }break;
                    case 'C' : if(buttonB)wrd.move(1,1); else{twn.move(1,1);
                    }break;
                    //
                    case 'B' : {
                        cleaner.clean(chk, scn.save);
                        wrd.switchSave();
                        if (wrd.save.length == wrd.charSF.length) {buttonB = true;}
                        else {buttonB = false;}
                    }
                }
                //
                if (!buttonB) {
                    ply.refresh();
                    if (twn.charSF[twn.posY][twn.posX] == 'E') {
                        do {
                            msg.refresh(ply.combat());
                        } while (ply.hpE > 0 && ply.hp > 0);
                        twn.charGen[twn.posY][twn.posX] = ' ';
                    }
                    if (twn.charSF[twn.posY][twn.posX] == '◘') {
                        msg.refresh(ply.addItem(item.random()));
                        twn.charGen[twn.posY][twn.posX] = ' ';
                    }
                    if (twn.charSF[twn.posY][twn.posX] == 'S') {
                        msg.refresh("Do you wish to upgrade you item?\nThe cost is 2 times your item value\nEnter /stats to see your items value\nEnter 1 to 4 to select item slot");
                        upgrade = true;
                    }
                    if (ply.lvlup) {msg.refresh("Level up!\nEnter /lvlup to add attribute");ply.dmg=0;}
                    mapHelp.blankQuick(ply.getStats1() + ply.getStats2(),ply.getItemL1() + ply.getItemL2() ,ply.getItemL3() + ply.getItemL4());
                    scn.append(scn.save.length-5, msg.save[0].length-1,mapHelp.blank);
                    scn.append(wrd.save.length-1, 0, btn.biomeButton);
                } else if (buttonB) {
                    scn.append(scn.save.length-5, msg.save[0].length-1,mapHelp.biomeHelp);
                    scn.append(scn.save.length - btn.biomeButton.length - 2, scn.save[0].length - btn.biomeButton[0].length, btn.biomeButton);
                }
                //
                if (ply.over) {msg.refresh("GAME OVER"); msg.refresh("BETTER LUCK NEXT TIME"); msg.refresh("THANK YOU FOR PLAYING");
                    scn.append(scn.save.length-msg.save.length,0, msg.save );scn.refresh();scn.render();Thread.sleep(10*1000);
                    return;}
                //
                twn.refresh();
                wrd.refresh();
                // IDK what's the loop below for, probably put it and forgot to delete but it has been finalised I will not delete it.
                switch (twn.charGen[twn.posY][twn.posX]) {
                    case '♣' :;
                    case '◘' :;
                    case 'E' :;
                    case 'S' :;
                }
            } else {
                if (!exit&&!uIn.equalsIgnoreCase("")) {uOut += uIn;}
                if (!uOut.equalsIgnoreCase("")) msg.refresh(uOut);
            }
            scn.append(0,8, twn.save );
            scn.append(0,0,wrd.save);
            scn.append(scn.save.length-msg.save.length,0, msg.save );
            scn.refresh();
        } while (!uIn.equalsIgnoreCase("/exit"));
    }
    public static boolean check(String check){
        if (check.length()!=1) return false;
        check = check.toUpperCase(Locale.ROOT);
        char chk = check.charAt(0);
        switch (chk) {
            case 'S' : return true;
            //
            case 'L' : return true;
            //
            case 'Q' : return true;
            case 'W' : return true;
            case 'E' : return true;

            case 'A' : return true;
            case 'D' : return true;

            case 'Z' : return true;
            case 'X' : return true;
            case 'C' : return true;
            //
            case 'B' : return true;
        }
        return false;
    }
}
class Screen {
    public static void main(String[] arg) {
        Screen scn = new Screen();
        scn.frameS();
        scn.render();
        scn.refresh();
        scn.render();
    }
    char[][] save = new char[29][149];
    public void render(){
        for (int i = 0; i < save.length; i++){
            for (int j = 0; j < save[i].length; j++) {
                System.out.print(save[i][j]);
            }
            System.out.println();
        }
    }

    public Screen() {
        for (int i = 0; i < save.length; i++) {
            for (int j = 0; j < save[0].length; j++) {
                save[i][j] = ' ';
            }
        }
    }
    public void refresh(){
        for (int i = 0; i < save.length; i++) {
            for (int j = 0; j < save[0].length; j++) {
                save[i][j] = checkSurround(i,j, save[i][j]);
            }
        }
        for (int i = save.length-1; i >= 0; i--) {
            for (int j = save[0].length-1; j >= 0; j--) {
                save[i][j] = checkSurround(i,j, save[i][j]);
            }
        }
    }
    public void frame(){
        for (int i = 0; i < save.length; i++) {
            for (int j = 0; j < save[0].length; j++) {
                char t = save[i][j];
                if (t == '╠' || t == '╣' || t == '╩' || t == '╦');
                else if (i == 0 && j == 0) save[i][j] = '╔';
                else if (i == 0 && j == save[0].length-1) save[i][j] = '╗';
                else if (i == save.length-1 && j == 0) save[i][j] = '╚';
                else if (i == save.length-1 && j == save[0].length-1) save[i][j] = '╝';
                else if (i == save.length-1 || i == 0) save[i][j] = '═';
                else if (j == save[0].length-1 || j == 0) save[i][j] = '║';
            }
        }
    }
    //in case for later uses and copies
    public void frameS() {
        for (int h = 0; h < save.length; h++) {
            for (int w = 0; w < save[0].length; w++) {
                if (h == 0 && w == 0) save[h][w] = '╔';
                else if (h == 0 && w == save[0].length - 1) save[h][w] = '╗';
                else if (h == save.length - 1 && w == 0) save[h][w] = '╚';
                else if (h == save.length - 1 && w == save[0].length - 1) save[h][w] = '╝';
                else if (save[h][w] == '╔' && h == 0) save[h][w] = '╦';
                else if (save[h][w] == '╗' && h == 0) save[h][w] = '╦';
                else if (save[h][w] == '╔' && w == 0) save[h][w] = '╠';
                else if (save[h][w] == '╚' && w == 0) save[h][w] = '╠';
                else if (save[h][w] == '╗' && w == save[0].length - 1) save[h][w] = '╣';
                else if (save[h][w] == '╝' && w == save[0].length - 1) save[h][w] = '╣';
                else if (save[h][w] == '╚' && h == save.length - 1) save[h][w] = '╩';
                else if (save[h][w] == '╝' && h == save.length - 1) save[h][w] = '╩';
                else if (save[h][w] == '╠' && save[h][w + 1] == '═') ;
                else if (save[h][w] == '╣' && save[h][w - 1] == '═') ;
                else if (save[h][w] == '╦' && save[h + 1][w] == '║') ;
                else if (save[h][w] == '╩' && save[h - 1][w] == '║') ;
                else if (h == save.length - 1 || h == 0) save[h][w] = '═';
                else if (w == save[0].length - 1 || w == 0) save[h][w] = '║';
            }
        }
    }
    public char checkSurround(int i, int j, char a){
        if (checkIfBorder(a)) {
            boolean up = false, down = false, left = false, right = false;
            if (j>0) if (save[i][j-1] == '═' || save[i][j-1] == '╠' || save[i][j-1] == '╔' || save[i][j-1] == '╚' || save[i][j-1] == '╦' || save[i][j-1] == '╩' || save[i][j-1] == '╬') left = true;
            if (j<148) if (save[i][j+1] == '═' || save[i][j+1] == '╣' || save[i][j+1] == '╗' || save[i][j+1] == '╝' || save[i][j+1] == '╦' || save[i][j+1] == '╩' || save[i][j+1] == '╬') right = true;
            if (i>0) if (save[i-1][j] == '║' || save[i-1][j] == '╣' || save[i-1][j] == '╗' || save[i-1][j] == '╔' || save[i-1][j] == '╦' || save[i-1][j] == '╠' || save[i-1][j] == '╬') up = true;
            if (i<28) if (save[i+1][j] == '║' || save[i+1][j] == '╣' || save[i+1][j] == '╚' || save[i+1][j] == '╝' || save[i+1][j] == '╩' || save[i+1][j] == '╠' || save[i+1][j] == '╬') down = true;
            //System.out.println(a + " " + i + " " + j);
            //System.out.println(up +"" + down +""+ left +""+ right);
            if (up&&!down&&left&&!right) return '╝';
            if (up&&!down&&!left&&right) return '╚';
            if (!up&&down&&left&&!right) return '╗';
            if (!up&&down&&!left&&right) return '╔';
            if (up&&down&&!left&&!right) return '║';
            if (up&&down&&left&&!right) return '╣';
            if (up&&down&&!left&&right) return '╠';
            if (!up&&!down&&left&&right) return '═';
            if (!up&&down&&left&&right) return '╦';
            if (up&&!down&&left&&right) return '╩';
            if (up&&down&&left&&right) return '╬';
        } else return a;
        return a;
    }
    public boolean checkIfBorder(char a){
        switch (a) {
            case '╔':
            case '╩':
            case '╗':
            case '╚':
            case '╝':
            case '═':
            case '║':
            case '╬':
            case '╣':
            case '╠':
            case '╦':
                return true;
        }
        return false;
    }
    public void append(int desY, int desX, char[][] src) {
        int mxy = src.length + desY > save.length ? save.length : src.length + desY;
        int mxx = src[0].length > save[0].length - desX ? save[0].length - desX : src[0].length ;
        for (int i = desY; i < mxy; i++) {
            System.arraycopy(src[i-desY], 0, save[i], desX, mxx);
        }
    }
}
class Clean{
    public static void clean(char b, char[][] scn){
        switch (b) {
            case 'L': cleanL(scn); break;
            //
            case 'B': cleanB(scn); break;

        }
    }
    public static void cleanL(char[][] scn){
        scn[scn.length-6-8][0] = '║';
        scn[scn.length-6-8+2][0] = '║';
        //
        for (int i = scn.length-5-10; i < scn.length-5; i++) {
            for (int j = 1; j < 41; j++) {
                scn[i][j] = ' ';
            }
        }
    }
    public static void cleanB(char[][] scn){
        //some minor fixes
        //
        for (int i = 1; i < scn.length-5; i++) {
            for (int j = 1; j < 148; j++) {
                scn[i][j] = ' ';
            }
        }
        for (int i = scn.length-5+1; i < scn.length-5+3; i++) {
            for (int j = 144; j < 148; j++) {
                scn[i][j] = ' ';
            }
        }
    }
}
class Button{
    private final int hMap = 3, wMap = 5;
    char[][] biomeButton = new char[hMap][wMap];
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
    {
        for (int i = 0; i < biomeButton.length; i++) {
            for (int j = 0; j < biomeButton[0].length; j++) {
                biomeButton[i][j] = ' ';
            }
        }
        biomeButton[(hMap-1)/2][(wMap-1)/2] = 'B';
        frame(biomeButton);
    }
}


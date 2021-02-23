package AutismFortress;

import AutismFortress.*;
import java.util.Scanner;

public class Main {
    /*
    Notes to player:
    atm world map is meaningless
    tree and water in town map are also meaningless
    because I don't know what to implement; in water no hp regen? in tree get random encounter?
    could be enemy following me then in water you walk slower but it's quite difficult and time consuming to do
     */
    public static void main(String[] arg) throws InterruptedException {
        /*
        READ ME:

        Roughly ≈2000 lines of code
        With only 15% about the game physics engine/system

        Visit the link below if you don't want to spend time testing the features.
        https://youtu.be/bduDhmCIcu0

        Features according to the PDF table:
        1)Store information about player: store info about hp, attribute stats, gold, etc.
        2)Place player on the grid: 2 grids, class world and class town
        3)Allow player to move on the board: allows player to move in 8 directions
        4)Add some elements on map: wall - player can't walk through, enemy, item chest
        5)Add randomness: world generator, town generator, other generator, almost every aspect of this game is random
        6)Add 4 types of elements: get item through chest, add attribute, fight, and gain exp, and other commands
        7)Add NPCs: randomise enemy NPC
        8)Allow to lose / win: this is a hardcore game no win only lose when hp < 1
        9)Make it for two players: N/A - will never be for 2 player
        10)Make world infinite: N/A - infinite world would be silly in this game since I intend to make everything savable but not enough time
        11)Add option to upgrade: if you count "upgradable map", upgradable items, upgradable stats
        12)Use hexagonal cells instead of squares to build the map: N/A - the idea of this game is from Dwarf Fortress
        13)Allow player to change a cell: world generation if that counts?, killing an enemy cause a cell to be empty not really an option to change it more like deleting it, so, idk?

        Features that this game lacks; 9, 10, 12 otherwise all applicable
        This means 18 points

        Regarding Good practices and Java API:
        I do only what is necessary for the project,
        e.g., I'm not going to do some "correct" indentation if it means I have a harder time reading it because I'm the only developer of this project
            , I will not check if the import is necessary because I might use it.
        Hope you understand

        CHEAT CODES (enter in the chat)
        whosyourdaddy = makes your str, con, dex = 10000
        greedisgood = your gold = 10000
         */
        Scanner scan = new Scanner(System.in);
        //
        char[][] tempArr;
        char[] intro;
        //
        Screen scn = new Screen();
        scn.frame();
        //
        scn = new Screen();
        scn.frame();
        intro = ("THE GAME WILL START SOON").toCharArray();
        tempArr = new char[][]{intro};
        scn.append((0+(scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
        scn.refresh();
        scn.render();
        System.out.print("Made in IntelliJ IDEA 2020.3.1 Ultimate Edition");
        Thread.sleep(3*1000);
        System.out.println();

        scn = new Screen();
        scn.frame();
        intro = ("PLEASE MAKE SURE THE TOP OF THE WINDOW SCREEN").toCharArray();
        tempArr = new char[][]{intro};
        scn.append((-2+(scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
        intro = ("MATCHES THE TOP OF THE TERMINAL SCREEN").toCharArray();
        tempArr = new char[][]{intro};
        scn.append((0+(scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
        intro = ("FOR YOUR BEST EXPERIENCE").toCharArray();
        tempArr = new char[][]{intro};
        scn.append((2+(scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
        scn.refresh();
        scn.render();
        System.out.print("Made in IntelliJ IDEA 2020.3.1 Ultimate Edition");
        Thread.sleep(5*1000);
        System.out.println();

        scn = new Screen();
        scn.frame();
        intro = ("INTRODUCING").toCharArray();
        tempArr = new char[][]{intro};
        scn.append((-1+(scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
        intro = ("AUTISM FORTRESS").toCharArray();
        tempArr = new char[][]{intro};
        scn.append((1+(scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
        scn.refresh();
        scn.render();
        System.out.print("Made in IntelliJ IDEA 2020.3.1 Ultimate Edition");
        Thread.sleep(3*1000);
        System.out.println();

        scn = new Screen();
        scn.frame();
        intro = ("INSPIRED BY").toCharArray();
        tempArr = new char[][]{intro};
        scn.append((-2+(scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
        intro = ("DWARF FORTRESS").toCharArray();
        tempArr = new char[][]{intro};
        scn.append((0+(scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
        intro = ("and").toCharArray();
        tempArr = new char[][]{intro};
        scn.append((1+(scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
        intro = ("DUNGEONS AND DRAGONS").toCharArray();
        tempArr = new char[][]{intro};
        scn.append((2+(scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
        scn.refresh();
        scn.render();
        System.out.print("Made in IntelliJ IDEA 2020.3.1 Ultimate Edition");
        Thread.sleep(3*1000);
        System.out.println();

        scn = new Screen();
        scn.frame();
        intro = ("PRESENTED TO YOU BY").toCharArray();
        tempArr = new char[][]{intro};
        scn.append((-4+(+scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
        intro = ("THANONDRAK ARUNSANGSIRINAK, S22130 12C").toCharArray();
        tempArr = new char[][]{intro};
        scn.append((-2+(+scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
        intro = ("INSTRUCTED BY TOMASZ WERNER").toCharArray();
        tempArr = new char[][]{intro};
        scn.append((2+(scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
        intro = ("AT POLISH-JAPANESE ACADEMY OF INFORMATION TECHNOLOGY").toCharArray();
        tempArr = new char[][]{intro};
        scn.append((4+(scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
        scn.refresh();
        scn.render();
        System.out.print("Made in IntelliJ IDEA 2020.3.1 Ultimate Edition");
        Thread.sleep(7*1000);
        System.out.println();
        //
        do {
            UI.screen();

            scn = new Screen();
            scn.frame();
            intro = ("GAME OVER").toCharArray();
            tempArr = new char[][]{intro};
            scn.append((-3+(+scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
            intro = ("BETTER LUCK NEXT TIME").toCharArray();
            tempArr = new char[][]{intro};
            scn.append((-1+(+scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
            intro = ("THANK YOU FOR PLAYING").toCharArray();
            tempArr = new char[][]{intro};
            scn.append((1+(scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
            intro = ("ENTER /quit TO QUIT").toCharArray();
            tempArr = new char[][]{intro};
            scn.append((3+(scn.save.length-1)/2),((scn.save[0].length-1)/2)-(intro.length/2)-1, tempArr);
            scn.refresh();
            scn.render();
            System.out.print("Enter >> ");
        } while (!scan.next().equalsIgnoreCase("/quit"));
    }
}



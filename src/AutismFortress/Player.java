package AutismFortress;
import java.util.Scanner;

class PlayerTest{
    public static void main(String[] arg){
        Scanner scan = new Scanner(System.in);
        Player test = new Player(10, 10, 10);
        test.refresh();
        System.out.println(test.getStats1());
        System.out.println(test.getStats2());
        while (!scan.next().equalsIgnoreCase("exit")){
        System.out.println(test.combat());}
    }
}

public class Player {

    int gold = 100, str, con, dex, hp, exp = 0, dmg = 0;
    int strB, conB, dexB, hpB = 30;
    ItemStats item1, item2, item3, item4;
    boolean itemFull = false, over = false, lvlup = false;
    Player(int str, int con, int dex) {
        this.strB = str; this.conB = con; this.dexB = dex;
        refresh();
    }
    public String getStats1(){
        return ("Level " + getLvl() + " has " + gold +
                " gold and EXP " + exp + " ");
    }
    public String getStats2(){
        return ("STR " + str + ", CON " + con + ", DEX " + dex + ", HP " + hp);
    }
    public void refresh(){
        str = strB; con = conB; dex = dexB;
        if (item1 != null) {
            str += item1.str;
            con += item1.con;
            dex += item1.dex;
        }
        if (item2 != null) {
            str += item2.str;
            con += item2.con;
            dex += item2.dex;
        }
        if (item3 != null) {
            str += item3.str;
            con += item3.con;
            dex += item3.dex;
        }
        if (item4 != null) {
            str += item4.str;
            con += item4.con;
            dex += item4.dex;
        }
        hp = hpB + mod(con) + dmg;
        if (-dmg >= hpB + mod(con)) over = true;
        if (dmg<0)dmg++;
    }
    int hpE, hpET, atkE, acE, lvE;
    public void Enemy(int lv){
        lvE = getLvl()+(int)(Math.random()*3)-1;
        if (lvE < 1) lvE = 1;
        hpE = (int)((Math.random()*((lv*5)+10))+1);
        atkE = (int)((Math.random()*12)+1+lvlMod());
        acE = (int)((Math.random()*20)+1+lvlMod());
    }
    public String getItem1(){
        if (item1 == null) return "Slot 1 is empty ";
        return item1.name + " Level " + item1.lvl + " Value " + item1.gold + "\nSTR" + item1.str + " CON" + item1.con + " DEX" + item1.dex+ " ";
    }
    public String getItem2(){
        if (item2 == null) return "Slot 2 is empty ";
        return item2.name + " Level " + item2.lvl + " Value " + item2.gold + "\nSTR" + item2.str + " CON" + item2.con + " DEX" + item2.dex+ " ";
    }
    public String getItem3(){
        if (item3 == null) return "Slot 3 is empty ";
        return item3.name + " Level " + item3.lvl + " Value " + item3.gold + "\nSTR" + item3.str + " CON" + item3.con + " DEX" + item3.dex+ " ";
    }
    public String getItem4(){
        if (item4 == null) return "Slot 4 is empty ";
        return item4.name + " Level " + item4.lvl + " Value " + item4.gold + "\nSTR" + item4.str + " CON" + item4.con + " DEX" + item4.dex+ " ";
    }
    public String getItemL1(){
        if (item1 == null) return "Slot 1 is empty ";
        return item1.name + " Level " + item1.lvl + " Value " + item1.gold + " STR" + item1.str + " CON" + item1.con + " DEX" + item1.dex+ " ";
    }
    public String getItemL2(){
        if (item2 == null) return "Slot 2 is empty ";
        return item2.name + " Level " + item2.lvl + " Value " + item2.gold + " STR" + item2.str + " CON" + item2.con + " DEX" + item2.dex+ " ";
    }
    public String getItemL3(){
        if (item3 == null) return "Slot 3 is empty ";
        return item3.name + " Level " + item3.lvl + " Value " + item3.gold + " STR" + item3.str + " CON" + item3.con + " DEX" + item3.dex + " ";
    }
    public String getItemL4(){
        if (item4 == null) return "Slot 4 is empty ";
        return item4.name + " Level " + item4.lvl + " Value " + item4.gold + " STR" + item4.str + " CON" + item4.con + " DEX" + item4.dex + " ";
    }
    public String combat(){
        int lv = getLvl();
        int a = 0, b = 0, d20p = 0, d20e = 0, d20p2 = 0, d12p = 0, hpT2 = hp;
        if (hpE < 1) {Enemy(getLvl()); hpET = hpE;}
        if (acE < (d20p = mod(dex)+(int)(Math.random()*20)+1) || d20p == 20) {
            a = mod(str)+(d12p = (int)(Math.random()*12)+1);
            hpE -= a;
        }
        if (hpE > 0) {
            if ((d20e = (int)(Math.random()*20)+1)>(d20p2=mod(dex)+(int)(Math.random()*20)+1) || d20e == 20 && d20p2-mod(dex) != 20) {
                b = atkE;
                dmg -= atkE;
            }
        }
        if (hpE <= 0) {
            gold += (lvE*40)+(int)((Math.random()*10)+1);
            exp += (lvE*80)+(int)((Math.random()*40)+1);
        }
        refresh();
        if (lv != getLvl()) lvlup = true;
        return "Enemy HP " + hpET + " to "+ hpE +
                "\nYour HP " + hpT2 + " to "+ hp;
    }
    /* for future uses
    return "   Attack with d20 " + d20p + " vs Nat " + acE +
                "\nDmg.d12 " + d12p + " Dmg " + a +
                "\nHP " + hpT + " to "+ hpE +
                "\nAttacked with d20 " + d20e + " vs d20 " + d20p2 +
                "\nNatDmg of " + b + " Dmg" +
                "\nHP " + hpT2 + " to "+ hp;
     */
    public String addItem(ItemStats a) {
        if (item1 == null ) {item1 = a;return getItem1() + "\nHas been Added to your inventory";}
        else if (item2 == null ) {item2 = a;return getItem2() + "\nHas been Added to your inventory";}
        else if (item3 == null ) {item3 = a;return getItem3() + "\nHas been Added to your inventory";}
        else if (item4 == null ) {item4 = a;return getItem4() + "\nHas been Added to your inventory";}
        else itemFull = true;
        return "Inventory is full \nWhich inventory slot would you like to remove\nEnter 1 to 4 else is none";
    }
    public String removeItem(char a){
        switch (a){
            case '1': item1 = null; return "Slot 1 have been removed";
            case '2': item2 = null; return "Slot 2 have been removed";
            case '3': item3 = null; return "Slot 3 have been removed";
            case '4': item4 = null; return "Slot 4 have been removed";
        }
        return "No item were removed";
    }
    public String removeGold(int a) {
        this.gold -= a;
        return "Upgrade successfully!";
    }
    public int lvlMod(){
        if (exp < 2700) return 2;
        else if (exp < 48000) return 3;
        else if (exp < 100000) return 4;
        else if (exp < 225000) return 5;
        return 6;
    }
    public int getLvl(){
        if (exp < 300) return 1;
        else if (exp < 900) return 2;
        else if (exp < 2700) return 3;
        else if (exp < 6500) return 4;
        else if (exp < 14000) return 5;
        else if (exp < 23000) return 6;
        else if (exp < 34000) return 7;
        else if (exp < 48000) return 8;
        else if (exp < 64000) return 9;
        else if (exp < 85000) return 10;
        else if (exp < 100000) return 11;
        else if (exp < 120000) return 12;
        else if (exp < 140000) return 13;
        else if (exp < 165000) return 14;
        else if (exp < 195000) return 15;
        else if (exp < 225000) return 16;
        else if (exp < 265000) return 17;
        else if (exp < 305000) return 18;
        else if (exp < 355000) return 19;
        return 20;
    }
    public void addPoint(char a, int b){
        switch (a){
            case 'S': strB += b; break;
            case 'C': conB += b; break;
            default : dexB += b; break;
        }
    }
    public int mod(int a){
        if (a < 1) return -5;
        switch (a){
            case 1: return -5;
            case 2:
            case 3: return -4;
            case 4:
            case 5: return -3;
            case 6:
            case 7: return -2;
            case 8:
            case 9: return -1;
            case 10:
            case 11: return 0;
            case 12:
            case 13: return 1;
            case 14:
            case 15: return 2;
            case 16:
            case 17: return 3;
            case 18:
            case 19: return 4;
            case 20:
            case 21: return 5;
            case 22:
            case 23: return 6;
            case 24:
            case 25: return 7;
            case 26:
            case 27: return 8;
            case 28:
            case 29: return 9;
            // I know i can write a maths formula for this but
            // It's supposed to be according to DND but for now ill allow more unguided mod table after mod 29
        }
        return (a-10)/2;
    }
}

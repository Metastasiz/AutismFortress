package AutismFortress;

public class Item{
    int a = 0;
    // dnde5 grit&glory
    ItemStats knuckles = new ItemStats("Knuckles", 50, 1, 0,3);
    ItemStats handaxe = new ItemStats("Handaxe", 250, 3, 0,3);
    ItemStats hatchet = new ItemStats("Hatchet", 150, 2, 0,3);
    //3
    ItemStats battleaxe = new ItemStats("Battleaxe", 500, 4, 2,0);
    ItemStats broadaxe = new ItemStats("Broadaxe", 400, 5, 1,-1);
    ItemStats greataxe = new ItemStats("Greataxe", 1500, 6, 1,-3);
    ItemStats maulaxe = new ItemStats("Maulaxe", 1250, 4, 2,0);
    ItemStats waraxe = new ItemStats("Waraxe", 350, 4, 1,0);
    //5
    ItemStats dagger = new ItemStats("Dagger", 100, 2, 0,3);
    ItemStats epee = new ItemStats("Epee", 1250, 4, 0,3);
    ItemStats falchion = new ItemStats("Falchion", 750, 5, 0,1);
    ItemStats greatsword = new ItemStats("Greatsword", 2500, 8, 0,-2);
    ItemStats longsword = new ItemStats("Longsword", 500, 5, 0,0);
    ItemStats rapier = new ItemStats("Rapier", 1250, 4, 0,2);
    ItemStats sabre = new ItemStats("Sabre", 1250, 5, 0,2);
    ItemStats scimitar = new ItemStats("Scimitar", 1250, 3, 0,3);
    //8
    ItemStats longspear = new ItemStats("Longspear", 250, 4, 1,1);
    ItemStats quarterstaff = new ItemStats("Quarterstaff", 20, 3, 1,2);
    ItemStats mace = new ItemStats("Mace", 250, 3, 0,2);
    //3
    public ItemStats random(){
        int a = (int)(Math.random()*5+1);
        switch (a){
            case 1: return knuckles;
            case 2: return handaxe;
            case 3: return hatchet;
            case 4: return battleaxe;
            case 5: return broadaxe;
            case 6: return greataxe;
            case 7: return maulaxe;
            case 8: return waraxe;
            case 9: return dagger;
            case 10: return epee;
            case 11: return falchion;
            case 12: return greatsword;
            case 13: return longsword;
            case 14: return rapier;
            case 15: return sabre;
            case 16: return scimitar;
            case 17: return longspear;
            case 18: return quarterstaff;
            case 19: return mace;
        }
        return null;
    }
}
class ItemStats {
    int gold, str, con, dex;
    String name;
    int lvl = 1;
    ItemStats(){}
    ItemStats(String name){this.name = name;}
    ItemStats(String name, int gold, int str, int con, int dex) {
        this.name = name; this.gold = gold; this.str = str; this.con = con; this.dex = dex;
    }
    ItemStats(String name, int gold, int str, int def, int dex, int lvl) {
        this.name = name; this.gold = gold; this.str = str; this.con = con; this.dex = dex;
        upgradeItem(lvl);
    }
    public void copy(ItemStats want) {
        want.name = name; want.lvl = lvl; want.gold = gold;
        want.str = str; want.con = con; want.dex = dex;
    }
    public ItemStats upgradeItem(int a){
        ItemStats out = new ItemStats();
        copy(out);
        if (a == 0) return out;
        out.lvl ++; refresh(out); a--;
        upgradeItem(a);
        return out;
    }
    private void refresh(ItemStats a){
        if (a.str > 0)a.str += a.str;
        if (a.con > 0)a.con += a.con;
        if (a.dex > 0)a.dex += a.dex;
        a.gold *= 2;
    }
    public String getStats(){
        return name + " Level " + lvl + " Value " + gold +
                "\nSTR" + str + " CON" + con + " DEX" + dex;
    }
}

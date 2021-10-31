package Item.company;

public class Item
{
    public int damage, lvlreq;
    public String name = "Item";

    //Get
    public int getLvl()
    {
        return lvlreq;
    }
    public int getDmg(){return damage;}
    public String getName(){return name;}

    //Set
    public void setLvlreq(int lvlreq){this.lvlreq = lvlreq; }
    public void setDmg(int damage){this.damage = damage; }
    public void setName(String name){this.name = name;}

    public  Item(String name, int dmg, int lrq)
    {
        this.name = name;
        this.damage = dmg;
        this.lvlreq = lrq;
    }

    public void ShowStats()
    {
        System.out.print("\u001b[38;5;38m" + "ITEM::: \u001b[38;5;93m" + name +
                "\u001b[38;5;15m" + " | Lvlreq: " + lvlreq  + " | Damage: " + damage + '\n');
    }
}


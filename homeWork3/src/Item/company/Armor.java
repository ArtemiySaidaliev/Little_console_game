package Item.company;

public class Armor extends Item
{
    int armor;
    public Armor(String name, int dmg, int lvlreq, int armor){
        super(name, dmg, lvlreq);
        this.armor = armor;
    }
    public int getArmor()
    {
        return armor;
    }

    public void setArmor(int armor){this.armor = armor; }

    public void ShowStats()
    {
        System.out.print("\u001b[38;5;251m" + "ARMOR::: \u001b[38;5;93m" + name +
                "\u001b[38;5;15m" + " | Lvlreq: " + lvlreq  + " | Damage: " + damage + '\n');
    }

}

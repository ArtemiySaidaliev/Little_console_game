package Item.company;

public class Weapon extends Item
{
    public Weapon(String name, int dmg, int lvlreq) {
        super(name, dmg, lvlreq);
    }
    public int getDmg(){return damage;}
    public void setDmg(int damage){this.damage = damage; }

    public void ShowStats()
    {
        System.out.print("\u001b[38;5;202m" + "WEAPON::: \u001b[38;5;170m" + name +
                "\u001b[38;5;15m" + " | Lvlreq: " + lvlreq  + " | Damage: " + damage + '\n');
    }
}

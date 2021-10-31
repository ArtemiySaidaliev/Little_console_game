package creature.company;

public class Creature {
    private int hp, exp, lvl, damage;
    private String name = "Default";

    //Get
    public int getHp() {
        return hp;
    }

    public int getLvl() {
        return lvl;
    }

    public int getExp() {
        return exp;
    }

    public int getDmg() {
        return damage;
    }

    public String getName() {
        return name;
    }

    //Set

    public void setHp ( int hp){
        this.hp = hp;
    }

    public void setDmg ( int dmg){
        this.damage = dmg;
    }

    public void setLvl ( int lvl){
        this.lvl = lvl;
    }

    public void setExp ( int exp){
        this.exp = exp;
    }

    public void setName (String name){
        this.name = name;
    }


    public Creature(String name, int hp, int lvl, int damage,int exp)
    {
        this.name = name;
        this.hp = hp;
        this.lvl = lvl;
        this.damage = damage;
        this.exp = exp;
    }
    public void ShowStats()
    {
        System.out.println("\u001b[38;5;42m" + "CREATURE::: \u001b[38;5;30m"  + name + "\u001b[38;5;15m | Hp: " + hp + " | Exp: " + exp
                + " | Lvl: " + lvl + " | Damage: " + damage + '\n');
    }

}

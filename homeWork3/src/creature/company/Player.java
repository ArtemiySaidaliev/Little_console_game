package creature.company;

import Item.company.Armor;
import Item.company.Item;
import Item.company.Weapon;

import java.util.ArrayList;
import java.util.Random;

public class Player extends Creature {
    int x,y, mapsize;
    Weapon equipment1;
    Armor equipment2;
    public Player(String name, int hp, int lvl, int damage, int exp, int x, int y, int mapsize, Weapon equipment1, Armor equipment2)
    {
        super(name, hp, lvl, damage,exp);
        this.x = x;
        this.y = y;
        this.mapsize = mapsize;
        this.equipment1 = equipment1;
        this.equipment2 = equipment2;
    }
    //GET
    public int getX(){return x;}
    public int getY(){return y;}
    public Weapon getWeap(){return equipment1;}
    public Armor getArm(){return equipment2;}
    //SET
    public void setX ( int x){
        this.x = x;
    }
    public void setY ( int y){
        this.y = y;
    }
    public void setWeap (Weapon equipment1) {this.equipment1 = equipment1;}
    public void setArm (Armor equipment2) {this.equipment2 = equipment2;}


    public void ShowStats()
    {
        System.out.print("\u001b[38;5;42m" + "PLAYER::: \u001b[38;5;30m"  + getName() + "\u001b[38;5;15m | Hp: " + getHp()
                + " | Exp: " + getExp() + " | Lvl: " + getLvl() + " | Damage: " + getDmg() + " | X: "+ x + " | Y: " + y +'\n');
    }

    public void run() {
        Random rnd = new Random();


        if((x+=1) >= mapsize){
            x--;
        }
        if((x-=1) <= 0){
            x++;
        }

        if((y+=1) >= mapsize){
            y--;
        }
        if((y-=1) <= 0){
            y++;

        }

        if(rnd.nextBoolean()){
            x++;
        }else{
            x--;
        }
        if(rnd.nextBoolean()){
            y++;
        }else{
            y--;
        }

    }
}

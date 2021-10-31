package com.company;

import Item.company.Armor;
import Item.company.Weapon;
import creature.company.Enemy;
import creature.company.Player;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static class GameLogic {
        static int enemyCount = 3;
        static int enemyAlive = enemyCount;
        static int mapSize = 3;
        static boolean alive = true;
        static Scanner in = new Scanner(System.in);
        static ArrayList<Enemy> Enemys = new ArrayList<Enemy>(enemyCount);
        static ArrayList<Weapon> Weapons = new ArrayList<Weapon>(enemyCount);
        static ArrayList<Armor> Armors = new ArrayList<Armor>(enemyCount);
        static String[][] map = new String[mapSize][mapSize];
        static Player player;
        static ExecutorService enemyThreadsExec = Executors.newCachedThreadPool();



        static int TakeDmg(int pHp, int eDmg) {
            pHp-=eDmg;
            if(pHp <=0)
                pHp = 0;
            return pHp;
        }

        static int Attack(int eHp, int pDmg) {
            eHp-=pDmg;
            if(eHp <=0)
                eHp = 0;
            return eHp;
        }

        static void Generator() {


            String Enames[] = {"Alloy", "Sam", "Fragile", "Heartman", "Deadman", "Inside", "Neo", "Mobius", "Trinity", "Smit"};
            String Wnames[] = {"sword", "m4a1", "dagger", "shotgun", "akimbo pistols", "railgun", "plasma rifle", "shield", "levithan", "chaos blades"};
            String Anames[] = {"Wooden chest", "Stone chest", "Bullet Proof jacket", "Protorian armor", "Spartan armor", "Smart metal armor", "Smoking", "Paper bag", "Lucky Jacket", "Un lucky jacket"};

            Random rndEName = new Random();
            Random rndWName = new Random();
            Random rndAName = new Random();

            Random rnd = new Random();


            for (int i = 0; i < enemyCount; i++) {
                Weapons.add(new Weapon(Wnames[rndWName.nextInt(9) + 1], rnd.nextInt(10), rnd.nextInt(10)));
                Armors.add(new Armor(Anames[rndAName.nextInt(9) + 1], rnd.nextInt(10), rnd.nextInt(10), rnd.nextInt(10)));
                Enemys.add(new Enemy(Enames[rndEName.nextInt(9) + 1], rnd.nextInt(10), rnd.nextInt(10), rnd.nextInt(10),
                        rnd.nextInt(10),rnd.nextInt(mapSize),rnd.nextInt(mapSize),i, mapSize, Weapons.get(i)
                        ,Armors.get(i)));



                System.out.println("-------------------------------------------------------");
                Enemys.get(i).ShowStats();
                Enemys.get(i).getWeap().ShowStats();
                Enemys.get(i).getArm().ShowStats();
                System.out.println("-------------------------------------------------------");
            }

        }

        static void createPlayer(){
            String Pnames[] = {"Fedya", "S117", "Sereja", "Danya", "Sam"};
            Random rndPName = new Random();
            Random rnd = new Random();
            Weapon basicWeapon = new Weapon("Fisting fist",1,0);
            Armor basicArmor = new Armor("Nothing LOL",0,0,0);
            player = new Player(Pnames[rndPName.nextInt(4) + 1],rnd.nextInt(10),0, rnd.nextInt(10),
                    0,rnd.nextInt(mapSize),rnd.nextInt(mapSize),mapSize,basicWeapon,basicArmor);
            player.ShowStats();
            player.getArm().ShowStats();
            player.getWeap().ShowStats();
        }

        static void Fight (int eIndex){
            int heal = player.getHp();

            while(player.getHp()>0)
            {
                if (player.getHp() <= 0){
                    System.out.println("You've been killed by: " + Enemys.get(eIndex).getName());
                    alive = false;
                    break;
                }

                if (Enemys.get(eIndex).getHp() <=0){
                    System.out.println("You defeat: " + Enemys.get(eIndex).getName());
                    player.setHp(heal);
                    enemyAlive--;
                    break;
                }

                while(Enemys.get(eIndex).getHp() >0) {

                    Enemys.get(eIndex).setHp(Attack(Enemys.get(eIndex).getHp(), player.getDmg()));
                    if (Enemys.get(eIndex).getHp() <= 0) {

                        System.out.println("You defeat: " + Enemys.get(eIndex).getName());
                        player.setHp(heal);
                        player.setWeap(checkItems(player.getWeap(), Enemys.get(eIndex).getWeap()));
                        break;
                    } else {
                        player.setHp(TakeDmg(player.getHp(), Enemys.get(eIndex).getDmg()));
                        if (player.getHp() <= 0)
                        {
                            System.out.println("You've been killed by: " + Enemys.get(eIndex).getName());
                            alive = false;
                            break;
                        }
                    }
                }


            }
            Enemys.remove(eIndex);
        }

        static void checkFight(){
            for(int i = 0; i<Enemys.size(); i++){
                if(Enemys.get(i).getY() == player.getY() && Enemys.get(i).getX() == player.getX()){
                    Enemys.get(i).ShowStats();
                    Fight(i);
                    break;
                }
            }
        }

        static Weapon checkItems(Weapon weapon1, Weapon weapon2){
            if(weapon2.getDmg() > weapon1.getDmg()){
                System.out.println("Вау у этого противника крутое оружие: ");
                weapon2.ShowStats();
                System.out.println("Вот твое для сравнения: ");
                weapon1.ShowStats();
                System.out.println("Теперь оружие противника твое!!!");
                return weapon2;
            }
            return weapon1;
        }

        static void DrawLvl(){
            for(int i = 0; i<mapSize; i++){
                for(int j = 0; j<mapSize; j++){
                    map[i][j] = "*";
                }
            }

            map[player.getX()][player.getY()] = "\u001b[38;5;42m" + player.getName().substring(0,1) + "\u001b[38;5;15m";


            for(int i = 0; i<Enemys.size(); i++){
                map[Enemys.get(i).getX()][Enemys.get(i).getY()] =  "\u001b[38;5;198m" + Enemys.get(i).getName().substring(0,1) + "\u001b[38;5;15m";
            }
            for(int i = 0; i<mapSize; i++){
                for(int j = 0; j<mapSize; j++){
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
        }

        static void clearScreen(){
            System.out.println("-_- \n");
        }

        static void changePosition(){
            for(int i = 0; i<Enemys.size(); i++) {
                enemyThreadsExec.execute(Enemys.get(i));
            }

            player.run();
        }

        static void Wait(){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        static void Game(){

            Generator();
            while(alive && enemyAlive!=0) {
                checkFight();
                DrawLvl();
                changePosition();
                clearScreen();
                Wait();
            }
            goToHub();
            enemyThreadsExec.shutdown();
        }

        static void goToHub(){

            int input;
            Random rnd = new Random();
            if(alive){
                System.out.println("ТЫ ПОБЕДИЛ! ТЫ СДАЛ ЛАБУ");
            }else{
                System.out.println("ПОХОЖЕ ТЫ НЕ НАСТРОИЛ SSH КЛИЕНТ... ПОНИМАЮ, НИЧЕГО В СЛЕДУЮЩИЙ РАЗ ТОЧНО ЗАЩИТИШЬ ЛАБУ, А ПОКА ЧИЛЬ ");
            }

            System.out.println("ДОБРО ПОЖАЛОВАТЬ В ЧИЛЗОНУ ПУТНИК, ОТДОХНИ У КОСТРА И ПОПОЛНИ СВОИ ЗАПАСЫ КОФЕ");
            System.out.println("ИЛИ ОТПРАВЛЯЙСЯ НА ОЧЕРЕДНУЮ ЗАЩИТУ ЛАБОРАТОРНЫХ РАБОТ (1) ");
            System.out.println("ТЫ ТАКЖЕ МОЖЕШЬ ФАРМИТЬ ОПЫТ НА ЗАНЯТИЯХ ПО ЖАБЕ  (2) ");
            System.out.println("ВОЗМОЖНО ТЫ СОВСЕМ УСТАЛ И ХОЧЕШЬ ЗАКОНЧИТЬ СВОЙ ПУТЬ СИСАДМИНА? (3) \n");
            System.out.println("ВОТ ТЫ И ТВОИ ВЕЩИ: ");
            player.ShowStats();
            player.getArm().ShowStats();
            player.getWeap().ShowStats();

            input = in.nextInt();

            if(input == 1){
                alive = true;
                enemyAlive = enemyCount;
                player.setHp(4);
                Game();
            }
            if(input == 2){
                System.out.println("ТЫ ИДЕШЬ НА ПАРУ ПО ЖАБЕ.... СИДЯ ЗА СТОЛОМ ТЫ ТЯНЕШЬСЯ ЗА РОЗЕТКОЙ И НАХОДИШЬ...");
                player.setWeap(Weapons.get(rnd.nextInt(enemyCount)));
                player.getWeap().ShowStats();
                System.out.println("О БОЖЕ МОЙ!! ТЕБЕ ТАК ПОНРАВИЛСЯ ВНЕШНИЙ ВИД ЭТОГО ОРУЖИЯ ЧТО ТЫ НЕ СМОТРЯ НА СТАТЫ ВЗЯЛ ЕГО!!! КАКОЙ ТЫ МОЛОДЕЦ....");
                System.out.println("ТЫ РЕШАЕШЬ СРАЗУ ОПРОБОВАТЬ ОРУЖИЕ В ДЕЙСТВИЕ И БЕЖИШЬ В КАБИНЕТ ИНФОКОМУНИКАЦИОННЫХ СЕТЕЙ!!!");
                alive = true;
                enemyAlive = enemyCount;
                player.setHp(4);
                Game();
            }
            if(input == 3){
                alive = false;
            }


        }
    }

        public static void main(String[] args) {
            GameLogic.createPlayer();
            GameLogic.Game();
        }

}

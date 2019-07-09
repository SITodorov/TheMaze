
package themaze;

import java.util.HashMap;
import java.util.Random;


public class Player {
    private String Pname;
    public int HP;
    public int backpackWeight,currRoomId;
    public int pAttack,pDefence;
    public final int backpackCapacity=20;
    private Random rand = new Random();
    HashMap<String,Integer> Food,Weapons;
    String equippedWeapon;
    boolean isEquipped;
    public Player(String name,int l,int w){
        Pname=name;
        backpackWeight=0;
       Food = new HashMap<String,Integer>();
       Weapons = new HashMap<String,Integer>();
       currRoomId=0;
       HP=3*l;
       pAttack=0;
       pDefence=0;
       isEquipped=false;
    }
    public void CurrHp(){
        System.out.println("Your current Hp is:"+HP);
        System.out.println();
    }
    public void CombatStats(){
            System.out.println("Your current Attack is:"+pAttack);
        System.out.println();
          System.out.println("Your current Defence is:"+pDefence);
        System.out.println();
    }
    public void SetName(String name){
        Pname=name;
    }
    public int GetCurrRoomId(){
        return currRoomId;
    }
    public void SetRoomId(int Id){
        currRoomId=Id;
    }
    public void PrintItemsInBackpack(){
        System.out.println("Your Food:");
        for (String i : Food.keySet()) {
            System.out.println(i); 
        }
        System.out.println();
        System.out.println("Your Weapons:");
        for(String i :Weapons.keySet()){
            System.out.println(i);
        }
         System.out.println();
    }
    public static int MeasureItemWeight(String itemName){
        int weight=0;
             if(itemName.equals("Watermelon")){
                 weight=5;
             }
              if(itemName.equals("Banana")){
                 weight=2;
             }
               if(itemName.equals("Chicken")){
                 weight=4;
             }
                 if(itemName.equals("Steak")){
                 weight=4;
             }
                 if(itemName.equals("Ordinary Sword")){
                 weight=7;
             }
                      if(itemName.equals("Enchanted Sword")){
                 weight=7;
             }
                      if(itemName.equals("Axe")){
                 weight=5;
             }
                               if(itemName.equals("Shield")){
                 weight=10;
             }
                               return weight;
    }
}


package themaze;


public class Weapon {
    public int weight,rarity,attackBonus,shieldBonus;
    public String Wname;
    public Weapon(String name,int r,int w,int a,int s){
        Wname=name;
        rarity=r;
        attackBonus=a;
        shieldBonus=s;
    }
    public static int BonusAtt(String itemName){
        int att=0;
            if(itemName.equals("Ordinary Sword")){
                att=2;
             }
                      if(itemName.equals("Enchanted Sword")){
                 att=4;
             }
                      if(itemName.equals("Axe")){
                 att=3;
             }
                               if(itemName.equals("Shield")){
                 att=1;
             }
                               return att;
    }
        public static int BonusDef(String itemName){
        int def=0;
            if(itemName.equals("Ordinary Sword")){
                def=0;
             }
                      if(itemName.equals("Enchanted Sword")){
                 def=1;
             }
                      if(itemName.equals("Axe")){
                def=0;
             }
                               if(itemName.equals("Shield")){
                 def=3;
             }
                               return def;
    }
}

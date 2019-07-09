
package themaze;


public class Food {
    public int weight,rarity,healing;
    public String Fname;
       public Food(String name,int r,int w,int h){
           Fname=name;
           rarity=r;
           weight=w;
           healing=h;
       }
       public static int GetHealing(String itemName){
           int heal=0;
             if(itemName.equals("Watermelon")){
                 heal=1;
             }
              if(itemName.equals("Banana")){
                 heal=2;
             }
               if(itemName.equals("Chicken")){
                 heal=5;
             }
                 if(itemName.equals("Steak")){
                 heal=10;
             }
                 return heal;
       }
}

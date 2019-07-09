
package themaze;

import javafx.util.Pair;


public class Commands {

         
      public static void InputError(){
         System.out.println("Error,input is incorrect");
     }
      
      public static void Help(){
          System.out.println();
          System.out.println("You can always enter -2 to go back to entering command");
          System.out.println();
          
          System.out.println("All the commands are case sensitive so they should be typed exactly as written");
          System.out.println();
          
          System.out.println("'Location' command shows the entire map with your position and the RoomId you are in.");
          System.out.println( "On the next lines the possible options for going in another room will be printed");
          System.out.println();
          
          System.out.println("'Go to' command followed with a RoomId to be entered on the next line(number for room or -1 for exit) moves you to adjacent place");
          System.out.println("In addition 'Location' will be executed.You lose HP for such change");
          System.out.println();
          
          System.out.println("'ItemsLocation' shows the items in your room(There won't be any in first room)");
          System.out.println();
          
          System.out.println("'Items' command prints the space in the backpack used for items");
          System.out.println(" and all the items in your backpack and then executes ItemsLocation");
          System.out.println();
          
          System.out.println("'Pick up' command with name of Item on next line picks up a an item");
          System.out.println("and puts it in your backpack if possible the items is executed");
          System.out.println();
          
          System.out.println("'Drop' command with name of item on next line drops an item from your backpack then items is executed");
          System.out.println();
          
          System.out.println("'Consume' consumes a food and you gain HP");
          System.out.println();
          
           System.out.println("'Equip' equips a weapon(drops if you had other) and you gain stats depending on it.You can have one at a time");
          System.out.println();
          
          System.out.println("'MyStats prints your stats and Items'");
          System.out.println();
          
          System.out.println("'Quit' to end the game");
            System.out.println();
      }
      
      public static void Location(Player p,Map m){
          System.out.println("Your RoomId is:"+p.GetCurrRoomId());
          m.Print();
          m.PrintAdjRooms(p.GetCurrRoomId());
      }
      
     public static int GoTo(Player p,Map m,int id){
         if(id==-2)return -2;
         
         if(m.adjacentRooms[p.GetCurrRoomId()].contains(id)==true){
             if(id!=-1){
                 
             p.SetRoomId(id);
            
             }
             return id;
         }else{
          
             return -3;
         }
     }
     
     public static void Items(Player p,Map m){
         System.out.println();
         System.out.println("Used capacity: " + p.backpackWeight + " of " + p.backpackCapacity + " maximum ");
         System.out.println();
         p.PrintItemsInBackpack();
         Commands.ItemsLocation(p, m);
     }
     public static void ItemsLocation(Player p,Map m){
         m.PrintItemsInRoom(p.GetCurrRoomId());
     }
     
     public static int PickUp(Player p,Map m,String itemName){
         
         if(itemName.equals("-2"))return -2;
        
         if(m.foodInRoom[p.GetCurrRoomId()].contains(itemName)==true || m.weaponsInRoom[p.GetCurrRoomId()].contains(itemName)==true){
             int weight=Player.MeasureItemWeight(itemName);
                if(weight+p.backpackWeight<=p.backpackCapacity && p.Weapons.containsKey(itemName)==false){               
                  return weight;
                }else{
                    return -3;
                }
         }else{
             return -3;
         }
     }
     public static int Drop(Player p,Map m,String itemName){
         
         if(itemName.equals("-2"))return -2;
         
         if(p.Food.containsKey(itemName) || p.Weapons.containsKey(itemName)){
             int weight=Player.MeasureItemWeight(itemName);
             return weight;
         }else{
             return -3;
         }
     }
     public static int ConsumeFood(Player p,Map m,String itemName){
           if(itemName.equals("-2"))return -2;
           
         int healing = Food.GetHealing(itemName);
         if(p.Food.containsKey(itemName)==true){
             return healing;
          }else{
             return -3;
         }
       
     }
     public static void MyStats(Player p,Map m){
          p.CurrHp();
          p.CombatStats();
          p.PrintItemsInBackpack();
      }
}   


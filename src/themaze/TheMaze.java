
package themaze;

import java.util.Scanner;

public class TheMaze {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
       
        int l,w;
        String name,command;
        boolean isItEnd=false;
        
         Map basic;
         
         Player p1;
         
         Food watermelon=new Food("Watermelon",1,5,1);
         Food banana=new Food("Banana",1,2,2);
         Food chicken= new Food("Chicken",2,4,5);
         Food steak = new Food("Steak",4,3,10);
         Weapon ordinarySword = new Weapon("Ordinary Sword",1,7,2,0);
         Weapon enchantedSword = new Weapon("Enchanted Sword",4,7,4,1);
         Weapon axe = new Weapon("Axe",2,5,3,0);
         Weapon shield = new Weapon("Shield",2,10,1,3);
         
           System.out.println("The Maze!!");
           
           System.out.print("Enter your name:");
           name=input.nextLine();
           
         System.out.print("Choose length of the square maze(between 5 and 20):");
         l=input.nextInt();
          input.nextLine();
         while(l<5 || l>20){
             
          Commands.InputError();
              System.out.print("Choose length of maze(between 5 and 20):");
         l=input.nextInt();
         input.nextLine();
         }
         
           p1=new Player(name,l,l);
           
           basic= new Map(l,l);
           basic.GenerateFood(steak);
           basic.GenerateFood(chicken);
           basic.GenerateFood(banana);
           basic.GenerateFood(watermelon);
           basic.GenerateWeapons(axe);
           basic.GenerateWeapons(shield);
           basic.GenerateWeapons(ordinarySword);
           basic.GenerateWeapons(enchantedSword);
           
            while(isItEnd==false){
                
                System.out.print("Enter a Command(Help if you want to see them):");
                command=input.nextLine();
             if(p1.HP<=0){
                 System.out.println("You are dead");
                 break;
             }
             if(basic.adjacentRooms[p1.GetCurrRoomId()].size()==0){
                 System.out.println("You were trapped by the maze so yuo are dead");
                 p1.HP=0;
                 break;
             }
                if(command.equals("Help")){
                    
                     Commands.Help();
                     
                }else{
                    if(command.equals("Location")){
                        
                         Commands.Location(p1, basic);
                         
                    }else{
                        if(command.equals("Go to")){
                            
                            int inpId=input.nextInt();
                            input.nextLine();
                            int retId,prevId=p1.GetCurrRoomId();
                            retId=Commands.GoTo(p1, basic, inpId);
                            
                            while(retId==-3){
                                
                                Commands.InputError();
                               Commands.Location(p1, basic);
                                System.out.println("You need to enter valid adjacent RoomId(-1 for exit)!!Enter again Id");
                                inpId=input.nextInt();
                            input.nextLine();
                                    retId=Commands.GoTo(p1, basic, inpId);
                                    
                            }
                            if(retId!=-2){
                                if(retId==-1){
                                   isItEnd=true; 
                                    System.out.println("Congratulations,you exited the maze!!");
                                }else{
                                   
                                      basic.ChangeMap((prevId/l)*2+1, (prevId%l)*2+1, '.');
                                      basic.ChangeMap((retId/l)*2+1, (retId%l)*2+1, 'P');
                                      System.out.println("You lost "+l+" HP due to movement");
                                      p1.HP-=l;
                                      p1.SetRoomId(retId);
                                      p1.CurrHp();
                                      if(basic.MonstersId[retId/l][retId%l]>0){
                                          if(Math.max(p1.pDefence,p1.pAttack)<basic.MonstersId[retId/l][retId%l]){
                                              int loss=basic.MonstersId[retId/l][retId%l]-Math.max(p1.pDefence,p1.pAttack);
                                              p1.HP-=loss;
                                              System.out.println("You lost "+loss+" HP due to monster encounter");
                                          }
                                      }
                                     Commands.Location(p1,basic);
                                }
                            }
                        }else{
                                if(command.equals("Items")){
                                    
                                    Commands.Items(p1,basic);
                                    
                                }else{
                                    if(command.equals("ItemsLocation")){
                                        
                                        Commands.ItemsLocation(p1, basic);
                                        
                                    }else{
                                        if(command.equals("Pick up")){
                                            
                                            String itemName=input.nextLine();
                                            int res;
                                            res=Commands.PickUp(p1, basic, itemName);
                                            
                                            if(res==-3){
                                                
                                                while(res==-3){
                                                    
                                                    Commands.InputError();
                                                    System.out.println("That Item cannot be found or does not exist or backack capacity will be exeeded if taken or you have it");
                                                    System.out.println("Please enter correct item name or press -2 to reset the query");
                                                    itemName=input.nextLine();
                                                     res=Commands.PickUp(p1, basic, itemName);
                                                     
                                                     
                                                }
                                                
                                            }
                                            
                                            if(res>=0){
                                                
                                                if(itemName.equals("Axe") || itemName.equals("Ordinary Sword") 
                                                        || itemName.equals("Shield") || itemName.equals("Enchanted Sword")){
                                                    
                                                    
                                                    p1.Weapons.put(itemName, 1);
                                                    basic.weaponsInRoom[p1.GetCurrRoomId()].remove(itemName);
                                                    p1.backpackWeight+=res;
                                                    
                                                }else{
                                                    
                                                    p1.Food.put(itemName, 1);
                                                    basic.foodInRoom[p1.GetCurrRoomId()].remove(itemName);
                                                    p1.backpackWeight+=res;
                                                    
                                                }
                                                Commands.Items(p1, basic);
                                            }
                                        }else{
                                            if(command.equals("Drop")){
                                                
                                                String itemName=input.nextLine();
                                                int res=Commands.Drop(p1, basic, itemName);
                                                
                                                while(res==-3){
                                                    
                                                    Commands.InputError();
                                                    System.out.println("You do not own the item or itdoes not exist");
                                                    itemName=input.nextLine();
                                                    res=Commands.Drop(p1, basic, itemName);
                                                    
                                                }
                                                
                                                if(res>=0){
                                                    
                                                    p1.backpackWeight-=res;
                                                    if(p1.Food.containsKey(itemName)){
                                                        p1.Food.remove(itemName);
                                                        basic.foodInRoom[p1.GetCurrRoomId()].add(itemName);
                                                        
                                                    }else{
                                                        
                                                        p1.Weapons.remove(itemName);
                                                        basic.weaponsInRoom[p1.GetCurrRoomId()].add(itemName);
                                                        
                                                    }
                                                }
                                                
                                            }else{
                                                
                                            if(command.equals("Quit")){
                                                
                                                break;
                                              }else{
                                                if(command.equals("Consume")){
                                                    
                                                    String itemName = input.nextLine();
                                                    int heal = Commands.ConsumeFood(p1, basic, itemName);
                                                    
                                                    while(heal==-3){
                                                        System.out.println("No such existing item or not owned one.Please enter again");
                                                        itemName = input.nextLine();
                                                        heal = Commands.ConsumeFood(p1, basic, itemName);
                                                        
                                                    }
                                                    
                                                    if(heal>=0){
                                                        
                                                        p1.HP+=heal;
                                                        p1.Food.remove(itemName);
                                                        p1.backpackWeight-=Player.MeasureItemWeight(itemName);
                                                        p1.CurrHp();
                                                    }
                                                }else{
                                                    if(command.equals("Equip")){
                                                        
                                                     String itemName = input.nextLine();
                                                      if(itemName!="-2"){
                                                     
                                                      while(p1.Weapons.containsKey(itemName)==false){
                                                          
                                                             System.out.println("No such weapon existing or in your backpack.Please enter again");
                                                             itemName = input.nextLine();
                                                             
                                                             if(itemName=="-2")break;
                                                             
                                                         }
                                                      
                                                           int att=Weapon.BonusAtt(itemName),def=Weapon.BonusDef(itemName);
                                                           p1.pAttack=att;
                                                           p1.pDefence=def;
                                                           
                                                           if(p1.isEquipped==false){
                                                               
                                                               p1.isEquipped=true;
                                                               
                                                           }else{
                                                               
                                                               int res=Commands.Drop(p1, basic, itemName);
                                                               p1.backpackWeight-=res;
                                                                basic.weaponsInRoom[p1.GetCurrRoomId()].add(itemName);
                                                                
                                                           }
                                                           
                                                           p1.equippedWeapon=itemName;
                                                           
                                                      }
                                                    }else{
                                                        if(command.equals("MyStats")){
                                                            Commands.MyStats(p1, basic);
                                                        }else{
                                                            if(command.equals("-2")){
                                                    
                                                                continue;
                                                            }else{
                                                    
                                                            Commands.InputError();
                                                            }
                                                        }
                                                    }
                                                }
                                             }
                                         }
                                        }
                                    }
                                }
                        }
                    }
                }
                ///
            }
    }

}
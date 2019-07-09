
package themaze;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


public class Map {
      private char [][]MapArr = new char[50][50];
      private int [][] RoomId  = new int [21][21];
      public int [][] MonstersId = new int [21][21];
     public LinkedList<Integer>adjacentRooms[];
    public LinkedList<String>weaponsInRoom[];
     public LinkedList<String>foodInRoom[];
        private Random rand = new Random();
    private int Mlength=5,Mwidth=5;
  
  
    public Map(int l,int w){
        Mlength=l;
       Mwidth=w; 
       adjacentRooms = new LinkedList[Mlength*Mwidth+1];
       weaponsInRoom =new LinkedList[Mlength*Mwidth+1];
       foodInRoom =new LinkedList[Mlength*Mwidth+1];
      
        for (int i = 0; i < Mlength*Mwidth; i++) {
            adjacentRooms[i]=new LinkedList<>();
            weaponsInRoom[i]=new LinkedList<>();
            foodInRoom[i] = new LinkedList<>(); 
        }
        for (int i = 0; i < Mlength*Mwidth/2; i++) {
            int x,y;
            x=rand.nextInt(Mlength);
            y=rand.nextInt(Mlength);
            MonstersId[x][y]+=1;
        }
        for (int i = 0; i < (Mlength*Mlength)/4; i++) {
            int x=0,y=0;
            x=rand.nextInt(Mlength)+1;
            y=rand.nextInt(Mlength)+1;
            while(MapArr[x*2][y*2-1]=='|' || MapArr[x*2][y*2-1]=='-'){
                 x=rand.nextInt(Mlength)+1;
            y=rand.nextInt(Mlength)+1;
            }
            MapArr[x*2][y*2-1]='-';
        }
         for (int i = 0; i < (Mlength*Mlength)/4; i++) {
            int x=0,y=0;
            x=rand.nextInt(Mlength)+1;
            y=rand.nextInt(Mlength)+1;
            while(MapArr[x*2-1][y*2]=='|' || MapArr[x*2-1][y*2]=='-'){
                 x=rand.nextInt(Mlength)+1;
            y=rand.nextInt(Mlength)+1;
            }
            MapArr[x*2-1][y*2]='|';
        }
        for(int i=0;i<Mlength*2+1;i++){
            for(int j=0;j<Mwidth*2+1;j++){
                if(i%2==0){
                    if(i==0){
                    MapArr[i][j]='-'; 
                    }
                }else{
                    if(j%2==0){
                        if(j==0){
                        MapArr[i][j]='|';
                        }
                    }else{
                        if(i/2==0 && j/2==0){
                            MapArr[i][j]='P';
                        }else{
                            MapArr[i][j]='.';
                        }
                        RoomId[i/2][j/2]=j/2+(i/2)*Mlength;
                       int currId=j/2+(i/2)*Mlength;
                      
                     
                           if(i>0 && MapArr[i-1][j]!='-' && MapArr[i-1][j]!='|'){
                            adjacentRooms[currId].add(currId-Mlength);
                           }
                       if(i/2==Mwidth-1){
                            adjacentRooms[currId].add(-1);
                       }else{
                               if(MapArr[i+1][j]!='-' && MapArr[i+1][j]!='|'){
                            adjacentRooms[currId].add(currId+Mlength);
                               }
                       }
                        if(j>0 && MapArr[i][j-1]!='-' && MapArr[i][j-1]!='|'){
                           adjacentRooms[currId].add(currId-1);
                        }
                        if(j/2==Mlength-1){
                           adjacentRooms[currId].add(-1);
                       }else{
                                if(MapArr[i][j+1]!='-' && MapArr[i][j+1]!='|'){
                           adjacentRooms[currId].add(currId+1);
                                }
                       }
                    }
                }
            }
        }
    }
    
    public  void ChangeMap(int i,int j,char c){
        MapArr[i][j]=c;
    }
    
    public void GenerateFood(Food f){
     int basicRate = (2*Mlength*Mwidth)/3;
        for (int i = 0; i < basicRate/f.rarity; i++) {
            int room=0;
            room=rand.nextInt(Mlength*Mwidth);
            while(foodInRoom[room].contains(f.Fname)==true || room==0){
                 room=rand.nextInt(Mlength*Mwidth);
            }
            foodInRoom[room].add(f.Fname);
        }
    }
    
    public void GenerateWeapons(Weapon w){
         int basicRate = (2*Mlength*Mwidth)/3;
           for (int i = 0; i < basicRate/w.rarity; i++) {
                 int room=0;
            room=rand.nextInt(Mlength*Mwidth);
            while(weaponsInRoom[room].contains(w.Wname)==true || room==0){
                 room=rand.nextInt(Mlength*Mwidth);
            }
            weaponsInRoom[room].add(w.Wname);
           }
    }
    
    public void PrintItemsInRoom(int Id){
        System.out.println("Food found in room:");
        for (int i = 0; i < foodInRoom[Id].size(); i++) {
            System.out.println(foodInRoom[Id].get(i));
        }
         System.out.println();
        System.out.println("Weapons found in room:");
        for (int i = 0; i < weaponsInRoom[Id].size(); i++) {
            System.out.println(weaponsInRoom[Id].get(i));
        }
         System.out.println();
    }
    
    public void PrintAdjRooms(int Id){
        int countOut=0;
        System.out.println("Options:");
        for (int i = 0; i < adjacentRooms[Id].size(); i++) {
           if(adjacentRooms[Id].get(i)==-1){
               countOut++;
           }else{
               System.out.println("Room:"+adjacentRooms[Id].get(i));
           }
        }
        if(countOut>0){
            System.out.println("Exit the Maze");
        }
    }
    
    public void Print(){
        for (int i = 0; i < Mlength*2+1; i++) {
            for (int j = 0; j < Mwidth*2+1; j++) {
              
                System.out.print(MapArr[i][j]);
                
            }
            System.out.println();
        }
         
    }
    
  
}

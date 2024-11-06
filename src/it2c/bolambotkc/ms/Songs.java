
package it2c.bolambotkc.ms;

import java.util.Scanner;


public class Songs {
    public void sDetails(){
        
      Scanner sc = new Scanner(System.in);
      String response;
      
      do{
            System.out.println("\n--------------------------");
            System.out.println("  SONG PANEL               ");
            System.out.println("1. ADD SONG                ");
            System.out.println("2. VIEW SONG               ");
            System.out.println("3. UPDATE SONG             ");
            System.out.println("4. DELETE SONG             ");
            System.out.println("5. EXIT                    ");
            System.out.println("----------------------------");

            System.out.print("Enter Selection: ");
            int action= sc.nextInt();
            Songs ss = new Songs ();
            while (action < 1 || action > 5) {
                      System.out.print("Invalid selection, Try Again: ");
                      action = sc.nextInt();
                   }

           switch (action){
                case 1:
                    ss.addSong();
                    ss.viewSong();
                    break;

                case 2:
                    ss.viewSong();
                    
                    break;
                
                case 3:
                    ss.viewSong();
                    ss.updateSong();
                    ss.viewSong();
                  
                    break;
                
                case 4:
                    ss.viewSong();
                    ss.deleteSong();
                    ss.viewSong();
                    break;
                    
                 case 5:    
                     
                     break;

           }   
           System.out.print("Do you want to continue? (yes/no): ");
           response = sc.next();
      }while(response.equalsIgnoreCase("yes"));
    }
    
    public void addSong(){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter Song Title: ");
        String s_title = sc.nextLine();
        System.out.print("Enter Song Artist: ");
        String s_artist = sc.nextLine();
        System.out.print("Enter Song Duration: ");
        int s_duration = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Song Release Date(MM-DD-YYYY): ");
        String s_rDate = sc.nextLine();
        
        
        String qry = "INSERT INTO Song(s_title, s_artist, s_duration, s_releaseDate) VALUES (?, ?, ?, ?)";
        config conf = new config();
        conf.addRecord(qry, s_title, s_artist, s_duration, s_rDate);
        
    }
    
    public void viewSong(){
        String qry = "SELECT * FROM Song";
       String[] hrds = {"ID", "Song Title", "Artist", "Duration", "ReleaseDate"};
       String[] clmns = {"s_id", "s_title", "s_artist", "s_duration", "s_releaseDate"};

       config conf = new config();
       conf.viewRecords(qry, hrds, clmns);
    }
    
    private void updateSong(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter ID to Update: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT s_id FROM Song WHERE s_id=?  ",id)==0){
              System.out.println("Selected ID doesn't exist");
              System.out.print("Select Song ID Again: ");
              id = sc.nextInt();
        }
        sc.nextLine();
         
        System.out.print("New Song Title: ");
        String s_title = sc.nextLine();
        System.out.print("New Song Artist: ");
        String s_artist = sc.nextLine();
        System.out.print("New Song Duration: ");
        int s_duration = sc.nextInt();
        sc.nextLine();
        System.out.print("New Song Release Date(MM-DD-YYYY): ");
        String s_rDate = sc.nextLine();
        
        String qry = "UPDATE Song SET s_title = ?, s_artist = ?, s_duration = ? , s_releaseDate = ? WHERE s_id = ? ";
        
        conf.updateRecord(qry, s_title, s_artist, s_duration, s_rDate, id);
    }
     private void deleteSong(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter ID to Delete: ");
        int id = sc.nextInt();
        
         while(conf.getSingleValue("SELECT s_id FROM Song WHERE s_id=?  ",id)==0){
              System.out.println("Selected ID doesn't exist");
              System.out.print("Select Song ID Again: ");
              id = sc.nextInt();
        }
        
        String qry = "DELETE FROM Song WHERE s_id = ?";
        conf.deleteRecord(qry, id);
        
        
        
    }
    
    
}



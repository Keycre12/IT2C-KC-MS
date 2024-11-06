
package it2c.bolambotkc.ms;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Favorite {
    public void fSong(){
        Scanner sc = new Scanner(System.in);
      String response;
      
      do{
            System.out.println("\n--------------------------");
            System.out.println("  FAVORITES                ");
            System.out.println("1. ADD FAVORITE            ");
            System.out.println("2. VIEW FAVORITE           ");
            System.out.println("3. UPDATE FAVORITE         ");
            System.out.println("4. DELETE FAVORITE         ");
            System.out.println("5. EXIT                    ");
            System.out.println("----------------------------");

            System.out.print("Enter Selection: ");
            int action= sc.nextInt();
            Favorite fs = new Favorite ();
            while (action < 1 || action > 5) {
                      System.out.print("Invalid selection, Try Again: ");
                      action = sc.nextInt();
                   }


           switch (action){
                case 1:
                    fs.addFavorite();
                    fs.viewFavorite();
                    break;

                case 2:
                    fs.viewFavorite();
                    
                    break;
                
                case 3:
                    fs.viewFavorite();
                    fs.updateFavorite();
                    fs.viewFavorite();
                    break;
                
                case 4:
                    fs.viewFavorite();
                    fs.deleteFavorite();
                    fs.viewFavorite();
                    break;
                    
                 case 5:    
                     
                     break;

           }   
           System.out.print("Do you want to continue? (yes/no): ");
           response = sc.next();
      }while(response.equalsIgnoreCase("yes"));
    }
    
    private void addFavorite(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        User cs = new User ();
        cs.viewUser();
         
        System.out.print("Enter the ID of the User: ");
        int u_id = sc.nextInt();
        
        String usql = "SELECT u_id FROM User WHERE u_id = ?";
        while(conf.getSingleValue(usql, u_id) == 0){
            System.out.print("User does not exist, Select ID again: ");
            u_id = sc.nextInt();
        }
        Songs ss = new Songs();
        ss.viewSong();
        System.out.print("Enter the ID of the Song: ");
        int s_id = sc.nextInt();
        
        String ssql = "SELECT s_id FROM Song WHERE s_id = ?";
        while(conf.getSingleValue(ssql, s_id) == 0){
            System.out.print("Song does not exist, Select ID again: ");
            s_id = sc.nextInt();
        }

        
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
        
        String Favqry = "INSERT INTO Favsong(u_id, s_id, fs_date ) VALUES(?, ?, ?)";
        
        conf.addRecord(Favqry, u_id, s_id, date);
       
    }
    
    public void viewFavorite(){
  
       String qry = "SELECT fs_id, u_fname, s_title, fs_date FROM Favsong"
               + " LEFT JOIN User ON User.u_id = Favsong.u_id "
               + "LEFT JOIN Song ON Song.s_id = Favsong.s_id";
       
       String[] hrds = {"FSID", "USER", "SONG", "DATE ADDED"};
        String[] clmns = {"fs_id", "u_fname", "s_title", "fs_date"};

       config conf = new config();
       conf.viewRecords(qry, hrds, clmns);
    }
    
    public void updateFavorite(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter FSID to Update: ");
        int id = sc.nextInt();
        
         while(conf.getSingleValue("SELECT fs_id FROM Favsong WHERE fs_id=?  ",id)==0){
              System.out.println("Selected ID doesn't exist");
              System.out.print("Select FSID Again: ");
              id = sc.nextInt();
        }
        Songs ss = new Songs();
        ss.viewSong();
        System.out.print("Enter the FSID to Update the Song: ");
        int s_id = sc.nextInt();
        
        String ssql = "SELECT s_id FROM Song WHERE s_id = ?";
        while(conf.getSingleValue(ssql, s_id) == 0){
            System.out.print("Song does not exist, Select ID again: ");
            s_id = sc.nextInt();
            
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
        
         String uqry = "UPDATE Favsong SET s_id = ?, fs_date = ? WHERE s_id = ? ";
         conf.updateRecord(uqry, s_id, date,  id);
         
        }
        
    }
    
    public void deleteFavorite(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter FSID to Delete: ");
        int id = sc.nextInt();
        
         while(conf.getSingleValue("SELECT fs_id FROM Favsong WHERE fs_id=?  ",id)==0){
              System.out.println("Selected ID doesn't exist");
              System.out.print("Select Favsong ID Again: ");
              id = sc.nextInt();
        }
        
        String qry = "DELETE FROM Favsong WHERE fs_id = ?";
        conf.deleteRecord(qry, id);
        
        
        
        
    }
    
}


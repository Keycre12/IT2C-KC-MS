
package it2c.bolambotkc.ms;

import java.util.Scanner;


public class User {
    public void uDetails(){
        
      Scanner sc = new Scanner(System.in);
      String response;
      
      do{
            System.out.println("\n--------------------------");
            System.out.println("  USER PANEL               ");
            System.out.println("1. ADD USER                ");
            System.out.println("2. VIEW USER               ");
            System.out.println("3. UPDATE USER             ");
            System.out.println("4. DELETE USER             ");
            System.out.println("5. EXIT                    ");
            System.out.println("----------------------------");

            System.out.print("Enter Selection: ");
            int action= sc.nextInt();
            User cs = new User ();
            while (action < 1 || action > 5) {
                      System.out.print("Invalid selection, Try Again: ");
                      action = sc.nextInt();
                   }

           switch (action){
                case 1:
                   cs.addUser();
                   cs.viewUser();
                    break;

                case 2:
                    cs.viewUser();
                    break;
                
                case 3:
                    cs.viewUser();
                    cs.updateUser();
                    cs.viewUser();
                    break;
                
                case 4:
                    cs.viewUser();
                    cs.deleteUser();
                    cs.viewUser();
                    break;
                    
                 case 5:    
                     
                     break;

           }   
           System.out.print("Do you want to continue? (yes/no): ");
           response = sc.next();
      }while(response.equalsIgnoreCase("yes"));
    }
    
    public void addUser(){
         Scanner sc = new Scanner(System.in);
         
         System.out.print("User First Name: ");
         String u_fname = sc.nextLine();
         System.out.print("User Last Name: ");
         String u_lname = sc.nextLine();
         System.out.print("User Email: ");
         String u_email = sc.nextLine();
         
         String qry = "INSERT INTO User(u_fname, u_lname, u_email) VALUES (?, ?, ?)";
         config conf = new config();
         conf.addRecord(qry, u_fname, u_lname, u_email);
    }
   
    public void viewUser(){
  
       String qry = "SELECT * FROM User";
       String[] hrds = {"ID", "FIRSTNAME", "LASTNAME", "EMAIL"};
       String[] clmns = {"u_id", "u_fname", "u_lname", "u_email"};

       config conf = new config();
       conf.viewRecords(qry, hrds, clmns);
    }
    
    private void updateUser(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter ID to Update: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT u_id FROM User WHERE u_id=?  ",id)==0){
              System.out.println("Selected ID doesn't exist");
              System.out.print("Select User ID Again: ");
              id = sc.nextInt();
        }
        sc.nextLine();
         
        System.out.print("New User First Name: ");
        String u_fname = sc.nextLine();
        System.out.print("New User Last Name: ");
        String u_lname = sc.nextLine();
        System.out.print("New User Email: ");
        String u_email = sc.nextLine();
        String qry = "UPDATE User SET u_fname = ?, u_lname = ?, u_email = ? WHERE u_id = ? ";
        
        conf.updateRecord(qry, u_fname, u_lname, u_email, id);
    }
    
    public void deleteUser(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter ID to Delte: ");
        int id = sc.nextInt();
        
         while(conf.getSingleValue("SELECT u_id FROM User WHERE u_id=?  ",id)==0){
              System.out.println("Selected ID doesn't exist");
              System.out.print("Select User ID Again: ");
              id = sc.nextInt();
        }
        
        String qry = "DELETE FROM User WHERE u_id = ?";
        conf.deleteRecord(qry, id);
        
        
        
    }
}

              
 
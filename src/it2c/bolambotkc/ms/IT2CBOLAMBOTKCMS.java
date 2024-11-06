
package it2c.bolambotkc.ms;

import java.util.Scanner;


public class IT2CBOLAMBOTKCMS {

   
    public static void main(String[] args) {
           
      Scanner sc = new Scanner(System.in);
      boolean exit = true;
      
      do{
        System.out.println("\n--------------------------");
        System.out.print("WELCOME TO MUSIC TRACKS  |");
        System.out.println("\n--------------------------");
        System.out.println("1. USER  ");
        System.out.println("2. SONGS ");
        System.out.println("3. FAVORITE");
        System.out.println("4. FAVORITE ");
        System.out.println("5. EXIT ");
        System.out.println("----------------------------");


        System.out.print("Enter Selection: ");
        int action= sc.nextInt();
        while (action < 1 || action > 5) {
                  System.out.print("Invalid selection, Try Again: ");
                  action = sc.nextInt();
               }


       switch (action){

            case 1:
               User cs = new User ();
               cs.uDetails();
                break;
            
            case 2:
                Songs ss = new Songs();
                ss.sDetails();
                break;
            
            case 3:
                Favorite fs = new Favorite ();
                fs.fSong();
                break;
                
            case 5:
               System.out.print("Exit Selected...type 'yes' to continue:" );
                String resp = sc.next();
                if(resp.equalsIgnoreCase("yes")){
                    exit = false;
                }
                break;


      }
     }while(exit);


    }
    
}

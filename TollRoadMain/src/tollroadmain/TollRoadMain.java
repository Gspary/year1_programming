
package tollroadmain;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TollRoadMain {
    
    //The initial creation of a toll road
    public static  TollRoad initialiseTollRoadFromFile() throws FileNotFoundException{
        
      //Sets up file for use in simulation
      ArrayList<CustomerAccount> customers = new ArrayList<>();
      CustomerAccount customer;
      TollRoad toll   = new TollRoad(customers, 0);
      String fileName = "customerData.txt";
      Scanner initialiseScanner = new Scanner(new File(fileName));
      initialiseScanner.useDelimiter(",|#");
      
      //starts to read in from the file
      while (initialiseScanner.hasNext()){
          String[] custInfo;
          custInfo = new String[8];
          //separates the information that is read in 
          for (int i = 0; i < custInfo.length; i++){
              custInfo[i] = initialiseScanner.next();
          }
          //if the vehicle type is a car, it creates a new car and adds the 
          //customer information into the arraylist
          if(custInfo[0].equalsIgnoreCase("Car")){
              Car carTemp     = new Car(custInfo[1], custInfo[4], Integer.parseInt(custInfo[5]));
              customer = new CustomerAccount(custInfo[2], custInfo[3], carTemp, Integer.parseInt(custInfo[5]));
              if(custInfo[7].equalsIgnoreCase("STAFF")){   
                 customer.activateStaffDiscount();
              } else if(custInfo[7].equalsIgnoreCase("FRIENDS_AND_FAMILY")){
                 customer.activateFriendsAndFamilyDiscount();
              } else if(custInfo[7].equalsIgnoreCase("NONE")){
                 customer.deactivateDiscount();
              }
              toll.addCustomer(customer);
             
             //if the vehicle type is a van, it creates a new van and adds the 
             //customer information into the arraylist  
          } else if (custInfo[0].equalsIgnoreCase("Van")){
              Van vanTemp     = new Van(custInfo[1], custInfo[4], Integer.parseInt(custInfo[5]));
              customer = new CustomerAccount(custInfo[2], custInfo[3], vanTemp, Integer.parseInt(custInfo[5]));    
              if(custInfo[7].equalsIgnoreCase("STAFF")){   
                 customer.activateStaffDiscount();
              } else if(custInfo[7].equalsIgnoreCase("FRIENDS_AND_FAMILY")){
                 customer.activateFriendsAndFamilyDiscount();
              } else if(custInfo[7].equalsIgnoreCase("NONE")){
                 customer.deactivateDiscount();
              }
              toll.addCustomer(customer);
            
             //if the vehicle type is a truck, it creates a new truck and adds the 
             //customer information into the arraylist  
          } else if (custInfo[0].equalsIgnoreCase("Truck")){
              Truck truckTemp = new Truck(custInfo[1], custInfo[4], Integer.parseInt(custInfo[5]));  
              customer = new CustomerAccount(custInfo[2], custInfo[3], truckTemp, Integer.parseInt(custInfo[5]));
              if(custInfo[7].equalsIgnoreCase("STAFF")){   
                 customer.activateStaffDiscount();
              } else if(custInfo[7].equalsIgnoreCase("FRIENDS_AND_FAMILY")){
                 customer.activateFriendsAndFamilyDiscount();
              } else if(custInfo[7].equalsIgnoreCase("NONE")){
                 customer.deactivateDiscount();
              }
              toll.addCustomer(customer); 
          } 
      }
     return toll; 
    }
    
    public static TollRoad simulateFromFile(TollRoad toll) throws FileNotFoundException, IOException {
        //Sets up file for use in simulation
        String fileName        = "transactions.txt";
        File transactionFile   = new File(fileName);
        Scanner transacScanner = new Scanner(new File(fileName));
        transacScanner.useDelimiter(",|\\$");
        
        //sets up files for output
        String FileName2   = "output.txt";
        File   OutputFile  = new File(FileName2);
        FileWriter fw      = new FileWriter(OutputFile);
        BufferedWriter bw  = new BufferedWriter(fw); 
        
        //reads through the file and starts the transactions
        while(transacScanner.hasNext()){
            String nextMethod = transacScanner.next();
            //if the next method is adding money to the account, it will add the
            //money into the account or throw an error
            if(nextMethod.equalsIgnoreCase("addFunds")){
                String regNum = transacScanner.next();
                int amount    = transacScanner.nextInt();
                try {
                    toll.findCustomer(regNum).addFunds(amount);
                    System.out.println(regNum + ": " + amount + " added successfully");
                    bw.write(regNum + ": " + amount + " added successfully");
                    bw.newLine();
                }
                catch(CustomerNotFoundException e) {
                    System.err.println(regNum + ": " + amount + " failed to add to account. " + e);
                    bw.write(regNum + ": " + amount + " failed to add to account. " + e);
                    bw.newLine();
                }
                
            //if the next method is going through the toll road, it will 
            //calculate the cost of the trip, or throw an error
            } else if (nextMethod.equalsIgnoreCase("makeTrip")){
                String regNum = transacScanner.next();
                try {
                    toll.chargeCustomer(regNum);
                    System.out.println(regNum + ": Trip completed succesfully");
                    bw.write(regNum + ": Trip completed succesfully");
                    bw.newLine();
                }
                catch(CustomerNotFoundException | InsufficientAccountBalanceException e){
                    System.err.println(regNum + ": Trip failed to complete. " + e);
                    bw.write(regNum + ": Trip failed to complete. " + e);
                    bw.newLine();
                }
            }
        }
        bw.write("This toll road has made: " + toll.getMoneyMade());
        bw.close();
        return toll;      
    }
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
       try{
        TollRoad tollRoadTest = initialiseTollRoadFromFile();
        simulateFromFile(tollRoadTest);
        //predicted result of 39400
        System.out.println("The toll road made: " +tollRoadTest.getMoneyMade());
        
       
       } catch(FileNotFoundException e) {
           System.err.println("Exception: " + e + " caught.");
       }
        
    }
    
}

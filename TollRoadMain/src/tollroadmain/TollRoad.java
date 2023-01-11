/*
 * A class to store and simulate the toll road when used.
 */
package tollroadmain;
import java.util.ArrayList;


public class TollRoad {
    ArrayList<CustomerAccount> customers = new ArrayList<>();
    private int moneyMade;
    
    //default constructor
    public TollRoad(ArrayList<CustomerAccount> customers , int moneyMade){
        this.customers = customers; 
        this.moneyMade = moneyMade;    
    }
    
    //adds a customer to the account list
    public void addCustomer(CustomerAccount acc){
        this.customers.add(acc);
    }
    
    //Searches through the accounts to find the correct account
    // if no account found, throws Exception
    public CustomerAccount findCustomer(String regNum) throws CustomerNotFoundException{
     for (int i =0;i<this.customers.size(); i++){
         if(this.customers.get(i).getVehicle().getVehicleReg().equalsIgnoreCase(regNum)){
             return this.customers.get(i);
         }
     }
     throw new CustomerNotFoundException();
    }
    
    //Uses Find Customer, and if found then charges them for a trip through the 
    //toll road
    public void chargeCustomer(String regNum) throws CustomerNotFoundException, InsufficientAccountBalanceException{
       
        this.moneyMade = this.moneyMade + this.findCustomer(regNum).makeTrip();        
    }
    
    //accessor methods for moneyMade and customer accounts
    public int getMoneyMade(){
        return this.moneyMade;
    }
    
    public ArrayList getCustomers(){
       return customers; 
    }
    
    
    //test harness
    public static void main(String args[]){
       Car c1 = new Car("AK34S3", "Audi", 3); 
       CustomerAccount cA1 = new CustomerAccount("James", "Mason", c1, 10000);  
       ArrayList<CustomerAccount> customersTest = new ArrayList<>();
       TollRoad t1 = new TollRoad(customersTest, 0);
       t1.addCustomer(cA1);
       
       try{
       t1.chargeCustomer("AK34S3");
       //Expected result: 500
       System.out.println(t1.getMoneyMade());
       }
       catch(CustomerNotFoundException | InsufficientAccountBalanceException e){
           System.err.println("Error: " + e);
       }
    }
    
    
}

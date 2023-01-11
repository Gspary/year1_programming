/*
  A Class to store the information about Staff
  And their discounts
 */
package tollroadmain;


public class CustomerAccount implements Comparable {
    
    //Variables needed for Customer account
    private String firstName;
    private String surname;
    private Vehicle vehicle;
    private int accountBalance;
    private int tripCost;
    //Enum for Customer Account
    private enum discountType{
        NONE,STAFF,FRIENDS_AND_FAMILY
    }
    private discountType discount;
    
    //default constructor
    public CustomerAccount(String firstName, String surname, Vehicle vehicle, 
                           int accountBalance){
        this.firstName      = firstName;
        this.surname        = surname;
        this.vehicle        = vehicle;
        this.accountBalance = accountBalance;
        this.discount       = discountType.NONE;
    }
    
    //activates Staff discount
    public discountType activateStaffDiscount(){
        this.discount   = discountType.STAFF;
        return this.discount;
    }
    
    //activates Freinds & Family Discount
    public discountType activateFriendsAndFamilyDiscount(){
        this.discount = discountType.FRIENDS_AND_FAMILY;
        return this.discount;
    }
    
    //removes discount
    public discountType deactivateDiscount(){
        this.discount = discountType.NONE;
        return this.discount;
    }
    
    //adds money to the account
    public void addFunds(int amount){
       this.accountBalance = this.accountBalance + amount;    
    }
    
    //processes a trip through the toll road
    public int makeTrip() throws InsufficientAccountBalanceException{   
        //reduces cost by 50% if a staff member
        if (this.discount == discountType.STAFF){
            this.accountBalance= this.accountBalance 
                 - (vehicle.calculateBasicTripCost()/2);
            tripCost = (vehicle.calculateBasicTripCost()/2);
            
        //reduces cost by 10% if a family/friend of a staff member    
        } else if (this.discount == discountType.FRIENDS_AND_FAMILY){
            this.accountBalance = (this.accountBalance 
                  -(vehicle.calculateBasicTripCost()/10)*9);
            tripCost = ((vehicle.calculateBasicTripCost()/10)*9);
            
        //Calculates a normal trip cost    
        } else if (this.discount == discountType.NONE){
            this.accountBalance =(this.accountBalance 
                  - vehicle.calculateBasicTripCost());
            tripCost = vehicle.calculateBasicTripCost();
            
        }   
        
       
        
     return tripCost;
    }
    
    //accessor fields for the variables
    public String getFirstName(){
        return this.firstName;
    }
    
    public String getSurname(){
        return this.surname;
    }
    
    public int getAccountBalance(){
        return this.accountBalance;
    }
    
    public Vehicle getVehicle(){
        return this.vehicle;
    }
 
    
    //test harness
    public static void main(String args[]){
    Car c1 = new Car("AK34S3", "Audi", 3); 
    CustomerAccount cA1 = new CustomerAccount("James", "Mason", c1, 10000);
    cA1.addFunds(500);
    //Expected Result: 10500
    System.out.println(cA1.accountBalance);

    try{
    cA1.makeTrip();
    //Expected Result: 10000
    System.out.println(cA1.getAccountBalance());
    }
    catch(InsufficientAccountBalanceException e){
     System.err.println("Error: " + e);
    }
    
    }
    
    
    //Alphabetical ordering of the Registrations
    @Override
    public int compareTo(Object o) {
       int compare = this.vehicle.getVehicleReg().compareTo(vehicle.getVehicleReg());
    if (compare >0) {
        return 1;
    } else if (compare < 0) {
        return -1;
    } else {
        return 0;
     }
    }
}


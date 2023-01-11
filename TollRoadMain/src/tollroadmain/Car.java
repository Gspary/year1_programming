/*
    A subclass of vehicle: Car
 */
package tollroadmain;


public class Car extends Vehicle{
    
    private int noOfSeats;
    
    //default constructor
    public Car(String vehicleReg, String vehicleMake, int noOfSeats){
        super(vehicleReg, vehicleMake); 
        this.noOfSeats = noOfSeats ;
    }
    
    //accessor method for no. of seats
    public int getNoOfSeat(){
        return this.noOfSeats;
    }
    
    
    //calculates trip cost, based on no. of seats
    @Override
    public int calculateBasicTripCost(){
        if(noOfSeats<=5){
            return 500;
        } else if(noOfSeats>5){
            return 600;
        } else {
            return 0;
        }
    }
    
    //Test Harness for Car
    public static void main(String args[]){
        Car c1 = new Car("AK34S3", "Audi", 3);
       
        //Expected Result: 500
        System.out.println(c1.calculateBasicTripCost());
    }
}

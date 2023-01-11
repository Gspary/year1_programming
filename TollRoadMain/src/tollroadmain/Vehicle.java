/*
 A program to be a superclass for various vehicle types
 */
package tollroadmain;


public abstract class Vehicle {
    
    private String vehicleReg;
    private String vehicleMake;
    
    //default constructor
    public Vehicle(String vehicleReg, String vehicleMake){
        this.vehicleReg  = vehicleReg;
        this.vehicleMake = vehicleMake;
    }
    
    //abstract calculation trip method
    abstract public int calculateBasicTripCost();
    
    //accessor methods
    public String getVehicleReg(){
        return vehicleReg;
    }
    
    public String getVehicleMake(){
        return vehicleMake;
    }
    
   // public static void main(String args[]){  
   //}
    
}

/*
   A Subclass for vehicle: Truck
 */
package tollroadmain;


public class Truck extends Vehicle {
    
    private int numTrailers;
    
    //Default contstuctor
    public Truck(String vehicleReg, String vehicleMake, int numTrailers){
        super(vehicleReg, vehicleMake); 
        this.numTrailers = numTrailers;
    }
    
    //Calculate's trip cost based on No. of Trailers
     @Override
     public int calculateBasicTripCost(){
         if(numTrailers == 1){
             return 1250;
         } else if (numTrailers >=2) {
             return 1500;
         } else
             return 0;
     }
     
    //test harness 
    public static void main(String args[]){
        Truck t1 = new Truck("ASK4N3W", "Toyota", 2);
        //Expected result: 1500
        System.out.println(t1.calculateBasicTripCost());
    }
}

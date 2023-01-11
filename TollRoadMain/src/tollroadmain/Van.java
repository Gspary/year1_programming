/*
    A Subclass of vehicle: Van
 */
package tollroadmain;

public class Van extends Vehicle {
    
    private int payload;
    
    public Van(String vehicleReg, String vehicleMake, int payload){
        super(vehicleReg, vehicleMake); 
        this.payload = payload;
    }
    
    //calculates trip cost, based on van weight
    @Override
    public int calculateBasicTripCost(){
        if (payload<=500) {
            return 500;
        } else 
        if (payload<=800 && payload>=600){
        return 750;
    }
          else
        if (payload>800) {
            return 1000;
        }
        else
            return 0;
            
    }
    
    //Test Harness
    public static void main(String args[]){
        Van v1 = new Van("AK34S3", "Audi", 600);
        //Expected Result: 750
        System.out.println(v1.calculateBasicTripCost());
        
    }
    
}

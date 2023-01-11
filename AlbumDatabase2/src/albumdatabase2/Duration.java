/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package albumdatabase2;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Duration {
    
    DecimalFormat format = new DecimalFormat("00");
    private int hours;
    private int mins;
    private int secs;
    ArrayList<Duration> d = new ArrayList();
    
    //base constructor
    public Duration()
    {
        hours = 0;
        mins  = 0;
        secs  = 0;
    }
    
    public Duration(int hours, int mins, int secs)
    {
        this.hours = hours;
        this.mins  = mins;
        this.secs  = secs;
        
        outOfBoundsCheck();
    }
    
    
    public Duration(String str)
    {
        String[] string = str.split(":");
        this.hours = Integer.parseInt(string[0]);
        this.mins = Integer.parseInt(string[1]);
        this.secs = Integer.parseInt(string[2]);
        
        outOfBoundsCheck();
    }
    
    
    @Override
    public String toString()
    {
        return format.format(this.hours) + ":" 
             + format.format(this.mins) + ":" + format.format(this.secs);
        
    }
    
  
    
    
    //Overflow of hours/mins/secs check
    private void outOfBoundsCheck(){
       //checks overflow of secs, corrects it
        if (secs > 59)
        {
         int outOfBoundsSecs = secs/60;
         mins += outOfBoundsSecs;
         
         secs%=60;
        }
       //Checks overflow of mins, corrects it.
        if (mins > 59)
        {
         int outOfBoundsMins = mins/60;
         hours += outOfBoundsMins;
         
         mins%= 60;
        }
        
    }
    
    public static void main(String args[])
    {
       Duration d1 = new Duration("02:04:61");
       Duration d2 = new Duration("03:65:11");

       System.out.println("d1 is: " + d1);
       System.out.println("d2 is: " + d2);
    }
}

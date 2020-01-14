import java.util.Collections;
import java.util.Comparator;

public class Car extends Vehicle implements Comparable<Car>
{
    public static enum Model
    {
        SEDAN, SUV, SPORTS, MINIVAN, COMPACT,CONVERATBLE, HATCHBACK, VAN, OFFROAD, WAGON;
    }

    Model   model;
    int     maxRange;
    double  safetyRating;
    boolean AWD;
    int  price;

    public Car()
    {
        this.model = Model.SEDAN;
    }

    public Car(String manuf, String color, Model model, Vehicle.PowerSource power,
               double safety, int range, boolean awd, int price)
    {
        super(manuf, color, 4, power);
        this.model = model;
        this.price = price;
        AWD = awd;
        safetyRating = safety;
        maxRange = range;
    }

    public String display()
    {
        return super.display() + " " + model + " Sales Price:  " + price + "$" + " SF: " + safetyRating + " RNG: " + maxRange;
    }

    public String toString()
    {
        return "";
    }


    public boolean equals(Object other)
    {
        Car otherCar = (Car) other;
        return super.equals(other) && this.model == otherCar.model && this.AWD == otherCar.AWD;
    }

    public int getPrice(){
        return price;
    }



    public int compareTo(Car other)
    {
        if      (this.price > other.price) return  1;
        else if (this.price < other.price) return -1;
        else                               return  0;
    }
}






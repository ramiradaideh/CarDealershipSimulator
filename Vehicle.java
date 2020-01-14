import java.util.Random;

public class Vehicle
{
    //The Program hasnt changed that much from assignment 1
    // I included the VIN random nu,ber in the constructor and display method.


    public enum PowerSource
    {
        GAS_ENGINE, DIESEL_ENGINE, ELECTRIC_MOTOR;
    }

    public PowerSource power;
    String manuf;
    String color;
    int    numWheels;
    int VIN;

    public Vehicle()
    {
        this.manuf = "";
    }

    public Vehicle(String manuf, String color, int numWheels, PowerSource power)
    {
        this.manuf     = manuf;
        this.color     = color;
        this.numWheels = numWheels;
        this.power     = power;
        VIN = (int)(Math.random() * 399 + 100);
    }

    public String display()
    {
        return "VIN:  "+ VIN +" "+ manuf + " " + color;
    }

    public int getVIN(){
        return VIN ;

    }

    public boolean equals(Object other)
    {
        Vehicle otherV = (Vehicle) other;
        return power == otherV.power && manuf.equals(otherV.manuf) && numWheels == otherV.numWheels;
    }
}



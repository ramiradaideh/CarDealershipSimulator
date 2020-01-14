public class ElectricCar extends Car
{
    int    rechargeTime;//minutes
    String batteryType;

    public ElectricCar(String manuf, String color, Model model, Vehicle.PowerSource power,
                       double safety, int range, boolean awd, int price, int rch)
    {
        super(manuf, color, model, Vehicle.PowerSource.ELECTRIC_MOTOR, safety, range, awd, price);
        rechargeTime = rch;
        batteryType = "Lithium";
    }

    public void setRechargeTime(int time)
    {
        rechargeTime = time;
    }

    public void batteryType(String type)
    {
        batteryType = type;
    }

    public String display()
    {
        return super.display() + " " + "EL, BAT: " + batteryType + " RCH: " + rechargeTime;
    }
}




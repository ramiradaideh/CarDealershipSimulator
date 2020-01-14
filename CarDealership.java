import java.util.*;
import java.lang.ArrayIndexOutOfBoundsException;

public class CarDealership{
    /*fields*/
     ArrayList<Car> cars;
    private SalesTeam team;
    private AccountingSystem accounting;//added these two variables for the car dealership.
    private Set<String> salesGang;
    private ArrayList<String> sales;


    boolean electricFilter = false;
    boolean priceFilter = false;
    double  priceMin    = 0;
    double  priceMax    = 0;
    boolean AWDFilter = false;
    private Car ref;
    private int month;
    private int day;
    String receiptBUY;
    String receiptRETURN;
    double mostCarsSold = 0;
    String salesOGG;
    private Set<Transaction> WAHSH;
    private Map<String, Integer> spSales;//HashMap stores the name of salesperson as a key and the number of cars they have sold as the value of the key
    private Map<Transaction, Integer> transactions; //arraylist of Transaction objects

//    private ArrayList<Transaction> boughtCars;




    /*
     *@param none
     * default constructor to set all the fields to their default value.
     */
    public CarDealership(){
        cars = new ArrayList<Car>(); //initializes the array list
//        boughtCars = new ArrayList<Transaction>();
        team = new SalesTeam("Saleh","Sami","George","Kelly","UncleTetsu","Popeyes");
        accounting = new AccountingSystem();
        salesGang  = new HashSet<String>();
        sales = new ArrayList<String>();
        ref = null;

    }

    public void addCars(ArrayList<Car> newCars)
    {
        cars.addAll(newCars);
        newCars.clear();
    }

    //1) The BUY car method takes in a VIN which gets checked first if it's valid or not by the Try Catch method.


    public String buyCar(int VIN)throws IllegalArgumentException
    {
        try {


            if (VIN > 499 || VIN < 100) {
                throw new IllegalArgumentException("vin must be valid");
            }
        }catch (IllegalArgumentException e){
            System.out.println("vin must be valid");
        }
        try {
            if (cars.size() == 0) {
                throw new IllegalArgumentException("there are no cars in the list");
            }
        }catch (IllegalArgumentException e){
            System.out.println("there are no cars in the list");
        }
        // Looping throght the Car object and comparing there VIN numbers with the number in the parameter
        for(int i =0; i< cars.size(); i++){
            if(cars.get(i).getVIN() == VIN){
                // add it to ref which is a car reference
                ref = cars.get(i);

                break;
            }
        }//end of for loop
        //Once the Car Has been added, I am removing it from the list
        // AND then using SALES OG to get a Random Name
        //place all the variables of the accounting object and create a String receipt
        cars.remove(ref);
        String salesOG = team.getRandomElement();
        month = (int) (Math.random() * 12 +1);
        day = (int) (Math.random() * 28 +1);
        GregorianCalendar calendar = new GregorianCalendar(2019, month,day);
        receiptBUY = accounting.add(calendar, ref, salesOG, Transaction.BUY, ref.getPrice());
        salesOGG = salesOG;
        return receiptBUY;

    }



    // SImilar to the BUY method
    // It takes a TransactionID integer and checks for validity
    //Then it adds all the variables on to the new receipt
    public void returnCar(int transactionID)throws IllegalArgumentException{
        try {
            if (transactionID < 0 || transactionID > 99) {
                throw new IllegalArgumentException("Not a Valid transactionID");
            }
        }catch (IllegalArgumentException e){
            System.out.println("Not A Valid TransactionID");
        }

        Transaction transaction = accounting.getTransaction(transactionID);
        int day = transaction.getDate().get(Calendar.DAY_OF_MONTH);
        Random rand = new Random();
    /*this obviously wont work for every month but this is an easy way of making it so the day after is always after because the random
    number only generates up to 27 for creating the original date*/
        GregorianCalendar calendar = new GregorianCalendar(2019,transaction.getDate().get(Calendar.MONTH),rand.nextInt(30-day) + day+1);
        receiptRETURN =  accounting.add(calendar, ref, salesOGG, Transaction.RETURN, ref.getPrice());
        //salesPeople.add()
        cars.add(transaction.getCar());
    }


    //Loops through all the Sales peoples names in the accounting object
    public Set<String> getSalesTeam(){
        for (int i=0; i < accounting.transactions.size() ; i++ ) {
            Transaction team = accounting.transactions.get(i);
            String namee = team.getName();
            salesGang.add(namee);
        }
        return salesGang;
    }

    public AccountingSystem getAccSystem() {
        return accounting;
    }

    public void STATS(){
        //print total Sales Value for the year
        //print the average sales per month
        //the total number of cars sold
        //The highest Sales Month in terms of Cars
        //The total Car returns
        ArrayList<Transaction> trans = getAccSystem().getTransactions();
        int salesAllYear = 0;
        int carsBought = 0;
        int returnsAllYear = 0;
        double avgSalesPerMonth;
//Looping through the transactions objects and getting the sales price for every month as well as adding to each other
        for (int i = 0; i < trans.size(); i++) {
            if (trans.get(i).getTransactionType() == Transaction.BUY) {
                salesAllYear += trans.get(i).getSalesPrice();
                carsBought++;
            } else if (trans.get(i).getTransactionType() == Transaction.RETURN) {
                returnsAllYear++;
            }
        }
        //Creating a nested loop to go through the transaction size and loop the months of these transaction
        int[] months = new int[12];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < trans.size(); j++) {
                if (trans.get(j).getDate().get(Calendar.MONTH) == i && trans.get(j).getTransactionType().equals(Transaction.BUY)) {
                    months[i] = months[i] + 1;
                }
            }
        }
        int highestMonth = 0;
        int carsSoldDuringHighestMonth = 0;
        for (int i = 0; i < 12; i++) {
            if (months[i] > carsSoldDuringHighestMonth) {
                highestMonth = i;
                carsSoldDuringHighestMonth = months[i];
            }
        }
        avgSalesPerMonth = salesAllYear / 12.0;

        System.out.println("Total sales for 2019: " + salesAllYear);
        System.out.println("Average Sales per month: " + avgSalesPerMonth);
        System.out.println("Total number of cars sold: " + carsBought);
        System.out.println("The highest sales month: " + highestMonth + " Cars sold during that month: " + carsSoldDuringHighestMonth);
        System.out.println("Total number of cars returned: " + returnsAllYear);

    }


    public Car getCar(){

        return ref;
    }

    public void displayInventory()
    {
        System.out.println("");

        for (int i = 0; i < cars.size(); i++)
        {
            Car car = cars.get(i);

            if (priceFilter && (car.price < priceMin || car.price > priceMax))
                continue;

            if (electricFilter && car.power != Vehicle.PowerSource.ELECTRIC_MOTOR)
                continue;

            if (AWDFilter && !car.AWD)
                continue;

            System.out.println(""+ i + " " + car.display());
        }
        System.out.println("");
    }

    public void filtersClear()
    {
        electricFilter = false;
        priceFilter = false;
        AWDFilter = false;
    }

    public void filterByPrice(double min, double max)
    {
        priceFilter = true;
        priceMin    = min;
        priceMax    = max;
    }
    public void filterByElectric()
    {
        electricFilter = true;
    }
    public void filterByAWD()
    {
        AWDFilter = true;
    }

    public void sortByPrice()
    {
        Collections.sort(cars);
    }

    private class SafetyRatingComparator implements Comparator<Car>
    {
        public int compare(Car a, Car b)
        {
            if      (a.safetyRating < b.safetyRating) return  1;
            else if (a.safetyRating > b.safetyRating) return -1;
            else                                      return  0;
        }
    }
    public void sortBySafetyRating()
    {
        Collections.sort(cars,new SafetyRatingComparator());
    }

    private class RangeComparator implements Comparator<Car>
    {
        public int compare(Car a, Car b)
        {
            if      (a.maxRange < b.maxRange) return  1;
            else if (a.maxRange > b.maxRange) return -1;
            else                              return  0;
        }
    }
    public void sortByMaxRange()
    {
        Collections.sort(cars,new RangeComparator());
    }
    // Creating a HashMap to be able to store the name and Integer associated with the Sales person
    public void getTopSP() {
        HashMap<String, Integer> map = new HashMap<>();
        for (String name : getSalesTeam()) {
            String inn = name.toUpperCase();
            if (map.get(inn) != null) {
                Integer couunt = map.get(inn) + 1;
                map.put(inn, couunt);
            } else {
                map.put(inn, 1);
            }


        }
        //Using Collections allows me to get the values from every sales person in map set and loop through it to find the amximum
        int maxValueInMap = (Collections.max(map.values()));
        for (Map.Entry<String, Integer> ma : map.entrySet()) {
            if (ma.getValue() == maxValueInMap) {
                System.out.println(ma.getKey());
            }

        }
    }


    public void getSalesMonth(int montth)throws IllegalArgumentException
    {
        ArrayList<Transaction> tempTrans = getAccSystem().getTransactions();

        for(int i =0; i < tempTrans.size();i++) {
            try {
                if (montth > 11) {
                    throw new IllegalArgumentException("Not a Valid transactionID");
                }
            }catch (Exception e){
                System.out.println("Not a valid month please choose a month in the range 0-11");
            }

                if (tempTrans.get(i).getDate().get(Calendar.MONTH) == montth) {
                System.out.println(tempTrans.get(i).display());
            }
        }

        }


    public void getSales(){
        ArrayList<Transaction> trans = getAccSystem().getTransactions();
        for (int i = 0; i < trans.size(); i++) {
            System.out.println(trans.get(i).display() + "\n");
        }


    }


}

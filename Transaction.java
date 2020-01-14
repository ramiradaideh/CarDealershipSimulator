import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Transaction {

//creating varianles
    private int transactionID;
    private GregorianCalendar transactionDate;
    Car ref ;
    private String personName ;
    private int salesPrice;
    private String transactionType;//uses BUY and RETURN for the type
    public static final String BUY = "BUY", RETURN ="RETURN";


//assigning variables

    public Transaction(int transactionID, GregorianCalendar transactionDate, Car carRef, String personName, int salesPrice,String type) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        ref = carRef;
        this.personName = personName;
        this.salesPrice = salesPrice;
        this.transactionType = type;

    }
// crating get methods for the variables
    public int getTransactionID(){


        return transactionID;

    }

    public Car getCar(){
        return ref;
    }

    public int getSalesPrice(){
        return salesPrice;

    }
    public String getName(){
        return personName;
    }
    public GregorianCalendar getDate(){return transactionDate;}




    public String display(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");

        return
                "ID : " + transactionID + " Date : " +sdf.format(transactionDate.getTime())+ " " +   transactionType + " Sales Person: " +personName+ " CAR : "+ ref.display() ;
    }

    public String getTransactionType(){
        return transactionType;
    }


}

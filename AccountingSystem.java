import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;


public class AccountingSystem {
    // Creating Variables and Lists
    String salesPerson;
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    //Creating an add method to be add all the Variables on to the Transaction object
    public String add(GregorianCalendar date, Car car, String salesPerson, String transType, int salePrice)
    {
        Random r = new Random();
        int transactionID1 = r.nextInt(99) + 1;
        Transaction transactionObj = new Transaction (transactionID1,date,car,salesPerson,salePrice,transType);
        transactions.add(transactionObj);
        return transactionObj.display();
    }
    //Creating a getTranasction ID method to loop through all transaction objects and be able to return the Car

    public Transaction getTransaction(int id)
    {
        for (int i=0 ; i < transactions.size() ; i++)
        {
            Transaction rr = transactions.get(i);
            if (rr.getTransactionID() == id)
            {
                return  rr;
            }
        }
        return null;
    }
    public String getPersonName()
    {
        return salesPerson;
    }
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }






}

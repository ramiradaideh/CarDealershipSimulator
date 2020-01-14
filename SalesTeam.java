import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;


public class SalesTeam {

    public LinkedList<String> salesTeam ;

    // Constructor adds all the names on to the Linked list
    public SalesTeam(String person1,String person2,String person3,String person4,String person5,String person6){

        salesTeam = new LinkedList<String>();
        salesTeam.add(person1);
        salesTeam.add(person2);
        salesTeam.add(person3);
        salesTeam.add(person4);
        salesTeam.add(person5);
        salesTeam.add(person6);
    }

//Add public methods to:
// 1) return a sales person String.
// Use a random number generator to randomly choose a sales person from the linked list.
// 2) display all the sales person names in a string.
// You must use a ListIterator to iterate through the linked list and create a string.
// Add other public methods as you see fit: for example, return the number of sales persons on the team,
// get the 5ith sales person in the list etc.

    public  String returnSalesPerson(){

        StringBuilder result = new StringBuilder();
        result.append(getRandomElement());
        // random number generator (int)(Math.random() * 499 + 100)
        String kl = result.toString();
        return kl;
    }

    public  String getRandomElement() {
        Random rand = new Random();
        String nan = salesTeam.get(rand.nextInt(salesTeam.size()));
        //Person someone = array.get(nan)

        return salesTeam.get(rand.nextInt(salesTeam.size()));
    }

    public String display(){
        ListIterator<String> iter = salesTeam.listIterator();
        StringBuilder result = new StringBuilder();
        while (iter.hasNext()){
            result.append(iter.next() + " ");

        }
        String kl = result.toString();
        return kl;



    }




}

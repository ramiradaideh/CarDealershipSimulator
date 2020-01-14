import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CarDealershipSimulator
{
  public static void main(String[] args) throws FileNotFoundException, NullPointerException {
    CarDealership dealership = new CarDealership();
    ArrayList<Car> newCars = new ArrayList<Car>();

    try {
      readFromTextFile(newCars);
    } catch (FileNotFoundException e) {
      System.out.println("The file was not found");
    }
    Scanner input = new Scanner(System.in);
    System.out.print(">");
    simulator(input, newCars, dealership);

//creating a read from file text to bra able to catch any exceptions in any of the methods
    //such as entering nothing into the command line without crashing
  }
    private static void readFromTextFile(ArrayList<Car> newCars) throws FileNotFoundException
    {
      int rechargeTime;
      boolean tempAWD = false;
      // Create some new cars of different types
      File infile = null;
      infile = new File("cars.txt");
      Scanner in = new Scanner(infile);
      while (in.hasNext()) {
        String manaf = in.next();
        String color = in.next();
        String model = in.next();
        Car.Model tempModelObj = Car.Model.valueOf(model);
        String power = in.next();
        Vehicle.PowerSource tempPower = Vehicle.PowerSource.valueOf(power);
        Double safetyRating = in.nextDouble();
        int range = in.nextInt();
        String awd = in.next();
        if (awd.equals("AWD")) {
          tempAWD = true;
        } else {
          tempAWD = false;
        }
        int price = in.nextInt();
        if (in.hasNextInt()) {
          rechargeTime = in.nextInt();
          ElectricCar tempCar = new ElectricCar(manaf, color, tempModelObj, tempPower, safetyRating, range, tempAWD, price, rechargeTime);
          newCars.add(tempCar);
        } else {
          Car tempCar = new Car(manaf, color, tempModelObj, tempPower, safetyRating, range, tempAWD, price);
          newCars.add(tempCar);
        }

      }

    }


    public static void simulator(Scanner input, ArrayList<Car> newCars , CarDealership dealership)
    {
      Scanner scanner = new Scanner(System.in);
      System.out.print(">");
      try {


        while (scanner.hasNextLine()) {
          String inputLine = scanner.nextLine();
          if (inputLine == null || inputLine.equals("")) {
            System.out.print("\n>");
            continue;
          }
          String currentCar = "kk";
          int carVIN = -1;
          int transID = -1;
          String c;
          Car gcar = null;
          ArrayList<Car> boughtCars = null;
          Scanner commandLine = new Scanner(inputLine);
          String command = commandLine.next();

          if (command == null || command.equals("")) {
            System.out.print("\n>");
            continue;
          } else if (command.equals("L")) {
            dealership.displayInventory();
          } else if (command.equals("Q") || command.equals("QUIT")) {
            System.exit(0);
          } else if (command.equalsIgnoreCase("BUY")) {
            carVIN = commandLine.nextInt();
            c = dealership.buyCar(carVIN);
            gcar = dealership.getCar();
            boughtCars = new ArrayList<Car>();
            if (c != null) {
              currentCar = c;
              boughtCars.add(gcar);
              System.out.println(currentCar);
            } else {
              System.out.println("Invalid argument");
            }

          } else if (command.equalsIgnoreCase("RET")) {
            if (currentCar != null) {
              transID = commandLine.nextInt();


              dealership.returnCar(transID);
              System.out.println("returning " + dealership.receiptRETURN);


            }
          } else if (command.equalsIgnoreCase("ADD")) {
            dealership.addCars(newCars);
          } else if (command.equalsIgnoreCase("SALES")) {
            //uses a helper method in the accounting system class to easily print out the
            if (!commandLine.hasNext()) {
              dealership.getSales();
            }
            //MONTH works but from it takes january as 0

            else if (commandLine.hasNextInt()) {
              int month = commandLine.nextInt();
              dealership.getSalesMonth(month);
            }
            if (commandLine.hasNext()) {
              String nextWord = commandLine.next();

              if (nextWord.equalsIgnoreCase("team")) {
                System.out.println(dealership.getSalesTeam());//uses the method display to show all sales team members.
              }
              //
              if (nextWord.equalsIgnoreCase("topsp")) {
                dealership.getTopSP();

              }
              if (nextWord.equalsIgnoreCase("stats")) {
                dealership.STATS();
              }
            }
            
          } else if (command.equals("SPR")) {
            dealership.sortByPrice();
          } else if (command.equals("SSR")) {
            dealership.sortBySafetyRating();
          } else if (command.equals("SMR")) {
            dealership.sortByMaxRange();
          } else if (command.equals("FPR")) {
            double minPrice = 0;
            double maxPrice = 0;
            // Filter
            if (!commandLine.hasNextDouble()) {
              System.out.println("Invalid arguements");
              continue;
            }
            minPrice = commandLine.nextDouble();
            if (!commandLine.hasNextDouble()) {
              System.out.println("Invalid arguements");
              continue;
            }
            maxPrice = commandLine.nextDouble();
            if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
              System.out.println("Invalid arguements");
              continue;
            }
            dealership.filterByPrice(minPrice, maxPrice);
          } else if (command.equals("FEL")) {
            dealership.filterByElectric();
          } else if (command.equals("FAW")) {
            dealership.filterByAWD();
          } else if (command.equals("FCL")) {
            dealership.filtersClear();
          }
          System.out.print("\n>");
        }
      }
      catch (Exception e) //This is so the program does not crash if the user simply presses enter instead of inputting a String
      {
        simulator(input, newCars, dealership);
      }
    }
  }


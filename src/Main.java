import exception.DinosaurIllException;
import exception.NotAppropriateAge;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void manageDinosaurs(Park park) throws DinosaurIllException {
        System.out.println("-- DINO -- MANAGEMENT --");
        System.out.println("1 - add dino");
        System.out.println("2 - kill dino");
        System.out.println("3 - show dino");
        System.out.println("4 - feed dino");
        System.out.println("5 - take dino to an enclosure");
        System.out.println("6 - read all info from file");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                System.out.println("-------Add A Dino------");
                scan.nextLine();
                System.out.println("Name: ");
                String name = scan.nextLine();
                System.out.println("Age: ");
                int age = scan.nextInt();
                System.out.println("Specie(0 - T-REX, 1 - TRICERATOPS, 2 - PTERODACTYL)");
                int specie = scan.nextInt();
                Dinosaur d = null;
                switch (specie){
                    case 0 -> d = new Tyrannosaurus(name, age);
                    case 1 -> d = new Triceratops(name, age);
                    case 2 -> d = new Pterodactyl(name, age);
                }
                park.addDino(d);
                System.out.println("Enclosure: ");
                int enc0 = scan.nextInt();
                park.enclosureDino(name, enc0);
                System.out.println("Enclosure for " + name + " is enclosure " + enc0);
                break;
            case 2:
                System.out.println("-------Kill dino------");
                scan.nextLine();
                System.out.println("Name: ");
                String name1 = scan.nextLine();
                park.dieDino(name1);
                break;
            case 3:
                System.out.println("-------Show A Dino------");
                scan.nextLine();
                System.out.println("Name: ");
                String name2 = scan.nextLine();
                Dinosaur dino = park.findDino(name2);
                if (dino != null) {
                    dino.showDino();
                    dino.showInfo();
                } else {
                    System.out.println("Dinosaur with name " + name2 + "' not found.");
                }
                break;
            case 4:
                System.out.println("-------Feed A Dino------");
                scan.nextLine();
                System.out.println("Name: ");
                String name3 = scan.nextLine();
                System.out.println("Type of food(0 - Vodka, 1 - Human, 2 - Chocolate):");
                int foodN = scan.nextInt();
                FoodChoice fc = FoodChoice.values()[foodN];
                Food.Nutrition n = new Food.Nutrition();
                switch (fc) {
                    case VODKA -> n = new Food.Nutrition(50, 10, 20);
                    case HUMAN -> n = new Food.Nutrition(70, 5, 20);
                    case CHOCOLATE -> n = new Food.Nutrition(3, 70, 35);
                }
                Food food = new Food(fc, 50, n);
                park.feedDino(name3, food);
                if (!park.map.get(park.findDino(name3)).checkIfEnough()) {
                    throw new DinosaurIllException("Not proper amount of food!");
                }
                park.map.get(park.findDino(name3)).foodBalance += food.weight/20;
                break;
            case 5:
                System.out.println("-------Put Dino Into Enclosure------");
                scan.nextLine();
                System.out.println("Name: ");
                String name4 = scan.nextLine();
                System.out.println("Enclosure: ");
                int enc = scan.nextInt();
                park.enclosureDino(name4, enc);
                break;
            case 6:
                final String path = "data.txt";
                park.fillDinoFromFile(path);
                break;
        }
    }
    public static void manageEmployees(Park park){
        System.out.println("-- EMPLOYEES -- MANAGEMENT --");
        System.out.println("1 - hire employee");
        System.out.println("2 - fire employee");
        System.out.println("3 - make them work");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                System.out.println("-------Hire Employee------");
                scan.nextLine();
                System.out.println("Name: ");
                String name = scan.nextLine();
                System.out.println("Experience: ");
                int exp = scan.nextInt();
                Jobs job = Jobs.DIRECTOR.distributeJob(exp);
                park.hireEmployee(name, exp, job);
                break;
            case 2:
                System.out.println("-------Fire Employee------");
                scan.nextLine();
                System.out.println("Name: ");
                String name1 = scan.nextLine();
                park.fireEmployee(name1);
                break;
            case 3:
                park.doWork();
        }
    }
    public static void manageTickets(Park park){
        System.out.println("-- TICKETS --");
        System.out.println("1 - sell ticket");
        System.out.println("2 - show opinions");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                System.out.println("-------Buy Ticket------");
                scan.nextLine();
                System.out.println("Name: ");
                String name = scan.nextLine();
                try {
                    System.out.println("Age: ");
                    int age = scan.nextInt();
                    park.buyTicket(new Ticket(name, age, new Date()));
                }catch (NotAppropriateAge e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                System.out.println("-------Show Opinions------");
                park.showReviews();
                break;
        }

    }
    public static void checkParkStatus(Park park){
        System.out.println("-- PARK --");
        System.out.println("1 - show park");
        System.out.println("2 - show budget/visitors");
        System.out.println("3 - save HTML version");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                System.out.println(" _            __         \n" +
                        " | \\ o ._   _   /  _   _  \n" +
                        " |_/ | | | (_) /_ (_) (_) ");
                park.showDino();
                break;
            case 2:
                System.out.println("-------Show Budget------");
                park.showMoney();
                break;
            case 3:
                park.createHTML();
        }
    }
    public static void main(String[] args) throws DinosaurIllException {
        Park park = new Park();
        boolean exit = false;
        while (!exit) {
            System.out.println(" _            __         \n" +
                    " | \\ o ._   _   /  _   _  \n" +
                    " |_/ | | | (_) /_ (_) (_) ");
            System.out.println("0 - open the park");
            System.out.println("1 - manage dinosaurs");
            System.out.println("2 - manage employees");
            System.out.println("3 - manage tickets");
            System.out.println("4 - manage park");
            System.out.println("5 - exit");

            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            switch (choice) {
                case 0:
                    park.open();
                    break;
                case 1:
                    if(Park.state == Status.OPEN) {
                        manageDinosaurs(park);
                    }
                    else System.out.println("Park is closed");
                    break;
                case 2:
                    if(Park.state == Status.OPEN) manageEmployees(park);
                    else System.out.println("Park is closed");
                    break;
                case 3:
                    if(Park.state == Status.OPEN)manageTickets(park);
                    else System.out.println("Park is closed");
                    break;
                case 4:
                    if(Park.state == Status.OPEN) checkParkStatus(park);
                    else System.out.println("Park is closed");
                    break;
                case 5:
                    System.out.println("Exiting...Leave your opinion!");
                    scan.nextLine();
                    String review = scan.nextLine();
                    park.addReview(review);
                    park.close();
                    exit = true;
                    break;
            }
        }
        park.sortDino();
        for(Dinosaur dino : Park.dinos){
            dino.showInfo();
        }
    }
}
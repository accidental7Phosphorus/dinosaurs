import exception.DinosaurIllException;
import exception.NotAppropriateAge;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Park implements CareSystem{
    public static ArrayList<Dinosaur> dinos = new ArrayList<>();
    public Enclosure[] enclosures = new Enclosure[2];
    Map<Dinosaur, Enclosure> map = new HashMap<>();

    private static ArrayList<BasicEmployee> employees = new ArrayList<>();

    public ArrayList<String> getReviews() {
        return reviews;
    }

    private ArrayList<String> reviews;

    private BigDecimal money;

    public int getVisitors() {
        return visitors;
    }

    private int visitors;

    public static Status state;

    public void showMoney() {
        System.out.println("Budget balance: " + money + "\nVisitors: " + this.getVisitors());
    }

    public Park() {
        money = new BigDecimal(0);
        dinos.add(new Cat("Director's cat", 100));
        employees.add(new BasicEmployee(25, Jobs.DIRECTOR, "Entity"));
        state = Status.CLOSED;
        reviews = new ArrayList<>();
        reviews.add(null);
        visitors = 0;
        String[] colors = {"#ffcccc", "#ccffcc"};
        for(int k=0; k<2; k++){
            enclosures[k] = new Enclosure(colors[k]);
        }
    }

    public void enclosureDino(String name, int index) {
        Dinosaur d = findDino(name);
        if (d == null) {
            System.out.println("Dinosaur with name " + name + " not found.");
            return;
        }

        if (index < 0 || index >= enclosures.length) {
            System.out.println("Invalid enclosure index.");
            return;
        }

        Enclosure enclosure = enclosures[index];
        if (enclosure == null) {
            System.out.println("Enclosure at index " + index + " is null.");
            return;
        }
        map.put(d, enclosures[index]);
        enclosures[index].addDinoToEnc(d);
        checkKill();
    }

    public void open() {
        state = Status.OPEN;
        System.out.println(":3 PARK IS OPENED :3");
    }

    public void close() {
        state = Status.CLOSED;
        visitors = 0;
    }

    public void buyTicket(Ticket ticket) throws NotAppropriateAge {
        if (state == Status.OPEN) {
            visitors++;
            money = money.add(ticket.getCost());
        } else {
            System.out.println("PARK IS CLOSED!");
        }
        if(ticket.getAge() <= 3){
            throw new NotAppropriateAge("Not appropriate age for this park!");
        }
    }

    public Dinosaur findDino(String name){
        Dinosaur thisDino = null;
        for(Dinosaur dino : dinos){
            if(dino.name.equals(name)){
                thisDino = dino;
            }
        }
        return thisDino;
    }

    public void dieDino(String name) {
        int foundIndex = -1;
        for (int i = 0; i < dinos.size(); i++) {
            if (dinos.get(i).getName().equals(name)) {
                foundIndex = i;
                System.out.println("New " + dinos.get(i).showInfoString() + " has died. Probably some worker killed him :( !");
                break;
            }
        }
        dinos.remove(dinos.get(foundIndex));
    }



    public void showDino() {
        if (state == Status.OPEN) {
            for (Dinosaur dino : dinos) {
                dino.showDino();
                dino.showInfo();
            }
        } else {
            System.out.println("Park is closed!");
        }
    }

    public void hireEmployee(String name, int experience, Jobs job) {
        employees.add(new BasicEmployee(experience, name, job));
        if (employees.size() % 5 == 0) {
            money = money.add(BigDecimal.valueOf(1000));
        }
        System.out.println("--" + job + " " + name + " is in our team now!");
    }

    public void doWork(){
        Employee empWorking = null;
        for(BasicEmployee emp : employees){
            if(emp != null) {
                switch (emp.getJob()) {
                    case JANITOR, VETERINARIAN, CARETAKER -> empWorking = new ServiceEmpDecorator(emp);
                    case RESEARCHER, MANAGER, DIRECTOR -> empWorking = new ScienceEmpDecorator(emp);
                }
                this.money = empWorking.work(this.money);
            }
        }
    }

    public void fireEmployee(String name) {
        int foundIndex = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().equals(name)) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1) {
            System.out.println("Employee " + name + " not found.");
            return;
        }
        System.out.println("-- " + employees.get(foundIndex).getJob() + " " + name + " was fired! He probably killed a dino :(");
         employees.remove(employees.get(foundIndex));
    }

    public void addReview(String review) {
        reviews.add(review);
        System.out.println("Thanks for sharing your opinion!");
    }

    public void showReviews() {
        for (String review : reviews) {
            if (review != null) {
                System.out.println(review);
            }
        }
    }

    public void feedDino(String dinoName, Food food) {
            Dinosaur dino0 = this.findDino(dinoName);
            for(Dinosaur dino : dinos){
                if(dino.equals(dino0)){
                    dino.eat(food);
                }
                if(!dino.alive){
                    dinos.remove(dino);
                    System.out.println("Dinosaur " + dino.name + " died :(");
                }
                dino.giveBirth();
            }
            this.money.subtract(BigDecimal.valueOf(food.totalCost()));
    }

    public void addDino(Dinosaur dino) {
        dinos.add(dino);
        System.out.println("New " + dino.showInfoString() + " is here!");
    }

    public void sortDino(){
        for(int i=0; i<dinos.size(); i++){
            for(int j=i+1; j<dinos.size(); j++){
                if(dinos.get(i).compareTo(dinos.get(j)) > 0){
                    Dinosaur temp = dinos.get(j);
                    dinos.set(j, dinos.get(i));
                    dinos.set(i, temp);
                }
            }
        }
    }

    public void createHTML(){
        /*
        for(Dinosaur dino : dinos){
            text.append(dino.sceneHTML());
            text.append("<p style=\"font-size: 52px;\">This is a " + dino.showInfoString() + "</p>\"");
        }

         */
        StringBuilder text = new StringBuilder("<!DOCTYPE html>\n" + "<html>\n" + "<body>\n" + "\n");

        for(Enclosure enc : enclosures){
            // Append HTML representation of each enclosure
            text.append(enc.sceneHTML());
        }

        text.append("\n</body>\n" + "</html>");
        String pathHTML = "dino.html";
        try{
            BufferedWriter bf = new BufferedWriter(new FileWriter(pathHTML));
            bf.write(text.toString());
            bf.close();
            System.out.println("HTML file created successfully at: " + pathHTML);
        } catch (IOException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkFood() {
        return false;
    }

    @Override
    public void checkKill() {
        for (Dinosaur dino : dinos) {
            Enclosure enclosure1 = map.get(dino);
            if (enclosure1 == null) {
                System.out.println("Enclosure for " + dino.showInfoString() + " is null.");
                continue;
            }
            for (Dinosaur dino2 : dinos) {
                Enclosure enclosure2 = map.get(dino2);
                if (enclosure2 == null) {
                    System.out.println("Enclosure for " + dino2.showInfoString() + " is null.");
                    continue;
                }
                if (!dino.equals(dino2) && enclosure1.equals(enclosure2) && dino instanceof Carnivore && !(dino2 instanceof Carnivore)) {
                    System.out.println(dino2.showInfoString() + " was eaten by " + dino.showInfoString() + " because they were in the same enclosure!");
                    this.dieDino(dino2.name);
                } else if (!dino.equals(dino2) && enclosure1.equals(enclosure2) && dino2 instanceof Carnivore && !(dino instanceof Carnivore)) {
                    System.out.println(dino.showInfoString() + " was eaten by " + dino2.showInfoString() + " because they were in the same enclosure!");
                    this.dieDino(dino.name);
                }
            }
        }
    }


    public void fillDinoFromFile(String file){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                StringBuilder name = new StringBuilder();
                StringBuilder ageStr = new StringBuilder();
                char specie = '\0';
                Dinosaur d = null;
                int i=0;
                while(i < line.length() && line.charAt(i) != ','){
                        name.append(line.charAt(i));
                        i++;
                }
                i++;
                while (line.charAt(i) != ',') {
                    ageStr.append(line.charAt(i));
                    i++;
                }
                i++;
                specie = line.charAt(i);
                int specieInt = specie - '0';
                switch (specieInt){
                        case 0 -> d = new Tyrannosaurus(name.toString(), Integer.parseInt(ageStr.toString()));
                        case 1 -> d = new Triceratops(name.toString(), Integer.parseInt(ageStr.toString()));
                        case 2 -> d = new Pterodactyl(name.toString(), Integer.parseInt(ageStr.toString()));
                    default -> {
                        throw new IllegalArgumentException("Invalid species: " + specie);
                    }
                }
                dinos.add(d);
            }
            System.out.println("Data added successfully");
        } catch (IOException e) {
            System.out.println("Error adding dinosaurs :(");
            throw new RuntimeException(e);
        }

    }
    @Override
    public void checkHealth() {

    }
}

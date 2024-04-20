import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Dinosaur implements Eatable, Movable, Comparable<Dinosaur>{
    protected String name;
    protected int age;
    protected int health;
    protected String path;
    protected boolean alive;

    public Dinosaur(String name, int age, String path) {
        this.name = name;
        this.age = age;
        this.health = 5;
        this.path = path;
        this.alive = true;
    }
    public void reduceHealth() {
        --this.health;
    }

    public void maintainHealth() {
        ++this.health;
    }

    public String getName() {
        return this.name;
    }

    public void showInfo() {
        System.out.println(this.name + " " + this.age);
    }

    public String showInfoString(){
        return this.name + " ";
    }

    public void showDino() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String line;
            while((line=bf.readLine()) != null) {
                System.out.println(line);
            }
            bf.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found!");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("File is not found!");
            throw new RuntimeException(e);
        }
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public void eat(Food food) {
        if(food.nutrition.protein() < food.nutrition.fat()){
            reduceHealth();
        }
        else{
            maintainHealth();
        }
        if(this.health <= 0){
            alive = false;
        }
    }

    @Override
    public Dinosaur giveBirth() {
        if(this.health > 5){
            System.out.println("New Dino was born! Give him name: ");
            Scanner scan = new Scanner(System.in);
            String name  = scan.nextLine();
            if(this instanceof Tyrannosaurus){
                return new Tyrannosaurus(name, 0);
            }
            else if(this instanceof Triceratops){
                return new Triceratops(name, 0);
            }
            else if(this instanceof Pterodactyl){
                return new Pterodactyl(name, 0);
            }
        }
        return null;
    }


    @Override
    public int compareTo(Dinosaur o) {
        return Integer.compare(this.age, o.age);
    }
}


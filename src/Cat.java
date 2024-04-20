public class Cat extends Dinosaur implements Carnivore{
    private static final Species specie = Species.CAT;
    private int kills = 0;
    private boolean killerStatus = false;
    private String pathPNG;
    public Cat(String name, int age) {
        super(name, age, specie.name() + ".txt");
    }

    @Override
    public void reduceHealth() {
        super.reduceHealth();
    }

    @Override
    public void maintainHealth() {
        super.maintainHealth();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void showDino() {
        super.showDino();
    }

    @Override
    public void showInfo() {
        super.showInfo();
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public String sceneHTML() {
        String pathPNG = specie.name() + ".png";
        return "<img src=\"" + pathPNG + "\" alt=\"Cat\">";
    }

    @Override
    public void killDino() {
        if(kills > 3){
            pathPNG = "killer.png";
            killerStatus = true;
        }
    }
}

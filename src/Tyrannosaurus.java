public class Tyrannosaurus extends Dinosaur implements Carnivore{
    private static final Species specie = Species.TYRANNOSAURUS;
    public Tyrannosaurus(String name, int age) {
        super(name, age, specie.name() + ".txt");
    }
    private String pathPNG;

    private int kills = 0;
    private boolean killerStatus = false;
    public String sceneHTML() {
        pathPNG = specie.name() + ".png";
        return "<img src=\"" + pathPNG + "\" alt=\"Tyrannosaurus\">";
    }
    @Override
    public String showInfoString() {
        return super.showInfoString() + specie.name().toLowerCase();
    }

    @Override
    public void killDino() {
        if(kills > 3){
            pathPNG = "killer.png";
            killerStatus = true;
        }
    }
}

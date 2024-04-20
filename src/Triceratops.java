public class Triceratops extends Dinosaur{
    public static final Species specie = Species.TRICERATOPS;
    public Triceratops(String name, int age) {
        super(name, age, (specie.name() + ".txt"));
    }
    public String sceneHTML() {
        String pathPNG = specie.name() + ".png";
        return "<img src=\"" + pathPNG + "\" alt=\"Triceratops\">";
    }
    @Override
    public String showInfoString() {
        return super.showInfoString() + specie.name().toLowerCase();
    }
}

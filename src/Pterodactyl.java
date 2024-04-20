public class Pterodactyl extends Dinosaur{
    private final static Species specie = Species.PTERODACTYL;
    public Pterodactyl(String name, int age) {
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

    public String getPath() {
        return path;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void showDino() {
        super.showDino();
    }

    public void showInfo() {
        super.showInfo();
    }

    @Override
    public String showInfoString() {
        return super.showInfoString() + specie.name().toLowerCase();
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public String sceneHTML() {
        String pathPNG = specie.name() + ".png";
        return "<img src=\"" + pathPNG + "\" alt=\"Pterodactyl\">";
    }
}

import java.util.ArrayList;

public class Enclosure implements Movable {
    protected ArrayList<Dinosaur> dino = new ArrayList<>();
    private final static String path = "cage.png";
    public int foodBalance = 0;
    private final String color;

    public Enclosure(String color) {
        this.color = color;
    }

    public void addDinoToEnc(Dinosaur d) {
        dino.add(d);
    }

    public boolean checkIfEnough() {
        if (foodBalance > 5 || foodBalance < 3) {
            return false;
        }
        return true;
    }


    @Override
    public Dinosaur giveBirth() {
        return null;
    }

    @Override
    public String sceneHTML() {
        StringBuilder scene = new StringBuilder("\n<div style=\"background-color: " + color + "; padding: 100px; border: 1px solid #ccc;\">\n");
        for (Dinosaur d : dino) {
            scene.append(d.sceneHTML());
            scene.append("<p style=\"font-size: 52px;\">" + d.showInfoString() + "</p>");
        }
        scene.append("</div>");
        return scene.toString();
    }
}

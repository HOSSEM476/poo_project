package units;

public class Archer extends Unit {

    public Archer() {
        super("Archer", 70, 20, 60);
    }

    @Override
    public String getType() {
        return "Archer";
    }
}
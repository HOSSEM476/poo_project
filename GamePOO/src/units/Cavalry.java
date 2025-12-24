package units;

public class Cavalry extends Unit {

    public Cavalry() {
        super("Cavalry", 120, 18, 80);
    }

    @Override
    public String getType() {
        return "Cavalry";
    }
}

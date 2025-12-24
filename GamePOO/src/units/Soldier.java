package units;

public class Soldier extends Unit {

    public Soldier() {
        super("Soldier", 100, 15, 50);
    }

    @Override
    public String getType() {
        return "Soldier";
    }
}

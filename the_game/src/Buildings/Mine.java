package Buildings;
import java.util.HashMap;
import java.util.Map;

public class Mine extends Buildings {

    public Mine() {
        super(
            "Mine",
            4,
            createCost()
        );
    }

    private static Map<String, Integer> createCost() {
        Map<String, Integer> cost = new HashMap<>();
        cost.put("Gold", 200);
        cost.put("Wood", 150);
        return cost;
    }

    @Override
    public void use() {
        System.out.println("إنتاج الذهب");
    }
    
}

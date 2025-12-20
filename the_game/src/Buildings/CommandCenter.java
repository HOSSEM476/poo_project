package Buildings;
import java.util.HashMap;
import java.util.Map;

public class CommandCenter extends Buildings {

    public CommandCenter() {
        super(
            "Command Center",
            5,
            createCost()
        );
    }

    private static Map<String, Integer> createCost() {
        Map<String, Integer> cost = new HashMap<>();
        cost.put("Gold", 500);
        cost.put("Wood", 300);
        cost.put("Stone", 200);
        return cost;
    }

    @Override
    public void use() {
        System.out.println("إدارة المدينة");
    }
}


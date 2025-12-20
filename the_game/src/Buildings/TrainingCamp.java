package Buildings;
import java.util.HashMap;
import java.util.Map;

public class TrainingCamp extends Buildings {

    public TrainingCamp() {
        super(
            "Training Camp",
            3,
            createCost()
        );
    }

    private static Map<String, Integer> createCost() {
        Map<String, Integer> cost = new HashMap<>();
        cost.put("Gold", 300);
        cost.put("Wood", 200);
        cost.put("Stone", 100);
        return cost;
    }

    @Override
    public void use() {
        System.out.println("تدريب الوحدات");
    }
}

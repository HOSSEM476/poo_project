package Buildings;
public class Main {
    public static void main(String[] args) {

        Buildings b1 = new CommandCenter();
        Buildings b2 = new TrainingCamp();
        Buildings b3 = new Mine();

        b1.use();
        b2.use();
        b3.use();
    }
}


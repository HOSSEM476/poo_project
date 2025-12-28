package GamePanelAndFrame;
import javax.swing.JFrame;
import GamePanelAndFrame.GamePanel;
public class main {

    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");
        
        window.add(gamePanel);
        
        // Causes window to be sized to fit the preferred size of GamePanel
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
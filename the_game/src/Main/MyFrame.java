package Main;
import javax.swing.JFrame;
import java.awt.Color;

public class MyFrame extends JFrame {

    public MyFrame() {
        this.setTitle("The Game"); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setResizable(false);
        
        GamePanel gamePanel = new GamePanel();
        this.add(gamePanel);
        
        this.setSize(768, 576); 
        this.setLocationRelativeTo(null);
        this.setVisible(true);}}
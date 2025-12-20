package main;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Color;

public class MyFrame extends JFrame implements Runnable {
	
	Thread GameThread;

    MyFrame() {
        this.setTitle("The Game"); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setResizable(false); 
        this.setSize(768,576); 
        this.setLocationRelativeTo(null);
        ImageIcon image = new ImageIcon("logo.png"); 
        this.setIconImage(image.getImage()); 
        this.getContentPane().setBackground(Color.RED); 
        this.setVisible(true); 
        
        
    }

	@Override
	public void run() {
		
	}
}
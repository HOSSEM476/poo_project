package GamePanelAndFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

// Handles the user interface (commentary rectangle + text)
public class UI {
    public int CommandNum =0;
	GamePanel g; // Reference to GamePanel
    Graphics2D g2;
    // Constructor
    public UI(GamePanel g) {
        this.g = g;
    }

    // Draw all UI elements
    public void draw(Graphics2D g2) {
    	  this.g2 = g2;
    	if(g.gameState == g.titelState) {  
    		 drawTitelScreen();
    		 return;
    	 }
    	drawCommentScreen(g2); // Draw rectangle
    	drawScor();
        drawText(g2);          // Draw commentary text
    }
    
    public void drawTitelScreen() {
    	String text =" 2D adventure";
    	int x = getXforCenteredText(text);
    	int y = g.tileSize *3;
    	g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
    	g2.setColor(Color.WHITE);
    	g2.drawString(text, x, y);
    	
    	//menu
    	g2.setFont(g2.getFont().deriveFont(Font.PLAIN,48F));
        text="New Game";    	
        x = 330;
        y = g.tileSize *9; 
    	g2.drawString(text, x, y);
    	
    	if(CommandNum ==0) {
    		g2.drawString(">", x-g.tileSize, y);
    	}
    	
    	text="Quit";    	
        x = getXforCenteredText(text)*2;
        y = g.tileSize*11 ; 
    	g2.drawString(text, x, y);
        
    	if(CommandNum ==1) {
    		g2.drawString(">", x-g.tileSize, y);
    	}
        
    }

    // Draw semi-transparent rectangle for commentary
    private void drawCommentScreen(Graphics2D g2) {
        int x = g.tileSize - 48;
        int y = g.tileSize * 8;
        int width = g.screenWidth;
        int height = g.tileSize * 5;

        g2.setColor(new Color(0, 0, 0, 220)); // Semi-transparent black
        g2.fillRoundRect(x, y, width, height, 35, 35); // Rounded rectangle
    }

    // Draw commentary text inside rectangle
    private void drawText(Graphics2D g2) {
        g2.setColor(Color.WHITE); // Text color
        g2.setFont(g2.getFont().deriveFont(18f)); // Text size

        int x = g.tileSize + 330; // Start X
        int y = g.tileSize * 8 + 40; // Start Y
        int lineHeight = 24; // Space between lines

        // Split commentary into lines
        String[] lines = g.commentaryText.split("\n");

        int start = g.commentaryScroll; // Start drawing from scroll offset
        int maxLines = (g.tileSize * 5 - 40) / lineHeight; // Max lines visible

        // Draw visible lines
        for (int i = 0; i < maxLines; i++) {
            int index = start + i;
            if (index >= lines.length) break; // Stop if no more lines

            g2.drawString(lines[index], x, y);
            y += lineHeight; // Move Y for next line
        }
    }
    
    int getXforCenteredText(String text) {
    	  int length = (int) g2.getFontMetrics()
    	            .getStringBounds(text, g2)
    	            .getWidth();

    	    return g.screenWidth /4 - length/2 ;
 
    }
    
    private void drawScor() {
    	
    }
    
}


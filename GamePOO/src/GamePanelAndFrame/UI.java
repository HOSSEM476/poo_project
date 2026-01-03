package GamePanelAndFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import buildings.Building;
import player.Player;
import resources.ResourceManager;
import resources.ResourceType;
import units.Unit;

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
    	drawResources(g2);
    	drawUnitList(g2);
    	drawBuildingList(g2);

    	drawCommentScreen(g2); // Draw rectangle
    	drawScore(g2);
        drawText(g2);          // Draw commentary text
    }
    
    public void drawTitelScreen() {
    	String text =" SILENT TACTICS     ";
    	int x = getXforCenteredText(text)-40;
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
        int y = g.tileSize -48;
        int width = g.screenWidth;
        int height = g.screenHeight;

        g2.setColor(new Color(0, 0, 0, 220)); // Semi-transparent black
        g2.fillRoundRect(x, y, width, height, 35, 35); // Rounded rectangle
    }

    // Draw commentary text inside rectangle
    private void drawText(Graphics2D g2) {
    	drawResources(g2);
    	drawUnitList(g2);
    	drawBuildingList(g2);


        g2.setColor(Color.WHITE); // Text color
        g2.setFont(g2.getFont().deriveFont(18f)); // Text size

        int x = g.tileSize + 200; // Start X
        int y = g.tileSize
        		; // Start Y
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
    
    private void drawScore(Graphics2D g2) {
    	String AI ="AI's score:";
    	String P ="your score:";
    	Player p1 = g.turnManager.player1;
    	Player p2 = g.turnManager.player2;

    	String PlayerScore = String.valueOf(p1.getPoints());
    	String AIScore = String.valueOf(p2.getPoints());
    	
    	g2.setColor(Color.WHITE); // Text color
        g2.setFont(g2.getFont().deriveFont(30f)); // Text size
        int x1 = g.tileSize+50;
        int x = g.tileSize +750; // Start X
        int y = g.tileSize *3; // Start Y
        g2.drawString(AIScore, x, y);
        g2.drawString(PlayerScore, x1, y);
    	
        g2.setColor(Color.WHITE); // Text color
        g2.setFont(g2.getFont().deriveFont(20f)); // Text size
        int x2=g.tileSize +700;
        int x3=g.tileSize ;  
        int y1 = g.tileSize *2;
       
        g2.drawString(AI, x2, y1);
        g2.drawString(P, x3, y1);
        
    }
   
    
 // UI.java
    public void drawResources(Graphics2D g2) {
        ResourceManager rm = g.turnManager.getCurrentPlayer().getResourceManager();

        int x = g.tileSize;
        int y = g.tileSize * 12;

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(28f));

        for (ResourceType type : ResourceType.values()) {
            g2.drawString(type + ": " + rm.getResource(type), x, y);
            y += 30;
        }
    }
 
    public void drawUnitList(Graphics2D g2) {

        Player human = g.turnManager.player1;
        Player ai = g.turnManager.player2;

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(28f));

        int startY = g.tileSize * 5;

        // ---- PLAYER UNITS ----
        g2.drawString("Your units:", g.tileSize, startY);
        int y = startY + 30;

        for (Unit u : human.getUnits()) {
            g2.drawString("- " + u.getName(), g.tileSize, y);
            y += 25;
        }

        // ---- AI UNITS ----
        int xAI = g.tileSize+700;
        y = startY;

        g2.drawString("AI units:", xAI, y);
        y += 30;

        for (Unit u : ai.getUnits()) {
            g2.drawString("- " + u.getName(), xAI, y);
            y += 25;
        }
    }

    public void drawBuildingList(Graphics2D g2) {

        Player human = g.turnManager.player1;
        Player ai = g.turnManager.player2;

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(26f));

        int startY = g.tileSize * 8;

        // ---- PLAYER BUILDINGS ----
        g2.drawString("Your buildings:", g.tileSize, startY);
        int y = startY + 28;

        for (Building b : human.getBuildings()) {
            g2.drawString("- " + b.getClass().getSimpleName(), g.tileSize, y);
            y += 24;
        }

        // ---- AI BUILDINGS ----
        int xAI =  g.tileSize + 700;
        y = startY;

        g2.drawString("AI buildings:", xAI, y);
        y += 28;

        for (Building b : ai.getBuildings()) {
            g2.drawString("- " + b.getClass().getSimpleName(), xAI, y);
            y += 24;
        }
    }

    
}

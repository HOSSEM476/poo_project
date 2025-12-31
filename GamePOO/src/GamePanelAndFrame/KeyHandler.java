package GamePanelAndFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import player.TurnManager;
import player.Player;

public class KeyHandler implements KeyListener {

    private GamePanel g;
    private TurnManager t;

    public KeyHandler(GamePanel g) {
        this.g = g;
        this.t = g.turnManager; // link TurnManager
    }
    public void updateTurnManager(TurnManager t) {
        this.t = t;
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_PAGE_UP) {
            g.scrollUp();
        }
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_PAGE_DOWN) {
            g.scrollDown();
        }

        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_PAGE_UP) {
            g.commentaryScroll--;
            if (g.commentaryScroll < 0) g.commentaryScroll = 0;
        }
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_PAGE_DOWN) {
            g.commentaryScroll++;
            // estimate max scroll lines
            int totalLines = g.commentaryText.split("\n").length;
            if (g.commentaryScroll > totalLines - g.commentaryLinesVisible)
                g.commentaryScroll = Math.max(0, totalLines - g.commentaryLinesVisible);
        }

        // ===== TITLE STATE =====
        if (g.gameState == g.titelState) {
            if (keyCode == KeyEvent.VK_UP) {
                g.UI.CommandNum--;
                if (g.UI.CommandNum < 0) g.UI.CommandNum = 1;
            }
            if (keyCode == KeyEvent.VK_DOWN) {
                g.UI.CommandNum++;
                if (g.UI.CommandNum > 1) g.UI.CommandNum = 0;
            }
            if (keyCode == KeyEvent.VK_ENTER) {
                if (g.UI.CommandNum == 0) {
                    g.gameState = g.playState; // start game
                } else if (g.UI.CommandNum == 1) {
                    System.exit(0); // quit
                }
            }
            return; // skip further input when in menu
        }

        // ===== PLAY STATE =====
        if (g.gameState == g.playState && t != null) {
            Player current = t.getCurrentPlayer();

            // Human input only
            if (!current.isAi()) {
                switch (keyCode) {
                    case KeyEvent.VK_1: t.humanTrainUnit(1); break;
                    case KeyEvent.VK_2: t.humanTrainUnit(2); break;
                    case KeyEvent.VK_3: t.humanTrainUnit(3); break;
                    case KeyEvent.VK_4: t.humanTrainUnit(4); break;
                    case KeyEvent.VK_ENTER: t.nextTurn(); break; // end turn
                }
            }

            // Pause/back to menu
            if (keyCode == KeyEvent.VK_ESCAPE) {
                g.gameState = g.titelState;
                g.UI.CommandNum = 0; // reset menu selector
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }
}
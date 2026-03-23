package view;

import model.GameModel;
import model.Map;
import model.Ghost;
import model.PacMan;
import utils.AssetManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    private GameModel gameModel;

    public GamePanel(GameModel gameModel) {
        this.gameModel = gameModel;
        Map map = gameModel.getMap();

        if (map != null && map.getGrid() != null) {
            int width = map.getCols() * GameModel.TILE_SIZE;
            int height = map.getRows() * GameModel.TILE_SIZE;
            this.setPreferredSize(new Dimension(width, height));
        } else {
            this.setPreferredSize(new Dimension(800, 600));
        }

        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawMap(g);

        drawEntities(g);
    }

    private void drawMap(Graphics g) {
        Map map = gameModel.getMap();
        char[][] grid = map.getGrid();

        if (grid == null) return;

        int tileSize = GameModel.TILE_SIZE;

        for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getCols(); col++) {
                char tileChar = grid[row][col];

                int x = col * tileSize;
                int y = row * tileSize;

                BufferedImage imageToDraw = null;

                switch (tileChar) {
                    case 'X':
                        imageToDraw = AssetManager.getInstance().getImage("wall");
                        break;
                    case '.':
                        imageToDraw = AssetManager.getInstance().getImage("cherry");
                        break;
                    case ' ':
                        break;
                    default:
                        break;
                }

                if (imageToDraw != null) {
                    g.drawImage(imageToDraw, x, y, tileSize, tileSize, null);
                }
            }
        }
    }

    private void drawEntities(Graphics g) {
        int tileSize = GameModel.TILE_SIZE;
        PacMan pacman = gameModel.getPacman();
        if (pacman != null && pacman.getCurrentImage() != null) {
            g.drawImage(pacman.getCurrentImage(), pacman.getX(), pacman.getY(),tileSize, tileSize, null);
        }

        if (gameModel.getGhosts() != null) {
            for (Ghost ghost : gameModel.getGhosts()) {
                if (ghost.getImage() != null) {
                    g.drawImage(ghost.getImage(), ghost.getX(), ghost.getY(),tileSize, tileSize, null);
                }
            }
        }
    }
}
package model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private Map map;
    public static final int TILE_SIZE = 32;
    private List<Ghost> ghosts;
    private PacMan pacman;

    public GameModel() {
        this.ghosts = new ArrayList<>();
    }

    public void setMap(Map map) {
        this.map = map;
        spawnEntities();
    }

    private void spawnEntities() {
        char[][] grid = map.getGrid();

        for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getCols(); col++) {
                char cell = grid[row][col];

                int x = col * TILE_SIZE;
                int y = row * TILE_SIZE;

                if (cell == 'r') {
                    ghosts.add(new Ghost(x, y,"redGhost"));
                    grid[row][col] = ' ';
                }else if (cell == 'p') {
                    ghosts.add(new Ghost(x,y,"pinkGhost"));
                    grid[row][col] = ' ';
                }else if (cell == 'o') {
                    ghosts.add(new Ghost(x,y,"orangeGhost"));
                    grid[row][col] = ' ';
                }else if (cell == 'b') {
                    ghosts.add(new Ghost(x,y,"blueGhost"));
                    grid[row][col] = ' ';
                } else if (cell == 'P') {
                    pacman = new PacMan(x, y);
                    grid[row][col] = ' ';
                }
            }
        }
    }

    public Map getMap() { return map; }
    public List<Ghost> getGhosts() { return ghosts; }
    public PacMan getPacman() { return pacman; }
}
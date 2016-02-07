package com.plumbum.ghosts.levels;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jesse on 2/4/2016.
 */
public class LevelGrid {
    private final static char WALL = '0';
    private final static char STREET = '1';
    private final static int HEIGHT = 19;
    private final static int WIDTH = 20;

    private static String[] testLevel = new String[]{
            "0000000000000000000000",
            "0111111100000011111110",
            "0101000111111110001010",
            "0111000100000010001110",
            "0100000100000010000010",
            "0100000100000010000010",
            "0100000111111110000010",
            "0100000100000010000010",
            "0100000100000010000010",
            "0100000100000011111110",
            "0111111100000010000010",
            "0100000100000010000010",
            "0100000100000010000010",
            "0100000111111110000010",
            "0100000100000010000010",
            "0100000100000010000010",
            "0111000100000010001110",
            "0101000111111110001010",
            "0111111100000011111110",
            "0000000000000000000000"};

    private LevelNode[][] levelGrid;

    private Set<LevelNode> pathNodes;

    public LevelGrid() {
        parseLevel(testLevel);
    }

    private void parseLevel(String[] levelDef) {
        levelGrid = new LevelNode[levelDef.length][levelDef[0].length()];
        pathNodes = new HashSet<>();
        for (int i = 0; i < levelDef.length; i++) {
            String row = levelDef[i];
            for (int j = 0; j < row.length(); j++) {
                char block = row.charAt(j);
                levelGrid[i][j] = new LevelNode(j, i, block == WALL);
            }
        }
        for (int i = 1; i < levelGrid.length - 1; i++) {
            for (int j = 1; j < levelGrid[i].length - 1; j++) {
                LevelNode current = levelGrid[i][j];
                if (!current.isWall()) {
                    if (!levelGrid[i-1][j].isWall())
                        current.setUp(levelGrid[i-1][j]);
                    if (!levelGrid[i][j+1].isWall())
                        current.setRight(levelGrid[i][j+1]);
                    if (!levelGrid[i+1][j].isWall())
                        current.setDown(levelGrid[i+1][j]);
                    if (!levelGrid[i][j-1].isWall())
                        current.setLeft(levelGrid[i][j-1]);
                    pathNodes.add(current);
                }
            }
        }
    }

    public void printLevel() {
        System.out.println("Level: \n\n");
        StringBuilder sb = new StringBuilder(HEIGHT*WIDTH+HEIGHT);
        for(int i = 0; i < levelGrid.length; i++) {
            for(int j = 0; j < levelGrid[i].length; j++) {
                sb.append(levelGrid[i][j].getNodeType().getRender());
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public Set<LevelNode> getPathNodes() {
        return pathNodes;
    }
}

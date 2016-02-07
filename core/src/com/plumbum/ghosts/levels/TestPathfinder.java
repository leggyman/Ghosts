package com.plumbum.ghosts.levels;

import java.util.Set;

/**
 * Created by jesse on 2/7/16.
 */
public class TestPathfinder {
    public static void main(String[] args) {
        LevelGrid grid = new LevelGrid();
        Pathfinder pathfinder = new Pathfinder(grid);
        Set<LevelNode> pathNodes = grid.getPathNodes();
        LevelNode previous = null;
        for (LevelNode node : pathNodes) {
            if (previous != null) {
                int distance = pathfinder.findDistance(node, previous);
                System.out.println("(" + node.getX() + "," + node.getY() + ") to (" + previous.getX() + "," + previous.getY() + "): " + distance);
            }
            previous = node;
        }
    }
}

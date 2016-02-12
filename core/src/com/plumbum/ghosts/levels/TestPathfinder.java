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
        long start = System.currentTimeMillis();
        int totalDistance = 0;
        for (LevelNode node : pathNodes) {
            if (previous != null) {
                int distance = pathfinder.findDistance(node, previous);
                totalDistance += distance;
                System.out.println("(" + node.getX() + "," + node.getY() + ") to (" + previous.getX() + "," + previous.getY() + "): " + distance);
            }
            previous = node;
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("\ntotal: " + totalDistance + "\ntime: " + elapsed + "ms");
    }
}

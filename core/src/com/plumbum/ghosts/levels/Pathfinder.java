package com.plumbum.ghosts.levels;

import com.badlogic.gdx.ai.pfa.PathFinder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jesse on 1/29/2016.
 */
public class Pathfinder {
    private LevelGrid levelGrid;

    public Pathfinder(LevelGrid levelGrid) {
        this.levelGrid = levelGrid;
    }

    public int findDistance(LevelNode a, LevelNode b) {
        List<LevelNode> shortestPath = findShortestPath(a, b);
        if (shortestPath == null)
            return 0;
        return shortestPath.size() - 1;
    }

    public List<LevelNode> findShortestPath(LevelNode start, LevelNode finish) {
        Set<LevelNode> openSet = new HashSet<>();
        openSet.add(start);
        Set<LevelNode> closedSet = new HashSet<>();
        Map<LevelNode, LevelNode> cameFrom = new HashMap<>();
        Map<LevelNode, Integer> gScores = new HashMap<>();
        gScores.put(start, 0);

        Map<LevelNode, Integer> fScores = new HashMap<>();
        fScores.put(start, estimateDistance(start, finish));

        while (!openSet.isEmpty()) {
            LevelNode current = bestScore(openSet, fScores);
            if (current == finish)
                return reconstructPath(cameFrom, finish);
            openSet.remove(current);
            closedSet.add(current);
            current.getNeighbors().stream().filter(n -> !closedSet.contains(n))
                .forEach(neighbor -> {
                    int tentativeGScore = gScores.get(current) + 1;
                    if (!openSet.contains(neighbor))
                        openSet.add(neighbor);
                    if (!gScores.containsKey(neighbor) || tentativeGScore < gScores.get(neighbor)) {
                        cameFrom.put(neighbor, current);
                        gScores.put(neighbor, tentativeGScore);
                        fScores.put(neighbor, tentativeGScore + estimateDistance(neighbor, finish));
                    }
                });

        }
        return null;
    }

    private int estimateDistance(LevelNode a, LevelNode b) {
        int obvious = Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
        Map<LevelNode, LevelNode> warpGates = levelGrid.getWarpGates();
        if (warpGates != null && !warpGates.isEmpty()) {
            int usingWarp = warpGates.keySet().stream().map(node -> {
                LevelNode exit = warpGates.get(node);
                return Math.abs(a.getX() - node.getX()) + Math.abs(a.getY() - node.getY())
                        + Math.abs(b.getX() - exit.getX()) + Math.abs(b.getY() - exit.getY());
            }).min(Integer::compare).get();
            if (usingWarp < obvious)
                obvious = usingWarp;
        }
        return obvious;
    }

    private LevelNode bestScore(Set<LevelNode> openSet, Map<LevelNode, Integer> fScores) {
        return openSet.stream().min((a, b) -> fScores.get(a).compareTo(fScores.get(b))).get();
    }

    private List<LevelNode> reconstructPath(Map<LevelNode, LevelNode> cameFrom, LevelNode current) {
        List<LevelNode> totalPath = new LinkedList<>();
        totalPath.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.add(current);
        }
        return totalPath;
    }
}

package com.plumbum.ghosts.levels;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by jesse on 2/4/2016.
 */
public class LevelGrid {
    public final static int HEIGHT = 11;
    public final static int WIDTH = 10;

    private static Map<Character, Integer> charToIndex;
    static {
        charToIndex = new HashMap<>();
        charToIndex.put(' ', 15);
        charToIndex.put('0', 0);
        charToIndex.put('1', 1);
        charToIndex.put('2', 2);
        charToIndex.put('3', 3);
        charToIndex.put('4', 4);
        charToIndex.put('5', 5);
        charToIndex.put('6', 6);
        charToIndex.put('7', 7);
        charToIndex.put('8', 8);
        charToIndex.put('9', 9);
        charToIndex.put('A', 10);
        charToIndex.put('B', 11);
        charToIndex.put('C', 12);
        charToIndex.put('D', 13);
        charToIndex.put('E', 14);
    }
    private static String[] testLevel = new String[]{
            "8598559859",
            "7CEACCAEC6",
            " 44 44 44 ",
            "5D7C67C6B5",
            " B5E55E5D ",
            " 48D  B94 ",
            "5E67CC67E5",
            " 95C67C58 ",
            "8ACACCACA9",
            "4 486794 4",
            "75AA55AA56"
            };

    private LevelNode[][] levelGrid;

    private Set<LevelNode> pathNodes;
    private Map<LevelNode, LevelNode> warpGates;

    public LevelGrid() {
        parseLevel(testLevel);
    }

    public LevelNode getNode(int row, int column) {
        return levelGrid[row][column];
    }

    private void parseLevel(String[] levelDef) {
        levelGrid = new LevelNode[levelDef.length][levelDef[0].length()];
        pathNodes = new HashSet<>();
        warpGates = new HashMap<>();
        for (int i = 0; i < levelDef.length; i++) {
            String row = levelDef[i];
            for (int j = 0; j < row.length(); j++) {
                char block = row.charAt(j);
                levelGrid[i][j] = new LevelNode(j, i, charToIndex.get(block));
            }
        }
        int up, down, left, right;
        for (int i = 0; i < levelGrid.length; i++) {
            up = i-1 < 0 ? LevelGrid.HEIGHT-1 : i-1;
            down = i+1 >= LevelGrid.HEIGHT ? 0 : i+1;
            for (int j = 0; j < levelGrid[i].length; j++) {
                left = j-1 < 0 ? LevelGrid.WIDTH-1 : j-1;
                right = j+1 >= LevelGrid.WIDTH ? 0 : j+1;

                LevelNode current = levelGrid[i][j];
                if (current.getNodeType() != LevelNode.NodeType.WALL)
                    pathNodes.add(current);

                switch (current.getNodeType()) {
                    case DEADEND_DOWN:
                        current.setDown(levelGrid[down][j]);
                        break;
                    case DEADEND_UP:
                        current.setUp(levelGrid[up][j]);
                        break;
                    case DEADEND_LEFT:
                        current.setLeft(levelGrid[i][left]);
                        break;
                    case DEADEND_RIGHT:
                        current.setRight(levelGrid[i][right]);
                        break;
                    case STREET_HORIZONTAL:
                        current.setRight(levelGrid[i][right]).setLeft(levelGrid[i][left]);
                        break;
                    case STREET_VERTICAL:
                        current.setUp(levelGrid[up][j]).setDown(levelGrid[down][j]);
                        break;
                    case BEND_DOWN_LEFT:
                        current.setDown(levelGrid[down][j]).setLeft(levelGrid[i][left]);
                        break;
                    case BEND_DOWN_RIGHT:
                        current.setDown(levelGrid[down][j]).setRight(levelGrid[i][right]);
                        break;
                    case BEND_UP_LEFT:
                        current.setUp(levelGrid[up][j]).setLeft(levelGrid[i][left]);
                        break;
                    case BEND_UP_RIGHT:
                        current.setUp(levelGrid[up][j]).setRight(levelGrid[i][right]);
                        break;
                    case TEE_DOWN:
                        current.setDown(levelGrid[down][j]).setLeft(levelGrid[i][left]).setRight(levelGrid[i][right]);
                        break;
                    case TEE_LEFT:
                        current.setDown(levelGrid[down][j]).setLeft(levelGrid[i][left]).setUp(levelGrid[up][j]);
                        break;
                    case TEE_RIGHT:
                        current.setUp(levelGrid[up][j]).setRight(levelGrid[i][right]).setDown(levelGrid[down][j]);
                        break;
                    case TEE_UP:
                        current.setUp(levelGrid[up][j]).setRight(levelGrid[i][right]).setLeft(levelGrid[i][left]);
                        break;
                    case INTERSECTION:
                        current.setUp(levelGrid[up][j]).setRight(levelGrid[i][right]).setLeft(levelGrid[i][left]).setDown(levelGrid[down][j]);
                        break;
                    default:
                        break;
                }

                if (current.getUp() != null && up > i)
                    warpGates.put(current, current.getUp());
                else if (current.getRight() != null && right < j)
                    warpGates.put(current, current.getRight());
                else if (current.getDown() != null && down < i)
                    warpGates.put(current, current.getDown());
                else if (current.getLeft() != null && left > j)
                    warpGates.put(current, current.getLeft());
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

    public Map<LevelNode, LevelNode> getWarpGates() {
        return warpGates;
    }
}

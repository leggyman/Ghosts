package com.plumbum.ghosts.levels;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jesse on 1/29/2016.
 */
public class LevelNode {
	private LevelNode up;
	private LevelNode down;
	private LevelNode left;
	private LevelNode right;
    private Set<LevelNode> neighbors;

	private int x;
	private int y;

	private NodeType nodeType;

	public LevelNode(int x, int y, int nodeTypeIndex) {
		this.x = x;
		this.y = y;
		this.up = this.down = this.left = this.right = null;
		this.nodeType = NodeType.fromIndex(nodeTypeIndex);
        neighbors = new HashSet<>();
	}

	public enum NodeType {
		DEADEND_UP(0, new String(Character.toChars(0x2579))),
		DEADEND_RIGHT(1, new String(Character.toChars(0x257A))),
		DEADEND_DOWN(2, new String(Character.toChars(0x257B))),
		DEADEND_LEFT(3, new String(Character.toChars(0x2578))),
		STREET_VERTICAL(4, new String(Character.toChars(0x2503))),
		STREET_HORIZONTAL(5, new String(Character.toChars(0x2501))),
		BEND_UP_LEFT(6, new String(Character.toChars(0x251B))),
		BEND_UP_RIGHT(7, new String(Character.toChars(0x2517))),
		BEND_DOWN_RIGHT(8, new String(Character.toChars(0x250F))),
		BEND_DOWN_LEFT(9, new String(Character.toChars(0x2513))),
		TEE_UP(10, new String(Character.toChars(0x253B))),
		TEE_RIGHT(11, new String(Character.toChars(0x2523))),
		TEE_DOWN(12, new String(Character.toChars(0x2533))),
		TEE_LEFT(13, new String(Character.toChars(0x252B))),
		INTERSECTION(14, new String(Character.toChars(0x254B))),
		WALL(15, "X");


		private int index;
		private String render;

		NodeType(int index, String render) {
			this.index = index;
			this.render = render;
		}

		public int getIndex() {
			return index;
		}
		public String getRender() {
			return render;
		}
		public static NodeType fromIndex(int index) {
			return NodeType.values()[index];
		}
	}

	public LevelNode setUp(LevelNode up) {
        if (this.up != up) {
            this.up = up;
            up.setDown(this);
            neighbors.add(up);
        }
		return this;
	}

	public LevelNode setDown(LevelNode down) {
        if (this.down != down) {
            this.down = down;
            down.setUp(this);
            neighbors.add(down);
        }
		return this;
	}

	public LevelNode setLeft(LevelNode left) {
        if (this.left != left) {
            this.left = left;
            left.setRight(this);
            neighbors.add(left);
        }
		return this;
	}

	public LevelNode setRight(LevelNode right) {
        if (this.right != right) {
            this.right = right;
            right.setLeft(this);
            neighbors.add(right);
        }
		return this;
	}

	public LevelNode getUp() {
		return up;
	}

	public LevelNode getDown() {
		return down;
	}

	public LevelNode getLeft() {
		return left;
	}

	public LevelNode getRight() {
		return right;
	}

	public Set<LevelNode> getNeighbors() {
		return neighbors;
	}

	public NodeType getNodeType() {
		return nodeType;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}

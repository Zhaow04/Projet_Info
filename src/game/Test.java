package game;

import game.utilities.Vector2D;

public class Test {

	public static void main(String[] args) {
		
		boolean[][] grid = new boolean[3][5];
		System.out.println(grid.length);
		
		Vector2D v = new Vector2D(0,0);
		Vector2D v1 = new Vector2D(3,4);
		v.add(v1);
		System.out.println(v);
		
	}
	
}

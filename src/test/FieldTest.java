package test;

import static org.junit.Assert.*;
import minesweeper.core.Clue;
import minesweeper.core.Field;
import minesweeper.core.GameState;
import minesweeper.core.Mine;
import minesweeper.core.Tile;
import minesweeper.core.Tile.State;

import org.junit.Test;

public class FieldTest {
	private int rows = 30;
	private int columns = 30;
	private int mines = 100;

	@Test
	public void test() {
		
	}

	@Test
	public void testIsSolved() {
		Field field = new Field(rows, columns, mines);

		assertEquals(GameState.PLAYING, field.getState());

		int opened = 0;
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				if (field.getTile(row, column) instanceof Clue) {
					field.openTile(row, column);
					opened++;
				}
				if (field.getRowCount() * field.getColumnCount() - opened == field
						.getMineCount()) {
					assertEquals(GameState.SOLVED, field.getState());
				} 
			}
		}

		assertEquals(GameState.SOLVED, field.getState());
	}

	@Test
	public void testGetNumberOfCluesAndMines() {
		Field field = new Field(rows, columns, mines);

		int count = 0;
		for (int i = 0; i < field.getRowCount(); i++) {
			for (int j = 0; j < field.getColumnCount(); j++) {
				if (field.getTile(i, j) instanceof Clue) {

					field.openTile(i, j);

				}
				if (field.getTile(i, j).getState() == State.OPEN) {
					count++;
				}
			}
		}

		assertEquals(rows * columns - mines, count);

		count=0;
		for (int i = 0; i < field.getRowCount(); i++) {
			for (int j = 0; j < field.getColumnCount(); j++) {
				if (field.getTile(i, j) instanceof Mine) {
					count++;
				}
			}
		}
		assertEquals(mines, count);
	}


}

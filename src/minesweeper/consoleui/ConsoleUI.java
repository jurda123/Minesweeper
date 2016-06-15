package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import minesweeper.UserInterface;
import minesweeper.core.Clue;
import minesweeper.core.Field;
import minesweeper.core.GameState;
import minesweeper.core.Mine;
import minesweeper.core.Tile;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
	/** Playing field. */
	private Field field;

	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(
			System.in));

	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public void newGameStarted(Field field) {
		this.field = field;

		do {
			if (field.getState() == GameState.SOLVED) {
				System.out.println("Congratulations, you win!");
				System.exit(0);

			}
			/*if (field.getState() == GameState.FAILED) {
				System.out.println("Game over!");
				System.exit(0);
			}*/
			update();
			processInput();

		} while (true);
	}

	@Override
	public void update() {

		System.out.print("  ");
		for (int i = 0; i < field.getColumnCount(); i++) {
			System.out.print(i);
			System.out.print(" ");
		}
		System.out.println();
		Tile tile;

		for (int i = 0; i < field.getRowCount(); i++) {

			int znak = i + 65;
			char a = ((char) znak);
			System.out.print(a + " ");
			for (int j = 0; j < field.getColumnCount(); j++) {

				tile = field.getTile(i, j);
				if (tile.getState() == Tile.State.CLOSED) {
					System.out.print("-");
					System.out.print(" ");
				} else if (tile.getState() == Tile.State.MARKED) {
					System.out.print("M");
					System.out.print(" ");
				}

				else if (tile.getState() == Tile.State.OPEN) {

					if (tile instanceof Mine) {
						System.out.print("X");
						System.out.print(" ");
					} else if (tile instanceof Clue) {
						System.out.print(((Clue) tile).getValue());
						System.out.print(" ");
					}

				}

			}
			System.out.println();
		}

	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {
		System.out.println("X – ukonèenie hry, MA1 – oznaèenie dlaždice v riadku A "
				+ "a ståpci 1, OB4 – odkrytie dlaždice v riadku B a ståpci 4");
		String input = readLine();
		if (input.equalsIgnoreCase("x")){
			System.out.print("GAME OVER");
			System.exit(1);
		}
		// String testInput = "BA1";
		Pattern pattern = Pattern
				.compile("(O|o|m|M)([A-I]|[a-i])([0-9]|[1-9][0-9])");
		Matcher matcher = pattern.matcher(input);
		if (!matcher.matches()) {
			System.out.println("Incorrect input format");
			return;
		} else {
			int row = (int) Character.toUpperCase(matcher.group(2).charAt(0)) - 65;
			int column = Integer.parseInt(matcher.group(3));
			char action = matcher.group(1).charAt(0);

			if (row > field.getRowCount()-1 || column > field.getColumnCount()-1) {
				System.out.println("out of bounds");
			} else {
				if (action == 'O' || action == 'o') {

					field.openTile(row, column);
				} else if (action == 'M' || action == 'm') {
					field.markTile(row, column);
				} else if (action == 'X' || action == 'x') {
					System.exit(0);
				}
			}
		}

	}
}

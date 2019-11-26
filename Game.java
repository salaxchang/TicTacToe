package model;

import java.io.Serializable;
import java.util.Arrays;

public class Game implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 20191121L;

	public enum Mark {
		X, O;
	}

	private enum Row {
		HORIZONTAL(new Integer[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } }),
		VERTICAL(new Integer[][] { { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 } }),
		DIAGONAL(new Integer[][] { { 0, 4, 8 }, { 2, 4, 6 } });

		private final Integer[][] lines;

		private Row(Integer[]... line) {
			lines = line;
		}

		public Integer[][] getLines() {
			return lines;
		}
	}

	public static final int NUM_SPACES = 9;

	private Mark[] grid;
	private boolean[] winningSpaces;
	private int turn;

	public Game() {
		reset();
	}

	/**
	 * Returns a copy of the game grid
	 * 
	 * @return a copy of the game grid
	 */
	public Mark[] getGrid() {
		return grid.clone();
	}

	/**
	 * Returns true iff the game is over by draw or win
	 * 
	 * @return true iff game is over
	 */
	public boolean isOver() {
		return turn == NUM_SPACES || foundWinner();
	}

	// Returns whether a winner was found
	private boolean foundWinner() {
		return winningSpaces != null;
	}

	/**
	 * Returns the current player
	 * 
	 * @return
	 */
	public Mark getCurrentPlayer() {
		return turn % 2 == 0 ? Mark.X : Mark.O;
	}

	/**
	 * Places the mark of the current player at the specified grid number if the
	 * move is valid. If move is not valid game is unchanged.
	 * 
	 * @param gridNum
	 * @return true iff move is valid
	 */
	public boolean placeMark(int gridNum) {
		return !isOver() && placeMark(getCurrentPlayer(), gridNum);
	}

	@Override
	public String toString() {
		return String.format("Game [grid=%s, turn=%s]", Arrays.toString(grid), turn);
	}

	private boolean placeMark(Mark mark, int gridNum) {
		boolean placed = false;
		if (isOnGrid(gridNum) && grid[gridNum] == null) {
			grid[gridNum] = mark;
			placed = true;
			checkForWin();
			if (!isOver()) {
				turn++;
			}
		}
		return placed;
	}

	// Checks all Rows for win. If found, will set the winningSpaces array.
	private void checkForWin() {
		if (winningSpaces == null)
			for (var row : Row.values()) {
				for (var line : row.getLines()) {
					if (grid[line[0]] != null && grid[line[0]] == grid[line[1]] && grid[line[0]] == grid[line[2]]) {
						setWinningSpaces(line);
					}
				}
			}
	}

	// Instantiates winningSpaces array if it does not exist and sets spaces to
	// true
	private void setWinningSpaces(Integer[] line) {
		if (winningSpaces == null) {
			winningSpaces = new boolean[NUM_SPACES];
		}
		for (var space : line) {
			winningSpaces[space] = true;
		}
	}

	/**
	 * Returns the current turn count for this game
	 * 
	 * @return the current turn count
	 */
	public int getTurn() {
		return this.turn;
	}

	// returns true iff the specified number is a spaces within the grid
	private boolean isOnGrid(int gridNum) {
		return gridNum >= 0 && gridNum < NUM_SPACES;
	}

	/**
	 * Returns the mark of the winning player, or {@code null} if game is not over
	 * or is a draw
	 * 
	 * @return
	 */
	public Mark getWinner() {
		return winningSpaces == null ? null : getCurrentPlayer();
	}

	/**
	 * Returns array indicating whether a space is on a winning line for the game
	 * 
	 * @return copy of winning spaces array, or null if there is no winner
	 */
	public boolean[] getWinningSpaces() {
		return winningSpaces == null ? null : winningSpaces.clone();
	}

	/**
	 * Initializes new game
	 */
	public void reset() {
		grid = new Mark[NUM_SPACES];
		turn = 0;
		winningSpaces = null;
	}
}
package co.tide.lab.labescape.solver;

import co.tide.lab.labescape.exception.InvalidLabyrinthException;
import co.tide.lab.labescape.exception.InvalidStartingPositionException;
import co.tide.lab.labescape.exception.NoEscapeException;
import org.springframework.stereotype.Component;

/**
 * Please implement your solution here
 */
@Component
public class LabEscape {

    public static final char WALL = 'O';
    public static final char FREE = ' ';
    public static final char PATH = '•';

    private static final int LAB_MIN_SIZE = 4;

    /**
     * @param labyrinth A labyrinth drawn on a matrix of characters. It's at least 4x4, can be a rectangle or a square.
     *                  Walkable areas are represented with a space character, walls are represented with a big O character.
     *                  The escape point is always on the border (see README)
     * @param startX    Starting row number for the escape. 0 based.
     * @param startY    Starting column number for the escape. 0 based.
     * @return A char matrix with the same labyrinth and a path drawn from the starting point to the escape
     * @throws NoEscapeException when no path exists to the outside, from the selected starting point
     */
    public char[][] drawPathForEscape(char[][] labyrinth, int startX, int startY) throws NoEscapeException {
        if (labyrinth[startX][startY] == WALL) {
            throw new InvalidStartingPositionException();
        }
        if (labyrinth.length < LAB_MIN_SIZE || labyrinth[0].length < LAB_MIN_SIZE) {
            throw new InvalidLabyrinthException();
        }

        boolean[][] visited = new boolean[labyrinth.length][labyrinth[0].length];

        placeIn(startX, startY, labyrinth, visited);
        if (walkTheLabyrinth(startX, startY, labyrinth, visited)) {
            return labyrinth;
        }

        throw new NoEscapeException();
    }

    private boolean walkTheLabyrinth(int x, int y, char[][] labyrinth, boolean[][] visited) {
        if (hasEscaped(x, y, labyrinth)) return true;

        if (tryMove(x + 1, y, labyrinth, visited)) return true;
        if (tryMove(x - 1, y, labyrinth, visited)) return true;
        if (tryMove(x, y - 1, labyrinth, visited)) return true;
        if (tryMove(x, y + 1, labyrinth, visited)) return true;

        return false;
    }

    protected boolean hasEscaped(int x, int y, char[][] labyrinth) {
        if (x == 0 || y == 0 || x == labyrinth.length - 1 || y == labyrinth[0].length - 1) {
            return true;
        }
        return false;
    }

    private boolean tryMove(int x, int y, char[][] labyrinth, boolean[][] visited) {
        if (canMove(x, y, labyrinth, visited)) {
            placeIn(x, y, labyrinth, visited);
            if (walkTheLabyrinth(x, y, labyrinth, visited)) {
                return true;
            }
            erase(x, y, labyrinth);
        }
        return false;
    }

    protected boolean canMove(int x, int y, char[][] labyrinth, boolean[][] visited) {
        if (x >= labyrinth.length || y >= labyrinth[0].length) {
            return false;
        }

        if (visited[x][y] || labyrinth[x][y] == WALL) {
            return false;
        }

        return true;
    }

    protected void placeIn(int x, int y, char[][] labyrinth, boolean[][] visited) {
        visited[x][y] = true;
        labyrinth[x][y] = PATH;
    }

    protected void erase(int x, int y, char[][] labyrinth) {
        labyrinth[x][y] = FREE;
    }
}

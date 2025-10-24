package module8;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int[][] grid;
    private final int size;

    private int blankRow;
    private int blankCol;

    public Board(int[][] tiles) {
        this.grid = new int[tiles.length][];

        for (int i = 0; i < tiles.length; ++i) {
            this.grid[i] = tiles[i].clone();
        }

        this.size = tiles.length;

        findBlank();
    }

    private void findBlank() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (grid[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    return;
                }
            }
        }
    }

    public int dimension() {
        return size;
    }

    public int hamming() {
        int hamming = 0;

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (grid[i][j] != 0 && posToIndex(i, j) != grid[i][j] - 1) {
                    ++hamming;
                }
            }
        }

        return hamming;
    }

    public int manhattan() {
        int manhattan = 0;

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (grid[i][j] != 0) {
                    int correctRow = (grid[i][j] - 1) / size;
                    int correctCol = (grid[i][j] - 1) % size;
                    manhattan += calcDist(i, j, correctRow, correctCol);
                }
            }
        }

        return manhattan;
    }

    private int calcDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private int posToIndex(int x, int y) {
        return x * size + y;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public Iterable<Board> neighbors() {
        List<Board> neighborList = new ArrayList<>();
        final int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        for (int[] dir : dirs) {
            final int newBlankRow = blankRow + dir[0];
            final int newBlankCol = blankCol + dir[1];

            if (newBlankRow < 0 || newBlankRow >= size ||
                newBlankCol < 0 || newBlankCol >= size) {
                continue;
            }

            neighborList.add(new Board(grid).moveBlank(newBlankRow, newBlankCol));
        }

        return neighborList;
    }

    private Board moveBlank(int destRow, int destCol) {
        swap(blankRow, blankCol, destRow, destCol);
        blankRow = destRow;
        blankCol = destCol;

        return this;
    }

    public Board twin() {
        Board twinBoard = new Board(grid);

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size - 1; ++j) {
                if (twinBoard.grid[i][j] != 0 && twinBoard.grid[i][j + 1] != 0) {
                    twinBoard.swap(i, j, i, j + 1);
                    return twinBoard;
                }
            }
        }

        return null;
    }

    private void swap(int x1, int y1, int x2, int y2) {
        int temp = grid[x1][y1];
        grid[x1][y1] = grid[x2][y2];
        grid[x2][y2] = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("%d\n", grid.length));

        for (int[] row : grid) {
            for (int cell : row) {
                sb.append(String.format("%2d ", cell));
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object y) {
        if (y != null && getClass() == y.getClass()) {
            Board other = (Board) y;

            if (dimension() != other.dimension()) {
                return false;
            }

            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    if (grid[i][j] != other.grid[i][j]) {
                        return false;
                    }
                }
            }

            return true;
        }

        return false;
    }
}
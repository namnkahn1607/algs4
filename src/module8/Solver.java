package module8;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solver {
    private final List<Board> boardSolutionPath;
    private boolean solvable;

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("module8.Board should not be null");
        }

        this.boardSolutionPath = new ArrayList<>();

        MinPQ<SearchNode> pq = new MinPQ<>();
        SearchNode goalNode = null;
        pq.insert(new SearchNode(initial, 0, false));
        pq.insert(new SearchNode(initial.twin(), 0, true));

        while (!pq.isEmpty()) {
            SearchNode node = pq.delMin();

            if (node.board.isGoal()) {
                this.solvable = !node.isTwin;

                if (!node.isTwin) {
                    goalNode = node;
                }

                break;
            }

            slide(pq, node);
        }

        if (solvable) {
            SearchNode current = goalNode;

            while (current != null) {
                boardSolutionPath.add(current.board);
                current = current.previousNode;
            }

            Collections.reverse(boardSolutionPath);
        }
    }

    private static class SearchNode implements Comparable<SearchNode> {
        SearchNode previousNode;
        Board board;
        int atMove;
        boolean isTwin;
        int manhattan;

        SearchNode(SearchNode previousNode, Board board, int atMove, boolean isTwin) {
            this.previousNode = previousNode;
            this.board = board;
            this.manhattan = board.manhattan();
            this.atMove = atMove;
            this.isTwin = isTwin;
        }

        SearchNode(Board board, int atMove, boolean isTwin) {
            this(null, board, atMove, isTwin);
        }

        @Override
        public int compareTo(SearchNode other) {
            int thisPriority = manhattan + atMove;
            int otherPriority = other.manhattan + other.atMove;

            return Integer.compare(thisPriority, otherPriority);
        }
    }

    private void slide(MinPQ<SearchNode> pq, SearchNode node) {
        for (Board neighbor : node.board.neighbors()) {
            if (node.previousNode != null && neighbor.equals(node.previousNode.board)) {
                continue;
            }

            pq.insert(new SearchNode(node, neighbor, node.atMove + 1, node.isTwin));
        }
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        if (isSolvable()) {
            return boardSolutionPath.size() - 1;
        }

        return -1;
    }

    public Iterable<Board> solution() {
        if (isSolvable()) {
            return boardSolutionPath;
        }

        return null;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);

        int n = in.readInt();
        int[][] tiles = new int[n][n];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                tiles[i][j] = in.readInt();
            }
        }

        Board initial = new Board(tiles);

        Solver solver = new Solver(initial);

        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.printf("Minimum number of moves = %d\n", solver.moves());

            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
    }
}
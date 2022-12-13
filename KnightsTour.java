/**
 * The KnightsTour class simulates the movement of a knight on
 * a chessboard. Let's the user enter the board size between the
 * range of 1 - 8. When the enter key is pressed, the display 
 * shows the order of traversal of the knight on the board.
 * 
 * Author Emmanuel Agbakpe
 * Course COSC 2436
 * Assignment Project 2 Part II
 * Instructor: Professor Steve Johnson
 * Due Date December 10, 2022
 */

import java.util.*;

public class KnightsTour{
    private int boardSize;
    private final int startPosition;
    private boolean hasCoveredBoard = false;
    private int[] rowArray = {-1,1};
    private int[]columnArray = {-2,2};
    static ArrayList<Integer> hamiltonianPath;    

    /*
     * Constructor
     */
    public KnightsTour() {
        this.startPosition = 1; // Constructor assignment
    }

    /**
     * The setBoardSize() mutator method sets the number of rows
     * and columns.
     * @param boardSize
     */
    public void setBoardSize(int newBoardSize) {
        this.boardSize = newBoardSize;
    }

    /**
     * The getBoardSize() accessor method returns the number
     * of rows and columns.
     * @return The value in the boardSize field.
     */
    public int getBoardSize() {
        return this.boardSize;
    }

    /**
     * The getStarPosition() accessor method returns the starting position
     * of the knight.
     * @return The value in the startPosition filed.
     */    
    public int getStartPosition() {
        return startPosition;
    }

    /**
     * The getHasCoveredBoard accessor method returns true if the game is complete.
     * @return The value in the hasCoveredBoard field.
     */
    public boolean getHasCoveredBoard() {
        return this.hasCoveredBoard;
    }

    /**
     * The getRowArray() accessor method returns the ...
     * @return The value in the rowArray field.
     */
    public int[] getRowArray() {
        return rowArray;
    }

    /**
     * The getRowArray() accessor method returns the ...
     * @return The value in the columnArray field.
     */
    public int[] getColumnArray() {
        return columnArray;
    }

    /**
     * Chess board is of size boardSize * boardSize
     */
    public static boolean isRowColValid(int r, int c, int boardSize) {
        if (r < 0 || r >= boardSize) {
            return false;
        }

        if (c < 0 || c >= boardSize) {
            return false;
        }

        return true;
    }

    public static boolean inStack(ArrayList<Integer> stack, int curPosition) {
        for (int i : stack) {
            if (curPosition == i) {
                return true;
            }
        }

        return false;
    }

    public boolean dfs(ArrayList<Integer> stack, int curPosition, int boardSize) {
        // We have already traversed the chessboard in the desired way, no need
        // to find other possible routes
        if (hasCoveredBoard) {
            return hasCoveredBoard;
        }

        // If number of elements in the stack is equal to the boardSize then we
        // have found the desired path and thus we can return
        int n = stack.size();
        if (n == boardSize * boardSize) {
            hasCoveredBoard = true;
            for (int i : stack) {
                hamiltonianPath.add(i);
            }

            return hasCoveredBoard;
        }

        // Get row and column
        int row = (curPosition - 1) / boardSize;
        int Column = (curPosition - 1) % boardSize;

        // Valid row and Column must be between 0 and boardSize-1
        for (int i : rowArray) {
            for (int j : columnArray) {
                int newRow = row + i;
                int newColumn = Column + j;
                if (isRowColValid(newRow, newColumn, boardSize) && !inStack(stack, newRow * boardSize + newColumn + 1)) {
                    // Add newPosition to the stack
                    stack.add(newRow * boardSize + newColumn + 1);
                    dfs(stack, newRow * boardSize + newColumn + 1, boardSize);

                    // Remove newPosition from the stack
                    stack.remove(stack.size() - 1);
                }
            }
        }

        for (int i : columnArray) {
            for (int j : rowArray) {
                int newRow = row + i;
                int newColumn = Column + j;
                if (isRowColValid(newRow, newColumn, boardSize) && !inStack(stack, newRow * boardSize + newColumn + 1)) {
                    // add newPosition to the stack
                    stack.add(newRow * boardSize + newColumn + 1);
                    dfs(stack, newRow * boardSize + newColumn + 1, boardSize);

                    // Remove newPosition from the stack
                    stack.remove(stack.size() - 1);
                }
            }
        }
        return hasCoveredBoard;
    }
}

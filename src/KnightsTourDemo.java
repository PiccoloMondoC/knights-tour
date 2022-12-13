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
import java.util.Scanner;

public class KnightsTourDemo{
    // Main function
    public static void main(String[] args) throws InterruptedException {
        int input;

        KnightsTour tour = new KnightsTour();
            try (Scanner keyboard = new Scanner(System.in)) {
                // Prompt user for number of chessboard rows and columns                        
                System.out.print(
                    "\nFor the number of rows and columns on the chessboard\n"
                    + "Enter a whole number in the range 1 to 8: "
                );

                // validate the user input
                do {
                    while (!keyboard.hasNextInt()) {
                        System.out.println("Wrong input! Please enter a number.");
                        keyboard.next();
                    }

                    // Receive the input
                    input = keyboard.nextInt();

                    if (input < 1 || input > 8) {
                        // Error
                        System.out.print(
                            "\nError! Row and column size cannot be less than 1\n"
                            + "or greater than 8. Please try again: ");

                        input = keyboard.nextInt();
                    }

                } while ( input < 1 || input > 8);
            }

                        // Set the board size with the user input
            tour.setBoardSize(input);
          //  }
            
            // Get the board size
            int boardSize = tour.getBoardSize();

            // Get the start position.
            int startPosition = tour.getStartPosition();

            // Setup an arraylist of boardSize * boardSize
            // to collect the moves
            ArrayList<Integer> stack = new ArrayList<Integer>();

            // Chess board is of the size boardSize * boardSize
            KnightsTour.hamiltonianPath = new ArrayList<Integer>(boardSize * boardSize);        

            // Push top element in stack
            stack.add(startPosition);
            tour.dfs(stack, startPosition, boardSize);
            stack.remove(stack.size() - 1);

            /*
             * Printing the order of traversal of the knight on the chessboard.
             * The square at which the knight starts is labeled 1, the next 
             * visted square is labeled 2... while the last visited square on
             * the tour is labeled boardSize * boardSize
             */
            if (! tour.getHasCoveredBoard()) {
                System.out.println("\nKnight's Tour is not possible for a chessboard size of "
                 + boardSize + " * " + boardSize + "\n");             
            } else {
                System.out.println("\nKnight's Tour on a "
                 + boardSize + " * " + boardSize + " chessboard\n");

                int[][] board = new int[boardSize][boardSize];
                int count = 1;
                for (int i :  KnightsTour.hamiltonianPath) {
                    System.out.println("Move # " + count + " at square number " + i);
                    board[(i-1)/boardSize][(i-1)%boardSize] = count;
                    count += 1;
                }

                System.out.println("\n -- Order of traversal on Chess Board below -- \n");


                // Print the board
                for(int i=0;i<boardSize;i++) {                
                    for(int j=0;j<boardSize;j++) {                    
                        System.out.format("%4d",board[i][j]);
                        System.out.print(" ");
                        Thread.sleep(50);
                    }

                    System.out.println("\n");
                    
                }
            }
    }
}

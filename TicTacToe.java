import java.util.Random;
import java.util.Scanner;

/**
 * TicTacToe
 * A console-based Tic-Tac-Toe game where a human plays against the computer.
 * UC1 initializes and displays an empty Tic-Tac-Toe board in a proper grid format.
 * UC2 performs a random toss to decide who plays first and assigns symbols (X or O).
 * UC3 reads a slot number (1-9) entered by the user.
 * UC4 converts a user-entered slot number (1-9) into corresponding row and column indices of a 2D array.
 * UC5 validates whether a move is within bounds and the cell is empty.
 * UC6 updates the board with the given symbol at the specified position.
 * UC7 allows the computer to make a random valid move (Easy Level).
 * UC8 implements a continuous turn-based game loop that runs until win or draw.
 */
public class TicTacToe {

    // ============ Game State Variables ============
    static char[][] board = new char[3][3];
    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;
    static boolean gameOver = false;

    /**
     * UC1: Display Empty Tic-Tac-Toe Board
     * Entry point of the program. It initializes the board and prints an empty grid on the console.
     */
    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        
        // UC2: Toss to decide who plays first
        tossAndAssignSymbols();
        displayTossResult();
        
        // UC8: Start the continuous game loop
        playGame();
        
        System.out.println("\n--- Game Over ---");
        printBoard();
    }

    /**
     * UC1: Initializes the 3x3 board by filling each cell with '-' to indicate an empty position.
     * Students should focus on correct nested loop usage here.
     */
    static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    /**
     * UC1: Prints the Tic-Tac-Toe board using horizontal and vertical separators
     * so that the grid structure is clearly visible to the user.
     */
    static void printBoard() {
        System.out.println("\nTic-Tac-Toe Board:\n");
        for (int row = 0; row < 3; row++) {
            System.out.print("  ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col]);
                if (col < 2) System.out.print(" | ");
            }
            System.out.println();
            if (row < 2) System.out.println("  -----------");
        }
        System.out.println();
    }

    /**
     * UC2: Uses random logic to decide the first player and assigns symbols
     * based on the toss outcome. This method initializes the game state.
     */
    static void tossAndAssignSymbols() {
        Random random = new Random();
        int tossResult = random.nextInt(2); // 0 or 1
        
        if (tossResult == 0) {
            // Human starts first
            isHumanTurn = true;
            humanSymbol = 'X';
            computerSymbol = 'O';
        } else {
            // Computer starts first
            isHumanTurn = false;
            humanSymbol = 'O';
            computerSymbol = 'X';
        }
    }

    /**
     * UC2: Displays the toss result, indicating who plays first and which
     * symbol is assigned to each player.
     */
    static void displayTossResult() {
        System.out.println("\n--- Toss Result ---");
        if (isHumanTurn) {
            System.out.println("You go first!");
        } else {
            System.out.println("Computer goes first!");
        }
        System.out.println("Your symbol: " + humanSymbol);
        System.out.println("Computer's symbol: " + computerSymbol);
        System.out.println("-------------------\n");
    }

    /**
     * UC3: Reads a slot number (1-9) entered by the user.
     * Input: Scanner object
     * Output: Slot number (1-9)
     * Hint: Validation will be added in later use cases.
     */
    static int getUserSlot() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("/**");
        System.out.println(" * Reads an integer slot value from the user.");
        System.out.println(" * Input: Scanner object");
        System.out.println(" * Output: Slot number (1-9)");
        System.out.println(" * Hint: Validation will be added in later use cases.");
        System.out.println(" */");
        
        System.out.print("Enter a slot number (1-9): ");
        int slot = scanner.nextInt();
        
        return slot;
    }

    /**
     * UC4: Converts slot number into row index using zero-based indexing.
     * Input: Slot number (1-9)
     * Output: Row index (0-2)
     */
    static int getRowFromSlot(int slot) {
        return (slot - 1) / 3;
    }

    /**
     * UC4: Converts slot number into column index using modulo operation.
     * Input: Slot number (1-9)
     * Output: Column index (0-2)
     */
    static int getColFromSlot(int slot) {
        return (slot - 1) % 3;
    }

    /**
     * UC5: Validate User Move
     * Goal: Ensure the move is within bounds and the cell is empty.
     * Actor: Game System
     * Flow: Row and column received → validation performed → move accepted or rejected.
     * 
     * Key Concepts:
     * - Conditional Logic
     * - Boundary Checking
     * - Defensive Programming
     * 
     * Key Requirements:
     * - Row and column must be 0–2
     * - Cell must be empty
     * 
     * Input: Row, Column
     * Output: true if valid, false otherwise.
     */
    static boolean isValidMove(int row, int col) {
        // Boundary Checking: Ensure row and column are within 0-2 range
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            System.out.println("Invalid move! Row and column must be between 0 and 2.");
            return false;
        }
        
        // Cell Availability Check: Ensure the cell is empty
        if (board[row][col] != '-') {
            System.out.println("Invalid move! Cell is already occupied.");
            return false;
        }
        
        // Move is valid
        return true;
    }

    /**
     * UC6: Place Move on Board
     * Goal: Update the board with the given symbol at the specified position.
     * Actor: Human Player / Computer Player
     * Flow: Valid move → symbol placed → board updated.
     * 
     * Key Concepts:
     * - State Update
     * - Array Indexing
     * - Reusable Methods
     * 
     * Key Requirements:
     * - Update board correctly
     * - Assumes the move has already been validated (defensive assumption)
     * 
     * Input: Row, Column, Symbol
     * Output: Void (modifies the board array)
     */
    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
        System.out.println("Symbol '" + symbol + "' placed at Row: " + row + ", Column: " + col);
    }

    /**
     * UC7: Computer Makes a Random Move (Easy Level)
     * Goal: Allow the computer to make a random valid move.
     * Actor: Computer Player
     * Flow: Computer turn → random slot generated → converted → validated → placed.
     * 
     * Key Concepts:
     * - Random Generation
     * - Loop Until Valid
     * - Logic Reuse
     * 
     * Key Requirements:
     * - Generate slot 1–9
     * - Ensure move validity
     * - Uses previously defined methods (isValidMove, getRowFromSlot, getColFromSlot, placeMove)
     * 
     * Input: None (uses computerSymbol from game state)
     * Output: Void (modifies the board array)
     */
    static void computerMove() {
        Random random = new Random();
        int slot;
        int row, col;
        boolean validMove = false;
        
        // Keep generating random slots until a valid move is found
        do {
            slot = random.nextInt(9) + 1; // Generate slot 1-9
            row = getRowFromSlot(slot);
            col = getColFromSlot(slot);
            validMove = isValidMove(row, col);
        } while (!validMove);
        
        // Place the computer's move on the board
        System.out.println("\nComputer's turn:");
        System.out.println("Computer selected slot: " + slot);
        placeMove(row, col, computerSymbol);
    }

    /**
     * UC8: Continuous Turn-Based Game Loop
     * Goal: Continue gameplay until win or draw is detected.
     * Actor: Game System
     * Flow: Turn starts → player move → check win/draw → switch turn → repeat.
     * 
     * Key Concepts:
     * - While Loop
     * - Game State Flags
     * - Turn Switching
     * 
     * Key Requirements:
     * - Alternate turns
     * - Stop loop on win or draw
     * 
     * Input: None (uses game state variables)
     * Output: Void (modifies board and game state)
     */
    static void playGame() {
        Scanner scanner = new Scanner(System.in);
        
        // Game loop continues until game is over
        while (!gameOver) {
            if (isHumanTurn) {
                // Human's turn
                System.out.println("\nYour turn (Human - Symbol: " + humanSymbol + ")");
                int slot = getUserSlot();
                int row = getRowFromSlot(slot);
                int col = getColFromSlot(slot);
                
                // Validate and place move
                if (isValidMove(row, col)) {
                    placeMove(row, col, humanSymbol);
                    printBoard();
                    
                    // Check if human won
                    if (hasWon(humanSymbol)) {
                        System.out.println("\n🎉 Congratulations! You won the game!");
                        gameOver = true;
                        break;
                    }
                    
                    // Check for draw
                    if (isBoardFull()) {
                        System.out.println("\n🤝 It's a draw!");
                        gameOver = true;
                        break;
                    }
                    
                    // Switch turn
                    isHumanTurn = false;
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            } else {
                // Computer's turn
                System.out.println("\nComputer's turn (Symbol: " + computerSymbol + ")");
                computerMove();
                printBoard();
                
                // Check if computer won
                if (hasWon(computerSymbol)) {
                    System.out.println("\n💻 Computer won! Better luck next time.");
                    gameOver = true;
                    break;
                }
                
                // Check for draw
                if (isBoardFull()) {
                    System.out.println("\n🤝 It's a draw!");
                    gameOver = true;
                    break;
                }
                
                // Switch turn
                isHumanTurn = true;
            }
        }
    }

    /**
     * Helper method: Check if the board is full (all cells occupied)
     * Used to detect draw condition
     */
    static boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Placeholder for UC9: Check Winning Condition
     * This will be implemented in UC9
     */
    static boolean hasWon(char symbol) {
        // Placeholder: Always returns false for now
        // Will be fully implemented in UC9
        return false;
    }
}

/**
 * @author Alexander Owen-Meehan 
 */

import java.util.Random;

public class Board{

    // Set max size for grid simulation 
    private int maxGridRows;
    private int maxGridCols;

    // Boolean array used to represent grid, true for present alive cell, false for empty
    private boolean[][] cellGrid;

    /** Board
     * Constructs board and draws initial state of grid
     */

    public Board(){

        // Default grid size of 10
        maxGridRows = 10;
        maxGridCols = 10;

        cellGrid = new boolean[maxGridRows][maxGridCols];
    	initialiseCellGrid();
        drawBoard();

    }

    public Board(int rows, int cols){

        maxGridRows = rows;
        maxGridCols = cols;

        cellGrid = new boolean[rows][cols];
        initialiseCellGrid();
        drawBoard();

    }

    /** initialiseCellGrid
     * randomly fills the grid with cells based on an RNG. 
     * can be configured to be less and more likely to generate a cell, currently set at 50% chance
     */

    public void initialiseCellGrid(){

        Random rand = new Random();

        for (int i = 0; i < maxGridRows; i++) {
            for(int j = 0; j < maxGridCols; j++){
                if(rand.nextDouble() >= 0.5){
                    cellGrid[i][j] = false;
                }
                else {
                    cellGrid[i][j] = true;
                }
            }
        }
    }

    /** addCell
     * Creates a cell in the grid (sets a specified part of the grid to true)
     */

    public void addCell(int col, int row){

    	cellGrid[col][row] = true;

    }

    /** killCell
     * Kills a cell in the grid (sets a specified part of the grid to false)
     */

    public void killCell(int col, int row){

    	cellGrid[col][row] = false;

    }

    /** getNeighbouringCells
     * Finds the number of alive cells surrounding a given cell
     */

    public int getNeighbouringCells(int col, int row) {
        
    	int neighbours = 0;

        // Large number of ifs used to prevent out of bound errors

    	if(col > 0){

    		neighbours += cellGrid[col-1][row] ? 1 : 0;

    	}

    	if(row > 0){

    		neighbours += cellGrid[col][row-1] ? 1 : 0;

    	}

    	if(col < (maxGridCols - 1)){

    		neighbours += cellGrid[col+1][row] ? 1 : 0;

    	}

    	if(row < (maxGridRows - 1)){

    		neighbours += cellGrid[col][row+1] ? 1 : 0;

    	}

    	//Top Left Check
    	if (col > 0 && row > 0){

    		neighbours += cellGrid[col-1][row-1] ? 1 : 0;

    	}

    	//Bottom Left Check
    	if (col > 0 && row < (maxGridRows - 1)){

    		neighbours += cellGrid[col-1][row+1] ? 1 : 0;

    	}

    	//Top Right Check
    	if (col < (maxGridCols - 1) && row > 0){

    		neighbours += cellGrid[col+1][row-1] ? 1 : 0;

    	}

    	//Bottom Right Check
    	if (col < (maxGridCols - 1) && row < (maxGridRows - 1)){

    		neighbours += cellGrid[col+1][row+1] ? 1 : 0;

    	}

    	return neighbours;

    }
    
    /** getGridValue
     * Getter for a specific cell in the grid
     */

    public boolean getGridValue(int col, int row){
        
        return cellGrid[col][row];

    }

    public int getMaxGridCols(){

        return maxGridCols;

    }

    public int getMaxGridRows(){

        return maxGridRows;

    }

    /** drawBoard
     * Draws the board using ASCII onto command line
     */

    public void drawBoard(){

        System.out.println("Printing Board");

        for (int i = 0; i < maxGridRows; i++) {
            for(int j = 0; j < maxGridCols; j++){
                if(cellGrid[i][j]){
                    //prints a filled box
                    System.out.print((char)0x25A3);
                }
                else {
                    //prints an empty box
                    System.out.print((char)0x25A2);
                }
            }
            System.out.println("");
        }

    }

}
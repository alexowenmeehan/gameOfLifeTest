/**
 * @author Alexander Owen-Meehan 
 */

import java.lang.Thread;

public class GameOfLifeRun{

    private Board lifeGrid;
    private int[][] cellFate;
    private boolean debugMode = false;
    private int maxGridRows = 0;
    private int maxGridCols = 0;

    /** GameOfLifeRun
     * Creates a Game of Life Simulation and starts it
     * also reads max rows and columns for simulation, makes the array 
     * for tracking values equal to that size
     */

    public GameOfLifeRun(boolean debug){

        debugMode = debug;
    	initialiseNeighbourGrid();
        lifeGrid = new Board();
        maxGridRows = lifeGrid.getMaxGridRows();
        maxGridCols = lifeGrid.getMaxGridCols();
        cellFate = new int[maxGridRows][maxGridCols]; 
        gameRun();

    }

    public GameOfLifeRun(boolean debug, int rows, int cols){

        debugMode = debug;
        initialiseNeighbourGrid();
        lifeGrid = new Board(rows, cols);
        cellFate = new int[rows][cols]; 
        gameRun();

    }

    /** initialiseNeighbourGrid
     * Makes all entries in cellFate array 0
     */

    public void initialiseNeighbourGrid(){

        for (int i = 0; i < maxGridRows; i++) {
            for(int j = 0; j < maxGridCols; j++){

            	cellFate[i][j] = 0;

            }
        }
    }

    /** gameRun
     * Main loop for the Game of Life, inifinitely repeats
     * Determines fate of cells, then redraws grid accordingly
     */

    public void gameRun() {
        
        while (true) {

            determineScenarios();
            nextGridState();
            pause();
            lifeGrid.drawBoard();
            
        }
    } 

    /** determineScenarios
     * Figures out what will happen to every cell in the next iteration
     */
   
    public void determineScenarios() {
        
        int emptyBoardTrack = 0;

        /**
        * 0 - No Life in Cell, no action needed 
        * 1 - Underpopulation, cell dies
        * 2 - Survival, cell stays alive
        * 3 - Creation of Life, new cell made
        * 4 - Overcrowding, cell dies
        * 5 - Error Handler State, should not occur
        */

        for (int i = 0; i < maxGridRows; i++) {
            for(int j = 0; j < maxGridCols; j++){

                switch(lifeGrid.getNeighbouringCells(i,j)) {
                    // Cell has less than 2 neighbours, dies
                    case 0: case 1: 
                        cellFate[i][j] = 1;
                        break;
                    // Cell has 2 or 3 neighbours
                    case 2: case 3:
                        // If no active cell and space has 3 neighbours, new live cell made
                        if (lifeGrid.getNeighbouringCells(i,j) == 3){
                            if(!lifeGrid.getGridValue(i,j)){
                                cellFate[i][j] = 3;
                            }
                        }
                        // If live cell has 2 or 3 neighbours, cell stays alive
                        if(lifeGrid.getGridValue(i,j)){
                            cellFate[i][j] = 2;
                        }
                        break;
                    // if cell has more than 3 neighbours, overcrowding, cell dies
                    case 4: case 5: case 6: case 7: case 8:
                        cellFate[i][j] = 4;
                        break;
                    // Error handler state, in case something weird happens
                    default:
                        cellFate[i][j] = 5;
                        break;
                }
            }
        }
    }

    /** nextGridState
     * Changes cells in lifeGrid depending on fate determined
     */

    public void nextGridState(){

        for (int i = 0; i < maxGridRows; i++) {
            for(int j = 0; j < maxGridCols; j++){

                switch(cellFate[i][j]){
                    case 0: 
                        // More verbose messages printed out of debug flag is set beforehand
                        if (debugMode){
                            System.out.println("No Life in Cell " + i + " , " + j + " , will remain empty");
                        }
                        break;
                    case 1:
                        if (debugMode){
                            System.out.println("Underpopulated space, cell at " + i + " , " + j + " has died");
                        }
                        lifeGrid.killCell(i,j);
                        break;
                    case 2:
                        if (debugMode){
                            System.out.println("Survival, cell at " + i  + " , " + j + " has stayed alive");
                        }
                        break;
                    case 3:
                        if (debugMode){
                            System.out.println("Creation of Life, new cell at " + i + " , " + j + " has been made");
                        }
                        lifeGrid.addCell(i,j);
                        break;
                    case 4:
                        if (debugMode){
                            System.out.println("Overcrowding, cell at " + i + " , " + j + " has died");
                        }
                        lifeGrid.killCell(i,j);
                        break;
                    case 5:
                        if (debugMode){
                            System.out.println("Error State, printing out debug info");
                            System.out.println("*********************************************************");
                            System.out.println("*********************************************************");
                            System.out.println("*********************************************************");
                            System.out.println("*********************************************************");
                            System.out.println("Board Position" + i + j + " Cell State " + lifeGrid.getGridValue(i,j));
                            System.out.println("Neigbours " + lifeGrid.getNeighbouringCells(i,j));
                        }
                        break;
                    default:
                        break;
                }   
            }
        }
    }

    /** pause
     * Simple method that waits, user friendly feature to make cells readable
     * Waits for 10 seconds before moving to next iteration of Game of Life
     */

    public void pause(){

        System.out.println("Simulating interactions.....................");

        try {
                Thread.sleep(10000);
            }
        catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

    }

}
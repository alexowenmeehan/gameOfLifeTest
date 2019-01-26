/**
 * @author Alexander Owen-Meehan 
 */

import java.util.*;

public class GameOfLifeRun{

    private Board lifeGrid = new Board();
    private int[][] cellFate = new int[10][10];
    private boolean emptyGridFlag = false;

    public GameOfLifeRun(){

    	initialiseNeighbourGrid();
        gameRun();

    }

    public void initialiseNeighbourGrid(){

        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++){
            	cellFate[i][j] = 0;
            }
        }
    }

    public void gameRun() {
        
        while (true) {

            determineScenarios();
            nextGridState();
            lifeGrid.drawBoard();
            pause();
        }
    }
   
    public void determineScenarios() {
        
        int emptyBoardTrack = 0;

        /**
        * 0 - No Life in Cell, no action needed 
        * 1 - Underpopulation, cell dies
        * 2 - Survival, cell stays alive
        * 3 - Creation of Life, new cell made
        * 4 - Overcrowding, cell dies
        * 5 - Error Handler State, should not occur
        * 6 - Cell was empty, stays empty
        */

        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++){

                switch(lifeGrid.getNeighbouringCells(i,j)) {
                    case 0:
                        emptyBoardTrack++;
                        break;
                    case 1: 
                        cellFate[i][j] = 1;
                        break;
                    case 2: case 3:
                        if (lifeGrid.getNeighbouringCells(i,j) == 3){
                            if(!lifeGrid.getGridValue(i,j)){
                                cellFate[i][j] = 3;
                            }
                        }
                        if(lifeGrid.getGridValue(i,j)){
                            cellFate[i][j] = 2;
                        }
                        break;
                    case 4: case 5: case 6: case 7: case 8:
                        cellFate[i][j] = 4;
                        break;
                    default:
                        cellFate[i][j] = 5;
                        break;
                }
            }
        }

        if(emptyBoardTrack == 100){
            emptyGridFlag = true;
        }
        else {
            emptyGridFlag = false;
        }
    }

    public void nextGridState(){

        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++){

                switch(cellFate[i][j]){
                    case 0: 
                        System.out.println("No Life in Cell " + i + " , " + j + " , will remain empty");
                        break;
                    case 1:
                        System.out.println("Underpopulated space, cell at " + i + " , " + j + " has died");
                        lifeGrid.killCell(i,j);
                        break;
                    case 2:
                        System.out.println("Survival, cell at " + i  + " , " + j + " has stayed alive");
                        break;
                    case 3:
                        System.out.println("Creation of Life, new cell at " + i + " , " + j + " has been made");
                        lifeGrid.addCell(i,j);
                        break;
                    case 4:
                        System.out.println("Overcrowding, cell at " + i + " , " + j + " has died");
                        lifeGrid.killCell(i,j);
                        break;
                    case 5:
                        System.out.println("Error State, printing out debug info");
                        System.out.println("*********************************************************");
                        System.out.println("*********************************************************");
                        System.out.println("*********************************************************");
                        System.out.println("*********************************************************");
                        System.out.println("Board Position" + i + j + " Cell State " + lifeGrid.getGridValue(i,j));
                        System.out.println("Neigbours " + lifeGrid.getNeighbouringCells(i,j));
                        break;
                    default:
                        break;
                }   
            }
        }
    }

    public void pause(){

        System.out.println("Simulating interactions.....................");

        try {
                Thread.sleep(60000);
            }
        catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

    }

}
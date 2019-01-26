/**
 * @author Alexander Owen-Meehan 
 */

import java.util.*;

public class GameOfLifeRun{

    private Board lifeGrid = new Board();
    private int[][] numOfNeighbours = new int[10][10];
    private boolean emptyGridFlag = false;

    public void GameOfLifeRun(){

    	initialiseNeighbourGrid(cellGrid);
        gameRun();

    }

    public void initialiseNeighbourGrid(int[][] numOfNeighbours){

        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++){
            	numOfNeighbours[i][j] = 0;
            }
        }
    }

    public void gameRun() {
        
        while (true) {

            determineScenario();
            updateLifeGrid();
            lifeGrid.redrawGrid();
            
        }
    }
   
    public void determineScenarios() {
        
        int emptyBoardTrack = 0;

        /**
        * 0 - Currently Unset, only in first run 
        * 1 - Underpopulation, cell dies
        * 2 - Survival, cell stays alive
        * 3 - Creation of Life, new cell made
        * 4 - Overcrowding, cell dies
        * 5 - Error Handler State, should not occur
        */

        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++){

                switch(lifeGrid.getNeighbouringCells(i,j)) {
                    case 0:
                        emptyBoardTrack++;
                        break;
                    case 1: 
                        numOfNeighbours[i][j] = 1;
                        break;
                    case 2: case 3:
                        if (lifeGrid.getNeighbouringCells(i,j) == 3){
                            if(!lifeGrid.getGridValue(i,j)){
                                numOfNeighbours[i][j] = 3;
                            }
                            break;
                        }
                        if(lifeGrid.getGridValue(i,j)){
                            numOfNeighbours[i][j] = 2;
                            break;
                        }
                        break;
                    case 4: case 5:
                    case 6: case 7:
                    case 8:
                        numOfNeighbours[i][j] = 4;
                        break;
                    default:
                        numOfNeighbours[i][j] = 5;
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

                switch(numOfNeighbours[i][j]){
                    case 1:
                        System.out.println("Underpopulated space, cell at %d, %d has died", i, j);
                        lifeGrid.killCell(i,j);
                        break;
                    case 2:
                        System.out.println("Survival, cell at %d, %d has stayed alive", i, j);
                        break;
                    case 3:
                        System.out.println("Creation of Life, new cell at %d, %d has been made", i, j);
                        lifeGrid.addCell(i,j);
                    case 4:
                        System.out.println("Overcrowding, cell at %d. %d has died", i, j);
                        lifeGrid.killCell(i,j);
                    case 5:
                        System.out.println("Error State, printing out debug info");
                        System.out.println("*********************************************************");
                        System.out.println("*********************************************************");
                        System.out.println("*********************************************************");
                        System.out.println("*********************************************************");
                        System.out.println("Board Position %d, %d, Cell State %b", i, j, lifeGrid.getGridValue(i,j));
                        System.out.println("Neigbours %d", lifeGrid.getNeighbouringCells(i,j));
                        TimeUnit.MINUTES.sleep(1);
                }   
            }
        }
    }

}
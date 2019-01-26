/**
 * @author Alexander Owen-Meehan 
 */

import java.util.*;

public class Board{

    private boolean[][] cellGrid = new boolean[10][10];

    public Board(){

    	initialiseCellGrid(cellGrid);
        drawBoard();

    }

    public void initialiseCellGrid(boolean[][] cellGrid){

        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++){
                if(rand.nextDouble() >= 0.5){
                    cellGrid[i][j] = false;
                }
                else {
                    cellGrid[i][j] = true;
                }
            }
        }

    }

    public void addCell(int col, int row){

    	cellGrid[col][row] = true;

    }

    public void killCell(int col, int row){

    	cellGrid[col][row] = false;

    }

    public int getNeighbouringCells(int col, int row) {
        
    	int neighbours = 0;

    	if(col > 0){

    		neighbours += cellGrid[col-1][row] ? 1 : 0;

    	}

    	if(row > 0){

    		neighbours += cellGrid[col][row-1] ? 1 : 0;

    	}

    	if(col < 9){

    		neighbours += cellGrid[col+1][row] ? 1 : 0;

    	}

    	if(row < 9){

    		neighbours += cellGrid[col][row+1] ? 1 : 0;

    	}

    	//Top Left Check
    	if (col > 0 && row > 0){

    		neighbours += cellGrid[col-1][row-1] ? 1 : 0;

    	}

    	//Bottom Left Check
    	if (col > 0 && row < 9){

    		neighbours += cellGrid[col-1][row+1] ? 1 : 0;

    	}

    	//Top Right Check
    	if (col < 9 && row > 0){

    		neighbours += cellGrid[col+1][row-1] ? 1 : 0;

    	}

    	//Bottom Right Check
    	if (col < 9 && row < 9){

    		neighbours += cellGrid[col+1][row+1] ? 1 : 0;

    	}

    	return neighbours;

    }
   
    public boolean getGridValue(int col, int row){
        
        return cellGrid[col][row];

    }

    public void drawBoard(){

        System.out.println("Printing Board");

        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++){
                if(cellGrid[i][j]){
                    System.out.print((char)0x25A3);
                }
                else {
                    System.out.print((char)0x25A2);
                }
            }
            System.out.println("");
        }

    }

}
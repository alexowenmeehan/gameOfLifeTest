/**
 * @author Alexander Owen-Meehan (1434732), Daniel Foad (1526813)
 */

import java.util.*;

public class Main{

  public static void main(String[] args){

  	Board lifeGrid = new Board();

  	lifeGrid.addCell(3,3);
  	lifeGrid.addCell(2,3);
  	lifeGrid.addCell(4,3);
  	lifeGrid.addCell(4,4);
  	lifeGrid.addCell(5,3);

  	System.out.println(lifeGrid.getNeighbouringCells(3,3));


  } 

}
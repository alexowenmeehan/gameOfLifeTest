/**
 * @author Alexander Owen-Meehan 
 */
import java.util.Scanner;

public class Main{

  public static void main(String[] args){

  	Scanner in = new Scanner(System.in);
  	boolean debugMode = false;
  	String rows, cols;
  	int rowsNum, colsNum;

  	if(args.length != 0){
		if(args[0].equalsIgnoreCase("debug")){
	  		debugMode = true;
		}
	}

	System.out.println("Enter number of Rows for Game of Life grid");
	System.out.println("(Hit Enter to use Default of 10)");
	rows = in.nextLine();
    
    System.out.println("Enter number of Columns for Game of Life grid");
	System.out.println("(Hit Enter to use Default of 10)");
	cols = in.nextLine();

	if (rows.isEmpty() && cols.isEmpty()){
		GameOfLifeRun ng = new GameOfLifeRun(debugMode);
	}
	else{

		if ((rows.matches("[0-9]+")) && (cols.matches("[0-9]+"))){

			try {
	   			rowsNum = Integer.parseInt(rows);
	   			colsNum = Integer.parseInt(cols);
			}
			catch (NumberFormatException e)
			{
				System.out.println("Unrecognised number detected, setting to defaults");
	   			rowsNum = 10;
	   			colsNum = 10;
			}
		}
		else{

			System.out.println("Input not pure numbers, setting to defaults");
	   		rowsNum = 10;
	   		colsNum = 10;

		}

			GameOfLifeRun ng = new GameOfLifeRun(debugMode, rowsNum, colsNum);
	}
  	

  } 

}
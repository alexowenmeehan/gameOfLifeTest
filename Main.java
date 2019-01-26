/**
 * @author Alexander Owen-Meehan 
 */

public class Main{

  public static void main(String[] args){

  	boolean debugMode = false;

  	if(args.length != 0){
		if(args[0].equalsIgnoreCase("debug")){
	  		debugMode = true;
		}
	}

  	GameOfLifeRun ng = new GameOfLifeRun(debugMode);

  } 

}
package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.core.Field;

/**
 * Main application class.
 */
public class Minesweeper {
	
	private long startMillis;
	 private BestTimes bestTimes;
	 private static Minesweeper instance;
	
    public long getStartMillis() {
		return startMillis;
	}

	/** User interface. */
    private UserInterface userInterface;
 
    /**
     * Constructor.
     */
    private Minesweeper() {
    	instance = this;
    	bestTimes = new BestTimes();
    	startMillis= System.currentTimeMillis();
        userInterface = new ConsoleUI();
        
        Field field = new Field(9, 9, 10);
        userInterface.newGameStarted(field);
    }
    

    public static Minesweeper getInstance() {
    	if(instance != null){
		return instance;
    	}
    	else {
    		instance = new Minesweeper();
    		return instance;
    	}
	}


	public BestTimes getBestTimes() {
		return bestTimes;
	}


	/**
     * Main method.
     * @param args arguments
     */
    public static void main(String[] args) {
        new Minesweeper();
    }
    
    public int getPlayingSeconds(){
    	
    	Long time= System.currentTimeMillis() - startMillis;
    	int intTime= time.intValue();
    	return intTime/1000;
    }
}

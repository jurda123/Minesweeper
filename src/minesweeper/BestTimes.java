package minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;



/**
 * Player times.
 */
public class BestTimes implements Iterable<BestTimes.PlayerTime> {
    /** List of best player times. */
    private List<PlayerTime> playerTimes = new ArrayList<PlayerTime>();

    /**
     * Returns an iterator over a set of  best times.
     * @return an iterator
     */
    public Iterator<PlayerTime> iterator() {
        return playerTimes.iterator();
    }

    /**
     * Adds player time to best times.
     * @param name name ot the player
     * @param time player time in seconds
     */
    public void addPlayerTime(String name, int time) {
    	PlayerTime playerTime = new PlayerTime(name,  time);
       playerTimes.add(playerTime);
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object
     */
    public String toString() {
    	sortPlayerlist();
    	Iterator<PlayerTime> iterator = this.iterator();
    	Formatter f = new Formatter();
    	int i=1;
    	while (iterator.hasNext()) { 
			PlayerTime p = iterator.next(); 
			
			f.format(i + ". " + parsePlayerTime(p) + "\n");		
			i++;
    	
			}
    	return f.toString();
    }
    
    void reset(){
    	playerTimes.clear();
    }
    private String parsePlayerTime(PlayerTime playerTime){
    	Formatter f = new Formatter();
    	f.format(playerTime.getName() + " " + playerTime.getTime());
    	 return f.toString();
    }
			
    private void sortPlayerlist(){
    	Collections.sort(playerTimes);
    	//playerTimes.sort();
    }
    

    /**
     * Player time.
     */
    public static class PlayerTime implements Comparable<PlayerTime> {
        /** Player name. */
        private final String name;

        /** Playing time in seconds. */
        private final int time;

        /**
         * Constructor.
         * @param name player name
         * @param time playing game time in seconds
         */
        public PlayerTime(String name, int time) {
            this.name = name;
            this.time = time;
        }
        

		public String getName() {
			return name;
		}

		public int getTime() {
			return time;
		}
		@Override
		 public int compareTo(PlayerTime o){
			
			 if (time<o.getTime()){
				 return -1;
			 }
			 else if (time==o.getTime()){
				 return 0;
			 }
			 else if (time>o.getTime()){
				 return 1;
			 }
			 return 0;
		 }
    }
}
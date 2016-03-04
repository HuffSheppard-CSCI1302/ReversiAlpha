import java.util.Random;
import java.util.Scanner;

/** Is an abstract class for player, Finds valid moves and gets input from either player or computer.
 * @author Preston Sheppard
**/
 abstract class Player {
	/** abstract method for getting input
	  * @param cPiece	The <code>char</code> with which to fill the space
	  * @param caArray The grid that the user is playing on
	 * @author Preston Sheppard
	**/
 		abstract int[] getInput(char cPiece, char[][] caArray);
 		/** boolean method to see if an input is actually a valid move
 		  * @param ia The coordinates of the space to check
 		 * @author Preston Sheppard
 		**/
 		public boolean isValid(int[] ia, char[][] caArray){
 			ReversiBoard validGrid=new ReversiBoard(caArray);
 			
 			
 			int x=ia[0],y=ia[1];
 			if (validGrid.getGrid()[x][y]=='_') return true;
 			else return false;
 		}
 		
 		/** A class that extends and inherits all of the abstract Player class
 		 * @author Preston Sheppard
 		**/
}
	class HumanPlayer extends Player{//good
		/** Is a default constructor for the human player class
		 * @author Preston Sheppard
		**/
		 public HumanPlayer(){}
			
		Scanner keyboard = new Scanner(System.in);
		 /** a method that overloads the abstract method of getting an input
		  * @param cPiece	The <code>char</code> with which to fill the space
		  * @param caArray The grid that the user is playing on
		 * @author Preston Sheppard
		**/
		 int[] getInput(char cPiece, char[][] caArray){
					int[] input = new int[2];
					while (true){
					String temp=keyboard.nextLine().trim();
					if ((temp.length()!=2)||(temp.matches("[0-9]+")==false)) System.out.println("Please enter a valid input!");
					else{
						int intTemp=Integer.parseInt(temp);
						input[1]=intTemp%10;
						intTemp=(int) intTemp/10;
						input[0]=intTemp;
						if (isValid(input, caArray)==true) return input;
						else System.out.println("Please enter a valid input!");
							}//end the else statement
						}//ends the while true loop
					}//ends getinput	
		 		}//ends human player
	/** Is an abstract class for computer player, Finds valid moves and puts them into an array. Also, has a pause in it so that
	 * the computer doesnt immediatly place its piece.
	 * @author Preston Sheppard
	**/
	 abstract class ComputerPlayer extends Player{
		 /** a method that pauses the class for a set amount of time
		 * @author Preston Sheppard
		**/
		 public void pause(){
			 try {
				    Thread.sleep(1);                 //reset to 3000
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
		 }//pauses the shit for a certain amount of time
		 
		 ////////////////////////////////////////////////////////////////////////////
		 /** A method that scans the grid for possible moves and stores all possible 
		  *  moves into an array to be called on later.  
		  * @author Miles B Huff
		 **/
		 public int[][] getPossibleGuesses(char[][] caaGrid)
		 { //method
			 // Find all possible places
			 int[][] iaaCoordBank    = new int[0][2];
			 int[][] iaaCoordBankNew = new int[0][2];
			 for(int i = 1; i < 9; i++)
			 { //loop
				 for(int j = 1; j < 9; j++)
				 { //loop
					 if(caaGrid[i][j] == '_')
					 { //if
						 iaaCoordBankNew = new int[iaaCoordBank.length + 1][2];
						 for(int k = 0; k < iaaCoordBank.length; k++)
						 { //loop
							 for(int l = 0; l < 2; l++)
							 { //loop
								 iaaCoordBankNew[k][l] = iaaCoordBank[k][l];
							 } //loop
						 } //loop
						 iaaCoordBank = iaaCoordBankNew;
						 iaaCoordBank[iaaCoordBank.length - 1][0] = i;
						 iaaCoordBank[iaaCoordBank.length - 1][1] = j;
					 } //if
				 } //loop
			 } //loop
			 iaaCoordBankNew = new int[0][0];
			 return iaaCoordBank; 	 	
		 } //method
	}

/** a class of computer player that chooses a random possible guess coordinate from possible moves
 * @author Preston Sheppard
**/
class RandomComputerPlayer extends ComputerPlayer
{ //class
	/** default constructor for random computer player
	 * @author Preston Sheppard
	**/
	 public RandomComputerPlayer(){}
	 /** a method that returns what the computer is going to guess for its turn
	  * @param cPiece	The <code>char</code> with which to fill the space
	  * @param caArray The grid that the computer is playing on
	 * @author Preston Sheppard
	**/
		int[] getInput (char cPiece, char[][] caArray){
			pause();
			int[][] guessLoc= getPossibleGuesses(caArray);
			int [] compGuess=new int[2];
			Random rand=new Random();
			
			int randNum = rand.nextInt(guessLoc.length);
			compGuess[0]=guessLoc[randNum][0];
			compGuess[1]=guessLoc[randNum][1];
			return compGuess;
			}//ends getInput	
	 }//end of randomcompplayer

/** A more intelligent computer player.  This calculates what guess out of the 
 *  possible guess array will yield the highest score.  
 * @author Miles B Huff, 
 *         Preston Sheppard
**/

class IntelligentComputerPlayer extends ComputerPlayer
{ //class
	////////////////////////////////////////////////////////////////////////////
	/** The default constructor for IntelligentComputerPlayer
	 * @author Preston Sheppard
	**/
	public IntelligentComputerPlayer() {}

	////////////////////////////////////////////////////////////////////////////
	/** A method that returns what the computer is going to guess for its turn.  
	 *  Also calculates what guess will return the highest score and then gives 
	 *  that guess as an <code>int[]</code>.  
	 * @param  cPiece  The <code>char</code> with which to fill the space
	 * @param  caaGrid The gameboard
	 * @author Miles B Huff
	**/
	int[] getInput (char cPiece, char[][] caaGrid){
		pause();
		int[][] iaaCoordBank = getPossibleGuesses(caaGrid);
		
		// Figure out which has the highest value
		int iPiece = 0;
		if(cPiece == 'O') iPiece = 1;
		ReversiBoard oBestGrid = new ReversiBoard(caaGrid);
		ReversiBoard oNewGrid  = new ReversiBoard(caaGrid);
		int[] iaBest = new int[2];
		for(int i = 0; i < iaaCoordBank.length; i++)
		{ //loop
				oNewGrid.setCoord(cPiece, iaaCoordBank[i]);
				if(oNewGrid.getScore()[iPiece] > oBestGrid.getScore()[iPiece])
				{ //loop
					oBestGrid.setGrid(oNewGrid.getGrid());
					iaBest = iaaCoordBank[i];
					oNewGrid.setGrid(caaGrid);
				} //loop
		} //loop
		return iaBest; 
	} //method
} //class
	 	
 

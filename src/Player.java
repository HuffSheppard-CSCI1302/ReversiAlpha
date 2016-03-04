import java.util.Random;
import java.util.Scanner;

/** Is an abstract class for player, Finds valid moves and gets input from either player or computer.
 * @author Preston Sheppard
**/
public abstract class Player {
	Scanner keyboard = new Scanner(System.in);
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
 		public boolean isValid(int[] ia){
 			char[][] validGrid=new char [9][9];//Reversi.getGrid();
 			int x=ia[0],y=ia[1];
 			if (validGrid[x][y]=='_') return true;
 			else return false;
 		}
 		
 		/** A class that extends and inherits all of the abstract Player class
 		 * @author Preston Sheppard
 		**/
	public class HumanPlayer extends Player{//good
		/** Is a default constructor for the human player class
		 * @author Preston Sheppard
		**/
		 public HumanPlayer(){}
		 /** a method that overloads the abstract method of getting an input
		  * @param cPiece	The <code>char</code> with which to fill the space
		  * @param caArray The grid that the user is playing on
		 * @author Preston Sheppard
		**/
		 int[] getInput(char cPiece, char[][] caArray){
					int[] input = new int[2];
					while (true){
					System.out.println("Enter your move, "+cPiece+" player: ");
					String temp=keyboard.nextLine().trim();
					if ((temp.length()!=2)||(temp.matches("[0-9]+")==false)) System.out.println("Please enter a valid input!");
					else{
						int intTemp=Integer.parseInt(temp);
						input[1]=intTemp%10;
						intTemp=(int) intTemp/10;
						input[0]=intTemp;
						if (isValid(input)==true) return input;
						else System.out.println("Please enter a valid input!");
							}//end the else statement
						}//ends the while true loop
					}//ends getinput	
		 		}//ends human player
	/** Is an abstract class for computer player, Finds valid moves and puts them into an array. Also, has a pause in it so that
	 * the computer doesnt immediatly place its piece.
	 * @author Preston Sheppard
	**/
	public static abstract class ComputerPlayer extends Player{
		 /** a method that pauses the class for a set amount of time
		 * @author Preston Sheppard
		**/
		 public void pause(){
			 try {
				    Thread.sleep(0000);                 //reset to 3000
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
		 }//pauses the shit for a certain amount of time
		 /** a method that scans the grid for possible moves and stores all possible moves into an array to be called on later
		 * @author Preston Sheppard
		**/
		 public int[][] possibleGuesses(){//returns a 2d array of possible guess cordinates
			 int count=0; int [] temp=new int[2];
			 	for (int x=0; x<9;x++){ //this just finds how many possible guess locations there are
			 		for(int y=0; y<9;y++){
			 			temp[0]=x;temp[1]=y;
			 			if (isValid(temp)==true) count++; 
			 		} //y for
			 }//x for
			int[][] possibleGuessLoc=new int[count][2];
			count=0;
			for (int x=0; x<9;x++){//this loop places all possible coordinates in the array
		 		for(int y=0; y<9;y++){
		 			temp[0]=x;temp[1]=y;
		 			if (isValid(temp)==true){
		 				possibleGuessLoc[count][0]=x;
		 				possibleGuessLoc[count][1]=y;
		 				count++;
		 			}//end if 
		 		} //y for
			}//x for
		return possibleGuessLoc; 	 	
		 }//end possible guesses
		 /** a class of computer player that chooses a random possible guess coordinate from possible moves
		 * @author Preston Sheppard
		**/
		 public class RandomComputerPlayer extends ComputerPlayer{
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
					int[][] guessLoc= possibleGuesses();
					int [] compGuess=new int[2];
					Random rand=new Random();
					int randNum=rand.nextInt(guessLoc.length);
					compGuess[0]=guessLoc[randNum][0];
					compGuess[1]=guessLoc[randNum][1];
					return compGuess;
					}//ends getInput	
			 }//end of randomcompplayer
		 /** a more intelligent computer player. This calculates what guess out of the possible guess array will yield the highest score
		 * @author Preston Sheppard
		**/
		public	 class IntelligentComputerPlayer extends ComputerPlayer{
			 /** the default constructor for intelligent computer player
			 * @author Preston Sheppard
			**/
			public IntelligentComputerPlayer(){}
			/** a method that returns what the computer is going to guess for its turn. Also calculates what guess will return the highest
			 * score and then gives that guess as an int array
			  * @param cPiece	The <code>char</code> with which to fill the space
			  * @param caArray The grid that the computer is playing on
			 * @author Preston Sheppard
			**/
			 int [] getInput (char cPiece, char[][] caaGrid){
				 pause();
				 int [][] possibleGuess=possibleGuesses();
				 int poop=2;
				 if (cPiece=='X') poop=0;
				 if (cPiece=='O') poop=1;
				 ReversiBoard tempGridCheck=new ReversiBoard(caaGrid);
				 ReversiBoard tempGridGuess=new ReversiBoard(caaGrid);
				 int [] compGuess=new int[2];//has the guess that results in highest number of pieces in total
				 int [] compCheck=new int[2];//this is checked agains the compGuess to see if this will result in a higher total of pieces
				 compGuess[0]=possibleGuess[0][0];
				 compGuess[1]=possibleGuess[0][1];
				 for (int i=0; i<possibleGuess.length; i++){//this goes through the possible guess array and changes the compCheck to new coordinates
						 compCheck[0]=possibleGuess[i][0]; compCheck[1]=possibleGuess[i][1];
						 tempGridCheck.setCoord(cPiece, compCheck);
						 tempGridGuess.setCoord(cPiece, compGuess);
						 if ((tempGridCheck.getScore()[poop])>(tempGridGuess.getScore()[poop])){
							 compGuess[0]=compCheck[0]; compGuess[1]=compGuess[1]; 
						 }	
						tempGridCheck.setGrid(caaGrid);
						tempGridGuess.setGrid(caaGrid);	 
				 }//end for loop 
				return compGuess; 
			 } //end get input  
		 }//intelligent computer
	 	}//end computerplayer abstract
 }

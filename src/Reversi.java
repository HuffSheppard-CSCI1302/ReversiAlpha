/** This class represents a game of Reversi.
 * @author Miles B Huff <a href="mailto:MilesBHuff@UGA.edu"><nohtml><</nohtml>email<nohtml>></nohtml></a>, 
 *         Preston Sheppard
 * @date   2016-03-03
 * @disclaimer
 *  The following code represents our own work.  We have neither received nor 
 *  given inappropriate assistance.  We have not copied or modified code from 
 *  any source, apart from the course webpage and the course-textbook.  We 
 *  recognize that any unauthorized assistance or plagiarism will be handled in 
 *  accordance with the University of Georgia's Academic Honesty Policy and the 
 *  policies of this course.  We recognize that our work is based upon an 
 *  assignment created by the Department of Computer Science at the University 
 *  of Georgia.  Any publishing or posting of source code for this project is 
 *  strictly prohibited unless we have written consent from the Department of 
 *  Computer Science at the University of Georgia.  This statement of academic 
 *  honesty is derived from the one given in the instructions for lab #2 in 
 *  CSCI1301.  
 * @see <a href="https://gist.github.com/mepcotterell/3564a8eaa32f49e3d460</a>
**/

public class Reversi
{ //class
	////////////////////////////////////////////////////////////////////////////
	/** The main method.  
	 * @author Miles B Huff
	 * @param  saArgs Arguments passed into the program from the commandline.  
	**/
	public static void main(String[] saArgs)
	{ //method
		// Variables
		boolean bOtherCanMove = false;
		char    cPiece        = 'X'  ;  // Dark goes first
		
		// Create players
		for(int i = 0; i < 2; i++)
		{ //loop
			switch(saArgs[i])
			{ //switch
				case "Human":
					if(i == 0) Player oDark  = new Player.HumanPlayer(); 
					else       Player oLight = new Player.HumanPlayer();
					break;
				case "RandomComputerPlayer":
					if(i == 0) Player oDark  = new Player.ComputerPlayer.RandomComputerPlayer();
					else       Player oLight = new Player.ComputerPlayer.RandomComputerPlayer();
					break;
				case "IntelligentComputerPlayer":
					if(i == 0) Player oDark  = new Player.ComputerPlayer.IntelligentComputerPlayer();
					else       Player oLight = new Player.ComputerPlayer.IntelligentComputerPlayer();
					break;
				default:
					System.out.println("Usage:  \n$ java Reversi [Human|RandomComputerPlayer|IntelligentComputerPlayer] [Human|RandomComputerPlayer|IntelligentComputerPlayer]");
					break;
			} //switch
		} //loop

		// Instantiate a new game
		ReversiBoard oGrid = new ReversiBoard();
		System.out.println("Welcome to Reversi!  Moves should be entered in \"[row] [column]\" format.  ");
		
		// Core game-loop
		while(true)
		{ //loop
			// Refresh and print the gameboard
			oGrid.calcMoves(cPiece);
			System.out.println(oGrid.toString());

			// If there are moves left...  
			if(oGrid.canMove())
			{ //3+
				// Other player might be able to go next turn
				bOtherCanMove = true;

				// Get player-input and change the grid with it
				if(cPiece == 'X')
				{ //4+
					System.out.println("Enter your move, X player:  ");
					oGrid.update(oDark.getInput('X', oGrid.getGrid()));
				} else { //4=
					System.out.println("Enter your move, O player:  ");
					oGrid.update(oLight.getInput('O', oGrid.getGrid()));
				} //4-

			// If there are no moves left...  
			} else { //3=
				// If the other player could still move last turn...  
				if(bOtherCanMove) bOtherCanMove = false;
				// If neither player can move, the game is over.  
				else endGame(oGrid.getScore());
			} //3-
			
			// Change whose turn it is
			if(cPiece == 'X') cPiece = 'O';
			else              cPiece = 'X' ;
		} //loop
    	} //method

	////////////////////////////////////////////////////////////////////////////
	/** Ends the game.  
	 * @author Preston Sheppard
	 * @param  saArgs  Arguments passed into the program from the commandline.  
	**/
	private static void endGame(int[] iaScore)
	{ //method
		int xScore=iaScore[0];
		int oScore=iaScore[1];
		System.out.println("\n<GAME OVER>\n");
		System.out.println("Player X Score:"+xScore+".");
		System.out.print("Player O Score:"+oScore+".\n");
		if (oScore>xScore) System.out.println("Congratulations Player O! You win!");
		else if (oScore==xScore) System.out.print("Both Players tied! No gloating for you today!");
		else System.out.println("Congratulations Player X! You win!");
		System.exit(0);
	} //method
} //class

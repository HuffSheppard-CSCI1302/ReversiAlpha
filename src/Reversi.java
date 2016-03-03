/** This class represents a game of Reversi.
 * @author Miles B Huff <a href="mailto:MilesBHuff@UGA.edu"><nohtml><</nohtml>email<nohtml>></nohtml></a>, 
 *         Preston Sheppard
 * @date   2016-02-29
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
		boolean bDarkTurn     = true ;  // Dark goes first
		boolean bOtherCanMove = false;

		// Check saArgs
		String[] sa = cleanArgs(saArgs);
		
		// Create players
		for(int i = 0; i < sa.length; i++)
		{ //loop
			switch(sa[i])
			{ //switch
				case "Human":
					if(i == 0) Player oDark  = new Player.HumanPlayer;
					else       Player oLight = new Player.HumanPlayer;
					break;
				case "RandomComputerPlayer":
					if(i == 0) Player oDark  = new Player.ComputerPlayer.RandomComputerPlayer;
					else       Player oLight = new Player.ComputerPlayer.RandomComputerPlayer;
					break;
				case "IntelligentComputerPlayer":
					if(i == 0) Player oDark  = new Player.ComputerPlayer.IntelligentComputerPlayer;
					else       Player oLight = new Player.ComputerPlayer.IntelligentComputerPlayer;
					break;
				default:
					System.out.println("Usage:  \n$ java Reversi [Human|RandomComputerPlayer|IntelligentComputerPlayer] [Human|RandomComputerPlayer|IntelligentComputerPlayer]");
					break;
			} //switch
		} //loop

		// Instantiate a new game
		Board oGrid = new ReversiBoard;
		System.out.println("Welcome to Reversi!  Moves should be entered in \"[row] [column]\" format.  ");
		
		// Core game-loop
		while(true)
		{ //loop
			// Refresh and print the gameboard
			oGrid.calcMoves();
			System.out.println(oGrid.toString());

			// If there are moves left...  
			if(oGrid.canMove())
			{ //3+
				// Other player might be able to go next turn
				bOtherCanMove = true;

				// Get player-input and change the grid with it
				if(bDarkTurn)
				{ //4+
					System.out.println("Enter your move, X player:  ");
					grid.update(oDark.getInput('X', oGrid.getGrid()));
				} else { //4=
					System.out.println("Enter your move, O player:  ");
					grid.update(oLight.getInput('O', oGrid.getGrid()));
				} //4-

			// If there are no moves left...  
			} else { //3=
				// If the other player could still move last turn...  
				if(bOtherCanMove) bOtherCanMove = false;
				// If neither player can move, the game is over.  
				else oGrid.endGame(oGrid.getScore());
			} //3-
			
			// Change whose turn it is
			if(bDarkTurn) bDarkTurn = false;
			else          bDarkTurn = true ;
		} //loop
    	} //method

	////////////////////////////////////////////////////////////////////////////
	/** Validate and simplify input to just 2 args.  
	 * @author Preston Sheppard
	 * @param  saArgs  Arguments passed into the program from the commandline.  
	**/
	private String[2] cleanArgs(String[] saArgs)
	{ //method
		//TODO
	} //method

	////////////////////////////////////////////////////////////////////////////
	/** Ends the game.  
	 * @author Preston Sheppard
	 * @param  saArgs  Arguments passed into the program from the commandline.  
	**/
	private void endGame(int[] iaScore)
	{ //method
		//TODO
	} //method
} //class

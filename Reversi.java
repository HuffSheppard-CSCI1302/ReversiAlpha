/** This class represents a game of Reversi.
 * @author Miles B Huff <a href="mailto:MilesBHuff@UGA.edu"><nohtml><</nohtml>email<nohtml>></nohtml></a>
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
{ // 0+
	private static boolean bDarkTurn  = true ;  // Dark goes first
	private static int     iDarkType  = 1    ;  // Default to random CPU
	private static int     iLightType = 1    ;  // Default to random CPU
	
	/* KEY:  
	 * '.' = empty
	 * 'X' = Dark (player 1)
	 * 'O' = Light (player 2)
	 * '_' = Possible move
	 */
	
	////////////////////////////////////////////////////////////////////////////
	/** The main method.  
	 * @author Miles B Huff
	 * @param  saArgs  A String array.  Unused.  
	**/
	public static void main(String[] saArgs)
	{ // 1+
		// Check saArgs
		validateArgs(saArgs) ;
		
		// Instantiate a new game
		Board grid = new ReversiBoard ;
		
		// Fill the grid with blanks and axis-numbers
		grid.reset() ;
		
		System.out.println("Welcome to Reversi!  Moves should be entered in \"[row] [column]\" format.  ") ;
		
		// Core game-loop
		while(true)
			{ // 2+
			grid.print() ;

			char c = 'O' ;
			if(bDarkTurn) c = 'X' ;
			System.out.println("Enter your move, " + c + " player:  ") ;

			if(bDarkTurn) 
				// grid.update() takes the input in, and changes the grid with it
				grid.update(playerDark.getInput()) ;
			else
				// grid.update() takes the input in, and changes the grid with it
				grid.update(playerLight.getInput()) ;

			// Return true if game is over, and recalculates possible moves.  
			// If true, printGrid, figure out who won, and print end message
			// If false, return void to end the method
			gameFinish(gridEval()) ;
			
			// Change whose turn it is
			if(bDarkTurn)
				 bDarkTurn = false ;
			else bDarkTurn = true  ;
		} // 2-

    } // 1-
} // 0-

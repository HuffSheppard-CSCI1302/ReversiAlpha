/** This class represents a Reversi game-board.  
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

public class ReversiBoard implements Board
{ //class
	private char[][] caaGrid=new char[9][9];
	/* KEY: 
	 * '.' = empty
	 * 'X' = Dark (player 1)
	 * 'O' = Light (player 2)
	 * '_' = Possible move
	 */
	
	////////////////////////////////////////////////////////////////////////////
	/** Constructor-method.  Fills the grid with axis-numbers and blanks.  
	 * @author Miles B Huff
	**/
	public ReversiBoard()
	{ //method
		// x-axis
		for(int i = 0; i < 9; i++)
		{ //loop
			caaGrid[0][i] = Integer.toString(i).charAt(0);
		} //loop

		// y-axis
		for(int i = 0; i < 9; i++)
		{ //loop
			caaGrid[i][0] = Integer.toString(i).charAt(0);;
		} //loop

		// center
		for(int i = 1; i < 9; i++)
		{ //loop
			for(int j = 1; j < 9; j++)
			{ //loop
				caaGrid[i][j] = '.';
			} //loop
		} //loop
		
		// Place starting-pieces
		caaGrid[4][4] = 'X';
		caaGrid[4][5] = 'O';
		caaGrid[5][4] = 'O';
		caaGrid[5][5] = 'X';
	} //method
	
	////////////////////////////////////////////////////////////////////////////
	/** Constructor-method.  Fills the grid with the parameter.  
	 *  Warning:  Validation is *not* perfomed on this parameter!	
	 * @author Miles B Huff
	 * @param caaGrid The grid you'd like to generate a ReversiBoard from.  
	**/
	public ReversiBoard(char[][] caaGrid)
	{ //method
		setGrid(caaGrid);
	} //method

	////////////////////////////////////////////////////////////////////////////
	/** Figures out if there are any moves left.  
	 * @author Miles B Huff
	 * @return <code>true</code> if there are moves left.  
	**/
	public boolean canMove()
	{ //method
		// Find '_'
		for(int i = 1; i < 9; i++)
		{ //loop
			for(int j = 1; j < 9; j++)
			{ //loop
				// If a '_' is found, then there is a move left.  
				if(caaGrid[i][j] == '_') return true;
			} //loop
		} //loop
		// If no '_' found, then there are no moves left.  
		return false;
	} //method
	
	////////////////////////////////////////////////////////////////////////////
	/** Makes and returns a deep copy of <code>caaGrid</code>.  
	 * @author Miles B Huff, 
	 *         Preston Sheppard
	 * @return a deep copy of <code>caaGrid</code>
	**/
	public char[][] getGrid()
	{ //method
		char[][] caaGrid = new char[9][9];
		for(int i = 0; i < 9; i++)
		{ //loop
			for(int j = 0; j < 9; j++)
			{ //loop
				caaGrid[i][j] = this.caaGrid[i][j];
			} //loop
		} //loop
		return caaGrid;
	} //method
	
	////////////////////////////////////////////////////////////////////////////
	/** Calculates and returns the current score for both X and Y, as well as the number of blank spaces.  
	 * @author Miles B Huff
	 * @return an array containing Dark and Light's scores, respectively.  Also contains the number of blank spaces.  
	**/
	public int[] getScore()
	{ //method
		// Create scoreboard
		int[] iaScore = new int[3];
		for(int i = 0; i < 3; i++)
		{ //loop
			iaScore[i] = 0;
		} //loop
		
		// Fill scoreboard
		for(int i = 1; i < 9; i++)
		{ //loop
			for(int j = 1; j < 9; j++)
			{ //loop
				if(caaGrid[i][j] == 'X') iaScore[0]++;
				if(caaGrid[i][j] == 'O') iaScore[1]++;
				if(caaGrid[i][j] == '.') iaScore[2]++;
				if(caaGrid[i][j] == '_') iaScore[2]++;
			} //loop
		} //loop
		return iaScore;
	} //method
	
	////////////////////////////////////////////////////////////////////////////
	/** Converts <code>caaGrid</code> into a <code>String</code>.  
	 * @author Miles B Huff
	 * @return a <code>String</code>-representatino of <code>caaGrid</code>.  
	**/
	@Override
	public String toString()
	{ //method
		String s = "\n";
		for(int i = 0; i < 9; i++)
		{ //loop
			s+= ' ';
			for(int j = 0; j < 9; j++)
			{ //loop
				s+= ' ';
				s+= caaGrid[i][j];
			} //loop
		s+= '\n';
		} //loop
		return s;
	} //method
	
	////////////////////////////////////////////////////////////////////////////
	/** Finds all possible moves.  
	 * @author Miles B Huff
	 * @param  cPiece The piece to check moves for
	**/
	public void calcMoves(char cPiece)
	{ //method
		// Wipe '_'
		for(int i = 1; i < 9; i++)
		{ //loop
			for(int j = 1; j < 9; j++)
			{ //loop
				if(caaGrid[i][j] == '_') caaGrid[i][j] = '.';
			} //loop
		} //loop

		boolean bHasOpp = false;
		int[]   iaCoord = new int[2];

		// Calculate '_'
		for(int k = 1; k < 9; k++)
		{ //loop
			for(int m = 1; m < 9; m++)
			{ //loop
				iaCoord[0] = k;
				iaCoord[1] = m;

				// LEFT-TO-RIGHT
				try
				{ //try
					bHasOpp = false;
					for(int i = 1; true; i++)
					{ //loop
						if(caaGrid[iaCoord[0]][iaCoord[1]] != cPiece) break;
						if(cPiece == 'X')
						{ //if
							if(caaGrid[iaCoord[0]+i][iaCoord[1]] == 'X') break;
							else
							{ //if
								if(caaGrid[iaCoord[0]+i][iaCoord[1]] == 'O') bHasOpp = true;
								else
								{ //if
									if(!bHasOpp) break;
									if(caaGrid[iaCoord[0]+i][iaCoord[1]] == '.') caaGrid[iaCoord[0]+i][iaCoord[1]] = '_';
									break;
								} //if
							} //if
						} else {
							if(caaGrid[iaCoord[0]+i][iaCoord[1]] == 'O') break;
							else
							{ //if
								if(caaGrid[iaCoord[0]+i][iaCoord[1]] == 'X') bHasOpp = true;
								else
								{ //if
									if(!bHasOpp) break;
									if(caaGrid[iaCoord[0]+i][iaCoord[1]] == '.') caaGrid[iaCoord[0]+i][iaCoord[1]] = '_';
									break;
								} //if
							} //if
						} //if
					} //loop
				} catch(ArrayIndexOutOfBoundsException ex) {
				} finally {
				} //try
				
				// RIGHT-TO-LEFT
				try
				{ //try
					bHasOpp = false;
					for(int i = -1; true; i--)
					{ //loop
						if(caaGrid[iaCoord[0]][iaCoord[1]] != cPiece) break;
						if(cPiece == 'X')
						{ //if
							if(caaGrid[iaCoord[0]+i][iaCoord[1]] == 'X') break;
							else
							{ //if
								if(caaGrid[iaCoord[0]+i][iaCoord[1]] == 'O') bHasOpp = true;
								else
								{ //if
									if(!bHasOpp) break;
									if(caaGrid[iaCoord[0]+i][iaCoord[1]] == '.') caaGrid[iaCoord[0]+i][iaCoord[1]] = '_';
									break;
								} //if
							} //if
						} else {
							if(caaGrid[iaCoord[0]+i][iaCoord[1]] == 'O') break;
							else
							{ //if
								if(caaGrid[iaCoord[0]+i][iaCoord[1]] == 'X') bHasOpp = true;
								else
								{ //if
									if(!bHasOpp) break;
									if(caaGrid[iaCoord[0]+i][iaCoord[1]] == '.') caaGrid[iaCoord[0]+i][iaCoord[1]] = '_';
									break;
								} //if
							} //if
						} //if
					} //loop
				} catch(ArrayIndexOutOfBoundsException ex) {
				} finally {
				} //try
				
				// TOP-TO-BOTTOM
				try
				{ //try
					bHasOpp = false;
					for(int j = -1; true; j--)
					{ //loop
						if(caaGrid[iaCoord[0]][iaCoord[1]] != cPiece) break;
						if(cPiece == 'X')
						{ //if
							if(caaGrid[iaCoord[0]][iaCoord[1]+j] == 'X') break;
							else
							{ //if
								if(caaGrid[iaCoord[0]][iaCoord[1]+j] == 'O') bHasOpp = true;
								else
								{ //if
									if(!bHasOpp) break;
									if(caaGrid[iaCoord[0]][iaCoord[1]+j] == '.') caaGrid[iaCoord[0]][iaCoord[1]+j] = '_';
									break;
								} //if
							} //if
						} else {
							if(caaGrid[iaCoord[0]][iaCoord[1]+j] == 'O') break;
							else
							{ //if
								if(caaGrid[iaCoord[0]][iaCoord[1]+j] == 'X') bHasOpp = true;
								else
								{ //if
									if(!bHasOpp) break;
									if(caaGrid[iaCoord[0]][iaCoord[1]+j] == '.') caaGrid[iaCoord[0]][iaCoord[1]+j] = '_';
									break;
								} //if
							} //if
						} //if
					} //loop
				} catch(ArrayIndexOutOfBoundsException ex) {
				} finally {
				} //try
				
				// BOTTOM-TO-TOP
				try
				{ //try
					bHasOpp = false;
					for(int j = 1; true; j++)
					{ //loop
						if(caaGrid[iaCoord[0]][iaCoord[1]] != cPiece) break;
						if(cPiece == 'X')
						{ //if
							if(caaGrid[iaCoord[0]][iaCoord[1]+j] == 'X') break;
							else
							{ //if
								if(caaGrid[iaCoord[0]][iaCoord[1]+j] == 'O') bHasOpp = true;
								else
								{ //if
									if(!bHasOpp) break;
									if(caaGrid[iaCoord[0]][iaCoord[1]+j] == '.') caaGrid[iaCoord[0]][iaCoord[1]+j] = '_';
									break;
								} //if
							} //if
						} else {
							if(caaGrid[iaCoord[0]][iaCoord[1]+j] == 'O') break;
							else
							{ //if
								if(caaGrid[iaCoord[0]][iaCoord[1]+j] == 'X') bHasOpp = true;
								else
								{ //if
									if(!bHasOpp) break;
									if(caaGrid[iaCoord[0]][iaCoord[1]+j] == '.') caaGrid[iaCoord[0]][iaCoord[1]+j] = '_';
									break;
								} //if
							} //if
						} //if
					} //loop
				} catch(ArrayIndexOutOfBoundsException ex) {
				} finally {
				} //try
				
				// TL-TO-BR
				try
				{ //try
					bHasOpp = false;
					for(int i = 1, j = -1; true; i++, j--)
					{ //loop
						if(caaGrid[iaCoord[0]][iaCoord[1]] != cPiece) break;
						if(cPiece == 'X')
						{ //if
							if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == 'X') break;
							else
							{ //if
								if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == 'O') bHasOpp = true;
								else
								{ //if
									if(!bHasOpp) break;
									if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == '.') caaGrid[iaCoord[0]+i][iaCoord[1]+j] = '_';
									break;
								} //if
							} //if
						} else {
							if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == 'O') break;
							else
							{ //if
								if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == 'X') bHasOpp = true;
								else
								{ //if
									if(!bHasOpp) break;
									if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == '.') caaGrid[iaCoord[0]+i][iaCoord[1]+j] = '_';
									break;
								} //if
							} //if
						} //if
					} //loop
				} catch(ArrayIndexOutOfBoundsException ex) {
				} finally {
				} //try
				
				// BR-TO-TL
				try
				{ //try
					bHasOpp = false;
					for(int i = -1, j = 1; true; i--, j++)
					{ //loop
						if(caaGrid[iaCoord[0]][iaCoord[1]] != cPiece) break;
						if(cPiece == 'X')
						{ //if
							if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == 'X') break;
							else
							{ //if
								if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == 'O') bHasOpp = true;
								else
								{ //if
									if(!bHasOpp) break;
									if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == '.') caaGrid[iaCoord[0]+i][iaCoord[1]+j] = '_';
									break;
								} //if
							} //if
						} else {
							if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == 'O') break;
							else
							{ //if
								if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == 'X') bHasOpp = true;
								else
								{ //if
									if(!bHasOpp) break;
									if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == '.') caaGrid[iaCoord[0]+i][iaCoord[1]+j] = '_';
									break;
								} //if
							} //if
						} //if
					} //loop
				} catch(ArrayIndexOutOfBoundsException ex) {
				} finally {
				} //try
				
				// TR-TO-BL
				try
				{ //try
					bHasOpp = false;
					for(int i = -1, j = -1; true; i--, j--)
					{ //loop
						if(caaGrid[iaCoord[0]][iaCoord[1]] != cPiece) break;
						if(cPiece == 'X')
						{ //if
							if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == 'X') break;
							else
							{ //if
								if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == 'O') bHasOpp = true;
								else
								{ //if
									if(!bHasOpp) break;
									if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == '.') caaGrid[iaCoord[0]+i][iaCoord[1]+j] = '_';
									break;
								} //if
							} //if
						} else {
							if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == 'O') break;
							else
							{ //if
								if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == 'X') bHasOpp = true;
								else
								{ //if
									if(!bHasOpp) break;
									if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == '.') caaGrid[iaCoord[0]+i][iaCoord[1]+j] = '_';
									break;
								} //if
							} //if
						} //if
					} //loop
				} catch(ArrayIndexOutOfBoundsException ex) {
				} finally {
				} //try
				
				// BL-TO-TR
				try
				{ //try
					bHasOpp = false;
					for(int i = 1, j = 1; true; i++, j++)
					{ //loop
						if(caaGrid[iaCoord[0]][iaCoord[1]] != cPiece) break;
						if(cPiece == 'X')
						{ //if
							if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == 'X') break;
							else
							{ //if
								if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == 'O') bHasOpp = true;
								else
								{ //if
									if(!bHasOpp) break;
									if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == '.') caaGrid[iaCoord[0]+i][iaCoord[1]+j] = '_';
									break;
								} //if
							} //if
						} else {
							if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == 'O') break;
							else
							{ //if
								if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == 'X') bHasOpp = true;
								else
								{ //if
									if(!bHasOpp) break;
									if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == '.') caaGrid[iaCoord[0]+i][iaCoord[1]+j] = '_';
									break;
								} //if
							} //if
						} //if
					} //loop
				} catch(ArrayIndexOutOfBoundsException ex) {
				} finally {
				} //try
			} //loop
		} //loop
	} //method
	
	////////////////////////////////////////////////////////////////////////////
	/** Sets a single space in the grid with the specified <code>char</code>.  
	 *  If the character used to fill that space happens to be a player's piece, 
	 *  then also flip things.  
	 * @author Miles B Huff
	 * @param cPiece  The <code>char</code> with which to fill the space
	 * @param iaCoord The coordinates of the space to fill
	**/
	public void setCoord(char cPiece, int[] iaCoord)
	{ //method
		// Set specified square
		caaGrid[iaCoord[0]][iaCoord[1]] = cPiece;
		
		// If we're setting the square to a player's colour...
		if((cPiece == 'X')
		|| (cPiece == 'O'))
		{ //if
			boolean bFoundPiece = false     ;
			int[]   iaEnd       = new int[2];
			
			// Find flipping-ranges and flips their contents
			// LEFT-TO-RIGHT
			bFoundPiece = false;
			try
			{ //try
				for(int i = 1; true; i++)
				{ //loop
					if(caaGrid[iaCoord[0]+i][iaCoord[1]] == cPiece)
					{ //if
						bFoundPiece = true;
						iaEnd[0] = iaCoord[0]+i;
						break;
					} //if
					if(cPiece == 'X')
					{      if(caaGrid[iaCoord[0]+i][iaCoord[1]] != 'O') break;
					} else if(caaGrid[iaCoord[0]+i][iaCoord[1]] != 'X') break;
				} //loop
			} catch(ArrayIndexOutOfBoundsException ex) {
			} //try
			if(bFoundPiece)
			{ //if
				for(int i = 1; i < (iaEnd[0] - iaCoord[0]); i++)
				{ //loop
					caaGrid[iaCoord[0]+i][iaCoord[1]] = cPiece;
				} //loop
			} //if
			
			// RIGHT-TO-LEFT
			bFoundPiece = false;
			try
			{ //try
				for(int i = -1; true; i--)
				{ //loop
					if(caaGrid[iaCoord[0]+i][iaCoord[1]] == cPiece)
					{ //if
						bFoundPiece = true;
						iaEnd[0] = iaCoord[0]+i;
						break;
					} //if
					if(cPiece == 'X')
					{      if(caaGrid[iaCoord[0]+i][iaCoord[1]] != 'O') break;
					} else if(caaGrid[iaCoord[0]+i][iaCoord[1]] != 'X') break;
				} //loop
			} catch(ArrayIndexOutOfBoundsException ex) {
			} //try
			if(bFoundPiece)
			{ //if
				for(int i = -1; i > (iaEnd[0] - iaCoord[0]); i--)
				{ //loop
					caaGrid[iaCoord[0]+i][iaCoord[1]] = cPiece;
				} //loop
			} //if
			
			// TOP-TO-BOTTOM
			bFoundPiece = false;
			try
			{ //try
				for(int j = -1; true; j--)
				{ //loop
					if(caaGrid[iaCoord[0]][iaCoord[1]+j] == cPiece)
					{ //if
						bFoundPiece = true;
						iaEnd[1] = iaCoord[1]+j;
						break;
					} //if
					if(cPiece == 'X')
					{      if(caaGrid[iaCoord[0]][iaCoord[1]+j] != 'O') break;
					} else if(caaGrid[iaCoord[0]][iaCoord[1]+j] != 'X') break;
				} //loop
			} catch(ArrayIndexOutOfBoundsException ex) {
			} //try
			if(bFoundPiece)
			{ //if
				for(int j = -1; j > (iaEnd[1] - iaCoord[1]); j--)
				{ //loop
					caaGrid[iaCoord[0]][iaCoord[1]+j] = cPiece;
				} //loop
			} //if
			
			// BOTTOM-TO-TOP
			bFoundPiece = false;
			try
			{ //try
				for(int j = 1; true; j++)
				{ //loop
					if(caaGrid[iaCoord[0]][iaCoord[1]+j] == cPiece)
					{ //if
						bFoundPiece = true;
						iaEnd[1] = iaCoord[1]+j;
						break;
					} //if
					if(cPiece == 'X')
					{      if(caaGrid[iaCoord[0]][iaCoord[1]+j] != 'O') break;
					} else if(caaGrid[iaCoord[0]][iaCoord[1]+j] != 'X') break;
				} //loop
			} catch(ArrayIndexOutOfBoundsException ex) {
			} //try
			if(bFoundPiece)
			{ //if
				for(int j = 1; j < (iaEnd[1] - iaCoord[1]); j++)
				{ //loop
					caaGrid[iaCoord[0]][iaCoord[1]+j] = cPiece;
				} //loop
			} //if
			
			// TL-TO-BR
			bFoundPiece = false;
			try
			{ //try
				for(int i = 1, j = -1; true; i++, j--)
				{ //loop
					if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == cPiece)
					{ //if
						bFoundPiece = true;
						iaEnd[0] = iaCoord[0]+i;
						iaEnd[1] = iaCoord[1]+j;
						break;
					} //if
					if(cPiece == 'X')
					{      if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] != 'O') break;
					} else if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] != 'X') break;
				} //loop
			} catch(ArrayIndexOutOfBoundsException ex) {
			} //try
			if(bFoundPiece)
			{ //if
				for(int i = 1, j = -1; (i < (iaEnd[0] - iaCoord[0])) && (j > (iaEnd[1] - iaCoord[1])); i++, j--)
				{ //loop
					caaGrid[iaCoord[0]+i][iaCoord[1]+j] = cPiece;
				} //loop
			} //if
			
			// BR-TO-TL
			bFoundPiece = false;
			try
			{ //try
				for(int i = -1, j = 1; true; i--, j++)
				{ //loop
					if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == cPiece)
					{ //if
						bFoundPiece = true;
						iaEnd[0] = iaCoord[0]+i;
						iaEnd[1] = iaCoord[1]+j;
						break;
					} //if
					if(cPiece == 'X')
					{      if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] != 'O') break;
					} else if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] != 'X') break;
				} //loop
			} catch(ArrayIndexOutOfBoundsException ex) {
			} //try
			if(bFoundPiece)
			{ //if
				for(int i = -1, j = 1; (i > (iaEnd[0] - iaCoord[0])) && (j < (iaEnd[1] - iaCoord[1])); i--, j++)
				{ //loop
					caaGrid[iaCoord[0]+i][iaCoord[1]+j] = cPiece;
				} //loop
			} //if
			
			// TR-TO-BL
			bFoundPiece = false;
			try
			{ //try
				for(int i = -1, j = -1; true; i--, j--)
				{ //loop
					if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == cPiece)
					{ //if
						bFoundPiece = true;
						iaEnd[0] = iaCoord[0]+i;
						iaEnd[1] = iaCoord[1]+j;
						break;
					} //if
					if(cPiece == 'X')
					{      if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] != 'O') break;
					} else if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] != 'X') break;
				} //loop
			} catch(ArrayIndexOutOfBoundsException ex) {
			} //try
			if(bFoundPiece)
			{ //if
				for(int i = -1, j = -1; (i > (iaEnd[0] - iaCoord[0])) && (j > (iaEnd[1] - iaCoord[1])); i--, j--)
				{ //loop
					caaGrid[iaCoord[0]+i][iaCoord[1]+j] = cPiece;
				} //loop
			} //if
			
			// BL-TO-TR
			bFoundPiece = false;
			try
			{ //try
				for(int i = 1, j = 1; true; i++, j++)
				{ //loop
					if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] == cPiece)
					{ //if
						bFoundPiece = true;
						iaEnd[0] = iaCoord[0]+i;
						iaEnd[1] = iaCoord[1]+j;
						break;
					} //if
					if(cPiece == 'X')
					{      if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] != 'O') break;
					} else if(caaGrid[iaCoord[0]+i][iaCoord[1]+j] != 'X') break;
				} //loop
			} catch(ArrayIndexOutOfBoundsException ex) {
			} //try
			if(bFoundPiece)
			{ //if
				for(int i = 1, j = 1; (i < (iaEnd[0] - iaCoord[0])) && (j < (iaEnd[1] - iaCoord[1])); i++, j++)
				{ //loop
					caaGrid[iaCoord[0]+i][iaCoord[1]+j] = cPiece;
				} //loop
			} //if
			
		} //if
	} //method
	
	////////////////////////////////////////////////////////////////////////////
	/** Fills <code>caaGrid</code> with a new grid
	 * @author Miles B Huff
	 * @param  caaGrid The new grid
	**/
	public void setGrid(char[][] caaGrid)
	{ //method
		for(int i = 0; i < 9; i++)
		{ //loop
			for(int j = 0; j < 9; j++)
			{ //loop
				this.caaGrid[i][j] = caaGrid[i][j];
			} //loop
		} //loop
	} //method
} //class

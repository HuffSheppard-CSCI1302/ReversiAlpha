public class ReversiBoard implements Board
{ //class
  private char[9][9] caaGrid ;

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
    for(int i = 1; i < 9; i++)
    { // 2+
      caaGrid[0][i] = i;
    } // 2-

    // y-axis
    for(int i = 1; i < 9; i++)
    { // 2+
      caaGrid[i][0] = i;
    } // 2-

    // center
    for(int i = 1; i < 9; i++)
    { // 2+
      for(int j = 1; j < 9; j++)
      { // 3+
        caaGrid[i][j] = '.' ;
      } // 3-
    } // 2-
  } //method
  
  ////////////////////////////////////////////////////////////////////////////
  /** Constructor-method.  Fills the grid with the parameter.  
   *  Warning:  Validation is *not* perfomed on this parameter!  
   * @author Miles B Huff
   * @param caaGrid The grid you'd like to generate a ReversiBoard from.  
  **/
  public ReversiBoard(char[9][9] caaGrid)
  { //method
    for(int i = 0; i < 9; i++)
    { // 2+
      for(int j = 0; j < 9; j++)
      { // 3+
        this.caaGrid[i][j] = caaGrid[i][j] ;
      } // 3-
    } // 2-
  } //method

  ////////////////////////////////////////////////////////////////////////////
  /** Figures out if there are any moves left.  
   * @author Miles B Huff
   * @return <code>true</code> if there are moves left.  
  **/
  @Override
  public boolean canMove()
  { //method
    // Find '_'
    for(int i = 1; i < 9; i++)
    { // 2+
      for(int j = 1; j < 9; j++)
      { // 3+
        // If a '_' is found, then there is a move left.  
        if(caaGrid[i][j] == '_') return true ;
      } // 3-
    } // 2-
    // If no '_' found, then there are no moves left.  
    return false ;
  } //method
  
  ////////////////////////////////////////////////////////////////////////////
  /** Makes and returns a deep copy of <code>caaGrid</code>.  
   * @author Miles B Huff
   * @return a deep copy of <code>caaGrid</code>
  **/
  @Override
  public char[9][9] getGrid()
  { //method
    char[][] caaGrid = new char[9][9] ;
    for(int i = 0; i < 9; i++)
    { // 2+
      for(int j = 0; j < 9; j++)
      { // 3+
        caaGrid[i][j] = this.caaGrid[i][j] ;
      } // 3-
    } // 2-
    return caaGrid ;
  } //method
  
  ////////////////////////////////////////////////////////////////////////////
  /** Calculates and returns the current score.  
   * @author Miles B Huff
   * @return an array containing Dark and Light's scores, respectively.  
  **/
  @Override
  public int[2] getScore()
  { //method
    //TODO
  } //method
  
  ////////////////////////////////////////////////////////////////////////////
  /** Converts <code>caaGrid</code> into a <code>String</code>.  
   * @author Miles B Huff
  **/
  @Override
  public String toString()
  { //method
    String s = '\n' ;
    for(int i = 0; i < 9; i++)
    { // 2+
      s+= ' ' ;
      for(int j = 0; j < 9; j++)
      { // 3+
        s+= ' ' ;
        s+= caaGrid[i][j]) ;
      } // 3-
    s+= '\n' ;
    } // 2-
    s+= '\n' ;
  } //method
  
  ////////////////////////////////////////////////////////////////////////////
  /** Finds all possible moves.  
   * @author Miles B Huff
  **/
  @Override
  public void calcMoves()
  { //method
    // Wipe '_'
    for(int i = 1; i < 9; i++)
    { // 2+
      for(int j = 1; j < 9; j++)
      { // 3+
        if(caaGrid[i][j] == '_') caaGrid[i][j] = '.' ;
      } // 3-
    } // 2-

    // Calculate '_'
    //TODO
  } //method
  
  ////////////////////////////////////////////////////////////////////////////
  /** 
   * @author Miles B Huff
   * @param  cPiece  
   * @param  iaCoord 
  **/
  @Override
  public void setCoord(char cPiece, int iaCoord[2])
  { //method
    caaGrid[iaCoord[0]][iaCoord[1]] = cPiece ;
  } //method
  
} //class

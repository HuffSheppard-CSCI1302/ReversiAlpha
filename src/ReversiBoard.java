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
  /** Figures out if there are any moves left.  
   * @author Miles B Huff
   * @return true if there are moves left.  
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
  /** 
   * @author Miles B Huff
  **/
  @Override
  public char[9][9] getGrid()
  { //method
    //TODO
  } //method
  
  ////////////////////////////////////////////////////////////////////////////
  /** 
   * @author Miles B Huff
  **/
  @Override
  public int[2] getScore()
  { //method
    //TODO
  } //method
  
  ////////////////////////////////////////////////////////////////////////////
  /** Converts the grid into a String.  
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
    //TODO
  } //method
  
  ////////////////////////////////////////////////////////////////////////////
  /** 
   * @author Miles B Huff
  **/
  @Override
  public void setCoord(int ia[2])
  { //method
    //TODO
  } //method
  
} //class

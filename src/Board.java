public interface Board
{ //Interface
  
  // Variables
  private char[9][9] caaGrid ;
  
  // Methods
  public  boolean    canMove()           ;
  public  char[9][9] getGrid()           ;
  public  int[2]     getScore()          ;
  public  String     toString()          ;
  public  void       calcMoves()         ;
  public  void       endGame()           ;
  public  void       setCoord(int ia[2]) ;
  
} //Interface

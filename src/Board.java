public interface Board
{ //interface
  
  // Variables
  private char[9][9] caaGrid ;
  
  // Methods
  public  boolean    canMove()   ;
  public  char[9][9] getGrid()   ;
  public  int[2]     getScore()  ;
  public  void       calcMoves() ;
  public  void       setCoord(char cPiece, int[2] iaCoord) ;
  public  void       setCoord(char[9][9] caaGrid)          ;
  
} //interface

public interface Board
{ //interface
  
  // Variables
  private char[][] caaGrid;
  
  // Methods
  public  boolean  canMove()  ;
  public  char[][] getGrid()  ;
  public  int[]    getScore() ;
  public  void     calcMoves();
  public  void     setCoord(char cPiece, int[] iaCoord);
  public  void     setCoord(char[][] caaGrid);
  
} //interface

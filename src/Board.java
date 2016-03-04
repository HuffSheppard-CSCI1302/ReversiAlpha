public interface Board
{ //interface
  
  // Variables
  public char[][] caaGrid = new char[9][9];
  
  // Methods
  public  boolean  canMove()  ;
  public  char[][] getGrid()  ;
  public  int[]    getScore() ;
  public  void     calcMoves();
  public  void     setCoord(char cPiece, int[] iaCoord);
  public  void     setCoord(char[][] caaGrid);
  
} //interface

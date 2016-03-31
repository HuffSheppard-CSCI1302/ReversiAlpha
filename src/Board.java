/** This class represents a generic game-board.  
 * @author Miles B Huff <a href="mailto:MilesBHuff@UGA.edu"><nohtml><</nohtml>email<nohtml>></nohtml></a>
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

public interface Board
{ //interface
  
  // Variables
  public char[][] caaGrid = new char[9][9] ;
  
  // Methods
  public  boolean  canMove()  ;
  public  char[][] getGrid()  ;
  public  int[]    getScore() ;
  public  void     calcMoves(char cPiece)    ;
  public  void     setCoord(char cPiece, int[] iaCoord) ;
  public  void     setGrid(char[][] caaGrid) ;
  
} //interface

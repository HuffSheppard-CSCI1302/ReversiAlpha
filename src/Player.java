import java.util.Random;
import java.util.Scanner;

abstract class Player {
	Scanner keyboard = new Scanner(System.in);
 		abstract int[] getInput(char cPiece, char[][] caArray);
 		public boolean isValid(int[] ia){
 			char[][] validGrid=new char [9][9];//Reversi.getGrid();
 			int x=ia[0],y=ia[1];
 			if (validGrid[x][y]=='_') return true;
 			else return false;
 		}

 class HumanPlayer extends Player{//good
	 int[] getInput(char cPiece, char[][] caArray){
				int[] input = new int[2];
				while (true){
				System.out.println("Enter your move, "+cPiece+" player: ");
				String temp=keyboard.nextLine().trim();
				if ((temp.length()!=2)||(temp.matches("[0-9]+")==false)) System.out.println("Please enter a valid input!");
				else{
					int intTemp=Integer.parseInt(temp);
					input[1]=intTemp%10;
					intTemp=(int) intTemp/10;
					input[0]=intTemp;
					if (isValid(input)==true) return input;
					else System.out.println("Please enter a valid input!");
						}
					} 
				}	
	 }
 
 
 abstract class ComputerPlayer extends Player{//good
	 
	 public void pause(){
		 try {
			    Thread.sleep(0000);                 //reset to 3000
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
	 }
	 
	 public int[][] possibleGuesses(){//returns a 2d array of possible guess cordinates
		 int count=0; int [] temp=new int[2];
		 	for (int x=0; x<9;x++){ //this just finds how many possible guess locations there are
		 		for(int y=0; y<9;y++){
		 			temp[0]=x;temp[1]=y;
		 			if (isValid(temp)==true) count++; 
		 		} //y for
		 }//x for
		int[][] possibleGuessLoc=new int[count][2];
		count=0;
		for (int x=0; x<9;x++){//this loop places all possible coordinates in the array
	 		for(int y=0; y<9;y++){
	 			temp[0]=x;temp[1]=y;
	 			if (isValid(temp)==true){
	 				possibleGuessLoc[count][0]=x;
	 				possibleGuessLoc[count][1]=y;
	 				count++;
	 			}//end if 
	 		} //y for
		}//x for
	return possibleGuessLoc; 	 	
	 }//end possible guesses
	
	
	 class RandomComputerPlayer extends ComputerPlayer{
		 
			int[] getInput (char cPiece, char[][] caArray){
				pause();
				int[][] guessLoc= possibleGuesses();
				int [] compGuess=new int[2];
				Random rand=new Random();
				int randNum=rand.nextInt(guessLoc.length);
				compGuess[0]=guessLoc[randNum][0];
				compGuess[1]=guessLoc[randNum][1];
				return compGuess;
				}	
		 }//end of randomcompplayer
	 
	 

	 class InteligentComputerPlayer extends ComputerPlayer
	 {
		 
		 int [] getInput (char cPiece, char[][] caaGrid){
			 pause();
			 int [][] possibleGuess=possibleGuesses();
			 int poop=2;
			 if (cPiece=='X') poop=0;
			 if (cPiece=='O') poop=1;
			 ReversiBoard tempGridCheck=new ReversiBoard(caaGrid);
			 ReversiBoard tempGridGuess=new ReversiBoard(caaGrid);
			 
			 int [] compGuess=new int[2];//has the guess that results in highest number of pieces in total
			 int [] compCheck=new int[2];//this is checked agains the compGuess to see if this will result in a higher total of pieces
			 compGuess[0]=possibleGuess[0][0];
			 compGuess[1]=possibleGuess[0][1];
			 for (int i=0; i<possibleGuess.length; i++){//this goes through the possible guess array and changes the compCheck to new coordinates
					 compCheck[0]=possibleGuess[i][0];
					 compCheck[1]=possibleGuess[i][1];
					 
					 tempGridCheck.setCoord(cPiece, compCheck);
					 tempGridGuess.setCoord(cPiece, compGuess);
					
				
					 if ((tempGridCheck.getScore()[poop])>(tempGridGuess.getScore()[poop])){
						 compGuess[0]=compCheck[0];
						 compGuess[1]=compGuess[1]; 
					 }	
					tempGridCheck.setGrid(caaGrid);
					tempGridGuess.setGrid(caaGrid);
					 
					 
					 
					 
			 }	 
			return compGuess; 
		 }  
 
 
 }
	

 }


 }





	
	
	
	


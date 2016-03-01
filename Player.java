import java.util.Scanner;

abstract class Player {
	Scanner keyboard = new Scanner(System.in);
 		abstract int[] getInput(char cPiece);
 		private boolean isValid(int[] ia){
 			char[][] validGrid=Reversi.getGrid();
 			int x=ia[0],y=ia[1];
 			if (validGrid[x][y]=='_') return true;
 			else return false;
 		}

 class HumanPlayer extends Player{
	 int[] getInput(char cPiece){
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
 
 class RandomComputerPlayer extends Player{
	 int[] getInput(char cPiece){
		 int [] compGuess=new int[2];
		 int[] temp=new int[2];
		 	for (int x=0; x<9;x++){
		 		for(int y=0; y<9;y++){
		 			temp[0]=x;temp[1]=y;
		 			if (isValid(temp)==true) compGuess=temp; 
		 		} //y for
		 }//x for
		 	return compGuess;
	 }//end of getinput
 }//end of randomcompplayer

 





}


	
	
	
	


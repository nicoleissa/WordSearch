/*Word Search: game where players take turns finding the most words that occur on the board
	
Name: Nicole Issagholian

Date: 12/5/2021

Note: Professor Odeh said to only have game be played by two players

*/

public class WordSearchLauncher
{
	//main function
    public static void main(String[] args)
	{        
        // Instantiate game object from GuessGame
        WordSearchGuessGame wordSearchGuessGame = new WordSearchGuessGame();

        // start the game
        wordSearchGuessGame.startGame();
    }

}

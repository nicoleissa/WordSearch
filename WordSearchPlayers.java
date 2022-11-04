/*Word Search: game where players take turns finding the most words that occur on the board
	
Name: Nicole Issagholian

Date: 12/5/2021

*/

import java.util.ArrayList;

public class WordSearchPlayers
{
	//variable that holds score for player
	int score;

	//array that holds player's guessed words
	ArrayList<String> guessedWords;

	//array that holds total guessed words from both players
	static ArrayList<String> totalGuessedWords = new ArrayList<String>();

	//constructor for players class
	public WordSearchPlayers ()
	{
		this.score = 0;
		this.guessedWords = new ArrayList<String>();	
	}
}

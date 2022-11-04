/*Word Search: game where players take turns finding the most words that occur on the board
	
Name: Nicole Issagholian

Date: 12/5/2021

*/

import java.util.Scanner;
import java.util.Arrays;

//The GuessGame is passed on 2 players trying to guess a word using the randomly generated letters on the board
public class WordSearchGuessGame
{
    //object of board class
    WordSearchBoard b;

    //player one and player two
    WordSearchPlayers p1;
    WordSearchPlayers p2;

    //variables that hold each player's guesses
    String guess;

    //array for players one and two 
    WordSearchPlayers[] players;

    //constructor for guess game class
    public WordSearchGuessGame()
    {
        WordSearchPlayers p1 = new WordSearchPlayers();
        WordSearchPlayers p2 = new WordSearchPlayers();
        this.players = new WordSearchPlayers[] {p1, p2};
        this.b = new WordSearchBoard(this.players);       
    }

    //method that starts the game
    public void startGame()
    {
        //scanner object
        Scanner data = new Scanner(System.in);
        
        //generate board
        b.printBoard();

        int player = 0; 

        //while loop asks for users to enter guesses until all the words on the board have been guessed 
        while ((players[0].guessedWords.size() + players[1].guessedWords.size()) < b.info.length-2)
        {
            System.out.print("Player " + player + ", please enter a word: ");
            String guess = data.nextLine();

            if (b.checkOrientations(guess, players[player]))
            {
                if (b.horizForward(guess))
                {
                    b.printBoard(); 
                }
                else if (b.horizBack(guess)) 
                {
                    b.printBoard(); 
                }               
                else if (b.vertForward(guess))
                {
                    b.printBoard(); 
                }
                else if (b.vertBack(guess))
                {
                    b.printBoard(); 
                }
                else if (b.diagForwardBL(guess))
                {
                    b.printBoard();
                }
                else if (b.diagForwardTL(guess))
                {
                    b.printBoard();    
                }
                else if (b.diagBackBR(guess))
                {
                    b.printBoard();
                }
                else if (b.diagBackBL(guess))
                {
                    b.printBoard();
                }
            }
            else 
            {
                System.out.println("Invalid input, try again.");
            }

            System.out.println("Score: ");
            System.out.println("Player 0: " + players[0].score + " " + players[0].guessedWords + "");    
            System.out.println("Player 1: " + players[1].score + " " + players[1].guessedWords + ""); 

            player = (player + 1) % 2;
        }    

    }
}
    
    
    


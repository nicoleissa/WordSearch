/*Word Search: game where players take turn finding the most words that occur on the board
	
Name: Nicole Issagholian

Date: 12/5/2021

*/

import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;

public class WordSearchBoard
{
    //instance variables 
    char[][] board;
    int dimension;
    String[] rightWords;
    WordSearchPlayers[] players;
    String[] info;
    
    //constructor for board class
    //Randomizes board elements on board, creates board, sets board's cells to values from csv file
    public WordSearchBoard(WordSearchPlayers[] players)
    {
        //intializes array holding words each player's valid guesses
        rightWords = new String[24];

        //randomizes board from 12 csv files
        int num = (int)(Math.random()*12) + 1;                
        String fileName = "board_" + num + ".csv";       
        File files = new File(fileName);  

        String line = "";   

        try   
        {  
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader br = new BufferedReader(new FileReader(files));

            //get the first line of the file, and extract all the information
            this.info = br.readLine().split(",");

            //makes first index of each csv file the dimension of the board
            this.dimension = Integer.parseInt(info[0]);
            board = new char[dimension][dimension];
            int row = 0;

            //splits all the letters in the csv file by comma, starting from the 2nd row till the last row
            while ((line = br.readLine()) != null)   
            { 
                String[] letters = line.split(",");
                // letters = {"l", "y", "n", "e", "t", "w"}
                for(int i = 0; i < letters.length; i++)
                {
                    board[row][i] = letters[i].charAt(0);
                }
                row++;                
            } 

        }
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }

    }

    //board with characters, updates based on players' guesses
    public void printBoard()
    {
        for (int rowsOfGrid=0; rowsOfGrid<board.length; rowsOfGrid++)
        {
            for (int colsOfGrid=0; colsOfGrid<board[rowsOfGrid].length; colsOfGrid++)
            {
                if (colsOfGrid == 0)
                {
                    System.out.print("+---+");
                    for (int row=0; row<dimension-1; row++)
                    {
                        System.out.print("---+");
                    }               
                    System.out.println();
                    System.out.print("|");

                    for (int row=0; row<dimension; row++)
                    {
                        System.out.print(" " + board[rowsOfGrid][row] + " |");
                    }               
                }           
            }
            System.out.println();
        }
        System.out.print("+---+");
        for (int row=0; row<dimension-1; row++)
        {
            System.out.print("---+");
        }
        System.out.println();
    }

    //makes sure guesses are valid-word is in answer key array, word has not been guessed already, and word is at least 3 letters long
    public boolean checkOrientations (String word, WordSearchPlayers p1)
    {
        if (word.length() < 3)
        {
            return false;
        }

        if (WordSearchPlayers.totalGuessedWords.contains("\'" + word + "\'"))
        {
            return false;
        }
        
        p1.guessedWords.add("\'" + word + "\'");
        WordSearchPlayers.totalGuessedWords.add("\'" + word + "\'");

        if (Arrays.asList(info).contains(word))
        {
            p1.score++;
            return true;
        }

        return false;       

    }   

    //checks for words that are horizontally forward
    public boolean horizForward(String word)
    {
        char firstLetter = word.charAt(0);
        boolean foundWord = false;
        int foundI=0;
        int foundJ=0;

        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<(board[i].length-word.length())+1; j++)
            {
                if (firstLetter == Character.toLowerCase(board[i][j]))
                {
                    if (j+word.length() <= board.length)
                    {
                        foundWord = true;
                        for (int k=0; k<word.length(); k++)
                        {
                            if (word.charAt(k) != Character.toLowerCase(board[i][j+k]))
                            {
                                foundWord = false;
                                break;
                            }    
                        }
                        if (foundWord == true)
                        {
                            foundI = i;
                            foundJ = j;

                            for (int l=0; l<word.length();l++)
                            {
                                board[foundI][foundJ+l] = Character.toUpperCase(word.charAt(l));
                            }  
                            return foundWord;
                        } 
                    }
                    
                }    
            }    
        }

        return foundWord;
    }

    //checks for words that are horizontally backward
    public boolean horizBack(String word)
    {
        char firstLetter = word.charAt(0);
        boolean foundWord = false;
        int foundI=0;
        int foundJ=0;

        for (int i=0; i<board.length; i++)
        {
            for (int j=board.length-1; j>-1; j--)
            {
                if (firstLetter == Character.toLowerCase(board[i][j]))
                {
                    if (j-(word.length()-1) >= 0)
                    {
                        foundWord = true;
                        for (int k=0; k<word.length(); k++)
                        {
                            if (word.charAt(k) != Character.toLowerCase(board[i][j-k]))
                            {
                                foundWord = false;
                                break;
                            }    
                        }
                        if (foundWord == true)
                        {
                            foundI = i;
                            foundJ = j;
                            for (int l=0; l<word.length(); l++)
                            {
                                board[foundI][foundJ-l] = Character.toUpperCase(word.charAt(l));
                            }
                            return foundWord;
                        } 
                    }
                }    
            }    
        }

        return foundWord;
    }
     
    //checks for words that are vertically forward 
    public boolean vertForward(String word)
    {
        char firstLetter = word.charAt(0);
        boolean foundWord = false;
        int foundI=0;
        int foundJ=0;

        for (int i=0; i<(board.length-word.length())+1; i++)
        {
            for (int j=0; j<(board[i].length); j++)
            {
                if (firstLetter == Character.toLowerCase(board[i][j]))
                {
                    if (i+word.length() <= board.length)
                    {
                        foundWord = true;
                        for (int k=0; k<word.length(); k++)
                        {
                            if (word.charAt(k) != Character.toLowerCase(board[i+k][j]))
                            {
                                foundWord = false;
                                break;
                            }    
                        }
                        if (foundWord == true)
                        {
                            foundI = i;
                            foundJ = j;
                            for (int l=0; l<word.length(); l++)
                            {
                                board[foundI+l][foundJ] = Character.toUpperCase(word.charAt(l));
                            } 
                            return foundWord;
                        } 
                    }
                }    
            }    
        }
         
        return foundWord;  
    }
    
    //checks for words that are vertically backward        
    public boolean vertBack(String word)
    {
        char firstLetter = word.charAt(0);
        boolean foundWord = false;
        int foundI=0;
        int foundJ=0;

        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
            {
                if (firstLetter == Character.toLowerCase(board[i][j]))
                {
                    if (i-word.length()+1 >= 0)
                    {
                        foundWord = true;
                        for (int k=0; k<word.length(); k++)
                        {
                            if (word.charAt(k) != Character.toLowerCase(board[i-k][j]))
                            {
                                foundWord = false;
                                break;
                            }    
                        }
                        if (foundWord == true)
                        {
                            foundI = i;
                            foundJ = j;
                            for (int l=0; l<word.length(); l++)
                            {
                                board[foundI-l][foundJ] = Character.toUpperCase(word.charAt(l));
                            }
                            return foundWord;
                        }
                    }
                }    
            }    
        }
    
        return foundWord;
    }

    //checks for words that are diagonally forward, from left (highest point) to right (lowest point)
    public boolean diagForwardTL(String word)
    {
        char firstLetter = word.charAt(0);
        boolean foundWord = false;
        int foundI=0;
        int foundJ=0;

        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
            {
                if (firstLetter == Character.toLowerCase(board[i][j]))
                {
                    if ((j+word.length() <= board.length) && (i+word.length() <= board.length))
                    {
                        foundWord = true;
                        for (int k=0; k<word.length()-1; k++)
                        {
                            if (word.charAt(k) != Character.toLowerCase(board[i+k][j+k]))
                            {
                                foundWord = false;
                                break;
                            }    
                        }
                        if (foundWord == true)
                        {
                            foundI = i;
                            foundJ = j;
                            for (int l=0; l<word.length(); l++)
                            {
                                board[foundI+l][foundJ+l] = Character.toUpperCase(word.charAt(l));
                            }
                            return foundWord; 
                        } 
                    }
                }    
            }    
        }
         
        return foundWord;
    }

    //checks for words that are diagonally forward, from left (lowest point) to right (highest point)-also not capitalizing, false?
    public boolean diagForwardBL(String word)
    {
        char firstLetter = word.charAt(0);
        boolean foundWord = false;
        int foundI=0;
        int foundJ=0;

        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
            {
                if (firstLetter == Character.toLowerCase(board[i][j]))
                {
                    if ((j+word.length()-1 < board.length) && (i-word.length()+1 >= 0))
                    {
                        foundWord = true;
                        for (int k=0; k<word.length(); k++)
                        {
                            if (word.charAt(k) != Character.toLowerCase(board[i-k][j+k]))
                            {
                                foundWord = false;
                                break;
                            }    
                        }
                        if (foundWord == true)
                        {
                            foundI = i;
                            foundJ = j;
                            for (int l=0; l<word.length(); l++)
                            {
                                board[foundI-l][foundJ+l] = Character.toUpperCase(word.charAt(l));
                            }
                            return foundWord;
                        } 
                    }
                }    
            }    
        }

        return foundWord;      
    }

    //checks for words that are diagonally backward, from right (lowest point) to left (highest point)-not capitalizing its false?
    public boolean diagBackBR(String word) 
    {
        char firstLetter = word.charAt(0);
        boolean foundWord = false;
        int foundI=0;
        int foundJ=0;

        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
            {
                if (firstLetter == Character.toLowerCase(board[i][j]))
                {                    
                    if ((j-word.length()+1 >= 0) && (i-word.length()+1 >= 0))
                    {
                        foundWord = true;
                        for (int k=0; k<word.length(); k++)
                        {
                            if (word.charAt(k) != Character.toLowerCase(board[i-k][j-k]))
                            {
                                foundWord = false;
                                break;
                            }    
                        }
                        if (foundWord == true)
                        {
                            foundI = i;
                            foundJ = j;
                            for (int l=0; l<word.length(); l++)
                            {
                                board[foundI-l][foundJ-l] = Character.toUpperCase(word.charAt(l));
                            }
                            return foundWord;
                        } 
                    }
                }    
            }    
        }

        return foundWord; 
    }

    //checks for words that are diagonally backward, from right (highest point) to left (lowest point)
    public boolean diagBackBL(String word)
    {
        char firstLetter = word.charAt(0);
        boolean foundWord = false;
        int foundI=0;
        int foundJ=0;

        for (int i=0; i<board.length; i++)
        {
            for (int j=0; j<board[i].length; j++)
            {
                if (firstLetter == Character.toLowerCase(board[i][j]))
                {   
                    if ((j-word.length()+1 >= 0) && (i+word.length()-1 < board.length))
                    {
                        foundWord = true;
                        for (int k=0; k<word.length(); k++)
                        {
                            if (word.charAt(k) != Character.toLowerCase(board[i+k][j-k]))
                            {
                                foundWord = false;
                                break;
                            }    
                        }
                        if (foundWord == true)
                        {
                            foundI = i;
                            foundJ = j;
                            for (int l=0; l<word.length(); l++)
                            {
                                board[foundI+l][foundJ-l] = Character.toUpperCase(word.charAt(l));
                            } 
                            return foundWord;
                        } 
                    }
                }    
            }    
        }

        return foundWord; 
    }

}

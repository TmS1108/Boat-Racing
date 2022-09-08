import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Processor
{
	Stream s = new Stream();
	Trap t = new Trap();
	private int currentPlayer;
	private final SeaObject[] grid;
	Player p1;
	Player p2;

//====================================================================================================================//
	//Constructor
	public Processor(String p1name, String p2name)
	{
		p1 = new Player(p1name);
		p2 = new Player(p2name);
		this.grid = new SeaObject[200];
		t.setTrap(grid);
		s.setStream(grid);
		this.grid[0] = p1;
		this.grid[1] = p2;
		currentPlayer = 1; //State that player 1 always start first
	}

//====================================================================================================================//
	//Methods
	/*Checks if a player has completed the game
	 *@return - Returns true, if there is a winner.
	 			Returns false, if there is no winner yet*/
	public boolean hasWon()
	{
		for ( int i = 0 ; i < grid.length ; i++)
		{
			if(grid[100] != null)
			{
				return true;
			}
		}
		return false;
	}

	//Checks for all possible moves which includes traps, streams and "eating"
	public boolean possibleMove(int diceNum, int move, String p1name, String p2name)
	{
		for ( int i = 0; i < grid.length; i++)
		{
			//If end position is null, move Player. i+diceNum is equals to end position
			if (grid[i] != null)
			{
				if (grid[i+diceNum] == null)
				{
					//Checks current player. Making sure that player 1 can only move player 1 object
					if (this.currentPlayer == 1)
					{
						//Checks SeaObject array objects. Making sure that player 1 can only move player 1 object
						if (this.grid[i].getObjectName().equals(p1name))
						{
							//Checks if end position is within the grid
							if ((i+diceNum) < 100)
							{
								p1.boatMove(grid,i,diceNum);
								p1.P1scoreCount(grid);
								return true;
							}
							//Checks if end position is equals to 100. If it is, then WIN.
							else if ((i+diceNum) == 100)
							{
								this.grid[i+diceNum] = this.grid[i];
								p1.P1scoreCount(grid);
								return true;
							}
							//Checks if end position goes beyond river boundary
							else if ((i+diceNum) > 100)
							{
								p1.overFlow(grid,i,diceNum);
								p1.P1scoreCount(grid);
								return true;
							}
						}
					}
					//check is repeated for player 2
					else if (this.currentPlayer == 2)
					{
						if (this.grid[i].getObjectName().equals(p2name))
						{
							if ((i+diceNum) < 100)
							{
								p2.boatMove(grid,i,diceNum);
								p2.P2scoreCount(grid);
								return true;
							}
							else if ((i+diceNum) == 100)
							{
								this.grid[i+diceNum] = this.grid[i];
								p2.P2scoreCount(grid);
								return true;
							}
							else if ((i+diceNum) > 100)
							{
								p2.overFlow(grid,i,diceNum);
								p2.P2scoreCount(grid);
								return true;
							}
						}
					}
				}

				//If end position is not null, eat, boost, or pull!
				else
				{
					if ( this.currentPlayer == 1)
					{
						if ( this.grid[i].getObjectName().equals(p1name))
						{
							//eat scenario
							if (this.grid[i+diceNum].getObjectName().equals(p2name))
							{
								p1.p1Eatp2(grid,i, diceNum);
								p1.P1scoreCount(grid);
								return true;
							}
							//boost scenario
							if (this.grid[i+diceNum].getObjectName().equals("@"))
							{
								s.hitStream(grid,i,move,diceNum);
								p1.P1scoreCount(grid);
								return true;
							}
							//pull scenario
							if (this.grid[i+diceNum].getObjectName().equals("#"))
							{
								t.hitTrap(grid,i,move,diceNum);
								p1.P1scoreCount(grid);
								return true;
							}
						}
					}
					else if (this.currentPlayer == 2)
					{
						if (this.grid[i].getObjectName().equals(p2name))
						{
							if (this.grid[i+diceNum].getObjectName().equals(p1name))
							{
								p2.p2Eatp1(grid,i, diceNum);
								p2.P2scoreCount(grid);
								return true;
							}
							if (this.grid[i+diceNum].getObjectName().equals("@"))
							{
								s.hitStream(grid,i, move,diceNum);
								p2.P2scoreCount(grid);
								return true;
							}
							if (this.grid[i+diceNum].getObjectName().equals("#"))
							{
								t.hitTrap(grid,i,move, diceNum);
								p2.P2scoreCount(grid);
								return true;
							}

						}
					}
				}
			}
		}
		return false;
	}

	//Inserts the scores and player's names into the TopScore.txt file
	public void appendScores() throws IOException {
		/* Objects used
			'writer' object opens the file in append mode
		*/
		File leaderboard = new File("TopScore.txt");
		//Create the file to store the scores if the file does not exist
		if (!leaderboard.exists()){
			leaderboard.createNewFile();
		}
		Scanner fileRead = new Scanner(new File("TopScore.txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(leaderboard, true));

		//Variables used
		String content;
		String separator = ",";

		//'content' contains data to store in TopScore.txt: player name and score
		if (currentPlayer == 1){
			content = (p1.getPlayerName() + separator + p1.P1scoreCount(grid));
		}
		else{
			content = (p2.getPlayerName() + separator + p2.P2scoreCount(grid));
		}

		try{
			if (!fileRead.hasNextLine()){
				writer.write(content);
				writer.close();
			}
			else{
				writer.newLine();
				writer.write(content);
				writer.close();
			}
		}
		catch (IOException e){
			System.out.println("Error! Could not add score!");
		}
	}

//====================================================================================================================//
	//Setter getter methods
	//Return the SeaObject Array
	public SeaObject[] getGrid()
	{
		return this.grid;
	}

	//Return the current player, represented by a number value
	public int getCurrentPlayer() 
	{
		return this.currentPlayer;
	}

	//Switch Player between 1 and 2 each turn
	public void setSwitchPlayer() 
	{
		if (!hasWon())
		{
			if (getCurrentPlayer() == 1)
			 {
				this.currentPlayer = 2;
				System.out.println("Player 2 Turn");
			 }
			else if (getCurrentPlayer() == 2)
			{
				this.currentPlayer = 1;
				System.out.println("Player 1 Turn");
			}
		}
	}
}




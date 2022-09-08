public class Player extends SeaObject
{
	private int P1count = 1;
	private int P2count = 1;

	public Player(String playerName)
	{
		this.setObjectName(playerName);
	}

//--------------------------------------------------------------------------------------------------------------------//
	//Methods

	//If player 1 lands on player 2, player 2 goes back to the start
	public void p1Eatp2(SeaObject[] grid, int i, int diceNum)
	{
		if (grid[0] == null)
		{
			grid[0] = grid[i+diceNum];
			grid[i+diceNum] = grid[i];
			grid[i] = null;
		}
		else
		{
			grid[199] = grid[i]; //Placeholder is used so p1 is not lost when p1 eats p2 at start position
			grid[i] = grid[i+diceNum];
			grid[i+diceNum] = grid[199];
			grid[199] = null;
		}
		System.out.println("Player 1 destroyed Player 2. Returning back to starting position.");
	}

	//If player 2 lands on player 1, player 1 goes back to the start
	public void p2Eatp1(SeaObject[] grid, int i, int diceNum)
	{
		if (grid[0] == null)
		{
			grid[0] = grid[i+diceNum];
			grid[i+diceNum] = grid[i];
			grid[i] = null;
		}
		else
		{
			grid[199] = grid[i];
			grid[i] = grid[i+diceNum];
			grid[i+diceNum] = grid[199];
			grid[199] = null;
		}
		System.out.println("Player 2 destroyed Player 1. Returning back to starting position.");
	}

	//moves players back if boat moves beyond boundaries
	public void overFlow(SeaObject[] grid, int i, int diceNum)
	{
		if (grid[i] != grid[200-i-diceNum])
		{
			grid[200-i-diceNum] = grid[i] ;
			System.out.println("Back to position: " + (200-i-diceNum) );
			grid[i] = null;
		}
		else
		{
			grid[200-i-diceNum] = grid[i] ;
			System.out.println("Back to position: " + (200-i-diceNum));
		}
	}

	//moves boat regularly if new position is null
	public void boatMove(SeaObject[] grid,int i, int diceNum)
	{
		grid[i+diceNum] = grid[i];
		grid[i] = null;
	}

	//Count player 1 score
	public int P1scoreCount(SeaObject[] grid)
	{
		if (grid[100] == null)
		{
			System.out.println("Step taken: " + P1count);
			return ++P1count;
		}
		else
		{
			System.out.println("Step taken: " + P1count);
			return P1count;
		}
	}

	//Count player 2 score
	public int P2scoreCount(SeaObject[] grid)
	{
		if (grid[100] == null)
		{
			System.out.println("Step taken: " + P2count);
			return ++P2count;
		}
		else
		{
			System.out.println("Step taken: " + P2count);
			return P2count;
		}
	}

//--------------------------------------------------------------------------------------------------------------------//
	//Getter methods

	//Returns the player's name if the object is a player
	public String getPlayerName() 
	{
		return this.getObjectName();
	}
	
	//returns player 1's current position
	public int getPlayer1Pos(SeaObject[]grid, int currentPlayer, String p1name)
	{
		int pos = 0;
		for(int i=0;i< grid.length;i++)
		{
			if (grid[100] == null)
			{
				if(grid[i] != null )
				{            	
					if (currentPlayer == 1) 
					{
						if(grid[i].getObjectName().equals(p1name))
						{
	            			pos = i;
	            			System.out.println("Position P1: " + (pos));
	            			break;
						}
					}
				}
			}
		}
		return pos;
	}
	
	//returns player 2's current position
	public int getPlayer2Pos(SeaObject[]grid, int currentPlayer, String p2name)
	{
		int pos = 0;
		for(int i=0;i < grid.length;i++)
		{
			if (grid[100] == null)
			{
	            if(grid[i] != null )
	            {
	            	if (currentPlayer == 2) 
	            	{
	            		if(grid[i].getObjectName().equals(p2name))
	            		{
	            			pos = i;
	            			System.out.println("Position P2: " + (pos));
	            			break;
	            		}
	            	}
	            }
	         }
		}
		return pos;
	}
}
	

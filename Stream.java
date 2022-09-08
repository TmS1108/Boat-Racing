import java.util.Random;

public class Stream extends SeaObject
{
	public Stream()
	{
		super();
	}

	public Stream (String n) 
	{
		this.setObjectName(n);//S
	}

	//If hit stream, boost to new position
	public void hitStream(SeaObject[] grid,int i,int move, int diceNum)
	{
		grid[i+diceNum+ move] = grid[i];
		System.out.println("Boost: " + move);
		grid[i] = null;

	}

//--------------------------------------------------------------------------------------------------------------------//

	//Setter getter methods

	//set stream range from 1 to 89
	public void setStream(SeaObject[]grid)
	{
		for ( int i = 0; i < 7 ; i++)
		{
			Random rng = new Random();
			int random = 1 + rng.nextInt(89);
			grid[random] = new Stream("@");
		}
	}
}

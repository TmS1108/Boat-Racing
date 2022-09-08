import java.util.Random;

public class Trap extends SeaObject
{
	public Trap()
	{
		super();
	}

	public Trap(String n)
	{
		this.setObjectName(n);
	}

	//If hit trap, pull to new position
	public void hitTrap(SeaObject[]grid, int i,int move, int diceNum)
	{
		grid[i+diceNum-move] = grid[i];
		System.out.println("Pull: " + move);
		if(diceNum != move)
		{
			grid[i] = null;//Set initial to null if new position is not the same as initial
		}
	}

//--------------------------------------------------------------------------------------------------------------------//

	//Setter getter methods

	//set trap range from 11 to 97
	public void setTrap(SeaObject[] grid)
	{
		for ( int i = 0; i < 7 ; i++)
		{
			Random rng = new Random();
			int random = 11 + rng.nextInt((97-11)+1);
			grid[random] = new Trap("#");
		}
	}
}

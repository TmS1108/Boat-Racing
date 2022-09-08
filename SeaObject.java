import java.util.Random;

public class SeaObject 
{
	private int specialMove;	//Holds the effect of the object
	private String name;		//Holds the name of the object
	
	//Default constructor
	public SeaObject()
	{
	}

//--------------------------------------------------------------------------------------------------------------------//

	//Setter getter methods
	public void setObjectName(String n)
	{
		this.name = n;
	}
	
	public String getObjectName()
	{
		return this.name;
	}

	public int getSpecialMove()
	{  
		return this.specialMove;
	}
	
	//Set number of steps trap/stream moves 
	public void setSpecialMove()
	{
		Random rng = new Random();
        this.specialMove = 1 +  rng.nextInt(10);
	}
}

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DisplayGame
{
	//Default constructor
	public DisplayGame()
	{
	
	}

	//Displays the main menu of the game
	public void mainMenu()
	{
		System.out.println("=============================");
		System.out.println("          Main Menu          ");
		System.out.println("=============================");
		System.out.print("1. Play game\n2. Leaderboard\n3. Exit program");
	}

	//Displays thank you message
	public void thankYou()
	{
		System.out.println("=========================================");
		System.out.println("     Thank you for playing the game!     ");
		System.out.println("=========================================");
	}

	//Displays the game rules
	public void gameRules()
	{
		System.out.println("==================");
		System.out.println("     Welcome!     ");
		System.out.println("==================");
		System.out.println("# = trap/pull\n@ = stream/boost");
	}

	//Displays the board of the game
	public void displayGrid(SeaObject[]grid, String p1name, String p2name)
	{
		System.out.print("|");
		for (int i = 0; i < 101 ; i ++){
			if (grid[i] == null)
			{
				if (i%10 == 0 && i > 0 && i < 100)
				{
					System.out.printf("%7d      |",i);
					System.out.print("\n|");
				}
				else if (grid[0] == null && i < 1)
				{
					System.out.printf("%7d      |\n|",i);
				}
				else
				{
					System.out.printf("%7d      |",i);
				}
			}

			//prints out the players', traps, and streams positions
			else
			{
				//System.out.print(grid[i].getObjectName() + "\t|");
				if (grid[i].getObjectName().equals("@"))
				{
					System.out.printf("%7s      |", grid[i].getObjectName());
					if (i%10 == 0 && i>0)
					{
						System.out.print("\n|");
					}
				}

				if  (grid[i].getObjectName().equals("#"))
				{
					System.out.printf("%7s      |", grid[i].getObjectName());
					if (i%10 == 0 && i>0)
					{
						System.out.print("\n|");
					}
				}

				if (grid[i].getObjectName().equals(p1name))
				{
					if (grid[0] != null && grid[0].getObjectName().equals(p1name))
					{
						System.out.printf("%-12s |\n|", grid[i].getObjectName());
					}

					else
					{
						System.out.printf("%-12s |", grid[i].getObjectName());
						if (i%10 == 0 && i> 0)
						{
							System.out.print("\n|");
						}
					}
				}

				if (grid[i].getObjectName().equals(p2name))
				{
					if (grid[0] != null && grid[0].getObjectName().equals(p2name))
					{
						System.out.printf("%-12s |\n|", grid[i].getObjectName());
					}

					else
					{
						System.out.printf("%-12s |", grid[i].getObjectName());
						if (i%10 == 0 && i> 0)
						{
							System.out.print("\n|");
						}
					}
				}
			}
		}
	}

	//Displays the leaderboard; only used by appendScores method to display scores after adding scores to txt file
	public void displayScores() throws IOException
	{
		File leaderboard = new File("TopScore.txt");
		//Create the file to store the scores if the file does not exist
		if (!leaderboard.exists()) {
			leaderboard.createNewFile();
		}

		Scanner fileRead = new Scanner(new File("TopScore.txt"));
		String [] data = new String[2];
		ArrayList<String> players = new ArrayList<>();
		ArrayList<Integer> scores = new ArrayList<>();
		String content, tempName;
		int tempScore;

		//Loop checks the order of the score until there is no more line of String in txt file
		while (fileRead.hasNextLine()){
			content = fileRead.nextLine();
			data = content.split(",");
			players.add(data[0]);
			scores.add(Integer.parseInt(data[1]));
		}

		//Sort the scores and corresponding players in ascending order
		for (int x = 0; x < scores.size(); x++){
			for (int y = x+1; y < scores.size(); y++){
				if (scores.get(x) > scores.get(y)){
					tempScore = scores.get(x);
					scores.set(x, scores.get(y));
					scores.set(y, tempScore);

					tempName = players.get(x);
					players.set(x, players.get(y));
					players.set(y, tempName);
				}
			}
		}

		System.out.println("===============================");
		System.out.println("          Leaderboard          ");
		System.out.println("===============================");
		//Prints the scores that were sorted in ascending order
		if (scores.size() == 0){
			System.out.println();
		}
		else{
			try{
				for (int count = 0; count < 5; count++){
					System.out.printf(" %-11s: %5s turns\n",players.get(count), scores.get(count));
				}
				System.out.println("-------------------------------");
			}
			catch (IndexOutOfBoundsException e){
				System.out.println(" ");
			}
		}
	}
}

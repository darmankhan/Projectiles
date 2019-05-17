import java.util.Random;
import java.util.Scanner;


public class CSC171_ProjectOne 
{

	public static void main(String[] args) 
	{
		//instructions for the game
		System.out.println("WELCOME TO PROJECT ONE: PROJECTILES.\n "
							+"After choosing the easy mode or hard mode"
							+ " \nEach round you will have the chance to launch a projectile at a speed and angle of your choice. \n" 
							+ "The challenge is to get the projectile over a wall with a randomly selected height at a random distance from the launch point. \n"
							+ "You will be told of the randomly selected height and distance."
							+ "But there is a trick to it: \n"
							+ "the longer you wait to enter the speed and the angle, the closer the wall gets to you until it eventually crushes you and you lose the game. \n"
							+ "You will lose a point for each launch. If you get the projectile to be more than 10 meters above the wall you will gain 3 points. \n"
							+ "If you get it closer to the height of wall and still get it over you will gain 5 points.\n"
							+ "If you don't get the projectile over the wall but were close, you will only lose only one point. \n"
							+ "If you don't make it over the wall with a long way to go, you will lose 5 points. \n"
							+ "Don't forget that the wall is coming closer and closer the longer you wait to enter the values.\n"
							+ "\nGOODLUCK! \n"
							+"\n    ");
		
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();

		double score = 0;
		int round = 1;
		
		System.out.print("For easy mode Press 0."
				+ " For hard mode press any other number. " );
		
		int gametype = sc.nextInt();
		
		//EASY MODE
		if ( gametype == 0) {
		
		
		//loop that repeats rounds
		do 
		{
			System.out.println("This is round " + round + ".");
			//random height
			int height = ran.nextInt(150) + 10;
			System.out.println("Height: " + height);
			//random distance
			int distance = ran.nextInt(150) + 20;
			System.out.println("Distance: " + distance);
			System.out.print("Enter a velocity (please enter a number below 100): ");
			//time lapse begins so as to have the effect of the 'wall moving closer' i.e distance between the launch-pad and the wall decreasing.
			double lStartTime = System.nanoTime();
			double velocity = sc.nextDouble();
			//score decreased by one point for launch
			score--;
			
			//restricting possible velocities. Anything from 0 to 100 is valid. 
			if (velocity > 100 || velocity < 0) 
			{
				while (velocity > 100 || velocity < 0) 
				{
					System.out.print("You entered an invalid value. Please enter valid velocity: ");
					velocity = sc.nextDouble();
					if (velocity > 100 || velocity < 0 ) 	
					{
						continue;
					}
			
				}
				
			}
			
			System.out.print("Enter angle in degrees: ");
			double angle = sc.nextDouble();
			
	        
			System.out.println("1 point is deducted for launching.");
			//time lapse ends
			double lEndTime = System.nanoTime();
			//evaluate a new distance based on the time lapse
			double newdistance = distance - (0.0000000005*(lEndTime - lStartTime));
			
			double x = newdistance;
			//convert Radians to degrees
			double radians = angle *(Math.PI/180);
			//formula to calculate the height reached by the projectile neglecting drag
			double y = x * (Math.tan(radians)) - (9.81 * (x * x)) / (2 * ((velocity * Math.cos(radians)) * (velocity * Math.cos(radians))));
			//double m = 0.0032;
			//double b = 0.00144;
			
			
			//double y1 = (((m * 9.81 * (1/Math.cos(radians)))/(b*velocity) + Math.tan(radians)) * x + (m*m*9.81)/(b*b) * (Math.log(1- ((b*(1/Math.cos(radians))*x)/(m*velocity)   )))  );
			
			//player 'dies' if the wall gets to close to them
			if (newdistance <= 0) {
				System.out.println("You took too long.");
				System.out.println("GAME OVER!");
				break;
				
			}
			
			
			
			
			
			//at least 10m above the wall. +3 score.
			else if (y > (height+10)) 
			{
				System.out.println("While you were taking your time, the wall moved. It is now at: " + newdistance);
				score= score + 3;
				System.out.println("The projectile was very far above the wall.");
				//extra information for the player on the relative position of their projectile
				System.out.println("The distance between your projectile and the wall was " + (y-height));
				
				int randomstatement1 = ran.nextInt(3)+1;
				
				if (randomstatement1 == 1) 
				{
					System.out.println("You got it over. Your current score is: " + score);
				}
				
				else if (randomstatement1 == 2) 
				{
					System.out.println("Congrats you did good. Your current score is: " + score);
				}
				
				else if (randomstatement1 == 3) 
				{
					System.out.println("You are decent at this! Your current score is: " + score);
				}
				
				System.out.println("You got it over. Your current score is: " + score);
				
		        System.out.print("Enter 0 to quit and any other number to proceed to the next round: ");
				
				int bx = sc.nextInt();
				round++;
				//quit if zero is entered
				if (bx == 0) 
				{
					System.out.println("Game terminated.");
					break;
				}
				
			}
			
			//over the wall +5 score.
			else if (y > height) 
			{
				System.out.println("While you were taking your time, the wall moved. It is now at: " + newdistance);
				score = score + 5;
				System.out.println("The projectile was over the wall, but close to it.");
				System.out.println("The distance between your projectile and the wall was " + (y-height));
		

				int randomstatement1 = ran.nextInt(3)+1;
				
				if (randomstatement1 == 1) 
				{
					System.out.println("WOW that was great! Your current score is: " + score);
				}
				
				else if (randomstatement1 == 2) 
				{
					System.out.println("Amazing! Your current score is: " + score);
				}
				
				else if (randomstatement1 == 3) 
				{
					System.out.println("You are a pro! Your current score is: " + score);
				}
				
		        
				System.out.print("Enter 0 to quit and any other number to proceed to the next round: ");
				
				int bx = sc.nextInt();
				round++;
				
				if (bx == 0) 
				{
					System.out.println("Game terminated.");
					break;
				}
				
			}
				
				
			//lost and more than 10m below the top of wall. -5 score. 
			else if (y <= (height-10)) 
			{
				System.out.println("While you were taking your time, the wall moved. It is now at: " + newdistance);
				score = score - 5;
				
int randomstatement1 = ran.nextInt(3)+1;
				
				if (randomstatement1 == 1) 
				{
					System.out.println("You were under by a lot. Your current score is: " + score);
				}
				
				else if (randomstatement1 == 2) 
				{
					System.out.println("Too bad! You lost.! Your current score is: " + score);
				}
				
				else if (randomstatement1 == 3) 
				{
					System.out.println("That was terrible. Your current score is: " + score);
				}
				
				if (y <= 0) 
				{
					System.out.println("You didn't even hit the wall. The ball is on the ground.");
				}
				
				else if (y > 0) 
				{
					System.out.println("You needed at least " + (height-y) + " more meters to go over the wall.");
				}
				
				System.out.print("Enter 0 to quit and any other number to proceed to the next round: ");
				int bx = sc.nextInt();
				round++;
				
				
				if (bx == 0) 
				{
					System.out.println("Game terminated.");
					break;
				}
			}
			
			//lost but not by much. -1 score.
			else if (y <= (height)) 
			{
				System.out.println("You took too long, the wall is now at: " + newdistance);
				score--;
				

int randomstatement1 = ran.nextInt(3)+1;
				
				if (randomstatement1 == 1) 
				{
					System.out.println("You almost made it. Your current score is: " + score);
				}
				
				else if (randomstatement1 == 2) 
				{
					System.out.println("So close! Your current score is: " + score);
				}
				
				else if (randomstatement1 == 3) 
				{
					System.out.println("It's frustrating isn't it? Your current score is: " + score);
				}
				
				if (y <= 0) 
				{
					System.out.println("You didn't even hit the wall. The ball is on the ground.");
				}
				
				else if (y > 0) 
				{
					System.out.println("You needed at least " + (height-y) + " more meters to go over the wall.");
				}
				
		
				
				System.out.print("Enter 0 to quit and any other number to proceed to the next round: ");
				int bx = sc.nextInt();
				round++;
				
				
				if (bx == 0) 
				{
					System.out.println("Game terminated.");
					break;
				}
			}
			
		}
		
		
		
		while (true);
		
		}
		
		
		//Hard Mode
		else {
			
			
			//loop that repeats rounds
			do 
			{
				System.out.println("This is round number " + round + ".");
				//random height
				int height = ran.nextInt(150) + 10;
				System.out.println("Height: " + height);
				//random distance
				int distance = ran.nextInt(100) + 20;
				System.out.println("Distance: " + distance);
				System.out.print("Enter a velocity: ");
				//time lapse begins so as to have the effect of the 'wall moving closer' i.e distance between the launch-pad and the wall decreasing.
				double lStartTime = System.nanoTime();
				double velocity = sc.nextDouble();
				//score decreased by one point for launch
				score--;
				
				System.out.print("Enter angle in degrees: ");
				double angle = sc.nextDouble();
				
		        
				System.out.println("1 point is deducted for launching.");
				//time lapse ends
				double lEndTime = System.nanoTime();
				//evaluate a new distance based on the time lapse
				double newdistance = distance - (0.0000000005*(lEndTime - lStartTime));
				
				double x = newdistance;
				//convert Radians to degrees
				double radians = angle *(Math.PI/180);
				
				double m = 0.0032;
				double b = 0.00144;
				
				double v1 = ( ( ( m * 9.81 * (1/Math.cos(radians)) ) / (b*velocity) ) + Math.tan(radians) ) * x;

				
				double v2 = ((m*m*9.81)/(b*b));
						
				double v3 = (b*(1/Math.cos(radians)) * x ) / (m * velocity ) ;
				
				
				if (v3 > 1) 
				{
					System.out.println("While you were taking your time, the wall moved. It is now at: " + newdistance);
					score = score - 5;
					
					int randomstatement1 = ran.nextInt(3)+1;
					
					
					
					if (randomstatement1 == 1) 
					{
						System.out.println("You were under by a lot. Your current score is: " + score);
					}
					
					else if (randomstatement1 == 2) 
					{
						System.out.println("Too bad! You lost.! Your current score is: " + score);
					}
					
					else if (randomstatement1 == 3) 
					{
						System.out.println("That was terrible. Your current score is: " + score);
					}
					
					System.out.print("Enter 0 to quit and any other number to proceed to the next round: ");
					int bx = sc.nextInt();
					round++;	
					
					if (bx == 0) 
					{
						System.out.println("Game terminated.");
						break;
					}
				}
				
				else 
				{
				
				double y1 = (v1) + (v2 * (Math.log(1-v3)) ) ;
				
				//player 'dies' if the wall gets to close to them
				if (newdistance <= 0) {
					System.out.println("You took to long.");
					System.out.println("GAME OVER!");
					break;
					
				}
				
				
				
				//at least 10m above the wall. +3 score.
				else if (y1 > (height+10)) 
				{
					System.out.println("While you were taking your time, the wall moved. It is now at: " + newdistance);
					score= score + 3;
					System.out.println("The projectile was very far above the wall.");
					
					int randomstatement1 = ran.nextInt(3)+1;
					
					if (randomstatement1 == 1) 
					{
						System.out.println("You got it over. Your current score is: " + score);
					}
					
					else if (randomstatement1 == 2) 
					{
						System.out.println("Congrats you did good. Your current score is: " + score);
					}
					
					else if (randomstatement1 == 3) 
					{
						System.out.println("You are decent at this! Your current score is: " + score);
					}
					
					
					
			        System.out.print("Enter 0 to quit and any other number to proceed to the next round: ");
					
					int bx = sc.nextInt();
					round++;
					//quit if zero is entered
					if (bx == 0) 
					{
						System.out.println("Game terminated.");
						break;
					}
					
				}
				
				//over the wall +5 score.
				else if (y1 > height) 
				{
					System.out.println("While you were taking your time, the wall moved. It is now at: " + newdistance);
					score = score + 5;
					System.out.println("The projectile was over the wall, but close to it.");

					int randomstatement1 = ran.nextInt(3)+1;
					
					if (randomstatement1 == 1) 
					{
						System.out.println("WOW that was great! Your current score is: " + score);
					}
					
					else if (randomstatement1 == 2) 
					{
						System.out.println("Amazing! Your current score is: " + score);
					}
					
					else if (randomstatement1 == 3) 
					{
						System.out.println("You are a pro! Your current score is: " + score);
					}
					
			        
					System.out.print("Enter 0 to quit and any other number to proceed to the next round: ");
					
					int bx = sc.nextInt();
					round++;
					
					if (bx == 0) 
					{
						System.out.println("Game terminated.");
						break;
					}
					
				}
					
					
				//lost and more than 10m below the top of wall. -5 score. 
				else if (y1 <= (height-10)) 
				{
					System.out.println("While you were taking your time, the wall moved. It is now at: " + newdistance);
					score = score - 5;
		
					int randomstatement1 = ran.nextInt(3)+1;
					
					if (randomstatement1 == 1) 
					{
						System.out.println("You were under by a lot. Your current score is: " + score);
					}
					
					else if (randomstatement1 == 2) 
					{
						System.out.println("Too bad! You lost.! Your current score is: " + score);
					}
					
					else if (randomstatement1 == 3) 
					{
						System.out.println("That was terrible. Your current score is: " + score);
					}
					
					if (y1 <= 0) 
					{
						System.out.println("You didn't even hit the wall. It is on the ground.");
					}
					
					else if (y1 > 0) 
					{
						//System.out.println("You needed at least " + (height-y1) + " more meters to go over the wall.");
					}
					
					System.out.print("Enter 0 to quit and any other number to proceed to the next round: ");
					int bx = sc.nextInt();
					round++;
					
					
					if (bx == 0) 
					{
						System.out.println("Game terminated.");
						break;
					}
				}
				
				//lost but not by much. -1 score.
				else if (y1 <= (height)) 
				{
					System.out.println("You took too long, the wall is now at: " + newdistance);
					score--;
					

	
					int randomstatement1 = ran.nextInt(3)+1;
					
					if (randomstatement1 == 1) 
					{
						System.out.println("You almost made it. Your current score is: " + score);
					}
					
					else if (randomstatement1 == 2) 
					{
						System.out.println("So close! Your current score is: " + score);
					}
					
					else if (randomstatement1 == 3) 
					{
						System.out.println("It's frustrating isn't it? Your current score is: " + score);
					}
					
					if (y1 <= 0) 
					{
						System.out.println("You didn't even hit the wall. The ball is on the ground.");
					}
					
					
			
					
					System.out.print("Enter 0 to quit and any other number to proceed to the next round: ");
					int bx = sc.nextInt();
					round++;
					
					
					if (bx == 0) 
					{
						System.out.println("Game terminated.");
						break;
					}
				}
				
			}
			
			}
			
			while (true);
			
			
		}
		
		sc.close();
	

	}

}



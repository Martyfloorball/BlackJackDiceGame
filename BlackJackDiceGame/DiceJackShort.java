gimport java.util.*; //for Scanner
public class DiceJackShort {
public static int machineLow = 17, playerSum, gameCounter = 0, winCounter = 0, machineWin = 0, winStreak = 0;
static Scanner console = new Scanner(System.in);

public static void main(String[] args){
	game();
}

public static void game(){
	boolean gameLoop = true;
	while (gameLoop) {
		if (gameCounter == 0) {
			System.out.println("Would you like to start? \ty/n");
		} else {
			statistik();
			System.out.println("Would like to play again?\ty/n");
		}
		gameCounter++;

		String gameInput = console.nextLine();
		switch (gameInput) {
		case "y":
			player();
			if (playerSum < 21 && playerSum != 21) {
				compare();
			}
			break;

		default:
			System.out.println("Please come again");
			gameLoop = false;
			break;
		}
	}
}

public static void player(){
	boolean playerLoop = true;
	playerSum = diceRoll();
	System.out.println("You startet out with: " + playerSum + "\n");
	while (playerLoop) {
		if (playerSum < 21) {
			System.out.println("Would you like to throw the dices again? y/n");
		}
		if (playerSum > 21) {
			System.out.println("You lost by exeeding 21");
			machineWin++;
			winStreak = 0;
			break;
		}
		if (playerSum == 21) {
			System.out.println("Blackjack you win!");
			winCounter++;
			winStreak++;
			break;
		}

		String gameInput = console.nextLine();
		switch (gameInput) {
		case "y":
			playerSum += diceRoll();
			System.out.println("You now got: " + playerSum);
			break;

		default:
			playerLoop = false;
			break;
		}
	}
}
public static int Machine(){
	int machineSum = diceRoll();
	while (machineSum <= machineLow) {
		machineSum += diceRoll();
	}
	return machineSum;
}
public static void compare(){
	int machineSum = Machine();

	if (machineSum > 21 || (machineSum < playerSum && machineSum >= machineLow)) {
		System.out.printf("You got: %d\nThe machine got: %d, you win!\n", playerSum, machineSum);
		winCounter++;
		winStreak++;
	}else if (machineSum > playerSum) {
		System.out.printf("The machine got: %d\nYou got: %d, the machine wins.\n", machineSum, playerSum);
		machineWin++;
		winStreak = 0;
	}else if (playerSum == machineSum) {
		System.out.printf("It's a draw, \nYou got: %d \nMachine got: %d \n", playerSum, machineSum);
	}
}

public static int diceRoll(){
	Random rand = new Random();
	int dice1 = rand.nextInt(6) + 1;
	int dice2 = rand.nextInt(6) + 1;
	return dice1 + dice2;
}
public static void statistik(){
	System.out.printf("\nYou have played:\t%d times\nGames won:\t%d \nGames lost:\t%d \n", gameCounter, winCounter, machineWin);
	System.out.printf("Your win streak is: %d", winStreak);
	System.out.printf("\nPercentage of games won:\t%.2f %%\n\n", 100.0 * winCounter / gameCounter);
}
}

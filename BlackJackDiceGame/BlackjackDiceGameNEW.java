import java.util.*; //for Scanner

public class BlackjackDiceGameNEW {
public static int playerSum, machineSum, gameCounter = 0, winCounter = 0, machineWin = 0, winStreak = 0;
static Scanner console = new Scanner(System.in);
static String play;
static boolean stopLoop = true;


public static void main(String[] args){
	user();
}

public static void user() {
	if (gameCounter == 0) {
		System.out.println("Would you like to start? y/n");
	} else {
		statistik();
		System.out.println("Would like to play again?\ty/n");
	}

	String play = console.nextLine();
	switch (play) {
	case "y":
   	gameCounter++;
		throwAgain();
		break;

	default:
		System.out.println("... goodbye ...");
		stopLoop = false;
		break;
	}
}

public static void throwAgain(){
	playerSum = diceRoll();
	System.out.println("You startet out with: " + playerSum + "\n");
	System.out.print("Would you like to throw the dices again? y/n\n");

	while (stopLoop) {
		play = console.nextLine();
		switch (play) {
		case "y":
			playerSum += diceRoll();
			System.out.print("you now got: " + playerSum + "\n");

			if (playerSum < 21) {
				System.out.print("\nWould you like to throw the dices again? y/n\n");
			}

			if (playerSum == 21) {
				System.out.println("Blackjack!\n");
				winCounter++;
				winStreak++;
            
				user();
			}
			if (playerSum > 21) {
				System.out.println("You exeeded 21.\nYou lose...\n");
				machineWin++;
				winStreak = 0;
				user();
			}
			break;

		default:
			machineThrow();
			user();
			break;
		}
	}
}
public static void machineThrow(){
	int machineSum = diceRoll();
	int machineLow = 17;

	do {
		machineSum += diceRoll();
		if (machineSum >= 17) {
			if (machineSum > 21 || (machineSum < playerSum && machineSum >= machineLow)) {
				System.out.printf("You got: %d\nThe machine got: %d, you win!\n", playerSum, machineSum);
				winCounter++;
				winStreak++;
				break;
			}else if (machineSum > playerSum) {
				System.out.printf("The machine got: %d\nYou got: %d, the machine wins.\n", machineSum, playerSum);
				machineWin++;
				winStreak = 0;
				break;
			}else if (playerSum == machineSum) {
				System.out.printf("It's a draw, \nYou got: %d \nMachine got: %d \n", playerSum, machineSum);
				break;
			}
		}
	} while (machineSum < machineLow);
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

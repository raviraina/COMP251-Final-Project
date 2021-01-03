import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Name:	Ravi Raina
 * ID:		260828720
 */

public class US_elections {

	public static int solution(int num_states, int[] delegates, int[] votes_Biden, int[] votes_Trump, int[] votes_Undecided) {

		// init
		int trump_delegates = 0;
		int biden_delegates = 0;
		int tot_delegates = 0;

		for (int n : delegates) {
			tot_delegates += n;
		}
		int delegates_to_win = (tot_delegates / 2) + 1;
		int[] pot_biden_needed_votes = new int[num_states];
		int ctr = 0;
		int b_win = 0;

		// check for biden/trump outright win, assign needed votes for biden win from undecided
		for (int i = 0; i < num_states; i++) {
			if (votes_Biden[i] + votes_Undecided[i] <= votes_Trump[i]) {
				trump_delegates += delegates[i];
				pot_biden_needed_votes[i] = -1;
				ctr++;

			} else if (votes_Biden[i] > votes_Trump[i] + votes_Undecided[i]) {
				biden_delegates += delegates[i];
				pot_biden_needed_votes[i] = 0;
				ctr++;
				b_win += delegates[i];
			} else {
				pot_biden_needed_votes[i] = ((votes_Biden[i] + votes_Trump[i] + votes_Undecided[i]) / 2) + 1 - votes_Biden[i];
			}
		}

		// return respective cases where trump/biden wins outright
		if (trump_delegates >= delegates_to_win) {
			return -1;
		} else if (biden_delegates >= delegates_to_win) {
			return 0;
		}

		// init "knapsack"
		double[][] M = new double[num_states - ctr][delegates_to_win - b_win + 1];
		int n = 0;

		// loop through and run dp
		for (int i = 0; i < num_states; i++) {
			if (pot_biden_needed_votes[i] == -1 || pot_biden_needed_votes[i] == 0) {
				continue;
			}
			for (int j = 0; j < delegates_to_win - b_win + 1; j++) {

				// initialization row (row 0)
				if (n == 0 && (j - delegates[i]) <= 0) {
					M[n][j] = pot_biden_needed_votes[i];
					continue;
				} else if (n == 0) {
					// if invalid fill with inf
					M[n][j] = Double.POSITIVE_INFINITY;
					continue;
				}

				// decide votes to assign to knapsack
				if (n > 0 && j - delegates[i] >= 0) {
					M[n][j] = Math.min(M[n - 1][j], pot_biden_needed_votes[i] + M[n - 1][j - delegates[i]]);
				} else {
					M[n][j] = Math.min(M[n - 1][j], pot_biden_needed_votes[i]);
				}
			}

			n++;
		}

		return (int) M[num_states - ctr - 1][delegates_to_win - b_win];
	}


	public static void main(String[] args) {
		try {
			String path = args[0];
			File myFile = new File(path);
			Scanner sc = new Scanner(myFile);
			int num_states = sc.nextInt();
			int[] delegates = new int[num_states];
			int[] votes_Biden = new int[num_states];
			int[] votes_Trump = new int[num_states];
			int[] votes_Undecided = new int[num_states];

			for (int state = 0; state < num_states; state++) {
				delegates[state] = sc.nextInt();
				votes_Biden[state] = sc.nextInt();
				votes_Trump[state] = sc.nextInt();
				votes_Undecided[state] = sc.nextInt();
			}

			sc.close();
			int answer = solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
			System.out.println(answer);

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}


	}

}
package gofish;

import gofish.controllers.GameController;
import gofish.controllers.GoFishController;
import gofish.controllers.Rule;
import gofish.models.Game;
import gofish.models.Player;
import gofish.views.Frame;
import gofish.views.MainMenu;

public class App {

	public static boolean verbose = true;

	// Define rules
	public static final Rule[] RULES = new Rule[]{
			new Rule("maxPlayers", 10, (Game game, int value) -> game.getPlayers().size() <= value ),
			new Rule("minPlayers", 2, (Game game, int value) -> game.getPlayers().size() >= value),
			new Rule("maxHandSize", 52, (Game game, int value) -> {
				for (Player p : game.getPlayers()) {
					if (p.getHand().getCardCount() > value) {
						return false;
					}
				}
				return true;
			}),
			new Rule("dealCardCount", (Game game) -> {
				int nPlayers = game.getPlayers().size();
				if (nPlayers <= 3) {
					return 7;
				} else {
					return 5;
				}
			})
	};

	public static void main(String[] args) {
		// Set verbose based on input
		for (String arg : args)
			if (arg.toLowerCase().equals("-v"))
				verbose = true;

		log("Launching");

		// Initialize GameController w/ frame
		GameController gc = new GoFishController("Go Fish", RULES);
		Frame frame = new Frame("Go Fish", 500, 629, 0xFF0000);
		frame.setLocationRelativeTo(null);
		gc.setFrame(frame);
		gc.loadPanel(new MainMenu(gc).getView());


	}

	/**
	 * Outputs the given string to console IF verbose output is requested.
	 * @param s The String to output to console.
	 */
	public static void log(String s) {
		if (verbose) {
			System.out.println(s);
		}
	}
}

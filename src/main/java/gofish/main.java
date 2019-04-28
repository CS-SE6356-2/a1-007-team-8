package gofish;

import gofish.views.*;

public class main {
    public static boolean verbose = false;

    public static void main(String args[]) {
        // Set verbose based on input
        for (String arg : args)
            if (arg.toLowerCase().equals("-v"))
                verbose = true;

        log("Launching with frame");

        // Create the frame
        Frame frame = createFrame();
    }

    public static Frame createFrame() {
        Frame frame = new Frame("Go Fish", 500, 500, 0);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
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

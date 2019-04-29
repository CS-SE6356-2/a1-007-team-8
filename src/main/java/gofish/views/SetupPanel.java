package gofish.views;

import gofish.App;
import gofish.controllers.GameController;
import gofish.controllers.GoFishController;
import gofish.models.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SetupPanel {
    private JPanel setupView;
    private JLabel title;
    private JButton mainMenuBtn;
    private JButton playBtn;
    private JTextArea playerNames;
    private JLabel instructions;

    int MAXP, MINP;

    public SetupPanel(GoFishController gc) {
        // Get allowed max/min players
        MAXP = gc.getRuleValue("maxPlayers");
        MINP = gc.getRuleValue("minPlayers");
        // Setup instructions
        instructions.setText("Enter one player name per line. (Minimum " + MINP + ", Maximum " + MAXP + ")");

        // Listeners
        mainMenuBtn.addActionListener(new EventListener((ActionEvent event) -> {
            gc.loadPanel(new MainMenu(gc).getView());
        }));

        playBtn.addActionListener(new EventListener((ActionEvent event) -> {
            // Check that player count is valid
            int numLines = countLines(playerNames.getText());
            if (numLines < MINP || numLines > MAXP)
                new InvalidPlayerCount(gc, numLines);
            else {
                // Reset the game before creating a new one
                gc.reset();
                // Add new players
                for (String name : playerNames.getText().split("\\n")) {
                    name = name.strip();
                    if (!name.isEmpty()) {
                        if (!gc.addPlayer(new Player(name)))
                            ; //Error has occurred
                    }
                }
                // Log names to console
                if (App.verbose) {
                    App.log("Player names:");
                    for (Player p : gc.getPlayers())
                        App.log("*" + p.getName() + "*");
                }
                // Load the play screen
                gc.advanceTurn();
                gc.deal();
                gc.loadPanel(new PlayScreen(gc).getView());
            }
        }));
    }

    private int countLines(String str) {
        int numLines = 0;
        for (String line : playerNames.getText().split("\\n")) {
            line = line.strip();
            if (!line.isEmpty())
                numLines++;
        }
        return numLines;
    }

    public JPanel getView() {
        return setupView;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        setupView = new JPanel();
        setupView.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        title = new JLabel();
        Font titleFont = this.$$$getFont$$$(null, -1, 20, title.getFont());
        if (titleFont != null) title.setFont(titleFont);
        title.setText("Setup");
        setupView.add(title, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mainMenuBtn = new JButton();
        mainMenuBtn.setText("Back");
        setupView.add(mainMenuBtn, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        playBtn = new JButton();
        playBtn.setText("Play");
        setupView.add(playBtn, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        playerNames = new JTextArea();
        playerNames.setRows(15);
        setupView.add(playerNames, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        instructions = new JLabel();
        instructions.setText("Enter one player name per line.");
        setupView.add(instructions, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return setupView;
    }

}

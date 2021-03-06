package gofish.views;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import gofish.controllers.GameController;
import gofish.controllers.GoFishController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RulesPanel {
    private JPanel rulesView;
    private JLabel title;
    private JTextPane rulesTextPane;
    private JButton backBtn;

    public RulesPanel(GoFishController gc) {
        backBtn.addActionListener(new EventListener((ActionEvent event) -> {
            gc.loadPanel(new MainMenu(gc).getView());
        }));
    }

    public JPanel getView() {
        return rulesView;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
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
        rulesView = new JPanel();
        rulesView.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        title = new JLabel();
        Font titleFont = this.$$$getFont$$$(null, -1, 20, title.getFont());
        if (titleFont != null) title.setFont(titleFont);
        title.setText("Rules");
        rulesView.add(title, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rulesTextPane = new JTextPane();
        rulesTextPane.setBackground(new Color(-1118482));
        rulesTextPane.setEditable(false);
        rulesTextPane.setText("SETUP\n\nFive cards are dealt from a standard 52-card deck to each player, or seven cards if there are three or fewer players. The remaining cards form the draw deck in the middle of the play area called \"ocean\".\n\nPLAY\n\nOn their turn, a player requests cards by face value, called \"rank\", from another player. The requesting player may only request a particular rank if they already have at least one matching card in their hand. The player from which the cards were requested must hand over all matching cards in their hand to the requesting player. If the requestee has no matching cards in their hand they tell the requester to \"Go Fish\", after which the requester must draw a card from the ocean and place it in their hand. If the requester receives one or more cards of their requested rank, either from the requestee or after randomly drawing it from the ocean, the requester shows the cards to the other players and repeats their turn. A turn ends and the play transfers to the next player when no cards matching the requested rank are received by the requester.\n\nSCORING\n\nWhen any player at any time has all four cards of one face value, forming a \"book\", the cards must be placed face up in front of that player. Each book is one point.\n\nWINNING\n\nWhen all sets of cards have been laid down in books, the game ends. The player with the most books wins.");
        rulesView.add(rulesTextPane, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        backBtn = new JButton();
        backBtn.setText("Back to Main Menu");
        rulesView.add(backBtn, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return rulesView;
    }

}

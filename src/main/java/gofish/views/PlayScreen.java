package gofish.views;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import gofish.App;
import gofish.controllers.GoFishController;
import gofish.models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;


public class PlayScreen {
    private JPanel playView;
    private JButton mainMenuBtn;
    private JPanel opponentPanel;
    private JPanel playerPanel;
    private JPanel infoPanel;
    private JLabel requestPlayerLabel;
    private JLabel requestCardLabel;
    private JButton requestBtn;
    private JLabel matchLabel;
    private JLabel activePlayerLabel;

    //ArrayList<JButton> opponentBtns = new ArrayList<>();
    // Player/Card to request (only card rank matters)
    Player requestPlayer = null;
    Card requestCard = null;
    GoFishController gc;
    boolean requestedCards = false;
    boolean gofish = false;
    boolean foundMatch = false;

    public PlayScreen(GoFishController gc) {
        this.gc = gc;
        $$$setupUI$$$();

        for (Player p : gc.getPlayers()) {
            App.log(p.getName() + ":");
            for (Card c : p.getHand().getPrivateCards()) {
                App.log("\t" + c);
            }
            App.log("");
        }
        requestPlayerLabel.setText("Request cards from: ");
        requestCardLabel.setText("Request card rank: ");
        activePlayerLabel.setText("Current Player: " + gc.getActivePlayer().getName());

        /* ----- Listeners ----- */
        mainMenuBtn.addActionListener(new EventListener((ActionEvent event) -> {
            gc.loadPanel(new WinScreen(gc).getView());
        }));
        requestBtn.addActionListener(new EventListener((ActionEvent event) -> {
            // If a card has not been requested before
            if (!requestedCards && requestCard != null && requestPlayer != null) {
                requestedCards = true;
                ArrayList<Card> matchedCards = gc.requestCard(gc.getActivePlayer(), requestPlayer, requestCard);
                if (matchedCards.size() > 0) {
                    matchLabel.setText("Match found!");
                    requestBtn.setText("Go Again");
                    for (Card c : matchedCards) {
                        addPlayerCard(c);
                    }
                    gofish = false;
                    foundMatch = true;
                } else {
                    matchLabel.setText("No match.");
                    requestBtn.setText("Go Fish!");
                    gofish = true;
                    foundMatch = false;
                }
            } else { // A care has been requested before --> now a go fish or next turn button
                if (gofish) {
                    // A card has been requested and it was not a match
                    gofish = false;
                    Card dealtCard = gc.dealSingleCard(gc.getActivePlayer());
                    addPlayerCard(dealtCard);
                    if (dealtCard.getRank().equals(requestCard.getRank())) {
                        // Received a matching card
                        matchLabel.setText("Drew a matching card!" + dealtCard.getRank() + " of " + dealtCard.getSuit() + ".");
                        requestBtn.setText("Go Again");
                        foundMatch = true;
                    } else {
                        matchLabel.setText("Drew a " + dealtCard.getRank() + " of " + dealtCard.getSuit() + ".");
                        requestBtn.setText("End Turn");
                        foundMatch = false;
                    }
                } else if (foundMatch) {
                    // A matched card has been found --> restart turn
                    gc.loadPanel(new PlayScreen(gc).getView());
                } else {
                    // A matched card was not found --> next player
                    gc.advanceTurn();
                    if(gc.checkWinner() != null) {
                        gc.loadPanel(new WinScreen(gc).getView());
                    } else {
                        gc.loadPanel(new PlayScreen(gc).getView());
                    }
                }
                //gc.loadPanel(new PlayScreen(gc).getView());
            }
            gc.getFrame().pack();
        }));
    }

    public JPanel getView() {
        return playView;
    }

    private void createUIComponents() {
        // Place custom component creation code here

        /* ----- Setup Opponent Panel ----- */
        opponentPanel = new JPanel();
        playerPanel = new JPanel();
        Player activePlayer = gc.getActivePlayer();
        requestPlayerLabel = new JLabel();

        //Populate opponent panel
        for (Player p : gc.getPlayers()) {
            if (p.getId() != activePlayer.getId()) {
                // Create a button for each opponent
                JPanel playerInfo = new JPanel();
                playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.PAGE_AXIS));
                JButton btn = new JButton(p.getName());
                btn.addActionListener(new EventListener((ActionEvent event) -> {
                    // Selects the opponent to request a card from
                    requestPlayer = p;
                    requestPlayerLabel.setText("Request cards from: " + p.getName());
                    this.enableRequest();
                }));
                playerInfo.add(btn);

                playerInfo.add(new JLabel("(" + p.getHand().getPrivateCards().size() + " cards)"));
                playerInfo.add(new JLabel("score: " + p.getHand().getPublicCards().size()));
                opponentPanel.add(playerInfo);
            } else {
                JPanel cardWrapper = new JPanel();
                cardWrapper.setLayout(new GridLayout(3, 21));
                for (Card c : p.getHand().getPrivateCards()) {
                    JButton card = new JButton(new ImageIcon(c.getImage(true)));
                    card.setBorder(BorderFactory.createEmptyBorder());
                    card.setContentAreaFilled(false);
                    card.addActionListener(new EventListener((ActionEvent e) -> {
                        requestCard = c;
                        requestCardLabel.setText("Request card rank: " + c.getRank());
                        App.log(requestCard.getRank());
                        requestCardLabel.setText("Request card rank: " + c.getRank());
                        this.enableRequest();
                    }));
                    cardWrapper.add(card);
                }
                //cardWrapper.setPreferredSize(new Dimension(500, -1));

                playerPanel.add(cardWrapper);
            }
        }
    }

    private void enableRequest() {
        if (requestCard != null && requestPlayer != null) {
            requestBtn.setEnabled(true);
        }
    }

    private void addPlayerCard(Card c) {
        JButton card = new JButton(new ImageIcon(c.getImage(true)));
        card.setBorder(BorderFactory.createEmptyBorder());
        card.setContentAreaFilled(false);
        ((JPanel)(playerPanel.getComponents()[0])).add(card);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        playView = new JPanel();
        playView.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainMenuBtn = new JButton();
        mainMenuBtn.setText("Return to Main Menu");
        playView.add(mainMenuBtn, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        playView.add(opponentPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        playView.add(playerPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(500, -1), null, 0, false));
        infoPanel = new JPanel();
        infoPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        playView.add(infoPanel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        requestPlayerLabel = new JLabel();
        requestPlayerLabel.setText("Label");
        infoPanel.add(requestPlayerLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        requestCardLabel = new JLabel();
        requestCardLabel.setText("Label");
        infoPanel.add(requestCardLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        matchLabel = new JLabel();
        matchLabel.setText("");
        infoPanel.add(matchLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        requestBtn = new JButton();
        requestBtn.setEnabled(false);
        requestBtn.setHorizontalAlignment(0);
        requestBtn.setHorizontalTextPosition(0);
        requestBtn.setText("Request");
        infoPanel.add(requestBtn, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 50), null, 0, false));
        activePlayerLabel = new JLabel();
        activePlayerLabel.setText("Label");
        infoPanel.add(activePlayerLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return playView;
    }

}

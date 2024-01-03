
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.Serializable;

import mancala.PitNotFoundException;
import mancala.InvalidMoveException;
import mancala.GameNotOverException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mancala.MancalaGame;
import mancala.Player;
import mancala.Saver;
import mancala.UserProfile;

public class MancalaBoardPage {

    private PositionAwareButton buttons[][];
    private MancalaGame game = new MancalaGame();

    private Player playerOne;
    private Player playerTwo;

    private Player currentPlayer;
    private JLabel currentPlayerLabel;

    private MyFrame gameFrame;

    private MancalaPlayerProfile playerProfileOne;
    private MancalaPlayerProfile playerProfileTwo;

    private String gameMode;

    private JPanel createButtonGrid(int rows, int columns) {
        JPanel panel = new JPanel();
        buttons = new PositionAwareButton[rows][columns];

        panel.setLayout(new GridLayout(columns, rows));
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                buttons[y][x] = new PositionAwareButton();
                buttons[y][x].setAcross(x + 1);
                buttons[y][x].setDown(y + 1);
                panel.add(buttons[y][x]);
            }
        }

        return panel;
    }

    private void disableInvalidButtons() {
        if (currentPlayer.equals(playerOne)) {
            for (int i = 1; i < 7; i++) {
                buttons[i][0].setEnabled(false);
                buttons[i][2].setEnabled(true);
            }
        } else if (currentPlayer.equals(playerTwo)) {
            for (int i = 1; i < 7; i++) {
                buttons[i][0].setEnabled(true);
                buttons[i][2].setEnabled(false);
            }
        }
    }

    private void turnOffButton(PositionAwareButton button) {
        button.setBackground(Color.LIGHT_GRAY);
        button.setEnabled(false);
        button.setBorderPainted(false);
    }

    private void performAction(int start) {
        try {
            if (game.stonesInPit(start) == 0) {
            JOptionPane.showMessageDialog(null, "Pit is empty. Please choose a different pit.", "Empty Pit",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            game.move(start);
            currentPlayer = game.getCurrentPlayer();
            renderValues();
            disableInvalidButtons();
            currentPlayerLabel.setText("Current Player: " + currentPlayer.getName());

            if (game.isGameOver()) {
                handleGameOver();
            }
        }
        } catch (InvalidMoveException f) {
            f.printStackTrace();
        }
    }

    private void setButtonValues() {
        int value = 12;
        for (int i = 1; i < 7; i++) {
            buttons[i][2].putClientProperty("Pit", i);
            buttons[i][0].putClientProperty("Pit", value);
            value--;
            buttons[i][2]
                    .addActionListener(e -> performAction((int) ((JButton) e.getSource()).getClientProperty("Pit")));
            buttons[i][0]
                    .addActionListener(e -> performAction((int) ((JButton) e.getSource()).getClientProperty("Pit")));
        }
    }

    public MancalaBoardPage(String selectedGameMode) {
        gameMode = selectedGameMode;

        String playerOneName = JOptionPane.showInputDialog("Player 1: ");
        String playerTwoName = JOptionPane.showInputDialog("Player 2: ");

        playerOne = new Player(playerOneName);
        playerTwo = new Player(playerTwoName);

        gameFrame = new MyFrame();
        gameFrame.setExtendedState(MyFrame.MAXIMIZED_BOTH);

        if (gameMode.equals("ayoayo")) {
            game.boardAyoayoGame();
            System.out.println("Player choose to play using ayoayo rules!");
        } else if (gameMode.equals("kalah")) {
            game.boardKalahGame();
            System.out.println("Player choose to play using kalah rules!");
        }

        game.setPlayers(playerOne, playerTwo);

        JPanel buttonsContainer = createButtonGrid(8, 3);

        renderValues();
        currentPlayer = game.getCurrentPlayer();
        disableInvalidButtons();
        setButtonValues();

        currentPlayerLabel = new JLabel("Current Player: " + currentPlayer.getName());

        gameFrame.setLayout(new BorderLayout());
        JPanel info = new JPanel();

        JPanel playerOneInfoPanel = new JPanel();
        playerProfileOne = new MancalaPlayerProfile(playerOne.getUserProfile());
        playerOneInfoPanel.add(playerProfileOne.getInfoPanel());

        JPanel playerTwoInfoPanel = new JPanel();
        playerProfileTwo = new MancalaPlayerProfile(playerTwo.getUserProfile());
        playerTwoInfoPanel.add(playerProfileTwo.getInfoPanel());

        info.setLayout(new BorderLayout());
        info.add(playerOneInfoPanel, BorderLayout.WEST);
        info.add(playerTwoInfoPanel, BorderLayout.EAST);

        info.setLayout(new GridLayout(1, 2));
        JPanel currentPanel = new JPanel();
        currentPanel.add(info);

        JPanel currentNamePanel = new JPanel();
        currentNamePanel.setLayout(new FlowLayout());
        currentNamePanel.add(currentPlayerLabel);

        currentPanel.add(currentNamePanel);
        currentPanel.setLayout(new GridLayout(2, 1));

        gameFrame.add(currentPanel, BorderLayout.NORTH);

        gameFrame.add(buttonsContainer, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        JMenu exitMenu = new JMenu("    Exit");
        JMenu playerMenu = new JMenu("      Player");

        JMenuItem quitNew = new JMenuItem("Quit and Start New");
        JMenuItem mainMenu = new JMenuItem("Main Menu");
        JMenuItem save = new JMenuItem("Save Game");
        JMenuItem load = new JMenuItem("Load Game");
        JMenuItem loadPlayer1Item = new JMenuItem("Load Player 1");
        JMenuItem savePlayer1Item = new JMenuItem("Save Player 1");
        JMenuItem loadPlayer2Item = new JMenuItem("Load Player 2");
        JMenuItem savePlayer2Item = new JMenuItem("Save Player 2");

        gameMenu.add(save);
        gameMenu.add(load);

        exitMenu.add(mainMenu);
        exitMenu.add(quitNew);

        menuBar.add(gameMenu);
        menuBar.add(exitMenu);

        menuBar.add(playerMenu);

        loadPlayer1Item.addActionListener(e -> loadPlayerProfile(playerOne));
        playerMenu.add(loadPlayer1Item);

        savePlayer1Item.addActionListener(e -> savePlayerProfile(playerOne));
        playerMenu.add(savePlayer1Item);

        loadPlayer2Item.addActionListener(e -> loadPlayerProfile(playerTwo));
        playerMenu.add(loadPlayer2Item);

        savePlayer2Item.addActionListener(e -> savePlayerProfile(playerTwo));
        playerMenu.add(savePlayer2Item);

        mainMenu.addActionListener(e -> {
            gameFrame.dispose();
            new MainPage();
        });

        quitNew.addActionListener(e -> {
            gameFrame.dispose();
            new MainPage();
        });

        save.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showSaveDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                String fileName = fileChooser.getSelectedFile().getName();
                Saver.saveObject(game, fileName);
            }
        });

        load.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                String fileName = fileChooser.getSelectedFile().getName();
                Serializable data = Saver.loadObject(fileName);
                if (data instanceof MancalaGame) {
                    game = (MancalaGame) data;
                    playerOne = game.getPlayers().get(0);
                    playerTwo = game.getPlayers().get(1);
                    renderValues();
                    currentPlayer = game.getCurrentPlayer();
                    disableInvalidButtons();
                    currentPlayerLabel.setText("Current Player: " + currentPlayer.getName());
                    info.remove(playerProfileOne.getInfoPanel());
                    info.remove(playerProfileTwo.getInfoPanel());
                    playerProfileOne = new MancalaPlayerProfile(playerOne.getUserProfile());
                    playerProfileTwo = new MancalaPlayerProfile(playerTwo.getUserProfile());
                    info.add(playerProfileOne.getInfoPanel());
                    info.add(playerProfileTwo.getInfoPanel());
                } else {
                    // Handle the case where the loaded object is not of type MancalaGame
                    System.out.println("Invalid file format. Please select a valid MancalaGame file.");
                }
            }
        });

        gameFrame.setJMenuBar(menuBar);
    }

    private void savePlayerProfile(Player player) {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showSaveDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getName();
            Saver.saveObject(player.getUserProfile(), fileName);
        }
    }

    private void loadPlayerProfile(Player player) {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getName();
            Serializable data = Saver.loadObject(fileName);
            if (data instanceof UserProfile) {
                UserProfile userProfile = (UserProfile) data;
                if (userProfile != null) {
                    userProfile.setUserProfile(player);
                    System.out.println("Player profile loaded successfully.");
                } else {
                    System.out.println("Failed to load player profile.");
                }
            } else {
                // Handle the case where the loaded object is not of type UserProfile
                System.out.println("Invalid file format. Please select a valid UserProfile file.");
            }
        }
    }

    public MancalaBoardPage(MancalaGame data) {
        this("");
        game = data;
    }

    private void handleGameOver() {
        JOptionPane over = new JOptionPane();
        try {
            if (game.getWinner() == null) {
                JOptionPane.showMessageDialog(null, "It's a tie", "Game Over!", JOptionPane.PLAIN_MESSAGE);
            } else {
                Player winner = game.getWinner();
                JOptionPane.showMessageDialog(null, winner.getName() + " won the game", "Game Over!",
                        JOptionPane.PLAIN_MESSAGE);
                UserProfile winnerProfile = winner.getUserProfile();
                if (gameMode.equals("ayoayo")) {
                    winnerProfile.incrementNumAyoGamesWon();
                    JOptionPane.showMessageDialog(null, winner.getName() + " is the winner of Ayoayo!", "Winner!",
                        JOptionPane.PLAIN_MESSAGE);
                } else {
                    winnerProfile.incrementNumKalahGamesWon();
                    JOptionPane.showMessageDialog(null, winner.getName() + " is the winner of Kalah!", "Winner!",
                        JOptionPane.PLAIN_MESSAGE);
                }
            }

            if (gameMode.equals("ayoayo")) {
                updatePlayerProfile(playerOne);
                updatePlayerProfile(playerTwo);
            } else {
                updatePlayerProfile(playerOne);
                updatePlayerProfile(playerTwo);
            }

            String options[] = { "Play Again", "Main Menu" };
            int response = JOptionPane.showOptionDialog(null,
                    "Would you like to Play Again or return to Main Menu?", "Play Again?",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, buttons);

            endGameHandler(response);
        } catch (GameNotOverException e) {
            e.printStackTrace();
        }
    }

    private void updatePlayerProfile(Player player) {
        UserProfile profile = player.getUserProfile();
        if (gameMode.equals("ayoayo")) {
            profile.incrementNumAyoGamesPlayed();
        } else {
            profile.incrementNumKalahGamesPlayed();
        }
    }

    private void endGameHandler(int response) {
        if (response == 1) {
            gameFrame.dispose();
            new MainPage();
        } else if (response == 0) {
            gameFrame.dispose();
            new MancalaRules();
        }
    }

    private void renderValues() {
        int playersToPit = 12;

        for (int i = 1; i < 7; i++) {
            try {
                String value = String.valueOf(game.getNumStones(i));
                buttons[i][2].setText(value);
                buttons[i][2].setFont(new Font("Verdana", Font.PLAIN, 20));
                String valueNum2 = String.valueOf(game.getNumStones(playersToPit));
                buttons[i][0].setText(valueNum2);
                buttons[i][0].setFont(new Font("Verdana", Font.PLAIN, 20));
                turnOffButton(buttons[i][1]);
                playersToPit--;
                currentPlayer = game.getCurrentPlayer();
            } catch (PitNotFoundException e) {
                e.printStackTrace();
            }
        }

        String valueNum3 = String.valueOf(playerTwo.getStoreCount());
        buttons[0][1].setText(valueNum3);
        buttons[0][1].setFont(new Font("Verdana", Font.PLAIN, 20));

        String valueNum4 = String.valueOf(playerOne.getStoreCount());
        buttons[7][1].setText(valueNum4);
        buttons[7][1].setFont(new Font("Verdana", Font.PLAIN, 20));

        turnOffButton(buttons[7][1]);
        turnOffButton(buttons[0][1]);
        turnOffButton(buttons[0][0]);
        turnOffButton(buttons[7][0]);
        turnOffButton(buttons[0][2]);
        turnOffButton(buttons[7][2]);
    }
}

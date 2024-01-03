package ui; 

import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

import mancala.UserProfile;

public class MancalaPlayerProfile {
    
    private UserProfile user;
    private JPanel infoPanel = new JPanel();

    public MancalaPlayerProfile(UserProfile profile) {
        user = profile;

        MyLabel playerName = new MyLabel("Player Name: " + user.getUserName());
        playerName.setForeground(new Color(2,129,176));
        playerName.setFont(new Font("Verdana", Font.BOLD, 18));

        MyLabel spaceLabel1 = new MyLabel(""); 

        MyLabel kalah = new MyLabel("Kalah Games");
        kalah.setFont(new Font("Verdana", Font.BOLD, 16));

        MyLabel kalahPlayed = new MyLabel("Played: " + user.getNumKalahGamesPlayed());
        kalahPlayed.setFont(new Font("Verdana", Font.PLAIN, 16));

        MyLabel kalahWon = new MyLabel("Won: " + user.getNumKalahGamesWon());
        kalahWon.setFont(new Font("Verdana", Font.PLAIN, 16));

        MyLabel spaceLabel2 = new MyLabel("");

        MyLabel ayoayo = new MyLabel("Ayoayo Games");
        ayoayo.setFont(new Font("Verdana", Font.BOLD, 16));

        MyLabel ayoayoPlayed = new MyLabel("Played: " + user.getNumAyoGamesPlayed());
        ayoayoPlayed.setFont(new Font("Verdana", Font.PLAIN, 16));

        MyLabel ayoayoWon = new MyLabel("Won: " + user.getNumAyoGamesWon());
        ayoayoWon.setFont(new Font("Verdana", Font.PLAIN, 16));

        infoPanel.setLayout(new GridLayout(9, 0));
        infoPanel.add(playerName);
        infoPanel.add(spaceLabel1);
        infoPanel.add(kalah);
        infoPanel.add(kalahPlayed);
        infoPanel.add(kalahWon);
        infoPanel.add(spaceLabel2);
        infoPanel.add(ayoayo);
        infoPanel.add(ayoayoPlayed);
        infoPanel.add(ayoayoWon);
    }

    public JPanel getInfoPanel() {
        return infoPanel;
    }
}


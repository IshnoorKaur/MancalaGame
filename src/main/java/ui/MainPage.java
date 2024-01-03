package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.Serializable;

import mancala.Saver;

public class MainPage {

    private MyFrame frame;
    private MyButton newGameButton;
    private MyButton loadGameButton;

    public MainPage() {
        frame = new MyFrame("Welcome!");

        MyLabel label = new MyLabel("Welcome to Mancala Game!");
        label.setFont(new Font("Verdana", Font.PLAIN, 20));

        newGameButton = new MyButton("New Game");
        newGameButton.addActionListener(e -> {
            frame.dispose();
            new MancalaRules();
        });

        MyButton newGame = new MyButton("New Game");
        newGame.addActionListener(e -> {

            frame.dispose();
            new MancalaRules(); 
        });

        loadGameButton = new MyButton("Load Game");
        loadGameButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                String fileName = fileChooser.getSelectedFile().getName();
                Serializable data = Saver.loadObject(fileName);
                // Handle loaded data if needed
                frame.dispose();
                new MancalaRules();
            }
        });

        JPanel main = new JPanel();
        JPanel buttons = new JPanel();

        buttons.setPreferredSize(new Dimension(100, 100));

        main.add(label);
        buttons.add(newGameButton);
        buttons.add(loadGameButton);

        frame.setLayout(new BorderLayout());
        frame.add(main, BorderLayout.NORTH);
        frame.add(buttons, BorderLayout.CENTER);

        frame.setResizable(false);

        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(frame, "Do you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    System.exit(0);
                }
            }
        });

        frame.setVisible(true);
    }

}

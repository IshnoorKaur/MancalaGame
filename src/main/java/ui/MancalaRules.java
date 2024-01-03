package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ui.MyFrame;
import ui.MyButton;

public class MancalaRules {

    public MancalaRules() {
        MyFrame frame = new MyFrame("Mancala Rules");

        MyLabel label = new MyLabel("Pick the game rules:");
        label.setFont(new Font("Verdana", Font.PLAIN, 20));

        MyButton ayoButton = new MyButton("Ayoayo");
        ayoButton.addActionListener(e -> {
            frame.dispose();
            new MancalaBoardPage("ayoayo");
        });

        MyButton kalahButton = new MyButton("Kalah");
        kalahButton.addActionListener(e -> {
            frame.dispose();
            new MancalaBoardPage("kalah");
        });

        JPanel main = new JPanel();
        JPanel buttons = new JPanel();

        main.setPreferredSize(new Dimension(100, 100));
        buttons.setPreferredSize(new Dimension(350, 100));

        main.add(label);
        buttons.add(ayoButton);
        buttons.add(kalahButton);

        frame.setLayout(new BorderLayout());
        frame.add(main, BorderLayout.NORTH);
        frame.add(buttons, BorderLayout.CENTER);

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

                        
        

package ui;

import javax.swing.JFrame;

public class MyFrame  extends JFrame{
    public MyFrame(){
        this.setSize(900,800);
        this.setTitle("Mancala Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public MyFrame(String title){
        super(title);
    }
}

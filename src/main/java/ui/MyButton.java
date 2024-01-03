package ui;

import javax.swing.JButton;

public class MyButton extends JButton{

    public MyButton(String text){
        this.setSize(30,20);
        this.setFocusable(false);
        this.setText(text);
    }
    
}


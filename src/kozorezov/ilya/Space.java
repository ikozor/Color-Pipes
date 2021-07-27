package kozorezov.ilya;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Space extends JButton{
    private int type = 0;
    private final int xPos;
    private final int yPos;
    
    public Space(int color, int x, int y){
        convert(color);
        xPos = x;
        yPos = y;
    }

    public void convert(int color){
        if(color < 10) this.setBorder(new LineBorder(Color.black));
        else this.setBorder(null);
        switch (color) {
            case 1, 10 -> {
                this.type = color;
                this.setBackground(Color.red);
            }
            case 2,20 -> {
                this.type = color;
                this.setBackground(Color.blue);
            }
            case 3,30 -> {
                this.type = color;
                this.setBackground(new Color(0,100,0));
            }
            case 4,40 -> {
                this.type = color;
                this.setBackground(Color.MAGENTA);
            }
            case 5,50 -> {
                this.type = color;
                this.setBackground(Color.yellow);
            }
            case 6,60 -> {
                this.type = color;
                this.setBackground(Color.ORANGE);
            }
            case 7,70 -> {
                this.type = color;
                this.setBackground(Color.PINK);
            }
            case 8,80 -> {
                this.type = color;
                this.setBackground(Color.GRAY);
            }
            case 9, 90 -> {
                this.type = color;
                this.setBackground(Color.white);
            }

            default -> {
                this.type = 0;
                this.setBackground(Color.black);
            }
        }
    }

    public int getType() {
        return type;
    }

    public int getyPos() {
        return yPos;
    }
    public int getxPos() {
        return xPos;
    }
}

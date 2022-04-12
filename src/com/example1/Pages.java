package com.example1;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Pages extends JPanel  {
    private int lengthRectangle;
    private int heightRectangle;
    transient BufferedImage image;

    Pages(){
        setPreferredSize(new Dimension(400,400));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.gray);

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        if(lengthRectangle!=0 && heightRectangle!=0){
          g.fillRect(getWidth()/2-lengthRectangle/2,getHeight()/2-heightRectangle/2,lengthRectangle,heightRectangle);

        }



    }

    public int getLengthRectangle() {
        return lengthRectangle;
    }

    public void setLengthRectangle(int lengthRectangle) {
        this.lengthRectangle = lengthRectangle;
    }

    public int getHeightRectangle() {
        return heightRectangle;
    }

    public void setHeightRectangle(int heightRectangle) {
        this.heightRectangle = heightRectangle;
    }
}

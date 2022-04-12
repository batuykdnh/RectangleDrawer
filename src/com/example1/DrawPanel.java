package com.example1;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    ArrayList<Pages> pages=new ArrayList<Pages>();
    int pageNumber;

    DrawPanel(){
        setLayout(new CardLayout());
        addPage();
    }

    void addPage(){
        if(!pages.isEmpty()){
            pages.add(new Pages());
            pageNumber++;
        }
        else{
            pages.add(new Pages());

        }


        add(pages.get(pageNumber),String.valueOf(pageNumber));
        JLabel jLabel=new JLabel(String.valueOf(pageNumber+1));
        jLabel.setForeground(Color.BLUE);
        pages.get(pageNumber).add(jLabel);
        showPage();

    }
    void showPage(){
        ((CardLayout)getLayout()).show(this,String.valueOf(pageNumber));
        repaint();

    }



}





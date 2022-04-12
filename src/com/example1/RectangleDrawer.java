package com.example1;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RectangleDrawer extends JFrame{
    DrawPanel drawPanels;
    JPanel inputPanel;
    JPanel buttonPanel;
    JLabel result1;
    JLabel result2;
    JLabel errorMessage;
    JTextField lengthInput;
    JTextField heightInput;
    JFileChooser jFileChooser=new JFileChooser("save");
    ArrayList<LengthAndHeight> lengthAndHeights=new ArrayList<LengthAndHeight>();

    RectangleDrawer(){

        super("RectangleDrawer");
        createSaveFolder();
        try(ImageInputStream in=new FileImageInputStream(new File("src/icons/icons8-rectangle-48.png"))){

            setIconImage(ImageIO.read(in));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileNameExtensionFilter fileNameExtensionFilter=new FileNameExtensionFilter("Data File","dat");
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.addChoosableFileFilter(fileNameExtensionFilter);
        lengthAndHeights.add(new LengthAndHeight(0,0));
        addComponents(getContentPane());
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
    static class LengthAndHeight implements Serializable{
        int length;
        int heigth;

        public LengthAndHeight(int length, int heigth) {
            this.length = length;
            this.heigth = heigth;
        }
    }
    void createSaveFolder(){
        try {
            Files.createDirectory(Paths.get("save"));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }



    void createInputPanel(){

        inputPanel=new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel,BoxLayout.Y_AXIS));
        inputPanel.setPreferredSize(new Dimension(400,400));
        JLabel lengthText=new JLabel("Input length of the rectangle:");
        lengthText.setPreferredSize(new Dimension(lengthText.getPreferredSize().width,50));
        lengthText.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel.add(lengthText);
        lengthInput=new JTextField();
        lengthInput.setMaximumSize(new Dimension(100,100));
        inputPanel.add(lengthInput);
        JLabel heightText=new JLabel("Input height of the rectangle:");
        heightText.setPreferredSize(new Dimension(lengthText.getPreferredSize().width,50));
        heightText.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel.add(heightText);
        heightInput=new JTextField();
        heightInput.setMaximumSize(new Dimension(100,100));
        inputPanel.add(heightInput);
        inputPanel.add(Box.createVerticalGlue());
        errorMessage=new JLabel();
        errorMessage.setPreferredSize(new Dimension(errorMessage.getPreferredSize().width,50));
        errorMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel.add(errorMessage);
        result1=new JLabel();
        result2=new JLabel();
        result1.setPreferredSize(new Dimension(result1.getPreferredSize().width,50));
        result1.setAlignmentX(Component.CENTER_ALIGNMENT);
        result2.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel.add(result1);
        inputPanel.add(result2);
        inputPanel.add(Box.createVerticalGlue());
        JButton create=new JButton("Create");
        create.setPreferredSize(new Dimension(50,50));
        create.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel.add(create);
        inputPanel.add(Box.createVerticalGlue());
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int height=Integer.parseInt(heightInput.getText());
                    int length=Integer.parseInt(lengthInput.getText());
                    lengthAndHeights.set(drawPanels.pageNumber,new LengthAndHeight(length,height));
                    drawPanels.pages .get(drawPanels.pageNumber).setHeightRectangle(height);
                    drawPanels.pages.get(drawPanels.pageNumber).setLengthRectangle(length);
                    result1.setText("Perimeter: "+(length+height)*2);
                    result2.setText("Area: "+length*height);
                    errorMessage.setText("");
                    drawPanels.repaint();
                }
                catch (NumberFormatException exception){
                    exception.printStackTrace();
                    errorMessage.setForeground(Color.red);
                    errorMessage.setText("Please Input Number");
                }


            }
        });

        inputPanel.setBorder(BorderFactory.createLineBorder(Color.black));

    }

    void addComponents(Container container){
        drawPanels=new DrawPanel();
        createButtonBar();
        createInputPanel();
        container.add(drawPanels,BorderLayout.CENTER);
        container.add(buttonPanel,BorderLayout.PAGE_END);
        container.add(inputPanel,BorderLayout.LINE_START);


    }


    void setTextMessages(int length,int height){
        if(length!=0 && height!=0){
            lengthInput.setText(String.valueOf(length));
            heightInput.setText(String.valueOf(height));
            result1.setText("Perimeter: "+2*(length+height));
            result2.setText("Area: "+length*height);
        }
        else{
            lengthInput.setText("");
            heightInput.setText("");
            result1.setText("");
            result2.setText("");
        }


    }
    void createButtonBar(){
        buttonPanel=new JPanel();
        buttonPanel.setPreferredSize(new Dimension(800,40));
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonPanel.setBackground(Color.DARK_GRAY);
        JButton button1=new JButton("Previous Page");
        JButton button2=new JButton("Next Page");
        JButton button3=new JButton("Reset Pages");
        JButton button4=new JButton("Save");
        JButton button5=new JButton("Load");
        JButton button6=new JButton("First Page");
        JButton button7=new JButton("Last Page");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(drawPanels.pageNumber>=1){
                    drawPanels.pageNumber-=1;

                    int length=lengthAndHeights.get(drawPanels.pageNumber).length;
                    int height=lengthAndHeights.get(drawPanels.pageNumber).heigth;
                    setTextMessages(length,height);
                    errorMessage.setText("");
                    drawPanels.showPage();
                    drawPanels.repaint();

                }

            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    if(drawPanels.pages.get(drawPanels.pageNumber+1)!=null){
                        drawPanels.pageNumber++;
                        int length=lengthAndHeights.get(drawPanels.pageNumber).length;
                        int height=lengthAndHeights.get(drawPanels.pageNumber).heigth;
                        setTextMessages(length,height);
                        drawPanels.showPage();
                    }
                }catch (IndexOutOfBoundsException exception){
                    exception.printStackTrace();
                    drawPanels.addPage();
                    lengthAndHeights.add(new LengthAndHeight(0,0));
                    int length=lengthAndHeights.get(drawPanels.pageNumber).length;
                    int height=lengthAndHeights.get(drawPanels.pageNumber).heigth;
                    setTextMessages(length,height);

                }
                errorMessage.setText("");
                drawPanels.repaint();

            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorMessage.setText("");
                drawPanels.pages.clear();
                lengthAndHeights.clear();
                lengthAndHeights.add(new LengthAndHeight(0,0));
                setTextMessages(lengthAndHeights.get(0).length,lengthAndHeights.get(0).heigth);
                drawPanels.removeAll();
                drawPanels.revalidate();
                drawPanels.repaint();
                drawPanels.pageNumber=0;
                drawPanels.addPage();

            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanels.pageNumber=0;
                int length=lengthAndHeights.get(drawPanels.pageNumber).length;
                int height=lengthAndHeights.get(drawPanels.pageNumber).heigth;
                setTextMessages(length,height);
                errorMessage.setText("");
                drawPanels.showPage();
                drawPanels.repaint();
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanels.pageNumber=drawPanels.pages.size()-1;
                int length=lengthAndHeights.get(drawPanels.pageNumber).length;
                int height=lengthAndHeights.get(drawPanels.pageNumber).heigth;
                setTextMessages(length,height);
                errorMessage.setText("");
                drawPanels.showPage();
                drawPanels.repaint();
            }
        });

        buttonPanel.add(button6);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button7);
    }
    void load(){
        jFileChooser.showOpenDialog(this);


        try(ObjectInputStream in=new ObjectInputStream(new FileInputStream(jFileChooser.getSelectedFile()))){

            lengthAndHeights=(ArrayList<LengthAndHeight>) in.readObject();
            remove(drawPanels);
            revalidate();
            drawPanels=(DrawPanel) in.readObject();
            setTextMessages(lengthAndHeights.get(drawPanels.pageNumber).length,lengthAndHeights.get(drawPanels.pageNumber).heigth);
            add(drawPanels);
            drawPanels.showPage();
            drawPanels.repaint();




        }catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }

    }
    void save(){
        jFileChooser.showSaveDialog(this);
        File file=jFileChooser.getSelectedFile();
        if(file!=null){
            String[] strings=file.getName().split("\\.");
            if(!strings[strings.length-1].equals("dat") || strings.length==1){
                file=new File(file.getAbsolutePath()+".dat");
            }
        }








        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(file))){
            out.writeObject(lengthAndHeights);
            out.writeObject(drawPanels);
        }catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }


    }




    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RectangleDrawer();
            }
        });
    }





}


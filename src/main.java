

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.Arrays;

public class main extends JFrame {
    private final JPanel down;
    private final MainPanel up;
    private final JFileChooser fileChooser=new JFileChooser();

    main(){
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Photo Viewer");
        up=new MainPanel();
        down=new JPanel();
        createUpPanel(up);
        createDownPanel(down);
        FileNameExtensionFilter fileNameExtensionFilter=new FileNameExtensionFilter("Image","png","jpg");
       //    fileChooser.setFileFilter(fileNameExtensionFilter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(fileNameExtensionFilter);
        pack();

        setVisible(true);

    }
    void draw(Graphics graphics, ArrayList<BufferedImage> arrayList){



    }


    void createUpPanel(JPanel jPanel){

        up.setPreferredSize(new Dimension(1360,400));
        add(up,BorderLayout.CENTER);

    }
    void createDownPanel(JPanel jPanel){

        down.setPreferredSize(new Dimension(1360,100));
        JButton button1=new JButton("Previous");
        JButton button2=new JButton("Next");
        JButton button3=new JButton("Select Folder");
        JButton button4=new JButton("Reset Folder");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(up.getArrayList().get(up.getSelectImage()-1)!=null){
                    up.setSelectImage(up.getSelectImage()-1);
                    up.repaint();
                }

            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(up.getArrayList().get(up.getSelectImage()+1)!=null){
                    up.setSelectImage(up.getSelectImage()+1);
                    up.repaint();
                }

            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

         //       fileChooser.setFileSystemView();
                fileChooser.showOpenDialog(up);



                try(ImageInputStream in=new FileImageInputStream(fileChooser.getSelectedFile())){


                    if(up.getArrayList().size()!=0){
                        up.setSelectImage(up.getSelectImage()+1);
                    }

                    up.getArrayList().add(ImageIO.read(in));

                    up.repaint();



                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                up.getArrayList().clear();
                up.setSelectImage(0);
                up.repaint();
            }
        });



        down.add(button1);
        down.add(button2);
        down.add(button3);
        down.add(button4);




        add(down,BorderLayout.PAGE_END);




    }
  /*  void createMenuBar(){
        jMenuBar=new JMenuBar();
        jMenuBar.setPreferredSize(new Dimension(100,10));
        JMenuItem jMenuItem=new JMenuItem();
        jMenuItem.add(jMenuItem);




        add(jMenuBar,BorderLayout.PAGE_START);
    }*/





    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new main();
            }
        });
    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MainPanel extends JPanel{
    private ArrayList<BufferedImage> arrayList=new ArrayList<BufferedImage>();
    private int selectImage;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        try{
            g.drawImage(arrayList.get(selectImage),0,0,this);
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }



    }

    public ArrayList<BufferedImage> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<BufferedImage> arrayList) {
        this.arrayList = arrayList;
    }

    public int getSelectImage() {
        return selectImage;
    }

    public void setSelectImage(int selectImage) {
        this.selectImage = selectImage;
    }
}

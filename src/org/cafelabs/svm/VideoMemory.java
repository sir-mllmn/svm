package org.cafelabs.svm;

import javax.swing.*;
import java.awt.*;
import java.awt.image.MemoryImageSource;


public class VideoMemory extends JPanel {
    private static int height = 120;
    private static int width = 240;
    private static int scale = 4;

    public static VideoMemory videoMemory = new VideoMemory();

    public VideoMemory() {
        JFrame window = new JFrame("SVM");
        window.getContentPane().add(this);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(width * scale, height * scale));
        window.setResizable(false);
        window.setVisible(true);

    }

    public static VideoMemory init() {
        return videoMemory;
    }


    static int[] videoMem = new int[height * width];

    static {
        for (int i = 0; i < height * width; i++) {
            //Show noise (comment line 37 to show it)
            //videoMem[i]=((int)(Math.random()*Integer.MAX_VALUE)) |0xff000000;

            videoMem[i] = 0xff000000;
        }
    }

    /**
     * @param index
     * @return
     */
    public static void setPixel(int pixel, int row, int line) {
        videoMem[((row - 1) * width + line)] = pixel | 0xff000000;
        videoMemory.repaint();
    }

    public static int getPixel(int index) {
        int p;
        if (index > videoMem.length) {
            System.out.println("Out of bound of the VideoMemory");
            return 0;
        }
        p = videoMem[index];
        return p;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Image img = createImage(new MemoryImageSource(width, height, videoMem, 0, width));
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }


}

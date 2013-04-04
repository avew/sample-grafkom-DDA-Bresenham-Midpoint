
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asep Rojali
 */
public class AlgoritmaDDA extends Frame {

    public static void main(String[] args) {
        new AlgoritmaDDA();
    }

    public AlgoritmaDDA() throws HeadlessException {
        super("Algoritma DDA");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setSize(600, 400);
        add("Center", new CvDDA());
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        show();
    }

    class CvDDA extends Canvas {

        //Inisialisasi canvas untuk menggambar
        float rWidth = 10.0F, rHeight = 7.5F, pixelSize;
        int centerX, centerY, dGrid = 10, maxX, maxY;

        void initgr() {
            Dimension d = getSize();
            maxX = d.width - 1;
            maxY = d.height - 1;
            pixelSize = Math.max(rWidth / maxX, rHeight / maxY);
            centerX = maxX / 2;
            centerY = maxY / 2;
        }

        int iX(float x) {
            return Math.round(centerX + x / pixelSize);
        }

        int iY(float y) {
            return Math.round(centerY - y / pixelSize);
        }

        void putPixel(Graphics g, int x, int y) {
            int x1 = x * dGrid, y1 = y * dGrid, h = dGrid / 2;
            g.drawOval(x1 - h, y1 - h, dGrid, dGrid);

        }

        //Menghitung algoritma DDA
        void DDA(Graphics g, int x0, int y0, int x1, int y1) {
            int x = x0, y = y0, dX = x1 - x0, dY = y1 - y0;
            int step, xInc, yInc;

            if (dX > dY) {
                step = Math.abs(dX);
            }
            step = Math.abs(dY);
            System.out.println("Nilai dX " + dX);
            System.out.println("Nilai dY " + dY);

            xInc = dX / step;
            yInc = dY / step;
            System.out.println("Nilai xInc " + xInc);
            System.out.println("Nilai yInc " + yInc);
            putPixel(g, x, y);
            for (int i = 1; i <= step; i++) {
                x = x + xInc;
                y = y + yInc;
                System.out.println("Nilai x " + i + " = " + x);
                System.out.println("Nilai y " + i + " = " + y);
                putPixel(g, x, y);
            }
            putPixel(g, dX, dY);
        }

        void showGrid(Graphics g) {
            for (int x = dGrid; x <= maxX; x += dGrid) {
                for (int y = dGrid; y <= maxY; y += dGrid) {
                    g.drawLine(x, y, x, y);
                }
            }
        }

        @Override
        public void paint(Graphics g) {
            initgr();
            //putPixel(g, 6, 2);
            showGrid(g);
            //putPixel(g, 30, 10);
            DDA(g, 6, 2, 30, 10);
        }
    }
}

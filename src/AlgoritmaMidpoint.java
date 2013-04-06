
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asep Rojali
 */
public class AlgoritmaMidpoint extends Frame {

    public static void main(String[] args) {
        new AlgoritmaMidpoint();
    }

    public AlgoritmaMidpoint() throws HeadlessException {
        super("Algoritma Midpoint");
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

        //Inisialisasi canvas untuk gambar
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

        //Menghitung Algoritma Midpoint
        void drawCircle(Graphics g, int xC, int yC, int r) {
            int x = 0, y = r, u = 1, v = 2 * r - 1, E = 0;
            while (x < y) {
                putPixel(g, xC + x, yC + y); 
                putPixel(g, xC + y, yC - x); 
                putPixel(g, xC - x, yC - y); 
                putPixel(g, xC - y, yC + x); 
                x++;
                E += u;
                u += 2;
                if (v < 2 * E) {
                    y--;
                    E -= v;
                    v -= 2;
                }
                if (x > y) {
                    break;
                }
                putPixel(g, xC + y, yC + x); 
                putPixel(g, xC + x, yC - y); 
                putPixel(g, xC - y, yC - x); 
                putPixel(g, xC - x, yC + y); 

            }
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
            showGrid(g);
            drawCircle(g, 23, 10, 8);
            //drawLine2(g, 6, 2, 30, 10);
        }
    }
}


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
public class AlgoritmaBresenham extends Frame {

    public static void main(String[] args) {
        new AlgoritmaBresenham();
    }

    public AlgoritmaBresenham() throws HeadlessException {
        super("Algoritma Bresenham");
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

        //Menghitung Algoritma Bresenham
        void bresenham(Graphics g, int x0, int y0, int x1, int y1) {
            int x = x0, y = y0, D = 0, dX, dY,
                    c, M, xInc = 1, yInc = 1;
            dX = x1 - x0;
            dY = y1 - y0;
            if (dX < 0) {
                xInc = -1;
                dX = -dX;
            }
            if (dY < 0) {
                yInc = -1;
                dY = -dY;
            }
            if (dY <= dX) {
                c = 2 * dX;
                M = 2 * dY;
                for (;;) {
                    putPixel(g, x, y);
                    if (x == x1) {
                        break;
                    }
                    x += xInc;
                    D += M;
                    if (D > dX) {
                        y += yInc;
                        D -= c;
                    }
                }
            } else {
                c = 2 * dY;
                M = 2 * dX;
                for (;;) {
                    putPixel(g, x, y);
                    if (y == y1) {
                        break;
                    }
                    y += yInc;
                    D += M;
                    if (D > dY) {
                        x += xInc;
                        D -= c;
                    }
                }
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
            bresenham(g, 6, 2, 30, 10);

        }
    }
}

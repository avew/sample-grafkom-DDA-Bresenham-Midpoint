
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asep Rojali
 */
public class AlgoritmaBresenham extends JDialog {

    public static void main(String[] args) {
        new AlgoritmaBresenham();
    }

    public AlgoritmaBresenham() throws HeadlessException {
        //super("Algoritma Bresenham");
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
            int x = x0, y = y0, e = 0, dX, dY,
                    duaDeltaX, duaDeltaY, xInc = 1, yInc = 1;
            //x0=6, y0=2, x1=15, y1=6

            dX = x1 - x0; // 15-6 =9  
            dY = y1 - y0; //6 - 2 = 4 
            if (dX < 0) { //cek nilai delta x  kurang dari 0
                xInc = -1; // nilai dari xInc = -1 
                dX = -dX; //dX =-dX
            }
            if (dY < 0) { //cek nilai delta y
                yInc = -1; //nilai dari yInc = 
                dY = -dY;
            }
            if (dY <= dX) { // 4 <= 9  m > 0
                duaDeltaX = 2 * dX; //2 x 9 =18
                duaDeltaY = 2 * dY; // 2 x 4 = 8
                System.out.println("Jika nilai dy <= dx,2dx = " + duaDeltaX + " dan 2dy = " + duaDeltaY);
                for (;;) { // ulang terus sampe nilai x == x1
                    putPixel(g, x, y);
                    System.out.println("x = " + x + " dan y = " + y);
                    if (x == x1) { //6 == 15
                        break;
                    }
                    x += xInc; // x = 6 + 1 = 7
                    //hasil increment x di ulang lagi dan nilai y tetap
                    e += duaDeltaY; //e = 0 + (0+8) = 8
                    if (e > dX) { //8 > 9 (tidak)
                        y += yInc;
                        e -= duaDeltaX;
                    }
                }
            } else { // 8 >= 14  m < 0
                duaDeltaX = 2 * dY;
                duaDeltaY = 2 * dX;
                System.out.println("Jika nilai dy >= dx,2dx = " + duaDeltaX + " dan 2dy = " + duaDeltaY);
                for (;;) {
                    putPixel(g, x, y);
                    System.out.println("x = " + x + " dan y = " + y);
                    if (y == y1) {
                        break;
                    }
                    y += yInc;
                    e += duaDeltaY;
                    if (e > dY) {
                        x += xInc;
                        e -= duaDeltaX;
                    }
                }
            }
        }

        void coba(Graphics g, int x0, int y0, int x1, int y1) {
            int x = x0, y = y0, deltaX, deltaY, duaDeltaY, duaDeltaX, duaDxDy, pk,e, k=0;
            
            deltaX = x1 - x0;
            deltaY = y1 - y0;
            
            duaDeltaY = 2 * deltaY;
            duaDeltaX = 2 * deltaX;
            pk = duaDeltaY - deltaX;
            
            e = duaDeltaY -duaDeltaX;
            
            if (pk < 0) {
                x += x+1;
                pk += pk+duaDeltaY;
                for (int i = 0; i < 10; i++) {
                    
                }
            }
            
            
        }

        void drawLine2(Graphics g, int xo, int y0, int x1, int y1) {
            int x = xo, y = y0, d = 0, dx = x1 - xo, c = 2 * dx,
                    m = 2 * (y1 - y0);
            for (;;) {
                putPixel(g, x, y);
                if (x == x1) {
                    break;
                }
                x++;
                System.out.println("x++ = "+x++);
                d += m;
                System.out.println("d="+d);
                if (d >= dx) {
                    y++;
                    System.out.println("y++ = "+y++);
                    d -= c;
                    System.out.println("d - = "+d);
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
            //bresenham(g, 6, 2, 15, 6);
            bresenham(g, 20, 10, 30, 18);
            //drawLine2(g, 6, 2, 30, 10);
        }
    }
}

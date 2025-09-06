package gui;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel {
    private static final int border = 25;
    private Image image2;
    private ImageIcon image;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Canvas(ImageIcon image, int x1, int y1, int x2, int y2) throws IOException {
        this.image = image;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        image2 = ImageIO.read(new File("gui/logo.png"));
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(250, 200);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D)graphics;
        graphics.setColor(Color.red);
        Rectangle size = getBounds();
        graphics.drawRect(border, border, size.width - 2*border, size.height - 2*border);
        graphics.setColor(Color.blue);
        graphics.drawString("Omar Elsaghir", 25, 20);
        graphics.drawLine(0, 0, 100, 100);
        graphics.drawLine(0, 100, 100, 0);
        graphics.drawImage(image2, 0,0, null);
    }
}

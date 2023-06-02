package me.anselm.main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class CoordinatePanel extends JPanel {

    public static final int WIDTH = UDPSender.WIDTH - 100, HEIGHT = UDPSender.HEIGHT;

    public static boolean enableCoordinate = true;

    public CoordinatePanel() {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        this.setBorder(border);
        this.setSize(WIDTH,  HEIGHT);
        this.setLocation(0,0);

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (!enableCoordinate) {
                    return;
                }
                int x = e.getX();
                int y = e.getY();
                y *= -1;
                x -= WIDTH / 2;
                y += HEIGHT / 2;

                float weightY = y;
                float weightX = x;
                weightY /= ((float)HEIGHT / 2f);
                weightX /= ((float)WIDTH / 2f);
                UDPSender.sendFloats(new float[] {weightX, weightY});
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Perform custom drawing operations
        int centerY = getHeight() / 2;
        int centerX = getWidth() / 2;
        g.drawLine(0, centerY, getWidth(), centerY);
        g.drawLine(centerX, 0,centerX, getHeight() );
    }


}

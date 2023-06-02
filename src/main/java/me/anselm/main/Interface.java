package me.anselm.main;

import javax.swing.*;
import java.awt.*;

public class Interface extends JFrame{

    public Interface() {
        this.setSize(new Dimension(UDPSender.WIDTH, UDPSender.HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(null);
        this.setTitle("2D coordinate system!");
        this.setResizable(false);
        ToolTipManager.sharedInstance().setInitialDelay(0);
    }
}

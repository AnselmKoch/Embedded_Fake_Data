package me.anselm.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllPanel extends JPanel {

    public JTextField xWeight, yWeight, lastX, lastY;
    private JLabel xPrompt, yPrompt;

    private JButton sendButton, disableCoordinateButton, informationButton;
    private float x = 1f, y = 1f;

    public static float lastSentX = 1, lastSentY = 1;

    public ControllPanel() {
        this.setSize(UDPSender.WIDTH - CoordinatePanel.WIDTH, UDPSender.HEIGHT);
        this.setLocation(UDPSender.WIDTH - this.getWidth(), 0);
        this.setBackground(Color.LIGHT_GRAY);
        xWeight = new JTextField();
        xWeight.setPreferredSize(new Dimension(50, 30));
        xWeight.setLocation(this.getWidth() / 2, 50);
        xWeight.setText("1");
        xPrompt = new JLabel("X Weight:");
        this.add(xPrompt);
        this.add(xWeight);
        yWeight = new JTextField();
        yWeight.setPreferredSize(new Dimension(50, 30));
        yWeight.setLocation(this.getWidth() / 2, 100);
        yWeight.setText("1");
        yPrompt = new JLabel("Y Weight:");
        this.add(yPrompt);
        this.add(yWeight);

        sendButton = new JButton();
        sendButton.setPreferredSize(new Dimension(75, 30));
        sendButton.setText("Send");
        sendButton.setToolTipText("Eingetragene Werte per UDP an localhost:3065 senden");
        sendButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            x = Float.parseFloat(xWeight.getText());
                        } catch (Exception exception) {
                            xWeight.setBackground(Color.RED);
                            return;
                        }
                        xWeight.setBackground(Color.WHITE);
                        try {
                            y = Float.parseFloat(yWeight.getText());
                        } catch (Exception exception) {
                            yWeight.setBackground(Color.RED);
                            return;
                        }
                        yWeight.setBackground(Color.WHITE);
                        UDPSender.sendFloats(new float[] {x, y});
                    }
                }
        );
        this.add(sendButton);
        disableCoordinateButton = new JButton();
        disableCoordinateButton.setText("An");
        disableCoordinateButton.setBackground(Color.GREEN);
        disableCoordinateButton.setToolTipText("Koordinate System aktivieren");
        disableCoordinateButton.setPreferredSize(new Dimension(75, 30));
        disableCoordinateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CoordinatePanel.enableCoordinate = !CoordinatePanel.enableCoordinate;
                boolean enable = CoordinatePanel.enableCoordinate;
               disableCoordinateButton.setText(enable ? "An" : "Aus");
               disableCoordinateButton.setToolTipText(enable ? "Koordinaten System deaktivieren" : "Koordinaten System aktivieren");
               disableCoordinateButton.setBackground(enable ? Color.GREEN : Color.RED);
            }
        });

        lastX = new JTextField();
        lastX.setPreferredSize(new Dimension(100, 30));
        lastX.setEditable(false);
        lastY = new JTextField();
        lastY.setPreferredSize(new Dimension(100,30));
        lastY.setEditable(false);
        this.add(disableCoordinateButton);
        this.add(lastX);
        this.add(lastY);
        informationButton = new JButton();
        informationButton.setText("?");
        informationButton.setPreferredSize(new Dimension(75, 30));
        informationButton.setToolTipText("Letzte gesendeten Weights. Oben X, unten Y");
        this.add(informationButton);
    }
}
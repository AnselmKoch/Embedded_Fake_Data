package me.anselm.main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class UDPSender {

    public static final int WIDTH = 1100, HEIGHT = 750;
    private static Interface mainFrame;
    private static CoordinatePanel coordinatePanel;
    private static ControllPanel controllPanel;

    public static void main(String[] args) {
        mainFrame = new Interface();
        coordinatePanel = new CoordinatePanel();
        controllPanel = new ControllPanel();

        mainFrame.add(coordinatePanel);
        mainFrame.add(controllPanel);
        mainFrame.setVisible(true);
    }

    public static void sendFloats(float[] floats) {
        System.out.println("Sending current weights!");
        try {
            ByteBuffer buffer = ByteBuffer.allocate(2 * 4);
            for (float coordinate : floats) {
                buffer.putFloat(coordinate);
                System.out.println("Added float to buffer! " + coordinate);
            };
            buffer.flip();

            InetAddress receiverAddress = InetAddress.getByName("localhost");
            int receiverPort = 3065;
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.limit(), receiverAddress, receiverPort);
            socket.send(packet);
            controllPanel.lastX.setText(String.valueOf(floats[0]));
            controllPanel.lastY.setText(String.valueOf(floats[1]));
            System.out.println("Sent weights!");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

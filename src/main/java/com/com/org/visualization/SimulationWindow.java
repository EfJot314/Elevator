package com.com.org.visualization;


import javax.swing.*;
import java.awt.*;

public class SimulationWindow extends JFrame {

    public SimulationWindow(int nOfElevators){
        setTitle("Elevators:");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);

        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        Panel leftPanel = new Panel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        Panel rightPanel = new Panel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        for(int i=0;i<10;i++){
            int floorNum = i+1;
            JButton floorButton = new JButton("Floor "+floorNum);
            floorButton.setFont(new Font("Arial", Font.PLAIN, 20));
            floorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            floorButton.addActionListener(e -> {
                System.out.println("Floor "+ floorNum);
            });
            leftPanel.add(floorButton);
        }

        for(int i=0;i<nOfElevators;i++){
            JLabel elevatorLabel = new JLabel("Elevator "+i);
            elevatorLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            elevatorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            rightPanel.add(elevatorLabel);
        }

        panel.add(leftPanel);
        panel.add(rightPanel);
        add(panel);

        setVisible(true);
    }



}

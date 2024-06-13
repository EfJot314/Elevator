package com.com.org.visualization;


import com.com.org.system.ElevatorSystem;

import javax.swing.*;
import java.awt.*;

public class ElevatorSystemView extends JFrame {

    private int nOfElevators = 1;

    public ElevatorSystemView(){

        Font smallFont = new Font("Arial", Font.PLAIN, 20);
        Font bigFont = new Font("Arial", Font.BOLD, 32);

        setTitle("Elevator Controller Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);

        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        JLabel titleLabel = new JLabel("Elevator Controller", SwingConstants.CENTER);
        titleLabel.setFont(bigFont);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nOfElevatorsLabel = new JLabel("Number of Elevators: "+nOfElevators, SwingConstants.CENTER);
        nOfElevatorsLabel.setFont(smallFont);
        nOfElevatorsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSlider elevatorSlider = new JSlider(JSlider.HORIZONTAL, 1, 16, 1);

        elevatorSlider.addChangeListener(e -> {
            nOfElevators = elevatorSlider.getValue();
            nOfElevatorsLabel.setText("Number of Elevators: "+nOfElevators);
        });

        JButton startSimulationButton = new JButton("Start Simulation");
        startSimulationButton.setFont(smallFont);
        startSimulationButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startSimulationButton.addActionListener(e -> {

            ElevatorSystem elevatorSystem = new ElevatorSystem();
            for(int i=0;i<nOfElevators;i++){
                elevatorSystem.addElevator(i+1);
            }

            Thread thread = new Thread(() -> {
                SimulationView sw = new SimulationView(elevatorSystem, 11);
                sw.setVisible(true);
            });
            thread.start();
        });

        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(Box.createVerticalGlue());
        panel.add(elevatorSlider);
        panel.add(nOfElevatorsLabel);
        panel.add(Box.createVerticalGlue());
        panel.add(startSimulationButton);
        panel.add(Box.createVerticalGlue());

        add(panel);

    }



}

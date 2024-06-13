package com.com.org.visualization;


import javax.swing.*;
import java.awt.*;

public class ElevatorSystemWindow extends JFrame {

    private JLabel titleLabel;
    private JSlider elevatorSlider;
    private JLabel nOfElevatorsLabel;
    private JButton startSimulationButton;
    private int nOfElevators = 1;

    public ElevatorSystemWindow(){
        setTitle("Elevator Controller Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);

        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        titleLabel = new JLabel("Elevator Controller", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        nOfElevatorsLabel = new JLabel("Number of Elevators: "+nOfElevators, SwingConstants.CENTER);
        nOfElevatorsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nOfElevatorsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        elevatorSlider = new JSlider(JSlider.HORIZONTAL, 1, 16, 1);

        elevatorSlider.addChangeListener(e -> {
            nOfElevators = elevatorSlider.getValue();
            nOfElevatorsLabel.setText("Number of Elevators: "+nOfElevators);
        });

        startSimulationButton = new JButton("Start Simulation");
        startSimulationButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startSimulationButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startSimulationButton.addActionListener(e -> {
            SimulationWindow sw = new SimulationWindow(nOfElevators);
        });

        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(elevatorSlider);
        panel.add(nOfElevatorsLabel);
        panel.add(startSimulationButton);
        panel.add(Box.createVerticalGlue());

        add(panel);

        setVisible(true);
    }



}

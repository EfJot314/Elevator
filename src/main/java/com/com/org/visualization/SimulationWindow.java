package com.com.org.visualization;


import com.com.org.datastructures.Direction;
import com.com.org.system.ElevatorSystem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SimulationWindow extends JFrame {

    private final ElevatorSystem elevatorSystem;
    private final java.util.List<ElevatorPanel> elevatorPanels = new java.util.ArrayList<>();

    public SimulationWindow(ElevatorSystem elevatorSystem, int nOfFloors){
        this.elevatorSystem = elevatorSystem;

        setTitle("Elevators Simulation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1400, 900);

        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Elevator Simulation");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));

        Panel mainPanel = new Panel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        Panel leftPanel = new Panel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        Panel rightPanel = new Panel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));



        //Floor buttons

        JLabel floorsLabel = new JLabel("Floors");
        floorsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        floorsLabel.setFont(new Font("Arial", Font.BOLD, 25));

        leftPanel.add(floorsLabel);
        leftPanel.add(Box.createVerticalGlue());

        for(int i=nOfFloors-1;i>=0;i--){
            int floorNum = i;

            Panel p = new Panel();
            p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));

            JLabel label = new JLabel("Floor "+floorNum);
            label.setFont(new Font("Arial", Font.PLAIN, 20));

            JButton upButton = new JButton("\u25B2");
            upButton.setFont(new Font("Arial", Font.PLAIN, 20));
            upButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            upButton.addActionListener(e -> {
                elevatorSystem.pickup(floorNum, Direction.UP);
            });

            JButton downButton = new JButton("\u25BC");
            downButton.setFont(new Font("Arial", Font.PLAIN, 20));
            downButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            downButton.addActionListener(e -> {
                elevatorSystem.pickup(floorNum, Direction.DOWN);
            });

            p.add(Box.createHorizontalGlue());
            p.add(label);
            p.add(Box.createHorizontalGlue());
            p.add(upButton);
            p.add(downButton);
            p.add(Box.createHorizontalGlue());

            leftPanel.add(p);
            leftPanel.add(Box.createVerticalStrut(7));
        }

        leftPanel.add(Box.createVerticalGlue());

        //Elevators

        JLabel elevatorsLabel = new JLabel("Elevators");
        elevatorsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        elevatorsLabel.setFont(new Font("Arial", Font.BOLD, 25));

        rightPanel.add(elevatorsLabel);
        rightPanel.add(Box.createVerticalGlue());

        for(int i=0;i<elevatorSystem.getNumberOfElevators();i++){
            ElevatorPanel p = new ElevatorPanel(elevatorSystem.getElevator(i+1), nOfFloors);
            elevatorPanels.add(p);
            rightPanel.add(p);
            rightPanel.add(Box.createVerticalStrut(7));
        }

        rightPanel.add(Box.createVerticalGlue());


        JButton stepButton = new JButton("Next Step");
        stepButton.setFont(new Font("Arial", Font.PLAIN, 20));
        stepButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        stepButton.addActionListener(e -> {
            makeStepAndUpdate();
        });


        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(Box.createVerticalGlue());
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        panel.add(mainPanel);
        panel.add(Box.createVerticalGlue());
        panel.add(stepButton);
        panel.add(Box.createVerticalGlue());

        add(panel);

    }


    void makeStepAndUpdate(){
        elevatorSystem.step();

        for(ElevatorPanel panel : elevatorPanels){
            panel.update();
        }
    }

}

package com.com.org.visualization;


import javax.swing.*;
import java.awt.*;

public class SimulationWindow extends JFrame {

    public SimulationWindow(int nOfElevators){
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

        for(int i=0;i<10;i++){
            Panel p = new Panel();
            p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
            int floorNum = i+1;

            JLabel label = new JLabel("Floor "+floorNum);
            label.setFont(new Font("Arial", Font.PLAIN, 20));

            JButton upButton = new JButton("\u25B2");
            upButton.setFont(new Font("Arial", Font.PLAIN, 20));
            upButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            upButton.addActionListener(e -> {
                System.out.println("Floor "+ floorNum + " up");
            });

            JButton downButton = new JButton("\u25BC");
            downButton.setFont(new Font("Arial", Font.PLAIN, 20));
            downButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            downButton.addActionListener(e -> {
                System.out.println("Floor "+ floorNum + " down");
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

        for(int i=0;i<nOfElevators;i++){
            Panel p = new Panel();
            p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));

            JLabel elevatorLabel = new JLabel("Elevator "+(i+1));
            elevatorLabel.setFont(new Font("Arial", Font.BOLD, 20));
            elevatorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel floorLabel = new JLabel("Floor "+i);
            floorLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            floorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel directionLabel = new JLabel("Direction: DOWN");
            directionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            directionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel destinationLabel = new JLabel("Destination: " + i);
            destinationLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            destinationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton panelButton = new JButton("Button Panel");
            panelButton.setFont(new Font("Arial", Font.PLAIN, 20));
            panelButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            int id = i+1;
            panelButton.addActionListener(e -> {
                Thread thread = new Thread(() -> {
                    ButtonPanelWindow bpw = new ButtonPanelWindow(id, 10);
                    bpw.setVisible(true);
                });
                thread.start();

            });

            p.add(Box.createHorizontalGlue());
            p.add(elevatorLabel);
            p.add(Box.createHorizontalGlue());
            p.add(floorLabel);
            p.add(Box.createHorizontalGlue());
            p.add(directionLabel);
            p.add(Box.createHorizontalGlue());
            p.add(destinationLabel);
            p.add(Box.createHorizontalGlue());
            p.add(panelButton);
            p.add(Box.createHorizontalGlue());

            rightPanel.add(p);
            rightPanel.add(Box.createVerticalStrut(7));
        }

        rightPanel.add(Box.createVerticalGlue());


        JButton stepButton = new JButton("Next Step");
        stepButton.setFont(new Font("Arial", Font.PLAIN, 20));
        stepButton.setAlignmentX(Component.CENTER_ALIGNMENT);


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

}

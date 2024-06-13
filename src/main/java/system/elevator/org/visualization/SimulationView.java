package system.elevator.org.visualization;


import system.elevator.org.framework.datastructures.Direction;
import system.elevator.org.framework.system.ElevatorSystem;

import javax.swing.*;
import java.awt.*;

public class SimulationView extends JFrame {

    private final ElevatorSystem elevatorSystem;
    private final java.util.List<ElevatorPanel> elevatorPanels = new java.util.ArrayList<>();

    public SimulationView(ElevatorSystem elevatorSystem, int nOfFloors){
        this.elevatorSystem = elevatorSystem;

        Font smallFont = new Font("Arial", Font.PLAIN, 20);
        Font mediumFont = new Font("Arial", Font.BOLD, 25);
        Font bigFont = new Font("Arial", Font.BOLD, 32);

        setTitle("Elevator System Simulation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1400, 900);

        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Elevator System Simulation");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(bigFont);

        Panel mainPanel = new Panel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        Panel leftPanel = new Panel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        Panel rightPanel = new Panel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));



        //Floor buttons

        JLabel floorsLabel = new JLabel("Floors");
        floorsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        floorsLabel.setFont(mediumFont);

        leftPanel.add(floorsLabel);
        leftPanel.add(Box.createVerticalGlue());

        for(int i = nOfFloors-1; i>=0; i--){
            int floorNum = i;

            Panel p = new Panel();
            p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));

            JLabel label = new JLabel("Floor "+floorNum);
            label.setFont(smallFont);

            JButton upButton = new JButton("\u25B2");
            upButton.setFont(smallFont);
            upButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            upButton.addActionListener(e -> {
                elevatorSystem.pickup(floorNum, Direction.UP);
            });

            JButton downButton = new JButton("\u25BC");
            downButton.setFont(smallFont);
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
        elevatorsLabel.setFont(mediumFont);

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
        stepButton.setFont(smallFont);
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

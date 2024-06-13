package com.com.org.visualization;

import com.com.org.elevator.Elevator;

import javax.swing.*;
import java.awt.*;

public class ElevatorPanel extends Panel {

    private final Elevator elevator;
    private final JLabel floorLabel;
    private final JLabel directionLabel;
    private final JLabel destinationLabel;

    public ElevatorPanel(Elevator elevator, int nOfFloors) {
        this.elevator = elevator;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JLabel elevatorLabel = new JLabel("Elevator "+elevator.id);
        elevatorLabel.setFont(new Font("Arial", Font.BOLD, 20));
        elevatorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        floorLabel = new JLabel("Floor: " + elevator.getCurrentFloor());
        floorLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        floorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        directionLabel = new JLabel("Direction: " + elevator.getDirection());
        directionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        directionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        destinationLabel = new JLabel("Destination: " + elevator.getDestinationFloor());
        destinationLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        destinationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton panelButton = new JButton("Button Panel");
        panelButton.setFont(new Font("Arial", Font.PLAIN, 20));
        panelButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        ButtonPanelWindow bpw = new ButtonPanelWindow(elevator, nOfFloors);

        panelButton.addActionListener(e -> {
//            Thread thread = new Thread(() -> {
//                bpw.setVisible(true);
//            });
//            thread.start();
            bpw.setVisible(true);
        });

        add(Box.createHorizontalGlue());
        add(elevatorLabel);
        add(Box.createHorizontalGlue());
        add(floorLabel);
        add(Box.createHorizontalGlue());
        add(directionLabel);
        add(Box.createHorizontalGlue());
        add(destinationLabel);
        add(Box.createHorizontalGlue());
        add(panelButton);
        add(Box.createHorizontalGlue());
    }

    public void update(){
        floorLabel.setText("Floor: " + elevator.getCurrentFloor());
        directionLabel.setText("Direction: " + elevator.getDirection());
        destinationLabel.setText("Destination: " + elevator.getDestinationFloor());
    }



}

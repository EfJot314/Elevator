package com.com.org.visualization;

import com.com.org.framework.interfaces.IElevator;

import javax.swing.*;
import java.awt.*;

public class ElevatorPanel extends Panel {

    private final IElevator elevator;
    private final JLabel floorLabel;
    private final JLabel directionLabel;
    private final JLabel destinationLabel;

    public ElevatorPanel(IElevator elevator, int nOfFloors) {
        this.elevator = elevator;

        Font smallFont = new Font("Arial", Font.PLAIN, 20);
        Font smallBoldFont = new Font("Arial", Font.BOLD, 20);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JLabel elevatorLabel = new JLabel("Elevator "+elevator.getId());
        elevatorLabel.setFont(smallBoldFont);
        elevatorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        floorLabel = new JLabel("Floor: " + elevator.getCurrentFloor());
        floorLabel.setFont(smallFont);
        floorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        directionLabel = new JLabel("Direction: " + elevator.getDirection());
        directionLabel.setFont(smallFont);
        directionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        destinationLabel = new JLabel("Destination: " + elevator.getDestinationFloor());
        destinationLabel.setFont(smallFont);
        destinationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton panelButton = new JButton("Button Panel");
        panelButton.setFont(smallFont);
        panelButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        ButtonPanelView bpw = new ButtonPanelView(elevator, nOfFloors);

        panelButton.addActionListener(e -> {
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

        //green background if open door
        if(elevator.isDoorOpen()){
            setBackground(Color.GREEN);
        }
        else{
            setBackground(null);
        }
    }



}

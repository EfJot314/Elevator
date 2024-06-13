package com.com.org.visualization;

import com.com.org.framework.interfaces.IElevator;

import javax.swing.*;
import java.awt.*;

public class ButtonPanelView extends JFrame {


    public ButtonPanelView(IElevator elevator, int nOfFloors){

        Font smallFont = new Font("Arial", Font.PLAIN, 20);
        Font bigFont = new Font("Arial", Font.BOLD, 32);

        setTitle("Elevator "+elevator.getId()+" Button Panel");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(500, 700);

        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Elevator "+elevator.getId());
        label.setFont(bigFont);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);


        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createVerticalGlue());
        for(int i=nOfFloors-1;i>=0;i--){
            int floor = i;

            JButton button = new JButton(String.valueOf(floor));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setFont(smallFont);

            button.addActionListener(e -> {
                elevator.goTo(floor);
            });

            panel.add(button);
        }
        panel.add(Box.createVerticalGlue());

        add(panel);
    }



}
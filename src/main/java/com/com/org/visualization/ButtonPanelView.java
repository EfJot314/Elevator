package com.com.org.visualization;

import com.com.org.elevator.Elevator;

import javax.swing.*;
import java.awt.*;

public class ButtonPanelView extends JFrame {


    public ButtonPanelView(Elevator elevator, int nOfFloors){
        setTitle("Elevator "+elevator.id+" Button Panel");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(500, 700);

        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Elevator "+elevator.id);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);


        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createVerticalGlue());
        for(int i=nOfFloors-1;i>=0;i--){
            int floor = i;

            JButton button = new JButton(String.valueOf(floor));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setFont(new Font("Arial", Font.PLAIN, 20));

            button.addActionListener(e -> {
                elevator.goTo(floor);
            });

            panel.add(button);
        }
        panel.add(Box.createVerticalGlue());

        add(panel);
    }



}
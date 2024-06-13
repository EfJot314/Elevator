package com.com.org.visualization;

import javax.swing.*;
import java.awt.*;

public class ButtonPanelWindow extends JFrame {


    public ButtonPanelWindow(int id, int nOfFloors){
        setTitle("Elevator "+id+" Button Panel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 700);

        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Elevator "+id);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);


        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createVerticalGlue());
        for(int i=0;i<nOfFloors;i++){
            JButton button = new JButton(String.valueOf((i+1)));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setFont(new Font("Arial", Font.PLAIN, 20));

            panel.add(button);
        }
        panel.add(Box.createVerticalGlue());

        add(panel);
    }



}
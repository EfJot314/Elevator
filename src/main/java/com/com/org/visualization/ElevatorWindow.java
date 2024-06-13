package com.com.org.visualization;


import javax.swing.*;
import java.awt.*;

public class ElevatorWindow extends JFrame {

    private JLabel floorDisplay;

    public ElevatorWindow(){
        setTitle("Elevator Controller Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Elevator Controller", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        add(title, BorderLayout.NORTH);


        setVisible(true);
    }



}

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class guiDriver extends JPanel {
    private JButton genButton;
    private JTextField textBox;
    private ArrayList<String> strArray;

    public guiDriver() {
        //construct components
        genButton = new JButton ("Scan text");
        /**
         * text fields
         */
        textBox = new JTextField (50);


        //adjust size and set layout
        setPreferredSize (new Dimension (1920, 1080));
        setLayout (null);

        //add components
        add (genButton);
        add (textBox);


        //set component bounds (only needed by Absolute Positioning)
        genButton.setBounds (775, 350, 270, 50);
        textBox.setBounds (0, 0, 500, 800);

        //Button Actions
        genButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                //on button push code

                

            }
        });

    }



    public static void main (String[] args) {
        JFrame frame = new JFrame ("gui3");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new guiDriver());
        frame.pack();
        frame.setVisible (true);


    }
}

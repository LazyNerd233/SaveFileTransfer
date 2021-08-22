package saveFileTransfer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.AncestorListener;

public class GUI implements ActionListener
{
    private JFrame frame;
    private JPanel panel;
    private JButton transferButton;
    private JLabel label;
    private JDialog errorlog;
    private JButton errorButton;
    private int numClicks = 0;
    DataTransfer dataTransfer = new DataTransfer();   
    
    public static void main(String[] args)
    {
        new GUI();
        
    }
    
    public GUI() 
    {
        frame = new JFrame();
        panel = new JPanel();
        transferButton = new JButton("Transfer your save data");
        transferButton.addActionListener(this); 
        label = new JLabel("number of save files successfully transfer: 0");
        
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
        panel.add(transferButton);
                         
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Data Transfer");
        frame.setLocation(520, 220);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {     
        File srcDir = new File("E:\\Steam\\userdata\\312769744\\262060\\remote");
        File destDir = new File("C:\\Users\\alfre\\Desktop\\Darkest dungeon save file");
        try {
            dataTransfer.copydir(srcDir, destDir);
            numClicks++;
        }
        catch (IOException e) {
            //e.printStackTrace();
            // pop up a window tell it can't find the source path
            if (!srcDir.exists())
            {
                errorMessage();
            }
        }
        label.setText("number of save files successfully transfer: " + numClicks);
    }
    
    public void errorMessage() 
    {
        errorlog = new JDialog(frame);
        errorlog.setTitle("Error on transfer the file");
        errorlog.setSize(300, 200);
        errorlog.setLocation(810, 360);
        JPanel errorPanel = new JPanel();
        errorButton = new JButton("comfirm");
        errorButton.addActionListener(
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.out.println("close the error warning");
                    errorlog.dispose();
                }
            }
        );  
        errorPanel.add(errorButton);
        errorlog.add(errorPanel);
        System.out.println("Couldn't Find the import match Path");
        errorlog.setVisible(true);    
    }
}

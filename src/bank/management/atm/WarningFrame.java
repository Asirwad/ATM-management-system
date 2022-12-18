package bank.management.atm;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class WarningFrame extends JFrame implements ActionListener{
    JButton cancelBut,yesBut;
    private int flag=-1;
    WarningFrame(){
        setSize(300,200);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        
        //background image
        getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(56,182,255)));
        setContentPane(new JLabel(new ImageIcon(ClassLoader.getSystemResource("icons/warningFrameBackground.png"))));
        
        //setting icon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bankIcon.png"));
        setIconImage(i1.getImage());
        
        //BUTTONS
        cancelBut = new JButton("Cancel");
        cancelBut.setFont(new Font("Railway",Font.BOLD,15));
        cancelBut.setForeground(new Color(56,182,255));
        cancelBut.setBackground(Color.WHITE);
        cancelBut.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(56,182,255)));
        cancelBut.setBorderPainted(false);
        cancelBut.setBounds(30,142,80,30);
        cancelBut.setFocusable(false);
        cancelBut.addActionListener(this);
        cancelBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt){
                flag = 0;
            }
            @Override
            public void mouseEntered(MouseEvent evt){
                cancelBut.setBorderPainted(true);
                cancelBut.setForeground(Color.WHITE);
                cancelBut.setBackground(new Color(56,182,255));
            }
            @Override
            public void mouseExited(MouseEvent evt){
                cancelBut.setBackground(Color.WHITE);
                cancelBut.setBorderPainted(false);
                cancelBut.setForeground(new Color(56,182,255));
            }
        });
        add(cancelBut);
        
        yesBut = new JButton("Yes");
        yesBut.setFont(new Font("Railway",Font.BOLD,15));
        yesBut.setForeground(Color.WHITE);
        yesBut.setBackground(new Color(56,182,255));
        yesBut.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(56,182,255)));
        yesBut.setFocusable(false);
        yesBut.setBorderPainted(false);
        yesBut.setBounds(190,142,80,30);
        yesBut.addActionListener(this);
        yesBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt){
                flag = 1;
            }
            @Override
            public void mouseEntered(MouseEvent evt){
                yesBut.setBorderPainted(true);
                yesBut.setBackground(Color.WHITE);
                yesBut.setForeground(new Color(56,182,255));
            }
            @Override
            public void mouseExited(MouseEvent evt){
                yesBut.setForeground(Color.WHITE);
                yesBut.setBorderPainted(false);
                yesBut.setBackground(new Color(56,182,255));
            }
        });
        add(yesBut);
        setVisible(true);
    }
    
    public int getReturnVal(){ 
        return(flag);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == yesBut){
            
        }else if(ae.getSource() == cancelBut){
            
        }
    }
    public static void main(String args[]) {
        new WarningFrame();
    }
}

package bank.management.atm;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener{
    JButton backBut;
    String cardNo,pinNo;
    BalanceEnquiry(String pinNo,String cardNo){
        this.cardNo = cardNo;
        this.pinNo = pinNo;
        setLayout(null);
        setSize(800,750);
        Font cochinFont = new Font("Cochin",Font.BOLD,20);
        Font railwayFont = new Font("Railway",Font.BOLD,35);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 750, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel bgImg = new JLabel(i3);
        bgImg.setBounds(0,0,800,750);
        add(bgImg);
        
        JLabel heading = new JLabel("Your available balance is.");
        heading.setForeground(Color.white);
        heading.setFont(cochinFont);
        heading.setBounds(165,330,700,45);
        bgImg.add(heading);
                
        String query =  "select balance from account where cardno = '"+cardNo+"';";
        Conn conn = new Conn();
        try{
            ResultSet rs = conn.s.executeQuery(query);
            rs.next();
            int balance = rs.getInt("balance");
            JLabel balanceLabel = new JLabel("Rs "+balance+"/-");
            balanceLabel.setBounds(165,355,400,50);
            balanceLabel.setForeground(Color.white);
            balanceLabel.setFont(railwayFont);
            bgImg.add(balanceLabel);
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
        
        backBut = new JButton("Back");
        backBut.setBounds(368,468,90,25);
        backBut.setBackground(Color.lightGray);
        backBut.setForeground(Color.BLACK);
        backBut.setFocusable(false);
        backBut.setBorderPainted(false);
        backBut.addActionListener(this);
        backBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                backBut.setBackground(Color.yellow);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                backBut.setBackground(Color.lightGray);
            }
        });
        bgImg.add(backBut);      
                
        setLocation(300,50);
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == backBut){
            setVisible(false);
            new Transactions(pinNo,cardNo).setVisible(true);
        }
    }
    public static void main(String args[]) {
        new BalanceEnquiry("","");
    }
}

package bank.management.system;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Date;
import java.sql.*;

public class Withdraw extends JFrame implements ActionListener {
    JTextField amountTextField;
    JButton withdrawBut,backBut;
    String pinNo,cardNo;
    Withdraw(String pinNo,String cardNo){
        this.pinNo = pinNo;
        this.cardNo = cardNo;
        setLayout(null);
        setSize(800,750);
        Font systemFont = new Font("System",Font.BOLD,16);
        Font railwayFont = new Font("Railway",Font.BOLD,16);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 750, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel bgImg = new JLabel(i3);
        bgImg.setBounds(0,0,800,750);
        add(bgImg);
        
        JLabel text = new JLabel("Enter the amount you want to withdraw");
        text.setBounds(157,270,700,35);
        text.setFont(systemFont);
        text.setForeground(Color.WHITE);
        bgImg.add(text);
        
        amountTextField = new JTextField();
        amountTextField.setFont(railwayFont);
        amountTextField.setBounds(150,350,300,30);
        amountTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        bgImg.add(amountTextField);
        
        withdrawBut = new JButton("Withdraw");
        withdrawBut.setBounds(368,435,90,25);
        withdrawBut.setBackground(Color.WHITE);
        withdrawBut.setForeground(Color.BLACK);
        withdrawBut.setFocusable(false);
        withdrawBut.setBorderPainted(false);
        withdrawBut.addActionListener(this);
        withdrawBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                withdrawBut.setBackground(Color.green);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                withdrawBut.setBackground(Color.WHITE);
            }
        });
        bgImg.add(withdrawBut);
        
        backBut = new JButton("Back");
        backBut.setBounds(368,465,90,25);
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
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == backBut){
            setVisible(false);
            new Transactions(pinNo,cardNo).setVisible(true);
        }else if(ae.getSource() == withdrawBut){
            String amount = amountTextField.getText();
            Date date = new Date();
            if(amount.equals("") || Integer.parseInt(amount)<=0){
                JOptionPane.showMessageDialog(null,"Please enter a valid amount you want to withdraw");
            }else{
                Conn conn = new Conn();
                int intAmount = Integer.parseInt(amount);
                String queryBank = "insert into bank values('"+cardNo+"','"+pinNo+"','"+date+"','Withdrawl','"+amount+"');";
                String balanceCheckQuery = "select balance from account where cardno = '"+cardNo+"'";
                String accountQuery = "insert into account values('"+cardNo+"','"+pinNo+"','"+intAmount+"') ON DUPLICATE KEY UPDATE balance = balance - '"+intAmount+"';";
                try{
                    ResultSet rs = conn.s.executeQuery(balanceCheckQuery);
                    rs.next();
                    int balance = rs.getInt("balance");
                    if(balance <=0 || balance-Integer.parseInt(amount)<0){
                        JOptionPane.showMessageDialog(null,"Not eneough balance");
                    }else{
                        try {
                            conn.s.executeUpdate(queryBank);
                            conn.s.executeUpdate(accountQuery);
                            JOptionPane.showMessageDialog(null,"Rs"+amount+" Withdrawed successfully");
                            setVisible(false);
                            new Transactions(pinNo,cardNo).setVisible(true);
                        }catch (SQLException ex2){
                             System.out.println(ex2);
                        }
                    }
                }catch(SQLException ex1){
                    System.out.println(ex1);
                }
            }
        }
    }
    public static void main(String args[]){
        new Withdraw("","");
    }
}

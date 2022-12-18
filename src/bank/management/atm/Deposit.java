package bank.management.atm;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    JTextField amountTextField;
    JButton depositBut,backBut;
    String pinNo,cardNo;
    Deposit(String pinNo,String cardNo){
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
        
        JLabel text = new JLabel("Enter the amount you want to deposit");
        text.setBounds(157,270,700,35);
        text.setFont(systemFont);
        text.setForeground(Color.WHITE);
        bgImg.add(text);
        
        amountTextField = new JTextField();
        amountTextField.setFont(railwayFont);
        amountTextField.setBounds(150,350,300,30);
        amountTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        bgImg.add(amountTextField);
        
        depositBut = new JButton("Deposit");
        depositBut.setBounds(372,435,80,25);
        depositBut.setBackground(Color.WHITE);
        depositBut.setForeground(Color.BLACK);
        depositBut.setFocusable(false);
        depositBut.setBorderPainted(false);
        depositBut.addActionListener(this);
        depositBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                depositBut.setBackground(Color.green);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                depositBut.setBackground(Color.WHITE);
            }
        });
        bgImg.add(depositBut);
        
        backBut = new JButton("Back");
        backBut.setBounds(372,465,80,25);
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
        }else if(ae.getSource() == depositBut){
            String amount = amountTextField.getText();
            Date date = new Date();
            if(amount.equals("") || Integer.parseInt(amount)<=0){
                JOptionPane.showMessageDialog(null,"Please enter a valid amount you want to deposit");
            }else{
                Conn conn = new Conn();
                String query = "insert into bank values('"+cardNo+"','"+pinNo+"','"+date+"','Deposit','"+amount+"');";
                int intAmount = Integer.parseInt(amount);
                String accountQuery = "insert into account values('"+cardNo+"','"+pinNo+"','"+intAmount+"') ON DUPLICATE KEY UPDATE balance = balance + '"+intAmount+"';";
                try {
                    conn.s.executeUpdate(query);
                    conn.s.executeUpdate(accountQuery);
                    JOptionPane.showMessageDialog(null,"Rs"+amount+" Deposited successfully");
                    setVisible(false);
                    new Transactions(pinNo,cardNo).setVisible(true);
                } catch (SQLException ex) {
                    System.out.println(cardNo);
                    System.out.println(ex);
                }
            }
        }
    }
    public static void main(String args[]) {
        new Deposit("","");
    }
}

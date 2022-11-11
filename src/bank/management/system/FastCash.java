package bank.management.system;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Date;


public class FastCash extends JFrame implements ActionListener {
    JButton backBut,rs100But,rs500But,rs1000But,rs2000But,rs5000But,rs10000But,withdrawBut;
    String pinNo,cardNo;
    FastCash(String pinNo,String cardNo){
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
        
        JLabel text = new JLabel("SELECT WITHDRAWL AMOUNT");
        text.setBounds(157,270,700,35);
        text.setFont(systemFont);
        text.setForeground(Color.WHITE);
        bgImg.add(text);
        
        
        rs100But = new JButton("Rs 100");
        rs100But.setBounds(150,383,120,20);
        rs100But.setBackground(Color.WHITE);
        rs100But.setForeground(Color.BLACK);
        rs100But.setFocusable(false);
        rs100But.setBorderPainted(false);
        rs100But.addActionListener(this);
        rs100But.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                rs100But.setBackground(Color.green);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                rs100But.setBackground(Color.WHITE);
            }
        });
        bgImg.add(rs100But);
        
        rs500But = new JButton("Rs 500");
        rs500But.setBounds(150,412,120,20);
        rs500But.setBackground(Color.WHITE);
        rs500But.setForeground(Color.BLACK);
        rs500But.setFocusable(false);
        rs500But.setBorderPainted(false);
        rs500But.addActionListener(this);
        rs500But.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                rs500But.setBackground(Color.green);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                rs500But.setBackground(Color.WHITE);
            }
        });
        bgImg.add(rs500But);
        
        rs1000But = new JButton("Rs 1000");
        rs1000But.setBounds(150,442,120,20);
        rs1000But.setBackground(Color.WHITE);
        rs1000But.setForeground(Color.BLACK);
        rs1000But.setFocusable(false);
        rs1000But.setBorderPainted(false);
        rs1000But.addActionListener(this);
        rs1000But.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                rs1000But.setBackground(Color.green);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                rs1000But.setBackground(Color.WHITE);
            }
        });
        bgImg.add(rs1000But);
        
        rs2000But = new JButton("Rs 2000");
        rs2000But.setBounds(150,470,120,20);
        rs2000But.setBackground(Color.WHITE);
        rs2000But.setForeground(Color.BLACK);
        rs2000But.setFocusable(false);
        rs2000But.setBorderPainted(false);
        rs2000But.addActionListener(this);
        rs2000But.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                rs2000But.setBackground(Color.green);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                rs2000But.setBackground(Color.WHITE);
            }
        });
        bgImg.add(rs2000But);
        
        rs5000But = new JButton("Rs 5000");
        rs5000But.setBounds(338,383,120,20);
        rs5000But.setBackground(Color.WHITE);
        rs5000But.setForeground(Color.BLACK);
        rs5000But.setFocusable(false);
        rs5000But.setBorderPainted(false);
        rs5000But.addActionListener(this);
        rs5000But.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                rs5000But.setBackground(Color.green);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                rs5000But.setBackground(Color.WHITE);
            }
        });
        bgImg.add(rs5000But);
        
        rs10000But = new JButton("Rs 10000");
        rs10000But.setBounds(338,412,120,20);
        rs10000But.setBackground(Color.WHITE);
        rs10000But.setForeground(Color.BLACK);
        rs10000But.setFocusable(false);
        rs10000But.setBorderPainted(false);
        rs10000But.addActionListener(this);
        rs10000But.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                rs10000But.setBackground(Color.green);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                rs10000But.setBackground(Color.WHITE);
            }
        });
        bgImg.add(rs10000But);
        
        
        
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
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == backBut){
            setVisible(false);
            new Transactions(pinNo,cardNo).setVisible(true);
        }else{
            Date date = new Date();
            String amount = ((JButton)ae.getSource()).getText().substring(3);
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
    public static void main(String args[]) {
        new FastCash("","");
    }
}

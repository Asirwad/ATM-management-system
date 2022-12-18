package bank.management.atm;
import java.awt.*;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;


public class Transactions extends JFrame implements ActionListener{
    JButton depositBut,withdrawBut,fastcashBut,miniStatementBut,balanceBut,exitBut,pinChangeBut;
    String pinNo,cardNo;
    Transactions(String pinNo,String cardNo){
        this.pinNo = pinNo;
        this.cardNo = cardNo;
        setLayout(null);
        Font systemFont = new Font("System",Font.BOLD,16);
        Font railwayFont = new Font("Railway",Font.BOLD,16);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 750, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel bgImg = new JLabel(i3);
        bgImg.setBounds(0,0,800,750);
        add(bgImg);
        
        setLocation(300,50);
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setContentPane(new JLabel(new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"))));
        
        JLabel text = new JLabel("Please select your transaction");
        text.setBounds(185,270,700,35);
        text.setFont(systemFont);
        text.setForeground(Color.WHITE);
        bgImg.add(text);
        
        depositBut = new JButton("Deposit");
        depositBut.setBounds(150,383,120,20);
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
        
        withdrawBut = new JButton("Withdraw");
        withdrawBut.setBounds(150,412,120,20);
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
        
        fastcashBut = new JButton("Fast cash");
        fastcashBut.setBounds(150,442,120,20);
        fastcashBut.setBackground(Color.WHITE);
        fastcashBut.setForeground(Color.BLACK);
        fastcashBut.setFocusable(false);
        fastcashBut.setBorderPainted(false);
        fastcashBut.addActionListener(this);
        fastcashBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                fastcashBut.setBackground(Color.green);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                fastcashBut.setBackground(Color.WHITE);
            }
        });
        bgImg.add(fastcashBut);
        
        miniStatementBut = new JButton("Mini Statement");
        miniStatementBut.setBounds(150,470,120,20);
        miniStatementBut.setBackground(Color.WHITE);
        miniStatementBut.setForeground(Color.BLACK);
        miniStatementBut.setFocusable(false);
        miniStatementBut.setBorderPainted(false);
        miniStatementBut.addActionListener(this);
        miniStatementBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                miniStatementBut.setBackground(Color.green);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                miniStatementBut.setBackground(Color.WHITE);
            }
        });
        bgImg.add(miniStatementBut);
        
        balanceBut = new JButton("Balance check");
        balanceBut.setBounds(330,383,120,20);
        balanceBut.setBackground(Color.WHITE);
        balanceBut.setForeground(Color.BLACK);
        balanceBut.setFocusable(false);
        balanceBut.setBorderPainted(false);
        balanceBut.addActionListener(this);
        balanceBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                balanceBut.setBackground(Color.green);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                balanceBut.setBackground(Color.WHITE);
            }
        });
        bgImg.add(balanceBut);
        
        pinChangeBut = new JButton("Pin Change");
        pinChangeBut.setBounds(330,412,120,20);
        pinChangeBut.setBackground(Color.WHITE);
        pinChangeBut.setForeground(Color.BLACK);
        pinChangeBut.setFocusable(false);
        pinChangeBut.setBorderPainted(false);
        pinChangeBut.addActionListener(this);
        pinChangeBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                pinChangeBut.setBackground(Color.green);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                pinChangeBut.setBackground(Color.WHITE);
            }
        });
        bgImg.add(pinChangeBut);
        
        exitBut = new JButton("Exit");
        exitBut.setBounds(330,442,120,20);
        exitBut.setBackground(Color.WHITE);
        exitBut.setForeground(Color.BLACK);
        exitBut.setFocusable(false);
        exitBut.setBorderPainted(false);
        exitBut.addActionListener(this);
        exitBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                exitBut.setBackground(Color.yellow);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                exitBut.setBackground(Color.WHITE);
            }
        });
        bgImg.add(exitBut);
        
        setSize(800,750);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == exitBut){
            setVisible(false);
            new Login();
        }else if(ae.getSource() == depositBut){
            setVisible(false);
            new Deposit(pinNo,cardNo).setVisible(true);
        }else if(ae.getSource() == withdrawBut){
            setVisible(false);
            new Withdraw(pinNo,cardNo).setVisible(true);
        }else if(ae.getSource() == fastcashBut){
            setVisible(false);
            new FastCash(pinNo,cardNo).setVisible(true);
        }else if(ae.getSource() == balanceBut){
            setVisible(false);
            new BalanceEnquiry(pinNo,cardNo).setVisible(true);   
        }else if(ae.getSource() == pinChangeBut){
            setVisible(false);
            new PinChange(pinNo,cardNo).setVisible(true);
        }else if(ae.getSource() == miniStatementBut){
            new MiniStatement(pinNo,cardNo).setVisible(true);
        }
    }
    public static void main(String args[]){
        new Transactions("","");
    }
}

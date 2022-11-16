package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JButton ClrBut,loginBut,signUpBut;
    JTextField cardTextField;
    JPasswordField pinTextField;
    JLabel closeLabel,signUpLabel,adminLoginlabel;
    Login(){
        setTitle("AUTOMATED TELLER MACHINE");
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setLocationRelativeTo(null);
       
        //BACKGROUND IMAGE
        setContentPane(new JLabel(new ImageIcon(ClassLoader.getSystemResource("icons/loginBackground.png"))));
        
        //Labels
        JLabel mainHeadingText = new JLabel("Welcome!");
        mainHeadingText.setForeground(Color.WHITE);
        mainHeadingText.setBounds(480,50,400,40);
        mainHeadingText.setFont(new Font("Osward",Font.BOLD,30));
        add(mainHeadingText);
        
        JLabel dtHaveAcntText = new JLabel("Dont have an account yet?");
        dtHaveAcntText.setBounds(480,85,200,30);
        dtHaveAcntText.setFont(new Font("Railway",Font.ROMAN_BASELINE,13));
        dtHaveAcntText.setForeground(Color.WHITE);
        add(dtHaveAcntText);
        
        signUpLabel = new JLabel("<html><u>Sign Up</u></html>");
        signUpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signUpLabel.setBounds(633,85,60,30);
        signUpLabel.setFont(new Font("Railway",Font.BOLD,13));
        signUpLabel.setForeground(Color.WHITE);
        signUpLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt){
                signUpEvent(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt){
                signUpLabel.setFont(new Font("Railway",Font.BOLD,15));
            }
            @Override
            public void mouseExited(MouseEvent evt){
                signUpLabel.setFont(new Font("Railway",Font.BOLD,13));
            }
        });
        add(signUpLabel);

        JLabel cardNum = new JLabel("Card Number");
        cardNum.setBounds(480,127,100,30);
        cardNum.setForeground(Color.white);
        cardNum.setFont(new Font("Dialog",Font.PLAIN,12));
        add(cardNum);
        
        JLabel pinNum = new JLabel("PIN Number");
        pinNum.setBounds(480,197,100,30);
        pinNum.setForeground(Color.white);
        pinNum.setFont(new Font("Dialog",Font.PLAIN,12));
        add(pinNum);
        
        closeLabel = new JLabel("X");
        closeLabel.setBounds(760,10,30,30);
        closeLabel.setFont(new Font("Railway",Font.BOLD,30));
        closeLabel.setForeground(Color.WHITE);
        closeLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt){
                closeLabelEvent(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt){
                closeLabel.setForeground(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                closeLabel.setForeground(Color.WHITE);
            }
        });
        add(closeLabel);
        
        //TextFields
        cardTextField = new JTextField();
        cardTextField.setBounds(480,155,220,30);
        cardTextField.setFont(new Font("Ariel",Font.BOLD,14));
        cardTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(cardTextField);
        
        pinTextField = new JPasswordField();
        pinTextField.setBounds(480,225,220,30);
        pinTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        pinTextField.setFont(new Font("Ariel",Font.BOLD,14));
        add(pinTextField);
        
        //BUTTONS
        loginBut = new JButton("SIGN IN");
        loginBut.setBounds(480,300,220,30);
        loginBut.setBackground(new Color(123,150,212));
        loginBut.setForeground(Color.WHITE);
        loginBut.setFocusable(false);
        loginBut.setBorderPainted(false);
        loginBut.addActionListener(this);
        loginBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                loginBut.setBackground(Color.green);
                loginBut.setForeground(Color.BLACK);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                loginBut.setBackground(new Color(123,150,212));
                loginBut.setForeground(Color.WHITE);
            }
        });
        add(loginBut);
        
        ClrBut = new JButton("CLEAR");
        ClrBut.setBounds(480,340,220,30);
        ClrBut.setBackground(new Color(123,150,212));
        ClrBut.setForeground(Color.WHITE);
        ClrBut.setFocusable(false);
        ClrBut.setBorderPainted(false);
        ClrBut.addActionListener(this);
        ClrBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                ClrBut.setBackground(Color.DARK_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                ClrBut.setBackground(new Color(123,150,212));
            }
        });
        add(ClrBut);
        
        adminLoginlabel = new JLabel("Admin Login");
        adminLoginlabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        adminLoginlabel.setBounds(550,450,100,30);
        adminLoginlabel.setFont(new Font("Railway",Font.PLAIN,13));
        adminLoginlabel.setForeground(Color.WHITE);
        adminLoginlabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt){
                adminLoginEvent(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt){
                adminLoginlabel.setText("<html><u>Admin Login</u></html>");
            }
            @Override
            public void mouseExited(MouseEvent evt){
                adminLoginlabel.setText("Admin Login");
            }
        });
        add(adminLoginlabel);

        setSize(800,480);
        setVisible(true);
        setLocation(350,200);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == ClrBut){
            cardTextField.setText("");
            pinTextField.setText("");
        }
        else if(ae.getSource() == loginBut){
            Conn conn = new Conn();
            String cardNo = cardTextField.getText();
            String pinNo = pinTextField.getText();
            String query = "select * from login where cardnumber = '"+cardNo+"' and pin = '"+pinNo+"'";
            try{
                ResultSet rs = conn.s.executeQuery(query);
                if(rs.next()){
                    setVisible(false);
                    new Transactions(pinNo,cardNo).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid credentials");
                }
            }catch(Exception e){
                //do nothing, have a kitkat
            }
        }
        
    }
    public void closeLabelEvent(MouseEvent evt){//CLOSE OPERATION
        int closeLabelChoice = JOptionPane.YES_NO_OPTION;
        closeLabelChoice = JOptionPane.showConfirmDialog(null,"Are you sure want to exit?","Warning",closeLabelChoice);
        if(closeLabelChoice == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
    public void signUpEvent(MouseEvent evt){//Sign up
        setVisible(false);
        new SignUpOne().setVisible(true);
    }
    public void adminLoginEvent(MouseEvent evt){
        setVisible(false);
        new AdminLogin().setVisible(true);
    }
    public static void main(String[] args) {
        Login login = new Login();
        
    }
}


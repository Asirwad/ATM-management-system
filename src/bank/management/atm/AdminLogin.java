package bank.management.atm;

import bank.management.dash.Dashboard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;
import javafx.application.Application;
import javax.swing.border.LineBorder;

public class AdminLogin extends JFrame implements ActionListener{
    JTextField useridText;
    JPasswordField passwordText;
    JLabel closeLabel;
    JButton clearBut,signInBut,backBut;
    public AdminLogin(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setLayout(null);
        setLocationRelativeTo(null);
        
        //BACKGROUND IMAGE
        Random r = new Random();
        if(r.nextInt()%2==0){
            setContentPane(new JLabel(new ImageIcon(ClassLoader.getSystemResource("icons/adminLoginBlackBackground.gif"))));
        }else setContentPane(new JLabel(new ImageIcon(ClassLoader.getSystemResource("icons/adminLoginBackground.gif"))));
    
        //CLOSE
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

        //TEXTFIELDS
        useridText = new JTextField();
        useridText.setBounds(446,125,265,35);
        useridText.setForeground(Color.BLACK);
        useridText.setBackground(Color.WHITE);
        useridText.setFont(new Font("Ariel",Font.BOLD,17));
        useridText.setBorder(new LineBorder(Color.BLACK,2));
        add(useridText);
        
        passwordText = new JPasswordField();
        passwordText.setBounds(446,195,265,35);
        passwordText.setForeground(Color.BLACK);
        passwordText.setBackground(Color.WHITE);
        passwordText.setFont(new Font("Ariel",Font.BOLD,17));
        passwordText.setBorder(new LineBorder(Color.BLACK,2));
        add(passwordText);
        
        //BUTTONS
        signInBut = new JButton("SIGN IN");
        signInBut.setBounds(446,260,265,35);
        signInBut.setForeground(Color.WHITE);
        signInBut.setBackground(Color.BLACK);
        signInBut.addActionListener(this);
        signInBut.setFont(new Font("Ariel",Font.BOLD,17));
        signInBut.setFocusable(false);
        signInBut.setBorderPainted(false);
        signInBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                signInBut.setBorderPainted(true);
                signInBut.setBorder(new LineBorder(Color.BLACK,2));
                signInBut.setBackground(Color.WHITE);
                signInBut.setForeground(Color.BLACK);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                signInBut.setBorderPainted(false);
                signInBut.setBackground(Color.BLACK);
                signInBut.setForeground(Color.WHITE);
            }
        });
        add(signInBut);
        
        backBut = new JButton("BACK");
        backBut.setBounds(446,305,130,30);
        backBut.setFocusable(false);
        backBut.setBorder(new LineBorder(Color.BLACK,2));
        backBut.setForeground(Color.BLACK);
        backBut.setBackground(Color.WHITE);
        backBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                backBut.setBorderPainted(false);
                backBut.setForeground(Color.WHITE);
                backBut.setBackground(Color.BLACK);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                backBut.setBorderPainted(true);
                backBut.setForeground(Color.BLACK);
                backBut.setBackground(Color.WHITE);
            }
            @Override
            public void mouseClicked(MouseEvent evt){
                setVisible(false);
                new Login().setVisible(true);
            }
        });
        add(backBut);
        
        clearBut = new JButton("CLEAR");
        clearBut.setBounds(581,305,130,30);
        clearBut.setFocusable(false);
        clearBut.setBorder(new LineBorder(Color.BLACK,2));
        clearBut.setForeground(Color.BLACK);
        clearBut.setBackground(Color.WHITE);
        clearBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                clearBut.setBorderPainted(false);
                clearBut.setForeground(Color.WHITE);
                clearBut.setBackground(Color.BLACK);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                clearBut.setBorderPainted(true);
                clearBut.setForeground(Color.BLACK);
                clearBut.setBackground(Color.WHITE);
            }
            @Override
            public void mouseClicked(MouseEvent evt){
                useridText.setText("");
                passwordText.setText("");
            }
        });
        add(clearBut);
    
        setSize(800,480);
        setVisible(true);
        setLocation(350,200);
    }
    public void closeLabelEvent(MouseEvent evt){//CLOSE OPERATION
        int closeLabelChoice = JOptionPane.YES_NO_OPTION;
        closeLabelChoice = JOptionPane.showConfirmDialog(null,"Are you sure want to exit?","Warning",closeLabelChoice);
        if(closeLabelChoice == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == signInBut){
            Conn conn = new Conn();
            String password = passwordText.getText();
            String userid = useridText.getText();
            String query = "select name from adminlogin where userid = '"+userid+"' and password = '"+password+"';";
            try{
                ResultSet rs = conn.s.executeQuery(query);
                if(userid.equals(""))
                    JOptionPane.showMessageDialog(null,"Please enter the userid");
                else if(password.equals(""))
                    JOptionPane.showMessageDialog(null,"Please enter a password");
                else if(rs.next()){
                    setVisible(false);
                    System.out.println(rs.getString("name"));
                    //System.out.println(rs.getInt("userid"));
                    conn.s.executeUpdate("insert into adminLoginRecord values('"+rs.getString("name")+"');");
                    //admin dashboard object here
                    Application.launch(Dashboard.class,"");
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Crediantals");
                }
            }catch(HeadlessException | SQLException e){
                //do nothing ,have a kitkat
            }
        }
    }
    public static void main(String args[]) {
       new AdminLogin();
    }
}

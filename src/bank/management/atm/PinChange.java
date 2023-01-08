package bank.management.atm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PinChange extends JFrame implements ActionListener{
    String pinNo,cardNo;
    JPasswordField pinTextField,pin2TextField;
    JButton changeBut,cancelBut;
    PinChange(String pinNo,String cardNo){
        this.pinNo = pinNo;
        this.cardNo = cardNo;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setUndecorated(true);
        setLocation(300,50);
        Font systemFont = new Font("System",Font.BOLD,16);
        Font railwayFont = new Font("Railway",Font.PLAIN,16);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 750, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel bgImg = new JLabel(i3);
        bgImg.setBounds(0,0,800,750);
        add(bgImg);
        
        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setBounds(225,270,700,35);
        text.setFont(systemFont);
        text.setForeground(Color.WHITE);
        bgImg.add(text);
        
        JLabel pinText = new JLabel("New Pin");
        pinText.setBounds(150,300,700,25);
        pinText.setFont(railwayFont);
        pinText.setForeground(Color.WHITE);
        bgImg.add(pinText);

        pinTextField = new JPasswordField();
        pinTextField.setFont(systemFont);
        pinTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        pinTextField.setBounds(150,330,200,30);
        bgImg.add(pinTextField);
        
        JLabel rePinText = new JLabel("Re enter the new pin");
        rePinText.setBounds(150,370,700,25);
        rePinText.setFont(railwayFont);
        rePinText.setForeground(Color.WHITE);
        bgImg.add(rePinText);
        
        pin2TextField = new JPasswordField();
        pin2TextField.setFont(systemFont);
        pin2TextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        pin2TextField.setBounds(150,400,200,30);
        bgImg.add(pin2TextField);
        
        changeBut = new JButton("CHANGE");
        changeBut.setBounds(370,460,85,30);
        changeBut.setBackground(Color.WHITE);
        changeBut.setForeground(Color.BLACK);
        changeBut.setFocusable(false);
        changeBut.setBorderPainted(false);
        changeBut.addActionListener(this);
        changeBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                changeBut.setBackground(Color.green);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                changeBut.setBackground(Color.WHITE);
            }
        });
        bgImg.add(changeBut);
        
        cancelBut = new JButton("CANCEL");
        cancelBut.setBounds(280,460,85,30);
        cancelBut.setBackground(Color.WHITE);
        cancelBut.setForeground(Color.BLACK);
        cancelBut.setFocusable(false);
        cancelBut.setBorderPainted(false);
        cancelBut.addActionListener(this);
        cancelBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt){
                int cancelChoice = JOptionPane.YES_NO_OPTION;
                cancelChoice = JOptionPane.showConfirmDialog(null,"Are you sure want to cancel","Warning",cancelChoice);
                if(cancelChoice == JOptionPane.YES_OPTION){
                    setVisible(false);
                    new Transactions(pinNo,cardNo).setVisible(true);
                }
            }
            @Override
            public void mouseEntered(MouseEvent evt){
                cancelBut.setBackground(Color.darkGray);
                cancelBut.setForeground(Color.WHITE);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                cancelBut.setBackground(Color.WHITE);
                cancelBut.setForeground(Color.BLACK);
            }
        });
        bgImg.add(cancelBut);
        
       
        

        
        setSize(800,750);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
       if(ae.getSource()==changeBut){
           try{
               String newPin = pinTextField.getText();
               String newPin2 = pin2TextField.getText();
               try{
                   int pinDummy = Integer.parseInt(newPin);
               }catch(NumberFormatException e){
                   JOptionPane.showMessageDialog(null,"PIN should contain only numbers");
                   pinTextField.setText("");
                   pin2TextField.setText("");
                   return;
               }
               if(newPin.equals("")){
                   JOptionPane.showMessageDialog(null,"Please enter a new pin");
                   return;
               }
               if(newPin.length()!=4){
                   JOptionPane.showMessageDialog(null,"PIN should contain exactly 4 numbers");
                   pinTextField.setText("");
                   pin2TextField.setText("");
                   return;
               }
               if(newPin2.equals("")){
                   JOptionPane.showMessageDialog(null,"Please re-enter the new pin");
                   return;
               }
               if(!newPin.equals(newPin2)){
                   JOptionPane.showMessageDialog(null,"Entered pins does not match");
                   pinTextField.setText("");
                   pin2TextField.setText("");
                   return;
               }
               Conn conn = new Conn();
               String queryBank = "update bank set pin = '"+newPin2+"' where cardno = '"+cardNo+"'";
               String queryLogin = "update login set pin = '"+newPin2+"' where cardnumber = '"+cardNo+"'";
               String querySignUp3 = "update signupthree set pin = '"+newPin2+"' where cardNumber = '"+cardNo+"'";
               String queryAccount = "update account set pin = '"+newPin2+"' where cardno = '"+cardNo+"'";
               
               conn.s.executeUpdate(queryBank);
               conn.s.executeUpdate(queryLogin);
               conn.s.executeUpdate(querySignUp3);
               conn.s.executeUpdate(queryAccount);
               
               JOptionPane.showMessageDialog(null,"PIN changed successfully");
               setVisible(false);
               new Transactions(newPin2,cardNo).setVisible(true);
               
           }catch(Exception ex){
               System.out.println(ex);
           }
       }
    }
    public static void main(String args[]) {
        new PinChange("","");
    }
}

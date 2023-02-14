
package bank.management.atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

public class CardConfirm extends JFrame implements ActionListener {
    String pinNum,cardNum,name;
    JButton okBut;
    JTextField cardTextField,pinTextField;
    JLabel nameLabel,expiryLabel;
    CardConfirm(String cardNum,String pinNum,String name){
        this.pinNum = pinNum;
        this.cardNum = cardNum;
        this.name = name;
        setSize(700,400);
        setLayout(null);
        //setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Confirmation");
        setLocationRelativeTo(null);
        
        //BACKGROUND IMAGE
        setContentPane(new JLabel(new ImageIcon(ClassLoader.getSystemResource("icons/cardPrototype.png"))));
        
        cardTextField = new JTextField();
        cardTextField.setText(cardNum.substring(0,4)+" "+cardNum.substring(4,8)+" "+cardNum.substring(8,12)+" "+cardNum.substring(12,16));
        cardTextField.setBounds(55,225,290,30);
        cardTextField.setForeground(Color.WHITE);
        cardTextField.setEditable(false);
        cardTextField.setOpaque(false);
        cardTextField.setBackground(new Color(0, 0, 0, 0));
        cardTextField.setFont(new Font("Monospaced",Font.BOLD,19));
        cardTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(cardTextField);
        
        pinTextField = new JTextField();
        pinTextField.setText("PIN : "+ pinNum);
        pinTextField.setBounds(55,250,100,30);
        pinTextField.setForeground(Color.WHITE);
        pinTextField.setEditable(false);
        pinTextField.setOpaque(false);
        pinTextField.setBackground(new Color(0, 0, 0, 0));
        pinTextField.setFont(new Font("Railway",Font.BOLD,14));
        pinTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(pinTextField);
        
        nameLabel = new JLabel();
        nameLabel.setText(name.toUpperCase());
        nameLabel.setBounds(55,286,100,30);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setOpaque(false);
        nameLabel.setBackground(new Color(0, 0, 0, 0));
        nameLabel.setFont(new Font("Monospaced",Font.BOLD,14));
        nameLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(nameLabel);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 3);
        Date date = calendar.getTime();
        String formattedDate = dateFormat.format(date);
        
        expiryLabel = new JLabel();
        expiryLabel.setText(formattedDate.toUpperCase());
        expiryLabel.setBounds(345,286,100,30);
        expiryLabel.setForeground(Color.WHITE);
        expiryLabel.setOpaque(false);
        expiryLabel.setBackground(new Color(0, 0, 0, 0));
        expiryLabel.setFont(new Font("Monospaced",Font.BOLD,14));
        expiryLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(expiryLabel);
        
        okBut = new JButton("OK");
        okBut.setBounds(550,300,100,30);
        okBut.setBackground(Color.WHITE);
        okBut.setForeground(Color.BLACK);
        okBut.setFocusable(false);
        okBut.setBorderPainted(true);
        okBut.addActionListener(this);
        okBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseExited(MouseEvent evt){
                okBut.setBackground(Color.WHITE);
                okBut.setForeground(Color.BLACK);
                okBut.setBorderPainted(true);
            }
            @Override
            public void mouseEntered(MouseEvent evt){
                okBut.setBackground(Color.BLACK);
                okBut.setForeground(Color.WHITE);
                okBut.setBorderPainted(false);
            }
        });
        add(okBut);
        
        
        
        
        setVisible(true);
    }
    
    public static void main(String args[]) {
        new CardConfirm("5040935911998810","5555","Asirwad");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==okBut){
            setVisible(false);
            new Deposit(pinNum,cardNum).setVisible(true);
        }
    }
}

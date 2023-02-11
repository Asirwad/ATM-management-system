
package bank.management.atm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.SQLException;

public class SignUpThree extends JFrame implements ActionListener{
    
    JRadioButton savingsBut,fixedBut,currentBut,rdBut;
    JButton submit,cancel;
    JCheckBox atmCardCheck,internetBankCheck,mobileBankCheck,alertCheck,chequeBookCheck,eStatementCheck,declerationCheck;
    String formno,name;
    SignUpThree(String formno,String name){
        this.formno = formno;
        this.name = name;
        setSize(500,620);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 3");
        setContentPane(new JLabel(new ImageIcon(ClassLoader.getSystemResource("icons/signUpOneBackground.png"))));
        
        JLabel title = new JLabel("<html><u>Page 3:Account Details</u></html>");
        title.setFont(new Font("Railway",Font.BOLD,22));
        title.setBounds(110,20,300,30);
        title.setForeground(Color.WHITE);
        add(title);
        
        JLabel acntType = new JLabel("Account type");
        acntType.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
        acntType.setBounds(100,65,300,20);
        acntType.setForeground(Color.WHITE);
        add(acntType);
        
        savingsBut = new JRadioButton("Savings Account");
        savingsBut.setBounds(100,85,300,20);
        savingsBut.setFocusable(false);
        savingsBut.setForeground(Color.WHITE);
        savingsBut.setBackground(new Color(76,111,191));
        add(savingsBut);
        fixedBut = new JRadioButton("Fixed deposit");
        fixedBut.setFocusable(false);
        fixedBut.setBounds(100,103,300,20);
        fixedBut.setForeground(Color.WHITE);
        fixedBut.setBackground(new Color(76,111,191));
        add(fixedBut);
        currentBut = new JRadioButton("Current Account");
        currentBut.setFocusable(false);
        currentBut.setBounds(100,122,300,20);
        currentBut.setForeground(Color.WHITE);
        currentBut.setBackground(new Color(76,111,191));
        add(currentBut);
        rdBut = new JRadioButton("Recurring Deposit Account");
        rdBut.setFocusable(false);
        rdBut.setBounds(100,140,300,20);
        rdBut.setForeground(Color.WHITE);
        rdBut.setBackground(new Color(76,111,191));
        add(rdBut);
        ButtonGroup acntTypeRadio = new ButtonGroup();
        acntTypeRadio.add(rdBut);
        acntTypeRadio.add(currentBut);
        acntTypeRadio.add(fixedBut);
        acntTypeRadio.add(savingsBut);
        
        JLabel cardNum = new JLabel("Card number");
        cardNum.setBounds(100,175,200,20);
        cardNum.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
        cardNum.setForeground(Color.WHITE);
        add(cardNum);
        
        JLabel cardNumDummy = new JLabel("XXXX-XXXX-XXXX-4134");
        cardNumDummy.setBounds(100,188,400,40);
        cardNumDummy.setForeground(Color.WHITE);
        cardNumDummy.setFont(new Font("Railway",Font.BOLD,25));
        add(cardNumDummy);
        
        JLabel cardinfo = new JLabel("*This is how your 16 digit card number looks like");
        cardinfo.setBounds(153,220,400,10);
        cardinfo.setForeground(Color.WHITE);
        cardinfo.setFont(new Font("Railway",Font.ITALIC,10));
        add(cardinfo);
        
        JLabel pinNum = new JLabel("PIN Number");
        pinNum.setBounds(100,235,200,20);
        pinNum.setForeground(Color.WHITE);
        pinNum.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
        add(pinNum);
        
        JLabel pinNumDummy = new JLabel("XXXX");
        pinNumDummy.setBounds(100,248,100,40);
        pinNumDummy.setForeground(Color.WHITE);
        pinNumDummy.setFont(new Font("Railway",Font.BOLD,25));
        add(pinNumDummy);
        
        JLabel pininfo = new JLabel("*This is how your PIN looks like");
        pininfo.setBounds(100,280,400,10);
        pininfo.setForeground(Color.WHITE);
        pininfo.setFont(new Font("Railway",Font.ITALIC,10));
        add(pininfo);
        
        JLabel servicesRequired = new JLabel("Services required");
        servicesRequired.setBounds(100,310,200,20);
        servicesRequired.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
        servicesRequired.setForeground(Color.WHITE);
        add(servicesRequired);
        
        atmCardCheck = new JCheckBox("ATM Card");
        atmCardCheck.setBackground(new Color(76,111,191));
        atmCardCheck.setForeground(Color.WHITE);
        atmCardCheck.setFocusable(false);
        atmCardCheck.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        atmCardCheck.setBounds(100,325,100,30);
        add(atmCardCheck);
        
        internetBankCheck = new JCheckBox("Internet Banking");
        internetBankCheck.setBackground(new Color(76,111,191));
        internetBankCheck.setForeground(Color.WHITE);
        internetBankCheck.setFocusable(false);
        internetBankCheck.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        internetBankCheck.setBounds(230,325,200,30);
        add(internetBankCheck);
        
        mobileBankCheck = new JCheckBox("Mobile Banking");
        mobileBankCheck.setBackground(new Color(76,111,191));
        mobileBankCheck.setForeground(Color.WHITE);
        mobileBankCheck.setFocusable(false);
        mobileBankCheck.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        mobileBankCheck.setBounds(100,355,110,30);
        add(mobileBankCheck);
        
        alertCheck = new JCheckBox("Email & SMS alerts");
        alertCheck.setBackground(new Color(76,111,191));
        alertCheck.setForeground(Color.WHITE);
        alertCheck.setFocusable(false);
        alertCheck.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        alertCheck.setBounds(230,355,200,30);
        add(alertCheck);
        
        chequeBookCheck = new JCheckBox("Cheque book");
        chequeBookCheck.setBackground(new Color(76,111,191));
        chequeBookCheck.setForeground(Color.WHITE);
        chequeBookCheck.setFocusable(false);
        chequeBookCheck.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        chequeBookCheck.setBounds(100,385,110,30);
        add(chequeBookCheck);
        
        eStatementCheck = new JCheckBox("e-Statement");
        eStatementCheck.setBackground(new Color(76,111,191));
        eStatementCheck.setForeground(Color.WHITE);
        eStatementCheck.setFocusable(false);
        eStatementCheck.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        eStatementCheck.setBounds(230,385,200,30);
        add(eStatementCheck);
        
        declerationCheck = new JCheckBox("<html>I here by declears that the above entered details are<br> correct to the best of my knowledge</html>");
        declerationCheck.setBackground(new Color(76,111,191));
        declerationCheck.setForeground(Color.WHITE);
        declerationCheck.setFocusable(false);
        declerationCheck.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        declerationCheck.setBounds(100,450,400,40);
        add(declerationCheck);
        
        submit = new JButton("<html><b>SUBMIT</b></html>");
        submit.setBounds(370,540,100,25);
        submit.setFocusable(false);
        submit.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        submit.setForeground(Color.WHITE);
        submit.setBackground(new Color(123,150,212));
        submit.addActionListener(this);
        submit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                submit.setBackground(Color.green);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                submit.setBackground(new Color(123,150,212));
            }
            });
        add(submit);
        
        cancel = new JButton("<html><b>CANCEL</b></html>");
        cancel.setBounds(260,540,100,25);
        cancel.setFocusable(false);
        cancel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(new Color(123,150,212));
        cancel.addActionListener(this);
        cancel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                cancel.setBackground(Color.YELLOW);
                cancel.setForeground(Color.BLACK);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                cancel.setBackground(new Color(123,150,212));
                cancel.setForeground(Color.WHITE);
            }
            });
        add(cancel);
        
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==submit){
            String accountType=null;
            if(savingsBut.isSelected())
                accountType = "Savings Account";
            else if(fixedBut.isSelected())
                accountType = "Fixed Account";
            else if(currentBut.isSelected())
                accountType = "Current Account";
            else if(rdBut.isSelected())
                accountType = "Recurring Deposit Account";
            Random random = new Random();
            String cardNum = "" + Math.abs((random.nextLong()%90000000L)+5040936000000000L);
            String pinNum = "" + Math.abs((random.nextLong()%9000L)+1000L);
            String facility=null;
            if(atmCardCheck.isSelected())
                facility+=" ATM Card";
            if(internetBankCheck.isSelected())
                facility+=" Internet Banking";
            if(mobileBankCheck.isSelected())
                facility+=" Mobile Banking";
            if(alertCheck.isSelected())
                facility+=" Email & SMS alerts";
            if(chequeBookCheck.isSelected())
                facility+=" Check Book";
            if(eStatementCheck.isSelected())
                facility+=" eStatement";
            try{
                if(accountType.equals("")){
                    JOptionPane.showMessageDialog(null,"Select any account type");
                }
                else if(!declerationCheck.isSelected()){
                    JOptionPane.showMessageDialog(null,"Please fill the declearation");
                }
                else{
                    Conn conn = new Conn();
                    String query1 = "insert into signupthree values('"+formno+"','"+accountType+"','"+cardNum+"','"+pinNum+"','"+facility+"');";
                    String query2 = "insert into login values('"+formno+"','"+cardNum+"','"+pinNum+"');";
                    conn.s.executeUpdate(query1);
                    conn.s.executeUpdate(query2);
                    
                    //JOptionPane.showMessageDialog(null,"Card Number : "+cardNum+"\nPIN : "+pinNum);
                    setVisible(false);
                    new CardConfirm(cardNum,pinNum,name).setVisible(true);
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
        else if(ae.getSource()==cancel){
            int choice = JOptionPane.YES_NO_OPTION;
            choice = JOptionPane.showConfirmDialog(null,"Are you sure want to cancel?","Warning",choice);
            if(choice == JOptionPane.YES_OPTION){
                try{
                    Conn conn = new Conn();
                    conn.s.executeUpdate("delete from signup where formno = '"+formno+"';");
                    conn.s.executeUpdate("delete from signuptwo where formno = '"+formno+"';");
                    setVisible(false);
                    new Login().setVisible(true);
                }catch(SQLException ex){
                    System.out.println(ex);
                }
            }
        }
    }
    public static void main(String args[]) {
        new SignUpThree("","");
    }
}
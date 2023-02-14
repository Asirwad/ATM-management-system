    package bank.management.atm;

    import javax.swing.*;
    import java.awt.*;
    import java.util.Random;
    import java.awt.event.*;
    import com.toedter.calendar.JDateChooser;
import java.util.regex.Pattern;

    public class SignUpOne extends JFrame implements ActionListener{
        JButton next,cancel;
        long random;
        JTextField nameText,fnameText,emailText,addressText,cityText,stateText,pincodeText;
        JRadioButton male,female,otherGender,married,unmarried;
        JDateChooser dateChooser;
        String name;
        SignUpOne(){
            setSize(500,700);
            setLocation(350,10);
            setResizable(false);
            //getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            setLayout(null);
            setContentPane(new JLabel(new ImageIcon(ClassLoader.getSystemResource("icons/signUpOneBackground.png"))));

            //Form number generator
            Random ran = new Random();
            random = Math.abs((ran.nextLong() % 9000L) + 1000L);

            //LABELS
            JLabel formNo = new JLabel("APPLICATION FORM NO : "+random);
            formNo.setForeground(Color.WHITE);
            formNo.setFont(new Font("Railway",Font.BOLD,25));
            formNo.setBounds(70,20,600,40);
            add(formNo);


            JLabel personDetails = new JLabel("Page 1: Personal Details ");
            personDetails.setFont(new Font("Railway",Font.BOLD,22));
            personDetails.setBounds(110,60,300,30);
            personDetails.setForeground(Color.WHITE);
            add(personDetails);

            JLabel name = new JLabel("Name ");
            name.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
            name.setBounds(100,110,300,12);
            name.setForeground(Color.WHITE);
            add(name);

            nameText = new JTextField();
            nameText.setFont(new Font("Railway",Font.BOLD,14));
            nameText.setBounds(100,125,300,30);
            nameText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            add(nameText);

            JLabel fname = new JLabel("Fathers's name ");
            fname.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
            fname.setBounds(100,170,300,12);
            fname.setForeground(Color.WHITE);
            add(fname);

            fnameText = new JTextField();
            fnameText.setFont(new Font("Railway",Font.BOLD,14));
            fnameText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            fnameText.setBounds(100,185,300,30);
            add(fnameText);

            JLabel dob = new JLabel("Date of Birth ");
            dob.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
            dob.setBounds(100,220,300,12);
            dob.setForeground(Color.WHITE);
            add(dob);

            dateChooser = new JDateChooser();
            dateChooser.setBounds(100,235,300,30);
            add(dateChooser);

            JLabel gender = new JLabel("Gender ");
            gender.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
            gender.setBounds(100,270,300,12);
            gender.setForeground(Color.WHITE);
            add(gender);

            male = new JRadioButton("Male");
            female = new JRadioButton("Female");
            otherGender = new JRadioButton("Other");
            male.setBounds(100,285,60,20);
            male.setFocusable(false);
            male.setForeground(Color.WHITE);
            male.setBackground(new Color(76,111,191));
            female.setBounds(170,285,70,20);
            female.setFocusable(false);
            female.setForeground(Color.WHITE);
            female.setBackground(new Color(76,111,191));
            otherGender.setBounds(260,285,60,20);
            otherGender.setFocusable(false);
            otherGender.setForeground(Color.WHITE);
            otherGender.setBackground(new Color(76,111,191));
            add(male);
            add(female);
            add(otherGender);
            ButtonGroup genderGroup = new ButtonGroup();
            genderGroup.add(male);
            genderGroup.add(female);
            genderGroup.add(otherGender);

            JLabel email = new JLabel("email ");
            email.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
            email.setBounds(100,320,300,12);
            email.setForeground(Color.WHITE);
            add(email);

            emailText = new JTextField();
            emailText.setFont(new Font("Railway",Font.BOLD,14));
            emailText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            emailText.setBounds(100,335,300,30);
            add(emailText);

            JLabel marital = new JLabel("Maritial Status ");
            marital.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
            marital.setBounds(100,380,300,10);
            marital.setForeground(Color.WHITE);
            add(marital);

            married = new JRadioButton("married");
            unmarried = new JRadioButton("unmarried");
            married.setBounds(100,395,80,20);
            married.setFocusable(false);
            married.setForeground(Color.WHITE);
            married.setBackground(new Color(76,111,191));
            unmarried.setBounds(180,395,90,20);
            unmarried.setFocusable(false);
            unmarried.setForeground(Color.WHITE);
            unmarried.setBackground(new Color(76,111,191));
            add(married);
            add(unmarried);
            ButtonGroup maritialGroup = new ButtonGroup();
            maritialGroup.add(married);
            maritialGroup.add(unmarried);

            JLabel address = new JLabel("Address ");
            address.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
            address.setBounds(100,420,300,12);
            address.setForeground(Color.WHITE);
            add(address);

            addressText = new JTextField();
            addressText.setFont(new Font("Railway",Font.BOLD,14));
            addressText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            addressText.setBounds(100,435,300,30);
            add(addressText);

            JLabel city = new JLabel("City ");
            city.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
            city.setBounds(100,470,300,12);
            city.setForeground(Color.WHITE);
            add(city);

            cityText = new JTextField();
            cityText.setFont(new Font("Railway",Font.BOLD,14));
            cityText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            cityText.setBounds(100,485,300,30);
            add(cityText);

            JLabel state = new JLabel("State ");
            state.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
            state.setBounds(100,520,300,12);
            state.setForeground(Color.WHITE);
            add(state);

            stateText = new JTextField();
            stateText.setFont(new Font("Railway",Font.BOLD,14));
            stateText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            stateText.setBounds(100,535,300,30);
            add(stateText);

            JLabel pincode = new JLabel("Pincode ");
            pincode.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
            pincode.setBounds(100,570,300,12);
            pincode.setForeground(Color.WHITE);
            add(pincode);

            pincodeText = new JTextField();
            pincodeText.setFont(new Font("Railway",Font.BOLD,14));
            pincodeText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            pincodeText.setBounds(100,585,300,30);
            add(pincodeText);

            next = new JButton("Next");
            next.setFont(new Font("Railway",Font.BOLD,14));
            next.setBounds(330,625,70,25);
            next.setFocusable(false);
            next.setBackground(new Color(123,150,212));
            next.setForeground(Color.WHITE);
            next.setBorderPainted(false);
            next.addActionListener(this);
            next.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt){
                next.setBackground(Color.green);
            }
            @Override
            public void mouseExited(MouseEvent evt){
                next.setBackground(new Color(123,150,212));
            }
            });
            add(next);

            setVisible(true);
            
            cancel = new JButton("Cancel");
            cancel.setFont(new Font("Railway",Font.BOLD,14));
            cancel.setBounds(100,625,85,25);
            cancel.setFocusable(false);
            cancel.setBackground(new Color(123,150,212));
            cancel.setForeground(Color.WHITE);
            cancel.setBorderPainted(false);
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
        public void actionPerformed(ActionEvent ae){
            if(ae.getSource() == next){
                String formno = ""+random;
                name = nameText.getText();
                String fname = fnameText.getText();
                String dob = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
                String gender = null;
                if(male.isSelected()){
                    gender = "Male";
                }
                else if(female.isSelected()){
                    gender = "Female";
                }
                else if(otherGender.isSelected()){
                    gender = "Other";
                }
                String email = emailText.getText();
                String maritialStatus = null;
                if(married.isSelected()){ 
                    maritialStatus = "Married";
                }
                else if(unmarried.isSelected()){
                    maritialStatus = "Unmarried";
                }
                String address = addressText.getText();
                String city = cityText.getText();
                String state = stateText.getText();
                String pin = pincodeText.getText();
            
                try{
                    if(name.equals("")||fname.equals("")||dob.equals("")||email.equals("")||address.equals("")||city.equals("")||state.equals("")||pin.equals("")){
                        JOptionPane.showMessageDialog(null,"All fields are Mandatory");
                    }
                    else if(pin.length()!=6){
                        JOptionPane.showMessageDialog(null,"Pin should be 6 digits");
                    }
                    else if(Pattern.matches("[a-zA-Z]+", pin)){
                        JOptionPane.showMessageDialog(null,"Pin should contain only numbers");
                    }
                    else{
                        Conn c = new Conn();
                        String query = "insert into signup values('"+formno+"','"+name+"','"+fname+"','"+dob+"','"+gender+"','"+email+"','"+maritialStatus+"','"+address+"','"+city+"','"+pin+"','"+state+"');";
                        c.s.executeUpdate(query);

                        setVisible(false);
                        new SignUpTwo(formno,name).setVisible(true);
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
            }else if(ae.getSource() == cancel){
                int choice = JOptionPane.YES_NO_OPTION;
                choice = JOptionPane.showConfirmDialog(null,"Are you sure want to cancel?","Warning",choice);
                if(choice == JOptionPane.YES_OPTION){
                    setVisible(false);
                    new Login().setVisible(true);
                }
            }   
        }
        public static void main(String args[]) {
           new SignUpOne();
        }
    }

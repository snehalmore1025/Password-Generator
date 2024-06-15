import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasswordGeneratorGUI extends JFrame {
    JTextField t1;
    JTextArea ta1;
    JLabel l1, l3;

    public PasswordGeneratorGUI() {
        setTitle("Password Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setResizable(true);
        getContentPane().setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        JPanel tp = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        tp.setBackground(Color.pink);
        l1 = new JLabel("Enter your password:");
        l1.setFont(new Font("Verdana", Font.PLAIN, 16));
        t1 = new JTextField(15);
        t1.setPreferredSize(new Dimension(300, 30));
        t1.setFont(new Font("Verdana", Font.PLAIN, 16));

        JButton b1= new JButton("Generate Password");
        b1.setPreferredSize(new Dimension(250, 30));
        b1.setFont(new Font("Verdana", Font.PLAIN, 16));
        b1.addActionListener(e -> generatePwd());
        tp.add(l1);
        tp.add(t1);
        tp.add(b1,BorderLayout.EAST);
        JPanel cp = new JPanel(new BorderLayout());
        cp.setBackground(Color.pink);
        JLabel l2 = new JLabel("Generated Password:");
        l2.setFont(new Font("Verdana", Font.PLAIN, 16));
        ta1 = new JTextArea(2, 15);
        ta1.setEditable(false);
        ta1.setBackground(Color.white);
        ta1.setFont(new Font("Verdana", Font.PLAIN, 16));
        cp.add(l2, BorderLayout.NORTH);
        cp.add(new JScrollPane(ta1), BorderLayout.CENTER);
        JPanel bp = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bp.setBackground(Color.pink);
        l3 = new JLabel();
        l3.setFont(new Font("Verdana", Font.PLAIN, 16));
        bp.add(l3);
        add(tp, BorderLayout.NORTH);
        add(cp, BorderLayout.CENTER);
        add(bp, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private String generateRandomPwd() {
        String uppercaseLetters="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters="abcdefghijklmnopqrstuvwxyz";
        String digits="0123456789";
        String symbols="!@#$%^&*()_+-=[]{}|;:,.<>?";
        String allChars= uppercaseLetters+lowercaseLetters+digits+symbols;
        List<Character> chars = new ArrayList<>();
        for (char c:allChars.toCharArray()) {
            chars.add(c);
        }
        Collections.shuffle(chars);
        StringBuilder pwd=new StringBuilder();
        for(char c:chars){
            pwd.append(c);
        }
        return pwd.toString();
    }
    private String shufflePwd(String pwd) {
        List<Character> chars=new ArrayList<>();
        for(char c:pwd.toCharArray()){
            chars.add(c);
        }
        Collections.shuffle(chars);
        StringBuilder shuffledpwd = new StringBuilder();
        for(char c : chars){
            shuffledpwd.append(c);
        }
        return shuffledpwd.toString();
    }
    private int passwordStrength(String pwd) {
        boolean hasUpperCase=false;
        boolean hasLowerCase=false;
        boolean hasDigit=false;
        boolean hasSymbol = false;
        for(char c : pwd.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } 
            else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSymbol = true;
            }
        }
        int strength=0;
        if(hasUpperCase) 
        strength++;
        if(hasLowerCase) 
        strength++;
        if(hasDigit) 
        strength++;
        if(hasSymbol) 
        strength++;
        if(pwd.length()>=8) 
        strength++;
        if(pwd.length()>=16) 
        strength++;
        return strength;
    }
    private void generatePwd() {
        String userPwd = t1.getText();
        String genPwd = generateRandomPwd();
        String shuffledPwd = shufflePwd(userPwd);
        if (!userPwd.isEmpty()) {
            ta1.setText(shuffledPwd);
            int strength = passwordStrength(shuffledPwd);
            displayStrength(strength);
        } else {
            ta1.setText(genPwd);
            int strength = passwordStrength(genPwd);
            displayStrength(strength);
        }
    }
    private void displayStrength(int strength) {
        if (strength == 6) {
            l3.setText("Password Strength is Very Strong");
        } else if (strength >= 4) {
            l3.setText("Password Strength is Strong");
        } else if (strength >= 3) {
            l3.setText("Password Strength is Moderate");
        } else {
            l3.setText("Password Strength is Weak");
        }
    }
    public static void main(String[] args) {
         new PasswordGeneratorGUI();
    }
}

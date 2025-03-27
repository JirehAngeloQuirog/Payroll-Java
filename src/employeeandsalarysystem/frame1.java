package employeeandsalarysystem;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class frame1 extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame1 frame = new frame1();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public frame1() {
        setResizable(false);
        setTitle("Employee and Salary System");
        setFont(new Font("Arial Black", Font.PLAIN, 18));
        setIconImage(Toolkit.getDefaultToolkit()
                .getImage(frame1.class.getResource("/employeeandsalarysystem_resources/ESS.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 945, 767);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Employee and Salary System");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 55));
        lblNewLabel.setBounds(10, 188, 911, 89);
        contentPane.add(lblNewLabel);

        JFormattedTextField frmtdtxtfldName = new JFormattedTextField();
        frmtdtxtfldName.setForeground(new Color(0, 0, 0));
        frmtdtxtfldName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frmtdtxtfldName.setBounds(318, 404, 318, 31);
        contentPane.add(frmtdtxtfldName);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        passwordField.setBounds(318, 445, 318, 30);
        contentPane.add(passwordField);

        JLabel lblNewLabel_1 = new JLabel("LAST NAME");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblNewLabel_1.setBounds(200, 404, 118, 31);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("PASSWORD");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblNewLabel_1_1.setBounds(200, 445, 118, 31);
        contentPane.add(lblNewLabel_1_1);

        JButton btnNewButton = new JButton("Log In");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = frmtdtxtfldName.getText().trim();
                String password = new String(passwordField.getPassword());

                if (authenticate(username, password)) {
                    JOptionPane.showMessageDialog(null, "Logged in successfully!");

                    // Pass username to frame2 constructor
                    frame2 jf2 = new frame2();
                    jf2.setVisible(true); // Use setVisible() instead of show()c

                    dispose(); // close current frame
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password!", "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
        btnNewButton.setBounds(401, 502, 166, 36);
        contentPane.add(btnNewButton);
    }

    private boolean authenticate(String username, String password) {

        String line = "";
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(
                "C:\\Users\\jireh angelo\\commision\\employeeandsalarysystem\\src\\employeeandsalarysystemdatabase\\Namesandpasswords.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                if (data.length == 2 && data[0].equals(username) && data[1].equals(password)) {
                    return true; // Username and password matched
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Username and password not found or incorrect
    }
}

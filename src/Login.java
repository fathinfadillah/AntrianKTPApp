import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JButton btnLogin;
    private JButton btnSignUp;
    public JPanel Login;
    private JPanel panel1;
    private JPanel panel2;

    public Login() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    String url = "jdbc:sqlserver://localhost;database=AntrianKTPApp;user=fathinfadillah;password=Fathin@2021";
                    Connection con = DriverManager.getConnection(url);
                    String sql = "Select * from pegawai where pg_username = ? and pg_password = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, txtUsername.getText());
                    pst.setString(2, txtPassword.getText());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Username dan Password Cocok!");
                        Login.remove(panel1);
                        Login.remove(panel2);
                        PegawaiAntrian c = new PegawaiAntrian();
                        c.formutama.setVisible(true);
                        Login.setLayout(new BorderLayout());
                        Login.add(c.formutama);
                        Login.validate();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Username dan Password Salah!");
                        txtUsername.setText("");
                        txtPassword.setText("");
                    }
                    con.close();
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }

            }
        });
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().Login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000,570);
    }
}
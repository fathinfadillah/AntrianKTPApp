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
    private JButton btnLogin;
    private JButton btnSignUp;
    public JPanel Login;
    private JPanel panel1;
    private JPanel panel2;
    private JButton btnback;
    private JPasswordField txtPassword;
    private JCheckBox lihatPasswordCheckBox;

    public Login() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                    String url = "jdbc:sqlserver://localhost;database=AntrianKTP_Kel05;user=sa;password=polman";
                    //String url = "jdbc:sqlserver://localhost;database=AntrianKTP_Kel05;integratedSecurity=true";

                    //mendapatkan data username dan password yang sesuai dari databse untuk login
                    Connection con = DriverManager.getConnection(url);
                    String sql = "Select * from pegawai where pg_username = ? and pg_password = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, txtUsername.getText());
                    pst.setString(2, txtPassword.getText());
                    ResultSet rs = pst.executeQuery();

                    //jika username dan password sesuai
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Selamat datang Bapak/Ibu!", "Login Pegawai",
                                JOptionPane.INFORMATION_MESSAGE);
                        Login.remove(panel1);
                        Login.remove(panel2);
                        MenuPegawai c = new MenuPegawai();
                        c.jpMenuPegawai.setVisible(true);
                        Login.setLayout(new BorderLayout());
                        Login.add(c.jpMenuPegawai);
                        Login.validate();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Username atau Password Salah!","Login Pegawai",
                                JOptionPane.ERROR_MESSAGE);
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
                Login.remove(panel1);
                Login.remove(panel2);
                SignUp c = new SignUp();
                c.SignUp.setVisible(true);
                Login.setLayout(new BorderLayout());
                Login.add(c.SignUp);
                Login.validate();
            }
        });

        btnback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login.remove(panel1);
                Login.remove(panel2);
                MainForm c = new MainForm();
                c.formutama.setVisible(true);
                Login.setLayout(new BorderLayout());
                Login.add(c.formutama);
                Login.validate();
            }
        });

        lihatPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //method untuk lihat password atas sembunyikan password
                if (lihatPasswordCheckBox.isSelected()){
                    txtPassword.setEchoChar((char) 0);
                }else{
                    txtPassword.setEchoChar('•');
                }
            }
        });
    }
}

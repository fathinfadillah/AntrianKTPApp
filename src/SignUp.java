import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp {
    public JPanel SignUp;
    private JPanel panel2;
    private JTextField txtNama;
    private JTextField txtRole;
    private JButton btnSignUp;
    private JTextField txtUsername;
    private JButton btnBack;
    private JPanel panel1;
    private JPanel panel3;
    private JPasswordField txtpassword;
    private JPasswordField txtpassword2;

    public SignUp() {
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp.remove(panel1);
                SignUp.remove(panel2);
                SignUp.remove(panel3);
                Login c = new Login();
                c.Login.setVisible(true);
                SignUp.setLayout(new BorderLayout());
                SignUp.add(c.Login);
                SignUp.validate();
            }
        });
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pegawai p = new Pegawai();
                p.setNama(txtNama.getText());
                p.setrole(txtRole.getText());
                p.setUsername(txtUsername.getText());
                if(String.valueOf(txtpassword.getPassword()).equals(String.valueOf(txtpassword2.getPassword())))
                {
                    p.setUsername(String.valueOf(txtpassword.getPassword()));
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Password anda belum sama!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

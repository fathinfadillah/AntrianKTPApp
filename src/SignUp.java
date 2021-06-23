import DB.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SignUp {
    public JPanel SignUp;
    private JPanel panel2;
    private JTextField txtNama;
    private JButton btnSignUp;
    private JTextField txtUsername;
    private JButton btnBack;
    private JPanel panel1;
    private JPanel panel3;
    private JPasswordField txtpassword;
    private JPasswordField txtpassword2;

    DBConnection connection = new DBConnection();

    public SignUp() {
        //validasi
        txtNama.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char karakter = e.getKeyChar();
                if(!(Character.isAlphabetic(karakter) || (Character.isWhitespace(karakter)))) {
                    e.consume();
                }
            }
        });

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
                if(txtNama.getText().equals("") || txtUsername.getText().equals("") ||
                        String.valueOf(txtpassword.getPassword()).equals("") || String.valueOf(txtpassword2.getPassword()).equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Harap isikan data dengan benar!","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(String.valueOf(txtpassword.getPassword()).equals(String.valueOf(txtpassword2.getPassword()))) {
                    try{
                        String sql = "SELECT pg_username FROM pegawai WHERE pg_username = '"+txtUsername.getText()+"' ORDER BY pg_username desc";
                        connection.stat = connection.conn.createStatement();
                        connection.result = connection.stat.executeQuery(sql);
                        if (connection.result.next()) {
                            JOptionPane.showMessageDialog(null, "Username tersebut sudah tidak tersedia!",
                                    "Error",JOptionPane.ERROR_MESSAGE);
                            txtUsername.setText("");
                        }else {
                            p.setNama(txtNama.getText());
                            p.setPassword(String.valueOf(txtpassword.getPassword()));
                            p.setUsername(txtUsername.getText());

                            String query = "INSERT INTO pegawai VALUES (?,?,?)";
                            connection.pstat = connection.conn.prepareStatement(query);
                            connection.pstat.setString(1, p.getnama());
                            connection.pstat.setString(2, p.getUsername());
                            connection.pstat.setString(3, p.getPassword());

                            connection.pstat.executeUpdate(); //inseert ke database
                            JOptionPane.showMessageDialog(null, "Sign Up Berhasil!","Informasi",JOptionPane.INFORMATION_MESSAGE);

                            clear();
                        }
                    }catch (Exception e1){
                        System.out.println("Terjadi error pada sign up: " + e1);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Password anda belum sama!","Error",JOptionPane.ERROR_MESSAGE);
                    txtpassword2.setText("");
                }
            }
        });
    }

    public void clear() {
        txtNama.setText("");
        txtUsername.setText("");
        txtpassword.setText("");
        txtpassword2.setText("");
    }
}

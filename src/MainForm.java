import DB.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainForm {
    private JButton btnuser;
    public JPanel formutama;
    private JButton btnpegawai;
    private JPanel panel1;
    private JPanel panel2;
    private JButton btnaboutus;
    private JButton btnkeluar;
    DBConnection connection = new DBConnection();

    public MainForm() {
        btnuser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formutama.remove(panel1);
                formutama.remove(panel2);
                PengantriAntrian c = new PengantriAntrian();
                c.formutama.setVisible(true);
                formutama.setLayout(new BorderLayout());
                formutama.add(c.formutama);
                formutama.validate();
            }
        });

        btnpegawai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formutama.remove(panel1);
                formutama.remove(panel2);
                Login c = new Login();
                c.Login.setVisible(true);
                formutama.setLayout(new BorderLayout());
                formutama.add(c.Login);
                formutama.validate();
            }
        });

        btnaboutus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formutama.remove(panel1);
                formutama.remove(panel2);
                AboutUs c = new AboutUs();
                c.AboutUs.setVisible(true);
                formutama.setLayout(new BorderLayout());
                formutama.add(c.AboutUs);
                formutama.validate();
            }
        });

        btnkeluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Apakah anda yakin akan keluar dari aplikasi ini?", "Log Out Validation", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        //untuk mengetahui apakah masih terdapat sisa antrian atau tidak
                        String query = "SELECT * FROM antrian ORDER BY no_antrian ASC";
                        connection.result = connection.stat.executeQuery(query);
                        if (connection.result.next()) {
                            JOptionPane.showMessageDialog(null, "Masih terdapat sisa antrian!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            //menghapus data pada loket1, loket2, loket3, serta mengubah total antrian menjadi 0
                            String sql2 = "EXEC sp_deleteData";
                            connection.pstat = connection.conn.prepareStatement(sql2);
                            connection.pstat.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Terima kasih telah menggunakan aplikasi ini, sampai jumpa kembali!", "Thanks Message", JOptionPane.INFORMATION_MESSAGE);
                            System.exit(0);
                        }
                    } catch (Exception e1) {
                        System.out.println("Terjadi error pada saat keluar:" + e1);
                    }
                }
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Aplikasi Antrian KTP");
        frame.setContentPane(new MainForm().formutama);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}

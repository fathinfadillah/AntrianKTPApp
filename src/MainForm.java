import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JButton btnuser;
    public JPanel formutama;
    private JButton btnpegawai;
    private JPanel panel1;
    private JPanel panel2;

    public MainForm() {
        btnuser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formutama.remove(panel1);
                formutama.remove(panel2);
                UserAntrian c = new UserAntrian();
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
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Coba SD");
        frame.setContentPane(new MainForm().formutama);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000,570);
    }
}

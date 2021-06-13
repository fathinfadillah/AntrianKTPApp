import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PegawaiAntrian {
    public JPanel formutama;
    private JPanel panel1;
    private JPanel panel2;
    private JButton berandaButton;
    private JButton btnnext1;
    private JButton logOutButton;
    private JButton btnnext3;
    private JButton btnpros1;
    private JButton btnpros3;
    private JButton btnpgl1;
    private JButton btnpgl2;
    private JButton btnpgl3;
    private JButton btnnext2;
    private JButton btnpros2;
    public JLabel lbl1;
    public JLabel lbl2;
    public JLabel lbl3;
    UserAntrian u = new UserAntrian();
    public PegawaiAntrian() {
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formutama.remove(panel1);
                formutama.remove(panel2);
                MainForm c = new MainForm();
                c.formutama.setVisible(true);
                formutama.setLayout(new java.awt.BorderLayout());
                formutama.add(c.formutama);
                formutama.validate();
            }
        });
        btnpgl1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            System.out.println(u.q.size);
            }
        });
    }
}

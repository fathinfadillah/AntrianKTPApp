import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPegawai {
    private JPanel panel1;
    private JButton btnantrian;
    private JButton berandaButton;
    private JTextArea textArea1;
    private JPanel jpmenu;
    public JPanel jpMenuPegawai;
    private JButton logOutButton;
    PegawaiAntrian p = new PegawaiAntrian();

    public MenuPegawai() {
        btnantrian.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DaftarAntrian c = new DaftarAntrian();
                p.jp1.setVisible(false);
                jpmenu.removeAll();
                jpmenu.revalidate();
                jpmenu.repaint();
                c.formutama.setVisible(true);
                jpmenu.setLayout(new BorderLayout());
                jpmenu.add(c.formutama);
            }
        });

        berandaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpmenu.removeAll();
                jpmenu.revalidate();
                jpmenu.repaint();
                p.jp1.setVisible(true);
                jpmenu.revalidate();
                jpmenu.setLayout(new BorderLayout());
                jpmenu.add(p.jp1);
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpMenuPegawai.removeAll();
                MainForm c = new MainForm();
                c.formutama.setVisible(true);
                jpMenuPegawai.setLayout(new java.awt.BorderLayout());
                jpMenuPegawai.add(c.formutama);
                jpMenuPegawai.validate();
            }
        });
    }
}

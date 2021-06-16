import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutUs {
    public JPanel AboutUs;
    private JButton btnBack;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    public AboutUs() {
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutUs.remove(panel1);
                AboutUs.remove(panel2);
                AboutUs.remove(panel3);
                MainForm c = new MainForm();
                c.formutama.setVisible(true);
                AboutUs.setLayout(new BorderLayout());
                AboutUs.add(c.formutama);
                AboutUs.validate();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("About Us");
        frame.setContentPane(new AboutUs().AboutUs);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000,570);
    }
}

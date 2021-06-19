import javax.swing.*;
import java.awt.event.ActionEvent;

public class DaftarAntrian {
    private JLabel txtsisa;
    private JLabel txttotal;
    private JPanel formutama;
    private JPanel panel1;
    private JButton berandaButton;
    private JTextArea daftarAntrian;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Coba SD");
        frame.setContentPane(new DaftarAntrian().formutama);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000,570);
    }
}

import DB.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Date;

public class DaftarAntrian {
    private JLabel txtsisa;
    private JLabel txttotal;
    public JPanel formutama;
    private JTextArea daftarAntrian;
    public LQueue q = new LQueue();
    DBConnection connection = new DBConnection();
    public DaftarAntrian(){
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Coba SD");
        frame.setContentPane(new DaftarAntrian().formutama);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000,570);
    }
}

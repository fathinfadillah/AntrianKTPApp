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
        try {
            String query = "SELECT * FROM antrian ORDER BY no_antrian ASC";
            connection.result = connection.stat.executeQuery(query);
            while (connection.result.next()) {
                q.enqueue(connection.result.getString("no_antrian"));
            }
        }
        catch (Exception e1) {
            System.out.println("Terjadi error pada saat load data antrian:" + e1);
        }
        /*if(q.size!=0) {
            int i = 0;
            while (i < q.size) {
                daftarAntrian.append("Antrian No " + q.show() + "\n");
                i++;
            }
        }*/
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

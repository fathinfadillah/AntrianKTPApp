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
            //melihat data antrian pada tabel
            String query = "SELECT * FROM antrian ORDER BY no_antrian ASC";
            connection.result = connection.stat.executeQuery(query);
            while (connection.result.next()) {
                //enqueue data di tabel ke dalam queue
                q.enqueue(connection.result.getString("no_antrian"));
            }
        }
        catch (Exception e1) {
            System.out.println("Terjadi error pada saat insert antrian:" + e1);
        }
        txtsisa.setText((Integer.toString(q.size)));
        while (q.size!=0)
        {
            //menampilkan data ke textarea lewat manipulasi dequeue (tidak benar2 dequeue pada antrian sebenarnya)
            daftarAntrian.append("- Antrian nomor "+q.dequeue()+"\n");
        }
        try {
            //menghitung total antrian
            String query4 = "SELECT COUNT(total) AS total FROM total";
            connection.result = connection.stat.executeQuery(query4);
            while (connection.result.next()) {
                txttotal.setText(connection.result.getString("total"));
            }
        }
        catch (Exception e1) {
            System.out.println("Terjadi error pada saat insert antrian:" + e1);
        }
    }
}

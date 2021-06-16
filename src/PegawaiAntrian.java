import DB.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PegawaiAntrian {
    public JPanel formutama;
    private JPanel panel1;
    private JPanel panel2;
    private JButton berandaButton;
    private JButton btnpros;
    private JButton logOutButton;
    private JButton btnpros3;
    private JButton btnpgl1;
    private JButton btnpgl2;
    private JButton btnpgl3;
    private JButton btnpros2;
    public JLabel lbl1;
    public JLabel lbl2;
    public JLabel lbl3;
    private JTextArea textArea1;
    public LQueue q = new LQueue();
    DBConnection connection = new DBConnection();
    public PegawaiAntrian() {
        try {
            String query = "SELECT * FROM antrian ORDER BY no_antrian ASC";
            connection.result = connection.stat.executeQuery(query);
            while (connection.result.next()) {
                q.enqueue(connection.result.getString("no_antrian"));
            }
            String query2 = "DELETE FROM antrian;";
            connection.pstat = connection.conn.prepareStatement(query2);
            connection.pstat.executeUpdate(); //inseert ke database
        }
        catch (Exception e1) {
            System.out.println("Terjadi error pada saat insert antrian:" + e1);
        }
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i=0;
                while (q.size != 0) {
                    try {
                        String query = "INSERT INTO antrian VALUES (?)";
                        connection.pstat = connection.conn.prepareStatement(query);
                        connection.pstat.setString(1, q.dequeue());
                        connection.pstat.executeUpdate(); //inseert ke database
                    } catch (Exception e1) {
                        System.out.println("Terjadi error pada saat insert antrian:" + e1);
                    }
                }
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
                lbl1.setText(q.dequeue());
                try{
                    String query2 = "DELETE FROM loket1;";
                    connection.pstat = connection.conn.prepareStatement(query2);
                    connection.pstat.executeUpdate(); //inseert ke database
                    String query = "INSERT INTO loket1 VALUES (?)";
                    connection.pstat = connection.conn.prepareStatement(query);
                    connection.pstat.setString(1, lbl1.getText());
                    connection.pstat.executeUpdate(); //inseert ke database
                }
                catch (Exception e1){
                    System.out.println("Terjadi error pada saat insert antrian:"+e1);
                }
            }
        });
        btnpgl2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbl2.setText(q.dequeue());
                try{
                    String query2 = "DELETE FROM loket2;";
                    connection.pstat = connection.conn.prepareStatement(query2);
                    connection.pstat.executeUpdate(); //inseert ke database
                    String query = "INSERT INTO loket2 VALUES (?)";
                    connection.pstat = connection.conn.prepareStatement(query);
                    connection.pstat.setString(1, lbl2.getText());
                    connection.pstat.executeUpdate(); //inseert ke database
                }
                catch (Exception e1){
                    System.out.println("Terjadi error pada saat insert antrian:"+e1);
                }
            }
        });
        btnpgl3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbl3.setText(q.dequeue());
                try{
                    String query2 = "DELETE FROM loket3;";
                    connection.pstat = connection.conn.prepareStatement(query2);
                    connection.pstat.executeUpdate(); //inseert ke database
                    String query = "INSERT INTO loket3 VALUES (?)";
                    connection.pstat = connection.conn.prepareStatement(query);
                    connection.pstat.setString(1, lbl3.getText());
                    connection.pstat.executeUpdate(); //inseert ke database
                }
                catch (Exception e1){
                    System.out.println("Terjadi error pada saat insert antrian:"+e1);
                }
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Coba SD");
        frame.setContentPane(new PegawaiAntrian().formutama);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,400);
    }
}

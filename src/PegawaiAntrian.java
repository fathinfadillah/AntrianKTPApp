import DB.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class PegawaiAntrian {
    public JPanel formutama;
    private JPanel panel2;
    private JButton btnpros;
    private JButton btnpros3;
    private JButton btnpgl1;
    private JButton btnpgl2;
    private JButton btnpgl3;
    private JButton btnpros2;
    public JLabel lbl1;
    public JLabel lbl2;
    public JLabel lbl3;
    public JPanel jp1;
    private JPanel jppp;
    private JPanel jpp;
    private JPanel jpppp;

    public LQueue q = new LQueue();
    DBConnection connection = new DBConnection();

    public PegawaiAntrian() {
        try {
            String query = "SELECT * FROM antrian ORDER BY no_antrian ASC";
            connection.result = connection.stat.executeQuery(query);
            while (connection.result.next()) {
                q.enqueue(connection.result.getString("no_antrian"));
            }

            String query0 = "SELECT * FROM loket3";
            connection.result = connection.stat.executeQuery(query0);
            while (connection.result.next()) {
                lbl3.setText(connection.result.getString("no_antrian"));
            }

            String query01 = "SELECT * FROM loket2";
            connection.result = connection.stat.executeQuery(query01);
            while (connection.result.next()) {
                lbl2.setText(connection.result.getString("no_antrian"));
            }

            String query02 = "SELECT * FROM loket1";
            connection.result = connection.stat.executeQuery(query02);
            while (connection.result.next()) {
                lbl1.setText(connection.result.getString("no_antrian"));
            }
        }
        catch (Exception e1) {
            System.out.println("Terjadi error pada saat insert antrian:" + e1);
        }

        btnpgl1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbl1.setText(q.dequeue());
                try{
                    String query3 = "DELETE FROM antrian WHERE no_antrian = "+lbl1.getText();
                    connection.pstat = connection.conn.prepareStatement(query3);
                    connection.pstat.executeUpdate(); //inseert ke database
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
                    String query3 = "DELETE FROM antrian WHERE no_antrian = "+lbl2.getText();
                    connection.pstat = connection.conn.prepareStatement(query3);
                    connection.pstat.executeUpdate(); //inseert ke database
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
                    String query3 = "DELETE FROM antrian WHERE no_antrian = "+lbl3.getText();
                    connection.pstat = connection.conn.prepareStatement(query3);
                    connection.pstat.executeUpdate(); //inseert ke database
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

        btnpros2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputKTP a= new InputKTP();
                a.menu();
            }
        });

        btnpros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputKTP a= new InputKTP();
                a.menu();
            }
        });
        
        btnpros3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputKTP a= new InputKTP();
                a.menu();
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

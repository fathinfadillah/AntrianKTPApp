import DB.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PengantriAntrian {
    private JButton AMBILNOANTRIANButton;
    public JPanel formutama;
    private JLabel txtsisa;
    private JLabel txttotal;
    private JPanel panel1;
    private JPanel panel2;
    private JButton logOutButton;
    private JPanel panel3;
    public  JLabel lbl1;
    public  JLabel lbl2;
    public  JLabel lbl3;
    private JTextArea jTextArea1;

    //variable yang diperlukan
    public int nomor=0;
    String sisa;

    //koneksi ke database
    DBConnection connection = new DBConnection();

    public PengantriAntrian() {
        setlabeltext();
        AMBILNOANTRIANButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                antrian();
                String total="";
                try{
                    String query = "INSERT INTO antrian VALUES (?)";
                    connection.pstat = connection.conn.prepareStatement(query);
                    connection.pstat.setInt(1, nomor);
                    connection.pstat.executeUpdate(); //inseert ke database
                    String query2 = "INSERT INTO total VALUES (?)";
                    connection.pstat = connection.conn.prepareStatement(query2);
                    connection.pstat.setInt(1, nomor);
                    connection.pstat.executeUpdate(); //inseert ke database
                    String query3 = "SELECT COUNT(no_antrian) AS sisa FROM antrian";
                    connection.result = connection.stat.executeQuery(query3);

                    while (connection.result.next()) {
                        sisa = connection.result.getString("sisa");
                    }

                    String query4 = "SELECT COUNT(total) AS total FROM total";
                    connection.result = connection.stat.executeQuery(query4);
                    while (connection.result.next()) {
                        total = connection.result.getString("total");
                    }

                    JOptionPane.showMessageDialog(null, "Nomor antrian anda adalah "+total+".\nSilakan tunggu panggilan dari loket.","Informasi",JOptionPane.INFORMATION_MESSAGE);
                    txtsisa.setText(sisa);
                    txttotal.setText(total);
                }
                catch (Exception e1){
                    System.out.println("Terjadi error pada saat insert antrian:"+e1);
                }
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formutama.remove(panel1);
                formutama.remove(panel2);
                formutama.remove(panel3);

                MainForm c = new MainForm();
                c.formutama.setVisible(true);

                formutama.setLayout(new java.awt.BorderLayout());
                formutama.add(c.formutama);
                formutama.validate();
            }
        });
    }

    public void setlabeltext() {
        try{
            String query = "SELECT * FROM loket1";
            connection.result = connection.stat.executeQuery(query);
            while (connection.result.next()) {
                lbl1.setText(connection.result.getString("no_antrian"));
            }

            String query2 = "SELECT * FROM loket2";
            connection.result = connection.stat.executeQuery(query2);
            while (connection.result.next()) {
                lbl2.setText(connection.result.getString("no_antrian"));
            }

            String query3 = "SELECT * FROM loket3";
            connection.result = connection.stat.executeQuery(query3);
            while (connection.result.next()) {
                lbl3.setText(connection.result.getString("no_antrian"));
            }
        }
        catch (Exception e1){
            System.out.println("Terjadi error pada saat insert antrian:"+e1);
        }
    }

    public void antrian(){
        try{
            String sql = "SELECT * FROM total ORDER BY total DESC";
            connection.stat = connection.conn.createStatement();
            connection.result = connection.stat.executeQuery(sql);
            if (connection.result.next()) {
                nomor = connection.result.getInt("total");
                nomor = nomor+1;
            }else {
                nomor = 1;
            }
        }catch (Exception e1){
            System.out.println("Terjadi error pada no antrian : " + e1);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Coba SD");
        frame.setContentPane(new PengantriAntrian().formutama);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,400);
    }
}

import DB.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputKTP {
    private JPanel JPInputKTP;
    private JPanel JPMiddleMenu;
    private JButton btnSimpan;
    private JTextField txtNIK;
    private JComboBox cmbJenisKelamin;
    private JTextField txtNama;
    private JTextField txtTTL;
    private JTextField txtAlamat;
    private JTextField txtRTRW;
    private JTextField txtKelurahan;
    private JTextField txtKecamatan;
    private JComboBox cmbAgama;
    private JComboBox cmbStatus;
    private JTextField txtPekerjaan;
    private JComboBox cmbKewarganegaraan;
    private JComboBox cmbGoldar;
    private JButton btnKembali;
    private JButton btnBatal;
    private JTable tblDataKTP;
    private JPanel JPRightMenu;

    //membuat object dari class DBConnection
    DBConnection connection = new DBConnection();
    private DefaultTableModel model;

    public InputKTP() {
        btnBatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNIK.setText("");
                txtNama.setText("");
                txtTTL.setText("");
                cmbJenisKelamin.setSelectedItem("");
                txtAlamat.setText("");
                txtRTRW.setText("");
                txtKelurahan.setText("");
                txtKecamatan.setText("");
                cmbAgama.setSelectedItem("");
                cmbStatus.setSelectedItem("");
                txtPekerjaan.setText("");
                cmbKewarganegaraan.setSelectedItem("");
                cmbGoldar.setSelectedItem("");
            }
        });

        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //object dari ADT Pengantri
                Pengantri data = new Pengantri();

                data.setnik(txtNIK.getText());
                data.setnama(txtNama.getText());
                data.setttl(txtTTL.getText());
                data.setjenis((String)cmbJenisKelamin.getSelectedItem());
                //String alamat = txtAlamat.getText() + ", " + txtRTRW.getText() + ", " + txtKelurahan.getText() +
                //        ", " + txtKecamatan.getText();
                data.setalamat(txtAlamat.getText());
                data.setagama((String)cmbAgama.getSelectedItem());
                data.setStatus((String)cmbStatus.getSelectedItem());
                data.setpekerjaan(txtPekerjaan.getText());
                data.setKewarganegaraan((String)cmbKewarganegaraan.getSelectedItem());
                data.setgoldar((String)cmbGoldar.getSelectedItem());

                //statement untuk proses input ke database
                try{
                    String query = "INSERT INTO data_ktp VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                    connection.pstat = connection.conn.prepareStatement(query);
                    connection.pstat.setString(1, data.getNik());
                    connection.pstat.setString(2, data.getNama());
                    connection.pstat.setString(3, data.getTtl());
                    connection.pstat.setString(4, data.getJenis());
                    connection.pstat.setString(5, data.getAlamat());
                    connection.pstat.setString(6, txtKelurahan.getText());
                    connection.pstat.setString(7, txtKecamatan.getText());
                    connection.pstat.setString(8, data.getAgama());
                    connection.pstat.setString(9, data.getStatus());
                    connection.pstat.setString(10, data.getPekerjaan());
                    connection.pstat.setString(11, data.getKewarganegaraan());
                    connection.pstat.setString(12, data.getGoldar());

                    connection.pstat.executeUpdate();   //insert ke database
                    connection.pstat.close();           //menutup koneksi db
                }
                catch (Exception e1){
                    System.out.println("Terjadi error pada saat insert data : "+e1);
                }
                JOptionPane.showMessageDialog(null, "Insert data berhasil");
            }
        });

        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //membuat tabel model
        model = new DefaultTableModel();

        //menambahkan table model ke table
        tblDataKTP.setModel(model);

        //memanggil method addColomn dan method loadData
        addColomn();
        loadData();
    }

    public void addColomn(){
        model.addColumn("NIK");
        model.addColumn("Nama");
        model.addColumn("Tempat, Tanggal Lahir");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Alamat");
        model.addColumn("Kelurahan");
        model.addColumn("Kecamatan");
        model.addColumn("Agama");
        model.addColumn("Status");
        model.addColumn("Pekerjaan");
        model.addColumn("Kewarganegaraan");
        model.addColumn("Golongan Darah");
    }

    public void loadData(){
        //menghapus seluruh data ditampilkan(jika ada) untuk tampilan pertama
        model.getDataVector().removeAllElements();

        //memberi tahu data telah kosong
        model.fireTableDataChanged();

        try{
            DBConnection connection = new DBConnection();
            connection.stat = connection.conn.createStatement();
            String query = "SELECT * FROM data_ktp";
            connection.result = connection.stat.executeQuery(query);

            //lakukan perbaris data
            while(connection.result.next()){
                Object[] obj = new Object[12];
                obj[0] = connection.result.getString("ktp_nik");
                obj[1] = connection.result.getString("ktp_nama");
                obj[2] = connection.result.getString("ktp_ttl");
                obj[3] = connection.result.getString("ktp_jkel");
                obj[4] = connection.result.getString("ktp_alamat");
                obj[5] = connection.result.getString("ktp_kel");
                obj[6] = connection.result.getString("ktp_kec");
                obj[7] = connection.result.getString("ktp_agama");
                obj[8] = connection.result.getString("ktp_status");
                obj[9] = connection.result.getString("ktp_pekerjaan");
                obj[10] = connection.result.getString("ktp_kewarganegaraan");
                obj[11] = connection.result.getString("ktp_goldar");
                model.addRow(obj);
            }
            connection.stat.close();
            connection.result.close();
        }
        catch (Exception e){
            System.out.println("Terjadi error saat load data : " +e);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("InputKTP");
        frame.setContentPane(new InputKTP().JPInputKTP);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

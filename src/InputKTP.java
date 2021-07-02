import DB.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputKTP {
    private JPanel JPInputKTP;
    private JPanel JPMiddleMenu;
    private JButton btnSimpan;
    private JTextField txtNIK;
    private JComboBox cmbJenisKelamin;
    private JTextField txtNama;
    private JTextField txtTTL;
    private JTextField txtAlamat;
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
    static JFrame frame = new JFrame("InputKTP");

    //membuat object dari class DBConnection
    DBConnection connection = new DBConnection();

    //table
    private DefaultTableModel model;

    public InputKTP() {
        btnBatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });

        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //object dari ADT Pengantri
                Pengantri data = new Pengantri();

                //validasi jika masih ada data yang belum diisi
                if (txtNIK.getText().equals("") || txtNama.getText().equals("") || txtTTL.getText().equals("")
                    || txtAlamat.getText().equals("") ||  txtKelurahan.getText().equals("")
                    || txtKecamatan.getText().equals("") || txtPekerjaan.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Data tidak boleh ada yang kosong!");
                }
                else if (txtNIK.getText().length() < 16 || txtNIK.getText().length() > 16) {
                    JOptionPane.showMessageDialog(null, "NIK harus 16 digit!");
                }
                else {
                    try {
                        //select NIK dari database jika NIK sudah pernah terdaftar
                        connection.stat = connection.conn.createStatement();
                        String query2 = "SELECT * FROM data_ktp WHERE ktp_nik = '" + txtNIK.getText() + "'";
                        connection.result = connection.stat.executeQuery(query2);
                        //lakukan perbaris data
                        if (connection.result.next()) {
                            JOptionPane.showMessageDialog(null, "NIK sudah terdaftar!", "Input Data",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            //set data KTP dari input user ke ADT pengantri
                            data.setnik(txtNIK.getText());
                            data.setnama(txtNama.getText());
                            data.setttl(txtTTL.getText());
                            data.setjenis((String)cmbJenisKelamin.getSelectedItem());
                            data.setalamat(txtAlamat.getText());
                            data.setagama((String)cmbAgama.getSelectedItem());
                            data.setStatus((String)cmbStatus.getSelectedItem());
                            data.setpekerjaan(txtPekerjaan.getText());
                            data.setKewarganegaraan((String)cmbKewarganegaraan.getSelectedItem());
                            data.setgoldar((String)cmbGoldar.getSelectedItem());
                            data.setKecamatan(txtKecamatan.getText());
                            data.setKelurahan(txtKelurahan.getText());

                            //konfirmasi untuk user apabila data yang sudah diinput ke database tidak bisa diubah
                            int reply = JOptionPane.showConfirmDialog(null, " Data yang telah diinput tidak dapat diedit." +
                                    "\n Pastikan seluruh data sudah benar. \n Yakin input data ini?", "Validasi", JOptionPane.YES_NO_OPTION);

                            if (reply == JOptionPane.YES_OPTION) {
                                //statement untuk proses input ke database
                                String query = "INSERT INTO data_ktp VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                                connection.pstat = connection.conn.prepareStatement(query);
                                connection.pstat.setString(1, data.getNik());
                                connection.pstat.setString(2, data.getNama());
                                connection.pstat.setString(3, data.getTtl());
                                connection.pstat.setString(4, data.getJenis());
                                connection.pstat.setString(5, data.getAlamat());
                                connection.pstat.setString(6, data.getKelurahan());
                                connection.pstat.setString(7, data.getKecamatan());
                                connection.pstat.setString(8, data.getAgama());
                                connection.pstat.setString(9, data.getStatus());
                                connection.pstat.setString(10, data.getPekerjaan());
                                connection.pstat.setString(11, data.getKewarganegaraan());
                                connection.pstat.setString(12, data.getGoldar());
                                connection.pstat.executeUpdate();   //insert ke database
                                JOptionPane.showMessageDialog(null, "Data berhasil diinput!", "Input Data", JOptionPane.INFORMATION_MESSAGE);
                                loadData();
                                clear();
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Data batal diinput!", "Input Data", JOptionPane.INFORMATION_MESSAGE);
                                System.exit(0);
                            }
                        }
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,"Terjadi eror saat load Data: " +ex);
                    }
                }
            }
        });

        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        //Validasi
        txtNIK.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                char c = e.getKeyChar();
                if(Character.isDigit(c) || Character.isISOControl(c)) {
                    txtNIK.setEditable(true);
                } else {
                    txtNIK.setEditable(false);
                }
            }
        });

        txtNama.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                char c = e.getKeyChar();
                if(Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c)) {
                    txtNama.setEditable(true);
                } else {
                    txtNama.setEditable(false);
                }
            }
        });

        txtKelurahan.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                char c = e.getKeyChar();
                if(Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c)) {
                    txtKelurahan.setEditable(true);
                } else {
                    txtKelurahan.setEditable(false);
                }
            }
        });

        txtKecamatan.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                char c = e.getKeyChar();
                if(Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c)) {
                    txtKecamatan.setEditable(true);
                } else {
                    txtKecamatan.setEditable(false);
                }
            }
        });

        txtPekerjaan.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                char c = e.getKeyChar();
                if(Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c)) {
                    txtPekerjaan.setEditable(true);
                } else {
                    txtPekerjaan.setEditable(false);
                }
            }
        });

        //membuat tabel model
        model = new DefaultTableModel();

        //menambahkan table model ke table
        tblDataKTP.setModel(model);

        //memanggil method addColomn dan method loadData
        addColumn();
        loadData();
    }

    public void clear() {
        //method clear untuk membuat data textbox/combobox menjadi kosong
        txtNIK.setText("");
        txtNama.setText("");
        txtTTL.setText("");
        txtAlamat.setText("");
        txtKecamatan.setText("");
        txtKelurahan.setText("");
        txtPekerjaan.setText("");
        cmbJenisKelamin.setSelectedItem("");
        cmbAgama.setSelectedItem("");
        cmbStatus.setSelectedItem("");
        cmbKewarganegaraan.setSelectedItem("");
        cmbGoldar.setSelectedItem("");
    }

    //Header table
    public void addColumn(){
        model.addColumn("NIK");
        model.addColumn("Nama");
        model.addColumn("Tempat, Tanggal Lahir");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Alamat");
    }

    public void loadData() {
        //menghapus seluruh data ditampilkan(jika ada) untuk tampilan pertama
        model.getDataVector().removeAllElements();

        //memberi tahu data telah kosong
        model.fireTableDataChanged();

        try {
            //koneksi untuk mendapatkan data KTP dari database
            DBConnection connection = new DBConnection();
            connection.stat = connection.conn.createStatement();
            String query = "SELECT * FROM data_ktp";
            connection.result = connection.stat.executeQuery(query);

            //lakukan perbaris data
            while (connection.result.next()) {
                Object[] obj = new Object[12];
                obj[0] = connection.result.getString("ktp_nik");
                obj[1] = connection.result.getString("ktp_nama");
                obj[2] = connection.result.getString("ktp_ttl");
                obj[3] = connection.result.getString("ktp_jkel");
                obj[4] = connection.result.getString("ktp_alamat");
                model.addRow(obj);
            }
            connection.stat.close();
            connection.result.close();
        } catch (Exception e) {
            System.out.println("Terjadi error saat load data : " + e);
        }
    }

    public void menu() {
        frame.setContentPane(new InputKTP().JPInputKTP);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}

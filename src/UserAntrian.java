import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserAntrian {
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
    public int nomor=0;
    public LQueue q = new LQueue();
    public UserAntrian() {
        AMBILNOANTRIANButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nomor++;
                PegawaiAntrian b = new PegawaiAntrian();
                String antri = Integer.toString(nomor);
                q.enqueue(antri);
                String sisa = Integer.toString(q.size);
                String total = Integer.toString(nomor);
                txtsisa.setText(sisa);
                txttotal.setText(total);
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
    public static void main(String[] args) {
        JFrame frame = new JFrame("Coba SD");
        frame.setContentPane(new UserAntrian().formutama);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,400);
    }
}

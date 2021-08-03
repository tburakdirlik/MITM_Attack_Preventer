package secure.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class SN extends javax.swing.JFrame {

    private static final String ARP_GET_IP_HW = "arp -a";
    static String arpTables;
    static String checkTables[] = new String[2];

    static String mainoutput = null;

    public static String getIpAddress() throws UnknownHostException {
        // Returns the instance of InetAddress containing
        // local host name and address
        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println("System IP Address : " + (localhost.getHostAddress()).trim());

        // Find public IP address
        String systemipaddress = "";
        try {
            URL url_name = new URL("http://bot.whatismyipaddress.com");
            BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
            systemipaddress = sc.readLine().trim();

        } catch (Exception e) {
            systemipaddress = "Cannot Execute Properly";
        }
        System.out.println("Public IP Address : " + systemipaddress + "\n");

        return "System IP Address : " + (localhost.getHostAddress()).trim() + "\n" + "Public IP Address    : " + systemipaddress;
    }

    //----------------------------------------------------------------------------------------------------------
    public static String getARPTable(String cmd) throws IOException {
        Scanner s = new Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    //----------------------------------------------------------------------------------------------------------
    public SN() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ip_area = new javax.swing.JTextArea();
        start_button = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        arp_table1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 800));

        ip_area.setColumns(20);
        ip_area.setRows(5);
        jScrollPane1.setViewportView(ip_area);

        start_button.setText("START");
        start_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start_buttonActionPerformed(evt);
            }
        });

        arp_table1.setColumns(20);
        arp_table1.setRows(5);
        jScrollPane2.setViewportView(arp_table1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(start_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(start_button)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void start_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_buttonActionPerformed

        try {
            ip_area.setText(getIpAddress());
            arp_table1.setText(getARPTable(ARP_GET_IP_HW));

        } catch (UnknownHostException ex) {
            Logger.getLogger(SN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_start_buttonActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }

            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new SN().setVisible(true);

            }

        });
        
        
        
        
        
             boolean attack = false;
        while (!attack) {

            try {
                arpTables = getARPTable(ARP_GET_IP_HW);
                checkTables[0] = arpTables;

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SN.class.getName()).log(Level.SEVERE, null, ex);
                }

                arpTables = getARPTable(ARP_GET_IP_HW);
                checkTables[1] = arpTables;

                if (checkTables[0].equals(checkTables[1])) {

                    mainoutput = "There is a no problem";

                   

                    System.out.println("There is no problem");

                } else {
                    mainoutput = "There is a man in the middle attack";
                    System.out.println("There is a man in the middle attack");
                    String filepath = "nuclear.wav";

                    musicStuff musicObject = new musicStuff();
                    musicObject.playMusic(filepath);
                    attack = true;

                }
            } catch (IOException ex) {
                Logger.getLogger(SN.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea arp_table1;
    private javax.swing.JTextArea ip_area;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton start_button;
    // End of variables declaration//GEN-END:variables
}

class musicStuff {

    void playMusic(String musicLocation) {

        try {
            File musicPath = new File(musicLocation);

            if (musicPath.exists()) {

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();

                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);

                JOptionPane.showMessageDialog(null, "Pres ok to stop playing");

                long clipTimePosition = clip.getMicrosecondPosition();
                clip.stop();
                clip.setMicrosecondPosition(clipTimePosition);
            } else {
                System.out.println("Can not find file ");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}

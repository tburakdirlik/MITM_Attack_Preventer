package mpreventer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;


public class Mpreventer {

    private static final String ARP_GET_IP_HW = "arp -a";
    static String arpTables;
    static String checkTables[] = new String[2];

    public static void main(String[] args) throws IOException, InterruptedException {

        mitm_main_codes();

    }

    //----------------------------------------------------------------------------------------------------------
    public static void getIpAddress() throws UnknownHostException {
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
    }

    //----------------------------------------------------------------------------------------------------------
    public static String getARPTable(String cmd) throws IOException {
        Scanner s = new Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    //----------------------------------------------------------------------------------------------------------
    public static void mitm_main_codes() throws UnknownHostException, IOException, InterruptedException {
        getIpAddress();
        System.out.println("--------------------------------------------------");

        System.out.println(getARPTable(ARP_GET_IP_HW));

        System.out.println("--------------------------------------------------");

        boolean attack = false;
        while (!attack) {

            arpTables = getARPTable(ARP_GET_IP_HW);
            checkTables[0] = arpTables;

            TimeUnit.SECONDS.sleep(1);

            arpTables = getARPTable(ARP_GET_IP_HW);
            checkTables[1] = arpTables;

            if (checkTables[0].equals(checkTables[1])) {

                System.out.println("There is no problem");

            } else {
                System.out.println("There is a man in the middle attack");
                String filepath = "alert.wav";

                musicStuff musicObject = new musicStuff();
                musicObject.playMusic(filepath);
                attack = true;

            }

        }
    }
    //----------------------------------------------------------------------------------------------------------

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

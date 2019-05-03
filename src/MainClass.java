import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class MainClass {
    public static void main(String argv[]) {
        InfoIP testing;
        try {
            if (argv.length > 0) {
                testing = new InfoIP(argv[0]);
            } else {
                testing = new InfoIP(" ");  // possibility to type address here instead of argv[1]
            }

            testing.checkSubnet();
            String IPinfo = testing.summary();
            System.out.println(IPinfo);
            try {
                FileWriter fstream = new FileWriter("IPinformations.docx");
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(IPinfo);
                out.close();
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
            //if (NetworkCalculations.isHost(testing.binaryIP, testing.binaryNetworkAdress, testing.binaryBroadcastAdress)) {
            if(NetworkCalculations.isHost(testing.adressIP,testing.NetworkString,testing.BroadcastString)){
                System.out.println("Address IP is host address, do you want to send ping? y/n");
                Scanner scanner = new Scanner(System.in);
                String answer = scanner.nextLine();
                if (answer.equals("y")) {
                    System.out.println("\n Ping " + testing.adressIP + ": \nY"
                            + PingClass.runSystemCommand("ping " + testing.adressIP));
                }
            }

        } catch (IllegalArgumentException ex) {
            System.err.println(ex.toString());
            System.exit(0);
        }
    }
}

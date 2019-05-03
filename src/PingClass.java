import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PingClass {
    public static String runSystemCommand(String ipString) {

        try {
            Process p = Runtime.getRuntime().exec(ipString);
            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));

            String outputInfo = "";
            while ((inputStream.readLine()) != null) {
                System.out.println(inputStream.readLine());
                outputInfo+=(inputStream.readLine())+"\n";
            }
            return outputInfo;
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Unable to ping given address";
        }
    }
}

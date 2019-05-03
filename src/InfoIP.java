import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Scanner.*;
//import org.apache.commons.validator.routines.InetAddressValidator;

public class InfoIP {
    String adressIP;
    int[] binaryIP;
    NetworkInterface networkInterface;
    short subnetPrefix;
    int[] binarySubnet;
    String binSubnetString;
    char oldClass;

    int[] binaryNetworkAdress;
    String binNetworkString;
    String NetworkString;
    int[] binaryBroadcastAdress;
    String binBreadcastString;
    String BroadcastString;

    int[] firstHost;
    int[] lastHost;

    InfoIP(String ipStr) throws IllegalArgumentException{

        if (ipStr.isBlank()) {
            try {
                InetAddress InetAdressIP = InetAddress.getLocalHost();
                if (isAdressCorrect(InetAdressIP.getHostAddress())) {
                    adressIP=InetAdressIP.getHostAddress();
                    try {
                        networkInterface = NetworkInterface.getByInetAddress(InetAdressIP);
                        subnetPrefix = networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
                    } catch (Exception e) {
                        System.err.println(e.toString());
                    }
                } else {
                    System.err.println("IP adress is not valid");
                }

            } catch (UnknownHostException e) {
                System.err.println(e.toString());
            }
        }
        else if(!isAdressCorrect(ipStr)){
            throw(new IllegalArgumentException("IP adrress is not correct!"));
        }
        else {
            String[] data = ipStr.split("/");
            adressIP=data[0];
            subnetPrefix=(short)Integer.parseInt(data[1]);
        }
    }
    public void checkSubnet(){
        binarySubnet = NumericalOperations.subnetBinary(subnetPrefix);
        binaryIP = NumericalOperations.ipBinary(adressIP);
        binSubnetString = NumericalOperations.convertToBinaryDottedString(binarySubnet);

        binaryNetworkAdress = NetworkCalculations.networkAdress(binaryIP,binarySubnet);
        binNetworkString = NumericalOperations.convertToBinaryDottedString(binaryNetworkAdress);

        binaryBroadcastAdress = NetworkCalculations.broadcastAdress(binarySubnet, binaryNetworkAdress);
        binBreadcastString = NumericalOperations.convertToBinaryDottedString(binaryBroadcastAdress);
        oldClass = NetworkCalculations.netClass(adressIP);

        firstHost = NetworkCalculations.firstHost(binaryNetworkAdress);
        lastHost = NetworkCalculations.lastHost(binaryBroadcastAdress);
    }
    public String summary(){
        String message;
        message = "IP: "+ adressIP+"\n";

        message+= "IP binary: "+ NumericalOperations.convertToBinaryDottedString(binaryIP)+"\n";
        message+= "Subnet mask: "+ NumericalOperations.convertBinStringToDecDottedString(binSubnetString);
        message+= "( "+ NumericalOperations.convertToBinaryDottedString(binarySubnet)+")\n";
        NetworkString=NumericalOperations.convertBinStringToDecDottedString(binNetworkString);
        message+= "Adress:  "+NetworkString+"/"+ subnetPrefix + " (old Class "+ oldClass +") ";
        message+= "("+ binNetworkString+ "\n";
        BroadcastString=NumericalOperations.convertBinStringToDecDottedString(binBreadcastString);
        message+= "Broadcast:  "+BroadcastString;
        message+= "("+ binBreadcastString+ ")\n";
        message+= "First host address: "+NumericalOperations.convertBinStringToDecDottedString(NumericalOperations.convertToBinaryDottedString(firstHost));
        message+= " ( "+NumericalOperations.convertToBinaryDottedString(firstHost)+ ")\n";
        message+= "Last host address: "+NumericalOperations.convertBinStringToDecDottedString(NumericalOperations.convertToBinaryDottedString(lastHost));
        message+= " ( "+NumericalOperations.convertToBinaryDottedString(firstHost)+ ")\n";
        message+= "Max number of hosts: "+NetworkCalculations.numberOfHosts(subnetPrefix) +"\n";
        if(NetworkCalculations.isprivate(adressIP)){
            message+= "Net is private \n";
        }
        else {
            message+= "Net is public";
        }

        return message;
    }

    public static boolean isAdressCorrect(String ipAdress) {
        String ipString=ipAdress;
        String[] IpAndMask;
        if(ipAdress.contains("/"))
        {
            IpAndMask = ipAdress.split("/");
            if(Integer.parseInt( IpAndMask[1]) >24 || Integer.parseInt( IpAndMask[1]) <0)
                return false;
            ipString=IpAndMask[0];
        }
        try {
            if ( ipString == null || ipString.isEmpty() ) {
                return false;
            }

            String[] addressParts = ipString.split( "\\." );
            if ( addressParts.length != 4 ) {
                return false;
            }
            for ( String s : addressParts ) {
                int i = Integer.parseInt( s );
                if ( (i < 0) || (i > 255) ) {
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}

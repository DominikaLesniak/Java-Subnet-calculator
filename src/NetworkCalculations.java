public class NetworkCalculations {

    public static int[] networkAdress(int[] ip, int[] mask){
        int[] adress = new int[32];
        for (int i = 0; i < 32; i++) {
            adress[i]= ip[i] * mask[i];
        }
        return adress;
    }
    public static int[] broadcastAdress(int[] mask, int[] netAdress){
        int[] broadcastAdr = new int[32];
        for (int i=0;i<32;i++) {
            if(mask[i] == 1){
                broadcastAdr[i]=netAdress[i];
            }
            else{
                broadcastAdr[i]=1;
            }
        }
        return broadcastAdr;
    }
    public static char netClass(String ipString){
        char subnetClass;
        String[] ipParts = ipString.split( "\\." );
        if(Integer.valueOf(ipParts[0]) < 128){
            subnetClass='A';
        }
        else if(Integer.valueOf(ipParts[0]) < 192){
            subnetClass='B';
        }
        else if(Integer.valueOf(ipParts[0]) < 224){
            subnetClass='C';
        }
        else if(Integer.valueOf(ipParts[0]) < 240){
            subnetClass='D';
        }
        else{
            subnetClass ='E';
        }
        return subnetClass;
    }
    public static boolean isprivate(String ipString){
        String[] ipParts = ipString.split( "\\." );
        if(Integer.valueOf(ipParts[0]).equals(10)){
            return true;
        }
        else if(Integer.valueOf(ipParts[0]).equals(172) && Integer.valueOf(ipParts[1])>16 && Integer.valueOf(ipParts[1])<32){
            return true;
        }
        else if(Integer.valueOf(ipParts[0]).equals(192) && Integer.valueOf(ipParts[1]).equals(168)){
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean isHost(int[] binIPAdress, int[] binNetAdress, int[] binBroadcastAdress ){
        boolean is=true;
        for (int i = 0; i < 32; i++) {
            if(binIPAdress[i]==binNetAdress[i] ){
                is= false;
            }
            else if(binIPAdress[i]==binBroadcastAdress[i]){
                is= false;
            }
            else {
                is=true;
                i=32;
            }
        }
        return is;
    }
    public static boolean isHost(String IPAdress, String NetAdress, String BroadcastAdress ){
        String[] ip=IPAdress.split("\\.");
        String[] net=NetAdress.split("\\.");
        String[] broad=BroadcastAdress.split("\\.");
        boolean is =false;
        for (int i = 0; i < 4; i++) {
            if(ip[i].equals(net[i]) || ip[i].equals(broad[i])){
                is=false;
            }
            else {
                is = true;
                break;
            }
        }
        return is;
    }
    public static int[] firstHost(int[] binNetAdress){
        int[] result = binNetAdress.clone();
        int i=31;
        while(result[i]==1) {
            i--;
        }
        result[i]=1;
        return result;
    }
    public static int[] lastHost(int[] breadcastAdress){
        int[] result = breadcastAdress.clone();
        int i=31;
        if(result[i]==0) {
            i--;
            while (result[i] == 0) {
                result[i] = 1;
                i--;
            }
        }
        result[i]=1;
        return result;
    }
    public static int numberOfHosts(int subnetPrefix){
        return (int)(Math.pow(2,32-subnetPrefix)-2);
    }

}

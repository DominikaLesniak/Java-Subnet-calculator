public class NumericalOperations {

    public static int[] subnetBinary(int prefixLength){
        int[] subnet = new int[32];
        for(int i=0;i<32;i++){
            if(i<prefixLength){
                subnet[i]=1;
            }
            else {
                subnet[i]=0;
            }
        }
        return subnet;
    }

    public static int[] ipBinary(String ipString){
        int[] ipBinResult = new int[32];
        String[] binParts = new String[4];

        String[] decimParts = ipString.split( "\\." );
        for ( int i=0;i<4;i++ ) {
            int numericalPart = Integer.parseInt( decimParts[i] );
            binParts[i] = Integer.toBinaryString(numericalPart);
            while(binParts[i].length() < 8) {
                binParts[i] = '0' + binParts[i];
            }
        }
        //System.out.println(binParts[0]);
        int j=0;
        for (int i = 0; i < 32; i++) {
            ipBinResult[i] = (int)( binParts[j].charAt(i%8)) - 48;
            //System.out.print(ipBinResult[i]);
            if(i==7 || i==15 || i==23){
                j++;
            }
        }
        return ipBinResult;
    }

    public static String convertToBinaryDottedString(int[] tab){
        String result = new String();
        for (int i = 1; i < 33; i++) {
            result+= Integer.toString(tab[i-1]);
            if(i%8==0 && i!=32){
                result+=".";
            }
        }
        //System.out.println(result);
        return result;
    }
    public static String convertBinStringToDecDottedString(String binString){
        String[] binParts = binString.split( "\\." );
        String result = new String();

        for (int i=0;i<4;i++) {
            int decPart= Integer.parseInt(binParts[i],2);
            result+= Integer.toString(decPart);
            if(i!=3) {
                result += ".";
            }
        }
        //System.out.println(result);
        return result;
    }
}

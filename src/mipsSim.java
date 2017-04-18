/**
 * Created by Aaron on 4/10/17.
 */
import java.io.*;

public class mipsSim {
    public static void main(String[] args) {

        System.out.println("mipsSim \nv1.0.0 \nA MIPS Simulator by Aaron R Miller");

        if (args.length < 1) {
            System.out.println("NO ARGUMENTS PASSED \n TERMINATING");
            return;
        }
        int[] MEM = new int[1048576];
        int[] R = new int[32];
        int PC = 0;
        int nPC = PC + 4;
        int LO;
        int HI;
        mipsCom mainframe;
        String mode = args[1];

        try {

            FileReader fr = new FileReader(args[0]);
            BufferedReader fin = new BufferedReader(fr);

            String curline = null;

            while ((curline = fin.readLine()) != null) {

                if (curline.startsWith("[")) {

                    curline.trim();

                    String[] parts = curline.split("\\s+");

                    for (int i = 0; i < parts.length; i++) {

                        if (parts[i].equals("[PC]")) {
                            PC = stringReader(parts[i + 1], 2, 0);
                            nPC = PC + 4;
                            i++;
                        }

                        if (parts[i].startsWith("[R")) {
                            int register = Integer.parseInt(parts[i].substring(2, parts[i].length() - 2));
                            R[register] = stringReader(parts[i + 1], 2, 0);
                            i++;
                        }
                            //Need to be able to add more information for cases such as
                            //# Data
                            //[0x00007000]		        0x6e756f43  0x6f742074  0x0a303120  0x00000a00
                        if (parts[i].startsWith("[0x")) {
                            MEM[stringReader(parts[i], 3, 1) / 4] = stringReader(parts[i + 1], 2, 0);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("FILE NOT FOUND \n TERMINATING");

        }

        while (MEM[PC / 4] != 0) {

           System.out.println( Integer.toBinaryString(MEM[PC/4] >> 24));
           PC = nPC;
           nPC = PC+4;
        }
    }

    public static int stringReader(String read, int shift, int drop){
        String sub = read.substring(shift, read.length() - drop);
        Long a = Long.parseLong(sub, 16);
        return a.intValue();
    }
    public static int Add(int a, int b){
        return a+b;
    }
    public static int Addi(int a, int b){
        return a+b;
    }
    public static int Addiu(int a, int b){
        return a+b;
    }
    public static int Addu(int a, int b){
        return a+b;
    }
    public static int And(int a, int b){
        return a&b;
    }
    public static int Andi(int a, int b){
        return a&b;
    }
    public static boolean Beq(int a, int b){
        return( a == b);
    }
   // public static boolean B
}
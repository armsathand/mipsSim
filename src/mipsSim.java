/**
 * Created by Aaron on 4/10/17.
 */
import java.io.*;
import java.util.Scanner;
public class mipsSim {
    public static void main(String[] args) {

        System.out.println("mipsSim \nv1.0.0 \nA MIPS Simulator by Aaron R Miller");

        if (args.length < 1) {
            System.out.println("NO ARGUMENTS PASSED \n TERMINATING");
            return;
        }
        mipsCom mainframe = new mipsCom(1048576,32,4);
        mipsInstruct[] program;
        String mode = args[1];

        Scanner in = new Scanner(System.in);

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
                            mainframe.PC = stringReader(parts[i + 1], 2, 0);
                            mainframe.nPC = mainframe.PC + 4;
                            i++;
                        }

                        if (parts[i].startsWith("[R")) {
                            int register = Integer.parseInt(parts[i].substring(2, parts[i].length() - 2));
                            mainframe.R[register] = stringReader(parts[i + 1], 2, 0);
                            i++;
                        }
                            //Need to be able to add more information for cases such as
                            //# Data
                            //[0x00007000]		        0x6e756f43  0x6f742074  0x0a303120  0x00000a00
                        if (parts[i].startsWith("[0x")) {
                            mainframe.MEM[stringReader(parts[i], 3, 1) / 4] = stringReader(parts[i + 1], 2, 0);
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("FILE NOT FOUND \n TERMINATING");
        }

        while (mainframe.MEM[mainframe.PC / 4] != 0) {
           System.out.println( Integer.toBinaryString(mainframe.MEM[mainframe.PC/4] >> 24));
           mainframe.PC = mainframe.nPC;
           mainframe.nPC = mainframe.PC+4;
        }

    }

    public static int stringReader(String read, int shift, int drop){
        String sub = read.substring(shift, read.length() - drop);
        Long a = Long.parseLong(sub, 16);
        return a.intValue();
    }
}
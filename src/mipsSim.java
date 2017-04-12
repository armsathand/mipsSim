/**
 * Created by Aaron on 4/10/17.
 */
import java.io.*;

public class mipsSim {
    public static void main(String[] args){

        System.out.println("mipsSim \nv1.0.0 \nA MIPS Simulator by Aaron R Miller");

        if(args.length < 1){
            System.out.println("NO ARGUMENTS PASSED \n TERMINATING");
            return;
        }
        int[] mem = new int[1048576];
        int[] genPop = new int[32];
        int PC;
        int nPC;
        int LO;
        int HI;

        String mode = args[1];

        try {
            FileReader fr = new FileReader(args[0]);
            BufferedReader fin = new BufferedReader(fr);

            String curline = null;


        } catch (IOException e){
                System.out.println("FILE NOT FOUND \n TERMINATING");
        }

    }
}
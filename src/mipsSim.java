/**
 * Created by Aaron on 4/10/17.
 */
import java.io.*;

public class mipsSim {
    public static void main(String[] args){

        System.out.print("mipsSim - A MIPS Simulator by Aaron R Miller");

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

        mode = args[1];
        file = args[0];

        FileInputStream in = file;






    }
}
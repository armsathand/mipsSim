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
        int[] MEM = new int[1048576];
        int[] R = new int[32];
        int PC;
        int nPC;
        int LO;
        int HI;

        String mode = args[1];

        try {

            FileReader fr = new FileReader(args[0]);
            BufferedReader fin = new BufferedReader(fr);

            String curline = null;

            while ((curline = fin.readLine()) != null){

                if (curline.startsWith("[")) {

                    curline.trim();

                    String[] parts = curline.split("\\s+");

                    for(int i = 0; i < parts.length; i++){

                        if(parts[i].equals("[PC]")) {
                            int placeholder = 0;
                            PC = placeholder;
                            nPC = placeholder +4;
                        }

                        if(parts[i].startsWith("[R")){
                            int placeholder =0;
                            int register = Integer.parseInt(parts[i].substring(2,parts[i].length()-2));
                            R[register] = placeholder;
                        }

                        if(parts[i].startsWith("[0x") ){
                            String sub = parts[i].substring(3, parts[i].length()-1);
                            System.out.println(sub);
                            long a = Long.parseLong(sub, 16);
                            System.out.println(a);
                            int placeholder = 0;
                            //NOT SAFE!
                            MEM[(int) a] = placeholder;
                        }
                    }
                }
            }

        } catch (IOException e){
                System.out.println("FILE NOT FOUND \n TERMINATING");
        }

    }
}
/**
 * Created by armsathand on 4/18/2017.
 **/
public class mipsCom {

    int[] MEM;
    int[] R;
    int PC = 0;
    int nPC;
    int LO;
    int HI;
    int offset;

    mipsInstruct[] instructs;

    public mipsCom(int memory, int register, int off){

        //Register  = 32, Memory = 1048576
        MEM = new int[memory];
        R = new int[register];
        nPC = PC + offset;
        offset = off;

    }
    public void advance(){

        PC = nPC;
        nPC = PC + 4;

    }
    public void advance(int j){

        PC = nPC;
        nPC = PC + j;

    }
    public void addInsts(){
        //fucks up if MIPS instruct = 0 so need to find a better way to implement this shit
        int i = 0;
        while(MEM[i] != 0 && MEM[i+1] != 0){
            i++;
        }
        mipsInstruct[] temp = new mipsInstruct[i];
        for(int z =0; z < temp.length; z++){
            temp[z] = new mipsInstruct(MEM[z], this);
        }
        instructs = temp;
    }
    public void debug(java.util.Scanner input){
        System.out.println("DEBUGGING MODE STARTED");
        String in = "Continue";
        while(in.equals("Continue")){

        }


    }
    public void nonDebug(){
        while(MEM[nPC/4] != 0 && MEM[(nPC+4)/4] != 0){
            mipsInstruct ins = new mipsInstruct(MEM[PC], this);
            if(ins.i){

            } else if (ins.j){

            } else { // ins.r

            }
        }
    }
}

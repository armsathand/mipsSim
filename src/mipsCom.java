/**
 * Created by armsathand on 4/18/2017.
 */
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
    public void addInsts(mipsInstruct[] inst){
        instructs = inst;
    }
    public void debug(java.util.Scanner input){
        System.out.println("DEBUGGING MODE STARTED");


    }
    public void nonDebug(){

    }
}

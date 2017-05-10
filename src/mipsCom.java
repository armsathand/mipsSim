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
    java.util.Scanner scn;

    mipsInstruct[] instructs;

    public mipsCom(int memory, int register, int off, java.util.Scanner scan){

        //Register  = 32, Memory = 1048576
        MEM = new int[memory];
        R = new int[register];
        nPC = PC + offset;
        offset = off;
        scn = scan;

    }
    public void advance(){

        PC = nPC;
        nPC = PC + offset;

    }
    public void advance(int j){

        PC = nPC;
        nPC = PC + j;

    }
    public void addInsts(){
        //fucks up if MIPS instruct = 0 so need to find a better way to implement this shit
        int i = 0;
        while(MEM[i] != 0 || MEM[i+1] != 0){
            i++;
        }
        mipsInstruct[] temp = new mipsInstruct[i];
        for(int z =0; z < temp.length; z++){
            temp[z] = new mipsInstruct(MEM[z], this);
        }
        instructs = temp;
    }
    public void run(boolean debug){
        if(debug) {
            System.out.println("DEBUGGING MODE STARTED");
        }
        String in = "Cont";
        mipsInstruct ins;
        while((PC/4) < (instructs.length) ){
            ins = instructs[PC/4];
            if(debug) {
                System.out.println(PC);
                System.out.println("0x" + Integer.toHexString(ins.inst));
            }
            if(ins.r){
                // INST
                switch(ins.funct) {
                    case 0x04:
                        ins.sllv();
                        break;
                    case 0x6:
                        ins.srlv();
                        break;
                    case 0x00:
                        if(ins.inst == 0) {
                            ins.noop();
                        } else {
                            ins.sll();
                        }
                        break;
                    case 0x0C:
                        ins.syscall();
                        break;
                    case 0x20:
                        ins.add();
                        break;
                    case 0x21:
                        ins.addu();
                        break;
                    case 0x24:
                        ins.and();
                        break;
                    case 0x1A:
                        ins.div();
                        break;
                    case 0x1B:
                        ins.divu();
                        break;
                    case 0x08:
                        ins.jr();
                        break;
                    case 0x10:
                        ins.mfhi();
                        break;
                    case 0x12:
                        ins.mflo();
                        break;
                    case 0x18:
                        ins.mult();
                        break;
                    case 0x26:
                        ins.xor();
                        break;
                    case 0x25:
                        ins.or();
                        break;
                    case 0x2A:
                        ins.slt();
                        break;
                    case 0x2B:
                        ins.sltu();
                        break;
                    case 0x02:
                        ins.srl();
                        break;
                    case 0x03:
                        ins.sra();
                        break;
                    case 0x22:
                        ins.sub();
                        break;
                    case 0x23:
                        ins.subu();
                        break;
                }
            } else if (ins.j){
                // JUMP
                if(ins.opc == 0x02){
                    ins.j();
                } else {
                    ins.jal();
                }
            } else {
                //IMMED
                switch(ins.opc) {
                    case 0x01:
                       switch(ins.b) {
                            case 0x11:
                                ins.bgezal();
                                break;
                            case 0x00:
                                ins.bltz();
                                break;
                            case 0x01:
                                ins.bgez();
                                break;
                            case 0x10:
                                ins.bltzal();
                                break;
                        }
                        break;
                    case 0x03:
                        ins.bgtz();
                        break;
                    case 0x06:
                        ins.blez();
                        break;
                    case 0xE:
                        ins.xori();
                        break;
                    case 0x04:
                        ins.beq();
                        break;
                    case 0x05:
                        ins.bne();
                        break;
                    case 0x08:
                        ins.addi();
                        break;
                    case 0x09:
                        ins.addiu();
                        break;
                    case 0x0A:
                        ins.slti();
                        break;
                    case 0x0B:
                        ins.sltiu();
                        break;
                    case 0x0C:
                        ins.andi();
                        break;
                    case 0x0D:
                        ins.ori();
                        break;
                    case 0x0F:
                        ins.lui();
                        break;
                    case 0x23:
                        ins.lw();
                        break;
                    case 0x24:
                        ins.lb();
                        break;
                    case 0x28:
                        ins.sb();
                        break;
                    case 0x2B:
                        ins.sw();
                        break;
                }
            }
                if(debug) {
                    while (!in.equals("Next")) {
                        in = scn.nextLine();
                        if (in.startsWith("R")) {
                            System.out.println(R[Integer.valueOf(in.substring(1))]);
                        }
                        if (in.startsWith("MEM")) {
                            System.out.println(MEM[Integer.valueOf(in.substring(3))]);
                        }
                        if (in.equals("PC")) {
                            System.out.println(PC);
                        }
                        if (in.equals("nPC")) {
                            System.out.println(nPC);
                        }
                        if (in.equals("HI")) {
                            System.out.println(HI);
                        }
                        if (in.equals("LO")) {
                            System.out.println(LO);
                        }
                    }
                    in = "cont";
                }
        }
    }
}

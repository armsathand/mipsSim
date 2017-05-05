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
                // INST
                if(ins.funct == 0x20){
                    ins.add();
                }
                if(ins.funct == 0x21){
                    ins.addu();
                }
                if(ins.funct == 0x24){
                    ins.and();
                }
                if(ins.funct == 0x1A){
                    ins.div();
                }
                if(ins.funct == 0x1B){
                    ins.divu();
                }
                if(ins.funct == 0x08){
                    ins.jr();
                }
                if(ins.funct == 0x10){
                    ins.mfhi();
                }
                if(ins.funct == 0x12){
                    ins.mflo();
                }
                if(ins.funct == 0x18){
                    ins.mult();
                }
                if(ins.funct == 0x26){
                    ins.xor();
                }
                if(ins.funct == 0x25){
                    ins.or();
                }
                if(ins.funct == 0x2A){
                    ins.slt();
                }
                if(ins.funct == 0x2B){
                    ins.sltu();
                }
                if(ins.funct == 0x00){
                    ins.sll();
                }
                if(ins.funct == 0x02){
                    ins.srl();
                }
                if(ins.funct == 0x03){
                    ins.sra();
                }
                if(ins.funct == 0x22){
                    ins.sub();
                }
                if(ins.funct == 0x23){
                    ins.subu();
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
                if(ins.opc == 0x04){
                    ins.beq();
                }
                if(ins.opc == 0x05){
                    ins.bne();
                }
                if(ins.opc == 0x08){
                    ins.addi();
                }
                if(ins.opc == 0x09){
                    ins.addiu();
                }
                if(ins.opc == 0x0A){
                    ins.slti();
                }
                if(ins.opc == 0x0B){
                    ins.sltiu();
                }
                if(ins.opc == 0x0C){
                    ins.andi();
                }
                if(ins.opc == 0x0D){
                    ins.ori();
                }
                if(ins.opc == 0x0F){
                    ins.lui();
                }
                if(ins.opc == 0x23){
                    ins.lw();
                }
                if(ins.opc == 0x24){
                    ins.lb();
                }
                if(ins.opc == 0x28){
                    ins.sb();
                }
                if(ins.opc == 0x2B){
                    ins.sw();
                }
            }
        }
    }
}

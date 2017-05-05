/**
 * Created by armsathand(Aaron Miller) on 4/17/2017.
 */
public class mipsInstruct {
    int inst;
    boolean j;
    boolean i;
    boolean r;
    int destination;
    int a;
    int b;
    int opc;
    int funct;
    int immed;
    mipsCom system;

    public mipsInstruct(int instruction, mipsCom sys){
        String tempBinary = Long.toBinaryString(instruction| 0x100000000L). substring(1);

        if (tempBinary.length() > 32){
            tempBinary = tempBinary.substring(31);
            System.out.println(tempBinary);
            System.out.println(tempBinary.substring(0,6));
        }

        system = sys;
        inst = instruction;
        opc = instruction >> 24;
        opc = Integer.parseInt(tempBinary.substring(0,6),2);
        a = Integer.parseInt(tempBinary.substring(7,11),2);
        b = Integer.parseInt(tempBinary.substring(12,16),2);
        destination = Integer.parseInt(tempBinary.substring(19,22),2);
        immed = Integer.parseInt(tempBinary.substring(15),2);
        funct = Integer.parseInt(tempBinary.substring(26),2);

        if(opc == 0){
            r = true;
            i = false;
            j = false;
        } else if(opc <= 3){
            r = false;
            i = false;
            j = true;
            destination = Integer.parseInt(tempBinary.substring(5), 2);
        } else {
            r = false;
            i = true;
            j = false;
        }
    }
    public String toString(){
        StringBuilder info = (new StringBuilder());
        info.append("Opcode : " + Integer.toBinaryString(opc | 0x40).substring(1) + "\n");
        info.append(" A : " + Integer.toBinaryString(a| 0x20).substring(1) + "\n");
        info.append(" B : " + Integer.toBinaryString(b| 0x20).substring(1) + "\n");
        info.append(" Destination : " + Integer.toBinaryString(destination) + "\n");
        info.append(" Immediate : " + Integer.toBinaryString(immed) + "\n");
        info.append(" Function : " + Integer.toBinaryString(funct| 0x40).substring(1) + " \n");
        info.append(Long.toBinaryString(inst| 0x100000000L). substring(1));
        return info.toString();
    }
    public void add(){
        // May need to divide by 4
        system.R[destination] = system.R[a] + system.R[b];
        system.advance();
    }
    public void addi(){
        system.R[destination] = system.R[a] + immed;
        system.advance();
    }
    public void addu(){
        int ua = system.R[a] & 0xFF;
        int ub = system.R[b] & 0xFF;
        system.R[destination] = ua + ub;
        system.advance();
    }
    public void addiu(){
        int ua = system.R[a] & 0xFF;
        int ui = immed & 0xFF;
        system.R[destination] = ua + ui;
        system.advance();
    }
    public void and(){
        system.R[destination] = system.R[a] & system.R[b];
        system.advance();
    }
    public void andi(){
        system.R[destination] = system.R[a] & immed;
        system.advance();
    }
    public void beq(){
        //Branch if equal
        if (system.R[a] == system.R[b]){
            system.advance(immed << 2);
        } else {
            system.advance();
        }
    }
    public void bgez(){
        if(system.R[a] >= 0){
            system.advance(immed << 2);
        } else {
            system.advance();
        }
    }
    public void bgezal(){
        if(system.R[a] >= 0){
            system.R[31] = system.nPC + 4;
            system.advance(immed << 2);
        } else {
            system.advance();
        }
    }
    public void bgtz(){
        if(system.R[a] > 0){
            system.advance(immed << 2);
        } else {
            system.advance();
        }
    }
    public void blez(){
        if (system.R[a] <=0){
            system.advance(immed << 2);
        } else {
            system.advance();
        }
    }
    public void bltz(){
        if (system.R[a] < 0 ){
            system.advance(immed << 2);
        } else {
            system.advance();
        }
    }
    public void bltzal(){
        if(system.R[a] < 0){
            system.R[31] = system.nPC + 4;
            system.advance(immed << 2);
        } else {
            system.advance();
        }
    }
    public void bne(){
        if (system.R[a] != system.R[b] ){
            system.advance(immed << 2);
        } else {
            system.advance();
        }
    }
    public void div(){
        system.LO = system.R[a]/system.R[b];
        system.HI = system.R[a]%system.R[b];
        system.advance();
    }
    public void divu(){
        system.LO = (system.R[a] & 0xFF) / (system.R[b] & 0xFF);
        system.HI = (system.R[a] & 0xFF) % (system.R[b] & 0xFF);
        system.advance();
    }
    public void j(){
        system.advance(system.PC & 0xf0000000);
    }
    public void jal(){
        system.R[31] = system.nPC;
        system.advance( system.PC & 0xf0000000);
    }
    public void jr(){
        system.advance(system.R[a]);
    }
    public void lb(){

    }
    public void lui(){
        system.R[b] = immed << 16;
        system.advance();
    }
    public void lw(){

    }
    public void mfhi(){
        system.R[destination] = system.HI;
        system.advance();
    }
    public void mflo(){
        system.R[destination] = system.LO;
        system.advance();
    }
    public void mult(){
        system.LO = system.R[a] * system.R[b];
        system.advance();
    }
    public void noop(){
        system.advance();
    }
    public void or(){
        system.R[destination] = system.R[a] | system.R[b];
        system.advance();
    }
    public void ori(){
        system.R[b] = system.R[a] | system.R[immed];
        system.advance();
    }
    public void sll(){
        system.R[destination] = system.R[b] << system.R[5];
        system.advance();
    }
    public void sllv(){
        system.R[destination] = system.R[b] << system.R[a];
        system.advance();
    }
    public void slt(){
        if(system.R[a] < system.R[b]){
            system.R[destination] = 1;
        } else {
            system.R[destination] = 0;
        }
        system.advance();
    }
    public void slti(){
        if(system.R[a] < system.R[immed]){
            system.R[b] = 1;
        } else {
            system.R[b] = 0;
        }
        system.advance();
    }
    public void sltiu(){
        if(system.R[a] < system.R[immed]){
            system.R[b] = 1;
        } else {
            system.R[b] = 0;
        }
        system.advance();
    }
    public void sltu(){
        if(system.R[b] < system.R[a]){
            system.R[destination] = 1;
        } else {
            system.R[destination] = 0;
        }
        system.advance();
    }
    public void sra(){
        ///NEEDS OTHER WORK TO BE DONE
        system.R[destination] = system.R[b] >> system.R[a];
        system.advance();
    }
    public void srl(){
        ///NEEDS OTHER WORK TO BE DONE
        system.R[destination] = system.R[b] >> system.R[a];
        system.advance();
    }
    public void srlv(){
        system.R[destination] = system.R[b] >> system.R[a];
        system.advance();
    }
    public void sub(){
        system.R[destination] = system.R[a] - system.R[b];
        system.advance();
    }
    public void subu(){
        int ia = system.R[a] & 0xFF;
        int ib = system.R[b] & 0xFF;
        system.R[destination] = ia - ib;
        system.advance();
    }
    public void sw(){

        system.advance();
    }
    public void sb(){

        system.advance();
    }
    public void syscall(){
        switch(system.R[2]){
            case 1:
                System.out.print(system.R[4]);
                break;
            case 2:
                System.out.print((float) system.R[12]);
                break;
            case 3:
                System.out.print((double) system.R[12]);
                break;
            case 4:
                System.out.print(system.MEM[system.R[4]]);
                break;
            case 5:
                break;
        }
        system.advance();
    }
    public void xor(){
        system.R[destination] = system.R[a] ^ system.R[b];
        system.advance();
    }
    public void xori(){
        system.R[b] = system.R[a] ^ immed;
        system.advance();
   }
}

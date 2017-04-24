/**
 * Created by armsathand on 4/17/2017.
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
        system = sys;
        inst = instruction;
        opc = instruction >> 24;
        if(opc == 0){
            r = true;
            i = false;
            j = false;
            a = (inst << 5) >> 16 ;
            funct = inst << 24;
            immed = 0;
        } else if(opc <= 3){
            r = false;
            i = false;
            j = true;
            funct = 0;
            immed = 0;
            destination = inst << 5;
        } else {
            r = false;
            i = true;
            j = false;
            immed = inst << 14;
            funct = 0;
        }
    }
    public String toString(){
        return Integer.toBinaryString(inst);
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

    }
    public void BGEZ(){

    }
    public void BGEZAL(){

    }
    public void BGTZ(){

    }
    public void BLEZ(){

    }
    public void BLTZ(){

    }
    public void BLTZAL(){

    }
    public void BNE(){

    }
    public void div(){
        system.R[destination] = system.R[a]/system.R[b];
        system.advance();
    }
    public void J(){
        system.advance(destination);
    }
    public void JAL(){

    }
    public void JR(){

    }
    public void LB(){

    }
    public void LUI(){

    }
    public void LW(){

    }
    public void MFHI(){

    }
    public void MFLO(){

    }
    public void MULT(){

    }
    public void OR(){

    }
    public void ORI(){

    }
    public void SLL(){

    }
    public void SLLV(){

    }
    public void SLT(){

    }
    public void SLTI(){

    }
    public void SLTIU(){

    }
    public void SLTU(){

    }
    public void SRA(){

    }
    public void SRL(){

    }
    public void SRLV(){

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
    public void SW(){

    }
    public void SB(){

    }
    public void SYSCALL(){

    }
    public void XOR(){

    }
    public void XORI(){

   }
}

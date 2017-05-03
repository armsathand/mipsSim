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
        system = sys;
        inst = instruction;
        opc = instruction >> 24;
        if(opc == 0){
            r = true;
            i = false;
            j = false;
            a = (inst << 5) >> 16 ;
            b = (inst << 10) >> 11;
            funct = inst << 24;
            immed = 0;
        } else if(opc <= 3){
            r = false;
            i = false;
            j = true;
            funct = 0;
            immed = 0;
            a = (inst << 5) >> 16 ;
            b = (inst << 10) >> 11;
            destination = inst << 5;
        } else {
            r = false;
            i = true;
            j = false;
            a = (inst << 5) >> 16 ;
            b = (inst << 10) >> 11;
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
    public void JAL(){
        system.R[31] = system.nPC;
        system.advance( system.PC & 0xf0000000);
    }
    public void JR(){
        system.advance(system.R[a]);
    }
    public void LB(){

    }
    public void LUI(){
        system.R[b] = immed << 16;
        system.advance();
    }
    public void LW(){

    }
    public void MFHI(){
        system.R[destination] = system.HI;
        system.advance();
    }
    public void MFLO(){
        system.R[destination] = system.LO;
        system.advance();
    }
    public void MULT(){
        system.LO = system.R[a] * system.R[b];
        system.advance();
    }
    public void NOOP(){
        system.advance();
    }
    public void OR(){
        system.R[destination] = system.R[a] | system.R[b];
        system.advance();
    }
    public void ORI(){
        system.R[b] = system.R[a] | system.R[immed];
        system.advance();
    }
    public void SLL(){
        system.R[destination] = system.R[b] << system.R[5];
        system.advance();
    }
    public void SLLV(){
        system.R[destination] = system.R[b] << system.R[a];
        system.advance();
    }
    public void SLT(){
        if(system.R[a] < system.R[b]){
            system.R[destination] = 1;
        } else {
            system.R[destination] = 0;
        }
        system.advance();
    }
    public void SLTI(){
        if(system.R[a] < system.R[immed]){
            system.R[b] = 1;
        } else {
            system.R[b] = 0;
        }
        system.advance();
    }
    public void SLTIU(){
        if(system.R[a] < system.R[immed]){
            system.R[b] = 1;
        } else {
            system.R[b] = 0;
        }
        system.advance();
    }
    public void SLTU(){
        if(system.R[b] < system.R[a]){
            system.R[destination] = 1;
        } else {
            system.R[destination] = 0;
        }
        system.advance();
    }
    public void SRA(){
        ///NEEDS OTHER WORK TO BE DONE
        system.R[destination] = system.R[b] >> system.R[a];
        system.advance();
    }
    public void SRL(){
        ///NEEDS OTHER WORK TO BE DONE
        system.R[destination] = system.R[b] >> system.R[a];
        system.advance();
    }
    public void SRLV(){
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
    public void SW(){

    }
    public void SB(){

    }
    public void SYSCALL(){


    }
    public void XOR(){
        system.R[destination] = system.R[a] ^ system.R[b];
        system.advance();
    }
    public void XORI(){
        system.R[b] = system.R[a] ^ immed;
        system.advance();
   }
}

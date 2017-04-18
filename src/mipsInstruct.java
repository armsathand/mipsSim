/**
 * Created by armsathand on 4/17/2017.
 */
public class mipsInstruct {
    int inst;
    boolean j;
    boolean i;
    boolean r;
    int opc;
    int funct;
    int immed;
    public mipsInstruct(int instruction){
        inst = instruction;
        opc = instruction >> 24;
        if(opc == 0){
            r = true;
            i = false;
            j = false;
            funct = inst << 24;
            immed = 0;
        } else if(opc <= 3){
            r = false;
            i = false;
            j = true;
            funct = 0;
            immed = 0;
        } else {
            r = false;
            i = true;
            j = false;
            immed = inst << 14;
            funct = 0;
        }
    }


}

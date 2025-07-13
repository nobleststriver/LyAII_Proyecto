package ArbolSintactico;

public class Whilex extends Statx{
    private Expx s1;
    private Statx s3;
    
    public Whilex(Expx st1, Statx st3) {
        s1 = st1;
        s3 = st3;  
    }
    
    public Object[] getVariables() {
        Object obj[] = new Object[2];
        obj[0] = s1;
        obj[2] = s3;
        return obj;
    }
    
}
package calculator;

public class arimath {
    private double op1;
    private double op2;
    CalDesign cal= new CalDesign();
    public String ariAdd(String tx1,String tx2){
        op1= Double.parseDouble(tx1);
        op2= Double.parseDouble(tx2);
        Double result=op1+op2;
        return Double.toString(result);
    }
    public String ariSub(String tx1,String tx2){
        op1= Double.parseDouble(tx1);
        op2=Double.parseDouble(tx2);
        Double result= op1-op2;
        return Double.toString(result);
    }
    public String arimul(String tx1, String tx2){
        op1=Double.parseDouble(tx1);
        op2=Double.parseDouble(tx2);
        Double result= op1*op2;
        return Double.toString(result);
    }
    public String aridiv(String tx1, String tx2){
        op1=Double.parseDouble(tx1);
        op2=Double.parseDouble(tx2);
        Double result=0.0;
        result=op1/op2;
        return Double.toString(result);
    }
    public String arisqrt(String tx1){
        op1=Double.parseDouble(tx1);
        Double result=0.0;
        result=Math.sqrt(op1);
        return Double.toString(result);
    }
    public String arisquare(String tx1){
        op1= Double.parseDouble(tx1);
        Double result=Math.pow(op1,2);
        return Double.toString(result);
    }
    public String ariPercentage(String tx1){
        op1=Double.parseDouble(tx1);
        Double result=op1/100;
        return Double.toString(result);
    }
}

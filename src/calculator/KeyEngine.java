package calculator;


import java.util.ArrayList;
import java.util.Stack;

public class KeyEngine {
    private ArrayList<String> infix = new ArrayList<String>();
    private Stack<String> postfix = new Stack<String>();
    //Converts Dipslay Port text to operator and operand
    public String KeyParser(String PortStat){
        String temp="";
        int asc;
        char txt;
        for(int i=0;i<PortStat.length();i++){
            asc=(int)PortStat.charAt(i);
            txt=PortStat.charAt(i);
            int enp=PortStat.length();
            if (asc>=48 && asc<=57){
                while(asc>=48 && asc<=57 || PortStat.charAt(i)=='.'){
                    temp+=PortStat.charAt(i);
                    if (i==(enp-1)){
                        i++;
                        break;
                    }
                    asc=(int)PortStat.charAt(++i);
                }
                infix.add(temp);
                temp="";
                --i;
            }
            else if (txt=='+' || txt=='-'|| txt=='*' || txt=='/') {
                if (txt=='-' && PortStat.charAt(i+1)=='('){
                    infix.add("0");
                    infix.add("-");
                    continue;
                }
                infix.add(Character.toString(PortStat.charAt(i)));
            }
            else if (txt=='('|| txt==')'){
                infix.add(Character.toString(PortStat.charAt(i)));
            }
            else if(txt=='^'){
                infix.add("^2");
                i++;
            }
            else if (Character.isAlphabetic(PortStat.charAt(i))){
                while (Character.isAlphabetic(PortStat.charAt(i))){
                    temp+=PortStat.charAt(i++);
                }
                infix.add(temp);
                i--;
                temp="";
            }
        }
        parseToPostfix();
        return intcheck(Eval());
    }
    //testing function for KeyParser
    public void checkarg(){
        for (int i=0;i<infix.size();i++){
            javax.swing.JOptionPane.showMessageDialog(null,infix.get(i));
        }
    }
    //Converts infix arraylist to postfix Stack
    public void parseToPostfix(){
        Stack<String> holdStack= new Stack<String>();
        int weight;
        int i=0;
        int k=0;
        String Stroke="";
        while (i<infix.size()){
            Stroke=infix.get(i);
            if(Stroke.equals("(")){
                holdStack.push(Stroke);
                i++;
                continue;
            }
            if(Stroke.equals(")")){
                while(!holdStack.isEmpty() && !(holdStack.peek()).equals("(")){
                    postfix.push(holdStack.pop());
                }
                if(!holdStack.isEmpty()){
                    holdStack.pop();
                }
                i++;
                continue;
            }
            weight=getWeight(Stroke);
            if (weight==0){
                postfix.push(Stroke);
            }
            else{
                if(holdStack.isEmpty()){
                    holdStack.push(Stroke);
                }
                else{
                    while (!holdStack.isEmpty()&& !(holdStack.peek()).equals("(") && weight<=getWeight(holdStack.peek())){
                        postfix.push(holdStack.pop());
                    }
                    holdStack.push(Stroke);
                }
            }
            i++;


        }
        while(!holdStack.isEmpty()){
            postfix.push(holdStack.pop());
        }

    }
    //returns the weight and priority of operator
    public int getWeight(String op){
        switch (op){
            case "+":
            case "-": return 1;
            case "/":
            case "*": return 2;
            case "^2":
            case "sqrt":return 3;
            default : return 0;
        }

    }
    //to check state of the stack
    public void checkstat(){
        for (int i =0;i<postfix.size();i++){
            javax.swing.JOptionPane.showMessageDialog(null,postfix.elementAt(i));
        }
    }

    //to evaluate the arithmatic expression after calculation of precidance
    public String Eval(){
        arimath find=new arimath();
        String invoke;
        String op1="";
        String op2="";
        Stack<String> result=new Stack<String>();
        for(int i=0;i<postfix.size();i++){
            invoke=postfix.get(i);
            switch (invoke){
                case "-":
                    op2=result.pop();
                    op1=result.pop();
                    result.push(find.ariSub(op1,op2));
                    break;
                case"+":
                    op2=result.pop();
                    op1=result.pop();
                    result.push(find.ariAdd(op1,op2));
                    break;
                case"/":
                    op2=result.pop();
                    op1=result.pop();
                    result.push(find.aridiv(op1,op2));
                    break;
                case"*":
                    op2=result.pop();
                    op1=result.pop();
                    result.push(find.arimul(op1,op2));
                    break;
                case "sqrt":
                    op1=result.pop();
                    result.push(find.arisqrt(op1));
                    break;
                case "^2":
                    op1=result.pop();
                    result.push(find.arisquare(op1));
                    break;
                default:
                    result.push(invoke);
                    break;
            }
        }
        return result.pop();
    }
    public void fush(){
        infix.clear();
        postfix.clear();
    }

    /**
     *
     * @param st
     * using double as a calcution variable it always generates string that consist of .0 which is not necessary
     * hence this funciton removes if the data calculated does not generate floating value
     * @return final value
     */
    public String intcheck(String st){
        Double test1=Double.parseDouble(st);
        Double test2=test1.intValue()/1.0;
        String t1= Double.toString(test1);
        String t2= Double.toString(test2);
        int check=test1.intValue();
        if(t1.equals(t2)){
            return Integer.toString(check);
        }
        else{
            return st;
        }
    }
}

package inputOutputExamples;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OracleJava8 {
    public static void main(String[] args) throws Exception {
        int x=0;
        BufferedReader br=new BufferedReader (new InputStreamReader(System.in));
        String voodoo = br.readLine();
        String[] tok=voodoo.split(" ");
        for (int i=0; i<tok.length;i++) {
            x += Integer.parseInt(tok[i]);
        }
        System.out.println(x);
    }
}

package inputOutputExamples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OracleJava7 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine().trim();
        String[] strings = input.split(" ");

        System.out.println(Long.parseLong(strings[0]) + Long.parseLong(strings[1]));
    }
}

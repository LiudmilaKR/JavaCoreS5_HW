package lesson5.ru.gb.task3;

import java.util.Random;

public class Program {
    public static void main(String[] args) {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.print(rand.nextInt(3) + " ");
        }


    }



}

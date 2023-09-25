package lesson5.ru.gb.task1;

import java.io.File;

public class Program {
    public static void main(String[] args) {
        print(new File("."), "", true);
        // . - текущая директория, indent - отступ
    }

    /**
     *
     * @param file текущий файл
     * @param indent отступ
     * @param isLast директория окончательная (root) или нет
     */
    public static void print (File file, String indent, boolean isLast) {
        System.out.print(indent); // отрисовка отступа
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles(); //возвращает и файлы и директории
        if (files == null) return;

        int subDirTotal = 0; // кол-во директорий
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) subDirTotal++;
        }

        int subDirCounter = 0; // подсчет поддиректорий
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                subDirCounter++;
                print(files[i], indent, subDirCounter == subDirTotal);
            }
        }
    }
}

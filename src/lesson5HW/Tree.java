package lesson5HW;

import java.io.File;

public class Tree {
    public static void main(String[] args) {
        print(new File("."), "", true);
//        print(new File("C:/Users/lidia/OneDrive/Desktop/Разное"), "", true);
    }

    /**
     * Отрисовка дерева
     * @param file текущая директория
     * @param indent отступ
     * @param isLast директория окончательная (root) или нет
     */
    public static void print (File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null) return;

        int subDirTotal = 0; // кол-во директорий
        int subFileTotal = 0; // кол-во файлов
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) subDirTotal++;
            else if (files[i].isFile()) subFileTotal++;
        }

        int subDirCounter = 0; // подсчет поддиректорий
        int subFileCounter = 0; // подсчет файлов
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                subDirCounter++;
                print(files[i], indent, subDirCounter == subDirTotal);
            } else if (files[i].isFile()) {
                subFileCounter++;
                print(files[i], indent, subFileCounter == subFileTotal);
            }
        }
    }
}

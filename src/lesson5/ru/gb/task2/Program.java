package lesson5.ru.gb.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Program {
    /**
     1.  Создать 2 текстовых файла, примерно по 50-100 символов в каждом (особого значения не имеет);
     2.  Написать метод, «склеивающий» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго.
     3.* Написать метод, который проверяет, присутствует ли указанное пользователем слово в файле (работаем только с латиницей).
     4.* Написать метод, проверяющий, есть ли указанное слово в папке
     * В юникоде все символы имеют свои коды
     */
    private static final Random rand = new Random();
    private static final int CHAR_BOUND_L = 65; // нижняя граница
    private static final int CHAR_BOUND_R = 90; // верхняя граница
    private static final String TO_SEARCH = "GeekBrains"; // фраза для поиска

    public static void main(String[] args) throws IOException {
//        System.out.println(generateSymbol(30));
        writeFileContents("sample01.txt", 20);
        writeFileContents("sample02.txt", 20, 1);
        concatenate("sample01.txt", "sample02.txt", "sample_res01.txt");
        if (searchInFile("sample_res01.txt", TO_SEARCH))
            System.out.printf("Файл %s содержит искомое слово %s\n", "sample_res01.txt", TO_SEARCH);

        String[] fileNames = new String[10];
        for (int i = 0; i < fileNames.length; i++) {
            fileNames[i] = "file_" + i + ".txt";
            writeFileContents(fileNames[i], 30, 3);
            System.out.printf("Файл %s создан.\n", fileNames[i]);
        }

        List<String> result = searchMatch(new File("."), TO_SEARCH);
        for (String s: result) {
            System.out.printf("Файл %s содержит искомое слово %s\n", s, TO_SEARCH);
        }
    }
    private static List<String> searchMatch(File dir, String search) throws IOException {
        dir = new File(dir.getCanonicalPath());
        List<String> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files == null) return list;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) continue;
            if (searchInFile(files[i].getName(), search)) list.add((files[i].getName()));
        }
        return list;
    }
    /**
     * Метод генерации некоторой последовательности символов
     * @param count количество символов
     * @return последовательность (строку) символов
     */
    private static String generateSymbol(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append((char) rand.nextInt(CHAR_BOUND_L, CHAR_BOUND_R + 1));
        }
        return sb.toString();
    }

    /**
     * Запись контекста (последовательности символов) в файл
     * можно было использовать прямой метод записи в файл (fileWriter), но решили через FileOutputStream
     * (он работает с массивами байтов)
     * @param fileName имя файлу (наверное, всё-таки, путь к файлу)
     * @param length длина последовательности символов (кол-во символов)
     * @throws IOException ошибка ввода-вывода
     */
    private static void writeFileContents(String fileName, int length) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(generateSymbol(length).getBytes());
            // getBytes() - получить массив байтов
        }
    }

    /**
     * это перегрузка предыдущего метода
     * @param fileName
     * @param length
     * @param i вероятность, что слово необходимо вклеить в наш файл
     * @throws IOException
     */
    private static void writeFileContents(String fileName, int length, int i) throws IOException {
            try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
                if (rand.nextInt(i) == 0) fileOutputStream.write(TO_SEARCH.getBytes());
                fileOutputStream.write(generateSymbol(length).getBytes());
                // getBytes() - получить массив байтов
            }
        }

    /**
     * Соединение 2-х файлов
     * @param fileIn1 файл 1 для соединения
     * @param fileIn2 файл 2 для соединения
     * @param fileOut объединенный файл
     * @throws IOException ошибка ввода-вывода
     */
    private static void concatenate(String fileIn1, String fileIn2, String fileOut) throws IOException {
        // открываем поток на запись
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileOut)) {
            int c;
            // открываем поток на чтение
            try (FileInputStream fileInputStream = new FileInputStream(fileIn1)) {
                // побайтовое чтение
                while ((c = fileInputStream.read()) != -1) fileOutputStream.write(c);
            }
            try (FileInputStream fileInputStream = new FileInputStream(fileIn2)) {
                while ((c = fileInputStream.read()) != -1) fileOutputStream.write(c);
            }
        }
    }

    /**
     * Ищем данные в файле, определить, содержится ли в файле искомое слово
     * @param filename имя файла
     * @param search искомая строка, слова
     * @return результат поиска
     * @throws IOException ошибка ввода-вывода
     */
    private static boolean searchInFile(String filename, String search) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            // считываем искомое слово в массив байт
            byte[] searchData = search.getBytes();
            int c, i = 0;
            while ((c = fileInputStream.read()) != -1) {
                if (c == searchData[i]) i++;
                else {
                    i = 0;
                    if (c == searchData[i]) i++;
                    continue;
                }
                if (i == searchData.length) return true;
            }
            return false;
        }
    }
}


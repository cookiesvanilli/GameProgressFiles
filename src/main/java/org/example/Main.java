package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress gamer1 = new GameProgress(50, 75, 3, 100.8);
        GameProgress gamer2 = new GameProgress(30, 43, 4, 95.2);
        GameProgress gamer3 = new GameProgress(78, 100, 10, 350.4);

        saveGamer("C:\\Users\\user\\IdeaProjects\\Games\\savegames\\gamer1.dat", gamer1);
        saveGamer("C:\\Users\\user\\IdeaProjects\\Games\\savegames\\gamer2.dat", gamer2);
        saveGamer("C:\\Users\\user\\IdeaProjects\\Games\\savegames\\gamer3.dat", gamer3);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("C:\\Users\\user\\IdeaProjects\\Games\\savegames\\gamer1.dat");
        arrayList.add("C:\\Users\\user\\IdeaProjects\\Games\\savegames\\gamer2.dat");
        arrayList.add("C:\\Users\\user\\IdeaProjects\\Games\\savegames\\gamer3.dat");
        zipFiles("C:\\Users\\user\\IdeaProjects\\Games\\savegames\\zipGame.zip", arrayList);

        //Удаление файлов, не лежащих в архиве
        fileDelete(arrayList.get(0));
        fileDelete(arrayList.get(1));
        fileDelete(arrayList.get(2));

    }

    private static void saveGamer(String path, GameProgress gamer) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(gamer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void zipFiles(String path, List<String> arrayList) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(path))) {
            for (String arr : arrayList) {
                try (FileInputStream fileInputStream = new FileInputStream(arr)) {
                    ZipEntry zipEntry = new ZipEntry(arr);
                    zipOutputStream.putNextEntry(zipEntry);
                    while (fileInputStream.available() > 0) {
                        zipOutputStream.write(fileInputStream.read());
                    }
                    zipOutputStream.closeEntry();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void fileDelete(String fail) {
        Path path = Paths.get(fail);
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
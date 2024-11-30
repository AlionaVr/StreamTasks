package src.filesAndSerialization.seralization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Saving {
    int counter = 1;


    public String saveGame(String path, GameProgress progress) {

        String name = "\\saving" + counter + ".dat";
        String filePath = path + name;
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progress);
            System.out.println("Игра сохранена: " + filePath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        counter++;
        return filePath;
    }

    public void zipFiles(String zipPath, String path) {
        File dir = new File(path);

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath))) {
            File[] files = dir.listFiles(file -> file.isFile() && !file.getAbsolutePath().equals(zipPath));
            if (files != null) {
                for (File item : files) {
                    try (FileInputStream fis = new FileInputStream(item)) {
                        ZipEntry entry = new ZipEntry(item.getName());
                        zos.putNextEntry(entry);

                        byte[] buffer = new byte[fis.available()]; // Фиксированный размер буфера
                        fis.read(buffer);
                        zos.write(buffer);

                        zos.closeEntry();
                        System.out.println("Добавлен файл в архив: " + item.getName());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteFile(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles(file -> file.isFile() && !file.getName().equals("zip.zip"));
        for (File file : files) {
            file.delete();
            System.out.println("Файл Удален " + file.getName());
        }
    }
}

package src.filesAndSerialization.seralization;

import src.filesAndSerialization.creationFiles.games.CreationFiles;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Saving {
    int counter = 1;


    public void saveGame(String path, GameProgress progress) {
        ensureDirectoryExists(path);
        String filePath = path + File.separator + "saving" + counter + ".dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(progress);
            System.out.println("Игра сохранена: " + filePath);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении игры:" + e.getMessage());
        }
        counter++;
    }

    public void zipFiles(String zipPath, String path) {
        ensureDirectoryExists(path);
        File dir = new File(path);

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath))) {
            File[] files = dir.listFiles(file -> file.isFile() && !file.getAbsolutePath().equals(zipPath));
            if (files != null) {
                for (File item : files) {
                    addFileToZip(item, zos);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании архива" + e.getMessage());
        }
    }

    // Проверка существования директории
    protected void ensureDirectoryExists(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
            System.out.println(("Создана директория: " + path));
        }
    }

    private void addFileToZip(File item, ZipOutputStream zos) {
        try (FileInputStream fis = new FileInputStream(item)) {
            ZipEntry entry = new ZipEntry(item.getName());
            zos.putNextEntry(entry);

            byte[] buffer = new byte[fis.available()]; // Фиксированный размер буфера
            fis.read(buffer);
            zos.write(buffer);

            zos.closeEntry();
            System.out.println("Добавлен файл в архив: " + item.getName());
        } catch (IOException e) {
            System.out.println("Ошибка при добавлении файла в архив: " + item.getName() + e.getMessage());
        }
    }

    public void deleteFile(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles(file -> file.isFile() && !file.getName().equals("zip.zip"));
        if (files != null) {
            for (File file : files) {
                if (file.delete()) {
                    System.out.println("Файл Удален " + file.getName());
                } else System.out.println("Ошибка при удалении файла: " + file.getName());
            }
        }
    }
}
package src.filesAndSerialization.seralization;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Reading {

    String name;
    String path;
    private final Saving saving = new Saving();

    public void openZip(String zipPath, String dirPath) {
        saving.ensureDirectoryExists(dirPath);

        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                path = dirPath + File.separator + name;
                extractFile(zin, path);
                zin.closeEntry();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    // Вспомогательный метод для извлечения файла из архива
    private void extractFile(ZipInputStream zin, String filePath) {
        try (FileOutputStream fout = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024]; // Буфер для копирования данных
            int length;
            while ((length = zin.read(buffer)) > 0) {
                fout.write(buffer, 0, length);
            }
            System.out.println("Файл распакован: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при распаковке файла: " + filePath + e.getMessage());
        }
    }

    public GameProgress openProgress(String path) {
        saving.ensureDirectoryExists(path);
        String filePath = path + "\\" + name;
        GameProgress gameProgress = null;
        // откроем входной поток для чтения файла
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            // десериализуем объект и скастим его в класс
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке прогресса из файла" + e.getMessage());
        }
        return gameProgress;
    }
}

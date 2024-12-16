package src.filesAndSerialization.seralization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Reading {
    ZipEntry entry;
    String name;
    String path;

    public void openZip(String zipPath, String dirPath) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipPath))) {

            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                path = dirPath + "\\" + name;
                //распаковка
                FileOutputStream fout = new FileOutputStream(path);
                System.out.println("распакован файл " + name);

                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public GameProgress openProgress(String path) {
        String filePath = path + "\\" + name;
        GameProgress gameProgress = null;
        // откроем входной поток для чтения файла
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            // десериализуем объект и скастим его в класс
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }
}

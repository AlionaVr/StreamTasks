package src.filesAndSerialization.creationFiles.games;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreationFiles {
    static StringBuilder textDate = new StringBuilder();

    public static void main(String[] args) {
        CreationFiles creator = new CreationFiles();
        String basePath = "F:\\Projects\\JavaCoreTasks\\src\\filesAndSerialization\\creationFiles\\games";
        creator.makeDir(basePath, "src1");
        creator.makeDir(basePath, "res");
        creator.makeDir(basePath, "savegames");
        creator.makeDir(basePath, "temp");

        String srcPath = basePath + "\\src1";
        creator.makeDir(srcPath, "main");
        creator.makeDir(srcPath, "test");

        String mainPath = srcPath + "\\main";
        creator.createFile(mainPath, "Main.java");
        creator.createFile(mainPath, "Utils.java");

        String resPath = basePath + "\\res";
        creator.makeDir(resPath, "drawables");
        creator.makeDir(resPath, "vectors");
        creator.makeDir(resPath, "icons");

        String tempPath = basePath + "\\temp";
        creator.createFile(tempPath, "temp.txt");

        String filePath = tempPath + "\\temp.txt";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(textDate.toString());
            writer.flush();
            System.out.println("Лог записан в файл temp.txt");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }

    }

    protected void makeDir(String path, String name) {
        File dir = new File(path, name);
        if (dir.mkdir()) {
            textDate.append("Создан катaлог ").append(name).append("\n");
        }
    }

    protected void createFile(String path, String name) {
        File newFile = new File(path, name);
        try {
            if (newFile.createNewFile())
                textDate.append("создан файл ").append(name).append("\n");
        } catch (IOException e) {
            textDate.append(e.getMessage());
        }

    }

}

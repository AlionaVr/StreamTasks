package src.filesAndSerialization.seralization;

public class Main {
    public static void main(String[] args) {
        GameProgress progress1 = new GameProgress(83, 5, 3, 5.8);
        GameProgress progress2 = new GameProgress(55, 3, 5, 8.3);
        GameProgress progress3 = new GameProgress(73, 5, 7, 12.1);

        String path = "F:\\Projects\\JavaCoreTasks\\src\\filesAndSerialization\\creationFiles\\games\\savegames";
        Saving save = new Saving();
        save.saveGame(path, progress1);
        save.saveGame(path, progress2);
        save.saveGame(path, progress3);

        String zipPath = path + "\\zip.zip";
        save.zipFiles(zipPath, path);
        save.deleteFile(path);

        Reading reader = new Reading();
        reader.openZip(zipPath, path);
        System.out.println("\nЗагруженный прогресс: " + reader.openProgress(path).toString());

    }
}

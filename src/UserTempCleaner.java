import java.io.File;

public class UserTempCleaner {

    public static void clean() {

        String folderPath = System.getProperty("user.home") + "\\AppData\\Local\\Temp";

        File tempFolder = new File(folderPath);
        File[] fileList = tempFolder.listFiles();

        if (fileList == null) {
            System.out.println("[ERROR] Unable to access folder.");
            return;
        }

        int removedFiles = 0;
        int skippedFiles = 0;
        long totalFreedSpace = 0;

        for (File currentFile : fileList) {

            String fileName = currentFile.getName().toLowerCase();

            boolean isTemporary =
                    fileName.endsWith(".tmp") ||
                    fileName.endsWith(".temp") ||
                    fileName.endsWith(".cache");

            if (currentFile.isFile() && isTemporary) {

                long fileSize = currentFile.length();

                boolean deleted = currentFile.delete();

                if (deleted) {
                    removedFiles++;
                    totalFreedSpace += fileSize;
                } else {
                    skippedFiles++;
                }
            }
        }

        System.out.println("[RESULT] Files Removed: " + removedFiles);
        System.out.println("[RESULT] Files Skipped: " + skippedFiles);
        System.out.println("[RESULT] Space Freed: " + (totalFreedSpace / 1024) + " KB");
    }
}
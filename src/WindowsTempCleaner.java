import java.io.File;

public class WindowsTempCleaner {

    public static void clean() {

        System.out.println("[INFO] Starting Windows Temp Cleanup...");

        String tempPath = "C:\\Windows\\Temp";

        File tempFolder = new File(tempPath);
        File[] files = tempFolder.listFiles();

        if (files == null) {
            System.out.println("[ERROR] Cannot access Windows Temp folder.");
            return;
        }

        int deletedCount = 0;
        int skippedCount = 0;
        long freedSpace = 0;

        for (File file : files) {

            // Only process normal files (skip folders for safety)
            if (file.isFile()) {

                try {
                    long fileSize = file.length();

                    // Try deleting file
                    if (file.delete()) {
                        deletedCount++;
                        freedSpace += fileSize;
                    } else {
                        skippedCount++; // file in use / permission issue
                    }

                } catch (Exception e) {
                    skippedCount++;
                }
            }
        }

        System.out.println("[RESULT] Files Deleted: " + deletedCount);
        System.out.println("[RESULT] Files Skipped: " + skippedCount);
        System.out.println("[RESULT] Space Freed: " + (freedSpace / 1024) + " KB");
    }
}
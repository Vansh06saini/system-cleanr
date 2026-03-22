import java.io.File;
public class junkcleaner 
{
    static int deletedFiles = 0;
    static int skippedFiles = 0;
    static long freedSpace = 0;

    public static void clean() 
    {
        System.out.println("Junk File Cleanup Started");
        String startPath = System.getProperty("user.home");
        File startFolder = new File(startPath);
        scanAndClean(startFolder);
        System.out.println("Files Deleted: " + deletedFiles);
        System.out.println("Files Skipped: " + skippedFiles);
        System.out.println("Space Freed: " + (freedSpace / 1024) + " KB");
    }
    public static void scanAndClean(File folder) 
    {
        File[] files = folder.listFiles();
        if (files == null) return;
        for (File file : files) 
            {
                String path = file.getAbsolutePath().toLowerCase();
                if (path.contains("windows") || path.contains("program files")) {
                continue;
            }
            if (file.isDirectory()) 
            {
                scanAndClean(file);
            }
            else {
                String name = file.getName().toLowerCase();
                    boolean isJunk =
                        name.endsWith(".tmp") ||
                        name.endsWith(".log") ||
                        name.endsWith(".cache") ||
                        name.endsWith(".bak");
                    if (isJunk) {
                    try {
                        long size = file.length();

                        if (file.delete()) {
                            deletedFiles++;
                            freedSpace += size;
                        } else {
                            skippedFiles++;
                        }

                    } catch (Exception e) {
                        skippedFiles++;
                    }
                }
            }
        }
    }
}
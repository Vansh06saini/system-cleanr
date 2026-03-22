import java.io.File;
public class recyclebincleaner {
    public static void clean() {
        System.out.println("[INFO] Cleaning Recycle Bin...");
        String path = "C:\\$Recycle.Bin";
        File bin = new File(path);
        File[] files = bin.listFiles();

        if (files == null) {
            System.out.println("ERROR:Cannot access Recycle Bin.");
            return;
        }
        int deleted = 0;
        for (File f : files) {

            try {
                if (f.delete()) {
                    deleted++;
                }
            } catch (Exception e) {
            }
        }
        System.out.println("[RESULT] Recycle Bin Files Deleted: " + deleted);
    }
}
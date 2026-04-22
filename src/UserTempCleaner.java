import java.io.File;

public class UserTempCleaner {

    public static void clean() {

        String path = System.getenv("TEMP");
        File folder = new File(path);
        File[] files = folder.listFiles();
        if (files == null) return;

        int count = 0;

        for (File f : files) {
            if (f.isFile()) {
                String n = f.getName().toLowerCase();
                if (n.endsWith(".tmp") || n.endsWith(".log") ||
                    n.endsWith(".cache") || n.endsWith(".bak")||
                    n.endsWith(".xml")||n.endsWith(".png")) {
                    count++;
                }
            }
        }

        File[] result = new File[count];
        int i = 0;

        for (File f : files) {
            if (f.isFile()) {
                String n = f.getName().toLowerCase();
                if (n.endsWith(".tmp") || n.endsWith(".log") ||
                    n.endsWith(".cache") || n.endsWith(".bak")||
                    n.endsWith(".xml")||n.endsWith(".png")) {
                    result[i++] = f;
                }
            }
        }

        if (count > 0) new FilePreviewFrameArray(result);
    }
}
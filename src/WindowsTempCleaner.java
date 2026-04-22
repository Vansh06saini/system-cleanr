import java.io.File;

public class WindowsTempCleaner {

    public static void clean() {

        String path = System.getenv("SystemRoot") + "\\Temp";

        File folder = new File(path);
        File[] files = folder.listFiles();

        if (files == null) {
            return;
        }

        int count = 0;

        for (File f : files) {

            if (f.isFile()) {

                String name = f.getName().toLowerCase();

                if (isJunk(name)) {
                    count++;
                }
            }
        }

        File[] junkFiles = new File[count];

        int index = 0;

        for (File f : files) {

            if (f.isFile()) {

                String name = f.getName().toLowerCase();

                if (isJunk(name)) {
                    junkFiles[index++] = f;
                }
            }
        }
        if (count > 0) {
            new FilePreviewFrameArray(junkFiles);
        }
    }

    static boolean isJunk(String name) {

        return name.endsWith(".tmp") ||
               name.endsWith(".log") ||
               name.endsWith(".cache") ||

               
               (name.endsWith(".txt") &&
               (
                   name.contains("temp") ||
                   name.contains("log") ||
                   name.contains("laptop") ||   // system-generated files
                   name.matches(".*\\d{8}.*")   // contains date pattern
               ));
    }
}
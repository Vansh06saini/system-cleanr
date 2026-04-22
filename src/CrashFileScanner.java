import java.io.File;

public class CrashFileScanner {

    public static void clean() {

        String path = System.getProperty("user.home");
        File folder = new File(path);

        File[] temp = new File[10000];
        int index = scanFiles(folder, temp, 0);

        if (index == 0) return;

        File[] result = new File[index];

        for (int i = 0; i < index; i++) {
            result[i] = temp[i];
        }

        new FilePreviewFrameArray(result);
    }

    static int scanFiles(File folder, File[] temp, int index) {

        File[] files = folder.listFiles();
        if (files == null) return index;

        for (File f : files) {

            String path = f.getAbsolutePath().toLowerCase();

            // skip system folders
            if (path.contains("windows") ||
                path.contains("program files") ||
                path.contains("programdata")) continue;

            if (f.isDirectory()) {
                index = scanFiles(f, temp, index);
            } else {

                String name = f.getName().toLowerCase();

                if (isCrashFile(name)) {
                    if (index < temp.length) {
                        temp[index++] = f;
                    }
                }
            }
        }

        return index;
    }

    static boolean isCrashFile(String name) {

        return name.endsWith(".dmp") ||
               name.endsWith(".wer") ||
               name.endsWith(".log");
    }
}
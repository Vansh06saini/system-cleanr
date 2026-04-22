import java.io.File;

public class junkcleaner {

    static File[] temp = new File[20000]; // buffer
    static int idx = 0;

    public static void clean() {

        idx = 0;
        scan(new File(System.getProperty("user.home")));

        if (idx == 0) return;

        File[] result = new File[idx];
        for (int i = 0; i < idx; i++) result[i] = temp[i];

        new FilePreviewFrameArray(result);
    }

    static void scan(File folder) {

        File[] files = folder.listFiles();
        if (files == null) return;

        for (File f : files) {

            String p = f.getAbsolutePath().toLowerCase();

            if (p.contains("windows") ||
                p.contains("program files") ||
                p.contains("programdata")) continue;

            if (f.isDirectory()) {
                scan(f);
            } else {
                String n = f.getName().toLowerCase();

                if (n.endsWith(".tmp") || n.endsWith(".log") ||
                    n.endsWith(".cache") || n.endsWith(".bak")) {

                    if (idx < temp.length) {
                        temp[idx++] = f;
                    }
                }
            }
        }
    }
}
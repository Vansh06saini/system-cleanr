public class recyclebincleaner {

    // returns true if command ran successfully
    public static boolean clean() {

        try {
            Process p = Runtime.getRuntime()
                    .exec("cmd /c rd /s /q %SystemDrive%\\$Recycle.Bin");

            int exitCode = p.waitFor();  // wait for command to finish

            return exitCode == 0;        // 0 = success

        } catch (Exception e) {
            return false;
        }
    }
}
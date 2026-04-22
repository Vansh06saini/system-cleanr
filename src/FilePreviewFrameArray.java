import java.awt.*;
import java.io.File;
import javax.swing.*;

public class FilePreviewFrameArray extends JFrame {

    JTextArea area;
    JButton deleteBtn, cancelBtn;
    JProgressBar progressBar;

    File[] files;

    public FilePreviewFrameArray(File[] files) {

        this.files = files;

        setTitle("Files to be Deleted (Preview)");
        setSize(650, 500);
        setLayout(new BorderLayout());

        //  Progress Bar
        progressBar = new JProgressBar(0, files.length);
        progressBar.setStringPainted(true);
        add(progressBar, BorderLayout.NORTH);

        //  Text Area
        area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Consolas", Font.PLAIN, 12));

        for (File f : files) {
            area.append(f.getAbsolutePath() + "\n");
        }

        add(new JScrollPane(area), BorderLayout.CENTER);

        //  Buttons
        JPanel panel = new JPanel();
        deleteBtn = new JButton("Delete");
        cancelBtn = new JButton("Cancel");

        panel.add(deleteBtn);
        panel.add(cancelBtn);
        add(panel, BorderLayout.SOUTH);

        deleteBtn.addActionListener(e -> confirmAndDelete());
        cancelBtn.addActionListener(e -> dispose());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void confirmAndDelete() {

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete these files?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        int confirm2 = JOptionPane.showConfirmDialog(
                this,
                "This action is permanent. Continue?",
                "Warning",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm2 != JOptionPane.YES_OPTION) return;

        //  Run in separate thread 
        new Thread(() -> deleteFiles()).start();
    }

    private void deleteFiles() {

        int deleted = 0;
        int skipped = 0;
        long space = 0;

        for (int i = 0; i < files.length; i++) {

            File f = files[i];

            try {
                long size = f.length();

                if (f.delete()) {
                    deleted++;
                    space += size;
                } else {
                    skipped++;
                }

            } catch (Exception e) {
                skipped++;
            }

            int progress = i + 1;

            //  Update UI safely
            SwingUtilities.invokeLater(() -> {
                progressBar.setValue(progress);
                progressBar.setString(progress + " / " + files.length);
            });

            try { Thread.sleep(10); } catch (Exception e) {}
        }

        //  Fix "final variable" issue
        int finalDeleted = deleted;
        int finalSkipped = skipped;
        long finalSpace = space;

        SwingUtilities.invokeLater(() -> {
            area.setText("Cleanup Completed!\n\n");
            area.append("Deleted: " + finalDeleted + "\n");
            area.append("Skipped: " + finalSkipped + "\n");
            area.append("Freed: " + (finalSpace / 1024) + " KB\n");
        });
    }
}
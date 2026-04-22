import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CleanerGUI extends JFrame implements ActionListener {

    JButton winBtn, userBtn, junkBtn, recycleBtn, crashBtn;

    public CleanerGUI() {
        setTitle("Java System Cleaner");
        setSize(720, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new GridLayout(1, 4, 10, 10));
        top.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        top.setBackground(new Color(30, 30, 30));

        winBtn = createButton("Windows Temp");
        userBtn = createButton("User Temp");
        junkBtn = createButton("Junk Scan");
        recycleBtn = createButton("Recycle Bin");
        crashBtn = createButton("Crash Files");

        

        top.add(winBtn);
        top.add(userBtn);
        top.add(junkBtn);
        top.add(recycleBtn);
        top.add(crashBtn);

        add(top, BorderLayout.NORTH);

        JLabel title = new JLabel("SYSTEM CLEANER (SAFE MODE)", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        add(title, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(Color.DARK_GRAY);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.addActionListener(this);
        return b;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == winBtn) WindowsTempCleaner.clean();
        if (e.getSource() == userBtn) UserTempCleaner.clean();
        if (e.getSource() == junkBtn) junkcleaner.clean();
        if (e.getSource() == crashBtn) CrashFileScanner.clean();
        if (e.getSource() == recycleBtn) {
            JOptionPane.showMessageDialog(this, "Cleaning Recycle Bin...");
            new Thread(() -> {
                boolean result = recyclebincleaner.clean();
                SwingUtilities.invokeLater(() -> {
                    if (result) {
                        JOptionPane.showMessageDialog(this, "Recycle Bin cleaned successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to clean Recycle Bin.\nTry running as Administrator.");
                    }
                });

            }).start();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CleanerGUI::new);
    }
}
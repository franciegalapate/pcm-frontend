import java.lang.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 * 3
 * If user clicked "Grades"
 * ONLY SHOWS GRADES OF THE CURRENT SEM YOU'RE ENROLLED IN

 FEATURES:
 * Shows grades
 * Enter grades for subjects recently finished
 * Edit a course
 * Add other courses taken

 THINGS NEDED:
 * Buttons DO NOT WORK
 */

public class Grades extends JFrame {

    /** INITIALIZE VARIABLES */
    JFrame frame;
    JPanel topPanel, centerPanel, bottomPanel;
    JButton btnSave, btnAdd, btnHelp, btnBack;
    JLabel title;
    Font boldFont, regularFont;

    /** MAIN CONSTRUCTOR */
    Grades() throws IOException, FontFormatException {

        /** INITIALIZE FRAME */
        frame = new JFrame("Personal Checklist Manager");
        frame.setPreferredSize(new Dimension(1200, 700));
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        /** SET UP FONT */
        regularFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\PlusJakartaSans-Regular.ttf")).deriveFont(Font.PLAIN, 13);
        boldFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\assets\\PlusJakartaSans-Bold.ttf")).deriveFont(Font.PLAIN, 14);

        /** INITALIZE PANELS AND BUTTONS */

        // TOP PANEL
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(new Color(0x6256EC));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        title = new JLabel("Personal Checklist Manager");
        title.setPreferredSize(new Dimension(250, 30));
        title.setFont(boldFont);
        title.setForeground(Color.WHITE);
        title.setFocusable(false);

        topPanel.add(title, BorderLayout.WEST);
        topPanel.add(Box.createRigidArea(new Dimension(200, 0)));

        // CENTER PANEL
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        centerPanel.setFont(regularFont);

        String semester = "First Semester";
        String fileName = "first-year-first-semester.txt";

        JPanel semesterPanel = new JPanel(new BorderLayout());
        semesterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTable table = new JTable();
        table.setFont(regularFont);
        table.getTableHeader().setFont(boldFont);
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Status");
        model.addColumn("Course Number");
        model.addColumn("Course Title");
        model.addColumn("Units");
        model.addColumn("Grades");

        table.setModel(model);

        // Ensure that the file path is correct
        String filePath = "src\\data\\grades\\grades-second-semester.txt";
        populateTableFromTextFile(filePath, model);

        // Adjust preferred column widths based on your data
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(50);

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        semesterPanel.add(new JLabel(semester, SwingConstants.CENTER), BorderLayout.NORTH);
        semesterPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(semesterPanel);

        // BOTTOM PANEL
        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(0x6256EC));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        btnHelp = new JButton("Help");
        btnHelp.setPreferredSize(new Dimension(100, 30));
        btnHelp.setFont(boldFont);
        btnHelp.setForeground(Color.BLACK);
        btnHelp.setBackground(new Color(0xBEB8FF));
        btnHelp.setFocusable(false);

        btnBack = new JButton("Back to Home"); // Leads to Homepage
        btnBack.setPreferredSize(new Dimension(150, 30));
        btnBack.setFont(boldFont);
        btnBack.setForeground(Color.BLACK);
        btnBack.setBackground(new Color(0xBEB8FF));
        btnBack.setFocusable(false);

        btnAdd = new JButton("Add Course");
        btnAdd.setPreferredSize(new Dimension(120, 30));
        btnAdd.setFont(boldFont);
        btnAdd.setForeground(Color.BLACK);
        btnAdd.setBackground(new Color(0xBEB8FF));
        btnAdd.setFocusable(false);

        btnSave = new JButton("Save");
        btnSave.setPreferredSize(new Dimension(100, 30));
        btnSave.setFont(boldFont);
        btnSave.setForeground(Color.BLACK);
        btnSave.setBackground(new Color(0xBEB8FF));
        btnSave.setFocusable(false);

        bottomPanel.add(btnHelp, BorderLayout.WEST);
        bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        bottomPanel.add(btnBack);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(btnAdd);
        bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        bottomPanel.add(btnSave);

        /** ADD ACTION LISTENERS HERE */

        // ADD PANELS TO FRAME
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        /** MAKE THIS LAST */
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    } // END OF MAIN CLASS CONSTRUCTOR

    /** OTHER METHODS */


    private static void populateTableFromTextFile(String filePath, DefaultTableModel model) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found: " + filePath);
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");
                model.addRow(columns);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** MAIN METHOD */
    public static void main(String[] args) throws IOException, FontFormatException {
        Grades grades = new Grades();
    }

} // END OF MAIN CLASS

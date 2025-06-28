import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {
    private String name, rollNo, course;
    public Student(String name, String rollNo, String course) {
        this.name = name; this.rollNo = rollNo; this.course = course;
    }
    public String getName() { return name; }
    public String getRollNo() { return rollNo; }
    public String getCourse() { return course; }
    public void setName(String name) { this.name = name; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
    public void setCourse(String course) { this.course = course; }
}

public class StudentInfoSystem {
    private final JFrame frame;
    private final JTextField nameField, rollField, courseField;
    private final DefaultTableModel model;
    private final JTable table;
    private final ArrayList<Student> students;

    public StudentInfoSystem() {
        frame = new JFrame("Student Information System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 400);
        frame.setLocationRelativeTo(null);

        nameField = new JTextField(10);
        rollField = new JTextField(10);
        courseField = new JTextField(10);
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        String[] columns = {"Name", "Roll No", "Course"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScrollPane = new JScrollPane(table);

        students = new ArrayList<>();

        // Single row layout
        JPanel panel = new JPanel();
        panel.add(new JLabel("Name:")); panel.add(nameField);
        panel.add(new JLabel("Roll No:")); panel.add(rollField);
        panel.add(new JLabel("Course:")); panel.add(courseField);
        panel.add(addButton); panel.add(updateButton); panel.add(deleteButton);

        // Button actions
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String roll = rollField.getText().trim();
            String course = courseField.getText().trim();
            if (name.isEmpty() || roll.isEmpty() || course.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            students.add(new Student(name, roll, course));
            model.addRow(new Object[]{name, roll, course});
            clearFields();
        });

        updateButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Select a student to update.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String name = nameField.getText().trim();
            String roll = rollField.getText().trim();
            String course = courseField.getText().trim();
            if (name.isEmpty() || roll.isEmpty() || course.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Student s = students.get(row);
            s.setName(name); s.setRollNo(roll); s.setCourse(course);
            model.setValueAt(name, row, 0);
            model.setValueAt(roll, row, 1);
            model.setValueAt(course, row, 2);
            clearFields();
        });

        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Select a student to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            students.remove(row);
            model.removeRow(row);
            clearFields();
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                Student s = students.get(row);
                nameField.setText(s.getName());
                rollField.setText(s.getRollNo());
                courseField.setText(s.getCourse());
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        frame.add(tableScrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void clearFields() {
        nameField.setText("");
        rollField.setText("");
        courseField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentInfoSystem::new);
    }
}
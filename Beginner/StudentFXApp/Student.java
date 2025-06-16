import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final StringProperty name;
    private final StringProperty roll;
    private final StringProperty course;

    public Student(String name, String roll, String course) {
        this.name = new SimpleStringProperty(name);
        this.roll = new SimpleStringProperty(roll);
        this.course = new SimpleStringProperty(course);
    }

    public String getName() { return name.get(); }
    public void setName(String val) { name.set(val); }
    public StringProperty nameProperty() { return name; }

    public String getRoll() { return roll.get(); }
    public void setRoll(String val) { roll.set(val); }
    public StringProperty rollProperty() { return roll; }

    public String getCourse() { return course.get(); }
    public void setCourse(String val) { course.set(val); }
    public StringProperty courseProperty() { return course; }
}

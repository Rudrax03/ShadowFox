import javafx.application.Application;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StudentFXApp extends Application {

    TableView<Student> table = new TableView<>();
    ObservableList<Student> students = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField rollField = new TextField();
        rollField.setPromptText("Roll");

        TextField courseField = new TextField();
        courseField.setPromptText("Course");

        Button addBtn = new Button("Add");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");

        TableColumn<Student, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<Student, String> rollCol = new TableColumn<>("Roll");
        rollCol.setCellValueFactory(data -> data.getValue().rollProperty());

        TableColumn<Student, String> courseCol = new TableColumn<>("Course");
        courseCol.setCellValueFactory(data -> data.getValue().courseProperty());

        table.setItems(students);
        table.getColumns().addAll(nameCol, rollCol, courseCol);

        HBox inputBox = new HBox(10, nameField, rollField, courseField, addBtn, updateBtn, deleteBtn);
        VBox layout = new VBox(10, inputBox, table);
        layout.setStyle("-fx-padding: 10");

        addBtn.setOnAction(e -> {
            students.add(new Student(nameField.getText(), rollField.getText(), courseField.getText()));
            nameField.clear(); rollField.clear(); courseField.clear();
        });

        updateBtn.setOnAction(e -> {
            Student selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setName(nameField.getText());
                selected.setRoll(rollField.getText());
                selected.setCourse(courseField.getText());
                table.refresh();
            }
        });

        deleteBtn.setOnAction(e -> {
            Student selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                students.remove(selected);
            }
        });

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                nameField.setText(newSel.getName());
                rollField.setText(newSel.getRoll());
                courseField.setText(newSel.getCourse());
            }
        });

        Scene scene = new Scene(layout, 750, 300);
        stage.setTitle("Student Information System (JavaFX)");
        stage.setScene(scene);
        stage.show();
    }
}
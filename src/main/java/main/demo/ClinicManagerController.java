package main.demo;

import files.*;
import files.Date;
import files.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javafx.scene.control.ToggleGroup;

/**
 * Controller class for managing clinic operations, including scheduling appointments and handling provider information.
 * @author Amani Islam *
 */

public class ClinicManagerController {

    private List<Appointment> appointments;
    private List<Provider> providers;
    Iterator<Technician> techIterator;
    private List<Technician> technicians;
    private List<Person> patients;

    /**
     * Constructs a new ClinicManagerController, initializing empty lists for appointments, providers, technicians, and patients.
     */
    public ClinicManagerController() {
        appointments = new List<>();
        providers = new List<>();
        technicians = new List<>();
        techIterator = technicians.iterator();
        patients = new List<>();
    }

    /**
     * Reads provider data from a file and initializes the list of providers and technicians based on the file's contents.
     *
     */
    public void run() {
        try {

            File file = new File("providers.txt");

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.toUpperCase().split("\\s+");

                Profile profile = new Profile(fields[1], fields[2], new Date(fields[3]));
                Provider provider;

                if ("D".equals(fields[0])) {
                    provider = new Doctor(profile, Location.valueOf(fields[4]), Specialty.valueOf(fields[5]),
                            fields[6]);
                    providers.add(provider);
                } else {
                    provider = new Technician(profile, Location.valueOf(fields[4]), Integer.parseInt(fields[5]));
                    providers.add(provider);
                    technicians.add((Technician) provider);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "File not found.");
            alert.showAndWait();
        }
    }

    /**
     * Handles the scheduling of an office appointment. Opens a form for entering patient and appointment details, then validates and submits the appointment.
     */
    @FXML
    private void handleScheduleOffice() {
        run();
        Stage appointmentStage = new Stage();
        appointmentStage.initModality(Modality.APPLICATION_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Define the components
        TextArea errorLabel = new TextArea();
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setEditable(false);
        errorLabel.setWrapText(true);

        Label dateLabel = new Label("Date (MM/DD/YYYY):");
        TextField dateField = new TextField();
        Label timeslotLabel = new Label("Timeslot:");
        TextField timeslotField = new TextField();
        Label firstNameLabel = new Label("Patient First Name:");
        TextField firstNameField = new TextField();
        Label lastNameLabel = new Label("Patient Last Name:");
        TextField lastNameField = new TextField();
        Label dobLabel = new Label("Patient DOB (MM/DD/YYYY):");
        TextField dobField = new TextField();
        Label npiLabel = new Label("Doctor's NPI:");
        TextField npiField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            errorLabel.setText("");

            if (dateField.getText().isEmpty() || timeslotField.getText().isEmpty() ||
                    firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() ||
                    dobField.getText().isEmpty() || npiField.getText().isEmpty()) {
                errorLabel.setText("Please fill in all fields.");
                return;
            }

            Date date;
            try {
            date = new Date(dateField.getText());
            if (!date.isValid()) {
                errorLabel.setText("Invalid date");
                return;}
            } catch (NumberFormatException | NoSuchElementException ex) {
                errorLabel.setText("Invalid date.");
                return;
            }


            try {
                int timeslot = Integer.parseInt(timeslotField.getText());
                if (timeslot < 1 || timeslot > 12) {
                    errorLabel.setText("Invalid timeslot");
                    return;
                }
            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid timeslot.");
                return;
            }

            Timeslot slot = Timeslot.choose(timeslotField.getText());

            Date dob;
            try {
                dob = new Date(dobField.getText());
                if (!dob.validDob()) {
                    errorLabel.setText("Invalid dob.");

                    return;
                }
            } catch (NumberFormatException | NoSuchElementException ex) {
                errorLabel.setText("Invalid dob.");
                return;
            }


            Person patient = new Person(new Profile(firstNameField.getText(), lastNameField.getText(), dob));
            if (!patients.contains(patient)) {
                patients.add(patient);
            }

            Person doctor = null;
            Appointment app = new Appointment(date, slot, patient, doctor);
            if (appointments.contains(app)) {
                errorLabel.setText("Appointment already exists at this time");
                return;
            }

            if (!npiField.getText().matches("\\d+")) {
                errorLabel.setText("Invalid NPI. Please enter a numeric value.");
                return;
            }

            for (int i = 0; i < providers.size(); i++) {
                Provider provider = providers.get(i);
                if (provider instanceof Doctor doc) {
                    if (doc.getNPI().equals(npiField.getText())) {
                        doctor = new Person(doc.getProfile());
                    }
                }
            }
            if (doctor == null) {
                errorLabel.setText("Doctor not found.");
                return;
            }

            app = new Appointment(date, slot, patient, doctor);

            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).availablity(app)) {
                    errorLabel.setText("Doctor not available");
                    return;
                }
            }

            appointments.add(app);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment submitted!");
            alert.showAndWait();
            appointmentStage.close();
        });

        gridPane.add(dateLabel, 0, 0);
        gridPane.add(dateField, 1, 0);
        gridPane.add(timeslotLabel, 0, 1);
        gridPane.add(timeslotField, 1, 1);
        gridPane.add(firstNameLabel, 0, 2);
        gridPane.add(firstNameField, 1, 2);
        gridPane.add(lastNameLabel, 0, 3);
        gridPane.add(lastNameField, 1, 3);
        gridPane.add(dobLabel, 0, 4);
        gridPane.add(dobField, 1, 4);
        gridPane.add(npiLabel, 0, 5);
        gridPane.add(npiField, 1, 5);
        gridPane.add(submitButton, 1, 6);
        gridPane.add(errorLabel, 0, 7, 2, 1);

        // Set up the scene
        Scene scene = new Scene(gridPane, 400, 500);
        appointmentStage.setScene(scene);
        appointmentStage.setTitle("Schedule Appointment");
        appointmentStage.showAndWait();
    }

    /**
     * Handles the scheduling of an imaging appointment. Opens a form for entering patient and appointment details, then validates and submits the appointment.
     */
    @FXML
    private void handleScheduleImaging() {
        run();
        Stage appointmentStage = new Stage();
        appointmentStage.initModality(Modality.APPLICATION_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);


        TextArea errorLabel = new TextArea();
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setEditable(false);
        errorLabel.setWrapText(true);

        Label dateLabel = new Label("Date (MM/DD/YYYY):");
        TextField dateField = new TextField();
        Label timeslotLabel = new Label("Timeslot:");
        TextField timeslotField = new TextField();
        Label firstNameLabel = new Label("Patient First Name:");
        TextField firstNameField = new TextField();
        Label lastNameLabel = new Label("Patient Last Name:");
        TextField lastNameField = new TextField();
        Label dobLabel = new Label("Patient DOB (MM/DD/YYYY):");
        TextField dobField = new TextField();
        Label serviceLabel = new Label("Radiology Service:");
        ToggleGroup serviceToggleGroup = new ToggleGroup();
        VBox radiologyServiceVBox = new VBox();

        for (Radiology service : Radiology.values()) {
            RadioButton radioButton = new RadioButton(service.toString());
            radioButton.setToggleGroup(serviceToggleGroup);
            radiologyServiceVBox.getChildren().add(radioButton);
        }

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            errorLabel.setText("");

            if (dateField.getText().isEmpty() || timeslotField.getText().isEmpty() ||
                    firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() ||
                    dobField.getText().isEmpty() || serviceToggleGroup.getSelectedToggle() == null){
                errorLabel.setText("Please fill in all fields.");
                return;
            }


            Date date;
            try {
                date = new Date(dateField.getText());
                if (!date.isValid()) {
                    errorLabel.setText("Invalid date");
                    return;}
            } catch (NumberFormatException | NoSuchElementException ex) {
                errorLabel.setText("Invalid date.");
                return;
            }

            try {
                int timeslot = Integer.parseInt(timeslotField.getText());
                if (timeslot < 1 || timeslot > 12) {
                    errorLabel.setText("Invalid timeslot");
                    return;
                }
            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid timeslot.");
                return;
            }

            Timeslot slot = Timeslot.choose(timeslotField.getText());

            Date dob;
            try {
                dob = new Date(dobField.getText());
                if (!dob.validDob()) {
                    errorLabel.setText("Invalid dob.");

                    return;
                }
            } catch (NumberFormatException | NoSuchElementException ex) {
                errorLabel.setText("Invalid dob.");
                return;
            }

            Person patient = new Person(new Profile(firstNameField.getText(),lastNameField.getText() , dob));

            if (!patients.contains(patient)) {
                patients.add(patient);
            }

            Person technician = null;
            Radiology room = null;

            Appointment app = new Appointment(date, slot, patient, technician);
            if (appointments.contains(app)) {
                errorLabel.setText("Appointment already exists at this time");
                return;
            }

            RadioButton selectButt = (RadioButton) serviceToggleGroup.getSelectedToggle();
            String selected = selectButt.getText();
            room = Radiology.valueOf(selected.replace(" ", "_").toUpperCase());

            Imaging appa = null;
            boolean ava = false;
            boolean looped = false;

            if (!techIterator.hasNext()) {
                techIterator = technicians.iterator();
            }

            while (!looped) {
                if (!techIterator.hasNext()) {
                    techIterator = technicians.iterator();
                    looped = true;
                }

                Technician tech = techIterator.next();
                ava = true;
                appa = new Imaging(room, date, slot, patient, tech);

                for (int i = 0; i < appointments.size(); i++) {
                    if (appointments.get(i).availablity(appa)) {
                        ava = false;
                        break;
                    }
                }

                if (ava) {
                    break;
                }
            }

            if (!ava) {
                errorLabel.setText("No available technicians");
                return;
            }

            appointments.add(appa);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment submitted!");
            alert.showAndWait();
            appointmentStage.close();
        });


        gridPane.add(dateLabel, 0, 0);
        gridPane.add(dateField, 1, 0);
        gridPane.add(timeslotLabel, 0, 1);
        gridPane.add(timeslotField, 1, 1);
        gridPane.add(firstNameLabel, 0, 2);
        gridPane.add(firstNameField, 1, 2);
        gridPane.add(lastNameLabel, 0, 3);
        gridPane.add(lastNameField, 1, 3);
        gridPane.add(dobLabel, 0, 4);
        gridPane.add(dobField, 1, 4);
        gridPane.add(serviceLabel, 0, 5);
        gridPane.add(radiologyServiceVBox, 1, 5);
        gridPane.add(submitButton, 1, 6);
        gridPane.add(errorLabel, 0, 7, 2, 1);

        Scene scene = new Scene(gridPane, 400, 500);
        appointmentStage.setScene(scene);
        appointmentStage.setTitle("Schedule Appointment");
        appointmentStage.showAndWait();
    }

    /**
     * Handles the cancelling of an appointment. Opens a form for entering patient and appointment details, then validates and cancels the appointment.
     */
    @FXML
    private void handleCancelAppointment() {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        TextArea errorLabel = new TextArea();
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setEditable(false);
        errorLabel.setWrapText(true);

        Label dateLabel = new Label("Date (MM/DD/YYYY):");
        TextField dateField = new TextField();
        Label timeslotLabel = new Label("Timeslot:");
        TextField timeslotField = new TextField();

        Label firstNameLabel = new Label("Patient First Name:");
        TextField firstNameField = new TextField();
        Label lastNameLabel = new Label("Patient Last Name:");
        TextField lastNameField = new TextField();
        Label dobLabel = new Label("Patient DOB (MM/DD/YYYY):");
        TextField dobField = new TextField();
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            errorLabel.setText("");

            if (dateField.getText().isEmpty() || timeslotField.getText().isEmpty() ||
                    firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() ||
                    dobField.getText().isEmpty()) {
                errorLabel.setText("Please fill in all fields.");
                return;
            }

            Date date;
            try {
                date = new Date(dateField.getText());
                if (!date.isValid()) {
                    errorLabel.setText("Invalid date");
                    return;}
            } catch (NumberFormatException | NoSuchElementException ex) {
                errorLabel.setText("Invalid date.");
                return;
            }

            Timeslot slot = Timeslot.choose(timeslotField.getText());

            Date dob;
            try {
                dob = new Date(dobField.getText());
                if (!dob.validDob()) {
                    errorLabel.setText("Invalid dob.");

                    return;
                }
            } catch (NumberFormatException | NoSuchElementException ex) {
                errorLabel.setText("Invalid dob.");
                return;
            }

            Person patient = new Person(new Profile(firstNameField.getText(), lastNameField.getText(), dob));
            Appointment app = new Appointment(date, slot, patient, null);
            if (!appointments.contains(app)) {
                errorLabel.setText("Appointment does not exist");
                return;
            }
            appointments.remove(app);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment cancelled!");
            alert.showAndWait();
            stage.close();
        });

        gridPane.add(dateLabel, 0, 0);
        gridPane.add(dateField, 1, 0);
        gridPane.add(timeslotLabel, 0, 1);
        gridPane.add(timeslotField, 1, 1);
        gridPane.add(firstNameLabel, 0, 2);
        gridPane.add(firstNameField, 1, 2);
        gridPane.add(lastNameLabel, 0, 3);
        gridPane.add(lastNameField, 1, 3);
        gridPane.add(dobLabel, 0, 4);
        gridPane.add(dobField, 1, 4);
        gridPane.add(submitButton, 1, 5);
        gridPane.add(errorLabel, 0, 6, 2, 1);

        Scene scene = new Scene(gridPane, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Cancel Appointment");
        stage.showAndWait();
    }


    /**
     * Handles the rescheduling of an appointment. Opens a form for entering patient and appointment details, then validates and reschedules the appointment.
     */
    @FXML
    private void handleRescheduleAppointment() {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        TextArea errorLabel = new TextArea();
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setEditable(false);
        errorLabel.setWrapText(true);

        Label dateLabel = new Label("Date (MM/DD/YYYY):");
        TextField dateField = new TextField();
        Label timeslotLabel = new Label("Timeslot:");
        TextField timeslotField = new TextField();
        Label firstNameLabel = new Label("Patient First Name:");
        TextField firstNameField = new TextField();
        Label lastNameLabel = new Label("Patient Last Name:");
        TextField lastNameField = new TextField();
        Label dobLabel = new Label("Patient DOB (MM/DD/YYYY):");
        TextField dobField = new TextField();
        Label newSlotLabel = new Label("New Timeslot:");
        TextField newslotField = new TextField();
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            errorLabel.setText("");

            if (dateField.getText().isEmpty() || timeslotField.getText().isEmpty() ||
                    firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() ||
                    dobField.getText().isEmpty() || newslotField.getText().isEmpty()) {
                errorLabel.setText("Please fill in all fields.");
                return;
            }

            Date date;
            try {
                date = new Date(dateField.getText());
                if (!date.isValid()) {
                    errorLabel.setText("Invalid date");
                    return;}
            } catch (NumberFormatException | NoSuchElementException ex) {
                errorLabel.setText("Invalid date.");
                return;
            }

            try {
                int timeslot = Integer.parseInt(timeslotField.getText());
                if (timeslot < 1 || timeslot > 12) {
                    errorLabel.setText("Invalid timeslot");
                    return;
                }
            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid timeslot.");
                return;
            }

            Timeslot slot = Timeslot.choose(timeslotField.getText());

            try {
                int timeslot = Integer.parseInt(newslotField.getText());
                if (timeslot < 1 || timeslot > 12) {
                    errorLabel.setText("Invalid new timeslot");
                    return;
                }
            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid new timeslot.");
                return;
            }

            Timeslot newSlot = Timeslot.choose(newslotField.getText());

            Date dob;
            try {
                dob = new Date(dobField.getText());
                if (!dob.validDob()) {
                    errorLabel.setText("Invalid dob.");

                    return;
                }
            } catch (NumberFormatException | NoSuchElementException ex) {
                errorLabel.setText("Invalid dob.");
                return;
            }

            Person patient = new Person(new Profile(firstNameField.getText(), lastNameField.getText(), dob));
            Appointment app = new Appointment(date, slot, patient, null);
            int pos = appointments.indexOf(app);
            if (pos == -1) {
                errorLabel.setText("Appointment does not exist");
                return;
            }

            Person doctor = appointments.get(pos).getProvider();
            app = new Appointment(date, newSlot, patient, doctor);

            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).availablity(app)) {
                    errorLabel.setText("Doctor not available");
                    return;
                }
            }

            appointments.set(pos, app);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment rescheduled!");
            alert.showAndWait();
            stage.close();
        });

        gridPane.add(dateLabel, 0, 0);
        gridPane.add(dateField, 1, 0);
        gridPane.add(timeslotLabel, 0, 1);
        gridPane.add(timeslotField, 1, 1);
        gridPane.add(firstNameLabel, 0, 2);
        gridPane.add(firstNameField, 1, 2);
        gridPane.add(lastNameLabel, 0, 3);
        gridPane.add(lastNameField, 1, 3);
        gridPane.add(dobLabel, 0, 4);
        gridPane.add(dobField, 1, 4);
        gridPane.add(newSlotLabel, 0, 5);
        gridPane.add(newslotField, 1, 5);
        gridPane.add(submitButton, 1, 6);
        gridPane.add(errorLabel, 0, 7, 2, 1);

        // Set up the scene
        Scene scene = new Scene(gridPane, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Reschedule Appointment");
        stage.showAndWait();
    }

    /**
     * Displays all appointments sorted by date in a modal table view.
     */
    @FXML
    private void handlePA() {
        if (appointments.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No Appointments Available");
            alert.showAndWait();
            return;
        }

        Sort.appointment(appointments, providers, 'd');

        ObservableList<Appointment> observableAppointments = FXCollections.observableArrayList();
        for (int i = 0; i < appointments.size(); i++) {
            observableAppointments.add(appointments.get(i));
        }
        TableView<Appointment> appointmentTableView = createAppointmentTableView(observableAppointments);

        appointmentTableView.setItems(observableAppointments);

        VBox vbox = new VBox(appointmentTableView);
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(scene);
        stage.setTitle("List of Appointments (sorted by date)");
        stage.showAndWait();
    }

    /**
     * Displays all appointments sorted by patient in a modal table view.
     */
    @FXML
    private void handlePP() {
        if (appointments.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No Appointments Available");
            alert.showAndWait();
            return;
        }

        Sort.appointment(appointments, providers, 'p');

        ObservableList<Appointment> observableAppointments = FXCollections.observableArrayList();
        for (int i = 0; i < appointments.size(); i++) {
            observableAppointments.add(appointments.get(i));
        }
        TableView<Appointment> appointmentTableView = createAppointmentTableView(observableAppointments);

        appointmentTableView.setItems(observableAppointments);

        VBox vbox = new VBox(appointmentTableView);
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(scene);
        stage.setTitle("List of Appointments (sorted by patient)");
        stage.showAndWait();
    }

    /**
     * Displays all appointments sorted by provider location in a modal table view.
     */
    @FXML
    private void handlePL() {
        if (appointments.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No Appointments Available");
            alert.showAndWait();
            return;
        }

        Sort.appointment(appointments, providers, 'l');

        ObservableList<Appointment> observableAppointments = FXCollections.observableArrayList();
        for (int i = 0; i < appointments.size(); i++) {
            observableAppointments.add(appointments.get(i));
        }
        TableView<Appointment> appointmentTableView = createAppointmentTableView(observableAppointments);

        appointmentTableView.setItems(observableAppointments);

        VBox vbox = new VBox(appointmentTableView);
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(scene);
        stage.setTitle("List of Appointments (sorted by location)");
        stage.showAndWait();
    }

    /**
     * Displays all patient billing statements in a modal table view.
     * Clears appointments after.
     */
    @FXML
    private void handlePS() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setTitle("Patient Billing Statements");
        TableView<Object[]> tableView = new TableView<>();

        TableColumn<Object[], String> patientColumn = new TableColumn<>("Patient");
        patientColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty((String) data.getValue()[0]));

        TableColumn<Object[], Integer> dueColumn = new TableColumn<>("Amount Due");
        dueColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty((Integer) data.getValue()[1]).asObject());

        tableView.getColumns().add(patientColumn);
        tableView.getColumns().add(dueColumn);

        ObservableList<Object[]> data = FXCollections.observableArrayList();

        for (Person patient : patients) {
            int total = 0;
            for (Appointment appointment : appointments) {
                if (appointment.getPatient().getProfile().equals(patient.getProfile())) {
                    for (Provider provider : providers) {
                        if (appointment.getProvider().getProfile().equals(provider.getProfile())) {
                            total += provider.rate();
                            break;
                        }
                    }
                }
            }
            if (total != 0) {
                data.add(new Object[]{patient.getProfile().toString(), total});
            }
        }

        tableView.setItems(data);

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        appointments = new List<Appointment>();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointments have been archived.");
        alert.showAndWait();
        stage.close();
    }

    /**
     * Displays all provider total credit amoutns in a modal table view.
     */
    @FXML
        private void handlePC() {

            if (appointments.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "No Appointments Available");
                alert.showAndWait();
                return;
            }


            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Provider Credit Amounts");

            TableView<Map.Entry<String, Integer>> tableView = new TableView<>();
            TableColumn<Map.Entry<String, Integer>, String> providerColumn = new TableColumn<>("Provider");
            TableColumn<Map.Entry<String, Integer>, Integer> creditColumn = new TableColumn<>("Credit Amount");

            providerColumn.setCellValueFactory(entry -> new javafx.beans.property.SimpleStringProperty(entry.getValue().getKey()));
            creditColumn.setCellValueFactory(entry -> new javafx.beans.property.SimpleIntegerProperty(entry.getValue().getValue()).asObject());

            tableView.getColumns().add(providerColumn);
            tableView.getColumns().add(creditColumn);

            Map<String, Integer> providerCredits = new HashMap<>();
            Sort.provider(providers);

            for (Provider provider : providers) {
                int total = 0;
                for (Appointment appointment : appointments) {
                    if (appointment.getProvider().getProfile().equals(provider.getProfile())) {
                        total += provider.rate();
                    }
                }
                if (total != 0) {
                    providerCredits.put(provider.getProfile().toString(), total);
                }
            }

            ObservableList<Map.Entry<String, Integer>> data = FXCollections.observableArrayList(providerCredits.entrySet());
            tableView.setItems(data);

            VBox vbox = new VBox(tableView);
            Scene scene = new Scene(vbox);
            stage.setScene(scene);
            stage.showAndWait();

    }

    /**
     * Displays all office and imaging appointments in separate tabs in a modal table view.
     */
    @FXML
    private void handlePOI() {

        if (appointments.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No Appointments Available");
            alert.showAndWait();
            return;
        }

        Sort.appointment(appointments,providers,'l');

        TabPane tabPane = new TabPane();

        ObservableList<Appointment> officeAppointments = FXCollections.observableArrayList();
        ObservableList<Appointment> imagingAppointments = FXCollections.observableArrayList();

        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i) instanceof Imaging) {
                imagingAppointments.add(appointments.get(i));
            } else {
                officeAppointments.add(appointments.get(i));
            }
        }

        Tab officeTab = new Tab("Office Appointments");
        TableView<Appointment> officeTable = createAppointmentTableView(officeAppointments);
        officeTab.setContent(officeTable);

        Tab imagingTab = new Tab("Imaging Appointments");
        TableView<Appointment> imagingTable = createAppointmentTableView(imagingAppointments);
        imagingTab.setContent(imagingTable);

        tabPane.getTabs().addAll(officeTab, imagingTab);

        VBox vbox = new VBox(tabPane);
        Scene scene = new Scene(vbox, 600, 400);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(scene);
        stage.setTitle("Appointments Ordered by Date");
        stage.showAndWait();
    }


    /**
     * Creates a table view displaying appointment details, including date, time, patient, and provider.
     *
     * @param filteredAppointments The list of appointments to display in the table.
     * @return TableView configured to show the appointment information.
     */
    private TableView<Appointment> createAppointmentTableView(ObservableList<Appointment> filteredAppointments) {
        TableView<Appointment> tableView = new TableView<>();

        TableColumn<Appointment, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().toString())); // Assuming Date has a toString()

        TableColumn<Appointment, String> timeslotColumn = new TableColumn<>("Time");
        timeslotColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTimeslot().toString())); // Assuming Timeslot has a toString()

        TableColumn<Appointment, String> patientColumn = new TableColumn<>("Patient");
        patientColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPatient().toString())); // Assuming Person has a toString()

        TableColumn<Appointment, String> providerColumn = new TableColumn<>("Provider");
        providerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProvider().toString())); // Assuming Person has a toString()

        tableView.getColumns().addAll(dateColumn, timeslotColumn, patientColumn, providerColumn);

        tableView.setItems(filteredAppointments);
        return tableView;
    }

}

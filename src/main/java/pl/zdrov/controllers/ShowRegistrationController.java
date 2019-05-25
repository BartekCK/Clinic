package pl.zdrov.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.database.modelsFX.RegistrationFx;
import pl.zdrov.database.modelsFX.RegistrationModel;
import pl.zdrov.database.modelsFX.SpecializationModel;
import java.time.LocalDate;
/**
 * Klasa kontroluje wykonywane akcje na pliku ShowRegistration.fxml
 */
public class ShowRegistrationController {

    /**
     * Przechowuje referencje obiektu, który odpowiada za akcje wykonywane na obiekcie registration
     */
    private RegistrationModel registrationModel;

    /**
     * Przechowuje wszystkie możliwe do uzyskania godziny
     */
    private final ObservableList<String> hourString = FXCollections.observableArrayList("08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30","12:00",
            "12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00");
    /**
     * Referencja w celu pobrania tekstu
     */
    @FXML
    private TextField nameDoctorTextField;

    /**
     * Referencja w celu pobrania tekstu
     */
    @FXML
    private TextField surnameDoctorTextField;

    /**
     * Referencja w celu pobrania specializacji
     */
    @FXML
    private ComboBox<Specialization> specializationConboBox;

    /**
     * Referencja w celu pobrania tekstu
     */
    @FXML
    private ComboBox<String> hourComboBox;

    /**
     * Referencja w celu wyboru daty
     */
    @FXML
    private DatePicker datePicker;

    /**
     * Referencja w celu pobrania specializacji
     */
    @FXML
    private TextField namePatientTextField;

    /**
     * Referencja w celu pobrania specializacji
     */
    @FXML
    private TextField surnamePatientTextField;

    /**
     * Wyświetlenie wszystkich rejestracji
     */
    @FXML
    private TableView<RegistrationFx> tableView;

    /**
     * Wyświetlenie wszystkich rejestracji
     */
    @FXML
    private TableColumn<RegistrationFx, String> nameDoctorTableColumn;

    /**
     * Wyświetlenie wszystkich rejestracji
     */
    @FXML
    private TableColumn<RegistrationFx, String> surnameDoctorTableColumn;

    /**
     * Wyświetlenie wszystkich rejestracji
     */
    @FXML
    private TableColumn<RegistrationFx, String> specializationTableColumn;

    /**
     * Wyświetlenie wszystkich rejestracji
     */
    @FXML
    private TableColumn<RegistrationFx, LocalDate> dateTableColumn;

    /**
     * Wyświetlenie wszystkich rejestracji
     */
    @FXML
    private TableColumn<RegistrationFx, String> hourTableColumn;

    /**
     * Wyświetlenie wszystkich rejestracji
     */
    @FXML
    private TableColumn<RegistrationFx, String> namePatientTableColumn;

    /**
     * Wyświetlenie wszystkich rejestracji
     */
    @FXML
    private TableColumn<RegistrationFx, String> surnamePatientTableColumn;

    /**
     * Metoda wykonująca się tuż po konstruktorze
     */
    @FXML
    public void initialize()
    {
        registrationModel = new RegistrationModel();
        registrationModel.init();

        hourComboBox.setItems(hourString);
        specializationConboBox.setItems(SpecializationModel.returnAllSpecialization());

        tableView.setItems(registrationModel.getRegistrationFxShowObservableList());
        nameDoctorTableColumn.setCellValueFactory(cellData -> cellData.getValue().getDoctorFx().nameProperty());
        surnameDoctorTableColumn.setCellValueFactory(cellData -> cellData.getValue().getDoctorFx().surNameProperty());
        specializationTableColumn.setCellValueFactory(cellData -> cellData.getValue().getDoctorFx().getSpecializationFx().titleProperty());
        dateTableColumn.setCellValueFactory(cellData -> cellData.getValue().addedDateProperty());
        hourTableColumn.setCellValueFactory(cellData -> cellData.getValue().timeFromProperty());
        namePatientTableColumn.setCellValueFactory(cellData -> cellData.getValue().getPatientFx().nameProperty());
        surnamePatientTableColumn.setCellValueFactory(cellData -> cellData.getValue().getPatientFx().surNameProperty());

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            registrationModel.setRegistrationFx(newValue);
        });



        namePatientTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            tableView.setItems(registrationModel.nameSearch(newValue,1));
            setMainObservableList(newValue);
        }));

        surnamePatientTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            tableView.setItems(registrationModel.surnameSearch(newValue,1));
            setMainObservableList(newValue);
        }));

        hourComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            tableView.setItems(registrationModel.comboboxSearch(newValue,1));
            setMainObservableList(newValue);
        });



        nameDoctorTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            tableView.setItems(registrationModel.nameSearch(newValue,0));
            setMainObservableList(newValue);
        }));

        surnameDoctorTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            tableView.setItems(registrationModel.surnameSearch(newValue,0));
            setMainObservableList(newValue);
        }));
        specializationConboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            tableView.setItems(registrationModel.comboboxSearch(newValue.getTitle(),0));
            setMainObservableList(newValue.getTitle());
        });

        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            tableView.setItems(registrationModel.dateSearch(newValue.toString()));
            setMainObservableList(newValue.toString());
        });


    }

    /**
     * Metoda czyszcząca wyszukiwanie
     */
    @FXML
    void clearSearch(){
        nameDoctorTextField.clear();
        surnameDoctorTextField.clear();
        specializationConboBox.setItems(SpecializationModel.returnAllSpecialization());

        hourComboBox.setValue(null);
        hourComboBox.setItems(hourString);

        datePicker.setValue(null);

        namePatientTextField.clear();

        surnamePatientTextField.clear();
        registrationModel.clearRegistrationFxShowObservableListSearch();
        tableView.setItems(registrationModel.getRegistrationFxShowObservableList());
    }

    /**
     * Metoda wykonująca się po kliknięcu prawym przyciskiem myszy
     */
    @FXML
    void deleteRegistration() {
        registrationModel.deleteRegistrationInDataBase();
    }

    /**
     * Ustala atualnie wybrana rejestracje w celu wykonywania na niej akcji
     * @param newValue sprawdza czy ma wyświetlić wszystkie dane
     */
    private void setMainObservableList(String newValue) {
        if(newValue.isEmpty())
        {
            tableView.setItems(registrationModel.getRegistrationFxShowObservableList());
        }
    }

}

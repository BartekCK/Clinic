package pl.zdrov.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.zdrov.Path;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.database.modelsFX.DoctorFx;
import pl.zdrov.database.modelsFX.DoctorModel;
import pl.zdrov.database.modelsFX.RegistrationModel;
import pl.zdrov.database.modelsFX.SpecializationModel;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.FxmlUtilies;
import pl.zdrov.utilies.converters.ConverterDoctor;
import pl.zdrov.utilies.exceptions.ApplicationException;


/**
 * Klasa kontroluje wykonywane akcje na pliku ShowDoctor.fxml
 */
public class ShowDoctorController{

    /**
     * Przechowuje referencje obiektu, który odpowiada za akcje wykonywane na obiekcie doctor
     */
    private DoctorModel doctorModel;

    /**
     * Referencja w celu pobrania tekstu
     */
    @FXML
    private TextField nameTextField;
    /**
     * Referencja w celu pobrania tekstu
     */
    @FXML
    private TextField surnameTextField;
    /**
     * Referencja w celu pobrania specializacji
     */
    @FXML
    private ComboBox<Specialization> specializationComboBox;
    /**
     * Wyświetlenie wszystkich lekarzy
     */
    @FXML
    private TableView<DoctorFx> tableView;
    /**
     * Wyświetlenie wszystkich lekarzy
     */
    @FXML
    private TableColumn<DoctorFx,String> nameTableView,surnameTableView,mailTableView, phoneTableView,specializationTableView;

    /**
     * Metoda wykonująca się tuż po konstruktorze
     */
    @FXML
    public void initialize()
    {
        doctorModel = new DoctorModel();
        doctorModel.init();
        specializationComboBox.setItems(SpecializationModel.returnAllSpecialization());

        tableView.setPlaceholder(new Label("Brak wyników"));

        tableView.setItems(doctorModel.getDoctorFxShowObservableList());
        nameTableView.setCellValueFactory(cellData-> cellData.getValue().nameProperty());
        surnameTableView.setCellValueFactory(cellData-> cellData.getValue().surNameProperty());
        mailTableView.setCellValueFactory(cellData-> cellData.getValue().mailProperty());
        phoneTableView.setCellValueFactory(cellData-> cellData.getValue().phoneProperty());
        specializationTableView.setCellValueFactory(cellData->  cellData.getValue().getSpecializationFx().titleProperty());

        tableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            doctorModel.setDoctorFx(newValue);
        }));

        nameTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            tableView.setItems(doctorModel.nameSearch(newValue));
            setMainObservableList(newValue);
        }));

        surnameTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            tableView.setItems(doctorModel.surnameSearch(newValue));
            setMainObservableList(newValue);
        }));
        specializationComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            tableView.setItems(doctorModel.specializationSearch(newValue));
            setMainObservableList(newValue.getTitle());
        });

    }

    /**
     * Metoda czyszcząca wyszukiwanie
     */
    @FXML
    public void clearSearch() {
        nameTextField.clear();
        surnameTextField.clear();
        specializationComboBox.setItems(SpecializationModel.returnAllSpecialization());
        tableView.setItems(doctorModel.getDoctorFxShowObservableList());

    }
    /**
     * Metoda wykonująca się po kliknięcu prawym przyciskiem myszy
     */
    @FXML
    public void registerPatient() {
        RegistrationController registrationController;
        if(BackgroundController.getRegistrationController() ==null)
        {
            registrationController = FxmlUtilies.getControllerNewWindow(Path.REGISTRATION_PATH,"Okno rejestracji").getController();
            BackgroundController.setRegistrationController(registrationController);
        }else {
            registrationController = BackgroundController.getRegistrationController();
        }
        registrationController.setDoctor(ConverterDoctor.convertToDoctor(doctorModel.getDoctorFx()));
    }

    /**
     * Metoda wykonująca się po kliknięcu prawym przyciskiem myszy
     */
    @FXML
    private void showDoctor() {

        ShowPersonController showPersonController = FxmlUtilies.getControllerNewWindow(Path.SHOW_PERSON_PATH,"").getController();
        showPersonController.setImage(ConverterDoctor.convertToDoctor(doctorModel.getDoctorFx()));

    }

    /**
     * Metoda wykonująca się po kliknięcu prawym przyciskiem myszy
     */
    @FXML
    public void checkWorkHourWork(){

        AddWorkHoursController addWorkHoursController = FxmlUtilies.getControllerNewWindow(Path.ADD_WORK_HOURS_PATH,"Godziny pracy lekarza").getController();
        addWorkHoursController.setVisibleHbox();
        addWorkHoursController.showWorkHours(ConverterDoctor.convertToDoctor(doctorModel.getDoctorFx()));

    }

    /**
     * Metoda wykonująca się po kliknięcu prawym przyciskiem myszy
     */
    @FXML
    public void editDoctor() {

        AddDoctorController addDoctorController = FxmlUtilies.getControllerMainWindow(Path.ADD_DOCTOR_PATH).getController();
        addDoctorController.setTextFields(ConverterDoctor.convertToDoctor(doctorModel.getDoctorFx()));

    }

    /**
     * Metoda wykonująca się po kliknięcu prawym przyciskiem myszy
     */
    @FXML
    public void deleteDoctor() {
        try
        {
            if(RegistrationModel.isDoctorInRegistration("DOCTOR_ID",ConverterDoctor.convertToDoctor(doctorModel.getDoctorFx())))
                throw new ApplicationException("Nie można usunąć lekarza, ktory ma zaplanowane wizyty");
            doctorModel.deleteDoctorInDataBase();
        }catch (ApplicationException e)
        {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }

    }

    /**
     * Ustala aktualnego wybranego lekarza w celu wykonywania na nim akcji
     * @param newValue sprawdza czy ma wyświetlić wszystkie dane
     */
    private void setMainObservableList(String newValue) {
        if(newValue.isEmpty())
        {
            tableView.setItems(doctorModel.getDoctorFxShowObservableList());
        }
    }

}

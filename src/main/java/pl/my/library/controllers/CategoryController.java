package pl.my.library.controllers;

/*
kontroller pliku fxml addCategory.fxml
dodawanie i edycja kategorii

 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.my.library.modelFx.CategoryFx;
import pl.my.library.modelFx.CategoryModel;
import pl.my.library.utils.DialogsUtils;
import pl.my.library.utils.exceptions.ApplicationExceptios;

public class CategoryController {

    @FXML
    private Button addCategoryButton;

    @FXML
    private Button deleteCategoryButton;

    @FXML
    private Button editCategoryButton;

    @FXML
    private TextField categoryTextField;

    @FXML
    private ComboBox<CategoryFx> categoryComboBox;
    private CategoryModel categoryModel;

    @FXML  //metody wywoływane wraz z otwarciem pliku fxml
    public void initialize() {
        this.categoryModel = new CategoryModel();
        try {
            this.categoryModel.init();
        } catch (ApplicationExceptios e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        this.categoryComboBox.setItems(this.categoryModel.getCategoryList());
        initBindings();
    }

    //wyłączenie dostępności kontrollek, jesli nie spełniony jest warunek
    private void initBindings() {
        this.addCategoryButton.disableProperty().bind(categoryTextField.textProperty().isEmpty());
        this.deleteCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());
        this.editCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());
    }

    //dodanie kategorii jesli możliwe, jeśli nie wywala okno dialogowe errorDialoge
    public void addCategoryOnAction() {
        try {
            categoryModel.saveCategoryInDataBase(categoryTextField.getText());
        } catch (ApplicationExceptios e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        categoryTextField.clear();
    }

    //usuniecie kategorii
    public void deleteCategoryOnAction() {
        try {
            this.categoryModel.deleteCategoryById();
        } catch (ApplicationExceptios e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    //wybierz element z comboBoxu
    public void comboBoxOnAction() {
        this.categoryModel.setCategory(this.categoryComboBox.getSelectionModel().getSelectedItem());
    }

    //edytowanie kategorii, jesli wybrana jest nazwa, jeśli nie, pokazuje okno dialogowe errorDialog
    public void editCategoryOnAction() {
        String newCategoryName = DialogsUtils.editDialog(this.categoryModel.getCategory().getName());
        if (newCategoryName != null) {
            this.categoryModel.getCategory().setName(newCategoryName);
            try {
                this.categoryModel.updateCategoryInDataBase();
            } catch (ApplicationExceptios e) {
                DialogsUtils.errorDialog(e.getMessage());
            }
        }
    }
}

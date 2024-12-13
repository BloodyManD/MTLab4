package com.example.mtlab4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TextViewerController implements Initializable {
    static Stage stage;
    DocumentProperty documentProperty;

    @FXML
    private HTMLEditor htmlEditor;
    private TableView tableView;

    @FXML
    private BorderPane borderPane;

    @FXML
    private void setBoldText(ActionEvent event) {
    }

    @FXML
    private void openFile(ActionEvent event) throws TikaException, TransformerConfigurationException, IOException, SAXException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");

        fileChooser.getExtensionFilters().add(new  FileChooser.ExtensionFilter("Documents", "*.doc", "*.docx", "*.txt", "*.xls", "*.xlsx"));
        File file = fileChooser.showOpenDialog(stage);
        documentProperty = new DocumentProperty(file.getAbsolutePath());
        htmlEditor.setHtmlText(documentProperty.getHTMLXDocReport());
        borderPane.setCenter(htmlEditor);
        System.out.println(htmlEditor.getHtmlText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

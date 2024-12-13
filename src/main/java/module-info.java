module com.example.mtlab4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.apache.tika.core;
    requires org.apache.poi.ooxml;
    requires fr.opensagres.poi.xwpf.converter.xhtml;
    requires fr.opensagres.poi.xwpf.converter.core;
    requires html2openxml;
    requires org.docx4j.core;


    opens com.example.mtlab4 to javafx.fxml;
    exports com.example.mtlab4;
}
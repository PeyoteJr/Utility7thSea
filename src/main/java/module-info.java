module com.example.utility7thsea {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.utility7thsea to javafx.fxml;
    exports com.example.utility7thsea;
    exports com.example.utility7thsea.controller;
    opens com.example.utility7thsea.controller to javafx.fxml;
    exports com.example.utility7thsea.application;
    opens com.example.utility7thsea.application to javafx.fxml;
}
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

    opens utility7thsea to javafx.fxml;
    exports utility7thsea;
    exports utility7thsea.controller;
    opens utility7thsea.controller to javafx.fxml;
    exports utility7thsea.model;
    opens utility7thsea.model to javafx.base;
}
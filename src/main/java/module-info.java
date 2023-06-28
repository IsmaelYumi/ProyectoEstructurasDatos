module Controlador {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens Controlador to javafx.fxml;
    exports Controlador;
}

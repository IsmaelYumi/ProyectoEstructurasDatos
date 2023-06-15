module Controlador {
    requires javafx.controls;
    requires javafx.fxml;

    opens Controlador to javafx.fxml;
    exports Controlador;
}

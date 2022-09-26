module ru.smirnygatotoshka.caseapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens ru.smirnygatotoshka.caseapp to javafx.fxml;
    exports ru.smirnygatotoshka.caseapp;
    exports ru.smirnygatotoshka.caseapp.Database;
    exports ru.smirnygatotoshka.caseapp.DataRepresentation;
    opens ru.smirnygatotoshka.caseapp.Database to javafx.fxml;
    opens ru.smirnygatotoshka.caseapp.DataRepresentation to javafx.base;
    exports ru.smirnygatotoshka.caseapp.Controllers;
    opens ru.smirnygatotoshka.caseapp.Controllers to javafx.fxml;
    exports ru.smirnygatotoshka.caseapp.Controllers.Registrator;
    opens ru.smirnygatotoshka.caseapp.Controllers.Registrator to javafx.fxml;
    exports ru.smirnygatotoshka.caseapp.Registrator;
    opens ru.smirnygatotoshka.caseapp.Registrator to javafx.fxml;
}
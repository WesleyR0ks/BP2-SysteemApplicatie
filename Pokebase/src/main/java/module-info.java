module com.wesley.r.pokebase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.wesley.r.pokebase to javafx.fxml;
    exports com.wesley.r.pokebase;
}
package Components.NiceListView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class NiceCell extends ListCell<CellObject> {

    @FXML
    private Label titleLabel;

    @FXML
    private Label commentLabel;

    @FXML
    private Label descriptionLabel;

    public NiceCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("nice_cell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(CellObject item, boolean empty) {
        super.updateItem(item, empty);

        if(empty) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {
            titleLabel.setText(item.getTitle());
            commentLabel.setText(item.getComment());
            descriptionLabel.setText(item.getDescription());

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}

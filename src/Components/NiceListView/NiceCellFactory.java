package Components.NiceListView;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class NiceCellFactory  implements Callback<ListView<CellObject>, ListCell<CellObject>> {

    @Override
    public ListCell<CellObject> call(ListView<CellObject> param)
    {
        return new NiceCell();
    }
}

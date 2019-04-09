package Components.NiceListView;

public class CellObject {
    private String title;
    private String comment;
    private String description;

    public CellObject(String title, String comment, String desc){
        this.title = title;
        this.comment = comment;
        this.description = desc;
    }

    public String getTitle(){
        return title;
    }

    public String getComment(){
        return comment;
    }

    public String getDescription(){
        return description;
    }
}

package Components.NiceListView;

public class CellObject {
    private String title;
    private String comment;
    private String description;
    private int id;

    public CellObject(String title, String comment, String desc){
        this.title = title;
        this.comment = comment;
        this.description = desc;
        this.id = 0;
    }

    public CellObject(String title, String comment, String desc, int id){
        this.title = title;
        this.comment = comment;
        this.description = desc;
        this.id = id;
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

    public int getId(){
        return id;
    }
}

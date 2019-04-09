package Dtos;

public class RideDto {
    private String title;
    private String comment;
    private String description;

    public RideDto(String title, String comment, String desc){
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

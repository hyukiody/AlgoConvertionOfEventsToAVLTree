package Entities;

public class Camera {

    private int id;
    private String type;
    private String imagePath;

    public Camera(int id, String type, String imagePath) {
        this.id = id;
        this.type= type;
        this.imagePath=imagePath;
    }

    /**
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * @param ID the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    public String getImagePath(){
        return this.imagePath;
    }
    public void setImagePth(String imagePath){
        this.imagePath = imagePath;
    }
}

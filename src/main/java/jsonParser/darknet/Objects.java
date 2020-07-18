package jsonParser.darknet;

public class Objects {
    private int class_id;
    private String name;
    private Relative_coordinates relative_coordinates;
    private float confidence;


    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getName() {
        return name;
    }

    public Relative_coordinates getRelative_coordinates() {
        return relative_coordinates;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    public void setRelative_coordinates(Relative_coordinates relative_coordinates) {
        this.relative_coordinates = relative_coordinates;
    }
}

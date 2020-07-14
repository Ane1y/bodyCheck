package jsonParser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relative_coordinates {
    private float center_x;
    private float center_y;
    private float width;
    private float height;

    public Relative_coordinates(float center_x, float center_y, float width, float height) {
        this.center_x = center_x;
        this.center_y = center_y;
        this.width = width;
        this.height = height;
    }

    public float getCenter_x() {
        return center_x;
    }

    public float getCenter_y() {
        return center_y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}

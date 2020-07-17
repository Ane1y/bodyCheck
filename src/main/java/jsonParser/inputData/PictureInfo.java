package jsonParser.inputData;

import java.util.LinkedList;

public class PictureInfo {
    private String filename;
    private LinkedList<Measuring> measuring;
    private Point scale = null;

    public PictureInfo(String filename, Measuring measuring) {
        this.filename = filename;
        this.measuring = new LinkedList<>();
        this.measuring.add(measuring);
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public LinkedList<Measuring> getMeasuring() {
        return measuring;
    }

    public void setMeasuring(LinkedList<Measuring> measuring) {
        this.measuring = measuring;
    }

    public Measuring getMeasuring(int index) {
        return measuring.get(index);
    }

    public Point getScale() {
        return scale;
    }

    public void setScale(Point scale) {
        this.scale = scale;
    }

    public void setScaledPoint(Point scale) {
        for(var m : measuring) {
            m.setScaledMeasuring(scale);
        }
    }
}

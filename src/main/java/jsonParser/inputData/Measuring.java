package jsonParser.inputData;

public class Measuring {
    private partsOfBody partOfBody;
    private Point beginPoint;
    private Point endPoint;

    public enum partsOfBody{
        SHOULDERS,
        WAIST,
        HIPS
    }
    public Measuring(partsOfBody partOfBody, Point beginPoint, Point endPoint) {
        this.partOfBody = partOfBody;
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }

    public partsOfBody getPartOfBody() {
        return partOfBody;
    }

    public void setPartOfBody(partsOfBody partOfBody) {
        this.partOfBody = partOfBody;
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public void setBeginPoint(Point beginPoint) {
        this.beginPoint = beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public void setScaledMeasuring(Point scale) {
        endPoint.setScaledPoint(scale);
        beginPoint.setScaledPoint(scale);
    }
}

package Lab02;

public class ShapeFactory {

    public Shape getShape(ShapeType shapeType){
        switch(shapeType){
            case CIRCLE:
                return new Circle();
            case RECTANGLE:
                return new Rectangle();
            case SQUARE:
                return new Square();
            default:
                return null;
        }
    }
}

package Lab02;

public class ShapeFactoryMain {

    public static void main(String[] args){
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape circle = shapeFactory.getShape(ShapeType.CIRCLE);
        Shape rectangle = shapeFactory.getShape(ShapeType.RECTANGLE);
        Shape square = shapeFactory.getShape(ShapeType.SQUARE);

        circle.draw();
        rectangle.draw();
        square.draw();
    }
}

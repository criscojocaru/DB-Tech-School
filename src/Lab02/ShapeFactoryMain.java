package Lab02;

public class ShapeFactoryMain {

    public static void main(String[] args){

        Shape circle = ShapeFactory.getShape(ShapeType.CIRCLE);
        Shape rectangle = ShapeFactory.getShape(ShapeType.RECTANGLE);
        Shape square = ShapeFactory.getShape(ShapeType.SQUARE);

        circle.draw();
        rectangle.draw();
        square.draw();
    }
}

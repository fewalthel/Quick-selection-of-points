/**
 * File: HelloApplication.java
 * Description: Главный класс, из которого происходит запуск приложения
 * Author: Полюхович Диана
 * Date: 15.05.2024
 */

package org.example.jvafxdemo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import static org.example.jvafxdemo.RectangularSelector.CreateMaxRectangle;

public class HelloApplication extends Application {

    public static final int WINDOW_WIDTH = 800; //ширина оконного приложения
    public static final int WINDOW_HEIGHT = 600; //высота оконного приложения
    public static int ZOOM_FACTOR = 60; //коэффициент масштабирования точек
    public static int POINT_RADIUS = 5; //радиус точки
    public static int MIN_VALUE = 10; //минимальная координата для случайной точки
    public static int MAX_VALUE = 15; //максимальная координата для случайной точки

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        BorderPane container = new BorderPane();
        Pane root = new Pane();
        container.setCenter(root);

        try {
            //создаем массив точек
            Point[] points = new Point[10];

            //заполняем массив случайными точками
            createRandomPoints(points, MIN_VALUE, MAX_VALUE);

            //точки, образующие прямоугольник с наибольшей площадью
            Point[] maxRect = CreateMaxRectangle(points);

            //отображение точек
            showPoints(points, root);

            //отображение прямоугольника
            showRectangle(maxRect, root);

            Scene scene = new Scene(container, WINDOW_WIDTH, WINDOW_HEIGHT); //инициализация окна для приложения
            stage.setTitle("QuickSelectionOfPoints"); //название окна приложения
            stage.setScene(scene);
            stage.show();
        } catch (NotEnoughSidesException | NoSuitablePointsException e) {
            System.err.println(e.getMessage()); //выводим ошибку в консоль
        }
    }

    /**
     * Метод для визуализации точек
     * @param points массив точек, которые нужно визуализировать
     * @param root корневой элемент, к которому будут добавляться точки
     */
    public static void showPoints(Point[] points, Pane root) {
        for (Point point : points) {
            javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle(point.getX() * ZOOM_FACTOR, point.getY() * ZOOM_FACTOR, POINT_RADIUS);
            circle.setFill(Color.RED);
            root.getChildren().add(circle);
        }
    }

    /**
     * Метод для визуализации прямоугольника
     * @param maxRect массив точек, находящихся на углах прямоугольника
     * @param root корневой элемент, к которому будет добавляться прямоугольник
     */
    public static void showRectangle(Point[] maxRect, Pane root) {
        Polygon rectangle = new Polygon();
        rectangle.getPoints().addAll(
                (double) maxRect[0].getX() * ZOOM_FACTOR, (double) maxRect[0].getY() * ZOOM_FACTOR,
                (double) maxRect[1].getX() * ZOOM_FACTOR, (double) maxRect[1].getY() * ZOOM_FACTOR,
                (double) maxRect[2].getX() * ZOOM_FACTOR, (double) maxRect[2].getY() * ZOOM_FACTOR,
                (double) maxRect[3].getX() * ZOOM_FACTOR, (double) maxRect[3].getY() * ZOOM_FACTOR);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLUE);
        root.getChildren().add(rectangle);
    }

    /**
     * Метод для создания случайных точек
     * @param points массив точек
     * @param minValue минимальное значение для генерации случайной координаты
     * @param maxValue максимальное значение для генерации случайной координаты
     * */
    public static void createRandomPoints(Point[] points, int minValue, int maxValue) {
        for (int i = 0; i < points.length; i++) {
            int x = (int) (Math.random() * (maxValue- minValue) + minValue);
            int y = (int) (Math.random() * (maxValue- minValue) + minValue);
            points[i] = new Point(x, y);
        }
    }
}
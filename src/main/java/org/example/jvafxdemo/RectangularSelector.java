/**
 * File: RectangularSelector.java
 * Description: Класс, содержащий методы для работы с точками в двумерном пространстве
 * Author: Полюхович Диана
 * Date: 15.05.2024
 */

package org.example.jvafxdemo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class RectangularSelector {

    /**
     * Метод для создания прямоугольной оболочки максимальной площади
     * @param points массив точек, среди которых будет строиться прямоугольник
     * @return четыре точки, образующие прямоугольник с максимальной площадью
     */
    public static Point[] CreateMaxRectangle(Point[] points) throws NotEnoughSidesException, NoSuitablePointsException {
        if (points.length < 4) {
            throw new NotEnoughSidesException("Для прямоугольника необходимо как минимум 4 точки");
        }
        LinkedList<Point[]> suitablePoints = new LinkedList<>(); //список наборов точек, являющихся вершинами прямоугольников
        if (!rectangleExists(points, suitablePoints)) {
            throw new NoSuitablePointsException("В массиве нет точек, являющихся веришнами прямоугольника");
        }

        //сортируем точки по координате X
        Arrays.sort(points, Comparator.comparingInt(Point::getX));

        //находим максимальное и минимальное значение Y для каждой координаты X
        int[] maxY = new int[points.length];
        int[] minY = new int[points.length];
        for (int i = 0; i < points.length; i++) { maxY[i] = points[i].getY(); minY[i] = points[i].getY();}
        for (int i = 1; i < points.length; i++) { maxY[i] = Math.max(maxY[i], maxY[i - 1]); minY[i] = Math.min(minY[i], minY[i - 1]);}

        //ищем прямоугольник с максимальной площадью среди найденных точек

        double maxArea = 0;
        Point[] maxRect = new Point[4];
        for (int i = 0; i < suitablePoints.size(); i ++) {
            Vectr side1 = new Vectr(suitablePoints.get(i)[0], suitablePoints.get(i)[1]);
            Vectr side2 = new Vectr(suitablePoints.get(i)[1], suitablePoints.get(i)[2]);
            if (side1.getLength() * side2.getLength() > maxArea) {
                maxArea = side1.getLength() * side2.getLength();
                maxRect[0] = suitablePoints.get(i)[0]; maxRect[1] = suitablePoints.get(i)[1];
                maxRect[2] = suitablePoints.get(i)[2]; maxRect[3] = suitablePoints.get(i)[3]; }
        }
        return maxRect;
    }

    /**
     * Метод, проверяющий, существует ли прямоугольник, вершинами которого являются любые 4 точки из массива
     * @param points массив всех точек
     * @return true, если такой прямоугольник существует
     * */
    public static boolean rectangleExists(Point[] points, LinkedList<Point[]> suitablePoints){
        if (points.length < 4) {
            return false; // если точек меньше четырех, то невозможно образовать прямоугольник
        }

        //проверяем все возможные перестановки точек
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        Point p1 = points[i];
                        Point p2 = points[j];
                        Point p3 = points[k];
                        Point p4 = points[l];
                        //проверяем, существует ли прямоугольник, вершинами которого являюся случайные 4 точки из массива всех точек
                        if (isRectangle(p1, p2, p3, p4)) {
                            Point[] four_points = {p1, p2, p3, p4};
                            suitablePoints.add(four_points);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Метод, проверяющий, что фигура, вершинами которого явлютсся 4 точки, является прямоугольником
     * @param p1 первая точка
     * @param p2 вторая точка
     * @param p3 третья точка
     * @param p4 четвертая точка
     * @return true, если фигура является прямоугольником
     * */
    public static boolean isRectangle(Point p1, Point p2, Point p3, Point p4) {
        //находим стороны фигуры
        Vectr side1 = new Vectr(p1, p2);
        Vectr side2 = new Vectr(p2, p3);
        Vectr side3 = new Vectr(p3, p4);
        Vectr side4 = new Vectr(p4, p1);

        //проверяем, равны ли длины сторон фигуры
        if (side1.getLength() == side3.getLength() && side2.getLength() == side4.getLength()) {
            //проверяем углы между сторонами
            double cosUgla1_2 = (Math.abs(side1.getX()*side2.getX() + side1.getY()* side2.getY()))/Math.sqrt(side1.getLength()*side2.getLength()); //косинус угла между стороной 1 и 2
            double cosUgla3_4 = (Math.abs(side3.getX()*side4.getX() + side3.getY()* side4.getY()))/Math.sqrt(side3.getLength()*side4.getLength()); //косинус угла между стороной 1 и 2
            //если углы прямые, косинус углов равен 0
            return cosUgla1_2 == cosUgla3_4 && cosUgla3_4 == (double) 0; //проверяем равенство косинусов углов
        }
        return false;
    }

}

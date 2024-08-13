/**
 * File: Point.java
 * Description: Класс, представляющий собой точку в двумерном пространстве с координатами x и y
 * Author: Полюхович Диана
 * Date: 15.05.2024
 */

package org.example.jvafxdemo;

public class Point {

    private final int x; //координата точки по x
    private final int y; //координата точки по y


    /**
     * Конструктоор экземпляра класса Point
     * @param x координата точки по x
     * @param y координата точки по y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return координата точки по x
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return координата точки по y
     */
    public int getY() {
        return this.y;
    }
}
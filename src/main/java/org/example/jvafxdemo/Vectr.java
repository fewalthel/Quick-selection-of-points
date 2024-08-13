/**
 * File: Point.java
 * Description: Класс, представляющий собой вектор, проходящий через 2 точки
 * Author: Полюхович Диана
 * Date: 15.05.2024
 */

package org.example.jvafxdemo;

public class Vectr {
    private final int x; //координата вектора по x
    private final int y; //координата вектора по y
    private final double length; //длина вектора

    /**
     * Конструктор экземпляра класса Vectr
     * @param point1 первая точка, через которую проходит вектор
     * @param point2 вторая точка, через которую проходит вектор
     */
    public Vectr(Point point1, Point point2) {
        this.x = point2.getX() - point1.getX();
        this.y = point2.getY() - point1.getY();
        this.length = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    /**
     * @return координата вектора по x
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return координата вектора по y
     */
    public int getY() {
        return this.y;
    }

    /**
     * @return длина вектора
     */
    public double getLength() {
        return this.length;
    }
}

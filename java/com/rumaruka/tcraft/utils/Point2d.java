package com.rumaruka.tcraft.utils;

public class Point2d {

    public final double x;
    public final double y;

    public Point2d(double x, double y){
        this.x=x;
        this.y=y;
    }
    public boolean containedBy(float x, float y, float w, float h){
        return (this.x>=x)&&(this.x <=x+w)&&(this.y>=y)&&(this.y<=y+h);
    }

}

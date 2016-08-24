package com.example.routemapper;

public class RouteItem
{
    public String name;
    public String date;
    public String color;
    public String location;
    public int grade;
    public String setter;

    public RouteItem(String name, String date, String color, String location, int grade, String setter)
    {
        this.name = name;
        this.date = date;
        this.color = color;
        this.location = location;
        this.grade = grade;
        this.setter = setter;
    }
}

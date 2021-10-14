package model;

public class Date {
    private int month;
    private int day;
    private int year;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public Date findEndDate(Date startDate) {
        return new Date(1,1,2021);
    }
}

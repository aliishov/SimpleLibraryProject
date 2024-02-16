package ru.alishov.springproject.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Book
{
    private int id;

    @NotEmpty(message = "Name should not be empty")
    private String bookName;

    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+")
    private String authorName;

    @Min(value = 0, message = "Year od issue should be greater than 0")
    private int yearOfIssue;

    public Book () {}

    public Book(String bookName, String authorName, int yearOfIssue)
    {
        this.bookName = bookName;
        this.authorName = authorName;
        this.yearOfIssue = yearOfIssue;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public int getYearOfIssue() { return yearOfIssue; }
    public void setYearOfIssue(int yearOfIssue) { this.yearOfIssue = yearOfIssue; }
}

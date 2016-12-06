package com.example.pratigya.hamblaster;

/**
 * Created by Pratigya on 05-12-2016.
 */

public class Book {
    public String book_email;
    public String book_phone;
    public String book_title;
    //  public List<String> admin_hubs;

    public Book()
    {

    }
    public Book(String book_email,String book_phone,String book_title)
    {
        this.book_email=book_email;
        this.book_phone=book_phone;
        this.book_title=book_title;
        // this.admin_hubs=admin_hubs;
    }
    public String getbook_email() {
        return book_email;
    }
    public String getbook_phone() {
        return book_phone;
    }
    public String getbook_title() {
        return book_title;
    }
    // public List<String> getadmin_hubs()
    // {
    //     return admin_hubs;
    //  }
}

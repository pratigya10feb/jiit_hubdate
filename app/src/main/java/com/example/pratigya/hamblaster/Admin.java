package com.example.pratigya.hamblaster;

/**
 * Created by Pratigya on 03-12-2016.
 */

public class Admin {
    public String admin_email;
  //  public List<String> admin_hubs;
public String admin_hubs;
    public Admin()
    {

    }
    public Admin(String admin_email,String admin_hubs)
    {
        this.admin_email=admin_email;
        this.admin_hubs=admin_hubs;
    }
    public String getadmin_email() {
        return admin_email;

    }
    public String getadmin_hubs()
    {
        return admin_hubs;
    }
   // public List<String> getadmin_hubs()
   // {
   //     return admin_hubs;
  //  }
}

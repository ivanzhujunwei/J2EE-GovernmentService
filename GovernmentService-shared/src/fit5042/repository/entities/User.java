/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.repository.entities;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
enum UserType
{
    GOVERNMENT_WORKER,
    PUBLIC
}

public class User
{

    private int id;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String type; // two user types
    private String address;
    private int phone;

    public int getId()
    {
        return id;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public String getType()
    {
        return type;
    }

    public String getAddress()
    {
        return address;
    }

    public int getPhone()
    {
        return phone;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setPhone(int phone)
    {
        this.phone = phone;
    }

}

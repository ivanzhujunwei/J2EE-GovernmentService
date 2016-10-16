/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.repository.entities;

import fit5042.utility.Constant;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Junwei Zhu
 */
//enum UserType
//{
//    GOVERNMENT_WORKER,
//    PUBLIC
//}

/***
 * SystemUser object.
 * There two types of government organization users: government workers and public
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@MappedSuperclass
//@NamedQueries({@NamedQuery(name = SystemUser.GET_ALL_QUERY_NAME, query = "SELECT u FROM SystemUser u")})
public class SystemUser implements Serializable
{
//    public static final String GET_ALL_QUERY_NAME = "SystemUser.getAllUsers";
    @Column(name = "user_id")
    @Id 
    protected int id;
    @Column(name = "lastname")
    protected String lastName;
    @Column(name = "firstname")
    protected String firstName;
    @Column(name = "email")
    protected String email;
    @Column (name = "password")
    protected String password;
    @Column (name = "user_type")
    protected String user_type; // two user types
    @Column (name = "address")
    protected String address;
    @Column (name = "phone")
    protected String phone;

    public SystemUser(){
        this.password = Constant.DEFAULT_PSD;
    }
    
    public SystemUser(int id, String lastName, String firstName, String email, String password, String type, String address, String phone){
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.user_type = type;
        this.address = address;
        this.phone = phone;
        this.password = Constant.DEFAULT_PSD;
    }
    
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


    public String getAddress()
    {
        return address;
    }

    public String getPhone()
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

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getUser_type()
    {
        return user_type;
    }

    public void setUser_type(String user_type)
    {
        this.user_type = user_type;
    }

}

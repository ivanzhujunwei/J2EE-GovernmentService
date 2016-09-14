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
public class Service {
    private int no;
    private String name;
    // TODO: what kind of type are not clearly clarified yet
    private String type;
    private String thumbnail;
    private String description;
    
    public Service(int no, String name, String type, String thumbnail, String description){
        this.no = no;
        this.name = name;
        this.type = type;
        this.thumbnail = thumbnail;
        this.description = description;
    }

    public int getNo()
    {
        return no;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public String getThumbnail()
    {
        return thumbnail;
    }

    public String getDescription()
    {
        return description;
    }

    public void setNo(int no)
    {
        this.no = no;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setThumbnail(String thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    
    
}

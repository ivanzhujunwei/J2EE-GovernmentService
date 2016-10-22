/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fit5042.repository.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Junwei Zhu
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Service.GET_ALL_QUERY_NAME, query = "SELECT s FROM Service s "
            + "WHERE s.isActive = :isactive "),
    @NamedQuery(name = Service.GET_SEARCHED_QUERY, query = "SELECT s FROM Service s "
            + "WHERE s.name LIKE :service_name "
            + "AND s.type LIKE :service_type "
            + "AND s.description LIKE :service_description "
            + "AND s.isActive = :isactive")})
@SequenceGenerator(name="SEQ_SERVICE", initialValue=10, allocationSize=1)
public class Service implements Serializable{
    
    public static final String GET_ALL_QUERY_NAME = "Service.getAllServices";
    public static final String GET_SEARCHED_QUERY = "Service.getCombinedSearchService";
    @Column(name = "service_no")
//    @Id @GeneratedValue(strategy= GenerationType.IDENTITY, generator="SEQ_SERVICE")
    @Id @GeneratedValue(strategy= GenerationType.AUTO, generator="SEQ_SERVICE")
    protected int service_no;
    @Column(name = "name")
    @NotNull
    @Size(min=1, max=50)
    protected String name;
    @Column(name = "type")
    protected String type;
    @Column(name = "thumbnail")
    protected String thumbnail;
    @Column(name = "description")
    @Size(min=0, max=500)
    protected String description;
    @Column( name = "isactive")
    protected boolean isActive;

    public Service()
    {
        isActive = true;
    }
    
    /***
     * Service constructor
     * @param type Service type
     * @param description  Service description
     */
    public Service(String type, String description){
        this.type = type;
        this.description = description;
        isActive = true;
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

    public int getService_no()
    {
        return service_no;
    }

    public void setService_no(int service_no)
    {
        this.service_no = service_no;
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

    public boolean isIsActive()
    {
        return isActive;
    }

    public void setIsActive(boolean isActive)
    {
        this.isActive = isActive;
    }
    
    
}

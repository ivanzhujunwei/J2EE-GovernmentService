/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.repository.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@Entity
@NamedQueries({@NamedQuery(name = ServiceType.GET_ALL_SERVICE_TYPE,query = "SELECT t FROM ServiceType t")})
public class ServiceType implements Serializable
{
    public static final String GET_ALL_SERVICE_TYPE = "ServiceType.getAllServiceType";
    @Id
    @Column(name = "type_id")
    private int typeId;

    @Column(name = "type_name")
    private String typeName;
    
    public ServiceType(){
    }

    public int getTypeId()
    {
        return typeId;
    }

    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

}

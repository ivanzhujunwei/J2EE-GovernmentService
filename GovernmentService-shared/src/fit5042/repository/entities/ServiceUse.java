/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fit5042.repository.entities;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author Junwei Zhu
 */
public class ServiceUse {

    // ID number of Service Use
    private int useId;
    // Member of public using the service
    private User publicUser;
    // Date when the service is used
    private Date useDate;
    // The services that the service use include
    private List<Service> serviceList;
    // Government worker linked to the service/transaction
    private User governmentWorker;

    public int getUseId()
    {
        return useId;
    }

    public User getPublicUser()
    {
        return publicUser;
    }

    public Date getUseDate()
    {
        return useDate;
    }

    public List<Service> getServiceList()
    {
        return serviceList;
    }

    public User getGovernmentWorker()
    {
        return governmentWorker;
    }

    public void setUseId(int useId)
    {
        this.useId = useId;
    }

    public void setPublicUser(User publicUser)
    {
        this.publicUser = publicUser;
    }

    public void setUseDate(Date useDate)
    {
        this.useDate = useDate;
    }

    public void setServiceList(List<Service> serviceList)
    {
        this.serviceList = serviceList;
    }

    public void setGovernmentWorker(User governmentWorker)
    {
        this.governmentWorker = governmentWorker;
    }
    
    
    
}

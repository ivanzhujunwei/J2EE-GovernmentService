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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 * *
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@Entity
@NamedQueries({
    })
@SequenceGenerator(name="SEQ_SERVICEUSE", initialValue=10, allocationSize=1)
public class ServiceUse implements Serializable
{
    // ID number of Service Use
    @Id
    @Column(name = "useId")
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="SEQ_SERVICEUSE")
    private int useId;
    // Member of public using the service
    @JoinColumn(name = "used_by", nullable = false)
    private PublicUser publicUser;
    // Date when the service is used
    @Column(name = "usedate")
    private String useDate;
    // Used service
    @ManyToOne
    @JoinColumn(name = "usedService", nullable = false)
    private Service usedService;
    // Government worker linked to the service/transaction
    @ManyToOne
    @JoinColumn(name = "managed_by", nullable = false)
    private Worker governmentWorker;
    @Column (name = "is_finished")
    private boolean isFinished;

    public ServiceUse()
    {
    }

    public ServiceUse(int useId, PublicUser publicUser, String useDate, Worker governmentWorker)
    {
        this.useId = useId;
        this.publicUser = publicUser;
        this.useDate = useDate;
        this.governmentWorker = governmentWorker;
        this.usedService = new Service();
        isFinished = false;
    }

    public int getUseId()
    {
        return useId;
    }

    public PublicUser getPublicUser()
    {
        return publicUser;
    }

    public String getUseDate()
    {
        return useDate;
    }

    public Service getUsedService()
    {
        return usedService;
    }

    public Worker getGovernmentWorker()
    {
        return governmentWorker;
    }

    public void setUseId(int useId)
    {
        this.useId = useId;
    }

    public void setPublicUser(PublicUser publicUser)
    {
        this.publicUser = publicUser;
    }

    public void setUseDate(String useDate)
    {
        this.useDate = useDate;
    }

    public void setUsedService(Service usedService)
    {
        this.usedService = usedService;
    }

    public void setGovernmentWorker(Worker governmentWorker)
    {
        this.governmentWorker = governmentWorker;
    }

    public boolean isIsFinished()
    {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished)
    {
        this.isFinished = isFinished;
    }

}

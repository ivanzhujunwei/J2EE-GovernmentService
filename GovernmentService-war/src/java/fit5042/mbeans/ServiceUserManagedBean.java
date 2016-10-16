/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.mbeans;

import fit5042.repository.PublicUserRepository;
import fit5042.repository.ServiceUseRepository;
import fit5042.repository.entities.PublicUser;
import fit5042.repository.entities.ServiceUse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@ManagedBean(name = "serviceUseBean")
@ViewScoped
public class ServiceUserManagedBean
{

    @EJB
    PublicUserRepository publicUserRepository;

    @EJB
    ServiceUseRepository serviceUseRepository;
    /**
     * *
     * The response after public user using a service
     */
    public String useServiceResult;

    private String service_id;

    ServiceManagedBean serviceManagedBean;

    public ServiceUserManagedBean()
    {
        useServiceResult = "";
        serviceManagedBean = new ServiceManagedBean();
    }

    public void isUsedService(String service_id)
    {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String username = request.getRemoteUser();
        PublicUser pu = publicUserRepository.searchPublicUserByEmail(username);
        // Get the service use from database
        int user_id = pu.getId();
        // Get the curren service's use list 
        ServiceUse uncompletedServiceUse = serviceUseRepository.getServiceUse(user_id, Integer.parseInt(service_id));
        // If the public user has alreay used the service
        if (uncompletedServiceUse != null) {
            this.useServiceResult = "You are currently using the service.";
        } else {
            this.useServiceResult = "";
        }

    }

    /**
     * *
     * Use a service
     *
     * @return Service detail page
     */
    public void useService()
    {
        // Simulate a public user
//        PublicUser pu = new PublicUser();
//        pu.setId(1);
//        // Create a new serviceUse
//        ServiceUse su = new ServiceUse();
//        su.setPublicUser(pu);
        // Distribute a worker to deal with the transaction
        // The service will be assigned to the worker who has smallest number of services to deal with.
//        su.setGovernmentWorker(getAssignedWorker());
        // Get current data
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//	Date date = new Date();
//        su.setUseDate(dateFormat.format(date));
//        su.setUseDate(useServiceResult);
        // Add the serviceUse
//        serviceRepository.addService(su);
//        this.useServiceResult = "Apply successfully";
//        return "service_detail";
    }

    public String getUseServiceResult()
    {
        return useServiceResult;
    }

    public void setUseServiceResult(String useServiceResult)
    {
        this.useServiceResult = useServiceResult;
    }

    public String getService_id()
    {
        return service_id;
    }

    public void setService_id(String service_id)
    {
        this.service_id = service_id;
    }

}

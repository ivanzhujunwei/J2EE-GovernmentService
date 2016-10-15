/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fit5042.mbeans;

import fit5042.repository.entities.PublicUser;
import fit5042.repository.entities.ServiceUse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
public class ServiceUserManagedBean {
    
    /***
     * The response after public user using a service
     */
    public String useServiceResult;
    
    ServiceManagedBean serviceManagedBean;
    
    public ServiceUserManagedBean(){
        useServiceResult = "";
        serviceManagedBean = new ServiceManagedBean();
    }
    
    /**
     * *
     * Use a service
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
}

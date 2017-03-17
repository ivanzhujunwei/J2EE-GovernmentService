/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.repository;

import fit5042.repository.entities.PublicUser;
import fit5042.repository.entities.Service;
import fit5042.repository.entities.ServiceUse;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@Remote
public interface ServiceUseRepository
{

    /***
     * Delete service use
     * @param su 
     */
    public void deleteServiceUse(ServiceUse su);

    /**
     * **
     * Get all service uses
     *
     * @return All service uses
     */
    public List<ServiceUse> getAllServiceUse();

    /**
     * *
     * Add a serviceUse
     *
     * @param serviceUse the serviceUse needed to be added
     */
    public void addServiceUse(ServiceUse serviceUse);

    /**
     * *
     * Get one public user's service uses records
     *
     * @param pu public user
     * @return Public user's service use list
     */
    public List<ServiceUse> getServiceUseByUser(PublicUser pu);

    /**
     * *
     * Update service use, e.g. finish one service use
     *
     * @param su Service use
     */
    public void updateServiceUse(ServiceUse su);

    /**
     * *
     * Get uncompleted service use
     *
     * @param user_id public user id
     * @param service_id service id
     * @return Uncompleted service use
     */
    public ServiceUse getServiceUse(int user_id, int service_id);
    
    /***
     * Get all related service use by a particular service
     * @param service
     * @return Service use related with the service
     */
    public List<ServiceUse> getServiceUsesByService(Service service);

    /**
     * *
     * Get current service use records
     *
     * @param publicUserId public user id
     * @return Service use list
     */
//    public List<ServiceUse> getCurrentServiceUsesByPublic(int publicUserId);

    /**
     * *
     * Get completed service use records
     *
     * @param publicUserId public user id
     * @return Service use list
     */
//    public List<ServiceUse> getFinishedServiceUsesByPublic(int publicUserId);

}

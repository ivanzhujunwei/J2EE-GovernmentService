/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.repository;

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
    /****
     * Get all service uses
     * @return All service uses
     */
    public List<ServiceUse> getAllServiceUse();
    
    /***
     * Add a serviceUse
     * @param serviceUse the serviceUse needed to be added
     */
    public void addServiceUse(ServiceUse serviceUse);
    
    /***
     * Get one public user's service uses records
     * @param su_id service use id
     * @return Public user's service use list
     */
    public List<ServiceUse> getServiceUseByUserNO(int su_id);
    
    /***
     * Update service use, e.g. finish one service use
     * @param su Service use 
     */
    public void updateServiceUse(ServiceUse su);
    
    /***
     * Get uncompleted service use 
     * @param user_id public user id
     * @param service_id service id
     * @return Uncompleted service use
     */
    public ServiceUse getServiceUse(int user_id, int service_id);
    
    
}

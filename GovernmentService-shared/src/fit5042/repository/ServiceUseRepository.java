/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.repository;

import fit5042.repository.entities.ServiceUse;
import fit5042.repository.entities.Worker;
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
    
}

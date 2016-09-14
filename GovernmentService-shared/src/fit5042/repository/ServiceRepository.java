/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.repository;

import fit5042.repository.entities.Service;
import java.util.List;
import javax.ejb.Remote;
/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Remote
public interface ServiceRepository
{
    /***
     * Search Service by service name
     * @param name service name
     * @return Service
     */
    public Service searchServiceByName(String name);
    
    /***
     * Search service by service no
     * @param no service no
     * @return Service
     */
    public Service searchServiceByNo(int no);
    
    /***
     * Search service by service type
     * @param type
     * @return 
     */
    public Service searchServiceByType(String type);
    
    /***
     * Get all service
     * @return Service list
     */
    public List<Service> getAllServices();
}

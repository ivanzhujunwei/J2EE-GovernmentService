/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.repository;

import fit5042.repository.entities.Service;
import fit5042.repository.entities.ServiceType;
import java.util.List;
import javax.ejb.Remote;
/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@Remote
public interface ServiceRepository
{
    /***
     * Search Service by service name
     * @param name service name
     * @return Service List
     */
    public List<Service> searchServiceByName(String name);
    
    /***
     * Search service by service no
     * @param no service no
     * @return Service
     */
    public Service searchServiceByNo(int no);
    
    /***
     * Search service combined by name, no, type, description
     * @param no Service NO
     * @param name Service name
     * @param type Service type
     * @param description Service description
     * @return Service list
     */
    public List<Service> searchServiceCombined(String no, String name, String type, String description);
    
    /***
     * Search service by service type
     * @param type
     * @return service list 
     */
    public List<Service> searchServiceByType(String type);
    
    /***
     * Get all service
     * @return Service list
     */
    public List<Service> getAllServices();
    
    /***
     * Update a service
     * @param service the service which needs to be updated
     */
    public void updateService(Service service);
    
    /***
     * Delete a service
     * @param service the service which needs to be deleted
     */
    public void deleteService(Service service);
    
    /***
     * Add a service
     * @param service 
     */
    public void addService(Service service);
    
    /***
     * Get all service types
     * @return Service types
     */
    public List<ServiceType> getAllServiceType();
    
    
}

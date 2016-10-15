/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.repository;

import fit5042.repository.entities.ServiceUse;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@Stateless
public class JPAServiceUseRepositoryImpl implements ServiceUseRepository
{

    @PersistenceContext(unitName = "GovernmentService-ejbPU")
    private EntityManager entityManager;
    
    public static List<ServiceUse> serviceUseList;
    
    @PostConstruct
    public void init(){
        // If I want to load all data into server when the 
        
    }

    @Override
    public void addServiceUse(ServiceUse serviceUse)
    {
        entityManager.persist(serviceUse);
    }

    @Override
    public List<ServiceUse> getAllServiceUse()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

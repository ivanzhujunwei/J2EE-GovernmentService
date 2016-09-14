/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fit5042.repository;

import fit5042.repository.entities.Service;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Stateless
public class JPAServicePRepositoryImpl implements ServiceRepository{
    

    @Override
    public List<Service> getAllServices()
    {
        List<Service> services = new ArrayList<>();
        Service s0 = new Service(0, "s1", "t1", "thu1", "desc1");
        Service s1 = new Service(1, "s2", "t2", "thu2", "desc2");
        services.add(s0);
        services.add(s1);
        return services;
    }

    @Override
    public Service searchServiceByName(String name)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Service searchServiceByNo(int no)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Service searchServiceByType(String type)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

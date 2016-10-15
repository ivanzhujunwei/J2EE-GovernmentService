/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.repository;

import fit5042.repository.entities.Service;
import fit5042.repository.entities.ServiceType;
import fit5042.utility.Validate;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Ivan Zhu 
 */
@Stateless
public class JPAServicePRepositoryImpl implements ServiceRepository
{

    @PersistenceContext(unitName = "GovernmentService-ejbPU")
    private EntityManager entityManager;

    @Override
    public void addService(Service service)
    {
        entityManager.persist(service);
    }

    @Override
    public void deleteService(Service service)
    {
        // Need to get the service from entityManager first
        Service s = entityManager.getReference(Service.class, service.getService_no());
        // After finding the service, then it can be deleted
        entityManager.remove(s);
    }

    @Override
    public List<ServiceType> getAllServiceType()
    {
         // JPQL
        List<ServiceType> types = entityManager.createNamedQuery(ServiceType.GET_ALL_SERVICE_TYPE).getResultList();
        return types;
    }

    @Override
    public List<Service> getAllServices()
    {
        // JPQL
        List<Service> services = entityManager.createNamedQuery(Service.GET_ALL_QUERY_NAME).getResultList();
        return services;
    }

    @Override
    public List<Service> searchServiceByName(String name)
    {
        // Criteria API
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Service> query = builder.createQuery(Service.class);
        Root<Service> s = query.from(Service.class);
        query.select(s).where(builder.like(s.get("name").as(String.class), "%" + name + "%"));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Service searchServiceByNo(int no)
    {
        Service service = entityManager.find(Service.class, no);
        return service;
    }

    @Override
    public List<Service> searchServiceByType(String type)
    {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Service> query = builder.createQuery(Service.class);
        Root<Service> s = query.from(Service.class);
        query.select(s).where(builder.like(s.get("type").as(String.class), "%" + type + "%"));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Service> searchServiceCombined(String no, String name, String type, String description)
    {
        List<Service> services = new ArrayList<>();
        // Control service no is not null
        if (!Validate.isEmpty(no)){
            int service_no = Integer.parseInt(no);
            Service s =  searchServiceByNo(service_no);
            if(s != null){
                services.add(s);
            }
        }else{
            Query q = entityManager.createNamedQuery(Service.GET_SEARCHED_QUERY);
            q.setParameter("service_name", "%"+name+"%");
            q.setParameter("service_type", "%"+type+"%");
            q.setParameter("service_description", "%"+description+"%");
            services = q.getResultList();
        }
        return services;
    }

    @Override
    public void updateService(Service service)
    {
        entityManager.merge(service);
    }

}

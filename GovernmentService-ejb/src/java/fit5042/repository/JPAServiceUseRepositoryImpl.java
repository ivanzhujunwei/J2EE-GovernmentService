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
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

    @Override
    public void deleteServiceUse(ServiceUse su)
    {
        // Need to get the service from entityManager first
        ServiceUse s = entityManager.getReference(ServiceUse.class, su.getUseId());
        // After finding the service, then it can be deleted
        entityManager.remove(s);
    }

    @Override
    public ServiceUse getServiceUse(int user_id, int service_id)
    {
        // JPQL
        Query q = entityManager.createNamedQuery(ServiceUse.GET_UNCOMPLETED_SERVICEUSE);
        q.setParameter("user_id", entityManager.find(PublicUser.class, user_id));
        q.setParameter("service_no", entityManager.find(Service.class, service_id));
        List<ServiceUse> sus = q.getResultList();
        if (sus.size() == 0) {
            return null;
        }
        return sus.get(0);
    }

    @Override
    public List<ServiceUse> getServiceUsesByService(Service service)
    {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ServiceUse> query = builder.createQuery(ServiceUse.class);
        Root<ServiceUse> s = query.from(ServiceUse.class);
        query.select(s).where(builder.equal(s.get("usedService").as(Service.class), service));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void updateServiceUse(ServiceUse su)
    {
        entityManager.merge(su);
    }

    @Override
    public List<ServiceUse> getServiceUseByUser(PublicUser pu)
    {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ServiceUse> query = builder.createQuery(ServiceUse.class);
        Root<ServiceUse> s = query.from(ServiceUse.class);
        query.select(s).where(builder.equal(s.get("publicUser").as(PublicUser.class), pu));
        return entityManager.createQuery(query).getResultList();
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

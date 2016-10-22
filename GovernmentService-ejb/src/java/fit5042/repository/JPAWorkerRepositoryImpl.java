/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.repository;

import fit5042.repository.entities.PublicUser;
import fit5042.repository.entities.Worker;
import fit5042.utility.Constant;
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
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@Stateless
public class JPAWorkerRepositoryImpl implements WorkerRepository
{

    @PersistenceContext(unitName = "GovernmentService-ejbPU")
    private EntityManager entityManager;

    @Override
    public List<Worker> getAllWorkers()
    {
        // JPQL
        List<Worker> workers = entityManager.createNamedQuery(Worker.GET_ALL_Worker).getResultList();
        return workers;
    }

    @Override
    public int getAssignedWorkerId()
    {
        // JPQL
        System.out.println(Worker.GET_ASSIGNED_WORKERID);
        String getAssignedWorkerId = "SELECT COUNT(wo.USER_ID),wo.USER_ID,wo.LASTNAME from worker wo "
                + "left join ServiceUse w   on wo.USER_ID = w.MANAGED_BY "
                + "GROUP BY wo.USER_ID,wo.LASTNAME ORDER BY COUNT(wo.USER_ID) asc";
        Query q = entityManager.createNativeQuery(getAssignedWorkerId);
        List<Object> ss = q.getResultList();
//        List workerObj = entityManager.createNamedQuery(Worker.GET_ASSIGNED_WORKERID).getResultList();
        if (ss.size() > 0) {
//            return workerObj.get(0)
            Object[] workerIds = (Object[]) ss.get(0);
            return (int) workerIds[1];
            //serverError: class javax.ejb.EJBException java.lang.Long cannot be cast to java.lang.Integer
//            return workerIds.get(0).intValue();
        } else {
            // Assign the first worker: Ivan Zhu
            return Constant.DEFAULT_WORKER_ID;
        }

    }

    @Override
    public Worker searchWorkerByEmail(String email)
    {
        // Criteria API
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Worker> query = builder.createQuery(Worker.class);
        Root<Worker> s = query.from(Worker.class);
        query.select(s).where(builder.like(s.get("email").as(String.class), "%" + email + "%"));
        List<Worker> usrs = entityManager.createQuery(query).getResultList();
        if (usrs.size() > 0) {
            return usrs.get(0);
        } else {
            return null;
        }
    }

}

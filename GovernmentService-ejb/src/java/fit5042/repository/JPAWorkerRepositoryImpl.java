/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.repository;

import fit5042.repository.entities.Worker;
import fit5042.utility.Constant;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        List<Integer> workerIds = entityManager.createNamedQuery(Worker.GET_ASSIGNED_WORKERID).getResultList();
        if (workerIds.size() > 0) {
            return 1;
            //serverError: class javax.ejb.EJBException java.lang.Long cannot be cast to java.lang.Integer
//            return workerIds.get(0).intValue();
        }else{
            // Assign the first worker: Ivan Zhu
            return Constant.DEFAULT_WORKER_ID;
        }

    }

}

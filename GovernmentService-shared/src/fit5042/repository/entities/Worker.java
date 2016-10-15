/*
 * Worker user entity.
 */
package fit5042.repository.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Worker.GET_ALL_Worker, query = "SELECT s FROM Worker s"),
    @NamedQuery(name = Worker.GET_ASSIGNED_WORKERID, query = 
            "SELECT COUNT(w.governmentWorker) from ServiceUse w GROUP BY w.governmentWorker ORDER BY COUNT(w.governmentWorker) asc")
})
public class Worker extends SystemUser
{

    // Get all workers
    public final static String GET_ALL_Worker = "Worker.getAllWorkers";
    // Assign a worker for the service which is going to be used by a public
    public static final String GET_ASSIGNED_WORKERID = "ServiceUse.getAssignedWorker";

    public Worker()
    {
        this.user_type = "worker";
    }

    // ServiceUse those are assigned to the worker
//    private List<ServiceUse> assignedServiceUses;
//
//    public List<ServiceUse> getAssignedServiceUses()
//    {
//        return assignedServiceUses;
//    }
//
//    public void setAssignedServiceUses(List<ServiceUse> assignedServiceUses)
//    {
//        this.assignedServiceUses = assignedServiceUses;
//    }
}

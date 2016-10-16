/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.repository;

import fit5042.repository.entities.PublicUser;
import fit5042.repository.entities.Worker;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@Remote
public interface WorkerRepository
{
    /****
     * Get all workers
     * @return All workers
     */
    public  List<Worker> getAllWorkers();
    
    /***
     * Get the worker id who has smallest number of service use record
     * @return Assigned worker id
     */
    public int getAssignedWorkerId();
    
    /***
     * Search worker by email
     * @param email user's email
     * @return Worker 
     */
    public Worker searchWorkerByEmail(String email);
}

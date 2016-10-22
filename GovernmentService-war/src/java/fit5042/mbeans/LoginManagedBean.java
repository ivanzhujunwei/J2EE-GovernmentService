/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.mbeans;

import fit5042.repository.PublicUserRepository;
import fit5042.repository.ServiceUseRepository;
import fit5042.repository.WorkerRepository;
import fit5042.repository.entities.PublicUser;
import fit5042.repository.entities.ServiceUse;
import fit5042.repository.entities.Worker;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * This managedBean is responsible for users who has already logged in # Get
 * their personal information # Redirect to different page according to
 * different roles
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginManagedBean
{

    @EJB
    PublicUserRepository publicUserRepository;

    @EJB
    WorkerRepository workerRepository;

    @EJB
    ServiceUseRepository serviceUseRepository;

    // Logged in public user
    private PublicUser loggedInPublicUser;

    // Logged in worker
    private Worker loggedInWorker;

    public LoginManagedBean()
    {
        loggedInPublicUser = new PublicUser();
        loggedInWorker = new Worker();
    }

    /**
     * *
     * Once user logged in, redirect immediately based on the different role
     *
     * @return
     */
    public String redirect()
    {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String username = request.getRemoteUser();
        // Get the information of public user logged in
        loggedInPublicUser = publicUserRepository.searchPublicUserByEmail(username);
        if (loggedInPublicUser != null) {
            return "publicUser/index.xhtml";
        }
        // Get the information of worker logged in
        loggedInWorker = workerRepository.searchWorkerByEmail(username);
        if (loggedInWorker != null) {
            return "worker/worker_publicUsers.xhtml";
        }
        return "error";
    }

    /**
     * *
     * Get current service use records
     *
     * @return Service use list
     */
    public List<ServiceUse> getCurrentServiceUse()
    {
        System.out.println("fit5042.mbeans.LoginManagedBean.getCurrentServiceUse()");
        List<ServiceUse> sus = new ArrayList<>();
        for (ServiceUse su : getAllServiceUse()) {
            if (!su.isIsFinished()) {
                sus.add(su);
            }
        }
        return sus;
    }

    /**
     * *
     * Get completed service use records
     *
     * @return Service use list
     */
    public List<ServiceUse> getFinishedServiceUsesByPublic()
    {
        List<ServiceUse> sus = new ArrayList<>();
        for (ServiceUse su : getAllServiceUse()) {
            if (su.isIsFinished()) {
                sus.add(su);
            }
        }
        return sus;
    }

    /***
     * Get all service use
     * @return 
     */
    public List<ServiceUse> getAllServiceUse()
    {
        return serviceUseRepository.getServiceUseByUser(loggedInPublicUser);
    }

    public PublicUser getLoggedInPublicUser()
    {
        return loggedInPublicUser;
    }

    public void setLoggedInPublicUser(PublicUser loggedInPublicUser)
    {
        this.loggedInPublicUser = loggedInPublicUser;
    }

    public Worker getLoggedInWorker()
    {
        return loggedInWorker;
    }

    public void setLoggedInWorker(Worker loggedInWorker)
    {
        this.loggedInWorker = loggedInWorker;
    }

   

}

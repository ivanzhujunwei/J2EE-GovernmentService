/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.mbeans;

import fit5042.repository.PublicUserRepository;
import fit5042.repository.WorkerRepository;
import fit5042.repository.entities.PublicUser;
import fit5042.repository.entities.Worker;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
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

    public String redirect()
    {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String username = request.getRemoteUser();
        PublicUser user = publicUserRepository.searchPublicUserByEmail(username);
        if (user != null){
            return "publicUser/index.xhtml";
        }
        Worker worker = workerRepository.searchWorkerByEmail(username);
        if ( worker != null){
            return "worker/worker_publicUsers.xhtml";
        }
        return "error";
    }
    
}

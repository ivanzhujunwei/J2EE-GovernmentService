/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.mbeans;

import fit5042.repository.PublicUserRepository;
import fit5042.repository.entities.PublicUser;
import fit5042.repository.entities.Service;
import fit5042.utility.Constant;
import fit5042.utility.Validate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@ManagedBean(name = "publicUserBean")
@SessionScoped
//@ViewScoped
public class PublicUserManagedBean
{

    @EJB
    PublicUserRepository publicUserRepository;

    // The variable to judge if this request is showing all public users search page  by worker
    private boolean isShowAll;

    private String userName;

    private String password;

    // Login result after use attempts to login
    private String loginResponse;

    // Search ID number in search page
    private String searchID;
    // Search first name in search page
    private String searchLastName;
    // Search first name in search page
    private String searchFirstName;
    // Search email in search page
    private String searchEmail;

    // The public user the worker is managing
    private PublicUser user;

    public PublicUserManagedBean()
    {
        user = new PublicUser();
        isShowAll = true;
        searchID = "";
        searchLastName = "";
        searchFirstName = "";
        searchEmail = "";
    }

    /**
     * *
     * Public user login
     *
     * @param userName
     * @param password
     * @return true if userName and password are both correct
     */
    public String login() throws IOException
    {
        // THIS IS AJAX HERE, NEED VIEWSCOPE 
        if (Validate.isEmpty(userName)) {
            setLoginResponse("ddd");
            return "login";
        }
        for (PublicUser pu : publicUserRepository.getAllPublicUser()) {
            System.out.println("333");
            if (userName.equals(pu.getEmail()) && password.equals(pu.getPassword())) {
                // Load user's data 

                this.loginResponse = "";
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                context.redirect(context.getRequestContextPath() + "/faces/publicUser/index.xhtml");
//                return "publicUser/index";
            }
        }
        setLoginResponse("Login failed");
        return "login";
    }

    /**
     * *
     * Search public user, set isShowAll false
     *
     */
    public void getSearchedServices()
    {
        isShowAll = false;
    }

    /**
     * *
     * Show All public users
     */
    public void getShowAllPublicUser()
    {
        isShowAll = true;
    }

    /**
     * *
     * Get public users according isShowAll attribute
     *
     * @return
     */
    public List<PublicUser> getAllPublicUsers()
    {
        List<PublicUser> users = new ArrayList<>();
        // Show all users when the page is initialised
        if (isShowAll) {
            users = getPublicUserList();
        } else {
            users = publicUserRepository.getSearchPublicUserCombined(searchID, searchLastName, searchFirstName, searchEmail);
        }
        return users;
    }

    /**
     * *
     * Get all public users
     *
     * @return PublicUser list
     */
    public List<PublicUser> getPublicUserList()
    {
        try {
            return publicUserRepository.getAllPublicUser();
        } catch (Exception ex) {
            Logger.getLogger(ServiceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /***
     * Go to adding public user page
     * @return Adding public user page
     */
    public String addingPublicUser()
    {
        this.user = new PublicUser();
        int id = publicUserRepository.getNewPublicUserId();
        this.user.setId(id);
        
        return "worker_publicUser_add";
    }
    
    /***
     * Add a user 
     * @return User manage home page
     */
    public String addPublicUser(){
        publicUserRepository.addPublicUser(user);
        return "worker_publicUsers";
    }
    
    public String updatePublicUser(){
        publicUserRepository.updatePublicUser(this.user);
        return "worker_publicUsers";
    }
    
    /***
     * Get the public user detail
     * @param p Public user
     * @return  public user detail page
     */
    public String getViewedUser(PublicUser p){
        this.user = p;
        return "worker_publicUser_detail";
    }

    /***
     * Get to the updating user page
     * @param p Public user
     * @return public user update page
     */
    public String getUpdatingUser(PublicUser p){
        this.user = p;
        return "worker_publicUser_update";
    }
    
    /***
     * Delete a user 
     * @param p The public user needed to be deleted
     * @return 
     */
    public String deleteUser(PublicUser p){
        publicUserRepository.deletePublicUser(p);
        return "worker_publicUsers";
    }

    /***
     * Reset user's password
     */
    public void resetPassword(){
        this.user.setPassword(Constant.DEFAULT_PSD);
    }
    public String getUserName()
    {
        return userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getLoginResponse()
    {
        return loginResponse;
    }

    public void setLoginResponse(String loginResponse)
    {
        this.loginResponse = loginResponse;
    }

    public String getSearchID()
    {
        return searchID;
    }

    public String getSearchLastName()
    {
        return searchLastName;
    }

    public String getSearchFirstName()
    {
        return searchFirstName;
    }

    public String getSearchEmail()
    {
        return searchEmail;
    }

    public void setSearchID(String searchID)
    {
        this.searchID = searchID;
    }

    public void setSearchLastName(String searchLastName)
    {
        this.searchLastName = searchLastName;
    }

    public void setSearchFirstName(String searchFirstName)
    {
        this.searchFirstName = searchFirstName;
    }

    public void setSearchEmail(String searchEmail)
    {
        this.searchEmail = searchEmail;
    }

    public PublicUser getUser()
    {
        return user;
    }

    public void setUser(PublicUser user)
    {
        this.user = user;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.mbeans;

import fit5042.repository.PublicUserRepository;
import fit5042.repository.ServiceUseRepository;
import fit5042.repository.entities.PublicUser;
import fit5042.repository.entities.ServiceUse;
import fit5042.utility.Constant;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@ManagedBean(name = "publicUserBean")
@SessionScoped
public class PublicUserManagedBean 
{

    @EJB
    PublicUserRepository publicUserRepository;
    
    @EJB
    ServiceUseRepository serviceUseRepository;

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
    
    // Search active and inactive public user
    public String activeSelect;

    public PublicUserManagedBean()
    {
        user = new PublicUser();
        isShowAll = true;
        searchID = "";
        searchLastName = "";
        searchFirstName = "";
        searchEmail = "";
        activeSelect = "1";
    }

    
    /**
     * *
     * Public user login
     *
     * @param userName
     * @param password
     * @return true if userName and password are both correct
     */
//    public String login() throws IOException
//    {
//        // THIS IS AJAX HERE, NEED VIEWSCOPE 
//        if (Validate.isEmpty(userName)) {
//            setLoginResponse("ddd");
//            return "login";
//        }
//        for (PublicUser pu : publicUserRepository.getAllPublicUser()) {
//            if (userName.equals(pu.getEmail()) && password.equals(pu.getPassword())) {
//                // Load user's data 
//                this.loginResponse = "";
//                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//                context.redirect(context.getRequestContextPath() + "/faces/publicUser/index.xhtml");
//            }
//        }
//        setLoginResponse("Login failed");
//        return "login";
//    }
    
//    public String loginJASS(){
//        return "worker/worker_publicUsers";
//    }

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
        activeSelect = "1";
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
            boolean isActive = activeSelect.equals("1");
            users = publicUserRepository.getSearchPublicUserCombined(searchID, searchLastName, searchFirstName, searchEmail, isActive);
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
            user.setPassword(SHA(user.getPassword()));
            user.setUser_type("public");
            publicUserRepository.addPublicUser(user);
        return "worker_publicUsers";
    }
    
    // reference: blog.csdn.net/joyous/article/details/49898383
    private String SHA(final String strText)
    {
        // 返回值  
        String strResult = null;
        // 是否是有效字符串  
        if (strText != null && strText.length() > 0) {
            try {
                // SHA 加密开始  
                // 创建加密对象 并傳入加密類型  
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                // 传入要加密的字符串  
                messageDigest.update(strText.getBytes());
                // 得到 byte 類型结果  
                byte byteBuffer[] = messageDigest.digest();
                // 將 byte 轉換爲 string  
                StringBuffer strHexString = new StringBuffer();
                // 遍歷 byte buffer  
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回結果  
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
  
    return strResult;  
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
        // delete all his/her service use records
        for (ServiceUse su :serviceUseRepository.getServiceUseByUser(p)){
            serviceUseRepository.deleteServiceUse(su);
        }
        // delete the public user
        publicUserRepository.deletePublicUser(p);
        return "worker_publicUsers";
    }
    
    public String activePublicUser(PublicUser p){
        p.setIsActive(true);
        publicUserRepository.updatePublicUser(p);
        return "worker_publicUsers";
    }
    
    public String inactivePublicUser(PublicUser p){
        // set service status
        p.setIsActive(false);
        // status of related service use remain
        publicUserRepository.updatePublicUser(p);
        return "worker_publicUsers";
    }

    /***
     * Reset user's password
     */
    public void resetPassword(){
        this.user.setPassword(SHA(Constant.DEFAULT_PSD));
        publicUserRepository.updatePublicUser(user);
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
        return serviceUseRepository.getServiceUseByUser(user);
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

    public String getActiveSelect()
    {
        return activeSelect;
    }

    public void setActiveSelect(String activeSelect)
    {
        this.activeSelect = activeSelect;
    }

}

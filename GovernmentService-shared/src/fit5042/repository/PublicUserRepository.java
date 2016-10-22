/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.repository;

import fit5042.repository.entities.PublicUser;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@Remote
public interface PublicUserRepository
{
    public List<PublicUser> getAllPublicUser();
    
    /***
     * Get searched public Users
     * @param id ID number
     * @param lastName user's last name 
     * @param firstName user's first name
     * @param email user's email
     * @param isActive this public user is active or inactive
     * @return 
     */
    public List<PublicUser> getSearchPublicUserCombined(String id, String lastName, String firstName, String email, boolean isActive);
    
    /***
     * Search public user by user id
     * @param id User id
     * @return Public user
     */
    public PublicUser searchPublicUserByID(int id);
    
    /***
     * Add a public user
     * @param pu Public user
     */
    public void addPublicUser(PublicUser pu);
    
    /***
     * Delete a public user
     * @param pu Public user
     */
    public void deletePublicUser(PublicUser pu);
    
    /***
     * Update a public user data
     * @param pu Public user
     */
    public void updatePublicUser(PublicUser pu);
    
    /***
     * Get a public user id
     * @return New public user id
     */
    public int getNewPublicUserId();
    
    /***
     * Search public user by email
     * @param email user's email
     * @return public user 
     */
    public PublicUser searchPublicUserByEmail(String email);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.repository;

import fit5042.repository.entities.PublicUser;
import fit5042.repository.entities.Service;
import fit5042.utility.Validate;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@Stateless
public class JPAPublicUserRepositoryImpl implements PublicUserRepository
{

    @PersistenceContext(unitName = "GovernmentService-ejbPU")
    private EntityManager entityManager;

    @Override
    public void addPublicUser(PublicUser pu)
    {
        entityManager.persist(pu);
    }

    @Override
    public void deletePublicUser(PublicUser pu)
    {
        // Need to get the user from entityManager first
        PublicUser user = entityManager.getReference(PublicUser.class, pu.getId());
        // After finding the user, then it can be deleted
        entityManager.remove(user);
    }

    @Override
    public List<PublicUser> getAllPublicUser()
    {
        // JPQL
        List<PublicUser> publicUsers = entityManager.createNamedQuery(PublicUser.GET_ALL_QUERY_NAME).getResultList();
        return publicUsers;
    }

    @Override
    public int getNewPublicUserId()
    {
        List<Integer> maxId = entityManager.createNamedQuery(PublicUser.GET_NEW_PUBLICUSER_ID).getResultList();
        if(maxId.size()>0){
            int max = maxId.get(0);
            return max+1;
        }else{
            return 1;
        }
    }

    @Override
    public List<PublicUser> getSearchPublicUserCombined(String id, String lastName, String firstName, String email)
    {
        List<PublicUser> users = new ArrayList<>();
        if (!Validate.isEmpty(id)) {
            int user_id = Integer.parseInt(id);
            PublicUser pu = searchPublicUserByID(user_id);
            if(pu != null){
                users.add(pu);
            }
        }else{
            Query q = entityManager.createNamedQuery(PublicUser.GET_SEARCHED_QUERY);
            q.setParameter("lastname", "%"+lastName+"%");
            q.setParameter("firstname", "%"+firstName+"%");
            q.setParameter("email", "%"+email+"%");
            users = q.getResultList();
        }
        return users;
    }

    @Override
    public PublicUser searchPublicUserByID(int id)
    {
        return entityManager.find(PublicUser.class, id);
    }

    @Override
    public void updatePublicUser(PublicUser pu)
    {
        entityManager.merge(pu);
    }

}

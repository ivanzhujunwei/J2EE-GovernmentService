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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
        Query q = entityManager.createNamedQuery(PublicUser.GET_ALL_QUERY_NAME);
        q.setParameter("isActive", true);
        List<PublicUser> publicUsers = q.getResultList();
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
    public List<PublicUser> getSearchPublicUserCombined(String id, String lastName, String firstName, String email, boolean isActive)
    {
        List<PublicUser> users = new ArrayList<>();
        if (!Validate.isEmpty(id)) {
            if (!Validate.isDigit(id)){
                return users;
            }
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
            q.setParameter("isActive", isActive);
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
    public PublicUser searchPublicUserByEmail(String email)
    {
        // Criteria API
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PublicUser> query = builder.createQuery(PublicUser.class);
        Root<PublicUser> s = query.from(PublicUser.class);
        query.select(s).where(builder.like(s.get("email").as(String.class), "%" + email + "%"));
        List<PublicUser> usrs = entityManager.createQuery(query).getResultList();
        if (usrs.size() >  0){
            return usrs.get(0);
        }else{
            return null;
        }
    }

    @Override
    public void updatePublicUser(PublicUser pu)
    {
        entityManager.merge(pu);
    }

}

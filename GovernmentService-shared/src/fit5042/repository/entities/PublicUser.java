/*
 * Public user entity.
 */
package fit5042.repository.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@Entity
@NamedQueries({
    @NamedQuery(name = PublicUser.GET_ALL_QUERY_NAME, query = "SELECT u FROM PublicUser u"),
    @NamedQuery(name = PublicUser.GET_SEARCHED_QUERY, query = "SELECT s FROM PublicUser s "
            + "WHERE s.lastName LIKE :lastname "
            + "AND s.firstName LIKE :firstname "
            + "AND s.email LIKE :email "),
    @NamedQuery(name = PublicUser.GET_NEW_PUBLICUSER_ID, query = "SELECT MAX(u.id) FROM PublicUser u")})
public class PublicUser extends SystemUser
{

    public static final String GET_ALL_QUERY_NAME = "PublicUser.getAllUsers";
    public static final String GET_SEARCHED_QUERY = "PublicUser.getSearchUsers";
    public static final String GET_NEW_PUBLICUSER_ID = "PublicUser.getNewPublicUserID";

    /**
     * *
     * The ServiceUses which have been used by the public user
     */
//    @OneToMany
//    private List<ServiceUse> serviceUse;
//
//    public PublicUser()
//    {
//        serviceUse = new ArrayList<>();
//    }
//
//    public List<ServiceUse> getServiceUse()
//    {
//        return serviceUse;
//    }
//
//    public void setServiceUse(List<ServiceUse> serviceUse)
//    {
//        this.serviceUse = serviceUse;
//    }

}

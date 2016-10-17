package fit5042.repository.entities;

import fit5042.repository.entities.ServiceUse;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-17T19:45:32")
@StaticMetamodel(Service.class)
public class Service_ { 

    public static volatile SingularAttribute<Service, String> thumbnail;
    public static volatile SingularAttribute<Service, String> name;
    public static volatile ListAttribute<Service, ServiceUse> serviceUseList;
    public static volatile SingularAttribute<Service, Integer> service_no;
    public static volatile SingularAttribute<Service, String> description;
    public static volatile SingularAttribute<Service, String> type;
    public static volatile SingularAttribute<Service, Boolean> isActive;

}
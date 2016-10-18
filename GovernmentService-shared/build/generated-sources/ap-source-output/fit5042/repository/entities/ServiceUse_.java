package fit5042.repository.entities;

import fit5042.repository.entities.PublicUser;
import fit5042.repository.entities.Service;
import fit5042.repository.entities.Worker;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-18T01:59:18")
@StaticMetamodel(ServiceUse.class)
public class ServiceUse_ { 

    public static volatile SingularAttribute<ServiceUse, PublicUser> publicUser;
    public static volatile SingularAttribute<ServiceUse, String> useDate;
    public static volatile SingularAttribute<ServiceUse, Integer> useId;
    public static volatile SingularAttribute<ServiceUse, Boolean> isFinished;
    public static volatile SingularAttribute<ServiceUse, Service> usedService;
    public static volatile SingularAttribute<ServiceUse, Worker> governmentWorker;

}
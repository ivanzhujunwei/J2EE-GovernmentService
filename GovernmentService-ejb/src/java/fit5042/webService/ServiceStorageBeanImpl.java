/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.webService;

import fit5042.repository.ServiceStorageBean;
import fit5042.repository.entities.Service;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@Singleton
public class ServiceStorageBeanImpl implements ServiceStorageBean
{

    List<Service> serviceTemplates;

//    private static final String INSURANCE_DESC = "Service of insurance, you can obtain several insurance services. \nProtect your most valuable assets with quality cover from a name you can trust. Our home and contents insurance offers peace of mind at an affordable price.";
//    private static final String CHILD_DESC = "Service of child care, you children would benefit a lot from the service. \nHelps parents and guardians easily collect and send important information about their child/children to police in an event of a disappearance or abduction. No data from this app is collected or stored by the police unless it is sent to them.";
//    private static final String EDUCATION_DESC = "Service of education, ";
    public ServiceStorageBeanImpl()
    {
        serviceTemplates = new ArrayList<>();
        serviceTemplates.add(new Service("Insurance", "Insurance service description template. Service of insurance, you can obtain several insurance services."));
        serviceTemplates.add(new Service("Childcare", "Childcare service description template. Service of child care, you children would benefit a lot from the service."));
        serviceTemplates.add(new Service("Education", "Education service description template. Service of education, you can find a situable certificate here."));
        serviceTemplates.add(new Service("Welfare", "Welfare service description template. Service of welfare, do what you want to help others."));
        serviceTemplates.add(new Service("Citizenship", "Citizenship service description template. Service of citizenship, some rights you may not know, explore here."));
    }

    public List<Service> getServiceTemplates()
    {
        return serviceTemplates;
    }

}

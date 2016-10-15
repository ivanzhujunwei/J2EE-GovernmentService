/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.mbeans;

import fit5042.repository.PublicUserRepository;
import fit5042.repository.ServiceRepository;
import fit5042.repository.ServiceUseRepository;
import fit5042.repository.WorkerRepository;
import fit5042.repository.entities.PublicUser;
import fit5042.repository.entities.Service;
import fit5042.repository.entities.ServiceType;
import fit5042.repository.entities.ServiceUse;
import fit5042.repository.entities.Worker;
import fit5042.utility.FileUtility;
import fit5042.utility.Validate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
@ManagedBean(name = "serviceManagedBean")
@SessionScoped
//@ViewScoped//How does it deal with Ajax?
public class ServiceManagedBean implements Serializable
{

    @EJB
    ServiceRepository serviceRepository;

    @EJB
    WorkerRepository workerRepository;

    @EJB
    ServiceUseRepository serviceUseRepository;

    @EJB
    PublicUserRepository publicUserRepository;

    // Search name in search page by public
    public String searchName;
    // Search NO in search page by public
    public String searchNO;
    // Search type in search page by public
    public String searchType;
    // Search description in search page by public
    public String searchDescription;

    // The variable to judge if this request is showing all services search page  by public
    private boolean isShowAll;
    // The service that is being processed 
    public Service service;
    // Service thumbnail 
    public Part thumbnail;

    /**
     * *
     * The response after public user using a service
     */
    public String isServiceUsed;

    /**
     * Creates a new instance of ServiceManagedBean
     */
    public ServiceManagedBean()
    {
        isShowAll = true;
        searchName = "";
        searchType = "";
        searchDescription = "";
        service = new Service();
        isServiceUsed = "";
    }

    ////////// Initialise data
    public List<Service> serviceList;
    public List<PublicUser> publicUserList;

//    @PostConstruct
    public void init()
    {
        System.out.println("### Start initializing...");
        System.out.println("#1  Service initializing...");
//        serviceList = getAllServices();
        System.out.println("#2  PublicUser initializing...");
//        publicUserList = publicUserRepository.getAllPublicUser();

    }

    /**
     * *
     * Get all services or services by searching criteria
     *
     * @return Service list
     */
    public List<Service> getServiceList()
    {
        List<Service> services = new ArrayList<>();
        // Show all services when the page is initialised
        if (isShowAll) {
            services = getAllServices();
        } else {
            services = serviceRepository.searchServiceCombined(searchNO, searchName, searchType, searchDescription);
        }
        return services;
    }

    /**
     * *
     * Get all services
     *
     * @return All services
     */
    public List<Service> getAllServices()
    {
        try {
            return serviceRepository.getAllServices();
        } catch (Exception ex) {
            Logger.getLogger(ServiceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * *
     * Show All services, redirect to index page
     *
     * @return index page
     */
    public String getShowAllService()
    {
        isShowAll = true;
        return "index";
    }

    /***
     * Get all service types
     * @return All service types
     */
    public List<String> getAllServiceType(){
        List<String> lst = new ArrayList<>();
        for(ServiceType st: serviceRepository.getAllServiceType()){
            lst.add(st.getTypeName());
        }
        return lst;
    }
    
    /**
     * *
     * Search services, redirect to index page
     *
     * @return index page
     */
    public String getSearchedServices()
    {
        isShowAll = false;
        return "index";
    }

    /**
     * *
     * View a service detail by public
     *
     * @param service
     * @return service detail page
     */
    public String getViewedService(Service service)
    {
        this.service = service;
//        return "service_detail.xhtml?sid="+service.getService_no()+"&faces-redirect=true";
        return "service_detail.xhtml";

    }

    /**
     * *
     * View a service detail by worker
     *
     * @param service
     * @return service detail page
     */
    public String viewServiceByWorker(Service service)
    {
        this.service = service;
        return "worker_service_detail.xhtml";
    }

    /**
     * *
     * Get service detail which is going to be updated
     *
     * @param service
     * @return service update page
     */
    public String getUpdatingService(Service service)
    {
        this.service = service;
        return "worker_service_update.xhtml";
    }

    public String getAddingService()
    {
        this.service = new Service();
        return "worker_service_add";
    }

    /**
     * *
     * Update the service
     *
     * @return Worker services page
     */
    public String updateService()
    {
        // If the thumnail has not been updated, skip the updating action
        if (this.thumbnail != null) {
            //Delete original thumbnail
            uploadThumbnail(this.service.getThumbnail());
        }
        serviceRepository.updateService(this.service);
        return "worker_services.xhtml";
    }

    /**
     * *
     * Add a service
     *
     * @return Worker services page
     */
    public String addService()
    {
        // Generate unique id for thumbnail 
        String uniqueID = UUID.randomUUID().toString();
        uniqueID += ".jpg";
        uploadThumbnail(uniqueID);
        this.service.setThumbnail(uniqueID);
        serviceRepository.addService(this.service);
        return "worker_services";
    }

    /**
     * *
     * Delete a service
     *
     * @param service
     * @return
     */
    public String deleteService(Service service)
    {
        // Delete thumbnail image
        File file = new File(FileUtility.THUMBNAIL_DIRECTORY + service.getThumbnail());
        if (!file.delete()) {
            return "error";
        }
        serviceRepository.deleteService(service);
        return "worker_services";
    }

    /**
     * *
     * Use a service
     *
     * @return Service detail page
     */
    public String useService()
    {
        // Simulate a public user
        PublicUser pu = new PublicUser();
        pu.setId(1);
        // Get the curren service's use list 
        // TODO: pu.getServiceUse() needs to be initialised firstly
        for (ServiceUse usedServices : pu.getServiceUse()) {
            if (usedServices.getUsedService().getService_no() == this.service.getService_no()) {
                return "You are already using the service.";
            }
        }
        ServiceUse su = new ServiceUse();
        pu.getServiceUse().add(su);
        // Get all service uses which are not done
        // When the page is load at first
        if (Validate.isEmpty(this.isServiceUsed)) {
            setIsServiceUsed("1");
            return "";
        }
        // If the public apply more than once, stop processing
//        if (this.isServiceUsed.equals("2")) {
//            return ".";
//        }
//        setIsServiceUsed("2");
        // Create a new serviceUse
        su.setUsedService(this.service);
        su.setIsFinished(false);
        su.setUsedService(searchServiceByNO(this.service.getService_no()));
        su.setPublicUser(pu);
        // Distribute a worker to deal with the transaction
        // The service will be assigned to the worker who has smallest number of services to deal with.
        su.setGovernmentWorker(getAssignedWorker());
        // Get current data
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        su.setUseDate(dateFormat.format(date));
        // Add the serviceUse
        serviceUseRepository.addServiceUse(su);
        // TODO 
        // ??? every time another page request the method, the previous response is still in the page
        return "Apply successfully";
    }

    /**
     * **
     * Get a worker who has smallest number of services he/she is dealing with.
     *
     * @return A worker who will be assigned for this service.
     */
    public Worker getAssignedWorker()
    {
        int workerId = workerRepository.getAssignedWorkerId();
        // Get all serviceUse
        for (Worker worker : workerRepository.getAllWorkers()) {
            if (worker.getId() == workerId) {
                return worker;
            }
        }
        // If no worker has been found, return a empty worker
        Worker w = new Worker();
        w.setLastName("Worker does not exist");
        return w;
    }

    /**
     * *
     * Upload and write the service thumbnail to server
     *
     * @param uuid unique id
     */
    public void uploadThumbnail(String uuid)
    {
        try {
            InputStream inputStream = thumbnail.getInputStream();
//            FileOutputStream outputStream = new FileOutputStream(FileUtility.THUMBNAIL_DIRECTORY + FileUtility.getFileName(thumbnail));
            FileOutputStream outputStream = new FileOutputStream(FileUtility.THUMBNAIL_DIRECTORY + uuid);
            byte[] buffer = new byte[4096];
            int bytesRead = 0;
            while (true) {
                bytesRead = inputStream.read(buffer);
                if (bytesRead > 0) {
                    outputStream.write(buffer, 0, bytesRead);
                } else {
                    break;
                }
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ServiceManagedBean.class.getName()).log(Level.SEVERE, "Failed to upload thumbnail", ex);
        }
    }

    public void validateImgType(FacesContext ctxContext, UIComponent compt, Object value)
    {
        System.out.println("fit5042.mbeans.ServiceManagedBean.validateImgType()");
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        Part file = (Part) value;
        if (!msgs.isEmpty()) {
            msgs.add(new FacesMessage("Emtpy .."));
        }
    }

    /**
     * *
     * Search a service by NO
     *
     * @param no ServiceNO
     * @return Service
     */
    public Service searchServiceByNO(int no)
    {
        return serviceRepository.searchServiceByNo(no);
    }

    /**
     * *
     * Search Service by service name
     *
     * @param name Service name
     * @return Service List
     */
    public List<Service> searchServiceByName(String name)
    {
        return serviceRepository.searchServiceByName(name);
    }

    /**
     * *
     * Search service by service type
     *
     * @param type
     * @return service list
     */
    public List<Service> searchServiceByType(String type)
    {
        return serviceRepository.searchServiceByType(type);
    }

    public Service getService()
    {
        return service;
    }

    public void setService(Service service)
    {
        this.service = service;
    }

    public Part getThumbnail()
    {
        return thumbnail;
    }

    public void setThumbnail(Part thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    public String getIsServiceUsed()
    {
        return isServiceUsed;
    }

    public void setIsServiceUsed(String isServiceUsed)
    {
        this.isServiceUsed = isServiceUsed;
    }

    public String getSearchName()
    {
        return searchName;
    }

    public String getSearchType()
    {
        return searchType;
    }

    public String getSearchDescription()
    {
        return searchDescription;
    }

    public void setSearchName(String searchName)
    {
        this.searchName = searchName;
    }

    public String getSearchNO()
    {
        return searchNO;
    }

    public void setSearchNO(String searchNO)
    {
        this.searchNO = searchNO;
    }

    public void setSearchType(String searchType)
    {
        this.searchType = searchType;
    }

    public void setSearchDescription(String searchDescription)
    {
        this.searchDescription = searchDescription;
    }

}

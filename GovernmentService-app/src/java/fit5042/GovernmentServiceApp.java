/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fit5042;

import fit5042.gui.GovernmentServiceGUI;
import fit5042.gui.GovernmentServiceGUIImpl;
import fit5042.repository.ServiceRepository;
import fit5042.repository.entities.Service;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.ejb.EJB;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class GovernmentServiceApp implements ActionListener, ListSelectionListener{
    
    @EJB
    private static ServiceRepository serviceRepository;
    
    private GovernmentServiceGUI gui;
    
    public GovernmentServiceApp()
    {       
        gui = new GovernmentServiceGUIImpl(this, this);    
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        try
        {
            if (ae.getSource() == gui.getSearchButton())
            {
                this.searchService();
            }else if (ae.getSource() == gui.getViewButton()){
                displayAllService();
            }
        }
        catch (Exception ex)
        {
            gui.displayMessageInDialog(ex.getMessage());
        }
    }
    
    private void displayAllService(){
        try {
            List<Service> services = serviceRepository.getAllServices();
            if (services != null) {
                this.gui.displayAllServices(services);
            }
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to retrieve services: " + ex.getMessage());
        }
    }

    public GovernmentServiceApp(GovernmentServiceGUI gui)
    {
        this.gui = gui;
    }
   
    /***
     * 
     */
    private void searchService(){
        switch(gui.getSelectedSearchType()){
            case GovernmentServiceGUIImpl.SEARCH_TYPE_NO:
                searchServiceByNO(gui.getServiceNo());
                break;
            case GovernmentServiceGUIImpl.SEARCH_TYPE_NAME:
                searchServiceByName(gui.getServiceName());
                break;
            case GovernmentServiceGUIImpl.SEARCH_TYPE_TYPE:
                searchServiceByType(gui.getServiceType());
                break;
            default:
                break;
        }
    }
    
    /***
     * Search a service by Service NO
     */
    private void searchServiceByNO(int service_no){
        try {
            Service service = serviceRepository.searchServiceByNo(service_no);
//            Property property = propertyRepository.searchPropertyById(id);
            if (service != null) {
                this.gui.displayService(service);
            } else {
                this.gui.displayMessageInDialog("No matched properties found");
                this.gui.clearServiceTable();
            }  
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search service by NO: " + ex.getMessage());
            this.gui.clearServiceTable();
        } finally {
            this.gui.clearInput();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GovernmentServiceApp governmentServiceApp = new GovernmentServiceApp();
    }

    /***
     * Search service by Name
     */
    private void searchServiceByName(String serviceName)
    {
        try {
            List<Service> services = serviceRepository.searchServiceByName(serviceName);
//            Property property = propertyRepository.searchPropertyById(id);
            if (services.size() > 0) {
                this.gui.displayAllServices(services);
            } else {
                this.gui.displayMessageInDialog("No matched properties found");
                this.gui.clearServiceTable();
            }  
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search service by Name: " + ex.getMessage());
            this.gui.clearServiceTable();
        } finally {
            this.gui.clearInput();
        }
    }

    /***
     * Search service by Type
     */
    private void searchServiceByType(String type)
    {
        try {
            List<Service> services = serviceRepository.searchServiceByType(type);
//            Property property = propertyRepository.searchPropertyById(id);
            if (services.size() > 0) {
                this.gui.displayAllServices(services);
            } else {
                this.gui.displayMessageInDialog("No matched properties found");
                this.gui.clearServiceTable();
            }  
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search service by Type: " + ex.getMessage());
            this.gui.clearServiceTable();
        } finally {
            this.gui.clearInput();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent event)
    {
        if ((event.getSource() == this.gui.getServiceTable().getSelectionModel())
            && (! event.getValueIsAdjusting()))
        {
            try
            {
                if (this.gui.isServiceSelected()) {
                    int serviceNO = this.gui.getSelectedServiceNO();
                    Service service = serviceRepository.searchServiceByNo(serviceNO);
//                    this.gui.displaySelectedPropertyDetails(property);
                    this.gui.displaySelectedServiceDetails(service);
                }               
            }
            catch (Exception e)
            {
                gui.displayMessageInDialog(e.getMessage());
            }
        }
    }
}
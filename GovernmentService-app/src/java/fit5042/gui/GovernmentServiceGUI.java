/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.gui;

import fit5042.repository.entities.Service;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface GovernmentServiceGUI
{
    /**
     * Display service detail information
     * @param services list of service
     */
    public void displayAllServices(List<Service> services);

    public void displayService(Service service);
    
    /***
     * Return the View Service button
     * @return the attribute viewButton
     */
    public JButton getViewButton();
    
    /**
     * Return the Search Service button
     *
     * @return the attribute searchButton
     */
    public JButton getSearchButton();
    
    /***
     * Clear service table content
     */
    public void clearServiceTable();
    
    /***
     * Clear input
     */
    public void clearInput();
    
    /***
     * Clear text fields
     */
    public void clearTextFields();
    
    /**
     * Display a message in a dialog box
     *
     * @param message - the message to display
     */
    public void displayMessageInDialog(String message);
    
    /***
     * Return the service no the user has entered
     * @return service no
     */
    public int getServiceNo();
    
     /***
     * Return the service name the user has entered
     * @return service name
     */
    public String getServiceName();
    
     /***
     * Return the service type the user has entered
     * @return service type
     */
    public String getServiceType();
    
    /***
     * Return the Service Description
     * @return service description
     */
    public String getServiceDescription();
    
    /**
     * Return the Service Details table
     *
     * @return the attribute serviceTable
     */
    public JTable getServiceTable();
    
     /**
     * Indicate whether any service is selected
     *
     * @return true if a service is selected, or false otherwise
     */
    boolean isServiceSelected();
    
    /**
     * Return the NO of the service selected in table
     *
     * @return the NO of the selected service
     */
    int getSelectedServiceNO();
    
    /**
     * Display the details of the selected service
     *
     * @param service - the details of the selected service to display
     */
    void displaySelectedServiceDetails(Service service);
    
    /***
     * Return selected search type from combo box
     * @return selected search type
     */
    String getSelectedSearchType();
}

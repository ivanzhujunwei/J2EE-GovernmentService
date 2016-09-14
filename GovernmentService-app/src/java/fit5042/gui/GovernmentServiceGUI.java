/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.gui;

import fit5042.repository.entities.Service;
import java.util.List;
import javax.swing.JButton;

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
    void displayMessageInDialog(String message);
}

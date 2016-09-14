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
//                int serviceNo =
//                Double principle = gui.getPrinciple();
//                Double interestRate = gui.getInterestRate();
//                int numberOfYears = gui.getNoOfYears();
//
//                gui.updateResult(calculator.calculate(principle, numberOfYears, interestRate));
            }else if (ae.getSource() == gui.getViewButton()){
                displayAllService();
            }
        }
        catch (Exception ex)
        {
//            gui.showMessage(ex.getMessage());
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
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new GovernmentServiceApp();
    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
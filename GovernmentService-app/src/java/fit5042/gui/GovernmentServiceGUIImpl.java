/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fit5042.gui;

import fit5042.repository.entities.Service;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class GovernmentServiceGUIImpl extends JFrame implements GovernmentServiceGUI {

    private static final String[] TABLE_COLUMNS = {"NO", "Name", "Type", "Thumbnail", "Description"};
    
    // buttons
    private final JButton searchButton;
    private final JButton viewButton;
    // labels
    private final JLabel serviceNoLabel;
    private final JLabel serviceNameLabel;
    private final JLabel serviceTypeLabel;
    private final JLabel serviceThumbnailLabel;
    private final JLabel serviceDescLabel;
    // text fields
    private final JTextField serviceNoField;
    private final JTextField serviceNameField;
    private final JTextField serviceTypeField;
    private final JTextField serviceThumbnailField;
    private final JTextField serviceDescField;
    // table
    private final JTable serviceTable;
    // panels
    private final JPanel inputPanel;
    private final JPanel buttonPanel;
    
     public GovernmentServiceGUIImpl(ActionListener actionListener, ListSelectionListener listSelectionListener) {
        super("Government Service");
        
        // create container
        Container container = this.getContentPane();

        // create buttons
        this.searchButton = new JButton("Search");
        this.viewButton = new JButton("View");

        // create labels
        this.serviceNoLabel = new JLabel("Service No:");
        this.serviceNameLabel = new JLabel("Service Name:");
        this.serviceTypeLabel = new JLabel("Service Type:");
        this.serviceThumbnailLabel = new JLabel("Service Thumbnail:");
        this.serviceDescLabel = new JLabel("Service Description:");

        // create text fields
        this.serviceNoField = new JTextField();
        this.serviceNameField = new JTextField();
        this.serviceTypeField = new JTextField();
        this.serviceThumbnailField = new JTextField();
        this.serviceDescField = new JTextField();
        
        // create table
        this.serviceTable = new JTable(new DefaultTableModel(TABLE_COLUMNS, 0));
        this.serviceTable.getSelectionModel().addListSelectionListener(listSelectionListener);       
        this.serviceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        // set different columns width
        TableColumnModel propertyTableColumnModel = this.serviceTable.getColumnModel();
        propertyTableColumnModel.getColumn(0).setPreferredWidth(50);
        propertyTableColumnModel.getColumn(1).setPreferredWidth(200);
        propertyTableColumnModel.getColumn(2).setPreferredWidth(100);
        propertyTableColumnModel.getColumn(3).setPreferredWidth(100);
        propertyTableColumnModel.getColumn(4).setPreferredWidth(100);
        
        // create panels
        this.inputPanel = new JPanel();
        this.buttonPanel = new JPanel();
        

        // add components
        this.inputPanel.add(serviceNoLabel);
        this.inputPanel.add(serviceNoField);

        this.inputPanel.add(this.serviceNameLabel);
        this.inputPanel.add(this.serviceNameField);
        
        this.inputPanel.add(this.serviceTypeLabel);
        this.inputPanel.add(this.serviceTypeField);
        
        this.inputPanel.add(this.serviceThumbnailLabel);
        this.inputPanel.add(this.serviceThumbnailField);
        
        this.inputPanel.add(this.serviceDescLabel);
        this.inputPanel.add(this.serviceDescField);
        
        // add buttons to panel
        this.buttonPanel.add(this.searchButton);
        this.buttonPanel.add(this.viewButton);
        
        // set layout manager
        container.setLayout(new BorderLayout());
        this.inputPanel.setLayout(new GridLayout(5,2));
        this.buttonPanel.setLayout(new GridLayout(1,4));

        // add action listeners
        this.viewButton.addActionListener(actionListener);
        this.searchButton.addActionListener(actionListener);
        
        // add panels to content pane
        container.add(inputPanel,BorderLayout.NORTH);
        container.add(new JScrollPane(this.serviceTable), BorderLayout.CENTER);
        container.add(buttonPanel,BorderLayout.SOUTH);
        
        // change the default behaviour of the close button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setSize(750, 570);       
        this.setVisible(true);
    }

    @Override
    public void clearTextFields()
    {
        this.serviceNoField.setText("");
        this.serviceNameField.setText("");
        this.serviceDescField.setText("");
        this.serviceTypeField.setText("");
        this.serviceThumbnailField.setText("");
    }
    
    @Override
    public void displayMessageInDialog(String message)
    {
         JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public JButton getSearchButton()
    {
        return searchButton;
    }

    @Override
    public JButton getViewButton()
    {
        return viewButton;
    }

    @Override
    public void clearServiceTable() {     
        int numberOfRow = this.serviceTable.getModel().getRowCount();
        
        if (numberOfRow > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) this.serviceTable.getModel();
            for (int index = (numberOfRow - 1); index >= 0; index --) {
                tableModel.removeRow(index);
            }            
        }
    }
    
    @Override
    public void clearInput() {
        this.clearTextFields();
    }
    
    @Override
    public void displayAllServices(List<Service> services)
    {
        this.clearServiceTable();
        this.clearInput();
        for (Service service : services) {
            ((DefaultTableModel)this.serviceTable.getModel()).addRow(new Object[]{service.getNo(), 
                                                                                   service.getName(), 
                                                                                   service.getType(),
                                                                                   service.getThumbnail(),
                                                                                   service.getDescription()});
        }
    }
}

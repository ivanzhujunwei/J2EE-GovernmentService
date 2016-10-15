/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fit5042.gui;

import fit5042.repository.entities.Service;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

    // only serviceName and serviceNO will be shown in the search results
    private static final String[] TABLE_COLUMNS = {"NO", "Name"};
    
    // buttons
    private final JButton searchButton;
    private final JButton viewButton;
    // labels
    private final JLabel serviceNoLabel;
    private final JLabel serviceNameLabel;
    private final JLabel serviceTypeLabel;
    private final JLabel serviceDescLabel;
    // text fields
    private final JTextField serviceNoField;
    private final JTextField serviceNameField;
    private final JTextField serviceTypeField;
    private final JLabel serviceThumbnailImageLabel;
    private final JTextField serviceDescField;
    // table
    private final JTable serviceTable;
    // panels
    private final JPanel inputPanel;
    private final JPanel buttonPanel;
    private final JPanel imgPanel;
    // combox
    private final JComboBox searchComboBox;

    /**
     * Search by different filter
     */
    public static final String SEARCH_TYPE_NO = "Service NO";
    public static final String SEARCH_TYPE_TYPE = "Service Type";
    public static final String SEARCH_TYPE_NAME = "Service Name";
    public static final String[] SEARCH_CONDITIONS = {SEARCH_TYPE_NO,SEARCH_TYPE_TYPE,SEARCH_TYPE_NAME};
    
    public GovernmentServiceGUIImpl(ActionListener actionListener, ListSelectionListener listSelectionListener) {
        super("Government Service");
        
        // create container
        Container container = this.getContentPane();

        // create buttons
        this.searchButton = new JButton("Search By");
        this.viewButton = new JButton("Show All");

        // create labels
        this.serviceNoLabel = new JLabel("Service No:");
        this.serviceNameLabel = new JLabel("Service Name:");
        this.serviceTypeLabel = new JLabel("Service Type:");
        this.serviceDescLabel = new JLabel("Service Description:");

        // create text fields
        this.serviceNoField = new JTextField();
        this.serviceNameField = new JTextField();
        this.serviceTypeField = new JTextField();
        this.serviceDescField = new JTextField();
        
        // create the icon filed
        this.serviceThumbnailImageLabel = new JLabel();
        
        // create table
        this.serviceTable = new JTable(new DefaultTableModel(TABLE_COLUMNS, 0));
        this.serviceTable.getSelectionModel().addListSelectionListener(listSelectionListener);       
        this.serviceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        // create combox
        this.searchComboBox = new JComboBox(SEARCH_CONDITIONS);
        
        // set different columns width
        TableColumnModel propertyTableColumnModel = this.serviceTable.getColumnModel();
        propertyTableColumnModel.getColumn(0).setPreferredWidth(100);
        propertyTableColumnModel.getColumn(1).setPreferredWidth(300);
        
        // create panels
        this.inputPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.imgPanel = new JPanel();
        

        // add components
        this.inputPanel.add(serviceNoLabel);
        this.inputPanel.add(serviceNoField);

        this.inputPanel.add(this.serviceThumbnailImageLabel);
        
        this.inputPanel.add(this.serviceNameLabel);
        this.inputPanel.add(this.serviceNameField);
        
        this.inputPanel.add(this.serviceTypeLabel);
        this.inputPanel.add(this.serviceTypeField);
        
        this.inputPanel.add(this.serviceDescLabel);
        this.inputPanel.add(this.serviceDescField);
        
        // add image to panel
        this.imgPanel.add(this.serviceThumbnailImageLabel);
        
        // add buttons to panel
        this.buttonPanel.add(this.searchButton);
        // add combox
        this.buttonPanel.add(this.searchComboBox);
        this.buttonPanel.add(this.viewButton);
        // set layout manager
        container.setLayout(new BorderLayout());
        this.inputPanel.setLayout(new GridLayout(4,2));
        this.buttonPanel.setLayout(new GridLayout(1,4));
        this.imgPanel.setLayout(new GridLayout(1,1));

        // add action listeners
        this.viewButton.addActionListener(actionListener);
        this.searchButton.addActionListener(actionListener);
        this.searchComboBox.addActionListener(actionListener);
        
        // add panels to content pane
        container.add(new JScrollPane(this.serviceTable), BorderLayout.CENTER);
        container.add(buttonPanel,BorderLayout.SOUTH);
        container.add(imgPanel,BorderLayout.WEST);
        container.add(inputPanel,BorderLayout.NORTH);
        
        this.imgPanel.setPreferredSize(new Dimension(400, 200));
        // change the default behaviour of the close button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setSize(800, 570);       
        this.setVisible(true);
    }

    @Override
    public void clearTextFields()
    {
        this.serviceNoField.setText("");
        this.serviceNameField.setText("");
        this.serviceDescField.setText("");
        this.serviceTypeField.setText("");
//        this.serviceThumbnailImageLabel.setIcon(imageIcon);
//        this.imgPanel.removeAll();
        this.serviceThumbnailImageLabel.setIcon(null);
    }
    
    @Override
    public void displayMessageInDialog(String message)
    {
         JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void displayService(Service service)
    {
        this.clearServiceTable();
        this.clearInput();
        ((DefaultTableModel)this.serviceTable.getModel()).addRow(new Object[]{service.getService_no(), 
                                                                               service.getName(), 
                                                                               service.getType(), 
                                                                               service.getThumbnail(), 
                                                                               service.getDescription()});
    }

    @Override
    public JButton getSearchButton()
    {
        return searchButton;
    }

    @Override
    public String getSelectedSearchType()
    {
        return (String) this.searchComboBox.getSelectedItem();
    }

    @Override
    public String getServiceName()
    {
        return this.serviceNameField.getText();
    }

    @Override
    public JTable getServiceTable()
    {
        return this.serviceTable;
    }

    @Override
    public String getServiceDescription()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getServiceNo()
    {
        String sevice_noString = this.serviceNoField.getText();
        return Integer.parseInt(sevice_noString);
    }

    @Override
    public String getServiceType()
    {
        return this.serviceTypeField.getText();
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
            ((DefaultTableModel)this.serviceTable.getModel()).addRow(new Object[]{service.getService_no(), 
                                                                                   service.getName(), 
                                                                                   service.getType(),
                                                                                   service.getThumbnail(),
                                                                                   service.getDescription()});
        }
    }

    @Override
    public boolean isServiceSelected()
    {
        return this.serviceTable.getSelectedRow() >= 0;
    }
    
    @Override
    public int getSelectedServiceNO() {
        int selectedRowIndex = this.serviceTable.getSelectedRow();
        String serviceNO = this.serviceTable.getValueAt(selectedRowIndex, 0).toString();
        return Integer.parseInt(serviceNO); 
    }
    
    @Override
    public void displaySelectedServiceDetails(Service service) {
        this.serviceNoField.setText(String.valueOf(service.getService_no()));
        this.serviceNameField.setText(service.getName());
        this.serviceTypeField.setText(service.getType());
        this.serviceDescField.setText(service.getDescription());
        String imgName = service.getThumbnail();
        imgName = "../images/serviceThumbnail/"+ imgName;
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(imgName).getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT));
        this.serviceThumbnailImageLabel.setIcon(imageIcon);
    }
}

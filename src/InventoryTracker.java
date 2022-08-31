import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class InventoryTracker extends JDialog{
    private JPanel outerMostPanel;
    private JTextField searchByTextField;
    private JButton searchButton;
    private JComboBox searchType;
    private JPanel searchPanel;
    private JButton editButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton exportCSVButton;
    private JButton createCSVButton;
    private JButton accessFolderButton;
    private JPanel mainFunctionsPanel;
    private JTable dataTable;
    private JTabbedPane dataEntryTabbedPane;
    private JTextField assignedUserTextField;
    private JTextField modelTextField;
    private JComboBox departmentsBox;
    private JComboBox hardwareBox;
    private JTextField serialNumberTextField;
    private JComboBox operatingSystemsBox;
    private JTextField brandTextField;
    private JComboBox physicalVirtualBox;
    private JPanel dataEntryPanel;
    private JTextField storageTextField;
    private JComboBox storageTypeBox;
    private JTextField ramTextField;
    private JTextField processorTextField;
    private JTextField officeKeyTextField;
    private JTextField installedSoftwareTextField;
    private JTextField purchaseDateTextField;
    private JTextField businessLocationTextField;
    private JTextField supplierTextField;
    private JComboBox officeVersionBox;
    private JPanel observationsPanel;
    private JTextArea observationsTextArea;
    private JTextArea notesTextArea;
    private JSplitPane mainContentSplitPane;
    private JScrollPane dataTablePane;
    private JButton saveButton;

    private void createUIComponents() {
        // TODO: place custom component creation code here

    }

    public InventoryTracker(JFrame parent){
        super(parent);

        String [] columnNames = {"Assigned User", "Department",	"Model", "Hardware", "Serial Number",
                "Operating System",	"Brand", "Physical/Virtual", "Storage",	"Storage Type",
                "Office Key", "Office version",	"Purchase Date", "Installed Software",
                "Processor", "Ram",	"Supplier",	"Business Location", "Observations", "Notes"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        dataTable.setModel(model);
        dataEntryPanel.setPreferredSize(new Dimension(256,512));




        // Add new row to dataTable using Data Entry Text Fields and Combo Boxes
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addRow(new Object[]{
                        assignedUserTextField.getText(),
                        departmentsBox.getSelectedItem(),
                        modelTextField.getText(),
                        hardwareBox.getSelectedItem(),
                        serialNumberTextField.getText(),
                        operatingSystemsBox.getSelectedItem(),
                        brandTextField.getText(),
                        physicalVirtualBox.getSelectedItem(),
                        storageTextField.getText(),
                        storageTypeBox.getSelectedItem(),
                        officeKeyTextField.getText(),
                        officeVersionBox.getSelectedItem(),
                        purchaseDateTextField.getText(),
                        installedSoftwareTextField.getText(),
                        processorTextField.getText(),
                        ramTextField.getText(),
                        supplierTextField.getText(),
                        businessLocationTextField.getText(),
                        observationsTextArea.getText(),
                        notesTextArea.getText()
                });
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(dataTable.getSelectedRow() >= 0){
                        model.removeRow(dataTable.getSelectedRow());
                    }
                }catch(ArrayIndexOutOfBoundsException ignored){

                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /*for(Component component : dataEntryPanel.getComponents()){
            if(component instanceof JTextField){
                JTextField componentJTextField = ((JTextField) component);
                String componentText = componentJTextField.getText();
                component.setForeground(Color.GRAY);
                component.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        if(componentJTextField.getText().equals(componentText)){
                            componentJTextField.setText("");
                            componentJTextField.setForeground(Color.BLACK);
                        }
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        if(componentJTextField.getText().isEmpty()){
                            componentJTextField.setForeground(Color.GRAY);
                            componentJTextField.setText(componentText);
                        }
                    }
                });
            }
        }*/

        // Fills the text fields in dataEntryPanel with placeholder gray text
        for(Component component : dataEntryPanel.getComponents()){
            if(component instanceof JTextField){
                JTextField componentJTextField = ((JTextField) component);
                String componentText = componentJTextField.getText();
                component.setForeground(Color.GRAY);
                component.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        if(componentJTextField.getText().equals(componentText)){
                            componentJTextField.setText("");
                            componentJTextField.setForeground(Color.BLACK);
                        }
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        if(componentJTextField.getText().isEmpty()){
                            componentJTextField.setForeground(Color.GRAY);
                            componentJTextField.setText(componentText);
                        }
                    }
                });
            }
        }


        // Fills the text area fields in observationsPanel with placeholder gray text
        for(Component component : observationsPanel.getComponents()){
            if(component instanceof JTextArea){
                JTextArea componentJTextArea = ((JTextArea) component);
                String componentText = componentJTextArea.getText();
                component.setForeground(Color.GRAY);
                component.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        if(componentJTextArea.getText().equals(componentText)){
                            componentJTextArea.setText("");
                            componentJTextArea.setForeground(Color.BLACK);
                        }
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        if(componentJTextArea.getText().isEmpty()){
                            componentJTextArea.setForeground(Color.GRAY);
                            componentJTextArea.setText(componentText);
                        }
                    }
                });
            }
        }

        // Fills the text fields in dataEntryPanel with placeholder gray text
        for(Component component : dataEntryPanel.getComponents()){
            if(component instanceof JComboBox<?>){
                JComboBox<?> componentJComboBox = ((JComboBox<?>) component);
                String componentText = componentJComboBox.getItemAt(0).toString();
                component.setForeground(Color.GRAY);
                component.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        if(!componentJComboBox.getSelectedItem().equals(componentJComboBox.getItemAt(0))){
                            componentJComboBox.setForeground(Color.BLACK);
                        }else{
                            componentJComboBox.setForeground(Color.GRAY);
                        }
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        if(!componentJComboBox.getSelectedItem().equals(componentJComboBox.getItemAt(0))){
                            componentJComboBox.setForeground(Color.BLACK);
                        }else{
                            componentJComboBox.setForeground(Color.GRAY);
                        }
                    }
                });
            }
        }


        setTitle("Inventory Tracker");
        setContentPane(outerMostPanel);
        setMinimumSize(new Dimension(768, 512));
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);
        setDefaultCloseOperation(0);

        if(!isVisible()){
            System.exit(0);
        }
    }

}

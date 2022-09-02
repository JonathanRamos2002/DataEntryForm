import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

public class InventoryTracker extends JDialog{
    private JPanel outerMostPanel;
    private JTextField searchTextField;
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
    private JButton clearAllButton;
    private JButton updateButton;

    private void createUIComponents() {
        // TODO: place custom component creation code here

    }

    public InventoryTracker(JFrame parent){

        String [] columnNames = {"Assigned User", "Department",	"Model", "Hardware", "Serial Number",
                "Operating System",	"Brand", "Physical/Virtual", "Storage",	"Storage Type",
                "Office Key", "Office Version",	"Purchase Date", "Installed Software",
                "Processor", "Ram",	"Supplier",	"Business Location", "Observations", "Notes"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        dataTable.setModel(model);
        dataEntryPanel.setPreferredSize(new Dimension(256,512));
        dataTable.getTableHeader().setBackground(Color.LIGHT_GRAY);
        dataTable.getTableHeader().setForeground(Color.BLACK);


        // Add new row to dataTable using Data Entry Text Fields, Combo Boxes, and Text Areas
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean allFilled = true;
                ArrayList<Component> components = new ArrayList<>(List.of(dataEntryPanel.getComponents()));

                for(Component component : components){
                    if(component instanceof JTextField){
                        if( ((JTextField) component).getText().equals(component.getName()) )
                            allFilled = false;
                    } else if(component instanceof JComboBox<?>){
                        if( ((JComboBox<?>)component).getSelectedItem().equals( ((JComboBox<?>)component).getItemAt(0) ) )
                            allFilled = false;
                    }
                }

                if(allFilled){
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
                            observationsTextArea.getText().equals(observationsTextArea.getName()) ? "" : observationsTextArea.getText(),
                            notesTextArea.getText().equals(notesTextArea.getName()) ? "" : notesTextArea.getText()
                    });
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    while(dataTable.getSelectedRow() >= 0){
                        model.removeRow(dataTable.getSelectedRow());
                    }
                }catch(ArrayIndexOutOfBoundsException ignored){

                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // set all data entry fields from selected row
                try{
                    int row = dataTable.getSelectedRow();
                    ArrayList<Component> components = new ArrayList<>(List.of(dataEntryPanel.getComponents()));
                    components.addAll(List.of(observationsPanel.getComponents()));

                    for(int i = 0; i < model.getColumnCount(); i++){
                        //System.out.println(model.getColumnName(i) + ": " + model.getValueAt(row, i));
                        for(Component component : components){
                            if(component instanceof JTextField){
                                if(component.getName().equals(model.getColumnName(i))){
                                    component.setForeground(Color.BLACK);
                                    ((JTextField) component).setText((String) model.getValueAt(row, i));
                                }
                            } else if(component instanceof JComboBox<?>){
                                if(component.getName().equals(model.getColumnName(i))){
                                    component.setForeground(Color.BLACK);
                                    ((JComboBox<?>) component).setSelectedItem(model.getValueAt(row, i));
                                }
                            } else if(component instanceof JTextArea){
                                if(component.getName().equals(model.getColumnName(i))){
                                    component.setForeground(Color.BLACK);
                                    ((JTextArea) component).setText((String) model.getValueAt(row, i));
                                }
                            }
                        }
                    }
                    //serialNumberTextField.setEditable(false);
                }catch (IndexOutOfBoundsException ignore){

                }

            }
        });

        ArrayList<Component> components = new ArrayList<>(List.of(dataEntryPanel.getComponents()));
        components.addAll(List.of(observationsPanel.getComponents()));
        components.addAll(List.of(searchPanel.getComponents()));

        // Fills the text fields, combo boxes, and text areas in components ArrayList with placeholder gray text
        for(Component component : components){
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
            } else if(component instanceof JComboBox<?>){
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
            } else if(component instanceof JTextArea){
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

        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Component> components = new ArrayList<>(List.of(dataEntryPanel.getComponents()));
                components.addAll(List.of(observationsPanel.getComponents()));

                for(Component component : components){
                    if(component instanceof JTextField){
                        component.setForeground(Color.GRAY);
                        ((JTextField) component).setText(component.getName());
                    } else if(component instanceof JComboBox<?>){
                        component.setForeground(Color.GRAY);
                        ((JComboBox<?>) component).setSelectedItem( ((JComboBox<?>) component).getItemAt(0));
                    } else if(component instanceof JTextArea){
                        component.setForeground(Color.GRAY);
                        ((JTextArea) component).setText(component.getName());
                    }
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //serialNumberTextField.setEditable(true);
                boolean foundDataEntry = false;
                // loop through each row
                for(int row = 0; row < model.getRowCount(); row++){
                    // search for the row that will be updated based on the serial number
                    if(model.getValueAt(row, 4).equals(serialNumberTextField.getText())){

                        ArrayList<Component> components = new ArrayList<>(List.of(dataEntryPanel.getComponents()));
                        boolean allFilled = true;

                        // check all components in panel to see if they are filled with text or left empty
                        for(Component component : components){
                            if(component instanceof JTextField){
                                if( ((JTextField) component).getText().equals(component.getName()) )
                                    allFilled = false;
                            } else if(component instanceof JComboBox<?>){
                                if( ((JComboBox<?>)component).getSelectedItem().equals( ((JComboBox<?>)component).getItemAt(0) ) )
                                    allFilled = false;
                            }
                        }

                        // array of data for the row to be added
                        Object [] data = {
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
                                observationsTextArea.getText().equals(observationsTextArea.getName()) ? "" : observationsTextArea.getText(),
                                notesTextArea.getText().equals(notesTextArea.getName()) ? "" : notesTextArea.getText()
                        };

                        // loop through all columns updating values in chosen row
                        for(int column = 0; column < model.getColumnCount(); column++) {
                            model.setValueAt(data[column], row, column);
                        }
                        clearAllButton.doClick();
                        foundDataEntry = true;
                        break;
                    }
                }

                if(!foundDataEntry){
                    Icon errorIcon = new ImageIcon("/Users/jonathanramos/Developer/IdeaProjects/Personal/DataEntryForm/src/warning.png");
                    JOptionPane.showMessageDialog(parent,
                            serialNumberTextField.getText().equals(serialNumberTextField.getName()) ?
                                    "Please Enter A Serial Number To Update" :
                                    "Unable To Find Serial Number : " + serialNumberTextField.getText() + "\nPlease Enter A Valid Serial Number To Update",
                            "Data Entry Not Found",
                            JOptionPane.ERROR_MESSAGE,
                            errorIcon
                    );
                    parent.setAlwaysOnTop(true);

                }

            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchingValue = searchTextField.getText();
                String searchingType = (String) searchType.getSelectedItem();
                //int columnSearchingType =




            }
        });

        setTitle("Inventory Tracker");
        ImageIcon icon = new ImageIcon("/Users/jonathanramos/Developer/IdeaProjects/Personal/DataEntryForm/src/inventory.png");
        setIconImage(icon.getImage());
        setContentPane(outerMostPanel);
        setMinimumSize(new Dimension(768, 512));
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);
        setDefaultCloseOperation(0);

        // terminates program
        if(!isVisible()){
            System.exit(0);
        }

    }

}

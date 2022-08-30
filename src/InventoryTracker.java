import javax.swing.*;
import java.awt.*;

public class InventoryTracker extends JDialog{
    private JPanel outerMostPanel;
    private JPanel buttonPanel;
    private JPanel dataInputPanel;
    private JTextField searchByTextField;
    private JButton searchButton;
    private JComboBox searchType;
    private JPanel searchPanel;
    private JButton editButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton exportCSVButton;
    private JButton importCSVButton;
    private JButton accessFolderButton;
    private JPanel mainFunctionsPanel;
    private JTable table1;
    private JTabbedPane tabbedPane1;
    private JTextField assignedUserTextField;
    private JTextField modelTextField;
    private JComboBox departmentsBox;
    private JComboBox hardwareBox;
    private JTextField serialNumberTextField;
    private JComboBox comboBox3;
    private JTextField brandTextField;
    private JComboBox comboBox4;
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
    private JComboBox comboBox1;
    private JPanel observationsPanel;
    private JTextArea observationsNotesTextArea;

    private void createUIComponents() {
        // TODO: place custom component creation code here

    }

    public InventoryTracker(JFrame parent){
        super(parent);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        String [] columnNames = {"Assigned User", "Department", "HardWare", "Physical/Virtual", "Model", "Serial Number", "Brand", "OS", "Ram",
                "Storage", "Storage Type",	"Processor", "Office Version", "Office Product Key",
                "Other Software", "Business Location", "Purchase Date",
                "Observations", "Last Maintenance Date"};
        


        setTitle("Inventory Tracker");
        setContentPane(outerMostPanel);
        setMinimumSize(new Dimension(768, 512));
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




    }

}

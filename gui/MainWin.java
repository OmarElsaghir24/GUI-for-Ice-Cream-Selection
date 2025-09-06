package gui;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.UIManager;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToggleButton;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import java.awt.FlowLayout;

import java.awt.Font;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import product.MixIn;
import product.IceCreamFlavor;
import product.MixInFlavor;
import product.Scoop;
import product.Container;
import product.Serving;
import product.Order;
import product.MixInAmount;
import product.Item;
import emporium.Emporium;
import person.Customer;
import person.Person;
import gui.Canvas;
import java.awt.Graphics;

public class MainWin extends JFrame {
    private String NAME = "MICE";
    private String VERSION = "0.3";
    private String FILE_VERSION = "1.0";
    private String MAGIC_COOKIE = "MICE𓎯";

    public MainWin() {
        super("Mavs Ice Cream Emporium");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        filename = new File("untitled.mice");

        emporium = new Emporium();

        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();
        
        JMenu     file       = new JMenu("File");
        JMenuItem quit       = new JMenuItem("Quit");
        JMenuItem save       = new JMenuItem("Save");
        JMenuItem saveas     = new JMenuItem("Save As");
        JMenuItem open       = new JMenuItem("Open");
        JMenu     help       = new JMenu("Help");
        JMenuItem about      = new JMenuItem("About");
        JMenu     create     = new JMenu("Create");
        JMenu     view       = new JMenu("View");
        JMenuItem iceCream   = new JMenuItem("Ice Cream Flavor");
        JMenuItem mixIn      = new JMenuItem("Mix In Flavor");
                  //scoop      = new JMenuItem("Scoop");
        JMenuItem container  = new JMenuItem("Container");
        JMenuItem order      = new JMenuItem("Order");
        JMenuItem customer   = new JMenuItem("Customer");
        JMenuItem iceCream2  = new JMenuItem("Ice Cream Flavor");
        JMenuItem mixIn2     = new JMenuItem("Mix In Flavor");
        //JMenuItem scoop2     = new JMenuItem("Scoop");
        JMenuItem container2 = new JMenuItem("Container");
        JMenuItem order2     = new JMenuItem("Order");
        JMenuItem customer2  = new JMenuItem("Customer");
        
        quit.addActionListener(event -> onQuitClick());
        open.addActionListener(event -> onOpenClick());
        save.addActionListener(event -> onSaveClick());
        saveas.addActionListener(event -> onSaveAsClick());
        iceCream2.addActionListener(event -> view(Screen.ICE_CREAM_FLAVORS));
        mixIn2.addActionListener(event -> view(Screen.MIX_IN_FLAVORS));
        container2.addActionListener(event -> view(Screen.CONTAINERS));
        order2.addActionListener(event -> view(Screen.ORDERS));
        customer2.addActionListener(event -> view(Screen.CUSTOMERS));
        iceCream.addActionListener(event -> onCreateIceCreamFlavorClick());
        mixIn.addActionListener(event -> onCreateMixInFlavorClick());
        //scoop.addActionListener(event -> onCreateScoopClick());
        container.addActionListener(event -> onCreateContainerClick());
        order.addActionListener(event -> onCreateOrderClick());
        customer.addActionListener(event -> onCreateCustomerClick());
        //scoop.setEnabled(false);
        about.addActionListener(event -> onAboutClick());

        file.add(quit);
        file.add(open);
        file.add(save);
        file.add(saveas);
        view.add(iceCream2);
        view.add(mixIn2);
        view.add(container2);
        view.add(order2);
        view.add(customer2);
        create.add(iceCream);
        create.add(mixIn);
        create.add(customer);
        create.add(container);
        create.add(order);
        help.add(about);
        
        menubar.add(file);
        menubar.add(view);
        menubar.add(create);
        menubar.add(help);
        setJMenuBar(menubar);

        JToolBar toolbar = new JToolBar("Ice Cream Buttons");

        JButton anewB  = new JButton(UIManager.getIcon("FileView.fileIcon"));
        anewB.setActionCommand("New Game");
        anewB.setToolTipText("Create a new game, discarding any in progress");
        anewB.setBorder(null);
        toolbar.add(anewB);
        //anewB.addActionListener(event -> onCreateIceCreamFlavorClick());
        
        // A "horizontal strut" is just a space of the specified pixel width
        toolbar.add(Box.createHorizontalStrut(25));

        JButton openA  = new JButton(new ImageIcon("gui/OpenFile.png"));
        openA.setActionCommand("Open a new file");
        openA.setToolTipText("Open a new file");
        toolbar.add(openA);
        openA.addActionListener(event -> onOpenClick());

        JButton saveA  = new JButton(new ImageIcon("gui/SaveFile.png"));
        saveA.setActionCommand("Save a new file");
        saveA.setToolTipText("Save a new file");
        toolbar.add(saveA);
        saveA.addActionListener(event -> onSaveClick());

        JButton saveasA  = new JButton(new ImageIcon("gui/SaveAsFile.png"));
        saveasA.setActionCommand("Save as a new file");
        saveasA.setToolTipText("Save as a new file");
        toolbar.add(saveasA);
        saveasA.addActionListener(event -> onSaveAsClick());

        toolbar.add(Box.createHorizontalStrut(25));

        JButton iceCreamA  = new JButton(new ImageIcon("gui/IceCreamFlavor.png"));
        iceCreamA.setActionCommand("New Ice Cream Flavor");
        iceCreamA.setToolTipText("Create an Ice Cream Flavor");
        toolbar.add(iceCreamA);
        iceCreamA.addActionListener(event -> onCreateIceCreamFlavorClick());

        JButton mixinA  = new JButton(new ImageIcon("gui/MixInFlavor.png"));
        mixinA.setActionCommand("New Mix In Flavor");
        mixinA.setToolTipText("Create a Mix In Flavor");
        toolbar.add(mixinA);
        mixinA.addActionListener(event -> onCreateMixInFlavorClick());

        JButton containerA  = new JButton(new ImageIcon("gui/Container.png"));
        containerA.setActionCommand("New Container");
        containerA.setToolTipText("Create a Container");
        toolbar.add(containerA);
        containerA.addActionListener(event -> onCreateContainerClick());

        JButton orderA = new JButton(new ImageIcon("gui/Order.png"));
        orderA.setActionCommand("New Order");
        orderA.setToolTipText("Create an Order");
        toolbar.add(orderA);
        orderA.addActionListener(event -> onCreateOrderClick());

        JButton customerA  = new JButton(new ImageIcon("gui/Customer.png"));
        customerA.setActionCommand("New Customer");
        customerA.setToolTipText("New Customer");
        toolbar.add(customerA);
        customerA.addActionListener(event -> onCreateCustomerClick());

        toolbar.add(Box.createHorizontalStrut(25));

        JButton viewiceCream  = new JButton(new ImageIcon("gui/IceCreamFlavor.png"));
        viewiceCream.setActionCommand("View Ice Cream Flavor");
        viewiceCream.setToolTipText("View an Ice Cream Flavor");
        toolbar.add(viewiceCream);
        viewiceCream.addActionListener(event -> view(Screen.ICE_CREAM_FLAVORS));

        JButton viewmixin  = new JButton(new ImageIcon("gui/MixInFlavor.png"));
        viewmixin.setActionCommand("View Mix In Flavor");
        viewmixin.setToolTipText("View a Mix In Flavor");
        toolbar.add(viewmixin);
        viewmixin.addActionListener(event -> view(Screen.MIX_IN_FLAVORS));

        JButton viewcontainer  = new JButton(new ImageIcon("gui/Container.png"));
        viewcontainer.setActionCommand("View Container");
        viewcontainer.setToolTipText("View a Container");
        toolbar.add(viewcontainer);
        viewcontainer.addActionListener(event -> view(Screen.CONTAINERS));

        JButton vieworder = new JButton(new ImageIcon("gui/Order.png"));
        vieworder.setActionCommand("View Order");
        vieworder.setToolTipText("View an Order");
        toolbar.add(vieworder);
        vieworder.addActionListener(event -> view(Screen.ORDERS));

        JButton viewcustomer = new JButton(new ImageIcon("gui/Customer.png"));
        viewcustomer.setActionCommand("View Customer");
        viewcustomer.setToolTipText("View a Customer");
        toolbar.add(viewcustomer);
        viewcustomer.addActionListener(event -> view(Screen.CUSTOMERS));

        toolbar.add(Box.createHorizontalStrut(25));

        toolbar.add(Box.createHorizontalGlue());

        getContentPane().add(toolbar, BorderLayout.PAGE_START);

        display = new JLabel();
        add(display, BorderLayout.CENTER);

        // Make everything in the JFrame visible
        setVisible(true); 
    }
    // Listeners

    protected void onQuitClick() {
        System.exit(0);
    }
    
    protected void onCreateIceCreamFlavorClick() {
      JLabel name = new JLabel("<HTML><br/>Name?</HTML>");
      JTextField names = new JTextField(20);

      JLabel description = new JLabel("<HTML><br/>Description?</HTML>");
      JTextField descriptions = new JTextField(20);

      JLabel cost = new JLabel("<HTML><br/>Cost?</HTML>");
      JTextField costs = new JTextField(20);

      JLabel price = new JLabel("<HTML><br/>Price?</HTML>");
      JTextField prices = new JTextField(20);

      Object[] Flavors = {
        name,    names,
        description,   descriptions,
        cost,    costs,
        price,   prices};

      try{
        int flavor = JOptionPane.showConfirmDialog(this, Flavors, "Create Ice Cream Flavor", JOptionPane.OK_CANCEL_OPTION);
        if(flavor == JOptionPane.OK_OPTION) {
            String costs2 = costs.getText();
            String prices2 = prices.getText();
            emporium.addIceCreamFlavor(new IceCreamFlavor(names.getText(), descriptions.getText(), Integer.parseInt(costs2), Integer.parseInt(prices2)));
        }
      }catch(Exception e) {
      }
      //scoop.setEnabled(true);
      view(Screen.ICE_CREAM_FLAVORS);
    }

    protected void onCreateMixInFlavorClick() {
      JLabel name = new JLabel("<HTML><br/>Name?</HTML>");
      JTextField names = new JTextField(20);

      JLabel description = new JLabel("<HTML><br/>Description?</HTML>");
      JTextField descriptions = new JTextField(20);

      JLabel cost = new JLabel("<HTML><br/>Cost?</HTML>");
      JTextField costs = new JTextField(20);

      JLabel price = new JLabel("<HTML><br/>Price?</HTML>");
      JTextField prices = new JTextField(20);
      emporium = new Emporium();

      Object[] MixInFlavors = {
        name,    names,
        description,   descriptions,
        cost,    costs,
        price,   prices};

      try{
        int mixin = JOptionPane.showConfirmDialog(this, MixInFlavors, "Create Mix In Flavor", JOptionPane.OK_CANCEL_OPTION);
        if(mixin == JOptionPane.OK_OPTION) {
            String costs2 = costs.getText();
            String prices2 = prices.getText();
            emporium.addMixInFlavor(new MixInFlavor(names.getText(), descriptions.getText(), Integer.parseInt(costs2), Integer.parseInt(prices2)));
        }
      }catch(Exception e) {
      }
      view(Screen.MIX_IN_FLAVORS);
    }
    
    protected Scoop onCreateScoop() {
        Scoop scoop = null;
        try {
        IceCreamFlavor icf = (IceCreamFlavor) JOptionPane.showInputDialog(this, 
            "Ice Cream Flavor?", "New Scoop", JOptionPane.QUESTION_MESSAGE, null, 
            emporium.iceCreamFlavor(), null);
        if(icf != null) {
            scoop = new Scoop(icf);
            if(emporium.mixinFlavors().length > 0) {
                String prompt = "<html>" + scoop + "<br/>Add a mix in?</html>";
                MixIn mixin = null;
                while((mixin = onCreateMixIn(prompt)) != null) {
                    scoop.addMixIn(mixin);
                    prompt = "<html>" + scoop + "<br/>Add another mix in?</html>";
                }
            }       
        }
        }catch(Exception e) {
        }
        return scoop;
    }

    protected void onCreateContainerClick() {
      JLabel name = new JLabel("<HTML><br/>Name</HTML>");
      JTextField names = new JTextField(20);

      JLabel description = new JLabel("<HTML><br/>Description</HTML>");
      JTextField descriptions = new JTextField(20);

      JLabel maxScoop = new JLabel("<HTML><br/>Max Scoop</HTML>");
      JTextField maxScoops = new JTextField(20);

      Object[] Containers = {
        name,    names,
        description,   descriptions,
        maxScoop,    maxScoops};

      try{
        int container = JOptionPane.showConfirmDialog(this, Containers, "Create Container", JOptionPane.OK_CANCEL_OPTION);
        if(container == JOptionPane.OK_OPTION) {
            String max = maxScoops.getText();
            emporium.addContainer(new Container(names.getText(), descriptions.getText(), Integer.parseInt(max)));
        }
      }catch(Exception e) {
      }
      view(Screen.CONTAINERS);
    }

    protected void onCreateCustomerClick() {
      JLabel name = new JLabel("<HTML><br/>Name</HTML>");
      JTextField names = new JTextField(20);

      JLabel phone = new JLabel("<HTML><br/>Phone Number</HTML>");
      JTextField phones = new JTextField(20);

      Object[] Customers = {
        name,    names,
        phone,   phones};

      try{
        int customer = JOptionPane.showConfirmDialog(this, Customers, "New Customer", JOptionPane.OK_CANCEL_OPTION);
        if(customer == JOptionPane.OK_OPTION) {
            emporium.addCustomer(new Customer(names.getText(), phones.getText()));
        }
      }catch(Exception e) {
      }
      view(Screen.CUSTOMERS);
    }
   
    protected Serving onCreateServing(Customer customer) {
        Serving serving = null;
        try {
            if(emporium.favoriteServings(customer).length > 0) {
                serving = (Serving) JOptionPane.showInputDialog(this, "Select favorite serving?", "Select Serving", JOptionPane.YES_NO_CANCEL_OPTION, null, emporium.favoriteServings(customer), null);
                //if(serving == JOptionPane.CANCEL_OPTION) return null;
            }else {
            Container container = (Container) JOptionPane.showInputDialog(this, "Container?", "New Container", JOptionPane.QUESTION_MESSAGE, null, emporium.containers(), null);
            if(container != null) {
                serving = new Serving(container);
                Scoop scoop = null;
                boolean noScoop = true;
                while((scoop = onCreateScoop()) != null) {
                    serving.addScoop(scoop);
                    noScoop = false;
                    if(serving.numScoops() == container.maxScoops()) break;
                    int result = JOptionPane.showConfirmDialog(
                        this, serving, "Add another scoop?", JOptionPane.YES_NO_CANCEL_OPTION);
                    if(result == JOptionPane.CANCEL_OPTION) return null;
                    if(result == JOptionPane.NO_OPTION) break;
                }
                if(noScoop) return null; 
                if(emporium.mixinFlavors().length > 0) {
                    String prompt = "<html>" + serving + "<br/>Add a topping?</html>";
                    MixIn topping = null;
                    while((topping = onCreateMixIn(prompt)) != null) {
                        serving.addTopping(topping);
                        prompt = "<html>" + serving + "<br/>Add another topping?</html>";
                    }
                }  
            }
           }
        } catch(Exception e) {
            System.err.println("onCreateScoop exception: " + e);
            return null;
        }
        return serving;
    }
    
    protected void onCreateOrderClick() {
        Order order = null;
        try {
            Serving serving = null;
            Customer customer = (Customer) JOptionPane.showInputDialog(this, 
            "Customer?", "New Customer", JOptionPane.QUESTION_MESSAGE, null, 
            emporium.customers(), null);
            if(customer != null) {
            while((serving = onCreateServing(customer)) != null) {
                if(order == null) order = new Order(customer);
                order.addServing(serving);
                int result = JOptionPane.showConfirmDialog(
                    this, order, "Add Another Serving?", JOptionPane.YES_NO_CANCEL_OPTION);
                if(result == JOptionPane.CANCEL_OPTION) return;
                if(result == JOptionPane.NO_OPTION) break;
            }
            if(order != null) emporium.addOrder(order, customer);
            view(Screen.ORDERS);
            }
        } catch(Exception e) {
            System.err.println("onCreateScoop exception: " + e);
        }
    }

    protected MixIn onCreateMixIn(String prompt) {
        MixIn mixin = null;
        try {
            JLabel status = new JLabel(prompt);
            JLabel flavor = new JLabel("<html><br/>MixIn Flavor</html>");
            JComboBox<Object> flavors = new JComboBox<>(emporium.mixinFlavors());
            JLabel amount = new JLabel("<html><br/>MixIn Amount</html>");
            JComboBox<MixInAmount> amounts = new JComboBox<>(MixInAmount.values());
            amounts.setSelectedItem(MixInAmount.Normal); // default value
          
            Object[] objects = { // Array of widgets to display
                status,
                flavor, flavors,
                amount, amounts};
            int button = JOptionPane.showConfirmDialog( // Show the dialog
                this, objects, "New MixIn", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null);
            if(button == JOptionPane.YES_OPTION) 
                mixin = new MixIn((MixInFlavor) flavors.getSelectedItem(), 
                                  (MixInAmount) amounts.getSelectedItem());         
        } catch(Exception e) {
            System.err.println("onCreateMixIn exception: " + e);
        }
        return mixin;
    }

    protected void onOpenClick() {         
        final JFileChooser fc = new JFileChooser(filename);  // Create a file chooser dialog
        FileFilter miceFiles = new FileNameExtensionFilter("MICE files", "mice");
        fc.addChoosableFileFilter(miceFiles);         // Add "MICE file" filter
        fc.setFileFilter(miceFiles);                  // Show MICE files only by default
        
        int result = fc.showOpenDialog(this);        // Run dialog, return button clicked
        if (result == JFileChooser.APPROVE_OPTION) { // Also CANCEL_OPTION and ERROR_OPTION
            filename = fc.getSelectedFile();        // Obtain the selected File object
            
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String magicCookie = br.readLine();
                if(!magicCookie.equals(MAGIC_COOKIE)) throw new RuntimeException("Not a MICE file");
                String fileVersion = br.readLine();
                if(!fileVersion.equals(FILE_VERSION)) throw new RuntimeException("Incompatible MICE file format");
                
                emporium = new Emporium(br);                  
                view(Screen.ICE_CREAM_FLAVORS);
                view(Screen.MIX_IN_FLAVORS);
                view(Screen.CONTAINERS);
                view(Screen.ORDERS); 
                view(Screen.CUSTOMERS);                       // Update the user interface
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Unable to open " + filename + '\n' + e, 
                    "Failed", JOptionPane.ERROR_MESSAGE); 
             }
        }
    }

    protected void onSaveClick() {       
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(MAGIC_COOKIE + '\n');
            bw.write(FILE_VERSION + '\n');
            emporium.save(bw);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to open " + filename + '\n' + e,
                "Failed", JOptionPane.ERROR_MESSAGE); 
        }
    }

    protected void onSaveAsClick() {         
        final JFileChooser fc = new JFileChooser(filename);  // Create a file chooser dialog
        FileFilter miceFiles = new FileNameExtensionFilter("MICE files", "mice");
        fc.addChoosableFileFilter(miceFiles);         // Add "MICE file" filter
        fc.setFileFilter(miceFiles);                  // Show MICE files only by default
        
        int result = fc.showSaveDialog(this);        // Run dialog, return button clicked
        if (result == JFileChooser.APPROVE_OPTION) { // Also CANCEL_OPTION and ERROR_OPTION
            filename = fc.getSelectedFile();         // Obtain the selected File object
            if(!filename.getAbsolutePath().endsWith(".mice"))  // Ensure it ends with ".mice"
                filename = new File(filename.getAbsolutePath() + ".mice");
            onSaveClick();                       // Delegate to Save method
        }
    }

    private void view(Screen screen) {
         String title = "";
         StringBuilder s = new StringBuilder();
         switch(screen) {
             case ICE_CREAM_FLAVORS: 
                 title = "Ice Cream Flavors";
                 for(var t : emporium.iceCreamFlavor()) s.append(t.toString() + "<br/>");
                 break;
             case MIX_IN_FLAVORS: 
                 title = "Mix In Flavors";
                 for(var t : emporium.mixinFlavors()) s.append(t.toString() + "<br/>");
                 break;
             case CONTAINERS: 
                 title = "Containers";
                 for(var t : emporium.containers()) s.append(t.toString() + "<br/>");
                 break;
             case ORDERS:
                 title = "Orders";
                 Customer customer = new Customer("Toby", "123");
                 Order orders = new Order(customer);
                 int ordernumber = 1;
                 for(var t : emporium.orders()) { 
                    s.append("Order " + ordernumber++ + " $" + orders.price() + " For " + t.toString() + "<br/>");
                 }
                 break;
             case CUSTOMERS:
                 title = "Customers";
                 for(var t : emporium.customers()) s.append(t.toString() + "<br/>");
             default:
                 display.setText("PANIC: Invalid Displays type: " + display);
         }
         display.setText("<html><font size=+4>" + title + 
                         "<br/></font><font size=+2>" + s.toString() + 
                         "</font></html>");
     }

    protected void onAboutClick() {                 // Display About dialog
        JDialog about = new JDialog(this, "Mavs Ice Cream Emporium");
        about.setLayout(new BoxLayout(about.getContentPane(), BoxLayout.PAGE_AXIS));
        
        try {
            BufferedImage myPicture = ImageIO.read(new File("gui/logo.png"));
            Canvas logo = new Canvas(new ImageIcon(myPicture), 1, 2, 3, 4);
            about.add(logo);
        } catch(IOException e) {
        }
        
        JLabel title = new JLabel("<html>"
          + "<p><font size=+4>" + NAME + "</font></p>"
          + "</html>", JLabel.CENTER);
        about.add(title);

        JLabel subTitle = new JLabel("<html>"
        + "<br/><p><font size=+1>Mavs Ice Cream Emporium</font></p>"
        + "</html>", JLabel.CENTER);

        JLabel artists = new JLabel("<html>"
          + "<p>Version " + VERSION + "</p>"
          + "<p>Copyright 2022 by George F. Rice</p>"
          + "<p>Licensed under Gnu GPL 3.0</p>"
          + "<p>Logo by Schmidsi, per the Pixabay License</p>"
          + "<p><font size=-2>https://pixabay.com/en/ice-ice-cream-cone-ice-ball-pink-1429596/</font></p>"
          + "<p>Open file icon created by Smashicons - Flaticon</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icons/</font></p>"
          + "<p>Save file icon created by smashingstocks - Flaticon</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icons/</font></p>"
          + "<p>Save as file icon created by Freepik - Flaticon</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icons/</font></p>"
          + "<p>Ice Cream Flavor icon created by Freepik - Flaticon</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icons/</font></p>"
          + "<p>Mix In Flavor icon created by tulpahn - Flaticon</p>"
          + "<p><font size=-2>https://www.flaticom.com/free-icons/</font></p>"
          + "<p>Container icon created by Smashicons - Flaticon</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icons/</font></p>"
          + "<p>Order icon created by Peerapek Takpho - Flaticon</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icons/</font></p>"
          + "<p> Customer icon created by Smashicons - Flaticon</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icons/</font></p>"

          + "</html>", JLabel.CENTER);
        about.add(artists);
        
        JPanel panel = new JPanel();
        JButton ok = new JButton("OK");
        ok.addActionListener(event -> about.setVisible(false));
        panel.add(ok);
        about.add(panel);

        about.add(Box.createVerticalStrut(10));

        about.pack();
        about.setVisible(true);
     }

     private Emporium emporium;
     private JLabel display;
     //private JMenuItem scoop;
     private File filename;
}

package br.com.aqbs.controller;

// Capture.java

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.imageio.*;
import javax.imageio.stream.*;
import javax.swing.*;
//import net.sourceforge.tess4j.ITesseract;
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;
//import net.sourceforge.tess4j.util.LoadLibs;

/**
 * This class defines the application GUI and starts the application.
 */
public class Capture extends Thread{

    JFrame p;
    int i = 0;
    /**
     * Width and height of screen. A single screen is assumed.
     */

    Dimension dimScreenSize;

    /**
     * Component for presenting captured image.
     */
    ImageArea ia = new ImageArea();

    /**
     * Screen width and height as a Rectangle. This is a convenience for Robot's
     * createScreenCapture() method.
     */
    Rectangle rectScreenSize;

    /**
     * Robot is needed to capture screen contents.
     */
    static Robot robot;

    /**
     * To support the display of images that can't be fully displayed without
     * scrolling, the ImageArea component is placed into a JScrollPane.
     */
    JScrollPane jsp;

    /**
     * Construct a Capture GUI.
     *
     * @param title text appearing in the title bar of Capture's main window
     */
    public Capture() {
        
    }
    
    public Capture(String title) {
        // Place title in the title bar of Capture's main window.

      //  p = new JFrame();
       // p.setTitle(title);

        // Exit the application if user selects Close from system menu.
        //  setDefaultCloseOperation (EXIT_ON_CLOSE);
        // Save screen dimensions for initially positioning main window and
        // performing screen captures.
        
      //  dimScreenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Copy screen dimensions to Rectangle for use with Robot's
        // createScreenCapture() method.
      //  rectScreenSize = new Rectangle(dimScreenSize);

        // Construct a save file chooser. Initialize the starting directory to
        // the current directory, do not allow the user to select the "all files"
        // filter, and restrict the files that can be selected to those ending
        // with .jpg or .jpeg extensions.
        final JFileChooser fcSave = new JFileChooser();
        fcSave.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fcSave.setAcceptAllFileFilterUsed(false);
        fcSave.setFileFilter(new ImageFileFilter());

        // Create the application's menus.
        JMenuBar mb = new JMenuBar();

        JMenu menu = new JMenu("File");

        ActionListener al;

        JMenuItem mi = new JMenuItem("Save As...");
        mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.ALT_MASK));
        al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Disallow image saving if there is no image to save.
           //     System.out.println("AQUI1");
                if (ia.getImage() == null) {
                    showError("No captured image.");
                    return;
                }

                // Present the "save" file chooser without any file selected.
                // If the user cancels this file chooser, exit this method.
                fcSave.setSelectedFile(null);
                if (fcSave.showSaveDialog(p)
                        != JFileChooser.APPROVE_OPTION) {
                    return;
                }

                // Obtain the selected file. Validate its extension, which 
                // must be .jpg or .jpeg. If extension not present, append
                // .jpg extension.
                File file = fcSave.getSelectedFile();
                String path = file.getAbsolutePath().toLowerCase();
                if (!path.endsWith(".jpg") && !path.endsWith(".jpeg")) {
                    file = new File(path += ".jpg");
                }

                // If the file exists, inform the user, who might not want
                // to accidentally overwrite an existing file. Exit method
                // if the user specifies that it is not okay to overwrite
                // the file.
                System.out.println("AQUI2");
                if (file.exists()) {
                    int choice = JOptionPane.
                            showConfirmDialog(null,
                                    "Overwrite file?",
                                    "Capture",
                                    JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.NO_OPTION) {
                        return;
                    }
                }

                // If the file does not exist or the user gives permission,
                // save image to file.
                ImageWriter writer = null;
                ImageOutputStream ios = null;

                try {
                    // Obtain a writer based on the jpeg format.

                    Iterator iter;
                    iter = ImageIO.getImageWritersByFormatName("jpeg");

                    // Validate existence of writer.
                    if (!iter.hasNext()) {
                        showError("Unable to save image to jpeg file type.");
                        return;
                    }

                    // Extract writer.
                    writer = (ImageWriter) iter.next();

                    // Configure writer output destination.
                    ios = ImageIO.createImageOutputStream(file);
                    writer.setOutput(ios);
System.out.println("AQUI3");
                    // Set JPEG compression quality to 95%.
                    ImageWriteParam iwp = writer.getDefaultWriteParam();
                    iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    iwp.setCompressionQuality(0.99f);

                    // Write the image.
                    writer.write(null,
                            new IIOImage((BufferedImage) ia.getImage(), null, null),
                            iwp);
                } catch (IOException e2) {
                    showError(e2.getMessage());
                } finally {
                    try {
                        // Cleanup.

                        if (ios != null) {
                            ios.flush();
                            ios.close();
                        }

                        if (writer != null) {
                            writer.dispose();
                        }
                    } catch (IOException e2) {
                    }
                }
            }
        };

        mi.addActionListener(al);
        menu.add(mi);

        menu.addSeparator();

        mi = new JMenuItem("Exit");
        mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                InputEvent.ALT_MASK));
        mi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(mi);

        mb.add(menu);
System.out.println("AQUI4");
        menu = new JMenu("Capture");

        mi = new JMenuItem("Capture");
        mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                InputEvent.ALT_MASK));
        al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Hide Capture's main window so that it does not appear in
                // the screen capture.

                p.setVisible(false);

                // Perform the screen capture.
                BufferedImage biScreen;
                biScreen = robot.createScreenCapture(rectScreenSize);

                // Show Capture's main window for continued user interaction.
                p.setVisible(true);
System.out.println("AQUI5");
                // Update ImageArea component with the new image, and adjust
                // the scrollbars.
                ia.setImage(biScreen);

                jsp.getHorizontalScrollBar().setValue(0);
                jsp.getVerticalScrollBar().setValue(0);
            }
        };

        mi.addActionListener(al);
        menu.add(mi);

        mb.add(menu);

        mi = new JMenuItem("Crop");
        mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,
                InputEvent.ALT_MASK));
        al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crop ImageArea component and adjust the scrollbars if
                // cropping succeeds.
System.out.println("AQUI6");
                if (ia.crop()) {
                    
                    jsp.getHorizontalScrollBar().setValue(0);
                    jsp.getVerticalScrollBar().setValue(0);
                } else {
                    showError("Out of bounds.");
                }
            }
        };

        mi.addActionListener(al);
        menu.add(mi);

        mb.add(menu);

      //   Install these menus.
      p.setJMenuBar(mb);

     //   Install a scollable ImageArea component.
        p.getContentPane().add(jsp = new JScrollPane(ia));
//System.out.println("AQUI7");
     //    Size main window to half the screen's size, and center window.
        p.setSize(dimScreenSize.width / 2, dimScreenSize.height / 2);

       p.setLocation((dimScreenSize.width - dimScreenSize.width / 2) / 2,
                (dimScreenSize.height - dimScreenSize.height / 2) / 2);

     //    Display the GUI and start the event-handling thread.
        p.setVisible(true);
    }

    /**
     * Present an error message via a dialog box.
     *
     * @param message the message to be presented
     */
    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Capture",
                JOptionPane.ERROR_MESSAGE);
    }
    
    public void save() {
        
        
      // LocalDateTime a = LocalDateTime.now();

          
          
        try {
            ImageIO.write((BufferedImage)ia.getImage(), "png", new File("numero.png"));
        } catch (IOException ex) {
            Logger.getLogger(Capture.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    public void cap() {
        // Hide Capture's main window so that it does not appear in
        // the screen capture.

        p.setVisible(false);

        // Perform the screen capture.
        BufferedImage biScreen;
          Rectangle aa = new Rectangle(730, 778, 19, 16);
                biScreen = robot.createScreenCapture(aa);

        // Show Capture's main window for continued user interaction.
        p.setVisible(true);

        // Update ImageArea component with the new image, and adjust
        // the scrollbars.
        ia.setImage(biScreen);

       jsp.getHorizontalScrollBar().setValue(0);
       jsp.getVerticalScrollBar().setValue(0);
    }

    public void crop() {
        if (ia.crop()) {
            jsp.getHorizontalScrollBar().setValue(0);
            jsp.getVerticalScrollBar().setValue(0);
        } else {
            showError("Out of bounds.");
        }

    }

    /**
     * Application entry point.
     *
     * @param args array of command-line arguments
     */
    
    public void caputarImagem()  {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            showError(e.getMessage());
            System.exit(0);
        } catch (SecurityException e) {
            showError("Permission required to use Robot.");
            System.exit(0);
        }

        // Construct the GUI and begin the event-handling thread.
        Thread thr1 = new Thread();
     //   Capture a = new Capture("Capture");
      thr1.start();
        while (true) {
              
            thr1 = Thread.currentThread();
//            try {
//                
//                cap();
//              save();
//              identificarNumero();
//                Thread.sleep(100000);
//                cap();
//            } catch (Exception ex) {
//                System.out.println("Puxa, estava dormindo! Você me acordou");
//            }

            
        }
    }
  
    
    public String identificarNumero() {
//        File imageFile = new File("D:\\Documentos\\NetBeansProjects\\RoulleteBet\\numero.png");
//        ITesseract instance = new Tesseract();  // JNA Interface Mapping
//        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
//        File tessDataFolder = LoadLibs.extractTessResources("tessdata"); // Maven build bundles English data
//        instance.setDatapath("D:\\Downloads\\Tess4J\\");
//        //instance.setLanguage("eng");
//
//        try {
//            String result = instance.doOCR(imageFile);
//            System.out.println(result);
//            return result;
//        } catch (TesseractException e) {
//            System.err.println(e.getMessage());
//        }
        
        return null;
    }
    
    @Override
    public void run() {
        caputarImagem();
        
    }
    
    
}

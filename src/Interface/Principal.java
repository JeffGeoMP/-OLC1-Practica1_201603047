package Interface;

import Functionalities.Analyzer_Lexico;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author JeffGeo
 */
public class Principal extends javax.swing.JFrame {

    private String Direction_File;
    private final String Extension = "er";

    public Principal() {
        initComponents();
        Direction_File = "";                //Direction the file to the start
        Activate_Desactivate_JCX(false);    // Desactive JCombobox to the start
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        input = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jctrees = new javax.swing.JComboBox<>();
        jcft = new javax.swing.JComboBox<>();
        jctt = new javax.swing.JComboBox<>();
        jca = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        view = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        menu = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        open = new javax.swing.JMenuItem();
        save = new javax.swing.JMenuItem();
        saveas = new javax.swing.JMenuItem();
        xml = new javax.swing.JMenuItem();
        exit = new javax.swing.JMenuItem();
        tools = new javax.swing.JMenu();
        automatas = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        help = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        about = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Menu");

        input.setColumns(20);
        input.setForeground(new java.awt.Color(0, 0, 255));
        input.setRows(5);
        jScrollPane1.setViewportView(input);

        jLabel1.setText("Trees in System");

        jLabel2.setText("Following Table");

        jLabel3.setText("Transition Table");

        jLabel4.setText("Automatas");

        jctrees.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jcft.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jctt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Input File");

        view.setText("View Image");

        jLabel6.setText("Output");

        jTextArea2.setColumns(20);
        jTextArea2.setForeground(new java.awt.Color(0, 0, 255));
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        menu.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Menu", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        File.setText("   File   ");

        open.setText("Open File");
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });
        File.add(open);

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        File.add(save);

        saveas.setText("Save as");
        saveas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveasActionPerformed(evt);
            }
        });
        File.add(saveas);

        xml.setText("Generate Output XML");
        File.add(xml);

        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        File.add(exit);

        menu.add(File);

        tools.setText("   Tools   ");

        automatas.setText("Generate Automata");
        automatas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                automatasActionPerformed(evt);
            }
        });
        tools.add(automatas);

        jMenuItem1.setText("Analyze Entries");
        tools.add(jMenuItem1);

        menu.add(tools);

        help.setText("Help");

        jMenuItem2.setText("User Manual");
        help.add(jMenuItem2);

        jMenuItem3.setText("Technical Manual");
        help.add(jMenuItem3);

        about.setText("About");
        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });
        help.add(about);

        menu.add(help);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jctrees, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcft, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jctt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(view, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jctrees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jctt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(view)))
                .addGap(14, 14, 14)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
        About at = new About();
        at.setVisible(true);
    }//GEN-LAST:event_aboutActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        this.dispose();
    }//GEN-LAST:event_exitActionPerformed

    private void openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openActionPerformed
        OpenFile();
    }//GEN-LAST:event_openActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        if (!Direction_File.isEmpty()) {
            if (SaveFile()) {
                JOptionPane.showMessageDialog(null, "File Saved", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            if (SaveFileas()) {
                JOptionPane.showMessageDialog(null, "File Saved", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_saveActionPerformed

    private void saveasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveasActionPerformed
        if (SaveFileas()) {
            JOptionPane.showMessageDialog(null, "File Saved", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_saveasActionPerformed

    private void automatasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_automatasActionPerformed
        Analyzer_Lexico Al = new Analyzer_Lexico();
        Al.Analyzer(input.getText());
        System.out.println(Al.Imprimir());
    }//GEN-LAST:event_automatasActionPerformed

    private void Activate_Desactivate_JCX(boolean parameter) {     //Turn jcombobox on or off
        jctrees.setEnabled(parameter);
        jcft.setEnabled(parameter);
        jctt.setEnabled(parameter);
        jca.setEnabled(parameter);
    }

    //Open, Save and Save as for File of input----------------------------------
    private void OpenFile() {
        JFileChooser Select = new JFileChooser();                                            //New Object Chooser
        FileNameExtensionFilter Filter = new FileNameExtensionFilter("Files er", Extension); //Filter for FileChooser
        File file;

        Select.setFileFilter(Filter);
        if (Select.showDialog(null, "Open") == JFileChooser.APPROVE_OPTION) {
            file = Select.getSelectedFile();
            if (file.canRead()) {                     //If can read the file is open
                if (file.getName().endsWith(Extension)) {
                    Direction_File = file.getAbsolutePath();
                    String Doc = "";

                    try {
                        FileInputStream Input = new FileInputStream(file);
                        InputStreamReader Reader = new InputStreamReader(Input, "ISO-8859-1");  //Format and Save
                        BufferedReader Br1 = new BufferedReader(Reader);

                        int ascci;
                        while ((ascci = Br1.read()) != -1) {
                            char caracter = (char) ascci;
                            Doc += caracter;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Not Can Read File", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                    input.setText(Doc);
                } else {
                    JOptionPane.showMessageDialog(null, "File Not Supported", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Not Can Read File", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private Boolean SaveFile() {
        Boolean Verify = false;
        try {
            FileWriter Writer = new FileWriter(Direction_File);
            String txt = input.getText().replace("\n", "\r\n");
            PrintWriter Print = new PrintWriter(Writer);
            Print.print(txt);
            Writer.close();
            Verify = true;

        } catch (IOException ex) {
            Logger.getLogger(Principal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return Verify;
    }

    private Boolean SaveFileas() {
        Boolean Verify = false;
        JFileChooser Select = new JFileChooser();                                            //New Object Chooser
        FileNameExtensionFilter Filter = new FileNameExtensionFilter("Files er", Extension); //Filter for FileChooser
        File file;

        if (Select.showDialog(null, "Save") == JFileChooser.APPROVE_OPTION) {
            file = Select.getSelectedFile();
            if (file.getName().endsWith(Extension)) {
                String Doc = input.getText().replace("\n", "\r\n");

                try {
                    FileOutputStream Output = new FileOutputStream(file);
                    byte[] bytxt = Doc.getBytes();
                    Output.write(bytxt);
                    Verify = true;

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Principal.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return Verify;
    }
    //--------------------------------------------------------------------------
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu File;
    private javax.swing.JMenuItem about;
    private javax.swing.JMenuItem automatas;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenu help;
    private javax.swing.JTextArea input;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JComboBox<String> jca;
    private javax.swing.JComboBox<String> jcft;
    private javax.swing.JComboBox<String> jctrees;
    private javax.swing.JComboBox<String> jctt;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem open;
    private javax.swing.JMenuItem save;
    private javax.swing.JMenuItem saveas;
    private javax.swing.JMenu tools;
    private javax.swing.JButton view;
    private javax.swing.JMenuItem xml;
    // End of variables declaration//GEN-END:variables
}

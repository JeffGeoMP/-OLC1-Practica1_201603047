package Interface;

import Functionalities.Analyzer_Lexico;
import Functionalities.Expression;
import Functionalities.Set;
import Functionalities.Test;
import Functionalities.Type_ER;
import Structs.Tree;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
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

    private ArrayList<Object> Sets;
    private ArrayList<Object> Expressions;
    private ArrayList<Object> Tests;

    public Principal() {
        initComponents();
        Direction_File = "";                //Direction the file to the start
        Activate_Desactivate_JCX(false);    // Desactive JCombobox to the start

        Sets = new ArrayList();
        Expressions = new ArrayList();
        Tests = new ArrayList();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Group1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        input = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jctrees = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        view = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextArea();
        rtrees = new javax.swing.JRadioButton();
        rft = new javax.swing.JRadioButton();
        rtt = new javax.swing.JRadioButton();
        ra = new javax.swing.JRadioButton();
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

        jLabel1.setText("Expression in System");

        jctrees.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---------- Select Option ----------" }));

        jLabel5.setText("Input File");

        view.setText("View Image");
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });

        jLabel6.setText("Output");

        output.setColumns(20);
        output.setForeground(new java.awt.Color(0, 0, 255));
        output.setRows(5);
        jScrollPane2.setViewportView(output);

        Group1.add(rtrees);
        rtrees.setText("View Image of Tree");

        Group1.add(rft);
        rft.setText("View Image of Table Follow");

        Group1.add(rtt);
        rtt.setText("View Image of Table Transitions");

        Group1.add(ra);
        ra.setText("View Image of Automata");

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(ra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rtt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                        .addComponent(rtrees, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rft, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jctrees, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jctrees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rtrees)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rft)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rtt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ra)
                        .addGap(30, 30, 30)
                        .addComponent(view))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
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
        if (!input.getText().isEmpty()) {
            Sets.clear();
            Expressions.clear();
            Tests.clear();

            jctrees.removeAllItems();
            jctrees.addItem("---------- Select Option ----------");
            output.setText("");

            Analyzer_Lexico Al = new Analyzer_Lexico();
            Al.Analyzer(input.getText());
            Al.File_Separate(Sets, Expressions, Tests);

            for (int i = 0; i < Expressions.size(); i++) {
                Expression exp = (Expression) Expressions.get(i);
                ArrayList<Object> aux = new ArrayList();
                Al.Analyze_Expressions(aux, exp.getExpression());
                for (int j = 0; j < aux.size(); j++) {
                    Type_ER te = (Type_ER) aux.get(j);
                    exp.getTreeEX().Insert(this.Name_Only(te) + j, te);
                }
                exp.getTreeEX().Calculate();
                exp.getTreeEX().Table_Follow();
                exp.getTreeEX().Table_Transitions();
            }

        } else {
            JOptionPane.showMessageDialog(null, "There is Nothing in the Text Area", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        Activate_Desactivate_JCX(true);
        Load_JCombobox();
        output.setText(this.SetsSystem());
    }//GEN-LAST:event_automatasActionPerformed

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
        Analyzer_Lexico Al = new Analyzer_Lexico();

        if (jctrees.getSelectedIndex() != 0) {
            String current_id = (String) jctrees.getSelectedItem();
            Tree Current_Tree = this.FindExpression(current_id);
            if (Current_Tree != null) {
                if (rtrees.isSelected()) {
                    Current_Tree.GenerateImageTree(current_id + "Tree");
                    JOptionPane.showMessageDialog(null, "Mostrando: " + current_id, "Informaciont", JOptionPane.INFORMATION_MESSAGE);
                    Images img = new Images(current_id + "Tree");
                    img.setVisible(true);
                } else if (rft.isSelected()) {
                    Current_Tree.GenerateImageTableF(current_id + "TF");
                    JOptionPane.showMessageDialog(null, "Mostrando: " + current_id, "Informaciont", JOptionPane.INFORMATION_MESSAGE);
                    Images img = new Images(current_id + "TF");
                    img.setVisible(true);
                } else if (rtt.isSelected()) {
                    Current_Tree.GenerateImageTableT(current_id + "TT");
                    JOptionPane.showMessageDialog(null, "Mostrando: " + current_id, "Informaciont", JOptionPane.INFORMATION_MESSAGE);
                    Images img = new Images(current_id + "TT");
                    img.setVisible(true);
                } else if (ra.isSelected()) {
                    Current_Tree.GenerateImageAFD(current_id + "AFD");
                    JOptionPane.showMessageDialog(null, "Mostrando: " + current_id, "Informaciont", JOptionPane.INFORMATION_MESSAGE);
                    Images img = new Images(current_id + "AFD");
                    img.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Not Option Selected", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Not Option Selected in List", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_viewActionPerformed

    private void Activate_Desactivate_JCX(boolean parameter) {     //Turn jcombobox on or off
        jctrees.setEnabled(parameter);
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
    private String Name_Only(Type_ER x) {
        String name = "";
        if (x.getType() == 1) {
            if (x.getLexema().equalsIgnoreCase("+")) {
                name = "Mas";
            } else if (x.getLexema().equalsIgnoreCase("*")) {
                name = "Asterisco";
            } else if (x.getLexema().equalsIgnoreCase("?")) {
                name = "Interrogacion";
            } else if (x.getLexema().equalsIgnoreCase("|")) {
                name = "Or";
            } else {
                name = "Concatenacion";
            }
        } else if (x.getType() == 2) {
            name = "Cadena";
        } else {
            name = "Set";
        }
        return name;
    }

    private void Load_JCombobox() {
        for (Object temp : this.Expressions) {
            Expression x = (Expression) temp;
            jctrees.addItem(x.getIdentifier());
        }
    }

    private String SetsSystem() {
        String temp = output.getText();
        temp += "Conjuntos en Sistema: \n";
        for (Object x : Sets) {
            Set s = (Set) x;
            temp += "Id " + s.getIdentifier() + " Conjunto: " + s.getSet() + "\n";
        }
        for (Object x : Tests) {
            Test s = (Test) x;
            temp += "Expresion a Evaluar: " + s.getIdentifier() + " Cadena a Comprobar: " + s.getCadena() + "\n";
        }
        return temp;
    }

    private Tree FindExpression(String Id) {
        Tree result = null;
        for (Object x : Expressions) {
            Expression exp = (Expression) x;
            if (Id.equals(exp.getIdentifier())) {
                result = exp.getTreeEX();
                break;
            }
        }
        return result;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu File;
    private javax.swing.ButtonGroup Group1;
    private javax.swing.JMenuItem about;
    private javax.swing.JMenuItem automatas;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenu help;
    private javax.swing.JTextArea input;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jctrees;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem open;
    private javax.swing.JTextArea output;
    private javax.swing.JRadioButton ra;
    private javax.swing.JRadioButton rft;
    private javax.swing.JRadioButton rtrees;
    private javax.swing.JRadioButton rtt;
    private javax.swing.JMenuItem save;
    private javax.swing.JMenuItem saveas;
    private javax.swing.JMenu tools;
    private javax.swing.JButton view;
    private javax.swing.JMenuItem xml;
    // End of variables declaration//GEN-END:variables
}

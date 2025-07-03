
//package ui;
import models.Culture;
import dao.CultureDao;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ui.ParcelleForm;
import utils.UtilsFonction;

 public class CultureForm extends javax.swing.JFrame {

    /**
     * Creates new form CultureForm
     */
    CultureDao culturDao;
    public CultureForm() throws SQLException {
        initComponents();
        loadTypeCulture();
        loadCycleCulture();
        affichageCulture();
        getformuaire();
        resetInput();
        culturDao = new CultureDao();
    }

    private void loadTypeCulture() {
        typeCulture_cbx.removeAllItems();
        List<String> typeCulture = List.of("Argileux", "Sableux", "Limoneux", "Humifères");
        for (String type : typeCulture) {
            typeCulture_cbx.addItem(type);
        }
        typeCulture_cbx.setSelectedIndex(-1);
    }
    
    private void loadCycleCulture() {
        cycleCulture_cbx.removeAllItems();
        List<String> cycleCulture = List.of("Annuel", "Bisannuel", "Vivace", "Saisonnière");
        for (String cycle : cycleCulture) {
            cycleCulture_cbx.addItem(cycle);
        }
        cycleCulture_cbx.setSelectedIndex(-1);
    }
    
    private boolean validerFields() {
        String nomCulture = nomCulture_tf.getText().trim();
        String description = description_tf.getText().trim();
        String typeCulture = typeCulture_cbx.getSelectedIndex() == -1 ? "" : typeCulture_cbx.getSelectedItem().toString();
        String cycleCulture = cycleCulture_cbx.getSelectedIndex() == -1 ? "" : cycleCulture_cbx.getSelectedItem().toString();
        String rendementAttendu = rendementAttendu_tf.getText().trim();
        
        if(dateDebut_tf.getDate() == null || dateFin_tf.getDate() == null) {
            showMessageError("Veuillez sélectionner des dates valides");
            return false;
        }

        List<String> champs = List.of(nomCulture, description, typeCulture, cycleCulture, rendementAttendu);
        
        boolean testField = champs.stream().anyMatch(c -> c.isEmpty());
        
        if(testField){
            showMessageError("Veuillez remplir tous les champs");
            return false;
        }

        try {
            Double rendement = Double.valueOf(rendementAttendu);
            if(rendement < 0.0){
                showMessageError("Veuillez saisir un rendement positif.");
                return false;
            }
        } catch (NumberFormatException e) {
            showMessageError("Veuillez saisir un nombre valide pour le rendement");
            return false;
        } 
        
        return true;
    }
    
    private void showMessageError(String message){
       JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    private void resetInput(){
        nomCulture_tf.setText("");
        description_tf.setText("");
        typeCulture_cbx.setSelectedIndex(-1);
        cycleCulture_cbx.setSelectedIndex(-1);
        rendementAttendu_tf.setText("");
        dateDebut_tf.setDate(null);
        dateFin_tf.setDate(null);
    }

    private void affichageCulture() throws SQLException{
        try{
            List<Culture> allCulture = CultureDao.getAllCultures();
            UtilsFonction.displayDataInTable(allCulture, tableauCulture);
        }catch(ClassNotFoundException ex){
            Logger.getLogger(CultureForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Culture c;
    private void getformuaire() {
        tableauCulture.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tableauCulture.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        List<Culture> mylist = CultureDao.getAllCultures();
                        c = mylist.get(selectedRow);
                        if (c != null) {
                            nomCulture_tf.setText(c.getNomCulture());
                            description_tf.setText(c.getDescription());
                            typeCulture_cbx.setSelectedItem(c.getTypeCulture());
                            cycleCulture_cbx.setSelectedItem(c.getCycleCulture());
                            dateDebut_tf.setDate(c.getDateDebut());
                            dateFin_tf.setDate(c.getDateFin());
                            rendementAttendu_tf.setText(c.getRendementAttendu().toString());
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(CultureForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        senzela = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        nomCulture_tf = new javax.swing.JTextField();
        description_tf = new javax.swing.JTextField();
        typeCulture_cbx = new javax.swing.JComboBox<>();
        cycleCulture_cbx = new javax.swing.JComboBox<>();
        rendementAttendu_tf = new javax.swing.JTextField();
        ajouter_btn = new javax.swing.JButton();
        annuler_btn = new javax.swing.JButton();
        modifier_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableauCulture = new javax.swing.JTable();
        dateDebut_tf = new com.toedter.calendar.JDateChooser();
        dateFin_tf = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        senzela.setBackground(new java.awt.Color(0, 102, 0));
        senzela.setPreferredSize(new java.awt.Dimension(348, 356));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SenZelaGreen");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("avec");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cultivez l'avenir ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Intelligence");

        javax.swing.GroupLayout senzelaLayout = new javax.swing.GroupLayout(senzela);
        senzela.setLayout(senzelaLayout);
        senzelaLayout.setHorizontalGroup(
            senzelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(senzelaLayout.createSequentialGroup()
                .addGroup(senzelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(senzelaLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel4))
                    .addGroup(senzelaLayout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, senzelaLayout.createSequentialGroup()
                .addGap(0, 56, Short.MAX_VALUE)
                .addGroup(senzelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );
        senzelaLayout.setVerticalGroup(
            senzelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(senzelaLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addGap(109, 109, 109)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 0));
        jLabel5.setText("Cultures");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 0));
        jLabel6.setText("Nom Culture");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 0));
        jLabel7.setText("Description");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 0));
        jLabel8.setText("Type Culture");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 0));
        jLabel9.setText("Cycle Culture");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 0));
        jLabel10.setText("Date Début");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 0));
        jLabel11.setText("Date Fin");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 0));
        jLabel12.setText("Rendement Attendu");

        nomCulture_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomCulture_tfActionPerformed(evt);
            }
        });

        description_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                description_tfActionPerformed(evt);
            }
        });

        typeCulture_cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cycleCulture_cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        rendementAttendu_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rendementAttendu_tfActionPerformed(evt);
            }
        });

        ajouter_btn.setBackground(new java.awt.Color(0, 102, 0));
        ajouter_btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ajouter_btn.setForeground(new java.awt.Color(255, 255, 255));
        ajouter_btn.setText("Ajouter");
        ajouter_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouter_btnActionPerformed(evt);
            }
        });

        annuler_btn.setBackground(new java.awt.Color(0, 102, 0));
        annuler_btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        annuler_btn.setForeground(new java.awt.Color(255, 255, 255));
        annuler_btn.setText("Annuler");
        annuler_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annuler_btnActionPerformed(evt);
            }
        });

        modifier_btn.setBackground(new java.awt.Color(0, 102, 0));
        modifier_btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        modifier_btn.setForeground(new java.awt.Color(255, 255, 255));
        modifier_btn.setText("Modifier");
        modifier_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifier_btnActionPerformed(evt);
            }
        });

        tableauCulture.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nom Culture", "Description", "Cycle Culture", "Type Culture", "Rendement", "Stade", "Date Debut", "Date Fin"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableauCulture);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(317, 317, 317)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(ajouter_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(190, 190, 190)
                        .addComponent(annuler_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cycleCulture_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeCulture_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(description_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomCulture_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateDebut_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rendementAttendu_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateFin_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(106, 106, 106))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(modifier_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nomCulture_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rendementAttendu_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(description_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(typeCulture_cbx)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dateDebut_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cycleCulture_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dateFin_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ajouter_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(annuler_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifier_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(senzela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(senzela, javax.swing.GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nomCulture_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomCulture_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomCulture_tfActionPerformed

    private void description_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_description_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_description_tfActionPerformed

    private void rendementAttendu_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rendementAttendu_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rendementAttendu_tfActionPerformed

    private void annuler_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annuler_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_annuler_btnActionPerformed

    private void ajouter_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouter_btnActionPerformed
        // TODO add your handling code here:
        if(!validerFields()){
            return;
        }
        
        String nomCulture = nomCulture_tf.getText().trim(); 
        String description = description_tf.getText().trim();
        String typeCulture = typeCulture_cbx.getSelectedItem().toString();
        String cycleCulture = cycleCulture_cbx.getSelectedItem().toString();
        Date dateDebut = dateDebut_tf.getDate();
        Date dateFin = dateFin_tf.getDate();
        Double rendementAttendu = Double.valueOf(rendementAttendu_tf.getText().trim());
        
        Culture cultur = new Culture(nomCulture, description, typeCulture, cycleCulture, dateDebut, dateFin, rendementAttendu);
        
        try {
            boolean success = CultureDao.addCulture(cultur);
            if(success){
                JOptionPane.showMessageDialog(this, "Insertion réussie");
                resetInput();
                affichageCulture();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'insertion");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CultureForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ajouter_btnActionPerformed

    private void modifier_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifier_btnActionPerformed

        try {
            int selectedRow = tableauCulture.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Aucune ligne sélectionnée. Veuillez choisir une culture à modifier.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(!validerFields()) {
                return;
            }

            Culture c1 = new Culture(c.getIdCulture(),
                nomCulture_tf.getText(),   
                description_tf.getText(),
                typeCulture_cbx.getSelectedItem().toString(),
                cycleCulture_cbx.getSelectedItem().toString(),
                dateDebut_tf.getDate(),
                dateFin_tf.getDate(),
                Double.valueOf(rendementAttendu_tf.getText())
            );

            boolean success = CultureDao.updateCulture(c1);
            if (success) {
                JOptionPane.showMessageDialog(this, "Modification réussie");
                affichageCulture();
                resetInput();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la modification", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CultureForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_modifier_btnActionPerformed

    
    /**
     * @param args the command line arguments
     */
     public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ParcelleForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParcelleForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParcelleForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParcelleForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CultureForm().setVisible(true);
            }
        });
    }




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajouter_btn;
    private javax.swing.JButton annuler_btn;
    private javax.swing.JComboBox<String> cycleCulture_cbx;
    private com.toedter.calendar.JDateChooser dateDebut_tf;
    private com.toedter.calendar.JDateChooser dateFin_tf;
    private javax.swing.JTextField description_tf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modifier_btn;
    private javax.swing.JTextField nomCulture_tf;
    private javax.swing.JTextField rendementAttendu_tf;
    private javax.swing.JPanel senzela;
    private javax.swing.JTable tableauCulture;
    private javax.swing.JComboBox<String> typeCulture_cbx;
    // End of variables declaration//GEN-END:variables


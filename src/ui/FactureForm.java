
package ui;
import dao.FactureDao;
import models.Facture;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.UtilsFonction;

/**
 *
 * @author MoRF9
 */
public class FactureForm extends javax.swing.JFrame {
    
    private FactureDao factureDao;
    private Facture f;
    
    /**
     * Creates new form CultureForm
     */
    public FactureForm() {
        initComponents();
        factureDao = new FactureDao();
        loadClients(); // Charger les clients dans la combobox
        affichageFacture();
        getFormulaire();
        resetInput();
        loadModePaiement();
    }


// Déclarez la Map comme variable d'instance de la classe
    private Map<String, String> clientIdMap = new HashMap<>();

    private void loadClients() {
        try {
            // Réinitialiser la ComboBox et la Map
            client_cbx.removeAllItems();
            clientIdMap.clear();

            // Récupérer la liste des clients depuis le DAO
            List<String[]> clients = FactureDao.getClientsForComboBox();

            // Remplir la ComboBox et la Map
            for (String[] client : clients) {
                String nomComplet = client[1];  // Le nom complet à afficher
                String idClient = client[0];    // L'ID à stocker

                client_cbx.addItem(nomComplet); // Ajouter à la ComboBox
                clientIdMap.put(nomComplet, idClient); // Stocker l'association dans la Map
            }

            // Aucune sélection par défaut
            client_cbx.setSelectedIndex(-1);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FactureForm.class.getName()).log(Level.SEVERE, "Erreur de chargement des clients", ex);
            JOptionPane.showMessageDialog(this, 
                "Erreur de chargement de la liste des clients", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
   
    private void loadModePaiement() {
        modePaiement_cbx.removeAllItems();
        List<String> modePaiement = List.of("Espèce", "Wave", "Orange Money", "Carte Bancaire");
        for (String p : modePaiement) {
            modePaiement_cbx.addItem(p);
        }
        modePaiement_cbx.setSelectedIndex(-1);
    }
    private boolean validerFields() {
        String nomProduit = nomProduit_tf.getText().trim();
        String quantite = quantite_tf.getText().trim();
        String montant = montant_tf.getText().trim();
        String modePaiement = modePaiement_cbx.getSelectedItem() == null ? "" : modePaiement_cbx.getSelectedItem().toString();
        String client = client_cbx.getSelectedItem() == null ? "" : client_cbx.getSelectedItem().toString();
        
        if(nomProduit.isEmpty() || quantite.isEmpty() || montant.isEmpty() || modePaiement.isEmpty() || client.isEmpty()) {
            showMessageError("Veuillez remplir tous les champs");
            return false;
        }
        try {
            double qte = Double.parseDouble(quantite);
            if(qte <= 0) {
                showMessageError("La quantité doit être positive");
                return false;
            }
        } catch (NumberFormatException e) {
            showMessageError("La quantité doit être un nombre");
            return false;
        }
        
        try {
            double mont = Double.parseDouble(montant);
            if(mont <= 0) {
                showMessageError("Le montant doit être positif");
                return false;
            }
        } catch (NumberFormatException e) {
            showMessageError("Le montant doit être un nombre");
            return false;
        }
        
        return true;
    }
    
    private void showMessageError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
    
    private void resetInput() {
        nomProduit_tf.setText("");
        quantite_tf.setText("");
        montant_tf.setText("");
        modePaiement_cbx.setSelectedIndex(-1);
        client_cbx.setSelectedIndex(-1);
//        f = null;
    }
    
    private void getFormulaire() {
        tableauFacture.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tableauFacture.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        List<Facture> mylist = FactureDao.getAllFactures();
                        f = mylist.get(selectedRow);
                        if (f != null) {
                            nomProduit_tf.setText(f.getNomProduit());
                            quantite_tf.setText(String.valueOf(f.getQuantite()));
                            montant_tf.setText(String.valueOf(f.getMontantTotal()));
                            modePaiement_cbx.setSelectedItem(f.getModePaiement());
                             for (Map.Entry<String, String> entry : clientIdMap.entrySet()) {
                                if (entry.getValue().equals(String.valueOf(f.getIdClientF()))) {
                                    client_cbx.setSelectedItem(entry.getKey());
                                    break;
                                }
                            }
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(FactureForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    private void affichageFacture() {
        try {
            List<Facture> allFactures = factureDao.getAllFactures();
            UtilsFonction.displayDataInTable(allFactures, tableauFacture, List.of("idFacture", "idClientF"));
        } catch(SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FactureForm.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        nomProduit_tf = new javax.swing.JTextField();
        quantite_tf = new javax.swing.JTextField();
        modePaiement_cbx = new javax.swing.JComboBox<>();
        ajouter_btn = new javax.swing.JButton();
        annuler_btn = new javax.swing.JButton();
        montant_tf = new javax.swing.JTextField();
        modifier_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableauFacture = new javax.swing.JTable();
        client_cbx = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        senzela.setBackground(new java.awt.Color(0, 102, 0));
        senzela.setPreferredSize(new java.awt.Dimension(348, 356));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("SenZelaGreen");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("avec");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Cultivez l'avenir ");

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
                        .addGap(34, 34, 34)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(senzelaLayout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(senzelaLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel4))
                    .addGroup(senzelaLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel25)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        senzelaLayout.setVerticalGroup(
            senzelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(senzelaLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel23)
                .addGap(64, 64, 64)
                .addComponent(jLabel25)
                .addGap(26, 26, 26)
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addContainerGap(473, Short.MAX_VALUE))
        );

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 0));
        jLabel5.setText("Facture");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 0));
        jLabel6.setText("Nom Produit");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 0));
        jLabel7.setText("Quantite ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 0));
        jLabel8.setText("Montant Total");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 0));
        jLabel9.setText("Mode payement");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 0));
        jLabel12.setText("Client");

        nomProduit_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomProduit_tfActionPerformed(evt);
            }
        });

        quantite_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantite_tfActionPerformed(evt);
            }
        });

        modePaiement_cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        montant_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                montant_tfActionPerformed(evt);
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

        tableauFacture.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nom Produit", "Quantite", "Prix", "Mode Payement", "Nom Client", "Prenom Client"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableauFacture);

        client_cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(315, 315, 315))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomProduit_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(montant_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quantite_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(client_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(modePaiement_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(54, 54, 54))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(ajouter_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(196, 196, 196)
                .addComponent(annuler_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(modifier_btn)
                .addGap(89, 89, 89))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nomProduit_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modePaiement_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(quantite_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(client_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(montant_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(annuler_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ajouter_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifier_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(172, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(senzela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(senzela, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nomProduit_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomProduit_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomProduit_tfActionPerformed

    private void quantite_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantite_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantite_tfActionPerformed

    private void annuler_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annuler_btnActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_annuler_btnActionPerformed

    private void montant_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_montant_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_montant_tfActionPerformed

    private void ajouter_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouter_btnActionPerformed
        // TODO add your handling code here:
        
        try {
            if(!validerFields()) {
                return;
            }
            
            String nomProduit = nomProduit_tf.getText().trim();
            double quantite = Double.parseDouble(quantite_tf.getText().trim());
            double montant = Double.parseDouble(montant_tf.getText().trim());
            String modePaiement = modePaiement_cbx.getSelectedItem().toString();
            String nomClient = client_cbx.getSelectedItem().toString();
            long idClient = Long.parseLong(clientIdMap.get(nomClient));            
            Facture facture = new Facture(nomProduit, quantite, montant, modePaiement, idClient);
            
            boolean success = FactureDao.addFacture(facture);
            
            if(success) {
                JOptionPane.showMessageDialog(this, "Ajout réussi");
                resetInput();
                affichageFacture();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FactureForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ajouter_btnActionPerformed

    private void modifier_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifier_btnActionPerformed
        // TODO add your handling code here:
        
        try {
            if(!validerFields()) {
                return;
            }
            
            if(f == null) {
                JOptionPane.showMessageDialog(this, "Aucune facture sélectionnée");
                return;
            }
            
            String nomProduit = nomProduit_tf.getText().trim();
            double quantite = Double.parseDouble(quantite_tf.getText().trim());
            double montant = Double.parseDouble(montant_tf.getText().trim());
            String modePaiement = modePaiement_cbx.getSelectedItem().toString();
            String nomClient = client_cbx.getSelectedItem().toString();
            long idClient = Long.parseLong(clientIdMap.get(nomClient));
        
            Facture facture = new Facture(
                f.getIdFacture(),
                nomProduit,
                quantite,
                montant,
                modePaiement,
                idClient
            );
            
            boolean success = FactureDao.updateFacture(facture);
            
            if(success) {
                JOptionPane.showMessageDialog(this, "Modification réussie");
                resetInput();
                affichageFacture();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la modification");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FactureForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_modifier_btnActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public  void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException | InstantiationException | 
             IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(FactureForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
FactureForm form = new FactureForm();
form.setVisible(true);
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajouter_btn;
    private javax.swing.JButton annuler_btn;
    private javax.swing.JComboBox<String> client_cbx;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> modePaiement_cbx;
    private javax.swing.JButton modifier_btn;
    private javax.swing.JTextField montant_tf;
    private javax.swing.JTextField nomProduit_tf;
    private javax.swing.JTextField quantite_tf;
    private javax.swing.JPanel senzela;
    private javax.swing.JTable tableauFacture;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import dao.UtilisateurDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import models.Utilisateur;
import utils.UtilsFonction;

/**
 *
 * @author HP
 */
public class Agriculteur extends javax.swing.JFrame {
    private UtilisateurDao utilisateurDao;
    private Utilisateur u; // Pour stocker l'utilisateur sélectionné
    /**
     * Creates new form Agriculteur
     */
    
    
    public Agriculteur() {
        initComponents();
        setLocationRelativeTo(null); 
        utilisateurDao = new UtilisateurDao();

        initComboBoxes();
        setupButtonActions();
        affichageAgriculteurs();
        getFormulaire();
        resetInput();

    }

    private void initComboBoxes() {
        role_cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "agriculteur" }));
        genre_cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Homme", "Femme", "Autre" }));
    }
    
    private void setupButtonActions() {
        // Bouton Ajouter
        ajouter_btn1.addActionListener(e -> ajouterAgriculteur());
        
        // Bouton Annuler
        annuler_btn.addActionListener(e -> {
            new AdminDashboard().setVisible(true);
            this.dispose();
        });
    }

    
    private boolean validerFields() {
        String nom = nom_tf.getText().trim();
        String prenom = prenom_tf.getText().trim();
        String telephone = telephone_tf.getText().trim();
        String email = email_cbx.getText().trim();
        String mdp = new String(mdp_pf.getPassword()).trim();
        String role = role_cbx.getSelectedItem() == null ? "" : role_cbx.getSelectedItem().toString();
        String genre = genre_cbx.getSelectedItem() == null ? "" : genre_cbx.getSelectedItem().toString();
        
        if(nom.isEmpty() || prenom.isEmpty() || telephone.isEmpty() || 
           email.isEmpty() || mdp.isEmpty() || role.isEmpty() || genre.isEmpty()) {
            showMessageError("Veuillez remplir tous les champs obligatoires");
            return false;
        }
        
        if(!telephone.matches("^[0-9]{9}$")) {
            showMessageError("Le téléphone doit contenir 9 chiffres");
            return false;
        }
        
        if(!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            showMessageError("Veuillez entrer une adresse email valide");
            return false;
        }
        
        if(mdp.length() < 6) {
            showMessageError("Le mot de passe doit contenir au moins 6 caractères");
            return false;
        }
        
        return true;
    }
    
    private void showMessageError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
    
    private void showMessageSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Succès", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void affichageAgriculteurs() {
      try {
          List<Utilisateur> allAgriculteurs = utilisateurDao.getAgriculteurs();
          // Supposons que vous avez un tableau appelé tableauAgriculteurs
          UtilsFonction.displayDataInTable(allAgriculteurs, tableauAgriculteur, 
              List.of("idUser", "mdp","idCulture")); // Exclure les champs sensibles
      } catch(SQLException | ClassNotFoundException ex) {
          Logger.getLogger(Agriculteur.class.getName()).log(Level.SEVERE, null, ex);
          showMessageError("Erreur lors du chargement des agriculteurs");
    }
    }
    private void ajouterAgriculteur() {
        try {
            if(!validerFields()) {
                return;
            }
            
            Utilisateur agriculteur = new Utilisateur(
                nom_tf.getText(),
                prenom_tf.getText(),
                telephone_tf.getText(),
                adresse_tf.getText(),
                role_cbx.getSelectedItem().toString(),
                genre_cbx.getSelectedItem().toString(),
                email_cbx.getText(),
                new String(mdp_pf.getPassword())
            );
            
            boolean success = UtilisateurDao.ajouterAgriculteur(agriculteur);
            
            if(success) {
                showMessageSuccess("Agriculteur ajouté avec succès!");
                resetInput();
                affichageAgriculteurs();
            } else {
                showMessageError("Erreur lors de l'ajout de l'agriculteur");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Agriculteur.class.getName()).log(Level.SEVERE, null, ex);
            showMessageError("Erreur: " + ex.getMessage());
        }
    }
    
    private void modifierAgriculteur() {
    try {
        if(!validerFields()) {
            return;
        }
        
        if(u == null) {
            showMessageError("Aucun agriculteur sélectionné");
            return;
        }
        
        Utilisateur agriculteur = new Utilisateur(
            u.getIdUser(),
            nom_tf.getText(),
            prenom_tf.getText(),
            telephone_tf.getText(),
            adresse_tf.getText(),
            role_cbx.getSelectedItem().toString(),
            genre_cbx.getSelectedItem().toString(),
            email_cbx.getText(),
            new String(mdp_pf.getPassword())
        );
        
        boolean success = UtilisateurDao.updateUtilisateur(agriculteur);
        
        if(success) {
            showMessageSuccess("Modification réussie!");
            resetInput();
            affichageAgriculteurs();
        } else {
            showMessageError("Erreur lors de la modification");
        }
    } catch (SQLException | ClassNotFoundException ex) {
        Logger.getLogger(Agriculteur.class.getName()).log(Level.SEVERE, null, ex);
        showMessageError("Erreur de base de données: " + ex.getMessage());
    }
}
    
    private void getFormulaire() {
    tableauAgriculteur.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = tableauAgriculteur.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    List<Utilisateur> mylist = utilisateurDao.getAgriculteurs();
                    u = mylist.get(selectedRow);
                    if (u != null) {
                        // Remplir les champs avec les données de l'agriculteur sélectionné
                        nom_tf.setText(u.getNom());
                        prenom_tf.setText(u.getPrenom());
                        telephone_tf.setText(u.getTelephone());
                        adresse_tf.setText(u.getAdresse());
                        email_cbx.setText(u.getEmail());
                        mdp_pf.setText(u.getMdp());
                        
                        // Sélectionner les valeurs dans les combobox
                        role_cbx.setSelectedItem(u.getRole());
                        genre_cbx.setSelectedItem(u.getGenre());
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Agriculteur.class.getName()).log(Level.SEVERE, null, ex);
                    showMessageError("Erreur lors du chargement des données");
                }
            }
        }
    });
}
    
    
    private void resetInput() {
        nom_tf.setText("");
        prenom_tf.setText("");
        telephone_tf.setText("");
        adresse_tf.setText("");
        email_cbx.setText("");
        mdp_pf.setText("");
        role_cbx.setSelectedIndex(-1);
        genre_cbx.setSelectedIndex(-1);
        u = null;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        ajouter_btn = new javax.swing.JButton();
        senzela = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        nom_tf = new javax.swing.JTextField();
        prenom_tf = new javax.swing.JTextField();
        telephone_tf = new javax.swing.JTextField();
        mdp_pf = new javax.swing.JPasswordField();
        adresse_tf = new javax.swing.JTextField();
        role_cbx = new javax.swing.JComboBox<>();
        genre_cbx = new javax.swing.JComboBox<>();
        email_cbx = new javax.swing.JTextField();
        ajouter_btn1 = new javax.swing.JButton();
        annuler_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableauAgriculteur = new javax.swing.JTable();
        modifier_btn = new javax.swing.JButton();

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 0));
        jLabel5.setText("Facture");

        ajouter_btn.setBackground(new java.awt.Color(0, 102, 0));
        ajouter_btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ajouter_btn.setForeground(new java.awt.Color(255, 255, 255));
        ajouter_btn.setText("Ajouter");

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
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(senzelaLayout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(senzelaLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel4))
                    .addGroup(senzelaLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel3)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        senzelaLayout.setVerticalGroup(
            senzelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(senzelaLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(72, 72, 72)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addContainerGap(530, Short.MAX_VALUE))
        );

        jLabel6.setBackground(new java.awt.Color(0, 102, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 0));

        jLabel7.setBackground(new java.awt.Color(0, 102, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 0));
        jLabel7.setText("Agriculteurs");

        jLabel8.setBackground(new java.awt.Color(0, 102, 0));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 0));
        jLabel8.setText("Prenom");

        jLabel9.setBackground(new java.awt.Color(0, 102, 0));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 0));
        jLabel9.setText("Nom");

        jLabel10.setBackground(new java.awt.Color(0, 102, 0));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 0));
        jLabel10.setText("Mot de passe");

        jLabel11.setBackground(new java.awt.Color(0, 102, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 0));
        jLabel11.setText("Telephone");

        jLabel12.setBackground(new java.awt.Color(0, 102, 0));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 0));
        jLabel12.setText("Adresse");

        jLabel13.setBackground(new java.awt.Color(0, 102, 0));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 0));
        jLabel13.setText("Genre");

        jLabel14.setBackground(new java.awt.Color(0, 102, 0));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 0));
        jLabel14.setText("Role");

        jLabel15.setBackground(new java.awt.Color(0, 102, 0));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 0));
        jLabel15.setText("Email");

        nom_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nom_tfActionPerformed(evt);
            }
        });

        prenom_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prenom_tfActionPerformed(evt);
            }
        });

        telephone_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telephone_tfActionPerformed(evt);
            }
        });

        mdp_pf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mdp_pfActionPerformed(evt);
            }
        });

        adresse_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adresse_tfActionPerformed(evt);
            }
        });

        role_cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        role_cbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                role_cbxActionPerformed(evt);
            }
        });

        genre_cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        genre_cbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genre_cbxActionPerformed(evt);
            }
        });

        email_cbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                email_cbxActionPerformed(evt);
            }
        });

        ajouter_btn1.setBackground(new java.awt.Color(0, 102, 0));
        ajouter_btn1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ajouter_btn1.setForeground(new java.awt.Color(255, 255, 255));
        ajouter_btn1.setText("Ajouter");
        ajouter_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouter_btn1ActionPerformed(evt);
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

        tableauAgriculteur.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nom", "Prenom", "Telephone", "Adresse", "Role", "Genre", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableauAgriculteur);

        modifier_btn.setBackground(new java.awt.Color(0, 102, 0));
        modifier_btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        modifier_btn.setForeground(new java.awt.Color(255, 255, 255));
        modifier_btn.setText("Modifier");
        modifier_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifier_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(456, 456, 456)
                .addComponent(ajouter_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120)
                .addComponent(annuler_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(modifier_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(354, 354, 354)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(328, 328, 328)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(13, 13, 13))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(senzela, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(120, 120, 120)
                                                        .addComponent(jLabel6))
                                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(nom_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(prenom_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(mdp_pf, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(telephone_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(206, 206, 206)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(genre_cbx, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(role_cbx, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(adresse_tf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(email_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(699, 699, 699)
                        .addComponent(jLabel7)
                        .addGap(261, 261, 261)))
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(361, 361, 361)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(senzela, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nom_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adresse_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prenom_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(role_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(genre_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telephone_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email_cbx, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mdp_pf, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ajouter_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(annuler_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifier_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nom_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nom_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nom_tfActionPerformed

    private void prenom_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prenom_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prenom_tfActionPerformed

    private void telephone_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telephone_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telephone_tfActionPerformed

    private void adresse_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adresse_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adresse_tfActionPerformed

    private void email_cbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_email_cbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_email_cbxActionPerformed

    private void annuler_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annuler_btnActionPerformed
        // TODO add your handling code here:
        new AdminDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_annuler_btnActionPerformed

    private void ajouter_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouter_btn1ActionPerformed
        // TODO add your handling code here:
        ajouterAgriculteur();
    }//GEN-LAST:event_ajouter_btn1ActionPerformed

    private void modifier_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifier_btnActionPerformed
        // TODO add your handling code here:
        modifierAgriculteur();
        
        
    }//GEN-LAST:event_modifier_btnActionPerformed

    private void genre_cbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genre_cbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genre_cbxActionPerformed

    private void mdp_pfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mdp_pfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mdp_pfActionPerformed

    private void role_cbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_role_cbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_role_cbxActionPerformed

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
            java.util.logging.Logger.getLogger(Agriculteur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agriculteur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agriculteur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agriculteur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Agriculteur().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adresse_tf;
    private javax.swing.JButton ajouter_btn;
    private javax.swing.JButton ajouter_btn1;
    private javax.swing.JButton annuler_btn;
    private javax.swing.JTextField email_cbx;
    private javax.swing.JComboBox<String> genre_cbx;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField mdp_pf;
    private javax.swing.JButton modifier_btn;
    private javax.swing.JTextField nom_tf;
    private javax.swing.JTextField prenom_tf;
    private javax.swing.JComboBox<String> role_cbx;
    private javax.swing.JPanel senzela;
    private javax.swing.JTable tableauAgriculteur;
    private javax.swing.JTextField telephone_tf;
    // End of variables declaration//GEN-END:variables
}
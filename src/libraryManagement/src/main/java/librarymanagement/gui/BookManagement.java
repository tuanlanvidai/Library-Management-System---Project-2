/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package librarymanagement.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;  
import javax.swing.table.DefaultTableModel;  
import librarymanagement.dao.QuanLySachDAO;  
import librarymanagement.pojo.QuanLySachPOJO;    

/**
 *
 * @author hoangvu
 */
public class BookManagement extends javax.swing.JFrame {
    
    QuanLySachDAO dao;
    String keyword;
    private DefaultTableModel model;
    
    public BookManagement() {
        initComponents();
        dao = new QuanLySachDAO();
        model = (DefaultTableModel) tblDisplay.getModel(); 
        dao.addDataToTable(model, tblDisplay);
    }

    public BookManagement(String type) {
        initComponents();
        keyword = type;
        Title.setText(type + "" + "Book");
        dao = new QuanLySachDAO();
        AddItemToCBX();
        dao.addDataToTable(model, tblDisplay);  
    }
    
    public void emptyInp() {
    txtTitle.setText("");
    txtAuthor.setText("");
    txtPublishYear.setText("");
    txtTotalQuantity.setText("");
    txtAvailableQuantity.setText("");
    cbxCategory.setSelectedIndex(0);
}


    private void AddItemToCBX() {
         List<String> item = dao.getCategoryNames(); 
        for (int i = 0; i < item.size(); i++) {
            cbxCategory.addItem(item.get(i));
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

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        labelPhone1 = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        txtTotalQuantity = new javax.swing.JTextField();
        labelPassWord = new javax.swing.JLabel();
        txtAvailableQuantity = new javax.swing.JTextField();
        cbxCategory = new javax.swing.JComboBox<>();
        txtTitle = new javax.swing.JTextField();
        txtAuthor = new javax.swing.JTextField();
        txtPublishYear = new javax.swing.JTextField();
        labelName = new javax.swing.JLabel();
        labelRole = new javax.swing.JLabel();
        labelPhone = new javax.swing.JLabel();
        btnXacNhan = new javax.swing.JButton();
        btnHuybo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDisplay = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        Title.setBackground(new java.awt.Color(255, 255, 255));
        Title.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book.png"))); // NOI18N

        btnClose.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText("X");
        btnClose.setBorder(null);
        btnClose.setBorderPainted(false);
        btnClose.setContentAreaFilled(false);
        btnClose.setDefaultCapable(false);
        btnClose.addActionListener(this::btnCloseActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnClose)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        labelPhone1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelPhone1.setForeground(new java.awt.Color(255, 255, 255));
        labelPhone1.setText("Author");

        labelEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelEmail.setForeground(new java.awt.Color(255, 255, 255));
        labelEmail.setText("Total quantity");

        labelPassWord.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelPassWord.setForeground(new java.awt.Color(255, 255, 255));
        labelPassWord.setText("Quantity available");

        cbxCategory.addActionListener(this::cbxCategoryActionPerformed);

        txtTitle.addActionListener(this::txtTitleActionPerformed);

        labelName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelName.setForeground(new java.awt.Color(255, 255, 255));
        labelName.setText("Book Name");

        labelRole.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelRole.setForeground(new java.awt.Color(255, 255, 255));
        labelRole.setText("Category");

        labelPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelPhone.setForeground(new java.awt.Color(255, 255, 255));
        labelPhone.setText("Year of publication");

        btnXacNhan.setBackground(new java.awt.Color(0, 204, 204));
        btnXacNhan.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        btnXacNhan.setText("Save");
        btnXacNhan.addActionListener(this::btnXacNhanActionPerformed);

        btnHuybo.setBackground(new java.awt.Color(0, 204, 204));
        btnHuybo.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnHuybo.setForeground(new java.awt.Color(255, 255, 255));
        btnHuybo.setText("Cancel");
        btnHuybo.addActionListener(this::btnHuyboActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPhone1)
                            .addComponent(labelPassWord, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPhone)
                            .addComponent(labelRole, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEmail)
                            .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPublishYear)
                            .addComponent(txtTitle)
                            .addComponent(txtAuthor)
                            .addComponent(cbxCategory, 0, 318, Short.MAX_VALUE)
                            .addComponent(txtTotalQuantity)
                            .addComponent(txtAvailableQuantity)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHuybo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)))
                .addGap(84, 84, 84))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelPhone1)
                        .addGap(159, 159, 159)
                        .addComponent(labelPassWord, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelRole))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPublishYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPhone))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotalQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEmail))
                        .addGap(28, 28, 28)
                        .addComponent(txtAvailableQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuybo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        tblDisplay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book Id", "Book Name", "Author", "Category", "Year Of Publication", "Total Quantity", "Quantity available"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDisplay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDisplayMouseClicked1(evt);
            }
        });
        jScrollPane1.setViewportView(tblDisplay);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Number of books available");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDisplayMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyboActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnHuyboActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
            if (txtTitle.getText().isEmpty() && txtAuthor.getText().isEmpty() && txtPublishYear.getText().isEmpty() && txtTotalQuantity.getText().isEmpty() && txtAvailableQuantity.getText().isEmpty()
            || txtTitle.getText().isEmpty() && txtAuthor.getText().isEmpty() && txtPublishYear.getText().isEmpty()
            || txtAuthor.getText().isEmpty() && txtPublishYear.getText().isEmpty() && txtTotalQuantity.getText().isEmpty()
            || txtTitle.getText().isEmpty() && txtAuthor.getText().isEmpty() && txtTotalQuantity.getText().isEmpty()
            || txtTitle.getText().isEmpty() && txtPublishYear.getText().isEmpty() && txtTotalQuantity.getText().isEmpty()
            || txtTitle.getText().isEmpty() && txtAuthor.getText().isEmpty() || txtPublishYear.getText().isEmpty() && txtTotalQuantity.getText().isEmpty()
            || txtTitle.getText().isEmpty() && txtPublishYear.getText().isEmpty() && txtTotalQuantity.getText().isEmpty()
            || txtAuthor.getText().isEmpty() && txtPublishYear.getText().isEmpty()
            || txtTitle.getText().isEmpty() && txtPublishYear.getText().isEmpty()
            || txtTotalQuantity.getText().isEmpty() && txtPublishYear.getText().isEmpty()
            || txtAvailableQuantity.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter all the require information");
    } else if (txtTitle.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter book name");
    } else if (txtAuthor.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter Author Name");
    } else if (txtPublishYear.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please Enter Year of Publication");
    } else if (txtTotalQuantity.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter total quantity");
    } else if (txtAvailableQuantity.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter availble quantity");
    } // Confirm action handling
    else {
        String title = txtTitle.getText().trim();
        String author = txtAuthor.getText().trim();
        String category = cbxCategory.getSelectedItem().toString().trim();
        int publishYear = Integer.parseInt(txtPublishYear.getText().trim());
        int totalQuantity = Integer.parseInt(txtTotalQuantity.getText().trim());
        int availableQty = Integer.parseInt(txtAvailableQuantity.getText().trim());

        QuanLySachPOJO book = new QuanLySachPOJO(title, author, category, publishYear, totalQuantity, availableQty);
        switch (keyword) {
            case "Add ":
                if (dao.isBookDuplicate(title)) { // Kiểm tra trùng tên sách khi thêm
                    JOptionPane.showMessageDialog(null, "Book already exist, please enter again!");
                    return;
                }
                if (dao.addBook(book)) {
                    dao.addDataToTable(QuanLySach.model, QuanLySach.tblQuanLySach);
                    dao.addDataToTable(model, tblDisplay);
                    JOptionPane.showMessageDialog(null, "Book add successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error when add book.");
                }
                break;
            case "Edit ":
                int selectedRow = tblDisplay.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please choose the book you want to edit");
                    return;
                }
                DefaultTableModel model = (DefaultTableModel) tblDisplay.getModel();
                int bookId = (int) model.getValueAt(selectedRow, 0);
                book.setBookId(bookId);
                if (dao.editBook(book)) {
                    dao.addDataToTable(QuanLySach.model, QuanLySach.tblQuanLySach);
                    dao.addDataToTable(model, tblDisplay);
                    JOptionPane.showMessageDialog(null, "Book update successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error when update book.");
                }
                break;
            default:
                throw new AssertionError();
        }
        emptyInp();
    }
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void cbxCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCategoryActionPerformed

    private void txtTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTitleActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
     // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void tblDisplayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDisplayMouseClicked

    }//GEN-LAST:event_tblDisplayMouseClicked

    private void tblDisplayMouseClicked1(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDisplayMouseClicked1
        // TODO add your handling code here:
         int row = tblDisplay.getSelectedRow();
        int colum = 0;
        int id = Integer.parseInt(tblDisplay.getModel().getValueAt(row, colum).toString());
        QuanLySachPOJO book = dao.getBookById(id);
        if (book != null) {
            txtTitle.setText(book.getTitle());
            txtAuthor.setText(book.getAuthor());
            cbxCategory.setSelectedItem(book.getCategory());
            txtPublishYear.setText(String.valueOf(book.getPublishYear()));
            txtTotalQuantity.setText(String.valueOf(book.getTotalQuantity()));
            txtAvailableQuantity.setText(String.valueOf(book.getAvailableQty()));
        }
    }//GEN-LAST:event_tblDisplayMouseClicked1

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
            java.util.logging.Logger.getLogger(ManageEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Title;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnHuybo;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JComboBox<String> cbxCategory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelPassWord;
    private javax.swing.JLabel labelPhone;
    private javax.swing.JLabel labelPhone1;
    private javax.swing.JLabel labelRole;
    public static javax.swing.JTable tblDisplay;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtAvailableQuantity;
    private javax.swing.JTextField txtPublishYear;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JTextField txtTotalQuantity;
    // End of variables declaration//GEN-END:variables

}
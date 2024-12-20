/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package librarymanagement.gui;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import librarymanagement.dao.QuanLyDocGiaDAO;
import librarymanagement.pojo.QuanLyDocGia;
import librarymanagement.gui.QuanLyDocGiaJPanel;
import librarymanagement.pojo.Reader;

public class ReaderManagement extends javax.swing.JFrame {

    QuanLyDocGiaDAO dao;
    String keyword;
    private DefaultTableModel model;

    public ReaderManagement() {
        initComponents();
        dao = new QuanLyDocGiaDAO();
        model = (DefaultTableModel) tblDisplay.getModel();
        dao.addDataToTable(model, tblDisplay);
    }

    public ReaderManagement(String type) {
        initComponents();
        keyword = type;
        Title.setText(type + "" + "Reader");
        dao = new QuanLyDocGiaDAO();
        model = (DefaultTableModel) tblDisplay.getModel();
        dao.addDataToTable(model, tblDisplay);
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
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        labelPhone1 = new javax.swing.JLabel();
        labelRole = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        labelPhone = new javax.swing.JLabel();
        labelRole1 = new javax.swing.JLabel();
        btnConfirm = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        bttCancel = new javax.swing.JButton();
        txtRegisterDay = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDisplay = new javax.swing.JTable();
        labelName1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        Title.setBackground(new java.awt.Color(0, 153, 153));
        Title.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/quanlydocgia.png"))); // NOI18N

        btnClose.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText("X");
        btnClose.setBorder(null);
        btnClose.setBorderPainted(false);
        btnClose.setContentAreaFilled(false);
        btnClose.setDefaultCapable(false);
        btnClose.addActionListener(this::btnCloseActionPerformed);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Reader Management");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(275, 275, 275))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnClose)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        txtName.addActionListener(this::txtNameActionPerformed);

        labelPhone1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelPhone1.setForeground(new java.awt.Color(255, 255, 255));
        labelPhone1.setText("Address");

        labelRole.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelRole.setForeground(new java.awt.Color(255, 255, 255));
        labelRole.setText("Phone");

        txtPhone.addActionListener(this::txtPhoneActionPerformed);

        labelPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelPhone.setForeground(new java.awt.Color(255, 255, 255));
        labelPhone.setText("Registration date");

        labelRole1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelRole1.setForeground(new java.awt.Color(255, 255, 255));
        labelRole1.setText("Email");

        btnConfirm.setBackground(new java.awt.Color(0, 204, 204));
        btnConfirm.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnConfirm.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirm.setText("Save");
        btnConfirm.addActionListener(this::btnConfirmActionPerformed);

        bttCancel.setBackground(new java.awt.Color(0, 204, 204));
        bttCancel.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        bttCancel.setForeground(new java.awt.Color(255, 255, 255));
        bttCancel.setText("Close");
        bttCancel.addActionListener(this::bttCancelActionPerformed);

        tblDisplay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reader Id", "Reader Name", "Adress", "Phone", "Email", "Register Day"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true
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

        labelName1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelName1.setForeground(new java.awt.Color(255, 255, 255));
        labelName1.setText("Reader Name");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(194, 194, 194)
                        .addComponent(bttCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPhone)
                            .addComponent(labelPhone1)
                            .addComponent(labelRole, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelRole1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelName1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName)
                            .addComponent(txtAddress)
                            .addComponent(txtPhone)
                            .addComponent(txtEmail)
                            .addComponent(txtRegisterDay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(159, 159, 159)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelName1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelPhone1)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelRole, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelRole1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPhone)
                            .addComponent(txtRegisterDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bttCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bttCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_bttCancelActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        String name = txtName.getText();
        String address = txtAddress.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        if (name.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are mandatory.");
            return;
        }

        try {
            QuanLyDocGia reader;
            if (keyword.equals("Add ")) {
                Date date = txtRegisterDay.getDate();
                String registerDay = new SimpleDateFormat("yyyy-MM-dd").format(date);
                reader = new QuanLyDocGia(name, address, phone, email, registerDay, false);

                // Thêm độc giả mới
                if (dao.addReader(reader)) {
                    dao.addDataToTable(model, tblDisplay);
                    dao.addDataToTable(QuanLyDocGiaJPanel.model, QuanLyDocGiaJPanel.tblQuanLyDocGia);
                    JOptionPane.showMessageDialog(null, "add Reader Success!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to Add Reader.");
                }
            } else if (keyword.equals("Edit ")) {
                int selectedRow = tblDisplay.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please Choose a Reader to Edit.");
                    return;
                }
                int readerId = (int) model.getValueAt(selectedRow, 0); // Lấy ID từ cột đầu tiên của bảng
                Date date = txtRegisterDay.getDate();
                String registerDay = new SimpleDateFormat("yyyy-MM-dd").format(date);
                reader = new QuanLyDocGia(readerId, name, address, phone, email, registerDay, false);

                // Sửa thông tin độc giả
                if (dao.editReader(reader)) {
                    dao.addDataToTable(model, tblDisplay);
                    dao.addDataToTable(QuanLyDocGiaJPanel.model, QuanLyDocGiaJPanel.tblQuanLyDocGia);
                    JOptionPane.showMessageDialog(null, "Edit Successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Edit Failed.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Input Invalid. Please Check your input.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An Unexpected error has occurred.");
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void tblDisplayMouseClicked1(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDisplayMouseClicked1
        // TODO add your handling code here:
        if (keyword.equals("Edit ")) {
            int row = tblDisplay.getSelectedRow();
            int colum = 0;
            int id = Integer.parseInt(tblDisplay.getModel().getValueAt(row, colum).toString());
            QuanLyDocGia reader = dao.getReaderById(id);
            if (reader != null) {
                txtName.setText(reader.getTenDocGia());
                txtAddress.setText(reader.getDiaChi());
                txtPhone.setText(reader.getSoDienThoai());
                txtEmail.setText(reader.getEmail());
                txtRegisterDay.setDate(java.sql.Date.valueOf(reader.getNgayDangKy()));
            }
        }

    }//GEN-LAST:event_tblDisplayMouseClicked1

    private void txtPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReaderManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Title;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton bttCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelName1;
    private javax.swing.JLabel labelPhone;
    private javax.swing.JLabel labelPhone1;
    private javax.swing.JLabel labelRole;
    private javax.swing.JLabel labelRole1;
    private javax.swing.JTable tblDisplay;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    private com.toedter.calendar.JDateChooser txtRegisterDay;
    // End of variables declaration//GEN-END:variables

}

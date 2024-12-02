/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package librarymanagement.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import librarymanagement.dao.EmployeeDAO;
import librarymanagement.pojo.Employee;

/**
 *
 * @author duyanh
 */
public class QuanLyThuThu extends javax.swing.JPanel {

    private EmployeeDAO dao;
    public static DefaultTableModel model;
    /**
     * Creates new form QuanLyThuThu
     */
    public QuanLyThuThu() {
        initComponents();
        dao= new EmployeeDAO();
        btnCancelSearch.setVisible(false);
        dao.addDataFromDB(model, tblEmployee);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployee = new javax.swing.JTable();
        btnAdd1 = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        SearchPanel = new javax.swing.JPanel();
        txtQuery = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnCancelSearch = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));
        setPreferredSize(new java.awt.Dimension(1324, 646));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/quanlythuthu.png"))); // NOI18N
        jLabel1.setText("Quản lý thủ thư");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Danh sách thủ thư");

        tblEmployee.setBackground(new java.awt.Color(0, 102, 102));
        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Name", "Role", "Phone number", "Email", "Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmployee.setRowSelectionAllowed(false);
        tblEmployee.setSelectionBackground(new java.awt.Color(0, 255, 204));
        jScrollPane1.setViewportView(tblEmployee);
        if (tblEmployee.getColumnModel().getColumnCount() > 0) {
            tblEmployee.getColumnModel().getColumn(0).setResizable(false);
            tblEmployee.getColumnModel().getColumn(1).setResizable(false);
            tblEmployee.getColumnModel().getColumn(2).setResizable(false);
            tblEmployee.getColumnModel().getColumn(3).setResizable(false);
            tblEmployee.getColumnModel().getColumn(4).setResizable(false);
            tblEmployee.getColumnModel().getColumn(5).setResizable(false);
        }

        btnAdd1.setBackground(new java.awt.Color(0, 204, 204));
        btnAdd1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        btnAdd1.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd1.setText("Add");
        btnAdd1.addActionListener(this::btnAdd1ActionPerformed);

        btnEdit.setBackground(new java.awt.Color(0, 204, 204));
        btnEdit.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit");
        btnEdit.addActionListener(this::btnEditActionPerformed);

        btnDelete.setBackground(new java.awt.Color(0, 204, 204));
        btnDelete.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        SearchPanel.setBackground(new java.awt.Color(255, 255, 255));
        SearchPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtQuery.setToolTipText("");
        txtQuery.setBorder(null);
        txtQuery.addActionListener(this::txtQueryActionPerformed);

        btnSearch.setText("Search");
        btnSearch.setBorder(null);
        btnSearch.setBorderPainted(false);
        btnSearch.setContentAreaFilled(false);
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        btnCancelSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelSearch.setText("X");
        btnCancelSearch.setBorder(null);
        btnCancelSearch.setBorderPainted(false);
        btnCancelSearch.setContentAreaFilled(false);
        btnCancelSearch.addActionListener(this::btnCancelSearchActionPerformed);

        javax.swing.GroupLayout SearchPanelLayout = new javax.swing.GroupLayout(SearchPanel);
        SearchPanel.setLayout(SearchPanelLayout);
        SearchPanelLayout.setHorizontalGroup(
            SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchPanelLayout.createSequentialGroup()
                .addComponent(txtQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        SearchPanelLayout.setVerticalGroup(
            SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(txtQuery)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SearchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnAdd1)
                    .addComponent(btnDelete))
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQueryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQueryActionPerformed

    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
        // TODO add your handling code here:
        ManageEmployee manage = new ManageEmployee("Add");
        manage.setVisible(true);
    }//GEN-LAST:event_btnAdd1ActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        ManageEmployee manage = new ManageEmployee("Edit");
        manage.setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        ManageEmployee manage = new ManageEmployee("Delete");
        manage.setVisible(true);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        int id = Integer.parseInt(txtQuery.getText());
        List<Employee> employees = dao.SearchById(id);
        if(employees!=null){
            dao.addDataFromDBSearch(employees, model, tblEmployee);
            btnCancelSearch.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null, "Error");
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnCancelSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelSearchActionPerformed
        // TODO add your handling code here:
        dao.addDataFromDB(model, tblEmployee);
        btnCancelSearch.setVisible(false);
    }//GEN-LAST:event_btnCancelSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel SearchPanel;
    private javax.swing.JButton btnAdd1;
    private javax.swing.JButton btnCancelSearch;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tblEmployee;
    private javax.swing.JTextField txtQuery;
    // End of variables declaration//GEN-END:variables
}

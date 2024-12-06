/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package librarymanagement.gui;

import javax.swing.table.DefaultTableModel;
import librarymanagement.dao.QuanLySachDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import librarymanagement.pojo.QuanLySachPOJO;


/**
 *
 * @author duyanh
 */
public class QuanLySach extends javax.swing.JPanel {

    /**
     * Creates new form QuanLySach
     */
       
     private QuanLySachDAO dao;
    public static DefaultTableModel model;

    public QuanLySach() {
        initComponents();
        dao = new QuanLySachDAO();
        model = (DefaultTableModel) tblQuanLySach.getModel();  // Khởi tạo model cho bảng
        btnCancelSearch.setVisible(false);
        dao.addDataToTable(model, tblQuanLySach); // Lấy dữ liệu sách từ cơ sở dữ liệu và thêm vào bảng
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
        txtQuery = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnCancelSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQuanLySach = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book.png"))); // NOI18N
        jLabel1.setText("Quản lý Sách");

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

        tblQuanLySach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Tác giả", "Thể loại", "Năm xuất bản", "Tổng số lượng", "Số lượng hiện có", "Đã xóa"
            }
        ));
        jScrollPane1.setViewportView(tblQuanLySach);

        btnThem.setBackground(new java.awt.Color(0, 204, 204));
        btnThem.setText("Thêm");
        btnThem.addActionListener(this::btnThemActionPerformed);

        btnSua.setBackground(new java.awt.Color(0, 204, 204));
        btnSua.setText("Sửa");
        btnSua.addActionListener(this::btnSuaActionPerformed);

        btnXoa.setBackground(new java.awt.Color(0, 204, 204));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(this::btnXoaActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1004, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(278, 278, 278))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuery)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(116, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQueryActionPerformed
        // TODO add your handling code here:
         btnSearchActionPerformed(evt);
    }//GEN-LAST:event_txtQueryActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        // Lấy ID sách từ txtQuery và gọi phương thức tìm kiếm
        String query = txtQuery.getText().trim();
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ID sách để tìm kiếm.");
            return;
        }

        try {
            int bookId = Integer.parseInt(query); // Chuyển đổi ID sách từ chuỗi sang số nguyên

            // Gọi phương thức tìm kiếm sách theo ID
            List<QuanLySachPOJO> books = dao.searchBookById(bookId);

            // Kiểm tra kết quả tìm kiếm
            if (books != null && !books.isEmpty()) {
                dao.addDataFromSearch(books, model, tblQuanLySach);  // Thêm dữ liệu vào bảng
                btnCancelSearch.setVisible(true);  // Hiển thị nút hủy tìm kiếm
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy sách với ID: " + bookId);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập một ID hợp lệ.");
        }

    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnCancelSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelSearchActionPerformed
        dao.addDataToTable(model, tblQuanLySach);
        btnCancelSearch.setVisible(false);
    }//GEN-LAST:event_btnCancelSearchActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        BookManagement manage = new BookManagement("Thêm");
        manage.setVisible(true);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        BookManagement manage = new BookManagement("Thêm");
        manage.setVisible(true);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // Mở cửa sổ BookManagement với chế độ "Xóa"
        BookManagement manage = new BookManagement("Xóa");
        manage.setVisible(true);
    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelSearch;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblQuanLySach;
    private javax.swing.JTextField txtQuery;
    // End of variables declaration//GEN-END:variables
}

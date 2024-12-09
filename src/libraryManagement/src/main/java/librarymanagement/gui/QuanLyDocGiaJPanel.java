/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package librarymanagement.gui;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import librarymanagement.dao.QuanLyDocGiaDAO;
import librarymanagement.pojo.QuanLyDocGia;



public class QuanLyDocGiaJPanel extends javax.swing.JPanel {
    
    private QuanLyDocGiaDAO dao;
    public static DefaultTableModel model;

    public QuanLyDocGiaJPanel() {
         initComponents();
        dao = new QuanLyDocGiaDAO();
        model = (DefaultTableModel) tblQuanLyDocGia.getModel();  // Khởi tạo model cho bảng
        btnCancelSearch.setVisible(false);
        dao.addDataToTable(model, tblQuanLyDocGia); // Lấy dữ liệu độc giả từ cơ sở dữ liệu và thêm vào bảng
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
        tblQuanLyDocGia = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/docgia.png"))); // NOI18N
        jLabel1.setText("Quản lý độc giả");

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

        tblQuanLyDocGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã độc giả", "Tên độc giả", "Đia chỉ", "Điện thoại", "Email", "Ngày đăng ký"
            }
        ));
        jScrollPane1.setViewportView(tblQuanLyDocGia);

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
                        .addGap(81, 81, 81)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 931, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(174, 174, 174)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(172, 172, 172)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(txtQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(209, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuery)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(161, 161, 161))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQueryActionPerformed
        // TODO add your handling code here:
        btnSearchActionPerformed(evt);
    }//GEN-LAST:event_txtQueryActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
            // Lấy giá trị từ ô tìm kiếm
    String query = txtQuery.getText().trim();

    // Kiểm tra nếu ô tìm kiếm trống
    if (query.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin để tìm kiếm.");
        btnCancelSearch.setVisible(false);  // Ẩn nút hủy tìm kiếm khi ô tìm kiếm trống
        return;
    }

    try {
        // Kiểm tra nếu chuỗi nhập vào là một số -> tìm kiếm theo ID
        if (query.matches("\\d+")) {  // Kiểm tra xem có phải số nguyên không
            int readerId = Integer.parseInt(query);  // Chuyển đổi chuỗi thành số nguyên

            // Gọi phương thức tìm kiếm độc giả theo ID
            List<QuanLyDocGia> readers = dao.searchReaderById(readerId);

            // Kiểm tra kết quả tìm kiếm
            if (readers != null && !readers.isEmpty()) {
                dao.addDataFromSearch(readers, model, tblQuanLyDocGia);  // Thêm dữ liệu vào bảng
                btnCancelSearch.setVisible(true);  // Hiển thị nút hủy tìm kiếm
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy độc giả với ID: " + readerId);
                btnCancelSearch.setVisible(false);  // Ẩn nút hủy tìm kiếm nếu không có kết quả
            }
        } else {
            // Tìm kiếm theo tên độc giả (nếu chuỗi nhập vào không phải là số)
            List<QuanLyDocGia> readers = dao.searchReader(query);

            // Kiểm tra kết quả tìm kiếm
            if (readers != null && !readers.isEmpty()) {
                dao.addDataFromSearch(readers, model, tblQuanLyDocGia);  // Thêm dữ liệu vào bảng
                btnCancelSearch.setVisible(true);  // Hiển thị nút hủy tìm kiếm
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy độc giả với tên: " + query);
                btnCancelSearch.setVisible(false);  // Ẩn nút hủy tìm kiếm nếu không có kết quả
            }
        }
    } catch (Exception e) {
        // Nếu có lỗi xảy ra
        JOptionPane.showMessageDialog(null, "Có lỗi khi tìm kiếm độc giả.");
    }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnCancelSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelSearchActionPerformed
        dao.addDataToTable(model, tblQuanLyDocGia);
        btnCancelSearch.setVisible(false);
    }//GEN-LAST:event_btnCancelSearchActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        ReaderManagement manage = new ReaderManagement("Add");
        manage.setVisible(true);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        ReaderManagement manage = new ReaderManagement("Edit");
        manage.setVisible(true);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

        ReaderManagement manage = new ReaderManagement("Delete");
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
    public static javax.swing.JTable tblQuanLyDocGia;
    private javax.swing.JTextField txtQuery;
    // End of variables declaration//GEN-END:variables
}

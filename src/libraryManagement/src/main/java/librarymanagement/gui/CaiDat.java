/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package librarymanagement.gui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import librarymanagement.dao.CaiDatDAO;
import librarymanagement.dao.TheLoaiDAO;
import librarymanagement.pojo.Setting;
import librarymanagement.util.DatabaseConnection;

/**
 *
 * @author duyanh
 */
public class CaiDat extends javax.swing.JPanel {

    /**
     * Creates new form CaiDat
     */
    
    private CaiDatDAO caiDatDAO;
    private TheLoaiDAO theLoaiDAO;

    public CaiDat() {
        try {
            // Lấy kết nối từ DatabaseConnection
            Connection connection = DatabaseConnection.getConnection();

            // Khởi tạo DAO với kết nối
            this.caiDatDAO = new CaiDatDAO(connection);
            this.theLoaiDAO = new TheLoaiDAO(connection);
            // Tạo giao diện
            initComponents();
            loadSettings();
            loadTheLoai();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
    }

    private void loadSettings() {
        try {
            Setting setting = caiDatDAO.getSetting();
            if (setting != null) {
                maxBorrowDays_txt.setText(String.valueOf(setting.getMaxBorrowDays()));
                lateFeePerDay_txt.setText(String.valueOf(setting.getLateFeePerDay()));
                maxBooksBorrowed_txt.setText(String.valueOf(setting.getMaxBooksBorrowed()));
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu cài đặt!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }
   
    private void loadTheLoai() {
    try {
        List<String> theLoaiList = theLoaiDAO.getAllTheLoai();
        DefaultTableModel model = new DefaultTableModel(new String[]{"Tên thể loại"}, 0);
        categoryTbl.setModel(model);
        model.setRowCount(0); // Xóa dữ liệu cũ trên bảng

        for (String theLoai : theLoaiList) {
            model.addRow(new Object[]{theLoai});
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách thể loại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        maxBorrowDays_txt = new javax.swing.JTextField();
        lateFeePerDay_txt = new javax.swing.JTextField();
        maxBooksBorrowed_txt = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        settingSaveBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        categoryTbl = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        deleteCategory = new javax.swing.JButton();
        editCategory = new javax.swing.JButton();
        addCategory = new javax.swing.JButton();
        categoryName_txt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(900, 706));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/setting.png"))); // NOI18N
        jLabel1.setText("Cài đặt");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel2.setText("Chỉnh sửa quy định");

        jLabel15.setText("Số tiền phải nộp theo ngày khi trả trễ sách:");

        jLabel16.setText("Số sách độc giả mượn tối đa :");

        maxBorrowDays_txt.addActionListener(this::maxBorrowDays_txtActionPerformed);

        jLabel19.setText("Số ngày độc giả mượn tối đa :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(maxBorrowDays_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lateFeePerDay_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(maxBooksBorrowed_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(88, 88, 88))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(jLabel19)
                    .addContainerGap(862, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxBorrowDays_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lateFeePerDay_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxBooksBorrowed_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel19)
                    .addContainerGap(61, Short.MAX_VALUE)))
        );

        settingSaveBtn.setText("Lưu thông tin");
        settingSaveBtn.addActionListener(this::settingSaveBtnActionPerformed);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(852, Short.MAX_VALUE)
                .addComponent(settingSaveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(settingSaveBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel9.setText("Thể loại");

        categoryTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Tên thể loại"
            }
        ));
        jScrollPane1.setViewportView(categoryTbl);

        jLabel3.setText("Danh sách thể loại");

        deleteCategory.setText("Xoá");
        deleteCategory.addActionListener(this::deleteCategoryActionPerformed);

        editCategory.setText("Sửa");
        editCategory.addActionListener(this::editCategoryActionPerformed);

        addCategory.setText("Thêm");
        addCategory.addActionListener(this::addCategoryActionPerformed);

        categoryName_txt.addActionListener(this::categoryName_txtActionPerformed);

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel4.setText("Tên thể loại:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(categoryName_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(249, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoryName_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(addCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(deleteCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(editCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addGap(36, 36, 36)
                .addComponent(jLabel3)
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void settingSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingSaveBtnActionPerformed
        try {
        // Kiểm tra dữ liệu từ các ô nhập liệu
        if (maxBorrowDays_txt.getText().trim().isEmpty() ||
            lateFeePerDay_txt.getText().trim().isEmpty() ||
            maxBooksBorrowed_txt.getText().trim().isEmpty()) {
            
            // Hiển thị lỗi nếu bất kỳ ô nào bị trống
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ tất cả các trường!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Chuyển đổi dữ liệu từ các ô nhập liệu
        int maxBorrowDays = Integer.parseInt(maxBorrowDays_txt.getText().trim());
        int lateFeePerDay = Integer.parseInt(lateFeePerDay_txt.getText().trim());
        int maxBooksBorrowed = Integer.parseInt(maxBooksBorrowed_txt.getText().trim());

        // Gọi DAO để cập nhật dữ liệu vào cơ sở dữ liệu
        caiDatDAO.updateSetting(maxBorrowDays, lateFeePerDay, maxBooksBorrowed);

        // Cập nhật lại dữ liệu trên giao diện
        loadSettings();

        // Thông báo thành công
        JOptionPane.showMessageDialog(this, "Cập nhật cài đặt thành công!");
    } catch (NumberFormatException ex) {
        // Xử lý lỗi khi nhập không phải số
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException ex) {
        // Xử lý lỗi kết nối cơ sở dữ liệu
        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_settingSaveBtnActionPerformed

    private void maxBorrowDays_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxBorrowDays_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maxBorrowDays_txtActionPerformed

    private void addCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCategoryActionPerformed
        try {
        String newTheLoai = categoryName_txt.getText().trim();

        if (newTheLoai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên thể loại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        theLoaiDAO.addTheLoai(newTheLoai); // Thêm thể loại vào cơ sở dữ liệu
        loadTheLoai(); // Cập nhật lại bảng
        JOptionPane.showMessageDialog(this, "Thêm thể loại thành công!");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi thêm thể loại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_addCategoryActionPerformed

    private void categoryName_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryName_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryName_txtActionPerformed

    private void editCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editCategoryActionPerformed
        try {
            int selectedRow = categoryTbl.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn thể loại cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String oldName = (String) categoryTbl.getValueAt(selectedRow, 0); // Tên thể loại cũ
            String newName = categoryName_txt.getText().trim(); // Tên thể loại mới

            if (newName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên thể loại mới!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            theLoaiDAO.updateTheLoai(oldName, newName); // Cập nhật trong cơ sở dữ liệu
            loadTheLoai(); // Cập nhật lại bảng
            JOptionPane.showMessageDialog(this, "Sửa thể loại thành công!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi sửa thể loại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_editCategoryActionPerformed

    private void deleteCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCategoryActionPerformed
        try {
            // Lấy hàng được chọn từ bảng
            int selectedRow = categoryTbl.getSelectedRow(); // Đổi từ categoryName_txt sang categoryTable

            // Kiểm tra xem có dòng nào được chọn không
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn thể loại cần xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lấy tên thể loại từ bảng
            String categoryName = (String) categoryTbl.getValueAt(selectedRow, 0);

            // Xóa thể loại trong cơ sở dữ liệu
            theLoaiDAO.deleteTheLoai(categoryName);

            // Cập nhật lại danh sách trên giao diện
            loadTheLoai();

            // Hiển thị thông báo thành công
            JOptionPane.showMessageDialog(this, "Xóa thể loại thành công!");
        } catch (SQLException e) {
            // Xử lý lỗi khi xóa
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa thể loại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteCategoryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCategory;
    private javax.swing.JTextField categoryName_txt;
    private javax.swing.JTable categoryTbl;
    private javax.swing.JButton deleteCategory;
    private javax.swing.JButton editCategory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lateFeePerDay_txt;
    private javax.swing.JTextField maxBooksBorrowed_txt;
    private javax.swing.JTextField maxBorrowDays_txt;
    private javax.swing.JButton settingSaveBtn;
    // End of variables declaration//GEN-END:variables
}

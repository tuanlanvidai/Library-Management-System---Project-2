/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package librarymanagement.gui;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import librarymanagement.dao.MuonTraSachDAO;
import librarymanagement.pojo.Reader;
import librarymanagement.pojo.ReturnBook;

/**
 *
 * @author lantr
 */
public class BookReturn extends javax.swing.JFrame {

    private MuonTraSachDAO muonTraSachDAO;
    private List<Reader> fullReaderList;
    private MuonTraSach muonTraSachPanel;

    /**
     * Creates new form BookReturn
     */
    public BookReturn() {
        initComponents();
        setTitle("Phiếu trả sách");
        initDAO();
        loadReaderData();
        loadStatusNames();
        addDocGiaSelectionListener();
        addPhieuMuonSelectionListener();
    }

    public void setReturnInfo(int readerId, String readerName, String email, String phoneNumber, int borrowId, String bookTitle, java.sql.Date dueDate) {
        txtMaDocGia.setText(readerId > 0 ? String.valueOf(readerId) : "");
        txtTenDocGia.setText(readerName != null ? readerName : "");
        txtEmail.setText(email != null ? email : "");
        txtSDT.setText(phoneNumber != null ? phoneNumber : "");
        txtMaPM.setText(borrowId > 0 ? String.valueOf(borrowId) : "");
        txtTenSach.setText(bookTitle != null ? bookTitle : "");
        txtNgayPhaiTra.setText(dueDate != null ? dueDate.toString() : "");
    }

    private void initDAO() {
        muonTraSachDAO = new MuonTraSachDAO();
    }

    private void loadReaderData() {
        DefaultTableModel model = (DefaultTableModel) tblDocGia.getModel();
        model.setRowCount(0);
        fullReaderList = muonTraSachDAO.getAllReaders();

        for (Reader reader : fullReaderList) {
            model.addRow(new Object[]{
                reader.getReaderId(),
                reader.getName(),
                reader.getEmail(),
                reader.getPhoneNumber(),
                reader.getAddress(),
                reader.getRegisterDay()
            });
        }
    }

    private void loadStatusNames() {
        List<String> statusNames = muonTraSachDAO.getStatusNames();
        cmbDamage.removeAllItems();

        for (String status : statusNames) {
            cmbDamage.addItem(status);
        }
    }

    private void filterReaderTableById(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblDocGia.getModel();
        model.setRowCount(0);

        for (Reader reader : fullReaderList) {
            if (String.valueOf(reader.getReaderId()).contains(keyword)) {
                model.addRow(new Object[]{
                    reader.getReaderId(),
                    reader.getName(),
                    reader.getEmail(),
                    reader.getPhoneNumber(),
                    reader.getAddress(),
                    reader.getRegisterDay()
                });
            }
        }
    }

    private void filterReaderTableByName(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblDocGia.getModel();
        model.setRowCount(0);

        for (Reader reader : fullReaderList) {
            String normalizedKeyword = normalizeString(keyword).toLowerCase();
            String normalizedName = normalizeString(reader.getName()).toLowerCase();
            if (normalizedName.contains(normalizedKeyword)) {
                model.addRow(new Object[]{
                    reader.getReaderId(),
                    reader.getName(),
                    reader.getEmail(),
                    reader.getPhoneNumber(),
                    reader.getAddress(),
                    reader.getRegisterDay()
                });
            }
        }
    }

    private String normalizeString(String input) {
        return java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }

    public void loadBorrowRecordsByReader(int readerId) {
        DefaultTableModel model = (DefaultTableModel) tblPhieuMuon.getModel();
        model.setRowCount(0);

        List<ReturnBook> borrowRecords = muonTraSachDAO.getBorrowedBooksWithFine(readerId);

        for (ReturnBook record : borrowRecords) {
            model.addRow(new Object[]{
                record.getBorrowId(),
                txtTenDocGia.getText(),
                record.getBookTitle(),
                record.getBorrowDate(),
                record.getDueDate(),
                record.getFineAmount()
            });
        }
    }

    private void addDocGiaSelectionListener() {
        tblDocGia.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblDocGia.getSelectedRow();
                if (selectedRow >= 0) {
                    int readerId = Integer.parseInt(tblDocGia.getValueAt(selectedRow, 0).toString());
                    String readerName = tblDocGia.getValueAt(selectedRow, 1).toString();
                    String email = tblDocGia.getValueAt(selectedRow, 2).toString();
                    String phoneNumber = tblDocGia.getValueAt(selectedRow, 3).toString();

                    txtMaDocGia.setText(String.valueOf(readerId));
                    txtTenDocGia.setText(readerName);
                    txtEmail.setText(email);
                    txtSDT.setText(phoneNumber);

                    loadBorrowRecordsByReader(readerId);
                }
            }
        });
    }

    private void addPhieuMuonSelectionListener() {
        tblPhieuMuon.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblPhieuMuon.getSelectedRow();
                if (selectedRow >= 0) {
                    String borrowId = tblPhieuMuon.getValueAt(selectedRow, 0).toString();
                    String bookTitle = tblPhieuMuon.getValueAt(selectedRow, 2).toString();
                    String dueDate = tblPhieuMuon.getValueAt(selectedRow, 4).toString();

                    txtMaPM.setText(borrowId);
                    txtTenSach.setText(bookTitle);
                    txtNgayPhaiTra.setText(dueDate);
                }
            }
        });
    }

    public void setMuonTraSachPanel(MuonTraSach muonTraSachPanel) {
        this.muonTraSachPanel = muonTraSachPanel;
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
        Title = new javax.swing.JLabel();
        txtMaPM = new javax.swing.JTextField();
        labelName = new javax.swing.JLabel();
        btnTaoPhieu = new javax.swing.JButton();
        btnDong = new javax.swing.JButton();
        labelId = new javax.swing.JLabel();
        btnTimMaDocGia = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocGia = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTenDocGia = new javax.swing.JTextField();
        labelName1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        labelName2 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        labelId1 = new javax.swing.JLabel();
        labelName3 = new javax.swing.JLabel();
        labelName4 = new javax.swing.JLabel();
        labelName5 = new javax.swing.JLabel();
        txtMaDocGia = new javax.swing.JTextField();
        txtTenSach = new javax.swing.JTextField();
        txtNgayPhaiTra = new javax.swing.JTextField();
        btnTimTenDocGia = new javax.swing.JButton();
        btnTimMaSach = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPhieuMuon = new javax.swing.JTable();
        cmbDamage = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Title.setBackground(new java.awt.Color(255, 255, 255));
        Title.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book.png"))); // NOI18N

        labelName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelName.setForeground(new java.awt.Color(255, 255, 255));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("librarymanagement/gui/Bundle"); // NOI18N
        labelName.setText(bundle.getString("BookReturn.labelName.text")); // NOI18N

        btnTaoPhieu.setBackground(new java.awt.Color(0, 204, 204));
        btnTaoPhieu.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnTaoPhieu.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoPhieu.setText(bundle.getString("BookReturn.btnTaoPhieu.text")); // NOI18N
        btnTaoPhieu.addActionListener(this::btnTaoPhieuActionPerformed);

        btnDong.setBackground(new java.awt.Color(0, 204, 204));
        btnDong.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnDong.setForeground(new java.awt.Color(255, 255, 255));
        btnDong.setText(bundle.getString("BookReturn.btnDong.text")); // NOI18N
        btnDong.addActionListener(this::btnDongActionPerformed);

        labelId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelId.setForeground(new java.awt.Color(255, 255, 255));
        labelId.setText(bundle.getString("BookReturn.labelId.text")); // NOI18N

        btnTimMaDocGia.setText(bundle.getString("BookReturn.btnTimMaDocGia.text")); // NOI18N
        btnTimMaDocGia.addActionListener(this::btnTimMaDocGiaActionPerformed);

        tblDocGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã độc giả", "Tên độc giả", "Email", "Số điện thoại", "Địa chỉ", "Ngày đăng ký"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
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
        jScrollPane1.setViewportView(tblDocGia);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText(bundle.getString("BookReturn.jLabel1.text")); // NOI18N

        txtTenDocGia.setText(bundle.getString("BookReturn.txtTenDocGia.text")); // NOI18N

        labelName1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelName1.setForeground(new java.awt.Color(255, 255, 255));
        labelName1.setText(bundle.getString("BookReturn.labelName1.text")); // NOI18N

        txtEmail.setEditable(false);
        txtEmail.setText(bundle.getString("BookReturn.txtEmail.text")); // NOI18N

        labelName2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelName2.setForeground(new java.awt.Color(255, 255, 255));
        labelName2.setText(bundle.getString("BookReturn.labelName2.text")); // NOI18N

        txtSDT.setEditable(false);
        txtSDT.setText(bundle.getString("BookReturn.txtSDT.text")); // NOI18N

        labelId1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelId1.setForeground(new java.awt.Color(255, 255, 255));
        labelId1.setText(bundle.getString("BookReturn.labelId1.text")); // NOI18N

        labelName3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelName3.setForeground(new java.awt.Color(255, 255, 255));
        labelName3.setText(bundle.getString("BookReturn.labelName3.text")); // NOI18N

        labelName4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelName4.setForeground(new java.awt.Color(255, 255, 255));
        labelName4.setText(bundle.getString("BookReturn.labelName4.text")); // NOI18N

        labelName5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelName5.setForeground(new java.awt.Color(255, 255, 255));
        labelName5.setText(bundle.getString("BookReturn.labelName5.text")); // NOI18N

        txtTenSach.setEditable(false);

        txtNgayPhaiTra.setEditable(false);

        btnTimTenDocGia.setText(bundle.getString("BookReturn.btnTimTenDocGia.text")); // NOI18N
        btnTimTenDocGia.addActionListener(this::btnTimTenDocGiaActionPerformed);

        btnTimMaSach.setText(bundle.getString("BookReturn.btnTimMaSach.text")); // NOI18N

        tblPhieuMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu mượn", "Tên độc giả", "Tên sách", "Ngày mượn", "Ngày phải trả", "Tiền phạt trả chậm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
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
        jScrollPane2.setViewportView(tblPhieuMuon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(269, 269, 269))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelName5)
                    .addComponent(labelId)
                    .addComponent(labelName1)
                    .addComponent(labelName)
                    .addComponent(labelName2)
                    .addComponent(labelId1)
                    .addComponent(labelName3)
                    .addComponent(labelName4))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgayPhaiTra, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenSach, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaPM, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenDocGia, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaDocGia)
                    .addComponent(cmbDamage, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTimMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTimMaDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimTenDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(btnTaoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(btnDong, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelId)
                            .addComponent(txtMaDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimMaDocGia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelName)
                            .addComponent(btnTimTenDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelName1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelName2)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelId1)
                            .addComponent(txtMaPM, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelName3)
                            .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelName4)
                            .addComponent(txtNgayPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelName5)
                            .addComponent(cmbDamage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnDongActionPerformed

    private void btnTimMaDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimMaDocGiaActionPerformed
        // TODO add your handling code here:
        String keyword = txtMaDocGia.getText().trim();
        filterReaderTableById(keyword);
    }//GEN-LAST:event_btnTimMaDocGiaActionPerformed

    private void btnTimTenDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimTenDocGiaActionPerformed
        // TODO add your handling code here:
        String keyword = txtTenDocGia.getText().trim();
        filterReaderTableByName(keyword);
    }//GEN-LAST:event_btnTimTenDocGiaActionPerformed

    private void btnTaoPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoPhieuActionPerformed
        // TODO add your handling code here:
        String borrowIdText = txtMaPM.getText().trim();

        if (borrowIdText.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập mã phiếu mượn!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        int borrowId;
        try {
            borrowId = Integer.parseInt(borrowIdText);
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Mã phiếu mượn phải là một số hợp lệ!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        MuonTraSachDAO dao = new MuonTraSachDAO();

        if (!dao.borrowRecordExists(borrowId)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Mã phiếu mượn không tồn tại!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        int statusId = cmbDamage.getSelectedIndex() + 1; // Bình thường: 1, Hư hại: 2, Mất sách: 3
        java.sql.Date dueDate = java.sql.Date.valueOf(txtNgayPhaiTra.getText());
        java.sql.Date returnDate = new java.sql.Date(System.currentTimeMillis());

        int totalFine = dao.calculateTotalFine(dueDate, statusId);

        if (totalFine > 0) {
            String message = "Tiền phạt: " + totalFine + " VND\n";
            if (statusId == 2) {
                message += "Lý do: Hư hại sách\n";
            } else if (statusId == 3) {
                message += "Lý do: Mất sách\n";
            }
            if (dueDate.before(returnDate)) {
                long daysLate = returnDate.toLocalDate().toEpochDay() - dueDate.toLocalDate().toEpochDay();
                message += "Trễ hạn: " + daysLate + " ngày\n";
            }
            message += "Bạn có muốn tiếp tục không?";

            int confirm = javax.swing.JOptionPane.showConfirmDialog(this, message, "Xác nhận", javax.swing.JOptionPane.YES_NO_OPTION);
            if (confirm != javax.swing.JOptionPane.YES_OPTION) {
                return;
            }
        }

        int returnId = dao.createReturnBook(borrowId, returnDate, statusId);

        if (returnId > 0 && totalFine > 0) {
            dao.createReturnFine(returnId, totalFine, statusId, dueDate);
        }

        dao.softDeleteBorrowBook(borrowId);

        int readerId = Integer.parseInt(txtMaDocGia.getText());
        loadBorrowRecordsByReader(readerId);
        if (muonTraSachPanel != null) {
            muonTraSachPanel.refreshBorrowerTable();
        }
        javax.swing.JOptionPane.showMessageDialog(this, "Tạo phiếu trả thành công!");
        this.dispose();
    }//GEN-LAST:event_btnTaoPhieuActionPerformed

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
            java.util.logging.Logger.getLogger(BookReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookReturn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Title;
    private javax.swing.JButton btnDong;
    private javax.swing.JButton btnTaoPhieu;
    private javax.swing.JButton btnTimMaDocGia;
    private javax.swing.JButton btnTimMaSach;
    private javax.swing.JButton btnTimTenDocGia;
    private javax.swing.JComboBox<String> cmbDamage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelId1;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelName1;
    private javax.swing.JLabel labelName2;
    private javax.swing.JLabel labelName3;
    private javax.swing.JLabel labelName4;
    private javax.swing.JLabel labelName5;
    private javax.swing.JTable tblDocGia;
    private javax.swing.JTable tblPhieuMuon;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaDocGia;
    private javax.swing.JTextField txtMaPM;
    private javax.swing.JTextField txtNgayPhaiTra;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenDocGia;
    private javax.swing.JTextField txtTenSach;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package librarymanagement.gui;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import librarymanagement.dao.MuonTraSachDAO;
import librarymanagement.pojo.Book;
import librarymanagement.pojo.Reader;

/**
 *
 * @author lantr
 */
public class BookBorrowForm extends javax.swing.JFrame {

    private MuonTraSachDAO muonTraSachDAO;
    private List<Reader> fullReaderList;
    private List<Book> fullBookList;

    /**
     * Creates new form BookBorrowForm
     */
    public BookBorrowForm() {
        initComponents();
        setTitle("Phiếu mượn");
        initDAO();
        loadReaderData();
        loadBookData();
        addDocGiaSelectionListener();
        addSachSelectionListener();
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

    private void loadBookData() {
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        model.setRowCount(0);

        fullBookList = muonTraSachDAO.getAllBooks();
        for (Book book : fullBookList) {
            model.addRow(new Object[]{
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getPublishYear(),
                book.getAvailableQty()
            });
        }
    }

    private void addDocGiaSelectionListener() {
        tblDocGia.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblDocGia.getSelectedRow();
                if (selectedRow >= 0) {
                    String readerId = tblDocGia.getValueAt(selectedRow, 0).toString();
                    String readerName = tblDocGia.getValueAt(selectedRow, 1).toString();
                    String email = tblDocGia.getValueAt(selectedRow, 2).toString();
                    String phoneNumber = tblDocGia.getValueAt(selectedRow, 3).toString();

                    txtMaDocGia.setText(readerId);
                    txtTenDocGia.setText(readerName);
                    txtEmail.setText(email);
                    txtSDT.setText(phoneNumber);
                }
            }
        });
    }

    private void addSachSelectionListener() {
        tblSach.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tblSach.getSelectedRow();
            if (selectedRow >= 0) {
                String bookId = tblSach.getValueAt(selectedRow, 0).toString();
                String title = tblSach.getValueAt(selectedRow, 1).toString();
                String author = tblSach.getValueAt(selectedRow, 2).toString();
                String availableQty = tblSach.getValueAt(selectedRow, 5).toString();

                txtMaSach.setText(bookId);
                txtTenSach.setText(title);
                txtTenTacGia.setText(author);
                txtSoSachSanCo.setText(availableQty);
            }
        });
    }

    //Search button
    private void filterReaderTable(String keyword) {
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

    //For searching with vietnamese
    private String normalizeString(String input) {
        return java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }

    private void filterBookTableByCode(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        model.setRowCount(0);

        for (Book book : fullBookList) {
            if (String.valueOf(book.getBookId()).contains(keyword)) {
                model.addRow(new Object[]{
                    book.getBookId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getCategory(),
                    book.getPublishYear(),
                    book.getAvailableQty()
                });
            }
        }
    }

    private void filterBookTableByName(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        model.setRowCount(0);

        for (Book book : fullBookList) {
            String normalizedKeyword = normalizeString(keyword).toLowerCase();
            String normalizedTitle = normalizeString(book.getTitle()).toLowerCase();
            if (normalizedTitle.contains(normalizedKeyword)) {
                model.addRow(new Object[]{
                    book.getBookId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getCategory(),
                    book.getPublishYear(),
                    book.getAvailableQty()
                });
            }
        }
    }

    private java.sql.Date calculateDueDate(java.sql.Date borrowDate) {
        int maxBorrowDays = muonTraSachDAO.getMaxBorrowDays();
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(borrowDate);
        calendar.add(java.util.Calendar.DATE, maxBorrowDays);
        return new java.sql.Date(calendar.getTimeInMillis());
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
        txtMaSach = new javax.swing.JTextField();
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
        txtTenTacGia = new javax.swing.JTextField();
        txtSoSachSanCo = new javax.swing.JTextField();
        btnTimTenDocGia = new javax.swing.JButton();
        btnTimMaSach = new javax.swing.JButton();
        btnTimTenSach = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSach = new javax.swing.JTable();
        btnClose1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        Title.setBackground(new java.awt.Color(255, 255, 255));
        Title.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book.png"))); // NOI18N

        labelName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelName.setForeground(new java.awt.Color(255, 255, 255));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("librarymanagement/gui/Bundle"); // NOI18N
        labelName.setText(bundle.getString("BookBorrowForm.labelName.text")); // NOI18N

        btnTaoPhieu.setBackground(new java.awt.Color(0, 204, 204));
        btnTaoPhieu.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnTaoPhieu.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoPhieu.setText(bundle.getString("BookBorrowForm.btnTaoPhieu.text")); // NOI18N
        btnTaoPhieu.addActionListener(this::btnTaoPhieuActionPerformed);

        btnDong.setBackground(new java.awt.Color(0, 204, 204));
        btnDong.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnDong.setForeground(new java.awt.Color(255, 255, 255));
        btnDong.setText(bundle.getString("BookBorrowForm.btnDong.text")); // NOI18N
        btnDong.addActionListener(this::btnDongActionPerformed);

        labelId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelId.setForeground(new java.awt.Color(255, 255, 255));
        labelId.setText(bundle.getString("BookBorrowForm.labelId.text")); // NOI18N

        btnTimMaDocGia.setText(bundle.getString("BookBorrowForm.btnTimMaDocGia.text")); // NOI18N
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
        jLabel1.setText(bundle.getString("BookBorrowForm.jLabel1.text")); // NOI18N

        txtTenDocGia.setText(bundle.getString("BookBorrowForm.txtTenDocGia.text")); // NOI18N

        labelName1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelName1.setForeground(new java.awt.Color(255, 255, 255));
        labelName1.setText(bundle.getString("BookBorrowForm.labelName1.text")); // NOI18N

        txtEmail.setEditable(false);
        txtEmail.setText(bundle.getString("BookBorrowForm.txtEmail.text")); // NOI18N

        labelName2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelName2.setForeground(new java.awt.Color(255, 255, 255));
        labelName2.setText(bundle.getString("BookBorrowForm.labelName2.text")); // NOI18N

        txtSDT.setEditable(false);
        txtSDT.setText(bundle.getString("BookBorrowForm.txtSDT.text")); // NOI18N

        labelId1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelId1.setForeground(new java.awt.Color(255, 255, 255));
        labelId1.setText(bundle.getString("BookBorrowForm.labelId1.text")); // NOI18N

        labelName3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelName3.setForeground(new java.awt.Color(255, 255, 255));
        labelName3.setText(bundle.getString("BookBorrowForm.labelName3.text")); // NOI18N

        labelName4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelName4.setForeground(new java.awt.Color(255, 255, 255));
        labelName4.setText(bundle.getString("BookBorrowForm.labelName4.text")); // NOI18N

        labelName5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelName5.setForeground(new java.awt.Color(255, 255, 255));
        labelName5.setText(bundle.getString("BookBorrowForm.labelName5.text")); // NOI18N

        txtTenTacGia.setEditable(false);

        txtSoSachSanCo.setEditable(false);

        btnTimTenDocGia.setText(bundle.getString("BookBorrowForm.btnTimTenDocGia.text")); // NOI18N
        btnTimTenDocGia.addActionListener(this::btnTimTenDocGiaActionPerformed);

        btnTimMaSach.setText(bundle.getString("BookBorrowForm.btnTimMaSach.text")); // NOI18N
        btnTimMaSach.addActionListener(this::btnTimMaSachActionPerformed);

        btnTimTenSach.setText(bundle.getString("BookBorrowForm.btnTimTenSach.text")); // NOI18N
        btnTimTenSach.addActionListener(this::btnTimTenSachActionPerformed);

        tblSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Tác giả", "Thể loại", "Năm xuất bản", "Số sách sẵn có"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class
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
        jScrollPane2.setViewportView(tblSach);

        btnClose1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnClose1.setForeground(new java.awt.Color(255, 255, 255));
        btnClose1.setText(bundle.getString("BookBorrowForm.btnClose1.text")); // NOI18N
        btnClose1.setBorder(null);
        btnClose1.setBorderPainted(false);
        btnClose1.setContentAreaFilled(false);
        btnClose1.setDefaultCapable(false);
        btnClose1.addActionListener(this::btnClose1ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(157, 488, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(269, 269, 269))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelName)
                            .addComponent(labelId)
                            .addComponent(labelName1)
                            .addComponent(labelName2)
                            .addComponent(labelId1)
                            .addComponent(labelName3)
                            .addComponent(labelName4)
                            .addComponent(labelName5))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSach, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSDT)
                            .addComponent(txtTenSach)
                            .addComponent(txtTenTacGia)
                            .addComponent(txtSoSachSanCo)
                            .addComponent(txtTenDocGia)
                            .addComponent(txtMaDocGia))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnTimMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnTimTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
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
                        .addGap(10, 10, 10)
                        .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClose1)
                        .addContainerGap())))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnClose1))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelName2)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelId1)
                            .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelName3)
                            .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelName4)
                            .addComponent(txtTenTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelName5)
                            .addComponent(txtSoSachSanCo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimMaDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimMaDocGiaActionPerformed
        // TODO add your handling code here:
        String keyword = txtMaDocGia.getText().trim();
        filterReaderTable(keyword);
    }//GEN-LAST:event_btnTimMaDocGiaActionPerformed

    private void btnTimTenDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimTenDocGiaActionPerformed
        // TODO add your handling code here:
        String keyword = txtTenDocGia.getText().trim();
        filterReaderTableByName(keyword);
    }//GEN-LAST:event_btnTimTenDocGiaActionPerformed

    private void btnTimMaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimMaSachActionPerformed
        // TODO add your handling code here:
        String keyword = txtMaSach.getText().trim();
        filterBookTableByCode(keyword);
    }//GEN-LAST:event_btnTimMaSachActionPerformed

    private void btnTimTenSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimTenSachActionPerformed
        // TODO add your handling code here:
        String keyword = txtTenSach.getText().trim();
        filterBookTableByName(keyword);
    }//GEN-LAST:event_btnTimTenSachActionPerformed

    private void btnTaoPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoPhieuActionPerformed
        // TODO add your handling code here:
        try {
            String readerIdText = txtMaDocGia.getText().trim();
            if (readerIdText.isEmpty() || !readerIdText.matches("\\d+")) {
                javax.swing.JOptionPane.showMessageDialog(this, "Mã độc giả không hợp lệ!");
                return;
            }
            int readerId = Integer.parseInt(readerIdText);

            String bookIdText = txtMaSach.getText().trim();
            if (bookIdText.isEmpty() || !bookIdText.matches("\\d+")) {
                javax.swing.JOptionPane.showMessageDialog(this, "Mã sách không hợp lệ!");
                return;
            }
            int bookId = Integer.parseInt(bookIdText);

            if (!muonTraSachDAO.readerExists(readerId)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Độc giả không tồn tại trong hệ thống.");
                return;
            }

            if (!muonTraSachDAO.bookExists(bookId)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Sách không tồn tại trong hệ thống.");
                return;
            }

            java.sql.Date borrowDate = new java.sql.Date(System.currentTimeMillis());
            java.sql.Date dueDate = calculateDueDate(borrowDate);

            // Book availbility
            int availableQty = Integer.parseInt(txtSoSachSanCo.getText().trim());
            if (availableQty <= 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Sách không còn sẵn có!");
                return;
            }

            // Book borrowed by one person
            int maxBooksBorrowed = muonTraSachDAO.getMaxBooksBorrowed();
            int currentBooksBorrowed = muonTraSachDAO.getCurrentBooksBorrowed(readerId);
            if (currentBooksBorrowed >= maxBooksBorrowed) {
                javax.swing.JOptionPane.showMessageDialog(this, "Độc giả đã mượn đủ số lượng sách tối đa: " + maxBooksBorrowed);
                return;
            }

            // Add to database
            boolean isInserted = muonTraSachDAO.addBorrowRecord(readerId, bookId, borrowDate, dueDate);

            if (isInserted) {
                javax.swing.JOptionPane.showMessageDialog(this, "Thêm phiếu mượn thành công!");
                loadBookData(); // update the table
                this.dispose();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Thêm phiếu mượn thất bại!");
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnTaoPhieuActionPerformed

    private void btnDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnDongActionPerformed

    private void btnClose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose1ActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClose1ActionPerformed

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
            java.util.logging.Logger.getLogger(BookBorrowForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookBorrowForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookBorrowForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookBorrowForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookBorrowForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Title;
    private javax.swing.JButton btnClose1;
    private javax.swing.JButton btnDong;
    private javax.swing.JButton btnTaoPhieu;
    private javax.swing.JButton btnTimMaDocGia;
    private javax.swing.JButton btnTimMaSach;
    private javax.swing.JButton btnTimTenDocGia;
    private javax.swing.JButton btnTimTenSach;
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
    private javax.swing.JTable tblSach;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaDocGia;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoSachSanCo;
    private javax.swing.JTextField txtTenDocGia;
    private javax.swing.JTextField txtTenSach;
    private javax.swing.JTextField txtTenTacGia;
    // End of variables declaration//GEN-END:variables
}

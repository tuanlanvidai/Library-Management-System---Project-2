/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package librarymanagement.gui;

import librarymanagement.dao.MuonTraSachDAO;
import librarymanagement.pojo.Borrower;
import librarymanagement.pojo.ReturnBook;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import librarymanagement.pojo.Reader;

/**
 *
 * @author duyanh, lantr
 */
public class MuonTraSach extends javax.swing.JPanel {

    private MuonTraSachDAO muonTraSachDAO;
    private List<Borrower> fullBorrowerList;

    /**
     * Creates new form MuonTraSach
     */
    public MuonTraSach() {
        initComponents();
        initDAO();
        loadBorrowerData();
        addTableSelectionListener();
    }

    private void initDAO() {
        muonTraSachDAO = new MuonTraSachDAO();
    }

    private void loadBorrowerData() {
        DefaultTableModel model = (DefaultTableModel) tblBorrowerName.getModel();
        model.setRowCount(0);
        fullBorrowerList = muonTraSachDAO.getBorrowers();

        for (Borrower borrower : fullBorrowerList) {
            model.addRow(new Object[]{
                borrower.getReaderId(),
                borrower.getReaderName(),
                borrower.getRegisterDay(),
                borrower.getTotalBooksBorrowed()
            });
        }
    }

    //action when click/select a borrower, then show the books he/she borrowed
    private void addTableSelectionListener() {
        tblBorrowerName.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tblBorrowerName.getSelectedRow();
                if (selectedRow >= 0) {
                    int readerId = (int) tblBorrowerName.getValueAt(selectedRow, 0);
                    loadBorrowedBooksData(readerId);
                }
            }
        });
    }

    private void loadBorrowedBooksData(int readerId) {
        DefaultTableModel model = (DefaultTableModel) tblBorrowBooks.getModel();
        model.setRowCount(0);

        List<ReturnBook> borrowedBooks = muonTraSachDAO.getBorrowedBooksWithFine(readerId);

        for (ReturnBook book : borrowedBooks) {
            model.addRow(new Object[]{
                book.getBorrowId(),
                book.getBookTitle(),
                book.getBorrowDate(),
                book.getDueDate(),
                book.getFineAmount()
            });
        }
    }

    private void filterBorrowerTableByName(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblBorrowerName.getModel();
        model.setRowCount(0);

        for (Borrower borrower : fullBorrowerList) {
            String normalizedKeyword = normalizeString(keyword).toLowerCase();
            String normalizedName = normalizeString(borrower.getReaderName()).toLowerCase();
            if (normalizedName.contains(normalizedKeyword)) {
                model.addRow(new Object[]{
                    borrower.getReaderId(),
                    borrower.getReaderName(),
                    borrower.getRegisterDay(),
                    borrower.getTotalBooksBorrowed()
                });
            }
        }
    }

    private void filterBorrowerTableById(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblBorrowerName.getModel();
        model.setRowCount(0);

        for (Borrower borrower : fullBorrowerList) {
            if (String.valueOf(borrower.getReaderId()).contains(keyword)) {
                model.addRow(new Object[]{
                    borrower.getReaderId(),
                    borrower.getReaderName(),
                    borrower.getRegisterDay(),
                    borrower.getTotalBooksBorrowed()
                });
            }
        }
    }

    private String normalizeString(String input) {
        return java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
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
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBorrowerName = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblBorrowBooks = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        cmbTimTheo = new javax.swing.JComboBox<>();
        btnTim = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnAddBorrow = new javax.swing.JButton();
        btnAddReturn = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));
        setForeground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1100, 552));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/docgia.png"))); // NOI18N
        jLabel1.setText("Manage borrowed/returned books");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));

        tblBorrowerName.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên Độc Giả", "Ngày lập thẻ", "Số sách đang mượn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblBorrowerName);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("List of readers borrowing books");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));

        tblBorrowBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã PM", "Tên sách", "Ngày mượn", "Ngày trả ", "Tiền phạt quá hạn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblBorrowBooks);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Search");

        cmbTimTheo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Reader Name", "Reader Code" }));

        btnTim.setText("Sarch");
        btnTim.addActionListener(this::btnTimActionPerformed);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(cmbTimTheo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(44, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim)
                    .addComponent(cmbTimTheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btnTim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        btnAddBorrow.setBackground(new java.awt.Color(0, 204, 204));
        btnAddBorrow.setForeground(new java.awt.Color(255, 255, 255));
        btnAddBorrow.setText("Create a loan slip");
        btnAddBorrow.addActionListener(this::btnAddBorrowActionPerformed);

        btnAddReturn.setBackground(new java.awt.Color(0, 204, 204));
        btnAddReturn.setForeground(new java.awt.Color(255, 255, 255));
        btnAddReturn.setText("Create a payment slip");
        btnAddReturn.addActionListener(this::btnAddReturnActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddBorrow, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddBorrow, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddReturnActionPerformed
        int selectedBorrowRow = tblBorrowBooks.getSelectedRow();
        int selectedBorrowerRow = tblBorrowerName.getSelectedRow();

        BookReturn returnForm = new BookReturn();

        returnForm.setMuonTraSachPanel(this);

        if (selectedBorrowRow >= 0 && selectedBorrowerRow >= 0) {
            int borrowId = (int) tblBorrowBooks.getValueAt(selectedBorrowRow, 0);
            String bookTitle = (String) tblBorrowBooks.getValueAt(selectedBorrowRow, 1);
            java.sql.Date dueDate = (java.sql.Date) tblBorrowBooks.getValueAt(selectedBorrowRow, 3);

            int readerId = (int) tblBorrowerName.getValueAt(selectedBorrowerRow, 0);
            String readerName = (String) tblBorrowerName.getValueAt(selectedBorrowerRow, 1);

            List<Reader> readers = muonTraSachDAO.getAllReaders();
            for (Reader reader : readers) {
                if (reader.getReaderId() == readerId) {
                    returnForm.setReturnInfo(
                            readerId,
                            readerName,
                            reader.getEmail(),
                            reader.getPhoneNumber(),
                            borrowId,
                            bookTitle,
                            dueDate
                    );
                    returnForm.loadBorrowRecordsByReader(readerId);
                    break;
                }
            }
        } else if (selectedBorrowerRow >= 0) {
            int readerId = (int) tblBorrowerName.getValueAt(selectedBorrowerRow, 0);
            String readerName = (String) tblBorrowerName.getValueAt(selectedBorrowerRow, 1);

            List<Reader> readers = muonTraSachDAO.getAllReaders();
            for (Reader reader : readers) {
                if (reader.getReaderId() == readerId) {
                    returnForm.setReturnInfo(
                            readerId,
                            readerName,
                            reader.getEmail(),
                            reader.getPhoneNumber(),
                            0,
                            "",
                            null
                    );
                    returnForm.loadBorrowRecordsByReader(readerId);
                    break;
                }
            }
        }

        returnForm.setVisible(true);
    }//GEN-LAST:event_btnAddReturnActionPerformed

    private void btnAddBorrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBorrowActionPerformed
        // TODO add your handling code here:
        BookBorrowForm borrowForm = new BookBorrowForm();
        borrowForm.setVisible(true);
    }//GEN-LAST:event_btnAddBorrowActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        String keyword = txtTim.getText().trim();
        String searchOption = cmbTimTheo.getSelectedItem().toString();

        if (searchOption.equals("Tên độc giả")) {
            filterBorrowerTableByName(keyword);
        } else if (searchOption.equals("Mã độc giả")) {
            filterBorrowerTableById(keyword);
        }
    }//GEN-LAST:event_btnTimActionPerformed
    public void refreshBorrowerTable() {
        loadBorrowerData();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddBorrow;
    private javax.swing.JButton btnAddReturn;
    private javax.swing.JButton btnTim;
    private javax.swing.JComboBox<String> cmbTimTheo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblBorrowBooks;
    private javax.swing.JTable tblBorrowerName;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}

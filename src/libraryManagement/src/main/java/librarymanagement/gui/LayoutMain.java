/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package librarymanagement.gui;

import java.util.ArrayList;
import java.util.List;
import librarymanagement.Dashboard;
import librarymanagement.dao.ChuyenManHinhDAO;

/**
 *
 * @author duyanh
 */
public class LayoutMain extends javax.swing.JFrame {

    /**
     * Creates new form LayoutMainimage
     */
    public LayoutMain() {
        initComponents();
        setTitle("Quản Lý Học Viên ");
        ChuyenManHinhDAO chuyenmanhinhDAO = new ChuyenManHinhDAO(jpnView);
        chuyenmanhinhDAO.setView(jpntrangchu, jlbtrangchu);
        
        List<Dashboard> listItem = new ArrayList<>();
        listItem.add(new Dashboard("TrangChu",jpntrangchu,jlbtrangchu));  
        listItem.add(new Dashboard("QuanLyDocGia",jpnquanlydocgia,jlbquanlydocgia));    
        listItem.add(new Dashboard("QuanLySach",jpnquanlysach,jlbquanlysach));
        listItem.add(new Dashboard("MuonTraSach",jpnmuontrasach,jlbmuontrasach));
        listItem.add(new Dashboard("QuanLyThuThu",jpnquanlythuthu,jlbquanlythuthu));
        listItem.add(new Dashboard("BaoCao",jpnbaocao,jlbbaocao));   
        listItem.add(new Dashboard("CaiDat",jpncaidat,jlbcaidat));


          chuyenmanhinhDAO.setEvent(listItem);
        

    }
    public LayoutMain(String Role) {
        initComponents();
        setTitle("Quản Lý Học Viên ");
        ChuyenManHinhDAO chuyenmanhinhDAO = new ChuyenManHinhDAO(jpnView);
        chuyenmanhinhDAO.setView(jpntrangchu, jlbtrangchu);
        
        List<Dashboard> listItem = new ArrayList<>();
        listItem.add(new Dashboard("TrangChu",jpntrangchu,jlbtrangchu));  
        listItem.add(new Dashboard("QuanLyDocGia",jpnquanlydocgia,jlbquanlydocgia));    
        listItem.add(new Dashboard("QuanLySach",jpnquanlysach,jlbquanlysach));
        listItem.add(new Dashboard("MuonTraSach",jpnmuontrasach,jlbmuontrasach));
        listItem.add(new Dashboard("QuanLyThuThu",jpnquanlythuthu,jlbquanlythuthu));
        listItem.add(new Dashboard("BaoCao",jpnbaocao,jlbbaocao));   
        listItem.add(new Dashboard("CaiDat",jpncaidat,jlbcaidat));


          chuyenmanhinhDAO.setEvent(listItem);
        if(!Role.equals("Admin")){
            jpnquanlythuthu.setVisible(false);
        };

    }

    /**
     * This method is called from within the constructor to initialiimageze the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jpnRoot = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jpntrangchu = new javax.swing.JPanel();
        jlbtrangchu = new javax.swing.JLabel();
        jpnquanlydocgia = new javax.swing.JPanel();
        jlbquanlydocgia = new javax.swing.JLabel();
        jpnquanlysach = new javax.swing.JPanel();
        jlbquanlysach = new javax.swing.JLabel();
        jpnmuontrasach = new javax.swing.JPanel();
        jlbmuontrasach = new javax.swing.JLabel();
        jpnquanlythuthu = new javax.swing.JPanel();
        jlbquanlythuthu = new javax.swing.JLabel();
        jpnbaocao = new javax.swing.JPanel();
        jlbbaocao = new javax.swing.JLabel();
        jpncaidat = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jlbcaidat = new javax.swing.JLabel();
        btn_dangxuat = new javax.swing.JButton();
        jpnView = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jpnRoot.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book.png"))); // NOI18N
        jLabel1.setText("Quản Lý Thư Viện");

        javax.swing.GroupLayout jpnRootLayout = new javax.swing.GroupLayout(jpnRoot);
        jpnRoot.setLayout(jpnRootLayout);
        jpnRootLayout.setHorizontalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50))
        );
        jpnRootLayout.setVerticalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jpntrangchu.setBackground(new java.awt.Color(0, 204, 204));
        jpntrangchu.setForeground(new java.awt.Color(255, 255, 255));
        jpntrangchu.setPreferredSize(new java.awt.Dimension(322, 40));

        jlbtrangchu.setBackground(new java.awt.Color(255, 255, 255));
        jlbtrangchu.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jlbtrangchu.setForeground(new java.awt.Color(255, 255, 255));
        jlbtrangchu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        jlbtrangchu.setText("Trang Chủ");

        javax.swing.GroupLayout jpntrangchuLayout = new javax.swing.GroupLayout(jpntrangchu);
        jpntrangchu.setLayout(jpntrangchuLayout);
        jpntrangchuLayout.setHorizontalGroup(
            jpntrangchuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpntrangchuLayout.createSequentialGroup()
                .addComponent(jlbtrangchu)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpntrangchuLayout.setVerticalGroup(
            jpntrangchuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbtrangchu, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jpnquanlydocgia.setBackground(new java.awt.Color(0, 204, 204));
        jpnquanlydocgia.setPreferredSize(new java.awt.Dimension(328, 40));

        jlbquanlydocgia.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jlbquanlydocgia.setForeground(new java.awt.Color(255, 255, 255));
        jlbquanlydocgia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/quanlydocgia.png"))); // NOI18N
        jlbquanlydocgia.setText("Quản Lý Độc Giả");

        javax.swing.GroupLayout jpnquanlydocgiaLayout = new javax.swing.GroupLayout(jpnquanlydocgia);
        jpnquanlydocgia.setLayout(jpnquanlydocgiaLayout);
        jpnquanlydocgiaLayout.setHorizontalGroup(
            jpnquanlydocgiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnquanlydocgiaLayout.createSequentialGroup()
                .addComponent(jlbquanlydocgia)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpnquanlydocgiaLayout.setVerticalGroup(
            jpnquanlydocgiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnquanlydocgiaLayout.createSequentialGroup()
                .addComponent(jlbquanlydocgia)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jpnquanlysach.setBackground(new java.awt.Color(0, 204, 204));

        jlbquanlysach.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jlbquanlysach.setForeground(new java.awt.Color(255, 255, 255));
        jlbquanlysach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/muontrasach.png"))); // NOI18N
        jlbquanlysach.setText("Quản Lý Sách");

        javax.swing.GroupLayout jpnquanlysachLayout = new javax.swing.GroupLayout(jpnquanlysach);
        jpnquanlysach.setLayout(jpnquanlysachLayout);
        jpnquanlysachLayout.setHorizontalGroup(
            jpnquanlysachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnquanlysachLayout.createSequentialGroup()
                .addComponent(jlbquanlysach)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpnquanlysachLayout.setVerticalGroup(
            jpnquanlysachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnquanlysachLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbquanlysach)
                .addContainerGap())
        );

        jpnmuontrasach.setBackground(new java.awt.Color(0, 204, 204));
        jpnmuontrasach.setPreferredSize(new java.awt.Dimension(300, 40));

        jlbmuontrasach.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jlbmuontrasach.setForeground(new java.awt.Color(255, 255, 255));
        jlbmuontrasach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/muontrasach.png"))); // NOI18N
        jlbmuontrasach.setText("Mượn Trả Sách");

        javax.swing.GroupLayout jpnmuontrasachLayout = new javax.swing.GroupLayout(jpnmuontrasach);
        jpnmuontrasach.setLayout(jpnmuontrasachLayout);
        jpnmuontrasachLayout.setHorizontalGroup(
            jpnmuontrasachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnmuontrasachLayout.createSequentialGroup()
                .addComponent(jlbmuontrasach)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpnmuontrasachLayout.setVerticalGroup(
            jpnmuontrasachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnmuontrasachLayout.createSequentialGroup()
                .addComponent(jlbmuontrasach)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jpnquanlythuthu.setBackground(new java.awt.Color(0, 204, 204));
        jpnquanlythuthu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jpnquanlythuthu.setPreferredSize(new java.awt.Dimension(300, 40));

        jlbquanlythuthu.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jlbquanlythuthu.setForeground(new java.awt.Color(255, 255, 255));
        jlbquanlythuthu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/quanlythuthu.png"))); // NOI18N
        jlbquanlythuthu.setText("Quản Lý Thủ Thư");

        javax.swing.GroupLayout jpnquanlythuthuLayout = new javax.swing.GroupLayout(jpnquanlythuthu);
        jpnquanlythuthu.setLayout(jpnquanlythuthuLayout);
        jpnquanlythuthuLayout.setHorizontalGroup(
            jpnquanlythuthuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnquanlythuthuLayout.createSequentialGroup()
                .addComponent(jlbquanlythuthu)
                .addGap(0, 112, Short.MAX_VALUE))
        );
        jpnquanlythuthuLayout.setVerticalGroup(
            jpnquanlythuthuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnquanlythuthuLayout.createSequentialGroup()
                .addComponent(jlbquanlythuthu)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jpnbaocao.setBackground(new java.awt.Color(0, 204, 204));
        jpnbaocao.setPreferredSize(new java.awt.Dimension(300, 40));

        jlbbaocao.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jlbbaocao.setForeground(new java.awt.Color(255, 255, 255));
        jlbbaocao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/baocao.png"))); // NOI18N
        jlbbaocao.setText("Báo Cáo");

        javax.swing.GroupLayout jpnbaocaoLayout = new javax.swing.GroupLayout(jpnbaocao);
        jpnbaocao.setLayout(jpnbaocaoLayout);
        jpnbaocaoLayout.setHorizontalGroup(
            jpnbaocaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnbaocaoLayout.createSequentialGroup()
                .addComponent(jlbbaocao)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpnbaocaoLayout.setVerticalGroup(
            jpnbaocaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnbaocaoLayout.createSequentialGroup()
                .addComponent(jlbbaocao)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jpncaidat.setBackground(new java.awt.Color(0, 204, 204));
        jpncaidat.setPreferredSize(new java.awt.Dimension(300, 40));

        jlbcaidat.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jlbcaidat.setForeground(new java.awt.Color(255, 255, 255));
        jlbcaidat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/caidat.png"))); // NOI18N
        jlbcaidat.setText("Cài Đặt");

        javax.swing.GroupLayout jpncaidatLayout = new javax.swing.GroupLayout(jpncaidat);
        jpncaidat.setLayout(jpncaidatLayout);
        jpncaidatLayout.setHorizontalGroup(
            jpncaidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpncaidatLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel9)
                .addGap(183, 183, 183)
                .addComponent(jLabel10)
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(jpncaidatLayout.createSequentialGroup()
                .addComponent(jlbcaidat, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpncaidatLayout.setVerticalGroup(
            jpncaidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpncaidatLayout.createSequentialGroup()
                .addComponent(jlbcaidat)
                .addGap(12, 12, 12)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        btn_dangxuat.setBackground(new java.awt.Color(204, 0, 0));
        btn_dangxuat.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        btn_dangxuat.setForeground(new java.awt.Color(255, 255, 255));
        btn_dangxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dangxuat.png"))); // NOI18N
        btn_dangxuat.setText("Đăng xuất");
        btn_dangxuat.addActionListener(this::btn_dangxuatActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jpnquanlysach, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpnmuontrasach, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                        .addComponent(jpnquanlythuthu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jpnRoot, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpntrangchu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                        .addComponent(jpnquanlydocgia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btn_dangxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jpncaidat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                            .addComponent(jpnbaocao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jpnRoot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpntrangchu, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jpnquanlydocgia, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jpnquanlysach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jpnmuontrasach, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jpnquanlythuthu, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jpnbaocao, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jpncaidat, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(btn_dangxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jpncaidat.getAccessibleContext().setAccessibleDescription("");

        jpnView.setBackground(new java.awt.Color(55, 162, 161));
        jpnView.setPreferredSize(new java.awt.Dimension(800, 0));

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1297, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jpnView, javax.swing.GroupLayout.PREFERRED_SIZE, 1297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_dangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dangxuatActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_dangxuatActionPerformed

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
            java.util.logging.Logger.getLogger(LayoutMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LayoutMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LayoutMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LayoutMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LayoutMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_dangxuat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlbbaocao;
    private javax.swing.JLabel jlbcaidat;
    private javax.swing.JLabel jlbmuontrasach;
    private javax.swing.JLabel jlbquanlydocgia;
    private javax.swing.JLabel jlbquanlysach;
    private javax.swing.JLabel jlbquanlythuthu;
    private javax.swing.JLabel jlbtrangchu;
    private javax.swing.JPanel jpnRoot;
    private javax.swing.JPanel jpnView;
    private javax.swing.JPanel jpnbaocao;
    private javax.swing.JPanel jpncaidat;
    private javax.swing.JPanel jpnmuontrasach;
    private javax.swing.JPanel jpnquanlydocgia;
    private javax.swing.JPanel jpnquanlysach;
    private javax.swing.JPanel jpnquanlythuthu;
    private javax.swing.JPanel jpntrangchu;
    // End of variables declaration//GEN-END:variables
}

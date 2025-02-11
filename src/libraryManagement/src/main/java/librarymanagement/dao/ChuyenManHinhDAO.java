/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement.dao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import librarymanagement.Dashboard;
import librarymanagement.gui.BaoCao;
import librarymanagement.gui.CaiDat;
import librarymanagement.gui.MuonTraSach;
import librarymanagement.gui.QuanLyDocGiaJPanel;
import librarymanagement.gui.QuanLySach;
import librarymanagement.gui.QuanLyThuThu;
import librarymanagement.gui.TrangChuJPanel;

/**
 *
 * @author duyanh
 */
public class ChuyenManHinhDAO {

    private JPanel root;
    private String kindSelected = "";
    List<Dashboard> listItem = null;

    public ChuyenManHinhDAO(JPanel jpnRoot) {
        this.root = jpnRoot;
    }

    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "TrangChu";
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChuJPanel());
        root.validate();
        root.repaint();
    }

    public void setEvent(List<Dashboard> listItem) {
        this.listItem = listItem;
        for (Dashboard item : listItem) {
            LabelEvent labelEvent = new LabelEvent(item.getKind(), item.getJpn(), item.getJlb());
            item.getJlb().addMouseListener(labelEvent);
            item.getJpn().addMouseListener(labelEvent);
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node;
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            switch (kind) {
                case "TrangChu":
                    node = new TrangChuJPanel();
                    break;
                case "QuanLyDocGia":
                    node = new QuanLyDocGiaJPanel();
                    break;
                case "QuanLySach":
                    node = new QuanLySach();
                    break;
                case "MuonTraSach":
                    node = new MuonTraSach();
                    break;
                case "QuanLyThuThu":
                    node = new QuanLyThuThu();
                    break;
                case "BaoCao":
                    node = new BaoCao();
                    break;
                case "CaiDat":
                    node = new CaiDat();
                    break;
                default:
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node, java.awt.BorderLayout.CENTER);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(211, 211, 211));
                jlbItem.setBackground(new Color(211, 211, 211));
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

    private void setChangeBackground(String kind) {
        for (Dashboard item : listItem) {
            if (item.getKind().equalsIgnoreCase(kind)) {
                item.getJpn().setBackground(new Color(211, 211, 211));
                item.getJlb().setBackground(new Color(211, 211, 211));
            } else {
                item.getJpn().setBackground(new Color(0, 204, 204));
                item.getJlb().setBackground(new Color(0, 204, 204));
            }
        }
    }

}

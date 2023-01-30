package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import components.panelRound.PanelRound;
import entity.Phong;
import utils.Utils;

public class Popup extends PanelRound {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 * 
	 * @param quanLyDatPhongGUI
	 */
	public Popup(QuanLyDatPhong_GUI quanLyDatPhongGUI, Phong phong, Rectangle rectangle) {
		setBorder(new LineBorder(Utils.primaryColor, 2));
		setBackground(Color.WHITE);
		setBounds(rectangle);
		setLayout(null);

		PanelRound pnlChuyenPhong = new PanelRound(8);
		pnlChuyenPhong.setBounds(10, 84, 111, 36);
		pnlChuyenPhong.setBackground(new Color(255, 154, 97));
		add(pnlChuyenPhong);
		pnlChuyenPhong.setLayout(null);

		JLabel lblChuyenPhong = new JLabel("Chuyển phòng");
		lblChuyenPhong.setForeground(Color.WHITE);
		lblChuyenPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblChuyenPhong.setHorizontalAlignment(SwingConstants.CENTER);
		lblChuyenPhong.setBounds(0, 0, 111, 36);
		pnlChuyenPhong.add(lblChuyenPhong);

		JLabel lblMaPhong = new JLabel(phong.getMaPhong());
		lblMaPhong.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaPhong.setForeground(Color.BLACK);
		lblMaPhong.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblMaPhong.setBounds(2, 0, 127, 36);
		add(lblMaPhong);

		PanelRound pnlDichVu = new PanelRound(8);
		pnlDichVu.setBackground(new Color(255, 154, 97));
		pnlDichVu.setBounds(10, 40, 111, 36);
		pnlDichVu.setLayout(null);
		add(pnlDichVu);

		JLabel lblDichVu = new JLabel("Dịch vụ");
		lblDichVu.setForeground(Color.WHITE);
		lblDichVu.setHorizontalAlignment(SwingConstants.CENTER);
		lblDichVu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDichVu.setBounds(0, 0, 111, 36);
		pnlDichVu.add(lblDichVu);

//		
		pnlChuyenPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				quanLyDatPhongGUI.handleClickOutsidePnlPhong();
				quanLyDatPhongGUI.handleOpenSubFrame(pnlChuyenPhong,
						new ChuyenPhong_GUI(quanLyDatPhongGUI, quanLyDatPhongGUI.getjFrame(), phong.getMaPhong()));
			}
		});

		pnlDichVu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				quanLyDatPhongGUI.handleClickOutsidePnlPhong();
			}
		});
	}
}

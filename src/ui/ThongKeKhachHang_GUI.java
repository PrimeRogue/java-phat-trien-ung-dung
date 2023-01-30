package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import components.button.Button;
import components.scrollbarCustom.ScrollBarCustom;
import utils.Utils;

public class ThongKeKhachHang_GUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblThongKe;

	/**
	 * Create the frame.
	 */
	public ThongKeKhachHang_GUI() {
		setBackground(Utils.secondaryColor);
		setBounds(0, 0, 1086, 508);
		setLayout(null);

		JPanel pnlChonNTN = new JPanel();
		pnlChonNTN.setBorder(new LineBorder(Utils.secondaryColor));
		pnlChonNTN.setForeground(new Color(0, 0, 0));
		pnlChonNTN.setBounds(0, 0, 1100, 146);
		pnlChonNTN.setBackground(Utils.secondaryColor);
		this.add(pnlChonNTN);
		pnlChonNTN.setLayout(null);

		JTextPane lblTKKH = new JTextPane();
		lblTKKH.setBounds(73, 20, 262, 33);
		lblTKKH.setText("Thống kê khách hàng theo:");
		lblTKKH.setForeground(Color.GRAY);
		lblTKKH.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTKKH.setBackground(new Color(203, 239, 255));
		pnlChonNTN.add(lblTKKH);

		Button btnNgay = new Button("Ngày");
		btnNgay.setBounds(358, 85, 102, 39);
		pnlChonNTN.add(btnNgay);
		btnNgay.setForeground(Color.GRAY);
		btnNgay.setBackground(Color.WHITE);
		btnNgay.setRadius(8);
		btnNgay.setColor(new Color(255, 255, 255));
		btnNgay.setFont(new Font("Segoe UI", Font.BOLD, 20));

		Button btnThang = new Button("Tháng");
		btnThang.setBounds(500, 85, 102, 39);
		btnThang.setFocusable(false);
		btnThang.setText("Tháng");
		btnThang.setRadius(8);
		btnThang.setForeground(Color.WHITE);
		btnThang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnThang.setColor(new Color(140, 177, 180));
		btnThang.setColorOver(new Color(140, 177, 180));
		btnThang.setColorClick(new Color(140, 177, 180));
		btnThang.setBorderColor(new Color(140, 177, 180));
		btnThang.setBackground(new Color(140, 177, 180));
		pnlChonNTN.add(btnThang);

		Button btnNam = new Button("Ngày");
		btnNam.setBounds(639, 85, 102, 39);
		pnlChonNTN.add(btnNam);
		btnNam.setText("Năm");
		btnNam.setRadius(8);
		btnNam.setForeground(Color.GRAY);
		btnNam.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNam.setColor(Color.WHITE);
		btnNam.setBackground(Color.WHITE);

		JTextPane lblNgay = new JTextPane();
		lblNgay.setBounds(358, 20, 61, 33);
		pnlChonNTN.add(lblNgay);
		lblNgay.setText("Ngày:");
		lblNgay.setForeground(Color.GRAY);
		lblNgay.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNgay.setBackground(new Color(203, 239, 255));

		JComboBox<String> cboNgay = new JComboBox<String>();
		cboNgay.setBounds(451, 20, 113, 38);
		pnlChonNTN.add(cboNgay);
		cboNgay.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cboNgay.setBackground(Color.WHITE);

		JTextPane lblThang = new JTextPane();
		lblThang.setBounds(606, 20, 71, 33);
		pnlChonNTN.add(lblThang);
		lblThang.setText("Tháng:");
		lblThang.setForeground(Color.GRAY);
		lblThang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblThang.setBackground(new Color(203, 239, 255));

		JComboBox<String> cboThang = new JComboBox<String>();
		cboThang.setBounds(706, 20, 113, 38);
		pnlChonNTN.add(cboThang);
		cboThang.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		JTextPane lblNam = new JTextPane();
		lblNam.setBounds(867, 20, 69, 38);
		pnlChonNTN.add(lblNam);
		lblNam.setText("Năm:");
		lblNam.setForeground(Color.GRAY);
		lblNam.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNam.setBackground(new Color(203, 239, 255));

		JComboBox<String> cboNam = new JComboBox<String>();
		cboNam.setBounds(956, 20, 113, 38);
		pnlChonNTN.add(cboNam);
		cboNam.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		Button btnThongKe = new Button("Ngày");
		btnThongKe.setBounds(771, 85, 298, 39);
		pnlChonNTN.add(btnThongKe);
		btnThongKe.setIcon(new ImageIcon("Icon\\statistics.png"));
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setText("Thống kê khách hàng:");
		btnThongKe.setRadius(8);
		btnThongKe.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnThongKe.setColor(new Color(140, 177, 180, 127));
		btnThongKe.setColorClick(new Color(140, 177, 180, 127));
		btnThongKe.setColorOver(new Color(140, 177, 180, 127));
		btnThongKe.setBorderColor(new Color(140, 177, 180));
		btnThongKe.setBackground(new Color(140, 177, 180));

		JPanel pnlThongKe = new JPanel();
		pnlThongKe.setBounds(0, 144, 1100, 364);
		this.add(pnlThongKe);
		pnlThongKe.setBackground(Utils.secondaryColor);
		pnlThongKe.setBorder(new LineBorder(Utils.secondaryColor));
		pnlThongKe.setLayout(null);

		JTextPane lblTitleTable = new JTextPane();
		lblTitleTable.setBounds(33, 0, 366, 33);
		pnlThongKe.add(lblTitleTable);
		lblTitleTable.setText("Thông tin khách hàng trong: 2/2022");
		lblTitleTable.setForeground(Color.GRAY);
		lblTitleTable.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitleTable.setBackground(new Color(203, 239, 255));

		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(10, 46, 650, 308);
		scr.setBackground(Utils.primaryColor);
		ScrollBarCustom scp = new ScrollBarCustom();
		scp.setScrollbarColor(new Color(203, 203, 203));
		scr.setVerticalScrollBar(scp);
		pnlThongKe.add(scr);

		tblThongKe = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (row % 2 == 0)
					c.setBackground(Color.WHITE);
				else
					c.setBackground(new Color(232, 232, 232));
				return c;
			}
		};
		tblThongKe.setModel(new DefaultTableModel(
				new Object[][] { { "MKH0001", "Nguyễn Phương Anh", "090190002456", "Nữ", "0314595218", "MHD001" }, },
				new String[] { "Mã KH", "Họ tên khách", "CCCD", "Giới tính", "SĐT khách", "Mã hoá đơn" }));
		tblThongKe.getColumnModel().getColumn(0).setPreferredWidth(70);
		tblThongKe.getColumnModel().getColumn(1).setPreferredWidth(190);
		tblThongKe.getColumnModel().getColumn(2).setPreferredWidth(110);
		tblThongKe.getColumnModel().getColumn(3).setPreferredWidth(70);
		tblThongKe.getColumnModel().getColumn(4).setPreferredWidth(100);
		tblThongKe.getColumnModel().getColumn(5).setPreferredWidth(100);
		// Cam
		tblThongKe.getTableHeader().setBackground(new Color(255, 195, 174));
		// Xanh
		tblThongKe.getTableHeader().setBackground(Utils.primaryColor);
		tblThongKe.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tblThongKe.setBackground(Utils.secondaryColor);
		tblThongKe.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblThongKe.getTableHeader()
				.setPreferredSize(new Dimension((int) tblThongKe.getTableHeader().getPreferredSize().getWidth(), 36));
		tblThongKe.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tblThongKe.setRowHeight(36);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		tblThongKe.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		scr.setViewportView(tblThongKe);

	}

}

package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import components.button.Button;
import components.panelRound.PanelRound;
import components.scrollbarCustom.ScrollBarCustom;
import utils.Utils;

public class HoaDon_GUI extends JFrame implements ItemListener {
	private static final long serialVersionUID = 1L;
	private PanelRound pnlContent;
	private DefaultTableModel tableModel;
	private JTable tblTienDV;

	public HoaDon_GUI(String maHoaDon, String ngayLap, String tenKhach, String tenNV) {
		setTitle("Hoá đơn tính tiền");
		this.getContentPane().setBackground(new Color(242, 246, 252));
		setBounds((Utils.getScreenWidth() - 500) / 2, 0, 500, Utils.getBodyHeight());
		setLayout(null);

		PanelRound pnlHeader = new PanelRound();
		pnlHeader.setBackground(Color.WHITE);
		pnlHeader.setBounds(0, 0, 485, 70);
		this.add(pnlHeader);
		pnlHeader.setLayout(null);

		JLabel lblTenQuanBold = new JLabel("Tên quán:");
		lblTenQuanBold.setBounds(10, 0, 400, 20);
		lblTenQuanBold.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblTenQuanBold.setForeground(new Color(100, 100, 100));
		pnlHeader.add(lblTenQuanBold);

		JLabel lblTenQuanPlain = new JLabel("Karaoke Nice");
		lblTenQuanPlain.setBounds(75, 0, 400, 20);
		lblTenQuanPlain.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTenQuanPlain.setForeground(new Color(100, 100, 100));
		pnlHeader.add(lblTenQuanPlain);

		JLabel lblDiaChiBold = new JLabel("Địa chỉ:");
		lblDiaChiBold.setBounds(10, 20, 400, 20);
		lblDiaChiBold.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblDiaChiBold.setForeground(new Color(100, 100, 100));
		pnlHeader.add(lblDiaChiBold);

		JLabel lblDiaChiPlain = new JLabel("Lê Thị Hồng, Phường 17, Gò Vấp, T.P Hồ Chí Minh");
		lblDiaChiPlain.setBounds(60, 20, 400, 20);
		lblDiaChiPlain.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDiaChiPlain.setForeground(new Color(100, 100, 100));
		pnlHeader.add(lblDiaChiPlain);

		JLabel lblLienHeBold = new JLabel("Liên hệ:");
		lblLienHeBold.setBounds(10, 40, 400, 20);
		lblLienHeBold.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblLienHeBold.setForeground(new Color(100, 100, 100));
		pnlHeader.add(lblLienHeBold);

		JLabel lblLienHePlain = new JLabel("033 673 5243 - 097 888 6331");
		lblLienHePlain.setBounds(65, 40, 400, 20);
		lblLienHePlain.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblLienHePlain.setForeground(new Color(100, 100, 100));
		pnlHeader.add(lblLienHePlain);

		pnlContent = new PanelRound();
		pnlContent.setBackground(new Color(242, 246, 252));
		pnlContent.setBounds(10, 80, 465, 450);
		this.add(pnlContent);
		pnlContent.setLayout(null);

		JLabel lblTieuDe = new JLabel("HOÁ ĐƠN TÍNH TIỀN");
		lblTieuDe.setBounds(130, 0, 400, 28);
		lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTieuDe.setForeground(new Color(100, 100, 100));
		pnlContent.add(lblTieuDe);

		PanelRound pnlThongTin = new PanelRound();
		pnlThongTin.setBackground(new Color(242, 246, 252));
		pnlThongTin.setBounds(10, 35, 445, 50);
		pnlContent.add(pnlThongTin);
		pnlThongTin.setLayout(null);

		// Mã hoá đơn
		JPanel pnlHoaDon = new JPanel();
		pnlHoaDon.setBounds(0, 0, 445 / 2 - 10, 28);
		pnlHoaDon.setBackground(new Color(242, 246, 252));
		pnlHoaDon.setLayout(new BorderLayout());
		pnlThongTin.add(pnlHoaDon);

		JLabel lblHoaDon = new JLabel("Mã hoá đơn: ");
		lblHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblHoaDon.setForeground(new Color(100, 100, 100));
		pnlHoaDon.add(lblHoaDon, BorderLayout.WEST);

		JLabel lblHoaDonKQ = new JLabel("");
		lblHoaDonKQ.setText(maHoaDon);
		lblHoaDonKQ.setBounds(-1, 0, 299, 28);
		lblHoaDonKQ.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHoaDonKQ.setForeground(new Color(100, 100, 100));
		pnlHoaDon.add(lblHoaDonKQ, BorderLayout.EAST);

		JPanel pnlNgayLap = new JPanel();
		pnlNgayLap.setBounds(220, 0, 445 / 2, 28);
		pnlNgayLap.setBackground(new Color(242, 246, 252));
		pnlNgayLap.setLayout(new BorderLayout());
		pnlThongTin.add(pnlNgayLap);

		JLabel lblNgayLap = new JLabel("Ngày lập: ");
		lblNgayLap.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNgayLap.setForeground(new Color(100, 100, 100));
		pnlNgayLap.add(lblNgayLap, BorderLayout.WEST);

		JLabel lblNgayLapKQ = new JLabel("");
		lblNgayLapKQ.setText(ngayLap);
		lblNgayLapKQ.setBounds(-1, 0, 299, 28);
		lblNgayLapKQ.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNgayLapKQ.setForeground(new Color(100, 100, 100));
		pnlNgayLap.add(lblNgayLapKQ, BorderLayout.EAST);

		JPanel pnlTenKH = new JPanel();
		pnlTenKH.setBounds(0, 25, 445 / 2 - 10, 28);
		pnlTenKH.setBackground(new Color(242, 246, 252));
		pnlTenKH.setLayout(new BorderLayout());
		pnlThongTin.add(pnlTenKH);

		JLabel lblKH = new JLabel("Tên KH: ");
		lblKH.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblKH.setForeground(new Color(100, 100, 100));
		pnlTenKH.add(lblKH, BorderLayout.WEST);

		JLabel lblKHKQ = new JLabel("");
		lblKHKQ.setText(tenKhach);
		lblKHKQ.setBounds(-1, 0, 299, 28);
		lblKHKQ.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblKHKQ.setForeground(new Color(100, 100, 100));
		pnlTenKH.add(lblKHKQ, BorderLayout.EAST);

		JPanel pnlTenNV = new JPanel();
		pnlTenNV.setBounds(220, 25, 445 / 2, 28);
		pnlTenNV.setBackground(new Color(242, 246, 252));
		pnlTenNV.setLayout(new BorderLayout());
		pnlThongTin.add(pnlTenNV);

		JLabel lblNV = new JLabel("Tên NV: ");
		lblNV.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNV.setForeground(new Color(100, 100, 100));
		pnlTenNV.add(lblNV, BorderLayout.WEST);

		JLabel lblNVKQ = new JLabel("");
		lblNVKQ.setText(tenNV);
		lblNVKQ.setBounds(-1, 0, 299, 28);
		lblNVKQ.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNVKQ.setForeground(new Color(100, 100, 100));
		pnlTenNV.add(lblNVKQ, BorderLayout.EAST);

		PanelRound pnlTienPhong = new PanelRound();
		pnlTienPhong.setBackground(Color.WHITE);
		pnlTienPhong.setBounds(0, 100, 465, 85);
		pnlTienPhong.setRoundBottomRight(10);
		pnlTienPhong.setRoundTopLeft(10);
		pnlTienPhong.setRoundTopRight(10);
		pnlTienPhong.setRoundBottomLeft(10);
		pnlContent.add(pnlTienPhong);
		pnlTienPhong.setLayout(null);

		// Tổng tiền phòng
		JPanel pnlTongTienPhong = new JPanel();
		pnlTongTienPhong.setBounds(10, 0, 445, 28);
		pnlTongTienPhong.setBackground(Color.WHITE);
		pnlTongTienPhong.setLayout(new BorderLayout());
		pnlTienPhong.add(pnlTongTienPhong);

		JLabel lblTongTienPhong = new JLabel("Tổng tiền phòng: ");
		lblTongTienPhong.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTongTienPhong.setForeground(new Color(100, 100, 100));
		pnlTongTienPhong.add(lblTongTienPhong, BorderLayout.WEST);

		JLabel lblTongTienPhongKQ = new JLabel("333330000 VND");
		lblTongTienPhongKQ.setBounds(-1, 0, 299, 28);
		lblTongTienPhongKQ.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTongTienPhongKQ.setForeground(new Color(100, 100, 100));
		pnlTongTienPhong.add(lblTongTienPhongKQ, BorderLayout.EAST);

		JPanel line = new JPanel();
		line.setBounds(10, 30, 445, 1);
		line.setLayout(null);
		line.setBackground(new Color(100, 100, 100));
		pnlTienPhong.add(line);

		// Giờ bắt đầu
		JPanel pnlGioBD = new JPanel();
		pnlGioBD.setBounds(10, 35, 445 / 2 - 10, 28);
		pnlGioBD.setBackground(Color.WHITE);
		pnlGioBD.setLayout(new BorderLayout());
		pnlTienPhong.add(pnlGioBD);

		JLabel lblGioBD = new JLabel("Giờ bắt đầu: ");
		lblGioBD.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblGioBD.setForeground(new Color(100, 100, 100));
		pnlGioBD.add(lblGioBD, BorderLayout.WEST);

		JLabel lblGioBDKQ = new JLabel("22:02:19 22/08/2022");
		lblGioBDKQ.setBounds(-1, 0, 299, 28);
		lblGioBDKQ.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblGioBDKQ.setForeground(new Color(100, 100, 100));
		pnlGioBD.add(lblGioBDKQ, BorderLayout.EAST);

		JPanel pnlGioKT = new JPanel();
		pnlGioKT.setBounds(230, 35, 445 / 2, 28);
		pnlGioKT.setBackground(Color.WHITE);
		pnlGioKT.setLayout(new BorderLayout());
		pnlTienPhong.add(pnlGioKT);

		JLabel lblGioKT = new JLabel("Giờ kết thúc: ");
		lblGioKT.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblGioKT.setForeground(new Color(100, 100, 100));
		pnlGioKT.add(lblGioKT, BorderLayout.WEST);

		JLabel lblGioKTKQ = new JLabel("22:02:19 22/08/2022");
		lblGioKTKQ.setBounds(-1, 0, 299, 28);
		lblGioKTKQ.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblGioKTKQ.setForeground(new Color(100, 100, 100));
		pnlGioKT.add(lblGioKTKQ, BorderLayout.EAST);

		JPanel pnlThoiGian = new JPanel();
		pnlThoiGian.setBounds(10, 55, 445 / 2 - 10, 28);
		pnlThoiGian.setBackground(Color.WHITE);
		pnlThoiGian.setLayout(new BorderLayout());
		pnlTienPhong.add(pnlThoiGian);

		JLabel lblThoiGian = new JLabel("Thời gian sử dụng: ");
		lblThoiGian.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblThoiGian.setForeground(new Color(100, 100, 100));
		pnlThoiGian.add(lblThoiGian, BorderLayout.WEST);

		JLabel lblThoiGianKQ = new JLabel("2 giờ 7 phút");
		lblThoiGianKQ.setBounds(-1, 0, 299, 28);
		lblThoiGianKQ.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblThoiGianKQ.setForeground(new Color(100, 100, 100));
		pnlThoiGian.add(lblThoiGianKQ, BorderLayout.EAST);

		JPanel pnlGiaGio = new JPanel();
		pnlGiaGio.setBounds(230, 55, 445 / 2, 28);
		pnlGiaGio.setBackground(Color.WHITE);
		pnlGiaGio.setLayout(new BorderLayout());
		pnlTienPhong.add(pnlGiaGio);

		JLabel lblGiaGio = new JLabel("Giá giờ: ");
		lblGiaGio.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblGiaGio.setForeground(new Color(100, 100, 100));
		pnlGiaGio.add(lblGiaGio, BorderLayout.WEST);

		JLabel lblGiaGioKQ = new JLabel("60.000 VND/giờ");
		lblGiaGioKQ.setBounds(-1, 0, 299, 28);
		lblGiaGioKQ.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblGiaGioKQ.setForeground(new Color(100, 100, 100));
		pnlGiaGio.add(lblGiaGioKQ, BorderLayout.EAST);

		PanelRound pnlTienDV = new PanelRound();
		pnlTienDV.setBackground(Color.WHITE);
		pnlTienDV.setBounds(0, 205, 465, 250);
		pnlTienDV.setRoundBottomRight(10);
		pnlTienDV.setRoundTopLeft(10);
		pnlTienDV.setRoundTopRight(10);
		pnlTienDV.setRoundBottomLeft(10);
		pnlContent.add(pnlTienDV);
		pnlTienDV.setLayout(null);

		JLabel lblTongTienDV = new JLabel("Tổng tiền dịch vụ: 244000 VND");
		lblTongTienDV.setBounds(10, 0, 400, 28);
		lblTongTienDV.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTongTienDV.setForeground(new Color(100, 100, 100));
		pnlTienDV.add(lblTongTienDV);

		JPanel line2 = new JPanel();
		line2.setBounds(10, 30, 445, 1);
		line2.setLayout(null);
		line2.setBackground(new Color(100, 100, 100));
		pnlTienDV.add(line2);

		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(10, 40, 445, 180);
		scr.setBackground(Utils.primaryColor);
		ScrollBarCustom scp = new ScrollBarCustom();
		scp.setScrollbarColor(new Color(203, 203, 203));
		scr.setVerticalScrollBar(scp);
		pnlTienDV.add(scr);

		tblTienDV = new JTable() {
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

		tableModel = new DefaultTableModel(new String[] { "Tên", "Số lượng", "Giá", "Tổng tiền" }, 0);
		tblTienDV.setModel(tableModel);
		tblTienDV.getColumnModel().getColumn(0).setPreferredWidth(445 / 4);
		tblTienDV.getColumnModel().getColumn(1).setPreferredWidth(445 / 4 - 10);
		tblTienDV.getColumnModel().getColumn(2).setPreferredWidth(445 / 4);
		tblTienDV.getColumnModel().getColumn(3).setPreferredWidth(445 / 4);

		tblTienDV.getTableHeader().setBackground(Utils.primaryColor);
		tblTienDV.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tblTienDV.getTableHeader().setForeground(Color.WHITE);
		tblTienDV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblTienDV.getTableHeader()
				.setPreferredSize(new Dimension((int) tblTienDV.getTableHeader().getPreferredSize().getWidth(), 25));
		tblTienDV.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		tblTienDV.setRowHeight(25);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tblTienDV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tblTienDV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		scr.setViewportView(tblTienDV);
		addRowRandomData();

		PanelRound pnlFooter = new PanelRound();
		pnlFooter.setBounds(10, Utils.getBodyHeight() - 100, 465, 50);
		pnlFooter.setBackground(new Color(242, 246, 252));
		this.add(pnlFooter);
		pnlFooter.setLayout(null);

		Button btnInHD = new Button("In hoá đơn");
		btnInHD.setFocusable(false);
		btnInHD.setForeground(Color.WHITE);
		btnInHD.setColor(Utils.primaryColor);
		btnInHD.setBorderColor(Utils.primaryColor);
		btnInHD.setRadius(10);
		btnInHD.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnInHD.setBounds(465 - 160, 5, 160, 44);
		btnInHD.setColorOver(Utils.primaryColor);
		btnInHD.setColorTextOver(Color.WHITE);
		btnInHD.setColorTextOut(Color.WHITE);
		btnInHD.setColorClick(Utils.primaryColor);
		btnInHD.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlFooter.add(btnInHD);

		btnInHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// print the panel to pdf
				Document document = new Document();
				try {
					String path = "HoaDon" + maHoaDon + ".pdf";
					PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
					document.open();
					PdfContentByte contentByte = writer.getDirectContent();
					PdfTemplate template = contentByte.createTemplate(500, 500);
					@SuppressWarnings("deprecation")
					Graphics2D g2 = template.createGraphics(500, 500);
					pnlContent.print(g2);
					g2.dispose();
					contentByte.addTemplate(template, 30, 300);

					File pdfFile = new File(path);
					if (Desktop.isDesktopSupported()) {
						Desktop.getDesktop().open(pdfFile);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					if (document.isOpen()) {
						document.close();
					}
				}
			}
		});

	}

	private void addRowRandomData() {
		for (int i = 0; i < 4; i++) {
			String tenHH[] = { "7 Up", "Trái cây size L", "Snack tôm", "Pepsi" };
			String ten = tenHH[i];
			int soLuong = ThreadLocalRandom.current().nextInt(1, 10 + 1);
			int giaHH[] = { 16000, 25000, 5000, 16000 };
			int gia = giaHH[i];
			long tongTien = soLuong * gia;
			tableModel.addRow(new String[] { ten, soLuong + "", gia + " VND", tongTien + " VND" });
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}

}

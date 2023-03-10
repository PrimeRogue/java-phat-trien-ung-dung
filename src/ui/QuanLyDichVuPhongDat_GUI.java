package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import components.button.Button;
import components.jDialog.JDialogCustom;
import components.notification.Notification;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import dao.ChiTietDatPhong_DAO;
import dao.ChiTietDichVu_DAO;
import dao.DichVu_DAO;
import dao.DonDatPhong_DAO;
import dao.KhachHang_DAO;
import dao.LoaiDichVu_DAO;
import dao.Phong_DAO;
import entity.ChiTietDatPhong;
import entity.ChiTietDichVu;
import entity.DichVu;
import entity.DonDatPhong;
import entity.KhachHang;
import entity.LoaiDichVu;
import entity.Phong;
import utils.Utils;

public class QuanLyDichVuPhongDat_GUI extends JFrame implements ItemListener {
	private static final long serialVersionUID = 1L;
	private QuanLyDichVuPhongDat_GUI _this;
	private ChiTietDatPhong_DAO chiTietDatPhong_DAO;
	private ChiTietDichVu_DAO chiTietDichVu_DAO;
	private JComboBox<String> cmbDatPhong;
	private JComboBox<String> cmbLoaiDV;
	private JComboBox<String> cmbPhongDat;
	private JComboBox<String> cmbTenDV;
	private DonDatPhong_DAO datPhong_DAO;
	private DichVu_DAO dichVu_DAO;
	private List<DichVu> dsDVDaChon;
	private ItemListener evtCmbDatPhong = new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.DESELECTED)
				return;
			maDatPhongChon = (String) cmbDatPhong.getSelectedItem();
			if (maDatPhongChon.equals(labelCmbDatPhong)) {
				setAllMaPhongToCombobox();
			} else {
				List<Phong> dsPhongDangThue = datPhong_DAO.getPhongDangThue(maDatPhongChon);
				emptyComboBox(cmbPhongDat, labelCmbPhongDat);
				for (Phong phong : dsPhongDangThue)
					cmbPhongDat.addItem(phong.getMaPhong());
			}
		}
	};
	private ItemListener evtCmbPhongDat = new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.DESELECTED)
				return;
			maPhongChon = (String) cmbPhongDat.getSelectedItem();

			// L??m s???ch table3 v?? danh s??ch d???ch v??? ch???n
			tableModel3.setRowCount(0);

			if (maPhongChon.equals(labelCmbPhongDat))
				return;
			String maDonDatPhong = (String) cmbDatPhong.getSelectedItem();
			if (maDonDatPhong.equals(labelCmbDatPhong))
				maDonDatPhong = datPhong_DAO.getDonDatPhong(maPhongChon).getMaDonDatPhong();

			// Bi???n t???m
			DichVu dichVuTrongChiTiet = new DichVu();
			// l???y m?? ph??ng ???????c ch???n
			if (dsDVDaChon == null)
				dsDVDaChon = new ArrayList<>();
			else
				dsDVDaChon.clear();

			List<ChiTietDichVu> dsDichVu = chiTietDichVu_DAO.getAllChiTietDichVu(maDonDatPhong, maPhongChon);
			for (ChiTietDichVu chiTietDichVu : dsDichVu) {
				dichVuTrongChiTiet = chiTietDichVu.getDichVu();
				dichVuTrongChiTiet.setSoLuong(chiTietDichVu.getSoLuong());

				dsDVDaChon.add(dichVuTrongChiTiet);
			}

			addRow3(dsDVDaChon);
			capNhatThanhTien();
		}
	};
	private KhachHang khachHang;
	private KhachHang_DAO khachHang_DAO;
	private final String labelCmbDatPhong = "????n ?????t ph??ng";
	private final String labelCmbPhongDat = "M?? ph??ng";
	private LoaiDichVu_DAO loaiDichVu_DAO;
	private String maDatPhongChon;
	private String maPhongChon;
	private Phong_DAO phong_DAO;
	private JPanel pnlContent;
	private JPanel pnlDV;
	private DefaultTableModel tableModel2, tableModel3;
	private JTable tbl2, tbl3;
	private TextField txtSoDienThoai;
	private TextField txtTenKhachHang;
	private JTextField txtTongTien;

	public QuanLyDichVuPhongDat_GUI(QuanLyDatPhong_GUI quanLyDatPhongGUI, JFrame parentFrame) {
		_this = this;
		khachHang_DAO = new KhachHang_DAO();
		loaiDichVu_DAO = new LoaiDichVu_DAO();
		datPhong_DAO = new DonDatPhong_DAO();
		dichVu_DAO = new DichVu_DAO();
		chiTietDichVu_DAO = new ChiTietDichVu_DAO();
		chiTietDatPhong_DAO = new ChiTietDatPhong_DAO();
		phong_DAO = new Phong_DAO();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 466);
		pnlContent = new JPanel();
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlContent);
		pnlContent.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(0, 0, 950, 466);
		pnlContent.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel pnlHeading = new JPanel();
		pnlHeading.setBackground(Utils.primaryColor);
		pnlHeading.setBounds(0, 0, 950, 50);
		pnlContainer.add(pnlHeading);
		pnlHeading.setLayout(null);

		JLabel lblTitle = new JLabel("Qu???n l?? d???ch v???");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(325, 9, 300, 32);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
		pnlHeading.add(lblTitle);

		JPanel pnlBody = new JPanel();
		pnlBody.setBackground(Utils.secondaryColor);
		pnlBody.setBounds(40, 50, 870, 398);
		pnlContainer.add(pnlBody);
		pnlBody.setLayout(null);

		txtSoDienThoai = new TextField();
		txtSoDienThoai.setBackground(Utils.secondaryColor);
		txtSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoDienThoai.setLabelText("S??? ??i???n tho???i kh??ch");
		txtSoDienThoai.setBounds(0, 0, 380, 55);
		pnlBody.add(txtSoDienThoai);
		txtSoDienThoai.setColumns(10);

		Button btnSearchSoDienThoai = new Button();
		btnSearchSoDienThoai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String soDienThoai = txtSoDienThoai.getText().trim();

				if (Utils.isSoDienThoai(soDienThoai)) {
					khachHang = khachHang_DAO.getKhachHang(soDienThoai);

					if (khachHang != null) {
						txtTenKhachHang.setText(khachHang.getHoTen());
						List<DonDatPhong> listDatPhongDangThue = datPhong_DAO.getAllDonDatPhongDangThue();
						emptyComboBox(cmbDatPhong, "M?? ?????t ph??ng");
						for (DonDatPhong datPhong : listDatPhongDangThue) {
							if (datPhong.getKhachHang().getMaKhachHang().equals(khachHang.getMaKhachHang())) {
								cmbDatPhong.addItem(datPhong.getMaDonDatPhong());
							}
						}
					} else {
						JDialogCustom jDialogCustom = new JDialogCustom(_this);

						jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								Main main = new Main();
								main.addPnlBody(new ThemKhachHang_GUI(main, _this, soDienThoai), "Th??m kh??ch h??ng", 2,
										0);
								main.setVisible(true);
								setVisible(false);
							}
						});

						jDialogCustom.showMessage("Warning",
								"Kh??ch h??ng kh??ng c?? trong h??? th???ng, b???n c?? mu???n th??m kh??ch h??ng m???i kh??ng?");
					}
				} else {
					new Notification(_this, components.notification.Notification.Type.ERROR,
							"S??? ??i???n tho???i kh??ng h???p l???").showNotification();
					txtSoDienThoai.setError(true);
				}
				if (txtTenKhachHang.getText().equals("") || txtTenKhachHang == null) {
					List<DonDatPhong> listDatPhongDangThue = datPhong_DAO.getAllDonDatPhongDangThue();
					emptyComboBox(cmbDatPhong, "M?? ?????t ph??ng");
					for (DonDatPhong datPhong : listDatPhongDangThue) {
						cmbDatPhong.addItem(datPhong.getMaDonDatPhong());
					}
				} else {
					emptyComboBox(cmbDatPhong, "M?? ?????t ph??ng");
					cmbDatPhong.addItem(datPhong_DAO.getDonDatPhongNgayTheoSoDienThoai(soDienThoai).getMaDonDatPhong());
					cmbDatPhong.setSelectedIndex(1);
				}
			}

		});

		btnSearchSoDienThoai.setFocusable(false);
		btnSearchSoDienThoai.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSearchSoDienThoai.setRadius(4);
		btnSearchSoDienThoai.setBorderColor(Utils.secondaryColor);
		btnSearchSoDienThoai.setColor(Utils.primaryColor);
		btnSearchSoDienThoai.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.9f));
		btnSearchSoDienThoai.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnSearchSoDienThoai.setIcon(new ImageIcon("Icon\\user_searching.png"));
		btnSearchSoDienThoai.setBounds(410, 2, 50, 50);
		pnlBody.add(btnSearchSoDienThoai);

		txtTenKhachHang = new TextField();
		txtTenKhachHang.setLabelText("H??? t??n kh??ch");
		txtTenKhachHang.setEditable(false);
		txtTenKhachHang.setBackground(Utils.secondaryColor);
		txtTenKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenKhachHang.setBounds(490, 0, 380, 55);
		pnlBody.add(txtTenKhachHang);
		txtTenKhachHang.setColumns(10);

		pnlDV = new JPanel();
		pnlDV.setBackground(Utils.secondaryColor);
		pnlDV.setBounds(0, 121, 870, 225);
		pnlBody.add(pnlDV);
		pnlDV.setLayout(null);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(410, 0, 36, 225);
		pnlDV.add(pnlActions);
		pnlActions.setLayout(null);

		Button btnChonDichVu = new Button("");
		btnChonDichVu.setFocusable(false);
		btnChonDichVu.setRadius(8);
		btnChonDichVu.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnChonDichVu.setColor(Utils.primaryColor);
		btnChonDichVu.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnChonDichVu.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.6f));
		btnChonDichVu.setBorderColor(Utils.secondaryColor);
		btnChonDichVu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row2 = tbl2.getSelectedRow();
				if (row2 != -1) {
					maPhongChon = (String) cmbPhongDat.getSelectedItem();
					// ki???m tra m?? ph??ng
					if (maPhongChon.equals(labelCmbPhongDat)) {
						JOptionPane.showMessageDialog(_this, "Vui l??ng ch???n m?? ph??ng", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					String maDonDatPhong = (String) cmbDatPhong.getSelectedItem();

					if (maDonDatPhong.equals(labelCmbDatPhong))
						maDonDatPhong = datPhong_DAO.getDonDatPhong(maPhongChon).getMaDonDatPhong();

					if (dsDVDaChon == null)
						dsDVDaChon = new ArrayList<>();

					String maDichVu = (String) tableModel2.getValueAt(row2, 0);
					DichVu dichVu = new DichVu(maDichVu);
					ChiTietDatPhong chiTietDatPhong = chiTietDatPhong_DAO.getChiTietDatPhongDangThue(maDonDatPhong,
							maPhongChon);
					chiTietDatPhong.setDonDatPhong(new DonDatPhong(maDonDatPhong));
					ChiTietDichVu chiTietDichVu = new ChiTietDichVu(dichVu, chiTietDatPhong, 1);

					if (dsDVDaChon.contains(dichVu)) {

						if (!chiTietDichVu_DAO.capNhatSoLuongDichVu(chiTietDichVu, true)) {
							new Notification(_this, components.notification.Notification.Type.ERROR,
									"Th??m d???ch v??? th???t b???i").showNotification();
							return;
						}
					} else {

						if (!chiTietDichVu_DAO.themChiTietDichVu(chiTietDichVu)) {
							new Notification(_this, components.notification.Notification.Type.ERROR,
									"Th??m d???ch v??? th???t b???i").showNotification();
							return;
						}
					}
					new Notification(_this, components.notification.Notification.Type.SUCCESS,
							"Th??m d???ch v??? th??nh c??ng").showNotification();

					dsDVDaChon.clear();
					List<ChiTietDichVu> ListChiTietDV = chiTietDichVu_DAO.getAllChiTietDichVu(maDonDatPhong,
							maPhongChon);
					DichVu dichVuTrongChiTiet;
					// l???y danh s??ch chi ti???t c???a ph??ng ???????c ch???n
					for (ChiTietDichVu chiTietDV : ListChiTietDV) {
						dichVuTrongChiTiet = chiTietDV.getDichVu();
						dichVuTrongChiTiet.setSoLuong(chiTietDV.getSoLuong());
						dsDVDaChon.add(dichVuTrongChiTiet);
					}
					tableModel3.setRowCount(0);
					List<DichVu> listDV = dichVu_DAO.getAllDichVuCoSoLuongLonHon0();
					addRow2(listDV);
					loadTable3();
					capNhatThanhTien();

				}
			}
		});
		btnChonDichVu.setIcon(new ImageIcon("Icon\\rightArrow_32x32.png"));
		btnChonDichVu.setBounds(0, 70, 36, 36);
		pnlActions.add(btnChonDichVu);

		Button btnXoaDichVu = new Button("");
		btnXoaDichVu.setFocusable(false);
		btnXoaDichVu.setRadius(8);
		btnXoaDichVu.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnXoaDichVu.setColor(Utils.primaryColor);
		btnXoaDichVu.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnXoaDichVu.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.6f));
		btnXoaDichVu.setBorderColor(Utils.secondaryColor);
		btnXoaDichVu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl3.getSelectedRow();
				if (row != -1) {

					JDialogCustom jDialogCustom = new JDialogCustom(_this);

					jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							maPhongChon = (String) cmbPhongDat.getSelectedItem();
							String maDonDatPhong = (String) cmbDatPhong.getSelectedItem();
							if (maDonDatPhong.equals(labelCmbDatPhong))
								maDonDatPhong = datPhong_DAO.getDonDatPhong(maPhongChon).getMaDonDatPhong();

							DichVu dichVuXoa = new DichVu((String) tableModel3.getValueAt(row, 0));
							if (chiTietDichVu_DAO.xoaChiTietDichVu(dichVuXoa.getMaDichVu(), maDonDatPhong,
									maPhongChon)) {
								if (dichVu_DAO.capNhatSoLuongDichVuTang(dichVuXoa.getMaDichVu(),
										Integer.parseInt(((String) tbl3.getValueAt(row, 2))))) {
									new Notification(_this, components.notification.Notification.Type.SUCCESS,
											"X??a d???ch v??? th??nh c??ng").showNotification();
								} else {
									new Notification(_this, components.notification.Notification.Type.ERROR,
											"X??a d???ch v??? th???t b???i").showNotification();
									return;
								}
							} else {
								new Notification(_this, components.notification.Notification.Type.ERROR,
										"X??a d???ch v??? th???t b???i").showNotification();
								return;
							}

							dsDVDaChon.clear();
							List<ChiTietDichVu> ListChiTietDV = chiTietDichVu_DAO.getAllChiTietDichVu(maDonDatPhong,
									maPhongChon);
							DichVu dichVuTrongChiTiet;
							// l???y danh s??ch chi ti???t c???a ph??ng ???????c ch???n
							for (ChiTietDichVu chiTietDV : ListChiTietDV) {
								dichVuTrongChiTiet = chiTietDV.getDichVu();
								dichVuTrongChiTiet.setSoLuong(chiTietDV.getSoLuong());
								dsDVDaChon.add(dichVuTrongChiTiet);
							}
							tableModel3.setRowCount(0);
							List<DichVu> listDV = dichVu_DAO.getAllDichVuCoSoLuongLonHon0();
							addRow2(listDV);
							loadTable3();
							capNhatThanhTien();

						}
					});

					jDialogCustom.getBtnCancel().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							return;
						}
					});
					jDialogCustom.showMessage("Warning", "B???n ch???c ch???n mu???n x??a d???ch v??? n??y");
				}
			}
		});
		btnXoaDichVu.setIcon(new ImageIcon("Icon\\bin.png"));
		btnXoaDichVu.setBounds(0, 130, 36, 36);
		pnlActions.add(btnXoaDichVu);

		JScrollPane scrDanhSachDichVu = new JScrollPane();
		scrDanhSachDichVu.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrDanhSachDichVu.setBackground(Utils.secondaryColor);
		scrDanhSachDichVu.setBounds(0, 0, 400, 225);
		pnlDV.add(scrDanhSachDichVu);
		ScrollBarCustom scbDanhSachDichVu = new ScrollBarCustom();
		// Set color scrollbar thumb
		scbDanhSachDichVu.setScrollbarColor(new Color(203, 203, 203));
		scrDanhSachDichVu.setVerticalScrollBar(scbDanhSachDichVu);

		tbl2 = new JTable() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			/**
			 * Set m??u t???ng d??ng cho Table
			 */
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (isRowSelected(row))
					c.setBackground(Utils.getOpacity(Utils.primaryColor, 0.3f));
				else if (row % 2 == 0)
					c.setBackground(Color.WHITE);
				else
					c.setBackground(new Color(232, 232, 232));
				return c;
			}
		};

		tableModel2 = new DefaultTableModel(new String[] { "M?? DV", "T??n DV", "S??? l?????ng", "????n gi??" }, 0);

		tbl2.setModel(tableModel2);
		tbl2.setFocusable(false);
		tbl2.getTableHeader().setBackground(Utils.primaryColor);
		tbl2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl2.setBackground(Color.WHITE);
		tbl2.getTableHeader()
				.setPreferredSize(new Dimension((int) tbl2.getTableHeader().getPreferredSize().getWidth(), 36));
		tbl2.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl2.setRowHeight(36);
		tbl2.getColumnModel().getColumn(0).setPreferredWidth(80);
		tbl2.getColumnModel().getColumn(1).setPreferredWidth(130);
		tbl2.getColumnModel().getColumn(2).setPreferredWidth(80);
		tbl2.getColumnModel().getColumn(3).setPreferredWidth(110);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		tbl2.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		tbl2.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		scrDanhSachDichVu.setViewportView(tbl2);

		JPanel pnlBtnGroup = new JPanel();
		pnlBtnGroup.setBackground(Utils.secondaryColor);
		pnlBtnGroup.setBounds(0, 362, 900, 36);
		pnlBody.add(pnlBtnGroup);
		pnlBtnGroup.setLayout(null);

		Button btnQuayLai = new Button("Quay l???i");
		btnQuayLai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				quanLyDatPhongGUI.closeJFrameSub();
			}
		});
		btnQuayLai.setFocusable(false);
		btnQuayLai.setRadius(8);
		btnQuayLai.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnQuayLai.setBorderColor(Utils.secondaryColor);
		btnQuayLai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnQuayLai.setBounds(0, 0, 150, 36);
		pnlBtnGroup.add(btnQuayLai);

		txtTongTien = new JTextField();
		txtTongTien.setEditable(false);
		txtTongTien.setText("T???ng ti???n: " + Utils.formatTienTe(0));
		txtTongTien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTongTien.setBackground(new Color(203, 239, 255));
		txtTongTien.setBounds(649, 0, 220, 36);
		pnlBtnGroup.add(txtTongTien);

		JPanel pnlFilter = new JPanel();
		pnlFilter.setBackground(Utils.secondaryColor);
		pnlFilter.setBounds(0, 70, 870, 36);
		pnlBody.add(pnlFilter);
		pnlFilter.setLayout(null);

		cmbDatPhong = new JComboBox<>();
		setAllDonDatPhongDangThueToCombobox();
		cmbDatPhong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbDatPhong.setBackground(Utils.primaryColor);
		cmbDatPhong.setBounds(0, 0, 190, 36);

		pnlFilter.add(cmbDatPhong);

		cmbPhongDat = new JComboBox<>();
		setAllMaPhongToCombobox();
		cmbPhongDat.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbPhongDat.setBackground(Utils.primaryColor);
		cmbPhongDat.setBounds(210, 0, 190, 36);
		pnlFilter.add(cmbPhongDat);

		cmbTenDV = new JComboBox<>();
		cmbTenDV.addItem("T??n d???ch v???");
		ArrayList<DichVu> listDichVu = (ArrayList<DichVu>) dichVu_DAO.getAllDichVuCoSoLuongLonHon0();
		for (DichVu dichVu : listDichVu)
			cmbTenDV.addItem(dichVu.getTenDichVu());
		cmbTenDV.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbTenDV.setBackground(Utils.primaryColor);
		cmbTenDV.setBounds(460, 0, 130, 36);
		pnlFilter.add(cmbTenDV);

		cmbLoaiDV = new JComboBox<>();
		cmbLoaiDV.setBackground(Utils.primaryColor);
		cmbLoaiDV.addItem("Ph??n lo???i");
		ArrayList<LoaiDichVu> listLoaiDV = (ArrayList<LoaiDichVu>) loaiDichVu_DAO.getAllLoaiDichVu();
		for (LoaiDichVu loaiDV : listLoaiDV)
			cmbLoaiDV.addItem(loaiDV.getTenLoaiDichVu());
		cmbLoaiDV.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbLoaiDV.setBounds(610, 0, 130, 36);
		pnlFilter.add(cmbLoaiDV);

		Button btnLamMoi = new Button("L??m m???i");
		btnLamMoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cmbLoaiDV.removeItemListener(_this);
				cmbTenDV.removeItemListener(_this);
				cmbDatPhong.removeItemListener(evtCmbDatPhong);
				cmbPhongDat.removeItemListener(evtCmbPhongDat);
				cmbLoaiDV.setSelectedIndex(0);
				cmbTenDV.setSelectedIndex(0);
				cmbDatPhong.setSelectedIndex(0);
				cmbPhongDat.setSelectedIndex(0);
				cmbLoaiDV.addItemListener(_this);
				cmbTenDV.addItemListener(_this);
				cmbDatPhong.addItemListener(evtCmbDatPhong);
				cmbPhongDat.addItemListener(evtCmbPhongDat);

			}
		});
		btnLamMoi.setFocusable(false);
		btnLamMoi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnLamMoi.setBounds(760, 0, 110, 36);
		pnlFilter.add(btnLamMoi);

		txtSoDienThoai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				khachHang = null;
				txtTenKhachHang.setText("");
				txtSoDienThoai.setError(false);
			}
		});

		txtSoDienThoai.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtSoDienThoai.setError(false);
			}
		});

		showDanhSachDichVuDaChon();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				List<DichVu> listDV = dichVu_DAO.getAllDichVuCoSoLuongLonHon0();

				cmbTenDV.removeItemListener(_this);

				addRow2(listDV);

				String soDienThoai = txtSoDienThoai.getText().trim();

				if (Utils.isSoDienThoai(soDienThoai)) {
					khachHang = khachHang_DAO.getKhachHang(soDienThoai);

					if (khachHang != null) {
						txtTenKhachHang.setText(khachHang.getHoTen());
						txtTenKhachHang.requestFocus();
					}
				}
				cmbTenDV.addItemListener(_this);
				cmbLoaiDV.addItemListener(_this);
				cmbDatPhong.addItemListener(evtCmbDatPhong);
				cmbPhongDat.addItemListener(evtCmbPhongDat);
			}
		});
	}

	/**
	 * Th??m m???t DV v??o table
	 *
	 * @param dichVu dichVu mu???n th??m
	 */
	private void addRow2(DichVu dichVu) {
		tableModel2.addRow(new String[] { dichVu.getMaDichVu(), dichVu.getTenDichVu(), dichVu.getSoLuong() + "",
				Utils.formatTienTe(dichVu.getGiaBan()) });
	}

	/**
	 * Th??m danh s??ch c??c DV v??o table
	 *
	 * @param list danh s??ch c??c DV c???n th??m
	 */
	private void addRow2(List<DichVu> list) {
		tableModel2.setRowCount(0);

		list.forEach(dichVu -> addRow2(dichVu));
	}

	/**
	 * Th??m m???t DV v??o table
	 *
	 * @param dichVu dichVu mu???n th??m
	 */
	private void addRow3(DichVu dichVu) {
		tableModel3.addRow(new String[] { dichVu.getMaDichVu(), dichVu.getTenDichVu(),
				String.valueOf(dichVu.getSoLuong()), Utils.formatTienTe(dichVu.getGiaBan() * dichVu.getSoLuong()) });

	}

	/**
	 * Th??m danh s??ch c??c DV v??o table
	 *
	 * @param list danh s??ch c??c DV c???n th??m
	 */
	private void addRow3(List<DichVu> list) {
		tableModel3.setRowCount(0);
		list.forEach(dichVu -> addRow3(dichVu));
	}

	private void capNhatThanhTien() {
		if (dsDVDaChon == null)
			txtTongTien.setText("T???ng ti???n: " + Utils.formatTienTe(0));
		double tongTien = 0;
		for (DichVu dichVu : dsDVDaChon)
			tongTien += dichVu.getGiaBan() * dichVu.getSoLuong();
		txtTongTien.setText("T???ng ti???n: " + Utils.formatTienTe(tongTien));
	}

	/**
	 * X??a t???t c??c c??c item trong ComboBox v?? th??m label v??o ComboBox
	 *
	 * @param jComboBox
	 */
	private void emptyComboBox(JComboBox<String> jComboBox) {
		jComboBox.removeAllItems();
	}

	/**
	 * X??a t???t c??c c??c item trong ComboBox v?? th??m label v??o ComboBox
	 *
	 * @param jComboBox ComboBox
	 * @param label
	 */
	private void emptyComboBox(JComboBox<String> jComboBox, String label) {
		emptyComboBox(jComboBox);
		jComboBox.addItem(label);
	}

	/**
	 * L???c danh s??ch c??c ph??ng theo m?? ph??ng, lo???i ph??ng v?? s??? l?????ng
	 */
	private void filterDichVu() {
		String maDV = (String) cmbTenDV.getSelectedItem();
		String loaiDV = (String) cmbLoaiDV.getSelectedItem();

		if (maDV.equals("T??n d???ch v???"))
			maDV = "";
		if (loaiDV.equals("Ph??n lo???i"))
			loaiDV = "";

		List<DichVu> list = dichVu_DAO.getDichVuTheoMaVaLoai(maDV, loaiDV);
		tableModel2.setRowCount(0);
		addRow2(list);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.DESELECTED)
			return;

		filterDichVu();
	}

	private void loadTable3() {
		maPhongChon = (String) cmbPhongDat.getSelectedItem();

		if (maPhongChon.equals(labelCmbPhongDat))
			return;
		addRow3(dsDVDaChon);
	}

	private void setAllDonDatPhongDangThueToCombobox() {
		emptyComboBox(cmbDatPhong, labelCmbDatPhong);
		List<DonDatPhong> listDatPhongDangThue = datPhong_DAO.getAllDonDatPhongDangThue();
		for (DonDatPhong datPhong : listDatPhongDangThue)
			cmbDatPhong.addItem(datPhong.getMaDonDatPhong());
	}

	private void setAllMaPhongToCombobox() {
		emptyComboBox(cmbPhongDat, labelCmbPhongDat);
		List<Phong> dsPhongDangThue = phong_DAO.getAllPhongDangThue();
		for (Phong phong : dsPhongDangThue)
			cmbPhongDat.addItem(phong.getMaPhong());
	}

	private void showDanhSachDichVuDaChon() {
		JScrollPane scrDanhSachDichVuDuocChon = new JScrollPane();
		scrDanhSachDichVuDuocChon.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrDanhSachDichVuDuocChon.setBackground(Utils.secondaryColor);
		scrDanhSachDichVuDuocChon.setBounds(460, 0, 410, 225);
		pnlDV.add(scrDanhSachDichVuDuocChon);
		ScrollBarCustom scbDanhSachDichVuDuocChon = new ScrollBarCustom();
		// Set color scrollbar thumb
		scbDanhSachDichVuDuocChon.setScrollbarColor(new Color(203, 203, 203));
		scrDanhSachDichVuDuocChon.setVerticalScrollBar(scbDanhSachDichVuDuocChon);

		tbl3 = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 2)
					return true;
				return false;
			}

			@Override
			/**
			 * Set m??u t???ng d??ng cho Table
			 */
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (isRowSelected(row))
					c.setBackground(Utils.getOpacity(Utils.primaryColor, 0.3f));
				else if (row % 2 == 0)
					c.setBackground(Color.WHITE);
				else
					c.setBackground(new Color(232, 232, 232));
				return c;
			}
		};

		tableModel3 = new DefaultTableModel(new String[] { "M?? DV", "T??n DV", "S??? l?????ng", "Th??nh ti???n" }, 0);
		tbl3.setModel(tableModel3);
		tbl3.setFocusable(false);
		tbl3.getTableHeader().setBackground(Utils.primaryColor);
		tbl3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl3.setBackground(Color.WHITE);
		tbl3.getTableHeader()
				.setPreferredSize(new Dimension((int) tbl3.getTableHeader().getPreferredSize().getWidth(), 36));
		tbl3.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl3.setRowHeight(36);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		tbl3.getColumnModel().getColumn(0).setPreferredWidth(80);
		tbl3.getColumnModel().getColumn(1).setPreferredWidth(130);
		tbl3.getColumnModel().getColumn(2).setPreferredWidth(80);
		tbl3.getColumnModel().getColumn(3).setPreferredWidth(120);

		tbl3.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		tbl3.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		scrDanhSachDichVuDuocChon.setViewportView(tbl3);

		tbl3.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int row3 = tbl3.getSelectedRow();
					if (row3 != -1) {
						maPhongChon = (String) cmbPhongDat.getSelectedItem();
						String maDonDatPhong = (String) cmbDatPhong.getSelectedItem();
						if (maDonDatPhong.equals(labelCmbDatPhong))
							maDonDatPhong = datPhong_DAO.getDonDatPhong(maPhongChon).getMaDonDatPhong();
						// D???ch v??? thay ?????i trong table3
						DichVu DichVuThayDoiSoLuong = new DichVu((String) tableModel3.getValueAt(row3, 0));
						// chi ti???t d???ch v??? trong chi ti???t d???ch v??? cua table3
						ChiTietDichVu chiTietDichVuThayDoi = chiTietDichVu_DAO
								.getChiTietDichVuTheoMa(DichVuThayDoiSoLuong.getMaDichVu(), maDonDatPhong, maPhongChon);
						String soLuongDV = (String) tbl3.getValueAt(row3, 2);
						if (soLuongDV.length() <= 0) {
							JOptionPane.showMessageDialog(_this, "Vui l??ng nh???p s??? l?????ng", "Error",
									JOptionPane.ERROR_MESSAGE);
							tableModel3.setRowCount(0);
							loadTable3();
							capNhatThanhTien();
							return;
						}
						if (!Utils.isInteger(soLuongDV)) {
							JOptionPane.showMessageDialog(_this, "S??? l?????ng ph???i nh???p s??? nguy??n", "Error",
									JOptionPane.ERROR_MESSAGE);
							tableModel3.setRowCount(0);
							loadTable3();
							capNhatThanhTien();
							return;
						}

						int soLuongDichVu = Integer.parseInt((String) tbl3.getValueAt(row3, 2));
						// Tr?????ng h???p 1: Gi???m s??? l?????ng d???ch v??? xu???ng 0
						if (soLuongDichVu == 0) {
							if (chiTietDichVu_DAO.xoaChiTietDichVu(DichVuThayDoiSoLuong.getMaDichVu(), maDonDatPhong,
									maPhongChon)) {
								if (dichVu_DAO.capNhatSoLuongDichVuTang(DichVuThayDoiSoLuong.getMaDichVu(),
										chiTietDichVuThayDoi.getSoLuong())) {
									new Notification(_this, components.notification.Notification.Type.SUCCESS,
											"C???p nh???t s??? l?????ng d???ch v??? th??nh c??ng").showNotification();
								} else {
									new Notification(_this, components.notification.Notification.Type.ERROR,
											"C???p nh???t s??? l?????ng d???ch v??? th???t b???i").showNotification();
									return;
								}
							} else {
								new Notification(_this, components.notification.Notification.Type.ERROR,
										"C???p nh???t s??? l?????ng d???ch v??? th???t b???i").showNotification();
								return;
							}
						}
						// Tr?????ng h???p 2: Gi???m s??? l?????ng d???ch v???
						else if (chiTietDichVuThayDoi.getSoLuong() > soLuongDichVu) {
							chiTietDichVuThayDoi.setSoLuong(chiTietDichVuThayDoi.getSoLuong() - soLuongDichVu);

							if (!chiTietDichVu_DAO.capNhatSoLuongDichVu(chiTietDichVuThayDoi, false)) {
								new Notification(_this, components.notification.Notification.Type.ERROR,
										"Th??m d???ch v??? th???t b???i").showNotification();
								return;
							}
							new Notification(_this, components.notification.Notification.Type.SUCCESS,
									"Th??m d???ch v??? th??nh c??ng").showNotification();
						}
						// Tr?????ng h???p 3: T??ng s??? l?????ng d???ch v???
						else if (chiTietDichVuThayDoi.getSoLuong() < soLuongDichVu) {
							DichVu dichVuThayDoi = dichVu_DAO.getDichVuTheoMa(DichVuThayDoiSoLuong.getMaDichVu());
							if (dichVuThayDoi.getSoLuong() < (soLuongDichVu - chiTietDichVuThayDoi.getSoLuong())) {
								JOptionPane.showMessageDialog(_this, "S??? l?????ng t???n kh??ng ?????", "Error",
										JOptionPane.ERROR_MESSAGE);
							} else {
								chiTietDichVuThayDoi.setSoLuong(Integer.parseInt((String) tbl3.getValueAt(row3, 2))
										- chiTietDichVuThayDoi.getSoLuong());
								if (!chiTietDichVu_DAO.capNhatSoLuongDichVu(chiTietDichVuThayDoi, true)) {
									new Notification(_this, components.notification.Notification.Type.ERROR,
											"C???p nh???t s??? l?????ng d???ch v??? th???t b???i").showNotification();
									return;
								}
								new Notification(_this, components.notification.Notification.Type.SUCCESS,
										"C???p nh???t s??? l?????ng d???ch v??? th??nh c??ng").showNotification();
							}
						}
						// L??m m???i l???i c??c table
						dsDVDaChon.clear();
						List<ChiTietDichVu> ListChiTietDV = chiTietDichVu_DAO.getAllChiTietDichVu(maDonDatPhong,
								maPhongChon);
						DichVu dichVuTrongChiTiet;
						// l???y danh s??ch chi ti???t c???a ph??ng ???????c ch???n
						for (ChiTietDichVu chiTietDV : ListChiTietDV) {
							dichVuTrongChiTiet = chiTietDV.getDichVu();
							dichVuTrongChiTiet.setSoLuong(chiTietDV.getSoLuong());
							dsDVDaChon.add(dichVuTrongChiTiet);
						}
						tableModel3.setRowCount(0);
						List<DichVu> listDV = dichVu_DAO.getAllDichVuCoSoLuongLonHon0();
						addRow2(listDV);
						loadTable3();
						capNhatThanhTien();
					}

				}
			}
		});

		if (pnlDV.getComponentAt(620, 0) != null) {
			pnlDV.remove(pnlDV.getComponentAt(620, 0));
		}

		pnlDV.add(scrDanhSachDichVuDuocChon);

		cmbTenDV.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filterDichVu();
				}
			}
		});

		cmbLoaiDV.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filterDichVu();
				}
			}
		});
	}

}

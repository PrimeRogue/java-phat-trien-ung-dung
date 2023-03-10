package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import components.button.Button;
import components.jDialog.JDialogCustom;
import components.notification.Notification;
import components.panelRound.PanelRound;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import dao.ChiTietDatPhong_DAO;
import dao.DonDatPhong_DAO;
import dao.KhachHang_DAO;
import dao.LoaiPhong_DAO;
import entity.ChiTietDatPhong;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.Phong;
import utils.Utils;

public class DatPhong_GUI extends JFrame implements ItemListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private DatPhong_GUI _this;
	private Button btnChonPhong;
	private Button btnDatPhong;
	private ChiTietDatPhong_DAO chiTietDatPhong_DAO;
	private JComboBox<String> cmbLoaiPhong;
	private JComboBox<String> cmbMaPhong;
	private JComboBox<String> cmbSoLuong;
	private int countItem;
	private DonDatPhong_DAO datPhong_DAO;
	private List<Phong> dsPhongDaChon;
	private List<Phong> dsPhongDatNgay;
	private final int gapY = 8;
	private final int heightItem = 36;
	private KhachHang khachHang;
	private KhachHang_DAO khachHang_DAO;
	private JLabel lblIconClose;
	private JLabel lblMaPhong;
	private LoaiPhong_DAO loaiPhong_DAO;
	private Main main;
	private PanelRound pnlContainerItem;
	private JPanel pnlContent;
	private JPanel pnlPhong;
	private JPanel pnlPhongDaChon;
	private QuanLyDatPhong_GUI quanLyDatPhongGUI;
	private JScrollPane scrPhongDaChon;
	private DefaultTableModel tableModel;
	private JTable tbl;
	private final int top = 11;
	private TextField txtSoDienThoai;
	private TextField txtTenKhachHang;

	/**
	 * Create the frame.
	 *
	 * @param quanLyDatPhongGUI
	 * @param main
	 */
	public DatPhong_GUI(QuanLyDatPhong_GUI quanLyDatPhongGUI, Main main) {
		_this = this;
		khachHang_DAO = new KhachHang_DAO();
		datPhong_DAO = new DonDatPhong_DAO();
		loaiPhong_DAO = new LoaiPhong_DAO();
		chiTietDatPhong_DAO = new ChiTietDatPhong_DAO();
		this.main = main;
		this.quanLyDatPhongGUI = quanLyDatPhongGUI;

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(0, 0, 850, 466);
		setUndecorated(true);
		setLocationRelativeTo(null);

		pnlContent = new JPanel();
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlContent.setLayout(null);
		setContentPane(pnlContent);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Color.WHITE);
		pnlContainer.setBounds(0, 0, 850, 466);
		pnlContent.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel pnlHeading = new JPanel();
		pnlHeading.setBackground(Utils.primaryColor);
		pnlHeading.setBounds(0, 0, 850, 50);
		pnlContainer.add(pnlHeading);
		pnlHeading.setLayout(null);

		JLabel lblTitle = new JLabel("?????t ph??ng");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(325, 9, 200, 32);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
		pnlHeading.add(lblTitle);

		JPanel pnlBody = new JPanel();
		pnlBody.setBackground(Color.WHITE);
		pnlBody.setBounds(40, 50, 770, 398);
		pnlContainer.add(pnlBody);
		pnlBody.setLayout(null);

		txtSoDienThoai = new TextField();
		txtSoDienThoai.setBackground(Color.WHITE);
		txtSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoDienThoai.setLabelText("S??? ??i???n tho???i kh??ch");
		txtSoDienThoai.setBounds(0, 0, 340, 55);
		pnlBody.add(txtSoDienThoai);
		txtSoDienThoai.setColumns(10);

		Button btnSearchSoDienThoai = new Button();
		btnSearchSoDienThoai.setFocusable(false);
		btnSearchSoDienThoai.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSearchSoDienThoai.setRadius(4);
		btnSearchSoDienThoai.setBorderColor(Color.WHITE);
		btnSearchSoDienThoai.setBackground(Utils.primaryColor, 0.9f, 0.8f);
		btnSearchSoDienThoai.setIcon(Utils.getImageIcon("user_searching.png"));
		btnSearchSoDienThoai.setBounds(360, 2, 50, 50);
		pnlBody.add(btnSearchSoDienThoai);

		txtTenKhachHang = new TextField();
		txtTenKhachHang.setLabelText("H??? t??n kh??ch");
		txtTenKhachHang.setEditable(false);
		txtTenKhachHang.setBackground(Color.WHITE);
		txtTenKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenKhachHang.setBounds(430, 0, 340, 55);
		pnlBody.add(txtTenKhachHang);
		txtTenKhachHang.setColumns(10);

		pnlPhong = new JPanel();
		pnlPhong.setBackground(Color.WHITE);
		pnlPhong.setBounds(0, 121, 770, 225);
		pnlBody.add(pnlPhong);
		pnlPhong.setLayout(null);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Color.WHITE);
		pnlActions.setBounds(574, 0, 36, 225);
		pnlPhong.add(pnlActions);
		pnlActions.setLayout(null);

		btnChonPhong = new Button("");
		btnChonPhong.setFocusable(false);
		btnChonPhong.setRadius(8);
		btnChonPhong.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnChonPhong.setBackground(Utils.primaryColor, 0.8f, 0.6f);
		btnChonPhong.setBorderColor(Color.WHITE);
		btnChonPhong.setIcon(Utils.getImageIcon("rightArrow_32x32.png"));
		btnChonPhong.setBounds(0, 94, 36, 36);
		btnChonPhong.setEnabled(false);
		pnlActions.add(btnChonPhong);

		JScrollPane scrDanhSachPhong = new JScrollPane();
		scrDanhSachPhong.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrDanhSachPhong.setBackground(Utils.secondaryColor);
		scrDanhSachPhong.setBounds(0, 0, 564, 225);
		scrDanhSachPhong.getViewport().setBackground(Color.WHITE);
		pnlPhong.add(scrDanhSachPhong);

		ScrollBarCustom scbDanhSachPhong = new ScrollBarCustom();
//		Set color scrollbar thumb
		scbDanhSachPhong.setScrollbarColor(new Color(203, 203, 203));
		scrDanhSachPhong.setVerticalScrollBar(scbDanhSachPhong);

		tbl = new JTable() {
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

		tableModel = new DefaultTableModel(new String[] { "M?? ph??ng", "Lo???i ph??ng", "S??? l?????ng", "Tr???ng Th??i" }, 0);
		JTableHeader tblHeader = tbl.getTableHeader();

		tbl.setModel(tableModel);
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbl.setFocusable(false);
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
		tblHeader.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setRowHeight(36);
		scrDanhSachPhong.setViewportView(tbl);
//		C??n ph???i column 3 table
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		tbl.getColumnModel().getColumn(2).setCellRenderer(dtcr);

		JPanel pnlBtnGroup = new JPanel();
		pnlBtnGroup.setBackground(Color.WHITE);
		pnlBtnGroup.setBounds(0, 362, 770, 36);
		pnlBody.add(pnlBtnGroup);
		pnlBtnGroup.setLayout(null);

		btnDatPhong = new Button("?????t ph??ng");
		btnDatPhong.setFocusable(false);
		btnDatPhong.setRadius(8);
		btnDatPhong.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDatPhong.setBorderColor(Color.WHITE);
		btnDatPhong.setForeground(Color.WHITE);
		btnDatPhong.setBackground(Utils.primaryColor, 0.8f, 0.6f);
		btnDatPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnDatPhong.setBounds(620, 0, 150, 36);
		btnDatPhong.setEnabled(false);
		pnlBtnGroup.add(btnDatPhong);

		Button btnQuayLai = new Button("Quay l???i");
		btnQuayLai.setFocusable(false);
		btnQuayLai.setRadius(8);
		btnQuayLai.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnQuayLai.setBorderColor(Color.WHITE);
		btnQuayLai.setBackground(Utils.secondaryColor, 0.8f, 0.8f);
		btnQuayLai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnQuayLai.setBounds(0, 0, 150, 36);
		pnlBtnGroup.add(btnQuayLai);

		JPanel pnlFilter = new JPanel();
		pnlFilter.setBackground(Color.WHITE);
		pnlFilter.setBounds(0, 70, 770, 36);
		pnlBody.add(pnlFilter);
		pnlFilter.setLayout(null);

		cmbMaPhong = new JComboBox<>();
		cmbMaPhong.setBackground(Utils.primaryColor);
		cmbMaPhong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbMaPhong.setModel(new DefaultComboBoxModel<>(new String[] { "M?? ph??ng" }));
		cmbMaPhong.setBounds(0, 0, 200, 36);
		pnlFilter.add(cmbMaPhong);

		cmbLoaiPhong = new JComboBox<>();
		cmbLoaiPhong.setBackground(Utils.primaryColor);
		cmbLoaiPhong.setModel(new DefaultComboBoxModel<>(new String[] { "Lo???i ph??ng", "Ph??ng th?????ng", "Ph??ng VIP" }));
		cmbLoaiPhong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbLoaiPhong.setBounds(220, 0, 200, 36);
		pnlFilter.add(cmbLoaiPhong);

		cmbSoLuong = new JComboBox<>();
		cmbSoLuong.setBackground(Utils.primaryColor);
		cmbSoLuong.setModel(new DefaultComboBoxModel<>(new String[] { "S??? l?????ng", "5", "10", "20" }));
		cmbSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbSoLuong.setBounds(440, 0, 200, 36);
		pnlFilter.add(cmbSoLuong);

		Button btnLamMoi = new Button("L??m m???i");
		btnLamMoi.setFocusable(false);
		btnLamMoi.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLamMoi.setRadius(4);
		btnLamMoi.setBorderColor(Color.WHITE);
		btnLamMoi.setBackground(Utils.secondaryColor, 0.8f, 0.8f);
		btnLamMoi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnLamMoi.setBounds(660, 0, 110, 36);
		pnlFilter.add(btnLamMoi);

		scrPhongDaChon = new JScrollPane();
		scrPhongDaChon.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhongDaChon.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrPhongDaChon.setBackground(Color.WHITE);
		scrPhongDaChon.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1, true),
				"Ph\u00F2ng \u0111\u00E3 ch\u1ECDn", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		scrPhongDaChon.setBounds(620, 0, 150, 225);
		pnlPhong.add(scrPhongDaChon);

		ScrollBarCustom scbPhongDaChon = new ScrollBarCustom();
		scbPhongDaChon.setBackgroundColor(Color.WHITE);
		scbPhongDaChon.setScrollbarColor(Utils.primaryColor);
		scrPhongDaChon.setVerticalScrollBar(scbPhongDaChon);

		pnlPhongDaChon = new JPanel();
		pnlPhongDaChon.setBackground(Color.WHITE);
		scrPhongDaChon.setViewportView(pnlPhongDaChon);
		pnlPhongDaChon.setLayout(null);

//		S??? ki???n window
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				dsPhongDatNgay = datPhong_DAO.getPhongDatNgay();
				List<LoaiPhong> loaiPhongs = loaiPhong_DAO.getAllLoaiPhong();

				cmbMaPhong.removeItemListener(_this);

				tableModel.setRowCount(0);
				emptyComboBox(cmbMaPhong, "M?? ph??ng");
				emptyComboBox(cmbLoaiPhong, "Lo???i ph??ng");

				addRow(dsPhongDatNgay);
				dsPhongDatNgay.forEach(phong -> cmbMaPhong.addItem(phong.getMaPhong()));
				loaiPhongs.forEach(loaiPhong -> cmbLoaiPhong.addItem(loaiPhong.getTenLoai()));

				String soDienThoai = txtSoDienThoai.getText().trim();

				if (Utils.isSoDienThoai(soDienThoai)) {
					khachHang = khachHang_DAO.getKhachHang(soDienThoai);

					if (khachHang != null) {
						txtTenKhachHang.setText(khachHang.getHoTen());
						txtTenKhachHang.requestFocus();

						if (dsPhongDaChon != null && dsPhongDaChon.size() > 0)
							btnDatPhong.setEnabled(true);
					}
				}

				setEventFilterComboBox(true);
				showDanhSachPhongDaChon();
				capNhatDanhSachPhongDatNgay();
			}
		});

//		S??? ki???n txtSoDienThoai
		txtSoDienThoai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				khachHang = null;
				txtTenKhachHang.setText("");
				txtSoDienThoai.setError(false);
				btnDatPhong.setEnabled(false);
			}
		});

		// S??? ki???n n??t l??m m???i
		btnLamMoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setEventFilterComboBox(false);

				tableModel.setRowCount(0);
				dsPhongDatNgay = datPhong_DAO.getPhongDatNgay();
				addRow(dsPhongDatNgay);
				cmbLoaiPhong.setSelectedIndex(0);
				cmbMaPhong.setSelectedIndex(0);
				cmbSoLuong.setSelectedIndex(0);
				capNhatDanhSachPhongDatNgay();

				setEventFilterComboBox(true);
			}
		});

//		S??? ki???n JTable
		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnChonPhong.setEnabled(true);
			}
		});

		tbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					if (tbl.getRowCount() == -1)
						btnChonPhong.setEnabled(false);
				}
			}
		});

//		S??? ki???n n??t t??m ki???m kh??ch h??ng theo s??? ??i???n tho???i
		btnSearchSoDienThoai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String soDienThoai = txtSoDienThoai.getText().trim();

				if (soDienThoai.equals(""))
					handleSoDienThoaiRong();
				else if (Utils.isSoDienThoai(soDienThoai))
					handleSoDienThoaiHopLe(soDienThoai);
				else
					handleSoDienThoaiKhongHopLe();
			}
		});

//		S??? ki???n n??t ch???n ph??ng
		btnChonPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl.getSelectedRow();
				if (row != -1 && row < tbl.getRowCount()) {
					if (dsPhongDaChon == null)
						dsPhongDaChon = new ArrayList<>();
					Phong phong = new Phong((String) tableModel.getValueAt(row, 0));
					if (dsPhongDaChon.contains(phong))
						return;
					dsPhongDaChon.add(phong);

					if (khachHang != null)
						btnDatPhong.setEnabled(true);
					capNhatDanhSachPhongDatNgay();
					showDanhSachPhongDaChon();
					repaint();
				}
			}
		});

//		S??? ki???n n??t quay l???i
		btnQuayLai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				quanLyDatPhongGUI.closeJFrameSub();
			}
		});

//		S??? ki???n n??t ?????t ph??ng
		btnDatPhong.addMouseListener(new MouseAdapter() {
			private void handleDatPhong() {
				String maDatPhong = datPhong_DAO.getMaDatPhongDangThue(khachHang.getSoDienThoai());
				Time time = Time.valueOf(LocalTime.now());
				boolean res = false;

				if (maDatPhong == null)
					res = datPhong_DAO.themPhieuDatPhongNgay(khachHang, utils.NhanVien.getNhanVien(), dsPhongDaChon);
				else
					res = chiTietDatPhong_DAO.themChiTietDatPhong(maDatPhong, dsPhongDaChon, time);

				if (res) {
					quanLyDatPhongGUI.capNhatTrangThaiPhong();
					quanLyDatPhongGUI.closeJFrameSub();
					new Notification(main, components.notification.Notification.Type.SUCCESS, "?????t ph??ng th??nh c??ng")
							.showNotification();
				} else {
					quanLyDatPhongGUI.closeJFrameSub();
					new Notification(main, components.notification.Notification.Type.ERROR, "?????t ph??ng th???t b???i")
							.showNotification();
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnDatPhong.isEnabled())
					return;

//				Ki???m tra kh??ch h??ng ???? ???????c nh???p hay ch??a?
				if (khachHang == null) {
					new Notification(_this, components.notification.Notification.Type.ERROR, "Vui l??ng nh???p kh??ch h??ng")
							.showNotification();
					return;
				}

//				Ki???m tra ph??ng ???? ???????c ch???n hay ch??a?
				if (dsPhongDaChon == null || dsPhongDaChon.size() <= 0) {
					new Notification(_this, components.notification.Notification.Type.ERROR, "Ch???n ph??ng mu???n ?????t")
							.showNotification();
					return;
				}

				List<ChiTietDatPhong> list = chiTietDatPhong_DAO.getGioVaoPhongCho(dsPhongDaChon);

				JDialogCustom jDialogCustom = new JDialogCustom(_this, components.jDialog.JDialogCustom.Type.confirm);
				jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						handleDatPhong();
					}
				});

				if (list.size() > 0) {
					String s = "";
					for (ChiTietDatPhong chiTietDatPhong : list) {
						s += String.format("Ph??ng %s ph???i tr??? tr?????c %s, ", chiTietDatPhong.getPhong().getMaPhong(),
								Utils.convertLocalTimeToString(chiTietDatPhong.getGioVao()));
					}
					s += "B???n c?? mu???n ?????t kh??ng?";
					jDialogCustom.showMessage("Question", s);
				} else
					handleDatPhong();
			}
		});
	}

	/**
	 * Th??m danh s??ch c??c ph??ng v??o table
	 *
	 * @param list danh s??ch c??c ph??ng c???n th??m
	 */
	private void addRow(List<Phong> list) {
		tableModel.setRowCount(0);

		list.forEach(phong -> addRow(phong));
	}

	/**
	 * Th??m m???t ph??ng v??o table
	 *
	 * @param phong ph??ng mu???n th??m
	 */
	private void addRow(Phong phong) {
		phong.setLoaiPhong(loaiPhong_DAO.getLoaiPhong(phong.getLoaiPhong().getMaLoai()));
		tableModel.addRow(new String[] { phong.getMaPhong(), phong.getLoaiPhong().getTenLoai(),
				phong.getSoLuongKhach() + "", Phong.convertTrangThaiToString(phong.getTrangThai()) });
	}

	private void capNhatDanhSachPhongDatNgay() {
		if (dsPhongDatNgay == null || dsPhongDaChon == null)
			return;

		tableModel.setRowCount(0);
		btnChonPhong.setEnabled(false);

		for (Phong phong : dsPhongDatNgay) {
			if (!dsPhongDaChon.contains(phong))
				addRow(phong);
		}

		if (tbl.getRowCount() > 0) {
			tbl.setRowSelectionInterval(0, 0);
			btnChonPhong.setEnabled(true);
		}
	}

	/**
	 * X??a t???t c??c c??c item trong ComboBox v?? th??m label v??o ComboBox
	 *
	 * @param jComboBox
	 */
	private void emptyComboBox(JComboBox<String> cmb) {
		cmb.removeAllItems();
	}

	/**
	 * X??a t???t c??c c??c item trong ComboBox v?? th??m label v??o ComboBox
	 *
	 * @param jComboBox ComboBox
	 * @param label
	 */
	private void emptyComboBox(JComboBox<String> cmb, String label) {
		emptyComboBox(cmb);
		cmb.addItem(label);
	}

	/**
	 * L???c danh s??ch c??c ph??ng theo m?? ph??ng, lo???i ph??ng v?? s??? l?????ng
	 */
	private void filterPhong() {
		String maPhong = (String) cmbMaPhong.getSelectedItem();
		String loaiPhong = (String) cmbLoaiPhong.getSelectedItem();
		String soLuong = (String) cmbSoLuong.getSelectedItem();

		if (maPhong.equals("M?? ph??ng"))
			maPhong = "";
		if (loaiPhong.equals("Lo???i ph??ng"))
			loaiPhong = "";

		dsPhongDatNgay = datPhong_DAO.getPhongDatNgay(maPhong, loaiPhong, soLuong);
		tableModel.setRowCount(0);
		addRow(dsPhongDatNgay);
		capNhatDanhSachPhongDatNgay();
	}

	/**
	 * Get panel ph??ng ???? ch???n
	 *
	 * @param top   kho???ng c??ch top t??? container ?????n item
	 * @param phong ph??ng ???????c ch???n
	 * @return panel
	 */
	private PanelRound getPanelPhongDaChonItem(int top, Phong phong) {
		pnlContainerItem = new PanelRound(8);
		pnlContainerItem.setBackground(Utils.primaryColor);
		pnlContainerItem.setBounds(11, top, 118, 36);
		pnlContainerItem.setLayout(null);

		lblMaPhong = new JLabel(phong.getMaPhong());
		lblMaPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMaPhong.setBounds(4, 0, 94, 36);
		pnlContainerItem.add(lblMaPhong);

		lblIconClose = new JLabel("");
		lblIconClose.setIcon(Utils.getImageIcon("close_16x16.png"));
		lblIconClose.setBounds(94, 10, 16, 16);
		pnlContainerItem.add(lblIconClose);
		lblIconClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dsPhongDaChon.remove(phong);
				if (dsPhongDaChon.size() <= 0)
					btnDatPhong.setEnabled(false);
				capNhatDanhSachPhongDatNgay();
				showDanhSachPhongDaChon();
			}
		});

		return pnlContainerItem;
	}

	private void handleSoDienThoaiHopLe(String soDienThoai) {
		khachHang = khachHang_DAO.getKhachHang(soDienThoai);

		if (khachHang != null) {
			txtTenKhachHang.setText(khachHang.getHoTen());
			if (dsPhongDaChon != null && dsPhongDaChon.size() > 0)
				btnDatPhong.setEnabled(true);
		} else {
			JDialogCustom jDialogCustom = new JDialogCustom(_this);

			jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ThemKhachHang_GUI themKhachHangGUI = new ThemKhachHang_GUI(main, _this, soDienThoai);
					quanLyDatPhongGUI.getGlass().setVisible(false);
					main.addPnlBody(themKhachHangGUI, "Th??m kh??ch h??ng", 2, 0);
					setVisible(false);
				}
			});

			jDialogCustom.showMessage("Warning",
					"Kh??ch h??ng kh??ng c?? trong h??? th???ng, b???n c?? mu???n th??m kh??ch h??ng m???i kh??ng?");
		}
	}

	private void handleSoDienThoaiKhongHopLe() {
		new Notification(_this, components.notification.Notification.Type.ERROR,
				"S??? ??i???n tho???i ph???i c?? d???ng 0XXXXXXXXX").showNotification();
		txtSoDienThoai.setError(true);
		txtSoDienThoai.selectAll();
		txtSoDienThoai.requestFocus();
	}

	private void handleSoDienThoaiRong() {
		new Notification(_this, components.notification.Notification.Type.ERROR, "Vui l??ng nh???p s??? ??i???n tho???i kh??ch")
				.showNotification();
		txtSoDienThoai.setError(true);
		txtSoDienThoai.requestFocus();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.DESELECTED)
			return;

		filterPhong();
	}

	private void setEventFilterComboBox(boolean b) {
		if (b) {
			cmbLoaiPhong.addItemListener(_this);
			cmbMaPhong.addItemListener(_this);
			cmbSoLuong.addItemListener(_this);
			return;
		}
		cmbLoaiPhong.removeItemListener(_this);
		cmbMaPhong.removeItemListener(_this);
		cmbSoLuong.removeItemListener(_this);
	}

	/**
	 * Hi???n th??? c??c ph??ng ???? ch???n v??o m???c ph??ng ???? ch???n
	 */
	private void showDanhSachPhongDaChon() {
		if (pnlPhongDaChon != null)
			pnlPhongDaChon.removeAll();
		if (dsPhongDaChon == null)
			return;
		scrPhongDaChon.setViewportView(pnlPhongDaChon);

		countItem = dsPhongDaChon.size();
		Phong phong;
		PanelRound pnlPhongDaChonItem;
		for (int i = 0; i < countItem; ++i) {
			phong = dsPhongDaChon.get(i);
			pnlPhongDaChonItem = getPanelPhongDaChonItem(top + i * (gapY + heightItem), phong);
			pnlPhongDaChon.add(pnlPhongDaChonItem);
		}

		pnlPhongDaChon.setPreferredSize(
				new Dimension(140, Math.max(202, top + heightItem * countItem + gapY * (countItem - 1))));
		repaint();
	}

}

package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.raven.datechooser.DateChooser;
import com.raven.datechooser.EventDateChooser;
import com.raven.datechooser.SelectedAction;
import com.raven.datechooser.SelectedDate;

import components.button.Button;
import components.comboBox.ComboBox;
import components.jDialog.JDialogCustom;
import components.notification.Notification;
import components.panelRound.PanelRound;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import dao.DonDatPhong_DAO;
import dao.KhachHang_DAO;
import dao.LoaiPhong_DAO;
import entity.KhachHang;
import entity.Phong;
import utils.Utils;

public class DatPhongTruoc_GUI extends JFrame implements ItemListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private DatPhongTruoc_GUI _this;
	private Button btnChonPhong;
	private Button btnDatPhong;
	private Button btnLamMoi;
	private JComboBox<String> cmbGio;
	private ComboBox<String> cmbLoaiPhong;
	private ComboBox<String> cmbMaPhong;
	private JComboBox<String> cmbPhut;
	private ComboBox<String> cmbSoLuong;
	private int countItem;
	private DateChooser dateChoose;
	private DonDatPhong_DAO datPhong_DAO;
	private List<Phong> dsPhongDaChon;
	private List<Phong> dsPhongDatTruoc;
	private final int gapY = 8;
	private final int gioDongCua = 24;
	private final int gioMoCua = 7;
	private final int heightItem = 36;
	private KhachHang khachHang;
	private KhachHang_DAO khachHang_DAO;
	private final String labelCmbMaPhong = "M?? ph??ng";
	private JLabel lblIconClose;
	private JLabel lblMaPhong;
	private LoaiPhong_DAO loaiPhong_DAO;
	private PanelRound pnlContainerItem;
	private JPanel pnlContent;
	private JPanel pnlPhong;
	private JPanel pnlPhongDaChon;
	private JScrollPane scrPhongDaChon;
	private DefaultTableModel tableModel;
	private JTable tbl;
	private final int top = 11;
	private TextField txtNgayNhanPhong;
	private TextField txtSoDienThoai;
	private TextField txtTenKhachHang;

	/**
	 * Create the frame.
	 *
	 * @param quanLyDatPhongGUI
	 * @param glass
	 */
	public DatPhongTruoc_GUI(QuanLyDatPhong_GUI quanLyDatPhongGUI, Main main) {
		_this = this;
		datPhong_DAO = new DonDatPhong_DAO();
		loaiPhong_DAO = new LoaiPhong_DAO();
		khachHang_DAO = new KhachHang_DAO();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(0, 0, 850, 536);
		pnlContent = new JPanel();
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlContent);
		pnlContent.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Color.WHITE);
		pnlContainer.setBounds(0, 0, 850, 536);
		pnlContent.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel pnlHeading = new JPanel();
		pnlHeading.setBackground(Utils.primaryColor);
		pnlHeading.setBounds(0, 0, 850, 50);
		pnlContainer.add(pnlHeading);
		pnlHeading.setLayout(null);

		JLabel lblTitle = new JLabel("?????t ph??ng tr?????c");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(325, 9, 200, 32);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
		pnlHeading.add(lblTitle);

		JPanel pnlBody = new JPanel();
		pnlBody.setBackground(Color.WHITE);
		pnlBody.setBounds(0, 50, 850, 486);
		pnlContainer.add(pnlBody);
		pnlBody.setLayout(null);

		txtSoDienThoai = new TextField();
		txtSoDienThoai.setBackground(Color.WHITE);
		txtSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoDienThoai.setLabelText("S??? ??i???n tho???i kh??ch");
		txtSoDienThoai.setBounds(40, 0, 340, 55);
		pnlBody.add(txtSoDienThoai);
		txtSoDienThoai.setColumns(10);

		Button btnSearchSoDienThoai = new Button();
		btnSearchSoDienThoai.setFocusable(false);
		btnSearchSoDienThoai.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSearchSoDienThoai.setRadius(4);
		btnSearchSoDienThoai.setBorderColor(Color.WHITE);
		btnSearchSoDienThoai.setColor(Utils.primaryColor);
		btnSearchSoDienThoai.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.9f));
		btnSearchSoDienThoai.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnSearchSoDienThoai.setIcon(Utils.getImageIcon("user_searching.png"));
		btnSearchSoDienThoai.setBounds(400, 2, 50, 50);
		pnlBody.add(btnSearchSoDienThoai);

		txtTenKhachHang = new TextField();
		txtTenKhachHang.setLabelText("H??? t??n kh??ch");
		txtTenKhachHang.setEditable(false);
		txtTenKhachHang.setBackground(Color.WHITE);
		txtTenKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenKhachHang.setBounds(470, 0, 340, 55);
		pnlBody.add(txtTenKhachHang);
		txtTenKhachHang.setColumns(10);

		pnlPhong = new JPanel();
		pnlPhong.setBackground(Color.WHITE);
		pnlPhong.setBounds(40, 187, 770, 225);
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
		btnChonPhong.setColor(Utils.primaryColor);
		btnChonPhong.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnChonPhong.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.6f));
		btnChonPhong.setBorderColor(Color.WHITE);
		btnChonPhong.setIcon(Utils.getImageIcon("rightArrow_32x32.png"));
		btnChonPhong.setBounds(0, 94, 36, 36);
		btnChonPhong.setEnabled(false);
		pnlActions.add(btnChonPhong);

		JScrollPane scrDanhSachPhong = new JScrollPane();
		scrDanhSachPhong.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrDanhSachPhong.setBackground(Utils.secondaryColor);
		scrDanhSachPhong.setBounds(0, 0, 564, 225);
		pnlPhong.add(scrDanhSachPhong);

		ScrollBarCustom scbDanhSachPhong = new ScrollBarCustom();
//		Set color scrollbar thumb
		scbDanhSachPhong.setScrollbarColor(new Color(203, 203, 203));
		scrDanhSachPhong.setVerticalScrollBar(scbDanhSachPhong);
		scrDanhSachPhong.getViewport().setBackground(Color.WHITE);

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
//		Set background cho ph???n header c???a table
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
//		Set chi???u r???ng v?? chi???u cao cho ph???n header c???a table
		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
		tblHeader.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setRowHeight(36);
		scrDanhSachPhong.setViewportView(tbl);
//		C??n ph???i cell 3 table
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		tbl.getColumnModel().getColumn(2).setCellRenderer(dtcr);

		JPanel pnlBtnGroup = new JPanel();
		pnlBtnGroup.setBackground(Color.WHITE);
		pnlBtnGroup.setBounds(40, 432, 770, 36);
		pnlBody.add(pnlBtnGroup);
		pnlBtnGroup.setLayout(null);

		btnDatPhong = new Button("?????t ph??ng tr?????c");
		btnDatPhong.setFocusable(false);
		btnDatPhong.setRadius(8);
		btnDatPhong.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDatPhong.setBorderColor(Color.WHITE);
		btnDatPhong.setForeground(Color.WHITE);
		btnDatPhong.setColor(Utils.primaryColor);
		btnDatPhong.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnDatPhong.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.6f));
		btnDatPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnDatPhong.setBounds(620, 0, 150, 36);
		btnDatPhong.setEnabled(false);
		pnlBtnGroup.add(btnDatPhong);

		Button btnQuayLai = new Button("Quay l???i");
		btnQuayLai.setBackground(Color.WHITE);
		btnQuayLai.setFocusable(false);
		btnQuayLai.setRadius(8);
		btnQuayLai.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnQuayLai.setBorderColor(Color.WHITE);
		btnQuayLai.setColor(Utils.secondaryColor);
		btnQuayLai.setColorOver(Utils.getOpacity(Utils.secondaryColor, 0.8f));
		btnQuayLai.setColorClick(Utils.getOpacity(Utils.secondaryColor, 0.8f));
		btnQuayLai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnQuayLai.setBounds(0, 0, 150, 36);
		pnlBtnGroup.add(btnQuayLai);

		JPanel pnlFilter = new JPanel();
		pnlFilter.setBackground(Color.WHITE);
		pnlFilter.setBounds(40, 140, 770, 36);
		pnlBody.add(pnlFilter);
		pnlFilter.setLayout(null);

		cmbMaPhong = new ComboBox<>();
		cmbMaPhong.setBackground(Utils.primaryColor);
		cmbMaPhong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbMaPhong.setModel(new DefaultComboBoxModel<>(new String[] { labelCmbMaPhong }));
		cmbMaPhong.setBounds(0, 0, 200, 36);
		pnlFilter.add(cmbMaPhong);

		cmbLoaiPhong = new ComboBox<>();
		cmbLoaiPhong.setBackground(Utils.primaryColor);
		cmbLoaiPhong.setModel(new DefaultComboBoxModel<>(new String[] { "Lo???i ph??ng", "Ph??ng th?????ng", "Ph??ng VIP" }));
		cmbLoaiPhong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbLoaiPhong.setBounds(220, 0, 200, 36);
		pnlFilter.add(cmbLoaiPhong);

		cmbSoLuong = new ComboBox<>();
		cmbSoLuong.setBackground(Utils.primaryColor);
		cmbSoLuong.setModel(new DefaultComboBoxModel<>(new String[] { "S??? l?????ng", "5", "10", "20" }));
		cmbSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbSoLuong.setBounds(440, 0, 200, 36);
		pnlFilter.add(cmbSoLuong);

		btnLamMoi = new Button("L??m m???i");
		btnLamMoi.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLamMoi.setRadius(4);
		btnLamMoi.setBorderColor(Color.WHITE);
		btnLamMoi.setColor(Utils.secondaryColor);
		btnLamMoi.setColorOver(Utils.getOpacity(Utils.secondaryColor, 0.8f));
		btnLamMoi.setColorClick(Utils.getOpacity(Utils.secondaryColor, 0.8f));
		btnLamMoi.setFocusable(false);
		btnLamMoi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnLamMoi.setBounds(660, 0, 110, 36);
		btnLamMoi.setEnabled(false);
		pnlFilter.add(btnLamMoi);

		txtNgayNhanPhong = new TextField();
		txtNgayNhanPhong.setIcon(Utils.getImageIcon("add-event 2.png"));
		txtNgayNhanPhong.setLabelText("Ng??y nh???n ph??ng");
		txtNgayNhanPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgayNhanPhong.setColumns(10);
		txtNgayNhanPhong.setBackground(Color.WHITE);
		txtNgayNhanPhong.setBounds(40, 70, 340, 55);
		pnlBody.add(txtNgayNhanPhong);
		dateChoose = new DateChooser();
		dateChoose.setDateFormat("dd/MM/yyyy");
		dateChoose.setTextRefernce(txtNgayNhanPhong);

		Button btnSearchPhongDatTruoc = new Button();
		btnSearchPhongDatTruoc.setIcon(Utils.getImageIcon("statistics.png"));
		btnSearchPhongDatTruoc.setRadius(4);
		btnSearchPhongDatTruoc.setFocusable(false);
		btnSearchPhongDatTruoc.setColorOver(new Color(140, 177, 180, 230));
		btnSearchPhongDatTruoc.setColorClick(new Color(140, 177, 180, 204));
		btnSearchPhongDatTruoc.setColor(new Color(140, 177, 180));
		btnSearchPhongDatTruoc.setBorderColor(Color.WHITE);
		btnSearchPhongDatTruoc.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSearchPhongDatTruoc.setBounds(675, 72, 50, 50);
		pnlBody.add(btnSearchPhongDatTruoc);

		cmbGio = new ComboBox<>();
		cmbGio.setModel(new DefaultComboBoxModel<String>());
		cmbGio.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbGio.setBackground(new Color(140, 177, 180));
		cmbGio.setBounds(400, 89, 70, 36);
		pnlBody.add(cmbGio);

		cmbPhut = new ComboBox<>();
		cmbPhut.setModel(new DefaultComboBoxModel<String>());
		cmbPhut.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbPhut.setBackground(new Color(140, 177, 180));
		cmbPhut.setBounds(537, 89, 70, 36);
		pnlBody.add(cmbPhut);

		JLabel lblTime = new JLabel("Gi??? nh???n ph??ng");
		lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTime.setBounds(400, 66, 200, 19);
		pnlBody.add(lblTime);

		JLabel lblGio = new JLabel("gi???");
		lblGio.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblGio.setBounds(480, 95, 34, 30);
		pnlBody.add(lblGio);

		JLabel lblPhut = new JLabel("ph??t");
		lblPhut.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblPhut.setBounds(612, 95, 45, 30);
		pnlBody.add(lblPhut);

		setEnabledFilterComboBox(false);

		showDanhSachPhongDaChon();
		setEventFilterComboBox(true);
		cmbPhut.addItemListener(_this);

		scrPhongDaChon = new JScrollPane();
		scrPhongDaChon.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrPhongDaChon.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrPhongDaChon.setBackground(Color.WHITE);
		scrPhongDaChon.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true),
				"Ph\u00F2ng \u0111\u00E3 ch\u1ECDn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				String soDienThoai = txtSoDienThoai.getText().trim();

				if (Utils.isSoDienThoai(soDienThoai)) {
					khachHang = khachHang_DAO.getKhachHang(soDienThoai);

					if (khachHang != null) {
						txtTenKhachHang.setText(khachHang.getHoTen());
						txtTenKhachHang.requestFocus();
					}
				}

				setEventFilterComboBox(true);
				setTimeComboBox(LocalTime.now().getHour());
			}
		});

//		S??? ki???n txtSoDienThoai
		txtSoDienThoai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtSoDienThoai.setError(false);
				txtTenKhachHang.setText("");
				khachHang = null;
				btnDatPhong.setEnabled(false);
			}
		});

//		S??? ki???n n??t t??m ki???m kh??ch h??ng theo s??? ??i???n tho???i
		btnSearchSoDienThoai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String soDienThoai = txtSoDienThoai.getText().trim();

				if (soDienThoai.equals("")) {
					new Notification(_this, components.notification.Notification.Type.ERROR,
							"Vui l??ng nh???p s??? ??i???n tho???i kh??ch").showNotification();
					txtSoDienThoai.setError(true);
					txtSoDienThoai.requestFocus();
					return;
				}

				if (Utils.isSoDienThoai(soDienThoai)) {
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
								ThemKhachHang_GUI themKhachHang_GUI = new ThemKhachHang_GUI(main, _this, soDienThoai);
								quanLyDatPhongGUI.getGlass().setVisible(false);
								main.addPnlBody(themKhachHang_GUI, "Th??m kh??ch h??ng", 2, 0);
								setVisible(false);
							}
						});

						jDialogCustom.showMessage("Warning",
								"Kh??ch h??ng kh??ng c?? trong h??? th???ng, b???n c?? mu???n th??m kh??ch h??ng m???i kh??ng?");
					}
				} else {
					new Notification(_this, components.notification.Notification.Type.ERROR,
							"S??? ??i???n tho???i ph???i c?? d???ng 0XXXXXXXXX").showNotification();
					txtSoDienThoai.setError(true);
					txtSoDienThoai.selectAll();
					txtSoDienThoai.requestFocus();
				}
			}
		});

//		S??? ki???n txtNgayDat
		dateChoose.addEventDateChooser(new EventDateChooser() {

			@Override
			public void dateSelected(SelectedAction arg0, SelectedDate arg1) {
				setTimeComboBox(-1);
				handleChangeDateTime();
			}
		});

//		S??? ki???n t??m ki???m ph??ng ?????t tr?????c
		btnSearchPhongDatTruoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dsPhongDaChon = null;
				showDanhSachPhongDaChon();
				LocalDate ngayNhanPhong = Utils.getLocalDate(txtNgayNhanPhong.getText());
				LocalDate dateNow = LocalDate.now();
				cmbMaPhong.removeItemListener(_this);
				cmbMaPhong.removeAllItems();
				cmbMaPhong.addItem(labelCmbMaPhong);
				if (ngayNhanPhong.isBefore(dateNow)) {
					JDialogCustom jDialogCustom = new JDialogCustom(_this,
							components.jDialog.JDialogCustom.Type.warning);
					jDialogCustom.showMessage("L???i", "Ng??y nh???n ph??ng kh??ng ???????c tr?????c ng??y hi???n t???i.");
					dateChoose.showPopup();
					return;
				}

				int gio = Integer.parseInt((String) cmbGio.getSelectedItem()),
						phut = Integer.parseInt((String) cmbPhut.getSelectedItem());
				LocalTime gioNhanPhong = LocalTime.of(gio, phut);

				dsPhongDatTruoc = datPhong_DAO.getPhongDatTruoc(ngayNhanPhong, gioNhanPhong);

				if (dsPhongDatTruoc.size() > 0) {
					setEnabledFilterComboBox(true);

					dsPhongDatTruoc.forEach(phong -> cmbMaPhong.addItem(phong.getMaPhong()));
					addRow(dsPhongDatTruoc);
					Utils.scrollToVisiable(tbl, 0, 0);
					btnLamMoi.setEnabled(true);
					cmbMaPhong.addItemListener(_this);
				} else
					new Notification(_this, components.notification.Notification.Type.WARNING,
							"Kh??ng c?? ph??ng n??o tr???ng trong kho???n th???i gian n??y.").showNotification();
			}
		});

//		S??? ki???n n??t l??m m???i
		btnLamMoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnLamMoi.isEnabled())
					return;

				int gio = Integer.parseInt((String) cmbGio.getSelectedItem()),
						phut = Integer.parseInt((String) cmbPhut.getSelectedItem());
				LocalTime gioNhanPhong = LocalTime.of(gio, phut);
				LocalDate ngayNhanPhong = Utils.getLocalDate(txtNgayNhanPhong.getText());

				setEventFilterComboBox(false);

				btnChonPhong.setEnabled(false);
				Utils.emptyTable(tbl);
				addRow(datPhong_DAO.getPhongDatTruoc(ngayNhanPhong, gioNhanPhong));
				cmbLoaiPhong.setSelectedIndex(0);
				cmbMaPhong.setSelectedIndex(0);
				cmbSoLuong.setSelectedIndex(0);
				capNhatDanhSachPhongDatTruoc();

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
					capNhatDanhSachPhongDatTruoc();
					showDanhSachPhongDaChon();
					if (khachHang != null)
						btnDatPhong.setEnabled(true);
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
				if (dsPhongDaChon.size() <= 0) {
					new Notification(_this, components.notification.Notification.Type.ERROR, "Ch???n ph??ng mu???n ?????t")
							.showNotification();
					return;
				}

				LocalDate ngayNhanPhong = Utils.getLocalDate(txtNgayNhanPhong.getText());
				int gio = Integer.parseInt((String) cmbGio.getSelectedItem()),
						phut = Integer.parseInt((String) cmbPhut.getSelectedItem());
				LocalTime gioNhanPhong = LocalTime.of(gio, phut);
				boolean res = datPhong_DAO.themPhieuDatPhongTruoc(khachHang, utils.NhanVien.getNhanVien(),
						dsPhongDaChon, ngayNhanPhong, gioNhanPhong);
				if (res) {
					quanLyDatPhongGUI.capNhatTrangThaiPhong();
					quanLyDatPhongGUI.closeJFrameSub();
					new Notification(main, components.notification.Notification.Type.SUCCESS, "?????t ph??ng th??nh c??ng")
							.showNotification();
				}
			}
		});

//		S??? ki???n Component ???????c hi???n th???
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				dateChoose.showPopup();
				dateChoose.hidePopup();
			}
		});
	}

	private void addRow(List<Phong> list) {
		btnChonPhong.setEnabled(false);
		Utils.emptyTable(tbl);

		list.forEach(phong -> addRow(phong));
	}

	private void addRow(Phong phong) {
		phong.setLoaiPhong(loaiPhong_DAO.getLoaiPhong(phong.getLoaiPhong().getMaLoai()));
		tableModel.addRow(new String[] { phong.getMaPhong(), phong.getLoaiPhong().getTenLoai(),
				phong.getSoLuongKhach() + "", Phong.convertTrangThaiToString(phong.getTrangThai()) });
	}

	private void capNhatDanhSachPhongDatTruoc() {
		if (dsPhongDatTruoc == null || dsPhongDaChon == null)
			return;

		btnChonPhong.setEnabled(false);
		Utils.emptyTable(tbl);
		btnChonPhong.setEnabled(false);

		for (Phong phong : dsPhongDatTruoc) {
			if (!dsPhongDaChon.contains(phong))
				addRow(phong);
		}

		if (tbl.getRowCount() > 0) {
			tbl.setRowSelectionInterval(0, 0);
			btnChonPhong.setEnabled(true);
		}
	}

	private void filterPhongDatTruoc() {
		if (!cmbGio.isEnabled())
			return;
		String maPhong = (String) cmbMaPhong.getSelectedItem();
		String loaiPhong = (String) cmbLoaiPhong.getSelectedItem();
		String soLuong = (String) cmbSoLuong.getSelectedItem();

		if (maPhong.equals("M?? ph??ng"))
			maPhong = "";
		if (loaiPhong.equals("Lo???i ph??ng"))
			loaiPhong = "";

		int gio = Integer.parseInt((String) cmbGio.getSelectedItem()),
				phut = Integer.parseInt((String) cmbPhut.getSelectedItem());
		LocalTime gioNhanPhong = LocalTime.of(gio, phut);
		LocalDate ngayNhanPhong = Utils.getLocalDate(txtNgayNhanPhong.getText());
		dsPhongDatTruoc = datPhong_DAO.getPhongDatTruoc(ngayNhanPhong, gioNhanPhong, maPhong, loaiPhong, soLuong);
		btnChonPhong.setEnabled(false);
		Utils.emptyTable(tbl);
		addRow(dsPhongDatTruoc);
		capNhatDanhSachPhongDatTruoc();
		Utils.scrollToVisiable(tbl, 0, 0);
		repaint();
	}

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
				capNhatDanhSachPhongDatTruoc();
				showDanhSachPhongDaChon();
				if (dsPhongDaChon.size() <= 0)
					btnDatPhong.setEnabled(false);
			}
		});

		return pnlContainerItem;
	}

	private void handleChangeDateTime() {
		btnLamMoi.setEnabled(false);
		dsPhongDaChon = null;
		dsPhongDatTruoc = null;
		btnChonPhong.setEnabled(false);
		Utils.emptyTable(tbl);
		showDanhSachPhongDaChon();
		repaint();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object o = e.getSource();

		if (e.getStateChange() == ItemEvent.DESELECTED)
			return;
		if (o.equals(cmbGio)) {
			setTimeComboBox(Integer.parseInt((String) cmbGio.getSelectedItem()));
			handleChangeDateTime();
		} else if (o.equals(cmbPhut))
			handleChangeDateTime();
		else
			filterPhongDatTruoc();
	}

	private void setEnabledFilterComboBox(boolean b) {
		cmbMaPhong.setEnabled(b);
		cmbLoaiPhong.setEnabled(b);
		cmbSoLuong.setEnabled(b);
	}

	private void setEnabledTimeComboBox(boolean b) {
		cmbGio.setEnabled(b);
		cmbPhut.setEnabled(b);
	}

	/**
	 * Set s??? ki???n cho c??c JComboBox
	 *
	 * @param b true add event, false remove event
	 */
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

	private void setTimeComboBox(int gioSelect) {
		cmbGio.removeItemListener(_this);

		cmbGio.removeAllItems();
		cmbPhut.removeAllItems();

		LocalTime timeNow = LocalTime.now();
		LocalDate dateSelect = Utils.getLocalDate(txtNgayNhanPhong.getText());
		int gio = timeNow.getHour(), phut = timeNow.getMinute();

		if (dateSelect.isBefore(LocalDate.now())) {
			setEnabledTimeComboBox(false);
			return;
		}
		setEnabledTimeComboBox(true);

		if (dateSelect.isEqual(LocalDate.now())) {
			for (int i = gio; i < gioDongCua; ++i)
				cmbGio.addItem(i + "");
			if (gio == gioSelect || gioSelect == -1) {
				if (phut > 30) {
					cmbGio.removeItemAt(0);
					for (int j = 0; j < 60; j += 5)
						cmbPhut.addItem(j + "");
				} else {
					phut += (5 - phut % 5);
					for (int i = phut; i < 60; i += 5)
						cmbPhut.addItem(i + "");
				}
			} else
				for (int j = 0; j < 60; j += 5)
					cmbPhut.addItem(j + "");
		} else {
			for (int i = gioMoCua; i < gioDongCua; ++i)
				cmbGio.addItem(i + "");
			for (int j = 0; j < 60; j += 5)
				cmbPhut.addItem(j + "");
		}

		if (gioSelect == 23) {
			cmbPhut.removeAllItems();
			cmbPhut.addItem("0");
		}

		cmbGio.setSelectedItem(gioSelect + "");
		cmbGio.addItemListener(_this);
	}

	private void showDanhSachPhongDaChon() {
		if (dsPhongDaChon == null)
			return;
		pnlPhongDaChon.removeAll();
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

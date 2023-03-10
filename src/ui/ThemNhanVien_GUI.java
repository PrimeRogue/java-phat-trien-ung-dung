package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.raven.datechooser.DateChooser;

import components.button.Button;
import components.comboBox.ComboBox;
import components.notification.Notification;
import components.notification.Notification.Type;
import components.radio.RadioButtonCustom;
import components.textField.TextField;
import dao.DiaChi_DAO;
import dao.NhanVien_DAO;
import entity.NhanVien;
import entity.NhanVien.ChucVu;
import entity.NhanVien.TrangThai;
import entity.Phuong;
import entity.Quan;
import entity.Tinh;
import utils.Utils;

public class ThemNhanVien_GUI extends JPanel implements ItemListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ButtonGroup buttonGroupGioiTinh;
	private JComboBox<String> cmbChucVu;
	private JComboBox<String> cmbPhuong;
	private JComboBox<String> cmbQuan;
	private JComboBox<String> cmbTinh;
	private JComboBox<String> cmbTrangThai;
	private DateChooser dateChoose;
	private DiaChi_DAO diaChi_DAO;
	private boolean isEnabledEventPhuong = false;
	private boolean isEnabledEventQuan = false;
	private boolean isEnabledEventTinh = false;
	private Main main;
	private NhanVien_DAO nhanVien_DAO;
	private Phuong phuong;
	private Quan quan;
	private RadioButtonCustom radNam;
	private RadioButtonCustom radNu;
	private Tinh tinh;
	private TextField txtCCCD;
	private TextField txtDiaChiCT;
	private TextField txtHoTen;
	private TextField txtLuong;
	private TextField txtMaNhanVien;
	private TextField txtMatKhau;
	private TextField txtNgaySinh;
	private TextField txtSoDienThoai;
	private final int widthPnlContainer = 948;

	/**
	 * Create the frame.
	 *
	 * @wbp.parser.constructor
	 */
	public ThemNhanVien_GUI(Main jFrame) {
		nhanVien_DAO = new NhanVien_DAO();
		diaChi_DAO = new DiaChi_DAO();
		main = jFrame;
		int padding = (int) Math.floor((Utils.getBodyHeight() - 428) * 1.0 / 8);
		int top = padding;

		setBackground(Utils.secondaryColor);
		setBounds(0, 0, Utils.getScreenWidth(), Utils.getBodyHeight());
		setLayout(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(Utils.getLeft(widthPnlContainer), 0, widthPnlContainer, Utils.getBodyHeight());
		this.add(pnlContainer);
		pnlContainer.setLayout(null);

		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(0, top, 948, 55);
		top += 55 + padding;
		pnlContainer.add(pnlRow1);
		pnlRow1.setLayout(null);

		txtMaNhanVien = new TextField();
		txtMaNhanVien.setLineColor(Utils.lineTextField);
		txtMaNhanVien.setBackground(Utils.secondaryColor);
		txtMaNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMaNhanVien.setLabelText("M?? nh??n vi??n");
		txtMaNhanVien.setBounds(0, 0, 449, 55);
		pnlRow1.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);

		txtHoTen = new TextField();
		txtHoTen.setLineColor(Utils.lineTextField);
		txtHoTen.setLabelText("H??? t??n");
		txtHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtHoTen.setColumns(10);
		txtHoTen.setBackground(new Color(203, 239, 255));
		txtHoTen.setBounds(499, 0, 449, 55);
		pnlRow1.add(txtHoTen);

		JPanel pnlRow2 = new JPanel();
		pnlRow2.setLayout(null);
		pnlRow2.setBackground(new Color(203, 239, 255));
		pnlRow2.setBounds(0, top, 948, 55);
		top += 55 + padding;
		pnlContainer.add(pnlRow2);

		txtCCCD = new TextField();
		txtCCCD.setLineColor(new Color(149, 166, 248));
		txtCCCD.setLabelText("C??n c?????c c??ng d??n");
		txtCCCD.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtCCCD.setColumns(10);
		txtCCCD.setBackground(new Color(203, 239, 255));
		txtCCCD.setBounds(0, 0, 449, 55);
		pnlRow2.add(txtCCCD);

		txtSoDienThoai = new TextField();
		txtSoDienThoai.setLineColor(new Color(149, 166, 248));
		txtSoDienThoai.setLabelText("S??? ??i???n tho???i");
		txtSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBackground(new Color(203, 239, 255));
		txtSoDienThoai.setBounds(499, 0, 449, 55);
		pnlRow2.add(txtSoDienThoai);

		JPanel pnlRow3 = new JPanel();
		pnlRow3.setLayout(null);
		pnlRow3.setBackground(new Color(203, 239, 255));
		pnlRow3.setBounds(0, top, 948, 55);
		top += 55 + padding;
		pnlContainer.add(pnlRow3);

		txtNgaySinh = new TextField();
		txtNgaySinh.setIcon(Utils.getImageIcon("add-event 2.png"));
		txtNgaySinh.setLineColor(new Color(149, 166, 248));
		txtNgaySinh.setLabelText("Ng??y sinh");
		txtNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBackground(new Color(203, 239, 255));
		txtNgaySinh.setBounds(0, 0, 449, 55);
		pnlRow3.add(txtNgaySinh);
		dateChoose = new DateChooser();
		dateChoose.setDateFormat("dd/MM/yyyy");
		dateChoose.setTextRefernce(txtNgaySinh);

		JPanel pnlGioiTinh = new JPanel();
		pnlGioiTinh.setBackground(Utils.secondaryColor);
		pnlGioiTinh.setBounds(499, 0, 550, 55);
		pnlRow3.add(pnlGioiTinh);
		pnlGioiTinh.setLayout(null);

		JLabel lblGioiTinh = new JLabel("Gi???i t??nh");
		lblGioiTinh.setForeground(Utils.labelTextField);
		lblGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblGioiTinh.setBounds(4, 6, 260, 19);
		pnlGioiTinh.add(lblGioiTinh);

		JPanel pnlGroupGioiTinh = new JPanel();
		pnlGroupGioiTinh.setBackground(Utils.secondaryColor);
		pnlGroupGioiTinh.setBounds(4, 39, 138, 16);
		pnlGioiTinh.add(pnlGroupGioiTinh);
		pnlGroupGioiTinh.setLayout(null);

		radNam = new RadioButtonCustom("Nam");
		radNam.setFocusable(false);
		radNam.setBackground(Utils.secondaryColor);
		radNam.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		radNam.setBounds(0, -2, 59, 21);
		pnlGroupGioiTinh.add(radNam);

		radNu = new RadioButtonCustom("N???");
		radNu.setFocusable(false);
		radNu.setBackground(Utils.secondaryColor);
		radNu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		radNu.setBounds(79, -2, 59, 21);
		pnlGroupGioiTinh.add(radNu);

		buttonGroupGioiTinh = new ButtonGroup();
		buttonGroupGioiTinh.add(radNam);
		buttonGroupGioiTinh.add(radNu);

		JPanel pnlRow4 = new JPanel();
		pnlRow4.setBackground(Utils.secondaryColor);
		pnlRow4.setBounds(0, top, 948, 65);
		top += 65 + padding;
		pnlContainer.add(pnlRow4);
		pnlRow4.setLayout(null);

		JLabel lblDiaChi = new JLabel("?????a ch???");
		lblDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDiaChi.setBounds(4, 6, 50, 19);
		lblDiaChi.setForeground(Utils.labelTextField);
		pnlRow4.add(lblDiaChi);

		cmbTinh = new ComboBox<>();
		cmbTinh.setModel(new DefaultComboBoxModel<String>());
		cmbTinh.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbTinh.setBackground(Utils.primaryColor);
		cmbTinh.setBounds(4, 29, 200, 36);
		pnlRow4.add(cmbTinh);

		cmbQuan = new ComboBox<>();
		cmbQuan.setModel(new DefaultComboBoxModel<String>());
		cmbQuan.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbQuan.setBackground(new Color(140, 177, 180));
		cmbQuan.setBounds(220, 29, 200, 36);
		pnlRow4.add(cmbQuan);

		cmbPhuong = new ComboBox<>();
		cmbPhuong.setModel(new DefaultComboBoxModel<String>());
		cmbPhuong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbPhuong.setBackground(new Color(140, 177, 180));
		cmbPhuong.setBounds(440, 29, 200, 36);
		pnlRow4.add(cmbPhuong);

		txtDiaChiCT = new TextField();
		txtDiaChiCT.setLineColor(new Color(149, 166, 248));
		txtDiaChiCT.setLabelText("?????a ch??? c??? th???");
		txtDiaChiCT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDiaChiCT.setColumns(10);
		txtDiaChiCT.setBackground(new Color(203, 239, 255));
		txtDiaChiCT.setBounds(660, 10, 288, 55);
		pnlRow4.add(txtDiaChiCT);

		JPanel pnlRow5 = new JPanel();
		pnlRow5.setLayout(null);
		pnlRow5.setBackground(new Color(203, 239, 255));
		pnlRow5.setBounds(0, top, 948, 55);
		top += 55 + padding;
		pnlContainer.add(pnlRow5);

		txtLuong = new TextField();
		txtLuong.setLineColor(new Color(149, 166, 248));
		txtLuong.setLabelText("L????ng");
		txtLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtLuong.setColumns(10);
		txtLuong.setBackground(new Color(203, 239, 255));
		txtLuong.setBounds(0, 0, 449, 55);
		pnlRow5.add(txtLuong);

		txtMatKhau = new TextField();
		txtMatKhau.setLineColor(new Color(149, 166, 248));
		txtMatKhau.setLabelText("M???t kh???u");
		txtMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBackground(new Color(203, 239, 255));
		txtMatKhau.setBounds(499, 0, 449, 55);
		txtMatKhau.setEditable(false);
		pnlRow5.add(txtMatKhau);

		JPanel pnlRow6 = new JPanel();
		pnlRow6.setLayout(null);
		pnlRow6.setBackground(new Color(203, 239, 255));
		pnlRow6.setBounds(0, top, 948, 65);
		top += 65 + padding;
		pnlContainer.add(pnlRow6);

		JPanel pnlChucVu = new JPanel();
		pnlChucVu.setBackground(Utils.secondaryColor);
		pnlChucVu.setBounds(0, 0, 449, 65);
		pnlRow6.add(pnlChucVu);
		pnlChucVu.setLayout(null);

		JLabel lblChucVu = new JLabel("Ch???c v???");
		lblChucVu.setForeground(Utils.labelTextField);
		lblChucVu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblChucVu.setBounds(4, 6, 100, 19);
		pnlChucVu.add(lblChucVu);

		cmbChucVu = new ComboBox<>();
		cmbChucVu.setModel(new DefaultComboBoxModel<>(new String[] { NhanVien.convertChucVuToString(ChucVu.QuanLy),
				NhanVien.convertChucVuToString(ChucVu.NhanVien) }));
		cmbChucVu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbChucVu.setBackground(new Color(140, 177, 180));
		cmbChucVu.setBounds(4, 29, 200, 36);
		pnlChucVu.add(cmbChucVu);

		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.setLayout(null);
		pnlTrangThai.setBackground(new Color(203, 239, 255));
		pnlTrangThai.setBounds(499, 0, 449, 65);
		pnlRow6.add(pnlTrangThai);

		JLabel lblTrangThai = new JLabel("Tr???ng th??i");
		lblTrangThai.setForeground(Utils.labelTextField);
		lblTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTrangThai.setBounds(4, 6, 100, 19);
		pnlTrangThai.add(lblTrangThai);

		cmbTrangThai = new ComboBox<>();
		cmbTrangThai.setModel(
				new DefaultComboBoxModel<>(new String[] { NhanVien.convertTrangThaiToString(TrangThai.DangLam),
						NhanVien.convertTrangThaiToString(TrangThai.NghiLam) }));
		cmbTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbTrangThai.setBackground(new Color(140, 177, 180));
		cmbTrangThai.setBounds(4, 29, 200, 36);
		pnlTrangThai.add(cmbTrangThai);

		txtMaNhanVien.setText(nhanVien_DAO.taoMaNhanVien());
		txtMatKhau.setText("1234Abc@");
		cmbQuan.addItem(Quan.getQuanLabel());
		cmbPhuong.addItem(Phuong.getPhuongLabel());

		setTinhToComboBox();
		cmbQuan.setEnabled(false);
		cmbPhuong.setEnabled(false);
		txtMaNhanVien.setEnabled(false);

		JPanel pnlActions = new JPanel();
		pnlActions.setLayout(null);
		pnlActions.setBackground(new Color(203, 239, 255));
		pnlActions.setBounds(0, top, 948, 48);
		pnlContainer.add(pnlActions);

		Button btnThem = new Button("Th??m");
		btnThem.setIcon(Utils.getImageIcon("add-user (2) 1.png"));
		btnThem.setRadius(8);
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnThem.setFocusable(false);
		btnThem.setColorOver(new Color(140, 177, 180));
		btnThem.setColorClick(new Color(140, 177, 180, 204));
		btnThem.setColor(new Color(140, 177, 180));
		btnThem.setBorderColor(new Color(203, 239, 255));
		btnThem.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnThem.setBounds(698, 0, 250, 48);
		pnlActions.add(btnThem);

		Button btnHuy = new Button("H???y");
		btnHuy.setIcon(Utils.getImageIcon("cancelled 1.png"));
		btnHuy.setRadius(8);
		btnHuy.setForeground(new Color(51, 51, 51));
		btnHuy.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnHuy.setFocusable(false);
		btnHuy.setColorOver(new Color(255, 255, 255, 204));
		btnHuy.setColorClick(new Color(255, 255, 255, 153));
		btnHuy.setColor(Color.WHITE);
		btnHuy.setBorderColor(new Color(203, 239, 255));
		btnHuy.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHuy.setBounds(0, 0, 250, 48);
		pnlActions.add(btnHuy);

		Button btnLamMoi = new Button("L??m m???i");
		btnLamMoi.setRadius(8);
		btnLamMoi.setForeground(new Color(51, 51, 51));
		btnLamMoi.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		btnLamMoi.setFocusable(false);
		btnLamMoi.setColorOver(new Color(255, 255, 255, 204));
		btnLamMoi.setColorClick(new Color(255, 255, 255, 153));
		btnLamMoi.setColor(Color.WHITE);
		btnLamMoi.setBorderColor(new Color(203, 239, 255));
		btnLamMoi.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLamMoi.setBounds(428, 0, 250, 48);
		pnlActions.add(btnLamMoi);

		cmbTinh.addItemListener(this);
		cmbQuan.addItemListener(this);
		cmbPhuong.addItemListener(this);

//		S??? ki???n txtHoten
		txtHoTen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtHoTen.setError(false);
			}
		});

//		S??? ki???n txtCCCD
		txtCCCD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtCCCD.setError(false);
			}
		});

//		S??? ki???n txtSoDienThoai
		txtSoDienThoai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtSoDienThoai.setError(false);
			}
		});

//		S??? ki???n txtDiaChiCT
		txtDiaChiCT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtDiaChiCT.setError(false);
			}
		});

//		S??? ki???n txtLuong
		txtLuong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtLuong.setError(false);
			}
		});

//		S??? ki???n txtMatKhau
		txtMatKhau.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtMatKhau.setError(false);
			}
		});

//		S??? ki???n n??t h???y
		btnHuy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.backPanel();
			}
		});

//		S??? ki???n n??t l??m m???i
		btnLamMoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				xoaRong();

				repaint();
			}
		});

//		S??? ki???n n??t th??m
		btnThem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!validator())
					return;
				NhanVien nhanVien = getNhanVienTuForm();
				boolean res = nhanVien_DAO.themNhanVien(nhanVien);

				if (res) {
					new Notification(jFrame, Type.SUCCESS, String.format("Th??m nh??n vi??n %s - %s th??nh c??ng",
							nhanVien.getMaNhanVien(), nhanVien.getHoTen())).showNotification();
					xoaRong();
					txtMaNhanVien.setText(nhanVien_DAO.taoMaNhanVien());
				} else
					new Notification(jFrame, Type.ERROR, "Th??m nh??n vi??n th???t b???i").showNotification();
			}
		});

//		S??? ki???n Component ???????c hi???n th???
		addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent event) {
				dateChoose.showPopup();
				dateChoose.hidePopup();
			}

			@Override
			public void ancestorMoved(AncestorEvent event) {
			}

			@Override
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
	}

	/**
	 * Get nh??n vi??n t??? form
	 *
	 * @return nhanVien
	 */
	private NhanVien getNhanVienTuForm() {
		String maNhanVien = txtMaNhanVien.getText();
		String hoTen = txtHoTen.getText().trim();
		String cccd = txtCCCD.getText().trim();
		String soDienThoai = txtSoDienThoai.getText().trim();
		LocalDate ngaySinh = Utils.getLocalDate(txtNgaySinh.getText());
		String diaChiCuThe = txtDiaChiCT.getText().trim();
		String luong = txtLuong.getText().trim();
		double luongDouble = luong.endsWith("?????") ? Utils.convertStringToTienTe(luong) : Double.parseDouble(luong);
		boolean gioiTinh = radNam.isSelected();
		ChucVu chucVu = NhanVien.convertStringToChucVu((String) cmbChucVu.getSelectedItem());
		TrangThai trangThai = NhanVien.convertStringToTrangThai((String) cmbTrangThai.getSelectedItem());
		return new NhanVien(maNhanVien, hoTen, cccd, soDienThoai, ngaySinh, gioiTinh, tinh, quan, phuong, diaChiCuThe,
				chucVu, luongDouble, trangThai);
	}

	/**
	 * L???ng nghe s??? ki???n JComboBox
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object object = e.getSource();
		if (e.getStateChange() != ItemEvent.SELECTED)
			return;
		if (cmbTinh.equals(object)) {
			if (!isEnabledEventTinh)
				return;
			isEnabledEventQuan = false;
			isEnabledEventPhuong = false;
			String tinhSelected = (String) cmbTinh.getSelectedItem();

			cmbPhuong.setSelectedIndex(0);
			cmbPhuong.setEnabled(false);
			cmbQuan = resizeComboBox(cmbQuan, Quan.getQuanLabel());
			quan = null;
			phuong = null;

			if (tinhSelected.equals(Tinh.getTinhLabel())) {
				cmbQuan.setSelectedIndex(0);
				cmbQuan.setEnabled(false);
				tinh = null;
				return;
			}
			Tinh tinh = diaChi_DAO.getTinh(tinhSelected);
			ThemNhanVien_GUI.this.tinh = tinh;
			setQuanToComboBox(ThemNhanVien_GUI.this.tinh);
			repaint();
			cmbQuan.setEnabled(true);
			isEnabledEventQuan = true;
			isEnabledEventPhuong = true;
		} else if (cmbQuan.equals(object)) {
			if (!isEnabledEventQuan)
				return;
			isEnabledEventPhuong = false;
			isEnabledEventQuan = false;
			String quanSelected = (String) cmbQuan.getSelectedItem();
			cmbPhuong = resizeComboBox(cmbPhuong, Quan.getQuanLabel());
			phuong = null;

			if (quanSelected.equals(Quan.getQuanLabel())) {
				cmbPhuong.setSelectedIndex(0);
				cmbPhuong.setEnabled(false);
				quan = null;
			} else {
				Quan quan = diaChi_DAO.getQuan(tinh, quanSelected);
				ThemNhanVien_GUI.this.quan = quan;
				cmbPhuong.setEnabled(true);
				setPhuongToComboBox(this.quan);
			}

			isEnabledEventPhuong = true;
			isEnabledEventQuan = true;
		} else if (cmbPhuong.equals(object)) {
			if (!isEnabledEventPhuong)
				return;
			isEnabledEventPhuong = false;
			String phuongSelect = (String) cmbPhuong.getSelectedItem();

			if (phuongSelect.equals(Phuong.getPhuongLabel())) {
				phuong = null;
				return;
			}

			Phuong phuong = diaChi_DAO.getPhuong(quan, phuongSelect);
			ThemNhanVien_GUI.this.phuong = phuong;
			isEnabledEventPhuong = false;
		}

	}

	/**
	 * X??a t???t c??? c??c items c???a JComboBox v?? th??m chu???i v??o JComboBox
	 *
	 * @param <E>
	 * @param list       JComboBox c???n x??a
	 * @param firstLabel chu???i c???n th??m
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <E> JComboBox<E> resizeComboBox(JComboBox<E> list, String firstLabel) {
		list.removeAllItems();
		list.addItem((E) firstLabel);
		return list;
	}

	/**
	 * Set danh s??ch ph?????ng c???a qu???n v??o JComboBox
	 *
	 * @param quan qu???n c???n l???y ph?????ng
	 */
	private void setPhuongToComboBox(Quan quan) {
		isEnabledEventPhuong = false;

		resizeComboBox(cmbPhuong, Phuong.getPhuongLabel());

		List<Phuong> phuongs = diaChi_DAO.getPhuong(quan);

		phuongs.sort(new Comparator<Phuong>() {

			@Override
			public int compare(Phuong o1, Phuong o2) {
				return o1.getPhuong().compareToIgnoreCase(o2.getPhuong());
			}
		});

		phuongs.forEach(phuong -> cmbPhuong.addItem(phuong.getPhuong()));

		isEnabledEventPhuong = true;
	}

	/**
	 * Set danh s??ch qu???n c???a t???nh v??o JComboBox
	 *
	 * @param tinh t???nh c???n l???y qu???n
	 */
	private void setQuanToComboBox(Tinh tinh) {
		isEnabledEventQuan = false;

		resizeComboBox(cmbQuan, Quan.getQuanLabel());
		List<Quan> quans = diaChi_DAO.getQuan(tinh);
		quans.sort(new Comparator<Quan>() {
			@Override
			public int compare(Quan o1, Quan o2) {
				return o1.getQuan().compareToIgnoreCase(o2.getQuan());
			}
		});
		quans.forEach(quan -> cmbQuan.addItem(quan.getQuan()));

		isEnabledEventQuan = true;
	}

	/**
	 * Set danh s??ch t???nh v??o JComboBox
	 */
	private void setTinhToComboBox() {
		isEnabledEventTinh = false;

		resizeComboBox(cmbTinh, Tinh.getTinhLabel());

		List<Tinh> tinhs = diaChi_DAO.getTinh();

		tinhs.sort(new Comparator<Tinh>() {
			@Override
			public int compare(Tinh o1, Tinh o2) {
				return o1.getTinh().compareToIgnoreCase(o2.getTinh());
			}
		});

		tinhs.forEach(tinh -> cmbTinh.addItem(tinh.getTinh()));

		isEnabledEventTinh = true;
	}

	/**
	 * Hi???n th??? th??ng b??o l???i v?? focus c??c JTextField
	 *
	 * @param txt     JtextField c???n focus
	 * @param message th??ng b??o l???i
	 * @return false
	 */
	private boolean showThongBaoLoi(TextField txt, String message) {
		new Notification(main, Type.ERROR, message).showNotification();
		txt.setError(true);
		txt.selectAll();
		txt.requestFocus();
		return false;
	}

	/**
	 * Ki???m tra th??ng tin nh??n vi??n
	 *
	 * @return true n???u th??ng tin nh??n vi??n h???p l???
	 */
	private boolean validator() {
		String vietNamese = Utils.getVietnameseDiacriticCharacters() + "A-Z";
		String vietNameseLower = Utils.getVietnameseDiacriticCharactersLower() + "a-z";

		String hoTen = txtHoTen.getText().trim();

		if (hoTen.length() <= 0)
			return showThongBaoLoi(txtHoTen, "Vui l??ng nh???p h??? t??n nh??n vi??n");

		if (Pattern.matches(String.format(".*[^%s%s ].*", vietNamese, vietNameseLower), hoTen))
			return showThongBaoLoi(txtHoTen, "H??? t??n ch??? ch???a c??c k?? t??? ch??? c??i");

		if (!Pattern.matches(
				String.format("[%s][%s]*( [%s][%s]*)+", vietNamese, vietNameseLower, vietNamese, vietNameseLower),
				hoTen))
			return showThongBaoLoi(txtHoTen, "H??? t??n ph???i b???t ?????u b???ng k?? t??? hoa v?? c?? ??t nh???t 2 t???");

		String cccd = txtCCCD.getText().trim();

		if (cccd.length() <= 0)
			return showThongBaoLoi(txtCCCD, "Vui l??ng nh???p s??? c??n c?????c c??ng d??n");

		if (!Pattern.matches("\\d{12}", cccd))
			return showThongBaoLoi(txtCCCD, "S??? c??n c?????c c??ng d??n ph???i l?? 12 k?? t??? s???");

		if (nhanVien_DAO.isCCCDDaTonTai(cccd))
			return showThongBaoLoi(txtCCCD, "S??? c??n c?????c c??ng d??n ???? t???n t???i");

		String soDienThoai = txtSoDienThoai.getText().trim();

		if (soDienThoai.length() <= 0)
			return showThongBaoLoi(txtSoDienThoai, "Vui l??ng nh???p s??? ??i???n tho???i");

		if (!Utils.isSoDienThoai(soDienThoai))
			return showThongBaoLoi(txtSoDienThoai, "S??? ??i???n tho???i ph???i b???t ?????u b???ng s??? 0, theo sau l?? 9 k?? t??? s???");

		if (nhanVien_DAO.isSoDienThoaiDaTonTai(soDienThoai))
			return showThongBaoLoi(txtSoDienThoai, "S??? ??i???n tho???i ???? t???n t???i");

		String ngaySinh = txtNgaySinh.getText();
		long daysElapsed = java.time.temporal.ChronoUnit.DAYS.between(Utils.getLocalDate(ngaySinh), LocalDate.now());
		boolean isDuTuoi = daysElapsed / (18 * 365) > 0;

		if (!isDuTuoi) {
			new Notification(main, Type.ERROR, "Nh??n vi??n ch??a ????? 18 tu???i").showNotification();
			dateChoose.showPopup();
			return false;
		}

		boolean isNamSelected = radNam.isSelected();
		boolean isNuSelected = radNu.isSelected();

		if (!isNamSelected && !isNuSelected) {
			new Notification(main, Type.ERROR, "Vui l??ng ch???n gi???i t??nh").showNotification();
			return false;
		}

		String tinh = (String) cmbTinh.getSelectedItem();

		if (tinh.equals(Tinh.getTinhLabel())) {
			new Notification(main, Type.ERROR, "Vui l??ng ch???n t???nh/ th??nh ph???").showNotification();
			cmbTinh.showPopup();
			return false;
		}

		String quan = (String) cmbQuan.getSelectedItem();

		if (quan.equals(Quan.getQuanLabel())) {
			new Notification(main, Type.ERROR, "Vui l??ng ch???n qu???n/ huy???n").showNotification();
			cmbQuan.showPopup();
			return false;
		}

		String phuong = (String) cmbPhuong.getSelectedItem();

		if (phuong.equals(Phuong.getPhuongLabel())) {
			new Notification(main, Type.ERROR, "Vui l??ng ch???n ph?????ng/ x??").showNotification();
			cmbPhuong.showPopup();
			return false;
		}

		String diaChi = txtDiaChiCT.getText().trim();

		if (diaChi.length() <= 0)
			return showThongBaoLoi(txtDiaChiCT, "Vui l??ng nh???p ?????a ch???");

		String luong = txtLuong.getText().trim();

		if (luong.length() <= 0)
			return showThongBaoLoi(txtLuong, "Vui l??ng nh???p l????ng");

		if (!Utils.isDouble(luong))
			return showThongBaoLoi(txtLuong, "L????ng ch??? ch???a c??c k?? t??? s???");

		double luongNumber = Double.parseDouble(luong);

		if (luongNumber <= 0)
			return showThongBaoLoi(txtLuong, "L????ng ph???i l???n h??n 0");

		return true;
	}

	/**
	 * X??a r???ng c??c textfield v?? l??m m???i ComboBox
	 */
	private void xoaRong() {
		txtHoTen.setText("");
		txtCCCD.setText("");
		txtSoDienThoai.setText("");
		dateChoose.toDay();
		buttonGroupGioiTinh.clearSelection();
		setTinhToComboBox();
		cmbChucVu.setSelectedIndex(0);
		cmbTrangThai.setSelectedIndex(0);
		cmbQuan.setSelectedIndex(0);
		cmbPhuong.setSelectedIndex(0);
		cmbQuan.setEnabled(false);
		cmbPhuong.setEnabled(false);
		txtDiaChiCT.setText("");
		txtLuong.setText("");
		txtHoTen.requestFocus();
		main.repaint();
	}
}

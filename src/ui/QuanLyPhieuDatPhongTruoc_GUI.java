package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import components.button.Button;
import components.controlPanel.ControlPanel;
import components.jDialog.JDialogCustom;
import components.scrollbarCustom.ScrollBarCustom;
import dao.ChiTietDatPhong_DAO;
import dao.KhachHang_DAO;
import dao.PhieuDatPhong_DAO;
import entity.ChiTietDatPhong;
import entity.DonDatPhong;
import utils.Utils;

public class QuanLyPhieuDatPhong_GUI extends JPanel {
	private static JLabel lblTime;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField txtSoDienThoai;
	private JTable tbl;
	private JComboBox<String> cboTrangThai;
	private JComboBox<String> cboMaPhieuDat;
	private DefaultComboBoxModel<String> maPhieuDatModel;
	private DefaultTableModel tableModel;
	private JDialogCustom jDialog;
	private PhieuDatPhong_DAO phieuDatPhong_DAO;
	private ControlPanel pnlControl;
	private KhachHang_DAO khachHang_DAO;
	private Button btnSearch;
	private Button btnXemPhong;
	private Button btnNhanPhong;
	private Button btnHuyPhong;
	private Button btnXuatPDF;
	private Button btnLamMoi;
	private final int widthPnlContainer = 1086;
	private ChiTietDatPhong_DAO chiTietDatPhong_DAO;

	/**
	 * Create the frame.
	 */
	public QuanLyPhieuDatPhong_GUI(Main main) {
		khachHang_DAO = new KhachHang_DAO();
		phieuDatPhong_DAO = new PhieuDatPhong_DAO();
		chiTietDatPhong_DAO = new ChiTietDatPhong_DAO();
		jDialog = new JDialogCustom(main, components.jDialog.JDialogCustom.Type.warning);

		setBackground(Utils.secondaryColor);
		setBounds(0, 0, Utils.getScreenWidth(), Utils.getBodyHeight());
		setLayout(null);

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(Utils.getLeft(widthPnlContainer), 0, widthPnlContainer, Utils.getBodyHeight());
		pnlContainer.setLayout(null);
		this.add(pnlContainer);

//		Search
		JPanel pnlSearch = new JPanel();
		pnlSearch.setBackground(Utils.secondaryColor);
		pnlSearch.setBounds(16, 10, 1054, 75);
		pnlContainer.add(pnlSearch);
		pnlSearch.setLayout(null);

		lblTime = new JLabel("");
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTime.setBounds(874, 0, 180, 24);
		pnlSearch.add(lblTime);
		clock();

		JPanel pnlMaPhieuDat = new JPanel();
		pnlMaPhieuDat.setBackground(Utils.secondaryColor);
		pnlMaPhieuDat.setBounds(0, 30, 300, 36);
		pnlSearch.add(pnlMaPhieuDat);
		pnlMaPhieuDat.setLayout(null);

		JLabel lblMaPhieuDat = new JLabel("Mã phiếu đặt");
		lblMaPhieuDat.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblMaPhieuDat.setBounds(0, 0, 150, 36);
		pnlMaPhieuDat.add(lblMaPhieuDat);

		cboMaPhieuDat = new JComboBox<String>();
		maPhieuDatModel = new DefaultComboBoxModel<String>(new String[] { "Mã phiếu đặt" });
		cboMaPhieuDat.setModel(maPhieuDatModel);
		cboMaPhieuDat.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cboMaPhieuDat.setBackground(Utils.primaryColor);
		cboMaPhieuDat.setBounds(150, 0, 150, 36);
		pnlMaPhieuDat.add(cboMaPhieuDat);

		JPanel pnlSoDienThoai = new JPanel();
		pnlSoDienThoai.setBackground(Utils.secondaryColor);
		pnlSoDienThoai.setBounds(605, 30, 275, 36);
		pnlSearch.add(pnlSoDienThoai);
		pnlSoDienThoai.setLayout(null);

		JLabel lblSoDienThoai = new JLabel("SĐT khách");
		lblSoDienThoai.setBounds(0, 0, 115, 36);
		lblSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		pnlSoDienThoai.add(lblSoDienThoai);

		JPanel pnlInputSoDienThoai = new JPanel();
		pnlInputSoDienThoai.setBackground(Utils.secondaryColor);
		pnlInputSoDienThoai.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pnlInputSoDienThoai.setBounds(125, 0, 150, 36);
		pnlSoDienThoai.add(pnlInputSoDienThoai);
		pnlInputSoDienThoai.setLayout(null);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBorder(null);
		txtSoDienThoai.setBackground(Utils.secondaryColor);
		txtSoDienThoai.setBounds(8, 1, 134, 34);
		pnlInputSoDienThoai.add(txtSoDienThoai);

		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.setBackground(Utils.secondaryColor);
		pnlTrangThai.setBounds(315, 30, 275, 36);
		pnlSearch.add(pnlTrangThai);
		pnlTrangThai.setLayout(null);

		JLabel lblTrangThai = new JLabel("Trạng thái");
		lblTrangThai.setBounds(0, 0, 115, 36);
		lblTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		pnlTrangThai.add(lblTrangThai);

		cboTrangThai = new JComboBox<String>();
		cboTrangThai.setModel(new DefaultComboBoxModel<String>(new String[] { "Trạng thái", "Đang chờ", "Đã hủy" }));
		cboTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cboTrangThai.setBackground(Utils.primaryColor);
		cboTrangThai.setBounds(125, 0, 150, 36);
		pnlTrangThai.add(cboTrangThai);

		btnSearch = new Button("Tìm");
		btnSearch.setFocusable(false);
		btnSearch.setIcon(Utils.getImageIcon("searching.png"));
		btnSearch.setRadius(4);
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setColor(new Color(134, 229, 138));
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnSearch.setBounds(904, 30, 150, 40);
		btnSearch.setBorderColor(Utils.secondaryColor);
		btnSearch.setColorOver(new Color(134, 229, 138));
		btnSearch.setColorClick(new Color(59, 238, 66));
		btnSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlSearch.add(btnSearch);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(16, 104, 1054, 36);
		pnlContainer.add(pnlActions);
		pnlActions.setLayout(null);

		btnXemPhong = new Button("Xem phòng");
		btnXemPhong.setFocusable(false);
		btnXemPhong.setIcon(Utils.getImageIcon("room.png"));
		btnXemPhong.setRadius(4);
		btnXemPhong.setForeground(Color.WHITE);
		btnXemPhong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnXemPhong.setColorOver(Utils.primaryColor);
		btnXemPhong.setColorClick(new Color(161, 184, 186));
		btnXemPhong.setColor(Utils.primaryColor);
		btnXemPhong.setBorderColor(Utils.secondaryColor);
		btnXemPhong.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnXemPhong.setBounds(0, 0, 200, 36);
		pnlActions.add(btnXemPhong);

		btnNhanPhong = new Button("Nhận phòng");
		btnNhanPhong.setRadius(4);
		btnNhanPhong.setIcon(Utils.getImageIcon("check-in (1).png"));
		btnNhanPhong.setForeground(Color.WHITE);
		btnNhanPhong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNhanPhong.setFocusable(false);
		btnNhanPhong.setColorOver(Utils.primaryColor);
		btnNhanPhong.setColorClick(new Color(161, 184, 186));
		btnNhanPhong.setColor(Utils.primaryColor);
		btnNhanPhong.setBorderColor(Utils.secondaryColor);
		btnNhanPhong.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNhanPhong.setBounds(220, 0, 200, 36);
		pnlActions.add(btnNhanPhong);

		btnHuyPhong = new Button("Hủy phòng");
		btnHuyPhong.setRadius(4);
		btnHuyPhong.setIcon(Utils.getImageIcon("door.png"));
		btnHuyPhong.setForeground(Color.WHITE);
		btnHuyPhong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnHuyPhong.setFocusable(false);
		btnHuyPhong.setColorOver(Utils.primaryColor);
		btnHuyPhong.setColorClick(new Color(161, 184, 186));
		btnHuyPhong.setColor(Utils.primaryColor);
		btnHuyPhong.setBorderColor(Utils.secondaryColor);
		btnHuyPhong.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHuyPhong.setBounds(440, 0, 200, 36);
		pnlActions.add(btnHuyPhong);

		btnXuatPDF = new Button("Xuất PDF");
		btnXuatPDF.setRadius(4);
		btnXuatPDF.setIcon(Utils.getImageIcon("add-file.png"));
		btnXuatPDF.setForeground(Color.WHITE);
		btnXuatPDF.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnXuatPDF.setFocusable(false);
		btnXuatPDF.setColorOver(Utils.primaryColor);
		btnXuatPDF.setColorClick(new Color(161, 184, 186));
		btnXuatPDF.setColor(Utils.primaryColor);
		btnXuatPDF.setBorderColor(Utils.secondaryColor);
		btnXuatPDF.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnXuatPDF.setBounds(660, 0, 200, 36);
		pnlActions.add(btnXuatPDF);

		btnLamMoi = new Button("Làm mới");
		btnLamMoi.setRadius(4);
		btnLamMoi.setIcon(Utils.getImageIcon("reset.png"));
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnLamMoi.setFocusable(false);
		btnLamMoi.setColorOver(Utils.primaryColor);
		btnLamMoi.setColorClick(new Color(161, 184, 186));
		btnLamMoi.setColor(Utils.primaryColor);
		btnLamMoi.setBorderColor(Utils.secondaryColor);
		btnLamMoi.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLamMoi.setBounds(880, 0, 180, 36);
		pnlActions.add(btnLamMoi);

//		Table danh sách phiếu đặt phòng trước
		int topPnlControl = Utils.getBodyHeight();
		topPnlControl -= 80;

		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(16, 158, 1054, topPnlControl - 174);
		scr.setBackground(Utils.primaryColor);
		scr.getViewport().setBackground(Color.WHITE);

		ScrollBarCustom scp = new ScrollBarCustom();
		scp.setScrollbarColor(new Color(203, 203, 203));
		scr.setVerticalScrollBar(scp);
		pnlContainer.add(scr);
		tbl = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean getShowVerticalLines() {
				return false;
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			/**
			 * Set màu từng dòng cho Table
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
		tbl.setAutoCreateRowSorter(true);
		tableModel = new DefaultTableModel(new String[] { "Mã phiếu đặt", "SĐT khách", "Thời gian lập phiếu",
				"Thời gian nhận phòng", "Phòng", "Trạng thái" }, 0);
		JTableHeader tblHeader = tbl.getTableHeader();
		TableColumnModel tableColumnModel = tbl.getColumnModel();
		tbl.setModel(tableModel);
		tbl.setFocusable(false);

		tableColumnModel.getColumn(0).setPreferredWidth(150);
		tableColumnModel.getColumn(1).setPreferredWidth(150);
		tableColumnModel.getColumn(2).setPreferredWidth(200);
		tableColumnModel.getColumn(3).setPreferredWidth(200);
		tableColumnModel.getColumn(4).setPreferredWidth(218);
		tableColumnModel.getColumn(5).setPreferredWidth(125);
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
		tblHeader.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setRowHeight(36);
		scr.setViewportView(tbl);

		pnlControl = new ControlPanel(Utils.getLeft(widthPnlContainer, 286), topPnlControl, main);
		pnlContainer.add(pnlControl);

//		Sự kiện nút tìm kiếm phiếu đặt phòng
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filterPhieuDatPhong();
			}
		});

//		Sự kiện nút xem thông tin phiếu đặt phòng
		btnXemPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnXemPhong.isEnabled())
					return;
				int row = tbl.getSelectedRow();
				if (row == -1) {
					jDialog.showMessage("Warning", "Vui lòng chọn phòng muốn xem");
				} else {
					String maPhieuDat = (String) tableModel.getValueAt(row, 0);
					ChiTietDatPhong phieuDatPhong = phieuDatPhong_DAO
							.getChiTietDatPhongTheoMa(new DonDatPhong(maPhieuDat));
					ThongTinChiTietPhieuDatPhong_GUI jFrame = new ThongTinChiTietPhieuDatPhong_GUI(main, phieuDatPhong);
					main.addPnlBody(jFrame, "Thông tin chi tiêt phiếu đặt phòng", 1, 0);
				}
			}
		});
//		Sự kiện nút nhận phòng
		btnNhanPhong.addMouseListener(new MouseAdapter() {
			private void handleNhanPhong() {
//				int row = tbl.getSelectedRow();
//				String maPhieuDat = (String) tbl.getValueAt(row, 0);

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnNhanPhong.isEnabled())
					return;

				handleNhanPhong();
			}
		});

//		Sự kiện nút làm mới
		btnLamMoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cboMaPhieuDat.setSelectedIndex(0);
				cboTrangThai.setSelectedIndex(0);
				txtSoDienThoai.setText("");
				Utils.emptyTable(tbl);
				addRow(phieuDatPhong_DAO.getAllDonDatPhong());
				pnlControl.setTbl(tbl);
			}
		});

//	Sự kiện JCombobox mã phiếu đặt phòng
		cboMaPhieuDat.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filterPhieuDatPhong();
				}
			}
		});
//	Sự kiện JCombobox trạng thái
		cboTrangThai.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filterPhieuDatPhong();
				}
			}
		});
//	Sự kiện cho JTable
//		tbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent lse) {
//				if (!lse.getValueIsAdjusting()) {
//					setEnabledBtnActions();
//				}
//			}
//		});

		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tbl.isEnabled()) {
					pnlControl.setTrangHienTai(tbl.getSelectedRow() + 1);
				}
			}
		});

		addAncestorListener(new AncestorListener() {
			Thread clockThread;

			public void ancestorAdded(AncestorEvent event) {
				clockThread = clock();

				Utils.emptyTable(tbl);
				cboMaPhieuDat.removeAllItems();
				cboMaPhieuDat.addItem("Mã phiếu đặt");
				List<DonDatPhong> list = phieuDatPhong_DAO.getAllDonDatPhong();
				list.forEach(phieuDatPhong -> cboMaPhieuDat.addItem(phieuDatPhong.getMaDonDatPhong()));
				pnlControl.setTbl(tbl);
			}

			public void ancestorMoved(AncestorEvent event) {
			}

			@SuppressWarnings("deprecation")
			public void ancestorRemoved(AncestorEvent event) {
				clockThread.stop();
			}
		});

	}

	private void filterPhieuDatPhong() {
		String maPhieuDat = (String) cboMaPhieuDat.getSelectedItem();
		String trangThai = (String) cboTrangThai.getSelectedItem();
		String soDienThoai = txtSoDienThoai.getText();

		if (maPhieuDat.equals("Mã phiếu đặt"))
			maPhieuDat = "";
		if (trangThai.equals("Trạng thái"))
			trangThai = "";
		if (soDienThoai.trim().equals(""))
			soDienThoai = "%%";

		List<DonDatPhong> list = phieuDatPhong_DAO.filterDonDatPhong(maPhieuDat, soDienThoai, trangThai);
		if (list.size() == 0) {
			jDialog.showMessage("Thông báo", "Không có phòng cần tìm");
			return;
		}

		Utils.emptyTable(tbl);
		addRow(list);
		pnlControl.setTbl(tbl);

	}

	public static Thread clock() {
		Thread clock = new Thread() {
			@Override
			public void run() {
				for (;;) {
					try {
						LocalDateTime currTime = LocalDateTime.now();
						int day = currTime.getDayOfMonth();
						int month = currTime.getMonthValue();
						int year = currTime.getYear();
						int hour = currTime.getHour();
						int minute = currTime.getMinute();
						int second = currTime.getSecond();
						lblTime.setText(String.format("%s/%s/%s | %s:%s:%s", day < 10 ? "0" + day : day,
								month < 10 ? "0" + month : month, year, hour < 10 ? "0" + hour : hour,
								minute < 10 ? "0" + minute : minute, second < 10 ? "0" + second : second));
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		clock.start();

		return clock;
	}

	private List<DonDatPhong> addRow(List<DonDatPhong> list) {
		list.forEach(datPhong -> addRow(datPhong));
		return list;
	}

	private void addRow(DonDatPhong donDatPhong) {
		String maDatPhong = donDatPhong.getMaDonDatPhong();
		String maKhachHang = donDatPhong.getKhachHang().getMaKhachHang();
		List<ChiTietDatPhong> listChiTietDatPhong = chiTietDatPhong_DAO.getAllChiTietDatPhong(donDatPhong);
		List<String> listPhong = new ArrayList<String>();
		listChiTietDatPhong.forEach(chiTietDatPhong -> listPhong.add(chiTietDatPhong.getPhong().getMaPhong()));

		tableModel.addRow(new String[] { maDatPhong, khachHang_DAO.getKhachHangTheoMa(maKhachHang).getSoDienThoai(),
				String.format("%s - %s", donDatPhong.getGioDatPhong(), donDatPhong.getNgayDatPhong()),
				String.format("%s - %s", donDatPhong.getGioNhanPhong(), donDatPhong.getNgayNhanPhong()),
				String.format("%s - %s", listPhong.size(), String.join(", ", listPhong)),
				DonDatPhong.convertTrangThaiToString(donDatPhong.getTrangThai()) });
	}

//	private void setEnabledBtnActions() {
//		int row = tbl.getSelectedRow();
//
//		if (row == -1) {
//			btnXemPhong.setEnabled(false);
//			btnNhanPhong.setEnabled(false);
//			btnHuyPhong.setEnabled(false);
//			btnXuatPDF.setEnabled(false);
//		} else {
//			btnXemPhong.setEnabled(true);
//			btnNhanPhong.setEnabled(true);
//			btnHuyPhong.setEnabled(true);
//			btnXuatPDF.setEnabled(true);
//		}
//	}
}

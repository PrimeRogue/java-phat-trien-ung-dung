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
import javax.swing.ImageIcon;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import components.button.Button;
import components.controlPanel.ControlPanel;
import components.jDialog.JDialogCustom;
import components.notification.Notification;
import components.panelRound.PanelRound;
import components.scrollbarCustom.ScrollBarCustom;
import dao.DichVu_DAO;
import dao.LoaiDichVu_DAO;
import entity.DichVu;
import entity.LoaiDichVu;
import utils.Utils;

public class QuanLyDichVu_GUI extends JPanel {

	private static JLabel lblTime;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void clock() {
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
	}

	private Button btnXem, btnThem, btnSua, btnXoa, btnKhoiPhuc, btnDanhSachXoa, btnDanhSachTonTai;
	private JComboBox<String> cmbLoaiDV;
	private JComboBox<String> cmbSoLuong;
	private DichVu_DAO dichVu_DAO;
	private LoaiDichVu_DAO loaiDichVu_DAO;
	private ControlPanel pnlControl;
	private DefaultTableModel tableModel;
	private JTable tbl;
	private List<DichVu> listDV;
	private JTextField txtSearch;

	public QuanLyDichVu_GUI(Main main) {
		loaiDichVu_DAO = new LoaiDichVu_DAO();
		dichVu_DAO = new DichVu_DAO();
		int left = Utils.getLeft(1054);

		setBackground(Utils.secondaryColor);
		setBounds(0, 0, Utils.getScreenWidth(), Utils.getBodyHeight());
		setLayout(null);

//		Search
		JPanel pnlSearch = new JPanel();
		pnlSearch.setBackground(Utils.secondaryColor);
		pnlSearch.setBounds(left, 16, 1054, 24);
		this.add(pnlSearch);
		pnlSearch.setLayout(null);

		JLabel lblSearch = new JLabel("T??M KI???M D???CH V??? THEO T??N:");
		lblSearch.setBounds(0, -1, 299, 27);
		lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		pnlSearch.add(lblSearch);

		lblTime = new JLabel("");
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTime.setBounds(874, 0, 180, 24);
		pnlSearch.add(lblTime);
		clock();

		JPanel pnlSearchForm = new JPanel();
		pnlSearchForm.setBackground(Utils.secondaryColor);
		pnlSearchForm.setBounds(left, 56, 1054, 36);
		this.add(pnlSearchForm);
		pnlSearchForm.setLayout(null);

		Button btnSearch = new Button("T??m");
		btnSearch.setFocusable(false);
		btnSearch.setIcon(new ImageIcon("Icon\\searching.png"));
		btnSearch.setRadius(4);
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setColor(new Color(134, 229, 138));
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnSearch.setBounds(660, -4, 154, 44);
		btnSearch.setBorderColor(Utils.secondaryColor);
		btnSearch.setColorOver(new Color(134, 229, 138));
		btnSearch.setColorClick(new Color(59, 238, 66));
		btnSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlSearchForm.add(btnSearch);

		PanelRound pnlSearchInput = new PanelRound();
		pnlSearchInput.setRounded(4);
		pnlSearchInput.setBackground(Utils.secondaryColor);
		pnlSearchInput.setBounds(0, 0, 631, 36);
		pnlSearchInput.setBorder(new LineBorder(Color.BLACK));
		pnlSearchInput.setRounded(4);
		pnlSearchForm.add(pnlSearchInput);
		pnlSearchInput.setLayout(null);

		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtSearch.setBackground(Utils.secondaryColor);
		txtSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtSearch.setBounds(9, 1, 620, 34);
		pnlSearchInput.add(txtSearch);
		txtSearch.setColumns(10);

		btnDanhSachXoa = new Button("DS ng???ng KD");
		btnDanhSachXoa.setIcon(new ImageIcon("Icon\\listdelete.png"));
		btnDanhSachXoa.setRadius(4);
		btnDanhSachXoa.setForeground(Color.WHITE);
		btnDanhSachXoa.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnDanhSachXoa.setColorOver(Utils.primaryColor);
		btnDanhSachXoa.setColorClick(new Color(161, 184, 186));
		btnDanhSachXoa.setColor(Utils.primaryColor);
		btnDanhSachXoa.setBorderColor(Utils.secondaryColor);
		btnDanhSachXoa.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDanhSachXoa.setBounds(844, -4, 210, 44);
		pnlSearchForm.add(btnDanhSachXoa);

		btnDanhSachTonTai = new Button("DS c??n KD");
		btnDanhSachTonTai.setIcon(new ImageIcon("Icon\\requirement.png"));
		btnDanhSachTonTai.setVisible(false);
		btnDanhSachTonTai.setForeground(Color.WHITE);
		btnDanhSachTonTai.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnDanhSachTonTai.setColorOver(Utils.primaryColor);
		btnDanhSachTonTai.setColorClick(new Color(161, 184, 186));
		btnDanhSachTonTai.setColor(Utils.primaryColor);
		btnDanhSachTonTai.setBorderColor(Utils.secondaryColor);
		btnDanhSachTonTai.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDanhSachTonTai.setBounds(844, -4, 210, 44);
		pnlSearchForm.add(btnDanhSachTonTai);

		JPanel pnlButton = new JPanel();
		pnlButton.setBackground(Utils.secondaryColor);
		pnlButton.setBounds(left, 108, 1054, 40);
		this.add(pnlButton);
		pnlButton.setLayout(null);

		btnXem = new Button("Xem");

		btnXem.setFocusable(false);
		btnXem.setIcon(new ImageIcon("Icon\\searching.png"));
		btnXem.setRadius(4);
		btnXem.setForeground(Color.WHITE);
		btnXem.setColor(new Color(134, 229, 138));
		btnXem.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnXem.setBounds(-2, -2, 154, 44);
		btnXem.setBorderColor(Utils.secondaryColor);
		btnXem.setColorOver(new Color(134, 229, 138));
		btnXem.setColorClick(new Color(59, 238, 66));
		btnXem.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlButton.add(btnXem);

		btnThem = new Button("Th??m");

		btnThem.setFocusable(false);
		btnThem.setIcon(new ImageIcon("Icon\\add 1.png"));
		btnThem.setRadius(4);
		btnThem.setForeground(Color.WHITE);
		btnThem.setColor(new Color(134, 229, 138));
		btnThem.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnThem.setBounds(182, -2, 154, 44);
		btnThem.setBorderColor(Utils.secondaryColor);
		btnThem.setColorOver(new Color(134, 229, 138));
		btnThem.setColorClick(new Color(59, 238, 66));
		btnThem.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlButton.add(btnThem);

		btnSua = new Button("S???a");

		btnSua.setFocusable(false);
		btnSua.setIcon(new ImageIcon("Icon\\update 1.png"));
		btnSua.setRadius(4);
		btnSua.setForeground(Color.WHITE);
		btnSua.setColor(new Color(134, 229, 138));
		btnSua.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnSua.setBounds(364, -2, 154, 44);
		btnSua.setBorderColor(Utils.secondaryColor);
		btnSua.setColorOver(new Color(134, 229, 138));
		btnSua.setColorClick(new Color(59, 238, 66));
		btnSua.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlButton.add(btnSua);

		btnXoa = new Button("X??a");

		btnXoa.setFocusable(false);
		btnXoa.setIcon(new ImageIcon("Icon\\download 1.png"));
		btnXoa.setRadius(4);
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setColor(new Color(134, 229, 138));
		btnXoa.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnXoa.setBounds(546, -2, 154, 44);
		btnXoa.setBorderColor(Utils.secondaryColor);
		btnXoa.setColorOver(new Color(134, 229, 138));
		btnXoa.setColorClick(new Color(59, 238, 66));
		btnXoa.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlButton.add(btnXoa);

		btnKhoiPhuc = new Button("Kh??i ph???c");

		btnKhoiPhuc.setFocusable(false);
		btnKhoiPhuc.setIcon(new ImageIcon("Icon\\restore.png"));
		btnKhoiPhuc.setRadius(4);
		btnKhoiPhuc.setForeground(Color.WHITE);
		btnKhoiPhuc.setColor(new Color(134, 229, 138));
		btnKhoiPhuc.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnKhoiPhuc.setBounds(546, -2, 154, 44);
		btnKhoiPhuc.setBorderColor(Utils.secondaryColor);
		btnKhoiPhuc.setColorOver(new Color(134, 229, 138));
		btnKhoiPhuc.setColorClick(new Color(59, 238, 66));
		btnKhoiPhuc.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlButton.add(btnKhoiPhuc);
		btnKhoiPhuc.setVisible(false);

//		s??? ki???n n??t btnDanhSachTonTai
		btnDanhSachTonTai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSearch.setText("");
				btnXoa.setVisible(true);
				btnKhoiPhuc.setVisible(false);
				btnDanhSachXoa.setVisible(true);
				btnXem.setEnabled(true);
				btnThem.setEnabled(true);
				btnSua.setEnabled(true);
				btnDanhSachTonTai.setVisible(false);
				tableModel.setRowCount(0);
				filterDichVu();
				pnlControl.setTbl(tbl);
				cmbLoaiDV.setSelectedIndex(0);
				cmbSoLuong.setSelectedIndex(0);

			}
		});
// s??? ki???n n??t btnDanhSachXoa
		btnDanhSachXoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSearch.setText("");
				btnXoa.setVisible(false);
				btnKhoiPhuc.setVisible(true);
				btnDanhSachTonTai.setVisible(true);
				btnXem.setEnabled(false);
				btnThem.setEnabled(false);
				btnSua.setEnabled(false);
				btnDanhSachXoa.setVisible(false);
				filterDichVuDaNgungKinhDoanh();
				cmbLoaiDV.setSelectedIndex(0);
				cmbSoLuong.setSelectedIndex(0);
			}
		});

		cmbLoaiDV = new JComboBox<String>();
		cmbLoaiDV.addItem("Ph??n lo???i");
		ArrayList<LoaiDichVu> listLoaiDV = (ArrayList<LoaiDichVu>) loaiDichVu_DAO.getAllLoaiDichVu();
		for (LoaiDichVu loaiDV : listLoaiDV) {
			cmbLoaiDV.addItem(loaiDV.getTenLoaiDichVu());
		}

		cmbLoaiDV.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbLoaiDV.setBackground(Utils.primaryColor);
		cmbLoaiDV.setBounds(728, 0, 150, 40);
		pnlButton.add(cmbLoaiDV);

		cmbSoLuong = new JComboBox<String>();

		cmbSoLuong.setModel(
				new DefaultComboBoxModel<String>(new String[] { "S??? l?????ng", "<50", "50-100", "100-200", ">200" }));
		cmbSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbSoLuong.setBackground(Utils.primaryColor);
		cmbSoLuong.setBounds(904, 0, 150, 40);
		pnlButton.add(cmbSoLuong);

		int topPnlControl = Utils.getBodyHeight() - 80;

		JScrollPane scr = new JScrollPane();
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(left, 164, 1054, topPnlControl - 180);
		scr.setBackground(Utils.primaryColor);
		ScrollBarCustom scp = new ScrollBarCustom();
//		Set color scrollbar thumb
		scp.setScrollbarColor(new Color(203, 203, 203));
		scr.setVerticalScrollBar(scp);
		this.add(scr);

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

		tableModel = new DefaultTableModel(
				new String[] { "M?? DV", "T??nDV", "????n v??? t??nh", "S??? l?????ng", "Lo???i", "Gi?? mua", "Gi?? b??n" }, 0);
		JTableHeader tblHeader = tbl.getTableHeader();
		TableColumnModel tableColumnModel = tbl.getColumnModel();

		tbl.setModel(tableModel);
		tbl.setFocusable(false);
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableColumnModel.getColumn(0).setPreferredWidth(80);
		tableColumnModel.getColumn(1).setPreferredWidth(224);
		tableColumnModel.getColumn(2).setPreferredWidth(120);
		tableColumnModel.getColumn(3).setPreferredWidth(120);
		tableColumnModel.getColumn(4).setPreferredWidth(180);
		tableColumnModel.getColumn(5).setPreferredWidth(160);
		tableColumnModel.getColumn(6).setPreferredWidth(160);
		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
		tblHeader.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setRowHeight(36);
		scr.setViewportView(tbl);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		tableColumnModel.getColumn(3).setCellRenderer(dtcr);
		tableColumnModel.getColumn(5).setCellRenderer(dtcr);
		tableColumnModel.getColumn(6).setCellRenderer(dtcr);
		pnlControl = new ControlPanel(Utils.getLeft(286), topPnlControl, main);
		this.add(pnlControl);

		addAncestorListener(new AncestorListener() {

			@Override
			public void ancestorAdded(AncestorEvent event) {
				if (listDV == null)
					listDV = (List<DichVu>) dichVu_DAO.getAllDichVu();
				else
					listDV = dichVu_DAO.getDanhSachDichVuTheoMa(listDV);
				setEmptyTable();
				addRow(listDV);
				pnlControl.setTbl(tbl);
			}

			@Override
			public void ancestorMoved(AncestorEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void ancestorRemoved(AncestorEvent event) {
				// TODO Auto-generated method stub

			}
		});

		// S??? ki???n n??t t??m ki???m d???ch v???
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnDanhSachXoa.isVisible()) {
					filterDichVu();
				} else
					filterDichVuDaNgungKinhDoanh();
			}
		});
//		S??? ki???n n??t th??m d???ch v???
		btnThem.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!btnThem.isEnabled())
					return;
				main.addPnlBody(new ThemDichVu_GUI(main), "Th??m d???ch v???", 1, 0);

			};
		});
//		S??? ki???n n??t xem d???ch v???
		btnXem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnXem.isEnabled())
					return;
				int row = tbl.getSelectedRow();
				if (row == -1) {
					new JDialogCustom(main, components.jDialog.JDialogCustom.Type.warning).showMessage("Warning",
							"Vui l??ng ch???n d???ch v??? mu???n xem");
				} else {
					String maDichVu = tableModel.getValueAt(row, 0).toString();
					main.addPnlBody(new ThongTinChiTietDichVu_GUI(main, dichVu_DAO.getDichVuTheoMa(maDichVu), false),
							"Th??ng tin chi ti???t d???ch v???", 1, 0);
					setEmptyTable();
					// addRow(DichVu_DAO.getAllDichVu());
					filterDichVu();
				}
			}
		});

//		S??? ki???n n??t s???a th??ng tin d???ch v???
		btnSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnSua.isEnabled())
					return;
				int row = tbl.getSelectedRow();
				if (row == -1) {
					new JDialogCustom(main, components.jDialog.JDialogCustom.Type.warning).showMessage("Warning",
							"Vui l??ng ch???n d???ch v??? mu???n s???a");
				} else {
					String maDichVu = tableModel.getValueAt(row, 0).toString();
					main.addPnlBody(new ThongTinChiTietDichVu_GUI(main, dichVu_DAO.getDichVuTheoMa(maDichVu), true),
							"Th??ng tin chi ti???t d???ch v???", 1, 0);
				}
			}
		});

//		S??? ki???n n??t x??a d???ch v???
		btnXoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnXoa.isEnabled())
					return;

				int row = tbl.getSelectedRow();

				if (row != -1) {

					JDialogCustom jDialogCustom = new JDialogCustom(main);

					jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							boolean res = dichVu_DAO.xoaDichVu(tbl.getValueAt(row, 0).toString());
							if (res)
								new Notification(main, components.notification.Notification.Type.SUCCESS,
										"X??a d???ch v??? th??nh c??ng");
							else
								new Notification(main, components.notification.Notification.Type.ERROR,
										"X??a d???ch v??? th???t b???i");
							setEmptyTable();
							// addRow(DichVu_DAO.getAllDichVu());
							filterDichVu();
						};
					});

					jDialogCustom.getBtnCancel().addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							return;
						};
					});
					jDialogCustom.showMessage("Warning", "B???n ch???c ch???n mu???n x??a d???ch v??? n??y");

				} else {
					new JDialogCustom(main, components.jDialog.JDialogCustom.Type.warning).showMessage("Warning",
							"Vui l??ng ch???n d???ch v??? mu???n x??a");
				}
			}
		});

// s??? ki???n n??t kh??i ph???c d???ch v???
		btnKhoiPhuc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnKhoiPhuc.isEnabled())
					return;

				int row = tbl.getSelectedRow();

				if (row != -1) {

					JDialogCustom jDialogCustom = new JDialogCustom(main);

					jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							boolean res = dichVu_DAO.khoiPhucDichVu(tbl.getValueAt(row, 0).toString());
							if (res)
								new Notification(main, components.notification.Notification.Type.SUCCESS,
										"Kh??i ph???c d???ch v??? th??nh c??ng");
							else
								new Notification(main, components.notification.Notification.Type.ERROR,
										"Kh??i ph???c d???ch v??? th???t b???i");
							setEmptyTable();

							filterDichVuDaNgungKinhDoanh();
						};
					});

					jDialogCustom.getBtnCancel().addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							return;
						};
					});
					jDialogCustom.showMessage("Warning", "B???n ch???c ch???n mu???n kh??i ph???c d???ch v??? n??y");

				} else {
					new JDialogCustom(main, components.jDialog.JDialogCustom.Type.warning).showMessage("Warning",
							"Vui l??ng ch???n d???ch v??? mu???n kh??i ph???c");
				}
			}
		});
//		S??? ki???n JComboBox lo???i d???ch v???
		cmbLoaiDV.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (btnDanhSachXoa.isVisible()) {
						filterDichVu();
					} else
						filterDichVuDaNgungKinhDoanh();
				}
			}
		});

//		S??? ki???n JComboBox tr???ng th??i
		cmbSoLuong.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (btnDanhSachXoa.isVisible()) {
						filterDichVu();
					} else
						filterDichVuDaNgungKinhDoanh();
				}
			}
		});

//		S??? ki???n JTable
		tbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					setEnabledBtnActions();
				}
			}
		});

		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tbl.isEnabled()) {
					pnlControl.setTrangHienTai(tbl.getSelectedRow() + 1);
				}
			}
		});

	}

	private void addRow(DichVu dichVu) {

		tableModel.addRow(new String[] { dichVu.getMaDichVu(), dichVu.getTenDichVu(), dichVu.getDonViTinh(),
				String.valueOf(dichVu.getSoLuong()), dichVu.getLoaiDichVu().getTenLoaiDichVu(),
				Utils.formatTienTe(dichVu.getGiaMua()), Utils.formatTienTe(dichVu.getGiaBan()) });
	}

	private List<DichVu> addRow(List<DichVu> list) {
		list.forEach(dichVu -> addRow(dichVu));
		pnlControl.setTbl(tbl);
		return list;
	}

	private void filterDichVu() {
		String tenDichVu = txtSearch.getText();
		String tenLoaiDV = cmbLoaiDV.getSelectedItem().toString();
		String soLuong = cmbSoLuong.getSelectedItem().toString();

		if (tenLoaiDV.equals("Ph??n lo???i"))
			tenLoaiDV = "";
		if (soLuong.equals("S??? l?????ng"))
			soLuong = "";
		listDV = dichVu_DAO.filterDichVu(tenDichVu, tenLoaiDV, soLuong);
		setEmptyTable();
		addRow(listDV);
	}

	private void filterDichVuDaNgungKinhDoanh() {
		String tenDichVu = txtSearch.getText();
		String tenLoaiDV = cmbLoaiDV.getSelectedItem().toString();
		String soLuong = cmbSoLuong.getSelectedItem().toString();

		if (tenLoaiDV.equals("Ph??n lo???i"))
			tenLoaiDV = "";
		if (soLuong.equals("S??? l?????ng"))
			soLuong = "";
		List<DichVu> list = dichVu_DAO.filterDichVuNgungKinhDoanh(tenDichVu, tenLoaiDV, soLuong);
		setEmptyTable();
		addRow(list);
	}

	public void loadTable() {
		setEmptyTable();
		addRow(dichVu_DAO.getAllDichVu());
	}

	private void setEmptyTable() {
		while (tbl.getRowCount() > 0)
			tableModel.removeRow(0);
	}

	private void setEnabledBtnActions() {
		int row = tbl.getSelectedRow();

		if (row == -1 || !btnDanhSachXoa.isVisible()) {
			btnXem.setEnabled(false);
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);
		} else {
			btnXem.setEnabled(true);
			btnSua.setEnabled(true);
			btnXoa.setEnabled(true);
		}
	}

}

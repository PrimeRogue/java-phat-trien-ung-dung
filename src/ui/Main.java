package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.drawer.Drawer;
import components.drawer.DrawerController;
import components.jDialog.JDialogCustom;
import components.menu.EventMenuSelected;
import components.menu.Menu;
import components.menu.ModelMenuItem;
import dao.DonDatPhong_DAO;
import dao.NhanVien_DAO;
import entity.NhanVien;
import entity.NhanVien.ChucVu;
import entity.PanelUI;
import utils.StackPanel;
import utils.Utils;

public class Main extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Main _this;
	private Button btnBack;
	private DonDatPhong_DAO datPhong_DAO;
	private DrawerController drawer;
	private Menu footer;
	private JLabel lblTitle;
	private Menu menu;
	private NhanVien_DAO nhanVien_DAO;
	private JPanel pnlBody;
	private JPanel pnlContent;
	private JPanel pnlHeader;

	/**
	 * Create the frame.
	 */
	public Main() {
		_this = this;
		nhanVien_DAO = new NhanVien_DAO();
		datPhong_DAO = new DonDatPhong_DAO();
		JDialogCustom jDialogCustom = new JDialogCustom(_this);

		jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(1);
			}
		});

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setExtendedState(Frame.MAXIMIZED_BOTH);

		pnlContent = new JPanel();
		pnlContent.setForeground(Color.GRAY);
		pnlContent.setBackground(Utils.secondaryColor);
		setContentPane(pnlContent);
		pnlContent.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlContent.setLayout(null);

		JPanel pnlHeaderWrapper = new JPanel();
		pnlHeaderWrapper.setBounds(0, 0, Utils.getScreenWidth(), Utils.getHeaderHeight());
		pnlHeaderWrapper.setBackground(Utils.primaryColor);
		pnlContent.add(pnlHeaderWrapper);
		pnlHeaderWrapper.setLayout(null);

		pnlHeader = new JPanel();
		pnlHeader.setBackground(Utils.primaryColor);
		pnlHeader.setBounds(Utils.getLeft(1054), 0, 1054, Utils.getHeaderHeight());
		pnlHeaderWrapper.add(pnlHeader);
		pnlHeader.setLayout(null);

		Button btnMenu = new Button("|||");
		btnMenu.setFocusable(false);
		btnMenu.setBounds(-2, 14, 42, 42);
		btnMenu.setForeground(Utils.primaryColor);
		btnMenu.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		btnMenu.setBorder(BorderFactory.createEmptyBorder());
		btnMenu.setBackground(Color.WHITE);
		btnMenu.setBorderColor(Utils.primaryColor);
		btnMenu.setRadius(8);
		btnMenu.setFocusable(false);
		pnlHeader.add(btnMenu);

		lblTitle = new JLabel("TRANG CH???");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(53, 17, 948, 32);
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
		pnlHeader.add(lblTitle);

		btnBack = new Button();
		btnBack.setFocusable(false);
		btnBack.setIcon(Utils.getImageIcon("back 1.png"));
		btnBack.setColor(Utils.primaryColor);
		btnBack.setColorOver(Utils.primaryColor);
		btnBack.setColorClick(Utils.primaryColor);
		btnBack.setBorderColor(Utils.primaryColor);
		btnBack.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnBack.setBounds(992, 1, 62, 62);
		pnlHeader.add(btnBack);
//		End Default Layout

		pnlBody = new JPanel();
		pnlBody.setLayout(null);
		pnlBody.setBounds(0, Utils.getHeaderHeight(), Utils.getScreenWidth(), Utils.getBodyHeight());
		pnlContent.add(pnlBody);

		String maNhanVien = utils.NhanVien.getNhanVien().getMaNhanVien();
		NhanVien nhanVien = nhanVien_DAO.getNhanVienTheoMa(maNhanVien);
		utils.NhanVien.setNhanVien(nhanVien);
		ChucVu chucVu = utils.NhanVien.getNhanVien().getChucVu();

//		Code menu
		menu = new Menu();
		footer = new Menu();
		drawer = Drawer.newDrawer(this).addChild(menu).addFooter(footer).build();

		menu.setDrawer(drawer);
		if (nhanVien.getTrangThai().equals(NhanVien.TrangThai.DangLam)) {
			menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("homeIcon.png"), Utils.trangChuMenuItem));
			if (chucVu.equals(NhanVien.ChucVu.QuanLy)) {
				menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("businessman.png"), Utils.nhanVienMenuItem,
						Utils.quanLyNhanVienMenuItem, Utils.themNhanVienMenuItem));
				menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("user.png"), Utils.quanLyPhongMenuItem));
			}
			menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("users-avatar.png"), Utils.quanLyKhachHangMenuItem));
			menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("doorMenuItem.png"), Utils.quanLyDatPhongMenuItem));
			menu.addMenuItem(
					new ModelMenuItem(Utils.getImageIcon("doorMenuItem.png"), Utils.quanLyDatPhongTruocMenuItem));
			menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("bar-graph.png"), Utils.thongKeMenuItem,
					Utils.thongKeDoanhThuMenuItem, Utils.thongKeHoaDonMenuItem, Utils.thongKeKhachHangMenuItem));
			menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("user.png"), Utils.thongTinCaNhanMenuItem));
			menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("user.png"), Utils.quanLyDichVuMenuItem));
			menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("user.png"), Utils.troGiupItem));
			menu.setPreferredSize(new Dimension(getPreferredSize().width, 503));
		}

		footer.setDrawer(drawer);
		footer.addMenuItem(new ModelMenuItem(Utils.getImageIcon("logout.png"), Utils.dangXuatMenuItem));
		footer.addMenuItem(new ModelMenuItem(Utils.getImageIcon("power.png"), Utils.thoatMenuItem));
		footer.setPreferredSize(new Dimension(getPreferredSize().width, 70));

//		Show/Hide menu
		btnMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawer.show();
			}
		});

		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelUI pnl = StackPanel.pop();
				boolean isEmpty = StackPanel.empty();

				if (pnl.getTitle().equals(Utils.trangChuMenuItem))
					while (!StackPanel.empty())
						StackPanel.pop();

				StackPanel.push(pnl);

				if (isEmpty)
					jDialogCustom.showMessage("????ng ???ng d???ng", "B???n c?? mu???n ????ng ???ng d???ng kh??ng?");
				else
					backPanel();
			}
		});

//		S??? ki???n Window
		addWindowListener(new WindowAdapter() {
			private Thread clock;

			@Override
			public void windowActivated(WindowEvent e) {
				clock = new Thread() {
					@Override
					public void run() {
						for (;;) {
							try {
								if (datPhong_DAO.huyPhongDatTre())
									repaint();
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				};
				clock.start();
			}

			@SuppressWarnings("deprecation")
			@Override
			public void windowClosed(WindowEvent e) {
				clock.stop();
			}
		});

		xuLySuKienMenu();
		addPnlBody(new TrangChu_GUI(), "Trang ch???", 0, 0);
	}

	/**
	 * Thay ?????i ph???n container UI
	 *
	 * @param pnl          panel c???n thay ?????i
	 * @param title        title c???a trang
	 * @param index        index menu
	 * @param indexSubmenu index submenu
	 */
	public void addPnlBody(JPanel pnl, String title, int index, int indexSubmenu) {
		PanelUI panelUI = new PanelUI(pnl, title, index, indexSubmenu);
		menu.setSelectedMenu(index, indexSubmenu);
		addPnlBody(panelUI);
		StackPanel.push(panelUI);
	}

	public void addPnlBody(JPanel pnl, String title, int index, int indexSubmenu, boolean b) {
		if (!b)
			return;
		PanelUI panelUI = new PanelUI(pnl, title, index, indexSubmenu);
		menu.setSelectedMenu(index, indexSubmenu);
		addPnlBody(panelUI);
		StackPanel.push(panelUI);
	}

	/**
	 * Thay ?????i ph???n container UI
	 *
	 * @param panelUI panel UI
	 */
	public void addPnlBody(PanelUI panelUI) {
		pnlBody.removeAll();
		pnlBody.add(panelUI.getjPanel());
		pnlBody.repaint();
		setTitle(panelUI.getTitle());
	}

	public void backPanel() {
		StackPanel.pop();
		PanelUI panelUI = StackPanel.peek();
		if (panelUI.getTitle().equals("Trang ch???")) {
			while (!StackPanel.empty())
				StackPanel.pop();
			StackPanel.push(panelUI);
		}
		addPnlBody(panelUI);
		menu.setSelectedMenu(panelUI.getIndex(), panelUI.getIndexSubmenu());
		repaint();
	}

	public Menu getMenu() {
		return menu;
	}

	@Override
	public void repaint() {
		pnlBody.repaint();
		pnlBody.revalidate();
	}

	@Override
	public void setTitle(String title) {
		super.setTitle(title);
		lblTitle.setText(title.toUpperCase());
	}

	public void setWidthHeader(int width) {
		pnlHeader.setBounds(Utils.getLeft(width), 0, width, Utils.getHeaderHeight());
		btnBack.setBounds(width - 62, 1, 62, 62);
		repaint();
	}

	/**
	 * X??? l?? s??? ki???n khi nh???n v??o menu item
	 */
	private void xuLySuKienMenu() {
		menu.addEvent(new EventMenuSelected() {

			@Override
			public void menuSelected(int index, int indexSubMenu) {
				JPanel pnl;
				String title;

				List<String> list = menu.getMenu().get(index);
				String titleMenu = list.get(indexSubMenu);

				switch (titleMenu) {
				case Utils.quanLyNhanVienMenuItem:
					title = "Qu???n l?? nh??n vi??n";
					pnl = new QuanLyNhanVien_GUI(_this);
					break;
				case Utils.themNhanVienMenuItem:
					title = "Th??m nh??n vi??n";
					pnl = new ThemNhanVien_GUI(_this);
					break;
				case Utils.quanLyKhachHangMenuItem:
					title = "Qu???n l?? kh??ch h??ng";
					pnl = new QuanLyKhachHang_GUI(_this);
					break;
				case Utils.quanLyDatPhongMenuItem:
					title = "Qu???n l?? ?????t ph??ng";
					pnl = new QuanLyDatPhong_GUI(_this);
					break;
				case Utils.quanLyDatPhongTruocMenuItem:
					title = "Qu???n l?? ?????t ph??ng tr?????c";
					pnl = new QuanLyPhieuDatPhongTruoc_GUI(_this);
					break;
				case Utils.thongKeDoanhThuMenuItem:
					title = "Th???ng k?? doanh thu";
					pnl = new ThongKeDoanhThu_GUI();
					break;
				case Utils.thongKeHoaDonMenuItem:
					title = "Th???ng k?? h??a ????n";
					pnl = new ThongKeHoaDon_GUI(_this);
					break;
				case Utils.thongKeKhachHangMenuItem:
					title = "Th???ng k?? kh??ch h??ng";
					pnl = new ThongKeKhachHang_GUI();
					break;
				case Utils.thongTinCaNhanMenuItem:
					title = "Th??ng tin c?? nh??n";
					pnl = new ThongTinCaNhan_GUI(_this);
					break;
				case Utils.quanLyDichVuMenuItem:
					title = "Qu???n l?? d???ch v???";
					pnl = new QuanLyDichVu_GUI(_this);
					break;
				case Utils.quanLyPhongMenuItem:
					title = "Qu???n l?? ph??ng";
					pnl = new QuanLyPhong_GUI(_this);
					break;
				case Utils.troGiupItem:
					Utils.openFile(_this.getClass().getResource("/PTUD_HDSD.pdf").toString());
					return;
				default:
					title = "Trang ch???";
					pnl = new TrangChu_GUI();
				}

				addPnlBody(pnl, title, index, indexSubMenu);
			}
		});

		footer.addEvent(new EventMenuSelected() {

			@Override
			public void menuSelected(int index, int indexSubMenu) {
				footer.clearSelected();
				JDialogCustom jDialogCustom = new JDialogCustom(_this);
				if (index == 1 && indexSubMenu == 0) {
					jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							System.exit(0);
						}
					});

					jDialogCustom.showMessage("Tho??t ???ng d???ng", "B???n c?? ch???c ch???n mu???n tho??t ???ng d???ng kh??ng?");
				} else {
					jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							utils.NhanVien.setNhanVien(null);
							new DangNhap_GUI().setVisible(true);
							setVisible(false);
						}
					});

					jDialogCustom.showMessage("????ng xu???t", "B???n c?? ch???c ch???n mu???n ????ng xu???t kh??ng?");
				}
			}
		});
	}
}

package entity;

import java.util.Objects;

public class TaiKhoan {
	private String matKhau;
	private NhanVien nhanVien;

	public TaiKhoan() {
		super();
	}

	public TaiKhoan(NhanVien nhanVien) {
		super();
		this.nhanVien = nhanVien;
	}

	public TaiKhoan(NhanVien nhanVien, String matKhau) {
		super();
		this.nhanVien = nhanVien;
		this.matKhau = matKhau;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(nhanVien, other.nhanVien);
	}

	public String getMatKhau() {
		return matKhau;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nhanVien);
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	@Override
	public String toString() {
		return "TaiKhoan [nhanVien=" + nhanVien + ", matKhau=" + matKhau + "]";
	}
}

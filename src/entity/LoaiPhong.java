package entity;

import java.util.Objects;

public class LoaiPhong {
	private String maLoai;
	private String tenLoai;

	public LoaiPhong() {
		super();
	}

	public LoaiPhong(String maLoai) {
		super();
		this.maLoai = maLoai;
	}

	public LoaiPhong(String maLoai, String tenLoai) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiPhong other = (LoaiPhong) obj;
		return Objects.equals(maLoai, other.maLoai);
	}

	public String getMaLoai() {
		return maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoai);
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	@Override
	public String toString() {
		return "LoaiPhong [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
	}

}

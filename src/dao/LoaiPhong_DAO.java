package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LoaiPhong;

public class LoaiPhong_DAO {
	/**
	 * Get tất cả các loại phòng
	 * 
	 * @return
	 */
	public List<LoaiPhong> getAllLoaiPhong() {
		List<LoaiPhong> list = new ArrayList<>();

		try {
			Statement statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM LoaiPhong");

			while (resultSet.next())
				list.add(getLoaiPhong(resultSet));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Get loại phòng từ resultSet
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private LoaiPhong getLoaiPhong(ResultSet resultSet) throws SQLException {
		String maLoai = resultSet.getString("maLoai");
		String tenLoai = resultSet.getString("tenLoai");
		return new LoaiPhong(maLoai, tenLoai);
	}

	/**
	 * Get loại phòng theo mã loại
	 * 
	 * @param maLoai
	 * @return
	 */
	public LoaiPhong getLoaiPhong(String maLoai) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM LoaiPhong WHERE maLoai = ?");
			preparedStatement.setString(1, maLoai);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return getLoaiPhong(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public LoaiPhong getLoaiPhongTheoTenLoai(String tenLoai) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM LoaiPhong WHERE tenLoai = ?");
			preparedStatement.setString(1, tenLoai);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return getLoaiPhong(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}

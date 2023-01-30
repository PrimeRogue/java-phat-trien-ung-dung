package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LoaiDichVu;

public class LoaiDichVu_DAO {
	public List<LoaiDichVu> getAllLoaiDichVu() {
		List<LoaiDichVu> list = new ArrayList<>();
		Statement statement;
		try {
			statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM LoaiDichVu");
			LoaiDichVu loaiDichVu;
			while (resultSet.next()) {
				loaiDichVu = getLoaiDichVu(resultSet);
				list.add(loaiDichVu);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private LoaiDichVu getLoaiDichVu(ResultSet resultSet) throws SQLException {
		String maLoaiDV = resultSet.getString(1);
		String tenLoaiDV = resultSet.getString(2);

		return new LoaiDichVu(maLoaiDV, tenLoaiDV);
	}

	public LoaiDichVu getLoaiDichVu(String maLoaiDV) {
		LoaiDichVu loaiDichVu = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM LoaiDichVu WHERE  maLoaiDichVu = ?");
			preparedStatement.setString(1, maLoaiDV);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				loaiDichVu = getLoaiDichVu(resultSet);
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loaiDichVu;
	}

	public LoaiDichVu getLoaiDichVuTheoTen(String tenLoaiDichVu) {
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM LoaiDichVu WHERE tenLoaiDichVu = ?");
			preparedStatement.setString(1, tenLoaiDichVu);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return getLoaiDichVu(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}

package model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class ImagesModel {
	private Long id;
	private Long product_Id;
	private String type;
	private Blob blob;
	private InputStream inputImage;

	public Long getProduct_Id() {
		return product_Id;
	}

	public void setProduct_Id(Long product_Id) {
		this.product_Id = product_Id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBase64Image() {
		String base64Image = null;
		try {
			InputStream inputStream;
			inputStream = this.blob.getBinaryStream();

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			base64Image = Base64.getEncoder().encodeToString(imageBytes);

			inputStream.close();
			outputStream.close();
		} catch (IOException | SQLException e) {
			return "";
		}

		return base64Image;
	}

	public void setBlod(Blob blob) {
		this.blob = blob;
	}

	public InputStream getInputImage() {
		return inputImage;
	}

	public void setInputImage(InputStream inputImage) {
		this.inputImage = inputImage;
	}

}

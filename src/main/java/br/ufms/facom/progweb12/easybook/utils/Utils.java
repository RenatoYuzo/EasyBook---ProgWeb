package br.ufms.facom.progweb12.easybook.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

public class Utils {

	public static void byteToFileTemp(byte[] pdfByte, String type) {
		try {
			Path tempFile = Files.createTempFile("tempFile", "." + type);
			Files.write(tempFile, pdfByte);

			StringBuilder caminho = new StringBuilder();
			caminho.append(tempFile.getRoot());
			for (int i = 0; i < tempFile.getNameCount() - 1; i++)
				if (i == 0)
					caminho.append(tempFile.getName(i));
				else
					caminho.append("\\" + tempFile.getName(i));

			String contentType;
			if (type.equals("png")) {
				contentType = "image/png";
			} else if (type.equals("jpg") || type.equals("jpeg")) {
				contentType = "image/jpeg";
			} else if (type.equals("gif")) {
				contentType = "image/gif";
			} else if (type.equals("pdf")) {
				contentType = "application/pdf";
			} else {
				contentType = "application/octet-stream";
			}

			openFileDocument(caminho.toString(), tempFile.getFileName().toString(), contentType);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void openFileDocument(String caminhoArquivo, String nomeArquivo, String contentType) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

			File file = new File(caminhoArquivo, nomeArquivo);
			InputStream input = new BufferedInputStream(new FileInputStream(file), 10240);

			response.setContentType(contentType);
			response.addHeader("Content-disposition", "inline; filename=\"" + nomeArquivo + "\"");
			OutputStream output = new BufferedOutputStream(response.getOutputStream(), 10240);

			byte[] buffer = new byte[10240];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			output.flush();

			input.close();
			output.close();
			context.renderResponse();
			context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String ddMMyyyyHHmm(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return sdf.format(data);
	}

}

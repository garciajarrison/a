package co.com.avaluo.common.bo;

import java.io.Serializable;
import java.util.List;

import com.itextpdf.text.Font;

public class ReporteLista implements Serializable{

	private static final long serialVersionUID = 1L;
	private String contenido;
	private List<String> contenidos;
	private Font fontTitulo;
	private Font fontContenido;
	
	public ReporteLista(String contenido) {
		this.contenido = contenido;
	}
	
	public ReporteLista(String contenido, Font fontTitulo, List<String> contenidos, Font fontContenido) {
		this.contenido = contenido;
		this.contenidos = contenidos;
		this.fontContenido = fontContenido;
		this.fontTitulo = fontTitulo;
	}

	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public List<String> getContenidos() {
		return contenidos;
	}
	public void setContenidos(List<String> contenidos) {
		this.contenidos = contenidos;
	}
	public Font getFontTitulo() {
		return fontTitulo;
	}
	public void setFontTitulo(Font fontTitulo) {
		this.fontTitulo = fontTitulo;
	}
	public Font getFontContenido() {
		return fontContenido;
	}
	public void setFontContenido(Font fontContenido) {
		this.fontContenido = fontContenido;
	}
	
}

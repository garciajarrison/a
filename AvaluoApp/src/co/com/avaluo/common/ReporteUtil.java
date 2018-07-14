package co.com.avaluo.common;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

public class ReporteUtil {
	
	public ReporteUtil(){}

	public Paragraph addTexto(String texto, Font fuente) {
		return addTexto(texto, fuente, 0);
	}
	
	public Paragraph addTexto(String texto, Font fuente, int align) {
		Paragraph retorno = new Paragraph(texto, fuente);
		retorno.setAlignment(align);
		return retorno;
	}

}

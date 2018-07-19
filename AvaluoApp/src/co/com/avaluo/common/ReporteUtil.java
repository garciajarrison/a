package co.com.avaluo.common;

import java.util.List;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class ReporteUtil {
	
	public ReporteUtil(){}

	public Paragraph addTexto(String texto, Font fuente, int espacios) {
		return addTexto(texto, fuente, espacios, 0);
	}
	
	public Paragraph addTexto(String texto, Font fuente, int espacios, int align) {
		Paragraph retorno = addEmptyLine(espacios);
		retorno.add(new Paragraph(texto, fuente));
		retorno.setAlignment(align);
		return retorno;
	}
	
	public Paragraph addEmptyLine(int number) {
		Paragraph paragraph = new Paragraph();
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
		return paragraph;
	}
	
	public PdfPTable createdTable(List<String> TitulosTabla, List<String> contenido) throws BadElementException {
		
		PdfPTable table = new PdfPTable(TitulosTabla.size());
		for(String titulo : TitulosTabla) {
			table.addCell(this.addColumnaAlign(titulo, Element.ALIGN_CENTER));
		}
		
		for(String conten : contenido) {
			table.addCell(conten);
		}

		return table;
	}
	
	public PdfPCell addColumnaAlign(String texto, int align) {
		PdfPCell c = new PdfPCell(new Phrase(texto));
		c.setHorizontalAlignment(align);
		return c;
	}
	
	public Chapter addBinneta(String titulo, List<String> contenidos, Font fontTitulo, Font FontContenido) {
		Anchor binneta = new Anchor(titulo, fontTitulo);
		binneta.setName(titulo);
		
		// Second parameter is the number of the chapter
		Chapter principal = new Chapter(new Paragraph(binneta), contenidos.size());
		
		Paragraph subContenido;
		Section subSection;
		for(String contenido: contenidos) {
			subContenido = new Paragraph(contenido, FontContenido);
			subSection = principal.addSection(subContenido);
		}
		
		return principal;
	}

}

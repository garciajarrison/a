package co.com.avaluo.common;


import java.util.List;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import co.com.avaluo.common.bo.ReporteLista;

public class ReporteUtil {
	
	public ReporteUtil(){}

	public Paragraph addTexto(String texto, Font fuente, int espacios) {
		return addTexto(texto, fuente, 0, espacios);
	}
	
	public Paragraph addTexto(String texto, Font fuente, int align, int espacios) {
		Paragraph parrafo = new Paragraph();
		parrafo.add(addEmptyLine(espacios));
		Paragraph contenido = new Paragraph(texto, fuente);
		contenido.setAlignment(align);
		parrafo.add(contenido);
		return parrafo;
	}
	
	public Paragraph addTextoNegritaTexto(String textoNegrita, String texto, Font bold, Font normal, int align, int espacios) {
		Paragraph parrafo = new Paragraph();
		parrafo.add(addEmptyLine(espacios));
		Paragraph contenido = new Paragraph();
		contenido.setAlignment(align);
		contenido.add(new Chunk(textoNegrita+" ", bold));
		contenido.add(new Chunk(texto, normal));
		parrafo.add(contenido);
        return parrafo;
	}
	
	public Paragraph addEmptyLine(int number) {
		Paragraph parrafo = new Paragraph();
		for (int i = 0; i < number; i++) {
			parrafo.add(new Paragraph(" "));
		}
		return parrafo;
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

	public Element addBinneta(List<ReporteLista> binnetas, int inicioBineta) {
		
		com.itextpdf.text.List list = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
		list.setFirst(inicioBineta);
		com.itextpdf.text.List listContenido;
		com.itextpdf.text.ListItem itemContenido;
		
		for(ReporteLista binneta: binnetas) {
			
			list.add(binneta.getContenido());
			listContenido = new com.itextpdf.text.List();
			listContenido.setListSymbol("\u2022");
			
			for(String contenido: binneta.getContenidos()) {
				itemContenido = new com.itextpdf.text.ListItem(contenido, binneta.getFontContenido());
				itemContenido.setAlignment(Element.ALIGN_JUSTIFIED);
				listContenido.add(itemContenido);
			}
			listContenido.setIndentationLeft(30);
			list.add(listContenido);
		}
		
		return list;
	}
	

}

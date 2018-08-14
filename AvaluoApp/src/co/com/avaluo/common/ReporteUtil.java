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
		parrafo.setIndentationLeft(25);
		parrafo.setIndentationRight(25);
		Paragraph contenido = new Paragraph(texto, fuente);
		contenido.setAlignment(align);
		contenido.setIndentationLeft(25);
		contenido.setIndentationRight(25);
		parrafo.add(contenido);
		return parrafo;
	}
	
	public Paragraph addTextoNegritaTexto(String texto1, String texto2, Font font1, Font font2, int align, int espacios) {
		Paragraph parrafo = new Paragraph();
		parrafo.add(addEmptyLine(espacios));
		parrafo.setIndentationLeft(25);
		parrafo.setIndentationRight(25);
		Paragraph contenido = new Paragraph();
		contenido.setAlignment(align);
		contenido.setIndentationLeft(25);
		contenido.setIndentationRight(25);
		contenido.add(new Chunk(texto1+" ", font1));
		contenido.add(new Chunk(texto2, font2));
		parrafo.add(contenido);
        return parrafo;
	}
	
	public Element addTextoNegritaTexto(String texto1, String texto2, String texto3, Font font1, Font font2,
													Font font3, int align, int espacios) {
		Paragraph parrafo = new Paragraph();
		parrafo.add(addEmptyLine(espacios));
		parrafo.setIndentationLeft(25);
		parrafo.setIndentationRight(25);
		Paragraph contenido = new Paragraph();
		contenido.setAlignment(align);
		contenido.setIndentationLeft(25);
		contenido.setIndentationRight(25);
		contenido.add(new Chunk(texto1+" ", font1));
		contenido.add(new Chunk(texto2+" ", font2));
		contenido.add(new Chunk(texto3, font3));
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
		table.setWidthPercentage(95);
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
			
			list.add(new com.itextpdf.text.ListItem(30, binneta.getContenido(), binneta.getFontTitulo()));
			if (binneta.getContenidos() != null) {
				listContenido = new com.itextpdf.text.List();
				listContenido.setListSymbol("\u2022");
				
				for(String contenido: binneta.getContenidos()) {
					itemContenido = new com.itextpdf.text.ListItem(" " + contenido, binneta.getFontContenido());
					itemContenido.setAlignment(Element.ALIGN_JUSTIFIED);
					itemContenido.setLeading(25f);
					itemContenido.setIndentationLeft(30);
					itemContenido.setIndentationRight(25);
				//	Paragraph parrafo = new Paragraph();
				//	parrafo.SetLeading(fixed, multiplied);
					listContenido.add(itemContenido);
				}
				listContenido.setIndentationLeft(45);
				list.add(listContenido);
			}
		}
		
		return list;
	}

}
package co.com.avaluo.controller.reporte;

import java.io.FileOutputStream;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import co.com.avaluo.common.ReporteUtil;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Cotizacion;

public class RCotizacion {
	private static final String FILE = "c:/temp/FirstPdf.pdf";
	private static final Font NORMAL_14 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
	private static final Font NORMAL_12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	private static final Font BOLD_14 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	private Util util;
	private ReporteUtil reporteUtil;
	
	public RCotizacion() {
		util = Util.getInstance();
		reporteUtil = new ReporteUtil();
		
	}
	
	public void generarReporte(Cotizacion cotizacion) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addMetaData(document);
			addTitlePage(document, cotizacion);
			addContent(document, cotizacion);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addMetaData(Document document) {
		document.addTitle(util.getMessage("reporte.cotizacion.titulo"));
		document.addAuthor(util.getMessage("app"));
		document.addCreator("Jarrison Andrey Garcia Paniagua");
	}
	
	private void addTitlePage(Document document, Cotizacion cotizacion) throws DocumentException {

		boolean urbano = true;
		boolean rural = true;
		
		Paragraph preface = new Paragraph();
		//Anexo de ciudad y fecha (debe obtenerse de la cotizacion)
		preface.add(reporteUtil.addTexto("Ciudad, Fecha correspondencia", NORMAL_14));
		addEmptyLine(preface, 2);
		
		preface.add(reporteUtil.addTexto("Señor(a)", NORMAL_14));
		preface.add(reporteUtil.addTexto("<NOMBRE DEL PROPIETARIO>", NORMAL_14));
		preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.solicitante.contratante"), NORMAL_12));
		addEmptyLine(preface, 3);
		preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido1"), NORMAL_12, Element.ALIGN_JUSTIFIED));
		addEmptyLine(preface, 1);
		preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido2","<MOTIVO DEL AVALUO>."), NORMAL_12));
		
		if(urbano) {
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido3"), BOLD_14));
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido4","1234"), NORMAL_12));
		}
		
		addEmptyLine(preface, 1);
		preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido5"), BOLD_14));
		addEmptyLine(preface, 1);
		preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido6"), NORMAL_12));
		
		if(urbano) {
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido7"), BOLD_14));
			//preface.setAlignment(Element.CENTER);
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido8"), BOLD_14));
		}
		
		if(rural) {
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido9"), NORMAL_12));
		}
		
		if(urbano) {
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido10"), BOLD_14));
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido11"), NORMAL_12));
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido12"), NORMAL_12));
		}
		
		if(rural) {
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido13"), BOLD_14));
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido14"), NORMAL_12));
		}
		
		if(urbano) {
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido15"), BOLD_14));
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido16"), NORMAL_12));
		}
		
		if(rural) {
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido17"), BOLD_14));
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido18"), NORMAL_12));
		}
		
		if(urbano) {
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido19"), BOLD_14));
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido20"), NORMAL_12));
		}
		
		if(rural) {
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido21"), BOLD_14));
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido22"), NORMAL_12));
		}
		
		if(urbano) {
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido23"), BOLD_14));
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido24"), NORMAL_12));
		}
		
		if(rural) {
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido25"), BOLD_14));
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido26"), NORMAL_12));
		}
		
		if(urbano) {
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido27"), BOLD_14));
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido28"), NORMAL_12));
		}
		
		if(rural) {
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido29"), BOLD_14));
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido30"), NORMAL_12));
		}
		
		if(urbano) {
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido31"), BOLD_14));
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido32"), NORMAL_12));
		}
		
		if(rural) {
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido33"), BOLD_14));
			addEmptyLine(preface, 1);
			preface.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido34"), NORMAL_12));
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		addEmptyLine(preface, 8);

		preface.add(new Paragraph(
				"This document is a preliminary version and not subject to your license agreement or any other agreement with vogella.com ;-).",
				NORMAL_12));

		document.add(preface);
		// Start a new page
		document.newPage();
	}

	private static void addContent(Document document, Cotizacion cotizacion) throws DocumentException {
		Anchor anchor = new Anchor("First Chapter", NORMAL_14);
		anchor.setName("First Chapter");

		// Second parameter is the number of the chapter
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		Paragraph subPara = new Paragraph("Subcategory 1", NORMAL_12);
		Section subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("Hello"));

		subPara = new Paragraph("Subcategory 2", NORMAL_12);
		subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("Paragraph 1"));
		subCatPart.add(new Paragraph("Paragraph 2"));
		subCatPart.add(new Paragraph("Paragraph 3"));

		// add a list
		createList(subCatPart);
		Paragraph paragraph = new Paragraph();
		addEmptyLine(paragraph, 5);
		subCatPart.add(paragraph);

		// add a table
		createTable(subCatPart);

		// now add all this to the document
		document.add(catPart);

		// Next section
		anchor = new Anchor("Second Chapter", NORMAL_14);
		anchor.setName("Second Chapter");

		// Second parameter is the number of the chapter
		catPart = new Chapter(new Paragraph(anchor), 1);

		subPara = new Paragraph("Subcategory", NORMAL_12);
		subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("This is a very important message"));

		// now add all this to the document
		document.add(catPart);

	}

	private static void createTable(Section subCatPart) throws BadElementException {
		PdfPTable table = new PdfPTable(3);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Table Header 2"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Table Header 3"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		table.addCell("1.0");
		table.addCell("1.1");
		table.addCell("1.2");
		table.addCell("2.1");
		table.addCell("2.2");
		table.addCell("2.3");

		subCatPart.add(table);

	}

	private static void createList(Section subCatPart) {
		List list = new List(true, false, 10);
		list.add(new ListItem("First point"));
		list.add(new ListItem("Second point"));
		list.add(new ListItem("Third point"));
		subCatPart.add(list);
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}

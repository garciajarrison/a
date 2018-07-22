package co.com.avaluo.controller.reporte;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
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

	public void exportFile(ByteArrayOutputStream docExport) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline=filename=file.pdf");
                 
        try {
                response.getOutputStream().write(docExport.toByteArray());
                response.getOutputStream().flush();
                response.getOutputStream().close();
                context.responseComplete();
        } catch (IOException e) {
                e.printStackTrace();
        }
}

	public ByteArrayOutputStream generarReporte(Cotizacion cotizacion) {
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			
			Document document = new Document();
			PdfWriter.getInstance(document, byteArrayOutputStream);

			// setting page size, margins and mirrered margins
			/*
			 * document.setPageSize(PageSize.A5); document.setMargins(36, 72, 108, 180);
			 * document.setMarginMirroringTopBottom(true);
			 */

			document.open();
			addMetaData(document);
			addContenido(document, cotizacion);
			document.close();
			
			//Exportar
			//exportFile(byteArrayOutputStream);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteArrayOutputStream;
	}

	private void addMetaData(Document document) {
		document.addTitle(util.getMessage("reporte.cotizacion.titulo"));
		document.addAuthor(util.getMessage("app"));
		document.addCreator("Jarrison Andrey Garcia Paniagua");
	}

	private void addContenido(Document document, Cotizacion cotizacion) throws DocumentException {

		boolean urbano = true;
		boolean rural = true;

		// Anexo de ciudad y fecha (debe obtenerse de la cotizacion)
		document.add(reporteUtil.addTexto("Ciudad, Fecha correspondencia", NORMAL_14, 0));

		document.add(reporteUtil.addTexto("Señor(a)", NORMAL_14, 3));
		document.add(reporteUtil.addTexto("<NOMBRE DEL PROPIETARIO>", NORMAL_14, 0));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.solicitante.contratante"), NORMAL_12, 0));

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido1"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 3));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido2", "<MOTIVO DEL AVALUO>."),
				NORMAL_12, 1));

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido3"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido4", "1234"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido5"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido6"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido7"), BOLD_14,
					Element.ALIGN_CENTER, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido8"), BOLD_14, 1));
		}

		if (rural) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido9"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido10"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido11"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido12"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (rural) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido13"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido14"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido15"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido16"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (rural) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido17"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido18"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido19"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido20"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (rural) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido21"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido22"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido23"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido24"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (rural) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido25"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido26"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido27"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido28"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (rural) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido29"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido30"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido31"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido32"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (rural) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido33"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido34"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido35"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido36"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (rural) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido37"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido38"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido39"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido40"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (rural) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido41"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido42"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido43"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido44"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (rural) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido43"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido44"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido45"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido46"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (rural) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido47"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido48"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido49"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido50"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		if (rural) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido51"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido52"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido53"), BOLD_14,
				Element.ALIGN_CENTER, 1));

		// Agregamos biñeta
		List<String> contenido = new ArrayList<String>();
		contenido.add(util.getMessage("reporte.cotizacion.contenido55"));
		contenido.add(util.getMessage("reporte.cotizacion.contenido56"));
		document.add(reporteUtil.addBinneta(util.getMessage("reporte.cotizacion.contenido54"), contenido, BOLD_14,
				NORMAL_12));
		// Agregamos biñeta
		contenido = new ArrayList<String>();
		contenido.add(util.getMessage("reporte.cotizacion.contenido58"));
		document.add(reporteUtil.addBinneta(util.getMessage("reporte.cotizacion.contenido57"), contenido, BOLD_14,
				NORMAL_12));

		// Tabla REQUERIMIENTOS: AVALUOS COMERCIAL DE INMUEBLES
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido59"), BOLD_14,
				Element.ALIGN_CENTER, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido60"), BOLD_14,
				Element.ALIGN_CENTER, 1));
		document.add(reporteUtil.addEmptyLine(1));

		List<String> titulosTabla = new ArrayList<String>();
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.avaluos.titulo1"));
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.avaluos.titulo2"));
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.avaluos.titulo3"));
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.avaluos.titulo4"));
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.avaluos.titulo5"));
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.avaluos.titulo6"));

		contenido = new ArrayList<String>();
		contenido.add("A");
		contenido.add("B");
		contenido.add("C");
		contenido.add("D");
		contenido.add("E");
		contenido.add("F");
		contenido.add("A");
		contenido.add("B");
		contenido.add("C");
		contenido.add("D");
		contenido.add("E");
		contenido.add("F");

		// Agregamos la tabla
		document.add(reporteUtil.createdTable(titulosTabla, contenido));

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido61"), NORMAL_12, 1));

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido62"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido63"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		// if(azul){
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido64"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido65"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		// }
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido66"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido67"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido68"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido69"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido70"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido72"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido73"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido74"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido75"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido76"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido77"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido78"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido79"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido80"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido81"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido82"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido83"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido84"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido85"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido86"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido87"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido88"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido89"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido90"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido91"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido92"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido93"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido94"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido95"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido96"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido97"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido98"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido99"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido100"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido101"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido102"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido103"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido104"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido105"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido106"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido107"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido108"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido109"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido110"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));

		// Disposiciones generales
		// if(azul){
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido111"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido112"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido113"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido114"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido115"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido116"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido117"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido118"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido119"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido120"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido121"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido122"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido123"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido124"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido125"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido126"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido127"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido128"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		// }

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido129"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido130"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido131"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido132"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido133"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido134"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido135"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido136"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido137"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido138"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido139"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido140"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido141"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido142"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		// if(azul)
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido143"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		// if(verde)
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido144"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		// if(azul){
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido145"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido146"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido147"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido148"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		// }

		// if(azul){
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido149"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido150"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido151"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido152"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido153"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido154"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido155"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		// }

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido156"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido157"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido158"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido159"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido160"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido161"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido162"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido163"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido164"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido165"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido166"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido167"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido168"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido169"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido170"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido171"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido172"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido173"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido174"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido175"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido176"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido177"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido178"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido179"), BOLD_14, 1));
		document.add(reporteUtil.addEmptyLine(1));

		// Tabla categorias de avaluos
		titulosTabla = new ArrayList<String>();
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.titulo1"));
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.titulo2"));
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.titulo3"));

		contenido = new ArrayList<String>();
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido11"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido12"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido13"));

		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido21"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido22"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido23"));

		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido31"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido32"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido33"));

		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido41"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido42"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido43"));

		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido51"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido52"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido53"));

		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido61"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido62"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido63"));

		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido71"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido72"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido73"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido74"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido75"));

		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido81"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido82"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido83"));

		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido91"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido92"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido93"));

		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido101"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido102"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido103"));

		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido111"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido112"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido113"));

		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido121"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido122"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido123"));

		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido131"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido132"));
		contenido.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido133"));

		// Agregamos la tabla de categorias de avaluos
		document.add(reporteUtil.createdTable(titulosTabla, contenido));

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido180"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido181"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addEmptyLine(1));
		// Tabla cotizaciones

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido182"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido183"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido184"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido185"), NORMAL_12,
				Element.ALIGN_JUSTIFIED, 1));
		document.add(reporteUtil.addEmptyLine(2));

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido186"), NORMAL_12, 1));
		document.add(reporteUtil.addTexto(
				util.getMessage("reporte.cotizacion.contenido187", "CC", "123456465", "Caldas"), NORMAL_12, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido188", "RAA AVAL-99999999"),
				NORMAL_12, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido189"), NORMAL_12, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido190"), NORMAL_12, 1));

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido191"), BOLD_14, 1));

		// tabla bibliografia

	}

}

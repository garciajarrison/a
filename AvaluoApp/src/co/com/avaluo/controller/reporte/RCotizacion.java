package co.com.avaluo.controller.reporte;

import java.io.ByteArrayOutputStream;
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
import co.com.avaluo.common.bo.ReporteLista;
import co.com.avaluo.model.entity.Cotizacion;

public class RCotizacion {

	private static final Font NORMAL_14 = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
	private static final Font NORMAL_12 = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
	private static final Font BOLD_14 = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
	private static final Font BOLD_12 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
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

		document.add(reporteUtil.addTexto("Señor(a)", NORMAL_14, 4));
		document.add(reporteUtil.addTexto(cotizacion.getUsuarioByClienteId().getNombre(), NORMAL_14, 0));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.solicitante.contratante"), NORMAL_12, 0));

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido1"), NORMAL_12, Element.ALIGN_JUSTIFIED, 4));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido2", "<MOTIVO DEL AVALUO>."),NORMAL_12, 1));

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido3"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido4", cotizacion.getId()), NORMAL_12, Element.ALIGN_JUSTIFIED, 1));
		}

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido5"), BOLD_14, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido6"), NORMAL_12, Element.ALIGN_JUSTIFIED, 1));

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido7"), BOLD_14, Element.ALIGN_CENTER, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido8"), BOLD_14, 1));
		}

		if (rural) {
			document.add(reporteUtil.addTextoNegritaTexto(util.getMessage("reporte.cotizacion.contenido9.negrita"), 
														util.getMessage("reporte.cotizacion.contenido9"), 
														BOLD_12, NORMAL_12, Element.ALIGN_JUSTIFIED, 1));
		}

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido10"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido11"), NORMAL_12, Element.ALIGN_JUSTIFIED, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido12"), NORMAL_12, Element.ALIGN_JUSTIFIED, 1));
		}

		if (rural) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido13"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido14"), NORMAL_12, Element.ALIGN_JUSTIFIED, 1));
		}

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido15"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido16"), NORMAL_12, Element.ALIGN_JUSTIFIED, 1));
		}

		if (rural) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido17"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido18"), NORMAL_12, Element.ALIGN_JUSTIFIED, 1));
		}

		if (urbano) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido19"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido20"), NORMAL_12, Element.ALIGN_JUSTIFIED, 1));
		}

		if (rural) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido21"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido22"), NORMAL_12, Element.ALIGN_JUSTIFIED, 1));
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
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido37"), BOLD_14, 2));
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
			document.add(reporteUtil.addTextoNegritaTexto(util.getMessage("reporte.cotizacion.contenido49"), 
					util.getMessage("reporte.cotizacion.contenido49.12"), 
					BOLD_14, BOLD_12, Element.ALIGN_JUSTIFIED, 1));
			
			document.add(reporteUtil.addTextoNegritaTexto(util.getMessage("reporte.cotizacion.contenido50"), 
					util.getMessage("reporte.cotizacion.contenido50.negrita"), 
					util.getMessage("reporte.cotizacion.contenido50.1"), 
					NORMAL_12, BOLD_12, NORMAL_12,  Element.ALIGN_JUSTIFIED, 1));
		}

		if (rural) {
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido51"), BOLD_14, 1));
			document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido52"), NORMAL_12,
					Element.ALIGN_JUSTIFIED, 1));
		}

		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido53"), BOLD_14, Element.ALIGN_JUSTIFIED, 1));
		
		List<ReporteLista> binnetas = new ArrayList<>();
		String titulo;
		List<String> contenidos = new ArrayList<>();
		//Anexo de biñetas
		titulo = util.getMessage("reporte.cotizacion.contenido.titulo.bineta54");
		contenidos.add(util.getMessage("reporte.cotizacion.contenido.contenido.bineta55"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido.contenido.bineta56"));
		binnetas.add(new ReporteLista(titulo, BOLD_14, contenidos, NORMAL_12));
		contenidos = new ArrayList<>();
		titulo = util.getMessage("reporte.cotizacion.contenido.titulo.bineta57");
		contenidos.add(util.getMessage("reporte.cotizacion.contenido.contenido.bineta58"));
		
		binnetas.add(new ReporteLista(titulo, BOLD_14, contenidos, NORMAL_12));
		document.add(reporteUtil.addBinneta(binnetas, 1));
		
		//Anexo de tabla
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido.contenido.bineta59"), BOLD_14, Element.ALIGN_CENTER, 1));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido.contenido.bineta60"), BOLD_14, Element.ALIGN_CENTER, 1));
		document.add(reporteUtil.addEmptyLine(1));
		List<String> titulosTabla = new ArrayList<String>();
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.avaluos.titulo1"));
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.avaluos.titulo2"));
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.avaluos.titulo3"));
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.avaluos.titulo4"));
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.avaluos.titulo5"));
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.avaluos.titulo6"));

		List<String> contenidoTabla = new ArrayList<String>();
		contenidoTabla.add("A");
		contenidoTabla.add("B");
		contenidoTabla.add("C");
		contenidoTabla.add("D");
		contenidoTabla.add("E");
		contenidoTabla.add("F");
		contenidoTabla.add("A");
		contenidoTabla.add("B");
		contenidoTabla.add("C");
		contenidoTabla.add("D");
		contenidoTabla.add("E");
		contenidoTabla.add("F");

		// Agregamos la tabla
		document.add(reporteUtil.createdTable(titulosTabla, contenidoTabla));
		document.add(reporteUtil.addTexto(util.getMessage("reporte.cotizacion.contenido61"), NORMAL_12, 1));
		
		binnetas = new ArrayList<>();
		contenidos = new ArrayList<>();
		//Anexo de biñetas
		titulo = util.getMessage("reporte.cotizacion.contenido62");
		contenidos.add(util.getMessage("reporte.cotizacion.contenido63"));
		binnetas.add(new ReporteLista(titulo, BOLD_14, contenidos, NORMAL_12));

		//if(azul){
			contenidos = new ArrayList<>();
			titulo = util.getMessage("reporte.cotizacion.contenido64");
			contenidos.add(util.getMessage("reporte.cotizacion.contenido65"));
			binnetas.add(new ReporteLista(titulo, BOLD_14, contenidos, NORMAL_12));
		//}
			
		contenidos = new ArrayList<>();
		titulo = util.getMessage("reporte.cotizacion.contenido66");
		contenidos.add(util.getMessage("reporte.cotizacion.contenido67"));
		binnetas.add(new ReporteLista(titulo, BOLD_14, contenidos, NORMAL_12));

		contenidos = new ArrayList<>();
		titulo = util.getMessage("reporte.cotizacion.contenido68");
		contenidos.add(util.getMessage("reporte.cotizacion.contenido69"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido70"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido72"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido73"));
		binnetas.add(new ReporteLista(titulo, BOLD_14, contenidos, NORMAL_12));

		contenidos = new ArrayList<>();
		titulo = util.getMessage("reporte.cotizacion.contenido74");
		contenidos.add(util.getMessage("reporte.cotizacion.contenido75"));
		binnetas.add(new ReporteLista(titulo, BOLD_14, contenidos, NORMAL_12));
		
		contenidos = new ArrayList<>();
		titulo = util.getMessage("reporte.cotizacion.contenido76");
		contenidos.add(util.getMessage("reporte.cotizacion.contenido77"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido78"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido79"));
		binnetas.add(new ReporteLista(titulo, BOLD_14, contenidos, NORMAL_12));
		
		contenidos = new ArrayList<>();
		titulo = util.getMessage("reporte.cotizacion.contenido80");
		contenidos.add(util.getMessage("reporte.cotizacion.contenido81"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido82"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido83"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido84"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido85"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido86"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido87"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido88"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido89"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido90"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido91"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido92"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido93"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido94"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido95"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido96"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido97"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido98"));
		binnetas.add(new ReporteLista(titulo, BOLD_14, contenidos, NORMAL_12));
		
		contenidos = new ArrayList<>();
		titulo = util.getMessage("reporte.cotizacion.contenido99");
		contenidos.add(util.getMessage("reporte.cotizacion.contenido100"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido101"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido102"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido103"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido104"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido105"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido106"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido107"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido108"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido109"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido110"));
		binnetas.add(new ReporteLista(titulo, BOLD_14, contenidos, NORMAL_12));

		// Disposiciones generales
		// if(azul){
		contenidos = new ArrayList<>();
		titulo = util.getMessage("reporte.cotizacion.contenido111");
		contenidos.add(util.getMessage("reporte.cotizacion.contenido112"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido113"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido114"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido115"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido116"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido117"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido118"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido119"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido120"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido121"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido122"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido123"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido124"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido125"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido126"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido127"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido128"));
		binnetas.add(new ReporteLista(titulo, BOLD_14, contenidos, NORMAL_12));
		// }

		contenidos = new ArrayList<>();
		titulo = util.getMessage("reporte.cotizacion.contenido129");
		contenidos.add(util.getMessage("reporte.cotizacion.contenido130"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido131"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido132"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido133"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido134"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido135"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido136"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido137"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido138"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido139"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido140"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido141"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido142"));
		
		// if(azul)
		contenidos.add(util.getMessage("reporte.cotizacion.contenido143"));
		// if(verde)
		contenidos.add(util.getMessage("reporte.cotizacion.contenido144"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido144.falto"));
		// if(azul){
		contenidos.add(util.getMessage("reporte.cotizacion.contenido145"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido146"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido147"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido148"));
		// }

		// if(azul){
		contenidos.add(util.getMessage("reporte.cotizacion.contenido149"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido150"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido151"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido152"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido153"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido154"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido155"));
		// }
		binnetas.add(new ReporteLista(titulo, BOLD_14, contenidos, NORMAL_12));

		contenidos = new ArrayList<>();
		titulo = util.getMessage("reporte.cotizacion.contenido156");
		contenidos.add(util.getMessage("reporte.cotizacion.contenido157"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido158"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido159"));
		binnetas.add(new ReporteLista(titulo, BOLD_14, contenidos, NORMAL_12));

		contenidos = new ArrayList<>();
		titulo = util.getMessage("reporte.cotizacion.contenido160");
		contenidos.add(util.getMessage("reporte.cotizacion.contenido161"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido162"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido163"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido164"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido165"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido166"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido167"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido168"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido169"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido170"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido171"));
		binnetas.add (new ReporteLista(titulo, BOLD_14, contenidos, NORMAL_12));

		contenidos = new ArrayList<>();
		titulo = util.getMessage("reporte.cotizacion.contenido172");
		contenidos.add(util.getMessage("reporte.cotizacion.contenido173"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido174"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido175"));
		binnetas.add(new ReporteLista(titulo, BOLD_14, contenidos, NORMAL_12));

		contenidos = new ArrayList<>();
		titulo = util.getMessage("reporte.cotizacion.contenido176");
		contenidos.add(util.getMessage("reporte.cotizacion.contenido177"));
		contenidos.add(util.getMessage("reporte.cotizacion.contenido178"));

		contenidos = new ArrayList<>();
		titulo = util.getMessage("reporte.cotizacion.contenido179");
		binnetas.add(new ReporteLista(titulo, BOLD_14, null, NORMAL_12));
		document.add(reporteUtil.addBinneta(binnetas, 3));
		
		document.add(reporteUtil.addEmptyLine(1));

		// Tabla categorias de avaluos
		titulosTabla = new ArrayList<String>();
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.titulo1"));
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.titulo2"));
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.titulo3"));

		contenidoTabla = new ArrayList<String>();
		int indiceTabla = 0;
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido11"));
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido12"));

		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido21"));
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido22"));

		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido31"));
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido32"));

		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido41"));
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido42"));

		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido51"));
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido52"));

		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido61"));
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido62"));

		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido71"));
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido72"));
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido73"));
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido74"));

		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido81"));
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido82"));

		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido91"));
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido92"));

		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido101"));
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido102"));

		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido111"));
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido112"));

		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido121"));
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido122"));

		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido131"));
		contenidoTabla.add(util.getMessage("reporte.cotizacion.tabla.categoria.avaluos.contenido132"));

		// Agregamos la tabla de categorias de avaluos
		document.add(reporteUtil.createdTable(titulosTabla, contenidoTabla));

		contenidos = new ArrayList<>();
		titulo = util.getMessage("reporte.cotizacion.contenido180");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido181"));
		binnetas.add(new ReporteLista(titulo, BOLD_14, null, NORMAL_12));
		document.add(reporteUtil.addBinneta(binnetas, 17));
		
		// Tabla cotizaciones
		document.add(reporteUtil.addEmptyLine(1));
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
		document.add(reporteUtil.addEmptyLine(1));

		// Tabla categorias de avaluos
		titulosTabla = new ArrayList<String>();
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.bibliografia.titulo1"));
		titulosTabla.add(util.getMessage("reporte.cotizacion.tabla.bibliografia.titulo2"));

		contenidoTabla = new ArrayList<String>();
		indiceTabla = 0;
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido192"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido193"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido194"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido195"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido196"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido197"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido198"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido199"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido200"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido201"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido202"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido203"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido204"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido205"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido206"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido207"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido208"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido209"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido210"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido211"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido212"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido213"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido214"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido215"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido216"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido217"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido218"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido219"));
		contenidoTabla.add((indiceTabla++)+"");
		contenidoTabla.add(util.getMessage("reporte.cotizacion.contenido220"));
		// Agregamos la tabla de categorias de avaluos
		document.add(reporteUtil.createdTable(titulosTabla, contenidoTabla));

	}

}
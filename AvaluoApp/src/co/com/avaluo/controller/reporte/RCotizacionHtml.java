package co.com.avaluo.controller.reporte;

import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;
import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;
import static org.thymeleaf.templatemode.TemplateMode.HTML;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Cotizacion;

public class RCotizacionHtml {

	private static final String OUTPUT_FILE = "cotizacion.pdf";
	private static final String UTF_8 = "UTF-8";

	public String generatePdf(Cotizacion cotizacion) throws Exception {

		Util util = Util.getInstance();
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(HTML);
		templateResolver.setCharacterEncoding(UTF_8);

		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		Context context = new Context();
		context.setVariable("cotizacion", cotizacion);

		String renderedHtmlContent = templateEngine.process("cotizacion", context);
		String xHtml = convertToXhtml(renderedHtmlContent);

		ITextRenderer renderer = new ITextRenderer();
		//renderer.getFontResolver().addFont("Code39.ttf", IDENTITY_H, EMBEDDED);

		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		StringBuilder realPath = new StringBuilder(((String) servletContext.getRealPath("/"))).append(util.SEPARADOR_CARPETA);
		realPath.append(cotizacion.getId()).append(util.SEPARADOR_CARPETA);
		util.borrarArchivos(realPath.toString());
		util.crearDirectorio(realPath.toString());

		renderer.setDocumentFromString(xHtml, realPath.toString());
		renderer.layout();

		// And finally, we create the PDF:
		String urlFinal = realPath.toString() + OUTPUT_FILE;
		File file = new File(urlFinal);
		if(!file.exists())
			file.createNewFile();
		OutputStream outputStream = new FileOutputStream(file);
		renderer.createPDF(outputStream);
		outputStream.close();
		return urlFinal;
	}

	private String convertToXhtml(String html) throws UnsupportedEncodingException {
		Tidy tidy = new Tidy();
		tidy.setInputEncoding(UTF_8);
		tidy.setOutputEncoding(UTF_8);
		tidy.setXHTML(true);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes(UTF_8));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		tidy.parseDOM(inputStream, outputStream);
		return outputStream.toString(UTF_8);
	}

}

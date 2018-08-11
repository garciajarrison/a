package co.com.avaluo.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.avaluo.common.EnumReporteCotizacion;
import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Reporte;
import co.com.avaluo.model.entity.Usuario;
import co.com.avaluo.service.IReporteService;

@ManagedBean(name = "reporteBB")
@ViewScoped
public class ReporteBB extends SpringBeanAutowiringSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private IReporteService reporteService;
	
	private Reporte reporte = new Reporte();
	private Reporte selectedReporte;
	private List<Reporte> entityList;
	private Usuario usuario;
	private Util util;
	
	public ReporteBB() {
		util = Util.getInstance();
		usuario = (Usuario) util.getSessionAttribute(EnumSessionAttributes.USUARIO);
		cargarListaReportes();
	}
	
	@PostConstruct
	public void validarSession() {
		util.validarSession();
	}
	
	private void cargarListaReportes() {
		entityList = reporteService.getReportes(util.getMessage("reporte.cotizacion"), usuario.getEmpresa());
	}
	
	public String contenidoKey(String codigoContenido) {
		StringBuilder retorno = new StringBuilder();
		EnumReporteCotizacion reporteEnc = null;
	    for(EnumReporteCotizacion rp : EnumReporteCotizacion.values()){
	        if( rp.toString().equals(codigoContenido.trim())){
	        	reporteEnc = rp;
	            break;
	        }
	    }
	    
	    if(reporteEnc != null) {
	    	//Datos en tabla
		    if(reporteEnc.isTabla()) {
		    	int contColum = 0;
		    	//titulos
		    	retorno.append("<table><tr align='center'>");
		    	for(int i = reporteEnc.getDesdeTabla(); i <= reporteEnc.getHastaTabla(); i++) {
		    			retorno.append("<th  width='10%'><strong>");
		    			retorno.append(util.getMessage((reporteEnc.getMessageKeyTituloTabla() + i)));
		    			retorno.append("</strong></th>");
		    			contColum++;
		    	}

		    	int contColumDatos = 1;
		    	int cont = 1;
		    	for(int i = reporteEnc.getDesde(); i <= reporteEnc.getHasta(); i++) {
		    		if(contColumDatos == 1) {
		    			retorno.append("</tr><tr><td width='10%'>");
		    			retorno.append(cont++);
		    			retorno.append("</td>");
		    		}

		    		if(reporteEnc.getHasta() > (reporteEnc.getHastaTabla() - 1) && 
		    			i > (reporteEnc.getHastaTabla() - 1)) {
		    			retorno.append("<br/>");
			    		retorno.append(util.getMessage((reporteEnc.getMessageKey() + i)));
		    		}else {
			    		retorno.append("<td>");
			    		retorno.append(util.getMessage((reporteEnc.getMessageKey() + i)));
			    		if(i < (reporteEnc.getHastaTabla() - 1))
			    			retorno.append("</td>");
		    			
		    			if(contColumDatos == contColum) {
				    		contColumDatos = 1;
			    		}else {
			    			contColumDatos++;
			    		}
		    			
		    		}
		    		
		    	}
		    	if(reporteEnc.getHasta() > (reporteEnc.getHastaTabla() - 1)){
		    		retorno.append("</td>");
		    	}
		    	
		    	retorno.append("</tr></table>");
		    	
		    //Datos con negrita    
		    }else if(reporteEnc.isNegrita()) {
		    	retorno.append("<strong>");
		    	retorno.append(util.getMessage((reporteEnc.getMessageKey())));
		    	retorno.append("</strong>");
		    	retorno.append(" ");
		    	retorno.append(util.getMessage((reporteEnc.getMessageKey().replace(".negrita", ""))));
		    	
		    }else if(reporteEnc.getDesde() > 0) {
		    	for(int i = reporteEnc.getDesde(); i <= reporteEnc.getHasta(); i++) {
		    		if(i == reporteEnc.getDesde()) {
		    			retorno.append("<strong>");
		    			retorno.append(util.getMessage((reporteEnc.getMessageKey() + i)));
		    			retorno.append("</strong>");
		    		}else {
		    			retorno.append("<br/>");
		    			retorno.append(util.getMessage((reporteEnc.getMessageKey() + i)));
		    		}
		    	}
		    }
		    
		
	    }
	    
	    
	    return retorno.toString();
	}
	
	public void cambiarEstado(Reporte reporte) {
		try {
			reporteService.updateReporte(reporte);
			util.mostrarMensajeKey("exito.actualizar"); 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Reporte getReporte() {
		return reporte;
	}

	public void setReporte(Reporte reporte) {
		this.reporte = reporte;
	}

	public void reset() {
		this.reporte = new Reporte();
	}

	public List<Reporte> getEntityList() {
		return entityList;
	}

	public IReporteService getReporteService() {
		return reporteService;
	}

	public void setReporteService(IReporteService reporteService) {
		this.reporteService = reporteService;
	}

	public Reporte getSelectedReporte() {
		if(selectedReporte == null)
			selectedReporte = new  Reporte();
		return selectedReporte;
	}

	public void setSelectedReporte(Reporte selectedReporte) {
		this.selectedReporte = selectedReporte;
	}

	public void setEntityList(List<Reporte> entityList) {
		this.entityList = entityList;
	}

 }
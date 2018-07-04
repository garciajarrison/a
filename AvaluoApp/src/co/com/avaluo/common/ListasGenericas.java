package co.com.avaluo.common;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class ListasGenericas {
	
	private static ListasGenericas instance;
	private List<SelectItem> listaTiposDocumento;
	private List<SelectItem> listaLicencias;
	private List<SelectItem> listaIdiomas;
	private List<SelectItem> listaTipoPropiedadUrbano;
	private List<SelectItem> listaTipoPropiedadRural;
	private List<SelectItem> listaTipoPropiedad;
	private List<SelectItem> listaEstratos;
	
	public static ListasGenericas getInstance() {
		if(instance == null)
			instance = new ListasGenericas();
		return instance;
	}
	
	
	private ListasGenericas(){
		
		listaTiposDocumento = this.consultarListas(EnumListas.TIPOS_DOCUMENTO);
		listaLicencias = this.consultarListas(EnumListas.LISTA_LICENCIAS);
		listaIdiomas = this.consultarListas(EnumListas.LISTA_IDIOMAS);
		listaTipoPropiedadUrbano = this.consultarListas(EnumListas.LISTA_TIPO_PROPIEDAD_URBANO);
		listaTipoPropiedadRural = this.consultarListas(EnumListas.LISTA_TIPO_PROPIEDAD_RURAL);
		listaEstratos = this.consultarListas(EnumListas.LISTA_ESTRATOS);
		listaTipoPropiedad = this.consultarListas(EnumListas.LISTA_TIPO_PROPIEDAD);
	}

	public List<SelectItem> consultarListas(EnumListas lista){
		List<SelectItem> retorno = new ArrayList<SelectItem>();
		try {
			int i = 1;
			while(true) {
				String dato = Util.getInstance().getMessage(lista.toString() + i);
				String[] valor = dato.split("-");
				retorno.add(new SelectItem(valor[0], valor[1]));
				i++;
			}
		}catch(Exception e) {}
		return retorno;
	}


	public List<SelectItem> getListaTiposDocumento() {
		return listaTiposDocumento;
	}


	public List<SelectItem> getListaLicencias() {
		return listaLicencias;
	}

	public List<SelectItem> getListaIdiomas() {
		return listaIdiomas;
	}

	public List<SelectItem> getListaTipoPropiedadUrbano() {
		return listaTipoPropiedadUrbano;
	}

	public List<SelectItem> getListaTipoPropiedadRural() {
		return listaTipoPropiedadRural;
	}

	public List<SelectItem> getListaEstratos() {
		return listaEstratos;
	}


	public List<SelectItem> getListaTipoPropiedad() {
		return listaTipoPropiedad;
	}


	public void setListaTipoPropiedad(List<SelectItem> listaTipoPropiedad) {
		this.listaTipoPropiedad = listaTipoPropiedad;
	}

}

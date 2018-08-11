package co.com.avaluo.common;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class ListasGenericas {
	
	private static ListasGenericas instance;
	private List<SelectItem> listaTiposDocumento;
	private List<SelectItem> listaLicencias;
	private List<SelectItem> listaIdiomas;
	private List<SelectItem> listaTipoPropiedad;
	private List<SelectItem> listaUnidadMedida;
	private List<SelectItem> listaEstados;
	
	public static ListasGenericas getInstance() {
		if(instance == null)
			instance = new ListasGenericas();
		return instance;
	}
	
	private ListasGenericas(){
		
		listaTiposDocumento = this.consultarListas(EnumListas.TIPOS_DOCUMENTO);
		listaLicencias = this.consultarListas(EnumListas.LISTA_LICENCIAS);
		listaIdiomas = this.consultarListas(EnumListas.LISTA_IDIOMAS);
		listaTipoPropiedad = this.consultarListas(EnumListas.LISTA_TIPO_PROPIEDAD);
		listaUnidadMedida = this.consultarListas(EnumListas.LISTA_UNIDAD_MEDIDA);
		listaEstados = this.consultarListas(EnumListas.LISTA_ESTADOS, true);
	}
	
	private List<SelectItem> consultarListas(EnumListas lista){
		return consultarListas(lista, false);
	}

	private List<SelectItem> consultarListas(EnumListas lista, boolean manejoBoolean){
		List<SelectItem> retorno = new ArrayList<SelectItem>();
		try {
			int i = 1;
			while(true) {
				String dato = Util.getInstance().getMessage(lista.toString() + i);
				String[] valor = dato.split("-");
				if(manejoBoolean)
					retorno.add(new SelectItem("true".equals(valor[0]), valor[1]));
				else
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

	public List<SelectItem> getListaTipoPropiedad() {
		return listaTipoPropiedad;
	}

	public void setListaTipoPropiedad(List<SelectItem> listaTipoPropiedad) {
		this.listaTipoPropiedad = listaTipoPropiedad;
	}

	public List<SelectItem> getListaEstados() {
		return listaEstados;
	}


	public List<SelectItem> getListaUnidadMedida() {
		return listaUnidadMedida;
	}


	public void setListaUnidadMedida(List<SelectItem> listaUnidadMedida) {
		this.listaUnidadMedida = listaUnidadMedida;
	}

}

package co.com.avaluo.common;

import java.util.Locale;

public enum EnumLenguajes {

	ESPANOL("ES", new Locale("es", "ES")),
	INGLES("EN", Locale.ENGLISH),
	FRANCES("FR", Locale.FRENCH),
	MANDARIN("MN", Locale.SIMPLIFIED_CHINESE);
	
	private String sigla;
	private Locale locale;
	
	private EnumLenguajes(String sigla, Locale locale) {
		this.sigla = sigla;
		this.locale = locale;
	}

	public String getSigla() {
		return sigla;
	}

	public Locale getLocale() {
		return locale;
	}

}

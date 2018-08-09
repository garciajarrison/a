package co.com.avaluo.common;

public enum EnumReporteCotizacion {

	CONTRATO_ORDEN_COMPRA("reporte.cotizacion.contenido", 3, 4, false, false, "", 0, 0),
	DEFINICIONES_BASICAS("reporte.cotizacion.contenido", 7, 8, false, false, "", 0, 0),
	DECRETO_1420_DE_1998("reporte.cotizacion.contenido9.negrita", 0, 0, true, false, "", 0, 0),
	PREDIO("reporte.cotizacion.contenido", 10, 12, false, false, "", 0, 0),
	PREDIO_RURAL("reporte.cotizacion.contenido", 13, 14, false, false, "", 0, 0),
	PREDIO_URBANO("reporte.cotizacion.contenido", 15, 16, false, false, "", 0, 0),
	URBANIZACION("reporte.cotizacion.contenido", 17, 18, false, false, "", 0, 0),
	PARCELACION("reporte.cotizacion.contenido", 19, 20, false, false, "", 0, 0),
	PROPIEDAD_HORIZONTAL("reporte.cotizacion.contenido", 21, 22, false, false, "", 0, 0),
	REGIMEN_PROPIEDAD_HORIZONTAL("reporte.cotizacion.contenido", 23, 24, false, false, "", 0, 0),
	EDIFICIO("reporte.cotizacion.contenido", 25, 26, false, false, "", 0, 0),
	CONJUNTO("reporte.cotizacion.contenido", 27, 28, false, false, "", 0, 0),
	CONDOMINIO("reporte.cotizacion.contenido", 29, 30, false, false, "", 0, 0),
	MEJORA_CONSTRUCCIONES_EDIFICACIONES_PREDIO_AJENO("reporte.cotizacion.contenido", 31, 32, false, false, "", 0, 0),
	CONSTRUCCIÓN_O_EDIFICACION("reporte.cotizacion.contenido", 33, 34, false, false, "", 0, 0),
	TERRENO("reporte.cotizacion.contenido", 35, 36, false, false, "", 0, 0),
	NORMA_USO_SUELO("reporte.cotizacion.contenido", 37, 38, false, false, "", 0, 0),
	TIPOLOGIA_CONSTRUCCIONES_EDIFICACIONES("reporte.cotizacion.contenido", 39, 40, false, false, "", 0, 0),
	AREA_BRUTA("reporte.cotizacion.contenido", 41, 42, false, false, "", 0, 0),
	AREA_NETA_URBANIZABLE("reporte.cotizacion.contenido", 43, 44, false, false, "", 0, 0),
	REVISION_EDIFICACION("reporte.cotizacion.contenido", 45, 46, false, false, "", 0, 0),
	ESCRITURA_PUBLICA("reporte.cotizacion.contenido", 47, 48, false, false, "", 0, 0),
	ERA_ENTIDAD_RECONOCIDA_AUTOREGULACION("reporte.cotizacion.contenido", 49, 50, false, false, "", 0, 0),
	RAA_REGISTRO_ABIERTO_AVALUADORES("reporte.cotizacion.contenido", 51, 52, false, false, "", 0, 0),
	VIGENCIA_COTIZACIÓN("reporte.cotizacion.contenido", 64, 65, false, false, "", 0, 0),
	DISPOSICIONES_GENERALES("reporte.cotizacion.contenido", 111, 128, false, false, "", 0, 0),
	//NOTA,
	NOTA_PROYECTO_INFRAESTRUCTURA("reporte.cotizacion.contenido", 143, 143, false, false, "", 0, 0),
	NOTA_PLUSVALIA("reporte.cotizacion.contenido", 144, 144, false, false, "", 0, 0),
	NOTA_VALORIZACION("reporte.cotizacion.contenido144.falto", 0, 0, false, false, "", 0, 0),
	NOTA_NORMAS_NIIF_NICSP("reporte.cotizacion.contenido", 145, 148, false, false, "", 0, 0),
	NOTA_CONTRATACIÓN_CON_ESTADO("reporte.cotizacion.contenido", 148, 151, false, false, "", 0, 0),
	PROCESOS_LEY_INSOLVENCIA("reporte.cotizacion.contenido", 176, 178, false, false, "", 0, 0),
	//TABLA CATEGORIAS DE AVALUOS (Ley 1673 de 2013):
	TBL1_INMUEBLES_URBANOS("reporte.cotizacion.tabla.categoria.avaluos.contenido1", 1, 2, false, true, "reporte.cotizacion.tabla.categoria.avaluos.titulo", 1, 3),
	TBL1_INMUEBLES_RURALES("reporte.cotizacion.tabla.categoria.avaluos.contenido2", 1, 2, false, true, "reporte.cotizacion.tabla.categoria.avaluos.titulo", 1, 3),
	TBL1_RECURSOS_NATURALES_SUELOS_PROTECCION("reporte.cotizacion.tabla.categoria.avaluos.contenido3", 1, 2, false, true, "reporte.cotizacion.tabla.categoria.avaluos.titulo", 1, 3),
	TBL1_OBRAS_INFRAESTRUCTURA("reporte.cotizacion.tabla.categoria.avaluos.contenido4", 1, 2, false, true, "reporte.cotizacion.tabla.categoria.avaluos.titulo", 1, 3),
	TBL1_EDIFICACIONES_CONSERVACION_ARQUEOLOGIA_MONUMENTOS_HISTORICOS("reporte.cotizacion.tabla.categoria.avaluos.contenido5", 1, 2, false, true, "reporte.cotizacion.tabla.categoria.avaluos.titulo", 1, 3),
	TBL1_INMUEBLES_ESPECIALES("reporte.cotizacion.tabla.categoria.avaluos.contenido6", 1, 2, false, true, "reporte.cotizacion.tabla.categoria.avaluos.titulo", 1, 3),
	TBL1_MAQUINARIA_FIJA_EQUIPOS_MAQUINARIO_MOVIL("reporte.cotizacion.tabla.categoria.avaluos.contenido7", 1, 4, false, true, "reporte.cotizacion.tabla.categoria.avaluos.titulo", 1, 3),
	TBL1_MAQUINARIA_EQUIPOS_ESPECIALES("reporte.cotizacion.tabla.categoria.avaluos.contenido8", 1, 2, false, true, "reporte.cotizacion.tabla.categoria.avaluos.titulo", 1, 3),
	TBL1_OBRAS_ARTE_ORFEBRERIA_PATRIMONIALES_SIMILARES("reporte.cotizacion.tabla.categoria.avaluos.contenido9", 1, 2, false, true, "reporte.cotizacion.tabla.categoria.avaluos.titulo", 1, 3),
	TBL1_SEMOVIEMTES_ANIMALES("reporte.cotizacion.tabla.categoria.avaluos.contenido10", 1, 2, false, true, "reporte.cotizacion.tabla.categoria.avaluos.titulo", 1, 3),
	TBL1_ACTIVOS_OPERACIONALES_ESTABLECIMIENTOS_COMERCIO("reporte.cotizacion.tabla.categoria.avaluos.contenido11", 1, 2, false, true, "reporte.cotizacion.tabla.categoria.avaluos.titulo", 1, 3),
	TBL1_INTANGIBLES("reporte.cotizacion.tabla.categoria.avaluos.contenido12", 1, 2, false, true, "reporte.cotizacion.tabla.categoria.avaluos.titulo", 1, 3),
	TBL1_INTANGIBLES_ESPECIALES("reporte.cotizacion.tabla.categoria.avaluos.contenido13", 1, 2, false, true, "reporte.cotizacion.tabla.categoria.avaluos.titulo", 1, 3),
	//BIBLIOGRAFICA
	BIBLIOGRAFICA("reporte.cotizacion.contenido", 191, 220, false, false, "reporte.cotizacion.tabla.bibliografia.titulo", 1, 2);
	
	private String messageKey;
	private int desde;
	private int hasta;
	private boolean negrita;
	private boolean tabla;
	private String messageKeyTituloTabla;
	private int desdeTabla;
	private int hastaTabla;
	
	private EnumReporteCotizacion(String messageKey, int desde, int hasta, 
									boolean negrita, boolean tabla,
									String messageKeyTituloTabla, 
									int desdeTabla, int hastaTabla) {
		this.messageKey = messageKey;
		this.desde = desde;
		this.hasta = hasta;
		this.negrita = negrita;
		this.tabla = tabla;
		this.messageKeyTituloTabla = messageKeyTituloTabla;
		this.desdeTabla = desdeTabla;
		this.hastaTabla = hastaTabla;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public int getDesde() {
		return desde;
	}

	public int getHasta() {
		return hasta;
	}

	public boolean isNegrita() {
		return negrita;
	}

	public boolean isTabla() {
		return tabla;
	}

	public String getMessageKeyTituloTabla() {
		return messageKeyTituloTabla;
	}

	public int getDesdeTabla() {
		return desdeTabla;
	}

	public int getHastaTabla() {
		return hastaTabla;
	}
	
}

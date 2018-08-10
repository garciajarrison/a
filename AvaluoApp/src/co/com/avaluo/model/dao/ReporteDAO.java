package co.com.avaluo.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.avaluo.common.EnumReporteCotizacion;
import co.com.avaluo.model.entity.Empresa;
import co.com.avaluo.model.entity.Reporte;

@Repository
public class ReporteDAO implements IReporteDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addReporte(Reporte entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.save(entity);
	}

	public void deleteReporte(Reporte entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	public void updateReporte(Reporte entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(entity);
	}

	public Reporte getReporteById(int id) {
		Session session = getSessionFactory().getCurrentSession();
		
		return (Reporte) session
				.createQuery("from Reporte where id=?").setParameter(0, id)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Reporte> getReportes(String codigo, int idReporte) {
		Session session = getSessionFactory().getCurrentSession();
		return (List<Reporte>) session.createQuery("from Reporte where empresa.id = :idEmpresa and codigo = :codigo order by id")
				.setParameter("idEmpresa", idReporte)
				.setParameter("codigo", codigo).list();
	}

	public List<Reporte> datosReporteCotizacion(String codigo, Empresa empresa) {
		Session session = getSessionFactory().getCurrentSession();
		Reporte guardar; 
		for(EnumReporteCotizacion enm : EnumReporteCotizacion.values()) {
			guardar = new Reporte();
			guardar.setEmpresa(empresa);
			guardar.setVisible(true);
			guardar.setCodigo(codigo);
			guardar.setIdContenido(enm.toString().trim());
			
			session.save(guardar);
		}
		return getReportes(codigo, empresa.getId());
	}

}

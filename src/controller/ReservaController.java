package controller;

import java.util.List;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {
	
	private ReservaDAO reservaDAO;

	public ReservaController(){
		this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperaConexion());
	}

	public int editar(Integer id, String fechaEntrada, String fechaSalida, String valor, String formaPago) {
		return reservaDAO.editar(id, fechaEntrada, fechaSalida, valor, formaPago);
	}

	public int eliminar(Integer id){
		return reservaDAO.eliminar(id);
	}

	public List<Reserva> listar(){
		return reservaDAO.listar();
	}
	
	public List<Reserva> buscar(Integer id){
		return reservaDAO.buscar(id);
	}

    public void guardar(Reserva reserva){
		reservaDAO.guardar(reserva);
	}
	
}

package controller;

import java.util.List;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huesped;
import modelo.Reserva;

public class HuespedController {
	
	private HuespedDAO huespedDAO;

	public HuespedController(){
		this.huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
	}

	public int editar(Integer id, String nombre, String apellido, String fechaNacimiento, String nacionalidad, Integer telefono, Integer idReserva) {
		return huespedDAO.editar(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
	}

	public int eliminar(Integer id){
		return huespedDAO.eliminar(id);
	}

	public List<Huesped> listar(){
		return huespedDAO.listar();
	}
	
	public List<Huesped> buscar(String apellido){
		return huespedDAO.buscar(apellido);
	}

    public void guardar(Huesped huesped){
		huespedDAO.guardar(huesped);
	}
	
}
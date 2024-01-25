package modelo;

public class Huesped {

	private Integer id;
	
	private String nombre;
	
	private String apellido;
	
	private String nacionalidad;
	
	private String fechaNacimiento;
	
	private Integer telefono;
	
	private Integer idReserva;

	public Huesped(Integer id, String nombre, String apellido, String nacionalidad, String fechaNacimiento, Integer telefono, Integer idReserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}
	
	public Huesped(String nombre, String apellido, String nacionalidad, String fechaNacimiento, String telefono, String idReserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = Integer.valueOf(telefono);
		this.idReserva = Integer.valueOf(idReserva);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public Integer getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}
	
	
	
}

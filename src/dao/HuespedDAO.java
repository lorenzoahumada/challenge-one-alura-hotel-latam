package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Huesped;
import modelo.Reserva;

public class HuespedDAO {
    final private Connection con;
    
    public HuespedDAO(Connection con){
        this.con = con;
    }

    public void guardar(Huesped huesped){
        try{
            final PreparedStatement statement = con.prepareStatement("INSERT INTO HUESPEDES(NOMBRE, APELLIDO, NACIONALIDAD, FECHA_DE_NACIMIENTO, TELEFONO, ID_RESERVA)"
                            + " VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            try(statement){
                ejecutaRegistro(huesped, statement);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    private void ejecutaRegistro(Huesped huesped, PreparedStatement statement) throws SQLException {
        statement.setString(1, huesped.getNombre());
        statement.setString(2, huesped.getApellido());
        statement.setString(3, huesped.getNacionalidad());
        statement.setString(4, huesped.getFechaNacimiento());
        statement.setInt(5, huesped.getTelefono());
        statement.setInt(6, huesped.getIdReserva());
        statement.execute();

        final ResultSet resultSet = statement.getGeneratedKeys();
        try (resultSet){
            while (resultSet.next()){
                huesped.setId(resultSet.getInt(1));
                System.out.println(String.format("Fue insertado el huesped %s", huesped.getNombre()));
            }
        }
    }
    
    public List<Huesped> listar() {
        List<Huesped> resultado = new ArrayList<>();

        try{
            final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, APELLIDO, NACIONALIDAD, FECHA_DE_NACIMIENTO, TELEFONO, ID_RESERVA FROM HUESPEDES");
            try(statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();
                try(resultSet) {
                    while (resultSet.next()) {
                        Huesped fila = new Huesped(resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("APELLIDO"),
                                resultSet.getString("NACIONALIDAD"),
                                resultSet.getString("FECHA_DE_NACIMIENTO"),
                                resultSet.getInt("TELEFONO"),
                                resultSet.getInt("ID_RESERVA"));
                        resultado.add(fila);
                    }
                }
            }
            return resultado;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public int eliminar(Integer id){
        try{
            final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");
            try(statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public int editar(Integer id, String nombre, String apellido, String nacionalidad, String fechaNacimiento, Integer telefono, Integer idReserva) {
        try{
            final PreparedStatement statement = con.prepareStatement("UPDATE HUESPEDES SET "
                    + " NOMBRE = ?"
                    + ", APELLIDO = ?"
                    + ", NACIONALIDAD = ?"
                    + ", FECHA_DE_NACIMIENTO = ?"
                    + ", TELEFONO = ?"
                    + ", ID_RESERVA = ?"
                    + " WHERE ID = ?");
            try(statement) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setString(3, nacionalidad);
                statement.setString(4, fechaNacimiento);
                statement.setInt(5, telefono);
                statement.setInt(6, idReserva);
                statement.setInt(7, id);
                statement.execute();

                return statement.getUpdateCount();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public List<Huesped> buscar(String apellido) {
        List<Huesped> resultado = new ArrayList<>();

        try{
            final PreparedStatement statement = con.prepareStatement("SELECT * FROM HUESPEDES"
                    + " WHERE APELLIDO = ?");
            try(statement) {
                statement.setString(1, apellido);
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();
                try(resultSet) {
                    while (resultSet.next()) {
                        Huesped fila = new Huesped(resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("APELLIDO"),
                                resultSet.getString("NACIONALIDAD"),
                                resultSet.getString("FECHA_DE_NACIMIENTO"),
                                resultSet.getInt("TELEFONO"),
                                resultSet.getInt("ID_RESERVA"));
                        resultado.add(fila);
                    }
                }
            }
            return resultado;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
   
}

package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Reserva;

public class ReservaDAO {
	
    final private Connection con;
    
    public ReservaDAO(Connection con){
        this.con = con;
    }

    public void guardar(Reserva reserva){
        try{
            final PreparedStatement statement = con.prepareStatement("INSERT INTO RESERVAS(FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO)"
                            + " VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            try(statement){
                ejecutaRegistro(reserva, statement);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    private void ejecutaRegistro(Reserva reserva, PreparedStatement statement) throws SQLException {
        statement.setString(1, reserva.getFechaEntrada());
        statement.setString(2, reserva.getFechaSalida());
        statement.setString(3, reserva.getValor());
        statement.setString(4, reserva.getFormaPago());
        statement.execute();

        final ResultSet resultSet = statement.getGeneratedKeys();
        try (resultSet){
            while (resultSet.next()){
                reserva.setId(resultSet.getInt(1));
                System.out.println(String.format("Fue insertada la reserva %s", reserva));
            }
        }
    }
    
    public List<Reserva> listar() {
        List<Reserva> resultado = new ArrayList<>();

        try{
            final PreparedStatement statement = con.prepareStatement("SELECT ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO FROM RESERVAS");
            try(statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();
                try(resultSet) {
                    while (resultSet.next()) {
                        Reserva fila = new Reserva(resultSet.getInt("ID"),
                                resultSet.getString("FECHA_ENTRADA"),
                                resultSet.getString("FECHA_SALIDA"),
                                resultSet.getString("VALOR"),
                                resultSet.getString("FORMA_PAGO"));
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
            final PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?");
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
    
    public int editar(Integer id, String fechaEntrada, String fechaSalida, String valor, String formaPago) {
        try{
            final PreparedStatement statement = con.prepareStatement("UPDATE RESERVAS SET "
                    + " FECHA_ENTRADA = ?"
                    + ", FECHA_SALIDA = ?"
                    + ", VALOR = ?"
                    + ", FORMA_PAGO = ?"
                    + " WHERE ID = ?");
            try(statement) {
                statement.setString(1, fechaEntrada);
                statement.setString(2, fechaSalida);
                statement.setString(3, valor);
                statement.setString(4, formaPago);
                statement.setInt(5, id);
                statement.execute();

                return statement.getUpdateCount();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public List<Reserva> buscar(Integer id) {
        List<Reserva> resultado = new ArrayList<>();

        try{
            final PreparedStatement statement = con.prepareStatement("SELECT * FROM RESERVAS"
                    + " WHERE ID = ?");
            try(statement) {
                statement.setInt(1, id);
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();
                try(resultSet) {
                    while (resultSet.next()) {
                        Reserva fila = new Reserva(resultSet.getInt("ID"),
                                resultSet.getString("FECHA_ENTRADA"),
                                resultSet.getString("FECHA_SALIDA"),
                                resultSet.getString("VALOR"),
                                resultSet.getString("FORMA_PAGO"));
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

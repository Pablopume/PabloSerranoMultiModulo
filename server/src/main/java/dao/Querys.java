package dao;

public class Querys {
    private Querys() {
    }

    public static final String SELECT_FROM_EMPLEADO = "SELECT * FROM Empleado";
    public static final String DELETE_FROM_EMPLEADO_WHERE_IDEQUIPO = "DELETE FROM Empleado WHERE IDEQUIPO = ?";
    public static final String SELECT_FROM_EMPLEADO_WHERE_IDEQUIPO = "SELECT * FROM Empleado WHERE IDEQUIPO = ?";
    public static final String SELECT_FROM_EMPLEADO_WHERE_ID = "SELECT * FROM Empleado WHERE ID = ?";
    public static final String INSERT_INTO_EMPLEADO_VALUES = "INSERT INTO Empleado VALUES (?,?,?,?,?,?,?)";
    public static final String UPDATE_EMPLEADO_SET_NOMBRE_APELLIDO_DOB_EMAIL_TELEFONO_IDEQUIPO_WHERE_ID = "UPDATE Empleado SET NOMBRE = ?, APELLIDO = ?, DOB = ?, EMAIL= ?, TELEFONO=?, IDEQUIPO=? WHERE ID = ?";
    public static final String DELETE_FROM_EMPLEADO_WHERE_ID = "DELETE FROM Empleado WHERE ID = ?";
    public static final String SELECT_FROM_EQUIPO_WHERE_ID = "SELECT * FROM Equipo WHERE ID = ?";
    public static final String SELECT_FROM_EQUIPO = "SELECT * FROM Equipo";
    public static final String SELECT_FROM_PROYECTO = "SELECT * FROM Proyecto";
    public static final String SELECT_FROM_PROYECTO_WHERE_ID = "SELECT * FROM Proyecto WHERE ID = ?";
}

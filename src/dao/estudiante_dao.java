/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import Conexion.Conexion;
import interfaces.metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Estudiante;

/**
 *
 * @author LN710Q
 */
public class estudiante_dao implements metodos<Estudiante>{
    
    private static final String SQL_INSERT = "INSERT INTO alumnos(carnet, nombre, apellidos, universidad, edad, estado) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE alumnos SET nombre = ?, apellidos = ?, edad=? WHERE carnet=?";
    private static final String SQL_DELETE = "DELETE FROM alumnos WHERE carnet=?";
    private static final String SQL_READ = "SELECT * FROM alumnos WHERE carnet=?";
    private static final String SQL_READALL = "SELECT * FROM alumnos";
    private static final Conexion con=Conexion.conectar();

    @Override
    public boolean create(Estudiante g) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setInt(1, g.getCarnet());
            ps.setString(2, g.getNombre());
            ps.setString(3, g.getApellido());
            ps.setString(4, g.getUnviersidad());
            ps.setInt(5, g.getEdad());
            ps.setBoolean(6, true);
            if(ps.executeUpdate()>0){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(estudiante_dao.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            
            if(ps.executeUpdate()>0){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(estudiante_dao.class.getName()).log(Level.SEVERE, null,ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Estudiante c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getCarnet());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setInt(1, c.getCarnet());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApellido());
            ps.setString(4, c.getUnviersidad());
            ps.setInt(5, c.getEdad());
            if(ps.executeUpdate()>0){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(estudiante_dao.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Estudiante read(Object key) {
        Estudiante e = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                e = new Estudiante(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getBoolean(6));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(estudiante_dao.class.getName()).log(Level.SEVERE,null,ex);
        } finally{
            con.cerrarConexion();
        }
        return e;
    }

    @Override
    public ArrayList<Estudiante> readAll() {
        ArrayList<Estudiante> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try {
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            while(rs.next()){
                all.add(new Estudiante(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getBoolean(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(estudiante_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }
    
}

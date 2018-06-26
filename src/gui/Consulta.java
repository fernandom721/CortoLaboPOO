/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.estudiante_dao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Estudiante;
/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame{
    
    public JLabel lbcarnet, lbnombre, lbapellido, lbuniversidad, lbedad, lbestado;
    public JPanel tabla;
    public JTextField carnet, nombre, apellidos, edad;
    public JButton busca, insertar, actualizar, eliminar, limpiar;
    ButtonGroup estado = new ButtonGroup();
    public JRadioButton activo, inactivo;
    public JComboBox universidad;
    public JTable resultados;
    
    
    private static final int ANCHOC = 120, ALTOC =30;
    
    DefaultTableModel tm;
    
    public Consulta(){
        
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        addLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lbcarnet);
        container.add(lbnombre);
        container.add(lbapellido);
        container.add(lbuniversidad);
        container.add(lbedad);
        container.add(lbestado);
        container.add(carnet);
        container.add(nombre);
        container.add(apellidos);
        container.add(edad);
        container.add(busca);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(activo);
        container.add(inactivo);
        container.add(universidad);
        container.add(tabla);
        setSize(1000,600);
        eventos();
    }
    
    public final void addLabels(){
        lbcarnet = new JLabel ("Carnet");
        lbnombre = new JLabel ("Nombre");
        lbapellido = new JLabel ("Apellido");
        lbuniversidad = new JLabel("Universidad");
        lbedad = new JLabel("Edad");
        lbestado = new JLabel("Estado");
        lbcarnet.setBounds(10, 10, ANCHOC, ALTOC);
        lbnombre.setBounds(10, 60, ANCHOC, ALTOC);
        lbapellido.setBounds(10, 100, ANCHOC, ALTOC);
        lbuniversidad.setBounds(10, 160, ANCHOC, ALTOC);
        lbedad.setBounds(10, 200, ANCHOC, ALTOC);
        lbestado.setBounds(10, 240, ANCHOC, ALTOC);
    }
    
    public final void formulario(){
        carnet = new JTextField();
        nombre = new JTextField();
        apellidos = new JTextField();
        edad = new JTextField();
        universidad = new JComboBox();
        activo = new JRadioButton("Activo", true);
        inactivo = new JRadioButton("Inactivo");
        resultados = new JTable();
        busca = new JButton("Buscar");
        insertar = new JButton("Insertar");
        actualizar = new JButton("Actualizar");
        eliminar = new JButton("Eliminar");
        limpiar = new JButton("Limpiar");
        
        tabla = new JPanel();
        universidad.addItem("UCA");
        universidad.addItem("UDB");
        universidad.addItem("UFG");
        universidad.addItem("UGB");
        
        estado = new ButtonGroup();
        estado.add(activo);
        estado.add(inactivo);
        
        carnet.setBounds(140, 10, ANCHOC, ALTOC);
        nombre.setBounds(140, 60, ANCHOC, ALTOC);
        apellidos.setBounds(140, 100, ANCHOC, ALTOC);
        edad.setBounds(140, 200, ANCHOC, ALTOC);
        universidad.setBounds(140, 150, ANCHOC, ALTOC);
        activo.setBounds(140, 240, ANCHOC, ALTOC);
        inactivo.setBounds(240, 240, ANCHOC, ALTOC);
        
        busca.setBounds(400, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 270, ANCHOC, ALTOC);
        actualizar.setBounds(150, 270, ANCHOC, ALTOC);
        eliminar.setBounds(300, 270, ANCHOC, ALTOC);
        limpiar.setBounds(450, 270, ANCHOC, ALTOC);
        resultados = new JTable();
        tabla.setBounds(10,300,900,200);
        tabla.add(new JScrollPane(resultados));
    }
    
    public void llenarTabla(){
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("Carnet");
        tm.addColumn("Nombre");
        tm.addColumn("Apellido");
        tm.addColumn("Edad");
        tm.addColumn("Universidad");
        tm.addColumn("Estado");
        
        estudiante_dao ed = new estudiante_dao();
        ArrayList<Estudiante> estudiantes = ed.readAll();
        
        for(Estudiante ei : estudiantes){
            tm.addRow(new Object[]{ei.getCarnet(),ei.getNombre(),ei.getApellido(),ei.getEdad(),ei.getUnviersidad(),ei.isEstado()});
        }
        
        resultados.setModel(tm);
    }
    
    public void eventos(){
        
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estudiante_dao fd = new estudiante_dao();
                Estudiante f = new Estudiante(carnet.getText(), nombre.getText(), apellidos.getText(),Integer.parseInt(edad.getText()), universidad.getSelectedItem().toString(),  true);
                if(inactivo.isSelected()){
                    f.setEstado(false);
                }
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null,"Estudiante registrado con exito.");
                    limpiarCampos();
                    llenarTabla();
                } else{
                    JOptionPane.showMessageDialog(null, "Ocurrió un problema al momento de modificar el estudiante.");
                }
            }
        });
        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estudiante_dao fd = new estudiante_dao();
                Estudiante f = new Estudiante(carnet.getText(), nombre.getText(), apellidos.getText(),Integer.parseInt(edad.getText()), universidad.getSelectedItem().toString(),  true);
                if(fd.update(f)){
                    JOptionPane.showMessageDialog(null,"Estudiante actualizad con exito.");
                    limpiarCampos();
                    llenarTabla();
                } else{
                    JOptionPane.showMessageDialog(null, "Ocurrió un problema al momento de actualizar el estudiante.");
                }
            }
        });
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estudiante_dao fd = new estudiante_dao();
                if(fd.delete(carnet.getText())){
                    JOptionPane.showMessageDialog(null, "Estudiante Eliminado con éxito.");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrió un problema al momento de eliminar el estudiante.");
                }
            }
        });
        
        busca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estudiante_dao fd = new estudiante_dao();
                Estudiante f = fd.read(carnet.getText());
                if(f==null){
                    JOptionPane.showMessageDialog(null, "El filtro buscado no se ha encontrado.");
                } else{
                    
                    carnet.setText(f.getCarnet());
                    nombre.setText(f.getNombre());
                    apellidos.setText(f.getApellido());
                    universidad.setSelectedItem(f.getUnviersidad());
                    edad.setText(Integer.toString(f.getEdad()));
                    
                    if(f.isEstado()){
                        activo.setSelected(true);
                    } else{
                        inactivo.setSelected(true);
                    }
                }
            }
        });
        
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }
    
   public void limpiarCampos(){
        carnet.setText("");
        nombre.setText("");
        apellidos.setText("");
        universidad.setSelectedItem("UCA");
        edad.setText("");
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }
}

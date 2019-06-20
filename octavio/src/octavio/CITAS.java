/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author salva_000
 */
public class CITAS extends javax.swing.JFrame {
DefaultTableModel model;
DateFormat df = DateFormat.getDateInstance();
    /**
     * Creates new form CITAS
     */
    public CITAS() {
        initComponents();
        mostrar("");
        bloquear();
    }
    void limpiar(){
        jTextField1.setText(null);
        jTextField2.setText(null);
        jTextField3.setText(null);
        jTextField4.setText(null);
        jTextField5.setText(null);
        jTextField6.setText(null);
        fecha.setDate(null);
    }
    public void bloquear(){
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jTextField4.setEnabled(false);
        jTextField5.setEnabled(false);
        jTextField6.setEnabled(false);
        jPasswordField1.setEnabled(false);
        fecha.setEnabled(false);
        jComboBox1.setEnabled(false);
        jComboBox2.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
        jButton5.setEnabled(false);
        jButton8.setEnabled(false);
        jButton9.setEnabled(false);
    }
    void desbloquear(){
        jTextField2.setEnabled(true);
        jTextField3.setEnabled(true);
        jTextField4.setEnabled(true);
        jTextField5.setEnabled(true);
        jTextField6.setEnabled(true);
        jPasswordField1.setEnabled(true);
        fecha.setEnabled(true);
        jComboBox1.setEnabled(true);
        jComboBox2.setEnabled(true);

}
    void desbloquearNuevo(){
        jTextField2.setEnabled(true);
        jTextField3.setEnabled(true);
        jTextField4.setEnabled(true);
        jTextField5.setEnabled(true);
        jTextField6.setEnabled(true);
        jPasswordField1.setEnabled(true);
        fecha.setEnabled(true);
        jComboBox1.setEnabled(true);
        jComboBox2.setEnabled(true);

}
void agregar(){
    System.out.println("marca 1");
    
conectar bd=new conectar();
    System.out.println("marca 2");
        Connection conexion =bd.conexion();
    System.out.println("marca 3");
        String a,b,c,d,e,f,g,h,i;
        char[] j;
        String sql;
        a=jTextField1.getText();
        b=jTextField2.getText();
        c=jTextField3.getText();
        d=jTextField4.getText();
        e=jTextField5.getText();
        f=jTextField6.getText();
        //g=jTextField7.getText();
        g=df.format(fecha.getDate());
        h=(String) jComboBox1.getSelectedItem();
        i=(String) jComboBox2.getSelectedItem();
        j=jPasswordField1.getPassword();
        
         sql ="INSERT INTO cita2 (nombre,email,telefono,ciudad,edad,fecha_cita,hora_cita,comentario, sexo, contraseña) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = conexion. prepareStatement(sql);
       
            pst.setString(1,b);
            pst.setString(2,c);
            pst.setString(3,d);
            pst.setString(4,e);
            pst.setString(5,f);
            pst.setString(6,g);
            pst.setString(7,h);
            pst.setString(8,i);
            pst.setString(9,i);
            pst.setString(10,String.valueOf(j));
            int w =pst.executeUpdate();
            if(w>0){
                bloquear();
            }
            } catch (SQLException ex) {
            Logger.getLogger(CITAS.class.getName()).log(Level.SEVERE, null, ex);
    }
        mostrar("");
}
void comparar(){conectar bd =new conectar ();
        Connection conexion=bd.conexion();
        String cap="";
        String tap="";
       String sql="SELECT * FROM cita2 WHERE fecha_cita='"+df.format(fecha.getDate())+"' && hora_cita='"+(String) jComboBox1.getSelectedItem()+"'";
        try {
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                cap=rs.getString("fecha_cita");
                tap=rs.getString("hora_cita");
            }
            if(tap.equals((String) jComboBox1.getSelectedItem())&& cap.equals(df.format(fecha.getDate())))
            {
              
                  JOptionPane.showMessageDialog(null, "CITA OCUPADA");
            }
            
            if(!tap.equals((String) jComboBox1.getSelectedItem())&& !cap.equals(df.format(fecha.getDate())))
            {
                agregar();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CITAS.class.getName()).log(Level.SEVERE, null, ex);
        }
}
void mostrar(String valor){
        String [] Titulos={ "clave", "nombre", "email", "telefono", "domicilio", "edad","fecha_cita","hora_cita","sexo"};
        String []registros=new String[9];
        String sql="SELECT * FROM cita2 where fecha_cita LIKE '%"+valor+"%'";
        
       model =new DefaultTableModel(null, Titulos);
        
        conectar bd =new conectar ();
        Connection conexion=bd.conexion();
        try {
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                registros[0]=rs.getString("clave");
                registros[1]=rs.getString("nombre");
                registros[2]=rs.getString("email");
                registros[3]=rs.getString("telefono");
                registros[4]=rs.getString("ciudad");
                registros[5]=rs.getString("edad");
                registros[6]=rs.getString("fecha_cita");
                registros[7]=rs.getString("hora_cita");
                registros[8]=rs.getString("comentario");
                model.addRow(registros);
            }
            jTable1.setModel(model);
            
        }catch (SQLException ex){
                  JOptionPane.showMessageDialog(null, ex);
        }}
void botonagregar(){
if (jTextField1.getText().equals(""))
        /*{
            JOptionPane.showMessageDialog(null,"CAMPO CLAVE OBLIGATORIO");
        } else{*/
         if (jTextField2.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"CAMPO NOMBRE OBLIGATORIO");
        }
         else{
         if (jTextField3.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"CAMPO IMAIL OBLIGATORIO");
        }
         else{
         if (jTextField4.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"CAMPO TELEFONO OBLIGATORIO");
        } else{
          
         if (jTextField5.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"CAMPO DOMICILIO OBLIGATORIO");
        }
         else{
         if (jTextField6.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"CAMPO EDAD OBLIGATORIO");
        } else{
             if (fecha.getCalendar() == null)
             {
                 JOptionPane.showMessageDialog(null,"CAMPO FECHA OBLIGATORIO");
             } else{
                 
                 comparar();
                 jButton2.setEnabled(false);
                 jButton8.setEnabled(true);
             }}}}}}

}
void modificar(){
        conectar bd=new conectar();
        Connection conexion =bd.conexion();
        String a,b,c,d,e,f,g,h,i;
        String sql;
        char[] j;
        a=jTextField1.getText();
        b=jTextField2.getText();
        c=jTextField3.getText();
        d=jTextField4.getText();
        e=jTextField5.getText();
        f=jTextField6.getText();
        SimpleDateFormat dFormat= new SimpleDateFormat("yyyy-MM-dd");
        String date=dFormat.format(fecha.getDate());
        g=date;
        h=(String) jComboBox1.getSelectedItem();
        i=(String) jComboBox2.getSelectedItem();
        j=jPasswordField1.getPassword();
        
         sql ="UPDATE cita2 SET nombre='"+b+"',email='"+c+"',telefono='"+d+"',ciudad='"+e+"',edad='"+f+"',fecha_cita='"+g+"',hora_cita='"+h+"',comentario='"+i+"', contraseña" + String.valueOf(j) +"WHERE clave='"+a+"'";
        try {
            PreparedStatement pst = conexion. prepareStatement(sql);
       
            int w =pst.executeUpdate();
            if(w>0){
                JOptionPane.showMessageDialog(this, "actualizado...");
                bloquear();
            }
             } catch (SQLException ex) {
            Logger.getLogger(CITAS.class.getName()).log(Level.SEVERE, null, ex);
             }
        
        mostrar("");
    
    }
void eliminar(){
    
    String id;
        id=jTextField1.getText();
        conectar bd = new conectar();
        Connection conexion = bd.conexion();
        String sql;
        sql = "DELETE FROM cita2 WHERE clave='"+id+"'";

        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            int e=pst.executeUpdate();
            if (e>0){
                JOptionPane.showMessageDialog(this, "se ha eliminado");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        mostrar("");
    }
void agregarPACIENTES(){
conectar bd=new conectar();
        Connection conexion =bd.conexion();
        String a,b,c,d,e,f,g;
        String sql;
        a=model.getValueAt(jTable1.getSelectedRow(),0)+"";
        b=model.getValueAt(jTable1.getSelectedRow(),1)+"";
        c=model.getValueAt(jTable1.getSelectedRow(),2)+"";
        d=model.getValueAt(jTable1.getSelectedRow(),4)+"";
        e=model.getValueAt(jTable1.getSelectedRow(),3)+"";
        f=model.getValueAt(jTable1.getSelectedRow(),5)+"";
        g=model.getValueAt(jTable1.getSelectedRow(),8)+"";
         sql ="INSERT INTO pacientes (clave,nombre,apellido,direccion,telefono,edad,tratamiento) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = conexion. prepareStatement(sql);
       
            pst.setString(1,a);
            pst.setString(2,b);
            pst.setString(3,c);
            pst.setString(4,d);
            pst.setString(5,e);
            pst.setString(6,f);
            pst.setString(7,g);
            int w =pst.executeUpdate();
            if(w>0){
            }
            } catch (SQLException ex) {
            Logger.getLogger(clientes.class.getName()).log(Level.SEVERE, null, ex);
    }
        JOptionPane.showMessageDialog(this, "DATOS ENVIADOS");
}
void compararpacientes(){

}
void botonmodificar(){
    System.out.println("marca 1");
    conectar bd =new conectar ();
    System.out.println("marca 2");
        Connection conexion=bd.conexion();
    System.out.println("marca 3");
        String cap="";
        String tap="";
       String sql="SELECT * FROM cita2 WHERE fecha_cita='"+df.format(fecha.getDate())+"' && hora_cita='"+(String) jComboBox1.getSelectedItem()+"'";
        try {
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                cap=rs.getString("fecha_cita");
                tap=rs.getString("hora_cita");
            }
            if(tap.equals((String) jComboBox1.getSelectedItem())&& cap.equals(df.format(fecha.getDate())))
            {
              
                  JOptionPane.showMessageDialog(null, "CITA OCUPADA");
            }
            
            if(!tap.equals((String) jComboBox1.getSelectedItem())&& !cap.equals(df.format(fecha.getDate())))
            {
        modificar();
        bloquear();
        jButton1.setEnabled(true);
        limpiar();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CITAS.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jButton8 = new javax.swing.JButton();
        fecha = new com.toedter.calendar.JDateChooser();
        jButton9 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setMinimumSize(new java.awt.Dimension(100, 0));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel1.setText("CITAS");

        jLabel2.setText("CLAVE");

        jLabel3.setText("NOMBRE");

        jLabel4.setText("EMAIL");

        jLabel5.setText("TELEFONO");

        jLabel6.setText("DOMICILIO");

        jLabel7.setText("EDAD");

        jLabel8.setText("FECHA CITA");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel9.setText("HORA CITA");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", " " }));
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jComboBox1MouseReleased(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("NUEVO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("ACEPTAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("MODIFICAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("HECHO");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("CANCELAR CITA");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton6.setText("MENU");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("SALIR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel10.setText("SEXO");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MASCULINO", "FEMENINO" }));

        jButton8.setText("ENVIAR DATOS A LISTA DE PACIENTES");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        fecha.setDateFormatString("dd/MM/yyyy");

        jButton9.setText("CANCELAR");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel11.setText("PASSWORD");

        jPasswordField1.setMinimumSize(new java.awt.Dimension(100, 20));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(541, 541, 541)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(148, 148, 148))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel5)))
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jLabel10)
                                .addGap(92, 92, 92)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(67, 67, 67)
                                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(211, 211, 211)
                                        .addComponent(jLabel11))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44)
                                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(48, 48, 48)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(55, 55, 55))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton6)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jButton3)
                                .addGap(28, 28, 28)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addComponent(jButton7))))
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(485, 485, 485)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jButton9))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (jTextField1.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"CAMPO CLAVE OBLIGATORIO");
        } else{
         if (jTextField2.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"CAMPO NOMBRE OBLIGATORIO");
        }
         else{
         if (jTextField3.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"CAMPO IMAIL OBLIGATORIO");
        }
         else{
         if (jTextField4.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"CAMPO TELEFONO OBLIGATORIO");
        } else{
          
         if (jTextField5.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"CAMPO DOMICILIO OBLIGATORIO");
        }
         else{
         if (jTextField6.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"CAMPO EDAD OBLIGATORIO");
        } else{
             if (fecha.getCalendar() == null)
             {
                 JOptionPane.showMessageDialog(null,"CAMPO FECHA OBLIGATORIO");
             } else{
                 
                 botonmodificar();
             }}}}}}}
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try{
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            String clave = model.getValueAt(jTable1.getSelectedRow(),0)+"";
            String sql="SELECT * FROM cita2 where clave LIKE '%"+clave+"%'";

            conectar bd =new conectar ();
            Connection conexion=bd.conexion();
            Statement st=conexion.createStatement();
            ResultSet rs= st.executeQuery(sql);
            if(rs.next()){
                jTextField1.setText(rs.getString("clave"));
                jTextField2.setText(rs.getString("nombre"));
                jTextField3.setText(rs.getString("email"));
                jTextField4.setText(rs.getString("telefono"));
                jTextField5.setText(rs.getString("ciudad"));
                jTextField6.setText(rs.getString("edad"));
                jPasswordField1.setText(rs.getString("contraseña"));
                jComboBox2.setSelectedItem(rs.getString("sexo"));
                fecha.setDate(formateador.parse(rs.getString("fecha_cita")));
                jComboBox1.setSelectedItem(rs.getString("hora_cita"));
                desbloquear();
                jButton1.setEnabled(false);
                jButton2.setEnabled(false);
                jButton3.setEnabled(true);
                jButton5.setEnabled(true);
                jButton8.setEnabled(true);
                
            }
        }
        catch(Exception ex){
            System.out.println("error: " + ex);
            
        }
        /*// TODO add your handling code here:
        jTextField1.setText(model.getValueAt(jTable1.getSelectedRow(),0)+"");
         jTextField2.setText(model.getValueAt(jTable1.getSelectedRow(),1)+"");
         jTextField3.setText(model.getValueAt(jTable1.getSelectedRow(),2)+"");
         jTextField4.setText(model.getValueAt(jTable1.getSelectedRow(),3)+"");
         jTextField5.setText(model.getValueAt(jTable1.getSelectedRow(),4)+"");
         jTextField6.setText(model.getValueAt(jTable1.getSelectedRow(),5)+"");
        
         //df.format(fecha.getDate());
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(true);
        jButton5.setEnabled(true);
        jButton8.setEnabled(true);*/
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
       botonagregar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        eliminar();
        mostrar("");
        bloquear();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        // TODO add your handling code here:
        mostrar(df.format(fecha.getDate()));
        JOptionPane.showMessageDialog(this, "DEBE SELECIONER UNA HORA NO AGENDADA");
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jComboBox1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseReleased
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jComboBox1MouseReleased

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        
        agregarPACIENTES();
        bloquear();
        limpiar();
        jButton1.setEnabled(true);
        jButton8.setEnabled(false);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        desbloquear();
        jButton1.setEnabled(false);
        jButton2.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        desbloquear();
        jButton8.setEnabled(false);
        jButton3.setEnabled(false);
        jButton4.setEnabled(true);
        jButton5.setEnabled(false);
        jButton9.setEnabled(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        LOGIN a = new LOGIN();
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        menu a = new menu();
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
        // TODO add your handling code here:
        if (isEmail(jTextField3.getText()))
        {
         
        } else{
            JOptionPane.showMessageDialog(null,"Email incorrecto","Validar Email",JOptionPane.INFORMATION_MESSAGE);
           jTextField3.requestFocus();
        }
    }//GEN-LAST:event_jTextField3FocusLost

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
         bloquear();
        jButton1.setEnabled(true);
        limpiar();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
         mostrar("");
        bloquear();
        jButton1.setEnabled(true);
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CITAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CITAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CITAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CITAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CITAS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables

    private boolean isEmail(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\w\\-\\_\\]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo);
        if(mat.find()){
        return true;
        }
        else {
        return false;
        }
    }
}


package Control;

import Datos.DatosConserje;
import Model.Conserje;
import Visual.FrmFormatConserje;
import Visual.FrmFormatEstudiante;
import com.toedter.calendar.JDateChooser;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AdmConserje {
    DatosConserje datos = null;
    ArrayList <Conserje> conserjes = null;
    static AdmConserje admConserje = null;
    Conserje c = null;
    int posicion=0;
    
    private AdmConserje(){
        conserjes = new ArrayList<>();
    }
    public static AdmConserje getAdmConserje() {
        if(admConserje==null){
            admConserje = new AdmConserje();
        }
        return admConserje;
    }
    public ArrayList<Conserje> getConserjes() {
        return conserjes;
    }
    public void setConserjes(ArrayList<Conserje> conserjes) {
        this.conserjes = conserjes;
    }

    public boolean Validar(String cedula, String nombre, String sexo, Date fechaNac, String telefono, String direccion, String instruccion, String sector, String turno, Date fechaIngreso) {
        boolean x=false;
        
        if(!cedula.isEmpty() && !nombre.isEmpty() && !sexo.isEmpty() && fechaNac != null && !telefono.isEmpty() && !direccion.isEmpty()
                && !instruccion.isEmpty() && !sector.isEmpty() && !turno.isEmpty() && fechaIngreso != null){
            if(BuscarCedula(cedula, posicion)==-1)
            x=true;
        }else{
            x=false;
        }
        return x;
    }

    public String Guardar(String cedula, String nombre, String sexo, Date fechaNac, String telefono, String direccion, String instruccion, String sector, String turno, Date fechaIngreso) {
        String x="";
        Validacion v= new Validacion();
        char sexoC=v.aChar(sexo);
        LocalDate fechaLD = fechaNac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaIngresoLD = fechaNac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        c = new Conserje(cedula, nombre, sexoC, fechaLD, telefono, direccion, instruccion, fechaIngresoLD, sector, turno);
        conserjes.add(c);
        x=c.toString();
        return x;
    }
    public void llenarTabla(JTable tblContenido, JLabel lblTotal) {
        int fila=0, n=0;
        DefaultTableModel modelo=(DefaultTableModel) tblContenido.getModel();
        if(conserjes.size()>0){
            n=conserjes.size()-1;
            c=conserjes.get(n);
            fila=n;
            modelo.addRow(new Object[1]);
            tblContenido.setValueAt(c.getCedula(),fila, 0);
            tblContenido.setValueAt(c.getNombre(),fila, 1);
            tblContenido.setValueAt(c.verSexo(),fila, 2);
            tblContenido.setValueAt(c.getFechaNac(),fila, 3);
            tblContenido.setValueAt(c.getTelefono(),fila, 4);
            tblContenido.setValueAt(c.getDireccion(),fila, 5);
            tblContenido.setValueAt(c.calcularEdad(),fila, 6);  
            tblContenido.setValueAt(c.getFechaIng(),fila, 7);
            tblContenido.setValueAt(c.getTurno(),fila, 8);
            tblContenido.setValueAt(c.getInstruccion(),fila, 9);
            tblContenido.setValueAt(c.getSector(),fila, 10);
            tblContenido.setValueAt(c.aniosServicio(),fila, 11);
            tblContenido.setValueAt(c.pagosMensuales(),fila, 12);
            lblTotal.setText(conserjes.size()+"");
        }
    }

    public int BuscarCedula(String cedula, int posicion) {
        int i=-1;
        for(Conserje mabel: conserjes){
           if(mabel.getCedula().compareToIgnoreCase(cedula)==0){
               i=conserjes.indexOf(mabel);   
           }
        }
       return i;
    }

    public boolean ValidarEditar(String cedula, String nombre, String sexo, Date fechaNac, String telefono, String direccion, String instruccion, String sector, String turno, Date fechaIngreso) {
        Validacion v = new Validacion();
        boolean x = false;
        
        if(!cedula.isEmpty() && !nombre.isEmpty() && !sexo.isEmpty() && fechaNac != null && !telefono.isEmpty() && !direccion.isEmpty()
                && !instruccion.isEmpty() && !sector.isEmpty() && !turno.isEmpty() && fechaIngreso != null){
            LocalDate fechaLD = fechaNac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            conserjes.set(posicion, c);
            conserjes.get(posicion).setNombre(nombre);
            conserjes.get(posicion).setSexo(v.aChar(sexo));           
            conserjes.get(posicion).setFechaNac(fechaLD);
            conserjes.get(posicion).setTelefono(telefono);
            conserjes.get(posicion).setDireccion(direccion);
            conserjes.get(posicion).setInstruccion(instruccion);
            conserjes.get(posicion).setSector(sector);
            conserjes.get(posicion).setTurno(turno);
            conserjes.get(posicion).setFechaIng(fechaLD);
            x=true;
        }else{
            x=false;
        }
        return x;
    }

    public void EliminarTabla(JTable tblContenido, JLabel lblTotal) {
        DefaultTableModel modelo=(DefaultTableModel) tblContenido.getModel();
        int i=tblContenido.getSelectedRow();
        if(i!=-1){
            modelo.removeRow(i);
            conserjes.remove(i);
            lblTotal.setText(conserjes.size()+"");
        }else{
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        }
    }

    public void VisualizarSiguiente(FrmFormatConserje frmFormatConserje) {
        if(conserjes.size()>0 && conserjes.size()-1>posicion){
            posicion = posicion + 1;
            llenarCampos(frmFormatConserje);
            bloquearCampos(frmFormatConserje);
        }
    }

    public void VisualizarAtras(FrmFormatConserje frmFormatConserje) {
        if(conserjes.size()>0 && conserjes.size()-1>posicion){
            posicion = posicion - 1;
            llenarCampos(frmFormatConserje);
            bloquearCampos(frmFormatConserje); 
        }
    }

    public void VisualizarPrimero(FrmFormatConserje frmFormatConserje) {
        if(conserjes.size()>0){
            c=conserjes.get(0);
            llenarCampos(frmFormatConserje);
            bloquearCampos(frmFormatConserje);
        }
    }

    public void VisualizarUltimo(FrmFormatConserje frmFormatConserje) {
        if(conserjes.size()>0){
            c=conserjes.get(conserjes.size()-1);
            llenarCampos(frmFormatConserje);
            bloquearCampos(frmFormatConserje);
        }
    }

    private void llenarCampos(FrmFormatConserje frmFormatConserje) {
        Date x=null;
        Date y=null;
        frmFormatConserje.getTxtCedula().setText(c.getCedula());
        frmFormatConserje.getTxtNombre().setText(c.getNombre());
        frmFormatConserje.getCmbSexo().setSelectedItem(c.verSexo());
        x=Date.from(c.getFechaNac().atStartOfDay(ZoneId.systemDefault()).toInstant());
        frmFormatConserje.getDtcFechaNac().setDate(x);
        frmFormatConserje.getTxtTelefono().setText(c.getTelefono());
        frmFormatConserje.getTxtDireccion().setText(c.getDireccion());
        frmFormatConserje.getTxtInstruccion().setText(c.getInstruccion());
        frmFormatConserje.getTxtSector().setText(c.getSector());
        frmFormatConserje.getCmbTurno().setSelectedItem(c.getTurno());
        y=Date.from(c.getFechaIng().atStartOfDay(ZoneId.systemDefault()).toInstant());
        frmFormatConserje.getDtcFechaIngreso().setDate(y);
    }

    private void bloquearCampos(FrmFormatConserje frmFormatConserje)  {
        frmFormatConserje.getTxtCedula().setEditable(false);
        frmFormatConserje.getTxtNombre().setEditable(false);
        frmFormatConserje.getCmbSexo().setEditable(false);
        frmFormatConserje.getDtcFechaNac().setEnabled(false);
        frmFormatConserje.getTxtTelefono().setEditable(false);
        frmFormatConserje.getTxtDireccion().setEditable(false);
        frmFormatConserje.getDtcFechaIngreso().setEnabled(false);
        frmFormatConserje.getCmbTurno().getSelectedItem();
        frmFormatConserje.getTxtSector().getText();
        frmFormatConserje.getTxtInstruccion().getText();             
    }

    public void EditarRegistro(FrmFormatConserje frmFormatConserje, int posicion) {
        c=conserjes.get(posicion);
        llenarCampos(frmFormatConserje);
        bloquearCedula(frmFormatConserje);
    }

    public void guardarArchivoC() {
        String resultado = "";
        
        if(conserjes.size()>0){
            datos = new DatosConserje();
            resultado=datos.guardarArchivo(conserjes);
            if(resultado.charAt(0)=='1'){
                JOptionPane.showMessageDialog(null, "DATOS GUARDADOS SATISFACTORIAMENTE");
            }else if(resultado.charAt(0)=='0'){
                JOptionPane.showMessageDialog(null, "NO SE GUARDÓ LA INFORMACIÓN "+resultado
                , "¡ADVERTENCIA! ", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(null, "NO EXISTEN DATOS POR GUARDAR");
        }
    }

    public boolean leerArchivo(JTextArea txtContenido) {
        boolean x=false;
        String contenido ="";
        datos = new DatosConserje();
        contenido=datos.leerArchivo();
        
        if(!contenido.isEmpty()){
            txtContenido.append(contenido);
            txtContenido.setEditable(false);
            x=true;
        }else{
            x=false;
        }
        return x;
    }

    public boolean leerArchivo(JTextArea txtContenido, JLabel lblTotal) {
        boolean x=false;
        String contenido ="";
        datos = new DatosConserje();
        contenido=datos.leerArchivo();
        
        if(!contenido.isEmpty()){
            txtContenido.append(contenido);
            txtContenido.setEditable(false);
            x=true;
        }else{
            x=false;
        }
        return x;
    }
    
    public boolean abrirArchivo(JTextArea txtContenido, JLabel lblTotal){
        boolean x = false;
        String contenido  = "";
        int i=0, suma=0, contaL=0, contaV=0, c=0;
        datos = new DatosConserje();
        contenido=datos.lecturaArchivo();
        
        if(!contenido.isEmpty()){
            String[] cadenas = contenido.split(",");//Útil para total de lineas
            //JOptionPane.showMessageDialog(null, cadenas);
            for(i=0; i<cadenas.length; i++){
                txtContenido.append(cadenas[i]+",\n");           
            }for(i=0; i<contenido.length(); i++){
                if(Character.isDigit(contenido.charAt(i))){
                    //suma = suma+Integer.parseInt(contenido.charAt(i)+"");
                    suma = suma+contenido.charAt(i);
                    //c=contenido.charAt(i);
                    //JOptionPane.showMessageDialog(null, c);
                }
                if(Character.isLetter(contenido.charAt(i))){
                    contaL = contaL+contenido.charAt(i);
                }
                if((contenido.charAt(i)=='A' || contenido.charAt(i)=='a') || (contenido.charAt(i)=='E' || contenido.charAt(i)=='e')
                    || (contenido.charAt(i)=='I' || contenido.charAt(i)=='i') || (contenido.charAt(i)=='O' || contenido.charAt(i)=='o')
                    || (contenido.charAt(i)=='U' || contenido.charAt(i)=='u')){                    
                    contaV = contaV+contenido.charAt(i);
                }
            }
            txtContenido.append("La suma de los dígitos es: "+suma);
            txtContenido.append("\nEl total de letras es: "+contaL); 
            txtContenido.append("\nEl total de vocales es: "+contaV);
            txtContenido.setEditable(false);
            lblTotal.setText(cadenas.length+"");
            x=true;
        }
        return x;
    }
    
    public boolean abrirArchivoBinario(JTable tblEstudiante, JLabel lblTotal) throws ClassNotFoundException{
        boolean x= false;
        int resp = 0, r=0;
        JFileChooser cuadro = new JFileChooser();
        resp = cuadro.showOpenDialog(null);
        if(resp == JFileChooser.APPROVE_OPTION){
            datos = new DatosConserje();
            if(conserjes.size()==0){
                conserjes = datos.abrirArchivoB(cuadro.getSelectedFile());
            }else{
                r = JOptionPane.showConfirmDialog(null, "EXISTE INFORMACIÓN QUE NO HA GUARDADO"+
                        "DESEA REEMPLAZARLA?");
                if(r == JOptionPane.YES_OPTION){
                    conserjes = datos.abrirArchivoB(cuadro.getSelectedFile());
                }else{
                    x=false;
                }
            }if(conserjes.size()>0){
                x=true;
                llenarTabla(tblEstudiante, lblTotal);
            }
        }
        
        return x;
    }
    
    public void guardarArchivoB() {
        String contenido ="";
        int resp=0;
        JFileChooser cuadro = null;
        if(conserjes.size()>0 ){
            cuadro = new JFileChooser();
            resp = cuadro.showOpenDialog(null);
        }
        if(resp == JFileChooser.APPROVE_OPTION){
            datos = new DatosConserje();
            contenido=datos.guardarArchivoB(conserjes,cuadro.getSelectedFile());
        }else if(contenido.charAt(0)=='1'){
            JOptionPane.showMessageDialog(null, "DATOS GUARDADOS EXITOSAMENTE");
        }else if(contenido.charAt(0)=='0'){
            JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR LA INFORMACIÓN"+contenido);
               
        }else {
            JOptionPane.showMessageDialog(null, "NO EXISTEN DATOS POR GUARDAR");
        }
    }

    private void bloquearCedula(FrmFormatConserje frmFormatConserje) {
        frmFormatConserje.getTxtCedula().setEditable(false);
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.tools;

/**
 *
 * @author ve´ronica
 */
public class Cadenas 
{
    public final String[] tipoMensajesAlumnos = {"Cambio de Horario",
                                                  "Suspensiòn de pruebas y/o claces",
                                                  "Aviso solicitud de ayudantes",
                                                  "Envió archivo de planificación",
                                                  "Oferta laboral",
                                                  "Aviso general"};
    
    public final String[] tipoMensajesExAlumnos = {"Oferta Laboral",
                                                  "Oferta Post-grado",
                                                  "Becas de estudio",
                                                  "Aviso general"};
    
    public final String[] modulos = {"Cálculo I, Seccion A",
                                     "Cálculo I, Seccion B"
                                    };
    
    public final Object[] titulosTabla = {"Bloque","Lunes","Martes","Miércoles",
                                          "Jueves","Viernes","Sábado"};
    
    
    public final String[] tipoSuspension = {"Prueba o exámen","Clases","Actividad extraurricular"};
    
    public final String[] cantidadHoras = {"2 horas","4 horas","6 horas","8 horas","10 horas","12 horas"};

    public final String[] cantidadMeses = {"2 meses","4 meses","6 meses","8 meses","10 meses","12 meses"};

    public final String[] tipoPostGrado = {"Diplomado","Magister","Doctorado"};
    
    public final String[] bloques = {"1 (8:30 - 9:30)","2 (9:40 - 10:40)","3 (10:50 - 11:50)",
                                     "4 (12:00 - 13:00)","5 (13:10 - 14:10)","6 (14:20 - 15:20)",
                                     "7 (15:30 - 16:30)","8 (16:40 - 17:40)","9 (17:50 - 18:50)",
                                     "10 (19:00 - 20:00)","11 (20:10 - 21:10)"};
    
    public final String[] salas = {"Sala 11","Sala 12","Sala 13","Sala 14",
                                   "Sala 21","Sala 22","Sala 23","Sala 24",
                                   "Sala 25","Sala 26 L","Laboratorio clases","Laboratorio Avanzáda",
                                   "Sala S1","Sala S2","Sala E1","Sala E2",
                                   "Sala T1","Sala T2","Sala T3","Sala C1",
                                   "Sala C2","Sala C3","Sala C4"};

    
    public String[] getBloques() {
        return bloques;
    }

    public String[] getSalas() {
        return salas;
    }
    
    public String[] getTipoPostGrado() {
        return tipoPostGrado;
    }
   
    public String[] getCantidadMeses() {
        return cantidadMeses;
    }
    
    
    public String[] getCantidadHoras() {
        return cantidadHoras;
    }

    public String[] getTipoSuspension() {
        return tipoSuspension;
    }

    public Object[] getTitulosTabla() {
        return titulosTabla;
    }
     

    public String[] getModulos() {
        return modulos;
    }
    

    public String[] getTipoMensajesAlumnos() {
        return tipoMensajesAlumnos;
    }

    public String[] getTipoMensajesExAlumnos() {
        return tipoMensajesExAlumnos;
    }
    
}

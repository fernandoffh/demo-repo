/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos.sqlite;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author CDS-
 */
public class Tester {

    public static void main(String[] args) {
//        System.out.println("ENCUESTA");
//        System.out.println(Tools.TABLA_ENCUESTA);
//        System.out.println("*************************************************************************");
//
//        System.out.println("OPCIÓN");
//        System.out.println(Tools.TABLA_OPCION);
//        System.out.println("*************************************************************************");
//
//        System.out.println("TIPO PREGUNTA");
//        System.out.println(Tools.TABLA_TIPO_PREGUNTA);
//        System.out.println("*************************************************************************");
//
//        System.out.println("PREGUNTA");
//        System.out.println(Tools.TABLA_PREGUNTA);
//        System.out.println("*************************************************************************");
//
//        System.out.println("RESPUESTA");
//        System.out.println(Tools.TABLA_RESPUESTA);
//        System.out.println("*************************************************************************");
//
//        System.out.println("ENTREVISTA");
//        System.out.println(Tools.TABLA_ENTREVISTA);
//        System.out.println("*************************************************************************");
//        
//        
//        System.out.println("ESTADO");
//        System.out.println(Tools.TABLA_ESTADO);
//        System.out.println("*************************************************************************");
//        
//        System.out.println("MUNICIPIO");
//        System.out.println(Tools.TABLA_MUNICIPIO);
//        System.out.println("*************************************************************************");
        Calendar c = new GregorianCalendar();

        int dia = Integer.parseInt(c.get(Calendar.DATE)+"");
        int mes = Integer.parseInt(c.get(Calendar.MONTH)+"")+1;
        int annio = Integer.parseInt(c.get(Calendar.YEAR)+"");
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minutos = c.get(Calendar.MINUTE);
        int segundos = c.get(Calendar.SECOND);
        System.out.println(annio+"-"+mes+"-"+dia+" "+hora + ":" + minutos + ":" + segundos);


    }
}

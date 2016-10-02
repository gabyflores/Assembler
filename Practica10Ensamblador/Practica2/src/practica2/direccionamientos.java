package practica2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class direccionamientos {
 static  Vector<Linea> lineas =new Vector<Linea>();
 static  Vector<Linea> codigo =new Vector<Linea>();
    public static String Operando = null;
    static ArrayList<String> lista4 = new ArrayList();
    static ArrayList<String>  lista5 = new ArrayList();

    static File archivo,archivo1,objeto;
    public static void main(String[] args) {
      
        int contaorg=0;
        int bandorg=0;
        int contloc=-1,dirini=-1,contlocguarda=-1;
         try{
          archivo=new File("Temporal.txt");
          archivo.delete();
        
        }catch(Exception e){}
         
         try{
          archivo1=new File("Tabsim.txt");
          archivo1.delete();
        
        }catch(Exception e){}
        
         try{
          objeto=new File("Objeto.OBJ");
          /*FileWriter escribirArchivo3 = new FileWriter(objeto, true);
                            escribirArchivo3.write("HOLA" + "\r\n");
                            escribirArchivo3.close();*/
           objeto.delete();
        
        }catch(Exception e){}
         
        ArrayList<String>  lista2 = new ArrayList();
       
        ArrayList<String> lista = new ArrayList();

        ArrayList<String> lista3 = new ArrayList();

        ArrayList<String>  lista4 = new ArrayList();

        /* try {
         FileReader fr = new FileReader("C:/Users/Gaby/Desktop/GABY/Taller deProgsis/Practica2 .3/Practica2/src/practica2/E2P3.txt");
         BufferedReader br = new BufferedReader(fr);
         String cadena;
         //"C:/Users/Gaby/Desktop/GABY/Taller deProgsis/Practica2 .3/Practica2/src/practica2/PRAC2.txt"
         while ((cadena = br.readLine()) != null) {
         lista.add(cadena);
            
         }
         } catch (Exception ex) {
         }*/
        try {
            FileReader fr = new FileReader("C:/Users/Gaby/Desktop/GABY/Taller deProgsis/Practica2 .3/Practica2/src/practica2/TABOP.txt");
            BufferedReader br = new BufferedReader(fr);
            String cadena;

            while ((cadena = br.readLine()) != null) {
                lista2.add(cadena);

            }
        } catch (Exception ex) {
        }

        try {
            Scanner sc = new Scanner(System.in);
            String NomArchivo;
            System.out.println("Ingresa el nombre del Archivo:");
            NomArchivo = sc.nextLine();
           
            File f = new File(NomArchivo);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String cadena;
            while ((cadena = br.readLine()) != null) {
                lista.add(cadena);

            }
        } catch (Exception ex) {
        }

        comparacion com = new comparacion(lista2);

        for (int i = 0; i < lista.size(); i++) {
          
            Linea aux=new Linea();
            String Codop = null;
            String Etiqueta = null;
            Operando = null;
            String Comentario=null,comentaux;
            String lineaPro=lista.get(i);
            if(lineaPro.contains(";")==true)
            {  comentaux=lineaPro;
                Comentario=comentaux.substring(comentaux.indexOf(";"), comentaux.length());
               // lineaPro=lineaPro.replace(Comentario, "");
                aux.Comentario=Comentario;
                //aux.contloc=contlocguarda; 
                aux.contloc=contloc;
                aux.tDireccionamiento="Comentario";
                System.out.println(aux.contloc);
            }
            if (!lineaPro.isEmpty() && lineaPro.charAt(0) == ';') {
                System.out.println(";COMENTARIO" + "\n");
                
            } else {

                String[] partes = lineaPro.split("\\s+");// 
            
            
            
            /* if (!lista.get(i).isEmpty() && lista.get(i).charAt(0) == ';') {
                System.out.println("COMENTARIO" + "\n");
            } else {

                String[] partes = lista.get(i).split("\\s+");// esto pertite separar las palabras con los espacios y los tabuladores
                String Etiqueta = null;*/
                

                if (partes[0].isEmpty() == false) //si no hay nada en la parte primera a la izquierda del codop, se considera que la etiqueta no hay nada, es null
                {
                    Etiqueta = partes[0]; //etiqueta toma el valor de lo que hay a la izquierda de codop
                }//fin de if

                 //String Codop = null;
                 // Operando = null;

                if (partes.length >= 2) {
                    if (!partes[ 1].isEmpty()) //si no hay nada en la parte primera a la izquierda del codop, se considera que la etiqueta no hay nada, es null
                    {
                        Codop = partes[ 1]; //etiqueta toma el valor de lo que hay a la izquierda de codop
                    }

                    String[] ref = lista.get(i).split(Codop, 3); // despues del codop, toma el valor del corte de lo demas despues del codop
                    if (ref.length == 2) // es necesario ver que se usan las 2 mitades en base a codop, ya que si el valor es 2, hay informacion en las 2 partes que son etiqueta y operando, si no lo es, solo habra valor en la etiqueta y no es necesarioe ntrar el if, solo mostraria null en el operando 
                    {
                        Operando = ref[ 1].replaceFirst("(\\s+)", ""); //es lo primero despues del corte de codop, despues de un espacio o tabulador, toma lo demas despues de codop
                        if (Operando.isEmpty()) {
                            Operando = null;
                        }

                    }
                }

                //validacion de los datos
                System.out.println("ETIQUETA = " + Etiqueta);
                if (Etiqueta != null) {

                    if (!Etiqueta.matches("[a-zA-Z].*")) //el primer caracter es el punto
                    {    aux.bandError=true;
                        System.out.println("\tERROR: PRIMER CARACTER DEBE SER LETRA");
                    }

                    if (!Etiqueta.matches(".\\w*"))//busca cualquier caracter en la primer posicion, y despues cualquier caracter que sea tipo letra 
                    {   aux.bandError=true;
                        System.out.println("\tERROR: DEBE TENER CARACTERES VALIDOS (LETRAS, 0-9, _)");
                    }

                    if (!Etiqueta.matches(".{1,8}")) //cualquier caracter de tamaño 1 a 8
                    {   aux.bandError=true;
                        System.out.println("\tERROR: ETIQUETA MAXIMO DE TAMAÑO 8 CARACTERES");
                    }

                }

                System.out.println("CODOP = " + Codop);

                if (Codop != null) {
                    if (!Codop.matches("[a-zA-Z].*")) //primer caracter debe ser letra, asterisco es para 1 o mas matches
                    {
                        System.out.println("\tERROR: PRIMER CARACTER DEBE SER LETRA");
                        aux.bandError=true;
                    }

                    if (!Codop.matches(".{1,5}")) //cualquier caracter de tamaño 1 a 5
                    {
                        System.out.println("\tERROR: CODIGO DE OPERACION MAXIMO DE TAMAÑO 5 CARACTERES");
                        aux.bandError=true;
                    }
                    // la palabra debe contener cualquier cnt de caracteres de la a a la z y puntos
                    String[] grupos = Codop.split("\\.");

                    if (!Codop.matches("[a-zA-Z\\.]*") || grupos.length > 2) {  // la palabra debe contener cualquier cant de caracteres de la a a la z y puntos   
                        System.out.println("\tERROR: SOLO DEBE LETRAS Y UN PUNTO");
                        aux.bandError=true;
                    }

                } else {
                    System.out.println("\tERROR: DEBE DE HABER SIEMPRE UN CODOP");
                    aux.bandError=true;
                }

                System.out.println("OPERANDO = " + Operando);
                if (Operando != null) {
                    if (!Operando.matches("[\\p{Print}\\p{Blank}]*")) // /p es para todos los caracteres imprimibles
                    { //http://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html
                        System.out.println("\tERROR: DEBE TENER CARACTERES IMPRIMIBLES");
                        aux.bandError=true;
                    }
                }
 
                 Directivas dma = new Directivas();
                if (Codop != null) {
                   // int contloc=-1,dirini=-1;
                    int valor;
                    String hexa,hexacontloc,hexavalor,hexa1="";
                    //int bandorg=0;
                    String inserta="", inserta1="";
                    
                    if(Codop.toUpperCase().equals("ORG")){
                    if(Operando!=null && Etiqueta==null){
                    contaorg++;
                    if(contaorg==1){
                   
                    valor = dma.RegresaValor(Operando);
                    hexa=Integer.toHexString(valor);
                    hexa1=dma.CompletaHexa(hexa);
                    inserta =hexa1+"\t"+"null"+"\t"+Codop+"\t"+Operando +"\n";
                    contlocguarda=valor;
                    contloc=valor;
                    dirini=valor;
                    bandorg=1;
                    //System.out.println("" + inserta);
                    
                    }
                    if(contaorg>1){
                    System.out.println("Error: Debe haber solo un ORG");
                    aux.bandError=true;
                    }
                    }else{
                    System.out.println("Error: El ORG debe ir acompañado de un Operando y no debe haber Etiqueta ");
                    aux.bandError=true;
                    }
                    }
                    
                    int banderaencontrado;
                    banderaencontrado = dma.Encuentra(Codop);
                    //System.out.println("BanderaEncontrado"+banderaencontrado);
                    if (banderaencontrado == 1) {
                    valor=dma.Analizabusca(Codop,Operando);
                    //System.out.println("Analizabusca,bandorg"+bandorg);
                    if(valor>-1){
                    if(bandorg==1){
                    //System.out.println("bandorg1");
                    hexacontloc=Integer.toHexString(contloc);
                    // System.out.println(" Hexacontloc="+hexacontloc);
                    if(Etiqueta==null){
                    hexa1=dma.CompletaHexa(hexacontloc);
                    //System.out.println("HEXA1="+hexa1);
                    inserta=hexa1+"\t"+"null"+"\t"+Codop+"\t"+Operando;
                    }
                    if(Etiqueta!=null){
                    if(dma.EsEtiqueta(Etiqueta)==0)//-------
                    hexa1=dma.CompletaHexa(hexacontloc);
                    inserta1=Etiqueta+"\t"+hexa1;
                    inserta=hexa1+"\t"+Etiqueta+"\t"+Codop+"\t"+Operando;
                    }
                    contlocguarda=contloc;
                    contloc=contloc+valor;//--------------
                    
                     aux.tDireccionamiento=dma.tDir;
                     aux.Error=dma.Error;
                     aux.Tamano=dma.Tamano;
                    /* aux.codigoMaquinaPrevio=x.CodigoMaquinaPrevio;
                     aux.Tamano=x.tamanotot;
                     aux.ByF=x.byf;
                     */
                    
                    
                    
                    
                    }else if(bandorg==0){//PREGUNTAR QUE SE HACE EN ESTE CASO
                    inserta=valor+"\t"+Codop+"\t"+Operando;
                    //System.out.println("HOLA ERROR");
                    }
                    }
                         
                    }//banderaencontrado==1
                        if (banderaencontrado == 0 && !Codop.toUpperCase().equals("ORG") && !Codop.toUpperCase().equals("EQU") && !Codop.toUpperCase().equals("END") ) {
                        String bytes;
                        int nobytes;
                            if (com.compara(Codop) == true) {
                                Operandos x = new Operandos();
                                x.lista1 = com.lista;
                                String ope1 = Operando;
                               bytes =x.analiza(ope1);
                               
                               aux.tDireccionamiento=x.tDir;
                               aux.codigoMaquinaPrevio=x.CodigoMaquinaPrevio;
                               aux.Tamano=x.tamanotot;
                               aux.ByF=x.byf;
                               aux.Error=x.Error;
                               System.out.println("---------------->"+aux.tDireccionamiento);
                               
                               if(bytes!=""){
                               nobytes=Integer.parseInt(bytes);
                               hexacontloc=Integer.toHexString(contloc);
                               if(Etiqueta==null){
                               hexa1=dma.CompletaHexa(hexacontloc);
                               inserta=hexa1+"\t"+"null"+"\t"+Codop+"\t"+Operando;
                               }
                               //contloc=contloc+nobytes;
                               if(Etiqueta!=null){
                               if(dma.EsEtiqueta(Etiqueta)==0){//----
                               hexa1=dma.CompletaHexa(hexacontloc);
                               inserta=hexa1+"\t"+Etiqueta+"\t"+Codop+"\t"+Operando;   
                               inserta1=Etiqueta+"\t"+hexa1;
                               
                               }
                               }
                               contlocguarda=contloc;
                               contloc=contloc+nobytes;//------
                               }
                            }

                    }
                     
                        if(Codop.toUpperCase().equals("EQU")){
                        if(Etiqueta!=null && Operando!=null){
                        if(dma.EsEtiqueta(Etiqueta)==0){
                        valor=dma.RegresaValor(Operando);
                        if(valor>=0 && valor<=65535){
                        hexavalor=Integer.toHexString(valor);
                        hexa1=dma.CompletaHexa(hexavalor);
                        inserta=hexa1+"\t"+Etiqueta+"\t"+Codop+"\t"+Operando;
                        inserta1=Etiqueta+"\t"+hexa1;
                        
                        }else{
                        System.out.println("ERROR: Rago valido del 0 al 65535");
                        aux.bandError=true;
                        }}
                        
                        }else{
                        System.out.println("ERROR:Debe haber una Etiqueta y Operando");
                        aux.bandError=true;
                        }
                        }
                         if(Codop.toUpperCase().equals("END")){
                         if(Operando==null){
                         valor=contloc;
                         contlocguarda=valor;//aqui se modifico
                         if(Etiqueta==null){
                        
                         hexavalor=Integer.toHexString(valor);
                         hexa1=dma.CompletaHexa(hexavalor);
                         inserta=hexa1+"\t"+"null"+"\t"+Codop+"\t"+Operando;
                         //System.out.println("HOLA");
                         }
                         if(Etiqueta!=null){
                         if(dma.EsEtiqueta(Etiqueta)==0){
                         hexavalor=Integer.toHexString(valor);
                         hexa1=dma.CompletaHexa(hexavalor);
                         inserta=hexa1+"\t"+Etiqueta+"\t"+Codop+"\t"+Operando;
                         inserta1=Etiqueta+"\t"+hexa1;
                         }
                         }
                         }else if(Operando!=null){
                         
                         System.out.println("ERROR:EL END NO LLEVA OPERANDO");
                         aux.bandError=true;
                         }
                         
                         }
                         
                      if(inserta!=""){  
                 try {
                            archivo = new File("Temporal.txt");
                            FileWriter escribirArchivo = new FileWriter(archivo, true);
                            escribirArchivo.write(inserta + "\r\n");
                            escribirArchivo.close();
                        }//fin de try
                        catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.toString());
                        }}
                       
                         if(inserta1!=""){   
                 try {
                            archivo1 = new File("Tabsim.txt");
                            FileWriter escribirArchivo1 = new FileWriter(archivo1, true);
                            escribirArchivo1.write(inserta1 + "\r\n");
                            escribirArchivo1.close();
                        }//fin de try
                        catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.toString());
                        }
                       }

                      
                 //}
                }//Codop distinto de null
                    
                
                if (i == lista.size() - 1) {
                        if (Codop == null || !Codop.equalsIgnoreCase("END")) {
                            System.out.println("\nERROR: NO TERMINA EN END");
                            aux.bandError=true;
                        }
                    }
                    //com.compara(Codop);
                    System.out.println("\n");

                //}
            if(aux.bandError==false)
            {
            aux.Instruccion=Codop;
            aux.Etiqueta=Etiqueta;
            aux.Operador=Operando;
            aux.contloc=contlocguarda; 
            }

            }
        //validar END
            //lo primero es obtener la ultima linea, lo siguente es convertir l ultima linea en las 2 partes:eti, codop y operando con el objetivo de identificar el codop, una vez identificado, debes saber si es end en todas sus variaciones(MAY o min), ya que se ifentifica que es un end, 
 //lo primero es obtener la ultima linea, lo siguente es convertir l ultima linea en las 2 partes:eti, codop y operando con el objetivo de identificar el codop, una vez identificado, debes saber si es end en todas sus variaciones(MAY o min), ya que se ifentifica que es un end, 
           /* if(aux.bandError==false)
            {
            aux.Instruccion=Codop;
            aux.Etiqueta=Etiqueta;
            aux.Operador=Operando;
            aux.contloc=contlocguarda; 
            }*/
            //else
          // aux.Error="ERROR";
            agregaLista(aux);
            //lineas.add(aux);
            //System.out.println("Prueba:"+aux.Instruccion+aux.Etiqueta+aux.Operador+aux.contloc);
        }//fin for
       
       segundoPaso();
       
       //System.out.println("Hola despues de segundo paso");
       int longui;
       String longuihex;
       longui=contloc-dirini;
       longuihex=Integer.toHexString(longui);
       System.out.println("Longitud: "+longui+" bytes");
       System.out.println("Contloc en decimal: "+contloc+" - DIR INIC en decimal: "+dirini);
       String c1=Integer.toHexString(contloc);
       String c2=Integer.toHexString(dirini);
       c1=completacontaloc(c1);
       c2=completacontaloc(c2);
       System.out.println("Contloc en hexadecimal: "+c1+" - DIR INIC en hexadecimal: "+c2);
       longuihex=completacontaloc(longuihex);
       System.out.println("Longuitud en hexadecimal: "+longuihex);  
    }//fin de main
    
    static public void agregaLista(Linea aux)
    {
        lineas.add(aux);
    }
    static public void segundoPaso()
    {//System.out.println("Hola despues de segundo paso"+lineas.size());
        GeneraObjeto();
        try {
            File f = new File("Tabsim.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String cadena;
            while ((cadena = br.readLine()) != null) {
                lista5.add(cadena);

            }
        } catch (Exception ex) {
        }
        
        //System.out.println(lista5.toString());
       
        String contaloc,contlochexa;
        for(int i=0;i<lineas.size();i++)
        {    
           
            try
            {
                if(lineas.elementAt(i).bandError==false)
                {   if(lineas.elementAt(i).Instruccion.toUpperCase().equals("ORG") ||lineas.elementAt(i).Instruccion.toUpperCase().equals("END") )
                   {
                   System.out.println("Directiva");
                   contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                   contlochexa=completacontaloc(contaloc);
                    System.out.println("\t"+contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador);
                
                   
                   }
                if(lineas.elementAt(i).Instruccion.toUpperCase().equals("EQU")){
                System.out.println("Directiva");
                String cont;
                    cont=completaEqu(lineas.elementAt(i).Operador);
                 System.out.println("\t"+cont+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador);
                
                }
                    switch(lineas.elementAt(i).tDireccionamiento)
                {  case "Inherente":
                   System.out.println("Inherente");
                   if(lineas.elementAt(i).Error==""){
                   lineas.elementAt(i).codigoMaquina=generaInherentes(lineas.elementAt(i));
                   contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                   contlochexa=completacontaloc(contaloc);
                   System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                   }else
                   System.out.println(lineas.elementAt(i).Error);
                   break;         
                case "Directo":
                    System.out.println("Directo");
                    if(lineas.elementAt(i).Error==""){
                    lineas.elementAt(i).codigoMaquina=generaDirectos(lineas.elementAt(i));
                   contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                   contlochexa=completacontaloc(contaloc);
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    }else
                   System.out.println(lineas.elementAt(i).Error);
                    break;
                case "Extendido":
                    System.out.println("Extendido");
                    if(lineas.elementAt(i).Error==""){
                    lineas.elementAt(i).codigoMaquina=generaExtendidos(lineas.elementAt(i));
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                    break;
                case "Inmediato":
                    System.out.println("Inmediato");
                    
                    if(lineas.elementAt(i).Error==""){
                   
                    lineas.elementAt(i).codigoMaquina=generaInmediatos(lineas.elementAt(i));
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                    break;
                case "Indexadode5bits":
                    System.out.println("IDX 5 bits");
                    if(lineas.elementAt(i).Error==""){
                    lineas.elementAt(i).codigoMaquina=generaIndex5(lineas.elementAt(i));
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                    break;
                case "Indexadode9bits":
                    System.out.println("IDX1 9 bits");
                    if(lineas.elementAt(i).Error==""){
                    lineas.elementAt(i).codigoMaquina=generaIndex9(lineas.elementAt(i));
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                    break;
                case "Indexadode16bits":
                    System.out.println("IDX2 16 bits");
                    if(lineas.elementAt(i).Error==""){
                    //System.out.println("Entro a if");
                    lineas.elementAt(i).codigoMaquina=generaIndex16(lineas.elementAt(i));
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                     //System.out.println("Regreso");
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                    break;
                case "IndizadodeAutoPreincremento":
                    System.out.println("IDX Auto Preincremento");
                    if(lineas.elementAt(i).Error==""){
                    //System.out.println("Entro a if");
                    lineas.elementAt(i).codigoMaquina=generaAutoPrecremento(lineas.elementAt(i));
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                    //System.out.println("Regreso");
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                    break;
                case "IndizadodeAutoPredecremento":
                    System.out.println("IDX Auto PreDecremento");
                    if(lineas.elementAt(i).Error==""){
                    //System.out.println("Entro a if");
                    lineas.elementAt(i).codigoMaquina=generaAutoPredecremento(lineas.elementAt(i));
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                    //System.out.println("Regreso");
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                    break;
                case "IndizadodeAutoPosIncremento":
                    System.out.println("IDX Auto PosIncremento");
                    if(lineas.elementAt(i).Error==""){
                    //System.out.println("Entro a if");
                    lineas.elementAt(i).codigoMaquina=generaAutoPosIncremento(lineas.elementAt(i));
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                    //System.out.println("Regreso");
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                    break;
                case  "IndizadodeAutoPosDecremento":
                    System.out.println("IDX Auto PosDecremento");
                    if(lineas.elementAt(i).Error==""){
                    //System.out.println("Entro a if");
                    lineas.elementAt(i).codigoMaquina=generaAutoPosDecremento(lineas.elementAt(i));
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                    //System.out.println("Regreso");
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                    
                    break;
                case   "IndizadodeAcumulador":
                    System.out.println("IDX Indizado de Acumulador");
                    if(lineas.elementAt(i).Error==""){
                    //System.out.println("Entro a if");
                    lineas.elementAt(i).codigoMaquina=generaIndizadodeAcumulador(lineas.elementAt(i));
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                    //System.out.println("Regreso");
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                    
                    
                    break;
                case "IndexadodeAcumulaadorIndirecto":
                     System.out.println("[D,IDX] Indizado de Acumulador Indirecto");
                    if(lineas.elementAt(i).Error==""){
                    //System.out.println("Entro a if");
                    lineas.elementAt(i).codigoMaquina=generaIndizadoAcumuladorDIndirecto(lineas.elementAt(i));
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                    //System.out.println("Regreso");
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                    
                    break;
                case "IndezadoIndirecto16bits":
                    System.out.println("[IDX2] Indizado de 16 bits Indirecto");
                    if(lineas.elementAt(i).Error==""){
                    //System.out.println("Entro a if");
                    lineas.elementAt(i).codigoMaquina=generaIndizado16Bind(lineas.elementAt(i));
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                    //System.out.println("Regreso");
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                    break;
                case "REL":
                    System.out.println("Relativo");
                    if(lineas.elementAt(i).Error==""){
                    //System.out.println("Entro a if");
                    lineas.elementAt(i).codigoMaquina=generaRelativo(lineas.elementAt(i),i);
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                    //System.out.println("Regreso");
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                    break;
                 case "ExtendidoEtiqueta":
                     System.out.println("Extendido");
                    if(lineas.elementAt(i).Error==""){
                    //System.out.println("Entro a if");
                    lineas.elementAt(i).codigoMaquina=generaExtendidoEtiqueta(lineas.elementAt(i));
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                    //System.out.println("Regreso");
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                     break;    
                case "DirectivasConstantes1":
                    System.out.println("Directiva de Constantes de 1 byte");
                    if(lineas.elementAt(i).Error==""){
                    lineas.elementAt(i).codigoMaquina=generaDirectivasConstantes(lineas.elementAt(i));
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                    break;
                case "DirectivasConstantes2":
                     System.out.println("Directiva de Constantes de 2 bytes");
                    if(lineas.elementAt(i).Error==""){
                    lineas.elementAt(i).codigoMaquina=generaDirectivasConstantes2(lineas.elementAt(i));
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                    break;
                case "DirectivasCaracteres":
                    System.out.println("Directiva de Caracteres");
                    if(lineas.elementAt(i).Error==""){
                    lineas.elementAt(i).codigoMaquina=generaDirectivasCaracteres(lineas.elementAt(i));
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador+"\t"+lineas.elementAt(i).codigoMaquina);
                    
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                    break;
                case "DirectivasReservaEspacio":
                    System.out.println("Directiva de Reserva de Espacio");
                    if(lineas.elementAt(i).Error==""){
                    contaloc=Integer.toHexString(lineas.elementAt(i).contloc);
                    contlochexa=completacontaloc(contaloc);
                    System.out.println("\t"+ contlochexa+"\t"+lineas.elementAt(i).Etiqueta+"\t"+lineas.elementAt(i).Instruccion+"\t"+lineas.elementAt(i).Operador);
                    codigo.add(lineas.elementAt(i));
                    }else
                    System.out.println(lineas.elementAt(i).Error);
                    break;
               
                } 
                }
               
            }catch(Exception e)
            {
                 
            }
            
        }
        /*for(int b=0;b<codigo.size();b++){
        
        System.out.println(codigo.elementAt(b).tDireccionamiento+","+codigo.elementAt(b).Instruccion+","+codigo.elementAt(b).codigoMaquina+","+codigo.elementAt(b).Tamano+","+codigo.elementAt(b).contloc);
        
        }*/
        
        generaS1();
    }
    static public String aHexa(String num, String tdir)
    { 
        String  cadena="";
        
        if(num.contains("%")==false && num.contains("@")==false && num.contains("$")==false)
        {   int aux;
            
            if(num.charAt(0)=='#'){
            num=num.substring(num.indexOf("#")+1, num.length());
            aux=Integer.parseInt(num);
            cadena=Integer.toHexString(aux);
            
            }else{
            aux=Integer.parseInt(num);
            cadena=Integer.toHexString(aux);
            }
            
        }
        
        
        if(num.contains("%"))
        {
            num=num.substring(num.indexOf("%")+1, num.length());
            int aux=Integer.parseInt(num, 2);
            cadena=Integer.toHexString(aux);
            
            
        }
        if(num.contains("@"))
        {
            num=num.substring(num.indexOf("@")+1, num.length());
            int aux=Integer.parseInt(num, 8);
            cadena=Integer.toHexString(aux);
            
        }
        if(num.contains("$"))
        {
            num=num.substring(num.indexOf("$")+1, num.length());
           
            cadena=num;
            
        }
        
         if(tdir.equals("Directo"))
        {   
            if(cadena.length()<2)
              cadena="0"+cadena;
        }
        if(tdir.equals("Extendido")){
           if(cadena.length()==2){
              cadena="00"+cadena;
           
           }
           if(cadena.length()==3){
           
           cadena="0"+cadena;
           }
        
        }
        if(tdir.equals("Inmediato")){
          if(cadena.length()==1){
          cadena="0"+cadena;
          
          }
       }
        if(tdir.equals("DirectivasConstantes1")){
           if(cadena.length()==1)
           {
            cadena="0"+cadena;
           }
        }
        
        if(tdir.equals("DirectivasConstantes2")){
        if(cadena.length()<4){
        if(cadena.length()==1)
        cadena="000"+cadena;
        if(cadena.length()==2)
        cadena="00"+cadena;
        if(cadena.length()==3)
        cadena="0"+cadena;
        }
        }
        cadena=cadena.toUpperCase();
        return cadena;
    }
    
    
    static public String generaDirectos(Linea ln)
    {
        String cm="";
        String Operando;
        
        Operando=aHexa(ln.Operador,ln.tDireccionamiento);//No se necesita evaluar rangos pues ya fueron evaluados en operandos
        cm=ln.codigoMaquinaPrevio+Operando;
        ln.codigoMaquina=cm;
        codigo.add(ln);
        return cm;
    }
    
    static public String generaInherentes(Linea ln){
    String cm="";
    cm=ln.codigoMaquinaPrevio;
    ln.codigoMaquina=cm;
    codigo.add(ln);
    
    return cm;
    
    }
    static public String generaExtendidos(Linea ln){
    String cm="";
    
    String Operando;
        int valor;
        Operando=aHexa(ln.Operador,ln.tDireccionamiento);//No se necesita evaluar rangos pues ya fueron evaluados en operandos
        cm=ln.codigoMaquinaPrevio+Operando;
        ln.codigoMaquina=cm;
        codigo.add(ln);
    return cm;
    }
    
    static public String generaInmediatos(Linea ln){
    String cm="";
    
    String Operando;
    int valor;
    Operando=aHexa(ln.Operador,ln.tDireccionamiento);//No se necesita evaluar rangos pues ya fueron evaluados en operandos
    if(ln.ByF.equals("2")){
    if(Operando.length()<4)
    if(Operando.length()==2)
    Operando="00"+Operando;
    if(Operando.length()==3)
    Operando="0"+Operando;
    }
    
    cm=ln.codigoMaquinaPrevio+Operando;
    ln.codigoMaquina=cm;
    codigo.add(ln);
    return cm;
    }
    
    static public String generaIndex5(Linea ln){
    String cm="";
    String cadenaaux[],valor,registro="",xb="",valorabinario,subcadena="",parte1,parte2,parte1hexa,parte2hexa;
    int valornumerico,parte1vnumerico,parte2vnumerico;
   
    cadenaaux=ln.Operador.split(",");
    
    if(ln.Operador.startsWith(",")){
    
    valor="0";
    registro=cadenaaux[1];
    
    }else{
    
    valor=cadenaaux[0];
    registro=cadenaaux[1];
    
    }
    
    if(registro.toUpperCase().equals("X")){
    xb="000";
    }
    
     if(registro.toUpperCase().equals("Y")){
    xb="010";
    }
     
      if(registro.toUpperCase().equals("SP")){
    xb="100";
    }
       if(registro.toUpperCase().equals("PC")){
    xb="110";
    }
    valornumerico=Integer.parseInt(valor); //convierte a entero
    valorabinario=Integer.toBinaryString(valornumerico);// convierte a binario
    //System.out.println(valorabinario);
    
    if(valorabinario.length()>5){//negativos
    subcadena=valorabinario.substring(27,32);//toma solo valores necesarios
    //System.out.println(subcadena);
    xb=xb+subcadena;
    }
    
    if(valorabinario.length()<5){//positivos
    if(valorabinario.length()==1){
    valorabinario="0000"+valorabinario;
    }
    if(valorabinario.length()==2){
    valorabinario="000"+valorabinario;
    }
    if(valorabinario.length()==3){
    valorabinario="00"+valorabinario;
    }
    if(valorabinario.length()==4){
    valorabinario="0"+valorabinario;
    }
    xb=xb+valorabinario;
    }
    //System.out.println(valorabinario);
    //System.out.println(xb);
    
    parte1=xb.substring(0,4);
    parte2=xb.substring(4,8);
    //System.out.println(parte1+","+parte2);
    
    parte1vnumerico=Integer.parseInt(parte1,2);
    parte2vnumerico=Integer.parseInt(parte2,2);
    //System.out.println(parte1vnumerico+","+parte2vnumerico);
    
    parte1hexa=Integer.toHexString(parte1vnumerico);
    parte2hexa=Integer.toHexString(parte2vnumerico);
    //System.out.println(parte1hexa+","+parte2hexa);
    
    cm=ln.codigoMaquinaPrevio+parte1hexa+parte2hexa;
    
    cm=cm.toUpperCase();
     ln.codigoMaquina=cm;
     codigo.add(ln);
    return cm;
    }
    
    static public String generaIndex9(Linea ln){
    String cm="";
     String cadenaaux[],valor,registro="",xb="",valorahexa="",subcadena="",parte1,parte2,parte1hexa,parte2hexa;
    int valornumerico,parte1vnumerico,parte2vnumerico;
    
    cadenaaux=ln.Operador.split(",");
    valor=cadenaaux[0];
    registro=cadenaaux[1];
    
    if(registro.toUpperCase().equals("X")){
    xb="1110000";
    }
    
     if(registro.toUpperCase().equals("Y")){
    xb="1110100";
    }
     
      if(registro.toUpperCase().equals("SP")){
    xb="1111000";
    }
       if(registro.toUpperCase().equals("PC")){
    xb="1111100";
    }
       
       valornumerico=Integer.parseInt(valor);
       
       if(valornumerico<0){//negativos
       xb=xb+"1";
       valorahexa=Integer.toHexString(valornumerico);
       //System.out.println(valorahexa);
       if(valorahexa.length()>2){
       valorahexa=valorahexa.substring(6,8);
       }
       //System.out.println(valorahexa);
       }
       
       
       if(valornumerico>=0){//positivos
       xb=xb+"0";
       valorahexa=Integer.toHexString(valornumerico);
       //System.out.println(valorahexa);
       
       if(valorahexa.length()==1){
       valorahexa="0"+valorahexa;
       }
       //System.out.println(valorahexa);
       
       }
       
       //System.out.println(xb);
       parte1=xb.substring(0,4);
       //System.out.println(parte1);
       parte2=xb.substring(4,8);
       //System.out.println(parte2);
       
       parte1vnumerico=Integer.parseInt(parte1,2);
       parte2vnumerico=Integer.parseInt(parte2,2);
       //System.out.println(parte1vnumerico);
       //System.out.println(parte2vnumerico);
    
       parte1hexa=Integer.toHexString(parte1vnumerico);
       parte2hexa=Integer.toHexString(parte2vnumerico); 
       //System.out.println(parte1hexa);
       //System.out.println(parte2hexa);
       
    
    cm=ln.codigoMaquinaPrevio+parte1hexa+parte2hexa+valorahexa;
    cm=cm.toUpperCase();
    ln.codigoMaquina=cm;
    codigo.add(ln);
    return cm;
    }
    
    static public String generaIndex16(Linea ln){
    String cm="";
    String cadenaaux[],valor,registro="",xb="",valorahexa="",subcadena="",parte1,parte2,parte1hexa,parte2hexa;
    int valornumerico,parte1vnumerico,parte2vnumerico;
    //System.out.println("Hola funcion");
    cadenaaux=ln.Operador.split(",");
    valor=cadenaaux[0];
    registro=cadenaaux[1];
    
    if(registro.toUpperCase().equals("X")){
    xb="11100010";
    }
    
     if(registro.toUpperCase().equals("Y")){
    xb="11101010";
    }
     
      if(registro.toUpperCase().equals("SP")){
    xb="11110010";
    }
       if(registro.toUpperCase().equals("PC")){
    xb="111110110";
    }
    //System.out.println(xb);
    parte1=xb.substring(0,4);
    //System.out.println(parte1);
    parte2=xb.substring(4,8);
    //System.out.println(parte2);
    
    parte1vnumerico=Integer.parseInt(parte1,2);
    //System.out.println(parte1vnumerico);
    parte2vnumerico=Integer.parseInt(parte2,2);
    //System.out.println(parte2vnumerico);
    parte1hexa=Integer.toHexString(parte1vnumerico);
    //System.out.println(parte1hexa);
    parte2hexa=Integer.toHexString(parte2vnumerico);
    // System.out.println(parte2hexa);
    
    valornumerico=Integer.parseInt(valor);
    //System.out.println(valornumerico);
    valorahexa=Integer.toHexString(valornumerico);
    //System.out.println(valorahexa);
    if(valorahexa.length()<4){
    if(valorahexa.length()==1)
        valorahexa="000"+valorahexa;
    if(valorahexa.length()==2)
        valorahexa="00"+valorahexa;
    if(valorahexa.length()==3)
        valorahexa="0"+valorahexa;
    
    
    }
    
    cm=ln.codigoMaquinaPrevio+parte1hexa+parte2hexa+valorahexa;
    cm=cm.toUpperCase();
    ln.codigoMaquina=cm;
    codigo.add(ln);
    
    return cm;
    }
    
    static public String generaAutoPrecremento(Linea ln){
    String cm="";
    String cadenaaux[],valor,registro="",xb="",valorahexa="",subcadena="",parte1,parte2,parte1hexa,parte2hexa;
    int valornumerico,parte1vnumerico,parte2vnumerico;
    
    cadenaaux=ln.Operador.split(",");
    valor=cadenaaux[0];
    registro=cadenaaux[1];
     if(registro.toUpperCase().equals("+X")){
    xb="2";
    }
    
     if(registro.toUpperCase().equals("+Y")){
    xb="6";
    }
     
      if(registro.toUpperCase().equals("+SP")){
    xb="A";
    }
       if(registro.toUpperCase().equals("+PC")){
    xb="E";
    }
       valornumerico=Integer.parseInt(valor);
       valornumerico=valornumerico-1;
       valorahexa=Integer.toHexString(valornumerico);
    cm=ln.codigoMaquinaPrevio+xb+valorahexa;
    ln.codigoMaquina=cm;
    codigo.add(ln);
    return cm;
    }
    
    static public String generaAutoPredecremento(Linea ln){
    String cm="";
    String cadenaaux[],valor,registro="",xb="",valorahexa="",subcadena="",parte1,parte2,parte1hexa,parte2hexa;
    int valornumerico,parte1vnumerico,parte2vnumerico;
    
    cadenaaux=ln.Operador.split(",");
    valor=cadenaaux[0];
    registro=cadenaaux[1];
     if(registro.toUpperCase().equals("-X")){
    xb="2";
    }
    
     if(registro.toUpperCase().equals("-Y")){
    xb="6";
    }
     
      if(registro.toUpperCase().equals("-SP")){
    xb="A";
    }
       if(registro.toUpperCase().equals("-PC")){
    xb="E";
    }
       valornumerico=Integer.parseInt(valor);
       valornumerico=valornumerico*-1;
       valorahexa=Integer.toHexString(valornumerico);
       valorahexa=valorahexa.substring(7,8);
    cm=ln.codigoMaquinaPrevio+xb+valorahexa;
    cm=cm.toUpperCase();
    ln.codigoMaquina=cm;
    codigo.add(ln);
    return cm;
    }
    
    static public String generaAutoPosIncremento(Linea ln){
    String cm="";
    String cadenaaux[],valor,registro="",xb="",valorahexa="",subcadena="",parte1,parte2,parte1hexa,parte2hexa;
    int valornumerico,parte1vnumerico,parte2vnumerico;
    
    cadenaaux=ln.Operador.split(",");
    valor=cadenaaux[0];
    registro=cadenaaux[1];
     if(registro.toUpperCase().equals("X+")){
    xb="3";
    }
    
     if(registro.toUpperCase().equals("Y+")){
    xb="7";
    }
     
      if(registro.toUpperCase().equals("SP+")){
    xb="B";
    }
       if(registro.toUpperCase().equals("PC+")){
    xb="F";
    }
       valornumerico=Integer.parseInt(valor);
       valornumerico=valornumerico-1;
       valorahexa=Integer.toHexString(valornumerico);
       cm=ln.codigoMaquinaPrevio+xb+valorahexa;
    
    cm=cm.toUpperCase();
     ln.codigoMaquina=cm;
     codigo.add(ln);
    
    return cm;
    }
    
    static public String generaAutoPosDecremento(Linea ln){
    String cm="";
    String cadenaaux[],valor,registro="",xb="",valorahexa="",subcadena="",parte1,parte2,parte1hexa,parte2hexa;
    int valornumerico,parte1vnumerico,parte2vnumerico;
    
    cadenaaux=ln.Operador.split(",");
    valor=cadenaaux[0];
    registro=cadenaaux[1];
     if(registro.toUpperCase().equals("X-")){
    xb="3";
    }
    
     if(registro.toUpperCase().equals("Y-")){
    xb="7";
    }
     
      if(registro.toUpperCase().equals("SP-")){
    xb="B";
    }
       if(registro.toUpperCase().equals("PC-")){
    xb="F";
    }
       valornumerico=Integer.parseInt(valor);
       valornumerico=valornumerico*-1;
       valorahexa=Integer.toHexString(valornumerico);
       valorahexa=valorahexa.substring(7,8);
    cm=ln.codigoMaquinaPrevio+xb+valorahexa;
    cm=cm.toUpperCase();
     ln.codigoMaquina=cm;
     codigo.add(ln);
    
    return cm;
    }
    static public String generaIndizadodeAcumulador(Linea ln){
    String cm="";
    String cadenaaux[],valor,registro="",xb="",subcadena="",parte1,parte2,parte1hexa,parte2hexa,subcadena2;
    int parte1vnumerico,parte2vnumerico;
    //System.out.println("Hola funcion");
    cadenaaux=ln.Operador.split(",");
    valor=cadenaaux[0];
    registro=cadenaaux[1];
    
    if(registro.toUpperCase().equals("X")){
    xb="111001";
    }
    
     if(registro.toUpperCase().equals("Y")){
    xb="111011";
    }
     
      if(registro.toUpperCase().equals("SP")){
    xb="111101";
    }
       if(registro.toUpperCase().equals("PC")){
    xb="111111";
    }
       
    if(valor.toUpperCase().equals("A"))
    subcadena="00";
    if(valor.toUpperCase().equals("B"))
    subcadena="01";
    if(valor.toUpperCase().equals("D"))
    subcadena="10";
    
    subcadena2=xb+subcadena;
    //System.out.println(subcadena2);
    parte1=subcadena2.substring(0,4);
    parte2=subcadena2.substring(4,8);
    
    parte1vnumerico=Integer.parseInt(parte1,2);
    parte2vnumerico=Integer.parseInt(parte2,2);
    
    parte1hexa=Integer.toHexString(parte1vnumerico);
    parte2hexa=Integer.toHexString(parte2vnumerico);
    
    cm=ln.codigoMaquinaPrevio+parte1hexa+parte2hexa;
    cm=cm.toUpperCase();
    ln.codigoMaquina=cm;
    codigo.add(ln);
    return cm;
    }
    
    static public String generaIndizadoAcumuladorDIndirecto(Linea ln){
        String cm="";
        String Operando="";
        String formula="";
        String hex1="";
        //int valornumerico;
        String valor [] = ln.Operador.split("\\[|,|\\]");
        if(valor[2].toUpperCase().equals("X")){
            formula="11100111";
        }
        if(valor[2].toUpperCase().equals("Y")){
            formula="11101111";
        }
        if(valor[2].toUpperCase().equals("SP")){
            formula="11110111";
        }
         if(valor[2].toUpperCase().equals("PC")){
            formula="11111111";
        }
        /*valornumerico=Integer.parseInt(formula,2);
        System.out.println(valornumerico);
        hex1=Integer.toHexString(valornumerico);
        System.out.println(hex1);*/
        hex1=String.format("%2X", Long.parseLong(formula,2)); //es el hexadecimal del 2do bytes

        cm=ln.codigoMaquinaPrevio+hex1;
        cm=cm.toUpperCase();
         ln.codigoMaquina=cm;
         codigo.add(ln);
        return cm;
    }
    
    static public String generaIndizado16Bind(Linea ln){
        String cm="";
        String Operando="";
        String formula="";
        String faltante="";
        int valornumerico;
        String hex1="";
        
        String valor [] = ln.Operador.split("\\[|,|\\]");
        if(valor[2].toUpperCase().equals("X")){
            formula="11100011";
        }
        if(valor[2].toUpperCase().equals("Y")){
            formula="11101011";
        }
        if(valor[2].toUpperCase().equals("SP")){
            formula="11110011";
        }
        if(valor[2].toUpperCase().equals("PC")){
            formula="11111011";
        }
        
        hex1=String.format("%2X", Long.parseLong(formula,2)); //es el hexadecimal del 2do bytes
        //faltante=String.format("%2X", Long.parseLong(valor[1]));
        valornumerico=Integer.parseInt(valor[1]);
        faltante=Integer.toHexString(valornumerico);
        //System.out.println(valornumerico);
        //System.out.println("h"+hex1+",fal"+faltante);
        //System.out.println(faltante.length());
        
    if(faltante.length()<4){
    if(faltante.length()==1)
       faltante="000"+faltante;
        //System.out.println("HOLA:"+faltante);}
    if(faltante.length()==2)
       faltante="00"+faltante;
    if(faltante.length()==3)
       faltante="0"+faltante;
    
    
    }
        cm=ln.codigoMaquinaPrevio+hex1+faltante;
        cm=cm.toUpperCase();
        //System.out.println(cm);
        
    ln.codigoMaquina=cm;
    codigo.add(ln);   
    return cm;
    }
    
    
    static public String generaRelativo(Linea ln,int a){
    String cm="",valor1="",desplazamientohex="";
    int valor2,valor1entero,desplazamiento,ban=0;
    for(int i=0;i<lista5.size();i++){
   
    String split[]=lista5.get(i).split("\\s+");
    //System.out.println(split[0]);
    
    if(split[0].toUpperCase().equals(ln.Operador.toUpperCase())){
    valor1=split[1];
    ban=1;
    }
    }
    if(ban==0){
    cm="No se encontro la etiqueta ni su valor en el Tabsim";
    }
    //System.out.println("Etiqueta="+valor1);
    if(ban==1){
    valor2=lineas.elementAt(a+1).contloc;
    //System.out.println("Valor"+valor2);
    valor1entero=Integer.parseInt(valor1,16);
    
    desplazamiento=valor1entero-valor2;
    //System.out.println(desplazamiento);
    
    if(desplazamiento>=-128 && desplazamiento<=127 && ln.ByF.equals("1")){
    if(desplazamiento<0 ){//negativo
    desplazamientohex=Integer.toHexString(desplazamiento);
    //System.out.println(desplazamientohex);
   
    desplazamientohex=desplazamientohex.substring(6,8);
    }
    
    if(desplazamiento>=0){//positivo
     
    desplazamientohex=Integer.toHexString(desplazamiento);
    //System.out.println(desplazamientohex);
       if(desplazamientohex.length()==1){
    desplazamientohex="0"+desplazamientohex;
    }
    
    }
    
    cm=ln.codigoMaquinaPrevio+desplazamientohex;
    ln.codigoMaquina=cm;
    codigo.add(ln);
    
    
    }else if (desplazamiento<-128 || desplazamiento>127){
    cm="Error:se pasa del Rango valido para desplazamientos que es de -128 a 127 y -32768 a 32767";
    }
    
    if(desplazamiento>=-32768 && desplazamiento<=32767 && ln.ByF.equals("2")){
    if(desplazamiento<0 ){//negativo
    
    desplazamientohex=Integer.toHexString(desplazamiento);
    //System.out.println(desplazamientohex);
    desplazamientohex=desplazamientohex.substring(4,8);
    
    }
    
    if(desplazamiento>=0){//positivo
     
    desplazamientohex=Integer.toHexString(desplazamiento);
    //System.out.println(desplazamientohex);
      if(desplazamientohex.length()<4){
    if(desplazamientohex.length()==1)
       desplazamientohex="000"+desplazamientohex;
    if(desplazamientohex.length()==2)
       desplazamientohex="00"+desplazamientohex;
    if(desplazamientohex.length()==3)
       desplazamientohex="0"+desplazamientohex;
    }
    
    }
    
    cm=ln.codigoMaquinaPrevio+desplazamientohex;
    ln.codigoMaquina=cm;
    codigo.add(ln);
    
    }else if (desplazamiento<-32768 || desplazamiento>32767){
    cm="Error: se pasa del Rango valido para desplazamientos que es de -128 a 127 y -32768 a 32767";
    }
    
    cm=cm.toUpperCase();
    }
    return cm;
    }
    
    static public String generaExtendidoEtiqueta(Linea ln){
    String cm="",valor="";
    int band=0;
    
    for(int i=0;i<lista5.size();i++){
    String split[]=lista5.get(i).split("\\s+");
        if(split[0].toUpperCase().equals(ln.Operador.toUpperCase())){
        valor=split[1];
        band=1;
    }
    }
    if(band==0){
    cm="Error: La Etiqueta no se encontro en el Tabsim";
    }
    if(band==1){
    cm=ln.codigoMaquinaPrevio+valor;
    cm=cm.toUpperCase();
    ln.codigoMaquina=cm;
    codigo.add(ln);
    }
    
    return cm;
    }
    
    
    static public String generaDirectivasConstantes(Linea ln){
    String cm="",hexavalor;
    hexavalor=aHexa(ln.Operador,ln.tDireccionamiento);
    
    cm=hexavalor;
    cm=cm.toUpperCase();
    ln.codigoMaquina=cm;
    codigo.add(ln);
    return cm;
    }
    
    
    static public String generaDirectivasConstantes2(Linea ln){
    String cm="",hexavalor;
    hexavalor=aHexa(ln.Operador,ln.tDireccionamiento);
    
    cm=hexavalor;
    cm=cm.toUpperCase();
    ln.codigoMaquina=cm;
    codigo.add(ln);
    return cm;
    } 
    
    static public String generaDirectivasCaracteres(Linea ln){
    String cm="",valorcad;
    int valor;
    
    for(int i=1;i<ln.Operador.length()-1;i++){
    valor=ln.Operador.charAt(i);
    valorcad=Integer.toHexString(valor);
    cm=cm+valorcad;
    }
    cm=cm.toUpperCase();
    ln.codigoMaquina=cm;
    codigo.add(ln);
    return cm;
    }
    
    
    static public String completaEqu(String num){
    String cadena="";
    if(num.contains("%")==false && num.contains("@")==false && num.contains("$")==false)
        {   int aux;
            
            if(num.charAt(0)=='#'){
            num=num.substring(num.indexOf("#")+1, num.length());
            aux=Integer.parseInt(num);
            cadena=Integer.toHexString(aux);
            
            }else{
            aux=Integer.parseInt(num);
            cadena=Integer.toHexString(aux);
            }
            
        }
        
        
        if(num.contains("%"))
        {
            num=num.substring(num.indexOf("%")+1, num.length());
            int aux=Integer.parseInt(num, 2);
            cadena=Integer.toHexString(aux);
            
            
        }
        if(num.contains("@"))
        {
            num=num.substring(num.indexOf("@")+1, num.length());
            int aux=Integer.parseInt(num, 8);
            cadena=Integer.toHexString(aux);
            
        }
        if(num.contains("$"))
        {
            num=num.substring(num.indexOf("$")+1, num.length());
           
            cadena=num;
            
        }
        
       if(cadena.length()<4){
    if(cadena.length()==1)
       cadena="000"+cadena;
    if(cadena.length()==2)
       cadena="00"+cadena;
    if(cadena.length()==3)
       cadena="0"+cadena;
    }
    cadena=cadena.toUpperCase();
    return cadena;
    }
    
    static public String completacontaloc(String cont){
    String contcompleto="";
    
    if(cont.length()==1){
    contcompleto="000"+cont;
    
    }
    
    if(cont.length()==2){
    contcompleto="00"+cont;
    
    }
    
    if(cont.length()==3){
    contcompleto="0"+cont;
    
    }
     if(cont.length()==4){
    contcompleto=cont;
    
    }
    contcompleto=contcompleto.toUpperCase();
    return contcompleto;
    }
    
    static void GeneraObjeto(){
        String nombre="";
        String cadenacompleta="",S0,tamaniohex,chhex;
        int tamanio,suma=0,ch;
    try{
          
          objeto=new File("Objeto.OBJ");
          FileWriter escribirArchivo3 = new FileWriter(objeto, true);
                            //escribirArchivo3.write("HOLA" + "\r\n");
                            escribirArchivo3.close();
                    nombre=objeto.getAbsolutePath();
                    System.out.println(nombre);
                    //System.out.println(nombre.length());
        }catch(Exception e){}
    
    for (int i=0;i< nombre.length();i++){
    int c=nombre.charAt(i);
    suma=suma+c;
    
    String chexa=Integer.toHexString(c);// pasa el valor a hexa
    //System.out.println(chexa);
    cadenacompleta=cadenacompleta+chexa;//concatena los valores en hexa
    }
    //System.out.println("Archivo Generado");
   
    
    //System.out.println(cadenacompleta);
    //System.out.println("Tamaño"+cadenacompleta.length());
    tamanio=(cadenacompleta.length()/2)+4;//length es de datos,2 de dirrecion y unode checkusn y 0A
    tamaniohex=Integer.toHexString(tamanio);
    suma=suma+tamanio+10;
    ch=65535-suma;
    chhex=Integer.toHexString(ch);
    System.out.println(chhex);
    chhex=chhex.substring(2,4);
    System.out.println(chhex);
    S0="S0"+tamaniohex+"0000"+cadenacompleta+"0A"+chhex;
    S0=S0.toUpperCase();
    //System.out.println(S0);
     try{
          
          objeto=new File("Objeto.OBJ");
          FileWriter escribirArchivo3 = new FileWriter(objeto, true);
                            escribirArchivo3.write(S0+ "\r\n");
                            escribirArchivo3.close();
                    
        }catch(Exception e){}
    
    }
    
    static void generaS1(){
    String codigoGenerado="";
    String tipoDir="";
    String tamanioCodigo="";
    int espaciodisponible=32,espaciousado=0;
    int tamanioCodigoNum;
    String Cadena="",CadenaAux="";
    int guardaContLoc=0,guardaContLocaux,guardaContLocreini,band;
    if(!codigo.isEmpty()){
    for(int i=0; i< codigo.size();i++){
    band=0;
    codigoGenerado=codigo.elementAt(i).codigoMaquina;
    tipoDir=codigo.elementAt(i).tDireccionamiento;
    tamanioCodigo=codigo.elementAt(i).Tamano;
    tamanioCodigoNum=Integer.parseInt(tamanioCodigo);
    
    if(!tipoDir.equals("DirectivasReservaEspacio") && espaciodisponible>=(tamanioCodigoNum*2) ){
    if(espaciousado==0)
    guardaContLoc=codigo.elementAt(i).contloc;
    
    Cadena=Cadena+codigoGenerado;
    
    espaciousado=espaciousado+(tamanioCodigoNum*2);
    espaciodisponible=espaciodisponible-(tamanioCodigoNum*2);
    band=1;
    if(espaciousado==32){
    if(Cadena!="")
    System.out.println(Cadena);
    }
    }
    if(tipoDir.equals("DirectivasReservaEspacio") && espaciodisponible>=(tamanioCodigoNum*2) ){
    if(Cadena!=""){
    //System.out.println("Caso2");
    //System.out.println(Cadena+","+guardaContLoc);
    CadenaAux=CompletaCadenaObj(Cadena,guardaContLoc);
    EscribeArchivo(CadenaAux);
    }//Equivale a escribir en el archivo
    espaciodisponible=32;
    espaciousado=0;
    Cadena="";
    }
     if(!tipoDir.equals("DirectivasReservaEspacio") && espaciodisponible<(tamanioCodigoNum*2) && band==0 ){
    String partecodigo=codigoGenerado.substring(0,espaciodisponible);
    Cadena=Cadena+partecodigo;
    //System.out.println("Caso3");
      if(Cadena!=""){
      //System.out.println(Cadena+","+guardaContLoc);
      CadenaAux=CompletaCadenaObj(Cadena,guardaContLoc);
      EscribeArchivo(CadenaAux);
     }
    
    String partecodigo2=codigoGenerado.substring(espaciodisponible,codigoGenerado.length());
    espaciodisponible=espaciodisponible-partecodigo.length();
    if(espaciodisponible==0){
    guardaContLocaux=codigo.elementAt(i).contloc;
    guardaContLocreini=guardaContLocaux+(partecodigo.length()/2);
    guardaContLoc=guardaContLocreini;
    //System.out.println("Caso3");
    //if(Cadena!=""){
     //System.out.println(Cadena+","+guardaContLoc);
     //CadenaAux=CompletaCadenaObj(Cadena,guardaContLoc);
    //}
    Cadena="";
    
    }
    Cadena=Cadena+partecodigo2;
    espaciousado=partecodigo2.length();
    espaciodisponible=32-partecodigo2.length();
    }
     
     if(i==codigo.size()-1){
     //System.out.println("Caso1");
     guardaContLoc=codigo.elementAt(i).contloc;
     if(Cadena!=""){
     CadenaAux=CompletaCadenaObj(Cadena,guardaContLoc);
     EscribeArchivo(CadenaAux);
     }
     //System.out.println(Cadena+","+guardaContLoc);
     
     }
     
    }}
    CadenaAux="S9030000FC";
    EscribeArchivo(CadenaAux);
    }

   static public String CompletaCadenaObj(String Cad,int Cont){
   int tamaniocad,x=0,auxnum,acum=0,chk;
   String tamaniocadhexa,conthexa,auxhexa,chkhex;
   tamaniocad=(Cad.length()/2)+3;//dos de direccion y un de chk
   tamaniocadhexa=Integer.toHexString(tamaniocad);
   if(tamaniocadhexa.length()==1)
   tamaniocadhexa="0"+tamaniocadhexa;
   tamaniocadhexa=tamaniocadhexa.toUpperCase();
   conthexa=Integer.toHexString(Cont);
   conthexa=completacontaloc(conthexa);
   Cad=tamaniocadhexa+conthexa+Cad;
   
   //System.out.println(Cad);
   
   for(int y=2;y<Cad.length()+2;y=y+2){
   auxhexa=Cad.substring(x,y);
   //System.out.println(auxhexa);
   x=x+2;
   auxnum=Integer.parseInt(auxhexa,16);
   //System.out.println(auxnum);
   acum=acum+auxnum;
   //System.out.println(acum);
   }
   chk=65535-acum;
   chkhex=Integer.toHexString(chk);
   //System.out.println(chkhex);
   if(chkhex.length()==4)
   chkhex=chkhex.substring(2,4);
   chkhex=chkhex.toUpperCase();
   Cad="S1"+Cad+chkhex;
   
   //System.out.println(Cad);
   
   return Cad;
   }
   
   static void EscribeArchivo(String Cd){
   try{
          
          objeto=new File("Objeto.OBJ");
          FileWriter escribirArchivo3 = new FileWriter(objeto, true);
                            escribirArchivo3.write(Cd+ "\r\n");
                            escribirArchivo3.close();
                    
        }catch(Exception e){}
   
   
   }
}//fin 

//partes significa la separaicion de una linea de codigo de los 3 tipos de informacion


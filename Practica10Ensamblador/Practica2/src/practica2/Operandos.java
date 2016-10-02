/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practica2;
import java.util.ArrayList;


/**
 *
 * @author Gaby
 */
public class Operandos {
     
    String tDir="";
    ArrayList<String> lista1 = new ArrayList();
    String CodigoMaquinaPrevio;
    String Error;
    String tamanotot;
    String byf;
    
    //String Ope=direccionamientos.Operando;
     public String analiza(String Operando){
       
        //Inherente 
        String NoBY="";
        String NoBi;
        int banIn=0;
      CodigoMaquinaPrevio="";
       Error="";
       tamanotot="";
       byf="";
       tDir="";
        
        if(Operando==null){
           //System.out.println("Inherente");
        
        for(int i=0; i<lista1.size();i++){
            String aux[]=lista1.get(i).split("\\s+");
        
           //System.out.println(lista1.get(i));
            if(aux[2].equals("Inherente")){
                tDir="Inherente";
                CodigoMaquinaPrevio=aux[3];
                NoBY=aux[6];
                tamanotot=NoBY;
                byf="0";
                System.out.println("Inherente,  "+NoBY+" de Bytes");
                banIn=1;
            }
        }  
        
        if(banIn==0){
        
        System.out.println("Error:El codop no tiene direccionamiento Inherente");
        Error="Error:El codop no tiene direccionamiento Inherente";
        }
        }
        
        
        else{//Comienzan demas modos
             
         String aux;
         int valor=-1;
         int auxTamanio;//Inmediato
         int aux2Tamanio=-1;
         int aux3Tamanio;
         int asig=0;
         if(Operando.startsWith("#")){
             
             if(Operando.length()==1){
             System.out.println("Error: Debe haber algun valor despues del #, un simbolo de $ hexadecimal,%binario,@octal o decimal 0-9");
             Error="Error: Debe haber algun valor despues del #, un simbolo de $ hexadecimal,%binario,@octal o decimal 0-9";
             }
             else{//else de que hay algo despues del gato
             
             if(Operando.charAt(1)=='$'){
             aux=Operando.substring(Operando.indexOf("$")+1,Operando.length());
             if(aux.length()>=1){
             if(aux.matches("[0-9A-Fa-f]*")){
             valor=Integer.parseInt(aux,16);
             //System.out.println(""+valor);-----------------------------------------------------
             asig=1;
             }
             else {
             System.out.println("Error:Los caracteres validos en base hexadecimal son del 0-9,a-f,A-F");
             Error="Error:Los caracteres validos en base hexadecimal son del 0-9,a-f,A-F";
             }  }else{ 
                 
              System.out.println("Error:Debe haber algo despues del $,validos caracteres del 0-9,a-f,A-F");//if charat $
             Error="Error:Debe haber algo despues del $,validos caracteres del 0-9,a-f,A-F";
             } }
          else 
             if(Operando.charAt(1)=='@'){
             aux=Operando.substring(Operando.indexOf("@")+1,Operando.length());
             if(aux.length()>=1){
             if(aux.matches("[0-7]*")){
             valor=Integer.parseInt(aux,8);
            //System.out.println(""+valor);--------------------------------------------------------
             asig=1;
             }
             else {
             System.out.println("Error: Los caracteres validos en base octal son del 0-7");
             Error="Error: Los caracteres validos en base octal son del 0-7";
             } }else {
              System.out.println("Error:Debe haber algo despues del @");//if charat @
             Error="Error:Debe haber algo despues del @";
             }}
            else 
             if(Operando.charAt(1)=='%'){
             aux=Operando.substring(Operando.indexOf("%")+1,Operando.length());
             if(aux.length()>=1){
             if(aux.matches("[0-1]*")){
             valor=Integer.parseInt(aux,2);
             //System.out.println(""+valor);----------------------------------------------------------
             asig=1;
             }
             else if(!aux.matches("[0-1]*")){
                 
             System.out.println("Error: Los caracteres validos en binario son 0 y 1");
             Error="Error: Los caracteres validos en binario son 0 y 1";
             } }else{ 
             System.out.println("Error:Debe haber algo despues del %");//if charat %
             Error="Error:Debe haber algo despues del %";
             } }
            else 
             if(Operando.charAt(1)=='0' || Operando.charAt(1)=='1' || Operando.charAt(1)=='2' || Operando.charAt(1)=='3' || Operando.charAt(1)=='4' || Operando.charAt(1)=='5' || Operando.charAt(1)=='6' || Operando.charAt(1)=='7' ||  Operando.charAt(1)=='8' || Operando.charAt(1)=='9'){
             aux=Operando.substring(Operando.indexOf("#")+1,Operando.length());
             if(aux.matches("[0-9]*")){
             valor=Integer.parseInt(aux);
             //System.out.println(""+valor);----------------------------------------------------------
             asig=1;
             }
             else{ 
             System.out.println("Error: Los caracteres validos en decimal son del 0 al 9");
             Error="Error: Los caracteres validos en decimal son del 0 al 9";
             }}//if charat decimal
             else //ERROR DE BASES EN UBICACION INMEDIATO
             if((Operando.contains("@")&& Operando.charAt(1)!='@') || (Operando.contains("$") && Operando.charAt(1)!='$') || (Operando.contains("%") && Operando.charAt(1)!='%' )){
             System.out.println("Error: Los simbolos de bases $ hexadecimal, @ octal y % binario van en la segunda posicion del operando");
             Error="Error: Los simbolos de bases $ hexadecimal, @ octal y % binario van en la segunda posicion del operando";
             }//if charat decimal
             else {
             System.out.println("Error:Despues del # solo pueden ir %,$,@ y 0-9");
             Error="Error:Despues del # solo pueden ir %,$,@ y 0-9";
             }
             if(valor>=0 && valor<=255)
                 aux2Tamanio=8;
             if(valor>=256 && valor<=65535)
                 aux2Tamanio=16;
                //valor<=-1 && valor>=65535
             
              if((valor<0 || valor>=65536) && asig==1){//ERROR DE RANGO INMEDIATO
             System.out.println("Error: Fuera de rango,\nRango Valido para un Inmediato:8bits va de 0 a 255,16 bits de 0 a 65535");
             Error="Error: Fuera de rango,\nRango Valido para un Inmediato:8bits va de 0 a 255,16 bits de 0 a 65535";
              }
             //int NoBYIN;
             for(int a=0; a<lista1.size();a++){
             String aux1[]=lista1.get(a).split("\\s+");
            //System.out.println(lista1.get(i));
                if(aux1[2].equals("Inmediato")){
                 NoBi=aux1[5];
                auxTamanio=Integer.parseInt(NoBi);
                aux3Tamanio=8*auxTamanio;
                if(aux2Tamanio<=aux3Tamanio && aux2Tamanio>-1){
                NoBY=aux1[6];
                System.out.println("Inmediato de"+aux3Tamanio+"bits,de"+NoBY+"bytes");
                tDir="Inmediato";
                CodigoMaquinaPrevio=aux1[3];
                byf=NoBi;
                tamanotot=NoBY;
                }
                if(aux2Tamanio>aux3Tamanio){
                System.out.println("Error: No se soporta ese rango debido al numero de bytes por calcular");
                Error="Error: No se soporta ese rango debido al numero de bytes por calcular";
                }
                banIn=1;
             }
        }
            
           
         if(banIn==0){
        
        System.out.println("Error:El codop no tiene direccionamiento Inmediato");
        Error="Error:El codop no tiene direccionamiento Inmediato";
        }
        }//else de que hay algo despues del gato
        }//if stars inmediato
        if(Operando.contains("#") && Operando.charAt(0)!='#'){
        
        System.out.println("Error: El # debe ir al inicio del operando");
        Error="Error: El # debe ir al inicio del operando";
        }//exclusion del inmediato
        
        //comienza directo y extendido
        if(!Operando.contains("#") && !Operando.contains(",") && !Operando.contains("[") && !Operando.contains("]")){
        if(Operando.contains("%") || Operando.contains("$") || Operando.contains("@") || Operando.matches("[0-9A-Za-z]*")){
            //char primero=Operando.charAt(0);
            if(Operando.length()==1 && (!Operando.matches("[0-9]*"))){
             System.out.println("Error: Debe haber algun valor despues del %,$,@");
             Error="Error: Debe haber algun valor despues del %,$,@";
             }
            
            else{
                int banD=0;
                int banE=0;
            
         if(Operando.charAt(0)=='%'){
         aux=Operando.substring(Operando.indexOf("%")+1,Operando.length());
             if(aux.matches("[0-1]*")){
             valor=Integer.parseInt(aux,2);
            // System.out.println(""+valor);---------------------------------------------------------
             asig=1;
             }
             else if(!aux.matches("[0-1]*")){System.out.println("Error: Los caracteres validos en binario son 0 y 1");
             Error="Error: Los caracteres validos en binario son 0 y 1";}
          }
     else if(Operando.charAt(0)=='@'){
             aux=Operando.substring(Operando.indexOf("@")+1,Operando.length());
             if(aux.matches("[0-7]*")){
             valor=Integer.parseInt(aux,8);
            // System.out.println(""+valor);---------------------------------------------------------
             asig=1;
             }
             else {System.out.println("Error: Los caracteres validos en base octal son del 0-7");
             Error="Error: Los caracteres validos en base octal son del 0-7";
             }
             }//if charat @
         
      else  if(Operando.charAt(0)=='$'){
             aux=Operando.substring(Operando.indexOf("$")+1,Operando.length());
             if(aux.matches("[0-9A-Fa-f]*")){
             valor=Integer.parseInt(aux,16);
             //System.out.println(""+valor);----------------------------------------------------------
             asig=1;
             }
             else {System.out.println("Error:Los caracteres validos en base hexadecimal son del 0-9,a-f,A-F");
             Error="Error:Los caracteres validos en base hexadecimal son del 0-9,a-f,A-F";
             
             }}//if charat $ 
      else  if(Operando.charAt(0)=='0' || Operando.charAt(0)=='1' || Operando.charAt(0)=='2' || Operando.charAt(0)=='3' || Operando.charAt(0)=='4' || Operando.charAt(0)=='5' || Operando.charAt(0)=='6' || Operando.charAt(0)=='7' ||  Operando.charAt(0)=='8' || Operando.charAt(0)=='9'){
             aux=Operando.substring(Operando.indexOf("#")+1,Operando.length());
             if(aux.matches("[0-9]*")){
             valor=Integer.parseInt(aux);
             //System.out.println(""+valor);------------------------------------------------------------
             asig=1;
             }
             else {System.out.println("Error: Los caracteres validos en decimal son del 0 al 9");
             Error="Error: Los caracteres validos en decimal son del 0 al 9";
             } }//if charat decimal
      else  if((Operando.contains("@") && Operando.charAt(0)!='@') ||  (Operando.contains("$") && Operando.charAt(0)!='$') ||  (Operando.contains("%") && Operando.charAt(0)!='%')){
              //( (Operando.charAt(0)!='@' && Operando.matches("[[-]0-9]*")) || (Operando.charAt(0)!='%' && Operando.matches("[[-]0-9]*")) ||(Operando.charAt(0)!='$' && Operando.matches("[[-]0-9]*"))){
              //(Operando.charAt(0)!='@' ||  Operando.charAt(0)!='$' ||  Operando.charAt(0)!='%'){
             System.out.println("Error: Los simbolos de bases $ hexadecimal, @ octal y % binario van en la primera posicion del operando en directos y extendidos \n Error: En las etiquetas solo son validos A-Z,az,0-9 y _, debe iniciar con letra mayuscula o minuscula y tener maximo 8 caracteres");
             Error="Error: Los simbolos de bases $ hexadecimal, @ octal y % binario van en la primera posicion del operando en directos y extendidos \n Error: En las etiquetas solo son validos A-Z,az,0-9 y _, debe iniciar con letra mayuscula o minuscula y tener maximo 8 caracteres";
      }//if charat decimal
     // else System.out.println("Error: Los caracteres validos de inicio en directo y extendido son %,@,% y 0-9 ");
        
             if(valor>=0 && valor<=255)
                 aux2Tamanio=8;
             if(valor>=256 && valor<=65535)
                 aux2Tamanio=16;
             if(valor<0 && asig==1){
             System.out.println("Error: Los simbolos de bases $ hexadecimal, @ octal y % binario van en la primera posicion del operando en directos y extendidos \n Error: En las etiquetas solo son validos A-Z,az,0-9 y _, debe iniciar con letra mayuscula o minuscula y tener maximo 8 caracteres");
             Error="Error: Los simbolos de bases $ hexadecimal, @ octal y % binario van en la primera posicion del operando en directos y extendidos \n Error: En las etiquetas solo son validos A-Z,az,0-9 y _, debe iniciar con letra mayuscula o minuscula y tener maximo 8 caracteres";
             }
             if(valor>=65536){//ERROR DE RANGO INMEDIATO
             System.out.println("Error: Fuera de rango,\nRango Valido para un Extendido:256-65535");
             Error="Error: Fuera de rango,\nRango Valido para un Extendido:256-65535";
             }
             
             for(int b=0; b<lista1.size();b++){
             String aux1[]=lista1.get(b).split("\\s+");
             if(aux2Tamanio==8){
                if(aux1[2].equals("Directo")){
                
               
                NoBi=aux1[5];
                auxTamanio=Integer.parseInt(NoBi);
                aux3Tamanio=8*auxTamanio;
                if(aux2Tamanio<=aux3Tamanio && aux2Tamanio>-1){
                    
                    
                NoBY=aux1[6];
                System.out.println("Directo,de"+NoBY+"bytes");
                tDir="Directo";
                CodigoMaquinaPrevio=aux1[3];
                tamanotot=NoBY;
                byf=NoBi;
                }
                if(aux2Tamanio>aux3Tamanio){
                System.out.println("Error: No se soporta ese rango debido al numero de bytes por calcular en modo Directo de ese Codop");
                Error="Error: No se soporta ese rango debido al numero de bytes por calcular en modo Directo de ese Codop";
                }
                banD=1;
             }
                 
             
             }
             
             if(aux2Tamanio==16){
                if(aux1[2].equals("Extendido")){
            
               
                NoBi=aux1[5];
                auxTamanio=Integer.parseInt(NoBi);
                aux3Tamanio=8*auxTamanio;
                if(aux2Tamanio<=aux3Tamanio && aux2Tamanio>-1){
                  NoBY=aux1[6];
                 tDir="Extendido";
                 CodigoMaquinaPrevio=aux1[3];
                 tamanotot=NoBY;
                 byf=NoBi;
                
                System.out.println("Extendido,de"+NoBY+"bytes");
                }
                if(aux2Tamanio>aux3Tamanio){
                System.out.println("Error: No se soporta ese rango debido al numero de bytes por calcular en modo Extendido de ese Codop");
                Error="Error: No se soporta ese rango debido al numero de bytes por calcular en modo Extendido de ese Codop";
                
                }
                banE=1;
             }
                 
             
             }
        
              }
            
           if(banD==0 && aux2Tamanio==8){
        
                 System.out.println("Error:El codop no tiene direccionamiento Directo");
                 Error="Error:El codop no tiene direccionamiento Directo";
               }
         if(banE==0 && aux2Tamanio==16){
        
                 System.out.println("Error:El codop no tiene direccionamiento Extendido");
                 Error="Error:El codop no tiene direccionamiento Extendido";
               }
             
             
             
        
        }}}
        if((Operando.contains("%") && Operando.contains("$")) || (Operando.contains("%") && Operando.contains("@")) ||(Operando.contains("$")&& Operando.contains("@")) || (Operando.contains("%") && Operando.contains("$") && Operando.contains("@"))){
        System.out.println("Error: Solo puede haber una base en el operando, @ octal,% binaria,$ hexadecimal o 0-9 decimal ");
        Error="Error: Solo puede haber una base en el operando, @ octal,% binaria,$ hexadecimal o 0-9 decimal ";
        }//exclusion del directo y extendido
        
        
        //comienza extendido y rel
        
         if(!Operando.contains("#") && !Operando.contains(",") && !Operando.contains("[") && !Operando.contains("]") && !Operando.contains("%") && !Operando.contains("$") && !Operando.contains("@"))    
         {   int banError=0;
         if(Operando.charAt(0)!='0'&& Operando.charAt(0)!='1' && Operando.charAt(0)!='2' && Operando.charAt(0)!='3' && Operando.charAt(0)!='4' && Operando.charAt(0)!='5' && Operando.charAt(0)!='6' && Operando.charAt(0)!='7' && Operando.charAt(0)!='8' && Operando.charAt(0)!='9')
         {   
             if(!Operando.matches("[a-zA-Z].*")){
         
             System.out.println("\tERROR: PRIMER CARACTER DEBE SER LETRA\n Error: El rango valido en directos es de 0-255, y extendidos de 256-65535");
             Error="ERROR: PRIMER CARACTER DEBE SER LETRA\n Error: El rango valido en directos es de 0-255, y extendidos de 256-65535";
             banError=1;
              }
          if (!Operando.matches(".\\w*"))//busca cualquier caracter en la primer posicion, y despues cualquier caracter que sea tipo letra 
               {
              System.out.println("\tERROR: DEBE TENER CARACTERES VALIDOS (LETRAS, 0-9, _)");
              Error="\tERROR: DEBE TENER CARACTERES VALIDOS (LETRAS, 0-9, _)";
              banError=1;
               }
           if (!Operando.matches(".{1,8}")) //cualquier caracter de tamaño 1 a 8
              {
              System.out.println("\tERROR: Formato de Etiqueta Maximo de Tamaño 8 Caracteres");
              banError=1;
              Error="\tERROR: Formato de Etiqueta Maximo de Tamaño 8 Caracteres";
              }
           if(banError==0){//si cumple con una etiqueta
           int banExiste=0;
           for(int b=0; b<lista1.size();b++)
           {
           String aux1[]=lista1.get(b).split("\\s+");
           if(aux1[2].equals("Extendido")){
            NoBY=aux1[6];
            //NoBi=aux1[5];
           tDir="ExtendidoEtiqueta";
           tamanotot=NoBY;
           byf=aux1[5];
           CodigoMaquinaPrevio=aux1[3];
           System.out.println("Extendido de "+NoBY+" Bytes");
           banExiste=1; 
           }
           else if(aux1[2].equals("REL")){
           
           NoBY=aux1[6];
           NoBi=aux1[5];
           auxTamanio=Integer.parseInt(NoBi);
           aux3Tamanio=8*auxTamanio;
           tamanotot=NoBY;
           byf=NoBi;
           tDir="REL";
           CodigoMaquinaPrevio=aux1[3];
          // if(aux1[5].equals('1')){
           
           System.out.println("Relativo de "+aux3Tamanio+" bits,de "+NoBY+" Bytes");
           banExiste=1;
           
           }//termina REL
           
           }
           
           if(banExiste==0){
           
           System.out.println("Error: El codop que contiene la palabra u etiqueta no tiene direccionamiento que lo soporte \n Los modos que soporta como operando las etiquetas o palabras son RELATIVO Y EXTENDIDO");
           Error="Error: El codop que contiene la palabra u etiqueta no tiene direccionamiento que lo soporte \n Los modos que soporta como operando las etiquetas o palabras son RELATIVO Y EXTENDIDO";
           }
           
           
           }//Si cumple con una etiqueta
           }//charat0!=numero
         }// contains!=# y otros 
        
        
        //INDEXADOS 5,9 y 16 bits, prepos y acumulador
        String auxreg;
         String value[];
         int valorOpe;
         int banenc=0;
        // int tamanioope;
         if(Operando.contains(",") && !Operando.contains("[") && !Operando.contains("]")){
         //String auxreg;
         //String value[];
         //int valorOpe;
         value=Operando.split(","); 
        // int tamanioope2;
         //tamanioope2=value.length;
         if(Operando.endsWith(",")){
             System.out.println("Error:Debe haber un valor(registro) despues de la coma");
             Error="Error:Debe haber un valor(registro) despues de la coma";
         }
        else if(Operando.equals(",")){// || Operando.endsWith(",")){
             System.out.println("Error:Debe haber otro valor aparte de la coma");
             Error="Error:Debe haber otro valor aparte de la coma";
       }
       else  if(value[0].matches("[a-zA-Z]*") && !value[1].contains("-") && !value[1].contains("+") && !Operando.startsWith(",")){
             int tamanioope3;
             tamanioope3=value.length;
             if(tamanioope3==2){
             String auxreg2;
             auxreg2=value[0].toUpperCase();
               if(auxreg2.equals("A") || auxreg2.equals("B") || auxreg2.equals("D")){
             
                 auxreg=value[1].toUpperCase();
                 if(auxreg.equals("X") || auxreg.equals("Y") || auxreg.equals("SP") || auxreg.equals("PC")){
                     
                    for(int m=0; m<lista1.size();m++){
                    String aux1[]=lista1.get(m).split("\\s+");
                    if(aux1[2].equals("IDX")){
                    NoBY=aux1[6];
                    System.out.println("Indizado de Acumulador ,IDX, de "+NoBY+" bytes");
                    tDir="IndizadodeAcumulador";
                    CodigoMaquinaPrevio=aux1[3];
                    tamanotot=NoBY;
                    byf=aux1[5];
                    banenc=1;
              }
              }
              if(banenc==0){
              System.out.println("Error: El codop no tiene dirrecionamiento Indizado de Acumulador");
              Error="Error: El codop no tiene dirrecionamiento Indizado de Acumulador";
              }  
                     
                 //System.out.println("Indizado de Acumulador");
                     }else{System.out.println("Error: Los registros validos son X,Y,SP Y PC");
                     Error="Error: Los registros validos son X,Y,SP Y PC";
                 }
                    }else {System.out.println("Error: Solo puede haber A,B y D antes de la coma");
                    Error="Error: Solo puede haber A,B y D antes de la coma";
               }
             }else if(tamanioope3>2){
             System.out.println("Error: Solo puede haber 1 coma");
             Error="Error: Solo puede haber 1 coma";
             }
         
         }
         else if(value[1].contains("+") || value[1].contains("-") && !Operando.startsWith(",")){//preposincremento
         int tamanioope2;
         tamanioope2=value.length;
         if(tamanioope2==2){
             
             if(value[0].matches("[1-8]*")){
               auxreg=value[1].toUpperCase();
               
               if(auxreg.equals("+X") || auxreg.equals("+Y") || auxreg.equals("+SP")){
                   
              for(int h=0; h<lista1.size();h++){
              String aux1[]=lista1.get(h).split("\\s+");
              if(aux1[2].equals("IDX")){
              NoBY=aux1[6];
               System.out.println("Indizado de Auto Preincremento,IDX, de "+NoBY+" bytes");
               tDir="IndizadodeAutoPreincremento";
               tamanotot=NoBY;
               byf=aux1[5];
               CodigoMaquinaPrevio=aux1[3];
              banenc=1;
              }
              }
              if(banenc==0){
              System.out.println("Error: El codop no tiene dirrecionamiento Indizado de Auto Pre incremento");
              Error="Error: El codop no tiene dirrecionamiento Indizado de Auto Pre incremento";
              }
              }
               else if ( auxreg.equals("-X") || auxreg.equals("-Y") || auxreg.equals("-SP")){
                   
                    for(int j=0; j<lista1.size();j++){
              String aux1[]=lista1.get(j).split("\\s+");
              if(aux1[2].equals("IDX")){
              NoBY=aux1[6];
               System.out.println("Indizado de Auto Predecremento,IDX, de "+NoBY+" bytes");
               tDir="IndizadodeAutoPredecremento";
               tamanotot=NoBY;
               byf=aux1[5];
               CodigoMaquinaPrevio=aux1[3];
              banenc=1;
              }
              }
              if(banenc==0){
              System.out.println("Error: El codop no tiene dirrecionamiento Indizado de Auto Pre Decremento");
              Error="Error: El codop no tiene dirrecionamiento Indizado de Auto Pre Decremento";
              }
              }
              else if(auxreg.equals("X+") || auxreg.equals("Y+") || auxreg.equals("SP+")){
                    for(int k=0; k<lista1.size();k++){
              String aux1[]=lista1.get(k).split("\\s+");
              if(aux1[2].equals("IDX")){
              NoBY=aux1[6];
               System.out.println("Indizado de Auto PosIncremento,IDX, de "+NoBY+" bytes");
              banenc=1;
              tDir="IndizadodeAutoPosIncremento";
              tamanotot=NoBY;
              byf=aux1[5];
              CodigoMaquinaPrevio=aux1[3];
              }
              }
              if(banenc==0){
              System.out.println("Error: El codop no tiene dirrecionamiento Indizado de Auto Pos incremento");
              Error="Error: El codop no tiene dirrecionamiento Indizado de Auto Pos incremento";
              }
              
               //POSINCREMENTO
               } else if( auxreg.equals("X-") || auxreg.equals("Y-") || auxreg.equals("SP-")){
               //System.out.println("POSDECREMENTO");
               for(int l=0; l<lista1.size();l++){
               
                             String aux1[]=lista1.get(l).split("\\s+");
              if(aux1[2].equals("IDX")){
              NoBY=aux1[6];
               System.out.println("Indizado de PosDecremento,IDX, de "+NoBY+" bytes");
              tDir="IndizadodeAutoPosDecremento";
              tamanotot=NoBY;
              byf=aux1[5];
              CodigoMaquinaPrevio=aux1[3];
              banenc=1;
              }
              }
               if(banenc==0){
              System.out.println("Error: El codop no tiene dirrecionamiento Indizado de Auto PosDecremento");
              Error="Error: El codop no tiene dirrecionamiento Indizado de Auto PosDecremento";
               }
                 //POSDECREMENTO 
               }else{System.out.println("Error:Los unicos registros validos son X,Y,SP, con un + o - antes o despues del nombre de registro");
               Error="Error:Los unicos registros validos son X,Y,SP, con un + o - antes o despues del nombre de registro";
               }
             
               }else if(!value[0].matches("[1-8]*")){
               System.out.println("Error: Los caracteres validos antes de la coma son del 1-8");
               Error="Error: Los caracteres validos antes de la coma son del 1-8";
               }

             
             
         
         }else if(tamanioope2>2){
         System.out.println("Error: Solo puede haber 1 coma");
         Error="Error: Solo puede haber 1 coma";
         }
        }//termina preposincremento
        else if(Operando.startsWith(",")){
         
         aux=Operando.substring(Operando.indexOf(",")+1,Operando.length());
                       auxreg=aux.toUpperCase();
              if(auxreg.equals("X") || auxreg.equals("Y") || auxreg.equals("SP") || auxreg.equals("PC"))
              {
                   
                                 for(int c=0; c<lista1.size();c++){
                                 
                                 String aux1[]=lista1.get(c).split("\\s+");
                             if(aux1[2].equals("IDX")){
                                  NoBY=aux1[6];
                                   System.out.println("Indexado de 5 bits,IDX,de  "+NoBY+" bytes");
                                   banenc=1;
                                   tDir="Indexadode5bits";
                                   tamanotot=NoBY;
                                   byf=aux1[5];
                                   CodigoMaquinaPrevio=aux1[3];
                               }//existe
                                 
                               }//for
              if(banenc==0){
              System.out.println("Error: El codop no tiene indizado de 5 bits como direccionamiento");
              Error= "Error: El codop no tiene indizado de 5 bits como direccionamiento";}
              }//registro correcto
              else {System.out.println("Error: Los registros validos son X,Y,SP,PC en mayusculas o minusculas");
              Error="Error: Los registros validos son X,Y,SP,PC en mayusculas o minusculas";
              }}//comienza con ,
         else if (!value[1].contains("+") && !value[1].contains("-") && !value[0].matches("[a-zA-Z]*")){
        int tamanioope;
         String gui[];
         int nogui;
        
         //value=Operando.split(",");
         tamanioope=value.length;   
         if(tamanioope==2){
         
         //System.out.println(" "+tamanioope);-----------------------------------------------
         if(value[0].matches("[[-]0-9]*")){//[[-]0-9]*
         //System.out.println(" "+value[0]);---------------------------------------------------
        // System.out.println(" "+value[1]);---------------------------------------------------
        
         if(value[0].contains("-")){
         gui=value[0].split("-");
         nogui=gui.length;
         //System.out.println(" "+nogui);-------------------------------------------------------
         //NEGATIVOS
         if(nogui==2 && value[0].startsWith("-")){
             auxreg=value[1].toUpperCase();
             if(auxreg.equals("X") || auxreg.equals("Y") || auxreg.equals("SP") || auxreg.equals("PC")){
             //System.out.println("Es negativo");-----------------------------------------------
             valorOpe=Integer.parseInt(value[0]);
             //System.out.println(" "+valorOpe);-------------------------------------------------
             
             if(valorOpe>=-16 && valorOpe<=15){
             for(int d=0; d<lista1.size();d++){
              String aux1[]=lista1.get(d).split("\\s+");
              if(aux1[2].equals("IDX")){
              NoBY=aux1[6];
              System.out.println("Indizado de 5 bits,IDX, de "+NoBY+" bytes");
              banenc=1;
              tDir="Indexadode5bits";
              tamanotot=NoBY;
              byf=aux1[5];
              CodigoMaquinaPrevio=aux1[3];
              }
             
             }
             if(banenc==0){
             System.out.println("Error:El rango esta dentro del indizado de 5 bits, pero el codop no tiene ese direccionamiento");
             Error="Error:El rango esta dentro del indizado de 5 bits, pero el codop no tiene ese direccionamiento";
             }
             }//5 bits
             
             if((valorOpe>=-256 && valorOpe<=-17) || (valorOpe>=16 && valorOpe<=255)){
             for(int e=0; e<lista1.size();e++){
              String aux1[]=lista1.get(e).split("\\s+");
              if(aux1[2].equals("IDX1")){
              NoBY=aux1[6];
              System.out.println("Indizado de 9 bits,IDX1, de "+NoBY+" bytes");
              tDir="Indexadode9bits";
              tamanotot=NoBY;
              byf=aux1[5];
              CodigoMaquinaPrevio=aux1[3];
              banenc=1;
              }
             
             }
             if(banenc==0){
             System.out.println("Error:El rango esta dentro del indizado de 9 bits, pero el codop no tiene ese direccionamiento");
             Error="Error:El rango esta dentro del indizado de 9 bits, pero el codop no tiene ese direccionamiento";
             }
             }//9 bits
             
             if(valorOpe<-256){
             System.out.println("Error: Rango Invalido el mas pequeño es -256");
             Error="Error: Rango Invalido el mas pequeño es -256";
             }
             
              
             
             }//registro correcto
             else{ System.out.println("Error: Los registros validos son X,Y,PC,SP");
             Error="Error: Los registros validos son X,Y,PC,SP";
             }}
         else if((nogui>2 && !value[0].startsWith("-")) || (nogui>2 && value[0].startsWith("-"))){
         
         System.out.println("Error: No puede haber mas de dos - y si lo hay debe ser el primer caracter");
         Error="Error: No puede haber mas de dos - y si lo hay debe ser el primer caracter";
         }
         }//else
         else if(!value[0].contains("-")){//POSITIVO
              auxreg=value[1].toUpperCase();
             if(auxreg.equals("X") || auxreg.equals("Y") || auxreg.equals("SP") || auxreg.equals("PC")){ 
             //System.out.println("Es positivo");-------------------------------------------
             valorOpe=Integer.parseInt(value[0]);
              //System.out.println(" "+valorOpe);--------------------------------------------
              
              if(valorOpe>=-16 && valorOpe<=15){
             for(int d=0; d<lista1.size();d++){
              String aux1[]=lista1.get(d).split("\\s+");
              if(aux1[2].equals("IDX")){
              NoBY=aux1[6];
              System.out.println("Indizado de 5 bits,IDX, de "+NoBY+" bytes");
              banenc=1;
              tDir="Indexadode5bits";
              tamanotot=NoBY;
              byf=aux1[5];
              CodigoMaquinaPrevio=aux1[3];
              }
             
             }
             if(banenc==0){
             System.out.println("Error:El rango esta dentro del indizado de 5 bits, pero el codop no tiene ese direccionamiento");
             Error="Error:El rango esta dentro del indizado de 5 bits, pero el codop no tiene ese direccionamiento";
             }
             }//5 bits
              
               if((valorOpe>=-256 && valorOpe<=-17) || (valorOpe>=16 && valorOpe<=255)){
             for(int e=0; e<lista1.size();e++){
              String aux1[]=lista1.get(e).split("\\s+");
              if(aux1[2].equals("IDX1")){
              NoBY=aux1[6];
              System.out.println("Indizado de 9 bits,IDX1, de "+NoBY+" bytes");
              tDir="Indexadode9bits";
              tamanotot=NoBY;
              byf=aux1[5];
              CodigoMaquinaPrevio=aux1[3];
              banenc=1;
              }
             
             }
             if(banenc==0){
             System.out.println("Error:El rango esta dentro del indizado de 9 bits, pero el codop no tiene ese direccionamiento");
             Error="Error:El rango esta dentro del indizado de 9 bits, pero el codop no tiene ese direccionamiento";
             }
             }//9 bits
             if(valorOpe>=256 && valorOpe<=65535) {
             for(int f=0; f<lista1.size();f++){
              String aux1[]=lista1.get(f).split("\\s+");
              if(aux1[2].equals("IDX2")){
              NoBY=aux1[6];
              System.out.println("Indizado de 16 bits,IDX2, de "+NoBY+" bytes");
              banenc=1;
              tDir="Indexadode16bits";
              tamanotot=NoBY;
              byf=aux1[5];
              CodigoMaquinaPrevio=aux1[3];
              }
             
             }
             if(banenc==0){
             System.out.println("Error:El rango esta dentro del indizado de 16 bits, pero el codop no tiene ese direccionamiento");
             Error="Error:El rango esta dentro del indizado de 16 bits, pero el codop no tiene ese direccionamiento";
             }
             }//16 bits
             
             if(valorOpe>65535){
             System.out.println("Error:Rango Invalido el rango mayor es 65535");
             Error="Error:Rango Invalido el rango mayor es 65535";
             }
             
             }//registro correcto
             else{ System.out.println("Error: Los registros validos son X,Y,PC,SP");
             Error="Error: Los registros validos son X,Y,PC,SP";
             }}
        
        
         } //caracteres validos
         else if (!value[0].matches("[[-]0-9]*")){
             System.out.println("Error: Los caracteres validos son 0-9 y - antes de la coma, despues de la coma los registros validos son:X,Y,SP Y PC");  
             Error="Error: Los caracteres validos son 0-9 y - antes de la coma, despues de la coma los registros validos son:X,Y,SP Y PC";
         }
        
      }//si el tamaño es 2
         else if(tamanioope>2){
         System.out.println("Error: Solo puede haber una coma");
         Error="Error: Solo puede haber una coma";
         }  
        
        
         }//else de k no comienza con ,
        
        
       }//contains ,  
         
         //COMIENZAN INDIRECTO DE 16 BITS Y ACUMULADOR INDIRECTO
         //if((Operando.contains(",") && Operando.contains("[") && Operando.contains("]")) || (Operando.contains(",") && Operando.contains("[") || (Operando.contains(",") && Operando.contains("]"))) )
         if((Operando.contains(",") && Operando.contains("[") && Operando.contains("]")) || (Operando.contains(",") && Operando.contains("[")) || (Operando.contains(",") && Operando.contains("]")) || Operando.contains("[") || Operando.contains("]") ){
            if(Operando.startsWith("[") && Operando.endsWith("]") ){
             int tamanioope1;
             value=Operando.split(","); 
             tamanioope1=value.length;
             if(tamanioope1==2){
                 String parte1;
                 parte1=value[0].substring(value[0].indexOf("[")+1,value[0].length());
                 int ocu=0;
                 //parte2=value[1].substring(value[1])
              if(value[0].startsWith("[D") || value[0].startsWith("[d")){
              auxreg=value[1].toUpperCase();
              if(auxreg.equals("X]") || auxreg.equals("Y]") || auxreg.equals("SP]") || auxreg.equals("PC]")){
              
                for(int f=0; f<lista1.size();f++){
              String aux1[]=lista1.get(f).split("\\s+");
              if(aux1[2].equals("[D,IDX]")){
              NoBY=aux1[6];
              System.out.println("Indizado de Acumulador Indirecto,[D,IDX] de "+NoBY+" bytes");
              tDir="IndexadodeAcumulaadorIndirecto";
              tamanotot=NoBY;
              byf=aux1[5];
              CodigoMaquinaPrevio=aux1[3];
              banenc=1;
              ocu=1;
              }
               }
                if(banenc==0){
                System.out.println("Error: El codop no tiene direccionamiento [D,IDX]");
                Error="Error: El codop no tiene direccionamiento [D,IDX]";
                }
                  
              
              }else{ System.out.println("Error:Los registros validos despues de la coma son X,Y,SP,PC");
              ocu=1;
              Error="Error:Los registros validos despues de la coma son X,Y,SP,PC";
              }
              }else if(!value[0].startsWith("[D") && !value[0].startsWith("[d") && parte1.matches("\\D*")){//![[-]0-9]*
              System.out.println("Error: El unico caracter valido antes de la coma es D o d");
              Error="Error: El unico caracter valido antes de la coma es D o d";
              ocu=1;
              }
              
              if(parte1.matches("[0-9]*")){
              auxreg=value[1].toUpperCase();
              if(auxreg.equals("X]") || auxreg.equals("Y]") || auxreg.equals("SP]") || auxreg.equals("PC]")){
               valorOpe=Integer.parseInt(parte1);
               if(valorOpe>=0 && valorOpe<=65535){
                  for(int g=0; g<lista1.size();g++){
              String aux1[]=lista1.get(g).split("\\s+");
              if(aux1[2].equals("[IDX2]")){
              NoBY=aux1[6];
              System.out.println("Indizado indirecto de 16 bits,[IDX2] de "+NoBY+" bytes");
              tDir="IndezadoIndirecto16bits";
              tamanotot=NoBY;
              byf=aux1[5];
              CodigoMaquinaPrevio=aux1[3];
              banenc=1;  }
               }
                if(banenc==0){
                System.out.println("Error: El codop no tiene direccionamiento [IDX2] indirecto de 16 bits");
                 Error= "Error: El codop no tiene direccionamiento [IDX2] indirecto de 16 bits";}
              } else {System.out.println("Error:Fuera de rango rango valido en indirecto de 16 bits es 0 a 65535");
                 Error="Error:Fuera de rango rango valido en indirecto de 16 bits es 0 a 65535";
                }  
              
              
              }else{ System.out.println("Error:Los registros validos despues de la coma son X,Y,SP,PC");
              Error="Error:Los registros validos despues de la coma son X,Y,SP,PC";
              }
              }else if(!parte1.matches("[0-9]*") && ocu==0){
               //(!value[0].startsWith("[D") && !value[0].startsWith("[d") && !parte1.matches("[a-zA-Z]*"))
               System.out.println("Error:Los unicos caracteres validos antes de la coma son 0-9, rango valido: 0 a 65535");
              Error= "Error:Los unicos caracteres validos antes de la coma son 0-9, rango valido: 0 a 65535";
              }
                 
                 
             
             }else if(tamanioope1>2 || tamanioope1>=0){
             System.out.println("Error:Solo puede haber una coma o al menos una, con un valor y un registro");    
             Error="Error:Solo puede haber una coma o al menos una, con un valor y un registro";
             }//error
            
            }
            else if(!Operando.startsWith("[") || !Operando.endsWith("]") || (Operando.startsWith("[") && !Operando.endsWith("]")) || (!Operando.startsWith("[") && Operando.endsWith("]")) || Operando.equals("[") || Operando.equals("]") ){
            System.out.println("Error: Siempre debe haber [ al inicio y ] al final ");
            Error="Error: Siempre debe haber [ al inicio y ] al final ";
            }//error
         
         
         
         }//TERMINAN INDIRECTO DE 16 BITS Y ACUMULADOR INDIRECTO

        }//else demas modos
        return NoBY;
     }//analiza
}//fin
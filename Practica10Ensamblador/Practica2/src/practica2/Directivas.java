/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practica2;

/**
 *
 * @author Gaby
 */
public class Directivas {
String tDir="";
String Error="";
String Tamano="";
public int Encuentra(String Directiva){
int bandera=0;

if(Directiva.toUpperCase().equals("DW") || Directiva.toUpperCase().equals("DB") || Directiva.toUpperCase().equals("DC.W") || Directiva.toUpperCase().equals("DC.B") || Directiva.toUpperCase().equals("FCB") || Directiva.toUpperCase().equals("FDB") || Directiva.toUpperCase().equals("FCC")){
bandera=1;}
if(Directiva.toUpperCase().equals("DS") || Directiva.toUpperCase().equals("DS.B") || Directiva.toUpperCase().equals("DS.W") || Directiva.toUpperCase().equals("RMB") || Directiva.toUpperCase().equals("RMW")){
bandera=1;
}


return bandera;
}

public int Analizabusca(String Directiva1,String Operan2){
    
    int bandorgencontrado=0;
    int valorope=-1,valoraux;
   //-----------------------------Directivas de Constantes de 1byte-----------
   if(Directiva1.toUpperCase().equals("DB") || Directiva1.toUpperCase().equals("DC.B") || Directiva1.toUpperCase().equals("FCB")){
   if(Operan2!=null){
   valoraux=RegresaValor(Operan2);
   if(valoraux>=0 && valoraux<=255){
   valorope=1;
   tDir="DirectivasConstantes1";
   Tamano="1";
   }else if(valoraux>-1){
   System.out.println("ERROR:Fuera de rango, rango valido del 0 al 255");
   Error="ERROR:Fuera de rango, rango valido del 0 al 255";
   }
   }else{
   System.out.println("ERROR:El operando no debe ser nulo");
   Error="ERROR:El operando no debe ser nulo";
   }
  
   }
  //-----------------------------------Directivas de Constantes de 2bytes----------------------   
   if(Directiva1.toUpperCase().equals("DW") || Directiva1.toUpperCase().equals("DC.W") || Directiva1.toUpperCase().equals("FDB")){
   if(Operan2!=null){
   valoraux=RegresaValor(Operan2);
   if(valoraux>=0 && valoraux<=65535){
   valorope=2;
   tDir="DirectivasConstantes2";
   Tamano="2";
   }else if(valoraux>-1){
   System.out.println("ERROR:Fuera de rengo, rango valido del 0 a 65535");
   Error="ERROR:Fuera de rango, rango valido del 0 a 65535";
   }
   }else{
   System.out.println("ERROR:El operando no debe ser nulo");
   Error="ERROR:El operando no debe ser nulo";
   }
  
   }
   //------------------De caracteres-------------------------------------------------
   if(Directiva1.toUpperCase().equals("FCC")){
   int tamanio;
  
  if(Operan2!=null){
  tamanio=Operan2.length();
  if(Operan2.length()==1 && Operan2.charAt(0)=='"'){
   System.out.println("ERROR: Debe haber comillas al inicio y final del operando");
   Error="ERROR: Debe haber comillas al inicio y final del operando";
  }
  if(Operan2.charAt(0)=='"' && Operan2.charAt(tamanio-1)=='"'){
   valorope=tamanio-2;
   tDir="DirectivasCaracteres"; 
   Tamano=Integer.toString(valorope);
  }else{
  System.out.println("ERROR: Debe haber comillas al inicio y final del operando");
  Error="ERROR: Debe haber comillas al inicio y final del operando";
  } }else{
  System.out.println("Error: Debe haber operando");
  Error="Error: Debe haber operando";
  }
  
  }
  //-----------------------Reserva de espacio de memoria 1byte----------------------------
  if(Directiva1.toUpperCase().equals("DS") || Directiva1.toUpperCase().equals("DS.B") || Directiva1.toUpperCase().equals("RMB")){
   if(Operan2!=null){
   valoraux=RegresaValor(Operan2);
   if(valoraux>=0 && valoraux<=65535){
   valorope=valoraux;
   tDir="DirectivasReservaEspacio";
   Tamano="1";
   }else if(valoraux>-1){
   System.out.println("ERROR:Fuera de rango, rango valido del 0 a 65535");
   Error="ERROR:Fuera de rango, rango valido del 0 a 65535";
   }
   }else{
   System.out.println("ERROR:El operando no debe ser nulo");
   Error="ERROR:El operando no debe ser nulo";
   }
   
   }
  
  //----------------------------Reserva de espacio de memoria 2bytes-----------------
  if(Directiva1.toUpperCase().equals("DS.W") || Directiva1.toUpperCase().equals("RMW")){
   if(Operan2!=null){
   valoraux=RegresaValor(Operan2);
   if(valoraux>=0 && valoraux<=65535){
   valorope=valoraux*2;
   tDir="DirectivasReservaEspacio";
   Tamano="2";
   }else if(valoraux>-1){
   System.out.println("ERROR:Fuera de rango, rango valido del 0 a 65535");
   Error="ERROR:Fuera de rango, rango valido del 0 a 65535";
   }
   }else{
   System.out.println("ERROR:El operando no debe ser nulo");
   Error="ERROR:El operando no debe ser nulo";
   }
  }
  
  
   
   
   

return valorope;
}

public int RegresaValor(String Ope2){
 String aux;
 int valor=-1;


 if(Ope2.length()==1 && (!Ope2.matches("[0-9]*"))){
             System.out.println("Error: Debe haber algun valor despues del %,$,@");
             }   
 
 if(Ope2.charAt(0)=='%'){
         aux=Ope2.substring(Ope2.indexOf("%")+1,Ope2.length());
             if(aux.matches("[0-1]*")){
             valor=Integer.parseInt(aux,2);
            // System.out.println(""+valor);---------------------------------------------------------
            // asig=1;
             }
             else System.out.println("Error: Los caracteres validos en binario son 0 y 1");
          }
     else if(Ope2.charAt(0)=='@'){
             aux=Ope2.substring(Ope2.indexOf("@")+1,Ope2.length());
             if(aux.matches("[0-7]*")){
             valor=Integer.parseInt(aux,8);
            // System.out.println(""+valor);---------------------------------------------------------
            // asig=1;
             }
             else System.out.println("Error: Los caracteres validos en base octal son del 0-7");
             }//if charat @
         
      else  if(Ope2.charAt(0)=='$'){
             aux=Ope2.substring(Ope2.indexOf("$")+1,Ope2.length());
             if(aux.matches("[0-9A-Fa-f]*")){
             valor=Integer.parseInt(aux,16);
             //System.out.println(""+valor);----------------------------------------------------------
             //asig=1;
             }
             else System.out.println("Error:Los caracteres validos en base hexadecimal son del 0-9,a-f,A-F");
             }//if charat $ 
      else  if(Ope2.charAt(0)=='0' || Ope2.charAt(0)=='1' || Ope2.charAt(0)=='2' || Ope2.charAt(0)=='3' || Ope2.charAt(0)=='4' || Ope2.charAt(0)=='5' || Ope2.charAt(0)=='6' || Ope2.charAt(0)=='7' ||  Ope2.charAt(0)=='8' || Ope2.charAt(0)=='9'){
             //aux=Ope2.substring(Ope2.indexOf("#")+1,Ope2.length());
             if(Ope2.matches("[0-9]*")){
             valor=Integer.parseInt(Ope2);
             //System.out.println(""+valor);------------------------------------------------------------
             //asig=1;
             }
             else System.out.println("Error: Los caracteres validos en decimal son del 0 al 9");
             } 
      else  if((Ope2.contains("@") && Ope2.charAt(0)!='@') ||  (Ope2.contains("$") && Ope2.charAt(0)!='$') ||  (Ope2.contains("%") && Ope2.charAt(0)!='%')){
              //( (Operando.charAt(0)!='@' && Operando.matches("[[-]0-9]*")) || (Operando.charAt(0)!='%' && Operando.matches("[[-]0-9]*")) ||(Operando.charAt(0)!='$' && Operando.matches("[[-]0-9]*"))){
              //(Operando.charAt(0)!='@' ||  Operando.charAt(0)!='$' ||  Operando.charAt(0)!='%'){
             System.out.println("Error: Los simbolos de bases $ hexadecimal, @ octal y % binario van en la primera posicion del operando en directos y extendidos \n Error: En las etiquetas solo son validos A-Z,az,0-9 y _, debe iniciar con letra mayuscula o minuscula y tener maximo 8 caracteres");
             }

 return valor;
}

public int  EsEtiqueta(String Eti){
  int  bandeti=0;
                    if (!Eti.matches("[a-zA-Z].*")) //el primer caracter es el punto
                    {   bandeti=1;
                        System.out.println("\tERROR: PRIMER CARACTER DEBE SER LETRA");
                        
                    }

                    if (!Eti.matches(".\\w*"))//busca cualquier caracter en la primer posicion, y despues cualquier caracter que sea tipo letra 
                    {   bandeti=1;
                        System.out.println("\tERROR: DEBE TENER CARACTERES VALIDOS (LETRAS, 0-9, _)");
                    }

                    if (!Eti.matches(".{1,8}")) //cualquier caracter de tamaño 1 a 8
                    {   bandeti=1;
                        System.out.println("\tERROR: ETIQUETA MAXIMO DE TAMAÑO 8 CARACTERES");
                    }
                    
                    
                    int i;
                   if(!direccionamientos.lista4.isEmpty()){
                    for(i=0;i<direccionamientos.lista4.size();i++){
                    
                    String lista;
                    lista=direccionamientos.lista4.get(i);
                   // System.out.println(lista+"\n");
                    if(lista.equals(Eti)){
                     bandeti=1;
                    System.out.println("Error:Etiqueta Repetida, Etiqueta:"+Eti);
                   
                    }
                    }
                    }
                    
                    
                    
                    /*if((direccionamientos.lista4.isEmpty()) && bandeti==0){//Para cuando este vacia y sea etiqueta
                    direccionamientos.lista4.add(Eti);
                    }
                    if((!direccionamientos.lista4.isEmpty()) && bandeti==0){
                    direccionamientos.lista4.add(Eti);
                    
                    }*/
                     if(bandeti==0){
                    
                        direccionamientos.lista4.add(Eti);
                    
                    }
                     //System.out.println(direccionamientos.lista4.toString());

return bandeti;
}

public String CompletaHexa(String Hexad){
String Hexad1="",Hexad2;
int tamanioh;

tamanioh=Hexad.length();

if(tamanioh<4){
//faltantes=4-tamanioh;
if(Hexad.length()==3){
   Hexad1="0"+Hexad;
}
if(Hexad.length()==2){
   Hexad1="00"+Hexad;
}
if(Hexad.length()==1){
   Hexad1="000"+Hexad;
}
}else if(Hexad.length()==4){
Hexad1=Hexad;
}

Hexad2=Hexad1.toUpperCase();


return Hexad2;
}


}



package practica2;

import java.util.ArrayList;


public class comparacion
{
    //String Codop;
    //String Cadena2;
   ArrayList<String> list;
   ArrayList<String> lista = new ArrayList();
    //Boolean b=false;
   Boolean b;
    
    public comparacion(ArrayList<String> list) {
        this.list=list;
    }
    
    public boolean compara(String codop){
        b=false;
         lista.clear();
        //String codop1;
        //codop1=codop.toUpperCase();
        int i;
        int c=0;
        for ( i = 0; i < list.size(); i++) 
        {   
            String split[]=list.get(i).split("\\s+");
            if(codop!=null){
            if(split[0].equals(codop.toUpperCase()))
            {
                System.out.println(list.get(i));
                b=true;
                c=i;
                lista.add(list.get(i));
                //return true;
                /*Operandos x= new Operandos();
                String Ope1;
                Ope1=direccionamientos.Operando;
                x.analiza(Ope1);*/
            }
            
        
        }}
        
        if(b==true){
        //int a;
        
        String split2[]=list.get(c).split("\\s+");
        
        if(split2[1].equals("1"))
                {
                    if(direccionamientos.Operando==null)
                    {
                        System.out.println("EL CODOP DEBE TENER OPERANDO");
                    }
                }
                //else 
                    if(split2[1].equals("0"))
                    {
                        if(direccionamientos.Operando!=null)
                        {
                            System.out.println("El CODOP NO DEBE TENER OPERANDO");
                        }
                    }
        }
        if(b==false)
        {
                System.out.println("NO SE ENCONTRO EL CODOP");
        }
       // b=false;
       // return b;
    return b;
    }
}


/*              String split2[]=list.get(i).split("\\s+");
                if(split2[1].equals("1"))
                {
                    System.out.println("El CODOP debe tener operando");
                }
                else
                {
                    System.out.println("El CODOP no debe tener operando");
                }*/
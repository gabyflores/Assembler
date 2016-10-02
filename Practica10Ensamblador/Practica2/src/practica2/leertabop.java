
/*package practica2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class leertabop 
{
    ArrayList<String> Tabop = new ArrayList();
    
    public List leer(String Ruta) //por que le pass un parametro
    {
        
        try
        {
            FileReader fr2 = new FileReader(Ruta);
            BufferedReader br2 = new BufferedReader(fr2);
            String cadena2;
            
            while ((cadena2 = br2.readLine()) != null) {
            Tabop.add(cadena2);   
        }
        }catch(Exception ex){ 
        }
    return Tabop;
    }
    
    
}*/









 /*ArrayList<String> Tabop = new ArrayList();

    for (int i = 0; i < Tabop.size(); i++) {
            if (!Tabop.get(i).isEmpty() && Tabop.get(i).charAt(0) == ';') {
                System.out.println("COMENTARIO" + "\n");
            } else {
                String[] partes = lista.get(i).split("\\s+");// esto pertite separar las palabras con los espacios y los tabuladores
                String Etiqueta = null;
                if (!partes[ 0].isEmpty()) //si no hay nada en la parte primera a la izquierda del codop, se considera que la etiqueta no hay nada, es null
                {
                    Etiqueta = partes[ 0]; //etiqueta toma el valor de lo que hay a la izquierda de codop
                }

                String Codop = null;
                String Operando = null;

                if (partes.length >= 2) {
                    if (!partes[ 1].isEmpty()) //si no hay nada en la parte primera a la izquierda del codop, se considera que la etiqueta no hay nada, es null
                    {
                        Codop = partes[ 1]; //etiqueta toma el valor de lo que hay a la izquierda de codop
                    }

                    String[] ref = lista.get(i).split(Codop,2); // despues del codop, toma el valor del corte de lo demas despues del codop
                    if (ref.length == 2) // es necesario ver que se usan las 2 mitades en base a codop, ya que si el valor es 2, hay informacion en las 2 partes que son etiqueta y operando, si no lo es, solo habra valor en la etiqueta y no es necesarioe ntrar el if, solo mostraria null en el operando 
                    {
                        Operando = ref[ 1].replaceFirst("(\\s+)", ""); //es lo primero despues del corte de codop, despues de un espacio o tabulador, toma lo demas despues de codop
                        if (Operando.isEmpty()) {
                            Operando = null;
                        }
                    }
                }
}
}*/
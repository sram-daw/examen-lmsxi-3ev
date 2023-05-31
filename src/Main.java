import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/*
 Utilización de la libreria para parseo de JSON
 https://github.com/google/gson
 Volcamos a un fichero los datos y recuperamos de otro fichero
*/
public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();

        /*Deserialización*/

        //Leemos el fichero json
        String jsonString = "";
        try (FileReader reader = new FileReader("employees.json");
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            StringBuilder builder = new StringBuilder();
            int numCharsRead;

            while ((numCharsRead = bufferedReader.read()) != -1) {
                builder.append((char) numCharsRead);
            }

            jsonString = builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //se indica el tipo de objetos que se can a deserializar (arraylist de Employee)
        Type type = new TypeToken<ArrayList<Employee>>() {
        }.getType();
        ArrayList<Employee> employees = gson.fromJson(jsonString, type);
        //se muestra el contenido de la lectura de los objetos java creados a partir del json
        System.out.println("Objetos serializados: ");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        //modificamos algunos datos de los empleados
        employees.get(0).setFirstName("María");
        employees.get(1).setLastName("López");
        employees.get(2).setFirstName("Pepito");

        //transformamos el array de employees a json (serialización)
        String json = gson.toJson(employees);
        FileWriter writer = new FileWriter("escritura-modificacion-array.json");
        writer.write(json);
        writer.close();

        //se lee el nuevo archivo para comprobar que la serialización se hizo correctamente
        FileReader reader = new FileReader("escritura-modificacion-array.json");
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder builder = new StringBuilder();
        int numCharsRead;

        while ((numCharsRead = bufferedReader.read()) != -1) {
            builder.append((char) numCharsRead);
        }

        reader.close();
        //y creamos el string con el contenido del fichero escritura-modificacion-array.json
        String jsonString2 = builder.toString();

        //se muestra el contenido
        System.out.println("Lectura del nuevo fichero json: ");
        System.out.println(jsonString2);

    }
}
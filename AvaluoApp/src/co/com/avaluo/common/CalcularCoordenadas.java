package co.com.avaluo.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

public class CalcularCoordenadas {

    public String getCoordenadasDeEstaDireccion(String urlToRead) {

        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
            url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //En el result viene los datos con una estructura llamada JSON (que es eso de las llaves { y dentro propiedades:valores, etc...
        //aqui abajo voy navegando por el objeto result y transformandolo hasta que llego a la "location" y ahi leo las propiedades lat y lng
        HashMap properties = new Gson().fromJson(result, HashMap.class);
        List resultados = (List) properties.get("results");
        LinkedTreeMap informacion = (LinkedTreeMap) resultados.get(0); //solo debe venir un elemento y estara en la posicion 0
        LinkedTreeMap geometryInfo = (LinkedTreeMap) informacion.get("geometry");
        LinkedTreeMap locationInfo = (LinkedTreeMap) geometryInfo.get("location");
        System.out.println("La latitud es: " + locationInfo.get("lat")); //LAtitud
        System.out.println("la longitud es: " +locationInfo.get("lng")); //longitud
        return "La latitud es: " + locationInfo.get("lat")+ ", "+"la longitud es: " +locationInfo.get("lng");
    }



    public static void main(String args[])
    {
        CalcularCoordenadas c = new CalcularCoordenadas();
        String direccion =  "http://maps.googleapis.com/maps/api/geocode/json?address=Calle+Sierra+de+Cuerda+Larga,+28038+Madrid";
        System.out.println(c.getCoordenadasDeEstaDireccion(direccion));
    }
}
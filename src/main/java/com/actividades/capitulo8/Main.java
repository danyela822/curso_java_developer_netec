package com.actividades.capitulo8;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("\n------------------------------- Contar Palabras ---------------------------------\n");
        contarPalabrasIO();
        System.out.println("\n------------------------------- Copiar Contenido ---------------------------------\n");
        copiarContenido();
        System.out.println("\n------------------------------- Serializar Objeto ---------------------------------\n");
        serializarObjeto();
        System.out.println("\n------------------------------- Deserializar Objeto ---------------------------------\n");
        deserializarObjeto();
        System.out.println("\n------------------------------- Manipular Archivos ---------------------------------\n");
        manipularArchivos();
        System.out.println("\n------------------------------- Manipular Directorios ---------------------------------\n");
        manipularArchivosDirectorios();
    }

    public static void contarPalabrasIO() throws IOException {

    String ruta = "src/main/java/com/actividades/capitulo8/resources/texto.txt";

        int totalPalabras = 0;
        String lineaActual;

        try (FileReader lectorArchivo = new FileReader(ruta);
             BufferedReader lectorLinea = new BufferedReader(lectorArchivo)) {

            System.out.println("-------------- Inicio Archivo --------------------");
            while ((lineaActual = lectorLinea.readLine()) != null) {
                System.out.println(lineaActual);

                // Dividir la línea por espacios en blanco
                String[] palabras = lineaActual.split(" ");
                totalPalabras += palabras.length;
            }
            System.out.println("-------------- Fin Archivo --------------------");
        }
        System.out.println("Número total de palabras: " + totalPalabras);
    }

    public static void copiarContenido() throws IOException{

        String archivoOrigen = "src/main/java/com/actividades/capitulo8/resources/texto_1.txt";
        String archivoDestino = "src/main/java/com/actividades/capitulo8/resources/texto_2.txt";

        try (FileChannel canalOrigen = new FileInputStream(archivoOrigen).getChannel();
             FileChannel canalDestino = new FileOutputStream(archivoDestino).getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate((int) canalOrigen.size());

            while ((canalOrigen.read(buffer)) != -1) {

                // Rebobinar el buffer para preparar la escritura
                buffer.rewind();

                // Escribir en el archivo de destino
                canalDestino.write(buffer);

                // Limpiar el buffer para la siguiente lectura
                buffer.clear();
            }
        }

        System.out.println("Contenido copiado");
    }

    public static void serializarObjeto() throws IOException{

        String ruta = "src/main/java/com/actividades/capitulo8/resources/objetoSerializado";

        Persona persona = new Persona("Juan",25,"Direccion 1");

        try (FileOutputStream file = new FileOutputStream(ruta);
             ObjectOutputStream salida = new ObjectOutputStream(file)){

            salida.writeObject(persona);
            System.out.println("Serializacion completa");
        }
    }

    public static void deserializarObjeto() throws IOException{

        String ruta = "src/main/java/com/actividades/capitulo8/resources/objetoSerializado";

        try (FileInputStream file = new FileInputStream(ruta);
             ObjectInputStream objeto = new ObjectInputStream(file)){

            Persona persona = (Persona) objeto.readObject();
            System.out.println("Dserializacion completa");
            System.out.println("Persona: "+persona.toString());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void manipularArchivos(){
        String stringRuta = "src/main/java/com/actividades/capitulo8/resources/archivo5.txt";

        Path rutaArchivo = Paths.get(stringRuta);

        if (!Files.exists(rutaArchivo)) {
            System.out.println("El archivo " + rutaArchivo + " no existe");

            try{
                Files.createFile(rutaArchivo);
                System.out.println("El archivo " + rutaArchivo + " se ha creado");
            }catch (IOException e){
                System.out.println("El archivo " + rutaArchivo + " no se ha creado");
            }

        } else {
            System.out.println("El archivo " + rutaArchivo + " existe");
        }

        try (Stream<Path> archivos = Files.list(Paths.get("src/main/java/com/actividades/capitulo8/resources/directorio"))){
            archivos.filter(archivo -> archivo.toString().endsWith(".txt"))
                    .forEach(archivo -> System.out.println(archivo));
        }catch (IOException e){
            System.out.println("El archivo " + rutaArchivo + " existe");
        }
    }

    public static void manipularArchivosDirectorios(){

        String stringRuta = "src/main/java/com/actividades/capitulo8/resources/directorio/";

        System.out.println("Archivos directorio principal\n");

        try (Stream<Path> archivos = Files.list(Paths.get(stringRuta))){
            archivos.filter(archivo -> !Files.isDirectory(archivo))
                    .forEach(archivo -> System.out.println(archivo.toString()));
        }catch (IOException e){
            System.out.println("El directorio " + stringRuta + " no existe");
        }

        System.out.println("\nArchivos .txt de todos los subdirectorios\n");

        try (Stream<Path> archivos = Files.walk(Paths.get(stringRuta))){
            archivos.filter(archivo -> !Files.isDirectory(archivo) && archivo.toString().endsWith(".txt"))
                    .forEach(archivo -> System.out.println(archivo.toString()));
        }catch (IOException e){
            System.out.println("El directorio " + stringRuta + " no existe");
        }
    }
}

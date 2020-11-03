package com.jasato.friegaplatos;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<Plato> bandejaDePlatos = crearPlatos(7);
        Bandeja bandejaSucios = new Bandeja(bandejaDePlatos);
        Bandeja bandejaFregar = new Bandeja();
        Bandeja bandejaSecar = new Bandeja();
        Bandeja bandejaGuardar = new Bandeja();

        Thread fregar = new Thread(new Fregador(bandejaSucios, bandejaFregar), "Fregar");
        Thread secar = new Thread(new Secador(bandejaFregar, bandejaSecar), "Secar");
        Thread organizar = new Thread(new Organizador(bandejaSecar, bandejaGuardar), "Guardar");

        fregar.start();
        secar.start();
        organizar.start();
        Thread.sleep(60000);
        fregar.interrupt();
        secar.interrupt();
        organizar.interrupt();
        fregar.join();
        secar.join();
        organizar.join();
        System.out.println("Feliz cumpleaños");

    }

        private static ArrayList<Plato> crearPlatos ( int i){
            ArrayList<Plato> platos = new ArrayList<>();
            for (int x = 0; x <= i; x++) {
                platos.add(new Plato(x));
            }
            return platos;
        }
}

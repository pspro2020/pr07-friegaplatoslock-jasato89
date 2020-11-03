package com.jasato.friegaplatos;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Secador implements Runnable {

    private final Bandeja platosLimpios;
    private final Bandeja platosSecos;

    public Secador(Bandeja platosLimpios, Bandeja platosSecos) {
        this.platosLimpios = platosLimpios;
        this.platosSecos = platosSecos;
    }

    @Override
    public void run()  {
        Plato plato;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                plato = platosLimpios.quitar();
            } catch (InterruptedException e) {
                return;
            }
            try {
                secarPlato(plato);
            } catch (InterruptedException e) {
                return;
            }

        }

    }

    private void secarPlato(Plato plato) throws InterruptedException {
        Random random = new Random();
        TimeUnit.SECONDS.sleep(random.nextInt(3)+1);
        platosSecos.añadir(plato);
    }
}

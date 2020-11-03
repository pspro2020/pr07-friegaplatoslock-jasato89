package com.jasato.friegaplatos;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Organizador implements Runnable{
    private final Bandeja bandejaSecos;
    private final Bandeja bandejaGuardar;

    public Organizador(Bandeja bandejaSecos, Bandeja bandejaGuardar) {
        this.bandejaSecos = bandejaSecos;
        this.bandejaGuardar = bandejaGuardar;
    }

    @Override
    public void run() {
        Plato plato;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                plato = bandejaSecos.quitar();
            } catch (InterruptedException e) {
                return;
            }
            try {
                guardarPlato(plato);
            } catch (InterruptedException e) {
                return;
            }
        }

    }

    private void guardarPlato(Plato plato) throws InterruptedException {
        Random random = new Random();
        TimeUnit.SECONDS.sleep(random.nextInt(2)+1);
        bandejaGuardar.añadir(plato);
    }
}

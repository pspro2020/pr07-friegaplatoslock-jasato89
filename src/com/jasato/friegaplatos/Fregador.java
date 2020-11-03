package com.jasato.friegaplatos;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Fregador implements Runnable {
    private Bandeja bandejaSucia;
    private Bandeja bandejaLimpia;
    private int num = 0;

    Fregador(Bandeja bandejaSucia, Bandeja bandejaLimpia) {
        this.bandejaSucia = bandejaSucia;
        this.bandejaLimpia = bandejaLimpia;
    }

    @Override
    public void run() {
        Plato plato;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                plato = cogerSucio();
            } catch (InterruptedException e) {
                return;
            }
            try {
                limpiarPlato(plato);
            } catch (InterruptedException e) {
                return;
            }

        }
    }

    private Plato cogerSucio() throws InterruptedException {
        return bandejaSucia.quitar();
    }

    private void limpiarPlato(Plato plato) throws InterruptedException {
        Random random = new Random();
        TimeUnit.SECONDS.sleep(random.nextInt(5) + 4);
        bandejaLimpia.añadir(plato);

    }

}


package com.jasato.friegaplatos;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bandeja {

    private List<Plato> bandejaPlatos = new ArrayList<>();

    public Bandeja(List<Plato> bandejaPlatos) {
        this.bandejaPlatos = bandejaPlatos;
    }
    public Bandeja() {}
    private Lock reentrantLock = new ReentrantLock();
    private Condition isNotEmpty = reentrantLock.newCondition();
    private Condition platosAvailable = reentrantLock.newCondition();

    protected void añadir(Plato plato) {
            reentrantLock.lock();

        try {
            System.out.printf(
                    "%s - %s plato núm %d\n",
                    LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                    Thread.currentThread().getName(),
                    plato.getNum()
                    );
            bandejaPlatos.add(plato);
            isNotEmpty.signal();
        } finally {
            reentrantLock.unlock();
        }

    }

    protected Plato quitar() throws InterruptedException {
        reentrantLock.lock();
        Plato plato = null;
        try {
                while (bandejaPlatos.isEmpty()) {
                    isNotEmpty.await();
                }
                plato = bandejaPlatos.remove(0);
            isNotEmpty.signal();
            return plato;
        }
        finally {
            reentrantLock.unlock();
        }
    }
}

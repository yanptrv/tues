package com.home.Kris;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting Main");
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Starting the thread");
//            }
//        });
        Thread t1 = new Thread(() -> {
            MechanicWorkshop workshop = new MechanicWorkshop();
            try {
                for (int i = 0; i < 10; i++) {
                    workshop.addCar();
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Starting thread");
       });
        Thread t = new Thread(new MechanicWorkshop());
        t.start();
        t1.start();
        t.join();
        System.out.println("Main finished");
    }
}

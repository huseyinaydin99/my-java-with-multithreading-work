package com.huseyinaydin;

class A implements Runnable{

	@Override
	public void run() {
		for(int i = 0; i < 5; i++) {
			System.out.println("merhaba " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
}

class B implements Runnable{

	@Override
	public void run() {
		for(int i = 0; i < 5; i++) {
			System.out.println("merhaba " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
}

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Runnable runnable = new A();
		Thread thread = new Thread(runnable,"Hi");
		Thread thread2 = new Thread(()-> {
			for(int i = 0; i < 5; i++) {
				System.out.println("merhaba " + i + " pri " + Thread.currentThread().getPriority());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
			}
		},"Hello");
		System.out.println(thread.getName());
		System.out.println(thread2.getName());
		System.out.println(thread.getPriority());
		System.out.println(thread2.getPriority());
		thread.setPriority(Thread.MIN_PRIORITY);
		thread2.setPriority(Thread.MAX_PRIORITY);
		thread.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {}
		thread2.start();
		thread.join();
		thread2.join();
		System.out.println("bye");
		System.out.println(thread.isAlive());
	}

}

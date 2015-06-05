package com.epam.jmp.t7.pc;

import java.util.concurrent.Semaphore;
		public class Main {
			public static void main(String args[]) {
				Semaphore sem = new Semaphore(1);
				new Producer(sem, "+Producer");
				new Consumer(sem, "-Consumer");
			}
		}
		
		class Shared {
			static int count = 10;
		}
		
		class Producer implements Runnable {
			String name;
			Semaphore sem;
		
			Producer(Semaphore s, String n) {
				sem = s;
				name = n;
				new Thread(this).start();
			}
		
			public void run() {
				while(true){
					try {
						sem.acquire();
						if (Shared.count < 10) {
							System.out.println(name + ": " + ++Shared.count);
							Thread.sleep(500);
						}
						sem.release();
					} catch (InterruptedException exc) {
						System.out.println(exc);
					}				
				}
			}
		}
		
		class Consumer implements Runnable {
			String name;
			Semaphore sem;
		
			Consumer(Semaphore s, String n) {
				sem = s;
				name = n;
				new Thread(this).start();
			}
		
			public void run() {
				while(true){
					try {
						sem.acquire();
						if (Shared.count > 5) {
							System.out.println(name + ":          " + --Shared.count);
							Thread.sleep(1000);
						}
						sem.release();
					} catch (InterruptedException exc) {
						System.out.println(exc);
					}				
				}
			}
		}
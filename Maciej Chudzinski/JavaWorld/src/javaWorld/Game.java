package javaWorld;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3452793272759655425L;
	private Thread thread;
	private boolean running = false;
	private World w;
	public Game(){
		new Window(World.xRange, World.yRange, "no elo", this);
		w= new World();
		w.generateobjects(300);
		//w.addOrganism(new Sheep(0,0));
	}
	public synchronized void start(){
		thread= new Thread(this);
		thread.start();
		running=true;
	}
	public synchronized void stop(){
		thread= new Thread(this);
		thread.start();
		try{
			thread.join();
			running = false;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void run(){
		long lastTime= System.nanoTime();
		double amountOfTicks= 2.0;
		double ns= 1000000000/amountOfTicks;
		double delta=0;
		long timer= System.currentTimeMillis();
		int frames=0;
		while(running){
			long now=System.nanoTime();
			delta+=(now-lastTime) /ns;
			lastTime=now;
			while(delta>=1){
				tick();
				delta--;
			}
			if(running)	render();
			frames++;
			if(System.currentTimeMillis()-timer>1000){
				timer +=1000;
				System.out.println("FPS: " + frames);
				frames=0;
			}
		}
		stop();
	}
	private void tick(){
		w.tick();
	}
	private void render(){
		BufferStrategy bs= this.getBufferStrategy();
		if(bs==null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g= bs.getDrawGraphics();
		g.setColor(Color.gray);
		g.fillRect(0, 0, World.xRange+50, World.yRange+50);
		w.render(g);
		g.dispose();
		bs.show();
	}
	public static void main(String[] args) throws InterruptedException {
		new Game();
		/*World w=new World();
		w.addOrganism(new Sheep(0, 0));
		w.addOrganism(new Sheep(0, 1));
		w.addOrganism(new Sheep(1, 0));
		w.addOrganism(new Sheep(1, 1));
		w.addOrganism(new Wolf(0, 0));
		w.addOrganism(new Sheep(2, 1));
		w.addOrganism(new Sheep(1, 2));
		w.makeTurn();*/
	}

}

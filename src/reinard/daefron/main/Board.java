package reinard.daefron.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable{

	private final int DELAY = 20;
    Thread t;
    Instance inst;
    
	Board(int xSize, int ySize, Color bg){
		BoardSet(xSize,ySize,bg);
		inst=new Instance();
		t=new Thread(this);
        t.start();
	}
	
	private void BoardSet(int xSize, int ySize, Color bg){
		setPreferredSize(new Dimension(xSize,ySize));
		setBackground(bg);
        setDoubleBuffered(true);
		System.out.println("Init executed");
        addKeyListener(new TAdapter());
        setFocusable(true);
    }
	
	 private class TAdapter extends KeyAdapter {
	    	
	        @Override
	        public void keyReleased(KeyEvent e) {
	            //System.out.println(e.getKeyCode());
	            inst.getPlayer().keyReleased(e);
	        }

	        @Override
	        public void keyPressed(KeyEvent e) {
	            ///System.out.println(e.getKeyCode());
	            inst.getPlayer().keyPressed(e);
	        }
	}
	
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		inst.drawInstance(g);
		Toolkit.getDefaultToolkit().sync();
	}
	
	@Override
	public void run(){
		long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        while (true) {
        	
        	repaint();
        	Toolkit.getDefaultToolkit().sync();
        	
        	timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;
            if(sleep < 0){sleep = 2;}
            try {Thread.sleep(sleep);}
            catch (InterruptedException e) {System.out.println("Interrupted: " + e.getMessage());}
            beforeTime = System.currentTimeMillis();
        }
	}
}

/*public class Board extends JPanel{

    public Board() {
        initBoard();
    }

    private void initBoard() {
		System.out.println("Init executed");
        addKeyListener(new TAdapter());
        setFocusable(true);
    }
    
    private class TAdapter extends KeyAdapter {
    	
        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println(e.getKeyCode());
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());
        }
    }
}*/


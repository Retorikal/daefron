package reinard.daefron.main;

import javax.swing.JFrame;
import java.awt.Color;

public class AppMain extends JFrame{

	AppMain(String title,boolean visible,boolean resizable,int onclose){
		add(new Board(500,500,Color.BLACK));	
		initFrame(title,visible,resizable,onclose);
	}
	
	private void initFrame(String title,boolean visible,boolean resizable,int onclose){
		setTitle(title);
		setResizable(resizable);
		setDefaultCloseOperation(onclose);
		setVisible(visible);
        pack();
	}
	
	public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
		//	@Override
        //    public void run() {
        //        AppMain ex = new AppMain();
        //        ex.initUI();
        //    }
        //});
		AppMain main = new AppMain("Test Project: Daemon Frontier",true,true,JFrame.EXIT_ON_CLOSE);	
		//main.initFrame("Test Project: Daemon Frontier",true,true,JFrame.EXIT_ON_CLOSE);
	}

}

/*public class AppMain extends JFrame {

    public AppMain() {
		add(new Board(500,500,Color.BLACK));
    }

    public static void main(String[] args) {
        AppMain ex = new AppMain();
        ex.setVisible(true);
    }
}*/

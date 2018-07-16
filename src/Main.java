
	import javax.swing.JFrame;
	
	
	public class Main {
	
	
	public static void main(String[] args) {
		
		GamePlay GP = new GamePlay();
		JFrame obj = new JFrame();
		
		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("Game Ball");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(GP);
		
		
	}

}

package serwerCSGO;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.*;

public class ServerGUI extends JFrame implements ActionListener
{
	JButton start = new JButton("Start server");
	JButton build = new JButton("Build server");
	JLabel signal = new JLabel("OFF");
	JPanel panel = new JPanel();


	public ServerGUI()
	{
		super("Serwer CS:GO");
		setSize(200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(new GridLayout(2, 3, 10, 10));
				
		panel.add(start); 
		panel.add(build);
		start.addActionListener(this);
		build.addActionListener(this);
		
		signal.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		Container cp = getContentPane();
		setContentPane(cp);
		cp.add(panel);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		Object source = evt.getSource();
		List cmdAndArgs = Arrays.asList("cmd", "/c", "start.bat");
		//List cmdAndArgsBuild = Arrays.asList()
		File dir = new File("C:/serwer");
		
		if(source==start)
		{
			if(start.getText()=="Start")
			{
				ProcessBuilder pb = new ProcessBuilder(cmdAndArgs);
				pb.directory(dir);
				try {
					Process p = pb.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					panel.setToolTipText("Umiesc plik start.bat w folderze C:/serwer!");
				}
				panel.setBackground(Color.green);
				start.setText("Stop");
			}
			else
			{
				try {
					Runtime.getRuntime().exec("taskkill /F /IM "+ ".exe");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				panel.setBackground(Color.red);
				start.setText("Start");
			}
		}
		if(source==build)
		{
			
		}
	}
	
}

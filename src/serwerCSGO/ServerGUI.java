package serwerCSGO;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.*;

public class ServerGUI extends JFrame implements ActionListener
{
	JButton start = new JButton("Start server");
	JButton build = new JButton("Build server");
	JTextArea signal = new JTextArea("Pozdrowienia od Agatki<3 Status budowy serwera:");
	JTextArea status = new JTextArea("");
	JPanel panel = new JPanel();
	//JTextArea autor = new JTextArea("Z pozdrowieniami od radioaktywnej <3");

	
	//wyswietlanie sie komend na jlabel
	public ServerGUI()
	{
		super("Serwer CS:GO");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(new GridLayout(2, 3, 10, 10));
				
		panel.add(start); 
		panel.add(build);
		panel.add(signal);
		panel.add(status);
		//panel.add(autor);
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
		List cmdAndArgs = Arrays.asList("cmd", "/c", " start.bat");
		List cmdAndArgsPrim = Arrays.asList("cmd", null, "steamcmd +runscript csgo_server_trigger.txt");
		//List cmdAndArgsBuild = Arrays.asList()
		File dir = new File("D:\\serwer\\");
		File dirPrim = new File("D:\\serwer\\steamcmd");
		
		if(source==start)
		{
			if(start.getText()=="Start")
			{
				ProcessBuilder pb = new ProcessBuilder(cmdAndArgs);
				pb.directory(dir);
				
				try 
				{
					Process p = pb.start();
				}
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					signal.setText("Umiesc plik start.bat w folderze C:/serwer!");
				}
				status.setBackground(Color.green);
				start.setText("Stop");
			}
			else
			{
				try {
					Runtime.getRuntime().exec("taskkill /F /IM "+ "srcds.exe");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				status.setBackground(Color.red);
				start.setText("Start");
			}
		}
		if(source==build)
		{
			
			ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "steamcmd +runscript csgo_server_trigger.txt");
			pb.directory(dirPrim);
			pb.redirectErrorStream(true);
			try {
				Process p = pb.start();
				InputStream is = p.getInputStream();
		        InputStreamReader isr = new InputStreamReader(is);
		        BufferedReader br = new BufferedReader(isr);
		        String line;
		        //signal.setText("Output of running %s is:\n",
		               //(cmdAndArgsPrim));
		        while ((line = br.readLine()) != null) 
		        {
		            signal.setText(line);
		        }
		        try {
		            int exitValue = p.waitFor();
		            System.out.println("\n\nExit Value is " + exitValue);
		        } catch (InterruptedException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}

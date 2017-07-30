package data;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel{
	public ArrayList<BufferedImage> Images = new ArrayList<>();
	private BufferedImage baseImage;
	private BufferedImage saveImage;
	private Graphics2D g2d;
	
	public Panel(JFrame jf){
		this.setBounds(0, 0, jf.getWidth()-2, jf.getHeight()-29-2);
		this.setBackground(Color.white);
		this.setVisible(true);
		baseImage = new BufferedImage(getWidth(), getHeight(),BufferedImage.TYPE_INT_ARGB);
		g2d = (Graphics2D) baseImage.getGraphics();
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.green);
		g.drawRect(0, 0, this.getWidth()-1,this.getHeight()-1);
		g.drawImage(baseImage,0, 0, this);
		for(int i=0;i<Images.size();i++){
			BufferedImage mi = Images.get(i);
			g.drawImage(mi,0, 0, this);
		}
		repaint();
	}
	public void imgAdd(BufferedImage img){
		System.out.println("|size:"+Images.size());
		if(Images.size()>=20){
			g2d.drawImage(Images.get(0),0, 0,null);
			Images.remove(0);
			System.out.println("|size:"+Images.size());
			Images.add(img);
		}else{
			Images.add(img);
		}
	}
	public void reset(){
		baseImage = new BufferedImage(getWidth(), getHeight(),BufferedImage.TYPE_INT_ARGB);
		g2d = (Graphics2D) baseImage.getGraphics();
		Images = new ArrayList<>();
	}
	public void save(String Path){
		saveImage = new BufferedImage(getWidth(), getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) saveImage.getGraphics(); 
		g.setColor(Color.white);
		g.fillRect(0, 0,getWidth(), getHeight());
		g.drawImage(baseImage, 0, 0,null);
		for(int i=0;i<Images.size();i++){
			BufferedImage mi = Images.get(i);
			g.drawImage(mi,0, 0, this);
		}
		File f = new File(Path+"\\"+new SimpleDateFormat("yy_MM_DD_HH_mm_ss").format(new Date())+".png");
		try {
			ImageIO.write(saveImage, "png", f);
			if(f.exists()){
				JOptionPane.showMessageDialog(null, "±£´æ³É¹¦", "", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "±£´æÊ§°Ü,ÇëÖØÊÔ!", "", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "±£´æÊ§°Ü,ÇëÖØÊÔ!", "", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

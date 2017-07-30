package data;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.regex.Pattern;

public class Main {
	public static final Dimension Display = Toolkit.getDefaultToolkit().getScreenSize();
	static Panel jp;
	static short check1 = 1;
	static short check2 = 4;
	static boolean draw = false;
	static Point pointS;
	static BufferedImage temp;
	static int[] angles = {0,360};
	static int sWidth = 1;
	static Color sColor = Color.black;
	static Point pointO;
	static String Path = "D:";
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setTitle("画乌龟");
		jf.setBounds(Display.width/6,Display.height/24, Display.width*2/3, Display.height*9/10);
		jf.setResizable(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		jp = new Panel(jf);
		jf.add(jp);
		JFrame jf2 = new JFrame();
		jf2.setLayout(null);
		Font ft = new Font("楷体",1,24);
		jf2.setBounds(Display.width-Display.width/6,Display.height/6, Display.width/12, Display.height*2/3);
		jf2.setResizable(false);
		jf2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jf.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				//jf2.requestFocus();
			}
		});
		JRadioButton jrb1 = new JRadioButton("线条");
		jrb1.setSelected(true);
		jrb1.setBackground(Color.yellow);
		jrb1.setFont(ft);
		jrb1.setBounds(0, 0, Display.width/24, 45);
		jrb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(jrb1.isSelected()){
					check1 = 1;
				}
			}
		});
		JRadioButton jrb2 = new JRadioButton("图形");
		jrb2.setBounds(Display.width/24,0 , Display.width/24, 45);
		jrb2.setBackground(Color.yellow);
		jrb2.setFont(ft);
		jrb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(jrb2.isSelected()){
					check1 = 2;
				}
			}
		});
		jrb2.setEnabled(false);
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(jrb1);
		bg1.add(jrb2);
		/////////
		JTextField jtf1 = new JTextField(3);
		jtf1.setBounds(0, Display.height*2/3-45*2-29,Display.width/12, 45);
		jtf1.setBackground(Color.black);
		jtf1.setForeground(Color.white);
		jtf1.setCaretColor(Color.white);
		jtf1.setFont(ft);
		Pattern pa = Pattern.compile("[0-9]{1,3}");
		jtf1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(pa.matcher(jtf1.getText()).find()&&Integer.parseInt(jtf1.getText())<angles[1]){
					angles[0] = Integer.parseInt(jtf1.getText());
				}else{
					jtf1.setText(""+angles[0]);
				}
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		JTextField jtf2 = new JTextField(3);
		jtf2.setBounds(0, Display.height*2/3-45-29,Display.width/12, 45);
		jtf2.setBackground(Color.black);
		jtf2.setBackground(Color.black);
		jtf2.setForeground(Color.white);
		jtf2.setCaretColor(Color.white);
		jtf2.setFont(ft);
		jtf2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(pa.matcher(jtf2.getText()).find()&&Integer.parseInt(jtf2.getText())>angles[0]){
					angles[1] = Integer.parseInt(jtf2.getText())>360?360:Integer.parseInt(jtf2.getText());
				}else{
					jtf2.setText(""+angles[1]);
				}
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		jtf1.setEnabled(false);
		jtf2.setEnabled(false);
		//////////
		JRadioButton jb1 = new JRadioButton("直线");
		jb1.setBounds(0, 45,Display.width/12, 45);
		jb1.setFont(ft);
		jb1.setForeground(Color.white);
		jb1.setBackground(Color.blue);
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(jrb1.isSelected()){
					check2 = 1;
					jrb2.setEnabled(false);
					jtf1.setEnabled(false);
					jtf2.setEnabled(false);
				}
			}
		});
		JRadioButton jb2 = new JRadioButton("方形");
		jb2.setBounds(0, 45*2,Display.width/12, 45);
		jb2.setFont(ft);
		jb2.setBackground(Color.green);
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(jb2.isSelected()){
					check2 = 2;
					jrb2.setEnabled(true);
					jtf1.setEnabled(false);
					jtf2.setEnabled(false);
				}
			}
		});
		JLabel jlb2 = new JLabel("弧度范围:");
		jlb2.setBounds(0, Display.height*2/3-45*3-29,Display.width/12, 45);
		jlb2.setFont(ft);
		jlb2.setForeground(Color.blue);
		JRadioButton jb3 = new JRadioButton("弧形");
		jb3.setBounds(0, 45*3,Display.width/12, 45);
		jb3.setFont(ft);
		jb3.setForeground(Color.white);
		jb3.setBackground(Color.blue);
		jb3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(jb3.isSelected()){
					check2 = 3;
					jrb2.setEnabled(true);
					jtf1.setEnabled(true);
					jtf2.setEnabled(true);
				}
			}
		});
		JRadioButton jb4 = new JRadioButton("铅笔");
		jb4.setBounds(0, 45*4,Display.width/12, 45);
		jb4.setSelected(true);
		jb4.setFont(ft);
		jb4.setBackground(Color.green);
		jb4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(jb4.isSelected()){
					check2 = 4;
					jrb2.setEnabled(false);
					jtf1.setEnabled(false);
					jtf2.setEnabled(false);
				}
			}
		});
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(jb1);
		bg2.add(jb2);
		bg2.add(jb3);
		bg2.add(jb4);
		JButton jc1 = new JButton("颜色选择");
		jc1.setBackground(Color.blue);
		jc1.setBounds(0, 45*5,Display.width/12, 45);
		jc1.setForeground(Color.white);
		jc1.setFont(ft);
		jc1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Color c = JColorChooser.showDialog(null, "请选择颜色", sColor);
				if(c!=null){
					sColor = c;
				}
			}
		});
		JLabel jlb1 = new JLabel("画笔宽:");
		jlb1.setBounds(0, 45*6,Display.width/12, 45);
		jlb1.setFont(ft);
		jlb1.setForeground(Color.blue);
		JSlider jsl1 = new JSlider(1,25,1);
		jsl1.setBackground(Color.green);
		jsl1.setBounds(0, 45*7,Display.width/12, 45);
		jsl1.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				sWidth = jsl1.getValue();
			}
		});
		JButton jb5 = new JButton("撤销");
		jb5.setBackground(Color.blue);
		jb5.setForeground(Color.white);
		jb5.setFont(ft);
		jb5.setBounds(0, 45*8,Display.width/12, 45);
		jb5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(jp.Images.size()>0){
					jp.Images.remove(jp.Images.size()-1);
				}else{
					JOptionPane.showMessageDialog(null, "不能再撤销啦!", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		JButton jb6 = new JButton("保存");
		jb6.setBackground(Color.green);
		jb6.setFont(ft);
		jb6.setBounds(0, 45*9,Display.width/12, 45);
		jb6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("路径选择");
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int re = jfc.showOpenDialog(jp);
				if(re == 0){
					Path = jfc.getSelectedFile().toPath().toString();
					System.out.println(Path);
					jp.save(Path);
				}
			}
		});
		jb6.setToolTipText("注意保存时以当前窗口大小为准!");
		JButton jb7 = new JButton("重置");
		jb7.setBackground(Color.blue);
		jb7.setFont(ft);
		jb7.setForeground(Color.white);
		jb7.setBounds(0, 45*10,Display.width/12, 45);
		jb7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(jp.Images.size()>0){
					jp.reset();
				}
			}
		});;
		jf2.add(jrb1);
		jf2.add(jrb2);
		jf2.add(jb1);
		jf2.add(jb2);
		jf2.add(jb3);
		jf2.add(jb4);
		jf2.add(jc1);
		jf2.add(jlb1);
		jf2.add(jsl1);
		jf2.add(jlb2);
		jf2.add(jtf1);
		jf2.add(jtf2);
		jf2.add(jb5);
		jf2.add(jb6);
		jf2.add(jb7);
		jf2.setVisible(true);
		jp.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Pressed:x:"+arg0.getX()+":y:"+arg0.getY());
				if(!draw){
					draw = true;
					pointS = new Point(arg0.getX(),arg0.getY());
					pointO = new Point(arg0.getX(),arg0.getY());
					jp.imgAdd(new BufferedImage(jp.getWidth(),jp.getHeight() ,BufferedImage.TYPE_INT_ARGB));
				}
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Released:"+jp.Images.size());
				if(draw){
					draw = false;
				}
			}
			
		});
		jp.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Drag:x:"+arg0.getX()+":y:"+arg0.getY());
				if(draw){
					if(check1 == 1&&check2 == 1){
						temp = new BufferedImage(jp.getWidth(), jp.getHeight(),BufferedImage.TYPE_INT_ARGB);
						Graphics2D g2d = temp.createGraphics();
						g2d.setStroke(new BasicStroke(sWidth));
						g2d.setColor(sColor);
						g2d.drawLine(pointS.x,pointS.y, arg0.getX(),arg0.getY());
						if(jp.Images.size()>0){
							jp.Images.remove(jp.Images.size()-1);
							}
						jp.imgAdd(temp);
					}else if(check1 == 1&&check2 == 2){
						temp = new BufferedImage(jp.getWidth(), jp.getHeight(),BufferedImage.TYPE_INT_ARGB);
						Graphics2D g2d = temp.createGraphics();
						g2d.setStroke(new BasicStroke(sWidth));
						g2d.setColor(sColor);
						g2d.drawRect(pointS.x,pointS.y, arg0.getX()-pointS.x,arg0.getY()-pointS.y);
						if(jp.Images.size()>0){
							jp.Images.remove(jp.Images.size()-1);
							}
						jp.imgAdd(temp);
					}else if(check1 == 2&&check2 == 2){
						temp = new BufferedImage(jp.getWidth(), jp.getHeight(),BufferedImage.TYPE_INT_ARGB);
						Graphics2D g2d = temp.createGraphics();
						g2d.setStroke(new BasicStroke(sWidth));
						g2d.setColor(sColor);
						g2d.fillRect(pointS.x,pointS.y, arg0.getX()-pointS.x,arg0.getY()-pointS.y);
						if(jp.Images.size()>0){
							jp.Images.remove(jp.Images.size()-1);
							}
						jp.imgAdd(temp);
					}else if(check1 == 1&&check2 == 3){
						temp = new BufferedImage(jp.getWidth(), jp.getHeight(),BufferedImage.TYPE_INT_ARGB);
						Graphics2D g2d = temp.createGraphics();
						g2d.setStroke(new BasicStroke(sWidth));
						g2d.setColor(sColor);
						g2d.drawArc(pointS.x,pointS.y, arg0.getX()-pointS.x,arg0.getY()-pointS.y, angles[0], angles[1]);
						if(jp.Images.size()>0){
							jp.Images.remove(jp.Images.size()-1);
							}
						jp.imgAdd(temp);
					}else if(check1 == 2&&check2 == 3){
						temp = new BufferedImage(jp.getWidth(), jp.getHeight(),BufferedImage.TYPE_INT_ARGB);
						Graphics2D g2d = temp.createGraphics();
						g2d.setStroke(new BasicStroke(sWidth));
						g2d.setColor(sColor);
						g2d.fillArc(pointS.x,pointS.y, arg0.getX()-pointS.x,arg0.getY()-pointS.y, angles[0], angles[1]);
						if(jp.Images.size()>0){
							jp.Images.remove(jp.Images.size()-1);
							}
						jp.imgAdd(temp);
					}else if(check1 == 1&&check2==4){
						temp = jp.Images.get(jp.Images.size()-1);
						Graphics2D g2d = temp.createGraphics();
						g2d.setStroke(new BasicStroke(sWidth));
						g2d.setColor(sColor);
						g2d.drawLine(pointO.x,pointO.y, arg0.getX(),arg0.getY());
						temp.setRGB(arg0.getX(), arg0.getY(), sColor.getRGB());
						pointO = new Point(arg0.getX(),arg0.getY());
					}
				}
			}
		});
	}
}

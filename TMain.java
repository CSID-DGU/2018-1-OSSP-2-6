package com.ok.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line.Info;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.ok.shape.Line;
import com.ok.window.Tetris;

public class TMain extends JFrame {

	private Tetris uc;
	
	// ���� ���۸��� ���� ȭ�鿡 �̹����� ��� ���� Instance���̴�.
	private Image screenImage;
	private Graphics screenGraphic;

	// ��׶��� �̹��� ��ü ����
	private Image background = new ImageIcon(Main.class.getResource("../images/IntroBackground.jpg")).getImage();

	// �޴��� ��ü ����
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	// �޴� �� ���� exit button ���� ��ü ����
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private JButton exitButton = new JButton(exitButtonBasicImage);

	// SINGLE button ���� ��ü ����
	private Image singleImage = new ImageIcon(Main.class.getResource("../images/SingleBasic.png")).getImage();
	private ImageIcon singleBasicImage = new ImageIcon(Main.class.getResource("../images/SingleBasic.png"));
	private ImageIcon singleEnteredImage = new ImageIcon(Main.class.getResource("../images/SingleEntered.png"));
	private JButton singleBtn = new JButton(singleBasicImage);

	// Normal button ���� ��ü ����
	private Image normalImage = new ImageIcon(Main.class.getResource("../images/NormalBasic.png")).getImage();
	private ImageIcon normalBasicImage = new ImageIcon(Main.class.getResource("../images/NormalBasic.png"));
	private ImageIcon normalEnteredImage = new ImageIcon(Main.class.getResource("../images/NormalEntered.png"));
	private JButton normalBtn = new JButton(normalBasicImage);

	// Hard button ���� ��ü ����
	private Image hardImage = new ImageIcon(Main.class.getResource("../images/HardBasic.png")).getImage();
	private ImageIcon hardBasicImage = new ImageIcon(Main.class.getResource("../images/HardBasic.png"));
	private ImageIcon hardEnteredImage = new ImageIcon(Main.class.getResource("../images/HardEntered.png"));
	private JButton hardBtn = new JButton(hardBasicImage);

	// Back button ���� ��ü ����
	private Image backImage = new ImageIcon(Main.class.getResource("../images/BackBasic.png")).getImage();
	private ImageIcon backBasicImage = new ImageIcon(Main.class.getResource("../images/BackBasic.png"));
	private ImageIcon backEnteredImage = new ImageIcon(Main.class.getResource("../images/BackEntered.png"));
	private JButton backBtn = new JButton(backBasicImage);

	// Multi button ���� ��ü ����
	private Image multiImage = new ImageIcon(Main.class.getResource("../images/MultiBasic.png")).getImage();
	private ImageIcon multiBasicImage = new ImageIcon(Main.class.getResource("../images/MultiBasic.png"));
	private ImageIcon multiEnteredImage = new ImageIcon(Main.class.getResource("../images/MultiEntered.png"));
	private JButton multiBtn = new JButton(multiBasicImage);

	// Setting button ���� ��ü ����
	private Image settingImage = new ImageIcon(Main.class.getResource("../images/SettingBasic.png")).getImage();
	private ImageIcon settingBasicImage = new ImageIcon(Main.class.getResource("../images/SettingBasic.png"));
	private ImageIcon settingEnteredImage = new ImageIcon(Main.class.getResource("../images/SettingEntered.png"));
	private JButton settingBtn = new JButton(settingBasicImage);

	// Exit button ���� ��ü ����
	private ImageIcon exitBasicImage = new ImageIcon(Main.class.getResource("../images/ExitBasic.png"));
	private ImageIcon exitEnteredImage = new ImageIcon(Main.class.getResource("../images/ExitEntered.png"));
	private JButton exitBtn = new JButton(exitBasicImage);

	private boolean isStartScreen = true;
	
	private boolean isSingleModeScreen = false;
	private boolean isNormalModeScrren = false;
	private boolean isHardModeScreen = false;
	private boolean isMultiModeScreen = false;
	private boolean isSettingModeScreen = false;
	
	// ���콺 �̺�Ʈ�� Ȱ���ϱ� ���� ���콺 x, y ��ǥ
	private int mouseX, mouseY;

	public TMain() {
		
		
		playSound(new File("D:\\bgm.wav"), 1.0f, false); //������� ���
		
		setUndecorated(true); // �⺻ �޴��ٰ� ������ ����. -> ���ο� menuBar�� �ֱ� ���� �۾�
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // ȭ�� ũ�� ���� �Ұ���
		setLocationRelativeTo(null); // ȭ�� ���߾ӿ� �߰� ��.
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null); // ȭ�鿡 ��ġ�Ǵ� ��ư�̳� label�� �� �ڸ� �״�� ���� ��.

		// Menu bar exit ��ư ���� ó��
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		// exit Button �̺�Ʈ ó��
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage); // ���콺�� exit ��ư�� �ö󰡸� �̹����� �ٲ���.
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺�� �ö󰡸� �հ��� ������ιٲ�
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺�� ���� �ٽ� ����Ʈ ������� �ٲ�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		add(exitButton);

		// �޴��� �̺�Ʈ
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { // ���콺 Ŭ�� �� x,y ��ǥ�� ����.
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() { // �޴��ٸ� �巡�� �Ҷ� ȭ���� ������� �ϴ� �̺�Ʈ
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY); // JFrame�� ��ġ�� �ٲ���
			}
		});
		add(menuBar);

		// Single ��ư ���� ó��
		singleBtn.setBounds(130, 200, 400, 100);
		singleBtn.setBorderPainted(false);
		singleBtn.setContentAreaFilled(false);
		singleBtn.setFocusPainted(false);
		// exit Button �̺�Ʈ ó��
		singleBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				singleBtn.setIcon(singleEnteredImage); // ���콺�� exit ��ư�� �ö󰡸� �̹����� �ٲ���.
				singleBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺�� �ö󰡸� �հ��� ������ιٲ�
			}

			@Override
			public void mouseExited(MouseEvent e) {
				singleBtn.setIcon(singleBasicImage);
				singleBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺�� ���� �ٽ� ����Ʈ ������� �ٲ�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// �̱� ��� ���� �̺�Ʈó�� �κ�
				normalModeScreen();
			}
		});
		add(singleBtn);

		// Normal ��ư ���� ó��
		normalBtn.setBounds(130, 210, 400, 100);
		normalBtn.setBorderPainted(false);
		normalBtn.setContentAreaFilled(false);
		normalBtn.setFocusPainted(false);
		normalBtn.setVisible(false);
		// exit Button �̺�Ʈ ó��
		normalBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				normalBtn.setIcon(normalEnteredImage); // ���콺�� exit ��ư�� �ö󰡸� �̹����� �ٲ���.
				normalBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺�� �ö󰡸� �հ��� ������ιٲ�
			}

			@Override
			public void mouseExited(MouseEvent e) {
				normalBtn.setIcon(normalBasicImage);
				normalBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺�� ���� �ٽ� ����Ʈ ������� �ٲ�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// �븻 ��� ���� �̺�Ʈ
				normalModeScreen();
			}
		});
		add(normalBtn);

		// Hard ��ư ���� ó��
		hardBtn.setBounds(130, 340, 400, 100);
		hardBtn.setBorderPainted(false);
		hardBtn.setContentAreaFilled(false);
		hardBtn.setFocusPainted(false);
		hardBtn.setVisible(false);
		// hard Button �̺�Ʈ ó��
		hardBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardBtn.setIcon(hardEnteredImage); // ���콺�� exit ��ư�� �ö󰡸� �̹����� �ٲ���.
				hardBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺�� �ö󰡸� �հ��� ������ιٲ�
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardBtn.setIcon(hardBasicImage);
				hardBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺�� ���� �ٽ� ����Ʈ ������� �ٲ�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// �ϵ� ��� ���� �̺�Ʈó�� �κ�
			}
		});
		add(hardBtn);

		// Back ��ư ���� ó��
		backBtn.setBounds(130, 470, 400, 100);
		backBtn.setBorderPainted(false);
		backBtn.setContentAreaFilled(false);
		backBtn.setFocusPainted(false);
		backBtn.setVisible(false);
		// exit Button �̺�Ʈ ó��
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backBtn.setIcon(backEnteredImage); // ���콺�� exit ��ư�� �ö󰡸� �̹����� �ٲ���.
				backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺�� �ö󰡸� �հ��� ������ιٲ�
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backBtn.setIcon(backBasicImage);
				backBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺�� ���� �ٽ� ����Ʈ ������� �ٲ�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// �ڷ� ���� �̺�Ʈ ó���κ�
			}
		});
		add(backBtn);

		// Multi ��ư ���� ó��
		multiBtn.setBounds(130, 320, 400, 100);
		multiBtn.setBorderPainted(false);
		multiBtn.setContentAreaFilled(false);
		multiBtn.setFocusPainted(false);
		// Multi Button �̺�Ʈ ó��
		multiBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				multiBtn.setIcon(multiEnteredImage); // ���콺�� Multi ��ư�� �ö󰡸� �̹����� �ٲ���.
				multiBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺�� �ö󰡸� �հ��� ������ιٲ�
			}

			@Override
			public void mouseExited(MouseEvent e) {
				multiBtn.setIcon(multiBasicImage);
				multiBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺�� ���� �ٽ� ����Ʈ ������� �ٲ�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// ��Ƽ ��� ���� �̺�Ʈó�� �κ�
			}
		});
		add(multiBtn);

		// Setting ��ư ���� ó��
		settingBtn.setBounds(130, 440, 400, 100);
		settingBtn.setBorderPainted(false);
		settingBtn.setContentAreaFilled(false);
		settingBtn.setFocusPainted(false);
		// Setting Button �̺�Ʈ ó��
		settingBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				settingBtn.setIcon(settingEnteredImage); // ���콺�� Setting ��ư�� �ö󰡸� �̹����� �ٲ���.
				settingBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺�� �ö󰡸� �հ��� ������ιٲ�
			}

			@Override
			public void mouseExited(MouseEvent e) {
				settingBtn.setIcon(settingBasicImage);
				settingBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺�� ���� �ٽ� ����Ʈ ������� �ٲ�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// Settingâ ȭ������ �Ѿ��
			}
		});
		add(settingBtn);

		// exit ��ư ���� ó��
		exitBtn.setBounds(130, 560, 400, 100);
		exitBtn.setBorderPainted(false);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setFocusPainted(false);
		// exit Button �̺�Ʈ ó��
		exitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitBtn.setIcon(exitEnteredImage); // ���콺�� exit ��ư�� �ö󰡸� �̹����� �ٲ���.
				exitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺�� �ö󰡸� �հ��� ������ιٲ�
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitBtn.setIcon(exitBasicImage);
				exitBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺�� ���� �ٽ� ����Ʈ ������� �ٲ�
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(exitBtn);
	}

	public void paint(Graphics g) {

		// 1280X720��ŭ�� �̹����� ��������� screenImage�� �־���.
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics(); // screenImage�� �̿��� �׷����� ����.
		screenDraw(screenGraphic); // ��ũ���� �׷����� �׷���.
		g.drawImage(screenImage, 0, 0, null);
	}

	// ��׶��� �̹����� �׷��ش�.
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null); // drawImage�� �߰��� ���� �ƴ϶� �ܼ��� ȭ�鿡 �̹����� ����� �� ���� �Լ��̴�.
		if(isNormalModeScrren == true)
		{
			g.clearRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		}
		paintComponents(g); // JLabel, ��ư ���� Main Frame�� �߰��� �͵��� �׷� �ִ� ������ ��.
		this.revalidate();
		this.repaint(); // paint �Լ��� �ٽ� �ҷ����� �Լ�. �� �� ������������ ���� �׷��ִ� ������ ��.
	}

	public void singleModeScreen() {
		singleBtn.setVisible(false);
		multiBtn.setVisible(false);
		settingBtn.setVisible(false);
		exitBtn.setVisible(false);
		isStartScreen = false;
		isSingleModeScreen = true;
		if (isSingleModeScreen == true) {
			normalBtn.setVisible(true);
			hardBtn.setVisible(true);
			backBtn.setVisible(true);
		}
	}
	
	public void normalModeScreen() {
		isSingleModeScreen = false;
		isNormalModeScrren = true;
		if(isNormalModeScrren == true) {
			normalBtn.setVisible(false);
			hardBtn.setVisible(false);
			backBtn.setVisible(false);
			dispose();
			uc = new Tetris();
		}
	}
	
    Clip bgmclip;
    public void playBgm(File file, float vol, boolean repeat){
            try{
                    //BGM�� ������ �������� ������ų �� �־�� �ϹǷ� �������� ���� Clip�� ���
                    bgmclip = (Clip)AudioSystem.getLine(new Info(Clip.class));
                    bgmclip.open(AudioSystem.getAudioInputStream(file));
                    bgmclip.addLineListener(new LineListener() {
                            @Override
                            public void update(LineEvent event) {
                                    // TODO Auto-generated method stub
                                    System.out.println("" + event.getType());
                                    if(event.getType()==LineEvent.Type.STOP){
                                            bgmclip.close();
                                    }
                            }
                    });
                    FloatControl volume = (FloatControl)bgmclip.getControl(FloatControl.Type.MASTER_GAIN);
                    volume.setValue(vol);
                    bgmclip.start();
                    if(repeat)
                            bgmclip.loop(bgmclip.LOOP_CONTINUOUSLY);
            }catch(Exception e){
                    e.printStackTrace();
            }
    }
    public void stopBgm(){
           
            if(bgmclip!=null && bgmclip.isRunning()){
                    bgmclip.stop();
                    bgmclip.close();
            }
    }
    public void playSound(File file, float vol, boolean repeat){
            try{
                    final Clip clip = (Clip)AudioSystem.getLine(new Info(Clip.class));
                    clip.open(AudioSystem.getAudioInputStream(file));
                    clip.addLineListener(new LineListener() {
                            @Override
                            public void update(LineEvent event) {
                                    // TODO Auto-generated method stub
                                    if(event.getType()==LineEvent.Type.STOP){
                                            // BGM�� �޸𸮿� �׿��� crash �Ǵ°��� ����
                                            clip.close();
                                    }
                            }
                    });
                    FloatControl volume = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                    volume.setValue(vol);
                    clip.start();
                    if(repeat)
                            clip.loop(Clip.LOOP_CONTINUOUSLY);
            }catch(Exception e){
                    e.printStackTrace();
            }
    }
}

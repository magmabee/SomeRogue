package shell;
 import com.jogamp.newt.event.WindowEvent;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.awt.TextRenderer;

import Engine.GameScene;
import abstraction.Scene;
import data.GameSettings;

import com.jogamp.opengl.util.*;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
 
  public class Game extends JFrame implements GLEventListener {
      private static final long serialVersionUID = 1L;
     
      private GameSettings settings;
      
      
	  private Scene currentScene;
	  private boolean started = false;
      public Game() {
    	  super("Nameless rogue");
    	  GLProfile profile = GLProfile.getDefault();
    	  GLCapabilities capabilities = new GLCapabilities(profile);
    	  
    	  GLCanvas canvas = new GLCanvas(capabilities);
    	  canvas.addGLEventListener(this);
    	 
    	  this.setName("Nameless rogue");
    	  this.getContentPane().add(canvas);
    	  //TODO: move to config later
    	  final int width = 60;
          final int height = 40;
    	  settings = new GameSettings(width, height);
  
    	  this.setSize(width*settings.getFontSize(), height* settings.getFontSize());
    	  this.setLocationRelativeTo(null);
    	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	  this.setVisible(true);
    	  this.setResizable(false);
  
    	  canvas.requestFocusInWindow();
    	
    	  
    	  currentScene = new GameScene(this);
    	
    	  FPSAnimator animator = new FPSAnimator(60);
          animator.add(canvas);
          animator.start();
    	  
      }
      
      public int getActualWidth()
      {
    	  return settings.getWidth()*settings.getFontSize();
      }
      public int getActualHeight()
      {
    	  return settings.getHeight()*settings.getFontSize();
      }
      
      
      
      @Override
      public void display(GLAutoDrawable drawable) {
    	  if(started)
    	  {
       	   GL3 gl = drawable.getGL().getGL3();
    	   gl.glClear(GL3.GL_COLOR_BUFFER_BIT | GL3.GL_DEPTH_BUFFER_BIT);
    	   currentScene.update();
    	   currentScene.draw();
    	   gl.glFlush();
    	  }
      }
      
      
      
      public void play() {
    	  started  = true;
    	  while(true)
    	  {
    		 
    	  }
      }
 
     @Override
     public void dispose(GLAutoDrawable drawable) {
     }
 
     @Override
     public void init(GLAutoDrawable drawable) {
    	   GL3 gl = drawable.getGL().getGL3();
    	   gl.glClearColor(0,0,0, 1.0f);
     }
 
     @Override
     public void reshape(GLAutoDrawable drawable, int x, int y, int width,
             int height) {
     }



	public GameSettings getSettings() {
		return settings;
	}



	void setSettings(GameSettings settings) {
		this.settings = settings;
	}  
  }

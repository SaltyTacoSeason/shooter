package run;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import dev.create.Initiate;
import states.GameState;
import states.LobbyState;
import states.StateMachine;

public class GameLoop {
	
	//Is the game running in a normal state, should be true usually.
	public static boolean isDefaultContext = true;
	
	
	public StateMachine state;
	
	public String newState;
	private String previousState;

	public static int moveXDirection;

	public static int moveYDirection;

	public static int moveXDirectionp;

	public static int moveYDirectionp;
	
	public boolean shooting;

	
	public GameLoop() {
		
		
		
	}
	
	

	public void run() {
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		
		
		
		initiate();
		
		while (!glfwWindowShouldClose(Initiate.window.id)) {
		    
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if (delta >= 1) {
				input();
				tick(shooting);
				render();
				getSwitchStates();
				
				delta --;
			}
			
			if (timer >= 1000000000) {
				
				
				timer = 0;
			}
			
			
			
			
			//reset for next time?
			glfwSwapBuffers(Initiate.window.id);
			glfwPollEvents();
			
			
		}
		
	}

	private void render() {

		state.render();
		
		
		
		
		
		
	}



	private void tick(boolean shooting) {

		state.tick(shooting);
		
		
		
		
	}

	private void input() {

		state.input();
		state.input(moveXDirection, moveYDirection, moveXDirectionp, moveYDirectionp);
		
	}

	public void initiate() {
		
		state = new StateMachine();
		state.add("Game", (new GameState()));
		state.add("Lobby", (new LobbyState()));
		state.change("Lobby");
		newState = "Lobby";
		previousState = "Lobby";

	}
	
	public void getSwitchStates() {
		
		if (newState != previousState) {
			state.change(newState);
			previousState = newState;
		}
		
	}
	
	
}

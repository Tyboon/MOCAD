package agent;

import java.awt.Color;

import utils.Environnement;

public class Wall extends Agent{

	public Wall(Environnement env) {
		super(env);
		this.color = Color.GRAY;
	}

	
	public void decide() {;}

}

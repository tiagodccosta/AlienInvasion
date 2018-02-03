package Main;

import Classes.EntityA;
import Classes.EntityB;

public class Fisica {

	public static boolean Collision(EntityA enta, EntityB entb){
		
		if(enta.getBounds().intersects(entb.getBounds())){
			return true;
		}
	return false;
}

	public static boolean Collision(EntityB entb, EntityA enta){

		if(entb.getBounds().intersects(enta.getBounds())){
			return true;
		}
	return false;
}
	
}

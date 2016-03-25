/* COPY RIGHT Chayanne Paulino  */


import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Game {

	static Hero user;
	static Map map;
	static int Life;
	static int Strength;
	static int Agility;
	static Scanner in;
	static int levelup = 50;
	static String heroname;
	static 	String herorace;

	public static void main(String[] args) {

		in = new Scanner(System.in); 


		/*
		introduction to the game
		user need to create a Hero and the Race.
		also need to used 150 points.
		 */


		System.out.println("Welcome to the Game");
		System.out.println("Rules of the Game!");
		System.out.println("You need to create a Hero and specife what type of Race your Hero will be"
				+ " a Human or an Elf or a Dwarf. ");

		System.out.println("You will start with 150 points that you need to distribute to your Hero , "
				+ "Strength,Inteligence,Agility,Magic,Life");
		System.out.println(" ");
		System.out.println("Please enter a name of your Hero:"); // User Hero name
		while(!in.hasNext("[A-Za-z]+")){
			System.out.println("Please enter letter's only ");
			in.next();
		}
		String heroname = in.next();
		System.out.println("Please what type of Race you want:  Human - Elf - Dwarf"); // User Race 
		while(!in.hasNext("[A-Za-z]+")){
			System.out.println("Please enter letter's only ");
			in.nextInt();
		}
		String herorace = in.next();

		user = new Hero(heroname, Strength, Agility, Life, herorace, 0);

		point();
		
		System.out.println(" ");


		System.out.println(user.getName()+" the game it's about to start.");
		System.out.println("You enter in a room to check for a monter, get ready to fight");
		pause("");

		map = new Map();

		boolean valid= true;
		while(valid){

			System.out.println(" ");

			checkForMonster();
			advanceRoom();
			pause("");


		}

	}

	/* ********************************* POINTS  *****************************************/

	public static void point(){
		System.out.println("Plese Distribute your 150 points -> Strength,Agility,Life.");

		while (true){

			System.out.println("Strength ->");
			Strength = in.nextInt();
			System.out.println("Aagility ->");
			Agility = in.nextInt();
			System.out.println("Life ->");
			Life = in.nextInt();

			int total = Strength+Agility+Life;
			if(total  <= 150 || 150 >= total){
				System.out.println(" ");
				System.out.println(" ");
				break;
			}else{
				System.out.println("It's only 150 points ");
			}

		}

	}




	/* *********************** CHECKING FOR A MOSNTER ********************************** */
	public static void checkForMonster(){

		if(map.there_is_monster()){
			System.out.println("There is a monter");
			Battle();
			//pause("Enter");
		}else{
			System.out.println("There is no monter in the room");
			checkWeapon();


		}

	}


	/* **************************************** THE BATTLE ***************************** */
	public static void Battle(){


		while(true){

			//Monster Damage!
			map.getMonster().reduceLife(damage(Strength,Agility));
			if(map.getMonster().getLife() > 0){
				System.out.println(" Wait!! the " + map.getMonster().getType() + " is alive.");

				user.reducelife(damage(map.getMonster().getStrength(),map.getMonster().getAgility()));
				System.out.println(user.getName() +" You still alive " + user.getLife()*-1);
				battlePoints();


				pause("");
				break;
			}else{
				System.out.println(" ");
				System.out.println(" The " + map.getMonster().getType()+ " is killed ");

			}


			//user still alive
			user.reducelife(damage(map.getMonster().getStrength(),map.getMonster().getAgility()));
			System.out.println(user.getName() +" You still alive " + user.getLife()*-1);
			battlePoints();

			if (user.Life > 0){
				System.out.println(" You are alive");
				battlePoints();
				pause(" ");


			}else if (map.getMonster().getLife() > user.getLife()){
				System.out.println(user.name + " you have been kill by " + map.getMonster().getType());
				pause("");

			}else {
				pause("");
			}



		}	
	}

	/* *********************************** CHEKING FOR A WAEPON ************************ */
	public static void checkWeapon(){

		if(map.look_for_weapon() != null){

			if(map.look_for_weapon().getType() == "sword"){

				System.out.println("You got a sword");

			}else if(map.look_for_weapon().getType() == "arc"){

				System.out.println("You got a arc");

			}else if(map.look_for_weapon().getType() == "axes"){

				System.out.println("You got a axes");
			}
		}else{
			System.out.println("No weapon");
		}


	}


	/* *********************************** ADVANCE TO THE ROOM ************************* */
	public static void advanceRoom(){

		System.out.println(user.getName()+ " Advance to new Room");

		if (map.doors().length > 1) { 
			System.out.println("The room have " + map.doors().length + " doors.");

		}else {
			System.out.println("Its only 1 door");
			System.out.println("Please continue...");
		}
		System.out.println("");
		for (int i= 0; i< map.doors().length-1;i++){
			System.out.println(" " + map.doors() [i] + ",");
		}
		// end door.

		if (map.doors().length > 1){
			System.out.println("and " + map.doors()[map.doors().length-1]);

		}else {
			System.out.println(" " + map.doors()[map.doors().length-1] );
		}


		// aks the user for room
		System.out.println("Select a door");

		char userRoom = in.next().charAt(0);

		map.advance_room(userRoom);

		// start room.
		System.out.println(" ");
		System.out.println("");
		checkForMonster();
		pause("Enter to continue...");

	}



	/* ***************************************** DAMAGE ******************************** */
	public static int damage(int a, int s){

		return (int) ((((generator(6)+1)*a)*0.07) + (((generator(12)+1)*s)*0.3));

	}


	/* ************************************Generator the random numb ******************* */
	public static int generator(int a){

		Random x = new Random(a);
		int n = x.nextInt(a)+1;

		return n;

	}


	/* ************************************THE PAUSE METHOD **************************** */
	public static void pause(String prompt){
		System.out.println(" ");
		System.out.println("Press Enter to continue");


		try {
			System.in.read();
		} catch (IOException e){
			e.printStackTrace();
		}
	}


	/* **************************************BATTLE POINTS ***************************** */
	public static void battlePoints(){

		if(map.getMonster().getType() == "Dark Elf"){
			if(user.getExperience() + 10 >=levelup){
				System.out.println("Good you advance 1 Level and you earned 10 Experience points!");
				user.setExperience(user.getExperience() +10);
				user.level +=1;
				levelup  += 50;
				pointsUp();
			}else {
				System.out.println("Good you get 10 Experience points");
				user.setExperience(user.getExperience() + 10);
			}
		}else if(map.getMonster().getType() == "Orc"){
			if(user.getExperience() + 20 >= levelup){
				System.out.println("Good you advance 1 Level and you earned 10 Experience points!");
				user.setExperience(user.getExperience() +20);
				user.level +=1;
				levelup  += 50;
				pointsUp();
			}else{ 
				System.out.println("Good you get 20 Experience points");
				user.setExperience(user.getExperience()+20);
			}
		}else if(map.getMonster().getType() == "Dragon"){
			if(user.getExperience() + 30 >= levelup){
				System.out.println("Good you advance 1 Level and you earned 10 Experience points!");
				user.setExperience(user.getExperience() +30);
				user.level +=1;
				levelup  += 50;
				pointsUp();
			}else{
				System.out.println("Good you get 30 Experience points");
				user.setExperience(user.getExperience()+30);

			}
		}else if(map.getMonster().getType() == "Sponge Bob"){
			if(user.getExperience() + 40 >= levelup){
				System.out.println("Good you advance 1 Level and you earned 10 Experience points!");
				user.setExperience(user.getExperience() +40);
				user.level +=1;
				levelup  += 50;
				pointsUp();

			}else {
				System.out.println("Good you get 40 Experience points");
				user.setExperience(user.getExperience()+40);
			}
		}

	}
	/* *****************************************points up ****************************** */
	public static void pointsUp(){

		int pointsleft;

		// let user know that need to set new points to the characteristics. 
		System.out.println(" ");
		System.out.println(user.getName() + " you just earn 10 points that you need to distribute to your characteristics"
				+ " (Strength,Life, Agility) ");

		System.out.println(" ");


		// life

		while(true){
			System.out.println("Please enter how many you want in life");
			Life = in.nextInt();
			if(Life > 0 && Life <= 10){
				user.setLife(user.getLife() + Life);
				break;		
			}else {
				System.out.println("you only are a allow to distribute from 0 to 10.");

			}

			System.out.println(" ");
		}

		// Strength

		while(true){
			System.out.println("Please enter how many you want in Strength");
			pointsleft = 10 - Life;

			if(pointsleft == 0){
				break;	
			}
			System.out.println("you only are a allow to distribute from 0 to"+ pointsleft);
			Strength = in.nextInt();
			if(Strength >= 0 && Strength <= pointsleft){
				user.setStrength(user.getStrength()+Strength);	
				break;
			}else{
				System.out.println("you only are a allow to distribute from 0 to"+ pointsleft);
			}
		}
		// Agility

		Agility = 10 - (Life + Strength);
		user.setAgility(user.getAgility()+ Agility);

	}


}



/* COPYRIGTH Chayanne Paulino*/

public  class Hero {
	
	String name;
	int Strength;
	int Agility;
	int Life;
	String Race;
	int Experience;
	int level;
	
	

	
	public Hero(String n,int s, int a, int l, String r,int e){
		 name = n;
		 Strength = s;
		 Agility = a;
		 Life = l;
		 Race= r;
		 Experience = e;
		 level =1;
		
	}

	
	public void reducelife(int damage){
		this.Life -= damage;
		
	}

	public int getExperience() {
		return Experience;
	}

	public void setExperience(int experience) {
		Experience = experience;
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public String getRace() {
		return Race;
	}
	public void setRace(String race) {
		Race = race;
	}

	public int getStrength() {
		return Strength;
	}
	public void setStrength(int strength) {
		Strength = strength;
	}

	public int getAgility() {
		return Agility;
	}
	public void setAgility(int agility) {
		Agility = agility;
	}
	
	public int getLife() {
		return Life;
	}
	public void setLife(int life) {
		Life = life;
	}
}

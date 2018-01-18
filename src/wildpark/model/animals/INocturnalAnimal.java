package wildpark.model.animals;

//active only at night 
public interface INocturnalAnimal { 
	final boolean isActiveDuringTheDay = false;
	final boolean isActiveDuringTheNight = true;
	public boolean isNocturnal();
}

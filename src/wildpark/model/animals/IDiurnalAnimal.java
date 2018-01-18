package wildpark.model.animals;

//active only at night 
public interface IDiurnalAnimal { 
	final boolean isActiveDuringTheDay = true;
	final boolean isActiveDuringTheNight = false;
	public boolean isDiurnal();
}

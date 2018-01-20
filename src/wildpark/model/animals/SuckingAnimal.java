package wildpark.model.animals;

import wildpark.model.*;

public interface SuckingAnimal extends SwallowingAnimal 
{
	public Food suck( Food food );
}
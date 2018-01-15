package wildpark.model.animals;

import wildpark.model.*;

public interface ChewingAnimal
extends SwollowingAnimal  
{
	public Food chew( Food food );
}

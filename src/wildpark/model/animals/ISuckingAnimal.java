package wildpark.model.animals;

import wildpark.model.*;

public interface ISuckingAnimal extends ISwallowingAnimal 
{
	public Food suck( Food food );
}
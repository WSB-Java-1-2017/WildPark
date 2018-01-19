package wildpark.model.animals;

import wildpark.model.*;

public interface IChewingAnimal extends ISwallowingAnimal {
	public Food chew( Food food );
}

package wildpark.model.animals;

public interface IPredator extends ICarnivorousAnimal {
	public boolean hunt( Animal animal );
}

package wildpark.model.animals.mammals;
import java.time.Duration;
import wildpark.model.*;

public final class HyenaSpecification extends AnimalSpeciesSpecification {
	private final String NAME = "Hyena";
	private final float ADULT_WEIGHT =63f;	// weight in kg 
	private final float NEWBORN_WEIGHT = 70f; // in kg
	private final float FOOD_QUANTITY_REQUIRED_PER_DAY = 4f; // in kg
	private final int MAX_STARVING_DAYS_BEFORE_DEATH = 5;
	private final int HUNGER_ENERGY_PERCENT = 70; // poniЕјej tej wartoЕ›ci zwierze poszukuje jedzenia. PowyЕјej zwierze nie jest zainteresowane jedzeniem 
	private final float STANDARD_SPEED = 10;	// km/h, sprawdzamy jakД… odlegЕ‚oЕ›Д‡ zwierzД™ standardowo pokonuje w ciД…gu dnia (w czasie godzin aktywnoЕ›ci) i na tej podstawie obliczamy stardard w km/h
	private final int MAX_SPEED = 60;	// km/h
	private final int MAX_STAMINA = 100; 	//
	private final int AVERAGE_SCION_COUNT_IN_LITTER = 4; // Е›rednia liczba potomkГіw w miocie
	private final int MAX_SCION_COUNT_IN_LITTER = 8;	// na tej podstawie okreЕ›limy wideЕ‚ki RANDOMa okreЕ›lajД…cego liczbД™ potomkГіw w danym miocie
	private final Duration MAX_AGE = Duration.ofDays(25*365); 
	private final Duration MIN_BREEDING_AGE = Duration.ofDays(2*365); // minimalny wiek rozrodczy
	private final Duration MAX_BREEDING_AGE = Duration.ofDays(20*365); // maksymalny wiek rozrodczy
	private final Duration MAX_AGE_IN_NEST = Duration.ofDays(2*365); // po ilu 
	private final Duration MIN_SELF_GOVERNMENT_AGE = Duration.ofDays(2*365); // minimalny wiek usamodzielnienia siД™
	private int CALORIC_EFFICIENCY_PER_KILO = 2320; // Cal/kg
	
	
	private static final CellType[] acceptableCellTypes = {
			CellType.FOREST,
			CellType.GRASS,
			CellType.MOUNTAIN		
		};

		public static CellType[] getAcceptableCellTypes() {
			return acceptableCellTypes;
		}

		public static boolean acceptsCellType( CellType cellType ) {
			boolean accepts = false;
			for( CellType type : acceptableCellTypes ) {
				System.out.println( "OK - ISBSpecification: "+type );
				if( type == cellType ) {
					accepts = true;
					break;
				}
			}
			return accepts;
		}



		private static final FoodType[] edibleFoodTypes = {
			FoodType.FLY
		};

		public static FoodType[] getEdibleFoodTypes() {
			return edibleFoodTypes;
		}

		/**
	 	 * Determines if this species eats particular type of Food/Meat/Animal/Plant
		 * @param  foodType FoodType object 
		 * @return          boolean - true if this species eats given FoodType
		 */
		public static boolean eatsFoodType( FoodType foodType ) {
			boolean eats = false;
			for( FoodType type : edibleFoodTypes ) {
				if( type == foodType ) {
					eats = true;
					break;
				}
			}
			return eats;
		}



		public static WildParkAreaCell selectRandomCell() {   
	        WildParkAreaCell areaCell = null;
	        // Get a random WildParkAreaCell acceptable for the particular species
	        boolean isAcceptable = false;
	        do {
	            areaCell =  WildPark.cellArray[ new Random().nextInt( WildPark.WILD_PARK_AREA_WIDTH ) ][ new Random().nextInt( WildPark.WILD_PARK_AREA_HEIGHT ) ];      
	            if( acceptsCellType( areaCell.getCellType() ) ) {
	                isAcceptable = true;
	            } 
	            System.out.println( "Random cell for " + "Hyena" + " ----- cellType: " + areaCell.getCellType() + " --- " + isAcceptable );    
	        } while( !isAcceptable );

	        return areaCell;
		}
	
	
		@Override
		public String getSPECIES_NAME() {
			return NAME;
		}

		public float getADULT_WEIGHT() {
			return ADULT_WEIGHT;
		}
		
		public float getNEWBORN_WEIGHT() {
			return NEWBORN_WEIGHT;
		}
		
		public float getFOOD_QUANTITY_REQUIRED_PER_DAY() {
			return FOOD_QUANTITY_REQUIRED_PER_DAY;
		}
		
		public int getMAX_STARVING_DAYS_BEFORE_DEATH() {
			return MAX_STARVING_DAYS_BEFORE_DEATH;
		}

		public int getHUNGER_ENERGY_PERCENT() {
			return HUNGER_ENERGY_PERCENT;
		}
		
		public float getSTANDARD_SPEED() {
			return STANDARD_SPEED;
		}
		
		public int getMAX_SPEED() {
			return MAX_SPEED;
		}

		public int getMAX_STAMINA() {
			return MAX_STAMINA;
		}

		public int getAVERAGE_SCION_COUNT_IN_LITTER() {
			return AVERAGE_SCION_COUNT_IN_LITTER;
		}
		
		public int getMAX_SCION_COUNT_IN_LITTER() {
			return MAX_SCION_COUNT_IN_LITTER;
		}
		
		public Duration getMAX_AGE() {
			return MAX_AGE;
		}

		public Duration getMIN_BREEDING_AGE() {
			return MIN_BREEDING_AGE;
		}
		
		public Duration getMAX_BREEDING_AGE() {
			return MAX_BREEDING_AGE;
		}
		
		public Duration getMAX_AGE_IN_NEST() {
			return MAX_AGE_IN_NEST;
		}
		
		public Duration getMIN_SELF_GOVERNMENT_AGE() {
			return MIN_SELF_GOVERNMENT_AGE;
		}
		
		public int getCALORIC_EFFICIENCY_PER_KILO() {
			return CALORIC_EFFICIENCY_PER_KILO;
		}	
}

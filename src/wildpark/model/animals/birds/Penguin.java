public class Penguin extends Bird implements DivingAnimal, WalkingAnimal, EggBearingAnimal, EggIncubatingAnimal, Predator {
    
    private String name;
    int ADULT_WEIGHT;
    float FOOD_QUANTITY_REQUIRED_PER_DAY; // in kg's
    int MAX_STARVING_DAYS_BEFORE_DEATH;
    int daysSinceLastMeal;
    int energyPercent;
    int maxEnergyPercent;
    int STANDARD_SPEED;
    int MAX_SPEED;
    int MAX_STAMINA;
    int AVERAGE_SCION_COUNT_IN_LITTER;
    int MAX_AGE;
    int MIN_BREEDING_AGE;
    int MAX_BREEDING_AGE;
    int MAX_AGE_IN_NEST;
    int MIN_SELF_GOVERNMENT_AGE;
    int age;
    Gender gender;
    boolean isProliferating;
    boolean isFeedingNewborns;
    Duration timeOfBirth;
    Duration timeOfDeath;

    public Penguin() {
        this.name = "Pingwin";
        this.ADULT_WEIGHT = 20;
        this.FOOD_QUANTITY_REQUIRED_PER_DAY = 1;
        this.MAX_STARVING_DAYS_BEFORE_DEATH = 1;
        this.daysSinceLastMeal = 0;
        this.energyPercent = 100;
        this.maxEnergyPercent = 120;
        this.STANDARD_SPEED = 1;
        this.MAX_SPEED = 1;
        this.MAX_STAMINA = 1;
        this.AVERAGE_SCION_COUNT_IN_LITTER = 1;
        this.MAX_AGE = 40;
        this.MIN_BREEDING_AGE = 1;
        this.MAX_BREEDING_AGE = 99;
        this.MAX_AGE_IN_NEST = 1;
        this.MIN_SELF_GOVERNMENT_AGE = 1;
        this.age = 0;
        this.gender = Gender.MALE;
        this.isProliferating = true;
        this.isFeedingNewborns = true;
        this.timeOfBirth = null;
        this.timeOfDeath = null;
    }

    public Food getFood(WildparkAreaCell cell){

    }

    public Food eat(Food food){

    }

    public Movement move(float time){

    }

    public void proliferate() {

    }

    public void sleep(){

    }

    public void wakeUp(){

    }

    public void die(){

    }

    public void swallow(){
        System.out.println("Pingwin polyka");
    }
    public void dive(){
        System.out.println("Pingwin nurkuje");
    }
    public void walk(){
        System.out.println("Pingwin chodzi");
    }
    public void bearEggs(){
        System.out.println("Pingwin znosi jajko");
    }
    public void incubateEggs(){
        System.out.println("Pingwin wysiaduje jajka");
    }
    public boolean hunt(Pike pike){
        // returns 0 or 1 if hunt was successful
    }
    public boolean hunt(Macrel macrel){
        // returns 0 or 1 if hunt was successful
    }
}
package wildpark.animals.birds;

import java.time.Duration;

public class Penguin extends Bird implements DivingAnimal, WalkingAnimal, EggBearingAnimal, EggIncubatingAnimal, Predator {
    
    private final AnimalSpeciesSpecification animalSpeciesSpecification;
    private String name;

    public Penguin() {
        this.animalSpeciesSpecification = new PenguinSpecification();
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
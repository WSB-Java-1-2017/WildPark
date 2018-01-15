public class Giraffe extends Mammal implements TreeEatingAnimal, WalkingAnimal, RunningAnimal, ChewingAnimal, Diurnal {

	


	

		abstract eat(int foodWeight){
        // tu uzupełnimy stan najedzenia.
		}
		

     	/**
		 *  Implementation of abstract method declared in Animal. 
		 *  @return move speed 
		 */



		abstract void move() {

		}

		void eatPlant() {

		}

		

		/**
		 * @return Returns moving speed while walking
		 */
		int walk() {

		}


		/**
		 * @return Returns moving speed while running.
		 */

		int run() {

		}


		public boolean Diurnal(){
         return true;
        		}

 }

}

}
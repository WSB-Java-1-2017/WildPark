/**
 * Użycie modelu MVC (Model-View-Controller) okazało się za bardzo skomplikowane dla studentów pierwszego semestru. 
 * Dlatego ta aplikacja jest intencjonalnie zaprojektowana bez użycia modelu MVC, aby stanowiła
 * "punkt wyjścia" dla kolejnej wersji projektu, która zostanie utworzona w następnym semestrze i będzie 
 * zaprojektowana zgodnie z filozofią MVC. To pozwoli na zaprezentowanie studentom przewagi modelu MVC
 * nad modelem prostym.
 */

package wildpark;

import java.util.ArrayList;
import java.util.Random;
import java.time.Duration;
import java.util.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Separator;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.control.ScrollPane;
//import javafx.scene.effect.DropShadow;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.paint.Paint;

import wildpark.controller.*;
import wildpark.model.*;
import wildpark.model.animals.*;
import wildpark.model.animals.mammals.*;
import wildpark.model.animals.birds.*;
import wildpark.model.animals.reptiles.*;
//import wildpark.model.animals.fish.*;

public class WildPark extends Application {
    
    public static final int WILD_PARK_AREA_WIDTH = 100;
    public static final int WILD_PARK_AREA_HEIGHT = 50;
    final int WILD_PARK_OCEAN_AVERAGE_WIDTH = (int)(0.30*WILD_PARK_AREA_WIDTH); // 30% of Park Area Width

    final int WILD_PARK_POLAR_AREA_AVERAGE_WIDTH = 35;
    final int WILD_PARK_POLAR_AREA_HEIGHT = 15;

    final int WILD_PARK_LAKE_AVERAGE_WIDTH = 35;
    final int WILD_PARK_LAKE_HEIGHT = 15;

    final int WILD_PARK_CELL_WIDTH = 54;
    final int WILD_PARK_CELL_HEIGHT = 54;

    public static Diagram01 diagram = new Diagram01(getAnimals());

    /**
     * Duration Time of the existance of current Wild Park.
     * Initially it is ZERO. 
     * In each Time Step of the Wild Park this value is increased by the amount of time 
     * defined in WILD_PARK_TIME_STEP_DURATION constant.
     */
    private static Duration wildParkTime = Duration.ZERO;

    /**
     * This defines the duration of a single step of Wild Park Duration Time. 
     * In each Step the value of wildParkTime attribute is increased 
     * by this value - in method makeWildParkTimeStep().
     */
    private static final Duration WILD_PARK_TIME_STEP_DURATION = Duration.ofHours(1);

    public static Duration getWildParkTimeStepDuration() {
        return WILD_PARK_TIME_STEP_DURATION;
    }

    /**
     * Returns the current Wild Park Time counter value
     * @return wildParkTime value - the current time counter of Wild Park
     */
    public static Duration getWildParkTime() {
    	//System.out.println("Access to park time: " + wildParkTime.toHours());
        return wildParkTime;
    }
    
    public static int getWildParkTimeHours() {
    	return (int) wildParkTime.toHours();
    }

    /**
     * Make a single time step of Wild Park - add a single Wild Park Time unit defined in WILD_PARK_TIME_STEP_DURATION constnt
     */
    public static void makeWildParkTimeStep() {
        wildParkTime = wildParkTime.plus( WILD_PARK_TIME_STEP_DURATION );
    	//System.out.println("Addition to park time: " + wildParkTime.toHours());

        // Update Step Counter in UI
        toolBarLabel_CurrentStep.setText( String.format( "%8d", wildParkTime.toHours() ) );

        for( Animal animal : getAnimals() ) {
            animal.performTimeStep();
        }
        //diagram.updateList(getAnimals());
        //diagram.nextStep();
    }

    /**
	 * Main 2-dimension Array of WildParkArea Cells
	 */
	public static WildParkAreaCell[][] cellArray = new WildParkAreaCell[WILD_PARK_AREA_WIDTH][WILD_PARK_AREA_HEIGHT];

    /**
     * The collection of all animals in Wild Park. It also contains dead animals - animals 
     * are not removed from this collection after death but are treated as Meat == Food. 
     */
    static private ArrayList<Animal> animals = new ArrayList<>();


    /**
     * Getter for animals attribute. animals array contains all animals in the park.
     * @return ArrayList of all animals in the park.
     */
    static ArrayList<Animal> getAnimals() {
        return animals;
    }

    public static void addAnimal( Animal animal ) {
        getAnimals().add( animal ); 
        label_NumberOfAnimals.setText( String.format( "Number of animals: %10d", getAnimals().size() ) );
    } 

    // final Background BACKGROUND_GREEN = new Background( new BackgroundFill( Paint.valueOf( "0x00ff0088" ), new CornerRadii( 5 ), new Insets(1,1,0,0) ) );
    final Background BACKGROUND_GREEN = new Background( new BackgroundFill( Paint.valueOf( "linear-gradient(from 0px 54px to 0px 0px, #44ff55 20%, 0x44ff5555 100%)" ), new CornerRadii( 5 ), new Insets(1,1,0,0) ) );
    final Background BACKGROUND_OCEAN_BLUE = new Background( new BackgroundFill( Paint.valueOf( "linear-gradient(from 0px 54px to 0px 0px, #2299ff 20%, 0x2299ff55 100%)" ), new CornerRadii( 5 ), new Insets(1,1,0,0) ) );
    final Background BACKGROUND_POLAR_WHITE = new Background( new BackgroundFill( Paint.valueOf( "linear-gradient(from 0px 54px to 0px 0px, #eeeeee 20%, 0xffffffff 100%)" ), new CornerRadii( 5 ), new Insets(1,1,0,0) ) );
    final Background BACKGROUND_LAKE_BLUE = new Background( new BackgroundFill( Paint.valueOf( "linear-gradient(from 0px 54px to 0px 0px, #66ddff 20%, 0x66ddff55 100%)" ), new CornerRadii( 5 ), new Insets(1,1,0,0) ) );

    // MENU
    MenuItem menu1_New = new MenuItem("New");
    MenuItem menu1_Open = new MenuItem("Open...");
    MenuItem menu1_Save = new MenuItem("Save...");
    MenuItem menu1_Exit = new MenuItem("Exit");
    //---------------------
    MenuItem menu2_AnimalList = new MenuItem("Animal List...");
    MenuItem menu2_AnimalCountDiagram = new MenuItem("Animal Count Diagram...");
    MenuItem menu2_GenerateWildParkArea = new MenuItem("Generate Wild Park Area...");
    //---------------------
    MenuItem menu3_AnimalSettings = new MenuItem("Animal Settings...");
    MenuItem menu3_WildParkSettings = new MenuItem("Wild Park Settings...");
    //---------------------
    MenuItem menu4_Help = new MenuItem("Help...");
    MenuItem menu4_AboutWildPark = new MenuItem("About Wild Park...");


    // TOOLBAR
    Button toolBarButton_New = new Button("New");
    Button toolBarButton_Open = new Button("Open");
    Button toolBarButton_Save = new Button("Save");
    Button toolBarButton_Reset = new Button("Reset");

    static Label toolBarLabel_CurrentStep = new Label( String.format( "%8d", wildParkTime.toHours() ) ); 
    Button toolBarButton_Step = new Button("Step");

    int playSpeed = 1; // Time Steps per second

    TextField textField_PlaySpeed = new TextField() {
        @Override
        public void replaceText(int start, int end, String text) {
            if( text.matches("[0-9]") ) 
                if( textField_PlaySpeed.getLength() < 3 ) {
                super.replaceText(start, end, text);   
                playSpeed = Integer.parseInt( super.getText() );                  
            } 
            else
                super.clear(); 
        }

        @Override
        public void replaceSelection(String text) {
            if ( text.matches("[0-9]") ) 
                if( textField_PlaySpeed.getLength() < 3 ) {
                    super.replaceSelection(text);
                    playSpeed = Integer.parseInt( super.getText() ); 
                }
                else
                    super.clear();
        }
    };       


    Button toolBarButton_Play = new Button("Play");
    boolean stopButtonClicked = true; // This is set to false, when Play button is clicked. Then it is set to true, when Stop button is clicked. It is used by Play function.

    int numberOfStepsToRun = 10; // when Run button is clicked

    TextField textField_NumberOfStepsToRun = new TextField() {
        @Override
        public void replaceText(int start, int end, String text) {
            if( text.matches("[0-9]") ) {
                if( super.getText().length() > 4 )
                    super.clear();
                else {
                    super.replaceText(start, end, text);   
                    numberOfStepsToRun = Integer.parseInt( super.getText() );  
                }                
            } 
        }

        @Override
        public void replaceSelection(String text) {
            if ( text.matches("[0-9]") ) {
                if( super.getText().length() > 4 )
                    super.clear();
                else {
                    super.replaceSelection(text);
                    numberOfStepsToRun = Integer.parseInt( super.getText() ); 
                }
            }
        }
    };       


    Button toolBarButton_Run = new Button("Run");
    
    Button toolBarButton_Settings = new Button("Settings");
    Button toolBarButton_Reports = new Button("Reports");
    Button toolBarButton_Help = new Button("Help");

    
    // STATUS BAR:
    static Label label_NumberOfAnimals = new Label( String.format( "Number of animals: %10d", 0 ) );
    Button button_SearchAnimal = new Button("Search"); 


    Stage stage;
    GridPane wildParkGrid = new GridPane();

















    @Override
    public void start( Stage stage ) {
 //       Group root = new Group();
        this.stage = stage;

        try {
            //stage.getIcons().add(new Image(this.getClass().getResource("login.png").toString()));
            
            //Image applicationIcon = new Image(getClass().getResourceAsStream("dukes_36x36.png"));
            // primaryStage.getIcons().add(applicationIcon);            
            stage.getIcons().add(new Image("wildpark/favicon-32x32.png"));

        } catch( IllegalArgumentException e ) {
            System.out.println( "Error loading favicon-32x32.png. Should be in \'wildpark\' directory together with WildPark.class file." );
            // System.exit(-1);
        }

        addUIEventListeners();

        wildParkGrid.setPadding( new Insets(10, 10, 10, 10) );
        wildParkGrid.setVgap(0);    // gap between cells
        wildParkGrid.setHgap(0);    // gap between cells
 
        Slider slider = new Slider( 0.34, 2, 0.34 );    // Slider( min, max, initial_value )
        slider.setOrientation( Orientation.VERTICAL );
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(0.25f);
        slider.setBlockIncrement(0.1f);
//        slider.setSnapToTicks(true);
        wildParkGrid.scaleXProperty().bind(slider.valueProperty());
        wildParkGrid.scaleYProperty().bind(slider.valueProperty());

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent( wildParkGrid );
//  scrollPane.setPannable(true);
//  scrollPane.setFitToWidth(true);
        scrollPane.setHvalue(0.5);        
        scrollPane.setVvalue(0.5);        

        final Menu menu1 = new Menu("File");
        menu1.getItems().addAll( menu1_New, menu1_Open, menu1_Save, new SeparatorMenuItem(), menu1_Exit );
        final Menu menu2 = new Menu("Reports");
        menu2.getItems().addAll( menu2_AnimalList, menu2_AnimalCountDiagram,  menu2_GenerateWildParkArea );
        final Menu menu3 = new Menu("Settings");
        menu3.getItems().addAll( menu3_AnimalSettings, menu3_WildParkSettings );
        final Menu menu4 = new Menu("Help");
        menu4.getItems().addAll( menu4_Help, menu4_AboutWildPark );

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll( menu1, menu2, menu3, menu4 );

        textField_PlaySpeed.setPrefColumnCount(2);
        textField_PlaySpeed.setText( String.format( "%d", playSpeed ) );
        toolBarButton_Play.setMinWidth( 42 );   // ...to avoid size change when "Play" is switched to "Stop"

        textField_NumberOfStepsToRun.setPrefColumnCount(3);
        textField_NumberOfStepsToRun.setText( String.format( "%d", numberOfStepsToRun ) );

        ToolBar toolBar = new ToolBar(
            toolBarButton_New,
            toolBarButton_Open,
            toolBarButton_Save,
            toolBarButton_Reset,
            new Separator( Orientation.VERTICAL ),
            new Label( "Current Step:"),
            toolBarLabel_CurrentStep,
            toolBarButton_Step,
            new Separator( Orientation.VERTICAL ),
            new Label( "Play Speed:"),
            textField_PlaySpeed,
            new Label( "steps/sec."),
            toolBarButton_Play,
            new Separator( Orientation.VERTICAL ),
            new Label( "Run"),
            textField_NumberOfStepsToRun,
            new Label( "steps"),
            toolBarButton_Run,            
            new Separator( Orientation.VERTICAL ),

            toolBarButton_Reports,
            toolBarButton_Settings,
            toolBarButton_Help
        );
        toolBarLabel_CurrentStep.setMinWidth(40);

        HBox statusBar = new HBox(10);  // HBox( spacing )
        HBox.setMargin( label_NumberOfAnimals, new Insets(6,4,4,43) );    // Insets( top, right, bottom, left )
        Label label_FindAnimal = new Label("Find animal:");
        HBox.setMargin( label_FindAnimal, new Insets(6,0,4,40) );    // Insets( top, right, bottom, left )
        TextField textField_FindAnimal = new TextField();
        HBox.setMargin( textField_FindAnimal, new Insets(2,0,2,0) );    // Insets( top, right, bottom, left )       
        HBox.setMargin( button_SearchAnimal, new Insets(2,0,2,0) );    // Insets( top, right, bottom, left )
        statusBar.setAlignment(Pos.BOTTOM_CENTER);
        statusBar.getChildren().addAll( label_NumberOfAnimals, label_FindAnimal, textField_FindAnimal, button_SearchAnimal );    

        stage.setTitle("Wild Park 1.04");


// anchorPane.getChildren().addAll(scrollPane, label);
// AnchorPane.setTopAnchor(scrollPane, 0.0);
// AnchorPane.setLeftAnchor(scrollPane, 0.0);
// AnchorPane.setRightAnchor(scrollPane, 0.0);
// AnchorPane.setBottomAnchor(scrollPane, 0.0);
// AnchorPane.setLeftAnchor(label, 0.0);

// DoubleBinding labelLayoutYBinding = Bindings.createDoubleBinding(
// () -> scrollPane.getViewportBounds().getHeight() - wildParkGrid.getHeight(),
// wildParkGrid.heightProperty(),
// scrollPane.viewportBoundsProperty());

                                    // BorderPane( center                                                   , top, right, bottom  , left )
                                                    // BorderPane( center    , top   , right, bottom, left ) 
        stage.setScene( new Scene( new BorderPane( new BorderPane( scrollPane, toolBar, null, null, slider ), menuBar, null, statusBar, null ) ) );

        
        initializeWildParkArea();
        newWildParkArea();

        stage.setWidth(1000);
        stage.setHeight(600);
        stage.show();

        textField_PlaySpeed.setAlignment(Pos.CENTER_RIGHT); // setAlignment MUST be called after the TextField was added to the scene
        textField_NumberOfStepsToRun.setAlignment(Pos.CENTER_RIGHT); // setAlignment MUST be called after the TextField was added to the scene

    }










    void initializeWildParkArea() {
        //WildParkAreaCell areaCell;
        for( int y=0; y<WILD_PARK_AREA_HEIGHT; y++ ) {
            for( int x=0; x<WILD_PARK_AREA_WIDTH; x++ ) {
                final WildParkAreaCell areaCell = new WildParkAreaCell( x, y, x + " : " + y );;
                cellArray[x][y] = areaCell;

                areaCell.setMinSize( WILD_PARK_CELL_WIDTH, WILD_PARK_CELL_HEIGHT );
                areaCell.setMaxSize( WILD_PARK_CELL_WIDTH, WILD_PARK_CELL_HEIGHT );

                areaCell.setBackground( Background.EMPTY );
                // cellArray[x][y].setBackground( new Background( new BackgroundFill( Paint.valueOf( "0x00FF00" ), CornerRadii.EMPTY, Insets.EMPTY ) ) );
                //areaCell.setBorder( null );
                GridPane.setConstraints( areaCell, x, y );
                wildParkGrid.getChildren().add( areaCell );

                areaCell.setOnAction( new EventHandler<ActionEvent>() {
                    @Override 
                    public void handle( ActionEvent e ) {
                        System.out.println("Cell["+this.toString()+"] clicked");
                    }
                });

                areaCell.addEventHandler( MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                      @Override
                      public void handle(MouseEvent e) {
                        //areaCell.setEffect(cellShadow);
                        //areaCell.setBackground( Background.EMPTY );
                      }
                    });

                areaCell.addEventHandler( MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                      @Override
                      public void handle(MouseEvent e) {
                        //areaCell.setEffect(null);
                        //areaCell.setBackground( BACKGROUND_OCEAN_BLUE ); //ocean
                      }
                    });

            }
        }
    }










    void newWildParkArea() {
        int currentOceanRightRandom;
        int currentPolarLeftRandom;
        int currentPolarRightRandom;
        int currentLakeLeftRandom;
        int currentLakeRightRandom;
        WildParkAreaCell areaCell;
        Random random = new Random();

        int previousOceanRightRandom = WILD_PARK_OCEAN_AVERAGE_WIDTH - (int)(0.05*WILD_PARK_AREA_WIDTH) + random.nextInt( (int)(0.10*WILD_PARK_AREA_WIDTH) ); // initial Ocean Right Edge 

        int previousPolarLeftRandom =  (int)(0.02*WILD_PARK_AREA_WIDTH); // initial Polar Area left edge - 2% of Wild Park Width
        int previousPolarRightRandom = previousPolarLeftRandom + random.nextInt( WILD_PARK_POLAR_AREA_AVERAGE_WIDTH );

        int previousLakeLeftRandom = WILD_PARK_OCEAN_AVERAGE_WIDTH + (int)(0.20*WILD_PARK_AREA_WIDTH); // initial lake left edge - from Ocean Average Righ Edge 20% of Wild Park Width
        int previousLakeRightRandom = previousLakeLeftRandom + random.nextInt( WILD_PARK_LAKE_AVERAGE_WIDTH );

        stage.getScene().setCursor(Cursor.WAIT);

        for( int y=0; y<WILD_PARK_AREA_HEIGHT; y++ ) {
            // Ocean right limits
            currentOceanRightRandom = previousOceanRightRandom - 1 + random.nextInt(3); // -1, żeby różniło się w lewo o 1 lub w prawo o 1
            if( currentOceanRightRandom < 3 )
                currentOceanRightRandom = previousOceanRightRandom+3;
            if( currentOceanRightRandom > WILD_PARK_OCEAN_AVERAGE_WIDTH+10 )
                currentOceanRightRandom = previousOceanRightRandom-3;

            // Polar Area left and right limits
            currentPolarLeftRandom = previousPolarLeftRandom - 1 + random.nextInt(3);
            currentPolarRightRandom = previousPolarRightRandom - 1 + random.nextInt(3);
            if( currentPolarRightRandom-currentPolarLeftRandom < 3 )
                currentPolarRightRandom = previousPolarRightRandom+3;
            if( y==WILD_PARK_POLAR_AREA_HEIGHT-2 ) currentPolarRightRandom = currentPolarLeftRandom+10;
            if( y==WILD_PARK_POLAR_AREA_HEIGHT-1 ) currentPolarRightRandom = currentPolarLeftRandom+5;

            // Lake left and right limits
            currentLakeLeftRandom = previousLakeLeftRandom - 1 + random.nextInt(3);
            currentLakeRightRandom = previousLakeRightRandom - 1 + random.nextInt(3);
            if( currentLakeRightRandom-currentLakeLeftRandom < 3 )
                currentLakeRightRandom = previousLakeRightRandom+3;
            if( y==11 ) currentLakeRightRandom = currentLakeLeftRandom+5;
            if( y==12 ) currentLakeRightRandom = currentLakeLeftRandom+10;
            if( y==10+WILD_PARK_LAKE_HEIGHT-2 ) currentLakeRightRandom = currentLakeLeftRandom+10;
            if( y==10+WILD_PARK_LAKE_HEIGHT-1 ) currentLakeRightRandom = currentLakeLeftRandom+5;

            for( int x=0; x<WILD_PARK_AREA_WIDTH; x++ ) {
                areaCell = cellArray[x][y];
                areaCell.setMinSize( WILD_PARK_CELL_WIDTH, WILD_PARK_CELL_HEIGHT );
                areaCell.setMaxSize( WILD_PARK_CELL_WIDTH, WILD_PARK_CELL_HEIGHT );

//                areaCell.setBackground( Background.EMPTY );
                // cellArray[x][y].setBackground( new Background( new BackgroundFill( Paint.valueOf( "0x00FF00" ), CornerRadii.EMPTY, Insets.EMPTY ) ) );

                if( x < currentOceanRightRandom ) {
                    areaCell.setCellType( CellType.OCEAN );
                    areaCell.setBackground( BACKGROUND_OCEAN_BLUE ); //ocean
                }
                else {
                    areaCell.setCellType( CellType.GRASS );
                    areaCell.setBackground( BACKGROUND_GREEN );  //teren
                }

                if( x>currentPolarLeftRandom && x<currentPolarRightRandom && y>=0 && y<WILD_PARK_POLAR_AREA_HEIGHT ) {
                    areaCell.setCellType( CellType.POLAR_AREA );
                    areaCell.setBackground( BACKGROUND_POLAR_WHITE ); //jezioro
                }

                if( x>currentLakeLeftRandom && x<currentLakeRightRandom && y>10 && y<10+WILD_PARK_LAKE_HEIGHT ) {
                    areaCell.setCellType( CellType.LAKE );
                    areaCell.setBackground( BACKGROUND_LAKE_BLUE ); //jezioro
                }

                //areaCell.setBorder( null );

                previousOceanRightRandom = currentOceanRightRandom;
                previousPolarLeftRandom = currentPolarLeftRandom;
                previousPolarRightRandom = currentPolarRightRandom;
                previousLakeLeftRandom = currentLakeLeftRandom;
                previousLakeRightRandom = currentLakeRightRandom;
            }
        }

        populateWildPark();

        stage.getScene().setCursor(Cursor.DEFAULT);
    }












    // Fill Wild Park with animals
    void populateWildPark() {

        final int INSECT_EATING_BAT_COUNT = 30; // Count of all bats to be generated 
        final int TEST_BAT_COUNT = 30; // Count of all bats to be generated in Wild Park 

       	final int LEOPARD_COUNT=10;
        final int LION_COUNT = 10;
        final int CROCODILE_COUNT = 10;
        final int POLAR_BEAR_COUNT = 10;
        final int HORSE_COUNT = 15;
        final int GIRAFFE_COUNT = 5;
        final int EAGLE_COUNT = 30;
        // single animals - pojedyncze egzemplarze:
        for( int i=0; i<INSECT_EATING_BAT_COUNT; i++ ) {
            Animal bat = new InsectEatingBat();
        }

        // A herd/pack in a single WildParkCell- stado w jednej komórce:
        WildParkAreaCell areaCell = InsectEatingBatSpecification.selectRandomCell();
        for( int i=0; i<EAGLE_COUNT; i++ ) {
            Animal eagle = new Eagle();
        }
        for( int i=0; i<5; i++ ) {
            Animal bat = new InsectEatingBat( areaCell, false );
        }
        

        // single animals - pojedyncze egzemplarze:
        for( int i=0; i<TEST_BAT_COUNT; i++ ) {
            Animal testBat = new TestBat();
        }
        
        for( int i=0; i<HORSE_COUNT; i++ ) {
            new Horse();
        }
        
        for(int i = 0; i < GIRAFFE_COUNT; i++) {
        	new Giraffe();
        }
        
        //WildParkAreaCell areaCell = InsectEatingBatSpecification.selectRandomCell();

        /*WildParkAreaCell areaCell = new WildParkAreaCell("Cell1");
        for( int i=0; i<5; i++ ) {
			Animal bat = new InsectEatingBat( areaCell, false );
        }*/
        



        // for( int i=0; i<LION_COUNT; i++ ) {
        //     Animal lion = new Lion( new LionSpecification(), new WildParkAreaCell( CellType.LAKE ), false );
        //     addAnimal( lion );
        // }

        // for( int i=0; i<LEOPARD_COUNT; i++ ) {
        //     Animal leopard = new Leopard( new LeopardSpecification(), new WildParkAreaCell( CellType.DESERT ), false );
        //     addAnimal( leopard );
        // }

        // // for( int i=0; i<CROCODILE_COUNT; i++ ) {
        // //     Animal crocodile = new Crocodile( new CrocodileSpecification(), new WildParkAreaCell( CellType.LAKE ), false );
        // //     getAnimals().add( crocodile );
        // // }
        
        // for( int i=0; i<POLAR_BEAR_COUNT; i++ ) {
        //     Animal polarBear = new PolarBear( new PolarBearSpecification(), new WildParkAreaCell( CellType.LAKE ), false );
        //     addAnimal( polarBear ); 
        // }
    }
    









    void clearWildPark() {
        getAnimals().clear();

        // Reset Meat/Animal identifier base value 
        Meat.lastAnimalID = 0;
        wildParkTime = Duration.ZERO;
        toolBarLabel_CurrentStep.setText( String.format( "%8d", wildParkTime.toHours() ) );

        for( int y=0; y<WILD_PARK_AREA_HEIGHT; y++ ) {
            for( int x=0; x<WILD_PARK_AREA_WIDTH; x++ ) {
                cellArray[x][y].clear();
            }
        }

    }










    void saveWildPark() {
        for( Animal animal : WildParkArea.getAnimals() ) {
            //Save Animal Specification
            //Save animal.getSpecification();
            //Save AnimalState list - a history of whole life of the animal
            //Save "______________________________________________________"
        }
    }













    public static WildParkAreaCell getWildParkAreaCell( int x, int y ) {
        return cellArray[x][y];
    }













    /**
     * Generate sample Wild Park Area
     */
    private void GenerateWildParkArea() {
        World.SEED = new Random().nextLong();
        Image img = World.generate();
        ImageView imageView = new ImageView(img);
        ZoomableScrollPane zm = new ZoomableScrollPane(imageView);
        Scene sce = new Scene(zm);
        try {
            Stage s = new Stage();
            s.getIcons().add(new Image("wildpark/favicon-32x32.png"));

            s.setScene(sce);
            s.show();
        } catch( IllegalArgumentException e ) {
            System.out.println( "Error loading favicon-32x32.png. Should be in \'wildpark\' directory together with WildPark.class file." );
            // System.exit(-1);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }













    /**
     * Listeners to menu items, buttons and other controls of UI
     */
    void addUIEventListeners() {
        // Resets Wild Park life - The new terrain is randomly generated and life starts from the begining. 
        menu1_New.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                System.out.println("menu1_New clicked"); 
                //Wyświetl okno dialogowe z pytaniem, czy użytkownik chce zapisać bieżący park.
                //
                clearWildPark();
                newWildParkArea();                
            }
        });

        menu1_Open.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                System.out.println("menu1_Open clicked");
                
            }
        });

        menu1_Save.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
            	System.out.println("menu1_Save_New clicked");
            }
        });

        menu1_Exit.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                System.exit(0);

            }        
        });

        //------------------

        menu2_AnimalList.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                System.out.println("menu2_AnimalList clicked");
                // Animal List page
                ReportAnimals ra = new ReportAnimals(getAnimals(), "Animal List"); // Just as-is but working report list
                try {
                    ra.show();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }                
            }        
        });

        menu2_AnimalCountDiagram.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                System.out.println("menu2_AnimalCountDiagram clicked");
                // Animal Count Diagram page
                try {
                    diagram.show();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }        
        });

        menu2_GenerateWildParkArea.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                System.out.println("menu2_GenerateWildParkArea clicked");
                // Generate sample Wild Park Area
                GenerateWildParkArea();                
            }        

        });


        //------------------

        menu3_AnimalSettings.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                System.out.println("menu3_AnimalSettings clicked");
                // Animal settings page
                
            }        
        });

        menu3_WildParkSettings.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                System.out.println("menu3_WildParkSettings clicked");
                // Animal settings page
                
            }        
        });

        //------------------


        //------------------
        //------------------
        //------------------
        // Resets Wild Park life - The new terrain is randomly generated and life starts from the begining. 
        toolBarButton_New.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                //Wyświetl okno dialogowe z pytaniem, czy użytkownik chce zapisać bieżący park.
                //
                clearWildPark();
                newWildParkArea();                
            }
        });

        toolBarButton_Open.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                System.out.println("toolBarButton_Open clicked");
                // Opens the file explorer window to find the Wild Park Log File saved in previous sessions.
                
            }
        });

        toolBarButton_Save.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                System.out.println("toolBarButton_Save clicked");
                // Open Wild Park Save As dialog box or just save the Park if the file name is already known
                
            }
        });

        toolBarButton_Reset.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                System.out.println("toolBarButton_Reset clicked");
                // Resets Wild Park life - but does not change the map. Life starts from the begining.
                clearWildPark();
                populateWildPark();
            }
        });


        toolBarButton_Play.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                System.out.println("toolBarButton_Play clicked");
                // Let the Park performs steps automatically with the speed specified in textField_PlaySpeed text field.
                // Change the button Label to Stop and perform Time Steps until the Stop button is clicked.
                if( stopButtonClicked ) {
                    toolBarButton_Play.setText( "Stop" );
                    stopButtonClicked = false;
                    //Perform automatic steps with specified delay determined on the basis of playSpeed attribute value.
                } else {
                    toolBarButton_Play.setText( "Play" );
                    stopButtonClicked = true;    
                    //Stop automatic steps                
                }

            }
        });

        toolBarButton_Step.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                System.out.println("toolBarButton_Step clicked");
               
                // Perform the single Time step
                makeWildParkTimeStep();
                diagram.updateList(animals);
                diagram.nextStep();
            }
        });

        toolBarButton_Run.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                System.out.println("toolBarButton_Run clicked");
                // Quickly perform the given amount of Time Steps. The amount of steps is specified in textField_NumberOfStepsToRun text field.
                for(int i=0; i<numberOfStepsToRun; i++) {
                    makeWildParkTimeStep();
                    toolBarLabel_CurrentStep.setText( String.format( "%8d", wildParkTime.toHours() ) );
                }
            }
        });
        
        toolBarButton_Reports.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle( ActionEvent e ) {
                System.out.println("toolBarButton_Reports clicked");
                List<String> choices = new ArrayList<>();
                choices.add("Animal List");
                choices.add("Animal Count Diagram");
                choices.add("Generate Wild Park Area");
                ChoiceDialog<String> dialog = new ChoiceDialog<>("Animal List", choices);
                dialog.setTitle("Report Choice");
                dialog.setHeaderText("Choose Report");
                dialog.setContentText("Report:");
                try {
                    Stage stg = (Stage) dialog.getDialogPane().getScene().getWindow();
                    stg.getIcons().add(new Image("wildpark/favicon-32x32.png"));
                } catch( IllegalArgumentException ex ) {
                    System.out.println( "Error loading favicon-32x32.png. Should be in \'wildpark\' directory together with WildPark.class file." );
                    // System.exit(-1);
                }


                Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()){
                    	if (result.get() == "Animal List") {
	                    	ReportAnimals ra = new ReportAnimals(getAnimals(), "Animal List"); // Just as-is but working report list
	                    	try {
								ra.show();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
                    	}
                    	if (result.get() == "Animal Count Diagram") {
	                    	//Diagram01 ra = new Diagram01(getAnimals()); // Just as-is but working report list
	                    	try {
								diagram.show();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
                    	}
                    	if (result.get() == "Generate Wild Park Area") {
                            GenerateWildParkArea();
                    	}
                        /*System.out.println("Your choice: " + result.get());
                        Stage stage2 = new Stage();
                        TextArea textArea = new TextArea();
                        textArea.setText("Przykladowa tresc raportu");
                        Scene scene2 = new Scene(new BorderPane(textArea));
                        stage2.setScene(scene2);
                        stage2.show();*/
                        }

                    }
        });

        toolBarButton_Settings.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("toolBarButton_Settings clicked");
//                Newwindow nw =new Newwindow();
            }
        });

        button_SearchAnimal.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                System.out.println("button_SearchAnimal clicked");
                // Point the animal with given ID at the map or in the table view


                //Testowo wyświetl listę wszystkich zwierząt w parku
                for( Animal animal : getAnimals() ) {
                    System.out.printf( "%6d   %-18s\r\n", animal.getId(), animal.getSPECIES_NAME() );
                }

            }
        });


    }
 
    public static void main(String[] args) {
        launch(args);
        
    }
}
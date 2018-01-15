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

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
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










public class WildPark extends Application {
    
    public static final int WILD_PARK_AREA_WIDTH = 100;
    public static final int WILD_PARK_AREA_HEIGHT = 50;
    final int WILD_PARK_OCEAN_AVERAGE_WIDTH = (int)(0.30*WILD_PARK_AREA_WIDTH); // 30% of Park Area Width
    final int WILD_PARK_LAKE_AVERAGE_WIDTH = 35;
    final int WILD_PARK_LAKE_HEIGHT = 15;

    final int WILD_PARK_CELL_WIDTH = 54;
    final int WILD_PARK_CELL_HEIGHT = 54;

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

    /**
     * Returns the current Wild Park Time counter value
     * @return wildParkTime value - the current time counter of Wild Park
     */
    public static Duration getWildParkTime() {
        return wildParkTime;
    }

    /**
     * Make a single time step of Wild Park - add a single Wild Park Time unit defined in WILD_PARK_TIME_STEP_DURATION constnt
     */
    public static void makeWildParkTimeStep() {

        wildParkTime = wildParkTime.plus( WILD_PARK_TIME_STEP_DURATION );

        // Update Step Counter in UI
        toolBarLabel_CurrentStep.setText( String.format( "%s", wildParkTime.toHours() ) );
    }


    /**
     * The collection of all animals in Wild Park. It also contains dead animals - animals 
     * are not removed from this collection after death but are treated as Meat == Food. 
     */
    static private ArrayList<Animal> animals = new ArrayList<>();


    /**
     * Getter for animals attribute. animals array contins all animals in the park.
     * @return ArrayList of all animals in the park.
     */
    static ArrayList<Animal> getAnimals() {
        return animals;
    }







    // final Background BACKGROUND_GREEN = new Background( new BackgroundFill( Paint.valueOf( "0x00ff0088" ), new CornerRadii( 5 ), new Insets(1,1,0,0) ) );
    final Background BACKGROUND_GREEN = new Background( new BackgroundFill( Paint.valueOf( "linear-gradient(from 0px 54px to 0px 0px, #44ff55 20%, 0x44ff5555 100%)" ), new CornerRadii( 5 ), new Insets(1,1,0,0) ) );
    final Background BACKGROUND_OCEAN_BLUE = new Background( new BackgroundFill( Paint.valueOf( "linear-gradient(from 0px 54px to 0px 0px, #2299ff 20%, 0x2299ff55 100%)" ), new CornerRadii( 5 ), new Insets(1,1,0,0) ) );
    final Background BACKGROUND_LAKE_BLUE = new Background( new BackgroundFill( Paint.valueOf( "linear-gradient(from 0px 54px to 0px 0px, #33aaff 20%, 0x33aaff55 100%)" ), new CornerRadii( 5 ), new Insets(1,1,0,0) ) );

 

    // MENU
    MenuItem menu1_Exit = new MenuItem("Exit");

    // TOOLBAR
    Button toolBarButton_New = new Button("New");
    static Label toolBarLabel_CurrentStep = new Label( "0" ); 
    Button toolBarButton_Step = new Button("Step");
    Button toolBarButton_Reset = new Button("Reset");


    Stage stage;
    GridPane wildParkGrid = new GridPane();
    Button[][] cellArray = new Button[WILD_PARK_AREA_WIDTH][WILD_PARK_AREA_HEIGHT];


    final static Label label = new Label();
//    DropShadow cellShadow = new DropShadow();





    @Override
    public void start( Stage stage ) {
 //       Group root = new Group();
        this.stage = stage;

        try {
            stage.getIcons().add(new Image("file:favicon-32x32.png"));
        } catch( IllegalArgumentException e ) {
            System.out.println( "Error loading favicon-32x32.png. Should be in \'wildpark\' directory together with WildPark.class file." );
            System.exit(-1);
        }

        addUIEventListeners();

        wildParkGrid.setPadding( new Insets(10, 10, 10, 10) );
        wildParkGrid.setVgap(0);    // gap between cells
        wildParkGrid.setHgap(0);    // gap between cells
 
        Slider slider = new Slider( 0.25, 2, 0.25 );    // Slider( min, max, value )
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


        final Menu menu1 = new Menu("File");
        MenuItem menu1_New = new MenuItem("New");
        MenuItem menu1_Open = new MenuItem("Open...");
        MenuItem menu1_Save = new MenuItem("Save...");
        menu1.getItems().addAll( menu1_1, menu1_2, menu1_3, new SeparatorMenuItem(), menu1_Exit );
        final Menu menu2 = new Menu("Reports");
        MenuItem menu2_Species = new MenuItem("Species...");
        MenuItem menu2_Animals = new MenuItem("Animals...");
        MenuItem menu2_Steps = new MenuItem("Steps...");
        final Menu menu3 = new Menu("Settings");
        MenuItem menu3_AnimalSettings = new MenuItem("Animal Settings...");
        MenuItem menu3_WildParkSettings = new MenuItem("Wild Park Settings...");
        final Menu menu4 = new Menu("Help");
        MenuItem menu4_Help = new MenuItem("Help...");
        MenuItem menu4_AboutWildPark = new MenuItem("About Wild Park...");


        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll( menu1, menu2, menu3, menu4 );

        TextField textField_PlaySpeed = new TextField();
        textField_PlaySpeed.setPrefColumnCount(2);
        TextField textField_NumberOfStepsToRun = new TextField();
        textField_NumberOfStepsToRun.setPrefColumnCount(3);

        ToolBar toolBar = new ToolBar(
            toolBarButton_New,
            new Button("Open"),
            new Button("Save"),
            toolBarButton_Reset,
            new Separator( Orientation.VERTICAL ),
            new Label( "Current Step:"),
            toolBarLabel_CurrentStep,
            toolBarButton_Step,
            new Separator( Orientation.VERTICAL ),
            new Label( "Play Speed:"),
            textField_PlaySpeed,
            new Label( "steps/sec."),
            new Button("Play"),
            new Separator( Orientation.VERTICAL ),
            new Label( "Run"),
            textField_NumberOfStepsToRun,
            new Label( "steps"),
            new Button("Run"),            
            new Separator( Orientation.VERTICAL ),
            new Button("Reports"),
            new Button("Settings"),
            new Button("Help")
        );
        toolBarLabel_CurrentStep.setMinWidth(40);

        HBox statusBar = new HBox(10);  // HBox( spacing )
        int numberOfAnimals = 1234;
        Label label_NumberOfAnimals = new Label( "Number of animals: " + numberOfAnimals );
        HBox.setMargin( label_NumberOfAnimals, new Insets(6,4,4,43) );    // Insets( top, right, bottom, left )
        Label label_FindAnimal = new Label("Find animal:");
        HBox.setMargin( label_FindAnimal, new Insets(6,0,4,40) );    // Insets( top, right, bottom, left )
        TextField textField_FindAnimal = new TextField();
        HBox.setMargin( textField_FindAnimal, new Insets(2,0,2,0) );    // Insets( top, right, bottom, left )
        Button button_SearchAnimal = new Button("Search");        
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

     // ListView list = new ListView();
     // BorderPane.setAlignment(list, Pos.TOP_LEFT);
     // BorderPane.setMargin(list, new Insets(12,12,12,12));   // Insets( top, right, bottom, left )
     // borderPane.setCenter(list);

        // scrollPane.setVbarPolicy( ScrollPane.ScrollBarPolicy.ALWAYS );

        // final Label dollar = new Label("$");
        // GridPane.setConstraints(dollar, 0, 0);
        // wildParkGrid.getChildren().add(dollar);
        
        // final TextField sum = new TextField() {
        //     @Override
        //     public void replaceText(int start, int end, String text) {
        //         if (!text.matches("[a-z, A-Z]")) {
        //             super.replaceText(start, end, text);                     
        //         }
        //         label.setText("Enter a numeric value");
        //     }
 
        //     @Override
        //     public void replaceSelection(String text) {
        //         if (!text.matches("[a-z, A-Z]")) {
        //             super.replaceSelection(text);
        //         }
        //     }
        // };
 
        // sum.setPromptText("Enter the total");
        // sum.setPrefColumnCount(10);
        // GridPane.setConstraints(sum, 1, 0);
        // wildParkGrid.getChildren().add(sum);
        
        // Button submit = new Button("Submit");
        // GridPane.setConstraints(submit, 2, 0);
        // wildParkGrid.getChildren().add(submit);
        
        // submit.setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent e) {
        //         label.setText(null);
        //     }
        // });
        // GridPane.setConstraints(label, 0, 1);
        // GridPane.setColumnSpan(label, 3);
        // wildParkGrid.getChildren().add(label);
        
        initializeWildParkArea();
        newWildParkArea();

        stage.setWidth(1000);
        stage.setHeight(600);
        stage.show();
    }










    void initializeWildParkArea() {
        //Button button;
        for( int y=0; y<WILD_PARK_AREA_HEIGHT; y++ ) {
            for( int x=0; x<WILD_PARK_AREA_WIDTH; x++ ) {
                final Button button = new Button( x + " : " + y );
                cellArray[x][y] = button;
                button.setMinSize( WILD_PARK_CELL_WIDTH, WILD_PARK_CELL_HEIGHT );
                button.setMaxSize( WILD_PARK_CELL_WIDTH, WILD_PARK_CELL_HEIGHT );

                button.setBackground( Background.EMPTY );
                // cellArray[x][y].setBackground( new Background( new BackgroundFill( Paint.valueOf( "0x00FF00" ), CornerRadii.EMPTY, Insets.EMPTY ) ) );
                //button.setBorder( null );
                GridPane.setConstraints( button, x, y );
                wildParkGrid.getChildren().add( button );

                button.setOnAction( new EventHandler<ActionEvent>() {
                    @Override 
                    public void handle( ActionEvent e ) {
                        System.out.println("Cell["+this.toString()+"] clicked");
                    }
                });

                button.addEventHandler( MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                      @Override
                      public void handle(MouseEvent e) {
                        //button.setEffect(cellShadow);
                        //button.setBackground( Background.EMPTY );
                      }
                    });

                button.addEventHandler( MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                      @Override
                      public void handle(MouseEvent e) {
                        //button.setEffect(null);
                        //button.setBackground( BACKGROUND_OCEAN_BLUE ); //ocean
                      }
                    });

            }
        }
    }










    void newWildParkArea() {
        int currentOceanRightRandom;
        int currentLakeLeftRandom;
        int currentLakeRightRandom;
        Button button;
        Random random = new Random();

        int previousOceanRightRandom = WILD_PARK_OCEAN_AVERAGE_WIDTH - (int)(0.05*WILD_PARK_AREA_WIDTH) + random.nextInt( (int)(0.10*WILD_PARK_AREA_WIDTH) ); // initial Ocean Right Edge 
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
                button = cellArray[x][y];
                button.setMinSize( WILD_PARK_CELL_WIDTH, WILD_PARK_CELL_HEIGHT );
                button.setMaxSize( WILD_PARK_CELL_WIDTH, WILD_PARK_CELL_HEIGHT );

//                button.setBackground( Background.EMPTY );
                // cellArray[x][y].setBackground( new Background( new BackgroundFill( Paint.valueOf( "0x00FF00" ), CornerRadii.EMPTY, Insets.EMPTY ) ) );

                if( x < currentOceanRightRandom ) 
                    button.setBackground( BACKGROUND_OCEAN_BLUE ); //ocean
                else 
                    button.setBackground( BACKGROUND_GREEN );  //teren

                if( x>currentLakeLeftRandom && x<currentLakeRightRandom && y>10 && y<10+WILD_PARK_LAKE_HEIGHT ) button.setBackground( BACKGROUND_LAKE_BLUE ); //jezioro

                //button.setBorder( null );

                previousOceanRightRandom = currentOceanRightRandom;
                previousLakeLeftRandom = currentLakeLeftRandom;
                previousLakeRightRandom = currentLakeRightRandom;
            }
        }

        populateWildPark();

        stage.getScene().setCursor(Cursor.DEFAULT);
    }












    // Fill Wild Park with animals
    void populateWildPark() {
        final int INSECT_EATING_BAT_COUNT = 10; // Count of all bats in Wild Park 
        //...

        for( int i=0; i<INSECT_EATING_BAT_COUNT; i++ ) {
            Animal bat = new InsectEatingBat( new InsectEatingBatSpecification(), new WildParkAreaCell( CellType.LAKE ), false );
        }

    }











    void clearWildParkArea() {
        for( int y=0; y<WILD_PARK_AREA_HEIGHT; y++ ) {
            for( int x=0; x<WILD_PARK_AREA_WIDTH; x++ ) {
                cellArray[x][y].setBackground( Background.EMPTY );
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









    /**
     * Listeners to menu items, buttons and other controls of UI
     */
    void addUIEventListeners() {
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

        toolBarButton_New.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                System.out.println("toolBarButton_New clicked");
//                clearWildParkArea();
                newWildParkArea();
            }
        });

        toolBarButton_Step.setOnAction( new EventHandler<ActionEvent>() {
            @Override 
            public void handle( ActionEvent e ) {
                System.out.println("toolBarButton_Step clicked");
                makeWildParkTimeStep();
            }
        });


    }








 
    public static void main(String[] args) {
        launch(args);
    }
}
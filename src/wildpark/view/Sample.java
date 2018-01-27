package wildpark.view;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Sample {
	private ImageView bgImageView = new ImageView();
	private Image image;
	private Scene scene;
	private DoubleProperty magnification = new SimpleDoubleProperty();
	private DoubleProperty GLASS_SIZE = new SimpleDoubleProperty();
	private DoubleProperty GLASS_CENTER = new SimpleDoubleProperty();
	private DoubleProperty centerX = new SimpleDoubleProperty();
	private DoubleProperty centerY = new SimpleDoubleProperty();
	private DoubleProperty factor = new SimpleDoubleProperty();
	private DoubleProperty viewportCenterX = new SimpleDoubleProperty();
	private DoubleProperty viewportCenterY = new SimpleDoubleProperty();
	private DoubleProperty viewportSize = new SimpleDoubleProperty();
	private ImageView magGlass = new ImageView();
	private Group glassGroup = new Group();
	private Text desc = new Text();

	public void start(Stage primaryStage) {
		viewportCenterX.bind(centerX.multiply(factor));
		viewportCenterY.bind(centerY.multiply(factor));
		//viewportSize.bind(GLASS_SIZE.multiply(factor).multiply(magnification));
		viewportSize.bind(GLASS_SIZE.multiply(magnification).multiply(factor));

		//image = new Image("file:map.png");

		Pane root = new Pane();
		scene = new Scene(root, 900, 700);

		setupBgImageView();
		setupFactor();
		setupGLASS_SIZE();
		magnification.setValue(1);

		setupMagGlass();
		setupGlassGroup();
		//setupDesc();
		bgImageView.requestFocus();
		primaryStage.setTitle("Magnifying Glass");
		primaryStage.setWidth(image.getWidth() / 2);
		primaryStage.setHeight(image.getHeight() / 2);

		root.getChildren().addAll(bgImageView, glassGroup, desc);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void adjustMagnification(final double amount) {
		// no bindings is needed here - it's one time operation
		//double newValue = magnification.get() + amount / 4;
		double newValue = magnification.get() * amount;
		if (newValue < .2) {
			newValue = .2;
		} else if (newValue > 1) {
			newValue = 1;
		}
		magnification.setValue(newValue);
	}

	private void setupGLASS_SIZE() {
		DoubleBinding db = new DoubleBinding() {
			{
				super.bind(bgImageView.boundsInLocalProperty());
			}

			@Override
			protected double computeValue() {
				return bgImageView.boundsInLocalProperty().get().getWidth() / 4;
			}
		};
		GLASS_SIZE.bind(db);
		GLASS_CENTER.bind(GLASS_SIZE.divide(2));
	}

	private void setupFactor() {
		DoubleBinding db = new DoubleBinding() {
			{
				super.bind(image.heightProperty(), bgImageView.boundsInLocalProperty());
			}

			@Override
			protected double computeValue() {
				return image.heightProperty().get() / bgImageView.boundsInLocalProperty().get().getHeight();
			}
		};
		factor.bind(db);
	}

	private void setupBgImageView() {
		bgImageView.setImage(image);
		bgImageView.fitWidthProperty().bind(scene.widthProperty());
		bgImageView.fitHeightProperty().bind(scene.heightProperty());

		// comparing double requires precision
		bgImageView.cacheProperty().bind(factor.isNotEqualTo(1.0, 0.05));

		bgImageView.setSmooth(true);
		bgImageView.setPreserveRatio(true);
		bgImageView.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				centerX.setValue(me.getX());
				centerY.setValue(me.getY());
			}
		});
//		bgImageView.setOnKeyPressed(new EventHandler<KeyEvent>() {
//			@Override
//			public void handle(KeyEvent ke) {
//				if (ke.getCode() == KeyCode.EQUALS || ke.getCode() == KeyCode.PLUS) {
//					adjustMagnification(1.0);
//				} else if (ke.getCode() == KeyCode.MINUS) {
//					adjustMagnification(-1.0);
//				}
//			}
//		});

		bgImageView.setOnScroll(new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent me) {
				adjustMagnification(Math.exp(-me.getDeltaY() * 0.005));
			}
		});

//		bgImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent me) {
//				if (me.getButton() != MouseButton.PRIMARY) {
//					magGlass.setSmooth(magGlass.isSmooth());
//				}
//				bgImageView.requestFocus();
//			}
//		});

	}

	private void setupMagGlass() {
		magGlass.setImage(image);
		magGlass.setPreserveRatio(true);
		magGlass.fitWidthProperty().bind(GLASS_SIZE);
		magGlass.fitHeightProperty().bind(GLASS_SIZE);
		magGlass.setSmooth(true);
		ObjectBinding<Rectangle2D> ob = new ObjectBinding<Rectangle2D>() {
			{
				super.bind(viewportCenterX, viewportSize, viewportCenterY);
			}

			@Override
			protected Rectangle2D computeValue() {
				return new Rectangle2D(viewportCenterX.get() - viewportSize.get() / 2,
						(viewportCenterY.get() - viewportSize.get() / 2), viewportSize.get(), viewportSize.get());
			}
		};
		magGlass.viewportProperty().bind(ob);
		Circle clip = new Circle();
		clip.centerXProperty().bind(GLASS_CENTER);
		clip.centerYProperty().bind(GLASS_CENTER);
		clip.radiusProperty().bind(GLASS_CENTER.subtract(5));
		magGlass.setClip(clip);
	}

	private void setupGlassGroup() {

		glassGroup.translateXProperty().bind(centerX.subtract(GLASS_CENTER));
		glassGroup.translateYProperty().bind(centerY.subtract(GLASS_CENTER));

		Text text = new Text();
		//text.xProperty().bind(GLASS_CENTER.multiply(1.5));
		//text.yProperty().bind(GLASS_SIZE);
		//text.textProperty().bind(Bindings.concat("x", magnification, " magnification"));
		Circle circle = new Circle();
		circle.centerXProperty().bind(GLASS_CENTER);
		circle.centerYProperty().bind(GLASS_CENTER);
		circle.radiusProperty().bind(GLASS_CENTER.subtract(3));
		circle.setStroke(Color.GREEN);
		circle.setStrokeWidth(4);
		circle.setFill(null);
		
		glassGroup.getChildren().addAll(magGlass, text, circle);
		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetY(3);
		dropShadow.setOffsetX(3);
		glassGroup.setEffect(dropShadow);
		glassGroup.setMouseTransparent(true);
	}

	/*private void setupDesc() {
		desc.setX(10);
		desc.setY(15);
		if (!bgImageView.isFocused()) {
			desc.setText("Click image to focus");
		} else {
			desc.setText("Use the +/- or mouse wheel to zoom. Right-click to make the magnification "
					+ "{if (magGlass.smooth)   less smooth. else more smooth.}");
		}
		desc.setFont(new Font(12));
	}*/

	public void setImage(Node node) {
		final Bounds bounds = node.getLayoutBounds();
		int scale = 1;
		final WritableImage img = new WritableImage(
        (int) Math.round(bounds.getWidth() * scale),
        (int) Math.round(bounds.getHeight() * scale));
		final javafx.scene.SnapshotParameters spa = new javafx.scene.SnapshotParameters();
        spa.setTransform(javafx.scene.transform.Transform.scale(scale, scale));
        

		image = (Image)node.snapshot(null, null);
	}
}
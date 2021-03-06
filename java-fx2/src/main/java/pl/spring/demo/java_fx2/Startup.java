package pl.spring.demo.java_fx2;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Startup extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	/**
	 * @see {@link javafx.application.Application#start(Stage)}
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		/*
		 * Set the default locale based on the '--lang' startup argument.
		 */
		String langCode = getParameters().getNamed().get("lang");
		if (langCode != null && !langCode.isEmpty()) {
			Locale.setDefault(Locale.forLanguageTag(langCode));
		}

		primaryStage.setTitle("Image-JavaFX");

		/*
		 * Load screen from FXML file with specific language bundle (derived
		 * from default locale).
		 */
		Parent root = FXMLLoader.load(getClass().getResource("/pl/spring/demo/java_fx2/view/img-search.fxml"), //
				ResourceBundle.getBundle("pl/spring/demo/java_fx2/bundle/base"));

		Scene scene = new Scene(root);

		/*
		 * Set the style sheet(s) for application.
		 */
		scene.getStylesheets()
				.add(getClass().getResource("/pl/spring/demo/java_fx2/css/standard.css").toExternalForm());
		// scene.getStylesheets().add(getClass().getResource("/pl/spring/demo/java_fx/css/alternative.css").toExternalForm());

		primaryStage.setScene(scene);

		primaryStage.show();
	}
}

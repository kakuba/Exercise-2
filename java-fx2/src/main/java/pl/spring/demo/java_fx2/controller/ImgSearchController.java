package pl.spring.demo.java_fx2.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImgSearchController {
	private static final Logger LOG = Logger.getLogger(ImgSearchController.class);

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button openButton;

	@FXML
	private Button nextButton;

	@FXML
	private Button prevButton;

	@FXML
	private Button startButton;

	@FXML
	private ImageView imageView;

	@FXML
	private ScrollPane scrollPane;

	@FXML
	private ListView<String> pictureList;

	private List<File> fileList;

	private int pictureNumber;

	private int lastPictureNumber;

	@FXML
	private void initialize() {
		LOG.debug("initialize(): nameField = ");

	}

	@FXML
	private void openButtonAction(ActionEvent event) throws MalformedURLException {
		LOG.debug("'Open' button clicked");

		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("View Pictures");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("GIF", "*.gif"),
				new FileChooser.ExtensionFilter("BMP", "*.bmp"), new FileChooser.ExtensionFilter("PNG", "*.png"));
		fileList = fileChooser.showOpenMultipleDialog(new Stage());

		if (fileList != null) {
			setFirstImage();
		}

	}

	private void setItemsForListView() {
		ObservableList<String> items = FXCollections.observableArrayList();
		for (File file : fileList) {
			items.add(file.getName().toString());
		}
		pictureList.setItems(items);
	}

	private void setFirstImage() throws MalformedURLException {
		setItemsForListView();

		pictureNumber = 0;
		lastPictureNumber = fileList.size() - 1;
		File file = fileList.get(pictureNumber);

		String imagepath = file.toURI().toURL().toString();
		System.out.println("file:" + imagepath);
		Image image = new Image(imagepath);
		imageView.setImage(image);
	}

	@FXML
	private void nextButtonAction(ActionEvent event) throws MalformedURLException {
		if ((lastPictureNumber - 1) >= pictureNumber) {
			pictureNumber += 1;
		} else {
			pictureNumber = 0;
		}

		File file = fileList.get(pictureNumber);

		String imagepath = file.toURI().toURL().toString();
		System.out.println("file:" + imagepath);
		Image image = new Image(imagepath);
		imageView.setImage(image);
	}

	@FXML
	private void prevButtonAction(ActionEvent event) throws MalformedURLException {
		if (0 != pictureNumber) {
			pictureNumber -= 1;
		} else {
			pictureNumber = lastPictureNumber;
		}
		File file = fileList.get(pictureNumber);

		String imagepath = file.toURI().toURL().toString();
		System.out.println("file:" + imagepath);
		Image image = new Image(imagepath);
		imageView.setImage(image);
	}

	@FXML
	private void startButtonAction(ActionEvent event) throws MalformedURLException {
		for (File file : fileList) {
			String imagepath = file.toURI().toURL().toString();
			System.out.println("file:" + imagepath);
			Image image = new Image(imagepath);
			imageView.setImage(image);
			try {

				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("Exception caught");
			}
		}

	}

}

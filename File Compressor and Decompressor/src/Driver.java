import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Driver extends Application {
	final Font font1 = Font.font("Vernada", FontWeight.BOLD, 13);
	final Font font = Font.font("Vernada", FontWeight.NORMAL, 12);
	final Font font3 = Font.font("Vernada", FontWeight.BOLD, 11);
	static int headLength = 0;
	static ArrayList<NodeWithCode> nodesWithCodes;

	// MainPage
	public void start(Stage primaryStage) throws Exception {
		Group pane = new Group();

		Rectangle r2 = new Rectangle(20, 20, 460, 560);
		r2.setStroke(Color.rgb(57, 39, 20));
		r2.setFill(null);
		r2.setStrokeWidth(2);

		// Title
		String n = " PROJECT No.2";
		Label topText = new Label(n);
		topText.setLayoutX(150);
		topText.setLayoutY(70);
		topText.setFont(new Font("Segoe UI Black", 24));
		topText.setStyle("-fx-text-fill: #ffffff");

		// Label and Button for input
		Label label = new Label("Please choose to either compress the file or to decompress it : ");
		label.setLayoutX(50);
		label.setLayoutY(144);
		label.setFont(new Font("Segoe UI Black", 12));
		label.setStyle("-fx-text-fill: #ffffff");

		// line
		Line line1 = new Line();
		line1.setStartX(50.0);
		line1.setStartY(185);
		line1.setEndX(450.0);
		line1.setEndY(185.0);

		Button Compression = new Button("  compression   ");
		Compression.setFont(font);
		Compression.setLayoutX(200);
		Compression.setLayoutY(212);
		Compression.setStyle("-fx-text-fill: #263f40");

		// line2
		Line line2 = new Line();
		line2.setStartX(50.0);
		line2.setStartY(262);
		line2.setEndX(450.0);
		line2.setEndY(262.0);

		Button DeCompression = new Button("Decompression");
		DeCompression.setFont(font);
		DeCompression.setLayoutX(200);
		DeCompression.setLayoutY(285);
		DeCompression.setStyle("-fx-text-fill: #263f40");

		// line2
		Line line3 = new Line();
		line3.setStartX(50.0);
		line3.setStartY(340);
		line3.setEndX(450.0);
		line3.setEndY(340.0);

		ImageView iv1 = new ImageView();

		// Image1
		Image img1 = new Image("file:1.png");
		iv1.setImage(img1);
		iv1.setFitHeight(150);
		iv1.setFitWidth(50);
		iv1.setLayoutX(225);
		iv1.setLayoutY(360);

		// Close Button
		Button exitButton = new Button();
		exitButton.setText("cancel");
		exitButton.setLayoutX(215);
		exitButton.setLayoutY(530);
		exitButton.setMinHeight(20);
		exitButton.setMinWidth(70);
		exitButton.setFont(font1);
		exitButton.setStyle("-fx-text-fill: #263f40");
		exitButton.setOnAction(e -> Platform.exit());

		pane.getChildren().add(topText);
		pane.getChildren().add(label);
		pane.getChildren().add(line1);
		pane.getChildren().add(Compression);
		pane.getChildren().add(line2);
		pane.getChildren().add(DeCompression);
		pane.getChildren().add(line3);
		pane.getChildren().add(iv1);
		pane.getChildren().add(exitButton);

		Compression.setOnAction(e -> {
			try {
				CompressionStage(primaryStage);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		DeCompression.setOnAction(e -> {
			try {
				DecompressionStage(primaryStage);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		Image img = new Image("file:icon.png");
		Scene scene = new Scene(pane, 500, 600, Color.rgb(153, 51, 51));
		primaryStage.setTitle("  Huffman Coding");
		primaryStage.getIcons().add(img);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// CompressionPage "chooseFile button , styling & get the path of file that was
	// chosen"
	public void CompressionStage(Stage CompressionStage) throws IOException {
		try {
			Group pane = new Group();
			FileChooser fileChooser = new FileChooser();

			Rectangle r3 = new Rectangle(20, 20, 460, 560);
			r3.setStroke(Color.rgb(27, 75, 52));
			r3.setFill(null);
			r3.setStrokeWidth(2);

			// Title
			String n = " Compression ";
			Label topText = new Label(n);
			topText.setLayoutX(160);
			topText.setLayoutY(70);
			topText.setFont(new Font("Segoe UI Black", 24));
			topText.setStyle("-fx-text-fill: #263f40");

			// Label and Button for input
			Label label = new Label("Please press to compress your file");
			label.setLayoutX(145);
			label.setLayoutY(125);
			label.setFont(new Font("Segoe UI Black", 12));
			label.setStyle("-fx-text-fill: #263f40");

			// line
			Line line1 = new Line();
			line1.setStartX(50.0);
			line1.setStartY(170.0);
			line1.setEndX(450.0);
			line1.setEndY(170.0);

			Button chooseFile = new Button("Choose a file");
			chooseFile.setFont(font);
			chooseFile.setLayoutX(210);
			chooseFile.setLayoutY(197);
			chooseFile.setStyle("-fx-text-fill: #263f40");

			// line2
			Line line2 = new Line();
			line2.setStartX(50.0);
			line2.setStartY(245);
			line2.setEndX(450.0);
			line2.setEndY(245);

			Label label2 = new Label("there is no any file selected !");
			label2.setLayoutX(50);
			label2.setLayoutY(340);
			label2.setFont(new Font("Segoe UI Black", 12));
			label2.setStyle("-fx-text-fill: #263f40");

			// line2
			Line line3 = new Line();
			line3.setStartX(50.0);
			line3.setStartY(325);
			line3.setEndX(450.0);
			line3.setEndY(325);

			Rectangle r1 = new Rectangle(40, 400, 180, 110);
			r1.setStroke(Color.rgb(27, 75, 52));
			r1.setFill(null);
			r1.setStrokeWidth(2);

			Rectangle r2 = new Rectangle(270, 400, 190, 110);
			r2.setStroke(Color.rgb(27, 75, 52));
			r2.setFill(null);
			r2.setStrokeWidth(2);

			// Close Button
			Button backButton = new Button();
			backButton.setText("Back");
			backButton.setLayoutX(215);
			backButton.setLayoutY(530);
			backButton.setMinHeight(20);
			backButton.setMinWidth(70);
			backButton.setFont(font1);
			backButton.setStyle("-fx-text-fill: #263f40");
			backButton.setOnAction(e -> {
				try {
					start(CompressionStage);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});

			pane.getChildren().add(topText);
			pane.getChildren().add(label);
			pane.getChildren().add(line1);
			pane.getChildren().add(chooseFile);
			pane.getChildren().add(line2);
			pane.getChildren().add(line3);
			pane.getChildren().add(label2);
			pane.getChildren().add(backButton);
			pane.getChildren().add(r1);
			pane.getChildren().add(r2);
			pane.getChildren().add(r3);

			Image img = new Image("file:icon.png");
			Scene scene = new Scene(pane, 500, 600, Color.rgb(57, 172, 115));
			CompressionStage.setTitle("  Huffman Coding");
			CompressionStage.getIcons().add(img);
			CompressionStage.setResizable(false);
			CompressionStage.setScene(scene);
			CompressionStage.show();

			// create an Event Handler
			EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
							new ExtensionFilter("PNG Files", "*.png"), new ExtensionFilter("JPG Files", "*.jpg"),
							new ExtensionFilter("Word Files", "*.docx"), new ExtensionFilter("Java Files", "*.java"),
							new ExtensionFilter("Web Files", "*.html", "*.css", "*.js", "*.php"),
							new ExtensionFilter("All Files", "*.*"));

					// get the file selected
					File f1 = fileChooser.showOpenDialog(CompressionStage);
					if (f1 != null) {
						String string = "The path of your file is : \n " + f1.getAbsolutePath();
						label2.setText(string);
						label2.setLayoutX(50);
						label2.setLayoutY(340);
						label2.setFont(new Font("Segoe UI Black", 12));
						label2.setStyle("-fx-text-fill: #263f40");

						try {
							StageOfCompression(CompressionStage, f1, pane);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			};
			chooseFile.setOnAction(event);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// also , CompressionPage "CompressionButton"
	public void StageOfCompression(Stage CompressionStage, File f, Group pane) throws IOException {
		TextArea ta = new TextArea();
		ta.setText("");
		ta.setEditable(false);
		ta.setMinHeight(500);
		ta.setMinWidth(500);

		Button CompressionButton = new Button("Save a compression file");
		CompressionButton.setFont(font);
		CompressionButton.setLayoutX(90);
		CompressionButton.setLayoutY(270);
		CompressionButton.setStyle("-fx-text-fill: #263f40");

		Label label = new Label("");
		label.setLayoutX(290);
		label.setLayoutY(415);
		label.setFont(font3);
		label.setStyle("-fx-text-fill: #263f40");

		Label labelx = new Label("");
		labelx.setLayoutX(57);
		labelx.setLayoutY(415);
		labelx.setFont(font3);
		labelx.setStyle("-fx-text-fill: #263f40");

		byte[] bytes = new byte[(int) f.length()];

		// read file using FileInputStream
		try (FileInputStream FIS = new FileInputStream(f)) {
			FIS.read(bytes);
		}

		// example : file.pdf
		String extentionOfFile = f.getName().split("\\.")[1];

		// 2^8 = 256
		int frequency[] = new int[256];
		ArrayList<Node> arraylistOfNodes = new ArrayList<Node>();

		// Range of bytes from -128 to 127 (original file)ٌ
		for (int i = 0; i < bytes.length; i++)
			frequency[bytes[i] + 128]++;

		// "i" is a char represented in bytes.
		for (int i = 0; i < frequency.length; i++)
			if (frequency[i] > 0)
				arraylistOfNodes.add(new Node(frequency[i], (byte) i, true));

		int fileLength = 0;
		int numOfChars = 0;

		fileLength = bytes.length;
		numOfChars = arraylistOfNodes.size();

		labelx.setText("Source File Information" + "\n\n" + "File Length : " + fileLength + "\n"
				+ "# of Distinguished char : " + numOfChars);

		int fileHeadLengths = 0;
		int actualDataLengths = 0;
		double compressionRates = 0;

		label.setText("Coded File Information" + "\n\n" + "File Head Length : " + fileHeadLengths + "\n"
				+ "Actual Data Length : " + actualDataLengths + "\n" + "Compression Rate : " + compressionRates + "%");

		pane.getChildren().add(CompressionButton);
		pane.getChildren().add(label);
		pane.getChildren().add(labelx);

		// Save the Compression file.
		CompressionButton.setOnAction(e -> {
			int fileHeadLength = 0;
			int actualDataLength = 0;
			double compressionRate = 0;

			FileChooser fileChooser = new FileChooser();
			// Set extension filter
			FileChooser.ExtensionFilter huffExtension = new FileChooser.ExtensionFilter("JabrHuffman files (*.huff)",
					"*.huff");
			fileChooser.getExtensionFilters().add(huffExtension);
			File file = fileChooser.showSaveDialog(CompressionStage);

			if (file != null) {
				// print the data.
				FileOutputStream FOS = null;
				try {
					FOS = new FileOutputStream(file);

					// to print bit by bit
					BinaryStdOut BSO = new BinaryStdOut(FOS);
					BitOutputStream BOS = new BitOutputStream(FOS);
					MinHeap minheap = new MinHeap(arraylistOfNodes.size());

					// add all nodes to MinHeap
					for (int i = 0; i < arraylistOfNodes.size(); i++) {
						minheap.add(arraylistOfNodes.get(i));
					}

					while (minheap.size > 1) {
						Node z = new Node(0);
						Node x = minheap.poll();
						Node y = minheap.poll();
						z.addtoLeft(x);
						z.addtoRight(y);
						minheap.add(z);
					}

					// when we have a 1 node
					Node root = minheap.peek();

					getHeaderLength(root);

					fileHeadLength = headLength;

					// print in the file
					BOS.writeH(new StringBuilder(extentionOfFile + ":" + headLength + ":"));
					BOS.writeH(new StringBuilder(bytes.length + ":"));

					BuildHeader(root, BSO);
					nodesWithCodes = addCode(root);

					// save data in s ( save the code of specific byte in str and all bytes)
					StringBuilder str = new StringBuilder("");

					// bytes = file
					for (int i = 0; i < bytes.length; i++)
						for (int j = 0; j < nodesWithCodes.size(); j++)
							// every byte with its value in bits
							if ((bytes[i]) == nodesWithCodes.get(j).value) {
								// add the code of specific byte to str.
								str.append(nodesWithCodes.get(j).code);
								break;
								// we had put and encrypted all bytes to str.
							}

					// to print all bytes in file .
					for (int i = 0; i < str.length(); i++) {
						if (str.charAt(i) == '1')
							BSO.write(true);
						else if (str.charAt(i) == '0')
							BSO.write(false);
					}
					BSO.close();
					actualDataLength = (int) file.length();
					compressionRate = ((double) (bytes.length - actualDataLength) / bytes.length) * 100;

				} catch (FileNotFoundException e1) {
					System.out.println("File not found" + e1);
				} catch (IOException ioe) {
					System.out.println("Exception while writing file " + ioe);
				} finally {
					// close the streams using close method
					try {
						if (FOS != null) {
							FOS.close();
						}
					} catch (IOException ioe) {
						System.out.println("Error while closing stream: " + ioe);
					}
				}
			}

			String cr = compressionRate + "";
			if (cr.length() > 7)
				cr = cr.substring(0, 7) + "%";
			label.setText("Coded File Information" + "\n\n" + "File Head Length : " + fileHeadLength + "\n"
					+ "Actual Data Length : " + actualDataLength + "\n" + "Compression Rate : " + cr);

			Button Huffmans = new Button("Huffman Codes");

			Huffmans.setFont(font);
			Huffmans.setLayoutX(300);
			Huffmans.setLayoutY(270);
			Huffmans.setStyle("-fx-text-fill: #263f40");
			pane.getChildren().add(Huffmans);

			Stage newstage = new Stage();
			Button Back = new Button("Back");
			Back.setText("Back");
			Back.setLayoutX(215);
			Back.setLayoutY(530);
			Back.setMinHeight(20);
			Back.setMinWidth(70);
			Back.setFont(font1);
			Back.setStyle("-fx-text-fill: #263f40");

			Back.setOnAction(e6 -> {
				newstage.close();
			});

			Group h = new Group();
			h.getChildren().addAll(ta);
			h.getChildren().addAll(Back);

			Scene scene = new Scene(h, 500, 600, Color.DARKGRAY);
			newstage.setScene(scene);

			Huffmans.setOnAction(e4 -> {
				newstage.show();

			});

			// to print characters with huffman codes ...
			ta.setText(ta.getText() + "" + "\n" + " Byte  ---------->  Code  \n");

			for (int i = 0; i < nodesWithCodes.size(); i++) {
				ta.setText("  " + ta.getText() + "\n" + "  " + (char) nodesWithCodes.get(i).value + "  --------->  "
						+ nodesWithCodes.get(i).code);
			}
			;
		});
	}

	// DecompressionPage "chooseFile button , styling & get the path of file that
	// was chosen"
	public void DecompressionStage(Stage DeCompressionStage) throws IOException {
		try {
			Group pane = new Group();
			FileChooser fileChooser = new FileChooser();

			Rectangle r2 = new Rectangle(20, 20, 460, 560);
			r2.setStroke(Color.rgb(0, 77, 77));
			r2.setFill(null);
			r2.setStrokeWidth(2);

			// Title
			String n = " Decompression ";
			Label topText = new Label(n);
			topText.setLayoutX(150);
			topText.setLayoutY(70);
			topText.setFont(new Font("Segoe UI Black", 24));
			topText.setStyle("-fx-text-fill: #ffffff");

			// Label and Button for input
			Label label = new Label("Please press to decompress your file");
			label.setLayoutX(145);
			label.setLayoutY(147);
			label.setFont(new Font("Segoe UI Black", 12));
			label.setStyle("-fx-text-fill: #ffffff");

			// line
			Line line1 = new Line();
			line1.setStartX(50.0);
			line1.setStartY(185);
			line1.setEndX(450.0);
			line1.setEndY(185.0);

			Button chooseFile = new Button("Choose a file");
			chooseFile.setFont(font);
			chooseFile.setLayoutX(210);
			chooseFile.setLayoutY(212);
			chooseFile.setStyle("-fx-text-fill: #263f40");

			// line2
			Line line2 = new Line();
			line2.setStartX(50.0);
			line2.setStartY(262);
			line2.setEndX(450.0);
			line2.setEndY(262);

			Label label2 = new Label("there is no any file selected !");
			label2.setLayoutX(50);
			label2.setLayoutY(355);
			label2.setFont(new Font("Segoe UI Black", 12));
			label2.setStyle("-fx-text-fill: #ffffff");

			// line2
			Line line3 = new Line();
			line3.setStartX(50.0);
			line3.setStartY(340);
			line3.setEndX(450.0);
			line3.setEndY(340);

			// Close Button
			Button backButton = new Button();
			backButton.setText("Back");
			backButton.setLayoutX(215);
			backButton.setLayoutY(530);
			backButton.setMinHeight(20);
			backButton.setMinWidth(70);
			backButton.setFont(font1);
			backButton.setStyle("-fx-text-fill: #263f40");
			backButton.setOnAction(e -> {
				try {
					start(DeCompressionStage);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});

			pane.getChildren().add(topText);
			pane.getChildren().add(label);
			pane.getChildren().add(line1);
			pane.getChildren().add(chooseFile);
			pane.getChildren().add(line2);
			pane.getChildren().add(line3);
			pane.getChildren().add(label2);
			pane.getChildren().add(backButton);
			pane.getChildren().add(r2);

			Image img = new Image("file:icon.png");
			Scene scene = new Scene(pane, 500, 600, Color.rgb(0, 128, 128));
			DeCompressionStage.setTitle("Huffman Coding");
			DeCompressionStage.getIcons().add(img);
			DeCompressionStage.setScene(scene);
			DeCompressionStage.show();

			// create an Event Handler
			EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Huffman Files", "*.huff"));
					File f1 = fileChooser.showOpenDialog(DeCompressionStage);
					if (f1 != null) {
						String string = "The path of your file is : \n " + f1.getAbsolutePath();
						label2.setText(string);
						label2.setLayoutX(50);
						label2.setLayoutY(355);
						label2.setFont(new Font("Segoe UI Black", 12));
						label2.setStyle("-fx-text-fill: #ffffff");

						try {
							StageOfDecompression(DeCompressionStage, f1, pane);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			};
			chooseFile.setOnAction(event);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// also , DecompressionPage " DecompressionButton"
	public void StageOfDecompression(Stage DeCompressionStage, File f, Group pane) throws IOException {
		Button DeCompressionButton = new Button("Save a Decompression file");
		DeCompressionButton.setFont(font);
		DeCompressionButton.setLayoutX(180);
		DeCompressionButton.setLayoutY(285);
		DeCompressionButton.setStyle("-fx-text-fill: #263f40");

		pane.getChildren().add(DeCompressionButton);

		// to read
		BinaryStdIn BSI = new BinaryStdIn(f);

		// to recover the file we need (extension, header length, File length
		DeCompressionButton.setOnAction(e -> {
			String typeOfFile = "";
			String headerLength = "";
			String dataLength = "";

			while (true) {
				char c = (char) BSI.readByte();
				if (c == ':')
					break;
				else
					typeOfFile += c;
			}

			while (true) {
				char c = (char) BSI.readByte();
				if (c == ':')
					break;
				else
					headerLength += c;
			}

			while (true) {
				char c = (char) BSI.readByte();
				if (c == ':')
					break;
				else
					dataLength += c;
			}
			FileChooser fileChooser = new FileChooser();
			// Set extension filter
			FileChooser.ExtensionFilter extension = new FileChooser.ExtensionFilter(
					typeOfFile + " files (*." + typeOfFile + ")", "*." + typeOfFile);
			fileChooser.getExtensionFilters().add(extension);
			// Show save file dialog
			File file = fileChooser.showSaveDialog(DeCompressionStage);
			// to save the data to file similar with original file.
			if (file != null) {
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(file);

					BinaryStdOut BSO = new BinaryStdOut(fos);

					// convert from string to long and int.
					Long datalength = Long.parseLong(dataLength);

					int headerlength = Integer.parseInt(headerLength);

					StringBuilder header = new StringBuilder("");

					// read bits
					for (int i = 0; i < headerlength; i++) {
						boolean b = BSI.readBoolean();
						if (b)
							header.append("1");
						else
							header.append("0");
					}

					// String that remove from front ( peek ) and modify on original String
					// example : 011010011000010
					Str str = new Str(header.toString());

					// root of Huffman tree ( build tree by fill it with 0 or 1 ) , ( recover the
					// root ).
					Node root = readTree(str);

					// read bit by bit to fill the bytes.
					for (int i = 0; i < datalength; i++) {
						// when we get the leaf we print it then we'll back to root.
						Node x = root;
						while (!x.isLeaf()) {
							boolean bit = BSI.readBoolean();
							if (bit)
								x = x.right;
							else
								x = x.left;
						}
						// when we get the leaf.
						BSO.write(x.value);
					}
					BSO.close();

				} catch (FileNotFoundException e1) {
					System.out.println("File not found" + e1);
				} catch (IOException ioe) {
					System.out.println("Exception while writing file " + ioe);
				} finally {
					// close the streams using close method
					try {
						if (fos != null) {
							fos.close();
						}
					} catch (IOException ioe) {
						System.out.println("Error while closing stream: " + ioe);
					}
				}
			}
		});
	}

	// in addCode below
	private static void addCode(Node root, String s, ArrayList<NodeWithCode> arraylist) {
		if (!(root.isLeaf())) {
			String leftString = s + "0";
			String rightString = s + "1";
			addCode(root.left, leftString, arraylist);
			addCode(root.right, rightString, arraylist);
		} else {
			// return the leaf with represented in bits (code) and value
			arraylist.add(new NodeWithCode(s.toString(), root.value));
		}
	}

	// StageOfCompression
	public static ArrayList<NodeWithCode> addCode(Node root) {
		ArrayList<NodeWithCode> array = new ArrayList<NodeWithCode>();
		String s = ("");
		addCode(root, s, array);
		return array;
	}

	// StageOfCompression
	public static void BuildHeader(Node header, BinaryStdOut BSO) throws IOException {
		if (header.leaf) {
			BSO.write(true);
			// get the value in string ( 00011100 as string )
			String s = getByteBinaryString(header.value);
			// print 8-bits
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '1')
					BSO.write(true);
				else
					BSO.write(false);
			}
			return;
		}
		BSO.write(false);
		BuildHeader(header.left, BSO);
		BuildHeader(header.right, BSO);
	}

	// StageOfCompression
	public static void getHeaderLength(Node root) {
		if (root == null)
			return;
		if (root.leaf) {
			headLength += 9;
			return;
		} else {
			headLength++;
			getHeaderLength(root.left);
			getHeaderLength(root.right);
		}
	}

	// used in addHeader , to get the value in string ( 00011100 as string ) , shift
	// right
	public static String getByteBinaryString(byte b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 7; i >= 0; --i) {
			sb.append(b >>> i & 1);
		}
		return sb.toString();
	}

	// StageOfDecompression ( Build tree from header )
	private static Node readTree(Str str) {
		// if bit = 1 , then recover 8 bits ( char)
		String s = str.peek(1);
		boolean n = s.equals("");
		if (n)
			return null;
		boolean isLeaf = s.equals("1");

		if (isLeaf) {
			byte b = (byte) ((Integer.parseInt(str.peek(8), 2)) - 128);
			return new Node(b, -1, null, null, true);
		}
		// if bit = 0 , then build tree ( left , right ) , any modification on Str , it
		// effect and it will modify on new Node.
		return new Node(readTree(str), readTree(str));
	}

	public static void main(String[] args) throws Exception {
		launch(args);
	}
}

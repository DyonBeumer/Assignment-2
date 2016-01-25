package UI;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Domein.Keyword;
import Domein.Pattern;
import Domein.PatternFacade;
import Domein.Purpose.purpose;
import Domein.Scope.scope;

public class MyFrame extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	//we hadden liever onze variabelen zoveel mogelijk lokaal gedefinieerd 
	//maar vanwege een bug in dyon's Eclipse konden we dat niet bereiken.
	
	
	//Laad de opgeslagen patterns
	SaveInterface saveInterface = new XML();
	LoadInterface loadInterface = new XML();
	ArrayList<Pattern> patternlist = new ArrayList<Pattern>();
	ArrayList<String> keyWordStrings = new ArrayList<String>();
	ArrayList<String> consequences = new ArrayList<String>();
	ArrayList<String> patternNamen = new ArrayList<String>();
	
	ChoiceBox<purpose> purposelijst = new ChoiceBox<purpose>();
	ChoiceBox<scope> scopelijst = new ChoiceBox();
	Button probleemAanmakenBttn = new Button("Maak een probleem aan");
	Button patternAanmakenBttn = new Button("Maak nieuw pattern aan");
	Button kiesPatternBttn = new Button("Bekijk pattern");
	Button annulerenBttn1 = new Button("Annuleren");
	Button annulerenBttn2 = new Button("Annuleren");
	Button opslaanBttn = new Button("Opslaan");
	Button wijzigenPatternBttn = new Button("Wijzigen pattern");
	TextField keywordField = new TextField();
	TextField consequenceField = new TextField();
	File selectedFile;
	TextField naamField = new TextField();
	Label lab2 = new Label("Keywords: (Gescheiden door spaties)");
	Label lab3 = new Label("Consequences: (Gescheiden door spaties)");
	Label lab4 = new Label("Scope:");
	Label lab6 = new Label("Purpose:");
	Label lab5 = new Label("Pattern naam:");
	
	//ChoiceBox cb = new ChoiceBox();
	ArrayList<CheckBox> chblist = new ArrayList<CheckBox>();
	
	Stage nieuwProbleem = new Stage();
	Stage nieuwPattern = new Stage();

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Patterns");
		purposelijst.getItems().setAll(purpose.values());
		scopelijst.getItems().setAll(scope.values());
		if(loadInterface.Load() != null){
			patternlist = (ArrayList<Pattern>)loadInterface.Load();
		}

		probleemAanmakenBttn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ChoiceBox cb = new ChoiceBox();
				cb.setPrefWidth(150);
				cb.getItems().clear();
				VBox left = new VBox();
				FlowPane checkBoxes = new FlowPane();
				checkBoxes.setPadding(new Insets(10,10,10,10));
				checkBoxes.getChildren().clear();
				chblist.clear();
				Label Melding = new Label("Alle gevonden patterns voor uw probleem");
				Label title = new Label("Kies keywords voor uw probleem!");
				left.getChildren().addAll(title,checkBoxes);
				
				for (PatternFacade p : patternlist) {
				
					for (int i = 0; i < p.getWords().size(); i++) {
						CheckBox chb = new CheckBox(p.getWords().get(i));
						//for each pattern make a checkbox for each unique keyword
						if (chblist.size() == 0) {
							chblist.add(chb);
						} 
						else {
							boolean ziterin = false;
							for (CheckBox cb1 : chblist) {
								if (cb1.getText().equals(p.getWords().get(i))) {
									ziterin = true;

								}
							}
							if (!ziterin) {
								chblist.add(chb);
							}
						}
					}
				}

				for (int i = 0; i < chblist.size(); i++) {
					checkBoxes.getChildren().add(chblist.get(i));
					CheckBox hulp = chblist.get(i);
					//for all checkboxes  
					chblist.get(i).setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							for (PatternFacade p : patternlist) {

								if (p.getWords().contains(hulp.getText())) {
									if (hulp.isSelected()) {
										if (patternNamen.size() == 0) {
											patternNamen.add(p.getNaam());
										}
										
										if (!patternNamen.contains(p.getNaam())) {
											patternNamen.add(p.getNaam());
										}
									}

									if (!hulp.isSelected()
											&& p.getWords().contains(
													hulp.getText())) {
										patternNamen.remove(p.getNaam());
										for (int i = 0; i < chblist.size(); i++) {
											CheckBox hulp1 = chblist.get(i);
											if (hulp1.isSelected()
													&& p.getWords().contains(
															hulp1.getText())
													&& !patternNamen.contains(p
															.getNaam())) {
												patternNamen.add(p.getNaam());
											}
										}

									}

								}
							}

							cb.setItems(FXCollections.observableArrayList(patternNamen));
						}
					});

				}
				VBox right = new VBox();
				right.setSpacing(10);
				right.setStyle("-fx-background-color:#336699;");
				right.setPadding(new Insets(20,30,30,30));
				right.getChildren().addAll(Melding, cb, kiesPatternBttn, annulerenBttn1);
				HBox root = new HBox();
				root.getChildren().addAll(left,right);
				nieuwProbleem.setScene(new Scene(root));
				nieuwProbleem.show();
				annulerenBttn1.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						nieuwProbleem.close();
					}
				});
				kiesPatternBttn.setOnAction(new EventHandler<ActionEvent>() {
				     @Override
				     
				     public void handle(ActionEvent event) {
				    	 if(cb.getValue() != null){
				      BorderPane bekijkpattern = new BorderPane();
				      Label lab5 = new Label();
				      bekijkpattern.setLeft(lab5);
				      Label title = new Label("Informatie over het pattern en het diagram");
				      title.setFont(new Font("Cambria", 25));
				      title.setAlignment(Pos.CENTER);
				      bekijkpattern.setStyle("-fx-background-color:#336699;");
				      bekijkpattern.setTop(title);
				      lab5.setAlignment(Pos.CENTER_LEFT);
				      lab5.setPadding(new Insets(180,50,50,50));
				      lab5.setFont(new Font("Cambria", 12));
				      lab5.setOnMouseEntered(new EventHandler<MouseEvent>() {
				    	    @Override public void handle(MouseEvent e) {
				    	        lab5.setScaleX(1.3);
				    	        lab5.setScaleY(1.5);
				    	    
				    	    }
				    	});

				    	lab5.setOnMouseExited(new EventHandler<MouseEvent>() {
				    	    @Override public void handle(MouseEvent e) {
				    	        lab5.setScaleX(1);
				    	        lab5.setScaleY(1);
				    	        
				    	    }
				    	});
				      bekijkpattern.setBottom(annulerenBttn1);
				      Scene scene = new Scene(bekijkpattern,800,500);
				      nieuwProbleem.setScene(scene);
				      nieuwProbleem.show();
				         String s = (String) cb.getValue();
				         PatternFacade realpattern = null;
				         for (PatternFacade p : patternlist){
				          if(p.getNaam().equals(s)){
				           realpattern = p;
				         }
				         
				         }
				            lab5.setText(realpattern.patternInfo());
				            Image test = new Image("file:/" + realpattern.getFilepath());
				            ImageView iv = new ImageView();
				            iv.setImage(test);
				            bekijkpattern.setRight(iv);
				            iv.setFitHeight(300);
				            iv.setFitWidth(300);
				          
				            
				          }
				    	 
				    	 else{Melding.setText("Kies pattern!");}
				     }
				     
				      });
				      annulerenBttn1.setOnAction(new EventHandler<ActionEvent>() {
				       @Override
				       public void handle(ActionEvent event) {
				        cb.getItems().clear();
				        nieuwProbleem.close();
				       }
				      });
			}
		});
		patternAanmakenBttn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				naamField.clear();
				keywordField.clear();
				consequenceField.clear();
				scopelijst.setValue(null);
				purposelijst.setValue(null);
				VBox left = new VBox();
				VBox right = new VBox();
				HBox both = new HBox();
				BorderPane plaatje = new BorderPane();
				Label title = new Label("Gekozen Plaatje:");
				title.setAlignment(Pos.TOP_CENTER);
				title.setFont(new Font("Cambria", 25));
				BorderPane bp = new BorderPane();
				plaatje.setTop(title);
				plaatje.setStyle("-fx-background-color:#336699;");
				plaatje.setMinWidth(200);
				left.setPadding(new Insets(30,30,30,30));
				left.setSpacing(20);
				left.setStyle("-fx-background-color:#336699;");
				right.setSpacing(10);
				right.setPadding(new Insets(30,30,30,30));
				both.getChildren().addAll(left,right,plaatje);
				Button kiesPlaatje = new Button("Kies plaatje");
				left.getChildren().addAll(lab5,lab2,lab3,lab4,lab6,new Label("Klik hier"),annulerenBttn2);
				right.getChildren().addAll(naamField,keywordField,consequenceField,scopelijst, purposelijst,kiesPlaatje);
				bp.setBottom(opslaanBttn);
				opslaanBttn.setPrefWidth(689);
				bp.setCenter(both);
				Scene scene = new Scene(bp);
				nieuwPattern.setScene(scene);
				nieuwPattern.setResizable(false);
				nieuwPattern.show();
				
				kiesPlaatje.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						FileChooser fileChooser = new FileChooser();
						fileChooser.setTitle("Open Resource File");
						Stage fcs = new Stage();
						
						selectedFile = fileChooser.showOpenDialog(fcs);
						if (selectedFile != null){
						 Image test = new Image("file:/" + selectedFile.toString());
						
				            ImageView iv = new ImageView();
				            iv.setImage(test);
				            plaatje.setCenter(iv);
				            iv.setFitHeight(200);
				            iv.setFitWidth(200);
						}
					}
				});
				
				
				annulerenBttn2.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						nieuwPattern.close();
					}
				});

				opslaanBttn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						boolean alGemaakt = false;
						for(PatternFacade p : patternlist){
							if(p.getNaam().equals(naamField.getText())){
								alGemaakt = true;
							}
						}
						if(alGemaakt == false && !naamField.equals("") && !keywordField.equals("") && !consequenceField.equals("") && purposelijst.getValue() != null && scopelijst.getValue() != null && selectedFile != null){
						//adding keywords by CSV's
						Scanner s = new Scanner(keywordField.getText()).useDelimiter("\\s* \\s*");
						while (s.hasNext()) {
							String temp = s.next();
							if (!keyWordStrings.contains(temp)) {
								keyWordStrings.add(temp.trim());
							}
							if (!s.hasNext()) {
								break;
							}
						}
						s.close();
						
						//adding pros by CSV's
						Scanner s1 = new Scanner(consequenceField.getText()).useDelimiter("\\s* \\s*");
						while (s1.hasNext()) {
							String temp = s1.next();
							if (!consequenceField.getText().equals("")) {
								consequences.add(temp.trim());
							}
							if (!s1.hasNext()) {
								break;
							}
						}
						s1.close();
					
						Pattern p = new Pattern();
						p.setKeywords(keyWordStrings);
						p.setNaam(naamField.getText());
						p.setMyscope(scopelijst.getValue());
						p.setMypurp(purposelijst.getValue());
						p.setPro(consequences);
						p.setPatternDiagram(selectedFile);
						patternlist.add(p);
						saveInterface.Save(patternlist);
						keyWordStrings.clear();
						consequences.clear();
						
						FlowPane fp2 = new FlowPane();
						fp2.setAlignment(Pos.CENTER);
						fp2.getChildren().addAll(lab5, naamField, lab2, keywordField,
								lab3, consequenceField, annulerenBttn2,
								opslaanBttn);
						Scene scene = new Scene(fp2, 200, 110);
						
						nieuwPattern.close();
					}}
				});
			}
		});
		
		
		wijzigenPatternBttn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage wijzigPattern = new Stage();
				naamField.setText("");
				keywordField.setText("");
				consequenceField.setText("");
				scopelijst.setValue(null);
				purposelijst.setValue(null);
				selectedFile = null;
				Button kiesPlaatje = new Button("Kies plaatje");
				ChoiceBox cbpatterns = new ChoiceBox();
				ImageView iv = new ImageView();
				BorderPane bp = new BorderPane();
				HBox both = new HBox();
				VBox right = new VBox();
				right.setSpacing(11);
				right.setPadding(new Insets(30,30,30,30));
				VBox left = new VBox();
				left.setPadding(new Insets(30,30,30,30));
				left.setStyle("-fx-background-color:#336699;");
				left.setSpacing(20);
				BorderPane plaatje = new BorderPane();
				Label title = new Label("Gekozen Plaatje:");
				title.setAlignment(Pos.TOP_CENTER);
				title.setFont(new Font("Cambria", 25));
				Button verwijderen = new Button("Verwijderen");
				bp.setCenter(both);
				both.getChildren().addAll(left,right,plaatje);
				left.getChildren().addAll(new Label("Selecteer Pattern"),lab5,lab2,lab3,lab4,lab6,new Label("Klik hier"),annulerenBttn2);
				right.getChildren().addAll(cbpatterns,naamField, keywordField, consequenceField,scopelijst,purposelijst,kiesPlaatje,verwijderen);
				bp.setBottom(opslaanBttn);
				opslaanBttn.setPrefWidth(690);
				opslaanBttn.setAlignment(Pos.BOTTOM_CENTER);
				bp.setPadding(new Insets(20,20,20,20));
				plaatje.setTop(title);
				plaatje.setStyle("-fx-background-color:#336699;");
				plaatje.setCenter(iv);
				iv.setFitHeight(200);
				iv.setFitWidth(200);
			//	fp3.getChildren().addAll(cbpatterns, lab, lab5, naamField, lab2, keywordField,
			//			lab3, consequenceField, lab4, scopelijst, lab6, purposelijst,annulerenBttn2, opslaanBttn,kiesPlaatje,iv,verwijderen);
				Scene scene2 = new Scene(bp);
				wijzigPattern.setScene(scene2);
				wijzigPattern.setResizable(false);
				wijzigPattern.show();
				
				
				cbpatterns.setItems(FXCollections.observableArrayList(patternlist));
				annulerenBttn2.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						wijzigPattern.close();
					}
				});
				cbpatterns.getSelectionModel().selectedIndexProperty()
						.addListener(new ChangeListener<Number>() {
							
							PatternFacade p = new Pattern();

							@Override
							public void changed(ObservableValue ov,
									Number value, Number new_value) {
						
								if (cbpatterns.getItems().get((int) new_value) != null) {

									for (PatternFacade realpattern : patternlist) {
										if (realpattern.equals(cbpatterns
												.getItems()
												.get((int) new_value))) {
											p = realpattern;

										}
									}
									Image test = new Image("file:/" + p.getFilepath());
						            iv.setImage(test);
									purposelijst.setValue(p.getMypurp());
									scopelijst.setValue(p.getMyscope());
									naamField.setText(p.getNaam());
									selectedFile = p.getPatternDiagramAsFile();
									String alleKeywords = "";
									for (Keyword k : p.getKeywords()) {
										alleKeywords += k.getKeyword() + " ";
									}
									keywordField.setText(alleKeywords);
									String allePros = "";
									for (String Pro : p.getPros()) {
										allePros += Pro + " ";
									}
									consequenceField.setText(allePros);
									verwijderen.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent event) {
											patternlist.remove(p);
											wijzigPattern.close();
											saveInterface.Save(patternlist);
										}
										});
									kiesPlaatje.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent event) {
											FileChooser fileChooser = new FileChooser();
											fileChooser.setTitle("Open Resource File");
											Stage fcs = new Stage();
											selectedFile = fileChooser.showOpenDialog(fcs);
											System.out.println(selectedFile);
											if(selectedFile != null){
											 Image test = new Image("file:/" + selectedFile.toString());
									            iv.setImage(test);
											}
									        
										}
									});
									opslaanBttn
											.setOnAction(new EventHandler<ActionEvent>() {
												@Override
												public void handle(
														ActionEvent event) {
											
													Scanner s = new Scanner(keywordField
															.getText())
															.useDelimiter("\\s* \\s*");
													while (s.hasNext()) {
														String temp = s.next();
														if (!keyWordStrings
																.contains(temp)) {
															keyWordStrings.add(temp
																	.trim());
														}
														if (!s.hasNext()) {
															break;
														}
													}
													s.close();
													Scanner s1 = new Scanner(
															consequenceField.getText())
															.useDelimiter("\\s* \\s*");
													while (s1.hasNext()) {
														String temp = s1.next();
														if (!consequenceField.getText()
																.equals("")) {
															consequences.add(temp.trim());
														}
														if (!s1.hasNext()) {
															break;
														}
													}
													s1.close();
												

													p.setKeywords(keyWordStrings);
													p.setNaam(naamField.getText());
													p.setPro(consequences);
													p.setMypurp(purposelijst.getValue());
													p.setMyscope(scopelijst.getValue());
													p.setPatternDiagram(selectedFile);
													saveInterface.Save(patternlist);
													keyWordStrings.clear();
													consequences.clear();
													

													wijzigPattern.close();
												
												}
											});
								}
							}
						});
				}
		});

		VBox root = new VBox();
		root.setPadding(new Insets(30,30,30,30));
		root.setSpacing(10);
		root.setStyle("-fx-background-color:#336699;");
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(probleemAanmakenBttn, patternAanmakenBttn,
				wijzigenPatternBttn);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}
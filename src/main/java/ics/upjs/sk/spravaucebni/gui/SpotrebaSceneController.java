package ics.upjs.sk.spravaucebni.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.time.LocalDate;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;

public class SpotrebaSceneController {
    
    
    @FXML
    private Spinner<Integer> rokSpinner;

    @FXML
    private Spinner<Integer> mesiacSpinner;
    
    @FXML
    private TableView<JednaSpotrebaFxModel> spotrebyTableView;

    @FXML
    private Button vykresliGrafButton;

    @FXML
    private Label priemernaSpotrebaLabel;

    @FXML
    private Label celkovaSpotrebaLabel;

    @FXML
    private Label celkovaCenaLabel;

    private SpotrebaFxModel model;
    private Long ucebnaId;
    
    public SpotrebaSceneController(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
        model = new SpotrebaFxModel(ucebnaId);
    }
    
    @FXML
    void initialize() {
        int rok = LocalDate.now().getYear();
        int mesiac = LocalDate.now().getMonthValue();
        SpinnerValueFactory.IntegerSpinnerValueFactory sprinnerValueFactory =
        new SpinnerValueFactory.IntegerSpinnerValueFactory(2015, rok, model.getRok());
        rokSpinner.setValueFactory(sprinnerValueFactory);
        rokSpinner.getValueFactory().setValue(rok);
        
        rokSpinner.valueProperty().addListener(((observable, oldValue, newValue) -> {
            model.setRok(newValue);
            nastavLabely();
        }));
        
        sprinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, model.getMesiac());
        mesiacSpinner.setValueFactory(sprinnerValueFactory);
        mesiacSpinner.getValueFactory().setValue(mesiac);
        
        mesiacSpinner.valueProperty().addListener(((observable, oldValue, newValue) -> {
            model.setMesiac(newValue);
            nastavLabely();
        }));
        
        spotrebyTableView.getColumns().clear();
        
        TableColumn<JednaSpotrebaFxModel, String> datumCol = new TableColumn<>("Dátum");
        datumCol.setCellValueFactory(new PropertyValueFactory<>("datum"));
        datumCol.setMinWidth(100);
        spotrebyTableView.getColumns().add(datumCol);
        
        TableColumn<JednaSpotrebaFxModel, String> hodnotaCol = new TableColumn<>("Spotreba (kWh)");
        hodnotaCol.setCellValueFactory(new PropertyValueFactory<>("hodnota"));
        hodnotaCol.setMinWidth(140);
        spotrebyTableView.getColumns().add(hodnotaCol);
        
        TableColumn<JednaSpotrebaFxModel, String> cenaCol = new TableColumn<>("Cena (0.15 €/kWh)");
        cenaCol.setCellValueFactory(new PropertyValueFactory<>("cena"));
        cenaCol.setMinWidth(150);
        spotrebyTableView.getColumns().add(cenaCol);
        
        spotrebyTableView.setItems(model.getSpotreby());
        
        nastavLabely();
        
        vykresliGrafButton.setOnAction(eh -> {
            vytvorPrislusnyGraf("Hodnoty", "Test Hodnot", "ahoj", "svet", true);
            vytvorPrislusnyGraf("Ceny", "Test Cien", "ahoj", "svet", false);
        });
    }
    
    private void nastavLabely() {
        celkovaSpotrebaLabel.setText("Celková spotreba v tomto mesiaci je " + model.getCelkovaSpotreba());
        celkovaCenaLabel.setText("Celková cena v tomto mesiaci je " + model.getCelkovaCena());
        priemernaSpotrebaLabel.setText("Priemerná spotreba v tomto mesiaci je " + model.getPriemernaSpotreba());
    }
    
   
    private void vytvorPrislusnyGraf (String stageTitle, String nazovGrafu, String xOs, String yOs, boolean spotrebaControl) {
        SwingNode swingNode = new SwingNode();

        createSwingContent(swingNode,nazovGrafu,xOs,yOs,spotrebaControl);

        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);

        Stage stage = new Stage();
        
        stage.setTitle(stageTitle);
        stage.setScene(new Scene(pane, 450, 450));
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        
        if(spotrebaControl){
            stage.setX((width/2)- (width/3));
            stage.setY((height/2)- (height/3));
        }else{
            stage.setX((width/2)+ (width/12));
            stage.setY((height/2)- (height/3));
        }
        
        stage.show();
        }
    
    private void createSwingContent(SwingNode swingNode, String nazovGrafu, String xOs, String yOs, boolean spotrebaControl) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFreeChart lineChart = ChartFactory.createLineChart(
                nazovGrafu,
                xOs,
                yOs,
                model.vytvorDataset(spotrebaControl),
                PlotOrientation.VERTICAL,
                true,true,false);
                
                
                
                ChartPanel chartPanel = new ChartPanel( lineChart );
                chartPanel.setPreferredSize( new java.awt.Dimension( 450 , 450 ) );
                
                CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
		plot.setBackgroundPaint(Color.blue);
		plot.setRangeGridlinePaint(Color.black);
                
                LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		renderer.setDefaultFillPaint(Color.white);
		renderer.setDrawOutlines(true);
		renderer.setUseFillPaint(true);
		renderer.setDefaultShapesFilled(true);
                renderer.setDefaultShapesVisible(true);
                
                swingNode.setContent(chartPanel);
            }
        });
    }
}

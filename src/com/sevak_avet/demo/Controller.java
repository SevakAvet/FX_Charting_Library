package com.sevak_avet.demo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import com.sevak_avet.XYChart.XYChartSeries;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

public class Controller implements Initializable {
	@FXML LineChart<Double, Double> lineChart;
	@FXML TextField formulaField;
	@FXML Button btnDrawChart;
	@FXML Button btnClear;
	
	@FXML Label deltaValue;
	@FXML Slider deltaSlider;
	
	@FXML Label minValue;
	@FXML Slider minSlider;
	
	@FXML Label maxValue;
	@FXML Slider maxSlider;
	
	private static double MIN = -20;
	private static double MAX = -1 * MIN;
	private static double DELTA = 0.01;
	
	private static XYChartSeries chartData = new XYChartSeries();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) { 
		deltaValue.setText(String.format("%.2f", DELTA));
		minValue.setText(String.format("%.2f", MIN));
		maxValue.setText(String.format("%.2f", MAX));
		
		deltaSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				DELTA = newValue.doubleValue();
				System.out.printf("DELTA: %.2f\n", DELTA);
				deltaValue.setText(String.format("%.2f", DELTA));
			}
		});
		
		minSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				MIN = newValue.doubleValue();
				System.out.printf("MIN: %.2f\n", MIN);
				minValue.setText(String.format("%.2f", MIN));
				
				if(MIN >= MAX) {
					MIN = Math.max(MAX - 0.01, -200);
					minSlider.setValue(MIN);
				}
			}
		});
		
		maxSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				MAX = newValue.doubleValue();
				System.out.printf("MAX: %.2f\n", MAX);
				maxValue.setText(String.format("%.2f", MAX));
				
				if(MAX <= MIN) {
					MAX = Math.min(MIN + 0.01, 200);
					maxSlider.setValue(MAX);
				}
			}
		});
		
		btnDrawChart.setOnAction(e -> {
			draw();
		});
		
		formulaField.setOnKeyReleased(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				draw();
			}
		});
		
		btnClear.setOnAction(e -> {
			lineChart.getData().clear();
		});
	}
	
	private void draw() {		
		try {										
			Calculable calc = new ExpressionBuilder(formulaField.getText()).
					withCustomFunctions(MyFunctions.getAll()).
					withVariable("x", 0).
					withVariable("E", Math.E).
					withVariable("PI", Math.PI).
					build();
			
			chartData.addSeries(MIN, MAX, DELTA, i -> {
				calc.setVariable("x", i);
				return calc.calculate();
			});
			
			lineChart.setData(chartData.getData());
		} catch (Exception e) {
			formulaField.setText(formulaField.getText() + " Incorrect formula!");
			e.printStackTrace();
		}
	}
}
package com.sevak_avet.XYChart;

import java.util.List;
import java.util.function.Function;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.chart.XYChart;

public class XYChartSeries {
	private ObservableList<XYChart.Series<Double, Double>> data = FXCollections.observableArrayList();

	public void addSeries(double start, double end, double delta, Function<Double, Double> f, String name) {
		XYChart.Series<Double, Double> series = new XYChart.Series<>();
		series.setName(name);

		for (double i = start; i <= end; i = i + delta) {
			series.getData().add(new XYChart.Data<Double, Double>(i, f.apply(i)));
			System.out.println(i + " " + f.apply(i));
		}

		data.add(series);
	}

	public void addSeries(double start, double end, double delta, Function<Double, Double> f) {
		addSeries(start, end, delta, f, "");
	}

	public void addSeries(List<Double> x, Function<Double, Double> f, String name) {
		XYChart.Series<Double, Double> series = new XYChart.Series<>();
		series.setName(name);

		for (double i : x) {
			series.getData().add(
					new XYChart.Data<Double, Double>(i, f.apply(i)));
		}

		data.add(series);
	}

	public void addSeries(List<Double> x, Function<Double, Double> f) {
		addSeries(x, f, "");
	}

	public void addSeries(List<Double> x, List<Double> y, String name) {
		XYChart.Series<Double, Double> series = new XYChart.Series<>();
		series.setName(name);

		for (int i = 0; i < x.size(); ++i) {
			series.getData().add(
					new XYChart.Data<Double, Double>(x.get(i), y.get(i)));
		}

		data.add(series);
	}

	public void addSeries(List<Double> x, List<Double> y) {
		addSeries(x, y, "");
	}

	public ObservableList<XYChart.Series<Double, Double>> getData() {
		return data;
	}
}

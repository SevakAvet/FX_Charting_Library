package com.sevak_avet.demo;

import java.util.Arrays;
import java.util.List;
import de.congrace.exp4j.CustomFunction;
import de.congrace.exp4j.InvalidCustomFunctionException;

public class MyFunctions {
	public static CustomFunction LOG = null;
	
	public static CustomFunction TAN = null;
	public static CustomFunction ARCTAN = null;
	public static CustomFunction ARCSIN = null;
	public static CustomFunction ARCCOS = null;
	
	public static CustomFunction SEC = null;
	public static CustomFunction COSEC = null;
	
	public static CustomFunction GD = null;
	public static CustomFunction SH = null;
	public static CustomFunction CH = null;
	public static CustomFunction TH = null;
	public static CustomFunction SCH = null;
	public static CustomFunction CSCH = null;
	public static CustomFunction CTH = null;
	
	public static List<CustomFunction> functions;
	
	static {
		try {
			TAN = new CustomFunction("tg") {
				@Override
				public double applyFunction(double... arg0) {
					return Math.tan(arg0[0]);
				}
			};
			
			ARCTAN = new CustomFunction("arctg") {
				@Override
				public double applyFunction(double... arg0) {
					return Math.atan(arg0[0]);
				}
			};
			
			ARCSIN = new CustomFunction("arcsin") {
				@Override
				public double applyFunction(double... arg0) {
					if(Math.abs(arg0[0]) <= 1) {
						return Math.asin(arg0[0]);
					}
					return 0;
				}
			};

			ARCCOS = new CustomFunction("arccos") {
				@Override
				public double applyFunction(double... arg0) {
					if(Math.abs(arg0[0]) <= 1) {
						return Math.acos(arg0[0]);
					}
					return 0;
				}
			};
			
			LOG = new CustomFunction("log", 2) {
				@Override
				public double applyFunction(double... arg0) {
					if(arg0[0] > 0 && arg0[1] > 0 && arg0[0] != 1) {
						return Math.log(arg0[1]) / Math.log(arg0[0]);
					}
					
					return 0;
				}
			};
			
			SEC = new CustomFunction("sec") {
				@Override
				public double applyFunction(double... arg0) {
					return sec(arg0[0]);
				}
			};
			
			COSEC = new CustomFunction("cosec") {
				@Override
				public double applyFunction(double... arg0) {
					return cosec(arg0[0]);
				}
			};
			
			GD = new CustomFunction("gd") {
				@Override
				public double applyFunction(double... arg0) {
					return gd(arg0[0]);
				}
			};
			
			SH = new CustomFunction("sh") {
				@Override
				public double applyFunction(double... arg0) {
					return Math.tan(gd(arg0[0]));
				}
			};
			
			CH = new CustomFunction("ch") {
				@Override
				public double applyFunction(double... arg0) {
					return sec(gd(arg0[0]));
				}
			};
			
			TH = new CustomFunction("th") {
				@Override
				public double applyFunction(double... arg0) {
					return Math.sin(gd(arg0[0]));
				}
			};
			
			SCH = new CustomFunction("sch") {				
				@Override
				public double applyFunction(double... arg0) {
					return Math.cos(gd(arg0[0]));
				}
			};
			
			CSCH = new CustomFunction("csch") {
				@Override
				public double applyFunction(double... arg0) {
					return ctg(gd(arg0[0]));
				}
			};
			
			CTH = new CustomFunction("cth") {
				
				@Override
				public double applyFunction(double... arg0) {
					return cosec(gd(arg0[0]));
				}
			};
			
			functions = Arrays.asList(TAN, ARCTAN, ARCSIN, ARCCOS, LOG, SEC, COSEC, GD, SH, CH, TH, SCH, CSCH, CTH);
		} catch (InvalidCustomFunctionException e) {
			e.printStackTrace();
		}
	}
	
	private static double ctg(double x) {
		return Math.cos(x) / Math.sin(x);
	}
	
	private static double gd(double x) {
		return 2.0 * Math.atan(Math.pow(Math.E, x)) - Math.PI / 2.0;
	}
	
	private static double sec(double x) {
		return Math.cos(x) != 0 ? 1 / Math.cos(x) : 0;
	}
	
	private static double cosec(double x) {
		return !(x <= 0.1 && x >= -0.1) ? 1 / Math.sin(x) : 0.001;
	}
	
	public static List<CustomFunction> getAll() {
		return functions;
	}
}
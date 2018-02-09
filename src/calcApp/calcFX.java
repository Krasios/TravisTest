package calcApp;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class calcFX extends Application {
	Label lbl;
	Button but0,but1,but2,but3,but4,but5,but6,but7,but8,but9;
	ArrayList<Button> numBut = new ArrayList<Button>();
	Button butAdd,butSub,butMult,butDiv,butEq,butDot,butClr,butBack;
	ArrayList<Button> opBut = new ArrayList<Button>();
	ArrayList<String> operators = new ArrayList<String>();
	ArrayList<Float> operands = new ArrayList<Float>();
	String display = "";
	String curFloat = "";
	Button prevBut;
	float total = 0;
	public static void main(String[] args) {
		launch(args);
	}
	public void start(Stage stage) throws Exception {
		stage.setTitle("Calculator");
		lbl = new Label(display);
		GridPane grid = new GridPane();
		//HBox root = new HBox();
		Scene scene = new Scene(grid,90,142);
		but1 = new Button("1");
		but1.setMaxWidth(Double.MAX_VALUE);
		numBut.add(but1);
		but1.setOnAction(clicked);
		but2 = new Button("2");
		but2.setMaxWidth(Double.MAX_VALUE);
		numBut.add(but2);
		but2.setOnAction(clicked);
		but3 = new Button("3");
		but3.setMaxWidth(Double.MAX_VALUE);
		numBut.add(but3);
		but3.setOnAction(clicked);
		but4 = new Button("4");
		but4.setMaxWidth(Double.MAX_VALUE);
		numBut.add(but4);
		but4.setOnAction(clicked);
		but5 = new Button("5");
		but5.setMaxWidth(Double.MAX_VALUE);
		numBut.add(but5);
		but5.setOnAction(clicked);
		but6 = new Button("6");
		but6.setMaxWidth(Double.MAX_VALUE);
		numBut.add(but6);
		but6.setOnAction(clicked);
		but7 = new Button("7");
		but7.setMaxWidth(Double.MAX_VALUE);
		numBut.add(but7);
		but7.setOnAction(clicked);
		but8 = new Button("8");
		but8.setMaxWidth(Double.MAX_VALUE);
		numBut.add(but8);
		but8.setOnAction(clicked);
		but9 = new Button("9");
		but9.setMaxWidth(Double.MAX_VALUE);
		numBut.add(but9);
		but9.setOnAction(clicked);
		but0 = new Button("0");
		but0.setMaxWidth(Double.MAX_VALUE);
		numBut.add(but0);
		but0.setOnAction(clicked);
		butAdd = new Button("+");
		butAdd.setMaxWidth(Double.MAX_VALUE);
		opBut.add(butAdd);
		butAdd.setOnAction(clicked);
		butSub = new Button("-");
		butSub.setMaxWidth(Double.MAX_VALUE);
		opBut.add(butSub);
		butSub.setOnAction(clicked);
		butMult = new Button("x");
		butMult.setMaxWidth(Double.MAX_VALUE);
		opBut.add(butMult);
		butMult.setOnAction(clicked);
		butDiv = new Button("/");
		butDiv.setMaxWidth(Double.MAX_VALUE);
		opBut.add(butDiv);
		butDiv.setOnAction(clicked);
		butEq = new Button("=");
		butEq.setMaxHeight(Double.MAX_VALUE);
		butEq.setMaxWidth(Double.MAX_VALUE);
		butEq.setOnAction(clicked);
		prevBut = butEq;
		butDot = new Button(".");
		butDot.setMaxWidth(Double.MAX_VALUE);
		numBut.add(butDot);
		butDot.setOnAction(clicked);
		butClr = new Button("C");
		butClr.setMaxWidth(Double.MAX_VALUE);
		butClr.setMaxHeight(Double.MAX_VALUE);
		butClr.setOnAction(clicked);
		grid.add(lbl,0,0,4,1);
		grid.add(butAdd,0,1,1,1);
		grid.add(butSub,1,1,1,1);
		grid.add(butMult,2,1,1,1);
		grid.add(butDiv,3,1,1,1);
		grid.add(but9,2,2,1,1);
		grid.add(but8,1,2,1,1);
		grid.add(but7,0,2,1,1);
		grid.add(butClr,3,2,1,2);
		grid.add(but6,2,3,1,1);
		grid.add(but5,1,3,1,1);
		grid.add(but4,0,3,1,1);
		grid.add(but3,2,4,1,1);
		grid.add(but2,1,4,1,1);
		grid.add(but1,0,4,1,1);
		grid.add(but0,0,5,2,1);
		grid.add(butDot,2,5,1,1);
		grid.add(butEq,3,4,1,2);
		stage.setScene(scene);
		stage.show();
	}
	public EventHandler<ActionEvent> clicked = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			Button x = (Button) e.getSource();
			String y = x.getText();
			if (numBut.contains(x)) {
				display = display.concat(y);
				curFloat = curFloat.concat(y);
			}else if (opBut.contains(x)) {
				if (numBut.contains(prevBut)||(prevBut == butEq && curFloat != "")) {
					operands.add(new Float(curFloat));
					operators.add(y);
					display = display.concat(y);
					curFloat = "";
				}else if (opBut.contains(prevBut)) {
					operators.set(operators.size()-1,y);
					display = display.substring(0,display.length()-1);
					display = display.concat(y);
				}
			}else if(x==butEq) {
				if (display.length() > 1) {
					if (numBut.contains(prevBut)) {
						operands.add(new Float(curFloat));
					}
					total+=operands.get(0);
					for (int j = 0; j<operands.size()-1; j++) {
						Operator op = OpFactory.getOp(operators.get(j));
						total = op.doOp(total,operands.get(j+1));
					}
					display = (""+ total);
					operands.clear();
					operators.clear();
					curFloat = (""+total);
					total = 0;
				}
			}else if(x==butClr) {
				operands.clear();
				operators.clear();
				curFloat = "";
				display = "";
			}
			lbl.setText(display);
			prevBut = x;
		}
	};
}

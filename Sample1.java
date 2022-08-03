import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.Event;

// 電卓のプログラム
public class Sample1 extends Application{
	// baseNumber に対して nextNumber を足したり引いたりする。
	int baseNumber=0;
	int nextNumber=0;
	// 足すか引くかは operator で場合分けできるようにする
	String operator="代入";
	// 電卓に配置するボタンやディスプレイの作成
	TextField displayField = new TextField();			// 数値ディスプレイ。
	TextField operatorField = new TextField(operator);	// 演算子ディスプレイ。
	Button[] numberButton = new Button[10];		// 0~9ボタン
	Button equalButton = new Button("＝");		// "="ボタン
	Button plusButton = new Button("＋");		// "+"ボタン
	Button minusButton = new Button("ー");		// "-"ボタン追加
	Button kakeButton = new Button("*");		// "*"ボタン追加
	// startメソッド
	public void start(Stage stage){
		// ボタンを配置するペイン
		GridPane pane1 = new GridPane();
		// 0~9ボタン
		for(int i=0;i<numberButton.length;i++){
			numberButton[i] = new Button(""+i);
			numberButton[i].setPrefSize(40,40);
			numberButton[i].setOnAction(event -> pressedNumberButton(event));
			pane1.getChildren().add(numberButton[i]);
		}
		// "=","+","-"ボタン
		equalButton.setPrefSize(40,40);
		plusButton.setPrefSize(40,40);
		minusButton.setPrefSize(40,40);		//追加
		kakeButton.setPrefSize(40,40);		//追加
		equalButton.setOnAction(event -> pressedEqualButton());
		plusButton.setOnAction(event -> pressedPlusButton());
		minusButton.setOnAction(event -> pressedMinusButton());		//追加
		kakeButton.setOnAction(event -> pressedKakeButton());		//追加
		pane1.getChildren().add(equalButton);
		pane1.getChildren().add(plusButton);
		pane1.getChildren().add(minusButton);		//追加
		pane1.getChildren().add(kakeButton);		//追加
		// 0~9ボタンの座標決定
		pane1.setConstraints(numberButton[0],0,3);
		pane1.setConstraints(numberButton[1],0,2);
		pane1.setConstraints(numberButton[2],1,2);
		pane1.setConstraints(numberButton[3],2,2);
		pane1.setConstraints(numberButton[4],0,1);
		pane1.setConstraints(numberButton[5],1,1);
		pane1.setConstraints(numberButton[6],2,1);
		pane1.setConstraints(numberButton[7],0,0);
		pane1.setConstraints(numberButton[8],1,0);
		pane1.setConstraints(numberButton[9],2,0);
		pane1.setConstraints(equalButton,3,3);
		pane1.setConstraints(plusButton,3,2);
		pane1.setConstraints(minusButton,3,1);
		pane1.setConstraints(kakeButton,3,0);
		
		// 数値などを表示するディスプレイ
		FlowPane pane2 = new FlowPane(); //ディスプレイを配置するペイン
		pane2.setPrefSize(160,40);
		displayField.setPrefSize(120,40);
		operatorField.setPrefSize(40,40);
		pane2.getChildren().add(displayField);
		pane2.getChildren().add(operatorField);
		// ディスプレイとボタンペインをルートペインへ配置
		VBox pane = new VBox();
		pane.getChildren().add(pane2);
		pane.getChildren().add(pane1);
		
		// ウィンドウ表示等
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}
	// ↓メソッド
	// ディスプレイに数値と演算子を表示
	void display(int n){
		displayField.setText(""+n);
		operatorField.setText(operator);
	}
	// 計算の確定
	void calclate(){
		// operatorの値に応じて計算方法を場合分け
		if(operator.equals("代入")){
			baseNumber = nextNumber;
		}else if(operator.equals("＋")){
			baseNumber += nextNumber;
		}else if(operator.equals("－")){ 	//追加
			baseNumber -= nextNumber;
		}else if(operator.equals("*")){ 	//追加
			baseNumber *= nextNumber;
		}
		nextNumber = 0;		// nextNumberのリセット
		display(baseNumber);// baseNumberの表示
	}
	// 0~9ボタンが押されたときに実行されるメソッド
	void pressedNumberButton(Event event){
		for(int i=0;i<numberButton.length;i++){
			// 押されたボタンがnumberButton[i]かどうかを判定するif文
			if(event.getSource()==numberButton[i]){
				System.out.println("["+i+"]が押されました。");
				nextNumber = nextNumber*10+i;
				display(nextNumber);
			}
		}
	}
	// "＝"ボタンが押されたときに実行されるメソッド
	void pressedEqualButton(){
		System.out.println("[＝]が押されました。");
		calclate();
		operator = "代入";
		display(baseNumber);
	}
	// "＋"ボタンが押されたときに実行されるメソッド
	void pressedPlusButton(){
		System.out.println("[＋]が押されました。");
		calclate();
		operator = "＋";
	}
	// "-"ボタンが押されたときに実行されるメソッド	追加
	void pressedMinusButton(){
		System.out.println("[－]が押されました。");
		calclate();
		operator = "－";
	}
	// "*"ボタンが押されたときに実行されるメソッド	追加
	void pressedKakeButton(){
		System.out.println("[*]が押されました。");
		calclate();
		operator = "*";
	}
	// mainメソッド
	public static void main(String args[]){
		launch();
	}
}

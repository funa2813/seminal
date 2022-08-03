import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.Event;
import javafx.scene.input.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// ペイントのプログラム
public class Sample2 extends Application{
	// キャンバスの設定
	Canvas canvas = new Canvas(800,600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	// 描画する図形を管理する変数（Figureは下で宣言したオリジナルのクラス）
	Figure figure = new Figure();
	// ボタン等のコントロールの作成
	ChoiceBox<String> colorChoice = new ChoiceBox<>();
	ChoiceBox<String> shapeChoice = new ChoiceBox<>();
	Button button = new Button("クリアボタン");//未使用（自由に使ってください）
	Slider slider = new Slider(0,100,50);//未使用（自由に使ってください）
	// startメソッド
	public void start(Stage stage){
		// キャンバスに対するマウスのイベントハンドラの登録
		canvas.setOnMouseClicked(event -> mouseClicked(event));
		canvas.setOnMouseEntered(event -> mouseEntered(event));
		canvas.setOnMouseExited(event -> mouseExited(event));
		canvas.setOnMouseDragged(event -> mouseDragged(event));
		canvas.setOnMouseMoved(event -> mouseMoved(event));
		canvas.setOnMousePressed(event -> mousePressed(event));
		canvas.setOnMouseReleased(event -> mouseReleased(event));
		
		// コントロールに対するイベントハンドラの登録
		button.setOnAction(event -> buttonPressed());
		slider.setOnMouseClicked(event -> sliderClicked());
		slider.setOnMousePressed(event -> sliderPressed());
		slider.setOnMouseReleased(event -> sliderReleased());
		
		// チョイスボックスの設定
		colorChoice.getItems().add("黒");
		colorChoice.getItems().add("赤");
		colorChoice.getItems().add("青");	//追加
		colorChoice.setValue("黒");
		shapeChoice.getItems().add("円");
		shapeChoice.getItems().add("四角");	//追加
		shapeChoice.setValue("円");
		
		// コントロールの配置
		FlowPane pane1 = new FlowPane();//コントロールを配置するペイン
		pane1.getChildren().add(colorChoice);
		pane1.getChildren().add(shapeChoice);
		pane1.getChildren().add(button);
		pane1.getChildren().add(slider);
		
		// コントロールのペインとキャンバスをルートペインへ配置
		VBox pane = new VBox();
		pane.getChildren().add(pane1);
		pane.getChildren().add(canvas);
		
		// ウィンドウの表示等
		Scene scene = new Scene(pane);
		stage.setTitle("ペイント");//タイトルの設定
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}
	// クリックされたときに実行されるメソッド
	void mouseClicked(MouseEvent event){
		int x=(int)event.getX();
		int y=(int)event.getY();
		System.out.print("クリックされました。");
		System.out.println("\t\t座標：("+x+","+y+")");
	}
	// マウスアイコンが入ったときに実行されるメソッド
	void mouseEntered(MouseEvent event){
		int x=(int)event.getX();
		int y=(int)event.getY();
		System.out.print("マウスアイコンが入りました。");
		System.out.println("\t座標：("+x+","+y+")");
	}
	// マウスアイコンが出たときに実行されるメソッド
	void mouseExited(MouseEvent event){
		int x=(int)event.getX();
		int y=(int)event.getY();
		System.out.print("マウスアイコンが出ました。");
		System.out.println("\t座標：("+x+","+y+")");
	}
	// ドラッグされたときに実行されるメソッド
	void mouseDragged(MouseEvent event){
		int x=(int)event.getX();
		int y=(int)event.getY();
		figure.draw(x,y);
	}
	// マウスアイコンが動いたときに実行されるメソッド
	void mouseMoved(MouseEvent event){
		int x=(int)event.getX();
		int y=(int)event.getY();
	}
	// 左クリックが押されたときに実行されるメソッド
	void mousePressed(MouseEvent event){
		int x=(int)event.getX();
		int y=(int)event.getY();
		System.out.print("左クリックが押されました。");
		System.out.println("\t座標：("+x+","+y+")");
		figure.draw(x,y);
	}
	// 左クリックが離されたときに実行されるメソッド
	void mouseReleased(MouseEvent event){
		int x=(int)event.getX();
		int y=(int)event.getY();
		System.out.print("左クリックが離されました。");
		System.out.println("\t座標：("+x+","+y+")");
	}
	// ボタンが押されたときに実行されるメソッド
	void buttonPressed(){
		System.out.println("ボタンが押されました。");
		gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
	}
	// スライダーがクリックされたときに実行されるメソッド
	void sliderClicked(){
		int value=(int)slider.getValue();
		System.out.print("スライダーがクリックされました。");
		System.out.println("\t値："+value);
	}
	// スライダーが押されたときに実行されるメソッド
	void sliderPressed(){
		int value=(int)slider.getValue();
		System.out.print("スライダーが押されました。");
		System.out.println("\t\t値："+value);
	}
	// スライダーが離されたときに実行されるメソッド
	void sliderReleased(){
		int value=(int)slider.getValue();
		System.out.print("スライダーが離されました。");
		System.out.println("\t\t値："+value);
	}
	// 描画図形のクラス
	class Figure{
		// 図形の描画
		void draw(int x,int y){
			// 図形のサイズ決定
			int width = 40;
			// 図形の色決定
			Color color = Color.BLACK;
			if(colorChoice.getValue()=="黒"){
				color = Color.BLACK;
			}if(colorChoice.getValue()=="赤"){
				color = Color.RED;
			}if(colorChoice.getValue()=="青"){	//追加
				color = Color.BLUE;
			}
			// 図形の描画
			gc.setFill(color);
			if(shapeChoice.getValue()=="円"){
				gc.fillOval(x-width/2,y-width/2,width,width);
			}if(shapeChoice.getValue()=="四角"){	//追加
				gc.fillRect(x-width/20,y-width/20,width,width);
			}
		}
	}
	// mainメソッド
	public static void main(String args[]){
		launch();
	}
}

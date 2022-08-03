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
import javafx.scene.shape.ArcType;

// 駒が２次元マス上を移動するゲーム
public class Sample3 extends Application{
	// キャンバスの設定
	Canvas canvas = new Canvas(800,600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	// ２次元マスの設定
	int width=50;					// マスの幅の長さ
	Tile[][] tile = new Tile[8][8];	// 各8×8マスの作成
	// プレイヤー（駒）の作成
	Player player = new Player();
	// startメソッド
	public void start(Stage stage){
		// 各マスの作成
		for(int i=0;i<tile.length;i++){
			for(int j=0;j<tile[i].length;j++){
				tile[i][j] = new Tile(i,j,false,false);
			}
		}
		//hasWall
		tile[7][0].hasWall = true;
		tile[7][1].hasWall = true;
		tile[6][2].hasWall = true;
		tile[6][3].hasWall = true;
		tile[6][4].hasWall = true;
		tile[6][5].hasWall = true;
		tile[5][4].hasWall = true;
		tile[4][4].hasWall = true;
		tile[4][5].hasWall = true;
		tile[4][7].hasWall = true;
		tile[3][3].hasWall = true;
		tile[3][0].hasWall = true;
		tile[3][1].hasWall = true;
		tile[2][3].hasWall = true;
		tile[2][6].hasWall = true;
		tile[2][7].hasWall = true;
		tile[0][3].hasWall = true;
		tile[0][2].hasWall = true;
		//hasFood
		tile[0][6].hasFood = true;
		tile[0][7].hasFood = true;
		tile[1][5].hasFood = true;
		tile[1][6].hasFood = true;
		tile[2][1].hasFood = true;
		tile[2][2].hasFood = true;
		tile[3][6].hasFood = true;
		tile[4][0].hasFood = true;
		tile[5][5].hasFood = true;
		tile[7][7].hasFood = true;
		tile[4][0].hasFood = true;
		tile[5][0].hasFood = true;
		tile[6][0].hasFood = true;
		tile[4][1].hasFood = true;
		tile[5][1].hasFood = true;
		tile[6][1].hasFood = true;
		tile[7][2].hasFood = true;
		tile[7][4].hasFood = true;
		drawAll();
		
		// 説明文の描画
		gc.setFill(Color.BLACK);
		gc.fillText("矢印キー：移動（↑↓は未実装）",400,100);
		
		// pane1の作成（未使用、自由に使ってください）
		FlowPane pane1 = new FlowPane();
		
		// paneとキャンバスをルートペインへ配置
		VBox pane = new VBox();
		pane.getChildren().add(pane1);
		pane.getChildren().add(canvas);
		
		// キーのイベントハンドラの登録
		Scene scene = new Scene(pane);
		scene.setOnKeyTyped(event -> keyTyped(event));
		scene.setOnKeyPressed(event -> keyPressed(event));
		scene.setOnKeyReleased(event -> keyReleased(event));
		
		// ウィンドウの表示等
		stage.setScene(scene);
		stage.setTitle("２次元マスを移動するゲーム");//タイトルの設定
		stage.sizeToScene();
		stage.show();
	}
	// 文字がタイプされたら実行されるメソッド
	void keyTyped(KeyEvent event){
		String text = event.getCharacter();
		System.out.print("タイプされました。");
		System.out.println("\tString：\t"+text);
	}
	// キーが押されたら実行されるメソッド
	void keyPressed(KeyEvent event){
		KeyCode key = event.getCode();
		System.out.print("キーが押されました。");
		System.out.println("\tKeyCode：\t"+key);
		// 押された矢印キーに応じて駒の移動や向きの変更
		if(key==KeyCode.RIGHT){
			player.moveTo(player.x+1,player.y);
			player.direction=1;
		}else if(key==KeyCode.LEFT){
			player.moveTo(player.x-1,player.y);
			player.direction=3;
		}else if(key==KeyCode.UP){
			player.moveTo(player.x,player.y-1);
			player.direction=2;
		}else if(key==KeyCode.DOWN){
			player.moveTo(player.x,player.y+1);
			player.direction=4;
		}
		drawAll();	
	}
	// キーが離されたら実行されるメソッド
	void keyReleased(KeyEvent event){
		KeyCode key = event.getCode();
		System.out.print("キーが離されました。");
		System.out.println("\tKeyCode：\t"+key);
	}
	// ↓その他メソッド
	// 駒とすべてのマスの描画
	void drawAll(){
		for(int i=0;i<tile.length;i++){
			for(int j=0;j<tile[i].length;j++){
				tile[i][j].draw();
			}
		}
		player.draw();
	}
	// マスのクラス
	class Tile{
		int x,y;			// マスの配置場所
		boolean hasWall;	// 壁が配置されているかの真偽値
		boolean hasFood;	// 餌が配置されているかの真偽値
		Tile(int x,int y,boolean b1,boolean b2){
			this.x = x;
			this.y = y;
			this.hasWall = b1;
			this.hasFood = b2;
		}
		// マスの描画
		void draw(){
			gc.setFill(Color.BLACK);
			gc.fillRect(x*width,y*width,width,width);
			// 壁や餌が配置されていたら追加描画
			if(hasWall){
				gc.setFill(Color.CYAN);
				gc.fillRect(x*width,y*width,width,width);
			}else if(hasFood){
				gc.setFill(Color.ORANGE);
				gc.fillRect(x*width+width/3,y*width+width/3,width/3,width/3);
			}
		}
	}
	// プレイヤー（駒）のクラス
	class Player{
		int x=0;
		int y=0;
		int direction=1; //1＝右、2＝上、3＝左、4＝下 とする。
		// 駒の移動（座標変更）
		void moveTo(int x,int y){
			// 移動先に壁や餌があるかどうかで場合分け
			if(!tile[x][y].hasWall){
				this.x = x;
				this.y = y;
			}
			if(tile[x][y].hasFood){
				tile[x][y].hasFood=false;
			}
		}
		// 駒の描画
		void draw(){
			gc.setFill(Color.YELLOW);
			// 向いている方向によって描画する内容を変更
			if(direction==1){
				gc.fillArc(x*width,y*width,width,width,30,300,ArcType.ROUND);
			}else if(direction==3){
				gc.fillArc(x*width,y*width,width,width,210,300,ArcType.ROUND);
			}else if(direction==2){//UP
				gc.fillArc(x*width,y*width,width,width,120,300,ArcType.ROUND);
			}else if(direction==4){//DOWN
				gc.fillArc(x*width,y*width,width,width,300,300,ArcType.ROUND);
			}
		}
	}
	// mainメソッド
	public static void main(String args[]){
		launch();
	}
}
